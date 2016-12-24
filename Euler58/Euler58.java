import java.util.ArrayList;
import java.util.List;


public class Euler58 {

	public static void main(String[] args) {

		long answer = gen(0.10);
		System.out.println("Side Length: "+answer);
	}

	
	// this function's from problem 28, with modifications!!
	static long gen(double goal){

		long i =0l;
		long cur = 1l;
		long primes = 0l;
		long total = 1;
		double ratio = Double.MAX_VALUE;
		
		while(ratio > goal){
			i++;
			for (int j = 0; j < 4; j++) { // add 2*i four times, each time we get a diagonal number on the (i+1)th level 
				cur += 2l*i;
				total++;
				if(isPrime(cur))
					primes++;
			}
			ratio = (double)primes/(double)total;
		//	System.out.println("Length: "+(((i+1)*2) -1)+"  ratio="+ratio);
		}
		
		return ((i+1)*2) -1;
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
