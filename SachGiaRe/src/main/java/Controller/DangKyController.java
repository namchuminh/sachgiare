/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.DangKyDAO;
import Model.nhanvien;

/**
 *
 * @author Administrator
 */
public class DangKyController {
    public static int DangKy(nhanvien objNV){
        if(objNV.getHoTen().equals("") == true){
            System.err.println("Vui long nhap ho ten!");
            return 0;
        }
        if(objNV.getGioiTinh().equals("") == true){
            System.err.println("Vui long nhap gioi tinh!");
            return 0;
        }
        if(objNV.getNgaySinh().equals("") == true){
            System.err.println("Vui long nhap ngay sinh!");
            return 0;
        }
        if(objNV.getQueQuan().equals("") == true){
            System.err.println("Vui long nhap que quan!");
            return 0;
        }
        if(objNV.getSoDienThoai().equals("") == true){
            System.err.println("Vui long nhap so dien thoai!");
            return 0;
        }
        if(objNV.getMatKhau().equals("") == true){
            System.err.println("Vui long nhap mat khau!");
            return 0;
        }
        int result = DangKyDAO.checkDangKy(objNV);
        if(result == 1){
            System.err.println("Dang ky thanh cong!");
            return 1;
        }else{
            System.err.println("Dang ky khong thanh cong!");
            return 0;
        }
    }
}
