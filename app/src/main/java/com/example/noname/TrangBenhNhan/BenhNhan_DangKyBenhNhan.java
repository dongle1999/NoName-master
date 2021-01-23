package com.example.noname.TrangBenhNhan;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.noname.R;
import com.example.noname.RequestHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class BenhNhan_DangKyBenhNhan extends AppCompatActivity {
    ImageButton imgBack_DangKy;
    Button btnDangKy_DangKy;
    EditText email ,pass ,pas2 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky_tk_benh_nhan);
        controls();
        events();
    }

    private void events() {
        imgBack_DangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BenhNhan_DangKyBenhNhan.this, BenhNhan_DangNhapBenhNhan.class));
            }
        });
        btnDangKy_DangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!email.getText().toString().equals("")&&!pass.getText().toString().equals("")) {

                    String us = email.getText().toString().trim();
                    String pas = pass.getText().toString().trim();
                    new Dangky(us, pas).execute("http://apiheal.000webhostapp.com/api//Registration");


                }
                else Toast.makeText(getApplicationContext(),"Vui lòng nhập đầy đủ các mục !",Toast.LENGTH_LONG).show();

            }
        });

    }



    private void controls() {
       email=findViewById(R.id.edtSoDienThoai_BenhNhan_DangKyBenhNhan);
       pass=findViewById(R.id.edtMatKhau_BenhNhan_DangKyBenhNhan);
       pas2=findViewById(R.id.edtXacNhanMatKhau_BenhNhan_DangKyBenhNhan);
        imgBack_DangKy=findViewById(R.id.imgBack_BenhNhan__DangKyBenhNhan);
        btnDangKy_DangKy=findViewById(R.id.btnDangKy_BenhNhan_DangKyBenhNhan);

    }
    class Dangky extends AsyncTask<String, JSONObject,String>
    {

        String  name="", pass="";

        public Dangky( String name, String pass) {

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

                return RequestHandler.sendPost("http://apiheal.000webhostapp.com/api/Registration",postDataParams);
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
                    Toast.makeText(BenhNhan_DangKyBenhNhan.this,"Đăng Ký Thành Công ! ",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(BenhNhan_DangKyBenhNhan.this, BenhNhan_DangNhapBenhNhan.class));
                }
                else Toast.makeText(BenhNhan_DangKyBenhNhan.this,"Đăng Ký Thất Bại !",Toast.LENGTH_LONG).show();
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(BenhNhan_DangKyBenhNhan.this,"Có lỗi xảy ra !",Toast.LENGTH_LONG).show();
            }

            super.onPostExecute(s);
        }
    }
}