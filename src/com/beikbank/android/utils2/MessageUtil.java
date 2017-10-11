package com.beikbank.android.utils2;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;

import com.beikbank.android.data.CommonInfo;
import com.beikbank.android.data.CommonInfo_data;
import com.beikbank.android.data.Message;
import com.beikbank.android.data.Message_data;
import com.beikbank.android.dataparam.MessageParam2;
import com.beikbank.android.dataparam.TotalMoneyParam;
import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.impl.CommonInfoManager;
import com.beikbank.android.net.impl.TongYongManager;
import com.beikbank.android.utils.BeikBankConstant;

/**
 * 得到未读消息计数
 * @author Administrator
 *
 */
public class MessageUtil 
{   
	//处理完消息和通知后回调
	ICallBack icb;
	Activity act;
	public MessageUtil(ICallBack icb,Activity act)
	{
		this.icb=icb;
		this.act=act;
		initData();
		
	}
	private void initData()
	{   
		String id=BeikBankApplication.getUserid();
		
		TotalMoneyParam tlp=new TotalMoneyParam();
    	tlp.memberID=id;
    	if(id==null)
    	{
    		tlp.memberID="";
    	}
    	CommonInfoManager cim=new CommonInfoManager(act,icb7,tlp);
    	cim.start();
    	
    	
    	MessageParam2 mp2=new MessageParam2();
    	mp2.userId=id;
    	if(id==null)
    	{
		    mp2.userId="";
    	}
		TongYongManager tym=new TongYongManager(act, icb1,mp2);
		tym.start();
	}
	int msgCount;
	//四否加载完成
	boolean b=false;
	ICallBack icb1=new ICallBack() {
		
		@Override
		public void back(Object obj) {
			
			if(obj!=null)
			{
				Message_data md=(Message_data)obj;
				ArrayList<Message> list=md.data;
				int count=0;
				for(Message m:list)
				{
					if(m.readFlg.equals("0"))
					{
						count++;
					}
				}
				msgCount=msgCount+count;
				if(b)
				{
					icb.back(msgCount);
				}
				else
				{
					b=true;
				}
			}
		}
	};
	
	
	
	
	
	ICallBack icb7=new ICallBack() {
		
		@Override
		public void back(Object obj) {
			 if(obj!=null)
			   {
				CommonInfo_data cd=(CommonInfo_data)obj;
				CommonInfo ci=cd.data;
				//List<Message> lists=null;
				//int b=0;

				
				
			    msgCount+=Integer.parseInt(ci.unreadedCount);
				
				if(b)
				{
					icb.back(msgCount);
				}
				else
				{
					b=true;
				}
			   }
		}
	};
}
