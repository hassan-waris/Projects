//
// SandCore.cpp
// Created by Hassan Waris on 02/12/2016.

#include "SandCore.h"
#include "BarGraph.h"

#include <iostream>
#include <vector>
#include <string>
#include <ostream>
#include <fstream>
#include <iomanip>

SandCore SandCoreObj;
std::vector<Bar> barGraphData;


using namespace std;

SandCore::SandCore() {
	
}

void SandCore::titles(){
	
	cout << "Hassan Waris" << "  " << "5774302"<< endl;
	cout << "This Program Calculates The Sand To Bar Ratio, Which Is Determined By The Bars On The Left And Right."<< endl;
	
}

int SandCore::option(){
	cout << "how would you like to create the graph, please press: " << endl;
	cout << endl; 
	cout << "1 - for randomly generated number graph"<< endl;
	cout << "2 - to enter all the values yourself"<< endl;
	cout << "3 - to read from a text file"<< endl;

	return 0;
}

void SandCore::inputMeth(){
	
	int i = 0;
	cin >> i;
		
		if (i == 1) {
			myBarGraph.randomGen();
		} else if (i == 2) {
			myBarGraph.readInput();
		} else if (i == 3) {
			myBarGraph.inputTextFile();
		}else if (i > 3) {
			cout << "invalid number enter please enter values between 1 - 3 "<< endl;
		}
		barGraphData.resize(0);
}


void SandCore::calcSand(){
	
	myBarGraph.calculateHighestLeft();
	myBarGraph.calculateHighestRight();
	myBarGraph.calculateSand();
}

void SandCore::display(){
	
	myBarGraph.display(cout);
}

void SandCore::outputFile(){
	ofstream ofs;
	ofs.open("output.txt");
	myBarGraph.display(ofs);
	ofs.close();
}

void SandCore::runProgram(){
	SandCoreObj.titles();
	SandCoreObj.option();
	SandCoreObj.inputMeth();
	SandCoreObj.calcSand();
	SandCoreObj.display();
//	SandCoreObj.outputFile();
}


void SandCore::reRun(){
		
	cout<< "Would you like to repeat the Program? 'y' or 'n'"<< " "<<  endl; 
	char repeat = 'y'; //repeat is the variable which stores y or n in order to determine 

	while (repeat == 'y' || repeat == 'Y')
	{
	cin>> repeat;
	if (repeat == 'y')
	 {
		
		cout<< endl;
		SandCore reload;
		reload.runProgram();
//		reload.reRun();

	 } else if (repeat =='n') {
	 cout<< "Thank you for using the sand caculations program. "<< endl; 
		}
	} 
}
	
void SandCore::saveFile(){
	cout << "would you like to output the graph results to a text file? 'y' or 'n'"<< " "<< endl;
	
	char save ='y';
		
	while (save == 'y' || save == 'Y') {
	
	cin >> save;
	
	if (save == 'y'){
		SandCoreObj.outputFile();
		cout << "file saved, thank you for using this Sand Calculator" << endl;
		
	}else if (save == 'n'){
		cout << "file has not been saved, thank you for using this Sand Calculator" << endl;
	}
	}
}