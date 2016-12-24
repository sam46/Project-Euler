
public class Euler44 {

	// A number y is pentagonal if y = x(3x-1)/2  --> 3x^2 -x -2y = 0  has solutions. (we're only interested in positive integral solutions!!)
	
	
	public static void main(String[] args) {
		for (long e = 0; e < 2000l; e++) { // e is for epsilon, which is how far i and j are!!  // I chose 2000 randomly, there has to be a better way

			for (long i = 1; i <= 400l*e; i++) {  // this counter i will determine how far the test will go, to (400*e)th term // 400 is also chosen randomly
				if(haveProperty(i, i+e))
					System.out.println("P("+(i+e)+") = "+P(i+e)+"   P("+i+") = "+ P(i) );
			}
		}

	}
	
	static boolean haveProperty(long j, long k){
		if(  isPentagonal( P(j) + P(k) ) )
			if( isPentagonal( Math.abs( P(j) - P(k) )  )   )
				return true;
		return false;
	}
	
	static long P(long n){
		return n*(3*n -1)/2;
	}
	
	static boolean isPentagonal(long y){
		if(y<0)			return false;
		double sqrtDelta = Math.sqrt( 1.0 + 24.0*y );
		if(sqrtDelta <= 0)    return false;   // delta == 0 this means y= -0.04 approximately
		double x = (1+sqrtDelta)/6.0 ;              // we're only interested in +sqrtDelta cuz -sqrtDelta will yield negative solutions!! 
		
		if(x == Math.floor(x)) // if x is actually a whole number (integer)
			return true;
		
		return false;
	}

}
