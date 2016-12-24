
public class Euler36 {

	public static void main(String[] args) {
		long sum = 0;
		for (int i = 1; i < 1000000; i++) {
			if(isPalindromic(""+i))
				if( isPalindromic(Integer.toBinaryString(i)) )
					sum += i;
		}
		
		System.out.println(sum);
		
	}

	static boolean isPalindromic(String str){	
		String str2 = "";
		for (int i = str.length()-1; i >=0; i--) 
			str2 += "" + str.charAt(i);
		
		if(str.equals(str2))
			return true;
		
		return false;
	}

	
}
