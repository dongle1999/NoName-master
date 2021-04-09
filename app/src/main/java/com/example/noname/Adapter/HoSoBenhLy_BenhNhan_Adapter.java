package com.example.noname.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.noname.Model.HoSoBenhLy_BenhNhan;
import com.example.noname.R;

import java.util.List;

public class HoSoBenhLy_BenhNhan_Adapter extends ArrayAdapter<HoSoBenhLy_BenhNhan> {

    Activity context;
    int resource;
    List<HoSoBenhLy_BenhNhan> objects;

    public HoSoBenhLy_BenhNhan_Adapter(@NonNull Activity context, int resource, @NonNull List<HoSoBenhLy_BenhNhan> objects) {
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

        TextView txtLanKham=row.findViewById(R.id.txtLanKham_CustomLv_BenhNhan_HoSoBenhLy);
        TextView txtNgayKham=row.findViewById(R.id.txtNgayKham_CustomLv_BenhNhan_HoSoBenhLy);
        TextView txtThoiGianKham=row.findViewById(R.id.txtThoiGianKham_CustomLv_BenhNhan_HoSoBenhLy);

        HoSoBenhLy_BenhNhan hoSo=this.objects.get(position);
        txtLanKham.setText(hoSo.getLanKham());
        txtNgayKham.setText(hoSo.getNgayKham());


        return row;
    }
}
