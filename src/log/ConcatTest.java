package log;

import org.apache.log4j.Logger;
import org.perf4j.LoggingStopWatch;
import org.perf4j.StopWatch;

public class ConcatTest {
	
	private static final Logger log = Logger.getLogger(ConcatTest.class);
	
	public void concatTestForString(String str) {
		String target = "";
		StopWatch stopWatch = new LoggingStopWatch();
		for (int i=0; i<20000; i++){
			target += "|" + str;
		}
		log.info(str);
		stopWatch.stop("concatTestForString");
	}
	
	public void concatTestForStringBuffer(String str) {
		StringBuffer target = new StringBuffer();
		StopWatch stopWatch = new LoggingStopWatch();
		for (int i=0; i<20000; i++){
			target.append("|").append(str);
		}
		log.info(str);
		stopWatch.stop("concatTestForStringBuffer");
	}	

}
