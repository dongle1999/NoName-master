package com.example.noname.TrangBenhNhan;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;


import com.example.noname.R;
import com.google.android.material.navigation.NavigationView;

public class Nav_TrangBenhNhan extends AppCompatActivity {
    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_trang_benh_nhan);

        Toolbar toolbar = findViewById(R.id.toolBar_benh_nhan);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout_benh_nhan);
        NavigationView navigationView = findViewById(R.id.nav_view_benh_nhan);

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_BenhNhan_TrangChu, R.id.nav_BenhNhan_DatLichKham, R.id.nav_BenhNhan_HoSoBenhNhan,
                R.id.nav_BenhNhan_HoSoBenhLy, R.id.nav_BenhNhan_LichSuKhamBenh, R.id.nav_BenhNhan_DangXuat)
                .setDrawerLayout(drawer).build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_benh_nhan);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_benh_nhan);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration) || super.onSupportNavigateUp();
    }
}