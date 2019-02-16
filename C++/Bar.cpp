//
// Bar.cpp
// Created by Hassan Waris on 21/11/2016.

#include "Bar.h"

Bar::Bar() {
	highestValueLeft = 0;
}

void Bar::setBarLevel(int newBarLevel) {

	barLength = newBarLevel;

}

int Bar::getBarLength() {
	return barLength;
}

void Bar::setHighestLeft(int newValue) {
	highestValueLeft = newValue;
}

int Bar::getHighestLeft() {
	return highestValueLeft;
}

void Bar::setHighestRight(int newValueRight) {
	highestValueRight = newValueRight;
}

int Bar::getHighestRight() {
	return highestValueRight;
}

int Bar::getSandLength() {
	return sandLength;
}

void Bar::calcSandOnTop() {
	if (highestValueLeft < highestValueRight) {
		sandLength = highestValueLeft - barLength;
	}
	else {
		sandLength = highestValueRight - barLength;
	}
}