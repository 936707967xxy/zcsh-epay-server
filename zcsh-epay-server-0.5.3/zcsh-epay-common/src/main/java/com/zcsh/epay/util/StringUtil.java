/**
 * Copyright www.hoomsun.com ���Ͻ�����Ϣ�����Ϻ������޹�˾
 */
package com.zcsh.epay.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.Character.UnicodeBlock;
import java.sql.Clob;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import com.zcsh.epay.exceptions.ZcshBusinessException;
import com.zcsh.epay.log.LogCvt;

/**
 * ���ߣ�Administrator <br>
 * ����ʱ�䣺2018��6��4�� <br>
 * ������
 */
public final class StringUtil {

	
	/**
	 * 
	 */
	public static final String COMMA = ",";
	/**
	 * 
	 */
	public static final String SINGLE_QUOTE = "'";
	/**
	 * 
	 */
	public static final String START = "*";
	/**
	 * 
	 */
	public static final String AT = "@";

	/**
	 * �зָ���
	 */
	public static final String LINE_SEPARATOR = System.getProperty("line.separator");

	private StringUtil() {
	}
	
	public static boolean isNull(String s) {
		return StringUtils.isEmpty(s);
	}

	/**
	 * ����Ƿ�Ϊ�ֻ�����(13��ͷ��11λ�����ִ�)
	 * 
	 * @param sMobile
	 *            �������ִ�
	 * @return
	 */
	public static boolean isMobileNumber(String sMobile) {
		if (sMobile == null) {
			return false;
		}
		boolean flag = false;
		flag = java.util.regex.Pattern.matches(
				"( *13[0-9]\\d{8} *)|( *15\\d{9} *)|( *18\\d{9} *)", sMobile);
		return flag;
	}

	/**
	 * ����Ƿ�Ϊ�ƶ��ֻ�����(13��ͷ��11λ�����ִ�)
	 * 
	 * @param sMobile
	 *            �������ִ�
	 * @return
	 */
	public static boolean isGmccMobile(String sMobile) {
		if (sMobile == null){
			return false;
		}
		boolean flag = false;
		flag = java.util.regex.Pattern.matches(
				"( *13[4-9]\\d{8} *)|( *15[8-9]\\d{8} *)|( *18[8]\\d{8} *)",
				sMobile);
		return flag;
	}

	/**
	 * ����Ƿ�Ϊ��ͨ�ֻ�����(13��ͷ��11λ�����ִ�)
	 * 
	 * @param sMobile
	 *            �������ִ�
	 * @return
	 */
	public static boolean isUnicomMobile(String sMobile) {
		if (sMobile == null) {
			return false;
		}
		return java.util.regex.Pattern.matches(
				"( *13[0-3]\\d{8} *)|( *153\\d{8} *)", sMobile);
	}

	/**
	 * ����Ƿ�Ϊ�����ַ���
	 * 
	 * @param �������ִ�
	 * @return
	 */
	public static boolean isNumber(String str) {
		if (str == null) {
			return false;
		}
		return java.util.regex.Pattern.matches(" *\\d+ *", str);
	}

	/**
	 * �ַ��ֲ𷽷�������String.split()
	 * 
	 * @param str
	 * @param div
	 * @return
	 */
	public static String[] splitByChar(String str, char div) {
		if (str == null) {
			return null;
		} else if (str.indexOf(div) == -1) {
			return new String[] { str };
		} else {
			java.util.Collection<String> values = new java.util.ArrayList<String>();
			int sBeginNo = 0;
			int sEndNo = str.indexOf(div);
			while (sEndNo != -1) {
				values.add(str.substring(sBeginNo, sEndNo));
				sBeginNo = sEndNo + 1;
				sEndNo = str.indexOf(div, sBeginNo);
			}
			if (sBeginNo < str.length()) {
				values.add(str.substring(sBeginNo));
			}
			String[] retArray = new String[values.size()];
			values.toArray(retArray);
			return retArray;
		}
	}

