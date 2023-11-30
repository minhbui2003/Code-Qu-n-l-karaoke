
package UI;

import connectdb.ConnectDB;
import dao.CTHoaDon_DAO;
import dao.DichVu_DAO;
import dao.HoaDon_DAO;
import dao.KhachHang_DAO;
import dao.PhieuDatPhong_DAO;
import dao.Phong_DAO;
import entity.CT_HoaDon;
import entity.DichVu;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;
import entity.PhieuDatPhong;
import entity.Phong;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

public class GUILapHoaDon extends javax.swing.JFrame {
    Phong_DAO phong_dao = new Phong_DAO();
    KhachHang_DAO khachHang_dao = new KhachHang_DAO();
    DichVu_DAO dichVu_dao = new DichVu_DAO();
    HoaDon_DAO hoaDon_dao = new HoaDon_DAO();
    PhieuDatPhong_DAO phieuDatPhong_dao = new PhieuDatPhong_DAO();
    CTHoaDon_DAO ctHoaDon_dao = new CTHoaDon_DAO();
    DefaultTableModel dftbl, dftblHoaDon;
    
    public GUILapHoaDon() {
        try {
            ConnectDB.getInstance().connect();
	} catch (SQLException e) {
            e.printStackTrace();
	}
        initComponents();
        setSize(1520, 780);
        setLocationRelativeTo(null);
        Time();
        Date();
    }

    private void Time() {
        new Thread(){
            public void run(){
                while (true) {                    
                    Calendar now = Calendar.getInstance();
                    int h = now.get(Calendar.HOUR_OF_DAY);
                    int m = now.get(Calendar.MINUTE);
                    int s = now.get(Calendar.SECOND);
                    lblTime.setText(h + ":" + m + ":" + s);
                }
            }   
        }.start();
    }
    
    private void Date() {
        new Thread(){
            public void run(){
                while (true) {                    
                    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                    Date currentDate = new Date();
                    lblDate.setText(df.format(currentDate));
                }
            }   
        }.start();
    }
        
//
        public void docDuLieuTuDataVaoBangHoaDon() throws SQLException {  
        DefaultTableModel modelDSHD = (DefaultTableModel) tblDanhSachPhieuDat.getModel();
        ArrayList<HoaDon> listHD = hoaDon_dao.layDanhSachHoaDon();
        modelDSHD.setRowCount(0);
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
        SimpleDateFormat outputFormat = new SimpleDateFormat("HH:mm:ss.S");

        for (HoaDon x : listHD) {
            modelDSHD.addRow(new Object[] {
                x.getMaHD(),
                x.getPhong().getMaPhong(),
                x.getPhong().getTenPhong(),
                x.getPhong().getGiaPhong(),
                x.getGioNhanPhong(),
                x.getTenKhachHang(),
                x.getNhanVien().getTenNhanVien()
            });
        }
    }
private void layDuLieuLenCbTenSanPham() throws SQLException {
		List<DichVu> dv = dichVu_dao.getAllDichVu();
		for (DichVu x : dv) {
			cbTenDichVu.addItem(x.getTenDV());
		}
	}
    
//  private double tinhTongTien(int row) {
//		// lay du lieu de tinh
////		Date thoiGianVao = (Date) tblPhongDangSuDung.getValueAt(row, 5);
////		int gioVao = thoiGianVao.getHours();
////		int phutVao = thoiGianVao.getMinutes();
////		int gioRa = java.time.LocalTime.now().getHour();
////		int phutRa = java.time.LocalTime.now().getMinute();
////		double donGia = (double) tblPhongDangSuDung.getValueAt(row, 4);
//		double tienDichVu = 0;
//		if (lblTongTien.getText().equals("")) {
//			tienDichVu = 0;
//		} else {
//			tienDichVu = Double.parseDouble(lblTongTien.getText().replaceAll(" VND", ""));
//		}
//
//		// tinh theo cong thuc
////		int tongGio = gioRa - gioVao;
////		int tongPhut = phutRa - phutVao;
////		int thoiGianSuDung = tongGio * 60 + tongPhut;
////		double tienSuDungPhong = thoiGianSuDung * (donGia / 60);
//		double tongTien =  tienDichVu; //+ tienSuDungPhong; 
//		return tongTien;
//	}
  
//  private void hienThiTongTien(int row) {
//		Locale locale = new Locale("vi", "VN");
//		NumberFormat format = NumberFormat.getCurrencyInstance(locale);
//		double tongTien = tinhTongTien(row);
//		lblTongTien.setText("Tổng tiền: " + String.valueOf(format.format(tongTien)));
//	}

    
//    private String timHoaDon(){
//        String maHoaDon = null;
//        int row = tblDanhSachPhieuDat.getSelectedRow();
//        if(row >=0){
//            String maP = tblDanhSachPhieuDat.getValueAt(row, 1).toString();
//        
//            maHoaDon = hoaDon_dao.timHoaDonTheoMaPhong(maP);
//        }
//        return maHoaDon;
//    }

