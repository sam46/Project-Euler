import java.util.ArrayList;
import java.util.List;


public class Euler50 {
	// I'm trying to brute force the thing based on the number of the consecutive primes(or the length of the chain)
	// I have an array pr that contains all the primes under 1 million. let's suppose it has n elements
	// i'll try to take chunks out of pr of lengths  n-1, n-2, .... 3, 2   .
	// example: if pr is {1,2,3,4,5,6,7,8,9}
	//			 for a length of 8 i'll have one chunks: {1,2,3,4,5,6,7,8}  
	//           then i'll take the length to be 7, this means i should have 2 chunks: {1,2,3,4,5,6,7} and {2,3,4,5,6,7,8}
	// so for a given length "len" , I start at pr[0] and take a chunk, then i start at pr[1] and take a chunk , keep doing that as long as the chunk doesn't go out the bounds of pr.
	// for each chunk i take i calculate the sum of that chunk using sum().
	// if the sum happens to be 
	
	static Integer[] pr = new Integer[0];
	static int limit = 1000000;
	
	public static void main(String[] args) throws IllegalAccessException {
		primes.add(2);		
		for (int i = 3; i < limit; i+=2) isPrime(i); // isPrime automatically fill in the list primes<> if i is prime. it'll take some time for large numbers!!.
		
		pr = primes.toArray(pr); 
		
		for (int len = pr.length-1; len > 1; len--) {   	 					 // start with maximum possible length for a chunk, and decrease the length in the next loop.
			int x = -1;
			for (int i = 0; i+len < pr.length-1; i++) {  	 					     // "i" will be the first element of the chunk, "i" will increase, the loop terminates when we've hit the last chunk 
				if(i==0) x = sum(i, i+len);   			       						 // sum(start position inclusive,  end position exclusive);
				else{
					x = x - pr[i-1] + pr[i+len-1];                                   // for a given length, we only need to sum the chunk one time , but after that, for the other chunks, no need to sum the whole chunk, we can just take the sum of the previous chunk, subtract the first element(in the preivous chunk) and add the last term (in the current chunk)
				}
				
				if(x==-1 || x > limit)   // this if statement is a life saver, without it the program took forever, if sum() return -1, x will be -1 and the current chunk's sum is greater than limit, and there's no need to try the rest of the chunks for this length, they'll all be even greater. but sum() is only called when i==0, what if the chunk's sum became > limit later? x wont be -1, and that why we need to add the condition that x>limit.
					break;
				
				if(x < limit)  
					if(isPrime(x)){
						System.out.println(x);
						System.out.println("from "+pr[i]+" to "+pr[i+len-1]);
						System.out.println("Num of consecutive primes: " + len);
						return;															  // the first hit will be the answer!!
					}		
			}
			
		}
		
	}
	
	
	
	static int sum(int start, int end) throws IllegalAccessException{
		if(end >= pr.length)
			throw new IllegalAccessException("end cant be >= pr.length");		
		if(start < 0)
				throw new IllegalAccessException("start cant be negative");
		int sum = 0;
		for (int i = end-1; i >= 0; i--) {
			sum+=pr[i];		
			if(sum > pr[pr.length-1])  // if the sum so far is bigger than the largest prime under the limit, don't bother calculating any further.
				return -1;
		}
		return sum;
	}


	static List<Integer> primes = new ArrayList<Integer>();
	static boolean isPrime(int x){
		if(primes.contains(x))
			return true;
		if(x==2 || x==3){
			primes.add(x);
			return true;
		}
		if(x<=1)
			return false;
		
//-----------------------------------------\\
		long n = (long) Math.ceil(Math.sqrt(x));
		long i =2;
		while( i<= n){
			if(x%i == 0)
				return false;
			i++;
		}
		
		primes.add(x);
		return true;
	}

}
