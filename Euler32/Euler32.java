import java.math.BigInteger;
import java.util.*;
public class Euler32 {
	// I deduced with math formulas and equalities
	//  that a and b can range from 1 to 99999    (although I've exaggerated a bit lol).
	// 
	
	public static void main(String[] args) {
		String str = "";
		List<String> pandigitals = new ArrayList<String>();
		long c;
		for (int a = 1; a < 99999; a++) {
			for (int b = 1; b < 99999; b++) {
					c= (long)a*(long)b;
					str = "" + a +""+ b +""+ c;
					if(containsAllDigits(str)){
						System.out.println(c);
						str = "" + c;  
						if(!pandigitals.contains(str))
							pandigitals.add(str);
					}
			}
		}
		
		BigInteger sum = new BigInteger("0");
		for (String string : pandigitals) {
			sum = sum.add( new BigInteger(string));
		}
		System.out.println("sum:");
		System.out.println(sum);
		
		//System.out.println(containsAllDigits("312456789"));
		
	}
	
	
	
	static boolean containsAllDigits(String str){
		if(str.length() != 9)
			return false;
		
		for (int i = 1; i <= 9; i++) {
			if(!str.contains(""+i))
				return false;
		}
		
		return true;
	}

}
