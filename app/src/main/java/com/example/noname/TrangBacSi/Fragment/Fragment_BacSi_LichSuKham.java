package com.example.noname.TrangBacSi.Fragment;

import android.app.ProgressDialog;
import android.content.DialogInterface;
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
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.noname.Adapter.LichKham_BacSi_Adapter;
import com.example.noname.Adapter.LichSuKham_BacSi_Adapter;
import com.example.noname.Model.LichKham_BacSi;
import com.example.noname.Model.LichSuKham_BacSi;
import com.example.noname.R;
import com.example.noname.RequestHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class Fragment_BacSi_LichSuKham extends Fragment {
    String prefname="my_data",id="";
    ListView lvLichSuKham;
    ArrayList<LichSuKham_BacSi>dsLichSuKham;
    LichSuKham_BacSi_Adapter lichSuKhamAdapter;
    ArrayList<lichsukham> listlskham ;
    ProgressDialog progressDialog;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_bac_si_lich_su_kham, container,false);
        progressDialog=new ProgressDialog(getActivity());
        lvLichSuKham=view.findViewById(R.id.lvFragment_BacSi_LichSuKham);
        listlskham=new ArrayList<>();
        dsLichSuKham=new ArrayList<>();
        restoringPreferences();
        progressDialog.show();
        new getlskham().execute("lay lich kham");
        lvLichSuKham.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                lichsukham a = listlskham.get(position);
                new getinfobs(a).execute("hien thi thong tin kham");
            }
        });

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

                postDataParams.put("doctorId",id);
                return RequestHandler.sendPost("http://apiheal.000webhostapp.com/api/Getappointment1", postDataParams);
            } catch (Exception e) {
                return new String("Exception: " + e.getMessage());
            }
        }

        @Override
        protected void onPostExecute(String s) {

            try {
                Log.e("chuoi tra ve :", s);
                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray = jsonObject.getJSONArray("data");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject a = jsonArray.getJSONObject(i);
                    String tt="";
                    if(a.getString("userStatus").equals("0")&&a.getString("doctorStatus").equals("0"))
                    {
                        tt="Hoạt Động";
                    }
                    if(a.getString("userStatus").equals("1")||a.getString("doctorStatus").equals("1")){
                        tt="Đã Hủy";
                        listlskham.add(new lichsukham(i,a.getString("id"),a.getString("doctorSpecialization"),a.getString("userId"),a.getString("consultancyFees"),a.getString("appointmentDate"),a.getString("appointmentTime"),a.getString("postingDate"),tt));
                        dsLichSuKham.add(new LichSuKham_BacSi(i,a.getString("id"),tt,a.getString("postingDate")));
                    }
                    if(a.getString("userStatus").equals("2")&&a.getString("doctorStatus").equals("2")){
                        tt="Hoàn Thành";
                        listlskham.add(new lichsukham(i,a.getString("id"),a.getString("doctorSpecialization"),a.getString("userId"),a.getString("consultancyFees"),a.getString("appointmentDate"),a.getString("appointmentTime"),a.getString("postingDate"),tt));
                        dsLichSuKham.add(new LichSuKham_BacSi(i,a.getString("id"),tt,a.getString("postingDate")));
                    }


                }
                lichSuKhamAdapter=new LichSuKham_BacSi_Adapter(getActivity(),R.layout.custom_listview_bac_si_lich_su_kham,dsLichSuKham);

                lvLichSuKham.setAdapter(lichSuKhamAdapter);
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
        lichsukham ls ;
        public getinfobs() {
        }

        public getinfobs(lichsukham ls) {
            this.ls = ls;
        }

        @Override
        protected String doInBackground(String... strings) {
            try {

                Log.e("lay thong tin user","bat dau");
                // POST Request
                JSONObject postDataParams = new JSONObject();
                postDataParams.put("ApiKey", "DENTALMEDICAL");

                postDataParams.put("id",ls.userId);
                return RequestHandler.sendPost("http://apiheal.000webhostapp.com/api/Getinfouser", postDataParams);
            } catch (Exception e) {
                return new String("Exception: " + e.getMessage());
            }
        }

        @Override
        protected void onPostExecute(String s) {

            try {
                Log.e("chuoi tra ve :", s);
                String tenbs="",dc="";
                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray = jsonObject.getJSONArray("data");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject a = jsonArray.getJSONObject(i);
                    tenbs=a.getString("fullName");
                    dc=a.getString("email");
                }
                AlertDialog.Builder b=new AlertDialog.Builder(getContext());
                b.setTitle("Thông tin lịch khám");
                b.setMessage("Tên Bệnh Nhân: "+tenbs+"\nEmail:"+dc+"\nKhoa: "+ls.doctorSpecialization+"\nPhí: "+ls.consultancyFees+"\nNgày Khám: "+ls.appointmentDate+"\nThời Gian: "+ls.appointmentTime);

                b.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override

                    public void onClick(DialogInterface dialog, int which)

                    {
                        dialog.cancel();
                    }

                });



                b.create().show();

            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(getView().getContext(), "Có lỗi xảy ra !", Toast.LENGTH_LONG).show();
            }

            super.onPostExecute(s);
        }

    }

    class lichsukham {
        public int ma;
        public String id, doctorSpecialization, userId, consultancyFees, appointmentDate, appointmentTime, postingDate, tt;

        public lichsukham() {
        }

        public lichsukham(int ma, String id, String doctorSpecialization, String doctorId, String consultancyFees, String appointmentDate, String appointmentTime, String postingDate, String tt) {
            this.ma = ma;
            this.id = id;
            this.doctorSpecialization = doctorSpecialization;
            this.userId = doctorId;
            this.consultancyFees = consultancyFees;
            this.appointmentDate = appointmentDate;
            this.appointmentTime = appointmentTime;
            this.postingDate = postingDate;
            this.tt = tt;
        }
    }
}