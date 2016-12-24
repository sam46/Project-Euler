import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.*;

/*
 	Note: t for total, b for number of blue discs and r for number of red discs
 	Here's our constraints:    	
 		t = b+r
 		t > 10^12
 		f(b) = b(b-1) / t(t-1) = 0.5                       
 		b, r are positive natural numbers
 		
 	we wanna find integer b solutions for f(b)=0 with the constraint that t>10^12
 	Let's start by solving for b: b^2-b-2t(t-1)=0 will 2 solutions 1 positive 1 negative (which we'll ignore)
 			sqrt(delta) = sqrt(1 + 2t(t-1))
 			b = [1+sqrt(delta)]/2 
	so if we pick t we'll have our b
	And now it's just a matter of picking the right t that'll give us an integer b
	Done!!
	
	
	Here's a code to find b for t>100, The answer should be 85  
	
	public static void main(String[] args) {
		double b = -1;
		int t = 100;
		while(b==-1){
			System.out.println(t);
			double temp = b(t);
			if(isInteger(temp))
				b=temp;
			t++;
		}
		
		System.out.println("Total discs: "+(t-1));
		System.out.println("Blue discs: "+b);

	}
	
	static double b(long t){
		double delta = 1.0 + 2*t*(t-1);
 		double b = (1+Math.sqrt(delta))/2.0 ;
 		return b;
	}

	
	
	public static boolean isInteger(double x){
		if(
				(Math.abs(x - (int)(x))  < 0.000000000001)
				                 ||
				(Math.abs(Math.ceil(x) - x)  < 0.000000000001)
		  )
			return true;
		return false;
	}
	
	
	Now this program is basically the same code but modified to handle huge numbers.
	
	Side Note:
		we have b = [1+sqrt(delta)]/2 
		   2b - 1 = sqrt(delta)
		   (2b-1)^2 = delta
		   4b^2 - 4b + 1 = 1 + 2t(t-1)
		   ...
		   2 = (t^2 - t)/(b^2 -b)      * let's call it equation #1
		   
	   With a bit of math I can write this as:
		   8(b-1/2)^2 - 4(t- 1/2)^2 = 1
		   2(b- 1/2)^2 - (t- 1/2)^2 = 1/4
		   we're close, now:
		   (t- 1/2)^2 - 2(b- 1/2)^2 = -1/4
		   4(t- 1/2)^2 - 2.4(b- 1/2)^2 = -1/4
		   (2(t- 1/2))^2 - 2(2(b- 1/2))^2 = -1
		   
		   Finally:
		   		   (2t- 1)^2 - 2(2b- 1)^2 = -1
		     
		   
		This equation is similar to problem 66:  x^2 - Dy^2 = 1    and D can't be a Square
		in which we had to find integer solutions for x,y
		we did that by using continued fraction for sqrt(D)
		   
		How does this apply to this problem? well, if we can find one (integer) solution for our equation,
		we can then easily find other solution!!
		
		check this out: https://www.youtube.com/watch?v=E51GKQ1qorE
		
		I apply the same technique on this equation: 
		lets make (2t-1)=x  and (2b-1)=y  and D=2
		so x^2 - Dy^2 = -1
		we can write: (x+ sqrt(2).y) . (x- sqrt(2).y) = -1
		so it's:  (x+ sqrt(2).y)  times the conjugate of (x+ sqrt(2).y) = -1
		in other words the norm of (x+ sqrt(2).y) = -1
		
		so if I have a number in the form (x + sqrt(2).y) 
		and norm(a) = -1  then x=2t-1   and y=2b-1  are solutions to x^2 - Dy^2 = -1
		and thus, I can then calculate t (and b for that matter ) from x and y
		x and y will be numbers of the form something.5 (decimal numbers with .5 like 2.5 or 10.5)
		and so t and b are guaranteed to be whole integers!!
		
		so if I have another solution b (this b is not the blue discs varaible b. I forgot I already used the letterused b above XD) . then norm(b) = -1
		and by the norm properties: norm(a).norm(b) = norm(a.b) -1 . -1 = +1
		so (a.b) is NOT a solution cuz norm(a.b) is not -1
		but if I have a solution c then norm(a).norm(b).norm(c) = norm(a.b.c) = (-1)^3 = -1
		so (a.b.c) is a solution!!
		of course nothing stops us from making b or c equal to a:
		so norm(a.a.b) = norm(a^2 . b) = -1 
		so c = (a^2 . b) is a solution !!      (if a and b are both solutions)
		In the program, I chose (a) to be the solution in which (b=3, t=4)
		and I worked out the formula  x + sqrt(2).y  for the solution (a^2 . b)
		and since x = 2t-1  (this t is a new valid t which I can use to get a new valid b)
		t = 2x+1 / 2
		and b is b(t)       (once I have t I can use equation #1 to get b)
		and now I have a new fresh solution (b,t) !!
		
		the formula:
		if we pick the solution (3,4):    (2t-1)^2 - 2(2b-1)^2 = (2.4 - 1) - 2(2b-1) = -1
						so [(2t-1) + sqrt(2).(2b-1)] * [(2t-1) + sqrt(2).(2b-1)] = -1
						we're interested in [(2t-1) + sqrt(2).(2b-1)]
						let's plug the solution we picked: (2.4-1) + sqrt(2).(2.3-1) = 7 + 5.sqrt(2)
						that's our a.
						let b be the solution passed to the function newSolution() . using b  and a^2 we can get another solution
						c = a^2 * b   :                  note: by b I mean the expression x+sqrt(2).y which can be obtained from b (blue discs, total).
						  = (7 + 5.sqrt(2))^2 * b
						let's first write  b (B blue discs, T total) in conjugate form: x+sqrt(2).y to get :    (2T-1)^2 - 2(2B-1)^2 = -1
						 																	blah,blah ... (2T-1) + sqrt(2).(2B-1)  and that is it.
						c =  (99 + 70sqrt(2)) * (x+sqrt(2).y)  
							we try to write c in the form: x+sqrt(2).y
						c  = [99(2T-1) + 140(2B-1)] + sqrt(2)[70(2T-1) + 99(2B-1)]
						c  =         x			    +  sqrt(2) . y	
						we have it, we just want x:
						x = [99(2T-1) + 140(2B-1)]
						remember: x^2 - sqrt(2)y^2 = -1      or  (2t-1)^2 - 2(2b-1)^2 = -1
						so x = 2t-1             this t is the new t we're looking for!!
						t = x + 1  / 2
						t = [99(2T-1) + 140(2B-1)] + 1  / 2
						Done!!
						to get b, we use b(t)  .
	The End		
	
	In the main(), I'm brute forcing to get some initial solutions, and then I'm running my math against these solutions to get new solutions much quicker!!
				
 */

