package com.beikbank.android.activity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import com.beikbank.android.adapter.TransactionRecordsAdapter;
import com.beikbank.android.dao.TableDaoSimple;
import com.beikbank.android.data.Message;
import com.beikbank.android.data.Message2;
import com.beikbank.android.data.Message_data;
import com.beikbank.android.data.SysNotice;
import com.beikbank.android.data.SysNotice_data;
import com.beikbank.android.data2.XiaoXiGet;
import com.beikbank.android.data2.XiaoXiGet0;
import com.beikbank.android.data2.XiaoXiGet_data;
import com.beikbank.android.data2.getJiaoYiJiLv;
import com.beikbank.android.dataparam.MessageParam;
import com.beikbank.android.dataparam.MessageParam2;
import com.beikbank.android.dataparam.MessageParam3;
import com.beikbank.android.dataparam.MessageReadParam;
import com.beikbank.android.dataparam.SysNoticeParam;
import com.beikbank.android.dataparam2.XiaoXiGetParam;
import com.beikbank.android.dataparam2.XiaoXiReadParam;
import com.beikbank.android.dataparam2.getJiaoYiJiLvParam;
import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.ManagerParam;
import com.beikbank.android.net.impl.MessageManager;
import com.beikbank.android.net.impl.MessageReadManager;
import com.beikbank.android.net.impl.MessagedelManager;
import com.beikbank.android.net.impl.SysNoticeManager;
import com.beikbank.android.net.impl.ThreadManagerSet;
import com.beikbank.android.net.impl.TongYongManager;
import com.beikbank.android.net.impl.TongYongManager2;
import com.beikbank.android.pullrefresh.PullToRefreshBase2;
import com.beikbank.android.pullrefresh.PullToRefreshBase2.OnRefreshListener;
import com.beikbank.android.pullrefresh.PullToRefreshListView2;
import com.beikbank.android.pullrefresh.PullToRefreshScrollView;
import com.beikbank.android.scroller.DeleteAdapter;
import com.beikbank.android.scroller.DeleteAdapter2;
import com.beikbank.android.scroller.DeleteAdapter3;
import com.beikbank.android.scroller.ScrollListviewDelete;
import com.beikbank.android.scroller.ScrollListviewDelete.ItemClickListener;
import com.beikbank.android.utils.BeikBankConstant;
import com.beikbank.android.utils.ViewRullUtil;
import com.beikbank.android.utils2.StateBarColor;

import comc.beikbank.android.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;

import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

/**
 * @author yuguohe
 * <p>
 * email:1374405188@qq.com
 * </p>
 *2015-2-9
 * 消息列表
 */
public class MessageActivity  extends BaseActivity1 implements OnClickListener
{   
	//标题，头部右文本
    TextView title,right;
	private DeleteAdapter adapter;
	
	//消息
	/***
	 * 通知
	 */
	private List<Object> list=new ArrayList<Object>();
	
	private ScrollListviewDelete sld1;
	//系统通知
	/**
	 * 消息
	 */
	private List<Object> list2=new ArrayList<Object>();
	private DeleteAdapter adapter2;
	private ScrollListviewDelete sld2;
	private PullToRefreshListView2 ps_notice;
	private PullToRefreshListView2 ps_message;
	//返回，listview父布局
	LinearLayout linear_left,ll;
	Activity act=this;
	LinearLayout ll3;
	LinearLayout ll4;
	/**
	 * 没有消息记录父布局
	 */
	LinearLayout ll_nomessage;
	int start1=0;
	int end1=100;
	int add=20;
	
