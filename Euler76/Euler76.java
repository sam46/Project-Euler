
public class Euler76 {
/*	
  	foo(6)=10 :
 		5 + 1
 		4 + 1 + 1
 		4 + 2
 		3 + 1 + 1 + 1
 		3 + 2 + 1
 		3 + 3
 		2 + 1 + 1 + 1 + 1
 		2 + 2 + 1 + 1
 		2 + 2
 		1 + 1 + 1 + 1 + 1 + 1
 		
 		we notice that, in each sum, the terms never exceed the first term, so this sum isnt possible: 2 + 3 + 1   because 3 exceed the first term 2
 		however 3 + 3 is allowed because no term exceed the first term 3 .
 		so we have a limit as to what values the subsequent (or non-first) terms are allowed to take.
 		----------------------------------------------------------------------------------------------------
 		In general, we are taking all numbers from 1 to n-1 and putting them in an array nums[]
 		if is say 100, then   nums = [1,2,3, ... 99]
 		we start at 99.  we go to nums and pick the first element 1 . count(99+1) =100. the next iterations will yield count(99+2), count (99+3),... count(99+99) but all of those will return 0
 		we start at 98.  we go to nums and pick the first element 1 . 98+1 = 99 . we go to nums and look the first. count(99+1)=100 we return to the previous level. now we are at 98 we pick 2. count(98+2)=100. the next iterations will yield count(98+3), count(98+4),... count(98+98) and all of those will return 0.
 		...
 		...
 		...
 		until: 
 		
 	    for each starting number i we call count giving it a start point at i, and a stop point at i. count starts the for loop at 1 (technically at i=0 but nums[0]=1) , then a bunch of recursive calls happen. the root count() call terminates when it hits the value "nums[limit]" (in other words the index "i=limit") and then the execution goes back to foo().
 		
*/	
	

	public static void main(String[] args) {
		foo(100);
	}
	
	static void foo(int n){
		int[] nums = new int[n-1];
		for (int i = 1; i <n; i++) nums[i-1] = i;
		
		int t = 0;
		for (int i = n-1; i > 0; i--) {        // if n=100, then for i=99 to i=1 do:
			t += count(i, i, nums, n);         // start at i, the subsequent terms shouldn't exceed i.
		}
			
		System.out.println(t);
	}

	
	
	static int count(int start, int limit, int[] nums, int goal){
		if(start==goal) return 1;
		if(goal<0 || start>goal) return 0;
		
		int total = 0;
		for (int i = 0; i < limit ; i++) 		
			total += count(start+nums[i], nums[i] ,nums,goal);	     // the subsequent terms cant exceed nums[i]. notice that the first terms is start, and the first subsequent terms is nums[0]                  		
		
		return total;
	}
	
	
}
