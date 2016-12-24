
public class Euler7 {

	public static void main(String[] args) {
		int primes=0;
		
		int x=1;
		while(primes!=10001){
			x++;
			if(isPrime(x))
				primes++;
			
		}
			
			
		
		System.out.println(x);
	}

	
	static boolean isPrime(long x){
		if(x==2 || x==3)
			return true;
		if(x==1)
			return false;
		
//-----------------------------------------\\
		long n = (long) Math.ceil(Math.sqrt(x));
		int i =2;
		while( i<= n){
			if(x%i == 0)
				return false;
			i++;
		}
		return true;
	}
}
