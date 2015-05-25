package sqlite.modul.yufri.databasesqlite;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * Created by Yufri on 30/04/2015.
 */
public class MenuActivity extends Activity implements OnClickListener {

    //Pendeklarasian variabel button dan intent. Intent fungsinya untuk memanggil kelas lain.
    private Intent intent;
    private Button buttonTambahData;
    private Button buttonDataBarang;

    /** Method onCreate berfungsi untuk memanggil variable yang sudah dideklarasikan
        dan juga mengeset aksi dari button **/
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        buttonTambahData = (Button) findViewById(R.id.buttonTambahData);
        buttonDataBarang = (Button) findViewById(R.id.buttonDataBarang);

        buttonTambahData.setOnClickListener(this);
        buttonDataBarang.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            // Perintah untuk memanggil kelas TambahActivity (Tambah Data)
            case R.id.buttonTambahData:
                intent = new Intent(MenuActivity.this, TambahActivity.class);
                startActivity(intent);
                break;
            // Perintah untuk memanggil kelas DataActivity (Lihat Data)
            case R.id.buttonDataBarang:
                intent = new Intent(MenuActivity.this, DataActivity.class);
                startActivity(intent);
                break;
        }
    }
}
