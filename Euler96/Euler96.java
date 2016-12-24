
public class Euler96 {

	
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		String spuzzle =  "8,5,0, 6,0,2, 4,3,7,"
						+ "7,0,3, 8,5,0, 1,0,0,"
						+ "1,6,4, 3,7,9, 5,2,8,"
						
						+ "0,8,6, 1,4,7, 3,5,2,"
						+ "3,7,5, 2,0,8, 9,1,4,"
						+ "2,4,1, 5,9,3, 7,0,6,"
						
						+ "4,3,2, 9,0,1, 6,7,0,"
						+ "6,0,7, 0,2,5, 8,9,3,"
						+ "5,9,8, 7,3,6, 0,4,1";
				
		Sudoku sdk = new Sudoku(spuzzle);
		sdk.printSolution();
		System.out.println();
		System.out.println("time: " + ((System.currentTimeMillis()-start)/1000.0));
	}

}
