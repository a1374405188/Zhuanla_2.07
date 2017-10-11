package com.beikbank.android.exception;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.io.StringWriter;
import java.util.Date;

import com.beikbank.android.conmon.SystemConfig;
import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.utils.BeikBankConstant;
 /**
 *copyright 喻国合 
 *email: 1374405188@qq.com
 *2014-12-5
 **/
public class LogHandler {
	 private static String error_file_path=SystemConfig.LOG_FILE_PATH;
	 private static String error_file=SystemConfig.LOG_FILE_PATH+SystemConfig.LOG_FILE;
	 private static String line_space="\r\n";
	 private static String space=":-------------------------------------------"+line_space;
	
	private static Boolean init()
	 {   
		 File f=new File(error_file_path);
		 if(!f.exists())
		 {
			return f.mkdirs();
		 }
		 return true;
	 }
	public static synchronized void writeLogFromString(String tag,String text)
	{
			try {
				Boolean b=init();
				if(b==false)
				{   
					System.out.println("内存卡不存在或文件创建错误");
					return;
				}
                if(null==text)
                {
                	text="";
                }
				
				text=line_space+getData()+space+tag+":"+text+line_space;
				writeLogToFile(text);
			} 
			catch (Exception ex) 
			{
				ex.printStackTrace();
			}
	}
     public static synchronized void writeLogFromException(String tag,Exception e)
     {   
    	 PrintWriter pw=null;
		try {
			BeikBankApplication.mSharedPref.putSharePrefString(BeikBankConstant.DO_SUCCESS1_VALUE,e.getClass().getName());
			Boolean b=init();
			if(b==false)
			{   
				System.out.println("内存卡不存在或文件创建错误");
				return;
			}
			StringWriter sw=new StringWriter();
			pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			StringBuffer sb=sw.getBuffer();
			
			String text=line_space+getData()+space+tag+":"+sb.toString()+line_space;
			writeLogToFile(text);
		} 
		catch (Exception ex) 
		{
			
			ex.printStackTrace();
		}
    	
     }
     private static String getData()
     {
    	 Date date=new Date();
    	 return date.toString();
     }
     private static void writeLogToFile(String text)
     {  
    	 File file=new File(error_file);
    	 RandomAccessFile ra=null;
        try 
        {   
        	
        	ra=new RandomAccessFile(file, "rw");
        	int lenght=(int) file.length();
			ra.skipBytes(lenght);
			ra.write(text.getBytes());
			ra.close();
		} catch (Exception e)
		{
			e.printStackTrace();
			if(ra!=null)
			{
				try {
					ra.close();
				} catch (IOException e1) {
					
					e1.printStackTrace();
				}
			}
		}
     }
}
