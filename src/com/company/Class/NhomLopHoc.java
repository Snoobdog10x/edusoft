package com.company.Class;

public class NhomLopHoc {
    private int Manhomlop;
    private int Nhom;
    private int thuchanh;
    private int MMH;
    private int SLdangki;
    private int SLtkb;

    public NhomLopHoc() {
    }

    public NhomLopHoc(int manhomlop) {
        Manhomlop = manhomlop;
    }

    public NhomLopHoc(int nhom, int thuchanh, int MMH, int SLdangki, int SLtkb) {
        Nhom = nhom;
        this.thuchanh = thuchanh;
        this.MMH = MMH;
        this.SLdangki = SLdangki;
        this.SLtkb = SLtkb;
    }
    public NhomLopHoc(int manhomlop, int nhom, int thuchanh, int MMH, int SLdangki, int SLtkb) {
        Manhomlop = manhomlop;
        Nhom = nhom;
        this.thuchanh = thuchanh;
        this.MMH = MMH;
        this.SLdangki = SLdangki;
        this.SLtkb = SLtkb;
    }
    public NhomLopHoc(int manhomlop,int nhom, int SLdangki, int SLtkb) {
        Manhomlop = manhomlop;
        Nhom = nhom;
        this.SLdangki = SLdangki;
        this.SLtkb = SLtkb;
    }

    public int getManhomlop() {
        return Manhomlop;
    }

    public void setManhomlop(int manhomlop) {
        Manhomlop = manhomlop;
    }

    public int getNhom() {
        return Nhom;
    }

    public void setNhom(int nhom) {
        Nhom = nhom;
    }

    public int getThuchanh() {
        return thuchanh;
    }

    public void setThuchanh(int thuchanh) {
        this.thuchanh = thuchanh;
    }

    public int getMMH() {
        return MMH;
    }

    public void setMMH(int MMH) {
        this.MMH = MMH;
    }

    public int getSLdangki() {
        return SLdangki;
    }

    public void setSLdangki(int SLdangki) {
        this.SLdangki = SLdangki;
    }

    public int getSLtkb() {
        return SLtkb;
    }

    public void setSLtkb(int SLtkb) {
        this.SLtkb = SLtkb;
    }

}
