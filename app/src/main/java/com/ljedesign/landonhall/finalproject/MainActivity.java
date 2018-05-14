package com.ljedesign.landonhall.finalproject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private static boolean isLaunch = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);

        mDrawerLayout = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        //Set up navigation
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        activateDrawerSelection(menuItem);
                        return true;
                    }
                });
        if(isLaunch) {
            isLaunch = false;
            callFragment("com.ljedesign.landonhall.finalproject.SocialFragment");
            setTitle("Social");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Activate the fragments that match user's selection
     * @param menuItem
     */

    protected void activateDrawerSelection(MenuItem menuItem) {

        Log.v("Menu Item", menuItem.toString());
        String className = "com.ljedesign.landonhall.finalproject." + menuItem.toString() + "Fragment";

        callFragment(className);
        // set item as selected to persist highlight
        menuItem.setChecked(true);
        // Highlight the selected item has been done by NavigationView
        menuItem.setChecked(true);
        // Set action bar title
        setTitle(menuItem.getTitle());
        // Close the navigation drawer
        mDrawerLayout.closeDrawers();

    }

    /**
     * Method to call the fragments
     * @param className
     */
    protected void callFragment(String className) {
        Fragment fragment = null;
        try {
            fragment = (Fragment) Class.forName(className).newInstance();
            // Insert the fragment by replacing any existing fragment
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
