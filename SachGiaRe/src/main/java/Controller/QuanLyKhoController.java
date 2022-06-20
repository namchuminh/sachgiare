/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.QuanLyKhoDAO;
import Model.chuyenmuc;
import Model.nhacungcap;
import Model.sach;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class QuanLyKhoController {
    public static int insertNhaCungCap(nhacungcap objNhaCungCap){
        if(objNhaCungCap.getTenNhaCungCap().equals("") == true){
            System.out.println("Vui long nhap ten nha cung cap!");
            return 0;
        }
        int result = QuanLyKhoDAO.insertNhaCungCapDAO(objNhaCungCap);
        if(result == 1){
            return 1;
        }else{
            return 0;
        }
    }
    
    public static List<nhacungcap> getAllNhaCungCap(){
        return QuanLyKhoDAO.getAllNhaCungCapDAO();
    }
    
    public static int insertSach(sach objSach){
        if(objSach.getTenSach().equals("") == true){
            System.out.println("Vui long nhap ten sach!");
            return 0;
        }
        
        if(objSach.getMaChuyenMuc() < 0){
            System.out.println("Vui long nhap ma chuyen muc!");
            return 0;
        }
        
        if(objSach.getMaNhaCungCap() < 0){
            System.out.println("Vui long nhap ma nha cung cap!");
            return 0;
        }
        
        if(objSach.getTacGia().equals("") == true){
            System.out.println("Vui long nhap tac gia!");
            return 0;
        }
        
        if(objSach.getGiaBan() <= 0){
            System.out.println("Vui long nhap gia ban!");
            return 0;
        }
        
        if(objSach.getSoLuong() <= 0){
            System.out.println("Vui long nhap so luong!");
            return 0;
        }
        
        int result = QuanLyKhoDAO.insertSachDAO(objSach);
        if(result == 1){
            return 1;
        }else{
            return 0;
        }
    }
    
    public static int insertChuyenMuc(chuyenmuc objChuyenMuc){
        if(objChuyenMuc.getTenChuyenMuc().equals("") == true){
            System.out.println("Vui long nhap ten chuyen muc!");
            return 0;
        }
        int result = QuanLyKhoDAO.insertChuyenMucDAO(objChuyenMuc);
        if(result == 1){
            return 1;
        }else{
            return 0;
        }
    }
    
    public static List<chuyenmuc> getAllChuyenMuc(){
        return QuanLyKhoDAO.getAllChuyenMucDAO();
    }
}
