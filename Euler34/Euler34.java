
public class Euler34 {

	public static void main(String[] args) {
		long sum = 0;
		for (int i = 3; i < 90000; i++) {
			if(i == digitFactSum(i)){
				System.out.println(i);
				sum+=i;
			}
		}
		
		System.out.println("sum: "+sum);	
	}
	
	static long fact(int n){
		if(n==0 || n==1)
			return 1;
		long product=1;
		while(n>0)
			product*=n--;
		
		return product;
	}
	
	static long digitFactSum(int n){
		if(n<0)
			return 0;
		
		long sum = 0;
		while(n>0){
			sum += fact(n%10);
			n /= 10;
		}
		
		return sum;		
	}
	
	
	// we wanna find all the numbers i such that i = digitFactSum(i).
	// if we take all natural numbers i= 1,2,3,4,5,6...
	// and then we take digitFactSum(i),
	// we can see how far apart is each i and its digitFactSum(i)
	// by ploting the i's on the x axis
	// and abs( i - digitFactSum(i) ).
	// I did plot that using matlab for large i's
	// found out that after i=90000 (roughly!!) 
	// no digitFactSum(i) get's close(let alone equal!!) to its i.
	// the numbers and their digitFactSum's become far apart steadily.
	// Plot the X's and Y's generated by this function to see for yourself!!
	// Note: print the X's and Y's separately, cuz java's console window 
	// can't display both a the same time :v .
	static void plotThosePoints(){
		
		System.out.println("X:");
		for (int i = 1; i < 100000; i++) {
			System.out.print(i+",");
		}
		
		// uncomment and print those Y's:
		/*
		System.out.println("\n Y:");
		for (int i = 1; i < 100000; i++) {
			System.out.print(Math.abs(i-digitFactSum(i))+",");
		}*/
	}


	
}
