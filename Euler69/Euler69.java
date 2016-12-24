import java.sql.Time;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Timer;


public class Euler69 {
	// to calculate phi of n:
	// if n is prime: phi = n-1
	// if n is composite: check wikipedia https://en.wikipedia.org/wiki/Euler%27s_totient_function#Euler.27s_product_formula
	// the formula is phi(n) = n * (1 - 1/p1) * (1- 1/p2) * ....   where p1,p2, p3,... are the prime factors of n:   n = p1*p2*p3...
	// anyway, we need to get the distinct prime factors of n to calculate phi(n)
	// done!!
	
	
	static int N = 10000000;
	static List<Integer> primes;
	
	public static void main(String[] args) {
		long startTime = System.nanoTime();
		
		
		primes = sieve(N);                             
		System.out.println("done generating primes!!");
		double greatest = 0;
		int ans = 0;
		
		for (int i = 2; i <= N; i++) {
			double noverphi = (i*1.0) /(1.0 * phi(i));
			if(noverphi> greatest){
				greatest = noverphi;
				ans = i;
			}
			if(i%500000==0)
				System.out.println("progress: "+ (100l*i)/(long)N +"%");
		}
		System.out.println("answer is "+ans+", for which phi is "+(ans/(int)greatest));
		
		
		
		long endTime = System.nanoTime();
		System.out.println("Runtime(with sieve's algo): "+(endTime-startTime)/1000000.0);
	}

	
	static int phi(int n){
		if(n%2 != 0)
			if(Collections.binarySearch(primes, n) >= 0)
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
