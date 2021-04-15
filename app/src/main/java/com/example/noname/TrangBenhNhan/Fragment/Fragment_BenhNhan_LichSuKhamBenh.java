package com.example.noname.TrangBenhNhan.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.noname.Adapter.HoSoBenhLy_BenhNhan_Adapter;
import com.example.noname.Adapter.LichSuKhamBenh_BenhNhan_Adapter;
import com.example.noname.Model.HoSoBenhLy_BenhNhan;
import com.example.noname.Model.LichSuKhamBenh_BenhNhan;
import com.example.noname.R;
import com.example.noname.RequestHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class Fragment_BenhNhan_LichSuKhamBenh extends Fragment{

    ListView lvLichSuKhamBenh;
    ArrayList<LichSuKhamBenh_BenhNhan>dsLichSu;
    LichSuKhamBenh_BenhNhan_Adapter lichSuAdapter;
    String prefname="my_data",id="";

    ProgressDialog progressDialog;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_benh_nhan_lich_su_kham_benh, container,false);
        progressDialog=new ProgressDialog(getActivity());
        lvLichSuKhamBenh=view.findViewById(R.id.lvFragment_BenhNhan_LichSuKhamBenh);
        restoringPreferences();
        progressDialog.show();
        new getlskham().execute("lay thong tin lich su kham");

        return view;
    }
    public void restoringPreferences()
    {
        SharedPreferences pre=this.getActivity().getSharedPreferences
                (prefname, MODE_PRIVATE); //lấy user, pwd, nếu không thấy giá trị mặc định là rỗng
        id=pre.getString("id", "");


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

                postDataParams.put("userId",id);
                return RequestHandler.sendPost("http://apiheal.000webhostapp.com/api/Getappointment", postDataParams);
            } catch (Exception e) {
                return new String("Exception: " + e.getMessage());
            }
        }

        @Override
        protected void onPostExecute(String s) {
            Log.e("chuoi tra ve :", s);
            try {

                dsLichSu=new ArrayList<>();
                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray = jsonObject.getJSONArray("data");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject a = jsonArray.getJSONObject(i);
                    getinfobs bs=new getinfobs(a.getString("doctorId"));
                    bs.execute("get ten bs");
                    String tt="";
                    if(a.getString("userStatus").equals("0")&&a.getString("doctorStatus").equals("0")) tt="Hoạt Động";
                    if(a.getString("userStatus").equals("1")||a.getString("doctorStatus").equals("1")) tt="Đã Hủy";
                    if(a.getString("userStatus").equals("2")&&a.getString("doctorStatus").equals("2")) tt="Hoàn Thành";
                    dsLichSu.add(new LichSuKhamBenh_BenhNhan(i,bs.gettenbs(),a.getString("doctorSpecialization"),a.getString("postingDate"),tt));
                }
                lichSuAdapter=new LichSuKhamBenh_BenhNhan_Adapter(getActivity(),
                        R.layout.custom_listview_benh_nhan_lich_su_kham_benh,dsLichSu);

                lvLichSuKhamBenh.setAdapter(lichSuAdapter);
                progressDialog.dismiss();
            } catch (JSONException e) {
                progressDialog.dismiss();
                e.printStackTrace();
                Toast.makeText(getView().getContext(), "Có lỗi xảy ra !", Toast.LENGTH_LONG).show();
            }

            super.onPostExecute(s);
        }
    }
    class getinfobs extends AsyncTask<String, JSONObject,String> {

        String idbs;
        String tenbs="ten bac si";
        public getinfobs() {

        }
        public getinfobs(String idbs) {
            this.idbs=idbs;
        }
        @Override
        protected String doInBackground(String... strings) {
            try {


                // POST Request
                JSONObject postDataParams = new JSONObject();
                postDataParams.put("ApiKey", "DENTALMEDICAL");

                postDataParams.put("id",idbs);
                return RequestHandler.sendPost("http://apiheal.000webhostapp.com/api/Getinfodoctor", postDataParams);
            } catch (Exception e) {
                return new String("Exception: " + e.getMessage());
            }
        }

        @Override
        protected void onPostExecute(String s) {
            Log.e("chuoi tra ve :", s);
            try {
                dsLichSu=new ArrayList<>();
                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray = jsonObject.getJSONArray("data");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject a = jsonArray.getJSONObject(i);
                    this.tenbs=a.getString("doctorName");
                    Log.e("ten bs ",this.tenbs);
                }
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(getView().getContext(), "Có lỗi xảy ra !", Toast.LENGTH_LONG).show();
            }

            super.onPostExecute(s);
        }
        public String gettenbs()
        {
            Log.e("ten bs----",this.tenbs);
            return this.tenbs;
        }

    }
}
