package in.inkers.gurujisyoga;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LangActivity extends AppCompatActivity {

    Button btnEng,btnMal;
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lang);

        ActionBar myBar=getSupportActionBar();
        myBar.hide();

        btnEng=findViewById(R.id.eng);
        btnEng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sharedPreferences = getSharedPreferences("gurujiapp",MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("lang", "eng"); // Storing string
                editor.commit();

                Intent intent= new Intent(LangActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnMal=findViewById(R.id.mal);
        btnMal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sharedPreferences = getSharedPreferences("gurujiapp",MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("lang", "mal"); // Storing string
                editor.commit();

                Intent intent= new Intent(LangActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }
}
