package com.example.noname.Blutooth;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ederdoski.simpleble.models.BluetoothLE;
import com.ederdoski.simpleble.utils.BluetoothLEHelper;
import com.example.noname.Adapter.LichSuTiepXuc_BlueScan_Adapter;
import com.example.noname.Adapter.HuongDanSoCapCuu_BenhNhan_Adapter;
import com.example.noname.Model.HuongDanSoCapCuu_BenhNhan;
import com.example.noname.Model.LichSuTiepXuc_BlueScan;
import com.example.noname.R;
import com.example.noname.RequestHandler;
import com.example.noname.TrangBenhNhan.BenhNhan_DangNhapBenhNhan;
import com.example.noname.TrangBenhNhan.Nav_TrangBenhNhan;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

//import com.ederdoski.simpleble.interfaces.BleCallback;
//import com.ederdoski.simpleble.models.BluetoothLE;
//import com.ederdoski.simpleble.utils.BluetoothLEHelper;
//import com.ederdoski.simpleble.utils.Constants;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.logging.LogRecord;

public class BlueScan extends AppCompatActivity {

    Button btnLichSuTiepXuc;
    TextView a ;
    BluetoothLEHelper ble;
    AlertDialog dAlert;
    String prefname="my_data";
    String st1,usid ;
    ArrayList<BluetoothLE> aBleAvailable  = new ArrayList<>();
    //danh sach
    private void setList(){



        if(ble.getListDevices().size() > 0){
            for (int i=0; i<ble.getListDevices().size(); i++) {
                aBleAvailable.add(new BluetoothLE(ble.getListDevices().get(i).getName(), ble.getListDevices().get(i).getMacAddress(), ble.getListDevices().get(i).getRssi(), ble.getListDevices().get(i).getDevice()));
              Log.e("trang thai",ble.getListDevices().get(i).getMacAddress());
            }


        }
        for (int i=0; i<aBleAvailable.size(); i++){
                new datLichKham(aBleAvailable.get(i).getMacAddress()).execute("them tiep xuc");
        }
    }
    private void scanCollars(){

        if(!ble.isScanning()) {

            android.os.Handler mHandler = new Handler();
            ble.scanLeDevice(true);

            mHandler.postDelayed(() -> {
                setList();
            },ble.getScanPeriod());

        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ble.disconnect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        ble.disconnect();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ncovi_bluescan);
        ble = new BluetoothLEHelper(this);
        controls();
        events();
        restoringPreferences();
        if(ble.isReadyForScan()){
            scanCollars();
        }else{
            Toast.makeText(getApplicationContext(), "You must accept the bluetooth and Gps permissions or must turn on the bluetooth and Gps", Toast.LENGTH_SHORT).show();
        }
        new login().execute("check");
    }

    private void events() {
        btnLichSuTiepXuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BlueScan.this,LichSuTiepXuc.class));
            }
        });
    }
    public void restoringPreferences()
    {
        SharedPreferences pre=getApplicationContext().getSharedPreferences
                (prefname, MODE_PRIVATE); //lấy user, pwd, nếu không thấy giá trị mặc định là rỗng
        usid=pre.getString("id", "");

    }
    private void controls() {
        btnLichSuTiepXuc=findViewById(R.id.btnLichSuTiepXuc_BlueScan);
        a=findViewById(R.id.txt_Canhbao_BlueScan);

    }
    class datLichKham extends AsyncTask<String, JSONObject,String> {

        String uid2;
        public datLichKham() {

        }
        public datLichKham(String uid2) {
            this.uid2=uid2;
        }
        @Override
        protected String doInBackground(String... strings) {
            try {


                // POST Request
                JSONObject postDataParams = new JSONObject();
                postDataParams.put("ApiKey", "DENTALMEDICAL");
                postDataParams.put("uid1", usid);
                postDataParams.put("uid2",uid2);

                return RequestHandler.sendPost("http://apiheal.000webhostapp.com/api/bluscan", postDataParams);
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
                  Log.e("Da them",uid2);
                }

            } catch (JSONException e) {

                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "Có lỗi xảy ra !", Toast.LENGTH_LONG).show();
            }

            super.onPostExecute(s);
        }
    }
    class login extends AsyncTask<String, JSONObject,String> {



        public login() {
        }

        @Override
        protected String doInBackground(String... strings) {
            try {


                // POST Request
                JSONObject postDataParams = new JSONObject();
                postDataParams.put("ApiKey", "DENTALMEDICAL");
                postDataParams.put("uid1",usid);
                return RequestHandler.sendPost("http://apiheal.000webhostapp.com/api/Gettttiepxuc",postDataParams);
            }
            catch(Exception e){
                return new String("Exception: " + e.getMessage());
            }
        }

        @Override
        protected void onPostExecute(String s) {

            try {
                Log.e("chuoi tra ve :",s);
                JSONObject jsonObject = new JSONObject(s);
                if(jsonObject.getString("result").equals("ok"))
                {
                        a.setText("Đã phát hiện trượng hợp nghi nhiễm tiếp xúc với bạn !");
                    AlertDialog.Builder b=new AlertDialog.Builder(getApplicationContext());
                    b.setTitle("Cảnh báo");
                    b.setMessage("Đã phát hiện tiếp xúc gần nghi nhiễm"+"/nVui lòng liên hệ 19009095 để được trợ giúp");
                   b.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override

                    public void onClick(DialogInterface dialog, int which)

                    {
                        dialog.cancel();
                    }

                });

                    b.create().show();
                }

            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(),"Đăng Nhập Thất Bại !",Toast.LENGTH_LONG).show();
            }

            super.onPostExecute(s);
        }
    }

}
