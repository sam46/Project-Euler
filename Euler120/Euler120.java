import java.math.BigInteger;


public class Euler120 {
/*
	public static void main(String[] args) {
		BigInteger[] rs = new BigInteger[998];
		
		for (int a = 4; a <= 4; a++) {
			//if(a%100==0)
				//System.out.println("a= "+a);
			BigInteger max = BigInteger.ZERO;
			for (int i = 1; i <= 30; i++) {
				BigInteger r = rem(a,i);
				
				System.out.println("n="+i+" : "+r);
				if(max.compareTo(r) == -1)
					max = new BigInteger(r.toByteArray());
			}
			
			//rs[a-3] = new BigInteger(max.toByteArray());
		}
		
		BigInteger sum = BigInteger.ZERO;
		for (int i = 0; i < rs.length; i++) 
			sum = sum.add(rs[i]);
		
		System.out.println(sum);
		
	}
	
	static BigInteger rem(long a, int n){
		BigInteger b1 = new BigInteger(""+(a+1));
		b1 = b1.pow(n);
		BigInteger b2 = new BigInteger(""+(a-1));
		b2 = b2.pow(n);
		(b1.add(b2)).mod(new BigInteger(""+(a*a)));
		return 	(b1.add(b2)).mod(new BigInteger(""+(a*a)));
	}
*/
	public static void main(String[] args){

		long sum = 0;
		for (int a = 3; a <= 20; a++) {
			//if(a%100==0)
				//System.out.println("a= "+a);
			int max_n = (a-1)/2;//(int)Math.round((1.0*a*a-1)/(2.0*a));
			//sum += 2l*a*max_n;
			//sum += rem(a,max_n).longValue();
			int nr = (int)(max_n+1 - Math.pow(-1,max_n)*max_n + Math.pow(-1,max_n));
			nr*=a;
			System.out.println("a="+a+"   n="+max_n+"  rem="+rem(a,(int)max_n).longValue()+"   2an="+(2l*a*max_n));//+"  new-r="+nr);
		}
	
		System.out.println(sum);
		
		for (int i = 1; i < 30; i++) {
			System.out.println("n="+i+":  rem="+rem(3,i));
		}
		
		
		double x = 2.9999999999999999;
		if(Math.floor(x)!=x)
			System.out.println("float");
		else 
			System.out.println("int");
		if(Math.abs(x-Math.round(x)) <= 0.000000000000000001)
			System.out.println("int");
		else
			System.out.println("float");
		
	}
	
	//static long rem(long a, int n){
	//	return (long) (Math.pow(a+1,n) + Math.pow(a-1,n))%(a*a);
	//}
	
	static BigInteger rem(long a, int n){
		BigInteger b1 = new BigInteger(""+(a+1));
		b1 = b1.pow(n);
		BigInteger b2 = new BigInteger(""+(a-1));
		b2 = b2.pow(n);
		(b1.add(b2)).mod(new BigInteger(""+(a*a)));
		return 	(b1.add(b2)).mod(new BigInteger(""+(a*a)));
	}
	
}
