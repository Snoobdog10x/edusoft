package com.company.Class;

public class SinhVien {
    private String MSSV;
    private String malop;
    private String holot;
    private String ten;
    private String ngaysinh;
    private String manganh;
    private String noisinh;
    private String email;
    private String sdt;

    public SinhVien(){}

    public SinhVien(String MSSV, String malop, String holot, String ten, String ngaysinh, String manganh, String noisinh, String email, String sdt) {
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

    public String getMSSV() {
        return MSSV;
    }

    public void setMSSV(String MSSV) {
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

    public String getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(String ngaysinh) {
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
}
