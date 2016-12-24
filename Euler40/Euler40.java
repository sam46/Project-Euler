import java.math.BigInteger;


public class Euler40 {

	public static void main(String[] args) {
		String str = "";
		int i = 1;
		while(str.length()<= 1000000){
			str += i;
			i++;
		}

		long x = 1;
		x *= Long.parseLong(""+str.charAt(0));
		x *= Long.parseLong(""+str.charAt(9));
		x *= Long.parseLong(""+str.charAt(99));
		x *= Long.parseLong(""+str.charAt(999));
		x *= Long.parseLong(""+str.charAt(9999));
		x *= Long.parseLong(""+str.charAt(99999));
		x *= Long.parseLong(""+str.charAt(999999));
		
		System.out.println("answer: "+x);
	}

}
