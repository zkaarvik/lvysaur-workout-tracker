package com.kaarvik.lvysaurworkouttracker.fragments;

import android.app.ActivityOptions;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kaarvik.lvysaurworkouttracker.R;
import com.kaarvik.lvysaurworkouttracker.activities.WorkoutActivity;

import static com.kaarvik.lvysaurworkouttracker.utils.Constants.EXTRA_WORKOUTID;

public class WorkoutHistoryFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public WorkoutHistoryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WorkoutHistoryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WorkoutHistoryFragment newInstance(String param1, String param2) {
        WorkoutHistoryFragment fragment = new WorkoutHistoryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_workout_history, container, false);

        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab_workout_history);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickFloatingActionButton(view);
            }
        });

        // Inflate the layout for this fragment
        return rootView;
    }

    public void onClickFloatingActionButton(View view) {
        createNewWorkout(-1);
    }

    public void createNewWorkout(long id) {
        //TODO: Pass workout ID if exists

        //Start the workout activity
        Intent intent = new Intent(getActivity(), WorkoutActivity.class);
        intent.putExtra(EXTRA_WORKOUTID, id);
        startActivity(intent);
    }
}
