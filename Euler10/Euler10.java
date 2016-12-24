
public class Euler10 {

	public static void main(String[] args) {
		int x=2;
		long sum=0;
		while(x<2000000l){
			if(isPrime(x))
				sum+=(long)x;
			x++;
		}
		System.out.println(sum);
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
