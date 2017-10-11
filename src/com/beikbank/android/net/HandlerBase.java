package com.beikbank.android.net;

import com.beikbank.android.activity.BaseActivity;
import com.beikbank.android.activity.BaseActivity1;
import com.beikbank.android.activity.LoginPwdInputActivity;
import com.beikbank.android.conmon.MessageManger;
import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.net.impl.ErrorMessage;
import com.beikbank.android.widget.CustomToast;

import comc.beikbank.android.R;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;
 /**
 *copyright yu guo he
 *email: 1374405188@qq.com
 *2014-12-11
 *<p>
 * what=-1 error 
 * what=-2 no net
 *</p>
 **/
public class HandlerBase extends Handler{
	/**
	 * 执行多个任务时发生网络断开等异常时，只显示一条提示信息
	 */
	public static boolean global_erroe;
	public static final String success="success";
	/**
	 * 未知错误
	 */
	public static final int error=-1;
	/**
	 * 数据错误
	 */
	public static final int data_error=-4;
	/**
	 * 数据库错误
	 */
	public static final int db_error=-3;
	public static final int nonet=-2;
	/**
	 * 服务器返回错误
	 */
	public static final int error1=-5;
	/**
	 *返回ResponseCheckReturn 错误
	 */
	public static final int error2=-6;
	public static final int error3=-7;
	
	public static final int success1=1;
	public static final int success2=2;
	public static final int success3=3;
	Activity act;
	BaseActivity1 ba1;
   public HandlerBase (Activity act)
   {
	   this.act=act;
	   if(act instanceof BaseActivity1)
	   {
		   ba1=(BaseActivity1) act;
	   }
   }
	@Override
	public void handleMessage(Message msg) 
	{   
		
	    
	    
	    
		switch (msg.what) {
		//no net
		case nonet:
			MessageManger.showMeg(act,act.getString(R.string.no_net),Toast.LENGTH_LONG);
			break;
       //error
      	case error:
      		String s=act.getString(R.string.error_number);
      		MessageManger.showMeg(act,act.getString(R.string.no_error),Toast.LENGTH_LONG);
			break;
	    //db_error
    	case db_error:
    		MessageManger.showMeg(act,act.getString(R.string.db_error),Toast.LENGTH_LONG);
  		     break;
    	case HandlerBase.success2:
    		if(ba1!=null)
    		{
    			ba1.debug=ba1.debug_ok2;
    		}
    	     break;
    	case HandlerBase.success1:
    		if(ba1!=null)
    		{
    			ba1.debug=ba1.debug_ok1;
    		}
   	         break;
    	case HandlerBase.error2:
    		
    		//如果提示已经显示，则下一个提示不显示
    	    if(BeikBankApplication.msg_show)
    	    {
    	    	return ;
    	    	
    	    }
    		
    		
    		 String text=ErrorCodeInfo.e0+ErrorCodeInfo.ee0;
    		 if(msg.obj!=null&&msg.obj instanceof  ErrorMessage)
    		 {   
    			 
    			 ErrorMessage rcr=(ErrorMessage) msg.obj;
    			 String s2=rcr.message;
    			 int index=rcr.index;
    			 int code=-1;
    			 if(index==0)
    			 {
    				 code=getInt(s2);
    			 }
    			
    			 if(code==61)
    			 {
    				 text=rcr.message+act.getString(R.string.no_net);
    			 }
    			 else if(code==500)
    			 {
    				 text="网络异常";
    			 }
    			 else if(code>=400&&code<=450)
    			 {
    				 text="网络异常";
    			 }
    			 else if(code>=60&&code<=199)
    			 {
    				 //text=rcr.message+act.getString(R.string.service_net_error);
    				 text="网络异常";
    			 }
    			 else if(code>=2&&code<=39)
    			 {
    				 //text=rcr.message+act.getString(R.string.service_data_error);
    				 text="网络异常";
    			 }
    			 else if(code>=40&&code<=99)
    			 {
    				// text=rcr.message+act.getString(R.string.client_data_error);
    				 text="网络异常";
    			 }
    			 else if(code>=600&&code<=699)
    			 {
    				 //text=rcr.message+act.getString(R.string.client_net_error);
    				 text="网络异常";
    			 }
    			 else if(code>=700&&code<=799)
    			 {
    				// text=rcr.message+act.getString(R.string.client_data_error);
    				 text="网络异常";
    			 }
    			 else if(code==250)
    			 {
    				 //text=rcr.message+act.getString(R.string.service_data_error)+":250";
    				 text="网络异常";
    			 }
    			 else 
    			 {  
    					 
    					 if(text==null||text.equals(""))
    					 {
    						 text="网络异常";
    					 }
    					 else
    					 {
    					    text="网络异常";
    					 }
    					 if(rcr.index==1)
    					 {
    						 text=rcr.message;
    					 }
    			 }
    		 }
    		 if(act instanceof BaseActivity1)
    		 {
    			 BaseActivity1 ba=(BaseActivity1) act;
        		 boolean isShow=ba.showToast(text);
        		 if(!isShow)
        		 {   
        			 if(text!=null&&!"".equals(text))
        			 {
        			    showMsg(act, text,Toast.LENGTH_LONG);
        			 }
        		 }
    		 }
    		 else
    		 {
    			 if(text!=null&&!"".equals(text))
    			 {
    			    showMsg(act, text,Toast.LENGTH_LONG);
    			 }
    		 }
             //MessageManger.showMeg(act,text,Toast.LENGTH_LONG);
             //CustomToast.makeText(act,rcr.message, Toast.LENGTH_LONG).show();
    		 if(ba1!=null)
     		{
     			ba1.debug=ba1.debug_fail;
     		}
			   break;  
		}
	
	
	}
	/**
	 * 截取厚2位
	 * @param s "100100"
	 * @return 100 if s 的长度不是6返回-1
	 */
	public static int getInt(String s)
	{   
		int a=0;
		if(s.length()==6)
		{
		  s=s.substring(3,6);
		  a=Integer.parseInt(s);
		}
		return a;
	}
    public static void showMsg(Activity act, String text, int type)
    {
    	 MessageManger.showMeg(act,text,Toast.LENGTH_LONG);
    }
}
