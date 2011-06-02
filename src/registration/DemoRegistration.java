package registration;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;

import com.google.common.collect.TreeMultimap;
import com.google.common.collect.TreeMultiset;

public class DemoRegistration {
	
	private static TreeMultiset<Result> results = TreeMultiset.create();
	private static HashMap<Integer, Integer> specialities = new HashMap<Integer, Integer>();
	private static TreeMultimap<Integer, Integer> priorities = TreeMultimap.create();
	private static TreeMultimap<Integer, State> states = TreeMultimap.create();

	public static void main(String[] args) {
		init();
		System.out.println(results);
		System.out.println(specialities);
		enrol();
		System.out.println(results);
		System.out.println(specialities);
		
	}
	
	private static void init() {
		results.add(new Result(1,1,1,1,1));
		results.add(new Result(2,1,2,12,2));
		results.add(new Result(3,1,3,16,3));
		results.add(new Result(4,1,4,4,4));
		results.add(new Result(5,1,5,5,5));
		results.add(new Result(6,2,1,10,1));
		results.add(new Result(7,2,2,22,2));
		results.add(new Result(8,2,3,6,3));
		results.add(new Result(9,3,1,21,1));
		results.add(new Result(10,3,2,60,2));
		results.add(new Result(11,3,3,26,3));
		results.add(new Result(12,4,1,27,1));
		results.add(new Result(13,4,2,28,2));
		results.add(new Result(14,5,1,31,1));
		results.add(new Result(15,5,2,33,2));
		results.add(new Result(16,6,1,44,1));
		results.add(new Result(17,6,2,40,2));
		results.add(new Result(18,7,1,42,2));
		results.add(new Result(19,7,2,43,1));
		results.add(new Result(20,8,1,46,2));
		results.add(new Result(21,8,2,47,1));
		results.add(new Result(22,9,3,35,2));
		results.add(new Result(23,9,4,36,1));
		results.add(new Result(24,10,1,9,2));
		results.add(new Result(25,10,5,8,1));		
		System.out.println(results);
		
		specialities.put(1, 3);
		specialities.put(2, 3);
		specialities.put(3, 3);
		specialities.put(4, 3);
		specialities.put(5, 3);
		System.out.println(specialities);
		getPriorities();
		System.out.println(priorities);
	
	}
	

	private static void filterEnrolApplicants(int step) {
		for(Result result : results) {
			SortedSet<Integer> prioritiesForApplicant = priorities.get(result.getApplicant());
			Object[] priors = prioritiesForApplicant.toArray();
			Integer maxPriority = (Integer) priors[step];
			//System.out.println(statesForApplicant);
			if (specialities.get(result.getSpeciality()).compareTo(0) == 1
					&& maxPriority.compareTo(result.getPriority()) == 0) {
				result.setEnrol(true);
				Integer newLimit = specialities.get(result.getSpeciality());
				--newLimit;
				specialities.put(result.getSpeciality(), newLimit);
			}
		}
	}
	
	public static void enrol() {
		int step = 1;
		while (specialities.get(1) != 0 
				&& specialities.get(2) != 0 
				&& specialities.get(3) != 0 
				&& specialities.get(4) != 0 
				&& specialities.get(5) != 0 ) {
			
			filterEnrolApplicants(step);
			step++;
		}
	}
	
	
	private static void getPriorities() {
		for(Result result : results) {
			priorities.put(result.getApplicant(), result.getPriority());
		}
	}
}
