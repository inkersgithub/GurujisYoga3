package in.inkers.gurujisyoga;

import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    EditText usrname,pswd;
    String langdata;
    Button Login;
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

    }
}
