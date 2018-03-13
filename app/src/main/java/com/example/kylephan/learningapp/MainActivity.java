package com.example.kylephan.learningapp;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                        item.setChecked(true);

                        displayFragments(item.getItemId());
                        drawerLayout.closeDrawers();


                        return true;
                    }
                }
        );

//        InputFragment inFrag = new InputFragment();
//        FragmentManager fragmentManager = getFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.add(R.id.fragment_container, inFrag);
//        fragmentTransaction.commit();

    }

    private void displayFragments(int viewId) {

        Fragment fragment = null;
        String title = getString(R.string.app_name);
        System.out.println("--------------------------------------No Case");
        System.out.println(viewId);
        System.out.println(R.id.input_frag);
        System.out.println(R.id.flickr_frag);
        switch (viewId) {
            case R.id.input_frag:
                System.out.println("--------------------------------------Case 1");
                fragment = new InputFragment();
                title = "Change the Text";

                break;
            case R.id.flickr_frag:
                System.out.println("--------------------------------------Case 2");
                fragment = new FlickrViewer();
                title = "View Flickr Photos";

        }

        if (fragment != null) {
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_container, fragment);
            ft.commit();
        } else {
            fragment = new InputFragment();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.add(R.id.fragment_container, fragment);
            ft.commit();
        }

        // set the toolbar title
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}
