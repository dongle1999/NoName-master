package com.example.noname.TrangBacSi.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.noname.Adapter.QuanLyBenhNhan_BacSi_Adapter;
import com.example.noname.Model.QuanLyBenhNhan_BacSi;
import com.example.noname.R;

import java.util.ArrayList;

public class Fragment_BacSi_QuanLyBenhNhan extends Fragment {

    ListView lvQuanLyBenhNhan;
    ArrayList<QuanLyBenhNhan_BacSi>dsQuanLy;
    QuanLyBenhNhan_BacSi_Adapter quanLyBenhNhanAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_bac_si_quan_ly_benh_nhan, container,false);

        lvQuanLyBenhNhan=view.findViewById(R.id.lvFragment_BacSi_QuanLyBenhNhan);

        dsQuanLy=new ArrayList<>();

        dsQuanLy.add(new QuanLyBenhNhan_BacSi(1,"Nguyễn Văn A","2315645","20/12/2020","8:00"));
        dsQuanLy.add(new QuanLyBenhNhan_BacSi(2,"Nguyễn Văn B","5654654","01/01/2020","10:00"));

        quanLyBenhNhanAdapter=new QuanLyBenhNhan_BacSi_Adapter(getActivity(),
                R.layout.custom_listview_bac_si_quan_ly_benh_nhan,dsQuanLy);

        lvQuanLyBenhNhan.setAdapter(quanLyBenhNhanAdapter);
        lvQuanLyBenhNhan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getActivity(),BacSi_ChiTiet_QuanLyBenhNhan.class));
            }
        });

        return view;
    }
}