package com.example.noname.TrangBenhNhan.NCOVI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.noname.R;
import com.example.noname.TrangBenhNhan.BenhNhan_NCOVI;

public class HuongDanPhongDich extends AppCompatActivity {

    ImageButton imgBack_HuongDanPhongDich;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ncovi_huong_dan_phong_dich);

        imgBack_HuongDanPhongDich = findViewById(R.id.imgBack_HuongDanPhongDich);
        imgBack_HuongDanPhongDich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HuongDanPhongDich.this, BenhNhan_NCOVI.class));
            }
        });
    }


}
