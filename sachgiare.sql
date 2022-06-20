-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th6 20, 2022 lúc 09:03 AM
-- Phiên bản máy phục vụ: 10.4.22-MariaDB
-- Phiên bản PHP: 7.4.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `sachgiare`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chamcong`
--

CREATE TABLE `chamcong` (
  `MaChamCong` int(11) NOT NULL,
  `MaNhanVien` int(11) NOT NULL,
  `NgayChamCong` date NOT NULL DEFAULT current_timestamp(),
  `TrangThai` varchar(255) COLLATE utf8_unicode_ci NOT NULL DEFAULT 'Đã chấm công'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `chamcong`
--

INSERT INTO `chamcong` (`MaChamCong`, `MaNhanVien`, `NgayChamCong`, `TrangThai`) VALUES
(1, 1, '2022-06-16', 'Đã chấm công'),
(2, 1, '2022-06-16', 'Đã chấm công'),
(3, 1, '2022-06-16', 'Đã chấm công'),
(4, 7, '2022-06-16', 'Đã chấm công'),
(5, 7, '2022-06-17', 'Đã chấm công'),
(6, 1, '2022-06-18', 'Đã chấm công'),
(7, 2, '2022-06-18', 'Đã chấm công'),
(8, 3, '2022-06-18', 'Đã chấm công'),
(9, 7, '2022-06-18', 'Đã chấm công'),
(10, 6, '2022-06-18', 'Đã chấm công'),
(11, 4, '2022-06-18', 'Đã chấm công'),
(12, 8, '2022-06-18', 'Đã chấm công'),
(13, 10, '2022-06-19', 'Đã chấm công'),
(14, 11, '2022-06-19', 'Đã chấm công'),
(15, 1, '2022-06-20', 'Đã chấm công');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chuyenmuc`
--

