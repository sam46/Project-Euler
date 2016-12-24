import java.math.BigInteger;
/*
	max: 1929394959697989990 --> sqrt(1929394959697989990) = 1389026623.11
	min: 1020304050607080900 --> sqrt(1020304050607080900) = 1010101010.1
	
	min < number < max
	our number has a units of 0 because number^2 = 1_2_3_4_5_6_7_8_9_0
	so start at min and keep stepping up by 10
 */
public class Euler206 {
	static BigInteger min;
	
	public static void main(String[] args) {
		min = new BigInteger("1010101010");
		BigInteger step = new BigInteger("10");
		BigInteger bi = new BigInteger(min.toString());
		while(true){
			bi = bi.add(step);
			String str = bi.pow(2).toString();
			if(check(str))
				break;
		}		
		System.out.println(bi);
	}
	
	static boolean check(String str){
		if(str.length()==19){
			
			if(str.charAt(0) != '1')
				return false;
			if(str.charAt(2) != '2')
				return false;
			if(str.charAt(4) != '3')
				return false;
			if(str.charAt(6) != '4')
				return false;
			if(str.charAt(8) != '5')
				return false;
			if(str.charAt(10) != '6')
				return false;
			if(str.charAt(12) != '7')
				return false;
			if(str.charAt(14) != '8')
				return false;
			if(str.charAt(16) != '9')
				return false;
			if(str.charAt(18) != '0')
				return false;

			return true;	
		}
		
		return false;
	}

}
