#include <iostream>
using namespace std;

int main() {

	long long x = 0;
	for (int i = 1; i <= 100 ; ++i)		x += i*i;
	cout << (50*101)*(50*101) - x <<endl;
	return 0;
}
