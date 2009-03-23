#define PLCMCU

/**
 * This is the main hardare specification file. This file is unique to each PLC 
 * that is supported by plcedit.
 */

/* HARDWARE specification */
/* CLOCK specification */
#define OCCILATOR 10000000 //our occilator in seconds
#define TCYTIME 1 //now many cycles / instruction 4 for non PLL 1 for PLL 

#define TCYTICK OCCILATOR / TCYTIME //how long per instruction tick
#define MSTIME 1000 //milliseconds to seconds

#define MSTCY TCYTICK / MSTIME //how many clocks in a ms (10000 for pll)

//crystal select block
#if (MSTCY >= 10000)
	#define DEFDELAY(timevalms) Delay10KTCYx(timevalms * (char) (MSTCY/10000))
#endif
#if (MSTCY >= 1000 && MSTCY < 10000)
	#define DEFDELAY(timevalms) Delay1KTCYx(timevalms * (char) (MSTCY/1000))
#endif
#if (MSTCY < 1000)
	#error "Unsupported OCCILATOR defined by hardware manufacturer please ensure crystal is faster than 1Mhz"
#endif

/* End CLOCK specification */

/* BEGIN port specification */
//using default port names


/* Chip configuration section */
#pragma config OSC = HSPLL //set occilator to HS-PLL
#pragma config OSCS = OFF //disable occilator switch
#pragma config PWRT = OFF //enable power on timer
#pragma config WDT = OFF //disable watchdog timer
#pragma config LVP = OFF //disable low power programming


/* Public methods */
void delay_ms(int);


/* MUST IMPLEMENT THE FOLLOWING METHOD */
void program (void);

/* Private methods below DO NOT USE */
void init_chip(void); //private method to initialize chip to the 
void finalize(void); //private method desigened to release the chip
