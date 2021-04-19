package com.example.noname.TrangBacSi.Fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.noname.R;

public class BacSi_QuanLyBenhNhan_ChinhSuaHoSoBenhNhan extends AppCompatActivity {

    ImageButton imgBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bac_si_quan_ly_benh_nhan_chinh_sua_ho_so_benh_nhan);

        imgBack=findViewById(R.id.imgBack_BacSi_QuanLyBenhNhan_ChinhSuaHoSoBenhNhan);

//        imgBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(BacSi_QuanLyBenhNhan_ChinhSuaHoSoBenhNhan.this, BacSi_ChiTiet_QuanLyHoSoBenhNhan.class));
//            }
//        });
    }
}
