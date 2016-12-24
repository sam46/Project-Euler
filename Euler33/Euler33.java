
public class Euler33 {

	public static void main(String[] args) throws InterruptedException {
		int num,denom,a,b,c,d;
		double frac=0,newFrac=0;
		String[] array = new String[4];
		int index=0;
		
		for (num = 10; num <= 99; num++) {
			for (denom = num+1; denom <= 99; denom++) {
			
				frac = (double)num/(double)denom;
				a = (num/10)%10;
				b = num%10;
				c = (denom/10)%10;
				d = denom%10;
				
				if(a==c){
					newFrac = b/(double)d;
					if(newFrac == frac)
						array[index++] = ""+num+"/"+denom +" = "+b+"/"+d; 				
				}
				
				if(a==d){
					newFrac = b/(double)c;
					if(newFrac == frac)
						array[index++] = ""+num+"/"+denom +" = "+b+"/"+c; 	
				}
				
				if(b==c){
					newFrac = a/(double)d;
					if(newFrac == frac)
						array[index++] = ""+num+"/"+denom +" = "+a+"/"+d; 	
				}
				
				if(b==d && b!=0){
					newFrac = a/(double)c;
					if(newFrac == frac)
						array[index++] = ""+num+"/"+denom +" = "+a+"/"+c; 	
				}
			}
		}
		
		for (int i = 0; i < array.length; i++) {
			System.out.println(array[i]);
		}
		
	}

	
	
	
	
}
