/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author ad
 */
public class HoaDon {
    private String maHD;
    private Date ngayLapHD;
    private NhanVien nhanVien;
    private String tenKhachHang;
    private Phong phong;
    private Date gioNhanPhong;
    private Date gioTraPhong;
    private double tongTien;  
    private KhuyenMai khuyenMai;

    public String getMaHD() {
        return maHD;
    }

    public Date getNgayLapHD() {
        return ngayLapHD;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public Phong getPhong() {
        return phong;
    }

    public Date getGioNhanPhong() {
        return gioNhanPhong;
    }

    public Date getGioTraPhong() {
        return gioTraPhong;
    }

    public double getTongTien() {
        return tongTien;
    }

    public KhuyenMai getKhuyenMai() {
        return khuyenMai;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public void setNgayLapHD(Date ngayLapHD) {
        this.ngayLapHD = ngayLapHD;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public void setPhong(Phong phong) {
        this.phong = phong;
    }

    public void setGioNhanPhong(Date gioNhanPhong) {
        this.gioNhanPhong = gioNhanPhong;
    }

    public void setGioTraPhong(Date gioTraPhong) {
        this.gioTraPhong = gioTraPhong;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public void setKhuyenMai(KhuyenMai khuyenMai) {
        this.khuyenMai = khuyenMai;
    }

    public HoaDon() {
    }

    public HoaDon(String maHD, NhanVien nhanVien, String tenKhachHang, Phong phong, Date gioNhanPhong) {
        this.maHD = maHD;
        this.nhanVien = nhanVien;
        this.tenKhachHang = tenKhachHang;
        this.phong = phong;
        this.gioNhanPhong = gioNhanPhong;
    }
    
    
    
    public HoaDon(String maHD, Date ngayLapHD, NhanVien nhanVien, String tenKhachHang, Phong phong, Date gioNhanPhong) {
        this.maHD = maHD;
        this.ngayLapHD = ngayLapHD;
        this.nhanVien = nhanVien;
        this.tenKhachHang = tenKhachHang;
        this.phong = phong;
        this.gioNhanPhong = gioNhanPhong;
    }

    public HoaDon(String maHD, Date ngayLapHD, NhanVien nhanVien, String tenKhachHang, Phong phong, Date gioNhanPhong, Date gioTraPhong, double tongTien, KhuyenMai khuyenMai) {
        this.maHD = maHD;
        this.ngayLapHD = ngayLapHD;
        this.nhanVien = nhanVien;
        this.tenKhachHang = tenKhachHang;
        this.phong = phong;
        this.gioNhanPhong = gioNhanPhong;
        this.gioTraPhong = gioTraPhong;
        this.tongTien = tongTien;
        this.khuyenMai = khuyenMai;
    }

    public HoaDon(NhanVien nhanVien, String tenKhachHang, Phong phong) {
        this.nhanVien = nhanVien;
        this.tenKhachHang = tenKhachHang;
        this.phong = phong;
    }

    @Override
    public String toString() {
        return "HoaDon{" + "maHD=" + maHD + ", ngayLapHD=" + ngayLapHD + ", nhanVien=" + nhanVien + ", tenKhachHang=" + tenKhachHang + ", phong=" + phong + ", gioNhanPhong=" + gioNhanPhong + ", gioTraPhong=" + gioTraPhong + ", tongTien=" + tongTien + ", khuyenMai=" + khuyenMai + '}';
    }
    
}
