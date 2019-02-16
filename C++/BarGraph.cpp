//
// BarGraph.cpp
// Created by Hassan Waris on 21/11/2016.
#include <iostream>
#include <fstream>
#include <iomanip>
#include "BarGraph.h"
#include <string>

using namespace std;


BarGraph::BarGraph() {

}


int myArray[16] = { 2, 6, 3, 5, 2, 8, 1, 4, 2, 2, 5, 3, 5, 7, 4, 1 };
vector<int> myVector(myArray, myArray + sizeof(myArray) / sizeof(int));

// this method reads iput from console to datastructure
void BarGraph::readInput() {

	int value = 0;
	int loop = 0;

	cout << "please enter how many bars are needed for this graph? : " << endl;
	cin >> loop;

	for (int i = 0; i < loop; i++)
	{
		cout << "please enter the value for bar  " << i + 1 << endl;
		cin >> value;

		Bar newBar;
		newBar.setBarLevel(value);
		barGraphData.push_back(newBar);
	}

}




void BarGraph::display(ostream& out) {				//ostream& was used as a refrence to output object

	out << endl;

	for (int c = 0; c < barGraphData.size(); c++)
	{
		if (c < 9) {
			out << "bar  " << c + 1;
			for (int x = 0; x < barGraphData[c].getBarLength(); x++)
			{

				out << "|";

			}
		}
		else {
			out << "bar " << c + 1;
			for (int x = 0; x < barGraphData[c].getBarLength(); x++)
			{

				out << "|";

			}

		}



		for (int i = 1; i <= barGraphData[c].getSandLength(); i++)
		{
			out << ":";
		}
		out << endl;
	}

}



void BarGraph::randomGen() {

	cout << setfill('|');

	int bars = 0;
	cout << "how many bars would you like to randomly generate?: " << endl;
	cin >> bars;
	srand((unsigned)time(0));

	for (int i = 0; i < bars; i++) {
		int random = (rand() % 100) + 1;

		Bar newBar;
		newBar.setBarLevel(random);
		barGraphData.push_back(newBar);

	}


	cout << endl;


}



void BarGraph::inputTextFile() {

	barGraphData.resize(0);
	cout << "Using a text file to make bar graph" << endl;

	string textFile = "BarGraphInput.txt";
	{

		ifstream outputFileStream;
		outputFileStream.open("BarGraphInput.txt");
		while (!outputFileStream.eof()) {
			int a;
			outputFileStream >> a;


			Bar newBar;
			newBar.setBarLevel(a);
			barGraphData.push_back(newBar);

			cout << a << " ";
		}
		outputFileStream.close();

		cout << endl;

	}

	cout << endl;
}


void BarGraph::calculateSand() {

	cout << endl;

	cout << "sand values are: ";

	for (int i = 0; i < barGraphData.size(); i++) {
		barGraphData[i].calcSandOnTop();

		cout << barGraphData[i].getSandLength() << " ";

	}

}
void BarGraph::calculateHighestLeft() {

	// 1. loop through vector
	// 2. for each bar we want to compare the highest so far with the current value for bar height
	// 3. if bar height is greater then highest so far then store highest value in highest on left

	int highestValueSoFar = 0;

	for (int i = 0; i < barGraphData.size(); i++) {

		if (barGraphData[i].getBarLength() > highestValueSoFar) {
			highestValueSoFar = barGraphData[i].getBarLength();
		}
		barGraphData[i].setHighestLeft(highestValueSoFar);

		cout << barGraphData[i].getHighestLeft() << " ";
	}
	cout << endl;
}

void BarGraph::calculateHighestRight() {

	// 1. loop through vector
	// 2. for each bar we want to compare the highest so far with the current value for bar height
	// 3. if bar height is greater then highest so far then store highest value in highest on right

	int highestValueSoFarRight = 0;

	for (int i = barGraphData.size() - 1; i >= 0; i--) {

		if (barGraphData[i].getBarLength() > highestValueSoFarRight) {
			highestValueSoFarRight = barGraphData[i].getBarLength();
		}
		barGraphData[i].setHighestRight(highestValueSoFarRight);

		cout << barGraphData[i].getHighestRight() << " ";
	}

}


