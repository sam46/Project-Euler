/*
 
 	The function shortest() will return the shortest path for a cuboid (w,l,h)
 	This is how it works:
 	since the route from the spider to the fly can only be on the surface, we need at least 2 lines to connect the spider to the fly. the first line goes from the spider to some side on the cuboid
 	and the second line from that side to the fly!! so 
 	so starting from the spider's point, we have 6 options for where the turning point (the point where the transition from one side to the other happens) can be located.
 	but since the cuboid only has 3 distinct sides (width side, length side, height side),  we can ignore 3 options, and we're left with 3 options only.
 	these 3 options are (width side, length side, height side). and thus we have 3 candidate shortest paths!!
 	Note: we could have considered the other combinations from the 6 options , we'll get slightly different formulae but the answer(shortest route) will be the same!!
 	 
 	SEE THE ATTACHED IMAGE!!
 	The case described in the paper is taking one of the 3 options and giving a function f(y)  and 2 possible  y's  that should be plugged into f(y)
 			so that's two final candidates for the shortest path!!
 	
 	for each function in the paper I made a function in here so (fx(x ,w,l,h), fy(y ,w,l,h) ,fz(z ,w,l,h) focus on the x,y,z parameters!!)
 	Since for each side of the 3 sides there're 2 solutions, eventually we'll have these solutions x1,x2,y1,y2,z1,z2
 	by plugging those into the corresponding function (f(y), f(z) or f(x)) we'll have 6 final candidate shortest paths,
 	Only the the minimum of those will survive to be the final true one and only Answer 
 	
 ------------------------------------------------------------------------------------------------------
   Now that we know how to get the shortest path for a given cuboid
   we just have to know which length "M" achieves more than 1 million integer shortest paths if we were to test all cubois with sides less than M
   So we need a way to generate all those cuboids(SEE THE ATTACHED WORD DOC), run'em all against our function shortest(), and see how many of them have integer shortest path (or "integerSolutions")
   the first M to give more that 1 million integerSolutions is our holy answer!!
 	
   so we start with M=2 and keep increasing each time and we see how much is each M scoring. suppose we know how much M=4 scored, now we wanna see how much M=5 
   will score. We wont test the cuboids all over again, since after all of the cuboids for M=4 are a subset of the cuboids for M=5
   So I've added a function that use the score for m-1 to calculate the score for m.
   
   	That's all folks!!
 
 */


public class Euler86 {

	public static void main(String[] args) {
		int pre_sol = 0;
		int sol=0;
		int m;
		for (m = 2; sol<1000000; m++) {
			sol = NumOfSolutionsForM(m,m-1,pre_sol);
			System.out.println("m="+m+"    "+sol);
			pre_sol = sol;
		}
		
		System.out.println("Answer is "+(m-1));
	}
	
	
	static int NumOfSolutionsForM(int m){
		int integerSolutions = 0;

		// THIS FOR LOOP IS A PRODUCT OF THE REASONNG IN THE WORD DOCUMENT. CHECK IT OUT!!
		for (int i = 1; i <= m; i++) {
			for (int j = i; j <= m; j++) {
				for (int k = j; k <= m; k++) {
					if( isInteger( shortest(i, j, k) ) )												//Each (i,j,k) triple represent a different cuboid!!
						integerSolutions++;	
				}
			}
		}
		
		return integerSolutions;
	}
	
	// More advanced version of the function NumOfSolutionsForM(int m).
	// Uses the answer for m-1 to compute the answer for m (kinda recursive).
	static int NumOfSolutionsForM(int m,int pre_m,int pre_sol){
		int integerSolutions = pre_sol;

		for (int i = 1; i <= m; i++) {
			for (int j = i; j <= m; j++) {
				for (int k = m; k <= m; k++) {                                                          //Notice: k starts at m and stops at m!!  
					if( isInteger( shortest(i, j, k) ) ){												//Each (i,j,k) triple represent a different cuboid!!
						integerSolutions++;				
					}
				}
			}
		}
		
		return integerSolutions;
	}
	

	static double shortest(int w, int h, int l){
		double fx,fy,fz;
		
		// The solutions for f`(x)=0 and f`(y)=0 and f`(z)=0     (I derived the function f(x),f(y),f(z) and solved  f`(x)=0, f`(y)=0, f`(z)=0 using wolframAlpha     
		double  x1 = w*l*1.0/(h+l*1.0),
				x2 = w*l*1.0/(l-h*1.0),
				y1 = l*h*1.0/(l-w*1.0),
				y2 = l*h*1.0/(l+w*1.0),
				z1 = l*h*1.0/(h-w*1.0),
				z2 = l*h*1.0/(h+w*1.0);
		
		// I'll just put'em in an array.
		double[] vars = {x1,x2,y1,y2,z1,z2};
		
		
		double fx1 = fx(vars[0],w,h,l), fx2= fx(vars[1],w,h,l) ;
		if(l-h==0)
			fx1=Double.MAX_VALUE;
		fx = Math.min(fx1, fx2);
		
		
		double fy1=fy(vars[2],w,h,l) , fy2=fy(vars[3],w,h,l) ;
		if(l-w==0) // denominator == 0
			fy1=Double.MAX_VALUE;
		fy = Math.min(fy1, fy2);
		
		
		double fz1=fz(vars[4],w,h,l) , fz2=fz(vars[5],w,h,l);
		if(h-w==0)
			fz1=Double.MAX_VALUE;
		fz = Math.min(fz1, fz2);
		
		
		
		
		// The shortest length eventually is really just the minimum of (fx1,fx2,fy1,fy2,fz1,fz2) !!
		double shrtst = Math.min(fx, fy);
		shrtst = Math.min(shrtst, fz);
		
		return shrtst;
	}
	
	//The route's length when the candidate point is p1              , if we pass the right x, the route will be the shortest route
	static double fx(double x, int w, int h, int l){
		double length = Math.sqrt(x*x + l*l) + Math.sqrt((w-x)*(w-x) + h*h);
		return length;
	}
	
	
	//The route's length when the candidate point is p2            , if we pass the right x, the route will be the shortest route
	static double fy(double y, int w, int h, int l){
		double length = Math.sqrt(y*y + l*l) + Math.sqrt((h-y)*(h-y) + w*w);
		return length;
	}
	
	
	//The route's length when the candidate point is p3             , if we pass the right x, the route will be the shortest route
	static double fz(double z, int w, int h, int l){
		double length = Math.sqrt(z*z + h*h) + Math.sqrt((l-z)*(l-z) + w*w);
		return length;
	}
	


	// I need this function to circumvent floating-point arithmetic errors. sometimes 16 is represented as 3.99999 or 4.000001 or something like that
	public static boolean isInteger(double x){
		if(
				(Math.abs(x - (int)(x))  < 0.000000000001)
				                 ||
				(Math.abs(Math.ceil(x) - x)  < 0.000000000001)
		  )
			return true;
		return false;
	}
	
	
}