	int start2=0;
	int end2=19;
	/**
	 * 通知和消息
	 */
	TextView notice,message;
	/**
	 * 标示当前是通知还是消息 1通知2消息
	 */
	int index=1;
	String userid;
	
	
	
	
	  /**
     * 上啦控件
     */
    LinearLayout ll_pull;
    /**
     * 
     */
    ViewRullUtil vpu;
    /**
     * listview父布局
     */
    LinearLayout ll2;
    
    
    
    
    /**
     * 上啦控件
     */
    LinearLayout ll_pull2;
    /**
     * 
     */
    ViewRullUtil vpu2;
    /**
     * listview父布局
     */
    LinearLayout ll22;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.message_activity);
		StateBarColor.init(this,0xffffffff);
		userid=BeikBankApplication.getUserid();
		initView();
		//initData();
		
	}
    private void initView()
    {
    	title=(TextView)findViewById(R.id.titleTv);
		right=(TextView)findViewById(R.id.right);
    	linear_left=(LinearLayout) findViewById(R.id.linear_left);
    	linear_left.setOnClickListener(this);
//		ll_nomessage=(LinearLayout) findViewById(R.id.ll_nomsg);
    	
    	
		title.setText(getString(R.string.message_text1));
		right.setText(getString(R.string.message_text2));
	    
		right.setOnClickListener(this);
		
		linear_left.setOnClickListener(this);
		
		notice=(TextView) findViewById(R.id.notice);
		message=(TextView) findViewById(R.id.message);
		notice.setOnClickListener(this);
		
		
		String phone=BeikBankApplication.mSharedPref.getSharePrefString(BeikBankConstant.LOGIN_ACCOUNT);
		if(phone==null||"".equals(phone))
		{
			message.setTextColor(0xff999999);
		}
		else
		{
			message.setOnClickListener(this);
		}
		
		
		initView1();
		initView2();
//		ps_notice=(PullToRefreshListView2) findViewById(R.id.ps_notice);
//		ps_message=(PullToRefreshListView2) findViewById(R.id.ps_message);
		
//		ps_notice.setPullLoadEnabled(true);
//		ps_notice.setScrollLoadEnabled(false);
//		ps_notice.setPullRefreshEnabled(true);
//		
//		
//		ps_message.setPullLoadEnabled(true);
//		ps_message.setScrollLoadEnabled(false);
//		ps_message.setPullRefreshEnabled(true);
//		
//	
//		ps_notice.setOnRefreshListener(new OnRefreshListener() {
//			
//			@Override
//			public void onPullUpToRefresh(PullToRefreshBase2 refreshView) {
//				initData();
//			}
//			
//			@Override
//			public void onPullDownToRefresh(PullToRefreshBase2 refreshView) {
//				initData();
//			}
//		});
//		ps_message.setOnRefreshListener(new OnRefreshListener() {
//			
//			@Override
//			public void onPullUpToRefresh(PullToRefreshBase2 refreshView) {
//				start2=0;
//				end2=list2.size()+add;
//				MessageParam mp=new MessageParam();
//				mp.endLine=end2+"";
//				mp.startLine=start2+"";
//				mp.type="";
//				mp.userId=BeikBankApplication.getUserid();
//				//mp.userId=userid;
//			
//				MessageManager mm=new MessageManager(act, icb2, mp);
//				mm.start();
//			}
//			
//			@Override
//			public void onPullDownToRefresh(PullToRefreshBase2 refreshView) {
//				MessageParam mp=new MessageParam();
//				mp.endLine=end2+"";
//				mp.startLine=start2+"";
//				mp.type="";
//				mp.userId=BeikBankApplication.getUserid();
//				//mp.userId=userid;
//				MessageManager mm=new MessageManager(act, icb2, mp);
//				mm.start();
//				
//			}
//		});
		
//		sld1=(ScrollListviewDelete) ps_notice.getRefreshableView();
//		sld1.setDivider(getResources().getDrawable(R.color.line3));
//		sld1.setDividerHeight(1);
//		sld1.setScrollingCacheEnabled(false);
//		sld1.setFadingEdgeLength(0);
//		
//		
//		sld2=(ScrollListviewDelete) ps_message.getRefreshableView();
//		sld2.setDivider(getResources().getDrawable(R.color.line3));
//		sld2.setDividerHeight(1);
//		sld2.setScrollingCacheEnabled(false);
//		sld2.setFadingEdgeLength(0);
//		ll=(LinearLayout) findViewById(R.id.ll);
//	    LayoutInflater li=getLayoutInflater();
//	    ll3=(LinearLayout) li.inflate(R.layout.scroll_listview_delete,ll,false);
//	    ll4=(LinearLayout) li.inflate(R.layout.scroll_listview_delete,ll,false);
//		sld1=(ScrollListviewDelete) ll3.findViewById(R.id.list);
//		
//		sld2=(ScrollListviewDelete) ll4.findViewById(R.id.list);
//		ll.addView(ll3);
		   
    }
  private void initView1()
  {
	  ll_pull=(LinearLayout) findViewById(R.id.ll);
      vpu=new ViewRullUtil(down, up);
     
      adapter = new DeleteAdapter(act,list,1);
     // tra=new TransactionRecordsAdapter(this);
	   vpu.adapter=adapter;
      ll2=vpu.initView(this,null);
      vpu.init(0);
	   ll_pull.removeAllViews();
	   ll_pull.addView(ll2);
	   vpu.prl.doPullRefreshing(true,200);
	   
	   
	   
	 ///  vpu.mListView.setOnItemClickListener(MessageActivity.this);
	   vpu.mListView.setOnItemClickListener(new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view,
				int position, long id) {
			
			Intent intent=new Intent(MessageActivity.this,MessageDetail.class);
			is_all=false;
			if(index==1)
			{   
				if(adapter.itemDelete!=null)
				{
					return;
				}
				XiaoXiGet msg=(XiaoXiGet) list.get(position);
				intent.putExtra("msg",msg);
				intent.putExtra("type","1");
	            if("1".equals(msg.is_read))
	            {
	            	msg.is_read="0";
	            	//adapter.notifyDataSetChanged();
	            	reads1.add(msg);
	            	read(msg.msg_log_id);
	            }
	            
			}
			else
			{   
				if(adapter2.itemDelete!=null)
				{
					return;
				}
				XiaoXiGet msg=(XiaoXiGet) list2.get(position);
				intent.putExtra("msg",msg);
				intent.putExtra("type","2");
				 if("1".equals(msg.is_read))
		            {
		            	msg.is_read="0";
		            	//adapter2.notifyDataSetChanged();
		            	reads2.add(msg);
		            	read(msg.msg_log_id);
		            }
			}
			startActivity(intent);
		}
	});
  }
  private void initView2()
  {
	  ll_pull=(LinearLayout) findViewById(R.id.ll);
      vpu2=new ViewRullUtil(down2, up2);
     
      adapter2 = new DeleteAdapter(act,list2,1);
     // tra=new TransactionRecordsAdapter(this);
	   vpu2.adapter=adapter2;
      ll22=vpu2.initView(this,null);
      vpu2.init(0);
	   ll_pull.removeAllViews();
	   ll_pull.addView(ll22);
	   vpu2.prl.doPullRefreshing(true,200);
	   
	   
	   
	 ///  vpu.mListView.setOnItemClickListener(MessageActivity.this);
	   vpu2.mListView.setOnItemClickListener(new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view,
				int position, long id) {
			
			Intent intent=new Intent(MessageActivity.this,MessageDetail.class);
			is_all=false;
			if(index==1)
			{   
				if(adapter.itemDelete!=null)
				{
					return;
				}
				XiaoXiGet msg=(XiaoXiGet) list.get(position);
				intent.putExtra("msg",msg);
				intent.putExtra("type","1");
	            if("1".equals(msg.is_read))
	            {
	            	msg.is_read="0";
	            	//adapter.notifyDataSetChanged();
	            	reads1.add(msg);
	            	
	            	read(msg.msg_log_id);
	            }
	            
			}
			else
			{   
				if(adapter2.itemDelete!=null)
				{
					return;
				}
				XiaoXiGet msg=(XiaoXiGet) list2.get(position);
				intent.putExtra("msg",msg);
				intent.putExtra("type","2");
				 if("1".equals(msg.is_read))
		            {
		            	msg.is_read="0";
		            	//adapter2.notifyDataSetChanged();
		            	reads2.add(msg);
		            	read(msg.msg_log_id);
		            }
			}
			startActivity(intent);
		}
	});
  }
    
    
  ICallBack icb_down=new ICallBack() {
	
	@Override
	public void back(Object obj) {
		
		if(obj!=null)
		{   
			 XiaoXiGet_data xd=(XiaoXiGet_data) obj;
			 List list1=xd.body.list;
			
			 list=list1;
			 vpu.doDownCompelete(list);
			 
			
		}
	}
};
ICallBack icb_down2=new ICallBack() {
	
	@Override
	public void back(Object obj) {
		
		if(obj!=null)
		{   
			 XiaoXiGet_data xd=(XiaoXiGet_data) obj;
			 List list1=xd.body.list;
			
			 list=list1;
			 vpu2.doDownCompelete(list);
			 
			
		}
	}
};


  ICallBack down=new ICallBack() {
		
		@Override
		public void back(Object obj) {
	
			XiaoXiGetParam xp=new XiaoXiGetParam();
	    	
	        String phone=BeikBankApplication.getPhoneNumber();
	        xp.limit="40";
	        xp.msg_type=index+"";
	        xp.phone_number=phone;
	        xp.offset=vpu.start+"";
	        TongYongManager2 tym2=new TongYongManager2(act, icb_down,xp);
			//tms.add(snm);
			tym2.start();
			
			
			   
		}
	};
	
	  ICallBack down2=new ICallBack() {
			
			@Override
			public void back(Object obj) {
		
				XiaoXiGetParam xp=new XiaoXiGetParam();
		    	
		        String phone=BeikBankApplication.getPhoneNumber();
		        xp.limit="40";
		        xp.msg_type=index+"";
		        xp.phone_number=phone;
		        xp.offset=vpu2.start+"";
		        TongYongManager2 tym2=new TongYongManager2(act, icb_down2,xp);
				//tms.add(snm);
				tym2.start();
				
				
				   
			}
		};
	  ICallBack icb_up=new ICallBack() {
			
			@Override
			public void back(Object obj) {
				 XiaoXiGet_data xd=(XiaoXiGet_data) obj;
				 List list1=xd.body.list;
				
				 list=list1;
				 vpu.doUpCompelete(list);
				 
				
				
			}
		};
		  ICallBack icb_up2=new ICallBack() {
				
				@Override
				public void back(Object obj) {
					 XiaoXiGet_data xd=(XiaoXiGet_data) obj;
					 List list1=xd.body.list;
					
					 list=list1;
					 vpu2.doUpCompelete(list);
					 
					
					
				}
			};
	ICallBack up=new ICallBack() {
		
		@Override
		public void back(Object obj) {
          XiaoXiGetParam xp=new XiaoXiGetParam();
	    	
	        String phone=BeikBankApplication.getPhoneNumber();
	        xp.limit="40";
	        xp.msg_type=index+"";
	        xp.phone_number=phone;
	        xp.offset=vpu.start+"";
	        TongYongManager2 tym2=new TongYongManager2(act, icb_up,xp);
			
			tym2.start();
		}
	};
	ICallBack up2=new ICallBack() {
		
		@Override
		public void back(Object obj) {
          XiaoXiGetParam xp=new XiaoXiGetParam();
	    	
	        String phone=BeikBankApplication.getPhoneNumber();
	        xp.limit="40";
	        xp.msg_type=index+"";
	        xp.phone_number=phone;
	        xp.offset=vpu2.start+"";
	        TongYongManager2 tym2=new TongYongManager2(act, icb_up2,xp);
			
			tym2.start();
		}
	};
   /**
    * 没有消息记录时显示消息
    * @param index 1通知 2消息
    */
    private void setNoMessage(int index)
    {
    	if(index==1)
    	{
    		if(list==null||list.size()==0)
    		{
    			ps_message.setVisibility(View.GONE);
    			ps_notice.setVisibility(View.GONE);
    			ll_nomessage.setVisibility(View.VISIBLE);
    		}
    		else
    		{
    			ps_message.setVisibility(View.GONE);
    			ps_notice.setVisibility(View.VISIBLE);
    			ll_nomessage.setVisibility(View.GONE);
    		}
    	}
    	else if(index==2)
    	{
    		if(list2==null||list2.size()==0)
    		{
    			ps_message.setVisibility(View.GONE);
    			ps_notice.setVisibility(View.GONE);
    			ll_nomessage.setVisibility(View.VISIBLE);
    		}
    		else
    		{
    			ps_message.setVisibility(View.VISIBLE);
    			ps_notice.setVisibility(View.GONE);
    			ll_nomessage.setVisibility(View.GONE);
    		}
    	}
    }
   

    /**
     * 加载公告
     */
    private void initData()
    {   
//    	list=new ArrayList<Message>();
//    	Message m1=new Message();
//    	m1.tittle="titl1";
//    	m1.content="send1";
//    	m1.readFlg="0";
//    	m1.sendTime="2014-12-11";
//    	m1.sender="send1";
//    	
//    	Message m2=new Message();
//    	m2.tittle="titl2";
//    	m2.content="send2";
//    	m2.readFlg="1";
//    	m2.sendTime="2015-12-11";
//    	m2.sender="send2";
//    	
//    	list.add(m1);
//    	list.add(m2);
//    	
//    	
//    	listviewDelete = (ScrollListviewDelete) findViewById(android.R.id.list);
//		adapter = new DeleteAdapter(this,list);
//		listviewDelete.setAdapter(adapter);
//		listviewDelete.setOnItemClickListener(this);
//		
		//SysNoticeParam snp=new SysNoticeParam();
		//snp.endLine=end1+"";
		//snp.startLine=start1+"";
		//SysNoticeManager snm=new SysNoticeManager(act, icb1, snp);
		//ThreadManagerSet tms=new ThreadManagerSet(icb);
//    	if(userid!=null)
//    	{
//    		MessageParam mp=new MessageParam();
//    		mp.endLine=end2+"";
//    		mp.startLine=start2+"";
//    		mp.type="";
//    		//mp.userId=BeikBankApplication.getUserid();
//    		mp.userId=userid;
//    		MessageManager mm=new MessageManager(act, icb2, mp);
//    		tms.add(mm);
//    		
//    		
//    		MessageParam2 mp2=new MessageParam2();
//    		mp2.userId=userid;
//    		TongYongManager tym=new TongYongManager(act, icb1,mp2);
//    		tym.start();
//    		tms.start();
//    	}
//    	else
//    	{
//    		MessageParam2 mp2=new MessageParam2();
//    		mp2.userId="";
//    		TongYongManager tym=new TongYongManager(act, icb1,mp2);
//    		tym.start();
//    		
//    	}
   
		
		
		
		
//		 list2=gettest();
//		 adapter2 = new DeleteAdapter(act,list2);
//		 sld2.setAdapter(adapter2);
//	     sld2.setOnItemClickListener(MessageActivity.this);
    }
    
    
    /**
     * 系统公告回调
     */
    ICallBack icb3=new ICallBack() {
		
		@Override
		public void back(Object obj) {
			
			
		}
	};
    
    ICallBack icb=new ICallBack() {
		
		@Override
		public void back(Object obj) {
						
		}
	};
  private void doData2(List<XiaoXiGet> list) 
  {   
	  list2.clear();
	  for(XiaoXiGet msg:list)
	  {
		  list2.add(msg);
	  }
  }
