package com.example.noname.TrangBenhNhan.Fragment;

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

import com.example.noname.Adapter.HoSoBenhLy_BenhNhan_Adapter;
import com.example.noname.Adapter.LichSuKhamBenh_BenhNhan_Adapter;
import com.example.noname.Model.HoSoBenhLy_BenhNhan;
import com.example.noname.Model.LichSuKhamBenh_BenhNhan;
import com.example.noname.R;
import com.example.noname.RequestHandler;
import com.example.noname.TrangBenhNhan.BenhNhan_DangNhapBenhNhan;
import com.example.noname.TrangBenhNhan.Nav_TrangBenhNhan;

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
    ArrayList<lichsukham> listlskham ;
    ProgressDialog progressDialog;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_benh_nhan_lich_su_kham_benh, container,false);
        progressDialog=new ProgressDialog(getActivity());
        lvLichSuKhamBenh=view.findViewById(R.id.lvFragment_BenhNhan_LichSuKhamBenh);
        restoringPreferences();
        progressDialog.show();
        listlskham=new ArrayList<>();
        dsLichSu=new ArrayList<>();
        new getlskham().execute("lay thong tin lich su kham");


        lvLichSuKhamBenh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                lichsukham a=listlskham.get(position);
                    new getinfobs(a).execute("lay thong tin bac si");
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

                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray = jsonObject.getJSONArray("data");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject a = jsonArray.getJSONObject(i);
                    String tt="";
                    if(a.getString("userStatus").equals("0")&&a.getString("doctorStatus").equals("0")) tt="Hoạt Động";
                    if(a.getString("userStatus").equals("1")||a.getString("doctorStatus").equals("1")) tt="Đã Hủy";
                    if(a.getString("userStatus").equals("2")&&a.getString("doctorStatus").equals("2")) tt="Hoàn Thành";
                    listlskham.add(new lichsukham(i,a.getString("id"),a.getString("doctorSpecialization"),a.getString("doctorId"),a.getString("consultancyFees"),a.getString("appointmentDate"),a.getString("appointmentTime"),a.getString("postingDate"),tt));
                    dsLichSu.add(new LichSuKhamBenh_BenhNhan(i,a.getString("id"),a.getString("doctorSpecialization"),a.getString("postingDate"),tt));

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
        lichsukham ls ;
        public getinfobs() {
        }

        public getinfobs(lichsukham ls) {
            this.ls = ls;
        }

        @Override
        protected String doInBackground(String... strings) {
            try {

                Log.e("lay thong tin bs","bat dau");
                // POST Request
                JSONObject postDataParams = new JSONObject();
                postDataParams.put("ApiKey", "DENTALMEDICAL");

                postDataParams.put("id",ls.doctorId);
                return RequestHandler.sendPost("http://apiheal.000webhostapp.com/api/Getinfodoctor", postDataParams);
            } catch (Exception e) {
                return new String("Exception: " + e.getMessage());
            }
        }

        @Override
        protected void onPostExecute(String s) {
            Log.e("chuoi tra ve :", s);
            try {
               String tenbs="",dc="";

                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray = jsonObject.getJSONArray("data");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject a = jsonArray.getJSONObject(i);
                    tenbs=a.getString("doctorName");
                    dc=a.getString("address");
                }
                AlertDialog.Builder b=new AlertDialog.Builder(getContext());
                b.setTitle("Thông tin lịch khám");
                b.setMessage("Tên bác sĩ: "+tenbs+"\nPhòng Khám:"+dc+"\nKhoa: "+ls.doctorSpecialization+"\nPhí: "+ls.consultancyFees+"\nNgày Khám: "+ls.appointmentDate+"\nThời Gian: "+ls.appointmentTime);
                b.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override

                    public void onClick(DialogInterface dialog, int which)

                    {
                        dialog.cancel();
                    }

                });
                if(ls.tt.equals("Hoạt Động")) {
                    b.setPositiveButton("Hủy Lịch", new DialogInterface.OnClickListener() {
                        @Override

                        public void onClick(DialogInterface dialog, int which) {
                           progressDialog.show();
                           new huylich(ls.id).execute("Huy lich kham");
                        }

                    });
                }

                b.create().show();

            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(getView().getContext(), "Có lỗi xảy ra !", Toast.LENGTH_LONG).show();
            }

            super.onPostExecute(s);
        }

    }
    class huylich extends AsyncTask<String, JSONObject,String> {

        String name = "";

        public huylich(String id) {

            this.name = id;
        }

        @Override
        protected String doInBackground(String... strings) {
            try {


                // POST Request
                JSONObject postDataParams = new JSONObject();
                postDataParams.put("ApiKey", "DENTALMEDICAL");
                postDataParams.put("id",name);

                return RequestHandler.sendPost("http://apiheal.000webhostapp.com/api/HuyApp",postDataParams);
            }
            catch(Exception e){
                return new String("Exception: " + e.getMessage());
            }
        }

        @Override
        protected void onPostExecute(String s) {
            Log.e("chuoi tra ve :",s);
            try {

                JSONObject jsonObject = new JSONObject(s);
                if(jsonObject.getString("result").equals("ok"))
                {
                    progressDialog.dismiss();
                    getActivity().recreate();
                    Toast.makeText(getActivity(),"Hủy thành công !",Toast.LENGTH_LONG).show();

                }
                else

                {
                    progressDialog.dismiss();
                    Toast.makeText(getActivity(),"Hủy Thất Bại !",Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                progressDialog.dismiss();
                e.printStackTrace();
                Toast.makeText(getActivity(),"Có lỗi xảy ra !",Toast.LENGTH_LONG).show();
            }

            super.onPostExecute(s);
        }
    }
    class lichsukham
    {
        public int ma ;
        public String id,doctorSpecialization,doctorId,consultancyFees,appointmentDate,appointmentTime,postingDate,tt;
        public lichsukham(){}

        public lichsukham(int ma, String id, String doctorSpecialization, String doctorId, String consultancyFees, String appointmentDate, String appointmentTime, String postingDate,String tt) {
            this.ma = ma;
            this.id = id;
            this.doctorSpecialization = doctorSpecialization;
            this.doctorId = doctorId;
            this.consultancyFees = consultancyFees;
            this.appointmentDate = appointmentDate;
            this.appointmentTime = appointmentTime;
            this.postingDate = postingDate;
            this.tt=tt;
        }
    }
}
