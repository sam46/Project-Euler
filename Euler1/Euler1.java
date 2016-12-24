
public class Euler1 {

	public static void main(String[] args) {
		int sum=0;
		int i=3;
		while(i<1000){
			if(isMultiple(i))
				sum += i;
			i++;
		}
		System.out.println(sum);

		
	}
	
	static boolean isMultiple(int x){
		if(x%5 ==0 || x%3 ==0)
			return true;
		return false;
				
	}

}
