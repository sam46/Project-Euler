import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Euler70 {
	
	static int N = 10000000;
	static List<Integer> primes;
	
	public static void main(String[] args) {
		long startTime = System.nanoTime();
		
		primes = sieve(N);
		System.out.println("done generating primes!!");
		double mini = Double.MAX_VALUE;
		int ans = 0;
		
		for (int i = 2; i <= N; i++) {
			int phy = phi(i);
			double noverphi = (i*1.0) /(1.0 * phy);
			if( isPermutation(String.valueOf(i), String.valueOf(phy)) ) {
				
				if(noverphi< mini){
					mini = noverphi;
					ans = i;
				}
			}
			if(i%500000==0)
				System.out.println("progress: "+ (100l*i)/(long)N +"%");
		}
		System.out.println("answer is "+ans+",  for which phi is "+(ans/(int)mini));

		long endTime = System.nanoTime();
		System.out.println("Runtime: "+(endTime-startTime)/1000000.0);

	}

	/* From Euler62_2 */
	static boolean isPermutation(String a, String b){
		if(a == null || a== "" || b == null || b == "")
			throw new IllegalArgumentException("invalid argument!!");
		
		
		if(b.length() < a.length()){ // I'm padding b with leading zeros to make it the same length as a's.
			for (int i = 0; i < b.length()-a.length(); i++)  
				b = "0"+b;
		}
	
		if(a.length() != b.length())
			return false;

		
		List<Integer> checked = new ArrayList<Integer>();
		for (int i = 0; i < a.length(); i++) {
			char ch = a.charAt(i);
			for (int j = 0; j < b.length(); j++) {
				if(ch == b.charAt(j) && checked.contains(j) == false){
					checked.add(j);
					break;
				}
				if(j==b.length()-1) 
					return false;
			}	
		}
		return true;	
	}
	

	
/* All of the following is from Euler69  */
	static int phi(int n){			
		if(n%2 != 0)      								  				    // if n is odd (since evens cant be prime except "2")
			if(Collections.binarySearch(primes, n) >= 0)    		    	// if n is prime
				return n-1;
		
		Integer[] factors = factorize(n);
		int product = n;
		for (int i = 0; i < factors.length; i++) 
			product *= (1.0-(1.0/factors[i]));
		return product;	
	}
	
	
	// returns the primes factors of n, without repetition (to the first power), so for 12 : {2,3} not {2,2,3}
	static Integer[] factorize(int n){
		if(Collections.binarySearch(primes, n) >= 0)           
			return new Integer[]{n};
		
		List<Integer> pfacts = new ArrayList<Integer>();

		Integer cur = 2;  											// start with the first prime
		int i = 0;
		while(n!=1) {
			
				if(n%cur == 0){          						 // if cur divides n  then cur must be a prime factor. Add it to the list!!
				    if(!pfacts.contains(cur))                    // this is to avoid adding the same factor more than once, keeping only distinct factors in the list!!
				    	pfacts.add(cur);
					n /= cur;   								  //  divide n by the factor we just found.
				 
					continue;           						  //  this will prevent cur from becoming the next prime until we have completely used up this current prime. for example if we're trying to factor 24 = 2^3 * 3   this will cause cur to stay equal to 2 for three loops and then change to 3 !!     				    
				}

				cur = primes.get(i++);  						 // set cur equal to the next prime!!		
		}
		
		Integer[] ret = new Integer[0];	
		return pfacts.toArray(ret);	
	}
	
	
	
	// check https://en.wikipedia.org/wiki/Sieve_of_Eratosthenes for a clear explanation of the algo!!
	static List<Integer> sieve(int n){
		List<Integer> nums = new ArrayList<Integer>();
		for (int i = 0; i <= n; i++) nums.add(i);                    // I'm filling the list with 0 and 1 just for easy indexing later.
		
		int p = 2;
		while(p*p<n) {	  // we're done when p^2 >= n
			for (int i = p*p; i <= n; ) {  // starting at p^2 remove all mark all multiples of p with 0.   We're starting at p^2 cuz it's guranteed that all non-primes under p^2 are already marked. 
				nums.set(i, 0);
				i += p;
			}
			
			int oldP = p;
			for (int i = p+1; i <= n; i++) {  // find the next number that is not marked (prime) in the list and set p equal to it.
				if(nums.get(i) != 0){
					p = nums.get(i);
					break;
				}
			}
			
			if(oldP == p)  // if p hasn't changed after the for loop, it means that we have reached the last prime already!!
				break;
		}
		
		nums.remove(new Integer(1));   
		nums.removeAll(Collections.singleton(new Integer(0)));    // remove all the numbers we've marked with 0
		 	
		return nums;
	}
	


}
