package com.example.bookyourevent.client;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookyourevent.R;
import com.example.bookyourevent.database_controller.PartyCenter;

import java.util.ArrayList;

public class PartyCenterAdaptor extends RecyclerView.Adapter<PartyCenterAdaptor.MyViewHolder> implements View.OnClickListener {
    ArrayList<PartyCenter> partyCenters;
    Context context;
    public PartyCenterAdaptor(Context context,ArrayList<PartyCenter> partyCenters)
    {
        this.context = context;
        this.partyCenters = partyCenters;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.party_center_view,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.partyCenterNameTextView.setText(partyCenters.get(position).getName());
        holder.partyCenterAddressTextView.setText(partyCenters.get(position).getAddress());
        Log.d("noman", (String) holder.partyCenterAddressTextView.getText());
        PartyCenter partyCenter = partyCenters.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,DetailedPartyCenterActivity.class);
                Log.d("size",String.valueOf(partyCenter.getSlots().size()) + "Noman");
                intent.putExtra("id", partyCenters.get(position).getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return partyCenters.size();
    }

    @Override
    public void onClick(View view) {

    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView partyCenterNameTextView;
        public TextView partyCenterAddressTextView;
        public TextView partyCenterTimeTextView;
        public TextView partyCenterIdTextView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            partyCenterNameTextView = itemView.findViewById(R.id.party_center_name_text_view);
            partyCenterAddressTextView = itemView.findViewById(R.id.party_center_address_text_view);
            partyCenterTimeTextView = itemView.findViewById(R.id.party_center_time_text_view);
            partyCenterIdTextView = itemView.findViewById(R.id.party_center_id);


        }
    }



}
