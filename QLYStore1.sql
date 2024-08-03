create database QUANLYSTORE12;
go
use QUANLYSTORE12;
go

-- Tạo bảng Nhân viên (Employees)
create table NhanVien(
    MaNV varchar(50) not null primary key,
    MatKhau varchar(50) not null,
    HoTen Nvarchar(50) not null,
    VaiTro bit not null 
);

-- Tạo bảng Sản phẩm (Products)
CREATE TABLE SanPham (
    maSP varchar(50) not null PRIMARY KEY,
    tenSP VARCHAR(255) NOT NULL,
    giaTien Varchar(50) NOT NULL,
    soluonghangtrongkho INT NOT NULL
);

-- Tạo bảng Khách hàng (Customers)
CREATE TABLE KhachHang (
    maKH varchar(50) not null primary key,
    hotenKH NVARCHAR(255) NOT NULL,
    --email VARCHAR(255) UNIQUE,
	sanpham varchar(200),
    soDT VARCHAR(20),
    diaChi nvarchar(200)
);

-- Tạo bảng Đơn hàng (Orders) trước để sử dụng trong ChiTietDonHang
CREATE TABLE DonHang (
    maDH INT PRIMARY KEY,
	maKhachHang varchar(50), 
    ngayDat DATE,
    diaChiGiaoHang NVARCHAR(255),
	Trangthai Nvarchar(50),
    FOREIGN KEY (maKhachHang) REFERENCES KhachHang(maKH)
);

-- Tạo bảng Chi tiết đơn hàng (OrderDetails) sau khi tạo bảng DonHang
CREATE TABLE ChiTietDonHang (
    order_detail_id INT PRIMARY KEY not null,
    maDH INT not null,
    MaSanPham varchar(50) not null,
    soluongSP INT,
    giaTriDH Varchar(50),
    maNV varchar(50) not null,
    FOREIGN KEY (maDH) REFERENCES DonHang(maDH),
    FOREIGN KEY (MaSanPham) REFERENCES SanPham(maSP), -- Sửa đổi tên cột tham chiếu
    FOREIGN KEY (maNV) REFERENCES NhanVien(MaNV) -- Thêm khóa ngoại
);

go
-- Thêm 10 bản ghi mới cho bảng NhanVien
INSERT INTO NhanVien (MaNV, MatKhau, HoTen, VaiTro)
VALUES
    ('Admin1', 'pass123', N'Nguyen Van A', 1),
    ('NV001', 'pass345', N'Cao Thi B', 0);
    
    
-- Thêm 10 bản ghi mới cho bảng SanPham
INSERT INTO SanPham (maSP, tenSP, giaTien, soluonghangtrongkho)
VALUES
    ('SP001', N'iPhone SE', '1500', 50);

-- Thêm 10 bản ghi mới cho bảng KhachHang
INSERT INTO KhachHang (maKH, hotenKH, sanpham, soDT, diaChi)
VALUES
    ('KH001', N'Nguyen Van Khach', 'iPhone 5', '0123456789', N'123 Nguyen Du, Quan 1, TP.HCM');
    
    

-- Thêm 10 bản ghi mới cho bảng DonHang
Insert INTO DonHang (maDH, maKhachHang, ngayDat, diaChiGiaoHang, Trangthai)  --Drop table DonHang drop table ChiTietDonHang
VALUES
    (1, 'KH001', '2023-11-01', N'123 Nguyen Du, Quan 1, TP.HCM', N'Đã giao hàng');
    
    

-- Thêm 10 bản ghi mới cho bảng ChiTietDonHang
INSERT INTO ChiTietDonHang (order_detail_id, maDH, MaSanPham, soluongSP, giaTriDH, maNV)
VALUES
    (1, 1, 'SP001', 2, '30000000', 'NV001'),
    (2, 1, 'SP002', 1, '8000000', 'NV002'),
    (3, 2, 'SP003', 3, '60000000', 'NV003');
    


go
CREATE PROCEDURE ThongKeSoLuongSanPham
AS
BEGIN
    DECLARE @ketqua INT;
    
    SELECT @ketqua = COUNT(*)
    FROM SanPham;

    -- Trả về giá trị
    SELECT @ketqua AS ketqua;
END;
go
CREATE PROCEDURE ThongKeSoLuongNhanVien
AS
BEGIN
    DECLARE @ketqua INT;

    SELECT @ketqua = COUNT(*)
    FROM NhanVien;

    -- Trả về giá trị
    SELECT @ketqua AS ketqua;
END;




--thống kê số lượng đơn hàng theo năm 
-- Thống kê số lượng đơn hàng theo năm
GO
-- Thống kê số lượng đơn hàng theo năm
CREATE PROCEDURE ThongKeSoLuongDonHangTheoNam
    @Nam INT
AS
BEGIN
    SELECT COUNT(*) AS SoLuongDonHang
    FROM DonHang
    WHERE YEAR(ngayDat) = @Nam;
END;
go
--thực thi
DECLARE @Nam INT;
SET @Nam = 2022; -- Thay đổi năm theo nhu cầu của bạn

EXEC ThongKeSoLuongDonHangTheoNam @Nam;


-- Thực thi thủ tục ThongKeSoLuongSanPham
EXEC ThongKeSoLuongSanPham;

-- Thực thi thủ tục ThongKeSoLuongNhanVien
EXEC ThongKeSoLuongNhanVien;




select * From NhanVien




