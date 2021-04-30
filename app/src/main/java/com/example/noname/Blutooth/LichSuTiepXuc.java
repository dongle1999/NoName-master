package com.example.noname.Blutooth;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.noname.Adapter.LichSuKhamBenh_BenhNhan_Adapter;
import com.example.noname.Adapter.LichSuTiepXuc_BlueScan_Adapter;
import com.example.noname.Model.LichSuKhamBenh_BenhNhan;
import com.example.noname.Model.LichSuTiepXuc_BlueScan;
import com.example.noname.R;
import com.example.noname.RequestHandler;
import com.example.noname.TrangBenhNhan.Fragment.Fragment_BenhNhan_LichSuKhamBenh;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class LichSuTiepXuc extends AppCompatActivity {
    ListView lvLichSuTiepXuc;
    ArrayList<LichSuTiepXuc_BlueScan> dsLichSuTiepXuc;
    LichSuTiepXuc_BlueScan_Adapter lichSuTiepXuc_Adapter;
    ImageButton imgBack;
    String prefname="my_data";
    String st1,usid ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lich_su_tiep_xuc);
        controls();
        events();
        dsLichSuTiepXuc=new ArrayList<>();
        restoringPreferences();

        new getlskham().execute("lay thong tin lich su kham");
    }
    public void restoringPreferences()
    {
        SharedPreferences pre=getApplicationContext().getSharedPreferences
                (prefname, MODE_PRIVATE); //lấy user, pwd, nếu không thấy giá trị mặc định là rỗng
        usid=pre.getString("id", "");

    }
    class getlskham extends AsyncTask<String, JSONObject,String> {


        public getlskham() {

        }

        @Override
        protected String doInBackground(String... strings) {
            try {

                // POST Request
                JSONObject postDataParams = new JSONObject();
                postDataParams.put("ApiKey", "DENTALMEDICAL");
                postDataParams.put("uid1",usid);
                return RequestHandler.sendPost("http://apiheal.000webhostapp.com/api/Getallbluscan", postDataParams);
            } catch (Exception e) {
                return new String("Exception: " + e.getMessage());
            }
        }

        @Override
        protected void onPostExecute(String s) {
            Log.e("chuoi tra ve :", s);
            try {

                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray = jsonObject.getJSONArray("data");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject a = jsonArray.getJSONObject(i);
                    dsLichSuTiepXuc.add(new LichSuTiepXuc_BlueScan(i,a.getString("date(postDate)"),a.getString("COUNT(*)")));

                }

                lichSuTiepXuc_Adapter=new LichSuTiepXuc_BlueScan_Adapter(LichSuTiepXuc.this,
                        R.layout.custom_listview_bluescan_lich_su_tiep_xuc,dsLichSuTiepXuc);

                lvLichSuTiepXuc.setAdapter(lichSuTiepXuc_Adapter);
            } catch (JSONException e) {

                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "Có lỗi xảy ra !", Toast.LENGTH_LONG).show();
            }

            super.onPostExecute(s);
        }
    }
    private void events() {
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LichSuTiepXuc.this,BlueScan.class));
            }
        });
    }

    private void controls() {
        imgBack=findViewById(R.id.imgBack_LichSuTiepXuc);
        lvLichSuTiepXuc=findViewById(R.id.lvLichSuTiepXuc_BlueScan);
    }
}