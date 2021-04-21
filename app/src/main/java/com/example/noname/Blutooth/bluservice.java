package com.example.noname.Blutooth;


import android.app.Service;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;


import androidx.annotation.Nullable;

import com.ederdoski.simpleble.models.BluetoothLE;
import com.ederdoski.simpleble.utils.BluetoothLEHelper;

import java.util.ArrayList;


public class bluservice extends Service {
    BluetoothLEHelper ble;
    WifiManager manager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
    WifiInfo info = manager.getConnectionInfo();
    String address = info.getMacAddress();
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Handler mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                scanCollars();
            }
        },30000);

        return START_STICKY;
    }

    private void scanCollars(){

        if(!ble.isScanning()) {
            Handler mHandler = new Handler();
            ble.scanLeDevice(true);
            mHandler.postDelayed(() -> {
                setList();
            },ble.getScanPeriod());

        }
    }
    private void setList(){
 try {

     ArrayList<BluetoothLE> aBleAvailable = new ArrayList<>();
     if (ble.getListDevices().size() > 0) {
         for (int i = 0; i < ble.getListDevices().size(); i++) {
             aBleAvailable.add(new BluetoothLE(ble.getListDevices().get(i).getName(), ble.getListDevices().get(i).getMacAddress(), ble.getListDevices().get(i).getRssi(), ble.getListDevices().get(i).getDevice()));
         }

     } else {

     }
 }catch (Exception e){
     Log.e("loi",e.toString());
 }
    }
}
