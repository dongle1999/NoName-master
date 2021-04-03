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

import com.example.noname.Adapter.HoSoBenhLy_BenhNhan_Adapter;
import com.example.noname.Model.HoSoBenhLy_BenhNhan;
import com.example.noname.R;

import java.util.ArrayList;

public class Fragment_BenhNhan_HoSoBenhLy extends Fragment {

    ListView lvHoSoBenhLy;
    ArrayList<HoSoBenhLy_BenhNhan>dsHoSo;
    HoSoBenhLy_BenhNhan_Adapter hoSoAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_benh_nhan_ho_so_benh_ly, container,false);

        lvHoSoBenhLy=view.findViewById(R.id.lvFragment_BenhNhan_HoSoBenhLy);

        dsHoSo=new ArrayList<>();

        dsHoSo.add(new HoSoBenhLy_BenhNhan(1,"1","30/03/2021","16:00"));
        dsHoSo.add(new HoSoBenhLy_BenhNhan(1,"2","30/04/2021","10:00"));

        hoSoAdapter=new HoSoBenhLy_BenhNhan_Adapter(getActivity(),
                R.layout.custom_listview_benh_nhan_ho_so_benh_ly,dsHoSo);

        lvHoSoBenhLy.setAdapter(hoSoAdapter);
        lvHoSoBenhLy.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getActivity(), BenhNhan_ChiTiet_HoSoBenhLy.class));
            }
        });


        return view;
    }

}
