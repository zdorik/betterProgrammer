package task2;

public class BetterProgrammerTask {
	
    public static boolean isPalindrome(String s) {
        /*
          Definition: A palindrome is a string that reads the same forward and backward.
          For example, "abcba" is a palindrome, "abab" is not.
          Please implement this method to
          return true if the parameter is a palindrome and false otherwise.
         */

    	int countComparison = s.length()/2;
    	int countCoincidence = 0;
    	for (int i=0, j=s.length()-1; i < countComparison; i++, j--) {
    		//convert char to uppercase and then compare
    		if (Character.toUpperCase(s.charAt(i)) == Character.toUpperCase(s.charAt(j))) {
    			countCoincidence +=1;
    		}
    	}
    	
    	if (countCoincidence > 0 && (countCoincidence == countComparison)) {
    		return Boolean.TRUE;
    	} else {
    		return Boolean.FALSE;
    	}
    }	
}
