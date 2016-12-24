import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;


public class Euler66 {
	
	// Check project Euler66_2 for working solution !!
	
	
	/*
	 * 	x^2 - Dy^2 = 1
	 * 	y = Sqrt( x^2-1  /  D  )    or     y = -Sqrt( x^2-1  /  D  )   but we only want positive y so ignore this one.
	 *  Since y has to be an Integer, and ignoring the solution (x=1, y=0), the square root has to be an integer.
	 *  so  x^2-1/D   has to be an integer, this means x^2-1 = D.k   x = Sqrt(D.k +1)   
	 *  however , we have the following:  y= Sqrt(D.k/D) = Sqrt(k)  so k has to be a perfect square!!  in fact k = y^2
	 *  In other words, to find integral x,y solutions for the equation y = Sqrt( x^2-1  /  D  )  we dont have to try x's one by one.
	 *  we will instead try k( remember k=y^2) one by one (perfect squares!!) , so 
	 *  x^2 = D.y^2 +1 (also equal to DK +1)  try all k's (or y^2) in order (low to high) and see if u get a perfect square.
	 *      

	 */
	
	
	static MathContext mc = new MathContext(25,RoundingMode.UP);
	
	public static void main(String[] args) {
		BigInteger greatest = BigInteger.ZERO;
		int ans=-1;
		
		for (int D = 1; D <= 61; D++) {
			if(isInteger(Math.sqrt(D)))	continue;

			BigInteger k;                                // in fact k=y^2
			BigInteger xsqrd = null;		
			BigInteger i = BigInteger.ONE;               // in fact i=y
			
			boolean found = false;
			while(!found){	
				k = i.pow(2);                            // As we said above, k has to be a perfect square. in fact k=y^2 !!
				xsqrd = getX(k, D);
				

				found = check(xsqrd, D);                 // check if xsqrd is a perfect square, if it is, this means its square root is integer!!	 this functions uses an performance-expensive sqrt() function	
				
					
				i = i.add(BigInteger.ONE);
			}
					
			
			if( xsqrd.compareTo(greatest) == 1 ){
				greatest = new BigInteger(xsqrd.toString());
				ans = D;		
			}
			System.out.println("D:"+D+"        (min-x)^2 : "+xsqrd.toString());
		}	
		
		
		
		System.out.println("\nanswer:  D="+ans);
				
	}
	
	// given y^2,D this function will give x^2 from the equation   x^2 -1 = D.y^2  = D.K
	static BigInteger getX(BigInteger k, int D){
		BigDecimal  x;	
		 x = new BigDecimal( (k.multiply(new BigInteger(D+""))).add(BigInteger.ONE) );	
		return x.toBigInteger();
	}
	
	
	// this function checks if x is perfect square, if it is, this means
	static boolean check(BigInteger xsqrd, int D){
		if(xsqrd== null || xsqrd.equals(BigInteger.ONE)) return false;
		BigDecimal x = sqrt(new BigDecimal(xsqrd), mc);
		return isInteger(x);
	}	
	
	
	static boolean isInteger(BigDecimal x) {
		BigDecimal b1 = new BigDecimal(x.toString());
		b1 = b1.setScale(0, RoundingMode.CEILING);
		BigDecimal b2 = b1.subtract(x);
	
		if(   b2.compareTo(new BigDecimal("0.000000000000000001"))   <= 0 )
			return true;
			
		b1 = new BigDecimal(x.toString());
		b1 = b1.setScale(0, RoundingMode.FLOOR);	
		b2 = x.subtract(b1);

		if(   b2.compareTo(new BigDecimal("0.000000000000000001"))   <= 0 )
			return true;
		return false;	
	}
	
	static boolean isInteger(double x) {
		if(Math.ceil(x)-x <= 0.00000000001)
			return true;
		return false;	
	}
	
	
	// This code isnt mine, it's to calculate sqrt with precision!!
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
	
	
	
}
