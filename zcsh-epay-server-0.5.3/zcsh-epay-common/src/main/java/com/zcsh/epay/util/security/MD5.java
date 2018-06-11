/**
 * Copyright www.hoomsun.com 红上金融信息服务（上海）有限公司
 */
package com.zcsh.epay.util.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.codec.digest.DigestUtils;
/**
 * 作者：Administrator <br>
 * 创建时间：2018年6月11日 <br>
 * 描述：
 */
public class MD5 {
	// 加的盐
		private static final String SALT = "HXWcjvQWVG1wI4FQBLZpQ3pWj48AV63d";
		
		
		// 加的盐
		private static final String PWD_SALT = "3780a2f63dbd4fd1b9d06e856dabec9c";

		public static String EncoderByMd5(String buf) {
			try {
				MessageDigest digist = MessageDigest.getInstance("MD5");
				byte[] rs = digist.digest(buf.getBytes());
				StringBuffer digestHexStr = new StringBuffer();
				for (int i = 0; i < 16; i++) {
					digestHexStr.append(byteHEX(rs[i]));
				}
				return digestHexStr.toString();
			} catch (NoSuchAlgorithmException e) {
			}
			return null;

		}

		public static void main(String args[]) {
			System.out.println(MD5.encodeByMd5AndSalt("Admin2018"));
			System.out.println(MD5.encodeByMd5AndPwdSalt("ls12345678"));
			System.out.println(MD5.EncoderByMd5("韩寒61010419520403349415991470325"));
		}

		/**
		 * 加盐的md5值。这样即使被拖库，仍然可以有效抵御彩虹表攻击
		 * 
		 * @param inbuf
		 *            需做md5的字符串
		 * @return
		 * 
		 */
		public static String encodeByMd5AndSalt(String inbuf) {
			return EncoderByMd5(EncoderByMd5(inbuf) + SALT);
		}
		
		public static String encodeByMd5AndPwdSalt(String inbuf) {
			return DigestUtils.md5Hex(DigestUtils.md5Hex(inbuf)+ PWD_SALT );
		}
		
		public static String byteHEX(byte ib) {
			char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
			char[] ob = new char[2];
			ob[0] = Digit[(ib >>> 4) & 0X0F];
			ob[1] = Digit[ib & 0X0F];
			String s = new String(ob);
			return s;
		}
}
