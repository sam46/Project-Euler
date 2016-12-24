
public class Euler6 {

	public static void main(String[] args) {
		int sum=0;
		for (int i = 1; i <=100; i++) 
			sum += i*i;
		
		int x= 50*101;
		
		System.out.println((x*x)-sum);

	}

}
