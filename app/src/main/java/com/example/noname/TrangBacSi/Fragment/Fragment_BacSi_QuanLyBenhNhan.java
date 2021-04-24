package com.example.noname.TrangBacSi.Fragment;

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


import com.example.noname.Adapter.QuanLyBenhNhan_BacSi_Adapter;

import com.example.noname.Model.QuanLyBenhNhan_BacSi;
import com.example.noname.Model.BenhNhan;
import com.example.noname.R;
import com.example.noname.RequestHandler;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class Fragment_BacSi_QuanLyBenhNhan extends Fragment {
    String prefname="my_data";
    String id="",mail;
    ListView lvQuanLyBenhNhan;
    ArrayList<QuanLyBenhNhan_BacSi>dsQuanLy;
    QuanLyBenhNhan_BacSi_Adapter quanLyBenhNhanAdapter;
    ArrayList<BenhNhan>listitem ;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_bac_si_quan_ly_benh_nhan, container,false);
        lvQuanLyBenhNhan=view.findViewById(R.id.lvFragment_BacSi_QuanLyBenhNhan);
        restoringPreferences();
        new getlankham().execute("Lay ds benh nhan");
        lvQuanLyBenhNhan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getActivity(),BacSi_ChiTiet_QuanLyHoSoBenhNhan.class);
                BenhNhan a = listitem.get(position);
                Bundle bundle=new Bundle();
                bundle.putString("ID",a.ID);
                bundle.putString("Docid",a.Docid);
                bundle.putString("PatientName",a.PatientName);
                bundle.putString("PatientContno",a.PatientContno);
                bundle.putString("PatientEmail",a.PatientEmail);
                bundle.putString("PatientGender",a.PatientGender);
                bundle.putString("PatientAdd",a.PatientAdd);
                bundle.putString("PatientAge",a.PatientAge);
                bundle.putString("PatientMedhis",a.PatientMedhis);
                bundle.putString("CreationDate",a.CreationDate);
                intent.putExtra("data",bundle);
                startActivity(intent);
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
    class getlankham extends AsyncTask<String, JSONObject,String> {


        public getlankham() {

        }

        @Override
        protected String doInBackground(String... strings) {
            try {


                // POST Request
                JSONObject postDataParams = new JSONObject();
                postDataParams.put("ApiKey", "DENTALMEDICAL");

                postDataParams.put("Docid",id);
                return RequestHandler.sendPost("http://apiheal.000webhostapp.com/api/Getallpatient", postDataParams);
            } catch (Exception e) {
                return new String("Exception: " + e.getMessage());
            }
        }

        @Override
        protected void onPostExecute(String s) {
            Log.e("chuoi tra ve :", s);
            try {

                dsQuanLy=new ArrayList<>();
                listitem=new ArrayList<>();
                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray = jsonObject.getJSONArray("data");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject a = jsonArray.getJSONObject(i);
                    dsQuanLy.add(new QuanLyBenhNhan_BacSi(1,a.getString("PatientName"),a.getString("PatientContno"),a.getString("CreationDate")));
                    listitem.add(new BenhNhan(i,a.getString("ID"),a.getString("Docid"),
                            a.getString("PatientName"),a.getString("PatientContno"),
                            a.getString("PatientEmail"),
                            a.getString("PatientGender"),
                            a.getString("PatientAdd"),
                            a.getString("PatientAge"),
                            a.getString("PatientMedhis"),
                            a.getString("CreationDate")));
                }
                quanLyBenhNhanAdapter=new QuanLyBenhNhan_BacSi_Adapter(getActivity(),
                        R.layout.custom_listview_bac_si_quan_ly_benh_nhan,dsQuanLy);

                lvQuanLyBenhNhan.setAdapter(quanLyBenhNhanAdapter);
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(getView().getContext(), "Có lỗi xảy ra !", Toast.LENGTH_LONG).show();
            }

            super.onPostExecute(s);
        }
    }
   
}