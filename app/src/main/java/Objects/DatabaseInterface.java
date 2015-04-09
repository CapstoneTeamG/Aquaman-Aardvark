package Objects;

import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

// TODO DBIntAsyncTask/Deal bridge
public class DatabaseInterface  extends AsyncTask<String, Void, String>{

	ArrayList<String> records = new ArrayList<String>();
	ArrayList<Deal> deals = new ArrayList<Deal>();

    @Override
    protected String doInBackground(String... urls)
    {
        HttpResponse response = null;
        HttpGet httpGet = null;
        HttpClient mHttpClient = null;
        String s = "";

        try
        {
            if (mHttpClient == null)
                mHttpClient = new DefaultHttpClient();

            httpGet = new HttpGet(urls[0]);

            response = mHttpClient.execute(httpGet);
            s = EntityUtils.toString(response.getEntity(), "UTF-8");


        } catch (IOException ioe)
        {
            ioe.printStackTrace();
        }

        return s;
    }

    @Override
    protected void onPostExecute (String result)
    {
    }

	public static String getPageData(String url)
	{
		String pageData = null;
		
		try
		{
			pageData = new Scanner(new URL(url).openStream(), "UTF-8").useDelimiter("\\A").next();
		} catch (Exception e)
		{
			System.err.println("Problem getting data from " + url + " inside String DatabaseInterface::getPageData(String url)");
			e.printStackTrace();
		}
		
		return pageData;
	}
	
	private ArrayList<String> parseRecords (String pageData)
	{
//		ArrayList<String> stringRecordList = new ArrayList<String>();
		ArrayList<String> stringRecordList;
//		String[] stringRecords = pageData.split("<br>");
		
		stringRecordList = new ArrayList<String>(Arrays.asList(pageData.split("<br>")));
		
//		for (String s: stringRecords)
//			stringRecordList.add(s);
		
		return stringRecordList;
	}
	
	public static void main(String[] args) {
		
		DatabaseInterface jurl = new DatabaseInterface();
		ArrayList<Deal> deals = new ArrayList<Deal>();
		
		String out = null;
		try {
//			out = DatabaseInterface.getPageData("http://uaf58598.ddns.uark.edu/php_to_java/javatest1.php");
			out = DatabaseInterface.getPageData("http://uaf58598.ddns.uark.edu/php_to_java/emulateDB.html");
		}
		catch (Exception e)
		{
			System.err.println("Problem getting data from url inside DatabaseInterface::main(String[] args)");
			e.printStackTrace();
		}
		
		if (out != null)
		{
			deals = Deal.parseDeals(jurl.parseRecords(out));
			
			// Remove last element if null
			if (deals.get(deals.size()-1).getRank() == -1)
				deals.remove(deals.size()-1);
		}
		
		Collections.sort(deals);
		for (Deal deal: deals)
		{
			System.out.println(deal);
		}
		
	}

}
