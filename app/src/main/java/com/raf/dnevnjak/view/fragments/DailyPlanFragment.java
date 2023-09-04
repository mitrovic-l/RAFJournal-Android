package com.raf.dnevnjak.view.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.raf.dnevnjak.R;
import com.raf.dnevnjak.models.Day;
import com.raf.dnevnjak.view.recycler.adapter.ObligationAdapter;
import com.raf.dnevnjak.view.recycler.differ.ObligationDiffItemCallback;
import com.raf.dnevnjak.viewmodels.CalendarViewModel;
import com.raf.dnevnjak.viewmodels.DayViewModel;
import com.raf.dnevnjak.viewmodels.ObligationViewModel;

public class DailyPlanFragment extends Fragment {

    private ObligationViewModel obligationViewModel;
    private CalendarViewModel calendarViewModel;
    private DayViewModel dayViewModel;
    private ObligationAdapter obligationAdapter;
    private RecyclerView recyclerView;
    private ConstraintLayout mainLayout;
    private TextView dateTV;
    private TextView lowTV;
    private TextView midTV;
    private TextView highTV;
    private CheckBox checkBox;
    private EditText searchET;
    private ImageButton deleteButton;
    private Day day;
    private boolean flag = false;

    public DailyPlanFragment(){
        super(R.layout.fragment_planner);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        obligationViewModel = new ViewModelProvider(requireActivity()).get(ObligationViewModel.class);
        calendarViewModel = new ViewModelProvider(requireActivity()).get(CalendarViewModel.class);
        dayViewModel = new ViewModelProvider(requireActivity()).get(DayViewModel.class);
        day = dayViewModel.getDay().getValue();
        init(view);
        initObservers();
        initRecycler();
    }
    private void init(View view){
        recyclerView = view.findViewById(R.id.recyclerDailyPlan);
        checkBox = view.findViewById(R.id.showPastCheckBox);
        lowTV = view.findViewById(R.id.lowTV);
        midTV = view.findViewById(R.id.midTV);
        highTV = view.findViewById(R.id.highTV);
        dateTV = view.findViewById(R.id.monthDailyPlanTV);
        dateTV.setText(day.getDate().toString());
    }
    private void initObservers(){
        obligationViewModel.getObligations().observe(getViewLifecycleOwner(), obligations -> {
            obligationAdapter.submitList(obligations);
        });
    }
    private void initRecycler(){
        obligationAdapter = new ObligationAdapter(new ObligationDiffItemCallback(), obligation -> {
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.mainFragmentContainer, new MainFragment()).addToBackStack(null);
            transaction.commit();
        }, obligationPosition -> {
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        recyclerView.setAdapter(obligationAdapter);
    }
}
