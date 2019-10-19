package in.inkers.gurujisyoga;

import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    EditText usrname,pswd;
    String langdata;
    Button Login;

    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private String url = "http://192.168.43.48/axia/api/index.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ActionBar myBar=getSupportActionBar();
        myBar.hide();
        usrname = findViewById(R.id.uname);
        Login = findViewById(R.id.Lgn);
        pswd = findViewById(R.id.pwd);

        sharedPreferences = getSharedPreferences("gurujiapp",MODE_PRIVATE);
        langdata=sharedPreferences.getString("lang","");
        if(langdata.equalsIgnoreCase("mal")) {
            usrname.setHint("ഉപയോക്തൃനാമം");
            pswd.setHint("പാസ്സ്\u200Cവേർഡ്");
            Login.setText("ലോഗിൻ");


        }


        sendAndRequestResponse();

    }



    private void sendAndRequestResponse() {

        //RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(this);

        //String Request initialized
        mStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Toast.makeText(getApplicationContext(),"Response :" + response.toString(), Toast.LENGTH_LONG).show();//display the response on screen

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(LoginActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                Log.i("VOLLEY","Error :" + error.toString());
            }
        });

        mRequestQueue.add(mStringRequest);
    }
}