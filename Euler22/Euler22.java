import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;


public class Euler22 {
	static String url = "f:\\names.txt";
	public static void main(String[] args) throws FileNotFoundException {
	
		String names="";
		Scanner sc = new Scanner(new FileReader(url));
		while (sc.hasNext()) 
			names += sc.nextLine();
		StringTokenizer tokenizer = new StringTokenizer(names, ",");
		String[] tokens = new String[tokenizer.countTokens()];
		int k=0;
		while (tokenizer.hasMoreTokens()) {
			String temp = tokenizer.nextToken();
			temp.trim();
			temp = temp.substring(1,temp.length()-1);
			tokens[k++] = temp;
		}
		
		//sort the array
		Arrays.sort(tokens);
		

		long scoreSum = 0;	
		for (long i = 0; i < tokens.length; i++) 
			scoreSum += (i+1)*(long)alphaSum(tokens[(int) i]);

		System.out.println(scoreSum);
	}
	
	static int alphaSum(String str){
		if(str == null || str.equals(""))
			return 0;
		int sum=0;
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if(Character.isLetter(c))
				sum +=  Character.toLowerCase(c) -'a' +1;
		}
		
		return sum;
	}

}
