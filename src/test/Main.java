package test;

import log.ConcatTest;
import log.ErrorTest;


public class Main {
	
	public static void main(String[] args) {
		tasks.task1.BetterProgrammerTask task1 = new tasks.task1.BetterProgrammerTask();
		System.out.println(task1.getSumOfNumbers("12 some text 3 example 9 8  7"));

		tasks.task2.BetterProgrammerTask task2 = new tasks.task2.BetterProgrammerTask();
		System.out.println(task2.isPalindrome("reifier"));
		
//		ErrorTest err = new ErrorTest();
//		err.readFile();
//		
//		ConcatTest concat = new ConcatTest();
//		concat.concatTestForString("str");
//		concat.concatTestForStringBuffer("str");
		


	}

}
