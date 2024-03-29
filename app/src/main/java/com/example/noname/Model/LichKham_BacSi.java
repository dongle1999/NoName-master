package com.example.noname.Model;

import java.io.Serializable;

public class LichKham_BacSi implements Serializable {
    private int ma;
    private String tenBenhNhan, soDienThoai, ngayKham, thoiGianKham;

    public LichKham_BacSi() {
    }

    public LichKham_BacSi(int ma, String soDienThoai, String ngayKham, String thoiGianKham) {
        this.ma = ma;
        this.soDienThoai = soDienThoai;
        this.ngayKham = ngayKham;
        this.thoiGianKham = thoiGianKham;
    }

    public int getMa() {
        return ma;
    }

    public void setMa(int ma) {
        this.ma = ma;
    }

    public String getTenBenhNhan() {
        return tenBenhNhan;
    }

    public void setTenBenhNhan(String tenBenhNhan) {
        this.tenBenhNhan = tenBenhNhan;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getNgayKham() {
        return ngayKham;
    }

    public void setNgayKham(String ngayKham) {
        this.ngayKham = ngayKham;
    }

    public String getThoiGianKham() {
        return thoiGianKham;
    }

    public void setThoiGianKham(String thoiGianKham) {
        this.thoiGianKham = thoiGianKham;
    }
}
