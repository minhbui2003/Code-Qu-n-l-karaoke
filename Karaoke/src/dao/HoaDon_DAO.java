
package dao;

import connectdb.ConnectDB;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;
import entity.PhieuDatPhong;
import entity.Phong;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

public class HoaDon_DAO {
    public HoaDon_DAO() {
    }

    public ArrayList<HoaDon> layDanhSachHoaDon() throws SQLException {
        ArrayList<HoaDon> danhSachHoaDon = new ArrayList<>();
        ConnectDB.getInstance();
	Connection con = ConnectDB.getInstance().getConnection();
        Statement stmt = con.createStatement();
        String sql = "select hd.MaHoaDon, hd.MaP, p.TenPhong, p.GiaPhong, hd.GioNhanPhong, hd.TenKhachHang, nv.MaNhanVien,nv.TenNV\n" +
                    "from HoaDon hd join NhanVien nv\n" +
                    "	on hd.MaNV=nv.MaNhanVien join Phong p\n" +
                    "	on hd.MaP=p.MaPhong";
	ResultSet rs = stmt.executeQuery(sql);
        try {     
            while (rs.next()) {
                String maHoaDon = rs.getString("MaHoaDon");
                String maPhong = rs.getString("MaP");
                String tenPhong = rs.getString("TenPhong");
                double giaPhong = rs.getDouble("GiaPhong");
                Timestamp gioNhanPhong = rs.getTimestamp("GioNhanPhong");
                String tenKhachHang = rs.getString("TenKhachHang");
                String maNhanVien = rs.getString("MaNhanVien");
                String tenNhanVien = rs.getString("TenNV");
                
                Phong phong = new Phong(maPhong, tenPhong, (float) giaPhong);
                NhanVien nhanVien = new NhanVien(maNhanVien, tenNhanVien);
                
                HoaDon hoaDon = new HoaDon(maHoaDon, nhanVien, tenKhachHang, phong, gioNhanPhong);
                danhSachHoaDon.add(hoaDon);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Xử lý lỗi nếu cần
            throw e;
        } finally {
            // Đóng tất cả các nguồn tài nguyên (ResultSet, PreparedStatement, Connection)
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return danhSachHoaDon;
    }


public boolean themHoaDon(HoaDon hoaDon) throws SQLException {
    Connection con = null;
    PreparedStatement stmt = null;
    int n = 0;

    try {
        ConnectDB.getInstance();
        con = ConnectDB.getInstance().getConnection();

        // Get the current timestamp
        Timestamp ngayLapHD = new Timestamp(System.currentTimeMillis());
        String maHoaDon = generateMaHoaDon(ngayLapHD);

        // Initialize PreparedStatement for inserting HoaDon
        String sql = "INSERT INTO HoaDon (MaHoaDon, NgayLapHD, MaNV, TenKhachHang, MaP, GioNhanPhong, GioTraPhong, MaKM, TongTien) "
                + "VALUES (?, ?, ?, ?, ?, ?, null, null, null)";
        stmt = con.prepareStatement(sql);

        stmt.setString(1, maHoaDon);
        stmt.setTimestamp(2, ngayLapHD);
        stmt.setString(3, hoaDon.getNhanVien().getMaNhanVien());
        stmt.setString(4, hoaDon.getTenKhachHang());
        stmt.setString(5, hoaDon.getPhong().getMaPhong());

        // Set GioNhanPhong to the current timestamp
        stmt.setTimestamp(6, ngayLapHD);

        n = stmt.executeUpdate();

    } catch (SQLException e) {
        e.printStackTrace();
        throw e;
    } finally {
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    return n > 0;
}

    private String generateMaHoaDon(Timestamp ngayLapHD) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyHHmmss");
        String ngayThangNamGio = dateFormat.format(ngayLapHD);

        String maHoaDon = "HD" + ngayThangNamGio;
        return maHoaDon;
    }
    
public String timHoaDonTheoMaPhong(String maPhong) {
    Connection con = ConnectDB.getInstance().getConnection();
    PreparedStatement stmt = null;
    String maHoaDon = null;
    ResultSet rs = null;

    try {
        // Sử dụng thực thể của ConnectDB thông qua phương thức getInstance()
        
        System.out.println("Connection status before query: " + (con.isClosed() ? "Closed" : "Open"));

        String sql = "SELECT HoaDon.MaHoaDon FROM HoaDon JOIN Phong ON HoaDon.MaP = Phong.MaPhong WHERE Phong.MaPhong = ?";
        stmt = con.prepareStatement(sql);
        stmt.setString(1, maPhong);

        rs = stmt.executeQuery();

        while (rs.next()) {
            maHoaDon = rs.getString("MaHoaDon");
        }

    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            if (stmt != null) {
                stmt.close();
            }
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    return maHoaDon;
}

public HoaDon timHoaDonTheoMa(String maHoaDon) throws SQLException {
    HoaDon hoaDon = null;
    ConnectDB.getInstance();
    Connection con = ConnectDB.getInstance().getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;


    try {
        String sql = "SELECT * FROM HoaDon WHERE MaHoaDon = ?";
        stmt = con.prepareStatement(sql);
        stmt.setString(1, maHoaDon);
        rs = stmt.executeQuery();

        if (rs.next()) {
            Date ngayLapHD = rs.getDate("NgayLapHD");
            NhanVien maNV = new NhanVien(rs.getString("MaNV"));
            String tenKhachHang = rs.getString("TenKhachHang");
            Phong maP = new Phong(rs.getString("MaP"));
            Timestamp gioNhanPhong = rs.getTimestamp("GioNhanPhong");

            hoaDon = new HoaDon(maHoaDon, ngayLapHD, maNV, tenKhachHang, maP, gioNhanPhong);
        }
    } catch (SQLException e) {
        e.printStackTrace();
        // Handle the exception if needed
        throw e;
    } 
    return hoaDon;
}
}
