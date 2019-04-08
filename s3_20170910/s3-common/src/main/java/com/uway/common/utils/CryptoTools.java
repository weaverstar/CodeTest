package com.uway.common.utils;

import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class CryptoTools {
	private static final byte[] DESkey = "23542336".getBytes();// 设置密钥，略去
	private static final byte[] DESIV = "61713431".getBytes() ;// 设置向量，略去
	//加密算法的参数接口，IvParameterSpec是它的一个实现
	static AlgorithmParameterSpec iv = null;
	private static Key key = null;
	
    private static CryptoTools  instance = null;
    
    
	private CryptoTools() throws Exception {
		// 设置密钥参数
		DESKeySpec keySpec = new DESKeySpec(DESkey);
		// 设置向量
		iv = new IvParameterSpec(DESIV);
		// 获得密钥工厂
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		key = keyFactory.generateSecret(keySpec);// 得到密钥对象
	}
	
	public static CryptoTools  getInstance(){
		if(instance!=null){
			return instance;
		}
		else{
			try {
				instance = new CryptoTools();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return instance;
		}
	}
	
	
   /**
    * @param data
    * @return
    * @throws 加密   图片URL不需要加密
    */
	public  String encode(String data) throws Exception {
		if(data==null ||"".equals(data) ){
			return data;
		}
		if(isImageUrl(data)){  //图片URL不需要加密
			return data;
		}
		// 得到加密对象Cipher
		Cipher enCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		// 设置工作模式为加密模式，给出密钥和向量
		enCipher.init(Cipher.ENCRYPT_MODE, key, iv);
		byte[] pasByte = enCipher.doFinal(data.getBytes("utf-8"));
		BASE64Encoder base64Encoder = new BASE64Encoder();
		return base64Encoder.encode(pasByte).replace("+", "_").replace("/", "@");
	}
	
	
	/**
    * @param data
    * @return
    * @throws 解密
    */
	public  String decode(String data) throws Exception {
		Cipher deCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		deCipher.init(Cipher.DECRYPT_MODE, key, iv);
		BASE64Decoder base64Decoder = new BASE64Decoder();
		byte[] pasByte = deCipher.doFinal(base64Decoder.decodeBuffer(data.replace("_","+").replace("@","/")));
		return new String(pasByte, "UTF-8");
	}

	private boolean isImageUrl(String url){
        if(url.endsWith(".jpg") || url.endsWith(".png") || url.endsWith(".gif") || url.endsWith(".jpeg") || url.endsWith(".bmp")
        		|| url.endsWith(".JPG") || url.endsWith(".PNG") || url.endsWith(".GIF") || url.endsWith(".JPEG") || url.endsWith(".BMP") ){
			return true;
		}
        else{
        	return false;
        }
	}
	
	public static void main(String[] args) throws Exception {
		CryptoTools tools = new CryptoTools();
		System.out.println("加密:" + tools.encode("http://jsb-online.oss-cn-beijing.aliyuncs.com/160125173708003409.pdf"));
		System.out.println("解密:" + tools.decode("sHlcT93EqQF@itdVox2hpuGS7_Xg9pghX6l2mY3ozP_sGf9MTs0b8SPFlWvQ0zKp2NjOV4xmziDO\r\niPv@RyaNCPpKI3Od9GWM"));
	}
}
