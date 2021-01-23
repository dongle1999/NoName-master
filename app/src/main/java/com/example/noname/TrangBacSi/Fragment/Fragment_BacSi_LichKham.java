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

import com.example.noname.Adapter.LichKham_BacSi_Adapter;
import com.example.noname.Model.LichKham_BacSi;
import com.example.noname.R;

import java.util.ArrayList;

public class Fragment_BacSi_LichKham extends Fragment {

    ListView lvLichKham;
    ArrayList<LichKham_BacSi>dsLichKham;
    LichKham_BacSi_Adapter lichKhamAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_bac_si_lich_kham, container,false);

        lvLichKham=view.findViewById(R.id.lvFragment_BacSi_LichKham);

        dsLichKham=new ArrayList<>();

        dsLichKham.add(new LichKham_BacSi(1,"Nguyễn Văn A","1231213122","20/12/2020","8:00"));
        dsLichKham.add(new LichKham_BacSi(2,"Nguyễn Văn B","4565464566","25/12/2020","9:00"));

        lichKhamAdapter=new LichKham_BacSi_Adapter(getActivity(),R.layout.custom_listview_bac_si_lich_kham,dsLichKham);

        lvLichKham.setAdapter(lichKhamAdapter);
        lvLichKham.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getActivity(),BacSi_ChiTiet_LichKham.class));
            }
        });

        return view;
    }
}