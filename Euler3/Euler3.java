import java.util.ArrayList;
import java.util.List;


public class Euler3 {
	static final long num = 600851475143l;
	
	public static void main(String[] args) {
		List<Long> l = factors(num);
		for (Long long1 : l) {
			if(isPrime(long1))
				System.out.println(long1);
		}
	}
	
	static List<Long> factors(long x){
		long n = (long) Math.ceil(Math.sqrt(x));
		List<Long> facts = new ArrayList<Long>();
		
		for (long i = 2; i <= n; i++) {
			if(x%i==0)
				facts.add((long) i);
		}
		
		int length = facts.size();
		for (int i = 0; i < length; i++) 
			facts.add(x/ facts.get(i));
		
		return facts;
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
