import java.math.BigInteger;


public class Euler48 {

	public static void main(String[] args) {
		BigInteger sum = BigInteger.ZERO;
		for (int i = 1; i <= 1000; i++) {
			BigInteger temp = new BigInteger(""+i);
			sum = sum.add( temp.pow(i) );
		}
		String str = sum.toString();
		System.out.println(str.subSequence(str.length()-10, str.length()));

	}

}
