// I notices a pattern:
// we start at 1 in the inner most level,
// add 2  4 times, each time u get a diagonal number
// now add 4 4 times , each time u get a diagonal number for the next level
// add 8 4 times, each time u get a diagonal number for the next level
// see the pattern? 
// 1 + 2 + 2 + 2 +2 + 4 + 4 + 4 + 4 + 8 + 8 + 8 + 8 + ...... 
public class Euler28 {

	
	public static void main(String[] args) {
		System.out.println(gen(1001));
	}
	
	
	
	// generates the diagonal numbers on an nXn grid, and returns their sum
	static int gen(int n){
		if(n<=0)
			return 0;
		if(n==1)
			return 1;
		if(n%2==0)
			throw new IllegalArgumentException("a grid of "+n+" by "+n+" is not possible");
		
		int i =0;
		int cur = 1;
		int sum = 1;
		
		
		n/=2;
		while(i< n){

			i++;
			for (int j = 0; j < 4; j++) { // add 2*i four times, each time we get a diagonal number on the (i+1)th level 
				cur += 2*i;
				sum += cur;
			}

		}
		
		return sum;
	}

}
