//package com.beikbank.android.email;
//import java.util.Date;    
//import java.util.Properties;   
//import javax.mail.Address;    
//import javax.mail.BodyPart;    
//import javax.mail.Message;    
//import javax.mail.MessagingException;    
//import javax.mail.Multipart;    
//import javax.mail.Session;    
//import javax.mail.Transport;    
//import javax.mail.internet.InternetAddress;    
//import javax.mail.internet.MimeBodyPart;    
//import javax.mail.internet.MimeMessage;    
//import javax.mail.internet.MimeMultipart;
//
//import android.util.Log;
///**   
// * 简单邮件（不带附件的邮件）发送器   
// */  
//public class SimpleMailSender  
//{    
//	/**   
//	 * 以文本格式发送邮件   
//	 * @param mailInfo 待发送的邮件的信息   
//	 */     
//	public boolean sendTextMail(MailSenderInfo mailInfo) 
//	{
//		// 判断是否需要身份认证      
//		MyAuthenticator authenticator = null;    
//		Properties pro = mailInfo.getProperties();   
//		if (mailInfo.isValidate()) 
//		{    
//			// 如果需要身份认证，则创建一个密码验证器     
//			authenticator = new MyAuthenticator(mailInfo.getUserName(), mailInfo.getPassword());    
//		}   
//		// 根据邮件会话属性和密码验证器构造一个发送邮件的session    
//		Session sendMailSession = Session.getDefaultInstance(pro,authenticator);    
//		try 
//		{    
//			// 根据session创建一个邮件消息    
//			Message mailMessage = new MimeMessage(sendMailSession);    
//			// 创建邮件发送者地址    
//			Address from = new InternetAddress(mailInfo.getFromAddress());    
//			// 设置邮件消息的发送者        
//			mailMessage.setFrom(from);    
//			// 创建邮件的接收者地址，并设置到邮件消息中
//			Address to = new InternetAddress(mailInfo.getToAddress());    
//			mailMessage.setRecipient(Message.RecipientType.TO,to);    
//			// 设置邮件消息的主题    
//			mailMessage.setSubject(mailInfo.getSubject());    
//			// 设置邮件消息发送的时间        
//			mailMessage.setSentDate(new Date());    
//			// 设置邮件消息的主要内容    
//			String mailContent = mailInfo.getContent();    
//			mailMessage.setText(mailContent);    
//			// 发送邮件    
//			Transport.send(mailMessage);
//			Log.e("email","发送邮件成功");
//			
//			return true;    
//		} 
//		catch (MessagingException ex) 
//		{    
//			ex.printStackTrace();    
//		}    
//		return false;    
//	}    
//
//	/**   
//	 * 以HTML格式发送邮件   
//	 * @param mailInfo 待发送的邮件信息   
//	 */  
//	public static boolean sendHtmlMail(MailSenderInfo mailInfo)
//	{    
//		 
//		MyAuthenticator authenticator = null;   
//		Properties pro = mailInfo.getProperties();   
//		
//		if (mailInfo.isValidate()) 
//		{    
//			authenticator = new MyAuthenticator(mailInfo.getUserName(), mailInfo.getPassword());   
//		}    
//		
//		Session sendMailSession = Session.getDefaultInstance(pro,authenticator);    
//		try {    
//		
//			Message mailMessage = new MimeMessage(sendMailSession);    
//		
//			Address from = new InternetAddress(mailInfo.getFromAddress());    
//		
//			mailMessage.setFrom(from);    
//		
//			Address to = new InternetAddress(mailInfo.getToAddress());    
//			 
//			mailMessage.setRecipient(Message.RecipientType.TO,to);    
//			
//			mailMessage.setSubject(mailInfo.getSubject());    
//			
//			mailMessage.setSentDate(new Date());    
//			
//			Multipart mainPart = new MimeMultipart();    
//		
//			BodyPart html = new MimeBodyPart();    
//			
//			html.setContent(mailInfo.getContent(), "text/html; charset=utf-8");    
//			mainPart.addBodyPart(html);    
//			
//			mailMessage.setContent(mainPart);    
//			
//			Transport.send(mailMessage);    
//			return true;    
//		} catch (MessagingException ex) {    
//			ex.printStackTrace();    
//		}    
//		return false;    
//	}    
//
//}
