package com.example.kylu.trackertir;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Krzysztof on 05.11.2015.
 */
public class PopPass extends Activity
{
    public Button zatwierdz;
    public EditText haslo;
    public String password = "haslo";
    public String edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pop_pass);
        zatwierdz = (Button) findViewById(R.id.zatwierdz);
        haslo = (EditText) findViewById(R.id.pass);


        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * 0.8), (int) (height * 0.6));


        zatwierdz.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view){

                edit = haslo.getText().toString();

                if(password.equals(edit))
                {
                    Intent intent = new Intent(PopPass.this, Ustawienia.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "wrong password",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
