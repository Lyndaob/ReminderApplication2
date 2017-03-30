package com.example.lyndaob.reminderapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashscreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);
        final ImageView im = (ImageView) findViewById(R.id.imageView);
        final Animation kh = AnimationUtils.loadAnimation(this, R.anim.rotate);
        im.startAnimation(kh);
        kh.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                finish();
                Intent a = new Intent(SplashscreenActivity.this,LoginActivity.class);
                startActivity(a);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
