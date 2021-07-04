package com.example.btl_music4b;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class AddBaiHat extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bai_hat);
        Toast.makeText(this, "Đây là màn hình thêm", Toast.LENGTH_SHORT).show();
    }
}