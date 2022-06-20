/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.QuanLyBanHangDAO;
import Model.hoadon;
import Model.sach;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class QuanLyBanHangController {
    public static int insertHoaDon(int MaSach, int MaNhanVien, float DonGia, int SoLuong, String TrangThai){
        if(SoLuong <= 0){
            System.err.println("Vui long nhap so luong!");
            return 0;
        }
        
        if(DonGia <= 0){
            System.err.println("Vui long nhap don gia!");
            return 0;
        }
        int result = QuanLyBanHangDAO.insertHoaDonDAO(MaSach, MaNhanVien, DonGia, SoLuong, TrangThai);
        if(result == 1){
            return 1;
        }else{
            return 0;
        }
    }
    
    public static List<hoadon> getAllHoaDon(){
        return QuanLyBanHangDAO.getAllHoaDonDAO();
    }
    
    public static List<sach> getSachByTen(String TenSach){
        return QuanLyBanHangDAO.getSachByTenDAO(TenSach);
    }
}
