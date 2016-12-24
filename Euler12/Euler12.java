
public class Euler12 {

	public static void main(String[] args) {
		long sum =0l;
		long target=0l;
		long i=1l;
		while(target <=500){
			sum = (i*(i+1))/2l;
			target = num_divisors(sum);
			i++;
		}
		
		System.out.println(sum);
		System.out.println(num_divisors(sum));
		
	}
	
	// the trick is in the algorithms for this function, my first approach was naive took ages to finish for large numbers,
	// but this one is super-fast!!
	// No need to go looking for divisors beyond the square root. Here's why:
	// two scenarios :
	// x divisors, square root, x divisors   (like 16:   1 2 4 6 12)   total divisors: 2x +1
	// x divisors, x divisors   (like 12:  1 2 3 4 6 12)  total divisors: 2x
	// so we just see how many divisors are there before the square root, multiply that by 2, and if the square root is a divisor we add 1  
	static int num_divisors(long x){
		long i =1;
		int num=0;
		double limit = Math.sqrt(x);
		
		while(i<limit){
			if(x%i==0)
				num++;
			i++;
		}
		num *=2;
		if(limit == Math.floor(limit)) // this is to know if the square root is a whole number(in other words: if it exixts!!) 
			num++;
		return num;
	}
}
