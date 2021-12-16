package com.company.Class;

public class KHGD {
    private String Holot;
    private String TenGV;
    private int maNhomLop;
    private int Nhom;
    private int thucHanh;
    private int MMH;
    private int SLDK;
    private int SLtkb;

    public KHGD(String holot, String tenGV, int maNhomLop, int nhom, int thucHanh, int MMH, int SLDK, int SLtkb) {
        Holot = holot;
        TenGV = tenGV;
        this.maNhomLop = maNhomLop;
        Nhom = nhom;
        this.thucHanh = thucHanh;
        this.MMH = MMH;
        this.SLDK = SLDK;
        this.SLtkb = SLtkb;
    }

    public String getHolot() {
        return Holot;
    }

    public void setHolot(String holot) {
        Holot = holot;
    }

    public String getTenGV() {
        return TenGV;
    }

    public void setTenGV(String tenGV) {
        TenGV = tenGV;
    }

    public int getMaNhomLop() {
        return maNhomLop;
    }

    public void setMaNhomLop(int maNhomLop) {
        this.maNhomLop = maNhomLop;
    }

    public int getNhom() {
        return Nhom;
    }

    public void setNhom(int nhom) {
        Nhom = nhom;
    }

    public int getThucHanh() {
        return thucHanh;
    }

    public void setThucHanh(int thucHanh) {
        this.thucHanh = thucHanh;
    }

    public int getMMH() {
        return MMH;
    }

    public void setMMH(int MMH) {
        this.MMH = MMH;
    }

    public int getSLDK() {
        return SLDK;
    }

    public void setSLDK(int SLDK) {
        this.SLDK = SLDK;
    }

    public int getSLtkb() {
        return SLtkb;
    }

    public void setSLtkb(int SLtkb) {
        this.SLtkb = SLtkb;
    }

    public Object[] toObjectArray() {
        Object[] objects = {Holot,
                TenGV,
                maNhomLop,
                Nhom,
                thucHanh,
                MMH,
                SLDK,
                SLtkb};
        return objects;
    }

    @Override
    public String toString() {
        return "KHGD{" +
                "Holot='" + Holot + '\'' +
                ", TenGV='" + TenGV + '\'' +
                ", maNhomLop=" + maNhomLop +
                ", Nhom=" + Nhom +
                ", thucHanh=" + thucHanh +
                ", MMH=" + MMH +
                ", SLDK=" + SLDK +
                ", SLtkb=" + SLtkb +
                '}';
    }
}
