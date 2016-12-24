import java.awt.List;
import java.util.*;


public class Euler49 {


	public static void main(String[] args) {
		for (int i = 1001; i < 10000; i += 2) {
			if(isPrime(i)){
				String[] temp = Permutation.permutations(""+i);
				int[] arr = new int[temp.length];
				for (int j = 0; j < arr.length; j++) {
					arr[j] = Integer.parseInt(temp[j]);
				}
				check(arr);
			}
		}
		
	}
	
	static void check(int[] arr){
		Arrays.sort(arr);

		for (int i = 0; i < arr.length; i++) {
			if(isPrime(arr[i]) && arr[i]>1000)			
				for (int j = 0; j < arr.length; j++) {
					if(arr[j]!=arr[i] && isPrime(arr[j]) && arr[j]>1000)						
						for (int k = 0; k < arr.length; k++) {
							if(arr[k]!=arr[i] && arr[k]!=arr[j] && isPrime(arr[k]))							
								if(arr[k]-arr[j] == arr[j]-arr[i] && arr[k]>1000)  {             // just assume: i, j=i+c, k=j+c
									System.out.println(arr[i]+",   "+arr[j]+",   "+arr[k]);
									return;
								}
						}
				}
		}
		
		
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

