package com.example.noname.TrangBenhNhan.Fragment;

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
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.noname.Adapter.HoSoBenhLy_BenhNhan_Adapter;
import com.example.noname.Model.HoSoBenhLy_BenhNhan;
import com.example.noname.R;
import com.example.noname.RequestHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class Fragment_BenhNhan_HoSoBenhLy extends Fragment {
    TextView ht,sdt,email,dc,tuoi,gt ;
    String id="",mail;
    ListView lvHoSoBenhLy;
    String prefname="my_data";
    ArrayList<HoSoBenhLy_BenhNhan>dsHoSo;
    ArrayList<item>listitem ;
    HoSoBenhLy_BenhNhan_Adapter hoSoAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_benh_nhan_ho_so_benh_ly, container,false);
        ht=view.findViewById(R.id.txtHoTenBenhNhan_Fragment_BenhNhan_HoSoBenhLy);
        sdt=view.findViewById(R.id.txtSoLienLac_Fragment_BenhNhan_HoSoBenhLy);
        email=view.findViewById(R.id.txtEmail_Fragment_BenhNhan_HoSoBenhLy);
        dc=view.findViewById(R.id.txtDiaChi_Fragment_BenhNhan_HoSoBenhLy);
        tuoi=view.findViewById(R.id.txtTuoi_Fragment_BenhNhan_HoSoBenhLy);
        gt=view.findViewById(R.id.txtGioiTinh_Fragment_BenhNhan_HoSoBenhLy);
        lvHoSoBenhLy=view.findViewById(R.id.lvFragment_BenhNhan_HoSoBenhLy);
        restoringPreferences();
        new getinfo().execute("laythongtin");
        new getlankham().execute("lay cac lan kham");
        lvHoSoBenhLy.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                item t = listitem.get(position);
                AlertDialog.Builder b=new AlertDialog.Builder(getContext());
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
        return view;
    }
    public void restoringPreferences()
    {
        SharedPreferences pre=this.getActivity().getSharedPreferences
                (prefname, MODE_PRIVATE); //lấy user, pwd, nếu không thấy giá trị mặc định là rỗng
        mail=pre.getString("email", "");


    }
    class getinfo extends AsyncTask<String, JSONObject,String> {


        public getinfo() {

        }

        @Override
        protected String doInBackground(String... strings) {
            try {


                // POST Request
                JSONObject postDataParams = new JSONObject();
                postDataParams.put("ApiKey", "DENTALMEDICAL");
                postDataParams.put("PatientEmail", mail);
                return RequestHandler.sendPost("http://apiheal.000webhostapp.com/api/Getinforpatient", postDataParams);
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
                    id=a.getString("ID");
                    ht.setText(a.getString("PatientName"));
                    sdt.setText(a.getString("PatientContno"));
                    email.setText(a.getString("PatientEmail"));
                    gt.setText(a.getString("PatientGender"));
                    dc.setText(a.getString("PatientAdd"));
                    tuoi.setText(a.getString("PatientAge"));
                }

            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(getView().getContext(), "Có lỗi xảy ra !", Toast.LENGTH_LONG).show();
            }

            super.onPostExecute(s);
        }
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
                hoSoAdapter=new HoSoBenhLy_BenhNhan_Adapter(getActivity(),
                        R.layout.custom_listview_benh_nhan_ho_so_benh_ly,dsHoSo);

                lvHoSoBenhLy.setAdapter(hoSoAdapter);
            } catch (JSONException e) {
                e.printStackTrace();
               Toast.makeText(getView().getContext(), "Có lỗi xảy ra !", Toast.LENGTH_LONG).show();
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
