package com.li.blog.util;

import org.apache.shiro.crypto.hash.Md5Hash;


/**
 * 密码加密的工具类
 */
public class CryptographyUtil {

	/**
	 * md5加密,管理员加密
	 */
	public static String md5Manager(String str,String salt){
		return new Md5Hash(str, salt, 1024).toString();
	}
	
	public static void main(String[] args) {
		System.out.println(md5Manager("1","1"));
	}
}
