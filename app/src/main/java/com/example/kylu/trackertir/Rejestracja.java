package com.example.kylu.trackertir;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class Rejestracja extends Activity {

    public EditText rejestracja;
    public static String numerR;
    public Button SaveNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rejestracja);
        rejestracja = (EditText)findViewById(R.id.editText);
        SaveNum = (Button)findViewById(R.id.button3);


        SaveNum.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view){

                numerR = rejestracja.getText().toString();

                Intent intent = new Intent(Rejestracja.this, Choice.class);
                startActivity(intent);
            }
        });

    }



}
