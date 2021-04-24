package com.example.noname.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.noname.Model.LichSuTiepXuc_BlueScan;
import com.example.noname.R;

import java.util.List;

public class LichSuTiepXuc_BlueScan_Adapter extends ArrayAdapter<LichSuTiepXuc_BlueScan> {

    Activity context;
    int resource;
    List<LichSuTiepXuc_BlueScan> objects;

    public LichSuTiepXuc_BlueScan_Adapter(@NonNull Activity context, int resource, @NonNull List<LichSuTiepXuc_BlueScan> objects) {
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

        TextView txtID=row.findViewById(R.id.txtID_CustomLv_LichSuTiepXuc_BlueScan);

        LichSuTiepXuc_BlueScan id=this.objects.get(position);
        txtID.setText(id.getId_Blt());

        return row;
    }
}
