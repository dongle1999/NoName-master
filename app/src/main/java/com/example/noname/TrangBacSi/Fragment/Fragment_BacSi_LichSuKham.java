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

import com.example.noname.Adapter.LichSuKham_BacSi_Adapter;
import com.example.noname.Model.LichSuKham_BacSi;
import com.example.noname.R;

import java.util.ArrayList;

public class Fragment_BacSi_LichSuKham extends Fragment {

    ListView lvLichSuKham;
    ArrayList<LichSuKham_BacSi>dsLichSuKham;
    LichSuKham_BacSi_Adapter lichSuKhamAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_bac_si_lich_su_kham, container,false);

        lvLichSuKham=view.findViewById(R.id.lvFragment_BacSi_LichSuKham);

        dsLichSuKham=new ArrayList<>();

        dsLichSuKham.add(new LichSuKham_BacSi(1, "Nguyễn Văn A","21316545","Đã hoàn thành","20/12/2020","8:00"));
        dsLichSuKham.add(new LichSuKham_BacSi(2, "Nguyễn Văn B","46544654","Chưa hoàn thành","25/12/2020","9:00"));

        lichSuKhamAdapter=new LichSuKham_BacSi_Adapter(getActivity(),
                R.layout.custom_listview_bac_si_lich_su_kham,dsLichSuKham);

        lvLichSuKham.setAdapter(lichSuKhamAdapter);
        lvLichSuKham.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getActivity(),BacSi_ChiTiet_LichSuKham.class));
            }
        });

        return view;
    }
}