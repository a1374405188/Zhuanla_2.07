package com.beikbank.android.net.impl;

import java.lang.reflect.Field;

import com.beikbank.android.net.ErrorCodeInfo;
import com.beikbank.android.net.HandlerBase;

/**
 * @author yuguohe
 * <p>
 * email:1374405188@qq.com
 * </p>
 *2015-1-5
 * 检查对象各个成员是否正确
 */
public class ErrorMessage {
	//返回的数据是否正确
    public boolean isSuccess;
    //错误的原因，及提示
    public String message="";
    /**
     * 0 应用消息
     * 1 服务器消息
     */
    public int index;
    public static ErrorMessage getEm(Object obj,String error_code) throws Exception
    {   
    	
    	ErrorMessage em=new ErrorMessage();
    	if(obj==null)
    	{   
    		em.message=error_code+ErrorCodeInfo.ee1;
    		return em;
    	}
    	else if(obj instanceof ErrorMessage)
    	{
    		ErrorMessage rcr=(ErrorMessage) obj;
			return rcr;
    	}
    	else
    	{
    		Class cla=obj.getClass();
    		Field f1=cla.getDeclaredField("result");
    		String s1=(String) f1.get(obj);
    		if(!HandlerBase.success.equals(s1))
    		{
    			Field f2=cla.getDeclaredField("message");
    			String s2=(String) f2.get(obj);
    			if(s2==null||"".equals(s2))
    			{
    				em.message=error_code+ErrorCodeInfo.ee8;
    			}
    			else
    			{   
    				if(error_code==null)
    				{
    					em.message=s2;
    				}
    				else
    				{
    					em.message=s2;
    				}
    				em.index=1;
    			}
    			return em;
    		}
    	}
    	em.isSuccess=true;
    	return em;
    }
}
