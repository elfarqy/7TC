package sqlite.modul.yufri.databasesqlite;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Yufri on 30/04/2015.
 */
public class TambahActivity extends Activity implements OnClickListener {

    //inisilisasi elemen-elemen pada layout
    private Button buttonTambah;
    private EditText editTextNama;
    private EditText editTextMerk;
    private EditText editTextHarga;

    //inisialisasi kontroller/Data Source
    private DBDataSource dataSource;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_data);

        buttonTambah = (Button) findViewById(R.id.buttonTambah);
        editTextNama = (EditText) findViewById(R.id.editTextNama);
        editTextMerk = (EditText) findViewById(R.id.editTextMerk);
        editTextHarga = (EditText) findViewById(R.id.editTextHarga);

        // instanstiasi kelas DBDataSource
        dataSource = new DBDataSource(this);

        //membuat sambungan baru ke database
        dataSource.open();

        buttonTambah.setOnClickListener(this);
    }

    //KETIKA Tombol Submit Diklik
    @Override
    public void onClick(View view) {

        // Inisialisasi data barang
        String nama = null;
        String merk = null;
        String harga = null;
        @SuppressWarnings("unused")

        //inisialisasi barang baru (masih kosong)
        Barang barang = null;
        if(editTextNama.getText()!=null && editTextMerk.getText()!=null && editTextHarga.getText()!=null) {
            /* jika field nama, merk, dan harga tidak kosong
             * maka masukkan ke dalam data barang*/
            nama = editTextNama.getText().toString();
            merk = editTextMerk.getText().toString();
            harga = editTextHarga.getText().toString();
        }

        switch (view.getId()) {
            case R.id.buttonTambah:

            // insert data barang baru
            barang = dataSource.createBarang(nama, merk, harga);

            //konfirmasi kesuksesan
            Toast.makeText(this, "Masuk Barang\n" +
                    "Nama : " + barang.getNamaBarang() + "\n" +
                    "Merk : " + barang.getMerkBarang() + "\n" +
                    "Harga : Rp. " + barang.getHargaBarang(), Toast.LENGTH_LONG).show();
                break;
        }
    }
}
