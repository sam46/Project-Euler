import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.*;

/*
 	sqrt(2)  has to be between 1 and 2
 	sqrt(15) has to be between 1 and 15 
 	and so on.
 	
 	Now let's take sqrt(2), we know  
 	  	1<sqrt(2)<2
 		avg(1,2) = 1.5   but 1.5^2 > 2    --> sqrt(2) has to be less than 1.5
 		1<sqrt(2)<1.5
 		avg(1,1.5) = 1.25   but 1.25^2 < 2  --> sqrt(2) has to be greater than 1.25
 		1.25<sqrt(2)<1.5
 		and we keep doing that until we reach the required precision.
 	
 		How do we know that we've reached the intended precision?
 		if we want sqrt(2) to be precise to at least 4 digits after the decimal point
 		then:
 		1.abcdxxxxxx.. < sqrt(2) < 1.abcdyyyyy...
 		that is: the lower and higher bounds both agree on the first 4 digits "abcd"!! 
 		
 	Note: they want the sum of the square root's first 100 digits to include the digits before the decimal point!!	
 */



public class Euler80 {
	static MathContext mc = new MathContext(500,RoundingMode.UP);
	static BigDecimal TWO = new BigDecimal("2");
	static List<Integer> squares;
	
	public static void main(String[] args) {
		squares = new ArrayList<Integer>();
		for (int i = 1; i < 15; i++) 
			squares.add(i*i);
		
		int total = 0;
		for (int i = 1; i <= 100; i++) {
			if(squares.contains(i)) continue;
			int dig = digits((int)Math.sqrt(i));
			BigDecimal sqrt = approximate(BigDecimal.ONE, BigDecimal.valueOf(i), dig);
			total += sum(sqrt);
		}
		
		System.out.println(total);
	}
	
	static BigDecimal approximate(BigDecimal Low, BigDecimal High, int digits){
		BigDecimal avg = null, low = Low, high = High;
		do{
			avg = avg(low,high);

			//System.out.println("low: "+low.toString());//.substring(0,10));
			//System.out.println("high: "+high.toString());//.substring(0,10));
			if(avg.multiply(avg,mc).compareTo(High) >= 0)
				high = avg;
			else
				low = avg;
			//System.out.println("avg: "+avg.toString());//.substring(0,10));
			//System.out.println();
		
		} while (low.multiply(BigDecimal.TEN.pow(102+digits)).toBigInteger().compareTo(high.multiply(BigDecimal.TEN.pow(102+digits)).toBigInteger()) != 0);
		
		return avg;
	}	
	
	static BigDecimal avg(BigDecimal b1, BigDecimal b2){
		BigDecimal temp = b1.add(b2,mc);
		return temp.divide(TWO,mc);
	}

	static int sum(BigDecimal x){
		String str = (x.multiply(new BigDecimal(10).pow(100))).toBigInteger().toString();
		str = str.substring(0,100);
		int sum = 0;
		for (int i = 0; i < str.length(); i++) 
			sum += Integer.parseInt(str.charAt(i)+"");
		return sum;
	}

	static int digits(int x){
		return (int)Math.floor((Math.log10(x))) + 1;
	}
	

}
