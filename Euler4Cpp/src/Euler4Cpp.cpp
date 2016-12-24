#include <iostream>
#include <sstream>
using namespace std;


bool isPal(long x){
	stringstream ss;
	ss << x;
	string a = ss.str();
	for (int i = 0; i < a.length()/2; ++i)
		if(a[i] != a[a.length()-1-i])
			return false;
	return true;
}

int main() {
	long max = 0;

	for (long i = 999; i >= 100; --i)
		for (long j = i; j >= 100; --j)
				if(isPal(i*j))
					max = (max<(i*j)) ? i*j : max;

	cout << max << endl;
	return 0;
}
