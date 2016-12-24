#include <cmath>
#include <iostream>
#include <vector>
using namespace std;
#ifndef EULER387_H_
#define EULER387_H_

vector<int> primes;

bool isPrime(unsigned long long x){
	if(x==1) return false;
	if(x==2 || x==3) return true;
	unsigned long long sqrt_x = (unsigned long long)(sqrt(x)+1);
	for(unsigned long long i = 2; i <= sqrt_x; i++)
		if(x%i==0)
			return false;
	return true;
}


int dsum(int x){
	int sum = 0 ;
	while(x>0){
		sum += x%10;
		x /= 10;
	}

	return sum;
}

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





#endif /* EULER387_H_ */