//	   ICallBack icb2=new ICallBack() {
//			
//			@Override
//			public void back(Object obj) {
//				ps_message.onPullUpRefreshComplete();
//				sld2.isLast=false;
//				 if(obj!=null)
//				 {
//					 Message_data md=(Message_data)obj;
//					 doData2(md.data);
//					 if(adapter2==null)
//					 {
//						 //adapter2 = new DeleteAdapter(act,list2,2);
//						 sld2.setAdapter(adapter2);
//					 }
//					 else
//					 {
//						 adapter2.notifyDataSetChanged();
//					 }
//				     sld2.setOnItemClickListener(MessageActivity.this);
//				 }
//			}
//		};
    private List<Message> gettest()
    {
    	List<Message> list=new ArrayList<Message>();
    	for(int i=0;i<10;i++)
    	{
    		Message msg=new Message();
    		msg.content="context"+i;
    		msg.readFlg="1";
    		msg.sender="sender"+i;
    		msg.sendTime="2014-12"+i;
    		msg.tittle="title"+i;
    		msg.updateTime="tpdate"+i;
    		list.add(msg);
    				
    	}
    	return list;
    }
    ICallBack icb1=new ICallBack() {
		
		@Override
		public void back(Object obj) {
			ps_notice.onPullDownRefreshComplete();
			sld1.isLast=false;
			if(obj!=null)
			{
				Message_data md=(Message_data)obj;
			   // list=md.data;
			   // sld1 = (ScrollListviewDelete) findViewById(android.R.id.list);
//			    if(list!=null)
//			    {   
//			    	for(Message msg:list)
//			    	{
//			    		msg.readFlg="0";
//			    	}
//			    	list=getNewMessage();
//			    }
				//adapter = new DeleteAdapter(act,list,1);
				sld1.setAdapter(adapter);
				//sld1.setOnItemClickListener(MessageActivity.this);
				setNoMessage(1);
			}
		}
	};
	
	/**
	 * 得到没有读的消息数量
	 * @return
	 */
	private int setUnReadMsg()
	{   
		int count=0;
//		for(Message msg:list)
//		{
//			if("0".equals(msg.readFlg))
//			{
//				count++;
//			}
//		}
//		for(Message msg:list2)
//		{
//			if("0".equals(msg.readFlg))
//			{
//				count++;
//			}
//		}
//		HomeActivity3.ha.msgCount=count;
		return count;
	}
