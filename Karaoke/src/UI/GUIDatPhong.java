/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UI;

import connectdb.ConnectDB;
import dao.KhachHang_DAO;
import dao.NhanVien_DAO;
import dao.PhieuDatPhong_DAO;
import dao.Phong_DAO;
import entity.KhachHang;
import entity.NhanVien;
import entity.PhieuDatPhong;
import entity.Phong;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Duong Ngo Manh
 */
public class GUIDatPhong extends javax.swing.JFrame {

    PhieuDatPhong_DAO pdp_dao = new PhieuDatPhong_DAO();
    Phong_DAO p_dao = new Phong_DAO();
    KhachHang_DAO khachHang_dao = new KhachHang_DAO();
    NhanVien_DAO nhanVien_dao = new NhanVien_DAO();
    DefaultTableModel dftblDP, dftblPT;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * Creates new form GUIThongTinPhong
     */
    public GUIDatPhong() {
        try {
            ConnectDB.getInstance().connect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        initComponents();
        dongHoNgay();
        dongHoGio();
        setSize(1520, 780);
        setLocationRelativeTo(null);
    }

    private void dongHoGio() {
        new Thread() {
            public void run() {
                while (true) {
                    Calendar ca = new GregorianCalendar();
                    int hour = ca.get(Calendar.HOUR);
                    int min = ca.get(Calendar.MINUTE);
                    int sec = ca.get(Calendar.SECOND);
                    lblGio.setText(hour + ":" + min + ":" + sec);
                }
            }
        }.start();
    }

    private void dongHoNgay() {
        new Thread() {
            public void run() {
                while (true) {
                    Calendar ca = new GregorianCalendar();
                    int day = ca.get(Calendar.DATE);
                    int month = ca.get(Calendar.MONTH) + 1;
                    int year = ca.get(Calendar.YEAR);
                    lblNgay.setText(+day + "/" + month + "/" + year);
                }
            }
        }.start();
    }

    private void themDuLieuComboBox() throws SQLException{
        ArrayList<NhanVien> dsNV = nhanVien_dao.layDanhSachNhanVien();
	for(NhanVien x : dsNV)
	{
		cboNhanVien.addItem(x.getMaNhanVien());
	}
    }

    public boolean kiemTraSDTKhach() {
        ArrayList<KhachHang> dskh = new ArrayList<>();
        KhachHang kh = null;
        String soDT = txtSoDienThoai.getText();
        if (soDT.trim().length() == 0) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập số điện thoại Khách");
            txtSoDienThoai.selectAll();
            txtSoDienThoai.requestFocus();
            return false;
        }
        if (!soDT.matches("(^(03)[2-9]\\d{7})|(^(07)[06-9]\\d{7})|(^(08)[1-5]\\d{7})|(^(056)\\d{7})|(^(058)\\d{7})|(^(059)\\d{7})|(^(09)[0-46-9]\\d{7})")) {
            JOptionPane.showMessageDialog(this, "Số điện thoại không đúng địng dạng");
            txtSoDienThoai.selectAll();
            txtSoDienThoai.requestFocus();
            return false;
        }
        kh = khachHang_dao.timKhachHangTheoSoDT(soDT);
        if (kh == null) {
            int xacNhan = JOptionPane.showConfirmDialog(this, "Khách hàng không có trong hệ thống, Bạn có muốn thêm khách hàng không", "Thông báo", JOptionPane.YES_NO_OPTION);
            if (xacNhan == JOptionPane.YES_OPTION) {
                GUICapNhatKhachHang guiKhachHang = new GUICapNhatKhachHang();
                guiKhachHang.setVisible(true);
            }
        } else {
            txtMaKhachHang.setText(kh.getMaKhachHang());
            txtTenKhachHang.setText(kh.getTenKhachHang());
        }
        return true;
    }

    public void docDuLieuTuDataVaoTableDanhSachPhong() throws SQLException {
        DefaultTableModel dftblDP = (DefaultTableModel) tblDanhSachDatPhong.getModel();
        ArrayList<PhieuDatPhong> listpdp = pdp_dao.getAllPhieuDatPhong();
        dftblDP.setRowCount(0);
        for (PhieuDatPhong x : listpdp) {
            dftblDP.addRow(new Object[]{x.getMaPhieuDatPhong(), x.getPhong().getMaPhong(), x.getKhachHang().getMaKhachHang(), x.getNhanVien().getMaNhanVien(), x.getNgayDatPhong(), x.getGioNhanPhong()});
        }
    }

