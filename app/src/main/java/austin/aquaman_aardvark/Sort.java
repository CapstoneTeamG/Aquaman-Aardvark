package austin.aquaman_aardvark;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import ListViewUtils.AppController;
import ListViewUtils.CustomListAdapter;
import Objects.SortedData;

public class Sort extends Activity {

    // Log tag
    private static final String TAG = Sort.class.getSimpleName();

    // Need json url?
//    private static final String url = "http://what.com";
    private ProgressDialog pDialog;
    private List<SortedData> dataList = new ArrayList<SortedData>();
    private ListView listView;
    private CustomListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sort);

        // ADDED FROM TUTORIAL
        final int numData = 10;

        listView = (ListView) findViewById(R.id.list);
        adapter = new CustomListAdapter(this, dataList);
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
        for (int dataInstance = 0; dataInstance < numData; dataInstance++)
        {
            dataList.add(new SortedData(dataInstance));
        }
        Collections.sort(dataList);
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
