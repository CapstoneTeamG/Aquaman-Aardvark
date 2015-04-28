package austin.aquaman_aardvark;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

import ListViewUtils.DealListAdapter;
import Objects.Deal;


public class DealActivity extends ActionBarActivity {

    Deal deal = null;
    TextView used;
    TextView complaints;

    boolean updated = false;

    String updateUsedURL = "http://uaf58598.ddns.uark.edu/java_to_php/updateused.php?id=";
    String updateComplaintsURL = "http://uaf58598.ddns.uark.edu/java_to_php/updatecomplaints.php?id=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deal);

        Intent intent = getIntent();

        deal = (Deal)intent.getSerializableExtra("Deal");


        String message = "null";
        if (deal != null)
        {
            message = deal.getDescription();
            message += " at ";
            message += deal.getRetailer();

        }


//        TextView textView = new TextView(this);
//        textView.setTextSize(20);
//        textView.setText(message);
        TextView retailer = (TextView) findViewById(R.id.retailer);
        TextView description = (TextView) findViewById(R.id.description);
        TextView address = (TextView) findViewById(R.id.address);
        TextView start_date = (TextView) findViewById(R.id.start_date);
        TextView exp_date = (TextView) findViewById(R.id.exp_date);
        this.used = (TextView) findViewById(R.id.used);
        this.complaints = (TextView) findViewById(R.id.complaints);

        retailer.setText(this.deal.getRetailer());
        description.setText(this.deal.getDescription());
        address.setText(this.deal.getAddress());
        this.used.setText(Integer.toString(this.deal.getUsed()));
        this.complaints.setText(Integer.toString(this.deal.getComplaints()));
        start_date.setText(this.deal.getStart_Date().toString());
        exp_date.setText(this.deal.getExp_Date().toString());



//        setContentView(retailer);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_deal, menu);
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

    public void gotUsed(View view)
    {
        if (!this.updated) {
            this.deal.gotUsed();
            String url = this.updateUsedURL + this.deal.getId();


            UpdateTask updateTask = new UpdateTask();
            updateTask.execute(url);

            this.used.setText(Integer.toString(this.deal.getUsed()));

            this.updated = true;
        }
    }

    public void gotComplaint(View view)
    {
        if (!this.updated) {
            this.deal.gotComplaint();
            String url = this.updateComplaintsURL + this.deal.getId();


            UpdateTask updateTask = new UpdateTask();
            updateTask.execute(url);

            this.complaints.setText(Integer.toString(this.deal.getComplaints()));

            this.updated = true;
        }
    }

    public void goToMap(View view)
    {
        String map  = "http://maps.google.co.in/maps?q=" + this.deal.getAddress();

        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(map));
        startActivity(i);
    }

    private class UpdateTask extends AsyncTask<String, Void, String>
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

        }
    }
}
