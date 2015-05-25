package mysql.php.yufri.androidlogin;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;

public class MainActivity extends Activity implements OnClickListener {

    private EditText log_username, log_password;
    private Button register, login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        log_username = (EditText) findViewById(R.id.editText_log_username);
        log_password = (EditText) findViewById(R.id.editText_log_password);

        login = (Button) findViewById(R.id.button_login);
        register = (Button) findViewById(R.id.button_log_register);

        login.setOnClickListener(this);
        register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.button_login:
                proseslogin task = new proseslogin();
                task.execute();
                break;
            case R.id.button_log_register:
                Intent i = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(i);
                break;
        }
    }

    class proseslogin extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
            postParameters.add(new BasicNameValuePair("reg_username", log_username.getText().toString()));
            postParameters.add(new BasicNameValuePair("reg_password", log_password.getText().toString()));
            String response = null;
            try {
                response = JSONFunction.executeHttpPost("http://10.0.2.2/Login/login.php", postParameters);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return response;
        }

        @Override
        protected void onPostExecute(String response){
            String res = response.toString();
            res = res.trim();
            res = res.replaceAll("\\s+","");
            if (res.equals("1")) {
                Toast.makeText(MainActivity.this, "Anda Berhasil Login", Toast.LENGTH_LONG).show();
                Intent i = new Intent(MainActivity.this, BarangActivity.class);
                startActivity(i);
            } else {
                Toast.makeText(MainActivity.this, "Maaf Username dan Password Anda Salah", Toast.LENGTH_SHORT).show(); }
        }
    }
}
