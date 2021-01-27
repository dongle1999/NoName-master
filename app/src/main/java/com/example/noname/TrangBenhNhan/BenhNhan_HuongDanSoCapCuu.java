package com.example.noname.TrangBenhNhan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.noname.Adapter.HuongDanSoCapCuu_BenhNhan_Adapter;
import com.example.noname.Model.HuongDanSoCapCuu_BenhNhan;
import com.example.noname.R;

import java.util.ArrayList;

public class BenhNhan_HuongDanSoCapCuu extends AppCompatActivity {

    ListView lvHuongDanSoCapCuu;
    ArrayList<HuongDanSoCapCuu_BenhNhan>dsHuongDan;
    HuongDanSoCapCuu_BenhNhan_Adapter huongDanAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_benh_nhan_huong_dan_so_cap_cuu);
        controls();
        events();

        dsHuongDan=new ArrayList<>();

        dsHuongDan.add(new HuongDanSoCapCuu_BenhNhan(1,"Bầm tím mắt"));
        dsHuongDan.add(new HuongDanSoCapCuu_BenhNhan(2,"Bắn hóa chất vào mắt"));

        huongDanAdapter=new HuongDanSoCapCuu_BenhNhan_Adapter(this,
                R.layout.custom_listview_benh_nhan_huong_dan_so_cap_cuu,dsHuongDan);

        lvHuongDanSoCapCuu.setAdapter(huongDanAdapter);
    }

    private void events() {
        lvHuongDanSoCapCuu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(BenhNhan_HuongDanSoCapCuu.this,BenhNhan_ChiTiet_HuongDanSoCapCuu.class));
            }
        });
    }

    private void controls() {
        lvHuongDanSoCapCuu=findViewById(R.id.lvBenhNhan_HuongDanSoCapCuu);
    }
}