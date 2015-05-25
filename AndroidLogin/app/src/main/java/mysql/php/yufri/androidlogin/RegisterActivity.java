package mysql.php.yufri.androidlogin;

import java.util.ArrayList;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import android.app.Activity;
import android.os.Bundle;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Yufri on 12/05/2015.
 */
public class RegisterActivity extends Activity implements View.OnClickListener {

    private EditText reg_username, reg_password;
    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        reg_username = (EditText) findViewById(R.id.editText_reg_username);
        reg_password = (EditText) findViewById(R.id.editText_reg_password);

        register = (Button) findViewById(R.id.button_reg_register);
        register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        prosesdaftar task = new prosesdaftar();
        task.execute();
    }

    class prosesdaftar extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
            postParameters.add(new BasicNameValuePair("reg_username", reg_username.getText().toString()));
            postParameters.add(new BasicNameValuePair("reg_password", reg_password.getText().toString()));
            String response = null;
            try {
                response = JSONFunction.executeHttpPost("http://10.0.2.2/Login/register.php", postParameters);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return response;
        }

        @Override
        protected void onPostExecute(String response) {
            String res = response.toString();
            res = res.trim();
            res = res.replaceAll("\\s+","");
            if (res.equals("1")) Toast.makeText(RegisterActivity.this, "Data Tersimpan", Toast.LENGTH_LONG).show();
            else Toast.makeText(RegisterActivity.this, "Data Tersimpan ke Server", Toast.LENGTH_LONG).show();
        }
    }
}
