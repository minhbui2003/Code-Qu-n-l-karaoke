/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.Date;

/**
 *
 * @author ad
 */
public class PhieuDatPhong {
    private String maPhieuDatPhong;
    private Phong phong;
    private KhachHang khachHang;
    private NhanVien nhanVien;
    private String ngayDatPhong;
    private String gioNhanPhong;
    private String trangThaiDon;
    public PhieuDatPhong() {
    }

    public PhieuDatPhong(String maPhieuDatPhong) {
        this.maPhieuDatPhong = maPhieuDatPhong;
    }

    public PhieuDatPhong(String maPhieuDatPhong, Phong phong, KhachHang khachHang, NhanVien nhanVien, String gioNhanPhong) {
        this.maPhieuDatPhong = maPhieuDatPhong;
        this.phong = phong;
        this.khachHang = khachHang;
        this.nhanVien = nhanVien;
        this.gioNhanPhong = gioNhanPhong;
    }

    public PhieuDatPhong(String maPhieuDatPhong, Phong phong, KhachHang khachHang, NhanVien nhanVien, String ngayDatPhong, String gioNhanPhong) {
        this.maPhieuDatPhong = maPhieuDatPhong;
        this.phong = phong;
        this.khachHang = khachHang;
        this.nhanVien = nhanVien;
        this.ngayDatPhong = ngayDatPhong;
        this.gioNhanPhong = gioNhanPhong;
    }

    public String getGioNhanPhong() {
        return gioNhanPhong;
    }

    public void setGioNhanPhong(String gioNhanPhong) {
        this.gioNhanPhong = gioNhanPhong;
    }
  
    public String getNgayDatPhong() {
        return ngayDatPhong;
    }

    public void setNgayDatPhong(String ngayDatPhong) {
        this.ngayDatPhong = ngayDatPhong;
    }

    

    public String getMaPhieuDatPhong() {
        return maPhieuDatPhong;
    }

    public void setMaPhieuDatPhong(String maPhieuDatPhong) {
        this.maPhieuDatPhong = maPhieuDatPhong;
    }

    public Phong getPhong() {
        return phong;
    }

    public void setTrangThaiDon(String trangThaiDon) {
        this.trangThaiDon = trangThaiDon;
    }

    public String getTrangThaiDon() {
        return trangThaiDon;
    }

    public void setPhong(Phong phong) {
        this.phong = phong;
    }

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public PhieuDatPhong(String maPhieuDatPhong, Phong phong, KhachHang khachHang, String gioNhanPhong) {
        this.maPhieuDatPhong = maPhieuDatPhong;
        this.phong = phong;
        this.khachHang = khachHang;
        this.gioNhanPhong = gioNhanPhong;
    }

    
    
    
    
}
