package com.example.loginregister.activities;

import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.EditText;
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

    DatabaseReference databaseReference;
    public static final String Database_Path = "Service_Provider_Database";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sp__registration);
        getSupportActionBar().hide();

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
