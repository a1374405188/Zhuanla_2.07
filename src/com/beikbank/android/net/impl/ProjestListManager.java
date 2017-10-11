package com.beikbank.android.net.impl;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;


import com.beikbank.android.data.ProjectList_data;
import com.beikbank.android.dataparam.ProjectListParam;
import com.beikbank.android.dataparam.UpdateTPasswdParam;
import com.beikbank.android.net.ErrorCodeInfo;
import com.beikbank.android.net.HandlerBase;
import com.beikbank.android.net.IBusiness;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.ICallBackHnadler;
import com.beikbank.android.net.ICallBackNet;
import com.beikbank.android.net.NetServicesFactory;

/**
 * @author yuguohe
 * <p>
 * email:1374405188@qq.com
 * </p>
 *2015-1-9
 * 
 */
public class ProjestListManager {
	 String error_code=ErrorCodeInfo.e22;	
		private Activity act;
	    private String TAG="ProjestListManager";
	    ICallBack icb;
	    String fundId;
	    String startLine;
	    String endLine;
	    boolean isShow=false;
        public void setShow(boolean b)
        {
        	isShow=b;
        }
	    /**
	     * 
	     * @param act
	     * @param userid 用户id
	     * @param name 用户名字
	     * @param id 用户身份证
	     * @param icb
	     */
	   public ProjestListManager(Activity act,ICallBack icb)
	   {
		   this.act=act;
		   this.icb=icb;
	   }
	   public void start(String fundId,String startLine,String endLine)
	   {   
		   this.fundId=fundId;
		   this.startLine=startLine;
		   this.endLine=endLine;
		   ThreadManagerImpl.execute(act, icbn, icbh, error_code,isShow);
	   }
	   ICallBackNet icbn=new ICallBackNet() {
		
		@Override
		public void back(Handler handler) throws Exception {
		     Message msg=new Message();
			 IBusiness im= (IBusiness) NetServicesFactory.getNetServices(act,NetServicesFactory.BUSINESS);
			 ProjectListParam plp=new ProjectListParam();
   		     plp.fundId=fundId;
   		     plp.startLine=startLine;
   		     plp.endLine=endLine;
             Object obj=im.projectList(ProjectList_data.class,null,plp);
			 ErrorMessage em=check(obj);
			 if(!em.isSuccess)
			 {
				 msg.what=HandlerBase.error2;
				 msg.obj=em;
				 handler.sendMessage(msg);
				 return;
			 }
			 msg.what=HandlerBase.success1;
			 msg.obj=obj;
			 handler.sendMessage(msg);
		}
	};
	   ICallBackHnadler icbh=new ICallBackHnadler() {
		
		@Override
		public void back(Message msg) {
			
			if(msg.what==HandlerBase.success1)
			{
				icb.back(msg.obj);
			}
		}
	};
	  public ErrorMessage check(Object obj) throws Exception
	  {
		   ErrorMessage em=new ErrorMessage();
		   if(obj instanceof ErrorMessage)
			{
				ErrorMessage rcr=(ErrorMessage) obj;
				return rcr;
			}
		   em=ErrorMessage.getEm(obj, error_code);
			if(!em.isSuccess)
			{
				return em;
			}
			else
			{
				em.isSuccess=false;
			}
			em.isSuccess=true;
		  return em;
	  }
}
