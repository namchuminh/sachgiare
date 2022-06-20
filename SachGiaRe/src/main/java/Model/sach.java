/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Administrator
 */
public class sach {
    private int MaSach;
    private String TenSach;
    private int MaChuyenMuc,MaNhaCungCap;
    private String TacGia;
    private int SoLuong;
    private float GiaBan;

    public sach() {
    }

    public sach(int MaSach) {
        this.MaSach = MaSach;
    }

    public sach(int MaSach, String TenSach, int MaChuyenMuc, int MaNhaCungCap, String TacGia, int SoLuong, float GiaBan) {
        this.MaSach = MaSach;
        this.TenSach = TenSach;
        this.MaChuyenMuc = MaChuyenMuc;
        this.MaNhaCungCap = MaNhaCungCap;
        this.TacGia = TacGia;
        this.SoLuong = SoLuong;
        this.GiaBan = GiaBan;
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

    public int getMaChuyenMuc() {
        return MaChuyenMuc;
    }

    public void setMaChuyenMuc(int MaChuyenMuc) {
        this.MaChuyenMuc = MaChuyenMuc;
    }

    public int getMaNhaCungCap() {
        return MaNhaCungCap;
    }

    public void setMaNhaCungCap(int MaNhaCungCap) {
        this.MaNhaCungCap = MaNhaCungCap;
    }

    public String getTacGia() {
        return TacGia;
    }

    public void setTacGia(String TacGia) {
        this.TacGia = TacGia;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    public float getGiaBan() {
        return GiaBan;
    }

    public void setGiaBan(float GiaBan) {
        this.GiaBan = GiaBan;
    }

}
