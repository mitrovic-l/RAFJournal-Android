package com.raf.dnevnjak.models;

import java.time.LocalTime;

public class Obligation {
    private String name;
    private String description;
    private LocalTime from;
    private LocalTime to;
    private ObligationLevel obligationLevel;

    public Obligation(String name, String description, LocalTime from, LocalTime to) {
        this.name = name;
        this.description = description;
        this.from = from;
        this.to = to;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalTime getFrom() {
        return from;
    }

    public void setFrom(LocalTime from) {
        this.from = from;
    }

    public LocalTime getTo() {
        return to;
    }

    public void setTo(LocalTime to) {
        this.to = to;
    }

    public ObligationLevel getObligationLevel() {
        return obligationLevel;
    }

    public void setObligationLevel(ObligationLevel obligationLevel) {
        this.obligationLevel = obligationLevel;
    }
}
