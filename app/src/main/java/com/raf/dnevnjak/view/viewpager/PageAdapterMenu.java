package com.raf.dnevnjak.view.viewpager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.raf.dnevnjak.view.fragments.CalendarFragment;
import com.raf.dnevnjak.view.fragments.DailyPlanFragment;
import com.raf.dnevnjak.view.fragments.ProfileFragment;

public class PageAdapterMenu extends FragmentPagerAdapter {
    private final int FRAGMENT_COUNT = 3;
    public static final int CALENDAR_FRAGMENT = 0;
    public static final int DAILYPLAN_FRAGMENT = 1;
    public static final int PROFILE_FRAGMENT = 2;

    public PageAdapterMenu(@NonNull FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case CALENDAR_FRAGMENT: fragment = new CalendarFragment();break;
            case DAILYPLAN_FRAGMENT:  fragment = new DailyPlanFragment();break;
            default: fragment = new ProfileFragment();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return FRAGMENT_COUNT;
    }
}
