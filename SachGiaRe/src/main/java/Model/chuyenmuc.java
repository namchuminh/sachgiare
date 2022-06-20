/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Administrator
 */
public class chuyenmuc {
    private int MaChuyenMuc; 
    private String TenChuyenMuc;
    
    public chuyenmuc() {
    }

    public chuyenmuc(int MaChuyenMuc) {
        this.MaChuyenMuc = MaChuyenMuc;
    }

    public chuyenmuc(int MaChuyenMuc, String TenChuyenMuc, int SoLuong) {
        this.MaChuyenMuc = MaChuyenMuc;
        this.TenChuyenMuc = TenChuyenMuc;
    }

    public int getMaChuyenMuc() {
        return MaChuyenMuc;
    }

    public void setMaChuyenMuc(int MaChuyenMuc) {
        this.MaChuyenMuc = MaChuyenMuc;
    }

    public String getTenChuyenMuc() {
        return TenChuyenMuc;
    }

    public void setTenChuyenMuc(String TenChuyenMuc) {
        this.TenChuyenMuc = TenChuyenMuc;
    }

}
