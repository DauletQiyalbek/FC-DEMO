package com.example.fcdemo.Basic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.fcdemo.Adapter.SliderAdapter;
import com.example.fcdemo.MainActivity;
import com.example.fcdemo.R;

public class OnBoardingActivity extends AppCompatActivity {


    ViewPager viewPager;
    Button skip_btn,next_btn,get_started_btn;
    LinearLayout dotsLayout;
    SliderAdapter sliderAdapter;
    TextView[] dots;
    Animation bottom_anim;
    int currentPos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);

        dotsLayout = findViewById(R.id.dots);
        viewPager = findViewById(R.id.slider);
        get_started_btn = findViewById(R.id.get_started_btn);
        next_btn = findViewById(R.id.btn_next);
        skip_btn = findViewById(R.id.skip_btn);
        bottom_anim = AnimationUtils.loadAnimation(this,R.anim.bottom_anim);

        sliderAdapter = new SliderAdapter(this);
        viewPager.setAdapter(sliderAdapter);

        addDots(0);
        viewPager.addOnPageChangeListener(changeListener);

        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(currentPos + 1);
            }
        });

        skip_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OnBoardingActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }



    private void addDots(int position){

        currentPos = position;

        dots = new TextView[4];
        dotsLayout.removeAllViews();
        for(int i=0; i<dots.length; i++){
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(35);

            dotsLayout.addView(dots[i]);
        }
        if (dots.length > 0){
            dots[position].setTextColor(getResources().getColor(R.color.colorPrimary));
        }
    }

    ViewPager.OnPageChangeListener changeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDots(position);

            if (position == 0){
                get_started_btn.setVisibility(View.INVISIBLE);
                skip_btn.setVisibility(View.VISIBLE);
                next_btn.setVisibility(View.VISIBLE);
            }
            else if (position == 1){
                get_started_btn.setVisibility(View.INVISIBLE);
                next_btn.setVisibility(View.VISIBLE);
                skip_btn.setVisibility(View.VISIBLE);
            }
            else if (position == 2){
                get_started_btn.setVisibility(View.INVISIBLE);
                next_btn.setVisibility(View.VISIBLE);
                skip_btn.setVisibility(View.VISIBLE);
            }
            else {
                get_started_btn.setVisibility(View.VISIBLE);
                next_btn.setVisibility(View.INVISIBLE);
                skip_btn.setVisibility(View.INVISIBLE);
                get_started_btn.setAnimation(bottom_anim);
                get_started_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(OnBoardingActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}