
public class Euler43 {

	public static void main(String[] args) {
		String[] perms = Permutation.permutations("0123456789");
		long sum = 0;
		
		for (int i = 0; i < perms.length; i++) {
			if( perms[i].charAt(5) == '5' || perms[i].charAt(5) == '0')
				if( Integer.parseInt(""+perms[i].charAt(3))  %2 == 0 )
					if( isSubstringDivisible(perms[i]) )
						sum += Long.parseLong(perms[i]);
		}
		
		System.out.println(sum);
			
		
	}

	static boolean isSubstringDivisible(String str){
		int[] subs = new int[7];
		for (int i = 1; i <= subs.length; i++) 
			subs[i-1] = Integer.parseInt( str.substring(i, i+3) );
		if(subs[0]%2 != 0)
			return false;
		if(subs[1]%3 != 0)
			return false;
		if(subs[2]%5 != 0)
			return false;
		if(subs[3]%7 != 0)
			return false;
		if(subs[4]%11 != 0)
			return false;
		if(subs[5]%13 != 0)
			return false;
		if(subs[6]%17 != 0)
			return false;
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

