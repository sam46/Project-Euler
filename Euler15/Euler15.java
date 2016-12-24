public class Euler15 {
	// the number of routes to any node on the grid is actually a number in the pascal triangle
	// so 99% of the solution is to generate a Pascal triangle (of level n)
	// p.s i guess this can be solved more easily with combinatorics and nCr .
	
	static int n = 41; // we need 41 levels of pascal triangle to know the number of routes for a 20*20 grid,
					   // more specifically the exact answer is the middle number in the last level
	
	static int num_elem =  (n * 2) -1;
	static long[][] mat = new long[n][num_elem];

	public static void main(String[] args) {
		mat[0][num_elem / 2 ] = 1;
		fill();
	
		System.out.println(mat[n-1][num_elem / 2 ]);  // like i said, the answer is: the middle number in the last level
	}

	static void fill() {
		for (int i = 1; i < n; i++) {
			for (int j = 0; j < num_elem; j++) {
				
				if (j + 1 >= num_elem)
					mat[i][j] = mat[i - 1][j - 1];
				else if (j - 1 <= 0)
					mat[i][j] = mat[i - 1][j + 1];
				else
					mat[i][j] = mat[i - 1][j - 1] + mat[i - 1][j + 1];
			}
		}
		
		
	}
	
}
