/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connectdb.ConnectDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Duong Ngo Manh
 */
public class DangNhap_DAO {

    private Connection connection;

    public DangNhap_DAO(Connection connection) {
        this.connection = connection;
    }

    public boolean kiemTraDangNhap(String soDienThoai, String matKhau) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean dangNhapThanhCong = false;

        try {
            String sql = "SELECT * FROM NhanVien WHERE SoDT = ? AND MatKhau = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, soDienThoai);
            stmt.setString(2, matKhau);
            rs = stmt.executeQuery();

            if (rs.next()) {
                dangNhapThanhCong = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return dangNhapThanhCong;
    }

    public String layChucVu(String soDienThoai, String matKhau) {
        String chucVu = null;
        Connection con = ConnectDB.getInstance().getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            String query = "SELECT ChucVu.TenChucVu FROM NhanVien JOIN ChucVu ON NhanVien.MaCV = ChucVu.MaChucVu WHERE SoDT = ? AND MatKhau = ?";
            stmt = con.prepareStatement(query);
            stmt.setString(1, soDienThoai);
            stmt.setString(2, matKhau);
            rs = stmt.executeQuery();
            if (rs.next()) {
                chucVu = rs.getString("TenChucVu");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return chucVu;
    }

}
