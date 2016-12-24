/*
 	Total (sum possibilities) for peter:
 			peter can get {9, 10 ,11,.....,35, 36}
 			in how many ways can he get each?
 			for 9 he needs nine 1's: 111 111 111
 			for 10 he need eight 1's and a 2:
 			we'll use generating functions:
 			his die can have an outcome {1,2,3,4},  but he rolls nine of them

 			( x^1 + x^2 + x^3 + x^4 )^9   that's the generating function.
 			if expanded(using wolframalpha) it'll give:  (a1)x^9 + (a2)x^10 + (a3)x^11 + ..... + (a27)x^35 + (a28)x^36
 			the parenthesis a1 through a28 represent how many ways to get each sum(the exact sum corresponds to the exponent).


 			NOW we do the same thing for colin:

 			( y^1 + y^2 + y^3 + y^4 + y^5 + y^6 )^6
 			and that will give: (b1)y^6 + (b2)y^7 + .... + (b31)y^36

 			obviously, Peter can get 28 different sums:
 			to get 9: a1 ways
 			to get 10: a2 ways
 			...
 			to get 36: a28 ways
 			so all different 28 sums can be accomplished with (a1 + a2 +...+ a28) ways (let's call this sum peter_ways)

 			With the same reasoning, colin_ways are (b1 + b2 + .... + b31)

	P = (# of ways peter wins)                           / (# of all possible sums combination for peter an colin)
	  = (# of ways peter gets a sum bigger than colin's) / (peter_ways * colin_ways)

	How to calculate the numerator of this P ?
			the generating function of each player already gave us all ways to get any sum that player can score.
			- if peter gets 9 (that's a1 ways), then he wins if colin gets 6 (b1 ways) or 7 (b2 ways) or 8 (b3 ways)
			  So the number of ways for peter to win when he gets a 9 is:  (a1 * b1) + (a1 * b2) + (a1 * b3)  =  a1 * (b1 + b2 + b3)
			- if peter gets 10 and wins then that's : a2 * (b1 + b2 + b3 + b4)
			- and so on
			so # of ways peter wins are:
			 	 #ways  he gets (9 and wins) + #ways he gets (10 and wins) + ... + #ways he gets (36 and wins)
			we just add all those up.

	NOW we have numerator and denominator
	THE END
 */


#include <iostream>
using namespace std;

// number of ways to get each sum for the two players:
int peter_coeff [28] = {                     1,     9,     45,   165,  486,  1206,  2598,  4950,	 8451, 13051,  18351, 23607,  27876,  30276,  30276,  27876,  23607,  18351,  13051,  8451,	4950,  2598,  1206,   486,  165,  45,  9,  1 };
int colin_coeff [31] = { 1,    6,    21,    56,    126,    252,  456,  756,  1161,  1666,  2247,  2856,  3431,   3906,  4221,   4332,   4221,   3906,   3431,   2856,   2247,   1666,  1161,  756,   456,   252,   126,   56,  21,  6,  1};

typedef unsigned long long uulong;

int main() {
	uulong num = 0, den;

	// calculate the numerator:  a1*(b1 + b2 + b3) + a2*(b1 + b2 + b3) + ..... + a28*(b1 + b2 +... + b30)
	for (int p = 0; p < 28; ++p) {
		uulong p_count = peter_coeff[p];    // p_count is the current a_

		uulong c_count = 0;					// c_count is the is the number of ways colin's sum is less than peters
		for (int c = 0; c < p+3; ++c) c_count += colin_coeff[c];  // notice if pete'sum is 9 (corresponding to index p=0) then possible colin's sum are 6,7,8  (corresponding to c= 0,1,2) so c runs from [0 to p+2] inclusive or [0 to p+3) exclusive.

		num += p_count*c_count;   // num += a_ * (b_ + b_ + ...)
	}

	// calculate the denominator
	uulong peter_ways = 0, colin_ways = 0;
	for (int i = 0; i <= 13; ++i) peter_ways += peter_coeff[i];
	peter_ways *=2;
	for (int i = 0; i <= 14; ++i) colin_ways += colin_coeff[i];
	colin_ways = colin_ways*2 + 4332;

	den = peter_ways * colin_ways;


	cout << num << " / " << den << endl;
	return 0;
}


