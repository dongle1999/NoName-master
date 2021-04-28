package com.example.noname.TrangBacSi.Fragment;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.noname.Adapter.HoSoBenhLy_BenhNhan_Adapter;
import com.example.noname.Adapter.LichSuKham_ChiTietQuanLyHoSoBenhNhan_BacSi_Adapter;
import com.example.noname.Model.HoSoBenhLy_BenhNhan;
import com.example.noname.Model.LichSuKham_ChiTietQuanLyHoSoBenhNhan_BacSi;
import com.example.noname.R;
import com.example.noname.RequestHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class BacSi_ChiTiet_QuanLyHoSoBenhNhan extends AppCompatActivity {

    ListView lvLichSuKhamBenh;
    ArrayList<LichSuKham_ChiTietQuanLyHoSoBenhNhan_BacSi>dsLichSu;
    LichSuKham_ChiTietQuanLyHoSoBenhNhan_BacSi_Adapter lichSuAdapter;

    String id ;
    ImageButton imgBack;
    Button btnThemLichSuKhamBenh;
    TextView txtChinhSuaHoSo,ht,sdt,email,dc,tuoi,gt,ngay;
    ArrayList<HoSoBenhLy_BenhNhan>dsHoSo;
    ArrayList<item>listitem ;
    HoSoBenhLy_BenhNhan_Adapter hoSoAdapter;
    ListView lvHoSoBenhLy;
    Intent callerIntent;
    Bundle packageFromCaller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bac_si_chi_tiet_quan_ly_ho_so_benh_nhan);
        controls();
        event();

        callerIntent=getIntent();
        packageFromCaller=
                callerIntent.getBundleExtra("data");
        id=packageFromCaller.getString("ID");
        ht.setText(packageFromCaller.getString("PatientName"));
        sdt.setText(packageFromCaller.getString("PatientContno"));
        email.setText(packageFromCaller.getString("PatientEmail"));
        dc.setText(packageFromCaller.getString("PatientAdd"));
        tuoi.setText(packageFromCaller.getString("PatientAge"));
        gt.setText(packageFromCaller.getString("PatientGender"));
        ngay.setText(packageFromCaller.getString("CreationDate"));
        new getlankham().execute("lay thong tin ls kham");




    }



    private void controls() {
        lvHoSoBenhLy=findViewById(R.id.lvLichSuKhamBenh_BacSi_ChiTietQuanLyBenhNhan);
        imgBack = findViewById(R.id.imgBack_BacSi_ChiTietQuanLyBenhNhan);
        btnThemLichSuKhamBenh = findViewById(R.id.btnThemLichSuKhamBenh_BacSi_ChiTietQuanLyBenhNhan);
        txtChinhSuaHoSo = findViewById(R.id.txtChinhSuaHoSoBenhNhan_BacSi_ChiTietQuanLyBenhNhan);
        ht = findViewById(R.id.txtHoTenBenhNhan_BacSi_ChiTietQuanLyBenhNhan);
        sdt = findViewById(R.id.txtSoLienLac_BacSi_ChiTietQuanLyBenhNhan);
        email = findViewById(R.id.txtEmail_BacSi_ChiTietQuanLyBenhNhan);
        dc = findViewById(R.id.txtDiaChi_BacSi_ChiTietQuanLyBenhNhan);
        tuoi = findViewById(R.id.txtTuoi_BacSi_ChiTietQuanLyBenhNhan);
        gt = findViewById(R.id.txtGioiTinh_BacSi_ChiTietQuanLyBenhNhan);
        ngay = findViewById(R.id.txtNgayGioDangKy_BacSi_ChiTietQuanLyBenhNhan);


    }

    private void event() {

        txtChinhSuaHoSo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a=new Intent(BacSi_ChiTiet_QuanLyHoSoBenhNhan.this, BacSi_QuanLyBenhNhan_ChinhSuaHoSoBenhNhan.class);
                a.putExtra("data",packageFromCaller);
                startActivity(a);
            }
        });
        lvHoSoBenhLy.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                item t = listitem.get(position);
                AlertDialog.Builder b=new AlertDialog.Builder(getApplicationContext());
                b.setTitle("Thông tin khám");
                b.setMessage("BloodPressure: "+t.BloodPressure+"\nBloodSugar: "+t.BloodSugar+"\nWeight: "+t.Weight+"\nTemperature: "+t.Temperature+"\nMedicalPres: "+t.MedicalPres);
                b.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override

                    public void onClick(DialogInterface dialog, int which)

                    {
                        dialog.cancel();
                    }

                });

                b.create().show();
            }
        });
        btnThemLichSuKhamBenh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BacSi_ChiTiet_QuanLyHoSoBenhNhan.this,BacSi_ThemLichSuKhamBenhNhan.class));
            }
        });
    }
    class getlankham extends AsyncTask<String, JSONObject,String> {


        public getlankham() {

        }

        @Override
        protected String doInBackground(String... strings) {
            try {


                // POST Request
                JSONObject postDataParams = new JSONObject();
                postDataParams.put("ApiKey", "DENTALMEDICAL");

                postDataParams.put("PatientID",id);
                return RequestHandler.sendPost("http://apiheal.000webhostapp.com/api/GetListMedicalHis", postDataParams);
            } catch (Exception e) {
                return new String("Exception: " + e.getMessage());
            }
        }

        @Override
        protected void onPostExecute(String s) {
            Log.e("chuoi tra ve :", s);
            try {

                dsHoSo=new ArrayList<>();
                listitem=new ArrayList<>();
                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray = jsonObject.getJSONArray("data");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject a = jsonArray.getJSONObject(i);
                    dsHoSo.add(new HoSoBenhLy_BenhNhan(i,Integer.toString(i+1),a.getString("CreationDate")));
                    listitem.add(new item(i,a.getString("BloodPressure"),a.getString("BloodSugar"),a.getString("Weight"),a.getString("Temperature"),a.getString("MedicalPres")));
                }
                hoSoAdapter=new HoSoBenhLy_BenhNhan_Adapter(BacSi_ChiTiet_QuanLyHoSoBenhNhan.this,
                        R.layout.custom_listview_benh_nhan_ho_so_benh_ly,dsHoSo);

                lvHoSoBenhLy.setAdapter(hoSoAdapter);
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "Có lỗi xảy ra !", Toast.LENGTH_LONG).show();
            }

            super.onPostExecute(s);
        }
    }
    class item{
        public  int id ;
        public String BloodPressure,BloodSugar,Weight,Temperature,MedicalPres;

        public item(int id, String bloodPressure, String bloodSugar, String weight, String temperature, String medicalPres) {
            this.id = id;
            BloodPressure = bloodPressure;
            BloodSugar = bloodSugar;
            Weight = weight;
            Temperature = temperature;
            MedicalPres = medicalPres;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getBloodPressure() {
            return BloodPressure;
        }

        public void setBloodPressure(String bloodPressure) {
            BloodPressure = bloodPressure;
        }

        public String getBloodSugar() {
            return BloodSugar;
        }

        public void setBloodSugar(String bloodSugar) {
            BloodSugar = bloodSugar;
        }

        public String getWeight() {
            return Weight;
        }

        public void setWeight(String weight) {
            Weight = weight;
        }

        public String getTemperature() {
            return Temperature;
        }

        public void setTemperature(String temperature) {
            Temperature = temperature;
        }

        public String getMedicalPres() {
            return MedicalPres;
        }

        public void setMedicalPres(String medicalPres) {
            MedicalPres = medicalPres;
        }
    }
}