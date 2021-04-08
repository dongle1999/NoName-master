package com.example.noname.TrangBenhNhan.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.noname.Blutooth.Scanblu;
import com.example.noname.ChatBot.MainActivity;
import com.example.noname.R;
import com.example.noname.TrangBenhNhan.BenhNhan_HuongDanSoCapCuu;
import com.example.noname.TrangBenhNhan.BenhNhan_NCOVI;

public class Fragment_BenhNhan_TrangChu extends Fragment {

    LinearLayout btnNCOVI, btnHuongDanSoCapCuu, btnChatVoiBacSi, btnBluScan;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_benh_nhan_trang_chu, container, false);
        btnNCOVI=v.findViewById(R.id.btnNCOVI_BenhNhan_TrangChu);
        btnHuongDanSoCapCuu=v.findViewById(R.id.btnHuongDanSoCapCuu_BenhNhan_TrangChu);
        btnBluScan=v.findViewById(R.id.btnBluScan_BenhNhan_TrangChu);
        btnChatVoiBacSi=v.findViewById(R.id.btnChatVoiBacSi_BenhNhan_TrangChu);
        controls();
        events();
        return v;
    }

    private void events() {
        btnNCOVI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), BenhNhan_NCOVI.class));
            }
        });
        btnHuongDanSoCapCuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), BenhNhan_HuongDanSoCapCuu.class));
            }
        });

        btnBluScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), Scanblu.class));
            }
        });
        btnChatVoiBacSi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {startActivity(new Intent(getActivity(), MainActivity.class));
            }
        });
    }


    private void controls() {

    }
}
