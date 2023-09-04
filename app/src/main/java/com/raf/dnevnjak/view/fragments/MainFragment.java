package com.raf.dnevnjak.view.fragments;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.raf.dnevnjak.R;
import com.raf.dnevnjak.view.viewpager.PageAdapterMenu;

public class MainFragment extends Fragment {
    private ViewPager viewPager;
    private View itemView;


    public MainFragment(){
        super(R.layout.fragment_main);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        System.out.println("CREATED MAIN FRAGMENT");
        itemView = view;
        initViewPager(view);
        initNavigation(view);

    }

    private void initNavigation(View view) {
        ((BottomNavigationView) view.findViewById(R.id.bottomNavigation)).setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.calendar: viewPager.setCurrentItem(PageAdapterMenu.CALENDAR_FRAGMENT, false); break;
                case R.id.daily_plan: viewPager.setCurrentItem(PageAdapterMenu.DAILYPLAN_FRAGMENT, false); break;
                case R.id.profile: viewPager.setCurrentItem(PageAdapterMenu.PROFILE_FRAGMENT, false); break;
            }
            return true;
        });
    }
    private void initViewPager(View view) {
        viewPager = view.findViewById(R.id.viewPager);
        viewPager.setAdapter(new PageAdapterMenu(getActivity().getSupportFragmentManager()));
    }
}
