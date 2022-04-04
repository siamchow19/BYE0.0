package com.example.bookyourevent.client;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.bookyourevent.R;

import java.util.Arrays;
import java.util.Calendar;

public class FilterDialogFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    private int day,moth,year;
    private int hour,minute;
    TextView dateShow;
    TextView timeShow;
    String date = "Select Date";
    String time = "Select Time";
    Spinner districtSpinner;
    Spinner subDistrictSpinner;
    String ar[] = {"Select District","f","a"};
    private DatePickerDialog datePicker;
    TimePickerDialog timePickerDialog;
    public interface OnSetListener
    {
        void setFilterData();
    }
    public OnSetListener onSetListener;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater = requireActivity().getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.filter_interface,null);
        builder.setView(view);
        onSetListener = (OnSetListener)getContext();
        Button setTimeButton = view.findViewById(R.id.set_time_button);
        Button setDateButton = view.findViewById(R.id.set_date_button);
        dateShow = view.findViewById(R.id.date_show_text);
        timeShow = view.findViewById(R.id.time_show_text);
        districtSpinner = view.findViewById(R.id.district_spinner);
        subDistrictSpinner = view.findViewById(R.id.sub_distct_spinner);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item,ar);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        districtSpinner.setAdapter(arrayAdapter);
        subDistrictSpinner.setAdapter(arrayAdapter);
        timeShow.setText(time);
        Log.d("noman",dateShow.toString());
        dateShow.setText(date);
        setDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initDatePicker();
                open();
            }
        });
        setTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("noman","Click");
                initTimePicker();
            }
        });


        builder.setMessage("Filter").
                setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                            onSetListener.setFilterData();
                    }
                }).
                setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).setIcon(R.drawable.ic_baseline_filter_alt_24);

        return builder.create();
    }
    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        //String date = String.format("%d %d %d",i,i1,i2);
        //Log.d("noman",date);

        TextView showText = datePicker.findViewById(R.id.date_show_text);

    }
    public void initTimePicker()
    {
        TimePickerDialog.OnTimeSetListener listener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {

                makeTime(i,i1);
            }
        };
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        int style = AlertDialog.THEME_HOLO_LIGHT;
        timePickerDialog = new TimePickerDialog(getContext(),style,listener,hour,minute,false);
        timePickerDialog.show();

    }
    public void initDatePicker()
    {
        DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                makeDate(i,i1,i2);
            }
        };
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        int style = AlertDialog.THEME_HOLO_LIGHT;
        datePicker = new DatePickerDialog(getContext(),style,listener,year,month,day);

    }
    private void makeTime(int hour,int minute)
    {
       // String time = String.format("%d %d",hour,minute);
        //timeShow.setText(time);
    }
    private void makeDate(int i, int i1, int i2)
    {
        this.day = i;
        this.moth = i1;
        this.year = i2;
        //String date = String.format("%d %d %d",i,i1,i2);
        //dateShow.setText(date);
    }
    public void open()
    {
        datePicker.show();
    }

}
