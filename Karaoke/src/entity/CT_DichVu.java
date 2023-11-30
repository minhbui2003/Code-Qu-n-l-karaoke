/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;


public class CT_DichVu {
    private String MaCTDV;
    private DichVu dichVu;
    private int soLuong;

    public String getMaCTDV() {
        return MaCTDV;
    }

    public DichVu getDichVu() {
        return dichVu;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setMaCTDV(String MaCTDV) {
        this.MaCTDV = MaCTDV;
    }

    public void setDichVu(DichVu dichVu) {
        this.dichVu = dichVu;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public CT_DichVu(String MaCTDV, DichVu dichVu, int soLuong) {
        this.MaCTDV = MaCTDV;
        this.dichVu = dichVu;
        this.soLuong = soLuong;
    }
    
    
}
