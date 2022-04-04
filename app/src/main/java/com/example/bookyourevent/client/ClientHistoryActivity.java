package com.example.bookyourevent.client;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.bookyourevent.R;

public class ClientHistoryActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_history);
        recyclerView = (RecyclerView) findViewById(R.id.history_recycler_view);
        ClientHistoryAdaptor clientHistoryAdaptor = new ClientHistoryAdaptor();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(clientHistoryAdaptor);
    }
}