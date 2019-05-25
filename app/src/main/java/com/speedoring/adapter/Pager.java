package com.speedoring.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.speedoring.ui.vendor.fragment.profile.VendorBusinessInfoFragment;
import com.speedoring.ui.vendor.fragment.profile.VendorContactInfoFragment;
import com.speedoring.ui.vendor.fragment.profile.VendorPersonalInfoFragment;

public class Pager extends FragmentStatePagerAdapter {

    //integer to count number of tabs
    int tabCount;

    private String[] tabTitles = new String[]{"Personal", "Business", "Contact"};

    //Constructor to the class
    public Pager(FragmentManager fm, int tabCount) {
        super(fm);
        //Initializing tab count
        this.tabCount = tabCount;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

    //Overriding method getItem
    @Override
    public Fragment getItem(int position) {
        //Returning the current tabs
        switch (position) {
            case 0:
                return new VendorPersonalInfoFragment();
            case 1:
                return new VendorBusinessInfoFragment();
            case 2:
                return new VendorContactInfoFragment();
            default:
                return null;
        }
    }

    //Overriden method getCount to get the number of tabs
    @Override
    public int getCount() {
        return tabCount;
    }
}