CREATE TABLE `chuyenmuc` (
  `MaChuyenMuc` int(11) NOT NULL,
  `TenChuyenMuc` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `chuyenmuc`
--

INSERT INTO `chuyenmuc` (`MaChuyenMuc`, `TenChuyenMuc`) VALUES
(1, 'Giáo Khoa'),
(2, 'Ngôn Tình'),
(3, 'Truyện Tranh'),
(4, 'Tiểu Thuyết'),
(5, 'Học Tập'),
(6, 'Giáo Trình');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `hoadon`
--

CREATE TABLE `hoadon` (
  `MaHoaDon` int(11) NOT NULL,
  `MaSach` int(11) NOT NULL,
  `MaNhanVien` int(11) NOT NULL,
  `ThoiGian` datetime NOT NULL DEFAULT current_timestamp(),
  `DonGia` decimal(19,3) NOT NULL,
  `SoLuong` int(11) NOT NULL,
  `TrangThai` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `hoadon`
--

INSERT INTO `hoadon` (`MaHoaDon`, `MaSach`, `MaNhanVien`, `ThoiGian`, `DonGia`, `SoLuong`, `TrangThai`) VALUES
(1, 4, 1, '2022-05-11 20:56:16', '265.000', 10, 'Đã thanh toán'),
(2, 3, 1, '2022-06-11 21:31:17', '285.000', 10, 'Đã thanh toán'),
(3, 5, 1, '2022-06-11 21:57:29', '612.000', 15, 'Đã thanh toán'),
(4, 5, 1, '2022-06-11 22:00:31', '408.000', 10, 'Đã thanh toán'),
(5, 5, 1, '2022-06-11 22:04:14', '163.200', 4, 'Đã thanh toán'),
(7, 7, 1, '2022-06-15 00:31:42', '600.000', 30, 'Đã thanh toán'),
(8, 9, 1, '2022-06-17 00:42:24', '950.000', 2, 'Đã thanh toán'),
(9, 9, 1, '2022-06-19 00:42:52', '2375.000', 5, 'Đã thanh toán'),
(10, 9, 1, '2022-06-19 02:14:54', '3800.000', 8, 'Đã thanh toán'),
(11, 9, 1, '2022-06-20 02:16:37', '4750.000', 10, 'Đã thanh toán'),
(12, 9, 1, '2022-06-20 02:20:54', '3800.000', 8, 'Đã thanh toán'),
(13, 3, 1, '2022-06-20 02:23:14', '285.000', 10, 'Đã thanh toán'),
(14, 5, 10, '2022-06-20 13:43:39', '204.000', 5, 'Đã thanh toán'),
(15, 6, 1, '2022-06-20 13:47:25', '150.000', 5, 'Đã thanh toán');

--
-- Bẫy `hoadon`
--
DELIMITER $$
CREATE TRIGGER `trigger_slSach` AFTER INSERT ON `hoadon` FOR EACH ROW UPDATE sach SET SoLuong = SoLuong - NEW.SoLuong
	WHERE MaSach = NEW.MaSach
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nhacungcap`
--

CREATE TABLE `nhacungcap` (
  `MaNhaCungCap` int(11) NOT NULL,
  `TenNhaCungCap` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `TrangThai` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `nhacungcap`
--

INSERT INTO `nhacungcap` (`MaNhaCungCap`, `TenNhaCungCap`, `TrangThai`) VALUES
(1, 'Nhà Xuất Bản Giáo Dục Việt Nam', 'Đang cung cấp'),
(2, 'Nhà Xuất Bản Đại Học Bách Khoa - HN', 'Đang cung cấp'),
(3, 'Nhà Xuất Bản Kim Đồng', 'Đang cung cấp'),
(4, 'Nhà Xuất Bản Đại Nam', 'Đang cung cấp'),
(5, 'Nhà Xuất Bản Hồng Hà', 'Đang cung cấp'),
(6, 'Nhà Xuất Bản Đông Á', 'Đang cung cấp');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nhanvien`
--

CREATE TABLE `nhanvien` (
  `MaNhanVien` int(11) NOT NULL,
  `HoTen` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `GioiTinh` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `NgaySinh` date NOT NULL,
  `QueQuan` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `SoDienThoai` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `MatKhau` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `Admin` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `nhanvien`
--

INSERT INTO `nhanvien` (`MaNhanVien`, `HoTen`, `GioiTinh`, `NgaySinh`, `QueQuan`, `SoDienThoai`, `MatKhau`, `Admin`) VALUES
(1, 'Chu Minh Nam', 'Nam', '2002-02-07', 'Thái Hòa, Ba Vì, Hà Nội', '0379962045', 'chuminhnam', 1),
(2, 'Phùng Thái Sơn', 'Nam', '2002-02-25', 'Thái Hòa, Ba Vì, Hà Nội', '0888999999', 'phungthaison', 0),
(3, 'Nguyễn Trí Thành', 'Nam', '2002-08-09', 'Ngõ 401, Xuân Đỉnh, Bắc Từ Liêm', '0973767246', 'nguyentrithanh', 0),
(4, 'Nguyễn Hoàng Tú', 'Nam', '2002-10-08', 'Ba Vì, Hà Nội', '0555666777', 'nguyenhoangtu', 0),
(10, 'Hoàng Tiến Sơn', 'Nam', '2002-09-08', 'Vạn Thắng, Thái Hòa, Ba Vì', '0813690756', 'hoangtienson', 0),
(11, 'Trần Ngọc Hà', 'Nữ', '2002-11-14', 'Cổ Đô, Ba Vì, Hà Nội', '0388315968', 'tranngocha', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `sach`
--

CREATE TABLE `sach` (
  `MaSach` int(11) NOT NULL,
  `TenSach` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `MaChuyenMuc` int(11) NOT NULL,
  `MaNhaCungCap` int(11) NOT NULL,
  `TacGia` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `GiaBan` decimal(10,3) NOT NULL,
  `SoLuong` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `sach`
--

INSERT INTO `sach` (`MaSach`, `TenSach`, `MaChuyenMuc`, `MaNhaCungCap`, `TacGia`, `GiaBan`, `SoLuong`) VALUES
(3, 'Đại Số 12', 1, 1, 'Trần Văn Hạo', '28.500', 30),
(4, 'Hóa Học 12', 1, 1, 'Nguyễn Xuân Trường', '26.500', 33),
(5, 'Nhập Môn Lập Trình C', 5, 2, 'Hoàng Xuân Kiên', '40.800', 30),
(6, 'Vua  Hải Tặc ', 3, 3, 'Oda Eiichiro', '30.000', 30),
(7, 'Bảy Viên Ngọc Rồng', 3, 3, 'Toriyama Akira', '20.000', 33),
(8, 'Thám Tử Lừng Danh Conan ', 3, 3, 'Aoyama Gōshō', '50.500', 84),
(9, 'Machine Learning Cơ Bản', 5, 2, 'Vũ Hữu Tiệp', '475.000', 45);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `chamcong`
--
ALTER TABLE `chamcong`
  ADD PRIMARY KEY (`MaChamCong`),
  ADD KEY `MaNhanVien` (`MaNhanVien`);

--
-- Chỉ mục cho bảng `chuyenmuc`
--
ALTER TABLE `chuyenmuc`
  ADD PRIMARY KEY (`MaChuyenMuc`);

--
-- Chỉ mục cho bảng `hoadon`
--
ALTER TABLE `hoadon`
  ADD PRIMARY KEY (`MaHoaDon`),
  ADD KEY `MaSach` (`MaSach`,`MaNhanVien`),
  ADD KEY `MaNhanVien` (`MaNhanVien`);

--
-- Chỉ mục cho bảng `nhacungcap`
--
ALTER TABLE `nhacungcap`
  ADD PRIMARY KEY (`MaNhaCungCap`);

--
-- Chỉ mục cho bảng `nhanvien`
--
ALTER TABLE `nhanvien`
  ADD PRIMARY KEY (`MaNhanVien`);

--
-- Chỉ mục cho bảng `sach`
--
ALTER TABLE `sach`
  ADD PRIMARY KEY (`MaSach`),
  ADD KEY `MaChuyenMuc` (`MaChuyenMuc`,`MaNhaCungCap`),
  ADD KEY `MaNhaCungCap` (`MaNhaCungCap`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `chamcong`
--
ALTER TABLE `chamcong`
  MODIFY `MaChamCong` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT cho bảng `chuyenmuc`
--
ALTER TABLE `chuyenmuc`
  MODIFY `MaChuyenMuc` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT cho bảng `hoadon`
--
ALTER TABLE `hoadon`
  MODIFY `MaHoaDon` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT cho bảng `nhacungcap`
--
ALTER TABLE `nhacungcap`
  MODIFY `MaNhaCungCap` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT cho bảng `nhanvien`
--
ALTER TABLE `nhanvien`
  MODIFY `MaNhanVien` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT cho bảng `sach`
--
ALTER TABLE `sach`
  MODIFY `MaSach` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