//	@Override
//	public void onItemClick(int position) 
//	{   
//		Intent intent=new Intent(this,MessageDetail.class);
//		if(index==1)
//		{   
//			if(adapter.itemDelete!=null)
//			{
//				return;
//			}
//			XiaoXiGet msg=(XiaoXiGet) list.get(position);
//			intent.putExtra("msg",msg);
//			intent.putExtra("type","1");
//            if("1".equals(msg.is_read))
//            {
//            	msg.is_read="0";
//            	//adapter.notifyDataSetChanged();
//            	reads1.add(msg);
//            }
//            
//		}
//		else
//		{   
//			if(adapter2.itemDelete!=null)
//			{
//				return;
//			}
//			XiaoXiGet msg=(XiaoXiGet) list2.get(position);
//			intent.putExtra("msg",msg);
//			intent.putExtra("type","2");
//			 if("1".equals(msg.is_read))
//	            {
//	            	msg.is_read="0";
//	            	//adapter2.notifyDataSetChanged();
//	            	reads2.add(msg);
//	            }
//		}
//		startActivity(intent);
//		
//	}
	/**
	 * 将通知或者消息标记
	 */
	private void readMsgOrNotice()
	{   
		doRead2();
	}
	/**
	 * true 已经全部已读，false相反 消息
	 */
	boolean is_msg_read=false;
	/**
	 * true 已经全部已读，false相反  通知
	 */
	boolean is_notice_read=false;
