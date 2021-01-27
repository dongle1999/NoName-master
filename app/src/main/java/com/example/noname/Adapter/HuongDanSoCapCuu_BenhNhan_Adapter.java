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

import com.example.noname.Model.HuongDanSoCapCuu_BenhNhan;
import com.example.noname.R;

import java.util.List;

public class HuongDanSoCapCuu_BenhNhan_Adapter extends ArrayAdapter<HuongDanSoCapCuu_BenhNhan> {

    Activity context;
    int resource;
    List<HuongDanSoCapCuu_BenhNhan> objects;

    public HuongDanSoCapCuu_BenhNhan_Adapter(@NonNull Activity context, int resource, @NonNull List<HuongDanSoCapCuu_BenhNhan> objects) {
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

        TextView txtTenBenh=row.findViewById(R.id.txtBenh_CustomLv_BenhNhan_HuongDanSoCapCuu);

        HuongDanSoCapCuu_BenhNhan huongDan=this.objects.get(position);
        txtTenBenh.setText(huongDan.getTenBenh());

        return row;
    }
}
