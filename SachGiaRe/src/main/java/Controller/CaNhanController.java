/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.nhanvien;
import DAO.CaNhanDAO;
import Model.chamcong;
import java.util.List;
/**
 *
 * @author Administrator
 */
public class CaNhanController {
    public static nhanvien getThongTinCaNhan(int MaNhanVien){
        return CaNhanDAO.getThongTinCaNhanDAO(MaNhanVien);
    }
    
    public static List<chamcong> getChamCong(int MaNhanVien){
        return CaNhanDAO.getChamCongDAO(MaNhanVien);
    }
    
    public static int insertChamCong(int MaNhanVien){
        int result = CaNhanDAO.insertChamCongDAO(MaNhanVien);
        if(result == 1){
            return 1;
        }else{
            return 0;
        }
    }
    
    public static chamcong getLastChamCong(int MaNhanVien){
        return CaNhanDAO.getLastChamCongDAO(MaNhanVien);
    }
    
    public static int updateThongTinCaNhan(nhanvien objNV){
        if(objNV.getHoTen().equals("") == true){
            System.out.println("Vui long nhap ho ten!");
            return 0;
        }
        
        if(objNV.getGioiTinh().equals("") == true){
            System.out.println("Vui long nhap gioi tinh!");
            return 0;
        }
        
        if(objNV.getQueQuan().equals("") == true){
            System.out.println("Vui long nhap que quan!");
            return 0;
        }
        
        if(objNV.getSoDienThoai().equals("") == true){
            System.out.println("Vui long nhap so dien thoai!");
            return 0;
        }
        
        if(objNV.getMatKhau().equals("") == true){
            System.out.println("Vui long nhap mat khau!");
            return 0;
        }
        
        if(objNV.getNgaySinh().equals("") == true){
            System.out.println("Vui long nhap ngay sinh!");
            return 0;
        }
        
        int result = CaNhanDAO.updateThongTinCaNhanDAO(objNV);
        if(result == 1){
            return 1;
        }else{
            return 0;
        }
    }
}
