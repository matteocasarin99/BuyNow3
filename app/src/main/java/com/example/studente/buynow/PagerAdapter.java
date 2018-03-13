package com.example.studente.buynow;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by studente on 06/03/2018.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {

    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0: {
                TabFragment1 tab1 = new TabFragment1();

            }
            case 1:{
                TabFragment2 tab2=new TabFragment2();
            }
            default:
                return null;

        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}

