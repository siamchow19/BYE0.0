package com.example.bookyourevent.owner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bookyourevent.R;
import com.squareup.picasso.Picasso;

public class PartyCenterDetail extends AppCompatActivity {
    private TextView partyCenterName;
    private TextView partyCenterCapacity;
    private TextView partyCenterAddress;
    private TextView partyCenterEmail;
    private TextView partyCenterMobile;
    private TextView terms;
    private TextView price;

    private ImageView imageView;




    private String partyCenterId, ownerId;

    private Button requestListButton;
    private Button editPartyCenterButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_party_center_detail);
        partyCenterName = findViewById(R.id.party_center_detail_name);
        partyCenterCapacity = findViewById(R.id.party_center_detail_capacity);
        partyCenterAddress = findViewById(R.id.party_center_detail_address);
        partyCenterEmail = findViewById(R.id.party_center_detail_Email);
        partyCenterMobile = findViewById(R.id.party_center_detail_mobile);
        terms = findViewById(R.id.party_center_detail_terms);
        price = findViewById(R.id.party_center_detail_price);
        imageView = findViewById(R.id.detail_image);
        requestListButton = findViewById(R.id.owner_request_list);
        editPartyCenterButton = findViewById(R.id.edit_party_center);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            partyCenterId = bundle.getString("partyCenterId");
            ownerId = bundle.getString("ownerId");
        }

        loadData();

    }

    public void loadData(){

        /***
         * load and show data to UI
         */
    }
}