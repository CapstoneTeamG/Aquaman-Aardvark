package Objects;

import java.io.Serializable;

public class Date implements Serializable{

	int day;
	int month;
	int year;
	
	boolean isLeapYear = false;
	
	// Constants
	
	// Months
	final static int JANUARY = 1;
	final static int FEBRUARY = 2;
	final static int MARCH = 3;
	final static int APRIL = 4;
	final static int MAY = 5;
	final static int JUNE = 6;
	final static int JULY = 7;
	final static int AUGUST = 8;
	final static int SEPTEMBER = 9;
	final static int OCTOBER = 10;
	final static int NOVEMBER = 11;
	final static int DECEMBER = 12;
	
	// Days in Months
	final static int numDays[] = new int[] {31,  //Jan
											28,  //Feb
											31,  //Mar
											30,  //Apr
											31,  //May
											30,  //Jun
											31,  //Jul
											31,  //Aug
											30,  //Sep
											31,  //Oct
											30,  //Nov
											31}; //Dec
	
	// Constructors
	public Date(int day_in, int month_in, int year_in)
	{
		this.day = day_in;
		this.month = month_in;
		this.year = year_in;
	}
	
	public Date(String stringDate)
	{
		final int YEAR = 0;
		final int MONTH = 1;
		final int DAY = 2;
		
		String[] instanceVariables = stringDate.split("-");
		
		this.year = Integer.parseInt(instanceVariables[YEAR]);
		this.month = Integer.parseInt(instanceVariables[MONTH]);
		this.day = Integer.parseInt(instanceVariables[DAY]);
	}
	
	// Getters, Setters
	public int getDay() {
		return day;
	}
	
	public void setDay(int day_in) {
		this.day = day_in;
	}
	
	public int getMonth() {
		return month;
	}
	
	public void setMonth(int month_in) {
		this.month = month_in;
	}
	
	public int getYear() {
		return year;
	}
	
	public void setYear(int year_in) {
		this.year = year_in;
	}
	
	// Functionality
	public void addDay()
	{
		
	}
	
	
	// Overrides
	
	@Override
	public String toString()
	{
		return (this.year + "-" + this.month + "-" + this.day);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
