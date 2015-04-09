package austin.aquaman_aardvark;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ListViewUtils.DealListAdapter;
import Objects.DealList;
import Objects.SortedData;


public class SortPopular extends Activity {

    private DealList dealList = new DealList();

    // Log tag
    private static final String TAG = Sort.class.getSimpleName();

    // Need json url?
    private ProgressDialog pDialog;
    private List<SortedData> dataList = new ArrayList<SortedData>();
    private ListView listView;
    //    private CustomListAdapter adapter;
    private DealListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sort_new);

        String url = "http://uaf58598.ddns.uark.edu/php_to_java/pulldeals.php";


        // ADDED FROM TUTORIAL
        final int numData = 10;

        listView = (ListView) findViewById(R.id.list);
//        adapter = new CustomListAdapter(this, dataList);
//        adapter = new DealListAdapter(this, dealList.getDeals());

        pDialog = new ProgressDialog(this);
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading. . .");
//        pDialog.show();
        DownloadTask dlTask = new DownloadTask();
        dlTask.execute(url);

//        dealList.deals.add(new Deal());
//        dealList.deals.add(new Deal());
//        dealList.deals.add(new Deal());
//        dealList.deals.add(new Deal());
    }


    private class DownloadTask extends AsyncTask<String, Void, String>
    {
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
            Log.d("URL Info", result);
            dealList.parseData(result);
            dealList.sortPopular();
            adapter = new DealListAdapter(SortPopular.this, dealList.getDeals());
            listView.setAdapter(adapter);

//            if (dealList.isEmpty()) {
//                finish();
//                startActivity(getIntent());
//            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sort_new, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
