package com.example.noname.Model;

import java.io.Serializable;

public class LichSuTiepXuc_BlueScan implements Serializable {
    private int ma;
    private String id_Blt;

    public LichSuTiepXuc_BlueScan() {
    }

    public LichSuTiepXuc_BlueScan(int ma, String id_Blt) {
        this.ma = ma;
        this.id_Blt = id_Blt;
    }

    public String getId_Blt() {
        return id_Blt;
    }

    public void setId_Blt(String id_Blt) {
        this.id_Blt = id_Blt;
    }

    public int getMa() {
        return ma;
    }

    public void setMa(int ma) {
        this.ma = ma;
    }
}