    private void loadDataCTHD() throws SQLException {    
        ArrayList<CT_HoaDon> DSCT = new ArrayList<>();
        int row = tblDanhSachPhieuDat.getSelectedRow();  
        if (row >= 0) {
            double tongTien = 0;
            int stt=1;
            dftblHoaDon = (DefaultTableModel) tblDanhSachChiTietHD.getModel();
            dftblHoaDon.setRowCount(0);
            String maHoaDon = lblMaHoaDon.getText();
            
            DSCT = ctHoaDon_dao.getdsCTHD(maHoaDon);
            
            for(CT_HoaDon cthd : DSCT) {
		dftblHoaDon.addRow(new Object[] {cthd.getDichVu().getTenDV(), cthd.getDichVu().getGiaBan(),
		cthd.getSoLuong(), cthd.getThanhTien()});
		tongTien += cthd.getThanhTien();
		lblTongTien.setText(tongTien + " VND");
            }
        }	
        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlThongTinHoaDon = new javax.swing.JPanel();
        lblTenNhanVien = new javax.swing.JLabel();
        lblTenKhachHang = new javax.swing.JLabel();
        scrDanhSachDichVuHD1 = new javax.swing.JScrollPane();
        tblDanhSachChiTietHD = new javax.swing.JTable();
        lblPhong = new javax.swing.JLabel();
        lblKhachHang = new javax.swing.JLabel();
        lblTenKhachHang1 = new javax.swing.JLabel();
        lblHoaDon = new javax.swing.JLabel();
        lblNhanVien = new javax.swing.JLabel();
        lblGiaPhong = new javax.swing.JLabel();
        lblTenPhong = new javax.swing.JLabel();
        lblMaHoaDon = new javax.swing.JLabel();
        lblNgayLapHD = new javax.swing.JLabel();
        lblNgayLap = new javax.swing.JLabel();
        pnlDanhSachPhong = new javax.swing.JPanel();
        scrDanhSachPhong2 = new javax.swing.JScrollPane();
        tblDanhSachPhieuDat = new javax.swing.JTable();
        pnTime = new javax.swing.JPanel();
        lblTime = new javax.swing.JLabel();
        lblDate = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtMaHoaDon = new javax.swing.JTextField();
        btnTimHoaDon = new javax.swing.JButton();
        pnTieuDe = new javax.swing.JPanel();
        lblTieuDe = new javax.swing.JLabel();
        btnChuyenPhong = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        cbTenDichVu = new javax.swing.JComboBox<>();
        txtSoLuong = new javax.swing.JTextField();
        btnThemDichVu = new javax.swing.JButton();
        btnCapNhatDV1 = new javax.swing.JButton();
        btnCapNhatDV = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        lblTongTien = new javax.swing.JLabel();
        btnThanhToan2 = new javax.swing.JButton();
        mnuTong = new javax.swing.JMenuBar();
        mnHeThong = new javax.swing.JMenu();
        mniTrangChu = new javax.swing.JMenuItem();
        mniTroGiup = new javax.swing.JMenuItem();
        mniDangXuat = new javax.swing.JMenuItem();
        mnuPhong = new javax.swing.JMenu();
        mniCapNhatPhong = new javax.swing.JMenuItem();
        mniCapNhatLoaiPhong = new javax.swing.JMenuItem();
        mniTimKiemPhong = new javax.swing.JMenuItem();
        mniDatPhong = new javax.swing.JMenuItem();
        mniDatPhongCho = new javax.swing.JMenuItem();
        mnuNhanVien = new javax.swing.JMenu();
        mniCapNhatNhanVien = new javax.swing.JMenuItem();
        mniTimKiemNhanVien = new javax.swing.JMenuItem();
        mnuKhachHang = new javax.swing.JMenu();
        mniCapNhatKhachHang = new javax.swing.JMenuItem();
        mniTimKiemKhachHang = new javax.swing.JMenuItem();
        mnuThongKe = new javax.swing.JMenu();
        mniThongKeDoanhThu = new javax.swing.JMenuItem();
        mniThongKeDoanhThuTheoKhachHang = new javax.swing.JMenuItem();
        mnuHoaDon = new javax.swing.JMenu();
        mniLapHoaDon = new javax.swing.JMenuItem();
        mniThanhToan = new javax.swing.JMenuItem();
        mnuDichVu = new javax.swing.JMenu();
        mniCapNhatDichVu = new javax.swing.JMenuItem();
        mniTimKiemDichVu = new javax.swing.JMenuItem();
        mnuKhuyenMai = new javax.swing.JMenu();
        mniCapNhatKhuyenMai = new javax.swing.JMenuItem();
        mniTimKiemKhuyenMai = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Design by MMM");
        setResizable(false);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlThongTinHoaDon.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "THÔNG TIN CHI TIẾT HÓA ĐƠN\n\n", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18))); // NOI18N
        pnlThongTinHoaDon.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTenNhanVien.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblTenNhanVien.setText("Tên nhân viên:");
        pnlThongTinHoaDon.add(lblTenNhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 110, -1, -1));

        lblTenKhachHang.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblTenKhachHang.setText("Giá phòng:");
        pnlThongTinHoaDon.add(lblTenKhachHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 70, -1, -1));

        tblDanhSachChiTietHD.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tblDanhSachChiTietHD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên dịch vụ", "Đơn giá", "Số lượng", "Thành tiền"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Double.class, java.lang.Integer.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblDanhSachChiTietHD.setRowHeight(25);
        tblDanhSachChiTietHD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDanhSachChiTietHDMouseClicked(evt);
            }
        });
        scrDanhSachDichVuHD1.setViewportView(tblDanhSachChiTietHD);
        if (tblDanhSachChiTietHD.getColumnModel().getColumnCount() > 0) {
            tblDanhSachChiTietHD.getColumnModel().getColumn(0).setResizable(false);
            tblDanhSachChiTietHD.getColumnModel().getColumn(2).setResizable(false);
        }

        pnlThongTinHoaDon.add(scrDanhSachDichVuHD1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 660, 410));

        lblPhong.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblPhong.setText("Phong");
        pnlThongTinHoaDon.add(lblPhong, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 60, -1, 40));

        lblKhachHang.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblKhachHang.setText("KH");
        pnlThongTinHoaDon.add(lblKhachHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 110, -1, -1));

        lblTenKhachHang1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblTenKhachHang1.setText("Tên Khách Hàng:");
        pnlThongTinHoaDon.add(lblTenKhachHang1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, -1, -1));

        lblHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblHoaDon.setText("Mã hóa đơn:");
        pnlThongTinHoaDon.add(lblHoaDon, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, 30));

        lblNhanVien.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblNhanVien.setText("NV");
        pnlThongTinHoaDon.add(lblNhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 110, -1, -1));

        lblGiaPhong.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblGiaPhong.setText("Gia");
        pnlThongTinHoaDon.add(lblGiaPhong, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 70, -1, -1));

        lblTenPhong.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblTenPhong.setText("Phòng:");
        pnlThongTinHoaDon.add(lblTenPhong, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, -1, 40));

        lblMaHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblMaHoaDon.setText("HD");
        pnlThongTinHoaDon.add(lblMaHoaDon, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 30, -1, 30));

        lblNgayLapHD.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblNgayLapHD.setText("Ngày lập:");
        pnlThongTinHoaDon.add(lblNgayLapHD, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 30, -1, 30));

        lblNgayLap.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblNgayLap.setText("HD");
        pnlThongTinHoaDon.add(lblNgayLap, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 30, -1, 30));

        getContentPane().add(pnlThongTinHoaDon, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 50, 680, 570));
        pnlThongTinHoaDon.getAccessibleContext().setAccessibleName("THÔNG TIN KHÁCH HÀNG\n\n");

        pnlDanhSachPhong.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "DANH SÁCH HÓA ĐƠN", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18))); // NOI18N
        pnlDanhSachPhong.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblDanhSachPhieuDat.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tblDanhSachPhieuDat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã phiếu đặt", "Mã Phòng", "Tên Phòng", "Giá phòng", "Giờ nhận phòng", "Tên khách hàng", "Tên nhân viên"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblDanhSachPhieuDat.setRowHeight(25);
        tblDanhSachPhieuDat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDanhSachPhieuDatMouseClicked(evt);
            }
        });
        scrDanhSachPhong2.setViewportView(tblDanhSachPhieuDat);
        if (tblDanhSachPhieuDat.getColumnModel().getColumnCount() > 0) {
            tblDanhSachPhieuDat.getColumnModel().getColumn(0).setResizable(false);
            tblDanhSachPhieuDat.getColumnModel().getColumn(1).setMinWidth(40);
            tblDanhSachPhieuDat.getColumnModel().getColumn(1).setMaxWidth(1000);
            tblDanhSachPhieuDat.getColumnModel().getColumn(2).setMinWidth(40);
            tblDanhSachPhieuDat.getColumnModel().getColumn(2).setMaxWidth(1000);
            tblDanhSachPhieuDat.getColumnModel().getColumn(3).setMinWidth(70);
            tblDanhSachPhieuDat.getColumnModel().getColumn(3).setMaxWidth(1000);
            tblDanhSachPhieuDat.getColumnModel().getColumn(4).setMinWidth(100);
            tblDanhSachPhieuDat.getColumnModel().getColumn(4).setMaxWidth(1000);
            tblDanhSachPhieuDat.getColumnModel().getColumn(5).setResizable(false);
        }

        pnlDanhSachPhong.add(scrDanhSachPhong2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 790, 390));

        pnTime.setBackground(new java.awt.Color(255, 255, 255));
        pnTime.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTime.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        lblTime.setText("18 : 28 :45");
        pnTime.add(lblTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 140, 30));

        lblDate.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblDate.setText("11/04/2023");
        pnTime.add(lblDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, 20));

        pnlDanhSachPhong.add(pnTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 130, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel2.setText("Tìm mã phiếu đặt:");
        pnlDanhSachPhong.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 40, -1, -1));

        txtMaHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        pnlDanhSachPhong.add(txtMaHoaDon, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 40, 220, 30));

        btnTimHoaDon.setBackground(new java.awt.Color(204, 204, 204));
        btnTimHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnTimHoaDon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-search-24.png"))); // NOI18N
        btnTimHoaDon.setText("TÌM");
        btnTimHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimHoaDonActionPerformed(evt);
            }
        });
        pnlDanhSachPhong.add(btnTimHoaDon, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 30, 100, 40));

        getContentPane().add(pnlDanhSachPhong, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 810, 490));
        pnlDanhSachPhong.getAccessibleContext().setAccessibleDescription("");

        pnTieuDe.setBackground(new java.awt.Color(102, 0, 0));

        lblTieuDe.setBackground(new java.awt.Color(102, 0, 0));
        lblTieuDe.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        lblTieuDe.setForeground(new java.awt.Color(242, 242, 242));
        lblTieuDe.setText("LẬP HOÁ ĐƠN");

        javax.swing.GroupLayout pnTieuDeLayout = new javax.swing.GroupLayout(pnTieuDe);
        pnTieuDe.setLayout(pnTieuDeLayout);
        pnTieuDeLayout.setHorizontalGroup(
            pnTieuDeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnTieuDeLayout.createSequentialGroup()
                .addGap(661, 661, 661)
                .addComponent(lblTieuDe)
                .addContainerGap(701, Short.MAX_VALUE))
        );
        pnTieuDeLayout.setVerticalGroup(
            pnTieuDeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnTieuDeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTieuDe)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        getContentPane().add(pnTieuDe, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1517, 50));

        btnChuyenPhong.setBackground(new java.awt.Color(0, 153, 153));
        btnChuyenPhong.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnChuyenPhong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/forward-button.png"))); // NOI18N
        btnChuyenPhong.setText("Chuyển phòng");
        btnChuyenPhong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChuyenPhongActionPerformed(evt);
            }
        });
        getContentPane().add(btnChuyenPhong, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 650, 180, 40));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chọn Dịch Vụ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 16))); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setText("Tên dịch vụ:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, -1, 30));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setText("Số lượng:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 40, -1, 30));

        cbTenDichVu.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jPanel1.add(cbTenDichVu, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 40, 270, -1));

        txtSoLuong.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtSoLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSoLuongActionPerformed(evt);
            }
        });
        jPanel1.add(txtSoLuong, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 40, 160, -1));

        btnThemDichVu.setBackground(new java.awt.Color(153, 255, 255));
        btnThemDichVu.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnThemDichVu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/dichvu.png"))); // NOI18N
        btnThemDichVu.setText("Thêm dịch vụ");
        btnThemDichVu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemDichVuActionPerformed(evt);
            }
        });
        jPanel1.add(btnThemDichVu, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 110, 180, 40));

        btnCapNhatDV1.setBackground(new java.awt.Color(255, 255, 0));
        btnCapNhatDV1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCapNhatDV1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/negative.png"))); // NOI18N
        btnCapNhatDV1.setText("BỚT DỊCH VỤ");
        jPanel1.add(btnCapNhatDV1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 110, 180, 40));

        btnCapNhatDV.setBackground(new java.awt.Color(255, 51, 51));
        btnCapNhatDV.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCapNhatDV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/thoat.png"))); // NOI18N
        btnCapNhatDV.setText("XÓA DỊCH VỤ");
        jPanel1.add(btnCapNhatDV, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 110, 180, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 540, 810, 160));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel1.setText("Tổng tiền :");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1260, 620, -1, -1));

        lblTongTien.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        getContentPane().add(lblTongTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(1390, 500, -1, -1));

        btnThanhToan2.setBackground(new java.awt.Color(153, 255, 153));
        btnThanhToan2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnThanhToan2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/hoadon.png"))); // NOI18N
        btnThanhToan2.setText("Thanh toán");
        btnThanhToan2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToan2ActionPerformed(evt);
            }
        });
        getContentPane().add(btnThanhToan2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1230, 650, 180, 40));

        mnuTong.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        mnHeThong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/system.png"))); // NOI18N
        mnHeThong.setText("Hệ Thống");
        mnHeThong.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        mniTrangChu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mniTrangChu.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        mniTrangChu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/24-hours.png"))); // NOI18N
        mniTrangChu.setText("Trang chủ");
        mniTrangChu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniTrangChuActionPerformed(evt);
            }
        });
        mnHeThong.add(mniTrangChu);

        mniTroGiup.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        mniTroGiup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/detail.png"))); // NOI18N
        mniTroGiup.setText("Trợ giúp");
        mniTroGiup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniTroGiupActionPerformed(evt);
            }
        });
        mnHeThong.add(mniTroGiup);

        mniDangXuat.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        mniDangXuat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/DangXuat.png"))); // NOI18N
        mniDangXuat.setText("Đăng xuất");
        mnHeThong.add(mniDangXuat);

        mnuTong.add(mnHeThong);

        mnuPhong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/home.png"))); // NOI18N
        mnuPhong.setText("Phòng");
        mnuPhong.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        mniCapNhatPhong.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        mniCapNhatPhong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/menu.png"))); // NOI18N
        mniCapNhatPhong.setText("Cập nhật phòng");
        mniCapNhatPhong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniCapNhatPhongActionPerformed(evt);
            }
        });
        mnuPhong.add(mniCapNhatPhong);

        mniCapNhatLoaiPhong.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        mniCapNhatLoaiPhong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/description.png"))); // NOI18N
        mniCapNhatLoaiPhong.setText("Cập nhật loại phòng");
        mniCapNhatLoaiPhong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniCapNhatLoaiPhongActionPerformed(evt);
            }
        });
        mnuPhong.add(mniCapNhatLoaiPhong);

        mniTimKiemPhong.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        mniTimKiemPhong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-search-24.png"))); // NOI18N
        mniTimKiemPhong.setText("Tìm kiếm phòng");
        mniTimKiemPhong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniTimKiemPhongActionPerformed(evt);
            }
        });
        mnuPhong.add(mniTimKiemPhong);

        mniDatPhong.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        mniDatPhong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/televisions.png"))); // NOI18N
        mniDatPhong.setText("Đặt phòng");
        mniDatPhong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniDatPhongActionPerformed(evt);
            }
        });
        mnuPhong.add(mniDatPhong);

        mniDatPhongCho.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        mniDatPhongCho.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/lich_lam.png"))); // NOI18N
        mniDatPhongCho.setText("Đặt phòng chờ");
        mniDatPhongCho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniDatPhongChoActionPerformed(evt);
            }
        });
        mnuPhong.add(mniDatPhongCho);

        mnuTong.add(mnuPhong);

        mnuNhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/nhanvien.png"))); // NOI18N
        mnuNhanVien.setText("Nhân viên");
        mnuNhanVien.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        mniCapNhatNhanVien.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        mniCapNhatNhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/adminLogin.png"))); // NOI18N
        mniCapNhatNhanVien.setText("Cập nhật nhân viên");
        mniCapNhatNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniCapNhatNhanVienActionPerformed(evt);
            }
        });
        mnuNhanVien.add(mniCapNhatNhanVien);

        mniTimKiemNhanVien.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        mniTimKiemNhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/customer-service.png"))); // NOI18N
        mniTimKiemNhanVien.setText("Tìm kiếm nhân viên");
        mniTimKiemNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniTimKiemNhanVienActionPerformed(evt);
            }
        });
        mnuNhanVien.add(mniTimKiemNhanVien);

        mnuTong.add(mnuNhanVien);

        mnuKhachHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/khachHang.png"))); // NOI18N
        mnuKhachHang.setText("Khách hàng");
        mnuKhachHang.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        mniCapNhatKhachHang.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        mniCapNhatKhachHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/customer.png"))); // NOI18N
        mniCapNhatKhachHang.setText("Cập nhật khách hàng");
        mniCapNhatKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniCapNhatKhachHangActionPerformed(evt);
            }
        });
        mnuKhachHang.add(mniCapNhatKhachHang);

        mniTimKiemKhachHang.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        mniTimKiemKhachHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/help (1).png"))); // NOI18N
        mniTimKiemKhachHang.setText("Tìm kiếm khách hàng");
        mniTimKiemKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniTimKiemKhachHangActionPerformed(evt);
            }
        });
        mnuKhachHang.add(mniTimKiemKhachHang);

        mnuTong.add(mnuKhachHang);

        mnuThongKe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/thongke.png"))); // NOI18N
        mnuThongKe.setText("Thống Kê");
        mnuThongKe.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        mniThongKeDoanhThu.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        mniThongKeDoanhThu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/bar-chart.png"))); // NOI18N
        mniThongKeDoanhThu.setText("Thống kê doanh thu");
        mniThongKeDoanhThu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniThongKeDoanhThuActionPerformed(evt);
            }
        });
        mnuThongKe.add(mniThongKeDoanhThu);

        mniThongKeDoanhThuTheoKhachHang.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        mniThongKeDoanhThuTheoKhachHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/budget.png"))); // NOI18N
        mniThongKeDoanhThuTheoKhachHang.setText("Thống kê doanh thu theo khách hàng");
        mniThongKeDoanhThuTheoKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniThongKeDoanhThuTheoKhachHangActionPerformed(evt);
            }
        });
        mnuThongKe.add(mniThongKeDoanhThuTheoKhachHang);

        mnuTong.add(mnuThongKe);

        mnuHoaDon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/hoadon.png"))); // NOI18N
        mnuHoaDon.setText("Hoá đơn");
        mnuHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        mniLapHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        mniLapHoaDon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/bill (1).png"))); // NOI18N
        mniLapHoaDon.setText("Lập hoá đơn");
        mniLapHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniLapHoaDonActionPerformed(evt);
            }
        });
        mnuHoaDon.add(mniLapHoaDon);

        mniThanhToan.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        mniThanhToan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/money-bag.png"))); // NOI18N
        mniThanhToan.setText("Thanh toán");
        mniThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniThanhToanActionPerformed(evt);
            }
        });
        mnuHoaDon.add(mniThanhToan);

        mnuTong.add(mnuHoaDon);

        mnuDichVu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/dichvu.png"))); // NOI18N
        mnuDichVu.setText("Dịch vụ");
        mnuDichVu.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        mniCapNhatDichVu.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        mniCapNhatDichVu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/storeNav.png"))); // NOI18N
        mniCapNhatDichVu.setText("Cập nhật dịch vụ");
        mniCapNhatDichVu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniCapNhatDichVuActionPerformed(evt);
            }
        });
        mnuDichVu.add(mniCapNhatDichVu);

        mniTimKiemDichVu.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        mniTimKiemDichVu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/invoices.png"))); // NOI18N
        mniTimKiemDichVu.setText("Tìm kiếm dịch vụ");
        mniTimKiemDichVu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniTimKiemDichVuActionPerformed(evt);
            }
        });
        mnuDichVu.add(mniTimKiemDichVu);

        mnuTong.add(mnuDichVu);

        mnuKhuyenMai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/khuyenmai.png"))); // NOI18N
        mnuKhuyenMai.setText("Khuyến mãi");
        mnuKhuyenMai.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        mniCapNhatKhuyenMai.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        mniCapNhatKhuyenMai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/promotion.png"))); // NOI18N
        mniCapNhatKhuyenMai.setText("Cập nhật khuyến mãi");
        mniCapNhatKhuyenMai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniCapNhatKhuyenMaiActionPerformed(evt);
            }
        });
        mnuKhuyenMai.add(mniCapNhatKhuyenMai);

        mniTimKiemKhuyenMai.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        mniTimKiemKhuyenMai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-search-24.png"))); // NOI18N
        mniTimKiemKhuyenMai.setText("Tìm kiếm khuyến mãi");
        mniTimKiemKhuyenMai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniTimKiemKhuyenMaiActionPerformed(evt);
            }
        });
        mnuKhuyenMai.add(mniTimKiemKhuyenMai);

        mnuTong.add(mnuKhuyenMai);

        setJMenuBar(mnuTong);

        pack();
    }// </editor-fold>//GEN-END:initComponents
                                       
    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown

    try {
        layDuLieuLenCbTenSanPham();
//        docDuLieuTuDataVaoDanhPhieuDat();
        docDuLieuTuDataVaoBangHoaDon();
        System.out.println("Doc Thanh Cong");
    } catch (SQLException ex) {
        ex.printStackTrace();
        System.out.println("Loi: " + ex.getMessage());
    }
    }//GEN-LAST:event_formComponentShown

    private void mniTrangChuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniTrangChuActionPerformed
        GUITrangChu tc = new GUITrangChu();
        tc.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_mniTrangChuActionPerformed

    private void mniTroGiupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniTroGiupActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mniTroGiupActionPerformed

    private void mniCapNhatPhongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniCapNhatPhongActionPerformed
        GUICapNhatPhong cnp = new GUICapNhatPhong();
        cnp.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_mniCapNhatPhongActionPerformed

    private void mniCapNhatLoaiPhongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniCapNhatLoaiPhongActionPerformed
        GUICapNhatLoaiPhong cnlp = new GUICapNhatLoaiPhong();
        cnlp.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_mniCapNhatLoaiPhongActionPerformed

    private void mniTimKiemPhongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniTimKiemPhongActionPerformed
        GUITimKiemPhong tkp = new GUITimKiemPhong();
        tkp.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_mniTimKiemPhongActionPerformed

    private void mniDatPhongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniDatPhongActionPerformed
        // TODO add your handling code here:
        GUIDatPhong ttdp = new GUIDatPhong();
        ttdp.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_mniDatPhongActionPerformed

    private void mniDatPhongChoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniDatPhongChoActionPerformed
        GUIDatPhongCho dpc = new GUIDatPhongCho();
        dpc.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_mniDatPhongChoActionPerformed

    private void mniCapNhatNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniCapNhatNhanVienActionPerformed
        GUICapNhatNhanVien cnnv = new GUICapNhatNhanVien();
        cnnv.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_mniCapNhatNhanVienActionPerformed

    private void mniTimKiemNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniTimKiemNhanVienActionPerformed
        GUITimKiemNhanVien tknv = new GUITimKiemNhanVien();
        tknv.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_mniTimKiemNhanVienActionPerformed

    private void mniCapNhatKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniCapNhatKhachHangActionPerformed
        GUICapNhatKhachHang cnkh = new GUICapNhatKhachHang();
        cnkh.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_mniCapNhatKhachHangActionPerformed

    private void mniTimKiemKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniTimKiemKhachHangActionPerformed
        GUITimKiemKhachHang tkkh = new GUITimKiemKhachHang();
        tkkh.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_mniTimKiemKhachHangActionPerformed

    private void mniThongKeDoanhThuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniThongKeDoanhThuActionPerformed
        GUIThongKeDoanhThu tkdt = new GUIThongKeDoanhThu();
        tkdt.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_mniThongKeDoanhThuActionPerformed

    private void mniThongKeDoanhThuTheoKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniThongKeDoanhThuTheoKhachHangActionPerformed
        GUIThongKeKhachHang tkdtkh = new GUIThongKeKhachHang();
        tkdtkh.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_mniThongKeDoanhThuTheoKhachHangActionPerformed

    private void mniLapHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniLapHoaDonActionPerformed
        GUILapHoaDon lhd = new GUILapHoaDon();
        lhd.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_mniLapHoaDonActionPerformed

    private void mniThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniThanhToanActionPerformed
        GUIThanhToan tt = new GUIThanhToan();
        tt.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_mniThanhToanActionPerformed

    private void mniCapNhatDichVuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniCapNhatDichVuActionPerformed
        GUICapNhatDichVu cndv = new GUICapNhatDichVu();
        cndv.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_mniCapNhatDichVuActionPerformed

    private void mniTimKiemDichVuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniTimKiemDichVuActionPerformed
        GUITimKiemDichVu tkdv = new GUITimKiemDichVu();
        tkdv.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_mniTimKiemDichVuActionPerformed

    private void mniCapNhatKhuyenMaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniCapNhatKhuyenMaiActionPerformed
        GUICapNhatKhuyenMai cnkm = new GUICapNhatKhuyenMai();
        cnkm.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_mniCapNhatKhuyenMaiActionPerformed

    private void mniTimKiemKhuyenMaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniTimKiemKhuyenMaiActionPerformed
        GUITimKiemKhuyenMai tkkm = new GUITimKiemKhuyenMai();
        tkkm.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_mniTimKiemKhuyenMaiActionPerformed

    private void btnThemDichVuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemDichVuActionPerformed
        dftblHoaDon = (DefaultTableModel) tblDanhSachChiTietHD.getModel();
        int row = tblDanhSachPhieuDat.getSelectedRow();
        if(row >= 0){
             Connection con = ConnectDB.getInstance().getConnection();
            try {
                System.out.println("Connection status before query: " + (con.isClosed() ? "Closed" : "Open"));
            } catch (SQLException ex) {
                Logger.getLogger(GUILapHoaDon.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                DichVu dichVu = new DichVu();;
                HoaDon hoaDon= new HoaDon();
                Phong phong = new Phong();
                String text = tblDanhSachPhieuDat.getValueAt(row, 5).toString();
                System.out.println("KH"+text);
                String maHoaDon = lblMaHoaDon.getText();
                dichVu = dichVu_dao.timDichVuTheoTen(cbTenDichVu.getSelectedItem().toString());
                hoaDon = hoaDon_dao.timHoaDonTheoMa(maHoaDon);
                System.out.println("hoaDon: "+maHoaDon);
                System.out.println("hoaDon: "+hoaDon);
                int soLuong = Integer.parseInt(txtSoLuong.getText());
                String maPhong = lblPhong.getText();
                phong = phong_dao.timPhongTheoMa(maPhong);
                CT_HoaDon cthd = new CT_HoaDon(hoaDon, dichVu, soLuong, phong);
                if(ctHoaDon_dao.themChiTietHoaDon(cthd)){
                    System.out.println("Thêm chi tiết thành công");
                    dftblHoaDon.addRow(new Object[]{cthd.getDichVu().getTenDV(),cthd.getDichVu().getGiaBan(),cthd.getSoLuong(),cthd.getThanhTien()});
                }
            } catch (SQLException ex) {
                Logger.getLogger(GUILapHoaDon.class.getName()).log(Level.SEVERE, null, ex);
            }   
        }
    }//GEN-LAST:event_btnThemDichVuActionPerformed

    private void btnChuyenPhongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChuyenPhongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnChuyenPhongActionPerformed

    private void tblDanhSachChiTietHDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDanhSachChiTietHDMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblDanhSachChiTietHDMouseClicked

    private void tblDanhSachPhieuDatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDanhSachPhieuDatMouseClicked
        dftblHoaDon = (DefaultTableModel) tblDanhSachChiTietHD.getModel();
        HoaDon hoaDon = null;
        String maHoaDon = "";
        int row = tblDanhSachPhieuDat.getSelectedRow();
        if (row >= 0) {
            maHoaDon = tblDanhSachPhieuDat.getValueAt(row, 0).toString();
            lblMaHoaDon.setText(maHoaDon);
//            try {
//                hoaDon = hoaDon_dao.timHoaDonTheoMa(maHoaDon);
//                SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
//                lblNgayLap.setText(df.format(hoaDon.getNgayLapHD()));
//            } catch (SQLException ex) {
//                Logger.getLogger(GUILapHoaDon.class.getName()).log(Level.SEVERE, null, ex);
//            }
            
            String tenPhong = tblDanhSachPhieuDat.getValueAt(row, 1).toString();
            String giaPhong = tblDanhSachPhieuDat.getValueAt(row, 3).toString();
            lblGiaPhong.setText(giaPhong);
            txtMaHoaDon.setText(maHoaDon);
            txtMaHoaDon.setEditable(false);
            lblPhong.setText(tenPhong);
            String tenKhachHang = tblDanhSachPhieuDat.getValueAt(row, 5).toString();
            String tenNhanVien = tblDanhSachPhieuDat.getValueAt(row, 6).toString();
            lblKhachHang.setText(tenKhachHang);
            lblNhanVien.setText(tenNhanVien);
            try {
                loadDataCTHD();
            } catch (SQLException ex) {
                System.out.println("Lỗi");
            }
        } 
    }//GEN-LAST:event_tblDanhSachPhieuDatMouseClicked

    private void btnTimHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimHoaDonActionPerformed
        DefaultTableModel modelDSHD = (DefaultTableModel) tblDanhSachPhieuDat.getModel();
        String maHD = txtMaHoaDon.getText().trim();
        HoaDon x = null;
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat dft = new SimpleDateFormat("HH:mm:ss");
        if(maHD.trim().length() > 0){
//            x = hoaDon_dao.timHoaDonTheoMa(maHD);
//            if(x!=null){
//                modelDSHD.setRowCount(0);
//                modelDSHD.addRow(new Object[] {x.getMaHD(),df.format(x.getNgayLapHD()),x.getPhong().getMaPhong(),x.getKhachHang().getMaKhachHang(),x.getNhanVien().getMaNhanVien(),dft.format(x.getGioNhanPhong()),x.getTrangThaiThanhToan()});
//            }
        }
    }//GEN-LAST:event_btnTimHoaDonActionPerformed

    private void txtSoLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSoLuongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSoLuongActionPerformed

    private void btnThanhToan2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToan2ActionPerformed
        GUIThanhToan tt = new GUIThanhToan();
        tt.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnThanhToan2ActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUILapHoaDon().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhatDV;
    private javax.swing.JButton btnCapNhatDV1;
    private javax.swing.JButton btnChuyenPhong;
    private javax.swing.JButton btnThanhToan2;
    private javax.swing.JButton btnThemDichVu;
    private javax.swing.JButton btnTimHoaDon;
    private javax.swing.JComboBox<String> cbTenDichVu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblGiaPhong;
    private javax.swing.JLabel lblHoaDon;
    private javax.swing.JLabel lblKhachHang;
    private javax.swing.JLabel lblMaHoaDon;
    private javax.swing.JLabel lblNgayLap;
    private javax.swing.JLabel lblNgayLapHD;
    private javax.swing.JLabel lblNhanVien;
    private javax.swing.JLabel lblPhong;
    private javax.swing.JLabel lblTenKhachHang;
    private javax.swing.JLabel lblTenKhachHang1;
    private javax.swing.JLabel lblTenNhanVien;
    private javax.swing.JLabel lblTenPhong;
    private javax.swing.JLabel lblTieuDe;
    private javax.swing.JLabel lblTime;
    private javax.swing.JLabel lblTongTien;
    private javax.swing.JMenu mnHeThong;
    private javax.swing.JMenuItem mniCapNhatDichVu;
    private javax.swing.JMenuItem mniCapNhatKhachHang;
    private javax.swing.JMenuItem mniCapNhatKhuyenMai;
    private javax.swing.JMenuItem mniCapNhatLoaiPhong;
    private javax.swing.JMenuItem mniCapNhatNhanVien;
    private javax.swing.JMenuItem mniCapNhatPhong;
    private javax.swing.JMenuItem mniDangXuat;
    private javax.swing.JMenuItem mniDatPhong;
    private javax.swing.JMenuItem mniDatPhongCho;
    private javax.swing.JMenuItem mniLapHoaDon;
    private javax.swing.JMenuItem mniThanhToan;
    private javax.swing.JMenuItem mniThongKeDoanhThu;
    private javax.swing.JMenuItem mniThongKeDoanhThuTheoKhachHang;
    private javax.swing.JMenuItem mniTimKiemDichVu;
    private javax.swing.JMenuItem mniTimKiemKhachHang;
    private javax.swing.JMenuItem mniTimKiemKhuyenMai;
    private javax.swing.JMenuItem mniTimKiemNhanVien;
    private javax.swing.JMenuItem mniTimKiemPhong;
    private javax.swing.JMenuItem mniTrangChu;
    private javax.swing.JMenuItem mniTroGiup;
    private javax.swing.JMenu mnuDichVu;
    private javax.swing.JMenu mnuHoaDon;
    private javax.swing.JMenu mnuKhachHang;
    private javax.swing.JMenu mnuKhuyenMai;
    private javax.swing.JMenu mnuNhanVien;
    private javax.swing.JMenu mnuPhong;
    private javax.swing.JMenu mnuThongKe;
    private javax.swing.JMenuBar mnuTong;
    private javax.swing.JPanel pnTieuDe;
    private javax.swing.JPanel pnTime;
    private javax.swing.JPanel pnlDanhSachPhong;
    private javax.swing.JPanel pnlThongTinHoaDon;
    private javax.swing.JScrollPane scrDanhSachDichVuHD1;
    private javax.swing.JScrollPane scrDanhSachPhong2;
    private javax.swing.JTable tblDanhSachChiTietHD;
    private javax.swing.JTable tblDanhSachPhieuDat;
    private javax.swing.JTextField txtMaHoaDon;
    private javax.swing.JTextField txtSoLuong;
    // End of variables declaration//GEN-END:variables
}
