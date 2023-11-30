
package dao;

import connectdb.ConnectDB;
import entity.CT_HoaDon;
import entity.DichVu;
import entity.HoaDon;
import entity.Phong;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;


public class CTHoaDon_DAO {
    private HoaDon hoaDon = new HoaDon();
    private DichVu_DAO dichVu_dao = new DichVu_DAO();
    private HoaDon_DAO hoaDon_dao = new HoaDon_DAO();
    private Phong_DAO phong_dao = new Phong_DAO();
    
public boolean themChiTietHoaDon(CT_HoaDon ctHD) {
    int n = 0;

    try (Connection con = ConnectDB.getInstance().getConnection();
         PreparedStatement stmt = con.prepareStatement(
                 "INSERT INTO CT_HoaDon (MaHD, MaDV, SoLuong, MaP) VALUES (?, ?, ?, ?)")) {
        System.out.println("Connection status before query: " + (con.isClosed() ? "Closed" : "Open"));
        // Set the HoaDon property before executing the query
        ctHD.setHoaDon(hoaDon_dao.timHoaDonTheoMa(ctHD.getHoaDon().getMaHD()));

        stmt.setString(1, ctHD.getHoaDon().getMaHD());
        stmt.setString(2, ctHD.getDichVu().getMaDV());
        stmt.setInt(3, ctHD.getSoLuong());
        stmt.setString(4, ctHD.getPhong().getMaPhong());

        n = stmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
        // Log or handle the exception appropriately
    }

    return n > 0;
}
    public static String dinhDangGio(int tongPhut) {
		int gio = tongPhut / 60;
		int phut = tongPhut % 60;
		if (gio != 0) {
			return gio + " Giờ " + phut + " Phút ";
		}
		return phut + " Phút ";
	}
    
//    public int capNhatThoiLuong(String maPhong) {
//		if (this.hoaDon.getGioTraPhong()== null) {
//			hoaDon.getGioTraPhong() = new Date();
//		}
//		//60000 = 60 * 1000 ms = 1p
//		//gio ket thuc - gio nhan phong ( don vi PHUT )
//		long phut = ((gioKetThuc.getTime() / 60000) - (gioNhanPhong.getTime() / 60000));
//		long phut2 = phut;
//		int size = chiTietHoaDon.size();
//		int index = -1;
//		for (int i = 0; i < size; i++) {
//			if (chiTietHoaDon.get(i).getPhong().getMaPhong().equals(maPhong)) {
//				index = i;
//			}
//			phut -= chiTietHoaDon.get(i).getThoiLuong();
//		}
//		chiTietHoaDon.get(index).setThoiLuong((int) phut + chiTietHoaDon.get(index).getThoiLuong());
//		return (int) phut2;
//	}
    
    
    public ArrayList<CT_HoaDon> getdsCTHD(String maHoaDon) throws SQLException {
    ArrayList<CT_HoaDon> dsCTHD = new ArrayList<>();

    // Make sure this initializes the connection pool
    ConnectDB.getInstance();

    // Get a connection from the pool
    Connection con = ConnectDB.getInstance().getConnection();

    // Check if the connection is closed before preparing the statement
    if (con.isClosed()) {
        System.out.println("KetNoi dong");
        // Reopen the connection manually
        con = ConnectDB.getInstance().getConnection();
    }

    PreparedStatement stmt = null;
    ResultSet rs = null;

    try {
        String sql = "SELECT ct.MaHD, dv.MaDichVu, dv.TenDichVu, ct.SoLuong FROM CT_HoaDon ct " +
                     "JOIN DichVu dv ON ct.MaDV = dv.MaDichVu " +
                     "WHERE MaHD = ?";
        stmt = con.prepareStatement(sql);
        stmt.setString(1, maHoaDon);
        rs = stmt.executeQuery();

        while (rs.next()) {
            String maDichVu = rs.getString("MaDichVu");
            String tenDichVu = rs.getString("TenDichVu");
            int soLuong = rs.getInt("SoLuong");

            DichVu dichVu = new DichVu(maDichVu, tenDichVu);
            CT_HoaDon cthd = new CT_HoaDon(hoaDon, dichVu, soLuong);
            dsCTHD.add(cthd);
        }
    } catch (SQLException e) {
        e.printStackTrace();
        // Log or throw a custom exception with more details if needed
    } finally {
        // Close resources (ResultSet, PreparedStatement) in a finally block
        // Handle closing properly to avoid leaks
        // Consider using try-with-resources for AutoCloseable resources
        // Close in reverse order of acquisition
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    return dsCTHD;
}
}
