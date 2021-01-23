package com.example.noname.TrangBenhNhan.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.noname.R;
import com.example.noname.TrangBenhNhan.BenhNhan_ChatVoiBacSi;
import com.example.noname.TrangBenhNhan.BenhNhan_HuongDanSoCapCuu;
import com.example.noname.TrangBenhNhan.BenhNhan_PhanAnh;
import com.example.noname.TrangBenhNhan.BenhNhan_NCOVI;
import com.example.noname.TrangBenhNhan.BenhNhan_DangKyKhamBenh;

public class Fragment_BenhNhan_TrangChu extends Fragment {

    TextView txtNCOVI, txtHuongDanSoCapCuu, txtChatVoiBacSi, txtPhanAnh, txtDangKiKhamBenh;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_benh_nhan_trang_chu, container, false);
        txtNCOVI=v.findViewById(R.id.txtNCOVI);
        txtDangKiKhamBenh=v.findViewById(R.id.txtDangKiKhamBenh);
        txtHuongDanSoCapCuu=v.findViewById(R.id.txtHuongDanSoCapCuu);
        txtPhanAnh=v.findViewById(R.id.txtPhanAnh);
        txtChatVoiBacSi=v.findViewById(R.id.txtChatVoiBacSi);
        controls();
        events();
        return v;
    }

    private void events() {
        txtNCOVI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), BenhNhan_NCOVI.class));
            }
        });
        txtDangKiKhamBenh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), BenhNhan_DangKyKhamBenh.class));
            }
        });
        txtHuongDanSoCapCuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), BenhNhan_HuongDanSoCapCuu.class));
            }
        });

        txtPhanAnh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), BenhNhan_PhanAnh.class));
            }
        });
        txtChatVoiBacSi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), BenhNhan_ChatVoiBacSi.class));
            }
        });
    }


    private void controls() {

    }
}
