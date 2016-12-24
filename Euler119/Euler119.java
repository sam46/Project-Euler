
public class Euler119 {

	public static void main(String[] args) {
		System.out.println(Math.log(4913)/Math.log(17));
		int n = 0;
		for (long i = 10; ; i++) {
			long s = dSum(i);
			if(s==1)
				continue;
			double exp =  Math.log(i)/Math.log(s);
			if(isInt(exp))
				System.out.println(++n+":  "+i+"  =  "+s+" ^ "+exp);
			/*
			long pow =0;
			
			 while(true){
				pow = (long) Math.pow(s,exp);
				//System.out.println("i="+i+":   "+s+"^"+exp+"="+pow);
				if(pow > i)
					break;
				else if(pow == i){
					System.out.println(++n+":  "+i+"  =  "+s+" ^ "+exp);
					break;
				}
				else
					exp++;
			}
			*/
			
			
		}
	}
	
	static long dSum(long x){
		long n = (long) Math.log10(x) + 1;
		long div = 1;
		long sum = 0;
		for (long i = 0; i < n; i++){
			sum += (x/div)%10;
			div *= 10;
		}
		return sum;	
	}
	
	static boolean isInt(double x){
		if(Math.abs(x- Math.round(x)) <= 0.00000000001)
			return true;
		return false;
	}

}
