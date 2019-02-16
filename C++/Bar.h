//
// Bar.h
// Created by Hassan Waris on 21/11/2016.

#ifndef _Bar_h_
#define _Bar_h_

class Bar {
private:
	int barLength;
	int highestValueLeft;
	int highestValueRight;
	int sandLength;

public:
	Bar();
	int getSandLength();
	int setBarLevel();
	int newbar();
	void setBarLevel(int);
	int getBarLength();
	void setHighestLeft(int);
	int getHighestLeft();
	void setHighestRight(int);
	int getHighestRight();
	void calcSandOnTop();

};

#endif