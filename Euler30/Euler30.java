import java.util.ArrayList;
import java.util.List;


public class Euler30 {
	static int limit = 999999;
	
	public static void main(String[] args) {
		List<Integer> nums = new ArrayList<Integer>();
		
		for (int i = 10; i < limit; i++) {
			if(digitPowSum(i) == i)
				nums.add(i);
		}
		
		int sum = 0;
		for (Integer integer : nums) {
			sum+=integer;
			System.out.println(integer);
		}
		
		System.out.println("The sum: "+ sum);
	}
	
	static int digitPowSum(int x){
		int sum = 0 ;
		while(x>0){
			int digit = x%10;  
			sum += Math.pow(digit, 5);
			x /= 10;
		}
		return sum;
	}

	
	static void proofThatTheLimitIs6or7Digits(){
		// this function is not part of the solution,
		// it is just a proof of concept 
		// that there is a finite number of numbers
		// that can be written as the sum of fifth powers of their digits.
		// Run the this function and observe and compare the numbers as they grow.
		// we only need to go looking for those number 
		// up to 999999
		
		int x= (int) Math.pow(9, 5);
		
		for (int i = 1; i < 35; i++) {
			for (int j = 0; j <i; j++) {
				System.out.print("9");
			}
			System.out.println("  "+i*x);
		}
	}
}
