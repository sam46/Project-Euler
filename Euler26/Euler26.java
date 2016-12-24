import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;


public class Euler26 {

	public static void main(String[] args) {
		int answer=-1;
		int greatest = -1;
		for (int i = 2; i < 1000; i++) {
			int l = cycleLength(i);

			if(l>greatest){
				greatest=l;
				answer = i;
			}
		}

		BigDecimal x = new BigDecimal("1.0");
		x = x.divide( new BigDecimal(String.valueOf((double)answer) ), 2000,RoundingMode.UP );
		System.out.println("1/"+answer+" = "+x);
		System.out.println("cycle length: "+greatest+"\n");
		
	}
	
	
	// Here's the meat and potato of the program, it's in this function's while loop.
	// start at first decimal place, try a substring of maxLen,maxLen-1,maxLen-2,......down to 2. If it is the repeating cycle cool, we're done.
	// start at the (2nd,3rd....) and do as in step 1, until we either have found the cycle or we have reached the end of the string. we're done.
	// at the end we'll have the cycle stored in cycle.
	// side note: the for loop inside the while loop finds the shortest cycle because we're taking a long substring and then a shorter and then shorter and so on:
	// example: in case of 0.009009009009.....  it considers 0.009009 before 0.009 . so the last(or the shortest) cycle is copied into the variable cycle.
	static int cycleLength(int d){
		BigDecimal x = new BigDecimal("1.0");
		x = x.divide(new BigDecimal( String.valueOf((double)d) ),  2000,RoundingMode.UP);
		String str = (x.toString()).substring(2); 
		
		int start = 0, maxLen = 1000;
		boolean found= false;
		String temp= "";
		String cycle = "";
		
		while(!found && (start<str.length())){
			
			for (int len = maxLen; len > 2; len--) {
				 try{
					 temp = str.substring(start, start+ len);
				 	if(  temp.equals( str.substring(start+len, start+2*len) ) ){
				 		found = true;
				 		cycle = temp;
				 	}
				 	
				 } catch(Exception e){
					 continue;
				 }
			}
			start++;
			temp="";
		}
		
		if(found==true)  // there is a cycle, however if there isn't, found will be false. temp wont be empty in any case!!
			return cycle.length();
			
		
		return 0; // no cycle --> return 0
	}
	
	

}
