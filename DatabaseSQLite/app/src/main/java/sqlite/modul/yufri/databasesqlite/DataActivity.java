package sqlite.modul.yufri.databasesqlite;

import java.util.ArrayList;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

/**
 * Created by Yufri on 30/04/2015.
 */
public class DataActivity extends ListActivity implements AdapterView.OnItemLongClickListener {

    //inisialisasi kontroller
    private DBDataSource dataSource;

    //inisialisasi arraylist
    private ArrayList<Barang> values;

    private Button buttonUbahDialog;
    private Button buttonHapusDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        dataSource = new DBDataSource(this);
        // buka kontroller
        dataSource.open();

        // ambil semua data barang
        values = dataSource.getAllBarang();

        // masukkan data barang ke array adapter
        ArrayAdapter<Barang> adapter = new ArrayAdapter<Barang>(this,
                android.R.layout.simple_list_item_1, values);

        // set adapter pada list
        setListAdapter(adapter);

        // mengambil listview untuk diset onItemLongClickListener
        ListView lv = (ListView) findViewById(android.R.id.list);
        lv.setOnItemLongClickListener(this);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        //tampilkan alert dialog
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.activity_dialog);
        dialog.setTitle("Pilih Aksi");
        dialog.show();
        final Barang b = (Barang) getListAdapter().getItem(i);
        buttonUbahDialog = (Button) dialog.findViewById(R.id.buttonUbahDialog);
        buttonHapusDialog = (Button) dialog.findViewById(R.id.buttonHapusDialog);

        //apabila tombol edit diklik
        buttonUbahDialog.setOnClickListener(
                new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        switchToEdit(b.getId());
                        dialog.dismiss();
                    }
                }
        );

        //apabila tombol delete di klik
        buttonHapusDialog.setOnClickListener(
                new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Delete barang
                        dataSource.deleteBarang(b.getId());
                        dialog.dismiss();
                        finish();
                        startActivity(getIntent());
                    }
                }
        );

        return true;
    }

    //method untuk edit data
    public void switchToEdit(long id) {
        Barang b = dataSource.getBarang(id);
        Intent i = new Intent(DataActivity.this, UbahActivity.class);
        Bundle bun = new Bundle();
        bun.putLong("id", b.getId());
        bun.putString("nama", b.getNamaBarang());
        bun.putString("merk", b.getMerkBarang());
        bun.putString("harga", b.getHargaBarang());
        i.putExtras(bun);
        finale();
        startActivity(i);
    }

    //method yang dipanggil ketika edit data selesai
    public void finale() {
        DataActivity.this.finish();
        dataSource.close();
    }

    @Override
    protected void onResume() {
        dataSource.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        dataSource.close();
        super.onPause();
    }
}