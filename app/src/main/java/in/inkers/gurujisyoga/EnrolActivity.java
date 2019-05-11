package in.inkers.gurujisyoga;

import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.design.widget.SnackbarContentLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EnrolActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    String langdata,Snktxt="Total Amount you have to pay is ₹";
    EditText e1,e2,e3,e4;
    Button Erl;
    CheckBox c1,c2,c3;
    TextView courses;
    Integer sum=0;
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enrol);

        ActionBar myBar=getSupportActionBar();
        myBar.hide();



        e1 = findViewById(R.id.name);
        e2 = findViewById(R.id.Email);
        e3 = findViewById(R.id.mobile);
        e4 = findViewById(R.id.address);
        courses = findViewById(R.id.crs);
        c1 = findViewById(R.id.kyoga);
        c2 = findViewById(R.id.tyoga);
        c3 = findViewById(R.id.myoga);
        Erl = findViewById(R.id.Enrl);



        sharedPreferences = getSharedPreferences("gurujiapp",MODE_PRIVATE);
        langdata=sharedPreferences.getString("lang","");
        if(langdata.equalsIgnoreCase("mal")) {

            e1.setHint("പേര്");
            e2.setHint("ഇമെയിൽ");
            e3.setHint("മൊബൈൽ");
            e4.setHint("വിലാസം");
            c1.setText("കുണ്ഡലിനി യോഗ");
            c2.setText("താമ്പൂല ക്രിയ");
            c3.setText("മഷിനോട്ടം");
            courses.setText("കോഴ്സുകൾ");
            Erl.setText("പങ്കെടുക്കുക");
            Snktxt="നിങ്ങൾ അടയ്ക്കേണ്ട മൊത്തം തുകയാണ് ₹";
        }


        c1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if (isChecked) {

                    sum = sum+6000;
                    addCheckedAmt(view);
                }
                else {
                    sum = sum-6000;
                    addCheckedAmt(view);
                }
            }
        });

        c2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if (isChecked) {

                    sum = sum+1101;
                    addCheckedAmt(view);
                }
                else {
                    sum = sum-1101;
                    addCheckedAmt(view);
                }
            }
        });

        c3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if (isChecked) {

                    sum = sum+20000;
                    addCheckedAmt(view);
                }
                else {
                    sum = sum-20000;
                    addCheckedAmt(view);
                }
            }
        });

        }



    public void addCheckedAmt (View view)
    {
        if(sum>0) {
            Snackbar.make(findViewById(R.id.myCoordinatorLayout), Snktxt+sum, Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
          /*  Toast.makeText(this, Snktxt+sum, Toast.LENGTH_SHORT).show();*/
        }
    }
}
