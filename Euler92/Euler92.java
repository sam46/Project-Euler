import java.io.ObjectInputStream.GetField;


public class Euler92 {

	public static void main(String[] args) {
		int total = 0;
		for (long i = 1; i <= 10000000; i++) {
			if(eigthyNiner(i))
				total++;
		}
		
		System.out.println(total);
	}
	
	static boolean eigthyNiner(long x){
		long cur = x, next=-1;
		
		while(cur != 1 && cur !=89){
			cur = nextTerm(cur);
		}
		
		if(cur == 89)
			return true;
		return false;
	}

	static long nextTerm(long x){
		long sum = 0l;
		int digits = digits(x);
		for (int i = 1; i <= digits; i++) {
			sum += Math.pow(x%10,2);
			x /= 10;
		}
		return sum;
	}
	
	static int digits(long x){
		if(x==0)
			return 0;
		if(x==1)
			return 1;
		
		return 1+ (int) Math.floor(Math.log10(x));
	}
		
}
