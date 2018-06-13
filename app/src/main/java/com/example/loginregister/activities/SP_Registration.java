package com.example.loginregister.activities;

import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import com.example.loginregister.R;
import com.example.loginregister.helpers.InputValidation;
import com.example.loginregister.model.SPDetails;
import com.firebase.client.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SP_Registration extends AppCompatActivity {

    private final AppCompatActivity activity = SP_Registration.this;

    private TextInputLayout sptextInputLayoutName;
    private TextInputLayout sptextInputLayoutEmail;
    private TextInputLayout sptextInputLayoutPhoneNumber;

    private EditText sptextInputEditTextName;
    private EditText sptextInputEditTextEmail;
    private EditText sptextInputEditTextPhoneNumber;

    private AppCompatButton SPSubmitButton ;
    private InputValidation inputValidation;
    public static final String Firebase_Server_URL = "https://fir-basic-ce1a5.firebaseio.com/";

    String NameHolder, NumberHolder,EmailHolder;

    Firebase firebase;

    Spinner s;

    DatabaseReference databaseReference;
    public static final String Database_Path = "Service_Provider_Database";


    String[] items = new String[] {"Select City","Hydrebad","Delhi" };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sp__registration);

        s = (Spinner) findViewById(R.id.Spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        s.setAdapter(adapter);
        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
               /* items[0] = "Hydrebad";
            if (position > 0){*/

                s.setSelected(true);
//                  lol = s.getItemAtPosition(position).toString();
//                  s.setSelected(lol);
            //}
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        initViews();
        initObjects();

        Firebase.setAndroidContext(SP_Registration.this);
        firebase = new Firebase(Firebase_Server_URL);
        databaseReference = FirebaseDatabase.getInstance().getReference(Database_Path);

        SPSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Checkdata();
                GetDataFromEditText();
                SPDetails spDetails = new SPDetails();

                spDetails.setName(NameHolder);
                spDetails.setEmail(EmailHolder);
                spDetails.setPhoneNumber(NumberHolder);

                String SPRecordIDFromServer = databaseReference.push().getKey();

                databaseReference.child(SPRecordIDFromServer).setValue(spDetails);

                Toast.makeText(SP_Registration.this,"Data Inserted Successfully into Firebase Database", Toast.LENGTH_LONG).show();

            }
        });


    }

    private void GetDataFromEditText() {

        NameHolder = sptextInputEditTextName.getText().toString().trim();

        NumberHolder = sptextInputEditTextEmail.getText().toString().trim();
        EmailHolder = sptextInputEditTextPhoneNumber.getText().toString().trim();
    }

    private void Checkdata() {

        if (!inputValidation.isInputEditTextFilledok(sptextInputEditTextName,  getString(R.string.error_message_name))) {
            return;
        }
        if (!inputValidation.isInputEditTextEmaiok(sptextInputEditTextEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextphnook(sptextInputEditTextPhoneNumber,  getString(R.string.error_message_name))) {
            return;
        }
    }


    private void initObjects() {

        inputValidation = new InputValidation(activity);
    }


    private void initViews() {


        sptextInputEditTextName = (EditText) findViewById(R.id.SPname);
        sptextInputEditTextEmail = (EditText) findViewById(R.id.SPEmail);
        sptextInputEditTextPhoneNumber = (EditText) findViewById(R.id.SPPhonenumber);


        SPSubmitButton = (AppCompatButton) findViewById(R.id.SPRegisterbtn);


    }
}
