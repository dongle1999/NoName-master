package com.example.noname.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.noname.Model.LichSuKham_BacSi;
import com.example.noname.R;

import java.util.List;

public class LichSuKham_BacSi_Adapter extends ArrayAdapter<LichSuKham_BacSi> {

    Activity context;
    int resource;
    List<LichSuKham_BacSi> objects;

    public LichSuKham_BacSi_Adapter(@NonNull Activity context, int resource, @NonNull List<LichSuKham_BacSi> objects) {
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


        TextView txtSoDienThoai=row.findViewById(R.id.txtSoDienThoai_CustomLv_BacSi_LichSuKham);
        TextView txtTrangThai=row.findViewById(R.id.txtTrangThai_CustomLv_BacSi_LichSuKham);
        TextView txtNgayKham=row.findViewById(R.id.txtNgayKham_CustomLv_BacSi_LichSuKham);


        LichSuKham_BacSi lichSuKham=this.objects.get(position);

        txtSoDienThoai.setText(lichSuKham.getSoDienThoai());
        txtTrangThai.setText(lichSuKham.getTrangThai());
        txtNgayKham.setText(lichSuKham.getNgayKham());


        return row;
    }
}
