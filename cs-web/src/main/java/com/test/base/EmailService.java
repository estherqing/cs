package com.test.base;

/**
 * @author       xuhuan
 * @date         2015-12-3
 * @description  发送邮件接口
 */

public interface EmailService {
	/**
	 * 发送邮件
	 * @param toEmail 邮件接收地址
	 * @param subject 邮件标题
	 * @param text    邮件内容
	 * @throws MessagingException 
	 */
	public void sendEmail(String toEmail, String subject, String text) throws Exception;
	
}
