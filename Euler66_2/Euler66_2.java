import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.*;

public class Euler66_2 {

	public static void main(String[] args) {

		
		BigInteger greatest = new BigInteger("0");
		int ans = 0;
	
		for (int D = 1; D <= 1000; D++) {
			if(isInteger(Math.sqrt(D)))	continue;
			
			BigInteger temp = solve(D);
			if(temp.compareTo(greatest) == 1){
				greatest = new BigInteger(temp.toString());
				ans = D;
			}
			
		}
		
		System.out.println("Greatest x: "+greatest+"\nis when D="+ans);
		
	}
	
	// Solves the pell(diophantine) equation with given constant D, by using the convergents of sqrt(D)
	// check https://en.wikipedia.org/w/index.php?title=Pell%27s_equation&redirect=no
	// check https://en.wikipedia.org/wiki/Generalized_continued_fraction#Simple_convergence_concepts
	// Since we're only interested in x, we'll return it and ignore y
	static BigInteger solve(int D){
		
		// Important: Note that for continued fractions of square roots, the numerators are always 1, so (a0,a1,a2,a3,....a_n) all of them are 1.
		
		
		BigDecimal num = sqrt(new BigDecimal(""+D),mc); // initial numerator: sqrt(D)
		BigDecimal den = new BigDecimal("1");  // intial denomenator: 1

		BigDecimal[] frac = fraction(num, den);  

		BigDecimal Ap = frac[2];                    // previous A  (or A_n-1)
		BigDecimal App = BigDecimal.ONE;             // previous previous A   (or A_n-2)

		BigDecimal Bp = BigDecimal.ONE;
		BigDecimal Bpp = BigDecimal.ZERO;

		frac = fraction(frac[0], frac[1]);                // the new frac[] will hold the next continued fraction and integral

		BigDecimal A = null, B=null ;					 // A and B	.	The convergent numerator and denominator!!	

		BigInteger[] sols = {Ap.toBigInteger(), Bp.toBigInteger()};           // solutions array!! holds the numerator and denominator of the convergent.
		
		for (int i = 1; ; i++) {
			if(checkSol(sols[0], sols[1], D)){
				//System.out.println(sols[0]+", "+sols[1]);
				return sols[0];
			}
			
			A = (frac[2].multiply(Ap)).add(App);		             // the new A
			
			if(i!=1)      B = (frac[2].multiply(Bp)).add(Bpp);      // the new B
			else if(i==1) B = frac[2];                              // special case, when n=1: B_n = B_1  . check wikipedia!! 
			
			//System.out.println(A+"/"+B);
			sols[0] = new BigInteger(A.toString());
			sols[1] = new BigInteger(B.toString());
			
			
			frac = fraction(frac[0], frac[1]);                  // update frac[] to hold the next continued fraction
			
			App = new BigDecimal(Ap.toString());
			Ap = new BigDecimal(A.toString());
			
			Bpp = new BigDecimal(Bp.toString());
			Bp = new BigDecimal(B.toString());
			
		}
		
	}
	
	// for a given x,y,d , check that x^2 - Dy^2 == 1
	static boolean checkSol(BigInteger x, BigInteger y, int D){
		BigInteger t = (y.multiply(y)).multiply(new BigInteger(""+D));
		BigInteger lefthand  = (x.multiply(x)).subtract(t);
		return lefthand.equals(BigInteger.ONE);
	}


	

	// return the integral part of  num/den
	static int integ(BigDecimal num, BigDecimal den){
		return (num.divide(den,mc)).intValue();
	}
	
	// This is the function foo() from problem 64, modified!!    return a 3-element array, example fraction(5/2) returns {1,2,2} because 5/2 = 2 + 1/2
	static BigDecimal[] fraction(BigDecimal num, BigDecimal den){
		int integ = integ(num, den);
		BigDecimal numm = new BigDecimal(den.toString());
		BigDecimal denn = num.subtract( den.multiply( new BigDecimal(integ))) ;
		return new BigDecimal[]{numm,denn, new BigDecimal(integ)};
	}
	
	

	static MathContext mc = new MathContext(3000,RoundingMode.UP);
	// This code isnt mine, it's to calculate sqrt for bigdecimals!!
	private static final BigDecimal TWO = BigDecimal.valueOf(2L);
	public static BigDecimal sqrt(BigDecimal x, MathContext mc) {
		BigDecimal g = x.divide(TWO, mc);
		boolean done = false;
		final int maxIterations = mc.getPrecision() + 1;		
		for (int i = 0; !done && i < maxIterations; i++) {
			// r = (x/g + g) / 2
			BigDecimal r = x.divide(g, mc);
			r = r.add(g);
			r = r.divide(TWO, mc);
			done = r.equals(g);
			g = r;
		}
		return g;
	}
	
	
	
	static boolean isInteger(double x) {
		if(Math.ceil(x)-x <= 0.00000000001)
			return true;
		return false;	
	}
	
	
}
