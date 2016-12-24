import java.math.BigInteger;


public class Euler25 {

	public static void main(String[] args) {
		BigInteger a= BigInteger.ONE;
		BigInteger b= BigInteger.ONE;
		BigInteger c;
		String str ="";
		int i=2;
		while(str.length()!=1000){
			c= new BigInteger((a.add(b)).toString());
			a = new BigInteger(b.toByteArray());
			str = c.toString();
			b = new BigInteger(c.toByteArray());
			i++;
		}
		System.out.println(i);
		
		
	}

}
