package sqlite.modul.yufri.androidphpmysql;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.util.Log;

/**
 * Created by Yufri on 05/05/2015.
 */

public class HttpHelper {

    /**
     * Method untuk Mengirimkan data kes erver event by button login diklik
     *
     * @paramview
    */
    public static String getRequest(String Url) {
        String sret="";
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(Url);
        try {
            HttpResponse response = client.execute(request);
            sret= request(response);
        } catch (Exception ex) {

        }
        return sret;
    }

    /**
     * Method untuk Menenrima data dari server
     *
     * @param response
     * @return
     */
    public static String request(HttpResponse response) {
        String result = "";
        try {
            InputStream in = response.getEntity().getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder str = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                str.append(line + "\n");
            }
            in.close();
            result = str.toString();
        } catch (Exception ex) {
            result = "Error";
        }
        return result;
    }
}