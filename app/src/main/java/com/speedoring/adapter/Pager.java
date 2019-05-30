package com.speedoring.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.speedoring.interface_update_data.UpdateInterface;
import com.speedoring.ui.vendor.fragment.profile.VendorBusinessInfoFragment;
import com.speedoring.ui.vendor.fragment.profile.VendorContactInfoFragment;
import com.speedoring.ui.vendor.fragment.profile.VendorPersonalInfoFragment;

public class Pager extends FragmentStatePagerAdapter {

    //integer to count number of tabs
    private int tabCount;
    private String[] tabTitles = new String[]{"Personal", "Business", "Contact"};
    private UpdateInterface updateInterface;

    //Constructor to the class
    public Pager(FragmentManager fm, int tabCount, UpdateInterface updateInterface) {
        super(fm);
        this.tabCount = tabCount;
        this.updateInterface = updateInterface;
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
                VendorPersonalInfoFragment personalInfoFragment = new VendorPersonalInfoFragment();
                personalInfoFragment.setUpdateInterface(updateInterface);
                return personalInfoFragment;
            case 1:
                VendorBusinessInfoFragment businessInfoFragment = new VendorBusinessInfoFragment();
                businessInfoFragment.setUpdateInterface(updateInterface);
                return businessInfoFragment;
            case 2:
                VendorContactInfoFragment contactInfoFragment = new VendorContactInfoFragment();
                contactInfoFragment.setUpdateInterface(updateInterface);
                return contactInfoFragment;
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