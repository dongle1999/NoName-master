package com.example.noname;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.example.noname.R;

import com.example.noname.TrangBacSi.BacSi_DangNhapBacSi;
import com.example.noname.TrangBenhNhan.BenhNhan_DangNhapBenhNhan;

public class ChonNguoiDung extends AppCompatActivity {

    TextView txtChonNguoiDung_BenhNhan, txtChonNguoiDung_BacSi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chon_nguoi_dung);

        txtChonNguoiDung_BenhNhan=findViewById(R.id.txtChonNguoiDung_BenhNhan);
        txtChonNguoiDung_BacSi=findViewById(R.id.txtChonNguoiDung_BacSi);

        txtChonNguoiDung_BenhNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ChonNguoiDung.this, BenhNhan_DangNhapBenhNhan.class));
            }
        });

        txtChonNguoiDung_BacSi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ChonNguoiDung.this, BacSi_DangNhapBacSi.class));
            }
        });

    }
}