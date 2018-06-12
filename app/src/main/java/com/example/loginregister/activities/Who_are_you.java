package com.example.loginregister.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.GridLayout;

import com.example.loginregister.R;

public class Who_are_you extends AppCompatActivity {

    GridLayout mainGrid;


    CardView usercard,servicecard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_who_are_you);


        usercard = (CardView)findViewById(R.id.usercard);


        servicecard = (CardView)findViewById(R.id.servicecard);

        servicecard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Who_are_you.this,SP_Registration.class);
                startActivity(i);
            }
        });

        usercard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Who_are_you.this,LoginActivity.class);
                startActivity(i);
            }
        });
}
}
