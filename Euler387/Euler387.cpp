#include <iostream>
#include "Euler387.h"
#include <algorithm>
using namespace std;

//    DID NOT SOLVE IT !!

/*	We will gather a list of strong harshads and go from there.
 * 	start with 999 999 999 999 9
 * 	remove one digit --> 999 999 999 999.  (12 digits)
 *
 * 	now  we want harshads that when divided by the sum will give a prime.
 * 	or   we can take primes multiples and check if they are harshads.
 * 	so now we want (multiples of primes) under 999 999 999 999
 * 	since the smallest number we can multiply by is 2,
 * 	then we want (primes) < (999 999 999 999 / 2 = 5 * 10^11).
 *
 * 	The largest prime in that range is 499999999979
 *
 *
 *
 *
 *
 *
 *
 */


bool check(unsigned long long x){ // the functions assumes x to be prime and x >= 10 !!
	unsigned long long original_x = x;
	x /= 10;

	// check if it's strong and Harshad
	unsigned long long sum = dsum(x);
	if(x%sum !=0 )//|| !binary_search(primes.begin(), primes.end(), x/sum) )
		return false;


	// check if it's right truncatable Harshad
	x /= 10;

	while(x>0) {
		sum = dsum(x);
		if(sum==0) cout << "warning sum = 0" << endl;
		if(x%sum != 0)
			return false;
		x /= 10;
	}

	if(!isPrime(original_x/dsum(original_x)))
			return false;

	return true;
}


int main() {
	//primes = seive(2000000);

	/*
	int num = 0, h_sum = 0;

	for (int i = 10;i<10000 ; ++i) {
		int current_prime = i;//primes[i];
		//if(current_prime >= 10000) break;

		if(check(current_prime)){
			h_sum += current_prime;
			num++;
			cout << current_prime << endl;
		}
	}
	*/

	unsigned long long num =1, h_sum = 0;

	for (unsigned long long i = 500000000000-1; ; i-=2) {
		//if(i%1000000000==1){
			//int x = (int)(i/1000000000);
			//cout << "currently at "<< x <<" * 10^9  in 10^14" << endl;
		//}
		if(isPrime(i)){
			//if(check(i)){
				cout << i << endl;
				return 0;
				//h_sum += i;
			}
	//	}
	}

	cout << "total numbers: "<<num<< endl;
	cout <<  "their sum: "<<h_sum<< endl;
	return 0;
}
