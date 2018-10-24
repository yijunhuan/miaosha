package com.huang.utils;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5Utilts {
    public static String md5(String src) {
        return DigestUtils.md5Hex(src);
    }

    private static final String salt = "1a2b3c4d";

    public static String inputPassToFormPass(String inputPass) {
        String pass = salt.charAt(0) + salt.charAt(1) + inputPass + salt.charAt(5) + 
                salt.charAt(3);
        return md5(pass);
    }
    
    public static String formPassToDaoPass(String inputPass,String salt) {
        String pass = salt.charAt(0) + salt.charAt(1) + inputPass + salt.charAt(5) + 
                salt.charAt(3);
        return md5(pass);
    }
    
    public static void main(String[] args) throws Exception {
        System.out.println(inputPassToFormPass("123456"));
        System.out.println(formPassToDaoPass("123456", "23sd34f"));
    }

}
