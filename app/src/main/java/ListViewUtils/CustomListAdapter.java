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

    private List<SortedData> sortedDatas;

    public CustomListAdapter(Activity activity){
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return sortedDatas.size();
    }

    @Override
    public Object getItem(int location) {
        return sortedDatas.get(location);
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

        SortedData m = sortedDatas.get(position);

        //TextView info = (TextView) convertView.findViewById(R.id.descri);



        return convertView;
    }

}




