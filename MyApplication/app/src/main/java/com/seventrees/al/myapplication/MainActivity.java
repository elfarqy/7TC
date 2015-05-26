package com.seventrees.al.myapplication;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    bangunDatar bd = new bangunDatar("");
    hitungBangun hb = new hitungBangun(0,0);
    Button encapsulasi, inheritance;
    EditText nama,panjang,lebar;
    TextView namaBangun,lebarBangun,panjangBangun;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        encapsulasi = (Button) findViewById(R.id.encapsulasi);
        inheritance = (Button) findViewById(R.id.inheritance);
        nama = (EditText)findViewById(R.id.Nama);
        namaBangun = (TextView)findViewById(R.id.namaBangun);
        panjang = (EditText)findViewById(R.id.panjang);
        lebar = (EditText)findViewById(R.id.lebar);
        lebarBangun = (TextView)findViewById(R.id.lebarBangun);
        panjangBangun = (TextView)findViewById(R.id.panjangBangun);

        encapsulasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String hasil = bd.setNama(nama.getText().toString());
                namaBangun.setVisibility(View.VISIBLE);
                namaBangun.setText(hasil);
            }
        });

        inheritance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hitungBangun hbNew = new hitungBangun(Double.parseDouble(panjang.getText().toString()),Double.parseDouble(lebar.getText().toString()));
                lebarBangun.setVisibility(View.VISIBLE);
                panjangBangun.setVisibility(View.VISIBLE);
                lebarBangun.setText(String.valueOf(hbNew.lebar));
                panjangBangun.setText(String.valueOf(hbNew.panjang));

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