    public void docDuLieuTuDataVaoTableDanhSachPhongTrong() throws SQLException {
        DefaultTableModel dftblPT = (DefaultTableModel) tblDanhSachPhongTrong.getModel();
        ArrayList<Phong> listpc = p_dao.getAllPhongTrong();
        dftblPT.setRowCount(0);
        for (Phong x : listpc) {
            dftblPT.addRow(new Object[]{x.getMaPhong(), x.getLoaiPhong().getMaLoaiPhong(), x.getTenPhong(), x.getGiaPhong(), x.getSoNguoiToiDa(), x.getTrangThaiPhong()});
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlThongTinDatPhong = new javax.swing.JPanel();
        lblMaPhieuDatPhong = new javax.swing.JLabel();
        lblMaNhanVien = new javax.swing.JLabel();
        cboNhanVien = new javax.swing.JComboBox<>();
        txtMaPhieuDatPhong = new javax.swing.JTextField();
        txtMaPhong = new javax.swing.JTextField();
        lblMaPhong = new javax.swing.JLabel();
        pnlDanhSachPhong = new javax.swing.JPanel();
        scrDanhSachPhong2 = new javax.swing.JScrollPane();
        tblDanhSachDatPhong = new javax.swing.JTable();
        pnlTieuDe = new javax.swing.JPanel();
        lblTieuDe = new javax.swing.JLabel();
        lblGio = new javax.swing.JLabel();
        lblNgay = new javax.swing.JLabel();
        pnlDanhSachPhongTrong = new javax.swing.JPanel();
        scrDanhSachPhong1 = new javax.swing.JScrollPane();
        tblDanhSachPhongTrong = new javax.swing.JTable();
        pnlThongTinKhachHang = new javax.swing.JPanel();
        txtSoDienThoai = new javax.swing.JTextField();
        lblTenKhachHang = new javax.swing.JLabel();
        lblNgayLap3 = new javax.swing.JLabel();
        btnKiemTraSDT = new javax.swing.JButton();
        txtMaKhachHang = new javax.swing.JTextField();
        lblNgayLap1 = new javax.swing.JLabel();
        txtTenKhachHang = new javax.swing.JTextField();
        pnlChucNang = new javax.swing.JPanel();
        btnDatPhongNgay = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        btnTaiLaiDanhSach = new javax.swing.JButton();
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
        setPreferredSize(new java.awt.Dimension(1520, 780));
        setResizable(false);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblMaPhieuDatPhong.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblMaPhieuDatPhong.setText("Mã phiếu đặt phòng:");

        lblMaNhanVien.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblMaNhanVien.setText("Nhân viên:");

        cboNhanVien.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả" }));
        cboNhanVien.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                cboNhanVienComponentShown(evt);
            }
        });

        txtMaPhieuDatPhong.setEnabled(false);

        lblMaPhong.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblMaPhong.setText("Mã phòng:");

        javax.swing.GroupLayout pnlThongTinDatPhongLayout = new javax.swing.GroupLayout(pnlThongTinDatPhong);
        pnlThongTinDatPhong.setLayout(pnlThongTinDatPhongLayout);
        pnlThongTinDatPhongLayout.setHorizontalGroup(
            pnlThongTinDatPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThongTinDatPhongLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlThongTinDatPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblMaPhieuDatPhong)
                    .addComponent(lblMaNhanVien)
                    .addComponent(lblMaPhong))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlThongTinDatPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMaPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaPhieuDatPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(11, Short.MAX_VALUE))
        );
        pnlThongTinDatPhongLayout.setVerticalGroup(
            pnlThongTinDatPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThongTinDatPhongLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlThongTinDatPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMaPhieuDatPhong)
                    .addComponent(txtMaPhieuDatPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlThongTinDatPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMaNhanVien)
                    .addComponent(cboNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlThongTinDatPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMaPhong)
                    .addComponent(txtMaPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(pnlThongTinDatPhong, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 320, 150));

        pnlDanhSachPhong.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "DANH SÁCH PHÒNG", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tblDanhSachDatPhong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã phiếu đặt phòng", "Mã phòng", "Khách hàng", "Nhân viên", "Ngày đặt phòng", "Giờ nhận phòng"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblDanhSachDatPhong.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tblDanhSachDatPhongFocusLost(evt);
            }
        });
        tblDanhSachDatPhong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDanhSachDatPhongMouseClicked(evt);
            }
        });
        tblDanhSachDatPhong.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                tblDanhSachDatPhongComponentShown(evt);
            }
        });
        scrDanhSachPhong2.setViewportView(tblDanhSachDatPhong);

        javax.swing.GroupLayout pnlDanhSachPhongLayout = new javax.swing.GroupLayout(pnlDanhSachPhong);
        pnlDanhSachPhong.setLayout(pnlDanhSachPhongLayout);
        pnlDanhSachPhongLayout.setHorizontalGroup(
            pnlDanhSachPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrDanhSachPhong2, javax.swing.GroupLayout.DEFAULT_SIZE, 780, Short.MAX_VALUE)
        );
        pnlDanhSachPhongLayout.setVerticalGroup(
            pnlDanhSachPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrDanhSachPhong2, javax.swing.GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)
        );

        getContentPane().add(pnlDanhSachPhong, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 790, 370));

        pnlTieuDe.setBackground(new java.awt.Color(102, 0, 0));

        lblTieuDe.setBackground(new java.awt.Color(102, 0, 0));
        lblTieuDe.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTieuDe.setForeground(new java.awt.Color(242, 242, 242));
        lblTieuDe.setText("ĐẶT PHÒNG NGAY");

        lblGio.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblGio.setForeground(new java.awt.Color(255, 255, 255));
        lblGio.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblGio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/alarm-clock.png"))); // NOI18N
        lblGio.setText("00:00:00");

        lblNgay.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblNgay.setForeground(new java.awt.Color(255, 255, 255));
        lblNgay.setText("Ngày , Tháng Năm");

        javax.swing.GroupLayout pnlTieuDeLayout = new javax.swing.GroupLayout(pnlTieuDe);
        pnlTieuDe.setLayout(pnlTieuDeLayout);
        pnlTieuDeLayout.setHorizontalGroup(
            pnlTieuDeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTieuDeLayout.createSequentialGroup()
                .addGap(661, 661, 661)
                .addComponent(lblTieuDe)
                .addGap(285, 285, 285)
                .addComponent(lblGio)
                .addGap(18, 18, 18)
                .addComponent(lblNgay)
                .addContainerGap(407, Short.MAX_VALUE))
        );
        pnlTieuDeLayout.setVerticalGroup(
            pnlTieuDeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTieuDeLayout.createSequentialGroup()
                .addGroup(pnlTieuDeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlTieuDeLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(lblTieuDe))
                    .addGroup(pnlTieuDeLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnlTieuDeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNgay)
                            .addComponent(lblGio))))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        getContentPane().add(pnlTieuDe, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1920, 90));

        pnlDanhSachPhongTrong.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "DANH SÁCH PHÒNG TRỐNG", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tblDanhSachPhongTrong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã phòng", "Loại phòng", "Tên phòng", "Giá phòng", "Số người tối đa", "Trạng thái"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class, java.lang.Integer.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblDanhSachPhongTrong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDanhSachPhongTrongMouseClicked(evt);
            }
        });
        tblDanhSachPhongTrong.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                tblDanhSachPhongTrongComponentShown(evt);
            }
        });
        scrDanhSachPhong1.setViewportView(tblDanhSachPhongTrong);

        javax.swing.GroupLayout pnlDanhSachPhongTrongLayout = new javax.swing.GroupLayout(pnlDanhSachPhongTrong);
        pnlDanhSachPhongTrong.setLayout(pnlDanhSachPhongTrongLayout);
        pnlDanhSachPhongTrongLayout.setHorizontalGroup(
            pnlDanhSachPhongTrongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrDanhSachPhong1, javax.swing.GroupLayout.DEFAULT_SIZE, 690, Short.MAX_VALUE)
        );
        pnlDanhSachPhongTrongLayout.setVerticalGroup(
            pnlDanhSachPhongTrongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrDanhSachPhong1, javax.swing.GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)
        );

        getContentPane().add(pnlDanhSachPhongTrong, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 340, 700, 370));

        pnlThongTinKhachHang.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông Tin Khách Hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        txtSoDienThoai.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txtSoDienThoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSoDienThoaiActionPerformed(evt);
            }
        });

        lblTenKhachHang.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTenKhachHang.setText("Tên Khách Hàng:");

        lblNgayLap3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblNgayLap3.setText("Số điện thoại:");

        btnKiemTraSDT.setBackground(new java.awt.Color(204, 204, 204));
        btnKiemTraSDT.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnKiemTraSDT.setText("Kiểm tra");
        btnKiemTraSDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKiemTraSDTActionPerformed(evt);
            }
        });

        txtMaKhachHang.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N

        lblNgayLap1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblNgayLap1.setText("Mã khách hàng:");

        txtTenKhachHang.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N

        javax.swing.GroupLayout pnlThongTinKhachHangLayout = new javax.swing.GroupLayout(pnlThongTinKhachHang);
        pnlThongTinKhachHang.setLayout(pnlThongTinKhachHangLayout);
        pnlThongTinKhachHangLayout.setHorizontalGroup(
            pnlThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThongTinKhachHangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTenKhachHang)
                    .addComponent(lblNgayLap3)
                    .addComponent(lblNgayLap1))
                .addGap(18, 18, 18)
                .addGroup(pnlThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlThongTinKhachHangLayout.createSequentialGroup()
                        .addComponent(txtSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnKiemTraSDT)))
                .addGap(13, 13, 13))
        );
        pnlThongTinKhachHangLayout.setVerticalGroup(
            pnlThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThongTinKhachHangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNgayLap3)
                    .addComponent(txtSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnKiemTraSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTenKhachHang)
                    .addComponent(txtTenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(pnlThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNgayLap1)
                    .addComponent(txtMaKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        getContentPane().add(pnlThongTinKhachHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 110, -1, -1));

        btnDatPhongNgay.setBackground(new java.awt.Color(153, 255, 255));
        btnDatPhongNgay.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnDatPhongNgay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/add.png"))); // NOI18N
        btnDatPhongNgay.setText("Đặt phòng ngay");
        btnDatPhongNgay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDatPhongNgayActionPerformed(evt);
            }
        });

        btnLamMoi.setBackground(new java.awt.Color(153, 255, 153));
        btnLamMoi.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLamMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/clean.png"))); // NOI18N
        btnLamMoi.setText("Làm mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        btnTaiLaiDanhSach.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnTaiLaiDanhSach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/synchronize.png"))); // NOI18N
        btnTaiLaiDanhSach.setText("Tải lại danh sách");
        btnTaiLaiDanhSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaiLaiDanhSachActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlChucNangLayout = new javax.swing.GroupLayout(pnlChucNang);
        pnlChucNang.setLayout(pnlChucNangLayout);
        pnlChucNangLayout.setHorizontalGroup(
            pnlChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlChucNangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnDatPhongNgay)
                .addGap(18, 18, 18)
                .addComponent(btnLamMoi)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(btnTaiLaiDanhSach)
                .addContainerGap())
        );
        pnlChucNangLayout.setVerticalGroup(
            pnlChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlChucNangLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlChucNangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDatPhongNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTaiLaiDanhSach, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
        );

        getContentPane().add(pnlChucNang, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 210, 510, 70));

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

    private void mniDichVuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniDichVuActionPerformed
        GUITimKiemDichVu ttdv = new GUITimKiemDichVu();
        ttdv.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_mniDichVuActionPerformed

    private void mniKhuyenMaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniKhuyenMaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mniKhuyenMaiActionPerformed

    private void mniHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniHoaDonActionPerformed
        GUIThanhToan tthd = new GUIThanhToan();
        tthd.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_mniHoaDonActionPerformed

    private void btnDatPhongNgayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDatPhongNgayActionPerformed
        if (txtMaPhong.getText().equals("") || txtMaKhachHang.getText().equals("") || cboNhanVien.getSelectedItem().equals("")) {
            JOptionPane.showMessageDialog(this, "Bạn chưa điền đủ thông tin");
            return;
        }
        
        Phong maP = new Phong(txtMaPhong.getText());
