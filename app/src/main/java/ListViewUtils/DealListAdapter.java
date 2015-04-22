package ListViewUtils;
//import austin.aquaman_aardvark.SortedData;

/**
 * Created by macbook on 2/12/15.
 */


//import Statements
import java.util.List;

import Objects.Deal;
import Objects.SortedData;
import austin.aquaman_aardvark.R;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import org.w3c.dom.Text;


public class DealListAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
//    private List<SortedData> dataList;
    private List<Deal> deals;
//    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public DealListAdapter(Activity activity, List<Deal> data_in){
        this.activity = activity;
        this.deals = data_in;
    }

    @Override
    public int getCount() {
        return deals.size();
    }

    @Override
    public Object getItem(int location) {
        return deals.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_row, null);
        // For pictures
//        if (imageLoader == null)
//            imageLoader = AppController.getInstance().getImageLoader();
//        NetworkImageView thumbNail = (NetworkImageView) convertView.findViewById(R.id.thumbnail);

        TextView thumbNail = (TextView) convertView.findViewById(R.id.thumbnail);
        TextView description = (TextView) convertView.findViewById(R.id.description);
        TextView address = (TextView) convertView.findViewById(R.id.address);
        TextView retailer = (TextView) convertView.findViewById(R.id.retailer);
        TextView used = (TextView) convertView.findViewById(R.id.used);
        TextView complaints = (TextView) convertView.findViewById(R.id.complaints);
        TextView start_date = (TextView) convertView.findViewById(R.id.start_date);
        TextView exp_date = (TextView) convertView.findViewById(R.id.exp_date);

        // Data for the row
        Deal deal = deals.get(position);

//        thumbNail.setImageURL(number.getThumbnailUrl(), imageLoader);

        retailer.setText(deal.getRetailer());
        description.setText(deal.getDescription());
        address.setText(deal.getAddress());

//        id.setText("Id: " + String.valueOf(deal.getId()));

        // When deals have tags
//        String tagStr="";
//        for (String tag : number.getTag())
//        {
//            tagStr += tag + ", ";
//        }
//        tagStr = tagStr.length() > 0 ? tagStr.substring(0, tagStr.length() -2) : tagStr;
//        tags.setText(tagStr);
        used.setText(String.valueOf(deal.getUsed()));
        complaints.setText(String.valueOf(deal.getComplaints()));

        start_date.setText("Starts: " + deal.getStart_Date().toString());
//        exp_date.setText("  Ends: " + deal.getExp_Date().toString());

        return convertView;
    }

}




