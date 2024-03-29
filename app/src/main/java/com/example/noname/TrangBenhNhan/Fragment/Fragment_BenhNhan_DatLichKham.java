package com.example.noname.TrangBenhNhan.Fragment;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.noname.Adapter.BacSiSpinner;
import com.example.noname.R;
import com.example.noname.RequestHandler;
import com.example.noname.TrangBenhNhan.BenhNhan_DangNhapBenhNhan;
import com.example.noname.TrangBenhNhan.Nav_TrangBenhNhan;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import static android.content.Context.MODE_PRIVATE;

public class Fragment_BenhNhan_DatLichKham extends Fragment {

    TextView txtNgay, txtGio,txtgia;
    Button btnDatLich;
    Calendar calendar=Calendar.getInstance();
    SimpleDateFormat sdfNgay=new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat sdfGio=new SimpleDateFormat("HH:mm");
    ArrayList<String> khoa ,idkhoa;
    ArrayList<bacSi> bs ;
    Spinner spKhoaKhamBenh, spBacSi;
    String prefname="my_data";
    String st1,usid ;
    ProgressDialog dialog;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_benh_nhan_dat_lich_kham, container,false);

        spKhoaKhamBenh=view.findViewById(R.id.spKhoaKhamBenh_BenhNhan_DatLichKham);
        spBacSi=view.findViewById(R.id.spChonBacSi_BenhNhan_DatLichKham);
        txtNgay=view.findViewById(R.id.txtNgay_BenhNhan_DatLichKham);
        txtGio=view.findViewById(R.id.txtGio_BenhNhan_DatLichKham);
        btnDatLich=view.findViewById(R.id.btnDatLich_BenhNhan_DatLichKham);
        txtgia=view.findViewById(R.id.txtgia);
        calendar=Calendar.getInstance();
        txtNgay.setText(sdfNgay.format(calendar.getTime()));
        txtGio.setText(sdfGio.format(calendar.getTime()));
        restoringPreferences();
        new getKhoa().execute("http://apiheal.000webhostapp.com/api/GetDotorspec");
        dialog=new ProgressDialog(getActivity());
        dialog.setTitle("Vui lòng đợi trong giây lát !");
        events();
        return view;

    }
    public void restoringPreferences()
    {
        SharedPreferences pre=this.getActivity().getSharedPreferences
                (prefname, MODE_PRIVATE); //lấy user, pwd, nếu không thấy giá trị mặc định là rỗng
        usid=pre.getString("id", "");


    }
    private void events() {
        txtNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xuLyHienThiNgay();
            }
        });
        txtGio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyHienThiGio();
            }
        });
       spKhoaKhamBenh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               String a = spKhoaKhamBenh.getItemAtPosition(position).toString();
              new GetBs(a).execute("http://apiheal.000webhostapp.com/api/GetDotorspec");

           }

           @Override
           public void onNothingSelected(AdapterView<?> parent) {

           }
       });
       spBacSi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               bacSi a = (bacSi) spBacSi.getItemAtPosition(position);
               txtgia.setText(a.getFeed());
               st1=a.getId();
           }

           @Override
           public void onNothingSelected(AdapterView<?> parent) {

           }
       });
       btnDatLich.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               dialog.show();
            new datLichKham().execute("http://apiheal.000webhostapp.com/api/Addappointment");
           }
       });
    }

    private void xuLyHienThiGio() {
        TimePickerDialog.OnTimeSetListener listener=new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                calendar.set(Calendar.HOUR_OF_DAY,hourOfDay);
                calendar.set(Calendar.MINUTE,minute);
                txtGio.setText(sdfGio.format(calendar.getTime()));
            }
        };
        TimePickerDialog timePickerDialog=new TimePickerDialog(getActivity(),listener,
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                true);

        timePickerDialog.show();
    }

    private void xuLyHienThiNgay() {
        DatePickerDialog.OnDateSetListener listener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR,year);
                calendar.set(Calendar.MONTH,month);
                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);

                txtNgay.setText(sdfNgay.format(calendar.getTime()));
            }
        };
        DatePickerDialog datePickerDialog=new DatePickerDialog(getActivity(), listener,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }
    class getKhoa extends AsyncTask<String, JSONObject,String> {


        public getKhoa() {

        }

        @Override
        protected String doInBackground(String... strings) {
            try {


                // POST Request
                JSONObject postDataParams = new JSONObject();
                postDataParams.put("ApiKey", "DENTALMEDICAL");


                return RequestHandler.sendPost("http://apiheal.000webhostapp.com/api/GetDotorspec", postDataParams);
            } catch (Exception e) {
                return new String("Exception: " + e.getMessage());
            }
        }

        @Override
        protected void onPostExecute(String s) {
            Log.e("chuoi tra ve :", s);
            try {

                khoa = new ArrayList<>();
                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray = jsonObject.getJSONArray("data");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject a = jsonArray.getJSONObject(i);
                    khoa.add(a.getString("specilization"));


                }
                ArrayAdapter<String> adapter = new ArrayAdapter(getView().getContext(), android.R.layout.simple_spinner_item, khoa);
                adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
                spKhoaKhamBenh.setAdapter(adapter);

            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(getView().getContext(), "Có lỗi xảy ra !", Toast.LENGTH_LONG).show();
            }

            super.onPostExecute(s);
        }
    }
    class datLichKham extends AsyncTask<String, JSONObject,String> {


        public datLichKham() {

        }

        @Override
        protected String doInBackground(String... strings) {
            try {


                // POST Request
                JSONObject postDataParams = new JSONObject();
                postDataParams.put("ApiKey", "DENTALMEDICAL");
                postDataParams.put("doctorSpecialization", spKhoaKhamBenh.getSelectedItem().toString());
                postDataParams.put("doctorId", st1);
                postDataParams.put("userId", usid);
                postDataParams.put("consultancyFees", txtgia.getText().toString());
                postDataParams.put("appointmentDate", txtNgay.getText().toString());
                postDataParams.put("appointmentTime", txtGio.getText().toString());
                return RequestHandler.sendPost("http://apiheal.000webhostapp.com/api/Addappointment", postDataParams);
            } catch (Exception e) {
                return new String("Exception: " + e.getMessage());
            }
        }

        @Override
        protected void onPostExecute(String s) {
            Log.e("chuoi tra ve dat lich:", s);
            try {

                JSONObject jsonObject = new JSONObject(s);
                if(jsonObject.getString("result").equals("ok"))
                {
                    dialog.dismiss();
                    getActivity().recreate();
                    Toast.makeText(getView().getContext(),"Đặt lịch khám thành công !",Toast.LENGTH_LONG).show();
                }

            } catch (JSONException e) {
                dialog.dismiss();
                e.printStackTrace();
                Toast.makeText(getView().getContext(), "Có lỗi xảy ra !", Toast.LENGTH_LONG).show();
            }

            super.onPostExecute(s);
        }
    }
        class GetBs extends AsyncTask<String, JSONObject, String> {


            private String k;

            public GetBs(String khoa) {
                this.k = khoa;
            }

            @Override
            protected String doInBackground(String... strings) {
                try {
                    // POST Request
                    JSONObject postDataParams = new JSONObject();
                    postDataParams.put("ApiKey", "DENTALMEDICAL");
                    postDataParams.put("specilization", k);

                    return RequestHandler.sendPost("http://apiheal.000webhostapp.com/api/GetListDotorfromspec", postDataParams);
                } catch (Exception e) {
                    return new String("Exception: " + e.getMessage());
                }
            }

            @Override
            protected void onPostExecute(String s) {
                Log.e("chuoi tra ve :", s);
                try {
                    bs = new ArrayList<>();
                    JSONObject jsonObject = new JSONObject(s);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject a = jsonArray.getJSONObject(i);
                        bacSi b = new bacSi();
                        b.setId(a.getString("id"));
                        b.setName(a.getString("doctorName"));
                        b.setFeed(a.getString("docFees"));
                        bs.add(b);

                    }
                    BacSiSpinner adapter = new BacSiSpinner(getView().getContext(), android.R.layout.simple_spinner_item, bs);
                    adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
                    spBacSi.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getView().getContext(), "Có lỗi xảy ra !", Toast.LENGTH_LONG).show();
                }

                super.onPostExecute(s);
            }
        }
     public    class  bacSi
        {
            private  String id ,name,feed ;
            public  bacSi(){}
            public bacSi(String id , String name,String feed)
            {
                this.id=id ;
                this.name=name;
                this.feed=feed;
            }

            public String getFeed() {
                return feed;
            }

            public void setFeed(String feed) {
                this.feed = feed;
            }

            public String getId() {
                return id;
            }

            public String getName() {
                return name;
            }

            public void setId(String id) {
                this.id = id;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

    @Override
    public void onPause() {
        super.onPause();
    }
}
