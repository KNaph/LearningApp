package com.example.kylephan.learningapp;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity implements AbstractFragment.OnFragmentInteractionListener{


    @BindView(R.id.drawer_layout) DrawerLayout drawerLayout;
    @BindView(R.id.nav_view) NavigationView navDrawer;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.tab_layout) TabLayout tabLayout;

    private final int NUM_PAGES = 3;

    @BindView(R.id.fragment_container) ViewPager mPager;

    private InputFragment inputFrag;
    private FlickrViewerFragment flickrFrag;
    private BalanceFragment balanceFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        mPager.setAdapter(new PagerAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(mPager, true);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);

        setNavDrawer(navDrawer);

        inputFrag = new InputFragment();
        flickrFrag = new FlickrViewerFragment();
        balanceFragment = new BalanceFragment();
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

        switch(item.getItemId()) {
            case R.id.nav_first:
                mPager.setCurrentItem(0);
                break;
            case R.id.nav_second:
                mPager.setCurrentItem(1);
                break;
            case R.id.nav_third:
                mPager.setCurrentItem(2);
                break;
            default:
                mPager.setCurrentItem(0);
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
    public void onFragmentInteraction(String fragTitle, int navId) {
        setTitle(fragTitle);
        navDrawer.getMenu().findItem(navId).setChecked(true);
    }

    private class PagerAdapter extends FragmentPagerAdapter {
        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {

            switch(position) {
                case 0:
                    Log.d("Pager Adapter", "Case 0: Input Fragment");
                    return inputFrag;
                case 1:
                    Log.d("Pager Adapter", "Case 1: Flickr Fragment");
                    return flickrFrag;
                case 2:
                    Log.d("Pager Adapter", "Case 2: Balance Fragment");
                    return balanceFragment;
                default:
                    Log.d("Pager Adapter", "Default Case: Input Fragment");
                    return inputFrag;
            }
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}
