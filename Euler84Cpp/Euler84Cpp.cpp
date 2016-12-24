#include <iostream>
#include <cmath>
#include <vector>
#include <algorithm>
#include <random>
#include <ctime>
#include <cstdlib>
#define JAIL 10
#define GO 0
#define G2J 30
#define CC1 2
#define CC2 17
#define CC3 33
#define CH2 22
#define CH1 7
#define CH3 36
#define C1 11
#define E3 24
#define H2 39
#define R1 5
#define NEXT_R 100
#define NEXT_U 200
#define BACK_3 300


#define die 6
using namespace std;
enum CellType {regular, jail, cc, ch};

class Cell{
public:
	int num;
	CellType type;

	Cell(){
		type = regular;
		num = -1;
	}

	Cell(int n){
		num = n;
	}

	Cell(int n, CellType ct){
		num = n;
		type = ct;
	}

	virtual int getNextStop(int i) = 0;
};

class RegularCell: public Cell{
public:
	RegularCell(){
		type = regular;
		num = -1;
	}

	RegularCell(int n){
		num = n;
	}

	RegularCell(int n, CellType ct){
		num = n;
		type = ct;
	}

	virtual int getNextStop(int i){
		return -1;
	}
};

class CC : public Cell {
	vector<int> deck;
	int curCard;

public:
	CC(int n) : deck(16,-1){
		type = cc;
		num = n;
		deck[0] = 0; deck[1] = JAIL;
		curCard = 0;
		for (int i = 0; i < deck.size(); ++i) {
			int ind = rand()%deck.size();
			int t = deck[ind];
			deck[ind] = deck[i];
			deck[i] = t;
		}
	}

	virtual int getNextStop(int i){
		int val = deck[curCard];
		curCard = (curCard+1)%16;
		return val;
	}

};

class CH : public Cell {
	vector<int> deck;
	int curCard;

public:
	CH(int n):	deck(16,-1){
		num = n;
		type = ch;
		deck[0] = 0; deck[1] = JAIL;
		deck[2] = C1; deck[3] = E3;
		deck[4] = H2; deck[5] = R1;
		deck[6] = NEXT_R; deck[7] = NEXT_R;
		deck[8] = NEXT_U; deck[9] = BACK_3;
		curCard = 0;
		for (int i = 0; i < deck.size(); ++i) {
			int ind = rand()%deck.size();
			int t = deck[ind];
			deck[ind] = deck[i];
			deck[i] = t;
		}
	}

	virtual int getNextStop(int i){
		int val = deck[curCard];
		curCard = (curCard+1)%16;

		if(val == NEXT_R) {
			if(i==7) return 15;
			if(i==22) return 25;
			if(i==36) return 5;
		}
		if(val == NEXT_U) {
			if(i==7) return 12;
			if(i==22) return 28;
			if(i==36) return 12;
		}
		if(val == BACK_3){
			if(i==7) return 4;
			if(i==22) return 19;
			if(i==36) return 33;
		}
		return val;
	}
};


class Board {
	vector<Cell*> cells;
	vector<long int> occurs;

	int double_chain;
	int cur;
	long int num_of_rolls;

	int move(int newLoc){
		cur = newLoc;
		//cout<<" -> "<<newLoc;
		if(newLoc == G2J){
			return move(JAIL);
			double_chain = 0;
		}

		int temp = cells[cur]->getNextStop(cur);
		//cout << "\n\tgot "<<temp<<" at "<<cur<<endl;
		if(temp == -1) return -1;
		return move(temp);
	}

public:
	Board(): occurs(40,0)
	{
		cur = 0;
		num_of_rolls = 0;
		double_chain = 0;
		for (int i = 0; i < 40; ++i)
			cells.push_back(new RegularCell(i, regular));
		cells[2] = new CC(2);
		cells[17] = new CC(17);
		cells[33] = new CC(33);
		cells[7] = new CH(7);
		cells[22] = new CH(22);
		cells[36] = new CH(36);
		delete cells[10];
		cells[10] = new RegularCell(10, jail);

		occurs[0]++;

	}

	int roll(){
		num_of_rolls++;

		int d1 = 1 + (rand()%die), d2 = 1 + (rand()%die);
		//cout << " ("<<d1<<", "<<d2<<") ";
		if(d1==d2)
			double_chain++;
		if(double_chain==3) {
			move(JAIL);
			double_chain = 0;
		}
		else{
			double_chain = 0;
			move((cur+d1+d2)%40);
		}
		occurs[cur]++;
		return cur;
	}

	void printOccur(){
		cout<<"\n--------------------------------------\n";
		for (int i = 0; i < occurs.size(); ++i) {
			cout<<i<<":\t"<<occurs[i]<<"/"<<num_of_rolls<<" = "
				<<100.0*occurs[i]/num_of_rolls<<"%"<<endl;
		}
		cout<<"--------------------------------------\n";
	}

	~Board(){
		for (int i = 0; i < cells.size(); ++i)
			delete cells[i];
	}

};



int main() {
	srand(time(NULL));
	Board b;
	for (int i = 0; i < 10000000; ++i) b.roll();
	b.printOccur();

	return 0;
}


















