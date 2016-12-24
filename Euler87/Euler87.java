import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

/*
 	Note:   x,y,z are primes and have an upper limit:
 	
 		x in: {1, 2, 3, ...,  sqrt(50 million)}   Union the primes set 
 		y in: {1, 2, 3, ...,  cube-root(50 million)   Union the primes set   		
 		z in: {1, 2, 3, ...,  4th-root(50 million)   Union the primes set  
 */

public class Euler87 {
	static long upper = 50000000l;
	static List<Long> primes;
	static int xLimit,yLimit,zLimit;
	static TreeSet<Long> numbers;                                     // Tree-sets dont store duplicates.
	static void init(){
		xLimit =  (int)Math.pow(upper,0.51);                           // sqrt of upper is pow(upper, 0.5) . but I add a notch to give it some room. it never hurts
		yLimit =  (int)Math.pow(upper, 0.4);						   // same thing. but here it's pow(upper, 1/3)			
		zLimit =  (int)Math.pow(upper,0.3);							   // pow(upper, 1/4)
		primes = sieve(xLimit);	                                  
		numbers = new TreeSet<>();
	}
	
	public static void main(String[] args) throws InterruptedException {
		init();

		long n= Long.MAX_VALUE;
		int x= (int)(long)primes.get(numPrimes(xLimit)-1);                   	 // The actual maximum value that x can take in the formula. and this value is the last prime under xLimit.  
		int y= (int)(long)primes.get(numPrimes(yLimit)-1);					 // The actual maximum value that y can take in the formula. and this value is the last prime under yLimit. 
		int z= (int)(long)primes.get(numPrimes(zLimit)-1);					 // The actual maximum value that z can take in the formula. and this value is the last prime under zLimit. 
		
		for (int i = 2; i <= x; i = getNext(i)) {
			for (int j = 2; j <= y; j = getNext(j)) {
				for (int k = 2; k <= z; k = getNext(k)) {
					
					n = n(i,j,k);
					if(n<upper)							
						numbers.add(n);  // we'll add n to numbers even if it's already there. numbers doesnt store duplicates!!				
					
				}
			}
		}
		
		System.out.println("total: "+numbers.size());
	}
	
	static long n(int x, int y, int z){
		return (long)(Math.pow(x, 2) + Math.pow(y, 3) + Math.pow(z, 4));			
	}
	
	// returns the number up to bound.
	static int numPrimes(long bound){
		long x=-1;
		int i;
		for (i = 0; x<=bound && i<primes.size(); i++) {
			x = primes.get(i);
		}
		return i-1;
	}
	
	// GIVEN A PRIME x, this returns the next prime.
	static int getNext(long x){
		int index = Collections.binarySearch(primes, x) +1;
		return (int)((long)primes.get(index));
	}
	
	// check https://en.wikipedia.org/wiki/Sieve_of_Eratosthenes for a clear explanation of the algo!!
	static List<Long> sieve(long n){
		List<Long> nums = new ArrayList<Long>();
		for (Long i = 0l; i <= n; i++) nums.add(i);                    // I'm filling the list with 0 and 1 just for easy indexing later.
		
		long p = 2l;
		while(p*p<n) {	  // we're done when p^2 >= n
			for (int i = (int) (p*p); i <= n; ) {  // starting at p^2 remove all mark all multiples of p with 0.   We're starting at p^2 cuz it's guranteed that all non-primes under p^2 are already marked. 
				nums.set(i, 0l);
				i += p;
			}
			
			long oldP = p;
			for (int i = (int) (p+1); i <= n; i++) {  // find the next number that is not marked (prime) in the list and set p equal to it.
				if(nums.get(i) != 0){
					p = nums.get(i);
					break;
				}
			}
			
			if(oldP == p)  // if p hasn't changed after the for loop, it means that we have reached the last prime already!!
				break;
		}
		
		nums.remove(new Long(1));   
		nums.removeAll(Collections.singleton(new Long(0)));    // remove all the numbers we've marked with 0
		 	
		return nums;
	}

}
