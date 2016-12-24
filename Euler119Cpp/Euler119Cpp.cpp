/*
	EASINESS: not hard
512 = 8 ^ 3
x   = s ^ p  where:	 all variables are INTEGERS. (especially p :v)
					 and s = s(x)  : the sum of the digits of x

I wanna generate the first 30 x's with the said property
-------------------------------------------------------------------------------------------
assume from now on that by log()  we mean: log_10()
with some math:
p = log(x) / log(s)

so if i suspect that a certain x has the said property, to test it do this:
	calculate s=s(x)   ----> calculate p = log(x)/log(x)
	if p is integer then yes x has the property
	if p isn't, x doesn't satisfy the property

now all i have to do is test all the numbers beginning at say 10, and going up.
that's cool but the 30th x that satisfies the property is 248155780267521
good luck doing (i=10 ; ; ++i) {...}
will take forever before i reaches the answer.

Instead we will generate a list of x's that can potentially satisfy the property.
notice: x = s^p  that is: x is a POWER of a number s  (which happens to be the sum but that's not important)
so all I have to do is make a list of all powers (up to a reasonable range) and test those.

now based on some observations: the 30th x wont have a crazy
amount of digits, say the max it can have is 25 digits  (in fact it's 15 :v)
Also based on observation and some luck and guessing, s and p wont go crazy big.
I know the 1st x is 81  so i want to have a list of the first 30  x's
   10 < (x = s^p) < 10^25   (probably an overkill)

so i wanna test different s's and p's but I dont want s^p to go beyoned 25 digits.
easy: try all s's beginning at 2 and going up to say 100  (probably an overkill)
now for a given s:   10 < s^p < 10^25  ---->    2/log(s) < p < 25/log(s)

and just like that i have a list of powers
take each one and test it
if it passes, print it
DONE
----------------------------------------------------------------------------------------
BRIEF:
x = s^p
how to generate the first 30 x's with the said property?
test all powers   s^p  for s: 2->100  and p: 2/log(s)->25/log(s)
where all the numbers (or bounds) 2,100,25 are deduced or guessed based on luck and observations

*/

#include <iostream>
#include <cmath>
#include <vector>
#include <algorithm>
using namespace std;

typedef unsigned long long uul;

// sum up the digits of x
int dsum(uul x){
	int sum = 0 ;
	while(x>0){
		sum += x%10;
		x /= 10;
	}

	return sum;
}


// test if x satisfies the said property or not
bool check(uul x){
	int s = dsum(x);
	long double p = log10(x)/log10(s);

	if(abs(round(p)-p) <= 0.000000000000001)
		return true;
	return false;
}

int main() {

	vector<uul> cands;

	// Make the list of powers
	for (int i = 2; i < 100; ++i) {			// minimum sum of digits is 2, and I picked a random maximum of 100
		int min_pow = 2.0/log10(i);  // I want my x's to be between	2 and 25 digits, so the sum (or i) should be raised to a power:  min_pow > p > max_pow
		int max_pow = 25.0/log10(i);	// and 23 digits.

		sort(cands.begin(), cands.end());  // nice to keep stuff ordered

		for (int j = min_pow; j <= max_pow ; ++j) {
			uul x = round(pow((uul)i,(uul)j));   			  // x = i^j .        	  weird thing is: without round() if i=17 and j=3, then x will somehow end up having 4912 instead of 4913
			if(!binary_search(cands.begin(), cands.end(),x))  // if x isn't already there , put it.
				cands.push_back(x);

		}
	}


	// print the powers that satsify the property
	int j = 0;
	for (int i = 0; i < cands.size(); ++i) {
		if(check(cands[i]))			// if x in cands[] has the so called property, print it
			cout <<++j<< ":    " <<cands[i]<< endl;
	}


	return 0;
}
