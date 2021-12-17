package com.company.Class;

import java.sql.Date;

public class Vienchuc {
    private int MVC;
    private String loaivc;
    private String ten;
    private String holot;
    private String sdt;
    private Date ngaysinh;
    private String noisinh;
    private String email;

    public Vienchuc() {
    }

    public Vienchuc(int MVC, String loaivc, String ten, String holot, String sdt, Date ngaysinh, String noisinh, String email) {
        this.MVC = MVC;
        this.loaivc = loaivc;
        this.ten = ten;
        this.holot = holot;
        this.sdt = sdt;
        this.ngaysinh = ngaysinh;
        this.noisinh = noisinh;
        this.email = email;
    }

    public Vienchuc(int MVC, String holot,String ten) {
        this.MVC = MVC;
        this.ten = ten;
        this.holot = holot;
    }

    public Vienchuc(String loaivc, String ten, String holot, String sdt, Date ngaysinh, String noisinh, String email) {
        this.MVC = MVC;
        this.loaivc = loaivc;
        this.ten = ten;
        this.holot = holot;
        this.sdt = sdt;
        this.ngaysinh = ngaysinh;
        this.noisinh = noisinh;
        this.email = email;
    }

    public int getMVC() {
        return MVC;
    }

    public void setMVC(int MVC) {
        this.MVC = MVC;
    }

    public String getLoaivc() {
        return loaivc;
    }

    public void setLoaivc(String loaivc) {
        this.loaivc = loaivc;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getHolot() {
        return holot;
    }

    public void setHolot(String holot) {
        this.holot = holot;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public Date getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(Date ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public String getNoisinh() {
        return noisinh;
    }

    public void setNoisinh(String noisinh) {
        this.noisinh = noisinh;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Object[] toObjectArray() {
        Object[] objects = {MVC, loaivc, ten, holot, sdt, ngaysinh, noisinh, email};
        return objects;
    }

    @Override
    public String toString() {
        return
                "mã viên chức=" + MVC + "-" +
                "" + holot  + " " + ten;
    }

}
