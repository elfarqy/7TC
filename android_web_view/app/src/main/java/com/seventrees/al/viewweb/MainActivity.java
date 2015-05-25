package com.seventrees.al.viewweb;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class MainActivity extends ActionBarActivity {
    WebView webviewku;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    //inisialisasi web view
        webviewku = (WebView)findViewById(R.id.webviewku);
    // konfigurasi untuk browser
        webviewku.getSettings().setLoadsImagesAutomatically(true);
        webviewku.getSettings().setJavaScriptEnabled(true);
        webviewku.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
    //setting web client
        webviewku.setWebViewClient(new Browserku());
    //url yang dipanggil
        webviewku.loadUrl("http://7trees.co");
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
    //mengatur ketika url diload
    private class Browserku extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
