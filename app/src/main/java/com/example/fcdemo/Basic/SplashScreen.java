package com.example.fcdemo.Basic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fcdemo.MainActivity;
import com.example.fcdemo.R;

public class SplashScreen extends AppCompatActivity {

    ImageView logo_img;
    TextView txt_from;
    Animation top_anim , bottom_anim;
    SharedPreferences sharedPreferences;

    private static int SPLASH_TIME = 3000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        logo_img = findViewById(R.id.logo_img);
        txt_from = findViewById(R.id.txt_from);

        top_anim = AnimationUtils.loadAnimation(this,R.anim.top_anim);
        bottom_anim = AnimationUtils.loadAnimation(this,R.anim.bottom_anim);

        logo_img.setAnimation(top_anim);
        txt_from.setAnimation(bottom_anim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                sharedPreferences = getSharedPreferences("OnBoardingScreen",MODE_PRIVATE);
                boolean isFirstTime = sharedPreferences.getBoolean("firstTime",true);
                if (isFirstTime){
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("firstTime",false);
                    editor.commit();

                    Intent intent = new Intent(SplashScreen.this, OnBoardingActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }


            }
        },SPLASH_TIME);

    }
}