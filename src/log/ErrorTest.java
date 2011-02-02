package log;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.log4j.Logger;

public class ErrorTest {

	private static final Logger log = Logger.getLogger(ErrorTest.class);
	
	public void readFile() {
		File file = new File("test");
		try {
			InputStream in = new BufferedInputStream(new FileInputStream(file));
		} catch(Exception e) {
			log.error(e);
		}
	}

}
