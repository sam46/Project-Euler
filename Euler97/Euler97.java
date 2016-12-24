import java.math.BigInteger;


public class Euler97 {

	public static void main(String[] args) {			
		BigInteger num = new BigInteger("2");
		num = num.pow(7830457);
		num = num.multiply(new BigInteger("28433"));
		num = num.add(BigInteger.ONE);
		System.out.println(num.mod(new BigInteger("10000000000")));  // last ten digits --->  num % (10^10) 
	}

}
