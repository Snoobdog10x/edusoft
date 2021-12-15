package com.company.Class;

import java.sql.Date;

public class lichsudangky {
    private String MSSV;
    private String ten;
    private String manhomlop;
    private String Nhom;
    private String TH;
    private String MMH;
    private String tenmonhoc;
    private Date ngaydangki;

    public lichsudangky(String MSSV, String ten, String manhomlop, String nhom, String TH, String MMH, String tenmonhoc, Date ngaydangki) {
        this.MSSV = MSSV;
        this.ten = ten;
        this.manhomlop = manhomlop;
        Nhom = nhom;
        this.TH = TH;
        this.MMH = MMH;
        this.tenmonhoc = tenmonhoc;
        this.ngaydangki = ngaydangki;
    }

    public String getMSSV() {
        return MSSV;
    }

    public void setMSSV(String MSSV) {
        this.MSSV = MSSV;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getManhomlop() {
        return manhomlop;
    }

    public void setManhomlop(String manhomlop) {
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

    public String getMMH() {
        return MMH;
    }

    public void setMMH(String MMH) {
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
        Object[] objects={MSSV,ten,manhomlop,Nhom,MMH,TH,tenmonhoc,ngaydangki};
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
