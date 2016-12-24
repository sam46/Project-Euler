

import java.util.*;

public class Sudoku {
	private int row=9;
	private int column=9;
	private int block=9;
	private int[] n = {1,2,3,4,5,6,7,8,9};
	private int[][] grid;
	private ArrayList<Point> zeros = new ArrayList<>();
	private int[][] solution;
	private TreeSet<Integer>[] nonZcols = new TreeSet[9];
	private TreeSet<Integer>[] nonZrows = new TreeSet[9];
	private TreeSet<Integer>[] nonZblocks = new TreeSet[9];
	private int[][] nonZ;
	
	public Sudoku(){
		grid= new int[9][9];
	}
	
	public Sudoku(String str){
		grid= new int[9][9];
		fill(grid,str);
		zeros = getZeros(grid);
		detectNonZero();
		fillNonZ();
		solve(grid, zeros);
		
	}
	
	private void fill(int[][] g, String str){
		StringTokenizer st = new StringTokenizer(str,",");
		int i=0;
		while (st.hasMoreElements()) {
			String token = ((String) st.nextElement()).trim();
			
			int x = Integer.parseInt( token);
			g[i/9][i%9] = x;
			i++;
		}
	}
	
	private void fillNonZ(){
		nonZ= new int[zeros.size()][];
		TreeSet<Integer> ts;
		for (int i = 0; i < nonZ.length; i++) {
			ts = new TreeSet<Integer>();
			Point p = zeros.get(i);	
			for (Integer integer : nonZrows[p.i]) 
				ts.add(integer);
			for (Integer integer : nonZcols[p.j]) 
				ts.add(integer);
			for (Integer integer : nonZblocks[blockNumber(p)]) 
				ts.add(integer);
			

			nonZ[i] = new int[ts.size()];
			int j =0;
			for (Integer integer : ts) 
				nonZ[i][j++] = integer;
		}
	}
	
	private int blockNumber(Point p){
		int r,c;
		if(p.i<3) r  = 1;
		else if(p.i<6) r = 2;
		else r = 3;
		if(p.j<3) c  = 1;
		else if(p.j<6) c = 2;
		else c = 3;
		
		return r+c;
	}
	
	private void detectNonZero(){
		for (int i = 0; i < 9; i++) 
			nonZrows[i] = new TreeSet<Integer>();	
		for (int j = 0; j < 9; j++) 
			nonZcols[j] = new TreeSet<Integer>();
		
		
		for (int i = 0; i < 9; i++) {
			nonZrows[i] = new TreeSet<Integer>();
			for (int j = 0; j < 9; j++) {
				if(grid[i][j]!=0){
					nonZrows[i].add(grid[i][j]);
					nonZcols[i].add(grid[i][j]);
				}
			}
		}
		
		for (int i = 0; i < 9; i++) {
			nonZblocks[i] = new TreeSet<Integer>();
			int[][] temp = getBlock(grid, i+1);
			for (int j = 0; j < 3; j++) {
				for (int k = 0; k <3; k++) {
					if(temp[j][k]!=0)
						nonZblocks[i].add(temp[j][k]);
				}
			}
		}

		
	}
	
