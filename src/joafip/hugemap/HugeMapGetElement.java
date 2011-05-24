/*
 * Copyright 2008 Luc Peuvrier
 * 
 * Licensed under the GNU LESSER GENERAL PUBLIC LICENSE
 * Licensed under the LGPL License, Version 3, 29 June 2007 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.gnu.org/licenses/lgpl.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package joafip.hugemap;

import java.util.Map;

import net.sf.joafip.entity.EnumFilePersistenceCloseAction;
import net.sf.joafip.service.FilePersistence;
import net.sf.joafip.service.FilePersistenceClassNotFoundException;
import net.sf.joafip.service.FilePersistenceDataCorruptedException;
import net.sf.joafip.service.FilePersistenceException;
import net.sf.joafip.service.FilePersistenceInvalidClassException;
import net.sf.joafip.service.FilePersistenceNotSerializableException;
import net.sf.joafip.service.IDataAccessSession;
import net.sf.joafip.store.service.StoreClassNotFoundException;

/**
 * get an element on the persisted map created by {@link HugeMapPopulate}<br>
 * This is to show that all the map is not loaded in memory<br>
 * 
 * @author luc peuvrier
 * 
 */
public final class HugeMapGetElement extends AbstractPersistence {

	public static void main(final String[] args) {
		final HugeMapGetElement hugeMapGetElement = new HugeMapGetElement();
		try {
			hugeMapGetElement.get();
		} catch (Exception e) {
			e.printStackTrace();// NOPMD
		}
	}

	private HugeMapGetElement() {
		super();
	}

	private void get() throws FilePersistenceException,
			FilePersistenceInvalidClassException,
			FilePersistenceNotSerializableException,
			FilePersistenceClassNotFoundException,
			FilePersistenceDataCorruptedException, StoreClassNotFoundException {
		/* create file persistence manager */
		final FilePersistence filePersistence =
		/**/createFilePersistence(false/* do not remove files */);
		/* create a data access session */
		final IDataAccessSession session = filePersistence
				.createDataAccessSession();
		/* get operation start time */
		final long startTime = System.currentTimeMillis();
		/* open the data access session to make able to access to persisted data */
		session.open();
		/* get the map */
		Map<Integer, String> map = getMap(session);
		/* search for a value */
		final String value = map.get(50000);
		final long duration = System.currentTimeMillis() - startTime;
		final int numberOfObjectStateBeforeSave = filePersistence
				.numberOfObjectState();
		/*
		 * for a read only it is possible to do
		 * session.closeAndWait(EnumFilePersistenceCloseAction.DO_NOT_SAVE);
		 */
		session.closeAndWait(EnumFilePersistenceCloseAction.SAVE);
		/*
		 * log information about persistent objects
		 */
		final int modified = filePersistence.getNumberOfModified();
		final int visited = filePersistence.getNumberOfVisited();
		/*
		 * to show how many object loaded in memory for a simple search on one
		 * million entries
		 */
		System.out.println(// NOPMD
				value + " " + duration + " mS elapsed, "
						+ numberOfObjectStateBeforeSave + " object loaded\n"
						+ visited + " visited, " + modified + " modified");

		/* unreference the map => will be garbaged */
		map = null;// NOPMD
		filePersistence.close();
	}

	@SuppressWarnings("unchecked")
	private Map<Integer, String> getMap(final IDataAccessSession session)
			throws FilePersistenceException {
		return (Map<Integer, String>) session.getObject("map");
	}
}
