import java.math.BigInteger;


public class Euler31 {

	public static void main(String[] args) {
		int sum = 0;
		BigInteger ways = new BigInteger("1");
		
		for (int ones = 0; ones <= 200; ones++) {
			for (int twos = 0; twos <= 100; twos++) {
				for (int fives = 0; fives <= 40; fives++) {
					for (int tens = 0; tens <= 20; tens++) {
						for (int twenties = 0; twenties <= 10; twenties++) {
							for (int fiftys = 0; fiftys <= 5; fiftys++) {
								for (int hundreds = 0; hundreds <= 2; hundreds++) {
									sum = ones + 2*twos + 5*fives + 10*tens + 20*twenties 
											+ 50*fiftys	+ 100 *hundreds;
									if(sum == 200)
										ways = ways.add(BigInteger.ONE);
								}
							}
						}
					}
				}
			}
		}
		
		
		System.out.println(ways);
	}
	

}
