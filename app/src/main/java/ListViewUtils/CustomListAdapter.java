package ListViewUtils;
//import austin.aquaman_aardvark.SortedData;

/**
 * Created by macbook on 2/12/15.
 */


//import Statements
import java.util.List;
import Objects.SortedData;
import austin.aquaman_aardvark.R;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


public class CustomListAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<SortedData> dataList;
//    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public CustomListAdapter(Activity activity, List<SortedData> data_in){
        this.activity = activity;
        this.dataList = data_in;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int location) {
        return dataList.get(location);
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
        TextView value = (TextView) convertView.findViewById(R.id.value);
        TextView dataId = (TextView) convertView.findViewById(R.id.dataId);
        TextView sentence = (TextView) convertView.findViewById(R.id.sentence);
        TextView desc = (TextView) convertView.findViewById(R.id.desc);

        // Data for the row
        SortedData number = dataList.get(position);

//        thumbNail.setImageURL(number.getThumbnailUrl(), imageLoader);

        value.setText(String.valueOf(number.getValue()));

        dataId.setText("Id: " + String.valueOf(number.getDataId()));

        // When deals have tags
//        String tagStr="";
//        for (String tag : number.getTag())
//        {
//            tagStr += tag + ", ";
//        }
//        tagStr = tagStr.length() > 0 ? tagStr.substring(0, tagStr.length() -2) : tagStr;
//        tags.setText(tagStr);
        sentence.setText(number.getSentence());

        desc.setText(number.getDesc());

        return convertView;
    }

}




