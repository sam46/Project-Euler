
public class Euler9 {

	public static void main(String[] args) {
		int a=998,b=999;
		double c;
		long count = 0;
		for(b=999;b>=2;b--){
			for(a=b-1;a>=1;a--){
				c=C(a,b);
				count++;
				if(c == Math.floor(c)){
					if(a+b+c == 1000)
						System.out.println(""+a+" X "+b+" X "+c+" = "+(a*b*c));
				}
			}
			
		}
		System.out.println(count);
	}
	
	static double C(int a, int b){
		return Math.sqrt(a*a+b*b);
	}
	
	

}
