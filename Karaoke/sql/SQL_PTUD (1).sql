Create database QlKara
Use QlKara

Create table DichVu(
MaDichVu nvarchar(20) primary key,
TenDichVu nvarchar(10) NOT NULL,
SoLuongDichVu int,
GiaBan float ,
DonViTinh nvarchar(10) NOT NULL,
trangThaiDichVu bit 
)
INSERT INTO DichVu(MaDichVu,TenDichVu,SoLuongDichVu,GiaBan,DonViTinh,trangThaiDichVu)
VALUES 
	('DV001',N'Coca cola',200,20000,N'lon','true'),
	('DV002',N'Lavie',100,10000,N'chai','true'),
	('DV003',N'Heniken',300,30000,N'lon','true'),
	('DV004',N'333',300,30000,N'lon','true'),
	('DV005',N'Tiger',300,30000,N'lon','true'),
	('DV006',N'Sting',200,20000,N'lon','true'),
	('DV007',N'Nuoc ngot',50,250000,N'thung','true'),
	('DV008',N'Dau phong',500,15000,N'bich','true'),
	('DV009',N'Mi xao',500,30000,N'phan','true'),
	('DV0010',N'Trai cay',500,50000,N'phan','true'),
	('DV0011',N'Com chien',500,30000,N'phan','true'),
	('DV0013',N'Com chien',500,30000,N'phan','false'),
	('DV0012',N'Muc nuong',500,70000,N'phan','true');

Create table KhuyenMai(
MaKhuyenMai nvarchar(20) primary key,
MaGiamGia nvarchar(20) NOT NULL,
ChietKhau float,
SoLuong int ,
ngayBatDauKM date NOT NULL,
ngayKetThucKM date NOT NULL,
MoTa nvarchar(20)
)
INSERT INTO KhuyenMai (MaKhuyenMai, MaGiamGia, ChietKhau, SoLuong, ngayBatDauKM, ngayKetThucKM, MoTa)
VALUES
    ('KM001', 'TrungThu', 0.10, 100, '2023-11-01', '2023-11-15', N'Giảm 10%'),
    ('KM002', 'Noel', 0.15, 50, '2023-11-05', '2023-11-20', N'Giảm 15%'),
    ('KM003', '20/10', 0.05, 200, '2023-11-10', '2023-11-25', N'Giảm 5%'),
    ('KM004', '20/11', 0.20, 75, '2023-11-15', '2023-11-30', N'Giảm 20%'),
    ('KM005', '1/1', 0.12, 80, '2023-11-20', '2023-12-05', N'Giảm 12%'),
    ('KM006', '2/9', 0.18, 60, '2023-11-25', '2023-12-10', N'Giảm 18%');

CREATE TABLE KhachHang (
    MaKhachHang nvarchar(20) PRIMARY KEY NOT NULL,
    TenKhachHang nvarchar(20) NOT NULL,
    GioiTinh nvarchar(5),
    NgaySinh date,
    SoDT nvarchar(12) NULL,
    SoCCCD nvarchar(13) NULL,
	DiaChi nvarchar(50) NULL
)