	/**
	 * ʹ��������ʽ���ַ����滻
	 * 
	 * @param src
	 * @param patternStr
	 *            ������ʽ�ı��滻�ַ���
	 * @param replacement
	 *            �滻���ִ�
	 * @return
	 */
	public static String regReplace(String src, String patternStr,
			String replacement) {
		// System.out.println("�滻��[" + src + "],[" + patternStr + "],[" +
		// replacement + "]");
		if (src == null || patternStr == null || replacement == null) {
			return src;
		}
		Pattern pattern = Pattern.compile(patternStr);
		Matcher matcher = pattern.matcher(src);
		return matcher.replaceAll(replacement);
	}

	/**
	 * �ַ����滻����������ʽ��
	 * 
	 * @param src
	 * @param seachWord
	 *            ���滻�ַ���
	 * @param replacement
	 *            �滻���ִ�
	 * @return
	 */
	public static String strReplace(String src, String seachWord,
			String replacement) {
		if (src == null || seachWord == null || src.indexOf(seachWord) == -1) {
			return src;
		}
		StringBuffer temp = new StringBuffer(src.length() + 100);
		int fromPos = 0;
		int findPos = -1;
		int wordLen = seachWord.length();
		while ((findPos = src.indexOf(seachWord, fromPos)) != -1) {
			temp.append(src.substring(fromPos, findPos)).append(replacement);
			fromPos = findPos + wordLen;
		}
		if (fromPos < src.length()) {
			temp.append(src.substring(fromPos));
		}
		return temp.toString();
	}

	/**
	 * ʹ��������ʽ���ַ����滻.�滻{0},{1},...
	 * 
	 * @param src
	 *            String
	 * @param replacements
	 *            String[]
	 * @return String
	 */
	public static String regReplace(String src, String[] replacements) {
		if (src == null || replacements == null || replacements.length == 0) {
			return src;
		}
		int len = replacements.length;
		String result = src;
		for (int i = 0; i < len; i++) {
			String irepValue = replacements[i];
			if (irepValue == null) {
				irepValue = "null";
			}
			result = regReplace(result, "#" + i + '#', irepValue);
		}

		return result;
	}

	/**
	 * �ַ����滻����������ʽ��
	 * 
	 * @param src
	 * @param replacement
	 * @return
	 */
	public static String strReplace(String src, String[] replacements) {
		if (src == null || replacements == null || replacements.length == 0) {
			return src;
		}
		int len = replacements.length;
		String result = src;
		for (int i = 0; i < len; i++) {
			String irepValue = replacements[i];
			if (irepValue == null) {
				irepValue = "null";
			}
			result = strReplace(result, "#" + i + '#', irepValue);
		}

		return result;
	}

	/**
	 * ȡָ�����ȵ��ַ���,λ��������ǰ�油0����λ����������ֻȡ��lenλ
	 * 
	 * @param iStr
	 *            ��ʼ����
	 * @param len
	 *            ����
	 * @return
	 */
	public static String fillZeroStr(int iStr, int len) {
		if (len <= 0) {
			return "";
		}
		String str = String.valueOf(iStr);
		int slen = String.valueOf(iStr).length();
		if (len < slen) {
			return str.substring(slen - len);
		}

		StringBuffer temp = new StringBuffer(len);
		for (int i = slen; i < len; i++) {
			temp.append('0');
		}
		temp.append(iStr);
		return temp.toString();
	}

	/**
	 * �ص��ַ����еĿո�
	 */
	public static String cutSpace(String src) {
		if (src == null || src.length() == 0) {
			return src;
		}
		// Ӣ�Ŀո�
		String temp = regReplace(src, " ", "");
		// ���Ŀո�
		temp = regReplace(temp, "��", "");
		return temp;
	}

	/**
	 * ��ȡ�ո�
	 */
	public static String trimAll(String src) {
		if (src == null || "".equals(src)) {
			return src;
		}

		String temp = regReplace(src, " ", "");
		temp = regReplace(temp, " ", "");
		return temp;
	}
	
	

