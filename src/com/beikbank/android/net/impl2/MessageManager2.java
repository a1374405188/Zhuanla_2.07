package com.beikbank.android.net.impl2;

import java.util.ArrayList;

import android.app.Activity;

import com.beikbank.android.data.Message;
import com.beikbank.android.data.Message_data;
import com.beikbank.android.dataparam.MessageParam;
import com.beikbank.android.dataparam.MessageParam2;
import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.impl.MessageManager;
import com.beikbank.android.net.impl.TongYongManager;

public class MessageManager2 {
	 private Activity act;
	 private ICallBack icb;
	 boolean ismsg=false;
	 boolean isfinish=false;
   public MessageManager2(Activity act,ICallBack icb)
   {
	   this.act=act;
	   this.icb=icb;
   }
   public void start()
   {    
	   
	   String id=BeikBankApplication.getUserCode();
	   if(id!=null)
   	{
   		MessageParam mp=new MessageParam();
   		mp.endLine=20+"";
   		mp.startLine=0+"";
   		mp.type="";
   		//mp.userId=BeikBankApplication.getUserid();
   		mp.userId=id;
   		MessageManager mm=new MessageManager(act, icb1, mp);
   		//TongYongManager tym0=new TongYongManager(act, icb1,mp);
   		mm.start();
   		
   		
   		MessageParam2 mp2=new MessageParam2();
   		mp2.userId=id;
   		TongYongManager tym=new TongYongManager(act, icb2,mp2);
   		tym.start();
   		
   	}
   	else
   	{
   		MessageParam2 mp2=new MessageParam2();
   		mp2.userId="";
   		TongYongManager tym=new TongYongManager(act, icb2,mp2);
   		tym.start();
   		
   	}
   }
   ICallBack icb1=new ICallBack() {
	
	@Override
	public void back(Object obj) {
		if(obj!=null)
		{
		   Message_data md=(Message_data)obj;
		   ArrayList<Message> list=md.data;
		   boolean b=false;
		   for(Message m:list)
		   {
			   if(m.readFlg.equals("0"))
			   {   
				   ismsg=true;
				   break;
				   
			   }
		   }
		  
		   
		}
		 String id=BeikBankApplication.getUserCode();
			if(isfinish==false&&id!=null)
			{
				isfinish=true;
			}
		if(isfinish==false)
		{
			isfinish=true;
		}
		else
		{
			 if(ismsg)
			   {
				   icb.back("");
			   }
			   else
			   {
				   icb.back(null);
			   }
		}
		
	}
};
 ICallBack icb2=new ICallBack() {
	
	@Override
	public void back(Object obj) {
		if(obj!=null)
		{
		   Message_data md=(Message_data)obj;
		   ArrayList<Message> list=md.data;
		   boolean b=false;
		   for(Message m:list)
		   {
			   if(m.readFlg.equals("0"))
			   {   
				   ismsg=true;
				   break;
				   
			   }
		   }
		  
		}
		
		 String id=BeikBankApplication.getUserCode();
		if(isfinish==false&&id!=null)
		{
			isfinish=true;
		}
		else
		{
			 if(ismsg)
			   {
				   icb.back("");
			   }
			   else
			   {
				   icb.back(null);
			   }
		}
	}
};
}
