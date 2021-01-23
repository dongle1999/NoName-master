package com.example.noname.TrangBenhNhan.Fragment;

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

import com.example.noname.Adapter.LichSuKhamBenh_BenhNhan_Adapter;
import com.example.noname.Model.LichSuKhamBenh_BenhNhan;
import com.example.noname.R;

import java.util.ArrayList;

public class Fragment_BenhNhan_LichSuKhamBenh extends Fragment{

    ListView lvLichSuKhamBenh;
    ArrayList<LichSuKhamBenh_BenhNhan>dsLichSu;
    LichSuKhamBenh_BenhNhan_Adapter lichSuAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_benh_nhan_lich_su_kham_benh, container,false);

        lvLichSuKhamBenh=view.findViewById(R.id.lvFragment_BenhNhan_LichSuKhamBenh);

        dsLichSu=new ArrayList<>();

        dsLichSu.add(new LichSuKhamBenh_BenhNhan(1, "Nguyễn Văn A","Trần Văn Công","Chấn thương","12/12/2020"));
        dsLichSu.add(new LichSuKhamBenh_BenhNhan(2, "Nguyễn Văn A","Trịnh Đắc Hưng","Sản","1/1/2021"));

        lichSuAdapter=new LichSuKhamBenh_BenhNhan_Adapter(getActivity(),
                R.layout.custom_listview_benh_nhan_lich_su_kham_benh,dsLichSu);

        lvLichSuKhamBenh.setAdapter(lichSuAdapter);
        lvLichSuKhamBenh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getActivity(), BenhNhan_ChiTiet_LichSuKhamBenh.class));
            }
        });


        return view;
    }

}
