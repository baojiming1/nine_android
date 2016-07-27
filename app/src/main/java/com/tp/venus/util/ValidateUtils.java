/**<p>Project: </p>
 * <p>Package:	com.qbt.framework.util</p>
 * <p>File: ValidateUtils.java</p>
 * <p>Version: 1.0.0</p>
 * <p>Date: 2015年8月26日-下午5:57:43</p>
 * Copyright © 2015 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.util;

import java.math.BigDecimal;
import java.util.regex.Pattern;

/**<p>Class: ValidateUtils.java</p>
 * <p>Description: 校验工具类</p>
 * <pre>
 *      
 * </pre>
 * @author 鲍建明
 * @date 2015年8月26日 下午5:57:43
 * @version 1.0.0
 */
public class ValidateUtils
{
	// url地址验证正则表达式
	public static final String REGULAR_URL = "^(http|https|ftp)://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?$";

	// IP地址验证正则表达式
	public static final String REGULAR_IP_ADDRESS = "^(((2[0-4]\\d|25[0-5]|[01]?\\d\\d?)\\.){3}(2[0-4]\\d|25[0-5]|[01]?\\d\\d?))?$";

	// 身份证验证正则表达式
	public static final String REGULAR_ID_CARD = "^([1-9][0-9]{14}|[1-9][0-9]{17}|[1-9][0-9]{16}[x,X])?$";

	// 邮政编码验证正则表达式
	public static final String REGULAR_ZIP_CODE = "^([1-9][0-9]{5})?$";

	// 电话验证正则表达式
	public static final String REGULAR_PHONE = "^([0-9]{3,4}-[0-9]{7,8}(-[0-9]{2,6})?)?$";

	// 邮箱验证正则表达式
	public static final String REGULAR_EMIAL = "^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+$";

	// 手机验证正则表达式
	 public static final String REGULAR_MOBILE = "^(1[0-9]{10})?$";
	//public static final String REGULAR_MOBILE = "^(1[3-8]+\\d{9})?$";

	// 金额验证正则表达式
	public static final String REGULAR_MONEY = "^((([1-9]{1}\\d{0,9})|([0]{1}))(\\.(\\d){1,2})?)?$";

	/**
	 * 私有构造器，防止类的实例化
	 */
	private ValidateUtils()
	{
		super();
	}
	
	/**
	 * 邮件地址格式验证(支持批量验证，各邮件地址用";"分隔)
	 * 
	 * @param value 			要检查的字符串
	 * @return boolean 		是否为正确的电话格式
	 */
	public static boolean isEmail(String value)
	{
		return validate(value, REGULAR_EMIAL);
	}

	/**
	 * 电话号码格式验证(支持批量验证，各电话号码用";"分隔)
	 * 
	 * @param value 			要检查的字符串
	 * @return boolean 		是否为正确的电话格式
	 */
	public static boolean isTelFormat(String value)
	{
		return validate(value, REGULAR_PHONE);
	}

	/**
	 * 邮政编码格式验证 6位(支持批量验证，各邮政编码用";"分隔)
	 * 
	 * @param value 		要检查的字符串
	 * @return boolean 		是否正确的邮编格式
	 */
	public static boolean isZipCode(String value)
	{
		return validate(value, REGULAR_ZIP_CODE);
	}

	/**
	 * 身份证号码格式验证 15位或18位(支持批量验证，各身份证号码用";"分隔)
	 * 
	 * @param value 		要检查的字符串
	 * @return boolean 		是否正确的身份证号码格式
	 */
	public static boolean isIDCard(String value)
	{
		return validate(value, REGULAR_ID_CARD);
	}

	/**
	 * 手机号码格式验证(支持批量验证，各手机号码用";"分隔)
	 * 
	 * @param value 		 要检查的字符串
	 * @return boolean 		是否正确的手机号码格式
	 */
	public static boolean isMobile(String value)
	{
		// 正则表达式参考 @link http://my.oschina.net/william1/blog/4752
		return validate(value, REGULAR_MOBILE);
	}

	/**
	 * IP地址格式验证(支持批量验证，各IP地址用";"分隔)
	 * 
	 * @param value 		要检查的字符串
	 * @return boolean 	是否正确的IP地址格式
	 */
	public static boolean isIpAddress(String value)
	{
		return validate(value, REGULAR_IP_ADDRESS);
	}

	/**
	 * URL地址格式验证(支持批量验证，各URL地址用";"分隔)
	 * @param value 		要检查的字符串
	 * @return boolean 	是否正确的URL地址格式
	 */
	public static boolean isUrl(String value)
	{
		return validate(value, REGULAR_URL);
	}



	/**
	 * 金额格式验证（范围约束）
	 * @param price 			金额
	 * @param min 			最小值（包含）　
	 * @param max 			最大值（包含）　
	 * @return
	 */
	public static Boolean isMoney(BigDecimal price, BigDecimal min, BigDecimal max)
	{
		boolean bool = true;
		bool = (null != price);
		if (bool)
		{
			bool = matchRegexp(price.toString(), REGULAR_MONEY);
		}
		if (bool && null != min)
		{
			bool = (price.compareTo(min) >= 0);
		}
		if (bool && null != max)
		{
			bool = (price.compareTo(max) <=0);
		}
		return bool;
	}


	/**
	 * 正则匹配
	 *
	 * @param value 		待匹配的字符串
	 * @param regexp 		正则表达式
	 * @return boolean 	是否匹配
	 */
	public static boolean matchRegexp(String value, String regexp)
	{
		return Pattern.matches(regexp, value);
	}

	/**
	 * 批量正则匹配
	 *
	 * @param values 			待匹配的字符串（多个带匹配的字符串用 ";" 分开）
	 * @param regex 			正则表达式
	 * @return boolean 		是否匹配
	 */
	public static boolean validate(String values, String regex)
	{
		if( StringUtil.isEmpty(values) || StringUtil.isEmpty(regex) ) return false;

		// 全角转半角
		char c[] = values.toCharArray();
		for (int i = 0; i < c.length; i++)
		{
			if (c[i] == '\u3000')
			{
				c[i] = ' ';
			} else if (c[i] > '\uFF00' && c[i] < '\uFF5F')
			{
				c[i] = (char) (c[i] - 65248);

			}
		}
		values = StringUtil.trimToEmpty(new String(c));
		if (values.contains(";"))// 单匹配
		{
			return matchRegexp(values, regex);
		}
		else// 批量匹配
		{
			String[] strs = values.split(";");
			for (int i = 0; i < strs.length; i++)
			{
				String str = strs[i];
				if (!matchRegexp(str, regex))
				{
					return false;
				}
			}
			return true;
		}
	}


}