INSERT INTO KhachHang (MaKhachHang, TenKhachHang, GioiTinh, NgaySinh, SoDT, SoCCCD, DiaChi)
VALUES
    ('KHA001', N'Nguyễn Văn Chí', 'Nam', '1990-05-15', '09712345678', '012345678901', N'Quận 1, TP Hồ Chí Minh'),
    ('KHA002', N'Trần Thị Bình', N'Nữ', '1985-12-10', '09823456789', '071234567890', N'Quận 2, TP Hồ Chí Minh'),
    ('KHA003', N'Lê Văn Cừ', 'Nam', '1995-08-20', '09334567890', '0512345678901', N'Quận 3, TP Hồ Chí Minh'),
    ('KHA004', N'Phạm Thị Thùy Duyên', N'Nữ', '2000-03-05', '07945678901', '0612345678902', N'Quận 4, TP Hồ Chí Minh'),
    ('KHA005', N'Nguyễn Thị Như', N'Nữ', '1992-11-28', '09312345678', '0712345678903', N'Quận 5, TP Hồ Chí Minh'),
    ('KHA006', N'Trần Văn Long', 'Nam', '1988-07-02', '07923456789', '0512345678904', N'Quận 6, TP Hồ Chí Minh'),
    ('KHA007', N'Lê Thị Huyền', N'Nữ', '1997-04-17', '09734567890', '0612345678905', N'Quận 7, TP Hồ Chí Minh'),
    ('KHA008', N'Phạm Văn Huynh', 'Nam', '2001-09-22', '09845678901', '0712345678906', N'Quận 8, TP Hồ Chí Minh'),
    ('KHA009', N'Nguyễn Văn Trỗi', 'Nam', '1998-02-14', '09356789012', '0512345678907', N'Quận 9, TP Hồ Chí Minh'),
    ('KHA010', N'Trần Thị Thư', N'Nữ', '1991-10-31', '09767890123', '0712345678908', N'Quận 10, TP Hồ Chí Minh'),
    ('KHA011', N'Lê Văn Long', 'Nam', '2002-07-25', '07978901234', '0612345678909', N'Quận 11, TP Hồ Chí Minh'),
    ('KHA012', N'Phạm Thị My', N'Nữ', '1989-03-18', '09889012345', '0712345678910', N'Quận 12, TP Hồ Chí Minh'),
    ('KHA013', N'Nguyễn Thị Quỳnh', N'Nữ', '1994-06-09', '09790123456', '0712345678911', N'Quận Bình Tân, TP Hồ Chí Minh'),
    ('KHA014', N'Trần Văn Phong', 'Nam', '2003-01-11', '09701234567', '0712345678912', N'Quận Bình Thạnh, TP Hồ Chí Minh'),
    ('KHA015', N'Lê Thị Linh', N'Nữ', '1987-08-08', '07912345678', '0712345678913', N'Quận Tân Phú, TP Hồ Chí Minh'),
    ('KHA016', N'Phạm Văn Phong', 'Nam', '1993-12-03', '09323456789', '0712345678914', N'Quận Tân Bình, TP Hồ Chí Minh'),
    ('KHA017', N'Nguyễn Văn Linh', 'Nam', '1996-04-06', '09334567890', '0712345678915', N'Quận Phú Nhuận, TP Hồ Chí Minh'),
    ('KHA018', N'Trần Thị Sáu', N'Nữ', '1999-09-29', '07945678901', '0712345678916', N'Quận Gò Vấp, TP Hồ Chí Minh'),
    ('KHA019', N'Lê Văn Tiến', 'Nam', '2004-02-20', '09756789012', '0712345678917', N'Quận Củ Chi, TP Hồ Chí Minh'),
    ('KHA020', N'Phạm Thị Ý', N'Nữ', '1986-07-13', '09867890123', '0712345678918', N'Quận Hóc Môn, TP Hồ Chí Minh');
select * FROM KhachHang

CREATE TABLE ChucVu (
    MaChucVu nvarchar(20) PRIMARY KEY,
    TenChucVu nvarchar(50) NOT NULL
)

INSERT INTO ChucVu (MaChucVu, TenChucVu)
VALUES
    ('CV001', N'Quản lý Karaoke'),
    ('CV002', N'Nhân viên Thu Ngân'),
    ('CV003', N'Nhân viên Phục vụ'),
    ('CV004', N'Nhân viên Bảo vệ'),
    ('CV005', N'Nhân viên Kĩ thuật');

SELECT * FROM ChucVu


Create table LoaiPhong(
MaLoaiPhong nvarchar(20) primary key,
TenLoaiPhong nvarchar(10),
)

INSERT INTO LoaiPhong(MaLoaiPhong, TenLoaiPhong)
VALUES
('LP001', 'VIP'),
('LP002', 'Thuong')
SELECT * FROM LoaiPhong

