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
public class Pop1 extends Activity
{
    public static String server;
    public EditText serwer;
    public Button saveServ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pop_up_serwer);
        serwer = (EditText) findViewById(R.id.editText2);
        saveServ = (Button)findViewById(R.id.button5);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width* 0.8),(int)(height* 0.6));

        saveServ.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                server = serwer.getText().toString();

                Intent intent = new Intent(Pop1.this, Ustawienia.class);
                startActivity(intent);
            }
        });
    }
}
