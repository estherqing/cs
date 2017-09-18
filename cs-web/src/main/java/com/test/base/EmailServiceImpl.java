package com.test.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * @author       xuhuan
 * @date         2015-12-3
 * @description  发送邮件实现类
 */

@Service
public class EmailServiceImpl implements EmailService{

	@Autowired
	private JavaMailSenderImpl javaMailSenderImpl;
	
	@Autowired
	private SystemConfig systemConfig;


	/* (non-Javadoc)
	 * @see com.yhbc.service.EmailService#sendEmail(java.lang.String, java.lang.String, java.lang.String)
	 */
	public void sendEmail(String toEmail, String subject, String text) throws Exception {
		MimeMessage mimeMessage = javaMailSenderImpl.createMimeMessage(); 
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");  
		String nick = javax.mail.internet.MimeUtility.encodeText("中安金控"); 
        mimeMessageHelper.setFrom(new InternetAddress(nick + " <"+systemConfig.getString("email.username")+">")); 
        mimeMessageHelper.setTo(toEmail);  
        mimeMessageHelper.setSubject(subject);  
        mimeMessageHelper.setText(text, true);  
        javaMailSenderImpl.send(mimeMessage); 
	}
	
	public JavaMailSenderImpl getJavaMailSenderImpl() {
		return javaMailSenderImpl;
	}
	public void setJavaMailSenderImpl(JavaMailSenderImpl javaMailSenderImpl) {
		this.javaMailSenderImpl = javaMailSenderImpl;
	}
//	
}
