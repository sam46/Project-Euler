import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;


public class Euler29 {

	public static void main(String[] args) {
		List<BigInteger> terms = new ArrayList<BigInteger>(); 
		BigInteger bi;
		
		for (int i = 2; i < 101; i++) {
			bi = new BigInteger(""+i);
			for (int j = 2; j < 101; j++) {
				bi = bi.multiply(new BigInteger(""+i));
				if(!terms.contains(bi))
					terms.add(new BigInteger(bi.toString()));
			}
		}
		
		System.out.println(terms.size());
	}

}
