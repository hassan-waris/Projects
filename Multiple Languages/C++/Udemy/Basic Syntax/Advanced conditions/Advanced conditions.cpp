#include <iostream>
using namespace std;

int main() {

	/*
	 * == equal
	 * < less than
	 * > greater than
	 * <= less than or equal
	 * >= greater than or equal
	 * != not equal
	 *
	 * && and
	 * || or
	 */

	int val1 = 7;
	int val2 = 10;

	if (val1 < 8 && val2 > 9) {
		cout << "And True!" << endl;
	} else {
		cout << "And False." << endl;
	}

	if (val1 == 7 || val2 > 11) {
		cout << "Or True!" << endl;
	} else {
		cout << "Or False." << endl;
	}

	if (val1 >= 7) {
		cout << ">= True!" << endl;
	} else {
		cout << ">= False." << endl;
	}

	bool cond1 = val1 < 8 && val2 > 9;
	bool cond2 = val2 != 88;

	if(cond1 || cond2) {
		cout << "Condition 1 is true" << endl;
	}
	else {
		cout << "Condition 1 is false" << endl;
	}

	return 0;
}
