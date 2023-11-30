/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connectdb.ConnectDB;
import entity.ChucVu;
import entity.NhanVien;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author Duong Ngo Manh
 */
public class NhanVien_DAO {

    public ArrayList<NhanVien> layDanhSachNhanVien() throws SQLException {
        ArrayList<NhanVien> dsNV = new ArrayList<>();
        ConnectDB.getInstance();
        Connection con = ConnectDB.getInstance().getConnection();
        String sql = "SELECT NhanVien.MaNhanVien, ChucVu.TenChucVu, NhanVien.TenNV, NhanVien.GioiTinh, NhanVien.NgaySinh, NhanVien.SoDT, NhanVien.DiaChi, NhanVien.MatKhau FROM NhanVien JOIN ChucVu ON ChucVu.MaChucVu = NhanVien.MaCV";
//        String sql = "SELECT * FROM NhanVien";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            String maNV = rs.getString(1);
            ChucVu tenCV = new ChucVu(rs.getString(2));
            String tenNV = rs.getString(3);
//            boolean gioiTinh = rs.getBoolean(4);
            String gioiTinh = rs.getString(4);
            String ngaySinh = rs.getString(5);
            String soDT = rs.getString(6);
            String diaChi = rs.getString(7);
            String matKhau = rs.getString(8);
            NhanVien nv = new NhanVien(maNV, tenCV, tenNV, gioiTinh, ngaySinh, soDT, diaChi, matKhau);

            dsNV.add(nv); //thêm nhân viên vào danh sách
        }
        return dsNV;
    }

    public boolean themNhanVien(NhanVien x) {
        Connection con = ConnectDB.getInstance().getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        String sql = "INSERT INTO NhanVien(MaNhanVien, MaCV, TenNV, GioiTinh, NgaySinh, SoDT, DiaChi, MatKhau) VALUES(?,?,?,?,?,?,?,?)";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, x.getMaNhanVien());
            String maChucVu = (String) x.getChucVu().getMaChucVu();
            stmt.setString(2, maChucVu);
            stmt.setString(3, x.getTenNhanVien());
            stmt.setString(4, x.getGioiTinh());
            stmt.setString(5, x.getNgaySinh());
            stmt.setString(6, x.getSoDienThoai());
            stmt.setString(7, x.getDiaChi());
            stmt.setString(8, x.getMatKhau());
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

    public boolean capNhatNhanVien(NhanVien x) {
        Connection con = ConnectDB.getInstance().getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        String sql = "UPDATE NhanVien SET MaCV = ?, TenNV = ?, GioiTinh = ?, NgaySinh = ?, SoDT = ?, DiaChi = ?, MatKhau = ? WHERE MaNhanVien = ?";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, x.getChucVu().getMaChucVu());
            stmt.setString(2, x.getTenNhanVien());
            stmt.setString(3, x.getGioiTinh());
            stmt.setString(4, x.getNgaySinh());
            stmt.setString(5, x.getSoDienThoai());
            stmt.setString(6, x.getDiaChi());
            stmt.setString(7, x.getMatKhau());
            stmt.setString(8, x.getMaNhanVien());
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

    public NhanVien timNhanVienTheoMa(String maNhanVien) throws SQLException {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getInstance().getConnection();
        String sql = "SELECT NhanVien.MaNhanVien, ChucVu.TenChucVu, NhanVien.TenNV, NhanVien.GioiTinh, NhanVien.NgaySinh, NhanVien.SoDT, NhanVien.DiaChi, NhanVien.MatKhau "
                + "FROM NhanVien JOIN ChucVu ON ChucVu.MaChucVu = NhanVien.MaCV "
                + "WHERE NhanVien.MaNhanVien = ?";

        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, maNhanVien);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            ChucVu tenCV = new ChucVu(rs.getString(2));
            String tenNV = rs.getString(3);
            String gioiTinh = rs.getString(4);
            String ngaySinh = rs.getString(5);
            String soDT = rs.getString(6);
            String diaChi = rs.getString(7);
            String matKhau = rs.getString(8);
            NhanVien nv = new NhanVien(maNhanVien, tenCV, tenNV, gioiTinh, ngaySinh, soDT, diaChi, matKhau);
            return nv;
        } else {
            return null;
        }
    }

    public ArrayList<NhanVien> timNhanVienTheoTen(String tenNhanVien) throws SQLException {
        ArrayList<NhanVien> danhSachNhanVienTheoTen = new ArrayList<>();

        ConnectDB.getInstance();
        Connection con = ConnectDB.getInstance().getConnection();
        String sql = "SELECT NhanVien.MaNhanVien, ChucVu.TenChucVu, NhanVien.TenNV, NhanVien.GioiTinh, NhanVien.NgaySinh, NhanVien.SoDT, NhanVien.DiaChi, NhanVien.MatKhau "
                + "FROM NhanVien JOIN ChucVu ON ChucVu.MaChucVu = NhanVien.MaCV "
                + "WHERE NhanVien.TenNV LIKE ?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, "%" + tenNhanVien + "%"); // Tìm kiếm một phần của tên nhân viên

        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            String maNV = rs.getString(1);
            ChucVu tenCV = new ChucVu(rs.getString(2));
            String tenNV = rs.getString(3);
            String gioiTinh = rs.getString(4);
            String ngaySinh = rs.getString(5);
            String soDT = rs.getString(6);
            String diaChi = rs.getString(7);
            String matKhau = rs.getString(8);

            NhanVien nv = new NhanVien(maNV, tenCV, tenNV, gioiTinh, ngaySinh, soDT, diaChi, matKhau);
            danhSachNhanVienTheoTen.add(nv);
        }

        rs.close();
        pstmt.close();
        con.close();

        return danhSachNhanVienTheoTen;
    }

    public ArrayList<NhanVien> layDanhSachNhanVienTheoChucVu(String chucVu) throws SQLException {
        ArrayList<NhanVien> danhSachNhanVienTheoChucVu = new ArrayList<>();

        ConnectDB.getInstance();
        Connection con = ConnectDB.getInstance().getConnection();
        String sql = "SELECT NhanVien.MaNhanVien, ChucVu.TenChucVu, NhanVien.TenNV, NhanVien.GioiTinh, NhanVien.NgaySinh, NhanVien.SoDT, NhanVien.DiaChi, NhanVien.MatKhau FROM NhanVien JOIN ChucVu ON ChucVu.MaChucVu = NhanVien.MaCV WHERE ChucVu.TenChucVu = ?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, chucVu);
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            String maNV = rs.getString(1);
            ChucVu tenCV = new ChucVu(rs.getString(2));
            String tenNV = rs.getString(3);
            String gioiTinh = rs.getString(4);
            String ngaySinh = rs.getString(5);
            String soDT = rs.getString(6);
            String diaChi = rs.getString(7);
            String matKhau = rs.getString(8);

            NhanVien nv = new NhanVien(maNV, tenCV, tenNV, gioiTinh, ngaySinh, soDT, diaChi, matKhau);
            danhSachNhanVienTheoChucVu.add(nv);
        }
        return danhSachNhanVienTheoChucVu;
    }

}
