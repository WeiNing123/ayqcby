package com.project.ayqcby.tools;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base32;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class CryptoUtil {

    private static final String DEFAULT_SECRET_KEY1 = "?:P)(OL><KI*&UJMNHY^%TGBVFR$#EDCXSW@!QAZ";
    private static final String DEFAULT_SECRET_KEY2 = "1qaz2wsx3edc4rfv5tgb6yhn7ujm8ik,9ol.0p;/";
    private static final String DEFAULT_SECRET_KEY3 = "!QAZ@WSX#EDC$RFV%TGB^YHN&UJM*IK<(OL>)P:?";
    private static final String DEFAULT_SECRET_KEY4 = "1qaz@WSX3edc$RFV5tgb^YHN7ujm*IK<9ol.)P:?";
    private static final String DEFAULT_SECRET_KEY5 = "!QAZ2wsx#EDC4rfv%TGB6yhn&UJM8ik,(OL>0p;/";
    private static final String DEFAULT_SECRET_KEY6 = "1qaz2wsx3edc4rfv5tgb^YHN&UJM*IK<(OL>)P:?";
    private static final String DEFAULT_SECRET_KEY = DEFAULT_SECRET_KEY1;
    private static final String SALT = "hnxrxxjs";
    private static final String DES = "DES";
    private static final Base32 base32 = new Base32();
    private static Key DEFAULT_KEY = null;

    static {
        DEFAULT_KEY = obtainKey(DEFAULT_SECRET_KEY);
    }

    /**
     * 获得key
     **/
    private static Key obtainKey(String key) {
        if (key == null) {
            return DEFAULT_KEY;
        }


        KeyGenerator generator = null;
        try {
            generator = KeyGenerator.getInstance(DES);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        SecureRandom random = null;
        try {
            random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(key.getBytes());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        generator.init(random);
        Key key1 = generator.generateKey();
        generator = null;
        return key1;
    }

    /**
     * 加密<br>
     * String明文输入,String密文输出
     */
    private static String encode(String str) {
        return encode64(null, str);
    }

    /**
     * 加密<br>
     * String明文输入,String密文输出
     */
    private static String encode64(String key, String str) {
        return Base64.encodeBase64URLSafeString(obtainEncode(key, str.getBytes()));
    }

    /**
     * 加密<br>
     * String明文输入,String密文输出
     */
    private static String encode32(String key, String str) {
        return base32.encodeAsString(obtainEncode(key, str.getBytes())).replaceAll("=", "");
    }

    /**
     * 加密<br>
     * String明文输入,String密文输出
     */
    private static String encode16(String key, String str) {
        return Hex.encodeHexString(obtainEncode(key, str.getBytes()));
    }

    /**
     * 解密<br>
     * 以String密文输入,String明文输出
     */
    private static String decode(String str) {
        return decode64(null, str);
    }

    /**
     * 解密<br>
     * 以String密文输入,String明文输出
     */
    private static String decode64(String key, String str) {
        return new String(obtainDecode(key, Base64.decodeBase64(str)));
    }

    /**
     * 解密<br>
     * 以String密文输入,String明文输出
     */
    private static String decode32(String key, String str) {
        return new String(obtainDecode(key, base32.decode(str)));
    }

    /**
     * 解密<br>
     * 以String密文输入,String明文输出
     */
    private static String decode16(String key, String str) {
        try {
            return new String(obtainDecode(key, Hex.decodeHex(str.toCharArray())));
        } catch (DecoderException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 加密<br>
     * 以byte[]明文输入,byte[]密文输出
     */
    private static byte[] obtainEncode(String key, byte[] str) {
        byte[] byteFina = null;
        Cipher cipher;
        try {
            Key key1 = obtainKey(key);
            cipher = Cipher.getInstance(DES);
            cipher.init(Cipher.ENCRYPT_MODE, key1);
            byteFina = cipher.doFinal(str);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cipher = null;
        }
        return byteFina;
    }

    /**
     * 解密<br>
     * 以byte[]密文输入,以byte[]明文输出
     */
    private static byte[] obtainDecode(String key, byte[] str) {
        Cipher cipher;
        byte[] byteFina = null;
        try {
            Key key1 = obtainKey(key);
            cipher = Cipher.getInstance(DES);
            cipher.init(Cipher.DECRYPT_MODE, key1);
            byteFina = cipher.doFinal(str);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cipher = null;
        }
        return byteFina;
    }

    public static String encodeSrc(String src) {
        String m = encode64(DEFAULT_SECRET_KEY2 + SALT, src);
        String saltm = SALT + ";" + m;
        String result = encode32(DEFAULT_SECRET_KEY3, saltm);
        return result;

    }

    public static String decodeTarget(String target) {
        String n = decode32(DEFAULT_SECRET_KEY3, target);
        String key = n.split(";")[0];
        String m = n.split(";")[1];
        String result = decode64(DEFAULT_SECRET_KEY2 + key, m);
        return result;
    }

    public static void main(String[] args) {
        String x = "admin&@&1598334855";
        String y = encodeSrc(x);
        System.out.println(y);
        System.out.println(decodeTarget(y));
        y = encode64(SALT, x);
        System.out.println(y);
        System.out.println(decode64(SALT, y));
    }
}
