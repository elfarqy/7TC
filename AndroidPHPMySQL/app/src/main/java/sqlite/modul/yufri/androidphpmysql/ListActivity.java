package sqlite.modul.yufri.androidphpmysql;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;

import android.content.Context;
import android.content.Intent;

import android.os.AsyncTask;
import android.os.Bundle;

import android.util.Log;

import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;


/**
 * Created by Yufri on 05/05/2015.
 */

public class ListActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    private ListView listViewBarang;
    private static final int REQUEST_CODE = 1;
    private ArrayList<HashMap<String, String>> mylist = new
            ArrayList<HashMap<String, String>>();
    private static final int ADD_ID = Menu.FIRST + 1;
    private static final int EDIT_ID = Menu.FIRST + 2;
    private static final int DELETE_ID = Menu.FIRST + 3;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        try {
            getDataBarang();
        } catch (Exception e) {
            Log.e("log_tag", "Error parsing data " + e.toString());
        }
    }

    /**
     * Class requestTask untuk implementasi class AscyncTask yang berfungsi untuk mengeksekusi
     * proses ambil data dari dalam database MySQL, ada 3 method utama dalam fungsi AscyncTask,
     * yaitu onPreExecute gunanya memunculkan progres bar atau proses tunggu ambil data,
     * doInBackground gunanya untuk meresponse data dari server database,
     * dan onPostExecute gunanya untuk mengeksekusi dan menampilkan data yang sudah diambil dari
     * database ke interface andoid.
     */
    private class requestTask extends AsyncTask<String, Void, String> {
        private ProgressDialog dialog;
        protected Context applicationContext;
        private String actionflag;

        @Override
        protected void onPreExecute() {
            this.dialog = ProgressDialog.show(applicationContext,
                    "Mengambil data dari server", "Tunggu...", true);
        }

        @Override
        protected String doInBackground(String... urls) {
            String response = "";
            response = getDataServer(urls[0]);
            return response;
        }

        @Override
        protected void onPostExecute(String result) {
            this.dialog.cancel();
            if (actionflag.equals("list")) {
                if (result.equals("1"))
                    setListData();
            } else if (actionflag.equals("hapus")) {
                getDataBarang();
            }
        }
    }

    public void getDataBarang() {
        mylist.clear();
        requestTask task = new requestTask();
        task.applicationContext = ListActivity.this;
        task.actionflag = "list";
        String url = "http://10.0.2.2/Barang/barang_lihat.php";
        task.execute(new String[]{url});
    }

    public String getDataServer(String url) {
        String sret = "1";
        JSONObject json = JSONFunction.getJSONfromURL(url);
        try {
            if (json.getString("errorcode").equals("0")) {
                Toast.makeText(getBaseContext(),
                        json.getString("errormsg"),
                        Toast.LENGTH_SHORT).show();
                sret = "0";
            }
            JSONArray barang = json.getJSONArray("data");
            for (int i = 0; i < barang.length(); i++) {
                HashMap<String, String> map = new HashMap<String, String>();
                JSONObject jsonobj = barang.getJSONObject(i);
                map.put("barang_id", jsonobj.getString("barang_id"));
                map.put("barang_nama", jsonobj.getString("barang_nama"));
                map.put("barang_merk", jsonobj.getString("barang_merk"));
                map.put("barang_harga", jsonobj.getString("barang_harga"));
                mylist.add(map);
            }
        } catch (JSONException e) {
            Log.e("log_tag", "Error parsing data " + e.toString());
        }
        return sret;
    }

    private void showToast(String msg, int flag) {
        Toast.makeText(getBaseContext(), "Result " + msg,
                Toast.LENGTH_LONG).show();
    }

    private void setListData() {
        listViewBarang = (ListView) findViewById(R.id.listBarang);
        ListAdapter adapter = new SimpleAdapter(this, mylist, R.layout.activity_row,
                new String[]{"barang_id", "barang_nama", "barang_merk", "barang_harga"},
                new int[]{R.id.textView_rowIdBarang, R.id.textView_rowNamaBarang,
                        R.id.textView_rowMerkBarang, R.id.textView_rowHargaBarang});
        listViewBarang.setAdapter(adapter);
        registerForContextMenu(listViewBarang);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        menu.add(Menu.NONE, ADD_ID, Menu.NONE, "Tambah")
                .setIcon(R.drawable.ic_launcher).setAlphabeticShortcut('a');
        menu.add(Menu.NONE, EDIT_ID, Menu.NONE, "Ubah")
                .setIcon(R.drawable.ic_launcher).setAlphabeticShortcut('e');
        menu.add(Menu.NONE, DELETE_ID, Menu.NONE, "Hapus")
                .setIcon(R.drawable.ic_launcher).setAlphabeticShortcut('d');
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo) item
                        .getMenuInfo();
        int barangid = (int) info.id;
        HashMap<String, String> o = (HashMap<String, String>) listViewBarang.getItemAtPosition(barangid);
        switch (item.getItemId()) {
            case ADD_ID:
                tambahData(o, "tambah");
                return (true);
            case EDIT_ID:
                ubahData(o, "ubah");
                return (true);
            case DELETE_ID:
                hapusData(o);
                return (true);
        }
        return (super.onOptionsItemSelected(item));
    }

    private void tambahData(HashMap<String, String> o, String action) {
        callIntent(o, action);
    }

    private void ubahData(HashMap<String, String> o, String action) {
        Log.d("****ubahData*****", "barang_id:" + o.get("barang_id"));
        callIntent(o, action);
    }

    private void hapusData(HashMap<String, String> o) {
        requestTask task = new requestTask();
        task.applicationContext = ListActivity.this;
        task.actionflag = "hapus";
        String url = "http://10.0.2.2/Barang/barang_hapus.php?barang_id=" + o.get("barang_id");
        task.execute(new String[] { url });
    }

    private void callIntent(HashMap<String, String> o, String action) {
        Intent i = new Intent(ListActivity.this, TambahActivity.class);
        i.putExtra("action", action);
        i.putExtra("databarang", o);
        startActivityForResult(i, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            if (data.hasExtra("refreshflag")) {
                if (data.getExtras().getString("refreshflag").equals("1"))
                    getDataBarang();
            }
        }
    }
}
