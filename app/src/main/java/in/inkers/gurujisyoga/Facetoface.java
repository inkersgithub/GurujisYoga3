package in.inkers.gurujisyoga;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.PermissionRequest;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Facetoface extends AppCompatActivity {

    private static final int MY_PERMISSIONS_RECORD_AUDIO = 1;
    private static final int MY_PERMISSIONS_CEM = 1;
    private static final int MY_PERMISSIONS_WRITE_EXTERNAL_STORAGE = 1;
    WebView webview;
    ProgressBar pay1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facetoface);

        ActionBar myBar=getSupportActionBar();
        myBar.hide();


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, MY_PERMISSIONS_RECORD_AUDIO);
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, MY_PERMISSIONS_CEM);
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSIONS_WRITE_EXTERNAL_STORAGE);
        }
        webview = (WebView)findViewById(R.id.webview);
        pay1 = findViewById(R.id.progressBar2);
        webview.setWebChromeClient(new ACWebchromeClient());
        WebSettings set = webview.getSettings();
        set.setJavaScriptEnabled(true);
        webview.getSettings().setMediaPlaybackRequiresUserGesture(false);
        set.setBuiltInZoomControls(false);
        webview.loadUrl("https://test4.atulkrishnan.com:4443/confo2.html?roomId=5da765ac3e614745c8ee642f&usertype=participant&user_ref=123");
        webview.setBackgroundResource(R.color.colorAccent);
        webview.setBackgroundColor(0x00000000);
        webview.setVerticalScrollBarEnabled(false);
        webview.getSettings().setAppCachePath("/data/data/"+ getPackageName() +"/cache");
        webview.getSettings().setAppCacheEnabled(true);
        webview.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        webview.getSettings().setDomStorageEnabled(true);
        webview.getSettings().setAllowUniversalAccessFromFileURLs(true);
        webview.setWebViewClient(new WebClient2());


    }

    public class ACWebchromeClient extends WebChromeClient {


        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);


            if (newProgress >= 100) {
                pay1.setVisibility(View.GONE);

            } else {
                pay1.setVisibility(View.VISIBLE);

            }
        }

        @Override
        public void onPermissionRequest(PermissionRequest request) {
            request.grant(request.getResources());
        }

    }


    class WebClient2 extends WebViewClient {





        public boolean shouldOverrideUrlLoading(WebView view, String url) {



            if (url.contains("EXITCALL" )){


                finish();

            }
            else {
                view.loadUrl(url);
            }


            return true;
        }









    }
}
