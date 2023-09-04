package com.raf.dnevnjak.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.raf.dnevnjak.models.Day;
import com.raf.dnevnjak.models.Obligation;
import com.raf.dnevnjak.models.ObligationLevel;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CalendarViewModel extends ViewModel {

    public final MutableLiveData<List<Day>> days = new MutableLiveData<>();
    private ArrayList<Day> daysList = new ArrayList<>();

    public CalendarViewModel() {
        LocalDate localDate = LocalDate.of(2022,8, 1);
        for (int i=0;i<365;i++){
            List<Obligation> obligations = new ArrayList<>();
            Obligation o = new Obligation("New Obligation", "New obligation description", LocalTime.of(13, 30), LocalTime.of(15,0));
            int randomInt = new Random().nextInt(100) % 4;
            if (randomInt == 0)
                o.setObligationLevel(ObligationLevel.LOW);
            else if (randomInt == 1)
                o.setObligationLevel(ObligationLevel.MID);
            else if (randomInt == 2)
                o.setObligationLevel(ObligationLevel.HIGH);
            else
                o.setObligationLevel(ObligationLevel.NONE);
            obligations.add(o);
            Day day = new Day(localDate.plusDays(i), ObligationLevel.LOW,obligations );
            daysList.add(day);

        }
        ArrayList<Day> toSubmit = new ArrayList<>(daysList);
        days.setValue(toSubmit);
    }

    public MutableLiveData<List<Day>> getDays() {
        return days;
    }
}
