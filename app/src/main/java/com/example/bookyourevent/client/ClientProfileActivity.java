package com.example.bookyourevent.client;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.bookyourevent.R;

import java.io.FileNotFoundException;
import java.io.IOException;

public class ClientProfileActivity extends AppCompatActivity implements View.OnClickListener {
    Button saveButton,changeProfileButton;
    EditText nameEditText,addressEditText,numberEditText;
    ProgressBar progressBar;
    ImageView profilePicView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_client_profile);
        nameEditText = findViewById(R.id.name_edit_text);
        addressEditText = findViewById(R.id.address_edit_text);
        numberEditText = findViewById(R.id.number_edit_text);
        progressBar = findViewById(R.id.save_progress);
        progressBar.setVisibility(View.INVISIBLE);
        setInitValue();
        profilePicView = (ImageView) findViewById(R.id.profile_image_in_profileactivity);
        saveButton = findViewById(R.id.save_button);
        changeProfileButton = findViewById(R.id.change_profile_pic_button);
        saveButton.setOnClickListener(this);
        changeProfileButton.setOnClickListener(this);
        makeDisable();
    }
    private void setInitValue()
    {

        nameEditText.setText("Sakhaouth Hossan");
        addressEditText.setText("Kishoregonj");
        numberEditText.setText("01770651111");
    }
    public void onSave()
    {
        finish();
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.save_button)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Are You Sure?").
                    setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            view.setVisibility(View.INVISIBLE);
                            progressBar.setVisibility(View.VISIBLE);
                        }
                    }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    onCancel();
                }
            });
            builder.show();
        }
        if(view.getId() == R.id.change_profile_pic_button)
        {
            final int REQUEST_IMAGE_CAPTURE = 200;
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent,REQUEST_IMAGE_CAPTURE);

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        final int REQUEST_IMAGE_CAPTURE = 1;
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK && data !=  null && data.getData() != null) {
            Uri uri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                profilePicView.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private void onCancel() {
    }
    private void makeEnable()
    {
        if(nameEditText.isEnabled() == false)
        {
            numberEditText.setEnabled(true);
            addressEditText.setEnabled(true);
            nameEditText.setEnabled(true);
            saveButton.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.client_profile_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Log.d("noman",String.format("%d",item.getItemId()));
        if(item.getItemId() == R.id.edit_enable_button)
        {
            makeEnable();
        }
        if(item.getItemId() == 16908332)
        {
            finish();
        }
        return true;
    }
    void makeDisable()
    {
        nameEditText.setEnabled(false);
        addressEditText.setEnabled(false);
        numberEditText.setEnabled(false);
        saveButton.setVisibility(View.INVISIBLE);
    }
}