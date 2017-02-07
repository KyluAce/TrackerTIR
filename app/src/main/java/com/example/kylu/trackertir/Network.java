package com.example.kylu.trackertir;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

/**
 * Created by Krzysztof on 05.11.2015.
 */
public class Network extends Activity

{
    public Button saveNet;
    public EditText wysylanieNet;
    public static String czestotliwoscNet;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.network_options);
        wysylanieNet = (EditText) findViewById(R.id.editText3);
        DisplayMetrics dm2 = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm2);
        int width = dm2.widthPixels;
        int height = dm2.heightPixels;

        getWindow().setLayout((int) (width * 0.8), (int) (height * 0.6));


        saveNet.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                czestotliwoscNet = wysylanieNet.getText().toString();

                Intent intent = new Intent(Network.this, Ustawienia.class);
                startActivity(intent);
            }
        });
    }
}