//	/**
//	 * 初始化消息和通知的状态
//	 */
//	private void initMsgAndNotice()
//	{
//		for(Message msg:list)
//		{
//			if("0".equals(msg.readFlg))
//			{
//				is_msg_read=false;
//				break;
//			}
//		}
//		
//		for(Message msg:list2)
//		{
//			if("0".equals(msg.readFlg))
//			{
//				is_notice_read=false;
//				break;
//			}
//			
//		}
//	}

	List<XiaoXiGet> reads1=new ArrayList<XiaoXiGet>();
	List<XiaoXiGet> reads2=new ArrayList<XiaoXiGet>();
	
	/**
	 * 部分标记
	 */
	private void doRead2()
	{   
		if(userid==null||userid.equals(""))
		{
			finish();
			return;
		}
		
				
		if(reads2.size()<=0&&reads1.size()<=0)
		{   
			finish();
			return;
		}
		
		if(reads1.size()>0)
		{
//		   TableDaoSimple.createTable(Message.class);
//		   for(Message msg:reads1)
//		   { 
//				TableDaoSimple.insert(msg);
//		   }
			if(userid!=null&&!userid.equals(""))
			{
				MessageParam3 mp3=new MessageParam3();
				mp3.userId=userid;
				mp3.noticeId=getAllId(reads1);
				if(mp3.noticeId!=null&&!mp3.noticeId.equals(""))
				{
//				TongYongManager tym=new TongYongManager(act, icb7,mp3);
//				tym.start();
//			    	finish();
				}
			}
			
			
			
		}
		setUnReadMsg();
		if(reads2.size()>0)
		{
		MessageReadParam mp=new MessageReadParam();
		mp.userId=userid;
		mp.id=getAllId(reads2);
		if(mp.id!=null&&!mp.id.equals(""))
		{
		  MessageReadManager mrm=new MessageReadManager(act, icb5, mp);
		  mrm.start();
		  finish();
		}
		}
		
	}
	/**
	 * 标记公告已读后回调
	 */
	ICallBack icb6=new ICallBack() {
		
		@Override
		public void back(Object obj) {
			// TODO Auto-generated method stub
			
		}
	};
	/**
	 * 全部标记
	 */
	private void doRead()
	{   
		if(index==1)
		{   
			String s1="";
			for(int i=0;i<list.size();i++)
			{   
				XiaoXiGet xg=(XiaoXiGet) list.get(i);
				if(i==0)
				{
					s1+=xg.msg_log_id;
				}
				else
				{
					s1+=","+xg.msg_log_id;
				}
			}
			if(!"".equals(s1))
			{
			read(s1);
			}
		}
		else
		{
			String s1="";
			for(int i=0;i<list.size();i++)
			{
				XiaoXiGet xg=(XiaoXiGet) list.get(i);
				if(i==0)
				{
					s1+=xg.msg_log_id;
				}
				else
				{
					s1+=","+xg.msg_log_id;
				}
			}
			if(!"".equals(s1))
			{
			   read(s1);
			}
		}
		//initMsgAndNotice();
//		if(index==1)
//		{
//			if(!is_notice_read&&userid!=null)
//			{   
////				int count=0;
////				TableDaoSimple.delete(Message.class,null,null);
////				TableDaoSimple.createTable(Message.class);
////				for(Message msg:list)
////				{   
////					if("0".equals(msg.readFlg))
////					{
////						msg.readFlg="1";
////						TableDaoSimple.insert(msg);
////					}
////					else
////					{   
////						msg.readFlg="1";
////						TableDaoSimple.insert(msg);
////						count++;
////					}
////				}
////				HomeActivity2.ha.msgCount-=count;
////				adapter.notifyDataSetChanged();
////				is_notice_read=true;
//				MessageParam3 mp3=new MessageParam3();
//				mp3.userId=userid;
//				mp3.noticeId=getAllId2(list);
//				if(mp3.noticeId!=null&&!mp3.noticeId.equals(""))
//				{
//				 TongYongManager tym=new TongYongManager(act, icb7,mp3);
//				 tym.start();
//				}
//			}
//		}
//		if(index==2)
//		{   //标记为已读 
//			if(!is_msg_read&&userid!=null)
//			{  
//				MessageReadParam mp=new MessageReadParam();
//				mp.userId=userid;
//				mp.id=getAllId2(list2);
//				if(mp.id!=null&&!mp.id.equals(""))
//						{
//				MessageReadManager mrm=new MessageReadManager(act, icb4, mp);
//				mrm.start();
//			}
//			}
//		}
	}
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
		 
