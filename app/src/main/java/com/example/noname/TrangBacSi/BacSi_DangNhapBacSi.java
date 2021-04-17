package com.example.noname.TrangBacSi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.noname.ChonNguoiDung;
import com.example.noname.R;
import com.example.noname.RequestHandler;
import com.example.noname.TrangBenhNhan.BenhNhan_DangNhapBenhNhan;
import com.example.noname.TrangBenhNhan.Nav_TrangBenhNhan;

import org.json.JSONException;
import org.json.JSONObject;

public class BacSi_DangNhapBacSi extends AppCompatActivity {

    Button btnDangNhap;
    EditText tdn , pass ;
    ImageButton imgBack_DangNhapBacSi;
    String prefname="my_data";
    ProgressDialog dialog ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap_tk_bac_si);

        btnDangNhap=findViewById(R.id.btnDangNhap_BacSi);
        imgBack_DangNhapBacSi=findViewById(R.id.imgBack_DangNhapBacSi);
        tdn=findViewById(R.id.edtSoDienThoai_DangNhap_BacSi);
        pass=findViewById(R.id.edtMatKhau_DangNhap_BacSi);
        dialog=new ProgressDialog(BacSi_DangNhapBacSi.this);
        dialog.setTitle("Loading");
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!tdn.getText().toString().equals("")&&!pass.getText().toString().equals("")) {
                    dialog.show();
                    String us = tdn.getText().toString().trim();
                    String pas = pass.getText().toString().trim();
                    new login(us, pas).execute("http://dentalmedical.ddns.net:8088/api/LoginDoctor");

                }
                else Toast.makeText(BacSi_DangNhapBacSi.this,"Lỗi Đăng Nhập !",Toast.LENGTH_LONG).show();
            }
        });

        imgBack_DangNhapBacSi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BacSi_DangNhapBacSi.this, ChonNguoiDung.class));
            }
        });
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
                postDataParams.put("docEmail",name);
                postDataParams.put("password",pass);

                return RequestHandler.sendPost("http://apiheal.000webhostapp.com/api/LoginDoctor",postDataParams);
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
                    dialog.dismiss();
                    Toast.makeText(BacSi_DangNhapBacSi.this,"Đăng Nhập Thành Công ! ",Toast.LENGTH_LONG).show();
                    savingPreferences(tdn.getText().toString(),jsonObject.getString("id"));
                    startActivity(new Intent(BacSi_DangNhapBacSi.this, Nav_TrangBacSi.class));
                }
                else {
                    dialog.dismiss();
                    Toast.makeText(BacSi_DangNhapBacSi.this,"Đăng Nhập Thất Bại !",Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                dialog.dismiss();
                e.printStackTrace();
                Toast.makeText(BacSi_DangNhapBacSi.this,"Có lỗi xảy ra !",Toast.LENGTH_LONG).show();
            }

            super.onPostExecute(s);
        }
    }
}