Create table NhanVien(
MaNhanVien nvarchar(20) primary key ,
MaCV nvarchar(20),
TenNV nvarchar(30) ,
GioiTinh nvarchar(5) ,
NgaySinh date,
SoDT nvarchar(11) , 
DiaChi nvarchar(40) ,
MatKhau nvarchar(40) ,
foreign key (MaCV) references ChucVu(MaChucVu)
)

INSERT INTO NhanVien(MaNhanVien, MaCV, TenNV, GioiTinh, NgaySinh, SoDT, DiaChi, MatKhau)
VALUES
('NV001','CV001', N'Nguyen Van Dao', N'Nam', '1980-07-14', '0723582763', '12 Nguyen Van Bao', 'adggdg'),
('NV002', 'CV002',N'Nguyen Thi Hanh', N'Nữ', '1970-11-09', '0826543574', '12/60 Nguyen Van Nghi', 'SGE4Dd'),
('NV003', 'CV003',N'Tran Chung Hieu', N'Nam', '1993-09-05', '0926456789', '125 Le Duc Tho', 'dfgwr3'),
('NV004', 'CV004',N'Duong Van Hung', N'Nam', '1996-02-12', '0823356789', '225 Ton That Thuyet', 'bferf4'),
('NV005', 'CV003',N'Nhat Van Tien', N'Nữ', '1998-03-13', '0823756789', '221 Le Thai To', 'aefsd2'),
('NV006', 'CV002',N'Bui Van Dung', N'Nam', '1991-08-19', '0923456749', '436 Tran Duy Hung', '43fsgegf'),
('NV007', 'CV002',N'Hua Van Khang', N'Nữ', '1992-06-20', '0723676789', '64 Nguyen Oanh', 'agser6'),
('NV008', 'CV004', N'That Van Ngoc', N'Nam', '1996-08-21', '0823456789', '69 Hoang Sa', 'adfg72'),
('NV009', 'CV005',N'Lo Van Hoang', N'Nữ', '1997-04-22', '0923456789', '93 Phan Van Tri', 'dzfhfg7'),
('NV010', 'CV003',N'Le Van Phuc', N'Nam', '1995-03-27', '0723456789', '551 Nguyen Thai Son', 'dgehsdh90'),
('NV011', 'CV002', N'Vi Van Tam', N'Nữ', '1993-01-24', '0523456789', '416 Nguyen Huy Thong', '0fgb63v'),
('NV012', 'CV002', N'Pham Van Huy', N'Nam', '1988-09-26', '0323456789', '31/20/8 Tran Ba Giao', 'dfg65sdfh'),
('NV0113', 'CV004', N'Kieu Van Yen', N'Nữ', '1984-11-21', '0723456789', '81/20 Tan Son Nhat', 'zdf5xdfb'),
('NV014', 'CV003', N'Hoang Gia Nguyen', N'Nam', '1987-12-18', '0923456789', '53/18 Duong Quang Ham', 'zgdf32zdgf'),
('NV015', 'CV003', N'Chu Van An', N'Nam', '1991-08-15', '0323456789', '347 Quang Trung', 'zdgf7zse'),
('NV016', 'CV002', N'Ton Van Ngo', N'Nữ', '1992-03-12', '0723456789', '821 Xo Viet', 'zdgf6zd'),
('NV017', 'CV002', N'Vu Van Thuan', N'Nam', '1993-06-11', '0923456789', '741 Nguyen Trai', 'hui76sgr'),
('NV018', 'CV002', N'Thong Van Ba',  N'Nữ', '1995-07-17', '0823456789', '183 Tran Quoc Toan', 'zdfg5gytu');

SELECT *FROM NhanVien

Create table Phong(
MaPhong nvarchar(20) primary key,
MaLP nvarchar(20),
TenPhong nvarchar(10),
GiaPhong float,
SoNguoiToiDa int,
TrangThaiPhong nvarchar(10),
foreign key (MaLP) references LoaiPhong(MaLoaiPhong)
)

