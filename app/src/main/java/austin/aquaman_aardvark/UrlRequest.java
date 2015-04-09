package austin.aquaman_aardvark;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;


public class UrlRequest extends Activity {

    private TextView contentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String url = "http://uaf58598.ddns.uark.edu/php_to_java/pulldeals.php";

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_url_request);

        contentView = (TextView) findViewById(R.id.urlTextView);
        DownloadTask dlTask = new DownloadTask();
        dlTask.execute(url);


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
        contentView.setText(result);
    }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_url_request, menu);
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
