
package entity;


public class CT_HoaDon {
    private HoaDon hoaDon;
    private DichVu dichVu;
    private int soLuong;
    private Phong phong;

    public HoaDon getHoaDon() {
        return hoaDon;
    }

    public Phong getPhong() {
        return phong;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public void setHoaDon(HoaDon hoaDon) {
        this.hoaDon = hoaDon;
    }

    public void setPhong(Phong phong) {
        this.phong = phong;
    }

   
    public double getThanhTien() {
	return soLuong * dichVu.getGiaBan();
    }

    public DichVu getDichVu() {
        return dichVu;
    }

    public void setDichVu(DichVu dichVu) {
        this.dichVu = dichVu;
    }

    public CT_HoaDon(HoaDon hoaDon, DichVu dichVu, int soLuong, Phong phong) {
        this.hoaDon = hoaDon;
        this.dichVu = dichVu;
        this.soLuong = soLuong;
        this.phong = phong;
    }

    public CT_HoaDon(HoaDon hoaDon, DichVu dichVu, int soLuong) {
        this.hoaDon = hoaDon;
        this.dichVu = dichVu;
        this.soLuong = soLuong;
    }
    
    

    @Override
    public String toString() {
        return "CT_HoaDon{" + "hoaDon=" + hoaDon + ", dichVu=" + dichVu + ", soLuong=" + soLuong + ", phong=" + phong + '}';
    }

    

}
