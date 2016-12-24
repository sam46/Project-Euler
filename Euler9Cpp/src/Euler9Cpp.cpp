#include <iostream>
#include <cmath>
using namespace std;

int main() {
	long long c, b;

	for (c = 997; c >= 3; --c) {
		for (b = 1000-c-1; b >= ceil((1000-c)/2.0); b--) {
			int a = 1000-b-c;
			if(a*a + b*b == c*c){
				cout << a<<"  "<< b<<"  "<<c << endl;
				cout << c*b*a << endl;
				return 0;
			}
		}
	}

	return 0 ;
}
