import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.StringTokenizer;

// CHECK THE ATTACHED WORD DOCUMENT!!
// I WONT FLIP THE TABLE LIKE I DID IN THE WORD DOCUMENT, I'LL KEEP IT AS IS!! 


public class Euler81 {
	static long mat[][];
	/*= {
			{	 131, 				 673, 			 	 234, 				 103, 			     18 , }  , 
			{	 201, 				 96, 				 342, 				 965, 				 150, }		       , 
			{ 	 630, 				 803, 				 746, 				 422, 				 111, }			       , 
			{	 537, 				 699, 				 497, 				 121, 				 956, }			       , 
			{	 805, 				 732, 				 524, 				  37, 				 331,} 
		};
	*/
	
	public static void main(String[] args) {
		try {
			fillMatrix();
		} catch (FileNotFoundException e) {}
		
		sum();
		System.out.println(mat[mat.length-1][mat[0].length-1]);

	}
	
	// the minimum path from the top left to the bottom right is the same minimum path from the bottom left to the bottom right
	// so I wont be starting with last diagonal and move closer to the top right, I'll start from the first diagonal (that contains the top
	// right element) and move closer to the bottom left. This is just as good!! and most importantly more straightforward to code!!
	static void sum(){
		
			/* This will handle the diagonals whose heads lie along the first row of the matrix */
		for (int J = 1; J < mat[0].length; J++) {                                   // J represents the element in the first row, which is at the head of the diagonal we wanna work on	
			for (int i = 0, j=J; i < mat.length && j >=0; i++, j--) {
				
				if (i-1 < 0) {                                          // special case
					mat[i][j] += mat[i][j-1]; 
				}
				else if(j-1 < 0){                                                // special case
					mat[i][j] += mat[i-1][j]; 
				}
				else{                                                     // most of the time
					mat[i][j] += Math.min( mat[i-1][j]  , mat[i][j-1] );                  // min(above, right)
				}
				
				// I'm not deleting anything, but who cares, this wont affect the end result :D
			}		
		}
		
			/* Now for the rest of the diagonals (the diagonal that their heads are along the right vertical side of the matrix) */
		
		for (int I = 1; I < mat.length; I++) {                                   // I represents the head of the diagonal, 
			for (int i = I, j=mat[0].length-1; i < mat.length && j >=0; i++, j--) {
			
				if (i-1 < 0) {                                                   // special case
					mat[i][j] += mat[i][j-1]; 
				}
				else if(j-1 < 0){                                                // special case
					mat[i][j] += mat[i-1][j]; 
				}
				else{                                                            // most of the time
					mat[i][j] += Math.min( mat[i-1][j]  , mat[i][j-1] );                  // min(above, right)
				}
				
				// I'm not deleting anything, but who cares, this wont affect the end result :D
			}
		}
		
		// Now the bottom right element will have the min sum!!
	}

	static void fillMatrix() throws FileNotFoundException{
		Scanner sc = new Scanner(new FileReader("p081_matrix.txt"));
		StringTokenizer st;
		
		mat = new long[80][80];
		int i=0,j=0;
		while(sc.hasNext()){
			String str = sc.nextLine();
			if(!str.isEmpty()){
				st = new StringTokenizer(str, ",");
				j=0;
				while (st.hasMoreElements()) {
					mat[i][j] = Long.parseLong(st.nextToken());			
					j++;	
				}
			}
			i++;
		}
		
	}
	
	
}