	/**
	 * �ṩ��ȷ��С��λ�������봦��
	 * 
	 * @param v
	 *            ��Ҫ������������� scale С���������λ.������Ĭ��ȡ2λС����
	 * @return ���������Ľ��
	 */
	public static double round(double v) {
		java.math.BigDecimal b = new java.math.BigDecimal(Double.toString(v));
		java.math.BigDecimal one = new java.math.BigDecimal("1");
		return (b.divide(one, 2, java.math.BigDecimal.ROUND_HALF_UP)).doubleValue();
	}

	public static boolean isEmail(String email) {
		boolean retval = false;
		String regex = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
		retval = email.matches(regex);
		return retval;
	}

	/**
	 * ת��
	 * 
	 * @param str
	 *            String
	 * @param defValue
	 *            int
	 * @return float
	 */
	public static int parseInt(String str, int defValue) {
		if (str == null || "".equals(str.trim())) {
			return defValue;
		}

		str = str.trim();
		try {
			return Integer.parseInt(str);
		} catch (NumberFormatException ex) {
			return defValue;
		}
	}

	public static float parseFloat(String str, float defValue) {
		if (str == null || "".equals(str.trim())) {
			return defValue;
		}

		str = str.trim();
		try {
			return Float.parseFloat(str);
		} catch (NumberFormatException ex) {
			return defValue;
		}
	}

	public static String toUTFbaString(String s) {
		if (s == null) {
			return null;
		}
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c >= 0 && c <= 255) {
				sb.append(c);
			} else {
				sb.append(parseByteType(c));
				/*byte[] b;
				try {
					b = Character.toString(c).getBytes("UTF-8");
				} catch (Exception ex) {
					b = new byte[0];
				}
				for (int j = 0; j < b.length; j++) {
					int k = b[j];
					if (k < 0) {
						k += 256;
					}
					sb.append("%" + Integer.toHexString(k).toUpperCase());
				}*/
			}
		}
		return sb.toString();
	}

	public static String parseByteType(char c){
		StringBuffer sb = new StringBuffer();
		byte[] b;
		try {
			b = Character.toString(c).getBytes("UTF-8");
		} catch (Exception ex) {
			b = new byte[0];
		}
		for (int j = 0; j < b.length; j++) {
			int k = b[j];
			if (k < 0) {
				k += 256;
			}
			sb.append("%" + Integer.toHexString(k).toUpperCase());
		}
		return sb.toString();
	}
			
	public static boolean isNotEmpty(String str) {
		boolean flag = org.apache.commons.lang.StringUtils.isNotEmpty(str);
		if (flag) {
			return !"".equals(str.trim());
		} else {
			return flag;
		}
		// return flag?!"".equals(str.trim()):flag;
	}

	public static String subFixedLen(String str, int len) {
		if (str != null && str.length() > len) {
			return str.substring(0, len);
		} else {
			return str;
		}
	}

	/**
	 * ����ֵֵ��ת�������ݿ�In��ѯ��Ҫ�Ľṹ��ʽ
	 * 
	 * @Title: toArraySqlIn
	 * @Description: TODO
	 * @author: Share 2013-10-29
	 * @modify: Share 2013-10-29
	 * @param ids
	 * @return String
	 * @throws
	 */
	public static String toArraySqlIn(String[] ids) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < ids.length; i++) {
			if (i == 0) {
				sb.append(ids[i]);
			} else {
				sb.append(",").append(ids[i]);
			}
		}

		return sb.toString();
	}

	/**
	 * ����ת����Unicode����
	 * 
	 * @Title: encodeUnicode
	 * @Description: TODO
	 * @author: share 2013-12-7
	 * @modify: share 2013-12-7
	 * @param inStr
	 * @return String
	 * @throws
	 */
	public static String encodeUnicode(String inStr) {
		char[] myBuffer = inStr.toCharArray();

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < inStr.length(); i++) {
			char ch = myBuffer[i];
			if ((int) ch < 10) {
				sb.append("\\u000" + (int) ch);
				continue;
			}
			UnicodeBlock ub = UnicodeBlock.of(ch);
			if (ub == UnicodeBlock.BASIC_LATIN) {
				// Ӣ�ļ����ֵ�
				sb.append(myBuffer[i]);
			} else if (ub == UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
				// ȫ�ǰ���ַ�
				int j = (int) myBuffer[i] - 65248;
				sb.append((char) j);
			} else {
				// ����
				short s = (short) myBuffer[i];
				String hexS = Integer.toHexString(s);
				String unicode = "\\u" + hexS;
				sb.append(unicode.toLowerCase());
			}
		}
		return sb.toString();
	}

	/**
	 * unicode ת���� ����(�ع�ǰ)
	 * 
	 * @Title: decodeUnicode
	 * @Description: TODO
	 * @author: share 2013-12-7
	 * @modify: share 2013-12-7
	 * @param theString
	 * @return String
	 * @throws
	 */
