package com.kaarvik.lvysaurworkouttracker.activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView;

import com.kaarvik.lvysaurworkouttracker.R;
import com.kaarvik.lvysaurworkouttracker.fragments.FeedbackFragment;
import com.kaarvik.lvysaurworkouttracker.fragments.GraphsFragment;
import com.kaarvik.lvysaurworkouttracker.fragments.SettingsFragment;
import com.kaarvik.lvysaurworkouttracker.fragments.WorkoutHistoryFragment;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity {

    private Realm realm;

    private String[] drawerListTitles;
    private DrawerLayout drawerLayout;
    private ListView drawerList;

    private CharSequence appTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Set up navigation drawer
        initializeDrawerList();

        //Set default fragment
        //Todo: Change initalization
        selectMenuItem(0);

        //Get realm instance
        realm = Realm.getDefaultInstance();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    private void initializeDrawerList() {
        drawerListTitles = getResources().getStringArray(R.array.drawer_titles);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerList = (ListView) findViewById(R.id.left_drawer);

        drawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item,
                drawerListTitles));
        drawerList.setOnItemClickListener(new DrawerItemClickListener());
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectMenuItem(position);
        }
    }

    /** Swaps fragments in the main content view */
    private void selectMenuItem(int position) {
        // Create a new fragment according to selection
        Fragment fragment = getFragmentForSelection(position);
//        Bundle args = new Bundle();
//        args.putInt(PlanetFragment.ARG_PLANET_NUMBER, position);
//        fragment.setArguments(args);

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, fragment)
                .commit();

        // Highlight the selected item, update the title, and close the drawer
        drawerList.setItemChecked(position, true);
        setTitle(drawerListTitles[position]);
        drawerLayout.closeDrawer(drawerList);
    }

    private Fragment getFragmentForSelection(int position) {
        String selectedListItem = drawerListTitles[position];
        Fragment fragment;

        //TODO: Change from hard coded strings..
        switch (selectedListItem) {
            default:
            case "Workouts":
                fragment = new WorkoutHistoryFragment();
                break;
            case "Graphs":
                fragment = new GraphsFragment();
                break;
            case "Settings":
                fragment = new SettingsFragment();
                break;
            case "Feedback":
                fragment = new FeedbackFragment();
                break;

        }

        return fragment;
    }

    @Override
    public void setTitle(CharSequence title) {
        appTitle = title;
        getSupportActionBar().setTitle(appTitle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
