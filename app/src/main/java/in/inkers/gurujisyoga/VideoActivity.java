package in.inkers.gurujisyoga;

import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.VideoView;

public class VideoActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    String langdata,langtran;
    ImageView water;
    ProgressBar progressbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        ActionBar myBar=getSupportActionBar();
        myBar.hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        water = findViewById(R.id.wm);
        progressbar = findViewById(R.id.progressBar);
        sharedPreferences = getSharedPreferences("gurujiapp",MODE_PRIVATE);
        langdata=sharedPreferences.getString("lang","");

        if(langdata.equalsIgnoreCase("mal")) {
            water.setImageDrawable(getDrawable(R.drawable.watmal));
        }
        VideoView videoView = (VideoView)findViewById(R.id.videoView);
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);

        videoView.setVideoPath("http://gurujisyoga.com/wp-content/uploads/2019/02/NadiKriya_SD-1.mp4");

        videoView.start();

        progressbar.setVisibility(View.VISIBLE);

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {




                @Override
            public void onPrepared(MediaPlayer mp) {



                mp.start();

                mp.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {

                    @Override
                    public void onVideoSizeChanged(MediaPlayer mp, int arg1, int arg2) {
                        // TODO Auto-generated method stub
                        progressbar.setVisibility(View.GONE);
                        mp.start();
                    }
                });


            }
        });



    }
}
