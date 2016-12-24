import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/*
 * 	Fill 6 Lists with the appropriate polygonal numbers that are 4 digits (between 1000 and 10000) using the function poly(int,int).
 *  run a 6 nested for-loops on the lists producing an int[] arr that contains 6 elements one from each list.
 *  now pass that arr[] to test().  
 *  test() will produce all arrangements (orders or permutations) of the elements in arr[] 
 *  and will pass each arrangement to isCyclic(String str) that will decide whether or not this arrangement is cyclic.
 * 	if isCyclic() returns true, then test will return that arrangement as a string. which should be the answer.
 * 	if arr[] isn't the right combination, test() return nulls, and the for-loops continue.
 *  The End.
 *  
 *  For Optimization:
 *   suppose we want only 3 4-digit cyclic numbers:
 *   for example:     abcd cdef efab so 2*(a,b,c,d,e,d) .
 *   or we can have:   abab abef efab  so 2*(e,f) 4*(a.b)  
 *   so we must have an even number of any digit!!
 *   I've added this optimization in the function checkLetterCount(String) which'll test if there's an even number of every digit or not.
 */

// 61

public class Euler61 {

	static String[] alphaPerm;
	static HashSet<String> fails = new HashSet<>();
	
	public static void main(String[] args) throws FileNotFoundException {
		alphaPerm = Permutation.Permutations("abcdef"); // later in test(), each letter will be replaced by a 4-digit number.
		/*
		List<Integer>Tri =  new ArrayList<Integer>(),
				Sq = new ArrayList<Integer>(),
				Pen = new ArrayList<Integer>(),
				Hex = new ArrayList<Integer>(),
				Hep = new ArrayList<Integer>(),
				Oct = new ArrayList<Integer>();
			
		//System.out.print("tri: [ ");
		int temp = 0;
		while (true) {
			int x = poly(3,temp++);
			if(x>9999)
			break;
			if(x>999){
				Tri.add(x);
				//System.out.println(x);
			}
			
		}	

		//System.out.print("\nsqr: [ ");
		temp = 0;
		while (true) {
			int x = poly(4,temp++);
			if(x>9999)
			break;
			if(x>999){
				Sq.add(x);
				//System.out.print(x+", ");
			}
		}	

		//System.out.print("\npen: [ ");
		temp = 0;
		while (true) {
			int x = poly(5,temp++);
			if(x>9999)
			break;			
			if(x>999){
				Pen.add(x);
				//System.out.print(x+", ");
			}
		}	

		//System.out.print("\nhex: [ ");
		temp = 0;
		while (true) {
			int x = poly(6,temp++);
			if(x>9999)
			break;
			if(x>999){
				Hex.add(x);
				//System.out.print(x+", ");
			}
		}	

		//System.out.print("\nhep: [ ");
		temp = 0;
		while (true) {
			int x = poly(7,temp++);
			if(x>9999)
			break;
			if(x>999){
				Hep.add(x);
				//System.out.print(x+", ");
			}
		}	

		//System.out.print("\noct: [ ");
		temp = 0;
		while (true) {
			int x = poly(8,temp++);
			if(x>9999)
			break;
			if(x>999){
				Oct.add(x);
				//System.out.print(x+", ");
			}
		}		
		
		int[] tri = new int[0], sq  = new int[0], pen = new int[0], hex = new int[0], hep = new int[0], oct = new int[0];
		tri = convertIntegers(Tri);
		sq = convertIntegers(Sq);
		pen = convertIntegers(Pen);
		hex = convertIntegers(Hex);
		hep = convertIntegers(Hep);
		oct = convertIntegers(Oct);
				
		
		StringBuilder sb;
		
		for (int i = 61; i < tri.length; i++) {
			int x0 = tri[i];
			System.out.println("i: "+i+"/"+tri.length);
			for (int j = 0; j < sq.length; j++) {
				int x1 = sq[j];
				if(x1 == x0) continue;

			//	System.out.println("j: "+j+"/"+sq.length);
				for (int k = 0; k < pen.length; k++) {
					int x2 = pen[k];
					if(x2 == x0 || x2 == x1) 
						continue;
				//	System.out.println("k:"+k);
					
					for (int m = 0; m < hex.length; m++) {
						int x3 = hex[m];
						if(x3 == x0 || x3 == x1 || x3 == x2) 
							continue;
						
						for (int n = 0; n < hep.length; n++) {
							int x4 = hep[n];
							if(x4 == x0 || x4 == x1 || x4 == x2 || x4 == x3)
								continue;
							
							for (int g = 0; g < oct.length; g++) {
								int x5 = oct[g];
								if(x5 == x0 || x5 == x1 || x5 == x2 || x5 == x3 || x5 == x4)
									continue;
								
								int[] arr = {x0,x1,x2,x3,x4,x5};
								sb = new StringBuilder("");
								sb.append(x0).append(x1).append(x2).append(x3).append(x4).append(x5);
								if(!checkLetterCount(sb.toString()))
									continue;
								
								String result = test(arr);
								if(result != null){
									System.out.println("answer: "+result);
									System.out.println("sum: "+ (x0+x1+x2+x3+x4+x5));
									return;
								}
							}
						}
					}
				}
			}
		}
	*/
		
		Scanner sc = new Scanner(new File("f:\\cand.txt"));
		while(sc.hasNext()){
			String s="";
			String str = sc.nextLine();
			StringTokenizer st = new StringTokenizer(str, ",");
			int[] arr = new int[6];
			for (int i = 0; i < arr.length; i++) {
				s = st.nextToken().trim();
				try{
				arr[i] = Integer.parseInt(s);
				}catch (Exception e){
					System.out.println(s);
				}
			}
			
			
			if(test(arr) != null)
				for (int i = 0; i < arr.length; i++) 
					System.out.print(arr[i]+", ");				
		}
		
		sc.close();
	}

	
	static String test(int[] arr){
		//String temp = "";
		
		for (int i = 0; i < alphaPerm.length; i++) {
			//temp = alphaPerm[i];
			int[] temp2 = new int[6];
			for (int j = 0; j < alphaPerm[i].length(); j++) {
				temp2[j] = arr[ alphaPerm[i].charAt(j) - 'a']; 
			}
			
			//for (int j = 0; j < arr.length; j++) 
				//temp = temp.replace(String.valueOf(((char)('a'+j))), String.valueOf(arr[j]));

			/* optimization here:
			the every 24 consecutive arrangements have the same first 2 numbers so abcdef,abcdfe...(21 more)...abfedc then: acbdef....   
			(first 24 arrangements abxxxx  , second 24 arrangements acxxxx  and so on)!!
			so if the first 2 numbers (in every other 24 arrangements)  are not cyclic
			then the next 23 arrangements wont be cyclic since they have the same first 
			so we'll just skip em !!
			
			if(!isCyclic(temp.substring(0, 4), temp.substring(4, 8))){
				
				i += 23;
				continue;
			}
			/* optimization end
			
			// another similar optimization, but for the 2nd and 2rd numbers, they are the same in every 6 consecutive arrangements!!
			if(!isCyclic(temp.substring(4, 8), temp.substring(8, 12))){
				i += 5;
				continue;
			}
			
			*/
			
			

			if(temp2[0]%100 != 100*(temp2[1]%10000)) {
				i += 23;
				continue;
			}
			
			if(isCyclic(temp2))
				return (temp2[0]+""+temp2[1]+""+temp2[2]+""+temp2[3]+""+temp2[4]+""+temp2[5]);
		}
		
		return null;
	}
	
