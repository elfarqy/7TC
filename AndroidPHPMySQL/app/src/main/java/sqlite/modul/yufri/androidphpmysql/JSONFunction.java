package sqlite.modul.yufri.androidphpmysql;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

/**
 * Created by Yufri on 05/05/2015.
 */
public class JSONFunction {
    public static JSONObject getJSONfromURL(String url) {
        InputStream is = null;
        String result = "0";
        JSONObject jArray = null;
        try {
            HttpParams httpParameters = new BasicHttpParams();
            int timeoutConnection = 60000;
            HttpConnectionParams. setConnectionTimeout(httpParameters,
                    timeoutConnection);
            int timeoutSocket = 60000;
            HttpConnectionParams. setSoTimeout(httpParameters,
                    timeoutSocket);
            HttpClient httpclient = new
                    DefaultHttpClient(httpParameters);
            HttpPost httppost = new HttpPost(url);
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();
        } catch (Exception e) {
            Log. e("log_tag", "Error intimeout http connection " +
                    e.toString());
        }

        // convert response to string
        try {
            BufferedReader reader = new BufferedReader(new
                    InputStreamReader(
                    is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            result = sb.toString();
        } catch (Exception e) {
            Log. e("log_tag", "Error converting result " + e.toString());
            result = "{\"errorcode\":\"0\"}";
        }
        try {
            jArray = new JSONObject(result);
        } catch (JSONException e) {
            Log. e("log_tag", "Error parsing data " + e.toString());
            result = "{\"errorcode\":\"0\"}";
            try {
                jArray = new JSONObject(result);
            } catch (JSONException e1) {
        // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
        return jArray;
    }

    public static String getStringJSONfromURL(String url) {
        InputStream is = null;
        String result = "0";

        // http post
        try {
            HttpParams httpParameters = new BasicHttpParams();
            int timeoutConnection = 60000;
            HttpConnectionParams. setConnectionTimeout(httpParameters,
                    timeoutConnection);
            int timeoutSocket = 60000;
            HttpConnectionParams. setSoTimeout(httpParameters,
                    timeoutSocket);
            HttpClient httpclient = new
                    DefaultHttpClient(httpParameters);
            HttpPost httppost = new HttpPost(url);
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();
        } catch (Exception e) {
            Log. e("log_tag", "Error intimeout http connection " +
                    e.toString());
        }

        // convert response to string
        try {
            BufferedReader reader = new BufferedReader(new
                    InputStreamReader(
                    is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            result = sb.toString();
        } catch (Exception e) {
            Log. e("log_tag", "Error converting result " + e.toString());
            result = "{\"errorcode\":\"0\"}";
        }
        return result;
    }
}
