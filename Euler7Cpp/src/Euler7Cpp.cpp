#include <iostream>
#include <cmath>
#include <vector>
using namespace std;

vector<int> seive(int x){
	int n = ceil(sqrt(x));
	n *= n;

	/* Note: if I do:  int arr[n]; instead, the array will be allocated on the stack and for large n
	   this will exceed the stack's memory limit.
	   This was a bug previously, now resolved by using "new". */

	int *arr = new int[n];
	for (int i = 0; i < n; ++i)	arr[i] = i;

	for (int i = 2; i <= sqrt(n); ++i) {
		if(i!=0)
			for (int j = i*2; j <= n; j+=i)
				arr[j] = 0;
	}

	vector<int> v;
	// keep the primes up to x only instead of n
	for (int i = 2; i <= x; ++i) if(arr[i]) v.push_back(i);
	delete[] arr;
	return v;
}


int main() {
	vector<int> p  = seive(50000);
	for (int i = 0; i < p.size(); ++i){
		cout << p.at(i) << " ";
		if(i%100==0) cout<< endl;
	}

	return 0;
}

