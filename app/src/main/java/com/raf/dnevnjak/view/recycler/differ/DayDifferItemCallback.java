package com.raf.dnevnjak.view.recycler.differ;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.raf.dnevnjak.models.Day;

public class DayDifferItemCallback extends DiffUtil.ItemCallback<Day> {
    @Override
    public boolean areItemsTheSame(@NonNull Day oldItem, @NonNull Day newItem) {
        return oldItem.getDate().compareTo(newItem.getDate()) == 0;
    }

    @Override
    public boolean areContentsTheSame(@NonNull Day oldItem, @NonNull Day newItem) {
        return oldItem.getObligations().equals(newItem.getObligations())
                && oldItem.getLevel().equals(newItem.getLevel());
    }
}
