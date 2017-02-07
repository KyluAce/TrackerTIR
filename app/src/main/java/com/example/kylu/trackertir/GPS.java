package com.example.kylu.trackertir;
import android.app.Activity;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class GPS extends Activity
{
    public EditText wysylanie;
    public Button Save;
    public static String czestotliwoscGps;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gps_options);

        wysylanie = (EditText) findViewById(R.id.editText3);
        Save = (Button)findViewById(R.id.saveGps);
        DisplayMetrics dm2 = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm2);
        int width = dm2.widthPixels;
        int height = dm2.heightPixels;
        getWindow().setLayout((int) (width* 0.8),(int)(height* 0.6));


        Save.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view)
            {
                czestotliwoscGps = wysylanie.getText().toString();

                Intent intent = new Intent(GPS.this, Ustawienia.class);
                startActivity(intent);
            }
        });


    }

}
