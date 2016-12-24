import java.util.ArrayList;
import java.util.List;


public class Euler91 {
	static public List<Point> nodes;
	
	// Makes a list of all possible nodes on an nxn grid that a point can take. 
	public static void generate(int n){
		nodes = new ArrayList<Point>();
		
		for (int j = 0; j <= n; j++) {
			for (int i = 0; i <= n; i++) {
				if(i==0 && j==0) continue;   // p and q can be at the origin O(0,0) !!
				nodes.add(new Point(i, j));
			}
		}
	}

	public static void print(){
		for (int i = 0; i < nodes.size(); i++) {
			System.out.println((i)+")  "+nodes.get(i).x+",  "+nodes.get(i).y);
		}
	}
	public static void main(String[] args) {
		generate(50);
		
		int count = 0;
		// go through all possible combinations of p and q (however starting j at i+1 gets rid of any repetitions due to symmetry, so at the end of the day the loops go through C(n,2) possibilities!!)
		for (int i = 0; i < nodes.size(); i++) {
			for (int j = i+1; j < nodes.size(); j++) {

				Point p = nodes.get(i);
				Point q = nodes.get(j);
				if(isRightTriangle(p, q))
					count++;
			}
		}
		
		System.out.println(count);
	}
	
	// Tests if the triple OPQ form a right triangle using dot products!!
	static boolean isRightTriangle(Point p, Point q){
		// OPQ is 90 deg
		if(p.x*(p.x-q.x) + p.y*(p.y-q.y) == 0)
			return true;
		
		//POQ  is 90 deg
		if(p.x*q.x + p.y*q.y == 0)
			return true;
		
		//PQO  is 90 deg
		if(q.x*(q.x-p.x) + q.y*(q.y-p.y) == 0)
			return true;
		
		return false;
	}

}


class Point{
	static public Point O = new Point(0,0); 
	int x ,y;
	public Point(int X, int Y){
		x=X; y=Y;
	}
}

