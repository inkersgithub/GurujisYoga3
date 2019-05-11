package in.inkers.gurujisyoga;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import static android.view.animation.AnimationUtils.loadAnimation;

public class splashScreen extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    String langdata,langtran;
    TextView txtv;
    ImageView load;
    Animation lod;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        ActionBar myBar=getSupportActionBar();
        myBar.hide();

        sharedPreferences = getSharedPreferences("gurujiapp",MODE_PRIVATE);
        langdata=sharedPreferences.getString("lang","");
        txtv = findViewById(R.id.textView2);
        load = findViewById(R.id.imageView2);
        if(langdata.equalsIgnoreCase("mal")) {
            txtv.setText("ഗുരുജീസ് യോഗ");
        }
        lod = loadAnimation(splashScreen.this, R.anim.rotate);
        load.startAnimation(lod);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if(langdata.length()>0) {
                    Intent i = new Intent(splashScreen.this, MainActivity.class).putExtra("parseLng", "done");
                    startActivity(i);
                    finish();
                }
                else
                {
                    Intent i = new Intent(splashScreen.this, LangActivity.class).putExtra("parseLng", "fail");
                    startActivity(i);
                    finish();
                }

            }
        }, 1500);


    }
}
