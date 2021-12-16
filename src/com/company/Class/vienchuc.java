package com.company.Class;

public class vienchuc {
    private String MVC;
    private String loaivc;
    private String ten;
    private String holot;
    private String sdt;
    private String ngaysinh;
    private String noisinh;
    private String email;

    public vienchuc() {
    }

    public vienchuc(String MVC, String loaivc, String ten, String holot, String sdt, String ngaysinh, String noisinh, String email) {
        this.MVC = MVC;
        this.loaivc = loaivc;
        this.ten = ten;
        this.holot = holot;
        this.sdt = sdt;
        this.ngaysinh = ngaysinh;
        this.noisinh = noisinh;
        this.email = email;
    }

    public String getMVC() {
        return MVC;
    }

    public void setMVC(String MVC) {
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

    public String getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(String ngaysinh) {
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
    public Object[] toObjectArray(){
        Object[] objects={ MVC, loaivc, ten, holot, sdt, ngaysinh, noisinh, email};
        return objects;
    }

    @Override
    public String toString() {
        return "vienchuc{" +
                "MVC='" + MVC + '\'' +
                ", loaivc='" + loaivc + '\'' +
                ", ten='" + ten + '\'' +
                ", holot='" + holot + '\'' +
                ", sdt='" + sdt + '\'' +
                ", ngaysinh='" + ngaysinh + '\'' +
                ", noisinh='" + noisinh + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
