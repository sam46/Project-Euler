
public class Euler4 {
//828828
	public static void main(String[] args) {
		int greatest= -1; 
		int i=-1,j=-1;
		for (int x = 999; x>=1; x--) {
			for (int y = 999; y >=1; y--) {
					int n = x*y;
					if (isPalindromic(n)){
						System.out.println(""+x+" X "+y+" = "+n);
						if(n>greatest){
							greatest=n;
							i=x;
							j=y;
						}
					
					}
					
			}
		}
		

		System.out.println(""+i+" X "+j+" = "+greatest);
		
	}
	
	static boolean isPalindromic(int n){
		String str1 = String.valueOf(n);
		String str2 = "";
		for (int i = str1.length()-1; i >=0; i--) 
			str2 += "" + str1.charAt(i);
		
		if(Integer.parseInt(str1) == Integer.parseInt(str2))
			return true;
		
		return false;
	}

}
