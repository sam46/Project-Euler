import java.util.*;
import java.io.*;

public class Euler54 {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(new File("f:\\poker.txt"));
		int p1Wins = 0;
		while(sc.hasNext()){
			String str = sc.nextLine();
			if(winner(str.substring(0, 14), str.substring(15)) == 1)
				p1Wins++;
				
		}
		
		System.out.println("P1 wins "+p1Wins+" times.");
	}
	
	// p1 wins return 1 else return 2;
	static int winner(String hand1, String hand2) {
		Card[] p1 = new Card[5];
		Card[] p2 = new Card[5];
		StringTokenizer st = new StringTokenizer(hand1," ");
		for (int i = 0; i < p1.length; i++) {
			p1[i] = new Card();
			String temp = st.nextToken();
			p1[i].setValue(temp.substring(0,temp.length()-1));
			p1[i].setSuit(temp.charAt(temp.length()-1));
		}
		
		st = new StringTokenizer(hand2," ");
		for (int i = 0; i < p2.length; i++) {
			p2[i] = new Card();
			String temp = st.nextToken();
			p2[i].setValue(temp.substring(0,temp.length()-1));
			p2[i].setSuit(temp.charAt(temp.length()-1));
		}

		if(isRoyalFlush(p1)) return 1;
		if(isRoyalFlush(p2)) return 2;

		if(isStriaghtFlush(p1) > isStriaghtFlush(p2))  return 1;
		if(isStriaghtFlush(p2) > isStriaghtFlush(p1))  return 2;

		if(isFourOfaKind(p1) > isFourOfaKind(p2)) return 1;
		if(isFourOfaKind(p2) > isFourOfaKind(p1)) return 2;

		if(isFullHouse(p1) > isFullHouse(p2)) return 1;
		if(isFullHouse(p2) > isFullHouse(p1)) return 2;	

		if(isFlush(p1) > isFlush(p2)) return 1;
		if(isFlush(p2) > isFlush(p1)) return 2;

		if(isStraight(p1) > isStraight(p2)) return 1;
		if(isStraight(p2) > isStraight(p1)) return 2;

		if(isThreeOfaKind(p1) > isThreeOfaKind(p2)) return 1;
		if(isThreeOfaKind(p2) > isThreeOfaKind(p1)) return 2;

		if(isTwoPairs(p1) > isTwoPairs(p2)) return 1;
		if(isTwoPairs(p2) > isTwoPairs(p1)) return 2;

		if(isOnePair(p1) > isOnePair(p2)) return 1;
		if(isOnePair(p2) > isOnePair(p1)) return 2;
		
		if(highestCard(p1, p2) == 1) return 1;
		else return 2;
	}
	
	static int highestCard(Card[] hand1, Card[] hand2){
		sort(hand1);
		sort(hand2);
		int i = 1;
		int h1 = hand1[5-i].value, h2 = hand2[5-i].value;
		
		while (h1 == h2) {
			i++;
			h1 = hand1[5-i].value;
			h2 = hand2[5-i].value;
		}
		
		if(h1>h2) return 1;
		else return 2;
	}
	
	static int highCard(Card[] hand){
		int h = -1;
		for (int i = 0; i < hand.length; i++) {
			if(hand[i].value > h)
				h= hand[i].value;
		}
		return h;
	}

	static void sort(Card[] hand){
		int sorted = 0;
		
		while(sorted < hand.length-1){
			int smallest = sorted;
			for (int i = sorted; i < hand.length; i++) {
				if(hand[i].value < hand[smallest].value)
					smallest = i;
			}
			
			/* swap hand[sorted] with hand[smallest] */
			Card temp = new Card(hand[sorted]);
			hand[sorted] = new Card(hand[smallest]);
			hand[smallest] = new Card(temp);
			/* done swapping */
			
			sorted++;
		}
		
	}

	static int isOnePair(Card[] hand){
		/* return the highest pair, or -1 if there ain't any. */
		for (int n = 14; n >=2; n--) {
			for (int i = 0; i < hand.length; i++) 
				for (int j = i+1; j < hand.length; j++) 
					if(hand[i].value == n && hand[j].value == n)
						return n;
					
		}
		
		return -1;
	}
	
	static int isTwoPairs(Card[] hand){
		int x=-1,y=-1;
		for (int i = 0; i < hand.length; i++) {
			for (int j = i+1; j < hand.length; j++) {
				if(hand[i].value == hand[j].value){
					x=i; y=j;
					break;
				}		
			}
		}
		
		if(x==-1) return -1;
		
		int a=-1, b=-1;
		for (int i = 0; i < hand.length; i++) {
			if(i==x || i==y) continue;
			for (int j = i+1; j < hand.length; j++) {
				if(j==x || j==y) continue;
				if(hand[i].value == hand[j].value){
					a=i; b=j;
					break;
				}
			}
		}
		if(a==-1) return -1;
				
		return Math.max(hand[x].value, hand[a].value);
	}
	
	static int isFlush(Card[] hand){
		if(sameSuit(hand))
			return highCard(hand);
		return -1;
	}
	
	static int isStraight(Card[] hand){
		sort(hand);
		int first = hand[0].value;
		for (int i = 1; i < hand.length; i++) {
			if(hand[i].value != first + i)
				return -1;
		}
		return hand[0].value;
	}
	
	static int isThreeOfaKind(Card[] hand){
		int x=-1,y=-1,z=-1;
		for (int i = 0; i < hand.length; i++) {
			for (int j = i+1; j < hand.length; j++) {
				for (int k = j+1; k < hand.length; k++) {
					if(hand[i].value == hand[j].value && hand[j].value == hand[k].value)
						x=i; y=j; z=k;
				}
			}
		}
		if(x==-1)
			return -1;
		
		return hand[x].value;
	}
	
	static int isFullHouse(Card[] hand) {
		int x=-1,y=-1,z=-1;
		for (int i = 0; i < hand.length; i++) {
			for (int j = i+1; j < hand.length; j++) {
				for (int k = j+1; k < hand.length; k++) {
					if(hand[i].value == hand[j].value && hand[j].value == hand[k].value){
						x=i; y=j; z=k;
						break;
					}
				}
			}
		}
		if(x==-1)
			return -1;
		
		int a=-1,b=-1;
		for (int i = 0; i < hand.length; i++) {
			if(i != x && i != y && i != z)
				a=i;
		}
		b = 10-(a+x+y+z);		
		
		if(hand[a].value == hand[b].value)
			return hand[x].value;
		return -1;
	}
	
	static int isFourOfaKind(Card[] hand) {
		/* C(5,4) = 5 possibilities */
		
		if(hand[0].value == hand[1].value && hand[1].value == hand[2].value && hand[2].value == hand[3].value)
			return hand[0].value;

		if(hand[0].value == hand[1].value && hand[1].value == hand[2].value && hand[2].value == hand[4].value)
			return  hand[0].value;

		if(hand[1].value == hand[2].value && hand[2].value == hand[3].value && hand[3].value == hand[4].value)
			return  hand[1].value;

		if(hand[0].value == hand[2].value && hand[2].value == hand[3].value && hand[3].value == hand[4].value)
			return  hand[0].value;

		if(hand[0].value == hand[1].value && hand[1].value == hand[3].value && hand[3].value == hand[4].value)
			return  hand[0].value;
		  
		return -1;
	}
	
	static int isStriaghtFlush(Card[] hand) {
		if(!sameSuit(hand))  return -1;
		sort(hand);
		int first = hand[0].value;
		for (int i = 1; i < hand.length; i++) {
			if(hand[i].value != first + i)
				return -1;
		}
		return hand[0].value;
	}

	static boolean isRoyalFlush(Card[] hand){	
		if(!sameSuit(hand))  return false;
		for (int i = 10; i <= 13; i++) {
			if(!contains(hand, i))
				return false;
		}
		return true;
	}

	static boolean sameSuit(Card[] hand){
		Suits s = hand[0].suit;
		for (int i = 1; i < hand.length; i++) {
			if(hand[i].suit != s)
				return false;
		}
		return true;
	}
	
	static boolean contains(Card[] hand, Suits s){
		for (int i = 0; i < hand.length; i++) 
			if(hand[i].suit == s)
				return true;	
		return false;
	} 
	
	static boolean contains(Card[] hand, int v){
		for (int i = 0; i < hand.length; i++) 
			if(hand[i].value == v)
				return true;	
		return false;	
	}
	
	static boolean contains(Card[] hand,int v, Suits s){
		for (int i = 0; i < hand.length; i++) 
			if(hand[i].value == v && hand[i].suit == s)
				return true;
		return false;
	}

}


enum Suits{
	C,S,D,H
};

class Card{
	public Suits suit;
	public int value;

	public Card(){}
	public Card(Card c){
		suit = c.suit;
		value = c.value;
	}
	public Card(String val, char s){
		setValue(val);
		setSuit(s);
	}
	
	public void setValue(String val){
		char v = val.charAt(0);
		if(v=='J')
			value = 11;
		else if(v=='Q')
			value = 12;
		else if(v=='K')
			value = 13;
		else if(v=='A')
			value = 14;
		else if(v=='T')
			value = 10;
		else 
			value = Integer.parseInt(val);
		
	}

	public void setSuit(char s){
		if(s=='H')
			suit = Suits.H;
		else if(s=='C')
			suit = Suits.C;
		else if(s=='D')
			suit = Suits.D;
		else if(s=='S')
			suit = Suits.S;
	}
	
}
