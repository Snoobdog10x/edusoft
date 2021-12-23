package com.company.DatabaseConnection;

import com.company.Class.KHGD;
import com.company.Class.SinhVien;
import com.company.Class.lichsudangky;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Database {
    private String URL = "jdbc:mysql://snooby.ddns.net:3306/cnpm";
    private String User = "root";
    private String pass = "thanhanh";
    private Connection conn;

    public Database() {
        connectdb();
    }

    public void connectdb() {
        try {
            conn = DriverManager.getConnection(URL, User, pass);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public ResultSet getResultsetbySQL(String SQL) {
        try {
            Statement stmt = null;
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            return rs;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    public int updatetoDatabasebySQL(String SQL) {
        try {
            Statement stmt = null;
            stmt = conn.createStatement();
            int rowcount = stmt.executeUpdate(SQL);
            return rowcount;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return 0;
        }
    }

    public List<lichsudangky> getListLSDK() {
        List<lichsudangky> list = new ArrayList<lichsudangky>();
        String SQL = "SELECT l.ID, s.MSSV,s.ten,n.manhomlop,n.Nhom,n.thuchanh,h.MMH,h.tenmonhoc,l.ngaydangki " +
                "FROM lichsudangky l, sinhvien s, nhomlophoc n,hocphan h " +
                "WHERE l.MSSV=s.MSSV and l.Manhomlop=n.Manhomlop and n.MMH=h.MMH";
        ResultSet rs = getResultsetbySQL(SQL);
        try {
            while (rs.next()) {
                lichsudangky ls = new lichsudangky(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getString(8), rs.getDate(9));
                list.add((lichsudangky) ls);
            }
            return list;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return list;
        }
    }

    public List<SinhVien> getListQLSV() {
        List<SinhVien> list = new ArrayList<SinhVien>();
        String SQL = "SELECT * " +
                "FROM  sinhvien ";
        //System.out.println(SQL);
        ResultSet rs = getResultsetbySQL(SQL);
        try {
            while (rs.next()) {
                SinhVien sv = new SinhVien(rs.getInt("MSSV"), rs.getString("malop"), rs.getString("holot"), rs.getString("ten"), rs.getDate("ngaysinh"),
                        rs.getString("sdt"), rs.getString("manganh"), rs.getString("noisinh"), rs.getString("email"));
                list.add(sv);
            }
            return list;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return list;
        }
    }

    public boolean addsv(SinhVien sv) {
        boolean check = false;
        List<SinhVien> list = new ArrayList<SinhVien>();
        String SQL = "Insert into sinhvien(malop,holot,ten,ngaysinh,sdt,manganh,noisinh,email) " +
                "values('" + sv.getMalop() + "','" + sv.getHolot() + "'," + sv.getTen() + "," + sv.getNgaysinh() +
                "" + sv.getSdt() + "," + sv.getManganh() + "," + sv.getNoisinh() + "," + sv.getEmail() + " ) ";
        //System.out.println(SQL);
        int row = updatetoDatabasebySQL(SQL);
        if (row == 1) {
            check = true;
        }
        return check;
    }

    public void closedb() {
        try {
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private boolean isdupplicateupdate(lichsudangky ls) {
        String SQL = "select * \n" +
                "from lichsudangky l\n" +
                "where l.MSSV=" + ls.getMSSV() + " and l.Manhomlop=" + ls.getManhomlop();
        try {
            ResultSet rs = getResultsetbySQL(SQL);
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int updatelsdk(lichsudangky ls) {
        if (!isdupplicateupdate(ls)) {
            String query = ("UPDATE lichsudangky SET Manhomlop=? WHERE ID=?");
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setInt(1, ls.getManhomlop());
                pstmt.setInt(2, ls.getID());
                //System.out.println(pstmt);
                return pstmt.executeUpdate();
            } catch (SQLException ex) {
                return -1;
            }
        }
        return -1;
    }

    public List<Object[]> getkhgd() {
        List<Object[]> list = new ArrayList<>();
        String SQL = "SELECT n.*,a.ten,nlph.MPH FROM (SELECT nl.*,hp.tenmonhoc,hp.sotinchi,hp.sotiet FROM nhomlophoc nl,hocphan hp WHERE nl.MMH=hp.MMH) n, nhomlopphonghoc nlph ,(SELECT v.ten, vn.Manhomlop FROM vienchuc v,vienchucnhomlop vn WHERE v.MVC=vn.MVC) a WHERE n.Manhomlop=a.Manhomlop && n.Manhomlop = nlph.Manhomlop && nlph.Manhomlop=a.Manhomlop";
        //System.out.println(SQL);
        ResultSet rs = getResultsetbySQL(SQL);
        try {
            int i = 0;
            while (rs.next()) {
                Object[] objects = {rs.getObject(1), rs.getObject(2), rs.getObject(3)
                        , rs.getObject(4), rs.getObject(5), rs.getObject(6)
                        , rs.getObject(7), rs.getObject(8), rs.getObject(9)
                        , rs.getObject(10), rs.getObject(11)};
                list.add(objects);
            }
            return list;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return list;
        }
    }

    private boolean isDuplicate(int MSSV, int MMH) {
        String SQL = "select * \n" +
                "from hocphan h, nhomlophoc n, lichsudangky l\n" +
                "where h.MMH=n.MMH and l.Manhomlop=n.Manhomlop and l.MSSV=" + MSSV + " and h.MMH=" + MMH;
        try {
            ResultSet rs = getResultsetbySQL(SQL);
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int DKMH(int MSSV, int MMH, int MNL) throws SQLException {
        if (!isDuplicate(MSSV, MMH)) {
            String sql = "Insert into lichsudangky (MSSV,Manhomlop,ngaydangki) " +
                    "Values(?,?,?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, MSSV);
            statement.setInt(2, MNL);
            statement.setDate(3, new Date(Calendar.getInstance().getTime().getTime()));
            return statement.executeUpdate();
        } else {
            return -1;
        }
    }
}
