
public class Euler2 {
	static final int MAX_VALUE = 4000000;
	
	public static void main(String[] args) {
		int t1 = 0;
		int t2 = 1;
		int t=-1;
		int sum=0;
		
		while(t<= MAX_VALUE) {
			t= t1+t2;
			if(t>MAX_VALUE)
				break;
			
			if(t%2==0)
				sum +=t;
			t1=t2;
			t2=t;
		}

		System.out.println(sum);	
		
	}
	
	
	

}
