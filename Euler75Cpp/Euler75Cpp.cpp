#include <iostream>
#include <algorithm>
#include <vector>
#include <cmath>
using namespace std;
typedef unsigned long long type;

//const type upperL = 1500000;
//const type upperC = 638425;
//type * sq;
//
//
//
//void clean(){
//	delete * sq;
//}
//
//
//
//int main() {
//	sq = new type[]
//	return 0;
//}
//
typedef long long dlong;

//long long limit = sqrt((long double)(100));
//
//
//int main(){
//	vector<dlong*> triples;
//	dlong s,t;
//
//	dlong tLimit = (sqrt((long double)(1 + 4*limit)) - 1)/2.0;
//	for(t=1; t < tLimit; ++t){
//		for (s = t+1; s*t<=limit; ++s) {
//			dlong a,b,c;
//			a = s*t;
//			b = (s*s - t*t)/2;
//			c = (s*s + t*t)/2;
//			dlong* trip = new dlong[3];
//			trip[0] = a;
//			trip[1] = b;
//			trip[2] = c;
//			triples.push_back(trip);
//		}
//	}
//
//	for (int i = 0; i < triples.size(); ++i)
//		cout << triples[i][0]<<","<<triples[i][1]<<","<<triples[i][2]<<endl;
//
//	cout << "total: "<<triples.size()<<endl;
//
//	for (int i = 0; i < triples.size(); ++i)
//		delete[] triples[i];
//
//
//
//	return 0;
//}

vector<int> sols;

int solCount(dlong L){
	int count = 0;
	for(dlong t = 1; t < L/2; ++t){
		for(dlong s = t+1; 2*s*(s+t) <= L;++s){


//			if((c == 50) && (a == 30 || b == 30))
//			cout << "("<<t<<", "<<s<<")"<<endl;
			dlong diff = L - 2*s*(s+t);
//			if(diff < 0)
//				break;
//			if(diff == 0){
//				count++;
			if(diff>0){
				dlong a = 2*s*t;
				dlong b = s*s - t*t;
				dlong c = s*s + t*t;
				//cout << a <<","<<b<<","<<c<<endl;
				sols[a+b+c]++;
			}
		}
	}
	return count;
}


int main() {
	for (int i = 0; i < 1500000; ++i)
		sols.push_back(0);


//	//dlong L = 120;
//	for (dlong i = 1; i < 1500000; ++i) {
//		if(i % 100000 == 0)
//			cout << "at "<<i<<endl;
//		int count = solCount(i);
////		if(count > 0)
////			cout<<"L = " <<i<< " has "<< count <<" solutions"<<endl;
//		sols[i] += count;
//	}



	cout <<solCount(1500000)<<endl;

	for (int i = 0; i < sols.size(); ++i) {
		if(sols[i] == 1)
			cout << i<<endl;
	}

	return 0;
}



