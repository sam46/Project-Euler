
public class Euler45 {

	public static void main(String[] args) {
		int i = 286;
		while(true){
			if( haveProperty( triangleNum(i) ) )
				break;
			i++;
		}
		
		System.out.println(triangleNum(i));
	}
	
	static long triangleNum(long n){
		return n*(n+1)/2;
	}
	
	
	static boolean haveProperty(long x){
		if(  isPentagonal( x ) )
			if(  isHexagonal( x ) )
				return true;
		return false;
	}
	
	// this function is from Problem 44
	static boolean isPentagonal(long y){
		if(y<0)			return false;
		double sqrtDelta = Math.sqrt( 1.0 + 24.0*y );
		if(sqrtDelta <= 0)    return false;   // delta == 0 this means y= -0.04 approximately
		double x = (1+sqrtDelta)/6.0 ;              // we're only interested in +sqrtDelta cuz -sqrtDelta will yield negative solutions!! 
		
		if(x == Math.floor(x)) // if x is actually a whole number (integer)
			return true;
		
		return false;
	}
	
	
	static boolean isHexagonal(long y){
		if(y<0)			return false;
		double sqrtDelta = Math.sqrt( 1.0 + 8.0*y );
		if(sqrtDelta <= 0)    return false;   // delta == 0 this means y= -0.04 approximately
		double x = (1+sqrtDelta)/4.0 ;              // we're only interested in +sqrtDelta cuz -sqrtDelta will yield negative solutions!! 
		
		if(x == Math.floor(x)) // if x is actually a whole number (integer)
			return true;
		
		return false;
	}
	
}
