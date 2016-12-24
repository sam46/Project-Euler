import java.util.ArrayList;
import java.util.List;


public class Euler41 {
	//  9-digit and 8-digit pandigitals can not be primes,
	// cuz the sum of their digits = 3*k, thus they are divisible by 3.
	// So without further investigation, we can say that 7654321 is the upper limit.
	// Now let's reduce the possibilities even more:
	// 2-digit, 3-digits, 5-digit and 6-digit pandigitals are all non prime cuz the sum of their digits is 3k.
	// we are only left with 4-digit and 7-digit pandigitals.
	// so total possibilities are: permutations of (7654321) and (4321) , so: 7! + 4! = 5064
	
	
	public static void main(String[] args) {
		String[] sevenPan,fourPan; // 4-digit and 7-digits pandigitals
		
		// we're looking for "The Greatest" pandigital prime, so we'll look for a 7-digit one first!!
		sevenPan = Permutation.permutations("7654321");
		
		int greatest = -1;
	
		for (int i = 0; i < sevenPan.length; i++) {
			int t = Integer.parseInt(sevenPan[i]);
			if(isPrime(t)){    // the permutaions() function happens to spit out the permutation in descending order, so the first prime we find will be the greatest :D
				greatest = t;
				break;
			}
		}
		
		if(greatest == -1) // if no luck finding a 7-digit prime
		{
			fourPan = Permutation.permutations("4321");
			for (int i = 0; i < fourPan.length; i++) {
				int t = Integer.parseInt(fourPan[i]);
				if(isPrime(t)){    
					greatest = t;
					break;
				}
			}
			
		}
		
		System.out.println("greatest: "+greatest);
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



class Permutation {
	private static String remove(String str,int ind){
		String s="";
		for (int i = 0; i < str.length(); i++) 
			if(i!=ind)s += str.charAt(i);
		return s;		
	}
	
	// calculates n!
	public static int fact(int n){
		int r=1;
		for (int i = 1; i <=n; i++) {
			r= r*i;
		}
		return r;
	}
	
	/** @param nums: The string for which the permutations are to be generated*/
	public static String[] permutations(String nums){
		if(nums.length() ==1){
			String[] ret = {nums};
			return ret;
		}
	
		String[] ret = new String[fact(nums.length())];
		for (int i = 0; i < nums.length(); i++) {
			String[] str = permutations(remove(nums, i));
			for (int j = 0; j < str.length; j++){ 
				str[j] = nums.charAt(i) + str[j];
				ret[(i* str.length)+ j]= str[j];
			}
		}
		return ret;
	}

}
