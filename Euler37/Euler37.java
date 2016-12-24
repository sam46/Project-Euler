
public class Euler37 {

	public static void main(String[] args) {
		long i=10l;
		long sum = 0;
		int y=0;
		
		while(y != 11){
			if(isTruncatable(i)){
				sum += i;
				y++;
			}
			i++;
		}
		
		System.out.println(sum);

	}
	
	static boolean isTruncatable(long x){
		String str = String.valueOf(x);
		String temp = "";

		for (int i = 0; i < str.length(); i++) {
			temp = str.substring(i, str.length());
			if(! isPrime(Long.parseLong(temp)) )
				return false;
		}
		
		for (int i = 1; i < str.length(); i++) {
			temp = str.substring(0, str.length()-i);
			if(! isPrime(Long.parseLong(temp)) )
				return false;
		}
		
		return true;
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
