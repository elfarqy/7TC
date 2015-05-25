package sqlite.modul.yufri.androidphpmysql;

import android.app.Activity;

import android.os.Bundle;
import android.os.AsyncTask;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import android.content.Intent;
import android.util.Log;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TambahActivity extends Activity implements View.OnClickListener {

    private TextView textViewNamaBarang, textViewMerkBarang, textViewHargaBarang;
    private EditText editTextNamaBarang, editTextMerkBarang, editTextHargaBarang;
    private Button buttonSimpanBarang;

    // Seusuaikan url dengan nama domain yang anda gunakan
    private String url = "";
    String action = "";
    String barangid = "";

    /**
     * Method yang dipanggil pada saat aplikaasi dijalankan
     * */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);

        textViewNamaBarang = (TextView) findViewById(R.id.textView_namaBarang);
        textViewMerkBarang = (TextView) findViewById(R.id.textView_merkBarang);
        textViewHargaBarang = (TextView) findViewById(R.id.textView_hargaBarang);

        editTextNamaBarang = (EditText) findViewById(R.id.editText_namaBarang);
        editTextMerkBarang = (EditText) findViewById(R.id.editText_merkBarang);
        editTextHargaBarang = (EditText) findViewById(R.id.editText_hargaBarang);

        buttonSimpanBarang = (Button) findViewById(R.id.button_simpanBarang);

        Bundle extras = getIntent().getExtras();
        if(extras == null) {
            return;
        }
        action = extras.getString("action");
        HashMap <String, String> databarang = (HashMap <String, String>) extras.get("databarang");
        if(action.equals("ubah")) {
            editTextNamaBarang.setText(databarang.get("barang_nama"));
            editTextMerkBarang.setText(databarang.get("barang_merk"));
            editTextHargaBarang.setText(databarang.get("barang_harga"));
            barangid = databarang.get("barang_id");
        }

        // daftarkan even onClick pada btnSave
        buttonSimpanBarang.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        try {
            if(action.equals("tambah")) {
                url = "http://10.0.2.2/Barang/barang_tambah.php";
            } else {
                url = "http://10.0.2.2/Barang/barang_ubah.php";
            }

            String namaBarang = URLEncoder.encode(editTextNamaBarang.getText().toString(),"utf-8");
            String merkBarang = URLEncoder.encode(editTextMerkBarang.getText().toString(),"utf-8");
            String hargaBarang = URLEncoder.encode(editTextHargaBarang.getText().toString(),"utf-8");

            url += "?barang_nama=" + namaBarang + "&barang_merk=" + merkBarang + "&barang_harga=" + hargaBarang;
            if (action.equals("ubah")){
                url+="&barang_id="+barangid;
            }
            Log.d("url**", "url:"+url);
            new RequestTask().execute(url);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void finish() {
        Intent data = new Intent();
        data.putExtra("refreshflag", "1");
        setResult(RESULT_OK, data);
        super.finish();
    }

    private void setResult(String result){
        Toast. makeText(getBaseContext(), result, Toast. LENGTH_LONG).show();
        finish();
    }

    class RequestTask extends AsyncTask<String, String, String>{

        @Override
        protected String doInBackground(String... uri) {
            String responseString = null;
            responseString= HttpHelper.getRequest(uri[0]);
            return responseString;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            setResult(result);
            //Do anything with response..
        }
    }
}
