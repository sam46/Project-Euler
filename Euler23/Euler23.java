import java.util.ArrayList;
import java.util.List;


// I'll make a list of all numbers that are sums of 2 abundants:
// start by having an array with the elements {1,2,3,4,5.....upperLimit}  .
// Remove from it any number that can be written as a sum of 2 abundants (be setting it equal to 0 or -1)
// you'll be left with an array of all positive integers that cannot be written as a sum of 2 abundants.
// now the final answer is to sum it up.
// :D

public class Euler23 {
	static int upperLimit = 28123;
	static Integer[] abundants = null;
	static int[] naturals = new int[28123+1];
	
	// return a list of all abundants up to the upper limit.
	static List<Integer> fill(){ 
		List<Integer> abd = new ArrayList<Integer>();
		for (int i = 0; i <= upperLimit; i++) {
			naturals[i] = i; // this array has nothing to do with the abundants list, I just threw it here to take advantage of the for loop and speed up the execution time of the program.
			if(isAbundant(i))
				abd.add(i);
		}
		
		return abd;
	}
	
	
	public static void main(String[] args) {
		abundants = fill().toArray(new Integer[0]);
		
		// Remove any/all possible sums of 2 abundants from naturals[]
		for (int i = 0; i < abundants.length; i++) {
			for (int j = 0; j < abundants.length; j++) {
				int x =abundants[i], y = abundants[j];
				if(x+y <= upperLimit) // this condition is not necessary, they told us that any number that can be written as a sum of 2 abundants has to be less than 28123
					naturals[x+y] = 0;
			}
		}
		
		// now just sum'em all up xD
		long sum=0;
		for (int i = 0; i < naturals.length; i++) 	
			sum += naturals[i];
		System.out.println(sum);
		

	}

	
	
	static boolean isAbundant(int n){
		if(d(n)>n)
			return true;
		return false;
	}
	
	
	
	
	
	
	
	// Functions factors() and d() are from Problem 21:
	
	// this is the same function I used in problem 13(or 3 maybe), but with some modifications.
	static List<Integer> factors(int x){
		int n = (int) Math.ceil(Math.sqrt(x));
		List<Integer> facts = new ArrayList<Integer>();
		
		for (int i = 1; i < n; i++) {
			if(x%i==0 && i!=x)
				facts.add(i);
		}
		
		int length = facts.size();
		for (int i = 0; i < length; i++) {
			if((x/ facts.get(i)) != x )
				facts.add(x/ facts.get(i));
		}
		
		if(Math.ceil(Math.sqrt(x)) == Math.sqrt(x)  && Math.sqrt(x)!=x)
			facts.add((int)Math.sqrt(x));
		return facts;
	}
	static int d(int n){
		Integer[] properDivisors = null;
		properDivisors = factors(n).toArray(new Integer[0]);
		int sum = 0;
		for (int i = 0; i < properDivisors.length; i++) {
			sum+= properDivisors[i];
		}
		return sum;
	}
	
}
