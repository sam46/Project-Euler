import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;


public class Euler38 {

	public static void main(String[] args) {
		List<Long> candidates = new ArrayList<Long>();
		for (int i = 1; i < 100000; i++) {
			String temp = generateProducts(i);
			if(temp != null)
				candidates.add(Long.parseLong(temp));
		}
		Collections.sort(candidates);
		
		System.out.println("Largest: "+ candidates.get(candidates.size()-1));

	}
	
	static String generateProducts(int x){
		String str = "";
		int i = 1; 
		while(str.length()<9 ){
			str = str + x*i;
			i++;
		}
		
		if(isPandigital(str)){
			System.out.println(x+" times "+" 1 to "+(i-1)+"    "+str);
			return str;
		}
		return null;
	}
	
	
	static boolean isPandigital(String str){
		if(str.length() != 9)
			return false;
		
		for (int i = 1; i <= 9; i++) {
			if(!str.contains(""+i))
				return false;
		}
		
		return true;
	}

}
