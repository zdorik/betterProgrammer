package fizzbuzz;

public class FizzBuzz {

	//Напишите программу, которая выводит на экран числа от 1 до 100. 
	//При этом вместо чисел, кратных трем, программа должна выводить слово «Fizz», 
	//а вместо чисел, кратных пяти — слово «Buzz». Если число кратно и 3, и 5, 
	//то программа должна выводить слово «FizzBuzz»	
	public void printFizzBuzz() {
	
		for(int i=1; i<=100; i++) {
			if (isMultipleOfThreeAndFive(i)) {
				System.out.println("FizzBuzz");
			}
			else if (isMultipleOfThree(i)) {
				System.out.println("Fizz");
			}
			else if (isMultipleOfFive(i)) {
				System.out.println("Buzz");
			}			
			else {
				System.out.println(i);
			}
		}
	}
	
	private boolean isMultipleOfFive(int i) {			
		return (i % 5 == 0);
	}
	
	private boolean isMultipleOfThree(int i) {			
		return (i % 3 == 0);
	}
	
	private boolean isMultipleOfThreeAndFive(int i) {			
		return (i % 5 == 0 && i % 3 == 0);
	}		
		

}
