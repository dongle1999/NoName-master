package com.example.noname.Model;

import java.io.Serializable;

public class LichSuKhamBenh_BenhNhan implements Serializable {

    private int ma;
    private String tenBenhNhan, bacSiKham, khoa, ngayDangKy;

    public LichSuKhamBenh_BenhNhan() {
    }

    public LichSuKhamBenh_BenhNhan(int ma, String tenBenhNhan, String bacSiKham, String khoa, String ngayDangKy) {
        this.ma = ma;
        this.tenBenhNhan = tenBenhNhan;
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

    public String getTenBenhNhan() {
        return tenBenhNhan;
    }

    public void setTenBenhNhan(String tenBenhNhan) {
        this.tenBenhNhan = tenBenhNhan;
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
