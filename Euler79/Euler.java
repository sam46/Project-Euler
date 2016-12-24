import java.util.*;


// There're 8 distinct characters in the successful logins:  {3, 1, 9, 6, 8, 0, 2, 7} , absent digits are: 4,5.
// so the shortest possible length for the password is 8!! (assuming no repetitions!!)
// let's assume it's abcdefgh
// how many ways to choose 3 from it ? C(8,3) = 56
// now out of those 56, we dont want cases like acd or dbc, 
// that is, we want the lexicographic order to be preserved.
// and fortunately, all of the 56 combinations have this property. we store them in candidates[56][3]
// now how many ways can we arrange {3, 1, 9, 6, 8, 0, 2, 7}? 8! = 40320. we store them in perms[]
// each permutation will represent a possible password!!
// now all that's left is to test each password(permutation) against the logins, and that's what check() is for!!
// So now we take a possible password, and using candidates's elements as a mask, we generate correspondingKey[56] which contains all 56 possible keylogs.
// if the 50 successful keylogs(logins) they gave us are a subset of the 56 keylogs we just generated, then the password that generated them is 
// the correct password!!  however if at least 1 of the 50 successful logins was not found in the 56 keylogs. then the password is not valid.
// Done!!

// Note: it can be solved by hand easily without brute force!!

public class Euler {
	static String[] logins = {
			"319",		"680",		"180",		"690",		"129",		"620",
			"762",		"689",		"762",		"318",		"368",		"710",
			"720",		"710",		"629",		"168",		"160",		"689",
			"716",		"731",		"736",		"729",		"316",		"729",
			"729",		"710",		"769",		"290",		"719",		"680",
			"318",		"389",		"162",		"289",		"162",		"718",
			"729",		"319",		"790",		"680",		"890",		"362",	
			"319",		"760",		"316",		"729",		"380",		"319",
			"728",		"716"	
		};
	
	static Character[][] candidates;

	public static void main(String[] args) {
		candidates = Combinations.combinations(new Character[]{'a','b','c','d','e','f','g','h',}, 3);     // C(8,3) = 56
		String[] perms = Permutations("01236789"); 														  // 8! = 40320
		
		for (int i = 0; i < perms.length; i++) {
			String pass = perms[i];
			if(checkPassword(pass))                  // checks if the password if valid
				System.out.println(pass);
		}
		System.out.println("Done!!");
		
	}
	
	static boolean checkPassword(String pass){
		String[] correspondingKeys = new String[candidates.length];
		for (int i = 0; i < correspondingKeys.length; i++) {
			char c0 = candidates[i][0], c1 = candidates[i][1], c2 = candidates[i][2];
			correspondingKeys[i] = "";
			correspondingKeys[i] += pass.charAt(c0-'a');         // candidates[i]'s characters will function as masks!!
			correspondingKeys[i] += pass.charAt(c1-'a');
			correspondingKeys[i] += pass.charAt(c2-'a');			
		}
		
		for (int i = 0; i < logins.length; i++) {
			String login = logins[i];
			if(!contains(correspondingKeys, login))     // a successfull login wasn't found in correspondingKeys[], it means the password we're checking aint valid!!
				return false;
		}
		return true;
	}
	
	static boolean contains(String[] arr, String str){
		for (int i = 0; i < arr.length; i++) {
			if(str.equals(arr[i]))
				return true;
		}
		return false;
	}
	
	
	
	/* 
	 * this function will filter out the combinations that dont have lexicographic order (like "adc" or "hab")
	 * but this wont be needed as all 56 combinations have this property
	 
	static List<String> foo(Character[][] chs){
		List<String> cand = new ArrayList<String>();
		
		for (int i = 0; i < chs.length; i++) {
			if(chs[i][0] < chs[i][1]   && chs[i][1] < chs[i][2])
				cand.add(Arrays.toString(chs[i]));	
		}
		
		return cand;
	}
	
	*/
	

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





class Combinations {


	static Character[][] combinations(Character[] n, int r){
		List<Character[]> results = new ArrayList<Character[]>();
		int alpha = n.length;
		
		if(r == alpha) return new Character[][]{ Arrays.copyOf(n, n.length) };
		else if(r > alpha) return new Character[][]{};
		else if(alpha>1 && r==1){
			for (int i = 0; i < alpha; i++){
				results.add(new Character[]{n[i]});
			}
				 
		}
		
		
		else{			
			Character[] temp;
			for (int i = 0; i < alpha; i++) {
				temp =   subArray(n, i+1) ;
				
				Character[][] comb = combinations(temp, r-1);
				for (int j = 0; j < comb.length; j++){
					Character[] t = addToFront(comb[j], n[i]);
					
				
					results.add(t);
					
				}
			}	
		}

		return  results.toArray(new Character[0][]);
	}	
	
	static Character[] addToFront(Character[] arr, Character a){
		Character[] ret = new Character[1 + arr.length];
		ret[0] = a;
		for (int i = 1; i < ret.length; i++) {
			ret[i] = arr[i-1];
		}
		return ret;
	}
	
	static Character[] subArray(Character[] n, int i){
		Character[] ret = new Character[n.length - i];
		int k =0 ;
		for (int j = i; j < n.length; j++,k++) {
			ret[k] = n[j];
		}
		return ret;
	}
	
	
}

