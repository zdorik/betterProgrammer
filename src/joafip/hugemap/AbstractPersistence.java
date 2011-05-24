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

import java.io.File;

import net.sf.joafip.heapfile.entity.EnumFileState;
import net.sf.joafip.service.FilePersistence;
import net.sf.joafip.service.FilePersistenceClassNotFoundException;
import net.sf.joafip.service.FilePersistenceDataCorruptedException;
import net.sf.joafip.service.FilePersistenceException;
import net.sf.joafip.service.FilePersistenceInvalidClassException;
import net.sf.joafip.service.FilePersistenceNotSerializableException;
import net.sf.joafip.store.service.StoreClassNotFoundException;

/**
 * generic for persistence manager creation
 * 
 * @author luc peuvrier
 * 
 */
public abstract class AbstractPersistence {// NOPMD

	/** where store files relative to current directory */
	private static final String RUNTIME_DIR = "runtime";

	/** crash safe mode */
	private static final boolean CRASH_SAFE_MODE = false;

	/** garbage management mode */
	private static final boolean GARBAGE = false;

	/** read write file cache page size */
	private static final int PAGE_SIZE = 16 * 1024;

	/** read write file cache number of pages */
	private static final int NUMBER_OF_PAGE = 1000;

	protected AbstractPersistence() {
		super();
	}

	/**
	 * creation of file persistence manager
	 * 
	 * @return file persistence manager
	 * @throws FilePersistenceException
	 *             failed create file persistence manager
	 * @throws FilePersistenceDataCorruptedException
	 * @throws FilePersistenceClassNotFoundException
	 * @throws FilePersistenceNotSerializableException
	 * @throws FilePersistenceInvalidClassException
	 * @throws StoreClassNotFoundException
	 */
	protected FilePersistence createFilePersistence(final boolean removeFiles)
			throws FilePersistenceException,
			FilePersistenceInvalidClassException,
			FilePersistenceNotSerializableException,
			FilePersistenceClassNotFoundException,
			FilePersistenceDataCorruptedException, StoreClassNotFoundException {
		new File(RUNTIME_DIR).mkdirs();
		FilePersistence filePersistence;
		try {
			filePersistence = newFilePersistence(removeFiles);
		} catch (FilePersistenceException exception) {
			final EnumFileState fileState = FilePersistence
					.fileState(exception);
			switch (fileState) {
			case STATE_OK:
				/* exception is not relative to file state */
				throw exception;
			case STATE_CORRUPTED:
				/* file content corrupted */
				throw exception;
			case STATE_RESTORED_DATA_LOST:
				filePersistence = newFilePersistence(removeFiles);
				break;
			case STATE_RESTORED_NO_DATA_LOST:
				filePersistence = newFilePersistence(removeFiles);
				break;
			case STATE_UNSTABLE:
				/* need file maintenance */
				throw exception;
			default:
				throw new FilePersistenceException("unknow state " + fileState,
						exception);
			}
		}
		return filePersistence;
	}

	private FilePersistence newFilePersistence(final boolean removeFiles)
			throws FilePersistenceException,
			FilePersistenceInvalidClassException,
			FilePersistenceNotSerializableException,
			FilePersistenceClassNotFoundException,
			FilePersistenceDataCorruptedException, StoreClassNotFoundException {
		FilePersistence filePersistence;
		filePersistence = new FilePersistence(1, null, RUNTIME_DIR,
		/**/removeFiles,
		/**/PAGE_SIZE, NUMBER_OF_PAGE,
		/**/GARBAGE,
		/**/CRASH_SAFE_MODE);
		return filePersistence;
	}
}