//        KhachHang maKH = new KhachHang(cboKhachHang.getSelectedItem().toString());
        KhachHang maKH = new KhachHang(txtMaKhachHang.getText());
        NhanVien maNV = new NhanVien(cboNhanVien.getSelectedItem().toString());
        LocalDate localTime1 = LocalDate.now();
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String ngayDatPhong = localTime1.format(formatter1);
        String maPDP = pdp_dao.khoiTaoMaPDPN(txtMaPhong.getText(),localTime1);
        LocalDateTime localTime2 = LocalDateTime.now();
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String gioNhanPhong = localTime2.format(formatter2);
        PhieuDatPhong x = new PhieuDatPhong(maPDP, maP, maKH, maNV, ngayDatPhong, gioNhanPhong);
        DefaultTableModel dftblPDP = (DefaultTableModel) tblDanhSachDatPhong.getModel();
        //kiem tra trung ma
        for (int i = 0; i < dftblPDP.getRowCount(); i++) {
            if (dftblPDP.getValueAt(i, 0).equals(maP)) {
                JOptionPane.showMessageDialog(this, "Trùng mã phòng!");
                return;
            }
        }
        try {
            pdp_dao.themPhieuDatPhong(x);
            dftblPDP.addRow(new Object[]{x.getMaPhieuDatPhong(), x.getPhong().getMaPhong(), x.getKhachHang().getMaKhachHang(), x.getNhanVien().getMaNhanVien(), x.getNgayDatPhong(), x.getGioNhanPhong()});

            JOptionPane.showMessageDialog(this, "Đặt phòng thành công!");
        } catch (Exception e2) {
            JOptionPane.showMessageDialog(this, "Đặt phòng không thành công!");
            e2.printStackTrace();
        }
    }//GEN-LAST:event_btnDatPhongNgayActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        // TODO add your handling code here:
        txtMaPhieuDatPhong.setText("");
        txtMaPhong.setText("");
        txtMaKhachHang.setText("");
