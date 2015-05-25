package sqlite.modul.yufri.databasesqlite;

/**
 * Created by Yufri on 30/04/2015.
 */
public class Barang {

    private long id;
    private String namaBarang;
    private String merkBarang;
    private String hargaBarang;

    public Barang() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public String getMerkBarang() {
        return merkBarang;
    }

    public void setMerkBarang(String merkBarang) {
        this.merkBarang = merkBarang;
    }

    public String getHargaBarang() {
        return hargaBarang;
    }

    public void setHargaBarang(String hargaBarang) {
        this.hargaBarang = hargaBarang;
    }

    @Override
    public String toString()
    {
        return "Barang : "+ namaBarang + " "+ merkBarang + " " + hargaBarang;
    }
}
