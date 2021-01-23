package com.example.noname.TrangBacSi.Fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.noname.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Fragment_BacSi_HoSoBacSi extends Fragment {

    TextView txtNamSinh;

    Calendar calendar=Calendar.getInstance();
    SimpleDateFormat sdfNgay=new SimpleDateFormat("dd/MM/yyyy");

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_bac_si_ho_so_bac_si, container,false);

        txtNamSinh=view.findViewById(R.id.txtNamSinh_Fragment_BacSi_HoSoBacSi);

        events();
        return view;
    }

    private void events() {
        txtNamSinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyHienThiNgay();
            }
        });
    }

    private void xuLyHienThiNgay() {
        DatePickerDialog.OnDateSetListener listener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR,year);
                calendar.set(Calendar.MONTH,month);
                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);

                txtNamSinh.setText(sdfNgay.format(calendar.getTime()));
            }
        };
        DatePickerDialog datePickerDialog=new DatePickerDialog(getActivity(), listener,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }
}