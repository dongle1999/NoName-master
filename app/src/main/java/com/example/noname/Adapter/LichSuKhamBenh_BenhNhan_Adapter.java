package com.example.noname.Adapter;

import android.app.Activity;
import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.noname.Model.LichSuKhamBenh_BenhNhan;
import com.example.noname.R;

import java.util.List;

public class LichSuKhamBenh_BenhNhan_Adapter extends ArrayAdapter<LichSuKhamBenh_BenhNhan> {

    Activity context;
    int resource;
    List<LichSuKhamBenh_BenhNhan> objects;

    public LichSuKhamBenh_BenhNhan_Adapter(@NonNull Activity context, int resource, @NonNull List<LichSuKhamBenh_BenhNhan> objects) {
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

        TextView txtTenBenhNhan=row.findViewById(R.id.txtTenBenhNhan_CustomLv_BenhNhan_LichSuKhamBenh);
        TextView txtBacSiKham=row.findViewById(R.id.txtBacSiKham_CustomLv_BenhNhan_LichSuKhamBenh);
        TextView txtKhoa=row.findViewById(R.id.txtKhoa_CustomLv_BenhNhan_LichSuKhamBenh);
        TextView txtNgay=row.findViewById(R.id.txtNgayDangKy_CustomLv_BenhNhan_LichSuKhamBenh);

        LichSuKhamBenh_BenhNhan lichSu=this.objects.get(position);
        txtTenBenhNhan.setText(lichSu.getTenBenhNhan());
        txtBacSiKham.setText(lichSu.getBacSiKham());
        txtKhoa.setText(lichSu.getKhoa());
        txtNgay.setText(lichSu.getNgayDangKy());



        return row;
    }
}
