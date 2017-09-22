package com.kaarvik.lvysaurworkouttracker.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.kaarvik.lvysaurworkouttracker.R;
import com.kaarvik.lvysaurworkouttracker.data.Set;
import com.kaarvik.lvysaurworkouttracker.views.RepsNumberButton;

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

        Set set = mDataset.get(position);
        double setWeight = set.getWeight();
        int setReps = set.getGoalReps();

        final RepsNumberButton button = (RepsNumberButton) convertView.findViewById(R.id.button_set_reps);
        button.setOnClickListener(new RepsNumberButton.OnClickListener() {
            @Override
            public void onClick(View view) {
                String num = button.getNumber();
            }
        });

        return convertView;
    }
}
