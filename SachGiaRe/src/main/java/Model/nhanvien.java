/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Administrator
 */
public class nhanvien {
    private int MaNhanVien; 
    private String HoTen, GioiTinh; 
    private String NgaySinh;
    private String QueQuan,SoDienThoai, MatKhau;	
    private int Admin;	

    public nhanvien() {
    }

    public nhanvien(int MaNhanVien) {
        this.MaNhanVien = MaNhanVien;
    }

    public nhanvien(int MaNhanVien, String HoTen, String GioiTinh, String NgaySinh, String QueQuan, String SoDienThoai, String MatKhau, int Admin) {
        this.MaNhanVien = MaNhanVien;
        this.HoTen = HoTen;
        this.GioiTinh = GioiTinh;
        this.NgaySinh = NgaySinh;
        this.QueQuan = QueQuan;
        this.SoDienThoai = SoDienThoai;
        this.MatKhau = MatKhau;
        this.Admin = Admin;
    }

    public int getMaNhanVien() {
        return MaNhanVien;
    }

    public void setMaNhanVien(int MaNhanVien) {
        this.MaNhanVien = MaNhanVien;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String HoTen) {
        this.HoTen = HoTen;
    }

    public String getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(String GioiTinh) {
        this.GioiTinh = GioiTinh;
    }

    public String getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(String NgaySinh) {
        this.NgaySinh = NgaySinh;
    }

    public String getQueQuan() {
        return QueQuan;
    }

    public void setQueQuan(String QueQuan) {
        this.QueQuan = QueQuan;
    }

    public String getSoDienThoai() {
        return SoDienThoai;
    }

    public void setSoDienThoai(String SoDienThoai) {
        this.SoDienThoai = SoDienThoai;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String MatKhau) {
        this.MatKhau = MatKhau;
    }

    public int getAdmin() {
        return Admin;
    }

    public void setAdmin(int Admin) {
        this.Admin = Admin;
    }
}
