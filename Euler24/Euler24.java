import java.util.Arrays;


public class Euler24 {
	static long[] numbers = new long[Permutation.fact(10)];
	
	public static void main(String[] args) {
		String[] temp = Permutation.permutations("0123456789");
		for (int i = 0; i < temp.length; i++) 
			numbers[i] = Long.parseLong(temp[i]);
		
		Arrays.sort(numbers);
		System.out.println(numbers[1000000-1]);
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
