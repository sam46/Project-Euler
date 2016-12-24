import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;


public class Euler42 {
	// let's say the longest word contains 30 letters and all its letters are z
	// thus it's value will be 30*26= 780, 
	// now 0.5n(n+1) >= 780  ---> n >= 39 considering only positive solutions.
	// we'll just take n to be 40 !! 
	
	
	static int[] triNums = new int[40];
	static void genTriNums(){
		triNums[0] = 1;
		for (int i = 1; i < triNums.length; i++) {
			triNums[i] = i+1 + triNums[i-1];
		}
	}
	
	
	
	public static void main(String[] args) throws FileNotFoundException {
		genTriNums();
		Scanner sc = new Scanner(new FileReader("f:\\words.txt"));
		StringTokenizer st;
		
		int answer = 0;

		while (sc.hasNext()) {
			st = new StringTokenizer(sc.nextLine(),",");
			while (st.hasMoreElements()) {
				String str = st.nextToken();
				if( contains(wordValue(str))  )
					answer++;

			}
		}
		
		System.out.println(answer);
		
	}
	
	static int wordValue(String str){
		int v = 0;
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			if(Character.isLetter(ch) && ch!=' ' && ch!='\"')
				v +=  Character.toLowerCase(ch) - 'a' + 1;
		}
		return v;
	}
	
	static boolean contains(int x){
		for (int i = 0; i < triNums.length; i++) 
			if(x== triNums[i])
				return true;
		return false;
	}

}
