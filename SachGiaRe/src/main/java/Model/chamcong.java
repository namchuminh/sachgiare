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
public class chamcong {
    private int MaChamCong;	
    private int MaNhanVien;
    private String NgayChamCong;	
    private String TrangThai;

    public chamcong() {
    }

    public chamcong(int MaChamCong) {
        this.MaChamCong = MaChamCong;
    }

    public chamcong(int MaChamCong, int MaNhanVien, String NgayChamCong, String TrangThai) {
        this.MaChamCong = MaChamCong;
        this.MaNhanVien = MaNhanVien;
        this.NgayChamCong = NgayChamCong;
        this.TrangThai = TrangThai;
    }

    public int getMaChamCong() {
        return MaChamCong;
    }

    public void setMaChamCong(int MaChamCong) {
        this.MaChamCong = MaChamCong;
    }

    public int getMaNhanVien() {
        return MaNhanVien;
    }

    public void setMaNhanVien(int MaNhanVien) {
        this.MaNhanVien = MaNhanVien;
    }

    public String getNgayChamCong() {
        return NgayChamCong;
    }

    public void setNgayChamCong(String NgayChamCong) {
        this.NgayChamCong = NgayChamCong;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String TrangThai) {
        this.TrangThai = TrangThai;
    }
    
}
