import java.util.*;

// Simple algorithm:
// We have 10000 cubes stored in cubes[]
// now we take each cube (let's call it i) and run it against the whole array (except itself!!)
// and see if the current element (call it j) is a permutation of a or not.
// so we need double-for-loop that will run through cubes[] both the outer and inner.
// so if cubes[] has 10000 elements, we'll run 10000*10000 iterations
// way better and faster than the previous attempt!!
// took a couple seconds to finish, while the first attempt never did!! 


public class Euler62_2 {

	static String[] cubes = new String[10000]; // i just chose a big number.
	
	static void genCubes(){
		for (int i = 10; i-10 < cubes.length; i++) 
			cubes[i-10] = String.valueOf(i*(long)i*i);	
	}
	
	public static void main(String[] args) {
		genCubes();
		
		for (int i = 0; i < cubes.length; i++) {
			int score = 1; // we're starting at 1 instead of 0, cuz any number we pick from the array cubes[] is a cube itself, and it should be counted.
			
			for (int j = 0; j < cubes.length; j++) {
				if(j==i) continue;
				
				if(isPermutation(cubes[i], cubes[j])) // the order of the parameters matters!! the function assumes that the second doesn't have more digits than the first.
					score++;
							
				if(score > 5) // we dont want cubes that have more than 5 cube permutations. they want it to be exactly 5 !!
					break;		
			}
			
			if(score == 5){
				System.out.println(cubes[i]);
				break;
			}
		}
		
		
		
		
	}


	static boolean isPermutation(String a, String b){
		if(a == null || a== "" || b == null || b == "")
			throw new IllegalArgumentException("invalid argument!!");
		
		
		if(b.length() < a.length()){ // I'm padding b with leading zeros to make it the same length as a's.
			for (int i = 0; i < b.length()-a.length(); i++) 
				b = "0"+b;
		}
	
		if(a.length() != b.length())
			return false;

		
		List<Integer> checked = new ArrayList<Integer>();
		for (int i = 0; i < a.length(); i++) {
			char ch = a.charAt(i);
			for (int j = 0; j < b.length(); j++) {
				if(ch == b.charAt(j) && checked.contains(j) == false){
					checked.add(j);
					break;
				}
				if(j==b.length()-1) // we've reached the end of b without finding any match for ch
					return false;
			}	
		}
		return true;	
	}

}
