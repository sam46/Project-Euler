#include <cmath>
#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
#ifndef EULER387_H_
#define EULER387_H_
typedef long long uulong;

vector<uulong> primes;

bool isPrime(unsigned long long x){

	if(x==1) return false;
	if(x==2 || x==3) return true;
	if(binary_search(primes.begin(), primes.end(), x))	return true;
	unsigned long long sqrt_x = (unsigned long long)(sqrt(x)+1);
	for(unsigned long long i = 2; i <= sqrt_x; i++)
		if(x%i==0)
			return false;
	return true;
}


vector<uulong> seive(uulong x){
	uulong n = ceil(sqrt(x));
	n *= n;

	/* Note: if I do:  uulong arr[n]; instead, the array will be allocated on the stack and for large n
	   this will exceed the stack's memory limit.
	   This was a bug previously, now resolved by using "new". */

	uulong *arr = new uulong[n];
	for (uulong i = 0; i < n; ++i)	arr[i] = i;

	for (uulong i = 2; i <= sqrt(n); ++i) {
		if(arr[i]!=0)
			for (uulong j = i*2; j < n; j+=i)
				arr[j] = 0;
	}

	vector<uulong> v;
	// keep the primes up to x only instead of n
	for (uulong i = 2; i <= x; ++i) if(arr[i]) v.push_back(i);
	delete[] arr;				// apparently when writing in a location outside arr[] boundries, a runtime error doesn't necessarily occur immediately, but it does later when delete is called.
	return v;
}

// realized I can use bool instead of numeric types. bool = 1 byte !!
vector<uulong> seive_bool(uulong x){
	uulong n = ceil(sqrt(x));
	n *= n;

	/* Note: if I do:  uulong arr[n]; instead, the array will be allocated on the stack and for large n
	   this will exceed the stack's memory limit.
	   This was a bug previously, now resolved by using "new". */

	bool *arr = new bool[n];
	for (uulong i = 0; i < n; ++i)	arr[i] = 1;

	for (uulong i = 2; i <= sqrt(n); ++i) {
		if(arr[i]!=0)
			for (uulong j = i*2; j < n; j+=i)
				arr[j] = 0;
	}

	vector<uulong> v;
	// keep the primes up to x only instead of n
	for (uulong i = 2; i <= x; ++i) if(arr[i]) v.push_back(i);
	delete[] arr;				// apparently when writing in a location outside arr[] boundries, a runtime error doesn't necessarily occur immediately, but it does later when delete is called.
	return v;
}




#endif /* EULER387_H_ */
