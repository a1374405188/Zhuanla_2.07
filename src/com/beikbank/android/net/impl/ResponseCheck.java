package com.beikbank.android.net.impl;

import java.lang.reflect.Field;

/**
 * @author yuguohe
 * <p>
 * email:1374405188@qq.com
 * </p>
 *2015-1-5
 * 
 */
public class ResponseCheck {
	/**
	 * 检查一个简单的对象
	 * String 是否null 是否""
	 * 
	 * if 它本身是一个ResponseCheckReturn
	 * 直接返回它
	 * @param obj
	 * @return ResponseCheckReturn
	 */
	 public ErrorMessage check(String tag,Object obj) throws Exception
	   {   
		   if(obj instanceof ErrorMessage)
		   {
			   return (ErrorMessage) obj;
		   }
		   ErrorMessage rcr=new ErrorMessage();
		   Class cla=obj.getClass();
		   Field fields[]=cla.getDeclaredFields();
		   Field field=null;
		   Class cla2=null;
		   for(int i=0;i<fields.length;i++)
		   {
			   field=fields[i];
			   field.setAccessible(true);
			   cla2=field.getType();
			   if("java.lang.String".equals(cla2.getName()))
			   {
				   String s=(String) field.get(obj);
				   if("".equals(s))
				   {
					   String text=tag+":"+cla.getSimpleName()+":"+field.getName()+" is null value";
					   rcr.message=text;
					   return rcr;
				   }
				   else if(s==null)
				   {
					   String text=tag+":"+cla.getName()+":"+field.getName()+" is null ";
					   rcr.message=text;
					   return rcr;
				   }
			   }
			   
		   }
		   rcr.isSuccess=true;
		   return rcr;
	   }

}
