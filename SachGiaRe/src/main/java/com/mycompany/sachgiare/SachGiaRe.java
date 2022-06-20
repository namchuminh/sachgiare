/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.sachgiare;
import View.Login;
import View.Main;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author TOOL
 */
public class SachGiaRe {
    public static void main(String[] args) {
        File file = new File(System.getProperty("user.dir") + "/src/main/resources/log/NhoMatKhau.txt");
        boolean NhoMatKhau;
        String SoDienThoai;
        try {
            Scanner scan = new Scanner(file);
            NhoMatKhau = Boolean.valueOf(scan.nextLine());
            SoDienThoai = scan.nextLine();
            if(NhoMatKhau == true){
                Main main = new Main(SoDienThoai);
                main.setVisible(true);
            }else{
                Login login = new Login();
                login.setVisible(true);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SachGiaRe.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
