package austin.aquaman_aardvark;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;

import ListViewUtils.CustomListAdapter;
import ListViewUtils.DealListAdapter;
import Objects.Date;
import Objects.Deal;
import Objects.SortedData;

public class Sort extends Activity {

    // Log tag
    private static final String TAG = Sort.class.getSimpleName();

    // Need json url?
//    private static final String url = "http://what.com";
    private ProgressDialog pDialog;
    private List<SortedData> dataList = new ArrayList<SortedData>();
    private ArrayList<Deal> deals = new ArrayList<Deal>();
    private ListView listView;
//    private CustomListAdapter adapter;
    private DealListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sort);

        // Database Stuff
//        DownloadDealsTask ddTask = new DownloadDealsTask();
//        ddTask.execute("http://uaf58598.ddns.uark.edu/php_to_java/emulateDB.html");

        listView = (ListView) findViewById(R.id.list);
//        adapter = new CustomListAdapter(this, dataList);
        adapter = new DealListAdapter(this, deals);
        listView.setAdapter(adapter);

        pDialog = new ProgressDialog(this);
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading. . .");
//        pDialog.show();

        // changing action bar color
        try
        {
            getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#1b1b1b")));
        }
        catch (Exception e)
        {

        }

        // JSON?
        // Creating volley request obj
//        JsonArrayRequest dataReq = new JsonArrayRequest(url, new Response.Listener<JSONArray>())
//        {
//            @Override
//            public void onResponse(JSONArray response)
//            {
//                Log.d(TAG, response.toString());
//                hidePDialog();
//            }
//
//            // Parsing json
//            for (int i = 0; i < response.length(); i++)
//            {
//                try
//                {
//                    //Need to do all of this. . .
//
//                }
//            }
//        }



//        for (int dataInstance = 0; dataInstance < numData; dataInstance++)
//        {
//            dataList.add(new SortedData(dataInstance));
//        }
        deals.add(new Deal(0, "Joe", 10, new Date(5, 3, 2014), new Date(10, 3, 2014), "Half price tacos!"));
        deals.add(new Deal(1, "Jen", 1, new Date(4, 2, 2014), new Date(10, 2, 2014), "BOGO Free: Burgers!"));
        deals.add(new Deal(2, "Jack", 7, new Date(15, 1, 2014), new Date(10, 2, 2014), "Free drink!"));
        deals.add(new Deal(3, "Jan", 2, new Date(2, 3, 2014), new Date(5, 3, 2014), "10% off meal!"));
        deals.add(new Deal(4, "Jay", 6, new Date(3, 3, 2014), new Date(8, 3, 2014), "Free appetizer!"));
        deals.add(new Deal(5, "Jace", 6, new Date(20, 4, 2014), new Date(25, 4, 2014), "BOGO froyo free!"));
        deals.add(new Deal(6, "Jake", 1, new Date(1, 3, 2014), new Date(2, 3, 2014), "Half price burgers!"));
        deals.add(new Deal(7, "Jill", 8, new Date(1, 3, 2014), new Date(4, 3, 2014), "Extra french fries!"));
        deals.add(new Deal(8, "Jud", 9, new Date(2, 2, 2014), new Date(2, 3, 2014), "Free milkshake!"));
        deals.add(new Deal(9, "Jude", 2, new Date(5, 3, 2014), new Date(11, 3, 2014), "15% all appetizers!"));
        deals.add(new Deal(10, "Jorge", 4, new Date(7, 3, 2014), new Date(20, 3, 2014), "Extra side-item!"));

        Random rand = new Random();
        for (Deal deal : deals)
        {
            deal.setUsed(rand.nextInt(50));
        }
        Collections.sort(deals);



//        AppController.getInstance().addToRequestQueue(dataReq);
    }

    @Override
    public void onDestroy ()
    {
        super.onDestroy();
        hidePDialog();
    }

    private void hidePDialog()
    {
        if (pDialog != null)
        {
            pDialog.dismiss();
            pDialog = null;
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sort, menu);
        return true;
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }


    }