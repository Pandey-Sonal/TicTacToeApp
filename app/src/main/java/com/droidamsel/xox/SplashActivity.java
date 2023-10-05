package com.droidamsel.xox;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Animation glowInAnimation = AnimationUtils.loadAnimation(this, R.anim.animation);

        //INITIALIZATION
        ImageView imageView1 = findViewById(R.id.imageView1);
        ImageView imageView2 = findViewById(R.id.imageView2);
        ImageView imageView3 = findViewById(R.id.imageView3);

        //setting animation on each
        imageView1.startAnimation(glowInAnimation);
        imageView2.startAnimation(glowInAnimation);
        imageView3.startAnimation(glowInAnimation);

        // setting a callback to start the next activity when the animation finishes
        glowInAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(SplashActivity.this, Players.class));
                        finish();
                    }
                }, 900);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });
    }
}