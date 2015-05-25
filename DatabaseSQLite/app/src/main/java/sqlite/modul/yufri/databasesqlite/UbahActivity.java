package sqlite.modul.yufri.databasesqlite;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Yufri on 30/04/2015.
 */
public class UbahActivity extends Activity implements OnClickListener {

    private DBDataSource dataSource;

    private long id;
    private String harga;
    private String merk;
    private String nama;

    private EditText editTextNama;
    private EditText editTextMerk;
    private EditText editTextHarga;

    private TextView textViewIdBarang;

    private Button buttonUbah;
    private Button buttonBatal;

    private Barang barang;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah_data);

        //buat sambungan baru ke database
        dataSource = new DBDataSource(this);
        dataSource.open();

        // ambil data barang dari extras
        Bundle bun = this.getIntent().getExtras();
        id = bun.getLong("id");
        harga = bun.getString("harga");
        merk = bun.getString("merk");
        nama = bun.getString("nama");

        //inisialisasi variabel
        editTextNama = (EditText) findViewById(R.id.editTextNama);
        editTextMerk = (EditText) findViewById(R.id.editTextMerk);
        editTextHarga = (EditText) findViewById(R.id.editTextHarga);
        textViewIdBarang = (TextView) findViewById(R.id.textViewIdBarang);

        //masukkan data-data barang tersebut ke field editor
        textViewIdBarang.append(String.valueOf(id));
        editTextNama.setText(nama);
        editTextMerk.setText(merk);
        editTextHarga.setText(harga);

        //set listener pada tombol
        buttonUbah = (Button) findViewById(R.id.buttonUbah);
        buttonBatal = (Button) findViewById(R.id.buttonBatal);

        buttonUbah.setOnClickListener(this);
        buttonBatal.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        // TODO Auto-generated method stub
        switch(view.getId()) {

            // apabila tombol save diklik (update barang)
            case R.id.buttonUbah:
                barang = new Barang();
                barang.setNamaBarang(editTextNama.getText().toString());
                barang.setMerkBarang(editTextMerk.getText().toString());
                barang.setHargaBarang(editTextHarga.getText().toString());
                barang.setId(id);
                dataSource.updateBarang(barang);
                Intent i = new Intent(UbahActivity.this, DataActivity.class);
                startActivity(i);
                UbahActivity.this.finish();
                dataSource.close();
                break;

            // apabila tombol cancel diklik, finish activity
            case R.id.buttonBatal:
                finish();
                dataSource.close();
                break;
        }
    }
}
