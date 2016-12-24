import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.StringTokenizer;

/*
 * 	sum of angles = 2pi 
 * 
 */
public class Euler102 {
	static int total = 0;
	
	public static void main(String[] args) throws FileNotFoundException {
		FileReader fw = new FileReader("d:\\e102.txt");
		Scanner sc = new Scanner(fw);
		StringTokenizer st;
		while (sc.hasNext()) {
			String temp = sc.nextLine();
			st = new StringTokenizer(temp, ",");
			Triplet trip = new Triplet(Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()),
					Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()),
					Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()));
			
			if (trip.check())
				total++;
		}
		
		System.out.println(total);
		
		
	}
	
	
	
}


class Triplet{
	Point p1, p2, p3;
	//int num_inter = 0;
	
	public Triplet(double p1x, double p1y, double p2x, double p2y, double p3x, double p3y) {
		p1 = new Point(p1x,p1y);
		p2 = new Point(p2x,p2y);
		p3 = new Point(p3x,p3y);
		
	}
	
	boolean check(){
		/*
		Point i12, i13, i23;
		Line l12 = new Line(p1, p2);
		Line l13 = new Line(p1, p3);
		Line l23 = new Line(p2, p3);
		
		Line ray = new Line(Point.O,
				new Point( (p2.x + p1.x)/2, (p2.y+p1.y)/2) );

		i12 = Line.Intersect(l12, ray);
		i13 = Line.Intersect(l13, ray);
		i23 = Line.Intersect(l23, ray);
		
		
		if(i12 != null){
			double d  = Math.sqrt(Math.pow(p2.x-p1.x, 2) + Math.pow( p2.y-p1.y, 2));
			double d1  = Math.sqrt(Math.pow(p2.x-i12.x, 2) + Math.pow( p2.y-i12.y, 2));
			double d2  = Math.sqrt( Math.pow(i12.x-p1.x, 2) + Math.pow( i12.y-p1.y, 2));
			System.out.println(d+" == "+(d1+d2));
			
			if(Math.abs(d-d1-d2) <= 0.1){
				num_inter++;
			}
		}
		
		
		if(i13 != null){
			double d  = Math.sqrt(Math.pow(p3.x-p1.x, 2) + Math.pow( p3.y-p1.y, 2));
			double d1  = Math.sqrt(Math.pow(p3.x-i13.x, 2) + Math.pow( p3.y-i13.y, 2));
			double d2  = Math.sqrt( Math.pow(i13.x-p1.x, 2) + Math.pow( i13.y-p1.y, 2));

			System.out.println(d+" == "+(d1+d2));
			
			if(Math.abs(d-d1-d2) <= 0.1){
				num_inter++;
			}
		}
		
		
		if(i23 != null){
			double d  = Math.sqrt(Math.pow(p3.x-p2.x, 2) + Math.pow( p3.y-p2.y, 2));
			double d1  = Math.sqrt(Math.pow(p3.x-i23.x, 2) + Math.pow( p3.y-i23.y, 2));
			double d2  = Math.sqrt( Math.pow(i23.x-p2.x, 2) + Math.pow( i23.y-p2.y, 2));
			System.out.println(d+" == "+(d1+d2));
			
			if(Math.abs(d-d1-d2) <= 0.1){
				num_inter++;
			}
		}
		System.out.println();
		
		if (num_inter == 1 || num_inter == 3)
			return true;
		return false;
		*/
		
		
		double sum = 0;
		double d1 = Math.sqrt(p1.x*p1.x + p1.y*p1.y);
		double d2 = Math.sqrt(p2.x*p2.x + p2.y*p2.y);
		double d3 = Math.sqrt(p3.x*p3.x + p3.y*p3.y);

		double a12 = (p1.x*p2.x + p1.y*p2.y)/(d1*d2);
		double a23 = (p3.x*p2.x + p3.y*p2.y)/(d3*d2);
		double a31 = (p1.x*p3.x + p1.y*p3.y)/(d1*d3);
		
		sum = Math.acos(a12) + Math.acos(a23) + Math.acos(a31);
		
		if(Math.abs(sum - 2*Math.PI) <= 0.00001)            // this precision is important!!
			return true;
		return false;
		
	}
}


class Point{
	public double x, y;
	static Point O = new Point(0,0);
	public Point(double X, double Y){
		x= X; y= Y;
	}
}
/*

class Line{	
	double m, b;
	boolean vertical = false;
	double vertX;
	
	
	public Line(Point p1, Point p2){
		if(p1.x == p2.x)
			vertical = true;
		else{
			m = (p2.y-p1.y)/(p2.x-p1.x);
			b = p2.y - m*p2.x;
		}
	}
	
	static Point Intersect(Line l1, Line l2){
		if(l1.vertical && l2.vertical)
			return null;
		
		if(l1.vertical){
			return new Point(l1.vertX, l2.m*l1.vertX + l2.b);
		}
		
		if(l2.vertical){
			return new Point(l2.vertX, l1.m*l2.vertX + l1.b);
		}
		
		double X = (l1.b-l2.b)/(l2.m-l1.m);
		return new Point(X, l2.m*X + l2.b);
	}
	
	public boolean pointOnline(Line l, Point p){
		if(l.vertical){
			if(p.x == l.vertX)
				return true;
			return false;
		}
		else{
			if(Math.abs(p.y - l.m*p.x +l.b) <= 0.001 )
				return true;
			return false;
		}
	}
	
}

*/