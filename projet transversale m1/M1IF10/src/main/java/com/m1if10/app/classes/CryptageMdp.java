package com.m1if10.app.classes;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Password crypting Class
 */
public class CryptageMdp {

    public CryptageMdp(){
    }

    /**
     * Creates a new encrypted string using the user's password
     * @param passwordToHash: the password to encrypt
     * @return a new encrypted string
     */
    public static String encrypt(String passwordToHash) {
        String generatedPassword = null; /*Initialisation du nouveau string crypté à retourner*/
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(passwordToHash.getBytes());
            //Get the hash's bytes
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return generatedPassword;
    }
}