/*	public static String decodeUnicode(String theString) {
		char aChar;
		int len = theString.length();
		StringBuffer outBuffer = new StringBuffer(len);
		for (int x = 0; x < len;) {
			aChar = theString.charAt(x++);
			if (aChar == '\\') {
				aChar = theString.charAt(x++);
				if (aChar == 'u') {
					// Read the xxxx
					int value = 0;
					for (int i = 0; i < 4; i++) {
						aChar = theString.charAt(x++);
						switch (aChar) {
							case '0':
							case '1':
							case '2':
							case '3':
							case '4':
							case '5':
							case '6':
							case '7':
							case '8':
							case '9':
								value = (value << 4) + aChar - '0';
								break;
							case 'a':
							case 'b':
							case 'c':
							case 'd':
							case 'e':
							case 'f':
								value = (value << 4) + 10 + aChar - 'a';
								break;
							case 'A':
							case 'B':
							case 'C':
							case 'D':
							case 'E':
							case 'F':
								value = (value << 4) + 10 + aChar - 'A';
								break;
							default:
								throw new IllegalArgumentException(
									"Malformed   \\uxxxx   encoding.");
						}
					}
					outBuffer.append((char) value);
				} else {
					if (aChar == 't') {
						aChar = '\t';
					} else if (aChar == 'r'){
						aChar = '\r';
					} else if (aChar == 'n'){
						aChar = '\n';
					} else if (aChar == 'f'){
						aChar = '\f';
					}
						
					outBuffer.append(aChar);
				}
			} else {
				outBuffer.append(aChar);
			}
				
		}
		return outBuffer.toString();
	}*/
	
	/**
	 * unicode ת���� ����(�ع���)
	 * @Title: decodeUnicode
	 * @Description: TODO
	 * @author: share 2013-12-7
	 * @modify: share 2013-12-7
	 * @param theString
	 * @return String
	 * @throws
	 */
	public static String decodeUnicode(String theString) {
		char aChar;
		int len = theString.length();
		StringBuffer outBuffer = new StringBuffer(len);
		for (int x = 0; x < len;) {
			aChar = theString.charAt(x++);
			if (aChar == '\\') {
				aChar = theString.charAt(x++);
				if (aChar == 'u') {
					int value = parseTypeCharByU(theString, x);
					outBuffer.append((char) value);
				} else {
					outBuffer.append(parseCharTypeByOther(aChar));
				}
			} else {
				outBuffer.append(aChar);
			}
				
		}
		return outBuffer.toString();
	}
	public static char parseCharTypeByOther(char aChar){
		if (aChar == 't') {
			aChar = '\t';
		} else if (aChar == 'r'){
			aChar = '\r';
		} else if (aChar == 'n'){
			aChar = '\n';
		} else if (aChar == 'f'){
			aChar = '\f';
		}
		return aChar;
	}
	public static int parseTypeCharByU(String theString, int x){

		int value = 0;
		for (int i = 0; i < 4; i++) {
			char aChar = theString.charAt(x++);
			value = parseCharArr(aChar, value);
			if (value==0){
				value = parseCharArrIsa(aChar, value);
			}
			if (value==0){
				value = parseCharArrIsA(aChar, value);
			} else {
				throw new IllegalArgumentException(
						"Malformed   \\uxxxx   encoding.");
			}
		}
		return value;
	}
	public static int parseCharArr(char aChar, int value){
		char[] charArr1 = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
		for (int a=0; a<charArr1.length; a++){
			if (charArr1[a] == aChar){
				value = (value << 4) + aChar - '0';
				break;
			}
		}
		return value;
	}
	public static int parseCharArrIsa(char aChar, int value){
		char[] charArr2 = {'a', 'b', 'c', 'd', 'e', 'f'};
		for (int a=0; a<charArr2.length; a++){
			if (charArr2[a] == aChar){
				value = (value << 4) + 10 + aChar - 'a';
				break;
			}
		}
		return value;
	}
	public static int parseCharArrIsA(char aChar, int value){
		char[] charArr3 = {'A', 'B', 'C', 'D', 'E', 'F'};
		for (int a=0; a<charArr3.length; a++){
			if (charArr3[a] == aChar){
				value = (value << 4) + 10 + aChar - 'A';
				break;
			}
		}
		return value;
	}
	/**
	 * @throws FroadBusinessException 
	 * ��ȡ�ֻ�����
	 * 
	 * @Title: getMbType
	 * @Description: ��ȡ�ֻ�����
	 * @author: froad-huangyihao 2014��8��29��
	 * @modify: froad-huangyihao 2014��8��29��
	 * @param @return
	 * @return String
	 * @throws
	 */
	public static String getMbType(String mobile) throws ZcshBusinessException {
		if (!StringUtil.isMobileNumber(mobile)) {
			throw new ZcshBusinessException("���ֻ�����");
		} else {
			if (StringUtil.isGmccMobile(mobile)) {
				return "1";
			} else if (StringUtil.isUnicomMobile(mobile)) {
				return "2";
			} else {
				return "3";
			}
		}
	}

	public static String formatMoneyByFormat(double money) {
		String tmp = "0";
		DecimalFormat df = new DecimalFormat("##,###,###,###,##0.00");
		tmp = df.format(money);
		return tmp;
	}
	
	public static String doubleFormatString(double dou){
		java.text.NumberFormat nf = java.text.NumberFormat.getInstance();   
		nf.setGroupingUsed(false);  
		return nf.format(dou); 
	}
	
	public static Double formatMoney(Double money){
		DecimalFormat df = new DecimalFormat("#.00");
		return Double.parseDouble(df.format(money));
	}

	/**
	 * @throws FroadBusinessException 
	 * ���ز��ֿ�����Ϣ
	 * 
	 * @Title: hiheAcctNo
	 * @Description: TODO
	 * @author: froad-zpy 2014��9��1��
	 * @modify: froad-zpy 2014��9��1��
	 * @param @return
	 * @return String
	 * @throws
	 */
	public static String hiheAcctNo(String acctNo) throws ZcshBusinessException {
		try {
			return acctNo.substring(0, 4) + "*********" + acctNo.substring(acctNo.length() - 4);
		} catch (Exception e) {
			LogCvt.error(e.getMessage(), e);
			throw new ZcshBusinessException("�û��ʺ�����");
		}
	}

	public static String getBlank(int digit) {
		StringBuffer sb = new StringBuffer();
		while (sb.length() < digit) {
			sb.append(" ");
		}
		return sb.toString();
	}

	public static String fixingSplitStr(String fileSplitStr) {
		return fileSplitStr.replaceAll("\\{", "\\\\{").replaceAll("\\|", "\\\\|").replaceAll("\\?", "\\\\?").replaceAll("\\}", "\\\\}");
	}
	
	
	public static String[] splitStr(String str, String delim) {
		StringTokenizer st = new StringTokenizer(str, delim);
		delim = fixingSplitStr(delim);
		return str.split(delim, st.countTokens() + 1);
	}
	

	public static String[] splitAndTrimStr(String str, String delim) {
		StringTokenizer st = new StringTokenizer(str, delim);
		delim = fixingSplitStr(delim);
		String[] split = str.split(delim, st.countTokens() + 1);
		for (int i = 0; i < split.length; i++) {
			split[i] = split[i].trim();
		}
		return split;
	}

	public static String toSqlInStatement(String[] strs) {
		List<String> tStrs = new ArrayList<String>();
		for (String str : strs) {
			tStrs.add(StringUtil.SINGLE_QUOTE + str + StringUtil.SINGLE_QUOTE);
		}
		return listToString(tStrs, StringUtil.COMMA);
	}

	public static String listToString(Collection<? extends Object> coll,
			String delimiter) {
		if (isEmptyCollection(coll)) {
			return "";
		}
		StringBuffer sb = new StringBuffer();
		for (Object obj : coll) {
			if (sb.length() > 0) {
				sb.append(delimiter);
			}
			sb.append(obj);
		}
		return sb.toString();
	}

	public static boolean isEmptyCollection(Collection<? extends Object> coll) {
		return coll == null || coll.size() == 0;
	}

	public static String clobToString(Clob clob) throws SQLException,
			IOException {
		StringBuffer sb = new StringBuffer();
		if (clob != null) {
			Reader is = clob.getCharacterStream();
			BufferedReader br = new BufferedReader(is);
			String s = br.readLine();
			while (s != null) {
				sb.append(s);
				s = br.readLine();
			}
		}
		return sb.toString();
	}

	public static boolean isNotEmpty(Object[] obj) {
		return obj != null && obj.length > 0;
	}
	public static boolean isNotEmpty(List<?> obj){
	    return obj != null && obj.size() > 0;
	}

	public static boolean isNullOrEmpty(String[] args){
		for (String str: args){
			if (str != null || !"".equals(str)){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * @Description: �ж��ַ���������ַ��Ƿ�ȫ����ͬ
	 * @author chenjun
	 * @time   2016��3��23�� ����12:36:43
	 * @param str
	 * @return
	 */
	public static boolean allEqual(String str) {
		for (int i = 1;i<str.length();i++) {
			if (str.charAt(i)!=str.charAt(0)) {
				return false;
			}
		}
		return true;
	}
	
	
	/**

     * <p>�ж��ַ����Ƿ���""," ",null,ע���isEmpty������</p>

     * ����ժ��commons.lang

     * <pre>

     * StringUtils.isBlank(null)      = true

     * StringUtils.isBlank("")        = true

     * StringUtils.isBlank(" ")       = true

     * StringUtils.isBlank("bob")     = false

     * StringUtils.isBlank("  bob  ") = false

     * </pre>

     */

    public static boolean isBlank(String str) {

        int strLen;

        if (str == null || (strLen = str.length()) == 0) {

            return true;

        }

        for (int i = 0; i < strLen; i++) {

            if ((Character.isWhitespace(str.charAt(i)) == false)) {

                return false;

            }

        }

        return true;

    }
    
    /**
     * @Description: ����û�����ʽ
     * @author chenjun
     * @time   2016��4��6�� ����9:39:09
     * @param loginName
     * @return
     * @throws FroadBusinessException 
     */
    public static void checkLoginName(String loginName) throws ZcshBusinessException {
    	int nameLen = loginName.length();
		if (nameLen<4 || nameLen>20) {
			throw new ZcshBusinessException("�û���������4-20λ");
		}
		String regex="[a-zA-Z0-9]*";
		Pattern pattern = Pattern.compile(regex);
		Matcher match=pattern.matcher(loginName);
		boolean falg=match.matches();
		if (!falg) {
			throw new ZcshBusinessException("�û���ֻ������Ӣ�Ļ�����,��Ӣ�������ֵ����");
		}
    }
    

}

