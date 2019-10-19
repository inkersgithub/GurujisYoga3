package in.inkers.gurujisyoga;

import android.app.ActionBar;
import android.content.ClipData;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.constraint.solver.widgets.WidgetContainer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView txtvw;
    String content="ഗുരുജീസ് യോഗ";
    SharedPreferences sharedPreferences;
    String langdata,url="file:///android_asset/";
    WebView webview;
    Animation slideLeftAnimation,slideRightAnimation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        slideLeftAnimation = AnimationUtils.loadAnimation(getBaseContext(), R.anim.left);
        slideRightAnimation = AnimationUtils.loadAnimation(getBaseContext(), R.anim.right);
        sharedPreferences = getSharedPreferences("gurujiapp",MODE_PRIVATE);
        langdata=sharedPreferences.getString("lang","");
        webview = findViewById(R.id.webview);
        webview.setWebViewClient(new WebClient());
        WebSettings set = webview.getSettings();
        webview.setHapticFeedbackEnabled(false);
        webview.setLongClickable(false);
        set.setJavaScriptEnabled(true);
        set.setBuiltInZoomControls(false);
        webview.setWebChromeClient(new ACWebchromeClient2());
        webview.setVerticalScrollBarEnabled(false);
        webview.getSettings().setAppCachePath("/data/data/"+ getPackageName() +"/cache");
        webview.getSettings().setAppCacheEnabled(true);
        webview.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        webview.getSettings().setDomStorageEnabled(true);
        webview.getSettings().setAllowUniversalAccessFromFileURLs(true);
        webview.setBackgroundColor(0x00000000);
        /*webview.loadUrl("file:///android_asset/"+langdata+"/index.html");*/
        webview.startAnimation(slideLeftAnimation);
        webview.loadUrl(url+langdata+"/index.html");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(langdata.equalsIgnoreCase("mal")) {
            getSupportActionBar().setTitle(content);
        }


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ytubeActivity.class).putExtra("yid","CtQYF3wlgL8");
                startActivity(intent);


            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerview = navigationView.getHeaderView(0);
        navigationView.setNavigationItemSelectedListener(this);
        Menu menu = navigationView.getMenu();
        txtvw = (TextView) headerview.findViewById(R.id.tttl);
        TextView tagline = (TextView) headerview.findViewById(R.id.textView);
        MenuItem m0 = menu.findItem(R.id.enrl);
        MenuItem m1 = menu.findItem(R.id.coruse);
        MenuItem m2 = menu.findItem(R.id.about);
        MenuItem m3 = menu.findItem(R.id.dis);
        MenuItem m4 = menu.findItem(R.id.news);
        MenuItem m5 = menu.findItem(R.id.cont);
        MenuItem m6 = menu.findItem(R.id.login);



        if(langdata.equalsIgnoreCase("mal")) {
            txtvw.setText(content);
            tagline.setText("ധ്യാനം ബുദ്ധിയുള്ള ജ്ഞാനം");
            m0.setTitle("അന്വേഷണം");
            m1.setTitle("കോഴ്സുകൾ");
            m2.setTitle("ഞങ്ങളെക്കുറിച്ച്");
            m3.setTitle("മാറ്റിയെടുക്കാവുന്ന രോഗങ്ങൾ");
            m4.setTitle("വാർത്തകൾ");
            m5.setTitle("ഞങ്ങളെ സമീപിക്കുക");
            m6.setTitle("ലോഗിൻ");

        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }

        if (webview.canGoBack()) {
            webview.startAnimation(slideRightAnimation);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    webview.startAnimation(slideLeftAnimation);
                    webview.goBack();

                }
            }, 500);



        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        if(langdata.equalsIgnoreCase("mal")) {
            getMenuInflater().inflate(R.menu.main2, menu);
        }
        else {
            getMenuInflater().inflate(R.menu.main, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent= new Intent(MainActivity.this,LangActivity.class);
            startActivity(intent);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.


        int id = item.getItemId();

        if (id == R.id.coruse) {
            webview.startAnimation(slideLeftAnimation);
            webview.loadUrl(url+langdata+"/cor.html");

        } else if (id == R.id.about) {
            webview.startAnimation(slideLeftAnimation);
            webview.loadUrl(url+langdata+"/about.html");

        } else if (id == R.id.dis) {
            webview.startAnimation(slideLeftAnimation);
            webview.loadUrl(url+langdata+"/dis.html");

        } else if (id == R.id.news) {

            webview.startAnimation(slideLeftAnimation);
            webview.loadUrl(url+langdata+"/news.html");

        }  else if (id == R.id.cont) {

            webview.startAnimation(slideLeftAnimation);
            webview.loadUrl(url+langdata+"/contact.html");

        }  else if (id == R.id.enrl) {

            webview.startAnimation(slideLeftAnimation);
            webview.loadUrl("https://app.gurujisyoga.com/enquiry-"+langdata);

        }else if (id == R.id.login) {

            Intent intent = new Intent(MainActivity.this,LoginActivity.class);
            startActivity(intent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public class ACWebchromeClient2 extends WebChromeClient {


        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);


            if (newProgress >= 100) {
                findViewById(R.id.avi2).setVisibility(View.GONE);

            } else {
                findViewById(R.id.avi2).setVisibility(View.VISIBLE);

            }
        }
    }
    class WebClient extends WebViewClient {
        public boolean shouldOverrideUrlLoading(WebView view, String url) {


           if (url.contains("PROMO.html")) {

                Intent intent = new Intent(MainActivity.this, VideoActivity.class);
                startActivity(intent);


            }


            else if (url.contains("youtube") || url.contains("youtu.be")) {

                String pattern = "(?<=watch\\?v=|/videos/|embed\\/|youtu.be\\/|\\/v\\/|\\/e\\/|watch\\?v%3D|watch\\?feature=player_embedded&v=|%2Fvideos%2F|embed%\u200C\u200B2F|youtu.be%2F|%2Fv%2F)[^#\\&\\?\\n]*";

                Pattern compiledPattern = Pattern.compile(pattern);
                Matcher matcher = compiledPattern.matcher(url); //url is youtube url for which you want to extract the id.
                if (matcher.find()) {

                Intent intent = new Intent(MainActivity.this,ytubeActivity.class);
                intent.putExtra("yid",matcher.group());
                startActivity(intent);
                }


            } else {

                view.loadUrl(url);
            }


            return true;
        }
    }

}
