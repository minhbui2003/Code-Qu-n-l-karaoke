/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connectdb.ConnectDB;
import entity.KhachHang;
import entity.LoaiPhong;
import entity.NhanVien;
import entity.PhieuDatPhong;
import entity.Phong;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author ad
 */
public class PhieuDatPhong_DAO {
    public PhieuDatPhong_DAO() 
        {
        }
        
        public ArrayList<PhieuDatPhong> getAllPhieuDatPhong() throws SQLException
	{
            
		ArrayList<PhieuDatPhong> dsPDP = new ArrayList<PhieuDatPhong>();
		
			//ket noi
			ConnectDB.getInstance();
			Connection con = ConnectDB.getInstance().getConnection();
			String sql = "SELECT * FROM PhieuDatPhong";
			Statement stmt = con.createStatement();
			//Thuc thi cau lenh SQL tra ve doi tuong ResultSet
			ResultSet rs = stmt.executeQuery(sql);
			//Duyet tren tung ket qua tra ve
			while(rs.next())
			{
                                String maPDP = rs.getString(1);
                                Phong maP = new Phong(rs.getString(2));
				KhachHang maKH = new KhachHang(rs.getString(3));
				NhanVien maNV = new NhanVien(rs.getString(4));
                                String ngayDatPhong = rs.getDate(5).toString();
                                String gioNhanPhong = rs.getTimestamp(6).toString();
				PhieuDatPhong pdp = new PhieuDatPhong(maPDP,maP ,maKH, maNV,ngayDatPhong,gioNhanPhong);
				dsPDP.add( pdp);    
			}
            
            return dsPDP;
	}
        public boolean themPhieuDatPhong(PhieuDatPhong x)
	{
		//ket noi ConnectDB
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n =0;
		String sql ="INSERT INTO PhieuDatPhong(MaPhieuDatPhong, MaP, MaKH, MaNV, NgayDatPhong, GioNhanPhong, TrangThaiDon) VALUES (?, ?, ?, ?, ?, ?, N'Chưa Thanh Toán') ";
		try {
			stmt = con.prepareStatement(sql);
                        stmt.setString(1,x.getMaPhieuDatPhong());
			stmt.setString(2,x.getPhong().getMaPhong());
                        stmt.setString(3, x.getKhachHang().getMaKhachHang());
			stmt.setString(4, x.getNhanVien().getMaNhanVien());
			stmt.setString(5, x.getNgayDatPhong());
                        stmt.setString(6,x.getGioNhanPhong());
                        
			n = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
                String sql2 ="Update Phong set TrangThaiPhong=N'Đầy' where MaPhong=?";
		try {
			stmt = con.prepareStatement(sql2);
                        stmt.setString(1,x.getPhong().getMaPhong());
			n = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				stmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return n > 0;
        }
        public boolean huyPhieuDatPhong(String maPDP,String maP)
        {
            Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n =0;
		String sql ="DELETE FROM PhieuDatPhong where MaPhieuDatPhong=?";
		try {
			stmt = con.prepareStatement(sql);
                        stmt.setString(1,maPDP);          
			n = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
                String sql2 ="Update Phong set TrangThaiPhong=N'Trống' where MaPhong=?";
		try {
			stmt = con.prepareStatement(sql2);
                        stmt.setString(1,maP);
			n = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				stmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return n > 0;
        }
        public ArrayList<PhieuDatPhong> layDSPhieuDatPhong() throws SQLException {
            ArrayList<PhieuDatPhong> dsPDP = new ArrayList<>();
            ConnectDB.getInstance();
            Connection con = ConnectDB.getInstance().getConnection();
            String sql = "SELECT pdp.MaPhieuDatPhong, pdp.MaP, p.TenPhong, p.GiaPhong, pdp.GioNhanPhong, pdp.MaKH, kh.TenKhachHang, nv.MaNhanVien,nv.TenNV\n" +
"                    FROM PhieuDatPhong pdp JOIN\n" +
"                    KhachHang kh ON pdp.MaKH = kh.MaKhachHang JOIN\n" +
"                    Phong p ON pdp.MaP = p.MaPhong JOIN NhanVien nv on pdp.MaNV=nv.MaNhanVien Where TrangThaiDon = N'Chưa Thanh Toán'";
            Statement stmt = con.createStatement();
            // Execute SQL statement and get ResultSet
            ResultSet rs = stmt.executeQuery(sql);

            // Iterate over the ResultSet
            while (rs.next()) {
                String maPDP = rs.getString("MaPhieuDatPhong");
                String maPhong = rs.getString("MaP");
                String tenPhong = rs.getString("TenPhong");
                double giaPhong = rs.getDouble("GiaPhong");
                String gioNhanPhong = rs.getTimestamp("GioNhanPhong").toString();
                String maKH = rs.getString("MaKH");
                String tenKhachHang = rs.getString("TenKhachHang");
                String maNhanVien = rs.getString("MaNhanVien");
                String tenNhanVien = rs.getString("TenNV");
                
                Phong phong = new Phong(maPhong, tenPhong, (float) giaPhong);
                KhachHang khachHang = new KhachHang(maKH, tenKhachHang);
                NhanVien nhanVien = new NhanVien(maNhanVien, tenNhanVien);
                PhieuDatPhong pdp = new PhieuDatPhong(maPDP, phong, khachHang, nhanVien, gioNhanPhong);
                dsPDP.add(pdp);
            }

            return dsPDP;
}
        public String khoiTaoMaPDPC(String maP,Date ngayDatPhong){
            SimpleDateFormat timeFormat = new SimpleDateFormat("ddMMyyHH");
            String ngayDP = timeFormat.format(ngayDatPhong);
            String maPDP = "PD" + maP + ngayDP ;
            return maPDP;
        }
        public String khoiTaoMaPDPN(String maP,LocalDate ngayDatPhong){
            SimpleDateFormat timeFormat = new SimpleDateFormat("ddMMyyHH");
            String ngayDP = timeFormat.format(ngayDatPhong);
            String maPDP = "PD" + maP + ngayDP ;
            return maPDP;
        }
}
