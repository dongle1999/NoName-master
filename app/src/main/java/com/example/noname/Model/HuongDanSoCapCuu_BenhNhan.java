package com.example.noname.Model;

import java.io.Serializable;

public class HuongDanSoCapCuu_BenhNhan implements Serializable {
    private int ma;
    private String tenBenh;

    public HuongDanSoCapCuu_BenhNhan() {
    }

    public HuongDanSoCapCuu_BenhNhan(int ma, String tenBenh) {
        this.ma = ma;
        this.tenBenh = tenBenh;
    }

    public int getMa() {
        return ma;
    }

    public void setMa(int ma) {
        this.ma = ma;
    }

    public String getTenBenh() {
        return tenBenh;
    }

    public void setTenBenh(String tenBenh) {
        this.tenBenh = tenBenh;
    }
}
