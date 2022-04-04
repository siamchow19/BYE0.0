package com.example.bookyourevent.client;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookyourevent.R;
import com.example.bookyourevent.database_controller.Slot;

import java.util.ArrayList;

public class SlotAdaptor extends RecyclerView.Adapter<SlotAdaptor.ViewHolder> {
    private ArrayList<Slot> slots;
    public SlotAdaptor(ArrayList<Slot> slots)
    {
        this.slots = slots;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.slot_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(slots.get(position).getName());
        holder.time.setText("Start from " + slots.get(position).getStartTime().toString() + " to " + slots.get(position).getEndTime().toString() );
        holder.requestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return slots.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name,time;
        public Button requestButton;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.slot_name_text);
            time = (TextView) itemView.findViewById(R.id.slot_time_text);
            requestButton = (Button) itemView.findViewById(R.id.slot_button);
        }
    }
}
