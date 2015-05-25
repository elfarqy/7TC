package sqlite.modul.yufri.databasesqlite;

//deklarasi import package
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Yufri on 30/04/2015.
 */
public class DBHelper extends SQLiteOpenHelper {

    /** deklarasi konstanta-konstanta yang digunakan pada database, seperti nama tabel,
     nama-nama kolom, nama database, dan versi dari database **/
    public static final String TABLE_NAME = "barang";
    public static final String COLUMN_ID = "barang_id";
    public static final String COLUMN_NAME = "barang_nama";
    public static final String COLUMN_MERK = "barang_merk";
    public static final String COLUMN_HARGA = "barang_harga";
    private static final String db_name = "db_barang.db";
    private static final int db_version = 1;

    // Perintah SQL untuk membuat tabel database baru
    private static final String db_create = "CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_NAME + " varchar(50) not null, "
            + COLUMN_MERK + " varchar(50) not null, "
            + COLUMN_HARGA + " varchar(50) not null);";

    public DBHelper(Context context) {
        super(context, db_name, null, db_version);
        // Auto generated
    }

    //mengeksekusi perintah SQL di atas untuk membuat tabel database baru
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(db_create);
    }

    // dijalankan apabila ingin mengupgrade database
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
        Log.w(DBHelper.class.getName(), "Upgrading database from version " + i + " to "
                + i2 + ", which will destroy all old data");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
