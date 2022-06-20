/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Date;

/**
 *
 * @author Administrator
 */
public class hoadon {
    private int MaHoaDon;
    private int MaSach;
    private int MaNhanVien;
    private String ThoiGian;
    private float DonGia;
    private int SoLuong;
    private String TrangThai;	

    public hoadon() {
    }

    public hoadon(int MaHoaDon) {
        this.MaHoaDon = MaHoaDon;
    }

    public hoadon(int MaHoaDon, int MaSach, int MaNhanVien, String ThoiGian, float DonGia, int SoLuong, String TrangThai) {
        this.MaHoaDon = MaHoaDon;
        this.MaSach = MaSach;
        this.MaNhanVien = MaNhanVien;
        this.ThoiGian = ThoiGian;
        this.DonGia = DonGia;
        this.SoLuong = SoLuong;
        this.TrangThai = TrangThai;
    }

    public int getMaHoaDon() {
        return MaHoaDon;
    }

    public void setMaHoaDon(int MaHoaDon) {
        this.MaHoaDon = MaHoaDon;
    }

    public int getMaSach() {
        return MaSach;
    }

    public void setMaSach(int MaSach) {
        this.MaSach = MaSach;
    }

    public int getMaNhanVien() {
        return MaNhanVien;
    }

    public void setMaNhanVien(int MaNhanVien) {
        this.MaNhanVien = MaNhanVien;
    }

    public String getThoiGian() {
        return ThoiGian;
    }

    public void setThoiGian(String ThoiGian) {
        this.ThoiGian = ThoiGian;
    }

    public float getDonGia() {
        return DonGia;
    }

    public void setDonGia(float DonGia) {
        this.DonGia = DonGia;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String TrangThai) {
        this.TrangThai = TrangThai;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }
}
