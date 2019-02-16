//
// SandCore.h
// Created by Hassan Waris on 02/12/2016.

#ifndef _SandCore_h_
#define _SandCore_h_
#include <vector>
#include <string>
#include <ostream>
#include "BarGraph.h"

class SandCore {
private:
	BarGraph myBarGraph;

public:
	SandCore();
	void inputMeth();
	void titles();
	int option();
	void calcSand();
	void display();
	void outputFile();
	void runProgram();
	void reRun();
	void Reset();
	void saveFile();
};

#endif