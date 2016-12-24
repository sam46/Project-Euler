import java.util.ArrayList;
import java.util.List;

public class Euler68 {
    // for convenience, throughout the program i'll be using t instead of 10!!
	// Note: check the attached picture(pic.tif) in the project folder to better understand the theory behind the program!!
   
    public static void main(String[] args) {
        //System.out.println(remove("abcdefg",0));
       
        String[] p = Permute("t987654321");
        p = genStrings(p);
        
        for (int i = 0; i < p.length; i++) {
            for (int j = 0; j < p[i].length(); j++) {
            	if(p[i].charAt(j)=='t')
            		System.out.print("10");
            	else System.out.print(p[i].charAt(j));
            	
			}
            System.out.println();
        }

       
   
    }
   
   
    // Returns Strings that are 16-digits sych that the first 3 characters (characters not digits, since one of the characters could be t) and
    // the 2nd 3 characters and the ..etc  sum up to the same number sum. and   13<=sum<=27  
    static String[] genStrings(String[] arr){ // arr is expected to contain the permutations of "t987654321" in this order specifically!!
        List<String> list = new ArrayList<String>();
   
        for (int i = 4*arr.length/10; i < arr.length; i++) {    // to skip the permutations that start with t,9,8 or 7 we're starting at i=4*Length/10 , cuz we dont want x0 to be 10. 
            String str = arr[i];                               // str characters will represent:  x0 a b c d e x1 x2 x3 x4   in this order!!
            if(!(str.substring(6)).contains("t")) continue;     // t has to be x1 or x2 or x3 or x4     this will guarantee 10 to be an external node (it wont be x0 anyway since we skipped those with the for-loop initialization trick!!)
            
            // here we construct our string from str:
            StringBuilder temp= new StringBuilder(str.substring(0, 3));                         // x0 a b       R1
          
            int sum = sumdigits(temp.toString());
            if(sum>27 || sum<13) continue;
           
            temp.append(str.charAt(6) + str.substring(2,4));                                  // x1 b c        R2
            if(sumdigits(temp.substring(temp.length()-3)) != sum) continue;
           
            temp.append(str.charAt(7) + str.substring(3,5));                                    // x2 c d     R3
            if(sumdigits(temp.substring(temp.length()-3)) != sum) continue;
           
            temp.append(str.charAt(8) + str.substring(4,6));                                 // x3 d e        R4
            if(sumdigits(temp.substring(temp.length()-3)) != sum) continue;
           
            temp.append(str.charAt(9) +""+ str.charAt(5) +""+ str.charAt(1));                // x4 e a        R5
            if(sumdigits(temp.substring(temp.length()-3)) != sum) continue;
           
            if( min(new char[]{str.charAt(0), str.charAt(6), str.charAt(7), str.charAt(8), str.charAt(9)}) // check that x0 is the lowest external
               == Integer.parseInt(""+str.charAt(0))  ) 
            		list.add(temp.toString());                                                             // temp is the concatenation of  R1R2R3R4R5
        }
       
        String[] ret = new String[0];
        return list.toArray(ret);
    }
   
    static int sumdigits(String str){
        int s = 0;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if(ch != 't')
                s += Integer.parseInt(""+ch);
            else
                s += 10;
        }
        return s;
    }
    
    
    static int min(char[] chs){     
    	int smallest = 1000;
    	for (int i = 0; i < chs.length; i++) {
    		int x;
    		if(chs[i] != 't')
    			 x = Integer.parseInt(""+chs[i]);
    		else 
    			x = 10;
			if(x < smallest)
				smallest = x;
		}
    	return smallest;
    }
   
    static String remove(String str,int ind){
        String s="";
        for (int i = 0; i < str.length(); i++)
            if(i!=ind)s += str.charAt(i);
        return s;       
    }
   
    static int fact(int n){
        int r=1;
        for (int i = 1; i <=n; i++) {
            r= r*i;
        }
        return r;
    }
   
    static String[] Permute(String nums){
        if(nums.length() ==1){
            String[] ret = {nums};
            return ret;
        }
       
        String[] ret = new String[fact(nums.length())];
        for (int i = 0; i < nums.length(); i++) {
            String[] str = Permute(remove(nums, i));
            for (int j = 0; j < str.length; j++){
                str[j] = nums.charAt(i) + str[j];
                ret[(i* str.length)+ j]= str[j];
            }
        }
        return ret;
    }

}