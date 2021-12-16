package com.company.Class;

public class HocPhan {
    private int MMH;
    private String tenmonhoc;
    private String mabomon;
    private int sotinchi;
    private int sotiet;
    private int sotietthuchanh;

    public HocPhan() {
    }

    public HocPhan(int MMH, String tenmonhoc, String mabomon, int sotinchi, int sotiet, int sotietthuchanh) {
        this.MMH = MMH;
        this.tenmonhoc = tenmonhoc;
        this.mabomon = mabomon;
        this.sotinchi = sotinchi;
        this.sotiet = sotiet;
        this.sotietthuchanh = sotietthuchanh;
    }

    public HocPhan(String tenmonhoc, String mabomon, int sotinchi, int sotiet, int sotietthuchanh) {
        this.MMH = MMH;
        this.tenmonhoc = tenmonhoc;
        this.mabomon = mabomon;
        this.sotinchi = sotinchi;
        this.sotiet = sotiet;
        this.sotietthuchanh = sotietthuchanh;
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

    public String getMabomon() {
        return mabomon;
    }

    public void setMabomon(String mabomon) {
        this.mabomon = mabomon;
    }

    public int getSotinchi() {
        return sotinchi;
    }

    public void setSotinchi(int sotinchi) {
        this.sotinchi = sotinchi;
    }

    public int getSotiet() {
        return sotiet;
    }

    public void setSotiet(int sotiet) {
        this.sotiet = sotiet;
    }

    public int getSotietthuchanh() {
        return sotietthuchanh;
    }

    public void setSotietthuchanh(int sotietthuchanh) {
        this.sotietthuchanh = sotietthuchanh;
    }
    public Object[] toObjectArray(){
        Object[] objects={MMH,tenmonhoc,mabomon,sotinchi,sotiet,sotietthuchanh};
        return objects;
    }

    @Override
    public String toString() {
        return "HocPhan{" +
                "MMH='" + MMH + '\'' +
                ", tenmonhoc='" + tenmonhoc + '\'' +
                ", mabomon='" + mabomon + '\'' +
                ", sotinchi=" + sotinchi +
                ", sotiet=" + sotiet +
                ", sotietthuchanh=" + sotietthuchanh +
                '}';
    }
}
