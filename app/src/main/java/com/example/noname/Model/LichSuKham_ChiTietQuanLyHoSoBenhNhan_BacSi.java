package com.example.noname.Model;

import java.io.Serializable;

public class LichSuKham_ChiTietQuanLyHoSoBenhNhan_BacSi implements Serializable {

    private int ma;
    private String bacSiKham, khoa, ngayDangKy,trangthai;

    public LichSuKham_ChiTietQuanLyHoSoBenhNhan_BacSi() {
    }

    public LichSuKham_ChiTietQuanLyHoSoBenhNhan_BacSi(int ma, String bacSiKham, String khoa, String ngayDangKy, String tenBenhNhan) {
        this.ma = ma;
        this.trangthai = tenBenhNhan;
        this.bacSiKham = bacSiKham;
        this.khoa = khoa;
        this.ngayDangKy = ngayDangKy;
    }

    public int getMa() {
        return ma;
    }

    public void setMa(int ma) {
        this.ma = ma;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

    public String getBacSiKham() {
        return bacSiKham;
    }

    public void setBacSiKham(String bacSiKham) {
        this.bacSiKham = bacSiKham;
    }

    public String getKhoa() {
        return khoa;
    }

    public void setKhoa(String khoa) {
        this.khoa = khoa;
    }

    public String getNgayDangKy() {
        return ngayDangKy;
    }

    public void setNgayDangKy(String ngayDangKy) {
        this.ngayDangKy = ngayDangKy;
    }
}
