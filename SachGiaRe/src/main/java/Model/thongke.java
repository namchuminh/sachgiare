package Model;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Administrator
 */
public class thongke {
    private int MaSach;
    private String TenSach;
    private String ThoiGian;
    private String HoTen;
    private int SoLuong;
    private String DonGia;
    private String TrangThai;

    public thongke() {
    }

    public thongke(int MaSach) {
        this.MaSach = MaSach;
    }

    public thongke(int MaSach, String TenSach, String ThoiGian, String HoTen, int SoLuong, String DonGia, String TrangThai) {
        this.MaSach = MaSach;
        this.TenSach = TenSach;
        this.ThoiGian = ThoiGian;
        this.HoTen = HoTen;
        this.SoLuong = SoLuong;
        this.DonGia = DonGia;
        this.TrangThai = TrangThai;
    }

    public int getMaSach() {
        return MaSach;
    }

    public void setMaSach(int MaSach) {
        this.MaSach = MaSach;
    }

    public String getTenSach() {
        return TenSach;
    }

    public void setTenSach(String TenSach) {
        this.TenSach = TenSach;
    }

    public String getThoiGian() {
        return ThoiGian;
    }

    public void setThoiGian(String ThoiGian) {
        this.ThoiGian = ThoiGian;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String HoTen) {
        this.HoTen = HoTen;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    public String getDonGia() {
        return DonGia;
    }

    public void setDonGia(String DonGia) {
        this.DonGia = DonGia;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String TrangThai) {
        this.TrangThai = TrangThai;
    }
    
    
}
