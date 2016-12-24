/*
	I used the recurrence formula at https://en.wikipedia.org/wiki/Partition_%28number_theory%29#Recurrence_formula
	(I tried implementing the generating function formula (see project Euler78Cpp) but it's too slow.)
	
	Brute force:
	keep generating p(n)  until we hit one divisible by 1 million.
	
	p(n) = sum p(n - generalised pentagonal number)
	
	see https://en.wikipedia.org/wiki/Pentagonal_number
	
	Hard to code, but mathematically easy.

 */

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Euler78 {
	static List<BigInteger> pents, 	 	// pents for generalised pentagons, k for
							p, 		    // p for p(n)'s
							k;          // k is 0, 1, -1, 2, -2, 3, -3, .....
	
	static int n = 0;  // n is the size of the lists

	
	static void addK(){ 
		BigInteger b;
		if((n+1) % 2 == 1)
			b = new BigInteger(""+((n+1)/-2));
		else
			b = new BigInteger(""+((n+1)/2));
		
		k.add(b);
		n++;
	}
	
	static void addPent(){
		addK(); 
		BigInteger b,t;
		t = k.get(n-1);
		t = t.multiply(t);
		b = (new BigInteger("3")).multiply(t);
		b = b.subtract(k.get(n-1));
		b = b.divide(new BigInteger("2"));
		pents.add(b);
	}
	
	static void addP(){
		addPent();
		BigInteger b = BigInteger.ZERO;
		BigInteger N = new BigInteger(""+(n-1));
		//System.out.println("n= "+N);

		for (int i = 1; i<n; i++) {
			int sign = (int)Math.pow(-1, k.get(i).intValue()-1);
			BigInteger t = N.subtract(pents.get(i));				
			if(t.compareTo(BigInteger.ZERO) == -1)
				break;
			BigInteger t2 = p.get(t.intValue());
			t2 = t2.multiply(new BigInteger(""+sign));
			b = b.add(t2);
		}

		
		p.add(b);
		//System.out.println(b);
		//System.out.println("---------------\n");
	}
	
	
	public static void main(String[] args) {
		p = new ArrayList<>();
		pents = new ArrayList<>();
		k = new ArrayList<>();
		p.add(BigInteger.ONE);
		addPent();
		
		for (int n = 1; ; n++) {
			addP();
			
			
			if(p.get(n).mod(new BigInteger(""+1000000)).compareTo(BigInteger.ZERO)==0){  // break when p(n) % 1000000 == 0
				System.out.println("p("+n+") = "+p.get(n));
				break;
			}
		}
	
	}

}
