
public class Euler14 {

	public static void main(String[] args) {
		long greatest=-1;
		int x=-1;
		for (int i = 1; i < 1000000; i++) {
			long t = num(i);
			if(t>greatest){
				greatest =t;
				x=i;
			}
		}
		
		System.out.println("longest chain: "+greatest+"  for n= "+x);
	}
	
	static long num(long n){
		long length=0;
		while(n>1) {
			if(n%2==0)
				n /=2;
			else if(n%2==1)
				n = 3*n+1;
			length++;
		}
		
		return length+1;
	}

}
