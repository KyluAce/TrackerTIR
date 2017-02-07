package com.example.kylu.trackertir;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Krzysztof on 05.11.2015.
 */
public class PopCounter extends Activity
{
    public static String licznikWys;
    public EditText licznik;
    public Button saveWys;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.counter);
        licznik = (EditText) findViewById(R.id.editText3);
        saveWys = (Button) findViewById(R.id.button6);
        DisplayMetrics dm2 = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm2);
        int width = dm2.widthPixels;
        int height = dm2.heightPixels;

        getWindow().setLayout((int) (width* 0.8),(int)(height* 0.6));


        saveWys.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                licznikWys = licznik.getText().toString();

                Intent intent = new Intent(PopCounter.this, Ustawienia.class);
                startActivity(intent);
            }
        });
    }
}
