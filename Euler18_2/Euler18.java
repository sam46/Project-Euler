
public class Euler18 {
	
	static int[][] data= {
		{75},
		{95, 64},
		{17, 47, 82},
		{18, 35, 87, 10},
		{20, 04, 82, 47, 65},
		{19, 01, 23, 75, 03, 34},
		{88, 02, 77, 73, 07, 63, 67},
		{99, 65, 04, 28, 06, 16, 70, 92},
		{41, 41, 26, 56, 83, 40, 80, 70, 33},
		{41, 48, 72, 33, 47, 32, 37, 16, 94, 29},
		{53, 71, 44, 65, 25, 43, 91, 52, 97, 51, 14},
		{70, 11, 33, 28, 77, 73, 17, 78, 39, 68, 17, 57},
		{91, 71, 52, 38, 17, 14, 91, 43, 58, 50, 27, 29, 48},
		{63, 66, 04, 68, 89, 53, 67, 30, 73, 16, 69, 87, 40, 31},
		{04, 62, 98, 27, 23,  9, 70, 98, 73, 93, 38, 53, 60, 04, 23}
	};

	public static void main(String[] args) {
		foo();
		print();
	}
	

	 
	 static void foo(){
		 for (int i = data.length-2; i >= 0; i--) {
			for (int j = 0; j < data[i].length; j++) {
				data[i][j] =  data[i][j] + Math.max(data[i+1][j], data[i+1][j+1]) ;
				
			
			}
		}
	 }
	 


		static void print(){
			int sp = 40;
		
			for (int i = 0; i < data.length; i++) {
				for (int j = 0; j < sp; j++) {
					System.out.print(" ");
				}
				for (int j = 0; j < data[i].length; j++) {
					if(data[i][j] < 10)
						System.out.print(data[i][j]+"     ");
					else if(data[i][j]>= 10 && data[i][j]< 100)
						System.out.print(data[i][j]+"    ");
					else if(data[i][j]>=100)
						System.out.print(data[i][j]+"   ");
				}
				System.out.println();
				sp-=3;
			}
		}

}
