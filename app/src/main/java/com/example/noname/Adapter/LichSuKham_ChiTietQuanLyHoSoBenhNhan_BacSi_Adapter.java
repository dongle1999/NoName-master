package com.example.noname.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.noname.Model.LichSuKham_ChiTietQuanLyHoSoBenhNhan_BacSi;
import com.example.noname.R;

import java.util.List;

public class LichSuKham_ChiTietQuanLyHoSoBenhNhan_BacSi_Adapter extends ArrayAdapter<LichSuKham_ChiTietQuanLyHoSoBenhNhan_BacSi> {

    Activity context;
    int resource;
    List<LichSuKham_ChiTietQuanLyHoSoBenhNhan_BacSi> objects;

    public LichSuKham_ChiTietQuanLyHoSoBenhNhan_BacSi_Adapter(@NonNull Activity context, int resource, @NonNull List<LichSuKham_ChiTietQuanLyHoSoBenhNhan_BacSi> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.objects=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=this.context.getLayoutInflater();
        View row=inflater.inflate(this.resource,null);

       TextView txttrangthai=row.findViewById(R.id.txtKhoa_CustomLv_BacSi_ChiTietQuanLyBenhNhan);
        TextView txtBacSiKham=row.findViewById(R.id.txtBacSiKham_CustomLv_BacSi_ChiTietQuanLyBenhNhan);
        TextView txtKhoa=row.findViewById(R.id.txtKhoa_CustomLv_BacSi_ChiTietQuanLyBenhNhan);
        TextView txtNgay=row.findViewById(R.id.txtNgayDangKy_CustomLv_BacSi_ChiTietQuanLyBenhNhan);

        LichSuKham_ChiTietQuanLyHoSoBenhNhan_BacSi lichSu=this.objects.get(position);
        txttrangthai.setText(lichSu.getTrangthai());
        txtBacSiKham.setText(lichSu.getBacSiKham());
        txtKhoa.setText(lichSu.getKhoa());
        txtNgay.setText(lichSu.getNgayDangKy());



        return row;
    }
}
