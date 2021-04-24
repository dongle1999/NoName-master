package com.example.noname.Blutooth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.noname.R;

public class LichSuTiepXuc extends AppCompatActivity {

    ImageButton imgBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lich_su_tiep_xuc);
        controls();
        events();

    }
    private void events() {
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LichSuTiepXuc.this,BlueScan.class));
            }
        });
    }

    private void controls() {
        imgBack=findViewById(R.id.imgBack_LichSuTiepXuc);
    }
}