import java.math.BigInteger;


public class Euler53 {

	public static void main(String[] args) {
		fillWithZeroes();
		mat[0][num_elem / 2 ] = BigInteger.ONE;
		fill();
		BigInteger bi = new BigInteger("1000000");
		int total = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < num_elem; j++) {
				if(mat[i][j].compareTo(bi) == 1)
					total++;
			}
		}
		System.out.println("answer: "+total);
	}
	
	
	
	// those are from problem 15, since n goes as large as 100 mat[][] elements will be very large, so I'm using bigintegers.

	static int n = 101;
	static int num_elem =  (n * 2) -1;
	static BigInteger[][] mat = new BigInteger[n][num_elem];
	static void fill() {
		for (int i = 1; i < n; i++) {
			for (int j = 0; j < num_elem; j++) {
				
				if (j + 1 >= num_elem)
					mat[i][j] = new BigInteger(mat[i - 1][j - 1].toString());
				
				else if (j - 1 <= 0)
					mat[i][j] = new BigInteger(mat[i - 1][j + 1].toString());
				
				else{
					BigInteger temp = mat[i - 1][j - 1].add(mat[i - 1][j + 1]);
					mat[i][j] = new BigInteger(temp.toString()) ;
				}
			}
		}
	}
	static void fillWithZeroes() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < num_elem; j++) {
				mat[i][j] = new BigInteger("0");
			}
		}
	}
	

}
