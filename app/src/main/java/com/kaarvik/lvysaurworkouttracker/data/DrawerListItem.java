package com.kaarvik.lvysaurworkouttracker.data;

import android.content.Context;
import com.kaarvik.lvysaurworkouttracker.R;

/**
 * Created by Zach on 1/15/2017.
 */

public class DrawerListItem {
    public static final String FRAGMENT_WORKOUT = "FRAGMENT_WORKOUT";
    public static final String FRAGMENT_GRAPHS = "FRAGMENT_GRAPHS";
    public static final String FRAGMENT_SETTINGS = "FRAGMENT_SETTINGS";
    public static final String FRAGMENT_FEEDBACK = "FRAGMENT_FEEDBACK";

    private String id;
    private int icon;
    private String title;

    public DrawerListItem(String id, int icon, String title) {
        this.id = id;
        this.icon = icon;
        this.title = title;
    }

    public static DrawerListItem[] DrawerListItemFactory(Context mContext) {
        String drawerListTitles[] = mContext.getResources().getStringArray(R.array.drawer_titles);

        DrawerListItem[] listItems = {
            new DrawerListItem(FRAGMENT_WORKOUT, R.drawable.ic_drawer_workout, drawerListTitles[0]),
            new DrawerListItem(FRAGMENT_GRAPHS, R.drawable.ic_drawer_graphs, drawerListTitles[1]),
            new DrawerListItem(FRAGMENT_SETTINGS, R.drawable.ic_settings, drawerListTitles[2]),
            new DrawerListItem(FRAGMENT_FEEDBACK, R.drawable.ic_drawer_feedback, drawerListTitles[3])
        };

        return listItems;
    }

    public int getIcon() {
        return icon;
    }

    public String getTitle() {
        return title;
    }

    public String getId() {
        return id;
    }
}
