/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connectdb.ConnectDB;
import entity.LoaiPhong;
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
public class LoaiPhong_DAO {

    public ArrayList<LoaiPhong> layDanhSachLoaiPhong() throws SQLException {
        ArrayList<LoaiPhong> dsLP = new ArrayList<>();
        ConnectDB.getInstance();
	Connection con = ConnectDB.getInstance().getConnection();
        String sql = "SELECT * FROM LoaiPhong";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            LoaiPhong lp = new LoaiPhong();
            lp.setMaLoaiPhong(rs.getString(1));
            lp.setTenLoaiPhong(rs.getString(2));
            dsLP.add(lp);
        }
        return dsLP;
    }

    public boolean themLoaiPhong(LoaiPhong x) {
        Connection con = ConnectDB.getInstance().getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        String sql = "INSERT INTO LoaiPhong(MaLoaiPhong, TenLoaiPhong) VALUES(?,?)";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, x.getMaLoaiPhong());
            stmt.setString(2, x.getTenLoaiPhong());
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

    public boolean capNhatLoaiPhong(LoaiPhong x) {
        Connection con = ConnectDB.getInstance().getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        String sql = "UPDATE LoaiPhong SET TenLoaiPhong = ? WHERE MaLoaiPhong = ?";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, x.getTenLoaiPhong());
            stmt.setString(2, x.getMaLoaiPhong());
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

}
