#include <iostream>
#include <vector>
#include "BarGraph.h"
#include "SandCore.h"
#include <fstream>

using namespace std;
int main(int argc, char *argv[]) {

	SandCore SandCoreObj;
	SandCoreObj.runProgram();
	SandCoreObj.reRun();
	SandCoreObj.saveFile();

}