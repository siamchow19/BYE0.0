package com.example.bookyourevent.client;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.bookyourevent.R;
import com.example.bookyourevent.database_controller.PartyCenter;
import com.example.bookyourevent.database_controller.Slot;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.sql.Time;
import java.util.ArrayList;

public class DetailedPartyCenterActivity extends AppCompatActivity implements OnMapReadyCallback {
    MapView mapView;
    GoogleMap map;
    TextView nameTextView, addressTextView, numberTextView, dateTextView;
    RatingBar ratingBar;
    RecyclerView recyclerView;
    Button setDateButton;
    private String partyCenterId;
    private PartyCenter partyCenter;
    Time time = new Time(System.currentTimeMillis());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deatailed_party_center);
        //
        partyCenterId = getIntent().getStringExtra("id");
        /*
        * Fetch party center from firebase using id
        * */
        partyCenter = new PartyCenter("01","ABC","a","Kishoreganj","a");
        mapView = (MapView) findViewById(R.id.detailed_map);
        ratingBar = (RatingBar) findViewById(R.id.detailed_ratting);
        ratingBar.setIsIndicator(true);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
        recyclerView = (RecyclerView) findViewById(R.id.detailed_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        Log.d("size",String.valueOf(partyCenter.getSlots().size()));
        recyclerView.setAdapter(new SlotAdaptor(partyCenter.getSlots()));
        recyclerView.setVerticalScrollBarEnabled(true);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        LatLng sydney = new LatLng(-34, 151);
        googleMap.addMarker(new MarkerOptions().position(sydney).title("Point"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney,7f));





    }

    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
}