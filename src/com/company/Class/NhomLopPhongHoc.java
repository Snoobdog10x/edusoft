package com.company.Class;

public class NhomLopPhongHoc {
    private int maNhomLop;
    private int MPH;

    public NhomLopPhongHoc(int maNhomLop, int MPH) {
        this.maNhomLop = maNhomLop;
        this.MPH = MPH;
    }

    public int getMaNhomLop() {
        return maNhomLop;
    }

    public void setMaNhomLop(int maNhonLop) {
        this.maNhomLop = maNhonLop;
    }

    public int getMPH() {
        return MPH;
    }

    public void setMPH(int MPH) {
        this.MPH = MPH;
    }

    @Override
    public String toString() {
        return "NhomLopPhongHoc{" +
                "maNhonLop=" + maNhomLop +
                ", MPH=" + MPH +
                '}';
    }
}
