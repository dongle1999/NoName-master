package com.example.noname.Model;

import java.io.Serializable;

public class HoSoBenhLy_BenhNhan implements Serializable {
    private int ma;
    private String lanKham, ngayKham ;

    public HoSoBenhLy_BenhNhan() {
    }

    public HoSoBenhLy_BenhNhan(int ma, String lanKham, String ngayKham) {
        this.ma = ma;
        this.lanKham = lanKham;
        this.ngayKham = ngayKham;

    }

    public int getMa() {
        return ma;
    }

    public void setMa(int ma) {
        this.ma = ma;
    }

    public String getLanKham() {
        return lanKham;
    }

    public void setLanKham(String lanKham) {
        this.lanKham = lanKham;
    }

    public String getNgayKham() {
        return ngayKham;
    }

    public void setNgayKham(String ngayKham) {
        this.ngayKham = ngayKham;
    }

}
