void program(void) //user generated program example
{
	while(1)
	{
		PORTA = 0x00;
		delay_ms(1000);
		PORTA = 0xFF;
		delay_ms(1000);
	}
}

