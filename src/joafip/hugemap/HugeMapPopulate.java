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
import net.sf.joafip.java.util.PTreeMap;
import net.sf.joafip.service.FilePersistence;
import net.sf.joafip.service.FilePersistenceClassNotFoundException;
import net.sf.joafip.service.FilePersistenceDataCorruptedException;
import net.sf.joafip.service.FilePersistenceException;
import net.sf.joafip.service.FilePersistenceInvalidClassException;
import net.sf.joafip.service.FilePersistenceNotSerializableException;
import net.sf.joafip.service.IDataAccessSession;
import net.sf.joafip.store.service.StoreClassNotFoundException;

/**
 * populate a persisted map with on million entries<br>
 * Its take 1 second for 1000 entry append on pc with ide ultra dma 2 disk<br>
 * 
 * @author luc peuvrier
 * 
 */
public final class HugeMapPopulate extends AbstractPersistence {


	public static void main(final String[] args) {
		final HugeMapPopulate hugeMapPopulate = new HugeMapPopulate();
		try {
			hugeMapPopulate.populate();
		} catch (Exception e) {
			e.printStackTrace();// NOPMD
		}
	}

	private HugeMapPopulate() {
		super();
	}

	/**
	 * add entries du persistent map<br>
	 * remove previous data file<br>
	 * each 1000 append save the data, after save no more object keep in memory<br>
	 * 
	 * @throws FilePersistenceException
	 * @throws FilePersistenceDataCorruptedException
	 * @throws FilePersistenceClassNotFoundException
	 * @throws FilePersistenceNotSerializableException
	 * @throws FilePersistenceInvalidClassException
	 * @throws StoreClassNotFoundException
	 */
	private void populate() throws FilePersistenceException, 
			FilePersistenceInvalidClassException,
			FilePersistenceNotSerializableException,
			FilePersistenceClassNotFoundException,
			FilePersistenceDataCorruptedException, StoreClassNotFoundException {
		/* create file persistence manager */
		final FilePersistence filePersistence =
		/**/createFilePersistence(true/* remove files */);
		/* create a data access session */
		final IDataAccessSession session = filePersistence
				.createDataAccessSession();
		/* get operation start time */
		final long startTime = System.currentTimeMillis();
		/* starting open session */
		session.open();
		/*
		 * create the map in memory and add it to persisted object
		 */
		Map<Integer, String> map = new PTreeMap<Integer, String>();
		session.setObject("map", map);
		int packet = 0;
		/*
		 * add one million of entry in the persisted map
		 */
		for (int index = 0; index < 1000000; index++) {
			/* get the persisted map */
			map = getMap(session);
			/* add an entry */
			map.put(index, String.valueOf(index));
			/*
			 * for each 10000 append: close the session saving data, then reopen
			 * session
			 */
			if (index / 10000 > packet) {
				/*
				 * after 1000 add, save in file. Object in memory will be
				 * detached
				 */
				final int numberOfObjectStateBeforeSave = filePersistence
						.numberOfObjectState();

				/*
				 * saving close of data access session
				 */
				session.closeAndWait(EnumFilePersistenceCloseAction.SAVE);

				/*
				 * log information about persistent objects
				 */
				final int modified = filePersistence.getNumberOfModified();
				final int visited = filePersistence.getNumberOfVisited();
				/*
				 * to show that for appends all the map is not loaded in memory
				 */
				System.out.println(// NOPMD
						(index + 1) + " appened, "
								+ (System.currentTimeMillis() - startTime)
								+ " mS elapsed, "
								+ numberOfObjectStateBeforeSave
								+ " object loaded\n" + visited + " visited, "
								+ modified + " modified, ");

				/* unreference the map => will be garbaged by VM */
				map = null;// NOPMD

				/* re open for next append */
				session.open();
				packet++;
			}
		}
		/* ending saving close */
		session.closeAndWait(EnumFilePersistenceCloseAction.SAVE);

		filePersistence.close();
	}

	@SuppressWarnings("unchecked")
	private Map<Integer, String> getMap(final IDataAccessSession session)
			throws FilePersistenceException {
		return (Map<Integer, String>) session.getObject("map");
	}
}
