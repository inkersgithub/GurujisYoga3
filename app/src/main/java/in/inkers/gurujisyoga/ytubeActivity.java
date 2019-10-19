package in.inkers.gurujisyoga;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.pierfrancescosoffritti.androidyoutubeplayer.player.PlayerConstants;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.YouTubePlayerView;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.listeners.YouTubePlayerInitListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.listeners.YouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.utils.YouTubePlayerTracker;

import static com.pierfrancescosoffritti.androidyoutubeplayer.player.PlayerConstants.PlayerState.PAUSED;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class ytubeActivity extends AppCompatActivity {
    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    private static final boolean AUTO_HIDE = true;

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;


    /*requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

*/


    YouTubePlayerView youTubePlayerView;
    LinearLayout Loader;
    YouTubePlayer youTubePlayer;
    YouTubePlayerListener listener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_ytube);
        getSupportActionBar().hide();
        /*getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);*/

        youTubePlayerView = findViewById(R.id.youtube_player_view);
        findViewById(R.id.youtube_button).setVisibility(View.GONE);
        findViewById(R.id.video_current_time).setVisibility(View.GONE);
        findViewById(R.id.seek_bar).setVisibility(View.GONE);
        findViewById(R.id.video_duration).setVisibility(View.GONE);
        findViewById(R.id.live_video_indicator).setVisibility(View.GONE);
        findViewById(R.id.fullscreen_button).setVisibility(View.GONE);
        findViewById(R.id.controls_root).setVisibility(View.GONE);
        Loader = findViewById(R.id.loader);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Loader.setVisibility(View.GONE);
                findViewById(R.id.button).setVisibility(View.VISIBLE);
            }
        }, 8000);


        ybplayer();





        youTubePlayerView.getPlayerUIController().showUI(true);
        youTubePlayerView.enterFullScreen();





    }

    void ybplayer(){

        youTubePlayerView.initialize(new YouTubePlayerInitListener() {
            @Override
            public void onInitSuccess(@NonNull final YouTubePlayer initializedYouTubePlayer) {
                initializedYouTubePlayer.addListener(new AbstractYouTubePlayerListener() {
                    @Override
                    public void onReady() {
                        String yid = getIntent().getStringExtra("yid");
                        initializedYouTubePlayer.loadVideo(yid, 0);

                    }



                });
            }



        }, true);
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        youTubePlayerView.release();

    }




}
