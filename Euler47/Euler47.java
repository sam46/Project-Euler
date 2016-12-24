import java.util.*;
public class Euler47 {

	public static void main(String[] args) {
		long a,b,c,d;
		for (a = 10; ; a++) {
			b=a+1;
			c=a+2;
			d=a+3;
			if(distinctPfactors(a).size() == 4)
				if(distinctPfactors(b).size() == 4)
					if(distinctPfactors(c).size() == 4)
						if(distinctPfactors(d).size() == 4)
							break;
		}
		System.out.println(a);
	}
	
	// This recursive function returns a list containing the prime factors of n, but without repeating the prime factor, example: for 4 it will return {2}, not {2,2}
	static List<Long> distinctPfactors(long n){
		List<Long> list;
		
		if(isPrime(n)){  // if n is prime then the only prime factor for it is itself!!, 
			list = new ArrayList<Long>();
			list.add(n);
			return list;
		}
		
		// now I 'll just need to know what's the first(and as a result, the smallest) prime factor for n, let's call it p.
		// then I can write n = p * (n/p) 
		// now the prime factors for n are: p and whatever prime factors (n/p) has.
		// for example:  16 = 2 * 8  so 16's prime factors are 2 and 8's prime factor.
		
		long factor = 2;
		while(n%factor!=0) factor = nextPrime(factor);
		
		
		list = distinctPfactors(n/factor);
		if( !list.contains(factor) ) list.add(factor); // now we'll add the smallest factor we discovered earlier if the list doesn't already contain it. and this is why we dont have repeated factors in the final answer.
		
		return list;
	}

	static long nextPrime(long start){
		start++;
		while(true) {
			if(isPrime(start))
				return start;
			start++;
		}
	}
	

	static List<Long> primes = new ArrayList<Long>();
	static boolean isPrime(long x){
		if(primes.contains(x))
			return true;
		if(x==2 || x==3)
			return true;
		if(x==1)
			return false;
		
//-----------------------------------------\\
		long n = (long) Math.ceil(Math.sqrt(x));
		long i =2;
		while( i<= n){
			if(x%i == 0)
				return false;
			i++;
		}
		
		primes.add(x);
		return true;
	}

}
