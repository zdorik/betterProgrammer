package fizzbuzz;

public class FizzBuzz {

	//�������� ���������, ������� ������� �� ����� ����� �� 1 �� 100. 
	//��� ���� ������ �����, ������� ����, ��������� ������ �������� ����� �Fizz�, 
	//� ������ �����, ������� ���� � ����� �Buzz�. ���� ����� ������ � 3, � 5, 
	//�� ��������� ������ �������� ����� �FizzBuzz�	
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
