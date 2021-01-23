package com.example.noname.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.noname.Model.LichKham_BacSi;
import com.example.noname.Model.QuanLyBenhNhan_BacSi;
import com.example.noname.R;

import java.util.List;

public class QuanLyBenhNhan_BacSi_Adapter extends ArrayAdapter<QuanLyBenhNhan_BacSi> {
    Activity context;
    int resource;
    List<QuanLyBenhNhan_BacSi> objects;
    public QuanLyBenhNhan_BacSi_Adapter(@NonNull Activity context, int resource, @NonNull List<QuanLyBenhNhan_BacSi> objects) {
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

        TextView txtTenBenhNhan= row.findViewById(R.id.txtTenBenhNhan_CustomLv_BacSi_QuanLyBenhNhan);
        TextView txtSoDienThoai=row.findViewById(R.id.txtSoDienThoai_CustomLv_BacSi_QuanLyBenhNhan);
        TextView txtNgayKham=row.findViewById(R.id.txtNgayKham_CustomLv_BacSi_QuanLyBenhNhan);
        TextView txtThoiGianKham=row.findViewById(R.id.txtThoiGianKham_CustomLv_BacSi_QuanLyBenhNhan);

        QuanLyBenhNhan_BacSi quanLyBenhNhan=this.objects.get(position);
        txtTenBenhNhan.setText(quanLyBenhNhan.getTenBenhNhan());
        txtSoDienThoai.setText(quanLyBenhNhan.getSoDienThoai());
        txtNgayKham.setText(quanLyBenhNhan.getNgayKham());
        txtThoiGianKham.setText(quanLyBenhNhan.getThoiGianKham());

        return row;
    }
}
