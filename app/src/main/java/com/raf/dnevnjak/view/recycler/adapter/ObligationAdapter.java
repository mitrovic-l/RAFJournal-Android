package com.raf.dnevnjak.view.recycler.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.raf.dnevnjak.R;
import com.raf.dnevnjak.models.Obligation;

import org.w3c.dom.Text;

import java.util.function.Consumer;

public class ObligationAdapter extends ListAdapter<Obligation, ObligationAdapter.ViewHolder> {
    private final Consumer<Integer> obligationPosition;
    private final Consumer<Obligation> onObligationClicked;

    public ObligationAdapter(@NonNull DiffUtil.ItemCallback<Obligation> diffCallback,Consumer<Integer> obligationPosition, Consumer<Obligation> onObligationClicked) {
        super(diffCallback);
        this.obligationPosition = obligationPosition;
        this.onObligationClicked = onObligationClicked;
    }

    @NonNull
    @Override
    public ObligationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_obligation, parent, false);
        return new ViewHolder(itemView, parent.getContext(), position -> {
            Obligation obligation = getItem(position);
            onObligationClicked.accept(obligation);
        });
    }


    @Override
    public void onBindViewHolder(@NonNull ObligationAdapter.ViewHolder holder, int position) {
        Obligation o = getItem(position);
        holder.bind(o);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private final Context context;

        public ViewHolder(@NonNull View itemView, Context context, Consumer<Integer> onItemClicked) {
            super(itemView);
            this.context = context;
        }
        public void bind(Obligation obligation){
            System.out.println("ADFJKVAJFVSDJVSADJBVASJDSADK" + obligation);
            TextView title = itemView.findViewById(R.id.obligationName);
            TextView date = itemView.findViewById(R.id.obligationDate);
            ImageView o = itemView.findViewById(R.id.itemObligation);
            ConstraintLayout layout = itemView.findViewById(R.id.itemLayout);
            title.setText(obligation.getName());
            date.setText(obligation.getFrom().toString() + "- " + obligation.getTo().toString());
            Drawable drawable = context.getDrawable(R.drawable.calendar_low_day_box);
            o.setBackground(drawable);
        }
    }
}
