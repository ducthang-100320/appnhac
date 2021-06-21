package com.example.btl_music4b.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.btl_music4b.R;

public class MainActivity extends AppCompatActivity {
    View view;
    Intent intent;
    Animation animation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view = findViewById(R.id.logomain);
        animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.anim_intent_in_main);
        intent = new Intent(MainActivity.this, HomeActivity.class);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                view.setVisibility(View.VISIBLE);
                view.startAnimation(animation);
            }
        }, 2500);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(intent);
                finish();
            }
        }, 5000);
    }
}