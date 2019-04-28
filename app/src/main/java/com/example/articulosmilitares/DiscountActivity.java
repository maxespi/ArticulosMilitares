package com.example.articulosmilitares;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

public class DiscountActivity extends AppCompatActivity {

    private VideoView videoView;
    private Button btnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descuento);

        videoView = (VideoView)findViewById(R.id.videoView);
        btnExit = (Button)findViewById(R.id.btnExit);

        /*String path = "android.resource://"+getPackageName()+"/"+R.raw.pollos;
        videoView.setVideoURI(Uri.parse(path));*/

        videoView.setVideoPath("https://www.costachickens.cl/wp-content/uploads/2019/03/pollos.mp4");
        videoView.start();

        /*MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);*/

        videoView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(videoView.isPlaying()){
                    videoView.pause();
                }else{
                    videoView.start();
                }

                return false;
            }
        });

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.start();
            }
        });

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(MenuActivity.this, PrincipalActivity.class));
                finish();
            }
        });



    }
}