UPDATE Phong
SET TrangThaiPhong = N'Đầy'
WHERE MaPhong IN ('P003', 'P005');

INSERT INTO Phong(MaPhong, MaLP, TenPhong, GiaPhong, SoNguoiToiDa,TrangThaiPhong)
VALUES
('P001', 'LP001', 'Phong 001', 2000000.00, 5,N'Chờ'),
('P002', 'LP002', 'Phong 002', 500000.00, 10,N'Đầy'),
('P003', 'LP001', 'Phong 003', 500000.00, 10,N'Trống'),
('P004', 'LP002', 'Phong 004', 3000000.00, 5,N'Trống'),
('P005', 'LP001', 'Phong 005', 1000000.00, 5,N'Đầy'),
('P006', 'LP002', 'Phong 006', 5000000.00, 5,N'Trống'),
('P007', 'LP001', 'Phong 007', 200000.00, 10,N'Đầy'),
('P008', 'LP001', 'Phong 008', 600000.00, 10,N'Trống'),
('P009', 'LP001', 'Phong 009', 3000000.00, 5,N'Đầy'),
('P010', 'LP002', 'Phong 010', 2000000.00, 5,N'Trống'),
('P011', 'LP002', 'Phong 011', 400000.00, 10,N'Chờ'),
('P012', 'LP002', 'Phong 012', 600000.00, 10,N'Đầy');

Create table PhieuDatPhong(
MaPhieuDatPhong nvarchar(20) primary key,
MaP nvarchar(20),
MaKH nvarchar(20),
MaNV nvarchar(20),
NgayDatPhong date,
GioNhanPhong datetime,
TrangThaiDon nvarchar(20),
foreign key (MaP) references Phong(MaPhong),
foreign key (MaKH) references KhachHang(MaKhachHang),
foreign key (MaNV) references NhanVien(MaNhanVien)
)

INSERT INTO PhieuDatPhong (MaPhieuDatPhong, MaP, MaKH, MaNV, NgayDatPhong, GioNhanPhong, TrangThaiDon)
VALUES
('PDP00330112315', 'P003', 'KHA017', 'NV002', '2023-01-15', '2023-01-15 12:00:00', N'Chưa thanh toán'),
('PDP00530112315', 'P005', 'KHA020', 'NV003', '2023-02-20', '2023-02-20 14:30:00', N'Chưa thanh toán');

SELECT * FROM PhieuDatPhong

Create table HoaDon(
MaHoaDon nvarchar(20) primary key,
NgayLapHD date,
MaNV nvarchar(20),
TenKhachHang nvarchar(20),
MaP nvarchar(20),
GioNhanPhong datetime,
GioTraPhong datetime null,
MaKM nvarchar(20) null,
TongTien float null,
foreign key (MaNV) references NhanVien(MaNhanVien),
foreign key (MaP) references Phong(MaPhong),
foreign key (MaKM) references KhuyenMai(MaKhuyenMai)
)

INSERT INTO HoaDon (MaHoaDon, NgayLapHD, MaNV, TenKhachHang, MaP, GioNhanPhong, GioTraPhong, MaKM, TongTien)
VALUES
('HD301123151339', '2023-01-15', 'NV002', N'Nguyễn Văn Linh', 'P003', '2023-01-15 12:00:00', NULL, NULL, NULL),
('HD301123151448', '2023-02-20', 'NV003', N'Phạm Thị Ý', 'P005', '2023-02-20 14:30:00', NULL, NULL, NULL);

select * from HoaDon

CREATE TABLE CT_HoaDon (
    MaHD NVARCHAR(20),
	MaDV NVARCHAR(20),
	SoLuong INT,
    MaP NVARCHAR(20),
    PRIMARY KEY (MaHD, MaDV),  -- Composite primary key
    FOREIGN KEY (MaHD) REFERENCES HoaDon(MaHoaDon),
    FOREIGN KEY (MaP) REFERENCES Phong(MaPhong),
	FOREIGN KEY (MaDV) REFERENCES DichVu(MaDichVu)
);

