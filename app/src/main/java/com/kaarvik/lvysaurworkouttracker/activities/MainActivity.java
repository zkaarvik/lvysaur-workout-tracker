package com.kaarvik.lvysaurworkouttracker.activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView;

import com.kaarvik.lvysaurworkouttracker.R;
import com.kaarvik.lvysaurworkouttracker.adapters.DrawerListAdapter;
import com.kaarvik.lvysaurworkouttracker.data.DrawerListItem;
import com.kaarvik.lvysaurworkouttracker.fragments.FeedbackFragment;
import com.kaarvik.lvysaurworkouttracker.fragments.GraphsFragment;
import com.kaarvik.lvysaurworkouttracker.fragments.SettingsFragment;
import com.kaarvik.lvysaurworkouttracker.fragments.WorkoutHistoryFragment;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity {

    private Realm realm;

    private DrawerListItem[] drawerListItems;
    private DrawerLayout drawerLayout;
    private ListView drawerList;
    private ActionBarDrawerToggle drawerToggle;

    private Toolbar toolbar;

    private CharSequence appTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Set up navigation drawer
        initializeDrawer();

        //Set default fragment
        selectMenuItem(0);

        //Get realm instance
        realm = Realm.getDefaultInstance();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    private void initializeDrawer() {
        drawerListItems = DrawerListItem.DrawerListItemFactory(this);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerList = (ListView) findViewById(R.id.left_drawer);

        drawerList.setAdapter(new DrawerListAdapter(this, drawerListItems));
        drawerList.setOnItemClickListener(new DrawerItemClickListener());

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
                R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(appTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle(appTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        //Set drawerToggle as the drawerListener
        drawerLayout.addDrawerListener(drawerToggle);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectMenuItem(position);
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

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

    /* Called whenever we call invalidateOptionsMenu() */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        boolean drawerOpen = drawerLayout.isDrawerOpen(drawerList);
//        menu.findItem(R.id.action_websearch).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    /** Swaps fragments in the main content view */
    private void selectMenuItem(int position) {
        // Create a new fragment according to selection
        Fragment fragment = getFragmentForSelection(position);

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, fragment)
                .commit();

        // Highlight the selected item, update the title, and close the drawer
        drawerList.setItemChecked(position, true);
        setTitle(drawerListItems[position].getTitle());
        drawerLayout.closeDrawer(drawerList);
    }

    private Fragment getFragmentForSelection(int position) {
        String selectedListItem = drawerListItems[position].getId();
        Fragment fragment;

        switch (selectedListItem) {
            default:
            case DrawerListItem.FRAGMENT_WORKOUT:
                fragment = new WorkoutHistoryFragment();
                break;
            case DrawerListItem.FRAGMENT_GRAPHS:
                fragment = new GraphsFragment();
                break;
            case DrawerListItem.FRAGMENT_SETTINGS:
                fragment = new SettingsFragment();
                break;
            case DrawerListItem.FRAGMENT_FEEDBACK:
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
}
