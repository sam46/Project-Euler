import java.util.*;

public class Euler74 {

	public static void main(String[] args) {
		int total = 0;
		List<Long> terms;
		for (long i = 2; i < 1000000; i++) {
			terms = new ArrayList<Long>();
			
			terms.add(i);
			long cur = factSum(i);
			while(!terms.contains(cur)){
				terms.add(cur);
				cur = factSum(cur);
			}
			
			if(terms.size()==60)
				total++;
		}
		
		System.out.println("total: "+total);
		
	}
	
	static long factSum(long x){
		long sum = 0 ;
		while(x>0){
			sum += fact(x%10);
			x /= 10;
		}
		return sum;
	}
	
	
	static long fact(long n){
		if(n<2)
			return 1;
		long r=1;
		for (long i = 1; i <=n; i++) {
			r= r*i;
		}
		return r;
	}
	

}
