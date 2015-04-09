package Objects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

/**
 * Created by austin on 3/28/15.
 */
public class DealList {

//    public ArrayList<Deal> deals;
    ArrayList<Deal> deals;

    public DealList()
    {
        this.deals = new ArrayList<Deal>();
    }

    // GETTERS AND SETTERS
    public ArrayList<Deal> getDeals()
    {
        return this.deals;
    }


    // OTHER INFO
    public boolean isEmpty ()
    {
        return (this.deals.size() < 1);
    }

    // PARSING
    public void parseData(String pageData)
    {
        //		ArrayList<String> stringRecordList = new ArrayList<String>();
        ArrayList<String> stringRecordList;
//		String[] stringRecords = pageData.split("<br>");

        stringRecordList = new ArrayList<String>(Arrays.asList(pageData.split("<br>")));

//		for (String s: stringRecords)
//			stringRecordList.add(s);


        deals = new ArrayList<Deal>();

        // TODO: REMOVE RANDOM STUFF
        Random rand = new Random();
        for (String record : stringRecordList)
        {
            Deal addedDeal = new Deal(record);
            addedDeal.setUsed(rand.nextInt(50));
            addedDeal.setComplaints(rand.nextInt(15));

            deals.add(addedDeal);
        }
    }

    // SORTING
    public void sortPopular()
    {
        Deal.setSortCriteria(Deal.SORT_POPULAR);

        Collections.sort(deals);
    }

    public void sortNew()
    {
        Deal.setSortCriteria(Deal.SORT_NEW);

        Collections.sort(deals);
    }

    public void sortProximity()
    {
        Deal.setSortCriteria(Deal.SORT_PROXIMITY);

        Collections.sort(deals);
    }
}
