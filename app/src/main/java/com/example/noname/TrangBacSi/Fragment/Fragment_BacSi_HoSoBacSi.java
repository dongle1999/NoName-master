package com.example.noname.TrangBacSi.Fragment;

import android.app.DatePickerDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.noname.R;
import com.example.noname.RequestHandler;
import com.example.noname.TrangBenhNhan.Fragment.Fragment_BenhNhan_HoSoBenhNhan;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Fragment_BacSi_HoSoBacSi extends Fragment {

    TextView txtNamSinh;
    EditText edtHoTen,edtKhoa,edtDiaChi,edtQueQuan,edtSdt;
    Button btnCapNhap;
    String prefname="my_data";
    String id ;
    Calendar calendar=Calendar.getInstance();
    SimpleDateFormat sdfNgay=new SimpleDateFormat("dd/MM/yyyy");

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_bac_si_ho_so_bac_si, container,false);
        txtNamSinh=view.findViewById(R.id.txtNamSinh_Fragment_BacSi_HoSoBacSi);
        edtHoTen=view.findViewById(R.id.edtHoTen_Fragment_BacSi_HoSoBacSi);
        edtKhoa=view.findViewById(R.id.edtKhoa_Fragment_BacSi_HoSoBacSi);
        edtDiaChi=view.findViewById(R.id.edtNoiOHienTai_Fragment_BacSi_HoSoBacSi);
        edtQueQuan=view.findViewById(R.id.edtQUeQuan_Fragment_BacSi_HoSoBacSi);
        edtSdt=view.findViewById(R.id.edtSoDienThoai_Fragment_BacSi_HoSoBacSi);
        restoringPreferences();
        if(!id.equals("")) {
            Log.e("id",id);
            new Getdoctor(id).execute("http://apiheal.000webhostapp.com/api/GetListDotor");
        }
        events();
        return view;
    }

    private void restoringPreferences() {
    }

    private void events() {
        txtNamSinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyHienThiNgay();
            }
        });
        btnCapNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!id.equals("")) {
                    Log.e("id",id);
                    new Fragment_BacSi_HoSoBacSi.EditDoc().execute("http://apiheal.000webhostapp.com/api//Registration");
                }else  Toast.makeText(getView().getContext(),"Có lỗi xảy ra !",Toast.LENGTH_LONG).show();
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

    class Getdoctor extends AsyncTask<String, JSONObject,String> {
        private  String id ;
        public Getdoctor(String id) {
            this.id =id ;
        }

        @Override
        protected String doInBackground(String... strings) {
            try {


                // POST Request
                JSONObject postDataParams = new JSONObject();
                postDataParams.put("ApiKey", "DENTALMEDICAL");
                postDataParams.put("id", id);

                return RequestHandler.sendPost("http://apiheal.000webhostapp.com/api/GetListDotor",postDataParams);
            }
            catch(Exception e){
                return new String("Exception: " + e.getMessage());
            }
        }
        @Override
        protected void onPostExecute(String s) {

            try {
                Log.e("lấy thông tin doctor",s);
                JSONObject jsonObject = new JSONObject(s);
                if(jsonObject.getString("result").equals("ok"))
                {
                    if(jsonObject.getString("fullName")!=null){
                        edtHoTen.setText(jsonObject.getString("fullName"));
                    }
                    if(jsonObject.getString("address")!=null){
                        edtDiaChi.setText(jsonObject.getString("address"));
                    }
                    if(jsonObject.getString("specilization")!=null){
                        edtKhoa.setText(jsonObject.getString("specilization"));
                    }
                    if(jsonObject.getString("city")!=null){
                        edtQueQuan.setText(jsonObject.getString("city"));
                    }
                    if(jsonObject.getString("age")!=null){
                        txtNamSinh.setText(jsonObject.getString("age"));
                    }

                }
                else Toast.makeText(getView().getContext(),"Không có người này !",Toast.LENGTH_LONG).show();
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(getView().getContext(),"Có lỗi xảy ra !",Toast.LENGTH_LONG).show();
            }

            super.onPostExecute(s);
        }
    }

    class EditDoc extends AsyncTask<String, JSONObject,String>{
        public EditDoc( ) {
        }
        @Override
        protected String doInBackground(String... strings) {
            try {


                // POST Request
                JSONObject postDataParams = new JSONObject();
                postDataParams.put("ApiKey", "DENTALMEDICAL");
                postDataParams.put("id", id);
                postDataParams.put("doctorName", edtHoTen.getText().toString());
                postDataParams.put("address",edtDiaChi.getText().toString());
                postDataParams.put("specilization", edtKhoa.getText().toString());
                postDataParams.put("age", txtNamSinh.getText().toString());
                Log.e("Sửa doctor",postDataParams.toString());
                return RequestHandler.sendPost("http://apiheal.000webhostapp.com/api/EditDoctor",postDataParams);
            }
            catch(Exception e){
                return new String("Exception: " + e.getMessage());
            }
        }
        @Override
        protected void onPostExecute(String s) {

            try {

                JSONObject jsonObject = new JSONObject(s);
                if(jsonObject.getString("result").equals("ok"))
                {
                    Toast.makeText(getView().getContext(),"Sửa thông tin thành công!",Toast.LENGTH_LONG).show();

                }
                else Toast.makeText(getView().getContext(),"Sửa thông tin thất bại !",Toast.LENGTH_LONG).show();
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(getView().getContext(),"Có lỗi xảy ra !",Toast.LENGTH_LONG).show();
            }

            super.onPostExecute(s);
        }
    }
}