/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;


public class DichVu {
    private String maDV;
    private String tenDV;
    private int soLuong;
    private double giaBan;
    private String donViTinh;
    private boolean trangThaiDV;
    public String getMaDV() {
        return maDV;
    }

    public void setMaDV(String maDV) {
        this.maDV = maDV;
    }

    public String getTenDV() {
        return tenDV;
    }

    public void setTenDV(String tenDV) {
        this.tenDV = tenDV;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(double giaBan) {
        this.giaBan = giaBan;
    }

    public String getDonViTinh() {
        return donViTinh;
    }

    public void setDonViTinh(String donViTinh) {
        this.donViTinh = donViTinh;
    }

    public boolean isTrangThaiDV() {
        return trangThaiDV;
    }

    public void setTrangThaiDV(boolean trangThaiDV) {
        this.trangThaiDV = trangThaiDV;
    }

    public DichVu(String maDV, String tenDV, int soLuong, double giaBan, String donViTinh, boolean trangThaiDV) {
        this.maDV = maDV;
        this.tenDV = tenDV;
        this.soLuong = soLuong;
        this.giaBan = giaBan;
        this.donViTinh = donViTinh;
        this.trangThaiDV = trangThaiDV;
    }

    public DichVu(String maDV, String tenDV) {
        this.maDV = maDV;
        this.tenDV = tenDV;
    }
    
    public DichVu() {
    }

    public DichVu(String maDV) {
        this.maDV = maDV;
    }
    
   
}
