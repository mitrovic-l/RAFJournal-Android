package com.raf.dnevnjak.view.recycler.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.raf.dnevnjak.R;
import com.raf.dnevnjak.models.Day;
import com.raf.dnevnjak.models.Obligation;
import com.raf.dnevnjak.models.ObligationLevel;

import java.util.List;
import java.util.function.Consumer;

public class DayAdapter extends ListAdapter<Day, DayAdapter.ViewHolder> {
    private final Consumer<Day> onDayClicked;

    public DayAdapter(@NonNull DiffUtil.ItemCallback<Day> diffCallback, Consumer<Day> onDayClicked) {
        super(diffCallback);
        this.onDayClicked = onDayClicked;
    }

    @NonNull
    @Override
    public DayAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_day, parent, false);
        return new ViewHolder(itemView, parent.getContext(), position -> {
            Day day = getItem(position);
            onDayClicked.accept(day);
        });
    }

    @Override
    public void onBindViewHolder(@NonNull DayAdapter.ViewHolder holder, int position) {
        Day day = getItem(position);
        holder.bind(day);
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        private final Context context;
        public ViewHolder(@NonNull View itemView, Context context, Consumer<Integer> onItemClicked) {
            super(itemView);
            this.context = context;
            itemView.setOnClickListener(v -> {
                if(getBindingAdapterPosition() != RecyclerView.NO_POSITION) {
                    onItemClicked.accept(getBindingAdapterPosition());
                }
            });
        }
        public void bind(Day day){
            TextView textView = itemView.findViewById(R.id.itemDay);
            textView.setText(String.valueOf(day.getDate().getDayOfMonth()));

            List<Obligation> daysObligations = day.getObligations();
            int lowO = 0, midO = 0, highO = 0;
            for (Obligation obligation : daysObligations){
                if (obligation.getObligationLevel().equals(ObligationLevel.LOW))
                    lowO++;
                else if (obligation.getObligationLevel().equals(ObligationLevel.MID))
                    midO++;
                else
                    highO++;
            }
            if (lowO>midO){
                if (lowO>highO) {
                    textView.setBackground(context.getDrawable(R.drawable.calendar_low_day_box));
                    return;
                }
            }
            else if (midO>lowO) {
                if (midO > highO) {
                    textView.setBackground(context.getDrawable(R.drawable.calendar_mid_day_box));
                    return;
                }
            }
            else if (highO>lowO) {
                    if (highO > midO) {
                        textView.setBackground(context.getDrawable(R.drawable.calendar_high_day_box));
                        return;
                    }
                }
            else
                textView.setBackground(context.getDrawable(R.drawable.calendar_day_box));
        }
    }
}
