//package com.beikbank.android.email;
//
//import com.beikbank.android.fragment.BeikBankApplication;
//
//import android.util.Log;
//
//
///**
// * 邮件管理
// * @author Administrator
// *
// */
//public class MailManager 
//{ 
//	/**
//	 * 发送邮件
//	 * @param text
//	 * @return
//	 */
//  public static boolean sendEmail(String text)
//  {
//	  boolean b=false;
//	  String phone=BeikBankApplication.getPhoneNumber();
//	  b=send(text,phone);
//	  
//	  
//	  return b;
//  }
//  /**
//	 * 发送邮件
//	 * @param text
//	 * @return
//	 */
// public static boolean sendEmail2(String text)
//{
//	  boolean b=false;
//	  String phone=BeikBankApplication.getPhoneNumber();
//	  b=send2(text,phone);
//	  
//	  
//	  return b;
//}
//  private static boolean send2(String email,String phone)
//  {   
//	  boolean b=false;
//	  try 
//      { 
//     	MailSenderInfo mailInfo = new MailSenderInfo();    
//       mailInfo.setMailServerHost("smtp.163.com");    
//       mailInfo.setMailServerPort("25");    
//       mailInfo.setValidate(true);    
//       mailInfo.setUserName("cbeikeapp@163.com");  //你的邮箱地址  
//       mailInfo.setPassword("b666666");//您的邮箱密码    
//       mailInfo.setFromAddress("cbeikeapp@163.com");    
//       mailInfo.setToAddress("cbeikeapp@163.com");
//       if(phone==null)
//       {
//    	   phone="";
//       }
//       mailInfo.setSubject("bug报告"+phone);    
//       mailInfo.setContent(email);    
//       
//          //这个类主要来发送邮件   
//           SimpleMailSender sms = new SimpleMailSender();   
//           b=sms.sendTextMail(mailInfo);//发送文体格式    
//           //sms.sendHtmlMail(mailInfo);//发送html格式 
//           
//      } 
//      catch (Exception e) { 
//          Log.e("SendMail", e.getMessage(), e); 
//      }
//	  return b;
//  }
//  private static boolean send(String email,String phone)
//  {   
//	  boolean b=false;
//	  try 
//      { 
//     	MailSenderInfo mailInfo = new MailSenderInfo();    
//       mailInfo.setMailServerHost("smtp.163.com");    
//       mailInfo.setMailServerPort("25");    
//       mailInfo.setValidate(true);    
//       mailInfo.setUserName("abeikeapp@163.com");  //你的邮箱地址  
//       mailInfo.setPassword("b666666");//您的邮箱密码    
//       mailInfo.setFromAddress("abeikeapp@163.com");    
//       mailInfo.setToAddress("abeikeapp@163.com");
//       if(phone==null)
//       {
//    	   phone="";
//       }
//       mailInfo.setSubject("bug报告"+phone);    
//       mailInfo.setContent(email);    
//       
//          //这个类主要来发送邮件   
//           SimpleMailSender sms = new SimpleMailSender();   
//           b=sms.sendTextMail(mailInfo);//发送文体格式    
//           //sms.sendHtmlMail(mailInfo);//发送html格式 
//           
//      } 
//      catch (Exception e) { 
//          Log.e("SendMail", e.getMessage(), e); 
//      }
//	  return b;
//  }
//}
