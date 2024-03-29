package com.example.noname.Model;

import java.io.Serializable;

public class QuanLyBenhNhan_BacSi implements Serializable {
    private int ma;
    private String tenBenhNhan, soDienThoai, ngayKham;

    public QuanLyBenhNhan_BacSi() {
    }

    public QuanLyBenhNhan_BacSi(int ma, String tenBenhNhan, String soDienThoai, String ngayKham) {
        this.ma = ma;
        this.tenBenhNhan = tenBenhNhan;
        this.soDienThoai = soDienThoai;
        this.ngayKham = ngayKham;

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


}
