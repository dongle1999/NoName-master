package com.example.noname.TrangBacSi.Fragment;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.noname.R;
import com.example.noname.RequestHandler;
import com.example.noname.TrangBenhNhan.Fragment.Fragment_BenhNhan_HoSoBenhNhan;

import org.json.JSONException;
import org.json.JSONObject;

import static android.content.Context.MODE_PRIVATE;

public class Fragment_BacSi_ThemHoSoBenhNhan extends Fragment {
    Button btnThemhoso;
    EditText hotenBN,EmailBN,Tuoi,DiaChi,SodienThoai;
    RadioButton nam,nu;
    String prefname="my_data";
    String id ;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_bac_si_them_ho_so_benh_nhan, container,false);
        btnThemhoso = view.findViewById(R.id.btnDangKy_DangKy_BenhNhan);
        hotenBN = view.findViewById(R.id.edtHoTen_Fragment_BacSi_ThemHoSoBenhNhan);
        EmailBN = view.findViewById(R.id.edtEmail_Fragment_BacSi_ThemHoSoBenhNhan);
        DiaChi = view.findViewById(R.id.edtNoiOHienTai_Fragment_BacSi_ThemHoSoBenhNhan);
        Tuoi = view.findViewById(R.id.txtTuoi_Fragment_BacSi_ThemHoSoBenhNhan);
        SodienThoai = view.findViewById(R.id.txtSoDienThoai_Fragment_BacSi_ThemHoSoBenhNhan);
        events();
        return view;
    }



    private void events() {
        btnThemhoso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!id.equals("")) {
                    Log.e("id",id);
                    new Fragment_BacSi_ThemHoSoBenhNhan.AddPatient().execute("http://apiheal.000webhostapp.com/api//Registration");
                }else  Toast.makeText(getView().getContext(),"Có lỗi xảy ra !",Toast.LENGTH_LONG).show();
            }
        });
    }


    class AddPatient extends AsyncTask<String, JSONObject,String> {
        public AddPatient() {
        }

        @Override
        protected String doInBackground(String... strings) {
            try {


                // POST Request
                JSONObject postDataParams = new JSONObject();
                postDataParams.put("ApiKey", "DENTALMEDICAL");
                postDataParams.put("id", id);
                postDataParams.put("PatientName", hotenBN.getText().toString());
                postDataParams.put("PatientAdd",DiaChi.getText().toString());
                postDataParams.put("PatientContno", SodienThoai.getText().toString());
                postDataParams.put("PatientEmail", EmailBN.getText().toString());
                if(nam.isChecked()) {
                    postDataParams.put("PatientGender", "male");
                }else  postDataParams.put("PatientGender", "famale");
                Log.e("Thêm hồ sơ",postDataParams.toString());
                return RequestHandler.sendPost("http://apiheal.000webhostapp.com/api/AddPatient",postDataParams);
            }
            catch(Exception e){
                return new String("Exception: " + e.getMessage());
            }
        }

        @Override
        protected void onPostExecute(String s) {
            try {

                JSONObject jsonObject = new JSONObject(s);
                if(jsonObject.getString("result").equals("ok"))
                {
                    Toast.makeText(getView().getContext(),"Thêm hồ sơ bệnh nhân thành công!",Toast.LENGTH_LONG).show();

                }
                else Toast.makeText(getView().getContext(),"Thêm hồ sơ thất bại !",Toast.LENGTH_LONG).show();
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(getView().getContext(),"Có lỗi xảy ra !",Toast.LENGTH_LONG).show();
            }

            super.onPostExecute(s);
        }
    }
}