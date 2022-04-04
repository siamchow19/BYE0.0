package com.example.bookyourevent.client;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.bookyourevent.R;
import com.example.bookyourevent.database_controller.Customer;
import com.example.bookyourevent.database_controller.FirebaseController;
import com.example.bookyourevent.database_controller.PartyCenter;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class ClientMainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,FilterDialogFragment.OnSetListener,FirebaseController.ClientMainActivityInterface,FirebaseController.SetImageInterface{
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    String name[] = {"Noman","Salman"};
    String ad[] = {"Kishoregonj","Netrokona"};
    TextView nameTextView;
    TextView emailTextView;
    ImageView profilePicture;
    SearchView searchView;
    RecyclerView recyclerView;
    ArrayList<PartyCenter> partyCenters = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //checking firebase


        // check
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_main);
        drawerLayout = findViewById(R.id.main_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.nav_open,R.string.nav_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.getHeaderView(0);
        nameTextView = (TextView) headerView.findViewById(R.id.name_text_view);
        emailTextView = (TextView) headerView.findViewById(R.id.email_text_view);
        emailTextView.setText("aa");
        profilePicture = (ImageView) headerView.findViewById(R.id.profile_picture);
        /*
        * get party center from database
        * */
        FirebaseController.setImage("48VHHcYW90i5TujJoBnp",this);
        Log.d("image",profilePicture.toString());
        PartyCenter partyCenter = new PartyCenter("a","ABC Party Center","c","a","a");
        partyCenters.add(partyCenter);
        Log.d("size",String.valueOf(partyCenter.getSlots().size()));
        PartyCenterAdaptor partyCenterAdaptor = new PartyCenterAdaptor(this,partyCenters);
        recyclerView = findViewById(R.id.party_center_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(partyCenterAdaptor);


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            Log.d("noman",item.toString());
            return true;
        }
        if(item.getItemId() == R.id.filter_button)
        {

            Log.d("noman","filter");
            DialogFragment dialogFragment = new FilterDialogFragment();
            dialogFragment.show(getSupportFragmentManager(),"Filter");
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mai_menu_bar,menu);
        MenuItem menuItem = menu.findItem(R.id.search_button);
        searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){

            @Override
            public boolean onQueryTextSubmit(String s) {
                Log.d("noman",s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.profile_button)
        {
            Intent intent = new Intent(this,ClientProfileActivity.class);
            startActivity(intent);
        }
        if(item.getItemId() == R.id.history_button)
        {
            Intent intent = new Intent(this,ClientHistoryActivity.class);
            startActivity(intent);
        }
        Log.d("noman",item.toString());
        return true;
    }
    public void setCustomerHeader()
    {

    }
    @Override
    public void setFilterData() {
        Log.d("noman","set data");
    }


    @Override
    public void setData(Customer customer) {
        Log.d("login",customer.getPhoneNumber());
    }

    @Override
    public void setImage(Bitmap bitmap) {
        profilePicture.setImageBitmap(bitmap);
        Log.d("image",profilePicture.toString());
    }
}