public class Euler100 {
	static MathContext mc = new MathContext(500,RoundingMode.UP);
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		BigDecimal b = new BigDecimal("-1");
		BigInteger t = new BigInteger("2"); //new BigInteger("2");
		while(true){                                                            // start at t=2 and brute-force the way up until a solutions is encountered
			BigDecimal temp = b(t);
		
			if(isInteger(temp)){													// when a solution is encountered, we can use the magic formulas to come up immediately with other solutions
				

				System.out.println("b= "+temp.toBigInteger()+"  t= "+t);
				tryChain(temp.toBigInteger(), t);                                    // we give this solution to tryChain() which will spit out a series of solutions (using the formulas) until it hits a solution greater than 10^2 
				System.out.println();
				sc.nextLine();
			}
			t = t.add(BigInteger.ONE);
		}
		

	}
	
	// given an initial solution, this spits out a series of solutions (using the formulas) until we hit a solution greater than 10^2
	static void tryChain(BigInteger b, BigInteger t){
		if(t.compareTo(new BigInteger("1000000000000")) >= 0)
			return;
		BigInteger[] newSol = newSolution(b, t);	
		System.out.println("b= "+newSol[0]+"  t= "+newSol[1]);
		tryChain(newSol[0], newSol[1]);
	}
	
	static void printNewSol(BigDecimal b, BigInteger t){
		BigInteger[] sol = newSolution(b.toBigInteger(), t);
		System.out.println("b= "+sol[0]+"  t= "+sol[1]);
	}
	
	// given t, return the corresponding b
	static BigDecimal b(BigInteger t){
		BigDecimal sqtdelta;
		// calc sqrt(delta):
		BigDecimal bd1  = new BigDecimal(t);
		bd1 = bd1.multiply(new BigDecimal("2"));
		bd1 = bd1.multiply( new BigDecimal(t.subtract(BigInteger.ONE)));
		sqtdelta = BigDecimal.ONE.add(bd1);
		sqtdelta = sqrt(sqtdelta, mc);	
			
 		BigDecimal b = sqtdelta.add(BigDecimal.ONE);
 		b = b.divide(new BigDecimal(2.0));
 		return b;
	}

	// This function is the math  core of the program. given a solution (b,t) this function will spit another solution
	static BigInteger[] newSolution(BigInteger B, BigInteger T){
		BigInteger newT = BigInteger.ONE;
		
		BigInteger b1 = T.multiply(newT.add(BigInteger.ONE)); 
		b1 =b1.subtract(BigInteger.ONE);
		BigInteger b2 = B.multiply(newT.add(BigInteger.ONE)); 
		b2 =b2.subtract(BigInteger.ONE);
		
		newT = newT.add(b1.multiply(new BigInteger("99")));
		newT = newT.add(b2.multiply(new BigInteger("140")));
		newT = newT.divide(new BigInteger("2"));
		
		
		
		return new BigInteger[]{
				b(newT).toBigInteger(),						 //	b(newT)					
				newT                                         // newT = [99(2T-1) + 140(2B-1)] + 1  / 2
		};
	}

	static boolean isInteger(BigDecimal x) {
		BigDecimal b1 = new BigDecimal(x.toString());
		b1 = b1.setScale(0, RoundingMode.CEILING);
		BigDecimal b2 = b1.subtract(x);
	
		if(   b2.compareTo(new BigDecimal("0.0000000001"))   <= 0 )
			return true;
			
		b1 = new BigDecimal(x.toString());
		b1 = b1.setScale(0, RoundingMode.FLOOR);	
		b2 = x.subtract(b1);

		if(   b2.compareTo(new BigDecimal("0.0000000001"))   <= 0 )
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
