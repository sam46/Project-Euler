import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
 	Sieve() will require a huge amount of heap memory , so I switched to int[] arrays instead of 
 	<Integer> lists. and left the zero-marked elements untouched. then I copied the resulting array primesArray
 	to a new List<integer> (without the zeroes of courese), and just like that, we have our primes in primes<>!!
 	
 	The program takes forever to finish, cuz we wanna factor every number from 2 to 100 million!!
 	So I need a better factorization alogrithm!!
 	
 	but It gives the correct answer(eventually :v).
 */

public class Euler357 {
	static List<Integer> primes = new ArrayList<Integer>();
	static int[] primesArray;
	static List<Integer> squares;
	
	public static void main(String[] args) {
		squares = new ArrayList<Integer>();
		for (int i = 2; i <= 10010; i++) 
			squares.add(i*i);		
		primesArray = sieve(100000000);
		fillPrimes();
		System.out.println("Preparations done.");

		BigInteger sum = BigInteger.ZERO;
		for (int i = 1; i <= 100000000L; i++) {
			List<Integer> facts = factors(i);
			if(check(facts, i))
				sum = sum.add(BigInteger.valueOf((long)i));
		}

		System.out.println(sum);	
	}

	static boolean check(List<Integer> divisors, int n){
		for (Integer divisor : divisors) 
			if(Collections.binarySearch(primes,divisor + n/divisor) < 0)
				return false;
		return true;
	}
	
	
	// this is the same function I used in problem 13(or 3 maybe), but with some modifications.
	static List<Integer> factors(int x){
		if(Collections.binarySearch(primes,x) >= 0){
			List<Integer> facts = new ArrayList<Integer>();
			facts.add(1);
			facts.add(x);
			return facts;
		}
		
		int n = (int) Math.ceil(Math.sqrt(x))+1;
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
		
		// if x is a square, then it is in the list squares<>, I can know its square-root from its index in squares<>, that is: sqrt(x) = 2 + x's_index    :D
		int index = Collections.binarySearch(squares, x);
		if(index>=0){
			facts.add(2+index);	
		}
		
		facts.add(x);
		return facts;
	}
	
		
	static void fillPrimes(){
		for (int i = 0; i < primesArray.length; i++) 
			if(primesArray[i]!=0)
				primes.add(primesArray[i]);
	}

	// check https://en.wikipedia.org/wiki/Sieve_of_Eratosthenes for a clear explanation of the algo!!
	static int[] sieve(int n){
		int nums[] = new int[n+1];
		for (int i = 0; i <= n ; i++) nums[i]=i;                   // I'm filling the list with 0 and 1 just for easy indexing later.
		
		int p = 2;
		while(p*p<n) {	  // we're done when p^2 >= n
			for (int i = p*p; i <= n; ) {  // starting at p^2 mark all multiples of p with 0.   We're starting at p^2 cuz it's guranteed that all non-primes under p^2 are already marked. 
				nums[i]=0;
				i += p;
			}
			
			int oldP = p;
			for (int i = p+1; i <= n; i++) {  // find the next number that is not marked (prime) in the list and set p equal to it.
				if(nums[i] != 0){
					p = nums[i];
					break;
				}
			}
			
			if(oldP == p)  // if p hasn't changed after the for loop, it means that we have reached the last prime already!!
				break;
		}
		
		nums[1]=0;
		return nums;
	}
	

}
