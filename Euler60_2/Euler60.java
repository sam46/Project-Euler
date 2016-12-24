import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Euler60 {

	// see Project Euler60_3
	// This is another version that (very similar) that uses 5 nested for-loops, and uses the first 1100 primes.
	
	public static void main(String[] args) {
		primes.add(2l);
		primes.add(3l);
		int tem = 3;
		


		while(primes.size() < 1100) {	isPrime(tem++);		}

		Long[] ppp = new Long[0];
		ppp = primes.toArray(ppp);

		
		for (int i = 0; i < ppp.length; i++) {
			long a = ppp[i];
			
			for (int j = i+1; j < ppp.length; j++) {
				long b = ppp[j];
				
				if(b == a) continue;
				if(sumBy3(b, a)) continue;
				if(!isPair(a, b)) continue;
				
				for (int g = j+1; g < ppp.length; g++) {
					long c = ppp[g];
					
					if(c==a || c==b) continue;
					if(sumBy3(c, a) || sumBy3(c, b)) continue;
					if(!isPair(c, a)) continue;
					if(!isPair(c, b)) continue;
					
					for (int k = g+1; k < ppp.length; k++) {
						long d = ppp[k];
						
						if(d==a || d==b || d==c) continue;
						if(sumBy3(d, a) || sumBy3(d, b) || sumBy3(d, c)) continue;
						if(!isPair(d, a)) continue;
						if(!isPair(d, b)) continue;
						if(!isPair(d, c)) continue;
						
						for (int t = k+1; t < ppp.length; t++) {
							long e = primes.get(t);

							long[] arr = new long[4];
							arr[0] = a;
							arr[1] = b;
							arr[2] = c;
							arr[3] = d;
							if(check(arr, e)){
								System.out.println(a+" , "+b+" , "+c+" , "+d+" , "+e);							
								return;
							}
							
						}
						
					
						
					}
					
				}
			}
		}

		
	}
	
	static boolean sumBy3(long a, long b){
		int sum = 0;
		String s = String.valueOf(a) + String.valueOf(b);
		for (int i = 0; i < s.length(); i++) 
			sum += Integer.parseInt(""+s.charAt(i));
		if(sum%3 ==0)
			return true;
		return false;
	}
	

	static boolean isPair(long a, long b){
		long x = Long.parseLong(String.valueOf(a) + String.valueOf(b));
		if(!isPrime(x))	return false;
		x = Long.parseLong(String.valueOf(b) + String.valueOf(a));
		if(isPrime(x))	return true;
		return false;
	}
	


	static boolean isPair(long[] arr){
		long a = arr[0], b = arr[1];
		long x = Long.parseLong(String.valueOf(a) + String.valueOf(b));
		if(!isPrime(x))	return false;
		x = Long.parseLong(String.valueOf(b) + String.valueOf(a));
		if(isPrime(x))	return true;
		return false;
	}
	
	
	static boolean check(long[] arr, long x){
		for (int i = 0; i < arr.length; i++) {
			if(!isPair(x, arr[i]))
				return false;
		}

		return true;
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
