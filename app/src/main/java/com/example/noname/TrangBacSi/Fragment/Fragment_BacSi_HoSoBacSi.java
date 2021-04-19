package com.example.noname.TrangBacSi.Fragment;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.noname.R;
import com.example.noname.RequestHandler;
import com.example.noname.TrangBenhNhan.Fragment.Fragment_BenhNhan_HoSoBenhNhan;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static android.content.Context.MODE_PRIVATE;

public class Fragment_BacSi_HoSoBacSi extends Fragment {
    ProgressDialog progressDialog;

    EditText edtHoTen,edtKhoa,edtDiaChi,edtQueQuan,edtSdt;
    Button btnCapNhap,btndoipass;
    String prefname="my_data";
    String id="" ;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_bac_si_ho_so_bac_si, container,false);
        edtHoTen=view.findViewById(R.id.txtHoTen_Fragment_BacSi_HoSoBacSi);
        edtKhoa=view.findViewById(R.id.txtKhoa_Fragment_BacSi_HoSoBacSi);
        edtDiaChi=view.findViewById(R.id.txtNoiOHienTai_Fragment_BacSi_HoSoBacSi);
        edtQueQuan=view.findViewById(R.id.txtQUeQuan_Fragment_BacSi_HoSoBacSi);
        edtSdt=view.findViewById(R.id.txtSoDienThoai_Fragment_BacSi_HoSoBacSi);
        btnCapNhap=view.findViewById(R.id.btnDangKy_DangKy_BenhNhan);
        btndoipass=view.findViewById(R.id.btnDangKy_DangKy_BenhNhan2);
        restoringPreferences();
        progressDialog=new ProgressDialog(getActivity());
        if(!id.equals("")) {

            new Getdoctor(id).execute("http://apiheal.000webhostapp.com/api/GetListDotor");
        }
      events();
        return view;
    }

    public void restoringPreferences()
    {
        SharedPreferences pre=this.getActivity().getSharedPreferences
                (prefname, MODE_PRIVATE); //lấy user, pwd, nếu không thấy giá trị mặc định là rỗng
        id=pre.getString("id", "");

    }

    private void events() {
        btnCapNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!id.equals("")) {

                    new EditDoc().execute("Sua thong tin bac si");
                }else  Toast.makeText(getView().getContext(),"Có lỗi xảy ra !",Toast.LENGTH_LONG).show();
            }
        });
    }


    class Getdoctor extends AsyncTask<String, JSONObject,String> {
        private  String id ;
        public Getdoctor(String id) {
            this.id =id ;
        }

        @Override
        protected String doInBackground(String... strings) {
            try {


                // POST Request
                JSONObject postDataParams = new JSONObject();
                postDataParams.put("ApiKey", "DENTALMEDICAL");
                postDataParams.put("id", id);

                return RequestHandler.sendPost("http://apiheal.000webhostapp.com/api/Getinfodoctor",postDataParams);
            }
            catch(Exception e){
                return new String("Exception: " + e.getMessage());
            }
        }
        @Override
        protected void onPostExecute(String s) {

            try {
                Log.e("lấy thông tin doctor",s);
                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray = jsonObject.getJSONArray("data");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject a = jsonArray.getJSONObject(i);
                    edtHoTen.setText(a.getString("doctorName"));
                    edtKhoa.setText(a.getString("specilization"));
                    edtDiaChi.setText(a.getString("address"));
                    edtQueQuan.setText(a.getString("docEmail"));
                    edtSdt.setText(a.getString("contactno"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(getView().getContext(),"Có lỗi xảy ra !",Toast.LENGTH_LONG).show();
            }

            super.onPostExecute(s);
        }
    }

    class EditDoc extends AsyncTask<String, JSONObject,String>{
        public EditDoc( ) {
        }
        @Override
        protected String doInBackground(String... strings) {
            try {


                // POST Request
                JSONObject postDataParams = new JSONObject();
                postDataParams.put("ApiKey", "DENTALMEDICAL");
                postDataParams.put("id", id);
                postDataParams.put("doctorName", edtHoTen.getText().toString());
                postDataParams.put("address",edtDiaChi.getText().toString());
                postDataParams.put("docEmail",edtQueQuan.getText().toString());
                postDataParams.put("contactno",edtSdt.getText().toString());

                return RequestHandler.sendPost("http://apiheal.000webhostapp.com/api/EditDoctor",postDataParams);
            }
            catch(Exception e){
                return new String("Exception: " + e.getMessage());
            }
        }
        @Override
        protected void onPostExecute(String s) {

            try {
                Log.e("Chuoi tra ve",s);
                JSONObject jsonObject = new JSONObject(s);
                if(jsonObject.getString("result").equals("ok"))
                {
                    Toast.makeText(getView().getContext(),"Sửa thông tin thành công!",Toast.LENGTH_LONG).show();

                }
                else Toast.makeText(getView().getContext(),"Sửa thông tin thất bại !",Toast.LENGTH_LONG).show();
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(getView().getContext(),"Có lỗi xảy ra !",Toast.LENGTH_LONG).show();
            }

            super.onPostExecute(s);
        }
    }
}