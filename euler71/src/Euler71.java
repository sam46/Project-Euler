package euler71;

import java.util.TreeSet;

public class Euler71 {
	static Fraction[] fracs = {
								new Fraction(1,8),
								new Fraction(3,7)
							  };
	
	/* Time : 1811
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		for (int d = 1000000; d >=  2; d--) {
			for (int n = 1; n < d; n++) {
				double temp = (n*1.0)/d;
				if( temp > fracs[0].value && temp < fracs[1].value )
					fracs[0] = new Fraction(n, d);
			}
		}
		
		System.out.println(fracs[0].num+"/"+fracs[0].den);
		System.out.println("time: "+((System.currentTimeMillis()-start)/1000));
	}
	*/
	
	
	
	// 428570/999997
	// time: 2644
	public static void main(String[] args) {
		int maxD = 1000000, maxN=999999;
		long start = System.currentTimeMillis();
		
		for (int n = 1; n <= 200000; n++) {
			for (int d=maxD; d >  n; d--) {
				double temp = (n*1.0)/d;
				if( temp > fracs[0].value && temp < fracs[1].value )
					fracs[0] = new Fraction(n, d);
			}
		}
		
		for (int n = 300000; n <= maxN; n++) {
			for (int d=maxD; d >  700000; d--) {
				double temp = (n*1.0)/d;
				if( temp > fracs[0].value && temp < fracs[1].value )
					fracs[0] = new Fraction(n, d);
			}
		}
		
		System.out.println(fracs[0].num+"/"+fracs[0].den);
		System.out.println("time: "+((System.currentTimeMillis()-start)/1000));
	}
	

}


class Fraction{
	public int num,den;
	public double value;
	
	public Fraction(int n, int d) {
		if(n>=d)
			throw new IllegalArgumentException(n+"/"+d);
		num = n;
		den = d;
		value = (n*1.0)/d;
	}
}