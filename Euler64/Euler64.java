import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.*;

public class Euler64 {
	// takes a long time to terminate. (possibly because the sqrt() function is costly when the precision is high)
	// the answer it gives is: 1322
	
	
	// for some reason the precision has to be big(like 3000 big) to get the right result. maybe the sqrt has to be calculated very precisely.
	static MathContext mc = new MathContext(3000,RoundingMode.UP);
	
	public static void main(String[] args) {
		long[] ps = new long[1010];
		for (int i = 1; i < ps.length; i++) 	ps[i] = (long)i*(long)i;
		
		int ans = 0;
		
		for (long i = 2l; i <= 10000; i++) {
			if(Arrays.binarySearch(ps, i) >= 0)
				continue;		
			

			String str = "";			
			str = foo(sqrt(new BigDecimal(""+i),mc), new BigDecimal("1"),500,str,500); // periods are long!!
			int period = cycle(str);
		    if(i%1000 == 0)
				System.out.println("currently at: "+i);
			if(period % 2 == 1)
				ans++;			
		}
		
		System.out.println("total odd period fractions: "+ans);
		
	}


	
	
	
	// check wikipedia to understand what a continued fraction is!!
	static void foo(double num, double den, int n) {
		if(n<=0) return ;		
		
		int integ = (int) (num/den);
		foo(den, num - den*integ, n-1);                                        // 1/(num - den*integ,  den)   the reciprocal!!
	}
	
	
	// same function but with big decimals, and stores integ's in str to return it later. (the numbers will be separated by spaces)
	static String foo(BigDecimal num, BigDecimal den, int n, String str, int startingN){
		if(n<=0) return str;		

		int integ = (num.divide(den,mc)).intValue();
		BigDecimal numm = new BigDecimal(den.toString());
		BigDecimal denn = num.subtract( den.multiply( new BigDecimal(integ))) ;
		
		if(n==startingN)            // in the first iteration, i dont want to include the first integ in the string. only interested in the repeating integs
			str =  foo(numm, denn, n-1, str,startingN);
		else
			str = integ+" "+ foo(numm, denn, n-1, str,startingN);
		return str;
	}	
	
	
	
	// This code isnt mine, it's to calculate sqrt with precision!!
	private static final BigDecimal TWO = BigDecimal.valueOf(2L);
	public static BigDecimal sqrt(BigDecimal x, MathContext mc) {
		BigDecimal g = x.divide(TWO, mc);
		boolean done = false;
		final int maxIterations = mc.getPrecision() + 1;		
		for (int i = 0; !done && i < maxIterations; i++) {
			// r = (x/g + g) / 2
			BigDecimal r = x.divide(g, mc);
			r = r.add(g);
			r = r.divide(TWO, mc);
			done = r.equals(g);
			g = r;
		}
		return g;
	}
	

	// returns the period's length (supposing the period exists!!)
	// this is how the function decides what the cycle(or period) is:
	// we take chunks from str (first_chunk),  we compare it to all the following substrings until the end of the string.
	// we keep increasing the length of  first_chunk until we find our correct chunk.
	static int cycle(String str){
		int len;
		
		for (len = 1; len <= str.length()/2; len++) {
			String first_chunk = str.substring(0, len);
			boolean valid = true;
			
			for (int i = 1; i*len + len < str.length(); i++) {
				String temp = str.substring(i*len, i*len + len);
				if(!temp.equals(first_chunk)){
					valid = false;;
					break;
				}
			}
			
			if(valid){
			    // the length of the period is equal to the number of " "'s. The only way to know the right length is by counting the spaces!!
				//System.out.println("{"+first_chunk+"}  len="+ countspaces(first_chunk)) ;
				return countspaces(first_chunk);
			}
		}
		
		return -1;
	}
	
	
	static int countspaces(String str){
		int count = 0;
		for (int i = 0; i < str.length(); i++) 
			if(str.charAt(i)==' ')
				count++;
		return count;
	}
	
	

}
