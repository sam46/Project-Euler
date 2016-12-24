
public class Euler52 {

	public static void main(String[] args) {
		long i = 1;
		
		while(true){
			int digits = (int)Math.log10(i);   // the integral part of log10(x) tells us how many digits x has.
			
			if( (int)Math.log10(6*i)  > digits ) { 
				i = nextMagnitude(i);
				continue;
			}
			
			
			if( sameDigits(i, 2*i) && sameDigits(i, 3*i) && sameDigits(i, 4*i) && sameDigits(i, 5*i) && sameDigits(i, 6*i) ) { 
					System.out.println(i);
					break;
			}
			
			i++;
		}
		
		
		
	}
	
	static long nextMagnitude(long x){
		if(x==0) return 10;
		return (long)Math.pow(10, 1+(int)Math.log10(x));
	}
	
	static boolean sameDigits(long x, long y){
		String a = String.valueOf(x);
		String b = String.valueOf(y);
		if(a.length() != b.length()) return false;
		for (int i = 0; i < a.length(); i++) {
			if(b.contains(""+a.charAt(i)) == false)
				return false;
		}
		
		return true;
	}

}
