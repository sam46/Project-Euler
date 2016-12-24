#include <iostream>
#include <cmath>
using namespace std;

typedef unsigned long long int ul;


ul f(int n){
	ul num = 1 + (ul)pow(n,11);
	return num/(1+n);
}

int main() {



	for (int i = 1; i < 20; ++i) {
		cout << f(i)<< ", " ;
	}

	return 0;
}
