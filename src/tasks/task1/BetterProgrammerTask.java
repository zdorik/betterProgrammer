package tasks.task1;

import java.util.StringTokenizer;
import java.util.regex.Pattern;


public class BetterProgrammerTask {
	
//    public static int getSumOfNumbers(String s) {
//        /*
//          Please implement this method to
//          return the sum of all integers found in the parameter String. You can assume that
//          integers are separated from other parts with one or more spaces (' ' symbol).
//          For example, s="12 some text 3  7", result: 22 (12+3+7=22)
//         */
//    	int sum = 0;
//    	StringTokenizer st = new StringTokenizer(s);
//    	while(st.hasMoreTokens()) {
//    		String token = st.nextToken();
//    		if(Pattern.matches("^\\d$", token)) {
//    			sum += Integer.valueOf(token);
//    		}
//    	}
//    	return sum;
//    }
    

	public static int getSumOfNumbers(String s) {
        /*
          Please implement this method to
          return the sum of all integers found in the parameter String. You can assume that
          integers are separated from other parts with one or more spaces (' ' symbol).
          For example, s="12 some text 3  7", result: 22 (12+3+7=22)
         */
    	int sum = 0;//sum of all integers
    	String[] tokens = s.split("\\s");
    	for (String token : tokens) {
    		//if token is integer
    		if(Pattern.matches("^\\d$", token)) {
    			sum += Integer.valueOf(token);
    		}
    	}
    	return sum;
    }	    
}
