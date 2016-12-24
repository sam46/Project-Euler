import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class Euler99 {

	/*
	 	which is greater 3^7 or 10^2
	 	I'll write 3^7 as 10^x :
	 	3^7 = 10^x   --> log_10(3^7) = log_10(10^x)
	 	--> x = 7*log_10(3)
	 	now if x > 2   then 3^7=10^x   is greater than 10^2
	 	
	 	so we don't have to calculate the actual numbers in order to compare them.
	 	we'll just write'em all as (10^something) and compare their something's.
	 */
	
	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(new FileReader("p099.txt"));
		
		String[] str = new String[1000];
		for (int i = 0; i < str.length; i++) str[i] = sc.nextLine();
		sc.close();
		
		StringTokenizer st;
		double greatestExp = -1;
		int index = -1;
		for (int i = 0; i < str.length; i++) {
			System.out.println("i: "+i);
			st = new StringTokenizer(str[i],",");

			double temp = exponent(st.nextToken(), st.nextToken());
			
			if(temp>greatestExp) {
				greatestExp = temp;
				index = i;
			}		
		}
		
		System.out.println(index+1);
		
	}
	
	static double exponent(String base, String exponent){
		int bs = Integer.parseInt(base);
		int exp = Integer.parseInt(exponent);
		
		double x = exp*Math.log10(bs);                        // I'll write all numbers (bs^exp) as (10^x)
		return x;
	}

}
