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

public class ThongTinDich extends AppCompatActivity {

    private WebView webViewThongTinDich;
    ImageButton imgBack_ThongTinDich;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ncovi_thong_tin_dich);

        imgBack_ThongTinDich=findViewById(R.id.imgBack_ThongTinDich);
        imgBack_ThongTinDich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ThongTinDich.this, BenhNhan_NCOVI.class));
            }
        });

        webViewThongTinDich=findViewById(R.id.webViewThongTinDich);
        webViewThongTinDich.setWebViewClient(new WebViewClient());
        webViewThongTinDich.loadUrl("https://news.google.com/covid19/map?hl=vi&mid=%2Fm%2F01crd5&gl=VN&ceid=VN%3Avi&fbclid=IwAR02PSt2UpyqfJFDUtWcnBjNacdxYQz5LVA-VgFdaSoPBYLM_p6P7QNSwDs");
        webViewThongTinDich.getSettings().setDomStorageEnabled(true);

    }

    @Override
    public void onBackPressed() {
        if(webViewThongTinDich.canGoBack())
        {
            webViewThongTinDich.goBack();
        }
        else {
            super.onBackPressed();
        }
    }

}