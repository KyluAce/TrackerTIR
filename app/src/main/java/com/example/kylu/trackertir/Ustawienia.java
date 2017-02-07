package com.example.kylu.trackertir;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class Ustawienia extends ListActivity implements AdapterView.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ustawienia);

        String [] myItems= {"GPS options", "Network options", "Frequency of sending data", "Server"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>( this, android.R.layout.simple_list_item_1, myItems);
        ListView listView = (ListView)findViewById(android.R.id.list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);


    }

    public void onItemClick(AdapterView parent, View view, int position, long id) {
        if(position == 0){

            Intent intent = new Intent(Ustawienia.this, GPS.class);
            startActivity(intent);

        } else if(position == 1){

            Intent intent = new Intent(Ustawienia.this, Network.class);
            startActivity(intent);
        }
        else if(position == 2){

            Intent intent = new Intent(Ustawienia.this, PopCounter.class);
            startActivity(intent);
        }
        else if(position == 3){

            Intent intent = new Intent(Ustawienia.this, Pop1.class);
            startActivity(intent);
        }


    }
}