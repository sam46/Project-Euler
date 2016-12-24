#include <iostream>
using namespace std;

// 20 = 5*2*2;       5
// 19 = 19  		 19
// 18 = 3*3*2;       3^2
// 17 = 17			 17
// 16 = 2*2*2*2;     2^4
// 15 = 5*3;
// 14 = 7*2;         7
// 13 = 13			 13
// 12 = 3*2*2;
//					 11
// 10 = 5*2;
//  9 = 3*3;
//					 7
//  8 = 2*2*2;
//					 5
//  6 = 3*2;
//  4 = 2*2;

int main() {
	//unsigned long long x = 2*2*2*2ul*3ul*3*5*7*11ul*13*17*19ul;
	//cout << x << endl;
int x=0;
for (int i = 0; i < 9; ++i) {
	if(x += 5) cout << x << endl;
}
	return 0;
}