//        cboKhachHang.setSelectedItem("Tất cả");
        cboNhanVien.setSelectedItem("Tất cả");
//        dcNgayDatPhong.setDate(null);
    }//GEN-LAST:event_btnLamMoiActionPerformed

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

    private void cboNhanVienComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_cboNhanVienComponentShown

    }//GEN-LAST:event_cboNhanVienComponentShown

    private void tblDanhSachPhongTrongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDanhSachPhongTrongMouseClicked
        // TODO add your handling code here:
        int row = tblDanhSachPhongTrong.getSelectedRow();
        String maPhong = tblDanhSachPhongTrong.getValueAt(row, 0).toString();
        txtMaPhong.setText(maPhong);

    }//GEN-LAST:event_tblDanhSachPhongTrongMouseClicked

    private void tblDanhSachPhongTrongComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_tblDanhSachPhongTrongComponentShown
        // TODO add your handling code here:
    }//GEN-LAST:event_tblDanhSachPhongTrongComponentShown

    private void tblDanhSachDatPhongFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tblDanhSachDatPhongFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_tblDanhSachDatPhongFocusLost

    private void tblDanhSachDatPhongComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_tblDanhSachDatPhongComponentShown
        // TODO add your handling code here:
    }//GEN-LAST:event_tblDanhSachDatPhongComponentShown

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        // TODO add your handling code here:
        try {
            themDuLieuComboBox();
            docDuLieuTuDataVaoTableDanhSachPhong();
            docDuLieuTuDataVaoTableDanhSachPhongTrong();
            System.out.println("Đọc thành công");
        } catch (SQLException ex) {
            System.out.println("Đọc lỗi");
        }

    }//GEN-LAST:event_formComponentShown

    private void tblDanhSachDatPhongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDanhSachDatPhongMouseClicked
        // TODO add your handling code here:
        int row = tblDanhSachDatPhong.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) tblDanhSachDatPhong.getModel();
        txtMaPhieuDatPhong.setText(model.getValueAt(row, 0).toString());
        txtMaPhong.setText(model.getValueAt(row, 1).toString());
        txtMaKhachHang.setText(model.getValueAt(row, 2).toString());
