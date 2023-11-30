/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connectdb.ConnectDB;
import entity.ChucVu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Duong Ngo Manh
 */
public class ChucVu_DAO {

    public ArrayList<ChucVu> layDanhSachChucVu() throws SQLException {
        ArrayList<ChucVu> dsCV = new ArrayList<>();
        ConnectDB.getInstance();
        Connection con = ConnectDB.getInstance().getConnection();
        String sql = "SELECT * FROM ChucVu";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            ChucVu cv = new ChucVu();
            cv.setMaChucVu(rs.getString(1));
            cv.setTenChucVu(rs.getString(2));
            dsCV.add(cv);
//            String maCV = rs.getString(1);
//            String tenCV = rs.getString(2);
//            ChucVu cv = new ChucVu(maCV, tenCV);
//            dsCV.add(cv);
        }
        return dsCV;
    }

    public boolean themChucVu(ChucVu x) {
        Connection con = ConnectDB.getInstance().getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        String sql = "INSERT INTO ChucVu(MaChucVu, TenChucVu) VALUE(?,?)";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, x.getMaChucVu());
            stmt.setString(2, x.getTenChucVu());
            n = stmt.executeUpdate();
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                stmt.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return n > 0;
    }

    // cập nhật chức vụ
    public boolean capNhatChucVu(ChucVu x) {
        Connection con = ConnectDB.getInstance().getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        String sql = "UPDATE ChucVu SET TenChucVu = ? WHERE MaChucVu = ?";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, x.getTenChucVu());
            stmt.setString(2, x.getMaChucVu());
            n = stmt.executeUpdate();
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                stmt.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return n > 0;
    }

    // tìm chức vụ theo mã chức vụ
    public ChucVu timChucVuTheoMa(String maChucVu) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getInstance().getConnection();
        PreparedStatement stmt = null;
        ChucVu cv = null;
        try {
            String sql = "SELECT * FROM ChucVu WHERE MaChucVu=?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, maChucVu);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String maCV = rs.getString(1);
                String tenCV = rs.getString(2);

                cv = new ChucVu(maCV, tenCV);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                stmt.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return cv;
    }
}
