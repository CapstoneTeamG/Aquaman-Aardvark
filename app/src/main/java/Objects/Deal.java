package Objects;

import java.util.ArrayList;
import java.util.Collections;


public class Deal implements Comparable<Deal>{

	// Database
	int id;
	String user;
	int rank;
	Date start_date;
	Date exp_date;
	String description;
	// Need in database
	int used;
	int complaints;
	int user_id;
	String retailer;
	int retailer_id;
	boolean valid;
	
	// Meta
//	boolean valid = false; //Should be in database
	
	public Deal (int id_in, String user_in, int rank_in, Date start_date_in, Date exp_date_in, String description_in)
	{
		// Database
		this.id = id_in;
		this.user = user_in;
		this.rank = rank_in;
		this.start_date = start_date_in;
		this.exp_date = exp_date_in;
		this.description = description_in;
		
		// Need in database
		this.used = 0;
		
		// Meta
		this.valid = true;
	}
	
	public Deal (String record)
	{
		// Indices for the array after the String.split
		final int ID = 0;
		final int USER = 1;
		final int RANK = 2;
		final int START_DATE = 3;
		final int EXP_DATE = 4;
		final int DESCRIPTION = 5;
		
		// Chances are likely that this deal is invalid
		if (record.charAt(0) != '{' || record.charAt(record.length() - 1) != '}')
		{
			this.id = -1;
			this.user = null;
			this.rank = -1;
			this.start_date = null;
			this.exp_date = null;
			this.description = null;
			
			// Need in database
			this.used = 0;
			
			// this.valid remains false
		}
		else
		{
			String trimmedRecord = record.substring(1, record.length() - 1);
			String[] instanceVariables = trimmedRecord.split(",");
			
			this.id = Integer.parseInt(instanceVariables[ID]);
			this.user = instanceVariables[USER];
			this.rank = Integer.parseInt(instanceVariables[RANK]);
			this.start_date = new Date(instanceVariables[START_DATE]);
			this.exp_date = new Date(instanceVariables[EXP_DATE]);
			this.description = instanceVariables[DESCRIPTION];
			
			// Need in database
			this.used = 0;
				
			this.valid = true;
		}
		
		
		
			
	}
	
	// Getters, setters
	public int getId() {
		return id;
	}
	
	public String getUser() {
		return user;
	}
	
	public int getRank() {
		return rank;
	}
	
	public Date getStart_Date()
	{
		return this.start_date;
	}
	
	public Date getExp_Date()
	{
		return this.exp_date;
	}
	
	public String getDescription() {
		return description;
	}
	
	public int getUsed() {
		return used;
	}
	
	public int getComplaints() {
		return complaints;
	}
	
	public int getUser_id() {
		return user_id;
	}
	
	public String getRetailer() {
		return retailer;
	}
	
	public int getRetailer_id() {
		return retailer_id;
	}
	