//		 if(list!=null)
//		 {   
//			 net=new HashMap<String, Message>();
//			 for(Message msg:list)
//			 {
//				 net.put(msg.id,msg);
//			 }
//		 }
//		 
//		 if(net==null&&db!=null)
//		 {
//			 return listd;
//		 }
//		 else if(net!=null&&db==null)
//		 {
//			 return list;
//		 }
//		 else if(net==null&&db==null)
//		 {
//			 return null;
//		 }
//		 
		 
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
	/**
	 * 选出未标记的
	 * @param list
	 * @return
	 */
	private String getAllId2(List<Message> list)
	{
		StringBuffer sb=new StringBuffer();
		
		for(int i=0;i<list.size();i++)
		{  
			Message msg=list.get(i);
			if(msg.readFlg.equals("1"))
			{
				continue;
			}
			if(i==list.size()-1)
			{
				sb.append(list.get(i).id);
			}
			else
			{
				sb.append(list.get(i).id+",");
			}
		}
		return sb.toString();
	}
	private String getAllId(List<XiaoXiGet> list)
	{
		StringBuffer sb=new StringBuffer();
		
		for(int i=0;i<list.size();i++)
		{  
			XiaoXiGet msg=list.get(i);
//			if(msg.readFlg.equals("1"))
//			{
//				continue;
//			}
			if(i==list.size()-1)
			{
				sb.append(list.get(i).msg_log_id);
			}
			else
			{
				sb.append(list.get(i).msg_log_id+",");
			}
		}
		return sb.toString();
	}
	
	ICallBack icb5=new ICallBack() {
		
		@Override
		public void back(Object obj) {
			if(obj!=null)
			{
				setUnReadMsg();
			}
			finish();
		}
	};
	//标记通知已读回调接口
