package com.company.Class;

public class PhongHoc {
    private String coSo, dayPhong, tenPhong;
    private int sucChua;
    private String MPH;

    public PhongHoc(String coSo, String dayPhong, String tenPhong, int sucChua, String MPH) {
        this.coSo = coSo;
        this.dayPhong = dayPhong;
        this.tenPhong = tenPhong;
        this.sucChua = sucChua;
        this.MPH = MPH;
    }

    public String getCoSo() {
        return coSo;
    }

    public void setCoSo(String coSo) {
        this.coSo = coSo;
    }

    public String getDayPhong() {
        return dayPhong;
    }

    public void setDayPhong(String dayPhong) {
        this.dayPhong = dayPhong;
    }

    public String getTenPhong() {
        return tenPhong;
    }

    public void setTenPhong(String tenPhong) {
        this.tenPhong = tenPhong;
    }

    public int getSucChua() {
        return sucChua;
    }

    public void setSucChua(int sucChua) {
        this.sucChua = sucChua;
    }

    public String getMPH() {
        return MPH;
    }

    public void setMPH(String MPH) {
        this.MPH = MPH;
    }
}
