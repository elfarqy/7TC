package com.seventrees.al.lifecycle;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
    String msg = "Aplikasi dalam : ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this,"The OnCreate() event",Toast.LENGTH_SHORT).show();
        Log.d(msg,"The OnCreate() event");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this,"The OnStart() event",Toast.LENGTH_SHORT).show();
        Log.d(msg,"The OnStart() event");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this,"The OnResume() event",Toast.LENGTH_SHORT).show();
        Log.d(msg,"The OnResume() event");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this,"The OnPause() event",Toast.LENGTH_SHORT).show();
        Log.d(msg,"The OnPause() event");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this,"The OnStop() event",Toast.LENGTH_SHORT).show();
        Log.d(msg,"The OnStop() event");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this,"The OnDestroy() event",Toast.LENGTH_SHORT).show();
        Log.d(msg,"The OnDestroy() event");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this,"The OnRestart() event",Toast.LENGTH_SHORT).show();
        Log.d(msg,"The OnRestart() event");
    }
}