	public void print(){
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) 
				System.out.print(grid[i][j]+", ");
			System.out.println();
		}
	}
	
	public void printSolution(){
		if(solution != null)
		for (int i = 0; i < solution.length; i++) {
			for (int j = 0; j < solution[0].length; j++) 
				System.out.print(solution[i][j]+", ");
			System.out.println();
		}
		/*
		else{
			for (Point p : zeros) {
				System.out.println(p.i+"  "+p.j);
			}
		}
		*/
	}
	
	private boolean everyReveryN(int[][] g){
	
		for (int i = 0; i < g.length; i++) { // for every row
			for (int n = 1; n <= 9; n++) { // for every number 1-9
				
				// look for n in  the cells of that row, if it's not found return false;
				boolean found = false;
				for (int j=0; j < g.length; j++) {
					if(p(i,j,n,g))	found=true;
				}
				if(!found)	return false;
				
			}
		}
		
		return true;
		
	}
	
	private boolean everyCeveryN(int[][] g){
		
		for (int j = 0; j < g.length; j++) { // for every column
			for (int n = 1; n <= 9; n++) { // for every number 1-9
				
				// look for n in  the cells of that column, if it's not found return false;
				boolean found = false;
				for (int i=0; i < g.length; i++) {
					if(p(i,j,n,g))	found=true;
				}
				if(!found)	return false;
				
			}
		}
		
		return true;
		
	}
	
	private boolean everyBeveryN(int[][] g){
		
		for (int b = 1; b <= 9; b++) { // for every block
			int[][] block =  getBlock(g, b);
			
			for (int n = 1; n <= 9; n++) { // for every number n 1-9, look for n in the block's cells
				boolean found =false;
				for (int i = 0; i < block.length; i++) {
					for (int j = 0; j < block[0].length; j++) {
						if(p(i,j,n,block)) found =true;
					}
				}
				if(!found) return false;
			}
			
		}
		
		return true;
	}
	
	private int[][] getBlock(int[][] g, int x){
		int[][] ret = new int[3][3];
		
		if(x==1){
			for (int i = 0; i < ret.length; i++) {
				for (int j = 0; j < ret.length; j++) {
					ret[i][j] = g[i][j];
				}
			}
		}
		
		else if(x==2){
			for (int i = 0; i < ret.length; i++) {
				for (int j = 0; j < ret.length; j++) {
					ret[i][j] = g[i][j+3];
				}
			}
		}
		
		else if(x==3){
			for (int i = 0; i < ret.length; i++) {
				for (int j = 0; j < ret.length; j++) {
					ret[i][j] = g[i][j+6];
				}
			}
		}
		
		else if(x==4){
			for (int i = 0; i < ret.length; i++) {
				for (int j = 0; j < ret.length; j++) {
					ret[i][j] = g[i+3][j];
				}
			}
		}
		
		else if(x==5){
			for (int i = 0; i < ret.length; i++) {
				for (int j = 0; j < ret.length; j++) {
					ret[i][j] = g[i+3][j+3];
				}
			}
		}
		
		else if(x==6){
			for (int i = 0; i < ret.length; i++) {
				for (int j = 0; j < ret.length; j++) {
					ret[i][j] = g[i+3][j+6];
				}
			}
		}
		
		else if(x==7){
			for (int i = 0; i < ret.length; i++) {
				for (int j = 0; j < ret.length; j++) {
					ret[i][j] = g[i+6][j];
				}
			}
		}
		
		else if(x==8){
			for (int i = 0; i < ret.length; i++) {
				for (int j = 0; j < ret.length; j++) {
					ret[i][j] = g[i+6][j+3];
				}
			}
		}
		
		else if(x==9){
			for (int i = 0; i < ret.length; i++) {
				for (int j = 0; j < ret.length; j++) {
					ret[i][j] = g[i+6][j+6];
				}
			}
		}
		
		return ret;
	}
	
	private boolean p(int i, int j , int n, int[][] g){
		return g[i][j]==n; 
	}
	
	// scans and determines the locations of the zeros
	private ArrayList<Point> getZeros(int[][] g){
		ArrayList<Point> zero = new ArrayList<>();
		for (int i = 0; i < g.length; i++) {
			for (int j = 0; j < g.length; j++) {
				if(g[i][j]==0)
					zero.add(new Point(i,j));
			}
		}
		return zero;
	}
	
	private int[][] copy(int[][] g){
		int[][] ret = new int[g.length][g[0].length];
		for (int i = 0; i < ret.length; i++) 
			for (int j = 0; j < ret.length; j++) 
				ret[i][j]=g[i][j];
		return ret;			
	}
	
	private boolean solve(int[][] g, ArrayList<Point> z){
		if(z.size()==1){
			
			
			for (int n = 0; n < nonZ[0].length; n++) {
				int[][] temp = copy(g);
				temp[z.get(0).i][z.get(0).j] = nonZ[0][n];
				if(acceptable(temp)){
					solution = temp;
					System.out.println("ok");
					return true;
				}
			}
			
			return false;
		}

		//int[] possib = possibilities(z.get(0));
		for(int n=0; n < nonZ[0].length; n++){
			int[][] temp = copy(g);
			temp[z.get(0).i][z.get(0).j] = nonZ[0][n];
			if( solve(temp, getZeros(temp)) ) return true;		
		}
		
		return false;
	}
	
	private boolean acceptable(int[][] g){
		if(everyBeveryN(g) && everyCeveryN(g) && everyReveryN(g))
			return true;
		
		return false;
	}
	

	
	private class Point{
		int i=-1,j=-1;
		public Point(){
		}
		
		public Point(int y, int x){
			i=y;
			j=x;
		}
	}
	
}


