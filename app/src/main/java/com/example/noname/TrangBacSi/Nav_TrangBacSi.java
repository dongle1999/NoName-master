package com.example.noname.TrangBacSi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.example.noname.R;
import com.google.android.material.navigation.NavigationView;

public class Nav_TrangBacSi extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_trang_bac_si);

        Toolbar toolbar = findViewById(R.id.toolBar_bac_si);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout_bac_si);
        NavigationView navigationView = findViewById(R.id.nav_view_bac_si);

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_BacSi_TrangChu, R.id.nav_BacSi_LichKham, R.id.nav_BacSi_ThemHoSoBenhNhan,
                R.id.nav_BacSi_QuanLyBenhNhan, R.id.nav_BacSi_HoSoBacSi, R.id.nav_BacSi_LichSuKham,
                R.id.nav_BacSi_DangXuat)
                .setDrawerLayout(drawer).build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_bac_si);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_bac_si);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration) || super.onSupportNavigateUp();
    }
}