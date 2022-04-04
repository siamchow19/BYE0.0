package com.example.bookyourevent.client;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookyourevent.R;
import com.example.bookyourevent.database_controller.Reservation;

import java.util.ArrayList;

public class ClientHistoryAdaptor extends RecyclerView.Adapter<ClientHistoryAdaptor.ViewHolder> {
    private Context context;
    private ArrayList<Reservation> reservations;
    public ClientHistoryAdaptor()
    {

    }

    public ClientHistoryAdaptor(Context context, ArrayList<Reservation> reservations)
    {
        this.context = context;
        this.reservations = reservations;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.history_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return reservations.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView historyName,historyDate,historyCost;
        RatingBar historyRatingBar;
        Button historyCancelButt0n;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            historyName = itemView.findViewById(R.id.history_name);
            historyDate = itemView.findViewById(R.id.history_date);
            historyCost = itemView.findViewById(R.id.history_cost);
            historyRatingBar = itemView.findViewById(R.id.history_ratting);
            historyCancelButt0n = itemView.findViewById(R.id.history_cancel);
        }
    }

}