//        String selectedKhachHang = model.getValueAt(row, 2).toString();
        String selectedNhanVien = model.getValueAt(row, 3).toString();
        // hiển thị giá trị cbobox lấy từ bảng
//        setComboBoxSelectedValue(cboKhachHang, selectedKhachHang);
        setComboBoxSelectedValue(cboNhanVien, selectedNhanVien);
        //Lưu ý: Chuyển đổi định dạng ngày tháng
//        try {
//            Date date = sdf.parse(model.getValueAt(row, 4).toString());
//            dcNgayDatPhong.setDate(date);
//        } catch (ParseException ex) {
//            ex.printStackTrace();
//        }
    }//GEN-LAST:event_tblDanhSachDatPhongMouseClicked

    private void btnTaiLaiDanhSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaiLaiDanhSachActionPerformed
        // TODO add your handling code here:
        taiLaiDanhSachDatPhong();
//        taiLaiDanhSachPhongTrong();
    }//GEN-LAST:event_btnTaiLaiDanhSachActionPerformed

    private void txtSoDienThoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSoDienThoaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSoDienThoaiActionPerformed

    private void btnKiemTraSDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKiemTraSDTActionPerformed
        kiemTraSDTKhach();
    }//GEN-LAST:event_btnKiemTraSDTActionPerformed

    private void taiLaiDanhSachDatPhong() {
        dftblDP = (DefaultTableModel) tblDanhSachDatPhong.getModel();
        dftblDP.setRowCount(0);
        try {
            ArrayList<PhieuDatPhong> danhSachDatPhong = pdp_dao.getAllPhieuDatPhong();

            for (PhieuDatPhong dp : danhSachDatPhong) {
                dftblDP.addRow(new Object[]{
                    dp.getMaPhieuDatPhong(),
                    dp.getPhong().getMaPhong(),
                    dp.getKhachHang().getMaKhachHang(),
                    dp.getNhanVien().getMaNhanVien(),
                    dp.getNgayDatPhong(),
                    dp.getGioNhanPhong()
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    private void taiLaiDanhSachPhongTrong() {
//        dftblPT = (DefaultTableModel) tblDanhSachPhongTrong.getModel();
//        dftblPT.setRowCount(0);
//        try {
//            ArrayList<Phong> danhSachPhongTrong = p_dao.getAllPhong();
//
//            for (Phong pt : danhSachPhongTrong) {
//                dftblPT.addRow(new Object[]{
//                    pt.getMaPhong(),
//                    pt.getLoaiPhong().getMaLoaiPhong(),
//                    pt.getTenPhong(),
//                    pt.getGiaPhong(),
//                    pt.getSoNguoiToiDa(),
//                    pt.getTrangThaiPhong()
//                });
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    // phương thức hiển thị lên cbobox dựa trên dữ liệu sẵn
    private void setComboBoxSelectedValue(JComboBox comboBox, String value) {
        for (int i = 0; i < comboBox.getItemCount(); i++) {
            if (comboBox.getItemAt(i).toString().equals(value)) {
                comboBox.setSelectedIndex(i);
                break;
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUICapNhatPhong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUICapNhatPhong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUICapNhatPhong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUICapNhatPhong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUIDatPhong().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDatPhongNgay;
    private javax.swing.JButton btnKiemTraSDT;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnTaiLaiDanhSach;
    private javax.swing.JComboBox<String> cboNhanVien;
    private javax.swing.JLabel lblGio;
    private javax.swing.JLabel lblMaNhanVien;
    private javax.swing.JLabel lblMaPhieuDatPhong;
    private javax.swing.JLabel lblMaPhong;
    private javax.swing.JLabel lblNgay;
    private javax.swing.JLabel lblNgayLap1;
    private javax.swing.JLabel lblNgayLap3;
    private javax.swing.JLabel lblTenKhachHang;
    private javax.swing.JLabel lblTieuDe;
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
    private javax.swing.JPanel pnlChucNang;
    private javax.swing.JPanel pnlDanhSachPhong;
    private javax.swing.JPanel pnlDanhSachPhongTrong;
    private javax.swing.JPanel pnlThongTinDatPhong;
    private javax.swing.JPanel pnlThongTinKhachHang;
    private javax.swing.JPanel pnlTieuDe;
    private javax.swing.JScrollPane scrDanhSachPhong1;
    private javax.swing.JScrollPane scrDanhSachPhong2;
    private javax.swing.JTable tblDanhSachDatPhong;
    private javax.swing.JTable tblDanhSachPhongTrong;
    private javax.swing.JTextField txtMaKhachHang;
    private javax.swing.JTextField txtMaPhieuDatPhong;
    private javax.swing.JTextField txtMaPhong;
    private javax.swing.JTextField txtSoDienThoai;
    private javax.swing.JTextField txtTenKhachHang;
    // End of variables declaration//GEN-END:variables
}
