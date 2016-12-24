import java.util.*;;


public class Euler46 {
	// x = p + 2*y^2
	// I pick an x (starting at 35 cuz that's the next composite odd number after the 33 in the question) 
	// then i pick a prime p 
	// now I try all y's that can could satisfy x == p + 2*y*y
	// y = sqrt((x-p)/2) so I'll try all y's from 1 to  ceil( sqrt((x-p)/2 )
	// now if none of those y's satisfy the equation, then let's try the next prime p
	// we keep doing this until we run out of possible p's. cuz p cannot exceed x.
	// Now the 2 inner loops will break if we can write x with p,y combination, and that's to avoid any further computation and jump immediately to the next x.
	// when x cannot be written with a p,y combination, the 2 inner loops will run to the end, and
	// the flag canBeWritten wont be set, this will cause the outer loop to print the x and break terminating the program.
	// The End.
	
	public static void main(String[] args) {
		
		for (long x = 35; ; x = nextOddComposite(x)) {
			boolean canBeWritten = false;
			for (long p = 2; p < x-2; p = nextPrime(p)) {

				for (long y = 0; y <= Math.ceil( Math.sqrt((x-p)/2.0) ); y++) {

					if(x == p + 2*y*y) {
						canBeWritten = true;
						System.out.println("can write. "+x+" = "+p+" + "+(2*y*y)); // just for fun, print the p,y for this x.
						break;		
					}
				}
				
				if(canBeWritten)
					break;
			}
			
			if(canBeWritten == false) { // if no p,y combination could be found, then this is the x we're looking for, let's pint it and halt.
				System.out.println("CAN'T WRITE "+x);
				break;
			}
		}
		
		
	}
	
	static long nextPrime(long start){
		start++;
		while(true) {
			if(isPrime(start))
				return start;
			start++;
		}
	}
	
	static long nextOddComposite(long start){
		start += 2;
		while(true) {
			if(start%2==1)
				if(!isPrime(start))
					return start;
			start +=2;
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
