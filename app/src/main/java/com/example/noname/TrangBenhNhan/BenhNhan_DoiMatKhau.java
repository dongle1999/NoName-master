package com.example.noname.TrangBenhNhan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.noname.R;
import com.example.noname.TrangBenhNhan.Fragment.Fragment_BenhNhan_HoSoBenhNhan;

public class BenhNhan_DoiMatKhau extends AppCompatActivity {

    ImageButton imgBack;
    Button btnCapNhat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_benh_nhan_doi_mat_khau);
        controls();
        events();

    }

    private void events() {
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BenhNhan_DoiMatKhau.this, Fragment_BenhNhan_HoSoBenhNhan.class));
            }
        });
        btnCapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BenhNhan_DoiMatKhau.this, BenhNhan_DangNhapBenhNhan.class));
            }
        });
    }

    private void controls() {
        imgBack=findViewById(R.id.imgBack_BenhNhan_DoiMatKhau);
        btnCapNhat=findViewById(R.id.btnCapNhat_BenhNhan_DoiMatKhau);
    }
}