import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Euler21 {
	static List<Integer> amicables = new ArrayList<Integer>();
	
	public static void main(String[] args) {
		
		for (int i = 1; i < 10000; i++) {
			int x = d(i);
			int y = d(x);
			
			if(y==i  && i!=x){
				amicables.add(i);
			}
		}
		
		int sum = 0;
		for (Integer integer : amicables) 
			sum += integer;		
		System.out.println(sum);
		
		
	}

	
	
	// this is the same function I used in problem 13(or 3 maybe), but with some modifications.
	static List<Integer> factors(int x){
		int n = (int) Math.ceil(Math.sqrt(x));
		List<Integer> facts = new ArrayList<Integer>();
		
		for (int i = 1; i < n; i++) {
			if(x%i==0 && i!=x)
				facts.add(i);
		}
		
		int length = facts.size();
		for (int i = 0; i < length; i++) {
			if((x/ facts.get(i)) != x )
				facts.add(x/ facts.get(i));
		}
		
		if(Math.ceil(Math.sqrt(x)) == Math.sqrt(x)  && Math.sqrt(x)!=x)
			facts.add((int)Math.sqrt(x));
		return facts;
	}
	
	static int d(int n){
		Integer[] properDivisors = null;
		properDivisors = factors(n).toArray(new Integer[0]);
		int sum = 0;
		for (int i = 0; i < properDivisors.length; i++) {
			sum+= properDivisors[i];
		}
		return sum;
	}
	
	
	
}
