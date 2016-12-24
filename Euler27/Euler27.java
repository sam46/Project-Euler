import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;


public class Euler27 {
	// a=-61 b= 971
	
	static Timer timer;
	static int p,q;
	public static void main(String[] args) {
		
		timer = new Timer(30000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("currentlt testing: "+p+"   "+q);
				
			}
		});
		timer.start();
		
		int greatestStreak= 0, a=-1000, b=1000;
		for (p = -999; p < 1000; p++) {
			for ( q = -999; q < 1000; q++) {
				int s = tryFormula(p, q);
				if(s > greatestStreak){
					greatestStreak = s;
					a = p;
					b = q;
				}
						
			}
		}
		

		System.out.println("Answer: "+a+"  "+b);
		System.out.println("a X b = "+(a*b));
		timer.stop();

	}

	static boolean isPrime(long x){
		if(x<=0)
			return false;
		if(x==2 || x==3)
			return true;
		if(x==1)
			return false;
		
//-----------------------------------------\\
		long n = (long) Math.ceil(Math.sqrt(x));
		int i =2;
		while( i<= n){
			if(x%i == 0)
				return false;
			i++;
		}
		return true;
	}

	static long quad(int a, int b, int n){
		return n*n + a*n +b;
	}

	static int tryFormula(int a, int b){
		int consec_primes = 0;
		int greatest_streak=0;
		
		for (int n = 0; n < b; n++) {
			
				if ( isPrime(quad(a,b,n)) )
					consec_primes++;
				else{
					greatest_streak = Math.max(greatest_streak, consec_primes);
					consec_primes=0;
				}
			
			
		}
		return greatest_streak;
	}
}
