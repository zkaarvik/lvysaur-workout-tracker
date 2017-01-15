package com.kaarvik.lvysaurworkouttracker.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kaarvik.lvysaurworkouttracker.R;

/**
 * Created by Zach on 1/14/2017.
 */

public class DrawerListAdapter extends BaseAdapter {

    private Context mContext;
    private String[] titles;
    private int[] icons;

    public DrawerListAdapter(Context mContext, String[] titles, int[] icons) {
        this.mContext = mContext;
        this.titles = titles;
        this.icons = icons;
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public String getItem(int position) {
        return titles[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.drawer_list_item, parent, false);

        TextView title = (TextView) row.findViewById(R.id.drawer_title);
        title.setText(titles[position]);

        ImageView icon = (ImageView) row.findViewById(R.id.drawer_icon);
        icon.setImageResource(icons[position]);

        return row;
    }
}
