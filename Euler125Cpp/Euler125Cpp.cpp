#include <iostream>
#include <sstream>

using namespace std;

typedef unsigned long long uul;

bool isPalindrome(uul x){
	stringstream ss;
	string str, reverse;
	ss << x; ss >> str;

	for (int i = str.length()-1; i>=0; --i)
		reverse += str[i];

	return str==reverse;
}

int main() {

	int count = 0;

	for (uul i = 10; i < 100000000; ++i) {
		if(isPalindrome(i))
			count++;
	}

	cout << count << endl;

	return 0;
}
