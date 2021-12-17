package com.company.DatabaseConnection;

import com.company.Class.KHGD;
import com.company.Class.SinhVien;
import com.company.Class.lichsudangky;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {
    private String URL="jdbc:mysql://snooby.ddns.net:3306/cnpm";
    private String User="root";
    private String pass="thanhanh";
    private Connection conn;

    public Database(){
        connectdb();
    }
    public void connectdb(){
        try{
            conn= DriverManager.getConnection(URL,User,pass);
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public ResultSet getResultsetbySQL(String SQL){
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
    public int updatetoDatabasebySQL(String SQL){
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
                "WHERE l.MSSV=s.MSSV and l.Manhomlop=n.Manhomlop and l.MMH=h.MMH";
        ResultSet rs = getResultsetbySQL(SQL);
        try {
            int i = 0;
            while (rs.next()) {
                lichsudangky ls=new lichsudangky(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getString(6),rs.getInt(7),rs.getString(8),rs.getDate(9));
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
                SinhVien sv = new SinhVien(rs.getInt("MSSV"),rs.getString("malop"),rs.getString("holot"), rs.getString("ten"),rs.getDate("ngaysinh"),
                        rs.getString("sdt"),rs.getString("manganh"),rs.getString("noisinh"),rs.getString("email"));
                list.add(sv);
            }
            return list;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return list;
        }
    }
    public boolean addsv(SinhVien sv){
        boolean check = false;
        List<SinhVien> list = new ArrayList<SinhVien>();
        String SQL = "Insert into sinhvien(malop,holot,ten,ngaysinh,sdt,manganh,noisinh,email) " +
                "values('" + sv.getMalop() + "','" + sv.getHolot() + "'," + sv.getTen() + "," + sv.getNgaysinh() +
                "" + sv.getSdt()+ "," + sv.getManganh() + "," + sv.getNoisinh() + "," + sv.getEmail() +" ) ";
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
    public void updatelsdk(lichsudangky ls){
        String query = ("UPDATE lichsudangky SET Manhomlop=? WHERE ID=?");
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, ls.getManhomlop());
            pstmt.setInt(2, ls.getID());
            pstmt.executeUpdate();
        }
        catch (SQLException ex) {
            // Exception handling
        }
    }
    public List<KHGD> getKHGD() {
        List<KHGD> list = new ArrayList<>();
        String SQL = "SELECT n.*,a.ten " +
                "FROM nhomlophoc n, (SELECT v.ten, vn.Manhomlop " +
                "FROM vienchuc v,vienchucnhomlop vn " +
                "WHERE v.MVC=vn.MVC) a " +
                "WHERE n.Manhomlop=a.Manhomlop ";
        ResultSet rs = getResultsetbySQL(SQL);
        try {
            while (rs.next()) {
                KHGD PlanTeching = new KHGD(rs.getString("holot"),
                        rs.getString("ten"), rs.getInt("Manhomlop"),
                        rs.getInt("nhom"), rs.getInt("thuchanh")
                        , rs.getInt("MMH"), rs.getInt("SLdangki"), rs.getInt("SLtkb"));
                list.add(PlanTeching);
            }
            return list;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return list;
        }
    }

}
