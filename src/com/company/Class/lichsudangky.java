package com.company.Class;

import java.sql.Date;

public class lichsudangky {
    private SinhVien sinhVien;
    private NhomLopHoc nhomLopHoc;
    private HocPhan hocPhan;
    private Date ngaydangki;

    public lichsudangky(SinhVien sinhVien, NhomLopHoc nhomLopHoc, HocPhan hocPhan, Date ngaydangki) {
        this.sinhVien = sinhVien;
        this.nhomLopHoc = nhomLopHoc;
        this.hocPhan = hocPhan;
        this.ngaydangki = ngaydangki;
    }

    public Object[] toObjectArray() {
        return new Object[]{sinhVien.getMSSV(),sinhVien.getHolot()+sinhVien.getTen(),nhomLopHoc.getManhomlop(),nhomLopHoc.getNhom(),nhomLopHoc.getThuchanh(),hocPhan.getMMH(),hocPhan.getTenmonhoc(),ngaydangki};
    }
}
