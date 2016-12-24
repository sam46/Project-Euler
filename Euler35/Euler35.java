import java.util.ArrayList;
import java.util.List;


public class Euler35 {

	public static void main(String[] args) {
		int total = 0 ;
		for (int i = 2; i < 1000000; i++) {
			if(isCircular(i)) {
				total++;
				System.out.println(i);
			}
		}
		
		System.out.println("total: "+total);
		
		
	}
	
	static boolean isCircular(int n){
		String str = ""+n;
		if(n==2 || n==5)
			return true;
		if(str.contains("0") || str.contains("2") || str.contains("4") || str.contains("6") || str.contains("8") || str.contains("5"))
			return false;
		
		String[] perms = rotations(str);
		for (int i = 0; i < perms.length; i++) {
			Long temp = Long.parseLong(perms[i]);
			if(  !isPrime(temp) )
				return false;
		}
		
		return true;
	}
	
	static boolean isPrime(long x){		
		if(x==2 || x==3)
			return true;
		if(x==1)
			return false;
		
//-----------------------------------------\\
		long n = (long) Math.ceil(Math.sqrt(x));
		int i =2;
		while( i<= n){
			if(x%i == 0)
				return false;
			i++;
		}
		
		return true;
	}
	
	
	
	static String[] rotations(String str){
		String temp = str;
		String[] rot = new String[str.length()];
		for (int i = 0; i < str.length(); i++) {
				temp = temp.charAt(str.length()-1) + temp.substring(0, str.length()-1);
				rot[i] = temp;
		}
		return rot;
	}
}
