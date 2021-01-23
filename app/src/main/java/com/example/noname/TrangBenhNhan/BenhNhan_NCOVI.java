package com.example.noname.TrangBenhNhan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.noname.TrangBenhNhan.NCOVI.HuongDanPhongDich;
import com.example.noname.TrangBenhNhan.NCOVI.KhaiBaoYTe;
import com.example.noname.TrangBenhNhan.NCOVI.ThongTinDich;
import com.example.noname.R;

public class BenhNhan_NCOVI extends AppCompatActivity {

    ImageButton imgBack_NCOVI;
    TextView txtThongTinDich, txtKhaiBaoYTe, txtHuongDanPhongDich;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_benh_nhan_ncovi);
        controls();
        events();
    }

    private void events() {
        imgBack_NCOVI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BenhNhan_NCOVI.this, Nav_TrangBenhNhan.class));
            }
        });
        txtThongTinDich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BenhNhan_NCOVI.this, ThongTinDich.class));
            }
        });
        txtKhaiBaoYTe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BenhNhan_NCOVI.this, KhaiBaoYTe.class));
            }
        });
        txtHuongDanPhongDich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BenhNhan_NCOVI.this, HuongDanPhongDich.class));
            }
        });
    }

    private void controls() {
        imgBack_NCOVI=findViewById(R.id.imgBack_NCOVI);
        txtThongTinDich=findViewById(R.id.txtThongTinDich);
        txtKhaiBaoYTe=findViewById(R.id.txtKhaiBaoYTe);
        txtHuongDanPhongDich=findViewById(R.id.txtHuongDanPhongDich);
    }
}