/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.nhanvien;
import java.util.List;
import DAO.QuanLyNhanVienDAO;
import Model.chamcong;

/**
 *
 * @author Administrator
 */
public class QuanLyNhanVienController {
    public static List<nhanvien> getAllNhanVien(){
        return QuanLyNhanVienDAO.getAllNhanVienDAO();
    }
    
    public static List<chamcong> getChamCong(){
        return QuanLyNhanVienDAO.getChamCongDAO();
    }
    
    public static int insertNhanVien(nhanvien objNV){
        int result = QuanLyNhanVienDAO.insertNhanVienDAO(objNV);
        if(result == 1){
            return 1;
        }else{
            return 0;
        }
    }
    
    public static int updateNhanVien(nhanvien objNV){
        int result = QuanLyNhanVienDAO.updateNhanVienDAO(objNV);
        if(result == 1){
            return 1;
        }else{
            return 0;
        }
    }
    
    public static int deleteNhanVien(int MaNhanVien){
        int result = QuanLyNhanVienDAO.deleteNhanVienDAO(MaNhanVien);
        if(result == 1){
            return 1;
        }else{
            return 0;
        }
    }
}
