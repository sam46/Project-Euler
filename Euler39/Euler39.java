import java.util.List;


public class Euler39 {
	// Throughout the program, 
	// I'm assuming c to be the hypotenuse. a, b are the sides
	// Note: I proved that there doesn't exist an isosceles right triangle with integral length sides. 
	// because the smallest isosceles right triangle is (a=1 b=1 c=sqrt(2)). Any other triangle u can come up with
	// has to be a scaled version of this smallest triangle. so it will be (A=x.1  b =x.1  c= x.sqrt(2)).  QED!!
	// Another proof: in an isosceles right triangle with sides of length x and a hypotenuse of length y:
	// x^2 + x^2 = y^2  -->   x^2 = y^2 / 2  -->   x^2 =  (y/sqrt(2))^2   --> x = sqrt( y^2 / 2) 
	// -->   x = y/sqrt(2)   --> y = x.sqrt(2) 
	// this means: if x is an integer then y has to be a multiple of sqrt(2)
	// or if y is the integer then x has to be a multiple of sqrt(2)
	// x and y cannot be both whole integers at the same time.
	// QED !!
	
	public static void main(String[] args) {
		int greatest = 0;
		int P=0;
		 
		for (int i = 4; i <= 1000; i++) {
			int t = solutions(i);
			if(t > greatest){
				greatest = t;
				P=i;
			}
		}
		
		System.out.println(P);
		

	}
	

	// return number of solutions for given p
	static int solutions(int p) {
		// if a+b+c = p
		// triangle inequality: c < a+b
		// c < p-c -->  c < p/2
		int c = p/2;  // so this is the maximum values for c
		
		int total = 0;
		
		// I've setup the loop counters a,b in a ay that guarantees a to be greater than b 
		// example: suppose c = 25 :   a will range from 24....13 and b will range from 1...12   
		// this way I avoid multiple solutions like (a=4, b=3) (a=3, b=4)
		// and reduce the amount of calculations.
		for (int a = c-1; a >= c/2 +1; a--) {
			for (int b = 1; b <= c/2 ; b++) {

				if(isPerfectSquare(a*a + b*b))
					if(a+b + Math.sqrt(a*a + b*b) == p)
						total++;
					
			}
		}
		
		return total;
	}
	

	static boolean isPerfectSquare(int x){
		if(x==0 || x==1)
			return true;
		if( Math.floor(Math.sqrt(x)) == Math.sqrt(x))
			return true;
		return false;
	}
	
}

