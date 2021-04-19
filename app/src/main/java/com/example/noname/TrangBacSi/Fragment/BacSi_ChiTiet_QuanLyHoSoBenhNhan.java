package com.example.noname.TrangBacSi.Fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.noname.R;

public class BacSi_ChiTiet_QuanLyHoSoBenhNhan extends AppCompatActivity {

    ImageButton imgBack;
    Button btnThemLichSuKhamBenh;
    TextView txtChinhSuaHoSo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bac_si_chi_tiet_quan_ly_ho_so_benh_nhan);

        controls();
        event();

    }


    private void controls() {
        imgBack = findViewById(R.id.imgBack_BacSi_ChiTietQuanLyBenhNhan);
        btnThemLichSuKhamBenh = findViewById(R.id.btnThemLichSuKhamBenh_BacSi_ChiTietQuanLyBenhNhan);
        txtChinhSuaHoSo = findViewById(R.id.txtChinhSuaHoSoBenhNhan_BacSi_ChiTietQuanLyBenhNhan);
    }

    private void event() {
//        imgBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(BacSi_ChiTiet_QuanLyHoSoBenhNhan.this, Fragment_BacSi_QuanLyBenhNhan.class));
//            }
//        });
        txtChinhSuaHoSo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BacSi_ChiTiet_QuanLyHoSoBenhNhan.this, BacSi_QuanLyBenhNhan_ChinhSuaHoSoBenhNhan.class));
            }
        });
    }
}