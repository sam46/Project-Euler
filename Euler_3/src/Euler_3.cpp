//============================================================================
// Name        : Euler_3.cpp
// Author      : 
// Version     :
// Copyright   : 
// Description : Hello World in C++, Ansi-style
//============================================================================
#include <cmath>
#include <iostream>
#include <vector>
using namespace std;
/*
bool isPrime(unsigned long long);
vector<unsigned long long> factorize(unsigned long long n);
void foo(unsigned long long);

void foo(unsigned long long x){
	if(x==1)
		return;
	unsigned long long n = sqrt(x)+1;

	for (unsigned long long var = 2; var <= n; var++) {
		if(x%var==0){
			cout<<var<<"   "<<(x/var)<<endl;
			foo(var); foo(x/var);
			break;
		}
	}
}

int main() {
	vector<unsigned long long> factors;
	foo(600851475143);
	return 0;
}

vector<unsigned long long> factorize(unsigned long long n){
	if(isPrime(n)) {
		vector<unsigned long long> t;
		t.push_back(n);
		return t;
	}

	vector<unsigned long long> pfact;
	unsigned long long rt_n = sqrt(n)+1;
	for(unsigned long long i = 2; i <= rt_n; i++){
		vector<unsigned long long> temp1,temp2;
		if(n%i==0){
			temp1 = factorize(i);
			pfact.insert(pfact.end(), temp1.begin(), temp1.end());
			temp2 = factorize(n/i);
			pfact.insert(pfact.end(), temp2.begin(), temp2.end());
		}
	}

	return pfact;
}



bool isPrime(unsigned long long x){
	if(x==1) return false;
	if(x==2 || x==3) return true;
	unsigned long long sqrt_x = (unsigned long long)(sqrt(x)+1);
	for(unsigned long long i = 2; i <= sqrt_x; i++)
		if(x%i==0)
			return false;
	return true;
}
*/

bool numPrime(unsigned long long x){
	unsigned long long a = 0;
	for(unsigned long long i = 2; i < x; ++i){
		if(x%i == 0) {
			a += 1;
			break;
		}
	}
	return (a == 0? false: true);
}

unsigned long long alg(unsigned long long y){
	unsigned long long a;
	for(unsigned long long i = 2; i < y; ++i){
		if(y % i == 0) {
			a = i;
			cout<<a<<endl;
			break;
		}
	}
	return y/a;
}

int main(){
	unsigned long long var = 3*5*12;
	while(numPrime(var)){
		var = alg(var);
		cout << var << endl;
	}
	cout << var << endl;
	return 0;
}
