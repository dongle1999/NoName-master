package com.example.noname.TrangBenhNhan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.noname.R;
import com.example.noname.RequestHandler;
import com.example.noname.TrangBacSi.BacSi_DangNhapBacSi;

import org.json.JSONException;
import org.json.JSONObject;

public class BenhNhan_DangNhapBenhNhan extends AppCompatActivity {

    Button btnDangNhap, btnDangKy_DangNhap;
    TextView txtDangNhapBacSi, txtQuenMatKhau;
    EditText tdn , pass ;
    String prefname="my_data";
    ProgressDialog progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap_tk_benh_nhan);
        controls();
        // Ask for location permission if not already allowed
        ActivityCompat.requestPermissions(
                this,
                new String[]
                        {
                                Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.ACCESS_COARSE_LOCATION,
                                Manifest.permission.ACCESS_BACKGROUND_LOCATION,
                                Manifest.permission.READ_EXTERNAL_STORAGE
                        }, 0);
        progress=new ProgressDialog(BenhNhan_DangNhapBenhNhan.this);
        progress.setTitle("Loading");
        events();
    }

    private void events() {
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*if(!tdn.getText().toString().equals("")&&!pass.getText().toString().equals("")) {
                    progress.show();
                    String us = tdn.getText().toString().trim();
                    String pas = pass.getText().toString().trim();
                    new login(us, pas).execute("http://dentalmedical.ddns.net:8088/api/Login");

                }
                else Toast.makeText(BenhNhan_DangNhapBenhNhan.this,"Lỗi Đăng Nhập !",Toast.LENGTH_LONG).show();*/

                startActivity(new Intent(BenhNhan_DangNhapBenhNhan.this,Nav_TrangBenhNhan.class));

            }
        });

        btnDangKy_DangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BenhNhan_DangNhapBenhNhan.this, BenhNhan_DangKyBenhNhan.class));
            }
        });
        txtDangNhapBacSi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BenhNhan_DangNhapBenhNhan.this, BacSi_DangNhapBacSi.class));
            }
        });
        txtQuenMatKhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BenhNhan_DangNhapBenhNhan.this,BenhNhan_QuenMatKhau.class));
            }
        });

    }

    private void controls() {
        txtDangNhapBacSi=findViewById(R.id.txtDangNhap_BacSi);
        btnDangNhap=findViewById(R.id.btnDangNhap_BenhNhan);
        btnDangKy_DangNhap=findViewById(R.id.btnDangKy_DangNhap_BenhNhan);
        tdn=findViewById(R.id.edtSoDienThoai_DangNhap_BenhNhan);
        pass=findViewById(R.id.edtMatKhau_DangNhap_BenhNhan);
        txtQuenMatKhau=findViewById(R.id.txtQuenMatKhau_BenhNhan);
    }
    public void savingPreferences(String user, String pwd)
    {
        //tạo đối tượng getSharedPreferences
        SharedPreferences pre=getSharedPreferences
                (prefname, MODE_PRIVATE);
        //tạo đối tượng Editor để lưu thay đổi
        SharedPreferences.Editor editor=pre.edit();


            //xóa mọi lưu trữ trước đó
            editor.clear();

            //lưu vào editor
            editor.putString("email", user);
            editor.putString("id", pwd);


        //chấp nhận lưu xuống file
        editor.commit();
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

            try {
                Log.e("chuoi tra ve :",s);
                JSONObject jsonObject = new JSONObject(s);
                if(jsonObject.getString("result").equals("ok"))
                {
                    progress.dismiss();
                    Toast.makeText(BenhNhan_DangNhapBenhNhan.this,"Đăng Nhập Thành Công ! ",Toast.LENGTH_LONG).show();
                    savingPreferences(tdn.getText().toString(),jsonObject.getString("id"));
                    startActivity(new Intent(BenhNhan_DangNhapBenhNhan.this, Nav_TrangBenhNhan.class));
                    finish();
                }
                else Toast.makeText(BenhNhan_DangNhapBenhNhan.this,"Đăng Nhập Thất Bại !",Toast.LENGTH_LONG).show();
            } catch (JSONException e) {
                progress.dismiss();
                e.printStackTrace();
                Toast.makeText(BenhNhan_DangNhapBenhNhan.this,"Đăng Nhập Thất Bại !",Toast.LENGTH_LONG).show();
            }

            super.onPostExecute(s);
        }
    }

}
