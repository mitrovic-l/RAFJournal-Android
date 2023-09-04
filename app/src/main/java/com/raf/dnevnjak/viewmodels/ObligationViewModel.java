package com.raf.dnevnjak.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.raf.dnevnjak.models.Obligation;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ObligationViewModel extends ViewModel {

    private final MutableLiveData<List<Obligation>> obligations = new MutableLiveData<>();

    private List<Obligation> obligationList = new ArrayList<>();
    public ObligationViewModel(){

    }

    public MutableLiveData<List<Obligation>> getObligations() {
        return obligations;
    }

    public void setObligations(List<Obligation> obligationList, boolean isChecked){
        if (!isChecked){
            List<Obligation> presentObligations = new ArrayList<>();
            for (Obligation obligation : obligationList){
                if (LocalTime.now().compareTo(obligation.getTo()) < 0){
                    presentObligations.add(obligation);
                }
            }
            this.obligationList = presentObligations;
            this.obligations.setValue(obligationList);
        } else {
            this.obligations.setValue(obligationList);
        }
    }
}
