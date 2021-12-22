package com.company.Class;

public class MaNhomLop {
    private int Manhomlop;
    private int nhom;
    private int TH;
    private int MaMH;
    private String TenMH;

    public MaNhomLop(int manhomlop, int nhom, int TH, int maMH, String tenMH) {
        Manhomlop = manhomlop;
        this.nhom = nhom;
        this.TH = TH;
        MaMH = maMH;
        TenMH = tenMH;
    }
    @Override
    public String toString() {
        return Manhomlop+"";
    }
}
