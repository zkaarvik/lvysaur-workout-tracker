package com.kaarvik.lvysaurworkouttracker.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.kaarvik.lvysaurworkouttracker.R;
import com.kaarvik.lvysaurworkouttracker.data.Set;

import io.realm.RealmList;

/**
 * Created by zkaar on 2017-09-19.
 */

public class SetListAdapter extends BaseAdapter {

    private RealmList<Set> mDataset;

    // Provide a suitable constructor (depends on the kind of dataset)
    public SetListAdapter(RealmList<Set> sets) {
        mDataset = sets;
    }

    @Override
    public int getCount() {
        return mDataset.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataset.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup container) {
        if (convertView == null) {
            convertView = (View) LayoutInflater.from(container.getContext())
                    .inflate(R.layout.fragment_single_set, container, false);
        }

        TextView textView = (TextView) convertView.findViewById(R.id.test_text2);
        textView.setText(mDataset.get(position).getGoalReps() + "");

        return convertView;
    }
}
