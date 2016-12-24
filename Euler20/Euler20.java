import java.math.BigInteger;


public class Euler20 {

	public static void main(String[] args) {
		String str = (fact(BigInteger.valueOf(100))).toString();
		
		System.out.println(str);
		BigInteger sum = BigInteger.ZERO;
		
		for (int i = 0; i <str.length(); i++) {
		
			sum = sum.add(new BigInteger(""+str.charAt(i)));
		}
		
		System.out.println(sum.toString());
		
	}

	static BigInteger fact(BigInteger n){
		if(n == BigInteger.ZERO  || n == BigInteger.ONE)
			return BigInteger.ONE;
		
		BigInteger b = new BigInteger(n.toString()) ;
		return n.multiply( fact(b.subtract(BigInteger.ONE)) );
	}
	
}
