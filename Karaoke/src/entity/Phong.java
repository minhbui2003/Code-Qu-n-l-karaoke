/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.Objects;

/**
 *
 * @author Duong Ngo Manh
 */
public class Phong {
    private String maPhong;
    private LoaiPhong loaiPhong;
    private String tenPhong;
    private float giaPhong;
    private int soNguoiToiDa;
    private String trangThaiPhong;

    public Phong() {
    }

    public Phong(String maPhong) {
        this.maPhong = maPhong;
    }

    public Phong(String maPhong, LoaiPhong loaiPhong, String tenPhong, float giaPhong, int soNguoiToiDa, String trangThaiPhong) {
        this.maPhong = maPhong;
        this.loaiPhong = loaiPhong;
        this.tenPhong = tenPhong;
        this.giaPhong = giaPhong;
        this.soNguoiToiDa = soNguoiToiDa;
        this.trangThaiPhong = trangThaiPhong;
    }

    public String getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
    }

    public LoaiPhong getLoaiPhong() {
        return loaiPhong;
    }

    public void setLoaiPhong(LoaiPhong loaiPhong) {
        this.loaiPhong = loaiPhong;
    }

    public String getTenPhong() {
        return tenPhong;
    }

    public void setTenPhong(String tenPhong) {
        this.tenPhong = tenPhong;
    }

    public float getGiaPhong() {
        return giaPhong;
    }

    public void setGiaPhong(float giaPhong) {
        this.giaPhong = giaPhong;
    }

    public int getSoNguoiToiDa() {
        return soNguoiToiDa;
    }

    public void setSoNguoiToiDa(int soNguoiToiDa) {
        this.soNguoiToiDa = soNguoiToiDa;
    }

    public String getTrangThaiPhong() {
        return trangThaiPhong;
    }

    public void setTrangThaiPhong(String trangThaiPhong) {
        this.trangThaiPhong = trangThaiPhong;
    }

    public Phong(String maPhong, String tenPhong, float giaPhong) {
        this.maPhong = maPhong;
        this.tenPhong = tenPhong;
        this.giaPhong = giaPhong;
    }

    

    
    

    

    
}   
