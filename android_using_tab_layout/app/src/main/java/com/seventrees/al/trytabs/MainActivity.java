package com.seventrees.al.trytabs;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;

import static android.support.v7.app.ActionBar.NAVIGATION_MODE_TABS;


public class MainActivity extends ActionBarActivity {


    private ViewPager mPager;
    ActionBar mActionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mActionBar = getSupportActionBar();
       mActionBar.setNavigationMode(NAVIGATION_MODE_TABS);

        mPager = (ViewPager) findViewById(R.id.pager);

        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();

        ViewPager.SimpleOnPageChangeListener pageChangeListener = new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                mActionBar.setSelectedNavigationItem(position);
            }
        };

        mPager.setOnPageChangeListener(pageChangeListener);
        TabsAdapter tabsAdapter = new TabsAdapter(fm);

        mPager.setAdapter(tabsAdapter);
        mActionBar.setDisplayShowTitleEnabled(true);

        ActionBar.TabListener tabListener = new ActionBar.TabListener() {
            @Override
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
                mPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

            }

            @Override
            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

            }
        };

        ActionBar.Tab tab = mActionBar.newTab().setText("Tab 2").setTabListener(tabListener);
        mActionBar.addTab(tab);
        tab = mActionBar.newTab().setText("Tab 2").setTabListener(tabListener);
        mActionBar.addTab(tab);
//        tab = mActionBar.newTab().setText("Tab 3").setTabListener(tabListener);
//        mActionBar.addTab(tab);

    }
}
