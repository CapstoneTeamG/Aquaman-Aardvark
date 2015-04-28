package Objects;

import java.util.ArrayList;
import java.util.Collections;
import java.io.Serializable;


public class Deal implements Comparable<Deal>, Serializable{

	// Database
	int id;
	String user;
	String retailer;
    String address;
	String description;
	Date start_date;
	Date exp_date;
	int used;
	int complaints;
	int rank;
	int user_id;
	int retailer_id;
	boolean valid;
    int user_rank;


    // finals
    public static final int SORT_POPULAR = 0;
    public static final int SORT_NEW = 1;
    public static final int SORT_PROXIMITY = 2;
    public static final int BUSINESS_RANK = 11;

    // Functionality
    static int sortCritera = Deal.SORT_POPULAR;

	// Meta
//	boolean valid = false; //Should be in database
//    public Deal ()
//    {
//			this.id = -1;
//			this.user = null;
//			this.rank = -1;
//			this.start_date = null;
//			this.exp_date = null;
//			this.description = null;
//			this.used = 0;
//
//            //Debug?
//            this.retailer = "Retailer";
//
//			// this.valid remains false
//    }

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
		final int RETAILER = 2;
		final int ADDRESS = 3;
        final int DESCRIPTION = 4;
		final int START_DATE = 5;
		final int EXP_DATE = 6;
		final int USED = 7;
		final int COMPLAINTS = 8;
        final int USER_RANK = 9;

		// Chances are likely that this deal is invalid
		if (record.charAt(0) != '{' || record.charAt(record.length() - 1) != '}')
		{
			this.id = -1;
			this.user = null;
			this.rank = -1;
			this.start_date = null;
			this.exp_date = null;
			this.description = null;
            this.user_rank = -1;

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
            this.retailer = instanceVariables[RETAILER];
            this.address = instanceVariables[ADDRESS];
            this.description = instanceVariables[DESCRIPTION];
            this.start_date = new Date(instanceVariables[START_DATE]);
            this.exp_date = new Date(instanceVariables[EXP_DATE]);
            this.used = Integer.parseInt(instanceVariables[USED]);
            this.complaints = Integer.parseInt(instanceVariables[COMPLAINTS]);
            this.user_rank = Integer.parseInt(instanceVariables[USER_RANK]);
			
			// Need in database
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

    public int getUser_rank() {return this.user_rank;}
	
	public String getRetailer() {
		return retailer;
	}

    public String getAddress() { return address; }
	
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

	public void setAddress(String address) { this.address = address; }

	public void setRetailer_id(int retailer_id) {
		this.retailer_id = retailer_id;
	}
	
	public void setValid(boolean valid) {
		this.valid = valid;
	}

    public void gotUsed()
    {
        this.used++;
    }

    public void gotComplaint()
    {
        this.complaints++;
    }

    public boolean isBusinessDeal() {return (this.user_rank == BUSINESS_RANK);}

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

        if (Deal.sortCritera == Deal.SORT_POPULAR) {

            int thisCompareValue = 0;
            int otherCompareValue = 0;

            // First, sort by business posted deals
            if (this.isBusinessDeal() && !otherDeal.isBusinessDeal())
            {
                    difference = -1;
            }

            if (!this.isBusinessDeal() && otherDeal.isBusinessDeal())
            {
                difference = 1;
            }

            // Default, sort by used
            if (difference == 0) {
                thisCompareValue = this.used;
                otherCompareValue = otherDeal.used;
                // Descending
                difference = otherCompareValue - thisCompareValue;
            }

            // If used is the same, sort by complaints
            if (difference == 0) {
                thisCompareValue = this.complaints;
                otherCompareValue = otherDeal.complaints;

                // Ascending
                difference = thisCompareValue - otherCompareValue;
            }

            // If complaints is the same, sort by start_date
            if (difference == 0) {
                difference = compareDate(this.getStart_Date(), otherDeal.getStart_Date());
            }

            // If start_date is the same, sort by exp_date
            if (difference == 0) {
                difference = compareDate(this.getExp_Date(), otherDeal.getExp_Date());
            }

            // If exp_date is the same, sort by rank
            if (difference == 0) {
                thisCompareValue = this.getRank();
                otherCompareValue = otherDeal.getRank();

                // Descending
                difference = otherCompareValue - thisCompareValue;
            }

        }
        else if (Deal.sortCritera == Deal.SORT_NEW)
        {
            int thisCompareValue = 0;
            int otherCompareValue = 0;

            if (this.isBusinessDeal() && !otherDeal.isBusinessDeal())
            {
                    difference = -1;
            }

            if (!this.isBusinessDeal() && otherDeal.isBusinessDeal())
            {
                difference = 1;
            }

            // Default, sort by start_date
            if (difference == 0) {
                difference = compareDate(this.getStart_Date(), otherDeal.getStart_Date());
            }

            // If start_date is the same, sort by used
            if (difference == 0) {
            thisCompareValue = this.used;
             otherCompareValue = otherDeal.used;

                // Descending
                difference = otherCompareValue - thisCompareValue;
            }

            // If used is the same, sort by complaints
            if (difference == 0) {
                thisCompareValue = this.complaints;
                otherCompareValue = otherDeal.complaints;

                // Ascending
                difference = thisCompareValue - otherCompareValue;
            }

            // If complaints is the same, sort by exp_date
            if (difference == 0) {
                difference = compareDate(this.getExp_Date(), otherDeal.getExp_Date());
            }

            // If exp_date is the same, sort by rank
            if (difference == 0) {
                // Descending
                difference = otherDeal.getRank() - this.getRank();
            }

        }
        else if (Deal.sortCritera == Deal.SORT_PROXIMITY)
        {

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

	public static void setSortCriteria(int sortCritera_in)
    {
        if (sortCritera_in == Deal.SORT_POPULAR)
        {
            Deal.sortCritera = Deal.SORT_POPULAR;
        }
        if (sortCritera_in == Deal.SORT_NEW)
        {
            Deal.sortCritera = Deal.SORT_NEW;
        }
        if (sortCritera_in == Deal.SORT_PROXIMITY)
        {
            Deal.sortCritera = Deal.SORT_PROXIMITY;
        }
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
