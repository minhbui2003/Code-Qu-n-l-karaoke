/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connectdb.ConnectDB;
import entity.DichVu;
import entity.LoaiPhong;
import entity.Phong;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author ad
 */
public class DichVu_DAO {
	public ArrayList<DichVu> getAllDichVu() throws SQLException
	{
            
		ArrayList<DichVu> dsDV = new ArrayList<DichVu>();
		
			//ket noi
			ConnectDB.getInstance();
			Connection con = ConnectDB.getInstance().getConnection();
			String sql = "SELECT * FROM DichVu";
			Statement stmt = con.createStatement();
			//Thuc thi cau lenh SQL tra ve doi tuong ResultSet
			ResultSet rs = stmt.executeQuery(sql);
			//Duyet tren tung ket qua tra ve
			while(rs.next())
			{
                                DichVu dv = new DichVu();
                                dv.setMaDV(rs.getString(1));
				dv.setTenDV(rs.getString(2));
				dv.setSoLuong(rs.getInt(3));
                                dv.setGiaBan(rs.getDouble(4));
				dv.setDonViTinh(rs.getString(5));
                                dv.setTrangThaiDV(rs.getBoolean(6));
				dsDV.add(dv);      
			}
            
            return dsDV;
	}
        public boolean themDichVu(DichVu x)
	{
		//ket noi ConnectDB
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n =0;
		String sql ="INSERT INTO DichVu(MaDichVu,TenDichVu,SoLuongDichVu,GiaBan,DonViTinh,trangThaiDichVu) VALUES(?,?,?,?,?,?)";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1,x.getMaDV());
			stmt.setString(2, x.getTenDV());
			stmt.setInt(3, x.getSoLuong());
                        stmt.setDouble(4, x.getGiaBan());
			stmt.setString(5, x.getDonViTinh());
                        stmt.setBoolean(6, x.isTrangThaiDV());
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
        public ArrayList<DichVu> locDichVu(String maDV,String tenDV,boolean trangThaiDV)
		{
			ArrayList<DichVu> dsp = new ArrayList<DichVu>();
			//ket noi 
			ConnectDB.getInstance();
			Connection con = ConnectDB.getInstance().getConnection();
			PreparedStatement stmt = null;
			try {
				String sql = "SELECT * FROM DichVu WHERE MaDichVu=? or TenDichVu=? or trangThaiDichVu=?";
				stmt = con.prepareStatement(sql);
				stmt.setString(1, maDV);
                                stmt.setString(2, tenDV);
                                stmt.setBoolean(3, trangThaiDV);
				//thuc thi cau lenh sql tra ve doi tuong ResultSet
				ResultSet rs = stmt.executeQuery();
				//Duyet tren ket qua tra ve
				while(rs.next())
				{
					String maDichVu = rs.getString(1);
                                        String tenDichVu = rs.getString(2);
                                        int soLuongDV = rs.getInt(3);
                                        double giaBan = rs.getDouble(4);
                                        String donViTinh = rs.getString(5);
					Boolean trangThaiDichVu = rs.getBoolean(6);
					
					DichVu x = new DichVu(maDichVu,tenDichVu, soLuongDV,giaBan, donViTinh,trangThaiDichVu);
					
					//them nv x vao dsnv
					dsp.add(x);
				}
				
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
			return dsp;
		}
        
        public DichVu timDichVuTheoTen(String tenDV) throws SQLException {
    DichVu dichVu = null;

    // Create an instance of ConnectDB
    ConnectDB connectDB = ConnectDB.getInstance();
    if (connectDB.getConnection() == null || connectDB.getConnection().isClosed()) {
        connectDB.connect();  // Re-establish the connection
}
    Connection con = connectDB.getConnection();
String sql = "SELECT * FROM DichVu WHERE TenDichVu=?";
    // Rest of your code remains unchanged...
    try (PreparedStatement stmt = con.prepareStatement(sql)) {
    // Your code here
} catch (SQLException e) {
    e.printStackTrace();
}
        
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, tenDV);
            // Thuc thi cau lenh SQL tra ve doi tuong ResultSet
            ResultSet rs = stmt.executeQuery();

            // Neu tim thay dich vu, gan gia tri cho dichVu
            if (rs.next()) {
                String maDichVu = rs.getString(1);
                String tenDichVu = rs.getString(2);
                int soLuongDV = rs.getInt(3);
                double giaBan = rs.getDouble(4);
                String donViTinh = rs.getString(5);
                Boolean trangThaiDichVu = rs.getBoolean(6);

                dichVu = new DichVu(maDichVu, tenDichVu, soLuongDV, giaBan, donViTinh, trangThaiDichVu);
            }
        }
   
    return dichVu;
}
        
        public DichVu timDichVuTheoMa(String maDV) throws SQLException {
            DichVu dichVu = null;

            // Ket noi 
            ConnectDB.getInstance().connect();  // Ensure the connection is open
            Connection con = ConnectDB.getInstance().getConnection();   
            PreparedStatement stmt = null;

            try {
                String sql = "SELECT * FROM DichVu WHERE MaDichVu=?";
                stmt = con.prepareStatement(sql);
                stmt.setString(1, maDV);

                // Thuc thi cau lenh SQL tra ve doi tuong ResultSet
                ResultSet rs = stmt.executeQuery();

                // Neu tim thay dich vu, gan gia tri cho dichVu
                if (rs.next()) {
                    String maDichVu = rs.getString(1);
                    String tenDichVu = rs.getString(2);
                    int soLuongDV = rs.getInt(3);
                    double giaBan = rs.getDouble(4);
                    String donViTinh = rs.getString(5);
                    Boolean trangThaiDichVu = rs.getBoolean(6);

                    dichVu = new DichVu(maDichVu, tenDichVu, soLuongDV, giaBan, donViTinh, trangThaiDichVu);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    stmt.close();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            return dichVu;
        }
}
