package com.beikbank.android.activity.help;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.widget.ListView;
import android.widget.TextView;

import com.beikbank.android.dao.TableDaoSimple;
import com.beikbank.android.data.CommonInfo;
import com.beikbank.android.data.CommonInfo_data;
import com.beikbank.android.data.Message;
import com.beikbank.android.data.Message_data;
import com.beikbank.android.dataparam.MessageParam;
import com.beikbank.android.dataparam.SysNoticeParam;
import com.beikbank.android.dataparam.TotalMoneyParam;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.impl.CommonInfoManager;
import com.beikbank.android.net.impl.MessageManager;
import com.beikbank.android.net.impl.SysNoticeManager;
import com.beikbank.android.net.impl.ThreadManagerSet;
import com.beikbank.android.scroller.DeleteAdapter;

/**
 * @author yuguohe
 * <p>
 * email:1374405188@qq.com
 * </p>
 *2015-3-5
 * 通知，私信帮助类
 */
public class MessageHelp {
	//消息
	private List<Message> list;
	//系统通知
    private List<Message> list2;
    private DeleteAdapter adapter;
    private DeleteAdapter adapter2;
    
    int start1=0;
	int end1=19;
	int add20;
	
	int start2=0;
	int end2=19;
	String userid="4811";
    /**
     * 通知
     */
    ListView lv1;
    /**
     * 消息
     */
    ListView lv2;
    Activity act;
    /**
     * 
     * @param lv1  通知
     * @param lv2  消息
     */
    public MessageHelp(Activity act,ListView lv1,ListView lv2)
    {
    	this.lv1=lv1;
    	this.lv2=lv2;
    	this.act=act;
    }
    //显示消息的控件
    TextView tv;
    ICallBack icbMsgCount;
    /**
     * 设置未读消息和通知数
     * @return
     */
    public void setMsgAndNotice(ICallBack icb)
    {
    	icbMsgCount=icb;
    	initData2();
      
    }
    private void initData()
    {
    	MessageParam mp=new MessageParam();
		mp.endLine=end2+"";
		mp.startLine=start2+"";
		mp.type="";
		//mp.userId=BeikBankApplication.getUserid();
		mp.userId=userid;
		MessageManager mm=new MessageManager(act, icb2, mp);
		SysNoticeParam snp=new SysNoticeParam();
		snp.endLine=end1+"";
		snp.startLine=start1+"";
		SysNoticeManager snm=new SysNoticeManager(act, icb1, snp);
		ThreadManagerSet tms=new ThreadManagerSet(icb);
		tms.add(mm);
		tms.add(snm);
		tms.start();
    }
    /**
     * 未读消息记数
     */
    int msgCount;
    /**
     * 初始化未读消息的数量
     */
    private void initData2()
    {   
    	TotalMoneyParam tlp=new TotalMoneyParam();
    	tlp.memberID=userid;
    	CommonInfoManager cim=new CommonInfoManager(act, icb3, tlp);
    	cim.start();
    }
    ICallBack icb=new ICallBack() {
		
		@Override
		public void back(Object obj) {
			
			
		}
	};
    ICallBack icb1=new ICallBack() {
		
		@Override
		public void back(Object obj) {
			
			if(obj!=null)
			{
				Message_data md=(Message_data)obj;
				list=md.data;
			}
		}
	};
   ICallBack icb2=new ICallBack() {
	
	@Override
	public void back(Object obj) {
		
		  if(obj!=null)
		  {
			  Message_data md=(Message_data)obj;
			  list2=md.data;
		  }
	}
    };
    ICallBack icb3=new ICallBack() {
		
		@Override
		public void back(Object obj) {
			if(obj!=null)
			{
				CommonInfo_data cd=(CommonInfo_data)obj;
				CommonInfo ci=cd.data;
				ArrayList<Message> lists=null;
				try {
				   lists=(ArrayList<Message>) TableDaoSimple.query(Message.class,null,null);
				} catch (Exception e) {
					e.printStackTrace();
				}
				int a=Integer.parseInt(ci.noticeRecordCount)-lists.size();
				if(a>0)
				{
					msgCount=Integer.parseInt(ci.unreadedCount)+a;
				}
				else
				{
					msgCount=Integer.parseInt(ci.unreadedCount);
				}
				icbMsgCount.back(msgCount);
			}
		}
	};
    /**
	 * 通知网络数据
	 */
	HashMap<String,Message> net;
	/**
	 * 通知本地数据
	 */
	HashMap<String,Message> db;

	
	/**
	 * 将本地数据库数据和服务器数据比对
	 * @return 比对后的数据
	 */
	private List<Message> getNewMessage()
	{   
		List<Message> listd=null;
		 try 
		 {
		    listd=(List<Message>) TableDaoSimple.query(Message.class,null,null);
		 } 
		 catch (Exception e) {
			
			e.printStackTrace();
		 }
		 if(listd!=null)
		 {
			 db=new HashMap<String, Message>();
			 for(Message msg:listd)
			 {
				 db.put(msg.id,msg);
			 }
		 }
		 
		 if(list!=null)
		 {   
			 net=new HashMap<String, Message>();
			 for(Message msg:list)
			 {
				 net.put(msg.id,msg);
			 }
		 }
		 
		 if(net==null&&db!=null)
		 {
			 return listd;
		 }
		 else if(net!=null&&db==null)
		 {
			 return list;
		 }
		 else if(net==null&&db==null)
		 {
			 return null;
		 }
		 
		 
		List<Message> list=new ArrayList<Message>();
		for(String nmsg:net.keySet())
		{
			Message msgn=net.get(nmsg);
			Message msgd=db.get(nmsg);
			if(msgd==null)
			{
				list.add(msgn);
			}
			else
			{   //如果本地有这个数据添加这个数据
				if(msgn.sendTime.equals(msgd.sendTime)&&msgn.updateTime.equals(msgn.updateTime))
				{
					list.add(msgd);
				}
				else
				{
					list.add(msgn);
				}
			}
		}
		return list;
	}
}
