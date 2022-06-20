/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import DAO.DangNhapDAO;
import Model.nhanvien;

/**
 *
 * @author Administrator
 */
public class DangNhapController {
    public static int DangNhap(String SoDienThoai, String MatKhau){
        try{
            int sdt = Integer.parseInt(SoDienThoai);
        }catch(Exception ex){
            System.out.println("Vui long nhap so dien thoai!");
            return 0;
        }
        if(SoDienThoai.equals("") == true){
            System.out.println("Vui long nhap so dien thoai!");
            return 0;
        }
        if(MatKhau.equals("") == true){
            System.out.println("Vui long nhap mat khau!");
            return 0;
        }
        int result = DangNhapDAO.checkDangNhap(SoDienThoai, MatKhau);
        if(result == 1){
            System.out.println("Dang nhap thanh cong!");
            return 1;
        }else{
            System.out.println("Dang nhap khong thanh cong!");
            return 0;
        }
    }
    public static nhanvien getNhanVien(String SoDienThoai){ 
        return DangNhapDAO.getNhanVienDAO(SoDienThoai);
    }
}
