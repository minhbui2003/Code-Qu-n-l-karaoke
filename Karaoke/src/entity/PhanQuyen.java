/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Duong Ngo Manh
 */
public class PhanQuyen {

    private static String vaiTro;

    public static void setVaiTro(String role) {
        vaiTro = role;
    }

    public static String getVaiTro() {
        return vaiTro;
    }

    public static boolean coQuyenCapNhatNhanVien() {
        return vaiTro.equals("Quản lý Karaoke");
    }
}
