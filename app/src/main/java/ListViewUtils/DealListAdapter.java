package ListViewUtils;

/**
 * Created by austin on 3/10/15.
 */
//import Statements
import java.util.List;
import austin.aquaman_aardvark.R;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

// Objects imported
import Objects.Date;
import Objects.Deal;


public class DealListAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
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
        TextView used = (TextView) convertView.findViewById(R.id.used);
        TextView id = (TextView) convertView.findViewById(R.id.id);
        TextView description = (TextView) convertView.findViewById(R.id.description);
        TextView start_date = (TextView) convertView.findViewById(R.id.start_date);
        TextView exp_date = (TextView) convertView.findViewById(R.id.exp_date);

        // Data for the row
        Deal deal = deals.get(position);

//        thumbNail.setImageURL(number.getThumbnailUrl(), imageLoader);

        used.setText(String.valueOf(deal.getUsed()));

        id.setText("Id: " + String.valueOf(deal.getId()));

        // When deals have tags
//        String tagStr="";
//        for (String tag : number.getTag())
//        {
//            tagStr += tag + ", ";
//        }
//        tagStr = tagStr.length() > 0 ? tagStr.substring(0, tagStr.length() -2) : tagStr;
//        tags.setText(tagStr);
        description.setText(deal.getDescription());

        start_date.setText(deal.getStart_Date().toString());
        exp_date.setText(deal.getExp_Date().toString());

        return convertView;
    }

}




