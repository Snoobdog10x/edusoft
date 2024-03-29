package com.company.Class;

import java.sql.Date;

public class SinhVien {
    private int MSSV;
    private String malop;
    private String holot;
    private String ten;
    private Date ngaysinh;
    private String manganh;
    private String noisinh;
    private String email;
    private String sdt;

    public SinhVien(){}

    public SinhVien(int MSSV, String malop, String holot, String ten, Date ngaysinh, String sdt ,String manganh, String noisinh, String email) {
        this.MSSV = MSSV;
        this.malop = malop;
        this.holot = holot;
        this.ten = ten;
        this.ngaysinh = ngaysinh;
        this.manganh = manganh;
        this.noisinh = noisinh;
        this.email = email;
        this.sdt = sdt;
    }

    public SinhVien( String malop, String holot, String ten, Date ngaysinh, String sdt ,String manganh, String noisinh, String email) {
        this.malop = malop;
        this.holot = holot;
        this.ten = ten;
        this.ngaysinh = ngaysinh;
        this.manganh = manganh;
        this.noisinh = noisinh;
        this.email = email;
        this.sdt = sdt;
    }
    public int getMSSV() {
        return MSSV;
    }

    public void setMSSV(int MSSV) {
        this.MSSV = MSSV;
    }

    public String getMalop() {
        return malop;
    }

    public void setMalop(String malop) {
        this.malop = malop;
    }

    public String getHolot() {
        return holot;
    }

    public void setHolot(String holot) {
        this.holot = holot;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public Date getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(Date ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public String getManganh() {
        return manganh;
    }

    public void setManganh(String manganh) {
        this.manganh = manganh;
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

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public Object[] toObjectArray(){
        Object[] objects={MSSV,malop,holot,ten,ngaysinh,sdt,manganh,noisinh,email};
        return objects;
    }

    @Override
    public String toString() {
        return "MSSV: " + MSSV +
                ", Tên Sinh Viên:'" + ten + '\'' +
                ", Mã Ngành: '" + manganh + '\'';
    }
}
