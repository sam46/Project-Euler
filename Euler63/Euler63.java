import java.math.BigInteger;


public class Euler63 {
/*
 *  x^i = y and we want digit(y) = i
 *  suppose x = 10,  10 ^1 = 10, 10^2 = 100, 10^3 = 10000   we notice that digit(y) > i  or more accurately i+1 = digit(y)
 *  Obviously if x is even more than 10, digit(y) will still be > i
 *  so now x<10.
 *  when x = 3    3^2 = 9,    3^3 = 27    3^4 = 81  so digit(y) < i    except when the power is 1:   3^1 = 3
 *  if x is less than 3 same thing happens.
 *  so:			3 < x < 10
 *  now we have to find a limit for the power:
 *  we'll just start with power 1 and keep increasing until we hit a power where digit(y) != i in other words the digits are less or more than the power.
 *  The End!!
 */
	
	
	public static void main(String[] args) {
		int ans = 9;      // 1^1  2^1  3^1 4^1 ... 9^1  all have this property obviously.
		BigInteger bi;
		
		for (int x = 4; x <= 9; x++) {
			String str = "";
			int p = 1;
			
			do {
				
				p++;
				bi = new BigInteger(""+x);
				bi = bi.pow(p);
				str = bi.toString();
				if(str.length() == p)
					ans++;			
				
			} while (str.length() == p);
			
			System.out.println(""+x+" breaks at power "+p);
		}
		
		System.out.println("total numbers with this property: "+ans);	
	}



}
