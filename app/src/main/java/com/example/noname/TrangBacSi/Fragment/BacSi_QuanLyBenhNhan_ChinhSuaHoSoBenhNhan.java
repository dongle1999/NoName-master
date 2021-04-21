package com.example.noname.TrangBacSi.Fragment;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.noname.R;
import com.example.noname.RequestHandler;

import org.json.JSONException;
import org.json.JSONObject;

public class BacSi_QuanLyBenhNhan_ChinhSuaHoSoBenhNhan extends AppCompatActivity {
    String id;
    ImageButton imgBack;
    Button btnChinhSuaLichSuKhamBenh;
    TextView ht, sdt, email, dc, tuoi, gt;
    Intent callerIntent;
    Bundle packageFromCaller;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bac_si_quan_ly_benh_nhan_chinh_sua_ho_so_benh_nhan);
        controls();
        callerIntent = getIntent();
        packageFromCaller =
                callerIntent.getBundleExtra("data");
        id = packageFromCaller.getString("ID");
        ht.setText(packageFromCaller.getString("PatientName"));
        sdt.setText(packageFromCaller.getString("PatientContno"));
        email.setText(packageFromCaller.getString("PatientEmail"));
        dc.setText(packageFromCaller.getString("PatientAdd"));
        tuoi.setText(packageFromCaller.getString("PatientAge"));
        gt.setText(packageFromCaller.getString("PatientGender"));

        event();
        imgBack = findViewById(R.id.imgBack_BacSi_QuanLyBenhNhan_ChinhSuaHoSoBenhNhan);


    }

    private void controls() {
        btnChinhSuaLichSuKhamBenh = findViewById(R.id.btnChinhSuaHoSo_BacSi_QuanLyBenhNhan_ChinhSuaHoSoBenhNhan);
        ht = findViewById(R.id.txtHoTenBenhNhan_BacSi_QuanLyBenhNhan_ChinhSuaHoSoBenhNhan);
        sdt = findViewById(R.id.txtSoLienLac_BacSi_QuanLyBenhNhan_ChinhSuaHoSoBenhNhan);
        email = findViewById(R.id.txtEmail_BacSi_QuanLyBenhNhan_ChinhSuaHoSoBenhNhan);
        dc = findViewById(R.id.txtDiaChi_BacSi_QuanLyBenhNhan_ChinhSuaHoSoBenhNhan);
        tuoi = findViewById(R.id.txtTuoi_BacSi_QuanLyBenhNhan_ChinhSuaHoSoBenhNhan);
        gt = findViewById(R.id.txtGioiTinh_BacSi_QuanLyBenhNhan_ChinhSuaHoSoBenhNhan);
    }

    private void event() {

        btnChinhSuaLichSuKhamBenh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Edituser().execute("chinh sua benh nhan");
            }
        });

    }

    class Edituser extends AsyncTask<String, JSONObject, String> {


        public Edituser() {
        }

        @Override
        protected String doInBackground(String... strings) {
            try {


                // POST Request
                JSONObject postDataParams = new JSONObject();
                postDataParams.put("ApiKey", "DENTALMEDICAL");
                postDataParams.put("ID",id);
                postDataParams.put("PatientName", ht.getText().toString());
                postDataParams.put("PatientContno",dc.getText().toString());
                postDataParams.put("PatientGender",gt.getText().toString());
                postDataParams.put("PatientAdd",dc.getText().toString());
                postDataParams.put("PatientAge",tuoi.getText().toString());
                return RequestHandler.sendPost("http://apiheal.000webhostapp.com/api/EditPatient1", postDataParams);
            } catch (Exception e) {
                return new String("Exception: " + e.getMessage());
            }
        }

        @Override
        protected void onPostExecute(String s) {

            try {

                JSONObject jsonObject = new JSONObject(s);
                if (jsonObject.getString("result").equals("ok")) {
                    Toast.makeText(getApplicationContext(), "Sửa thông tin thành công!", Toast.LENGTH_LONG).show();
                } else {

                    Toast.makeText(getApplicationContext(), "Sửa thông tin thất bại !", Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {

                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "Có lỗi xảy ra !", Toast.LENGTH_LONG).show();
            }

            super.onPostExecute(s);
        }
    }
}