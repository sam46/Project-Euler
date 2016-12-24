#include <iostream>
#include <vector>
#include "Euler204Cpp.h"
using namespace std;
/*
 *
 *
 * 		7	11	13	17	19	23	29	31	37 41	43	47
 *
 *
 *
 */
vector<int> factorize(int x){
	vector<int> pfacts;
	if(isPrime(x)){
		pfacts.push_back(x);
		return pfacts;
	}

	int cur = 2;
	int i = 0;
	while(x!=1){

		if(x%cur == 0){
			//if(!binary_search(pfacts.begin(), pfacts.end(), cur)) // no duplicates !!
			pfacts.push_back(cur);
			cout <<  x<<"/"<< (int)pow(cur, floor(log(x)/log(cur)));
			x /= (int)pow(cur, floor(log(x)/log(cur))); cout << " = "<<x << endl;
			//continue; // dont jump to the next potential factor, cuz the current one might divide agian.
		}

		cur = primes[i++];
	}

	return pfacts;

}

bool isHamming(int x){

	if(x>5){
		if(isPrime(x))
			return false;
	}


	int cur = 2;
	int i = 0;
	while(x!=1){

		if(x%cur == 0){
			if(cur>5)
				return false;
			x /= cur;
			continue; // dont jump to the next potential factor, cuz the current one might divide agian.
		}

		cur = primes[i++];
	}

	return true;
}



int main() {
	long long n = 250;
	primes = seive_bool(n);

	long long good_primes_limit = 5;
	long long largest_good_prime = good_primes_limit;
	long long ind_largest_good_prime = -1;

	for (int i = 0; i < primes.size(); ++i) {
		if(primes[i] > good_primes_limit){
			largest_good_prime = primes[i-1];
			ind_largest_good_prime = i-1;
			break;
		}
	}

	//vector<int> pfacts = factorize(24);
	//for (int i = 0; i < pfacts.size(); ++i) {;
		//cout << pfacts[i] << ", ";
	//}

	cout << "done sieving" << endl;


	int total = 5;
	for (int i = 6; i < n ; ++i) {
		if(i%1000000==0) cout << "i= "<<i << endl;
		if(isHamming(i)){
			total++;
			//cout << i <<" is a hamming" << endl;
		}
	}
	cout << total << endl;

	/*
	int sum = 0;
	sum += 10; 			 // primes above n/2 = 100/2 = 50
	sum +=  100/7 + 100/11 + 100/13 + 100/17 + 100/19 + 100/23 + 100/29 + 100/31 + 100/37 + 100/41 + 100/43 + 100/47; // multiples of the (unallowed primes under n/2)
	sum -=  0    +     1    +  1    +	0 + 0 +0 +0    ;  // subtract repeated multiples
	cout << 99-sum << endl;
	*/



	long long non_hammings = 0;
	int ind_largest_under_half_n = -1;
	// count how many primes there are after n/2
	for (int i = 0; i < primes.size(); ++i) {
		if(primes[i] > n/2 ){
			non_hammings++;
			if(ind_largest_under_half_n == -1) ind_largest_under_half_n = i-1;
		}
	}

	vector<uulong> checked_primes;
	for (int i = ind_largest_good_prime+1 ; i <= ind_largest_under_half_n; ++i) {
		non_hammings += (int)(n/primes[i]);

		//cout << "\nrepeated multiples of "<<primes[i] << ": ";

		for (int j = 0; j < checked_primes.size(); ++j) {
			if(primes[i] * checked_primes[j] <= n){
				non_hammings--;
				//cout << checked_primes[j] << "  ";
			}
			//else break;
		}

		checked_primes.push_back(primes[i]);
	}
	cout << n<<" - 1 - "<< non_hammings<<" = ";
	cout << n-1-non_hammings << endl;
	return 0;
}




