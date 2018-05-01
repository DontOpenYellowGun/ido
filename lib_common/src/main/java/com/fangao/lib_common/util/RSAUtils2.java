package com.fangao.lib_common.util;

import android.util.Base64;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

/**
 * 文件描述：公钥加密
 * <p>
 * 作者：   Created by Sven on 2017/7/21 0021.
 */
public final class RSAUtils2 {

    public static final String KEY_ALGORITHM = "RSA";

    public static byte[] decryptBASE64(String key) {
        return Base64.decode(key, Base64.NO_WRAP);
    }

    public static String encryptBASE64(byte[] bytes) {
        return Base64.encodeToString(bytes, Base64.NO_WRAP);
    }

    public static PublicKey getPublicKey(String key) throws Exception {
        byte[] keyBytes = decryptBASE64(key);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        PublicKey publicK = keyFactory.generatePublic(keySpec);
        return publicK;
    }

    public static String getPublicKeyStr(String key) throws Exception {
        PublicKey publicK = getPublicKey(key);
        return encryptBASE64(publicK.getEncoded());
    }

    /**
     * 公钥加密
     *
     * @param content
     * @param publicKey
     * @return
     * @throws Exception
     */
    public static byte[] encrypt(byte[] content, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);//java默认"RSA"="RSA/ECB/PKCS1Padding"
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(content);
    }

    public static String encryptByPublicKey(String contentStr, String publicKeyStr) {
        String text = contentStr;
        byte[] content = new byte[0];
        try {
            content = RSAUtils2.encrypt(text.getBytes("UTF-8"), RSAUtils2.getPublicKey(publicKeyStr));
        } catch (Exception e) {
            try {
                throw new Exception("加密失败！", e);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        text = RSAUtils2.encryptBASE64(content);
        return text;
    }

    /**RSA算法*/
    public static final String RSA = "RSA";
    /**加密方式，android的*/
//  public static final String TRANSFORMATION = "RSA/None/NoPadding";
    /**加密方式，标准jdk的*/
    public static final String TRANSFORMATION = "RSA/None/PKCS1Padding";
    /** 使用公钥加密 */
    public static byte[] encryptByPublicKey(byte[] data, byte[] publicKey) throws Exception {
        // 得到公钥对象
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKey);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey pubKey = keyFactory.generatePublic(keySpec);
        // 加密数据
        Cipher cp = Cipher.getInstance(TRANSFORMATION);
        cp.init(Cipher.ENCRYPT_MODE, pubKey);
        return cp.doFinal(data);
    }
}
