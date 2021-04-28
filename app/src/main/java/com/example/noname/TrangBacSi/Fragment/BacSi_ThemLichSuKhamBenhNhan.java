package com.example.noname.TrangBacSi.Fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.noname.R;

public class BacSi_ThemLichSuKhamBenhNhan extends AppCompatActivity {

    Button btnThemLichSuKham;
    ImageButton imgBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bac_si_them_lich_su_kham_benh_nhan);
        controls();
        events();

    }


    private void events() {
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BacSi_ThemLichSuKhamBenhNhan.this,BacSi_ChiTiet_QuanLyHoSoBenhNhan.class));
            }
        });
    }

    private void controls() {
        btnThemLichSuKham=findViewById(R.id.btnThemLichSuKham_BacSi_ThemLichSuKhamBenhNhan);
        imgBack=findViewById(R.id.imgBack_BacSi_ThemLichSuKhamBenhNhan);
    }
}