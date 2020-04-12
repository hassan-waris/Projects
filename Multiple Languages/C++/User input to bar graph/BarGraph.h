//
// BarGraph.h
// Created by Hassan Waris on 21/11/2016.

#ifndef _BarGraph_h_
#define _BarGraph_h_
#include <vector>
#include "Bar.h"
#include <string>
#include <ostream>

using namespace std;


class BarGraph {
private:
	std::vector<Bar> barGraphData;

public:
	BarGraph();
	void display(ostream& out);
	void readInput();
	void randomGen();
	void inputTextFile();
	void calculateSand();
	void calculateHighestLeft();
	void calculateHighestRight();
	void displaySand();
};

#endif

