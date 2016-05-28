package com.example.app.digitalizebillscustomer;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.app.digitalizebillscustomer.SlidingDrawer.SlidingDrawerModel;

import java.util.ArrayList;

/**
 * Created by vikkycorner on 28/05/16.
 */
public class SlidingDrawerAdapter extends BaseAdapter{

    private Context context;
    private ArrayList<SlidingDrawerItem> slidingDrawerItems;

    public SlidingDrawerAdapter(Context context, ArrayList<SlidingDrawerItem> slidingDrawerItems) {
        this.context = context;
        this.slidingDrawerItems = slidingDrawerItems;
    }

    @Override
    public int getCount() {
        return slidingDrawerItems.size();
    }

    @Override
    public Object getItem(int position) {
        return slidingDrawerItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            LayoutInflater mInflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.drawer_list_item, null);
        }
        ImageView imgIcon = (ImageView) convertView.findViewById(R.id.icon);
        TextView txtTitle = (TextView) convertView.findViewById(R.id.title);
        TextView txtCount = (TextView) convertView.findViewById(R.id.counter);

        imgIcon.setImageResource(slidingDrawerItems.get(position).getIcon());
        txtTitle.setText(slidingDrawerItems.get(position).getTitle());

        if(slidingDrawerItems.get(position).isCounterVisible()){
            txtCount.setText(slidingDrawerItems.get(position).getCount());
        }else{
            // hide the counter view
            txtCount.setVisibility(View.GONE);
        }

        return convertView;
    }
}
