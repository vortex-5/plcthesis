void program(void) //user generated program example normally this is the output from the plcedit program
{
	while(1)
	{
		PORTA = 0x00;
		delay_ms(1000);
		PORTA = 0xFF;
		delay_ms(1000);
	}
}

