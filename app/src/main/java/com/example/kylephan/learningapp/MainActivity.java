package com.example.kylephan.learningapp;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements AbstractFragment.OnFragmentInteractionListener{

    private DrawerLayout drawerLayout;
    private NavigationView navDrawer;
    private InputFragment inputFrag;
    private FlickrViewerFragment flickrFrag;
    private BalanceFragment balanceFragment;

    private String appTitle;

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

        navDrawer = findViewById(R.id.nav_view);
        setNavDrawer(navDrawer);

//        Fragment fragment = new InputFragment();
//        FragmentManager fragmentManager = getFragmentManager();
//        fragmentManager.beginTransaction().add(R.id.fragment_container, fragment).commit();

        inputFrag = new InputFragment();
        flickrFrag = new FlickrViewerFragment();
        balanceFragment = new BalanceFragment();
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_container, inputFrag).commit();

    }

    private void setNavDrawer(NavigationView navView) {
        navView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        selectDrawerItem(item);
                        return false;
                    }
                }
        );
    }

    private void selectDrawerItem(MenuItem item) {

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        switch(item.getItemId()) {
            case R.id.nav_first:
                fragmentTransaction.replace(R.id.fragment_container, inputFrag).addToBackStack(null).commit();
                break;
            case R.id.nav_second:
                fragmentTransaction.replace(R.id.fragment_container, flickrFrag).addToBackStack(null).commit();
                break;
            case R.id.nav_third:
                fragmentTransaction.replace(R.id.fragment_container, balanceFragment).addToBackStack(null).commit();
                break;
            default:
                fragmentTransaction.replace(R.id.fragment_container, inputFrag).addToBackStack(null).commit();
        }

        item.setChecked(true);
        setTitle(item.getTitle());
        drawerLayout.closeDrawers();

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
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawers();
        }
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
//            super.onBackPressed();
        }
    }

    @Override
    public void onFragmentInteraction(String string) {
        this.appTitle = string;
        setTitle(string);
    }
}
