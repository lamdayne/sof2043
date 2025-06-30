/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.cafe.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author PHUONG LAM
 */
public class PasswordUtil {

    public static String hashPassword(String passWord) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        return XStr.toHexString(md.digest(passWord.getBytes()));
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        System.out.println(hashPassword("Hello"));
    }
}
