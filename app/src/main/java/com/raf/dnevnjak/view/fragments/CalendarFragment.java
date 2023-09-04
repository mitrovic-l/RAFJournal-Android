package com.raf.dnevnjak.view.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.raf.dnevnjak.R;
import com.raf.dnevnjak.models.Day;
import com.raf.dnevnjak.view.recycler.adapter.DayAdapter;
import com.raf.dnevnjak.view.recycler.differ.DayDifferItemCallback;
import com.raf.dnevnjak.viewmodels.CalendarViewModel;
import com.raf.dnevnjak.viewmodels.DayViewModel;
import com.raf.dnevnjak.viewmodels.ObligationViewModel;

import java.time.LocalDate;

public class CalendarFragment extends Fragment {

    private CalendarViewModel calendarViewModel;
    private ObligationViewModel obligationViewModel;
    private DayViewModel dayViewModel;
    private RecyclerView recyclerView;
    private DayAdapter dayAdapter;
    private TextView monthTV;

    public CalendarFragment() {
        super(R.layout.fragment_calendar);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        calendarViewModel = new ViewModelProvider(requireActivity()).get(CalendarViewModel.class);
        dayViewModel = new ViewModelProvider(requireActivity()).get(DayViewModel.class);
        obligationViewModel = new ViewModelProvider(requireActivity()).get(ObligationViewModel.class);
        init(view);
        initObservers();
        initRecycler();
    }
    private void init(View view){
        recyclerView = view.findViewById(R.id.recyclerView);
        monthTV = view.findViewById(R.id.monthTV);
    }
    private void initObservers(){
        calendarViewModel.getDays().observe(getViewLifecycleOwner(), days -> {
            dayAdapter.submitList(days);
        });
    }
    private void initRecycler(){
        Day today = null;
        for(int i = 0; i < calendarViewModel.getDays().getValue().size(); i++) {
            if(calendarViewModel.getDays().getValue().get(i).getDate().compareTo(LocalDate.now()) == 0) {
                today = calendarViewModel.getDays().getValue().get(i);
                dayViewModel.setDay(today);
                break;
            }
        }
        obligationViewModel.setObligations(today.getObligations(), true);
        dayAdapter = new DayAdapter( new DayDifferItemCallback(), day -> {
            FragmentTransaction transaction = this.getActivity().getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.mainFragmentContainer, new DailyPlanFragment()).addToBackStack(null);
            transaction.commit();
        });
        recyclerView.setLayoutManager( new GridLayoutManager( this.getActivity(), 7));
        recyclerView.setAdapter(dayAdapter);

        GridLayoutManager layoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
        LocalDate now = LocalDate.now();
        int dayInMonth = now.getDayOfMonth();
        for (int i=0;i<calendarViewModel.getDays().getValue().size();i++){
            if(calendarViewModel.getDays().getValue().get(i).getDate().equals(now)) {
                // Skrolujem na poziciju trenutna - dan u mesecu plus jedan da bi bio prvi
                layoutManager.scrollToPosition(i-dayInMonth+1);
            }
        }
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                boolean first=false, last=false;
                int lastVisiblePosition = layoutManager.findLastCompletelyVisibleItemPosition();
                int firstVisiblePosition = layoutManager.findFirstCompletelyVisibleItemPosition();
                if(firstVisiblePosition != -1) {
                    for (int i = firstVisiblePosition; i <= lastVisiblePosition; i++) {
                        if (!first) {
                            if (calendarViewModel.getDays().getValue().get(i).getDate().getDayOfMonth() == 1) {
                                last = false;
                                first = true;
                            }
                        } else {
                            if (calendarViewModel.getDays().getValue().get(i).getDate().getDayOfMonth() == 1) {
                                last = true;
                                first = false;
                                // Uzimam dan iz liste koji je bio pre prvog da bih mogao da mu izvucem mesec
                                monthTV.setText(calendarViewModel.getDays().getValue().get(i - 1).getDate().getMonth().toString() + " "
                                        + calendarViewModel.getDays().getValue().get(i - 1).getDate().getYear() + ".");
                                break;
                            }
                        }
                    }
                }
            }
        });
    }

}
