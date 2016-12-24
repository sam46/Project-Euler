import java.util.*;

public class Euler85 {
	// Note: when I say: Rectangle (J x K) I mean J is the width and K is the
	// height!!

	// On the qustion's page we see that for a (3 x 2) rectangle there are 6 cases when picking a sub-rectangle (x by y): ( such that x is the width,	// y is the height)
	// we can pick x=1 and then y: 1,2 (1 x 1) (1 x 2)
	// we can pick x=2 and then y: 1,2 (2 x 1) (2 x 2)
	// we can pick x=3 and then y: 1,2 (3 x 1) (3 x 2)

	// so if our main rect is (w x h): x will go 1,2,3,...w and 
	// for every x, y will go 1,2,3,....h
	// so to get those 6 rectangles in code:
	
	/*
	 * for (int x = 1; i <= w; i++) 
	 * 		for (int y = 1; j <= h; j++)
	 */
	
	// Now, if we have a horizontal rectangle (5 x 1) ,it obviously has 3 (3 x 1) sub-rectangles, 4 (2 x 1) sub-rectangles and so on.
	// what's the formula that tells me how many (x by 1) sub-rectangles there are in a (w x 1) rectangle ?
	// it's: w-x+1

	// How about a vertical rectangle (1 x 5) ? how many (1 x 3) sub-rectangle does it have? also 3 and so on.
	// so if I have a rectangle (1 x h) , what's the formula that tells me how many (1 x y) sub-rects it has?
	// it's: h-y+1

	// Now, what many sub-rects (x by y) are there in a, say, (w x h) rectangle?
	// if I know: * how many (x by 1) are in (w x 1) ... gamma1
	//			  * how many (1 x y) are in (1 x h) ... gamma2
	// then there're gamma1 x gamma2 (x by y) sub-rects in (w x h)
	// we already know that gamma1 = w-x+1 and gamma2 = h-y+1

	// So: If I have (w x h) big rectangle, it has (w-x+1)*(h-y+1) sub-rectangles of size (x by y) !!

	/*
	 * |||||||||||||||||||||||||||||||||||||||||||||||||||||||||| 
	 * || EXAMPLE: the second case on the problem's website:   ||
	 * || 					(w-x+1)*(h-y+1)                    || 
	 * || 				(3-2+1)*(2-1+1) = 2*2 = 4 			   ||	
	 * ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
	 */

	public static void main(String[] args) throws InterruptedException {		
		long area = 0;                                                 // the area of the solution 
		long closest = Integer.MAX_VALUE;                              // this will store
		long proximityToTarget = Integer.MAX_VALUE;                    // this will store how close n is to 2m
		long n;

		// I'll try all possible rects/squares between (1,1) and (632,632) .  I arrived at the number 632 with guessing and trial and error XD
		// At the end I'll see the closest number to 2m.
		for (int i = 632; i >= 1; i--) {
			for (int j = 632; j >= 1; j--) {
				n = numRect(i, j);
				proximityToTarget = Math.abs(2000000 - n);
				
				if (proximityToTarget < closest) {
					closest = proximityToTarget;
					area = i * j;
				}
			}
		}
		
		System.out.println("nearest solution's area: "+area);
		System.out.println("number of sub-rectangles: "+(2000000-closest));
	}

	// Returns the number of possible sub-rectangles in the big rectangle (w x h) by summing the number of sub-rects for each case (i x j)
	static long numRect(long w, long h) {
		long total = 0l;
		for (long i = 1; i <= w; i++) {
			for (long j = 1; j <= h; j++) {
				long n = (w - i + 1l) * (h - j + 1l); // number of sub-rects (i x j)
				total += n;
			}
		}
		return total;
	}

}
