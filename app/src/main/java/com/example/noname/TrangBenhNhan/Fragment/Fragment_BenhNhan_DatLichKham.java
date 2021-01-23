package com.example.noname.TrangBenhNhan.Fragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.noname.R;
import com.example.noname.RequestHandler;
import com.example.noname.TrangBenhNhan.BenhNhan_DangNhapBenhNhan;
import com.example.noname.TrangBenhNhan.Nav_TrangBenhNhan;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Fragment_BenhNhan_DatLichKham extends Fragment {

    TextView txtNgay, txtGio;
    Button btnDatLich;
    Spinner khoa , bs ;
    Calendar calendar=Calendar.getInstance();
    SimpleDateFormat sdfNgay=new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat sdfGio=new SimpleDateFormat("HH:mm");
    ArrayList<String> a ;
    Spinner spKhoaKhamBenh, spBacSi;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_benh_nhan_dat_lich_kham, container,false);

        spKhoaKhamBenh=view.findViewById(R.id.spKhoaKhamBenh_BenhNhan_DatLichKham);
        spBacSi=view.findViewById(R.id.spChonBacSi_BenhNhan_DatLichKham);
        txtNgay=view.findViewById(R.id.txtNgay_BenhNhan_DatLichKham);
        txtGio=view.findViewById(R.id.txtGio_BenhNhan_DatLichKham);
        btnDatLich=view.findViewById(R.id.btnDatLich_BenhNhan_DatLichKham);
        khoa=view.findViewById(R.id.spKhoaKhamBenh_BenhNhan_DatLichKham);
        bs=view.findViewById(R.id.spChonBacSi_BenhNhan_DatLichKham);
        calendar=Calendar.getInstance();
        txtNgay.setText(sdfNgay.format(calendar.getTime()));
        txtGio.setText(sdfGio.format(calendar.getTime()));

        events();
        return view;

    }

    private void events() {
        txtNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xuLyHienThiNgay();
            }
        });
        txtGio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyHienThiGio();
            }
        });
    }

    private void xuLyHienThiGio() {
        TimePickerDialog.OnTimeSetListener listener=new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                calendar.set(Calendar.HOUR_OF_DAY,hourOfDay);
                calendar.set(Calendar.MINUTE,minute);
                txtGio.setText(sdfGio.format(calendar.getTime()));
            }
        };
        TimePickerDialog timePickerDialog=new TimePickerDialog(getActivity(),listener,
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                true);

        timePickerDialog.show();
    }

    private void xuLyHienThiNgay() {
        DatePickerDialog.OnDateSetListener listener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR,year);
                calendar.set(Calendar.MONTH,month);
                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);

                txtNgay.setText(sdfNgay.format(calendar.getTime()));
            }
        };
        DatePickerDialog datePickerDialog=new DatePickerDialog(getActivity(), listener,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }
    class login extends AsyncTask<String, JSONObject,String> {

        String name = "", pass = "";

        public login(String name, String pass) {

            this.name = name;
            this.pass = pass;
        }

        @Override
        protected String doInBackground(String... strings) {
            try {


                // POST Request
                JSONObject postDataParams = new JSONObject();
                postDataParams.put("ApiKey", "DENTALMEDICAL");
                postDataParams.put("email",name);
                postDataParams.put("password",pass);

                return RequestHandler.sendPost("http://apiheal.000webhostapp.com/api/LoginUser",postDataParams);
            }
            catch(Exception e){
                return new String("Exception: " + e.getMessage());
            }
        }

        @Override
        protected void onPostExecute(String s) {
            Log.e("chuoi tra ve :",s);
            try {

                JSONObject jsonObject = new JSONObject(s);
                if(jsonObject.getString("result").equals("ok"))
                {
                    Toast.makeText(BenhNhan_DangNhapBenhNhan.this,"Đăng Nhập Thành Công ! ",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(BenhNhan_DangNhapBenhNhan.this, Nav_TrangBenhNhan.class));
                }

            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(BenhNhan_DangNhapBenhNhan.this,"Đăng Nhập Thất Bại !",Toast.LENGTH_LONG).show();
            }

            super.onPostExecute(s);
        }
    }
    @Override
    public void onPause() {
        super.onPause();
    }
}
