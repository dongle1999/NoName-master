package com.example.noname.TrangBenhNhan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.noname.R;

public class BenhNhan_DangKyKhamBenh extends AppCompatActivity {

    ImageButton imgBack_DangKyKhamBenh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_benh_nhan_dang_ky_kham_benh);
        controls();
        events();
    }

    private void events() {
        imgBack_DangKyKhamBenh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BenhNhan_DangKyKhamBenh.this, Nav_TrangBenhNhan.class));
            }
        });
    }

    private void controls() {
        imgBack_DangKyKhamBenh=findViewById(R.id.imgBack_DangKyKhamBenh);
    }
}