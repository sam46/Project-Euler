import java.math.BigInteger;


public class Euler57 {

	public static void main(String[] args) {
		/* simple code for demonstration here:
		 * 		for(int i = 1; i<=6; i++){
		 *			int[] f = foo(1,2,i);
		 *			f[0] = f[0]+f[1]; // add 1 to the resulting fraction to get the final fraction.
		 * 			System.out.println(f[0]+"/"+f[1]);
		 * 		}
		 */
				
		int answer = 0;
		for(int i = 1; i <= 1000; i++) {
		 	BigInteger[] f = foo(new BigInteger("1"), new BigInteger("2"),i);
		 	f[0] = f[0].add(f[1]);  
		 	
		 	String n = f[0].toString();
		 	String d = f[1].toString();
			if(n.length() > d.length())
			 	answer++;
		}
		
		System.out.println("answer: "+answer);
	}

	// this is the BigInteger version of the function foo() below
	static BigInteger[] foo(BigInteger num, BigInteger den, int iter){
		if(iter<=1)
			return new BigInteger[] { new BigInteger(num.toString()),  new BigInteger(den.toString()) };
		
		BigInteger n,d;
		n =  den.multiply(new BigInteger("2"));
		n = n.add(num);
		d = new BigInteger(den.toString());
		return foo(d,n, iter-1); // return (n/d)^-1 = d/n
	}
	


	// this function calculates    1/(2+(1/(2+(1/(2+(num/den))))) or   ((((num/den +2)^-1 +2)^-1 +2)^-1.....)  "iter" times.
	// by starting at the innermost fraction and going all the way up to the top
	// so i start with num/den  (which should be 1/2, passed as initial arguments for the sake of this problem)
	// and then I calculate 2 + num/den = (den*2+num)/den
	// and pass the reciprocal of that, and pass iter-1 indicating that we're done with one level.
	// the final(base) case is when iter==1 and then i'll have finished the computation and we'll just return the resulting fraction.
	static int[] foo(int num, int den, int iter){
		if(iter<=1)
			return new int[] { num, den };
		
		int n,d;
		n = den*2 +num;
		d = den;
		return foo(d,n, iter-1); // return (n/d)^-1 = d/n
	}
	
}
