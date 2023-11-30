Create database QlKara
Use QlKara

Create table DichVu(
MaDichVu nvarchar(10) primary key,
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
	('DV007',N'Nước ngọt',50,250000,N'thùng','true'),
	('DV008',N'Đậu phộng',500,15000,N'bịch','true'),
	('DV009',N'Mì xào',500,30000,N'phần','true'),
	('DV0010',N'Trái cây',500,50000,N'phần','true'),
	('DV0011',N'Cơm chiên',500,30000,N'phần','true'),
	('DV0012',N'Mực nướng',500,70000,N'phần','true');

Create table KhuyenMai(
MaKhuyenMai nvarchar(10) primary key,
MaGiamGia nvarchar(20) NOT NULL,
ChietKhau float,
SoLuong int ,
ngayBatDauKM date NOT NULL,
ngayKetThucKM date NOT NULL
)
INSERT INTO KhuyenMai(MaKhuyenMai,MaGiamGia,ChietKhau,SoLuong,ngayBatDauKM,ngayKetThucKM)
VALUES 
	('KM001','Christmas',0.25,50,'2023-11-30','2023-12-31'),
	('KM002','Halloween',0.3,50,'2023-11-30','2023-12-31'),
	('KM003','NGAY10102023',0.1,50,'2023-10-10','2023-10-11'),
	('KM004','NGAY09092023',0.1,50,'2023-09-09','2023-09-10'),
	('KM005','NGAY08082023',0.1,50,'2023-08-08','2023-08-09'),
	('KM006','NGAY07072023',0.1,50,'2023-07-07','2023-07-08');

CREATE TABLE KhachHang (
    MaKhachHang nvarchar(10) PRIMARY KEY NOT NULL,
    TenKhachHang nvarchar(20) NOT NULL,
    GioiTinh bit,
    NgaySinh date,
    SoDT nvarchar(12) NULL,
    SoCCCD nvarchar(13) NULL
)

INSERT INTO KhachHang (MaKhachHang, TenKhachHang, GioiTinh, NgaySinh, SoDT, SoCCCD)
VALUES
    ('KHA001', N'Nguyễn Văn A', 1, '1990-03-15', '0123456789', '123456789012'),
    ('KHA002', N'Trần Thị B', 0, '1995-07-20', '0987654321', '987654321098'),
    ('KHA003', N'Lê Minh C', 1, '1987-11-10', '0365987412', '369874123369'),
    ('KHA004', N'Phạm Thị D', 0, '2000-05-30', '0912345678', '091234567809'),
    ('KHA005', N'Nguyễn Văn E', 1, '1985-09-25', '0987654321', '987654321234'),
    ('KHA006', N'Lê Thị F', 0, '1992-12-05', '0365987412', '369874123456'),
    ('KHA007', N'Trần Văn G', 1, '1998-06-18', '0123456789', '123456789345'),
    ('KHA008', N'Hoàng Thị H', 0, '1997-04-07', '0912345678', '091234567456'),
    ('KHA009', N'Phan Văn I', 1, '1989-08-12', '0987654321', '987654321567'),
    ('KHA010', N'Mai Thị K', 0, '1994-02-28', '0365987412', '369874123678'),
    ('KHA011', N'Lê Văn L', 1, '1991-09-17', '0123456789', '123456789789'),
    ('KHA012', N'Trịnh Thị M', 0, '1996-03-22', '0987654321', '987654321890'),
    ('KHA013', N'Hoàng Văn N', 1, '1988-12-03', '0365987412', '369874123901'),
    ('KHA014', N'Mai Thị O', 0, '2001-07-11', '0912345678', '091234567012'),
    ('KHA015', N'Phan Văn P', 1, '1993-05-02', '0987654321', '987654321123'),
    ('KHA016', N'Lê Thị Q', 0, '1984-10-29', '0123456789', '123456789234'),
    ('KHA017', N'Trần Văn R', 1, '1999-08-08', '0365987412', '369874123345'),
    ('KHA018', N'Nguyễn Thị S', 0, '1990-01-14', '0912345678', '091234567456'),
    ('KHA019', N'Phạm Văn T', 1, '1986-06-26', '0987654321', '987654321567'),
    ('KHA020', N'Lê Minh U', 0, '1997-04-04', '0365987412', '369874123678');

SELECT * FROM KhachHang

CREATE TABLE ChucVu (
    MaChucVu nvarchar(10) PRIMARY KEY,
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
MaLoaiPhong nvarchar(10) primary key,
TenLoaiPhong nvarchar(10),
GiaPhong float,
SoNguoiToiDa int
)

INSERT INTO LoaiPhong(MaLoaiPhong, TenLoaiPhong, GiaPhong, SoNguoiToiDa)
VALUES
('LP001', 'VIP', 2000000.00, 5),
('LP002', 'Thuong', 500000.00, 10),
('LP003', 'Thuong', 500000.00, 10),
('LP004', 'VIP', 3000000.00, 5),
('LP005', 'VIP', 1000000.00, 5),
('LP006', 'VIP', 5000000.00, 5),
('LP007', 'Thuong', 200000.00, 10),
('LP008', 'Thuong', 600000.00, 10),
('LP009', 'VIP', 3000000.00, 5),
('LP010', 'VIP', 2000000.00, 5),
('LP011', 'Thuong', 400000.00, 10),
('LP012', 'Thuong', 600000.00, 10);
SELECT * FROM LoaiPhong

Create table NhanVien(
MaNhanVien nvarchar(10) primary key ,
MaCV nvarchar(10),
TenNV nvarchar(30) ,
GioiTinh bit ,
NgaySinh date,
SoDT nvarchar(11) , 
DiaChi nvarchar(40) ,
MatKhau nvarchar(40) ,
foreign key (MaCV) references ChucVu(MaChucVu)
)

INSERT INTO NhanVien(MaNhanVien, TenNV, GioiTinh, NgaySinh, SoDT, DiaChi, MatKhau)
VALUES
('NV001', N'Nguyen Van Dao', 1, '1980-07-14', '0723582763', '12 Nguyen Van Bao', 'adggdg'),
('NV025', N'Nguyen Thi Hanh', 0, '1970-11-09', '0826543574', '12/60 Nguyen Van Nghi', 'SGE4Dd'),
('NV033', N'Tran Chung Hieu', 1, '1993-09-05', '0926456789', '125 Le Duc Tho', 'dfgwr3'),
('NV048', N'Duong Van Hung', 1, '1996-02-12', '0823356789', '225 Ton That Thuyet', 'bferf4'),
('NV052', N'Nhat Van Tien', 1, '1998-03-13', '0823756789', '221 Le Thai To', 'aefsd2'),
('NV061', N'Bui Van Dung', 1, '1991-08-19', '0923456749', '436 Tran Duy Hung', '43fsgegf'),
('NV075', N'Hua Van Khang', 1, '1992-06-20', '0723676789', '64 Nguyen Oanh', 'agser6'),
('NV083', N'That Van Ngoc', 1, '1996-08-21', '0823456789', '69 Hoang Sa', 'adfg72'),
('NV099', N'Lo Van Hoang', 1, '1997-04-22', '0923456789', '93 Phan Van Tri', 'dzfhfg7'),
('NV101', N'Le Van Phuc', 1, '1995-03-27', '0723456789', '551 Nguyen Thai Son', 'dgehsdh90'),
('NV011', N'Vi Van Tam', 1, '1993-01-24', '0523456789', '416 Nguyen Huy Thong', '0fgb63v'),
('NV012', N'Pham Van Huy', 1, '1988-09-26', '0323456789', '31/20/8 Tran Ba Giao', 'dfg65sdfh'),
('NV013', N'Kieu Van Yen', 1, '1984-11-21', '0723456789', '81/20 Tan Son Nhat', 'zdf5xdfb'),
('NV014', N'Hoang Gia Nguyen', 1, '1987-12-18', '0923456789', '53/18 Duong Quang Ham', 'zgdf32zdgf'),
('NV015', N'Chu Van An', 1, '1991-08-15', '0323456789', '347 Quang Trung', 'zdgf7zse'),
('NV016', N'Ton Van Ngo', 1, '1992-03-12', '0723456789', '821 Xo Viet', 'zdgf6zd'),
('NV017', N'Vu Van Thuan', 1, '1993-06-11', '0923456789', '741 Nguyen Trai', 'hui76sgr'),
('NV018', N'Thong Van Ba', 1, '1995-07-17', '0823456789', '183 Tran Quoc Toan', 'zdfg5gytu');

SELECT *FROM NhanVien

Create table Phong(
MaPhong nvarchar(10) primary key,
MaLP nvarchar(10),
TenPhong nvarchar(10),
foreign key (MaLP) references LoaiPhong(MaLoaiPhong)
)

INSERT INTO Phong(MaPhong, MaLP, TenPhong)
VALUES
('P001', 'LP001', 'Phong 001'),
('P002', 'LP002', 'Phong 002'),
('P003', 'LP003', 'Phong 003'),
('P004', 'LP004', 'Phong 004'),
('P005', 'LP005', 'Phong 005'),
('P006', 'LP006', 'Phong 006'),
('P007', 'LP007', 'Phong 007'),
('P008', 'LP008', 'Phong 008'),
('P009', 'LP009', 'Phong 009'),
('P010', 'LP010', 'Phong 010'),
('P011', 'LP011', 'Phong 011'),
('P012', 'LP012', 'Phong 012');

SELECT *FROM Phong



Create table PhieuDatPhong(
MaPhieuDatPhong nvarchar(10) primary key,
MaKH nvarchar(10),
MaP nvarchar(10),
GioNhanPhong datetime,
NgayDatPhong date,
MaNV nvarchar(10),
foreign key (MaNV) references NhanVien(MaNhanVien),
foreign key (MaKH) references KhachHang(MaKhachHang)
)
Create table PhieuDichVu(
MaPhieuDichVu nvarchar(10) primary key,
MaNV nvarchar(10),
foreign key (MaNV) references NhanVien(MaNhanVien)
)
Create table HoaDon(
MaHoaDon nvarchar(10) primary key,
NgayLapHD date,
GioTraPhong datetime,
TrangThaiThanhToan bit,
MaKM nvarchar(10),
MaNV nvarchar(10),
MaKH nvarchar(10),
MaP nvarchar(10),
foreign key (MaNV) references NhanVien(MaNhanVien),
foreign key (MaKH) references KhachHang(MaKhachHang),
foreign key (MaP) references Phong(MaPhong),
foreign key (MaKM) references KhuyenMai(MaKhuyenMai)
)


Create table CT_PhieuDatPhong(
MaPDP nvarchar(10) primary key,
MaP nvarchar(10),
TrangThaiPhong nvarchar(10),
foreign key (MaPDP) references PhieuDatPhong(MaPhieuDatPhong),
foreign key (MaP) references Phong(MaPhong)
)
Create table CT_PhieuDichVu(
MaPDV nvarchar(10) primary key,
soLuong int,
MaDV nvarchar(10),
foreign key (MaDV) references DichVu(MaDichVu),
foreign key (MaPDV) references PhieuDichVu(MaPhieuDichVu)
)
Create table CT_HoaDon(
MaHD nvarchar(10) primary key,
MaP nvarchar(10),
MaPDP nvarchar(10),
MaPDV nvarchar(10),
MaDV nvarchar(10),
foreign key (MaP) references Phong(MaPhong),
foreign key (MaHD) references HoaDon(MaHoaDon),
foreign key (MaPDP) references CT_PhieuDatPhong(MaPDP),
foreign key (MaPDV) references CT_PhieuDichVu(MaPDV),
foreign key (MaDV) references DichVu(MaDichVu)
)


