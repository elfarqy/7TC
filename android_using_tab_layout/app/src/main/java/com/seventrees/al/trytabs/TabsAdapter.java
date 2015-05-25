package com.seventrees.al.trytabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by al on 5/18/15.
 */
public class TabsAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 2;

    /** Constructor of the class */
    public TabsAdapter(FragmentManager fm) {
        super(fm);
    }
    @Override
    public Fragment getItem(int i) {
        Bundle data = new Bundle();
        switch(i){

            /** tab1 is selected */
            case 0:
                fragment1 fragment1 = new fragment1();
                return fragment1;

            /** tab2 is selected */
            case 1:
                fragment2 fragment2 = new fragment2();
                return fragment2;
        }
        return null;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }
}
