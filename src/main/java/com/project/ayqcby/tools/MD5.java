package com.project.ayqcby.tools;

import net.iharder.Base64;

import java.security.MessageDigest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MD5 {

	/**
	 * base64加MD5加密
	 * @param pass
	 * @return
	 * @throws Exception
	 */
	public static String BASE64AndMD5(String pass) throws Exception{
		if(null == pass){
			return "";
		}else{
			//确定计算方法
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			//加密后的字符串
			@SuppressWarnings("static-access")
			byte[] buf= Base64.encodeBytesToBytes(md5.digest(pass.getBytes("utf-8")));

			String newpass = new String(buf);
			return newpass;
		}
	}
	public static void main(String[] args) throws Exception {
		System.out.println(BASE64AndMD5("a"));
		System.out.println(Md5("006688"));
	}
	/**
	 * MD5加密
	 * @param plainText
	 * @return
	 * @throws Exception
	 */
	public static String Md5(String plainText) throws Exception{
		if(null == plainText){
			return "";
		}else{
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] bytes = md.digest(plainText.getBytes("utf-8"));

			final char[] HEX_DIGITS = "0123456789abcdef".toCharArray();
		    StringBuilder ret = new StringBuilder(bytes.length * 2);
		    for (int i=0; i<bytes.length; i++) {
		        ret.append(HEX_DIGITS[(bytes[i] >> 4) & 0x0f]);
		        ret.append(HEX_DIGITS[bytes[i] & 0x0f]);
		    }
		    return ret.toString();
		}
	}
	/**
	 * 自定义解密
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public static String jieMi(String str) {
		if(null == str){
			return "";
		}else{
			char[]arr = str.toCharArray();
			StringBuffer buf = new StringBuffer();
			for (int i = 0; i < arr.length; i++) {
				if(i%2 == 0){
					buf.append(arr[i]);
				}
			}
			char[]arr1 = buf.toString().toCharArray();
			StringBuffer buf1 = new StringBuffer();
			for (int i = (arr1.length-1); i >= 0; i--) {
				buf1.append(arr1[i]);
			}
			char[]arr2 = buf1.toString().toCharArray();
			StringBuffer buf2 = new StringBuffer();
			for (int i = 0; i < arr2.length; i++) {
				if(i%2 == 0){
					buf2.append(arr2[i]);
				}
			}
			return buf2.toString();
		}
	}
	/**
	 * 自定义解密  正则表达式
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public static String jieMi1(String str) throws Exception {
		if(null == str){
			return "";
		}else{
			String regEx="[!-@:&]";
			Pattern p = Pattern.compile(regEx);
	        Matcher m = p.matcher(str);
	        return m.replaceAll("").trim();
		}
	}
}