	public boolean isValid() {
		return valid;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setUser(String user) {
		this.user = user;
	}
	
	public void setRank(int rank) {
		this.rank = rank;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setUsed(int used) {
		this.used = used;
	}
	
	public void setComplaints(int complaints) {
		this.complaints = complaints;
	}
	
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
	public void setRetailer(String retailer) {
		this.retailer = retailer;
	}
	
	public void setRetailer_id(int retailer_id) {
		this.retailer_id = retailer_id;
	}
	
	public void setValid(boolean valid) {
		this.valid = valid;
	}

	// Functionality
	
	public static ArrayList<Deal> parseDeals (ArrayList<String> records)
	{
		ArrayList<Deal> deals = new ArrayList<Deal>();
		
		for (String record : records)
		{
			deals.add(new Deal(record));
		}
		
		return deals;
	}
	
	// Ovverrides
	
	@Override
	public int compareTo(Deal otherDeal)
	{
		int difference = 0;
		
		// Default, sort by used
		int thisCompareValue = this.used;
		int otherCompareValue = otherDeal.used;
		// Descending
		difference = otherCompareValue - thisCompareValue;
		
		// If used is the same, sort by start_date
		if (difference == 0)
		{
			difference = compareDate(this.getStart_Date(), otherDeal.getStart_Date());
		}
		
		// If start_date is the same, sort by exp_date
		if (difference == 0)
		{
			difference = compareDate(this.getExp_Date(), otherDeal.getExp_Date());
		}
		
		// If exp_date is the same, sort by rank
		if (difference == 0)
		{
			// Descending
			difference = otherDeal.getRank() - this.getRank();
		}
		
		return difference;
	}
	
	// Returns an int (to compareTo() method)
	// Used to sort Deals by start_date, ascending order
	public int compareDate (Date thisDate, Date otherDate)
	{
		int difference = 0;
		
		// Default, sort by year
		int thisCompareValue = thisDate.getYear();
		int otherCompareValue = otherDate.getYear();
		// Descending
		difference = otherCompareValue - thisCompareValue;
		
		// Break ties to month
		if (difference == 0)
		{
			// sort by month
			thisCompareValue = thisDate.getMonth();
			otherCompareValue = otherDate.getMonth();
			// Descending
			difference = otherCompareValue - thisCompareValue;
		}
		
		// Break final ties to day
		if (difference == 0)
		{
			// sort by month
			thisCompareValue = thisDate.getDay();
			otherCompareValue = otherDate.getDay();
			// Descending
			difference = otherCompareValue - thisCompareValue;
		}
		
		return difference;
	}
	
	@Override
	public String toString ()
	{
		String dealString = "";
		
		dealString += "[" + this.id + "]";
		dealString += "\t[" + this.user + "]";
		dealString += "\t[" + this.rank + "]";
		dealString += "\t[" + this.start_date + "]";
		dealString += "\t[" + this.exp_date + "]";
		dealString += "\t[" + this.description + "]";
		
		return dealString;
	}
	
	
	// Driver
	public static void main(String[] args) {
		
		ArrayList<Deal> deals = new ArrayList<Deal>();
		
		deals.add(new Deal(8, "Joey", 6, new Date(9, 3, 2015), new Date(11, 3, 2015), "This is a Deal"));
		deals.add(new Deal(0, "Joe", 2, new Date(1, 1, 1990), new Date(5, 1, 1990), "This is a Deal"));
		deals.add(new Deal(2, "Joe", 7, new Date(1, 2, 2015), new Date(5, 2, 2015), "This is a Deal"));
		deals.add(new Deal(9, "Jim", 4, new Date(10, 3, 2015), new Date(11, 3, 2015), "This is a Deal"));
		deals.add(new Deal(6, "Jack", 0, new Date(6, 3, 2015), new Date(7, 3, 2015), "This is a Deal"));
		deals.add(new Deal(1, "Jenny", 3, new Date(1, 1, 2015), new Date(5, 1, 2015), "This is a Deal"));
		deals.add(new Deal(5, "Bob", 1, new Date(1, 3, 2015), new Date(5, 3, 2015), "This is a Deal"));
		deals.add(new Deal(4, "Bill", 2, new Date(1, 3, 2015), new Date(5, 3, 2015), "This is a Deal"));
		deals.add(new Deal(3, "Joe", 2, new Date(1, 3, 2015), new Date(5, 3, 2015), "This is a Deal"));
		deals.add(new Deal(7, "Jill", 9, new Date(8, 3, 2015), new Date(8, 3, 2015), "This is a Deal"));
		deals.add(new Deal(10, "Jill", 9, new Date(8, 3, 2014), new Date(8, 3, 2015), "This is a Deal"));
		
		Collections.sort(deals);
		
		for (Deal deal : deals)
			System.out.println(deal);
			
//		public Deal (int id_in, String user_in, int rank_in, Date start_date_in, Date exp_date_in, String description_in)
	}

}
