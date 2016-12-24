import java.util.ArrayList;
import java.util.List;


public class Euler51 {
	// took about 10 minutes to finish!!
	// this is the output: 929393 ace
	// the number 929393 is the greatest in the family
	// so to get the smallest
	// try replacing the digits a,c,e with 0..9  
	// and see which is the smallest prime.
	// if u do this u will get the answer: 121313  (so a,c,e were replaced with 1)
	
	public static void main(String[] args) {
		int i = 10;
		while(foo(i) == -1) {
			i++;
		}

		
	}
	
	static int foo(long x){
		int alpha = String.valueOf(x).length();
		String mask = "";
		for (int i = 0; i < alpha; i++) {
			mask += (char)('a'+i) ;
		}
		
		for (int numOfDigits = 1; numOfDigits < alpha-1; numOfDigits++) {
			String[] combs = combinations(mask, numOfDigits);
			String temp = "";
			for (int i = 0; i < combs.length; i++) {
				int y = -1;
				temp = String.valueOf(x);
				int score = 0;
				for (int number = 0; number < 10; number++) {

					temp = String.valueOf(x);
					for (int j = 0; j < combs[i].length(); j++) {
						int target = -97+(int)combs[i].charAt(j);
						temp = temp.substring(0,target) + (""+number) + temp.substring(target+1);
					}
					long z = Long.parseLong(temp);
					if(String.valueOf(z).length() != alpha)
						continue;
					if(isPrime(z)) 	score++;
				}
				if(score == 8){
					System.out.println(temp+" "+combs[i]);
					return 0;
				}
			}
			
		}
		
		return -1;
	}

	

	static String[] combinations(String n, int r){
		List<String> results = new ArrayList<String>();
		int alpha = n.length();
		
		if(r == alpha) return new String[]{n};
		else if(r > alpha) return new String[0];
		else if(alpha>1 && r==1){
			for (int i = 0; i < alpha; i++) 
				results.add(""+n.charAt(i));
		}
		
		
		else{			
			String temp = "";
			for (int i = 0; i < alpha; i++) {
				temp =   n.substring(i+1) ;
				
				String[] comb = combinations(temp, r-1);
				for (int j = 0; j < comb.length; j++) 
					results.add(n.charAt(i)+comb[j]);				
			}	
		}

		return results.toArray(new String[0]);
	}
	


	


	static List<Long> primes = new ArrayList<Long>();
	static boolean isPrime(long x){
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
