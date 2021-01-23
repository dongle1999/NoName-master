package com.example.noname.Model;

import java.io.Serializable;

public class LichSuKham_BacSi implements Serializable {
    private int ma;
    private String tenBenhNhan, soDienThoai, trangThai, ngayKham, thoiGianKham;

    public LichSuKham_BacSi() {
    }

    public LichSuKham_BacSi(int ma, String tenBenhNhan, String soDienThoai, String trangThai, String ngayKham, String thoiGianKham) {
        this.ma = ma;
        this.tenBenhNhan = tenBenhNhan;
        this.soDienThoai = soDienThoai;
        this.trangThai = trangThai;
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

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
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
