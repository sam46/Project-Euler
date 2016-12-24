import java.math.BigInteger;


public class Euler16 {

	public static void main(String[] args) {
	
		BigInteger p = new BigInteger("2");
		p=p.pow(1000);
		String str = p.toString();
		long sum=0l;
		for (int i = 0; i < str.length(); i++) {
			sum += (long)Integer.parseInt(""+str.charAt(i));
		}
		
		System.out.println(sum);
	}

}
