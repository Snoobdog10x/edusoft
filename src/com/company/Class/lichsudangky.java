package com.company.Class;

import java.sql.Date;

public class lichsudangky {
    private int MSSV;
    private String ten;
    private int manhomlop;
    private String Nhom;
    private String TH;
    private int MMH;
    private String tenmonhoc;
    private Date ngaydangki;

    public lichsudangky(int MSSV, String ten, int manhomlop, String nhom, String TH, int MMH, String tenmonhoc, Date ngaydangki) {
        this.MSSV = MSSV;
        this.ten = ten;
        this.manhomlop = manhomlop;
        Nhom = nhom;
        this.TH = TH;
        this.MMH = MMH;
        this.tenmonhoc = tenmonhoc;
        this.ngaydangki = ngaydangki;
    }

    public int getMSSV() {
        return MSSV;
    }

    public void setMSSV(int MSSV) {
        this.MSSV = MSSV;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getManhomlop() {
        return manhomlop;
    }

    public void setManhomlop(int manhomlop) {
        this.manhomlop = manhomlop;
    }

    public String getNhom() {
        return Nhom;
    }

    public void setNhom(String nhom) {
        Nhom = nhom;
    }

    public String getTH() {
        return TH;
    }

    public void setTH(String TH) {
        this.TH = TH;
    }

    public int getMMH() {
        return MMH;
    }

    public void setMMH(int MMH) {
        this.MMH = MMH;
    }

    public String getTenmonhoc() {
        return tenmonhoc;
    }

    public void setTenmonhoc(String tenmonhoc) {
        this.tenmonhoc = tenmonhoc;
    }

    public Date getNgaydangki() {
        return ngaydangki;
    }

    public void setNgaydangki(Date ngaydangki) {
        this.ngaydangki = ngaydangki;
    }
    public Object[] toObjectArray(){
        Object[] objects={MSSV,ten,manhomlop,Nhom,TH,MMH,tenmonhoc,ngaydangki};
        return objects;
    }
    @Override
    public String toString() {
        return "lichsudangky{" +
                "MSSV='" + MSSV + '\'' +
                ", ten='" + ten + '\'' +
                ", manhomlop='" + manhomlop + '\'' +
                ", Nhom='" + Nhom + '\'' +
                ", TH='" + TH + '\'' +
                ", MMH='" + MMH + '\'' +
                ", tenmonhoc='" + tenmonhoc + '\'' +
                ", ngaydangki=" + ngaydangki +
                '}';
    }
}
