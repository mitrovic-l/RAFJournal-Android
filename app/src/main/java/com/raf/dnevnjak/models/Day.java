package com.raf.dnevnjak.models;

import java.time.LocalDate;
import java.util.List;

public class Day {
    private LocalDate date;
    private ObligationLevel level;
    private List<Obligation> obligations;

    public Day(LocalDate date, ObligationLevel level, List<Obligation> obligations) {
        this.date = date;
        this.level = level;
        this.obligations = obligations;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public ObligationLevel getLevel() {
        return level;
    }

    public void setLevel(ObligationLevel level) {
        this.level = level;
    }

    public List<Obligation> getObligations() {
        return obligations;
    }

    public void setObligations(List<Obligation> obligations) {
        this.obligations = obligations;
    }
}
