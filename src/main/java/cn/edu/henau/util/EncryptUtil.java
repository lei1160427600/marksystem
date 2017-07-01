package cn.edu.henau.util;

import java.security.MessageDigest;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * 采用MD5加密
 * 
 * @author zhangcd
 * @date 2016-4-29
 */
/**
 * @author xue
 *
 */
/**
 * @author xue
 *
 */
public class EncryptUtil {

	private static final String MAC_NAME = "HmacSHA1";
	private static final String ENCODING = "UTF-8";
	private static final String key = "iloveyou";

	/***
	 * MD5加码 生成32位md5码
	 */
	public static String string2MD5(String inStr) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
			return "";
		}
		char[] charArray = inStr.toCharArray();
		byte[] byteArray = new byte[charArray.length];

		for (int i = 0; i < charArray.length; i++)
			byteArray[i] = (byte) charArray[i];
		byte[] md5Bytes = md5.digest(byteArray);
		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++) {
			int val = (md5Bytes[i]) & 0xff;
			if (val < 16)
				hexValue.append("0");
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();

	}

	/***
	 * MD5加密 生成32位md5码
	 */
	public static String stringMD5(String inStr) {
		return string2MD5(string2MD5(inStr));

	}

	/**
	 * 加密解密算法
	 */
	public static String convertMD5(String inStr) {

		char[] a = inStr.toCharArray();
		for (int i = 0; i < a.length; i++) {
			a[i] = (char) (a[i] ^ 't');
		}
		String s = new String(a);
		return s;

	}

	/**
	 * HMAC-SHA1
	 * 
	 * @param encryptText
	 * @param encryptKey
	 * @return
	 * @throws Exception
	 */
	public static String HmacSHA1Encrypt(String encryptText, String encryptKey)
			throws Exception {
		byte[] data = encryptKey.getBytes(ENCODING);
		SecretKey secretKey = new SecretKeySpec(data, MAC_NAME);
		Mac mac = Mac.getInstance(MAC_NAME);
		mac.init(secretKey);

		byte[] text = encryptText.getBytes(ENCODING);
		byte[] str = mac.doFinal(text);
		// Create Hex String
		StringBuffer hexString = new StringBuffer();
		// 字节数组转换为 十六进制 数
		for (int i = 0; i < str.length; i++) {
			String shaHex = Integer.toHexString(str[i] & 0xFF);
			if (shaHex.length() < 2) {
				hexString.append(0);
			}
			hexString.append(shaHex);
		}
		return hexString.toString();
	}

	public static String convertSHA1(String instr) {
		try {
			return HmacSHA1Encrypt(instr, key);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}

	// 测试主函数
	public static void main(String args[]) throws Exception {
		// 哈希散列带秘钥加密
		String tt = convertSHA1("123456");
		System.out.println(tt);

		// MD5加密
		String s = new String("123456");
		System.out.println("原始：" + s);
		System.out.println("MD5后：" + string2MD5(s));
		System.out.println("MD5加密后：" + stringMD5(s));
	}
}