#include <p18f452.h>
#include <delays.h>

#include "plcmcu.h"
#include "program.c"

 //initializes the chips and sets configuration bits to what they should be
void init_chip(void)
{	
	TRISA = 0x00; //set all portA to output
	TRISB = 0xFF; //set all portB to input
}

// Delays the program time in ms
void delay_ms(int time)
{
	DEFDELAY(time);
}


// Performs any final operations for a safe system shutdown
void finalize(void)
{
	//stub
}

//test routine no real code has been done at this point other than a blinking led
void main(void)
{
	init_chip();	
	program(); //run the userland program
	finalize(); //run any final methods for this device once program is completed
	//rarely is finalize ever used since programs are designed to run forever	
}



