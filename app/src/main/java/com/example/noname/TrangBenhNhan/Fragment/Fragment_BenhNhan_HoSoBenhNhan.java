package com.example.noname.TrangBenhNhan.Fragment;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.noname.R;
import com.example.noname.RequestHandler;
import com.example.noname.TrangBenhNhan.BenhNhan_DoiMatKhau;
import com.example.noname.TrangBenhNhan.BenhNhan_QuenMatKhau;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static android.content.Context.MODE_PRIVATE;

public class Fragment_BenhNhan_HoSoBenhNhan extends Fragment {

    TextView txtNamSinh, txtDoiMatKhau;
    EditText ht , dc, qq ;
    RadioButton nam ,nu ;
    Calendar calendar=Calendar.getInstance();
    SimpleDateFormat sdfNgay=new SimpleDateFormat("dd/MM/yyyy");
    String prefname="my_data";
    String id ;
    Button btnDangKy_DangKy_BenhNhan;
    ProgressDialog dialog;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_benh_nhan_ho_so_benh_nhan, container,false);

        txtNamSinh=view.findViewById(R.id.txtNamSinh_Fragment_BenhNhan_HoSoBenhNhan);
        txtDoiMatKhau=view.findViewById(R.id.txtDoiMatKhau_Fragment_BenhNhan_HoSoBenhNhan);
        ht=view.findViewById(R.id.edtHoTen_Fragment_BenhNhan_HoSoBenhNhan);
        dc=view.findViewById(R.id.edtNoiOHienTai_Fragment_BenhNhan_HoSoBenhNhan);
        qq=view.findViewById(R.id.edtQUeQuan_Fragment_BenhNhan_HoSoBenhNhan);
        nam=view.findViewById(R.id.rdNam_Fragment_BenhNhan_HoSoBenhNhan);
        nu=view.findViewById(R.id.rdNu_Fragment_BenhNhan_HoSoBenhNhan);
        btnDangKy_DangKy_BenhNhan=view.findViewById(R.id.btnDangKy_DangKy_BenhNhan);
        restoringPreferences();
        dialog=new ProgressDialog(getActivity());
        dialog.setTitle("Vui lòng đợi trong giây lát !");
        if(!id.equals("")) {
            Log.e("id",id);
            new Getuser(id).execute("http://apiheal.000webhostapp.com/api/Registration");
        }
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
        txtDoiMatKhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), BenhNhan_DoiMatKhau.class));
            }
        });
        btnDangKy_DangKy_BenhNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!id.equals("")) {
                    dialog.show();
                    Log.e("id",id);
                    new Edituser().execute("http://apiheal.000webhostapp.com/api//Registration");
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
    public void restoringPreferences()
    {
        SharedPreferences pre=this.getActivity().getSharedPreferences
                (prefname, MODE_PRIVATE); //lấy user, pwd, nếu không thấy giá trị mặc định là rỗng
            id=pre.getString("id", "");


    }
    class Getuser extends AsyncTask<String, JSONObject,String>
    {

    private  String id ;

        public Getuser(String id ) {
            this.id =id ;

        }

        @Override
        protected String doInBackground(String... strings) {
            try {


                // POST Request
                JSONObject postDataParams = new JSONObject();
                postDataParams.put("ApiKey", "DENTALMEDICAL");
                postDataParams.put("id", id);

                return RequestHandler.sendPost("http://apiheal.000webhostapp.com/api/GetTTUser",postDataParams);
            }
            catch(Exception e){
                return new String("Exception: " + e.getMessage());
            }
        }

        @Override
        protected void onPostExecute(String s) {

            try {
                Log.e("lấy thông tin user",s);
                JSONObject jsonObject = new JSONObject(s);
                if(jsonObject.getString("result").equals("ok"))
                {
                    if(jsonObject.getString("fullName")!=null){
                        ht.setText(jsonObject.getString("fullName"));
                    }
                    if(jsonObject.getString("address")!=null){
                        dc.setText(jsonObject.getString("address"));
                    }
                    if(jsonObject.getString("city")!=null){
                        qq.setText(jsonObject.getString("city"));
                    }
                    if(jsonObject.getString("age")!=null){
                        txtNamSinh.setText(jsonObject.getString("age"));
                    }
                    if(jsonObject.getString("gender")!=null){
                        if(jsonObject.getString("gender").equals("female"))
                        {
                            nu.setChecked(true);
                        }
                        if(jsonObject.getString("gender").equals("male"))
                        {
                            nam.setChecked(true);
                        }
                    }
                }
                else Toast.makeText(getView().getContext(),"Đăng Ký Thất Bại !",Toast.LENGTH_LONG).show();
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(getView().getContext(),"Có lỗi xảy ra !",Toast.LENGTH_LONG).show();
            }

            super.onPostExecute(s);
        }
    }
    class Edituser extends AsyncTask<String, JSONObject,String>
    {



        public Edituser( ) {
        }

        @Override
        protected String doInBackground(String... strings) {
            try {


                // POST Request
                JSONObject postDataParams = new JSONObject();
                postDataParams.put("ApiKey", "DENTALMEDICAL");
                postDataParams.put("id", id);
                postDataParams.put("fullName", ht.getText().toString());
                postDataParams.put("address",dc.getText().toString());
                postDataParams.put("city", qq.getText().toString());
                if(nam.isChecked()) {
                    postDataParams.put("gender", "male");
                }else  postDataParams.put("gender", "famale");
                postDataParams.put("age", txtNamSinh.getText().toString());
                Log.e("Sửa user",postDataParams.toString());
                return RequestHandler.sendPost("http://apiheal.000webhostapp.com/api/EditUser",postDataParams);
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
                    dialog.dismiss();
                    Toast.makeText(getView().getContext(),"Sửa thông tin thành công!",Toast.LENGTH_LONG).show();

                }
                else
                {
                    dialog.dismiss();
                    Toast.makeText(getView().getContext(),"Sửa thông tin thất bại !",Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                dialog.dismiss();
                e.printStackTrace();
                Toast.makeText(getView().getContext(),"Có lỗi xảy ra !",Toast.LENGTH_LONG).show();
            }

            super.onPostExecute(s);
        }
    }
}
