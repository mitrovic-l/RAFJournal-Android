package com.raf.dnevnjak.view.recycler.differ;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.raf.dnevnjak.models.Obligation;

public class ObligationDiffItemCallback extends DiffUtil.ItemCallback<Obligation> {
    @Override
    public boolean areItemsTheSame(@NonNull Obligation oldItem, @NonNull Obligation newItem) {
        return oldItem.getName().equals(newItem.getName());
    }

    @Override
    public boolean areContentsTheSame(@NonNull Obligation oldItem, @NonNull Obligation newItem) {
        return oldItem.getObligationLevel().equals(newItem.getObligationLevel())
                && oldItem.getDescription().equals(newItem.getDescription());
    }
}
