
public class Euler17 {

	public static void main(String[] args) {
		String str="";
		for (int i = 1; i <= 1000; i++) 
			str += Num.spell(i);
		
		System.out.println(str.length());
	}

}


class Num{
	private static String[] spelling = {
		"","one","two","three","four","five","six","seven","eight","nine","ten",
		"eleven","twelve","thirteen","fourteen","fifteen","sixteen","seventeen","eighteen","nineteen",
		"twenty","thirty","forty","fifty","sixty","seventy","eighty","ninety","hundred"
	};
	
	public static String spell(int x){
		if(x >=0 && x<=20){
			return spelling[x];
		}
		
	    if(x>20 && x <=99) {
			String temp="";
			temp += spelling[ 20-2 +(x/10) ];
			temp += spell(x%10);
			return temp;
		}
	    
	    if(x>=100 && x<1000 && x%100==0 ){
	    	String temp="";
	    	temp += spell(x/100)+"hundred";		
	    	return temp;		
	    }
	    
	    if(x>100 && x<1000){
	    	String temp="";
	    	temp += spell(x/100)+"hundredand";
	    	temp += spell(x%100);
	    	return temp;
	    }
	    
	    if(x==1000)
	    	return "onethousand";
	    
		return null;
	}
	
	
}