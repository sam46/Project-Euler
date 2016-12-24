/*
	 UNSOLVED:  This works (in principle) but it's too slow and goes over the datatypes limit,
	    		for another faster method see: java project Euler78

 	 The expansion of the genrating function:  prod from n=1 to infinity of 1 / (1-x^n)
 	 yeilds: sum (a_i)x^i
 	 where a_i = p(i) {the number of partitions of i}

 	 Watch this: https://www.youtube.com/watch?v=XN3Vy2M5ov8&list=PLDDGPdw7e6Aj0amDsYInT_8p6xTSTGEi2&index=14

 	 So i'll expand the generating functions to get a list of p(i)  (for i goes as big as needed)

	 Another faster way to generate p(n) is using recurrence formula:
		see: java project Euler78


 */
#include <iostream>
#include <cmath>
#include <vector>
#include <algorithm>

using namespace std;

typedef unsigned long long myT;


/*
 Given A and B, the coeffis of two power-series f(x) and g(x),  it returns the coeffis of f(x)*g(x)
 f(x) 		 = a0 + a1 x + a2 x^2 + ....
 g(x) 		 = b0 + b1 x + b2 x^2 + ....
 f(x) * g(x) = c0 + c1 x + c2 x^2 + ....

 it returns C = [c0, c1, c2,....]
*/

vector<myT> multiply(const vector<myT> & A, const vector<myT> & B){
	int num = A.size();

	vector<myT> C;

	for (int n = 0; n < num; ++n) {
		// calculate c_n
		myT c = 0;
		for (int i = 0; i <= n; ++i)
			c += A[i] * B[n-i];

		C.push_back(c);
	}

	return C;
}


// returns the coeffis of the power series representation of 1/(1-x^m)
// num is how many terms we want
vector<myT> pSeries(int m, int num){
	vector<myT> coeffis;

	for (int i = 0; i < num; ++i) {
		if(i % m == 0)
			coeffis.push_back(1);
		else
			coeffis.push_back(0);
	}

	return coeffis;
}

// num is how many terms
vector<myT> prod(int num){
	vector<myT> A = pSeries(1, num);
	vector<myT> B = pSeries(2, num);
	vector<myT> C;
	for (int i = 3; i < num; ++i) {
		cout << "Currently at "<<i << endl;
		C = multiply(A, B);
		for (int j = 0; j < C.size(); ++j)
			if((C[j]%1000000 == 0) && C[j]!=0){	cout <<  j-1<< endl; }

		A = C;
		B = pSeries(i, num);
		//if(i%100==0)
	}

	return C;
}



int main() {

	//vector<myT> vec= prod(10000);
	myT x =0;
	cout << x-1 << endl;
	//for (int i = 0; i < vec.size(); ++i) {
		//if(vec[i] % 1000000 == 0)
		//cout << i-1 <<", ";
	//}


}