//		ICallBack icb7=new ICallBack() {
//			
//			@Override
//			public void back(Object obj) {
//				if(obj!=null)
//				{   
//					/**
//					 * 未读消息减少的数量
//					 */
//					int count=0;
//					for(Message msg:list)
//					{  
//						if("0".equals(msg.readFlg))
//						{
//							msg.readFlg="1";
//							count++;
//						}
//					}
//					adapter.notifyDataSetChanged();
//					is_msg_read=true;
//					reads1.clear();
//					HomeActivity3.ha.msgCount-=count;
//				}
//				
//			}
//		};
	//标记消息已读回调接口
	ICallBack icb4=new ICallBack() {
		
		@Override
		public void back(Object obj) {
			if(obj!=null)
			{   
				/**
				 * 未读消息减少的数量
				 */
				int count=0;
//				for(Message msg:list2)
//				{  
//					if("0".equals(msg.readFlg))
//					{
//						msg.readFlg="1";
//						count++;
//					}
//				}
				adapter2.notifyDataSetChanged();
				is_msg_read=true;
				reads2.clear();
				HomeActivity3.ha.msgCount-=count;
			}
		}
	};
	boolean is_all;
	@Override
	public void onClick(View v) 
	{
		switch (v.getId()) {
		case R.id.linear_left:
			//HomeActivity3.isMsg=true;
			//readMsgOrNotice();
			finish();
			break;
		case R.id.right:
			is_all=true;
            doRead();
			break;
		case R.id.notice:
			notice.setTextColor(0xffffffff);
			notice.setBackgroundResource(R.drawable.bg_sysnotice_left);
			message.setBackgroundColor(0x00ffffff);
			message.setTextColor(0xffdd2238);
//			ll.removeAllViews();
//			ll.addView(ll4);
			//ps_notice.setVisibility(View.VISIBLE);
			//ps_message.setVisibility(View.GONE);
			index=1;
			//setNoMessage(index);
			 ll_pull.removeAllViews();
			 ll_pull.addView(ll2);
			break;
		case R.id.message:
			notice.setTextColor(0xffdd2238);
			notice.setBackgroundColor(0x00ffffff);
			message.setBackgroundResource(R.drawable.bg_sysnotice_right2);
			message.setTextColor(0xffffffff);
			//ps_notice.setVisibility(View.GONE);
			//ps_message.setVisibility(View.VISIBLE);
//			ll.removeAllViews();
//			ll.addView(ll3);
			index=2;
			//setNoMessage(index);
			  ll_pull.removeAllViews();
			  ll_pull.addView(ll22);
			  vpu2.prl.doPullRefreshing(true,200);
			break;
		default:
			break;
		}
		
	}
	/**
	 * 设置为已读
	 */
	private void read(String id)
	{   
		boolean islogin=BeikBankApplication.mSharedPref.getSharePrefBoolean(BeikBankConstant.DO_SUCCESS,false);
		
		if(!islogin)
		{
			return;
		}
		ICallBack icb=new ICallBack() {
			
			@Override
			public void back(Object obj) {
				// TODO Auto-generated method stub
				if(is_all)
				{      if(index==1)
				   {
					   vpu.prl.doPullRefreshing(true,200);
				   }
				  else
				  {
					 vpu2.prl.doPullRefreshing(true,200);
				  }
				}
			}
		};
		XiaoXiReadParam xp=new XiaoXiReadParam();
		xp.msg_list=id;
		TongYongManager2 tym2=new TongYongManager2(act, icb,xp);
		tym2.start();
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK)
		{   
			HomeActivity3.isMsg=true;
			readMsgOrNotice();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	@Override
	protected void onRestart() {
		super.onRestart();
		if(adapter!=null)
		{
			adapter.notifyDataSetChanged();
		}
		if(adapter2!=null)
		{
			adapter2.notifyDataSetChanged();
		}
	}
	
	
}
