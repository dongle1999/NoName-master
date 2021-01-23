package com.example.noname.TrangBenhNhan.NCOVI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.noname.R;
import com.example.noname.TrangBenhNhan.BenhNhan_NCOVI;

public class KhaiBaoYTe extends AppCompatActivity {

    private WebView webViewKhaiBaoYTe;
    ImageButton imgBack_KhaiBaoYTe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ncovi_khai_bao_y_te);

        imgBack_KhaiBaoYTe=findViewById(R.id.imgBack_KhaiBaoYTe);
        imgBack_KhaiBaoYTe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(KhaiBaoYTe.this, BenhNhan_NCOVI.class));
            }
        });

        webViewKhaiBaoYTe=findViewById(R.id.webViewKhaiBaoYTe);
        webViewKhaiBaoYTe.setWebViewClient(new WebViewClient());
        webViewKhaiBaoYTe.loadUrl("https://tokhaiyte.vn/");
        webViewKhaiBaoYTe.getSettings().setDomStorageEnabled(true);
    }

    @Override
    public void onBackPressed() {
        if(webViewKhaiBaoYTe.canGoBack())
        {
            webViewKhaiBaoYTe.goBack();
        }
        else{

            super.onBackPressed();
        }

    }


}