import java.math.BigInteger;


public class Euler56 {

	public static void main(String[] args) {
		int greatest = -1, I=-1,P=-1;
		BigInteger b;
		
		for (int i = 2; i < 100; i++) {
			for (int p = 10; p < 100; p++) {
				b = new BigInteger(""+i);
				b = b.pow(p);
				int s = digitSum(b.toString()) ;
				if( s > greatest ){
					greatest = s;
					I=i;
					P=p;
				}
			}
		}
		
		System.out.println("Greatest sum: "+greatest);
		System.out.println("a="+I+"    b="+P);
		
		
	}
	
	static int digitSum(String str){
		int sum = 0;
		for (int i = 0; i < str.length(); i++) {
			sum += Integer.parseInt(""+str.charAt(i));
		}
		return sum;
	}

}
