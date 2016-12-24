import java.util.ArrayList;
import java.util.List;

// First Attempt, Failure:
// the program should give a correct answer, it's just super slow!!
// Check project Euler62_2 for a way better algorithm.

// synopsis:
// try 10^3 .. 11^3 .. 12^3 ....
// generate permutations of it.
// see how many of the permutations are cubes.
// until u find a cube that has exactly 5 cubes among its permutations.

public class Euler62 {
// the cube we want has at least 3 digits, cuz it has to have at least 5 permutations , 2! = 2 only, 3! = 6 so at least 3 digits!!
// so we'll start from 1000, so cube root of that is 10!!
// Note: beware, if the cube has repeated digits, there will be repeated permutations!!. e.g: 323 will have multiple repeated permutations, 
// that's why we have a checked<> list. to avoid counting the same permutation more than once!!
	
	public static void main(String[] args) {
		foo();
	}
	
	static void foo(){
		String[] perms;

		for (long i = 1008l; ; i++) { // start from 10 cuz 10^3 = 1000 ,the lower bound for our desired cube.
			System.out.println("i:"+i);
			long x = i*i*i;
			perms = Permutation.Permutations(""+x);

			if(check(perms)){
				System.out.println("answer: "+i+"^3 = "+(i*i*i));
				return;
			}
		}
		
	}
	
	static boolean check(String[] arr){
		if(arr[0].equals(""+(1009*1009*1009)))
			System.out.println();
		int score = 0;
		List<Long> checked = new ArrayList<>(); 
		
		for (int i = 0; i < arr.length; i++) {
			long x = Long.parseLong(arr[i]);
			if(checked.contains(x))
				continue;
			
			checked.add(x);
			
			double y = Math.pow(x, 1.0/3.0) ;			
			if(   Math.ceil(y) - y <= 0.000000000001 )  // precision on computers sucks, even if x is in fact a cube, pow(x,1.0/3.0) is still giving inaccurate results for some numbers, for example: instead of 2.0 it might give 1.99999999999 so i'm considering two doubles to be equal if the difference between them is so small !!
				score++;
			if(score > 6)  // we want it to be exactly 5, no more no less!!
				return false;
		}
		
		if(score == 5)
			return true;
		return false;
		
	}
	

}






class Permutation{

	static String remove(String str,int ind){
		String s="";
		for (int i = 0; i < str.length(); i++) 
			if(i!=ind)s += str.charAt(i);
		return s;		
	}
	
	static int fact(int n){
		int r=1;
		for (int i = 1; i <=n; i++) {
			r= r*i;
		}
		return r;
	}
	
	static String[] Permutations(String nums){
		if(nums.length() ==1){
			String[] ret = {nums};
			return ret;
		}
		
		String[] ret = new String[fact(nums.length())];
		for (int i = 0; i < nums.length(); i++) {
			String[] str = Permutations(remove(nums, i));
			for (int j = 0; j < str.length; j++){ 
				str[j] = nums.charAt(i) + str[j];
				ret[(i* str.length)+ j]= str[j];
			}
		}
		return ret;
	}

}