	static boolean isCyclic(int[] arr){
		if(arr[5]%100 != 100*(arr[0]%10000))
			return false;
		for (int i = 1; i < arr.length-2; i++) {
			if( (arr[i]%100) != 100*(arr[i+1]%10000) )
				return false;
		}
		return true;
	}
	
	static boolean isCyclic(String str){
		if(!isCyclic(str.substring(0, 2), str.substring(22)))
			return false;
		
		for (int i = 8; i <= 4*4; i+=4) {  // i=8: start at the 3rd number cuz the first 2 numbers were checked in test()'s optimization part.
			String a = str.substring(i,i+4);
			String b = str.substring(i+4,i+8);
			if(fails.contains(a+b))
				return false;
			if(!isCyclic(a, b)){
				fails.add(a+b);
				return false;
				
			}
		}
		
		return true;
	}
	
	static boolean isCyclic(String a, String b){
		if((a.substring(a.length()-2)).equals( b.substring(0, 2) ))
			return true;
		return false;
	}	

	static boolean checkLetterCount(String str){
		int[] nums = {0,0,0,0,0,0,0,0,0,0};

		for (int i = 0; i < str.length(); i++) {
			int x = Integer.parseInt(""+str.charAt(i));
			nums[x]++;
		}
		
		for (int i = 0; i < nums.length; i++) {
			if(nums[i]%2==1)
				return false;
		}
		return true;
	}
	

	static int poly(int y,int n){
		if(y==3){
			return (n*(n+1)/2);
		}
		
		else if(y==4){
			return n*n;
		}

		else if(y==5){

			return (n*(3*n-1)/2);
		}

		else if(y==6){

			return (n*(2*n-1));
		}

		else if(y==7){

			return (n*(5*n-3)/2);
		}

		else if(y==8){

			return (n*(3*n-2));
		}
		
		return -1;
	}
	
	
	
	static int[] convertIntegers(List<Integer> integers)
	{
	    int[] ret = new int[integers.size()];
	    for (int i=0; i < ret.length; i++)
	    {
	        ret[i] = integers.get(i).intValue();
	    }
	    return ret;
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
