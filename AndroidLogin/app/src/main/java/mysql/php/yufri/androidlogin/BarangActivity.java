package mysql.php.yufri.androidlogin;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
/**
 * Created by Yufri on 12/05/2015.
 */
public class BarangActivity extends ListActivity {

        /** Called when the activity is first created. */

        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            // Create an array of Strings, that will be put to our ListActivity
            String[] menu = new String[] { "Data Barang", "Tambah Barang", "Keluar"};

            //Menset nilai array ke dalam list adapater sehingga data pada array akan dimunculkan dalam list
            this.setListAdapter(new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1,menu));
        }

        @Override
        /**method ini akan mengoveride method onListItemClick yang ada pada class List Activity
         * method ini akan dipanggil apabilai ada salah satu item dari list menu yang dipilih
         */
        protected void onListItemClick(ListView l, View v, int position, long id) {
            super.onListItemClick(l, v, position, id);
            // Get the item that was clicked
            // Menangkap nilai text yang dklik
            Object o = this.getListAdapter().getItem(position);
            String pilihan = o.toString();
            // Menampilkan hasil pilihan menu dalam bentuk Toast
            tampilkanPilihan(pilihan);
        }
        /**
         * Tampilkan Activity sesuai dengan menu yang dipilih
         *
         */
    protected void tampilkanPilihan(String pilihan) {
        try {
            if (pilihan.equals("Data Barang")) {
                Toast.makeText(BarangActivity.this,"Anda Memilih: " + pilihan
                        + " , Actionnya belum dibuat", Toast.LENGTH_LONG).show();
            } else if (pilihan.equals("Tambah Barang")) {
                Toast.makeText(BarangActivity.this,"Anda Memilih: " + pilihan
                        + " , Actionnya belum dibuat", Toast.LENGTH_LONG).show();
            } else if (pilihan.equals("Keluar")) {
                finish();
            } else {
                Toast.makeText(BarangActivity.this,"Anda Memilih: " + pilihan
                        + " , Actionnya belum dibuat", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
