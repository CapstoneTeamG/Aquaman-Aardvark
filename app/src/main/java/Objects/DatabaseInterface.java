package Objects;

import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import Objects.Date;
import Objects.Deal;

public class DatabaseInterface {

//	ArrayList<String> records = new ArrayList<String>();
	ArrayList<Deal> deals = new ArrayList<Deal>();

	public static String getPageData(String url_in)
	{
		String pageData = null;

        // HttpClient attempt
        InputStream contentStream = null;

        try{
            HttpClient httpclient = new DefaultHttpClient();
            HttpResponse response = httpclient.execute(new HttpGet(url_in));
            contentStream = response.getEntity().getContent();
        } catch(Exception e){
            Log.e("Aquaman-Aardvark", "exception", e);
//            e.printStackTrace();
        }
//        return contentStream;

//        public static String getStringFromUrl(String url)  {
        BufferedReader br = new BufferedReader(new InputStreamReader(contentStream));

        StringBuffer sb = new StringBuffer();

        try{
            String line = null;

            while ((line = br.readLine())!=null){
                sb.append(line);
            }
        }catch (IOException e){
            e.printStackTrace();
        }

        Log.e("pageDage", sb.toString());

        return sb.toString();
//    }

//		try
//		{
//			pageData = new Scanner(new URL(url).openStream(), "UTF-8").useDelimiter("\\A").next();
//            URL url = new URL(url_in);
//
//            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
//            pageData = in.readLine();
//            if (pageData == null)
//                Log.e("pageData", "NULL");
//            else
//                Log.e("pageData", pageData);
//            in.close();

//		}
//        catch (MalformedURLException e){
//		}
//        catch (IOException e){
//
//        }
//        catch (Exception e)
//		{
//			System.err.println("Problem getting data from " + url + " inside String DatabaseInterface::getPageData(String url)");
//			Log.e("Communication Error", "Problem getting data from url inside ArrayList<Deal> DatabaseInterface::requestDeals()");
//            Log.e("Aquaman-Aardvark", "exception", e);
//			e.printStackTrace();
//		}

//		return pageData;
	}

	private static ArrayList<String> parseRecords (String pageData)
	{
//		ArrayList<String> stringRecordList = new ArrayList<String>();
		ArrayList<String> stringRecordList;
//		String[] stringRecords = pageData.split("<br>");

        stringRecordList = new ArrayList (Arrays.asList(pageData.split("<br>")));

//        for (String s: stringRecords)
//			stringRecordList.add(s);

		return stringRecordList;
	}

//    public ArrayList<Deal> requestDeals()
//    {
//		String out = null;
//		try {
////			out = DatabaseInterface.getPageData("http://uaf58598.ddns.uark.edu/php_to_java/javatest1.php");
//			out = DatabaseInterface.getPageData("http://uaf58598.ddns.uark.edu/php_to_java/emulateDB.html");
//		}
//		catch (Exception e)
//		{
//			Log.e("Communication Error", "Problem getting data from url inside ArrayList<Deal> DatabaseInterface::requestDeals()");
//			e.printStackTrace();
//		}
//
//        if (out != null)
//        {
//			this.deals = Deal.parseDeals(parseRecords(out));
//
//			// Remove last element if null
//			if (deals.get(deals.size()-1).getRank() == -1)
//				deals.remove(deals.size()-1);
//        }
//
//		Collections.sort(deals);
//
//        return this.deals;
//    }

    public static ArrayList<Deal> requestDeals()
    {
		String out = null;
        ArrayList<Deal> deals = new ArrayList<Deal>();
		try {
//			out = DatabaseInterface.getPageData("http://uaf58598.ddns.uark.edu/php_to_java/javatest1.php");
			out = DatabaseInterface.getPageData("http://uaf58598.ddns.uark.edu/php_to_java/emulateDB.html");
		}
		catch (Exception e)
		{
//			System.err.println("Problem getting data from url inside ArrayList<Deal> DatabaseInterface::requestDeals()");
			Log.e("Communication Error","Problem getting data from url inside ArrayList<Deal> DatabaseInterface::requestDeals()");
			e.printStackTrace();
		}

        if (out != null)
        {
			deals = Deal.parseDeals(parseRecords(out));

			// Remove last element if null
			if (deals.get(deals.size()-1).getRank() == -1)
				deals.remove(deals.size()-1);
        }

		Collections.sort(deals);

        return deals;
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
