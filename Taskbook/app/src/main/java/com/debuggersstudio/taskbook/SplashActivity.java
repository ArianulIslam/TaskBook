package com.debuggersstudio.taskbook;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

public class SplashActivity extends AppCompatActivity {


        ProgressBar progressBar;
        ProgressBarAnimation anim;
        Intent intent;
        private static int Splash_Time_out = 2000;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_splash);

            progressbarstatus();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent loginintent = new Intent(SplashActivity.this,LoginActvitity.class);
                    startActivity(loginintent);
                    finish();
                }
            },Splash_Time_out);


        }

    public boolean progressbarstatus(){


        progressBar = (ProgressBar)findViewById(R.id.progressBar);

        anim = new ProgressBarAnimation(progressBar,0,100);
        anim.setDuration(Splash_Time_out);
        progressBar.startAnimation(anim);

        progressBar.getProgressDrawable().setColorFilter(
                Color.RED, android.graphics.PorterDuff.Mode.SRC_IN);

        return true;
    }
}
