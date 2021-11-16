package com.example.validationfeature12;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    Spinner listProperties;
    Spinner listBedrooms;
    Spinner listFurnished;

    EditText monthlyRent;
    EditText dateTime;
    EditText notes;
    EditText nameReporter;

    Button btnSubmit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSubmit = findViewById(R.id.btn_submit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] listError={};
                List<String> ErrorValidated = new ArrayList<>(Arrays.asList((listError)));

                String[] arrProperties = {"Flat", "House", "Bungalow"};
                String[] arrBedrooms =  {"One", "Two", "Studio"};
                String[] arrFurnished = {"Furnished", "Unfurnished", "Part Furnished"};

                List<String> listArrProperties = new ArrayList<>(Arrays.asList(arrProperties));
                List<String> listArrBedrooms = new ArrayList<>(Arrays.asList(arrBedrooms));
                List<String> listArrFurnished = new ArrayList<>(Arrays.asList(arrFurnished));

                listProperties = findViewById(R.id.list_properties);
                String valueOfProperties = listProperties.getSelectedItem().toString();

                listBedrooms = findViewById(R.id.list_bedrooms);
                String valueOfBedrooms = listBedrooms.getSelectedItem().toString();

                listFurnished = findViewById(R.id.list_furnished);
                String valueOfFurnished = listFurnished.getSelectedItem().toString();

                monthlyRent = findViewById(R.id.input_monthly);
                String valueOfMonthly = monthlyRent.getText().toString();

                dateTime = findViewById(R.id.dateTimeText);
                String valueOfDateTime = dateTime.getText().toString();

                notes = findViewById(R.id.input_notes);
                String valueOfNotes = notes.getText().toString();

                nameReporter = findViewById(R.id.input_reporter);
                String valueOfNameReporter = nameReporter.getText().toString();

                if(!(listArrProperties.contains(valueOfProperties))){
                    ErrorValidated.add("You need to enter properties");
                }
                if(!(listArrBedrooms.contains(valueOfBedrooms))){
                    ErrorValidated.add("You need to enter bedrooms");
                }
                if(!(listArrFurnished.contains(valueOfFurnished))){
                    ErrorValidated.add("You need to enter Furnished");
                }
                if(isValidateEmptyOrWhiteSpace(valueOfNameReporter) || isValidateEmptyOrWhiteSpace(valueOfMonthly)){
                    ErrorValidated.add("You need to enter name of Reporter");
                }
                if(!(isValidateNumber(valueOfMonthly))){
                    ErrorValidated.add("You need to enter value of Monthly");
                }
                if(!(isValidateDate(valueOfDateTime))){
                    ErrorValidated.add("You need to enter value of Date Time");
                }
                Toast showToast;
                String successCreate = "You have created a new room";
                if(ErrorValidated.size() > 0){
                    showToast = Toast.makeText(MainActivity.this, createMsg(ErrorValidated), Toast.LENGTH_LONG);
                    showToast.show();
                } else {
                    showToast = Toast.makeText(MainActivity.this, successCreate , Toast.LENGTH_LONG);
                    showToast.show();
                }
            }
        });
    }
    public final static String createMsg(List validate) {
        String err = "";
        for (int index = 0; index < validate.size(); index++) {
            err += (validate.get(index) + ", ");
        }

        return err;
    }

    public final static boolean isValidateDate(String val)
    {
        return Pattern.compile("^\\d{4}[\\/.]\\d{1,2}[\\/.]\\d{1,2}$").matcher(val).matches();
    }

    public final static boolean isValidateEmptyOrWhiteSpace(String val)
    {
        return Pattern.compile("^\\s*$").matcher(val).matches();
    }

    public final static boolean isValidateNumber(String val)
    {
        return Pattern.compile("^\\d+$").matcher(val).matches();
    }
}