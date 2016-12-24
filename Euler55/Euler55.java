import java.math.BigInteger;


public class Euler55 {

	public static void main(String[] args) {
		int n = 0;
		for (int i = 10; i <= 9999; i++) {
			if(isLychrel(i))
				n++;
		}
		
		System.out.println("Lychrels under 10000: "+n);
	}
	
	static String reverse(String str1) {
		String str2 = "";
		for (int i = str1.length()-1; i >=0; i--) 
			str2 += "" + str1.charAt(i);
		
		return str2;
	}
	
	
	static boolean isPalindromic(long n){
		String str1 = String.valueOf(n);
		String str2 = reverse(str1);
			
		if(Long.parseLong(str1) == Long.parseLong(str2))
			return true;
		
		return false;
	}
		
	static boolean isPalindromic(BigInteger n){
		String str1 = n.toString();
		String str2 = reverse(str1);
		
		if(str1.equals(str2))
			return true;
		
		return false;
	}
	
	static boolean isLychrel(long x){		
		BigInteger b = new BigInteger(String.valueOf(x));
		int i = 0;

		while(i<50){
			i++;			
			b = b.add( new BigInteger( reverse( b.toString() ) ));

			if(isPalindromic(b))
				return false;
		}
		
		return true;
	}
	

}
