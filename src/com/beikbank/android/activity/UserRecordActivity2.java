package com.beikbank.android.activity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.beikbank.android.adapter.UserRecordAdapteer;
import com.beikbank.android.data.UserRecord;
import com.beikbank.android.data.UserRecord2;
import com.beikbank.android.data.UserRecord3_data;
import com.beikbank.android.data.UserRecord4;
import com.beikbank.android.data.UserRecord_data;
import com.beikbank.android.dataparam.UserRecord3Param;
import com.beikbank.android.dataparam.UserRecord3Param2;
import com.beikbank.android.dataparam.UserRecordParam;
import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.net.HandlerBase;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.impl.TongYongManager;
import com.beikbank.android.net.impl.TranListManager;
import com.beikbank.android.net.impl.UserRecordManager;
import com.beikbank.android.pullrefresh.PullToRefreshBase;
import com.beikbank.android.pullrefresh.PullToRefreshListView;
import com.beikbank.android.pullrefresh.PullToRefreshBase.OnRefreshListener;
import com.beikbank.android.utils.DateManager;
import com.beikbank.android.utils.DensityUtil;
import com.beikbank.android.utils.NumberManager;
import com.beikbank.android.utils.ViewRullUtil;
import com.beikbank.android.utils2.StateBarColor;

import comc.beikbank.android.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

/**
 * @author yuguohe
 * <p>
 * email:1374405188@qq.com
 * </p>
 *2015-3-2
 *定期 用户收益
 */
public class UserRecordActivity2 extends BaseActivity1 implements OnClickListener
{   
    int start=0;
    int end=19;
    int count=20;
	/**
	 * 定期计数
	 */
	int count2=1;
	
	/**
	 * 定期计数v2
	 */
	int count3=20;
	/**
	 * 定期开始计算
	 */
	int startCount=0;
	/**
	 * 请求到了结束
	 */
	boolean isend=false;
	/**
	 * 返回
	 */
    LinearLayout left;
    /**
     * 标题
     */
    TextView title;
    /**
     * 1全部收益，2近一周收益
     */
    String index="1";
    /**
     * 全部收益
     */
    TextView all;
    /**
     * 近一周收益
     */
    TextView week;
    private PullToRefreshListView listview_transaction_records;
    private PullToRefreshListView listview_transaction_records2;
    /**
     * 全部收益
     */
    private ListView mListView;
    /**
     * 近一周收益
     */
    private ListView mListView2;
    private UserRecordAdapteer adapter;
    private UserRecordAdapteer adapter2;
    
    Activity act=this;
    /**
     * 全部收益
     */
    List<UserRecord> list1=new ArrayList<UserRecord>();
    /**
     * 计算过的全部收益
     */
    List<UserRecord2> list11=new ArrayList<UserRecord2>();
    /**
     * 计算过的一周收益
     */
    List<UserRecord2> list12=new ArrayList<UserRecord2>();
    /**
     * 全部收益
     */
    LinearLayout ll1;
    /**
     * 近一周收益
     */
    LinearLayout ll2;
    /**
     * 进度条总宽度
     */
    int totalWidth;
    /**
     * listview父布局
     */
    LinearLayout ll;
    String userid;
    /**
     * 总收益
     */
    TextView total;
    /**
     * 累计收益
     */
    String money;
  
    
    ViewRullUtil vpu;
    LinearLayout ll3;
    
    
    
    private void initView2()
    {
    	vpu=new ViewRullUtil(down,up);
    	ll3=vpu.initView(this,null);
    	
    	
    	adapter=new UserRecordAdapteer(act,list11,money,1);
		adapter.endCount=2;
		vpu.adapter=adapter;
		ll.addView(ll3);
		vpu.prl.doPullRefreshing(true,200);
    	
    }
    ICallBack down=new ICallBack() {
		
		@Override
		public void back(Object obj) {
			initData();
		}
	};
	  ICallBack up=new ICallBack() {
			
			@Override
			public void back(Object obj) {
				// TODO Auto-generated method stub
				
			}
		};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_userrecord);
		StateBarColor.init(this,0xffffffff);
		initView();
		//initView2();
		//index=getIntent().getStringExtra("index");
		//initTitle(index);
//		UserRecordParam urp=new UserRecordParam();
//		urp.startLine="0";
//		urp.endLine="19";
//		urp.memberID=BeikBankApplication.getUserid();
//		//urp.memberID=userid;
//		start+=count;
//		end+=count;
		list11.add(new UserRecord2());
		list12.add(new UserRecord2());
//		new UserRecordManager(act, icb, urp).start();
		money=getIntent().getStringExtra("money");
		
		//adapter=new UserRecordAdapteer(act,list11,money,1);
		initData();
	}
	private void initData()
	{   
//		UserRecord3Param up3=new UserRecord3Param();
//		up3.memberID=BeikBankApplication.getUserid();
//		up3.monthsBefore=count2+"";
		
		UserRecord3Param2 up3=new UserRecord3Param2();
		up3.memberID=BeikBankApplication.getUserid();
		up3.page=startCount+"";
		up3.pageSize=count3+"";

		TongYongManager tym=new TongYongManager(act, icb2,up3);
		tym.start();
		
	}
	ICallBack icb2=new ICallBack() {
		
		@Override
		public void back(Object obj) {
			if(obj!=null)
			{ 
			  UserRecord3_data ud3=(UserRecord3_data) obj;
			  UserRecord_data ud=new UserRecord_data();
			  if(ud3.data!=null)
			  { 
				  
				if(isend)
				{
					 ud=new UserRecord_data();
					 ud.data=new ArrayList<UserRecord>();
					 icb.back(ud);
					 return;
				}
			    ud.data=soft(ud3.data.termbond);
			    icb.back(ud);
			    if(ud.data.size()>=count3)
			    {
			       count3+=20;
			       startCount+=20+1;
			    }
			    else
			    {
			    	isend=true;
			    }
			  }
			  else
				{
					 ud=new UserRecord_data();
					 ud.data=new ArrayList<UserRecord>();
					 icb.back(ud);
				}
			}
			else
			{
//				 UserRecord_data ud=new UserRecord_data();
//				 ud.data=new ArrayList<UserRecord>();
//				 icb.back(ud);
			}
		}
	};
	private ArrayList<UserRecord> soft(ArrayList<UserRecord4> list)
	{   
		//日期
		ArrayList<String> list1=new ArrayList<String>();
		//收益
		ArrayList<String> list2=new ArrayList<String>();
		int b=-1;
		for(int i=0;i<list.size();i++)
		{
			b=soft2(list1,list.get(i).dealTime);
			if(b>=0)
			{
				String s=list2.get(b);
				s=NumberManager.getAddString(s,list.get(i).interest,4);
				//list2.add(b,s);
				list2.set(b,s);
			}
			else
			{
				list1.add(list.get(i).dealTime);
				list2.add(list.get(i).interest);
			}
		}
		ArrayList<UserRecord> list3=new ArrayList<UserRecord>();
		UserRecord ur2=null;
		for(int i=0;i<list1.size();i++)
		{
			ur2=new UserRecord();
			ur2.dealTime=list1.get(i);
			ur2.interest=NumberManager.getString(list2.get(i),"1",2);
			list3.add(ur2);
		}
		Collections.sort(list3);
		return list3;
	}
	/**
	 * 返回s在集合中的索引，没有的话返回-1
	 * @param list1
	 * @param s
	 * @return
	 */
	private int soft2(ArrayList<String> list1,String s)
	{   
		int b=-1;
		for(int i=0;i<list1.size();i++)
		{  
			if(list1.get(i).equals(s))
			{
				return i;
			}
		}
		return b;
	}
	String pltext;
    private  void initView()
    {
    	left=(LinearLayout) findViewById(R.id.linear_left);
    	title=(TextView) findViewById(R.id.titleTv);
    	title.setText("赚啦定期收益");
    	all=(TextView) findViewById(R.id.all);
    	week=(TextView) findViewById(R.id.week);
    	all.setOnClickListener(this);
    	week.setOnClickListener(this);
    	left.setOnClickListener(this);
    	ll=(LinearLayout) findViewById(R.id.ll);
//    	String money=getIntent().getStringExtra("money");
//    	total=(TextView) findViewById(R.id.total_money);
//    	total.setText(money);
    	
    	LinearLayout ll10=new LinearLayout(this);
    	ll1=(LinearLayout) getLayoutInflater().inflate(R.layout.userrecord_list,ll10,false);
    	ll2=(LinearLayout) getLayoutInflater().inflate(R.layout.userrecord_list,left,false);
    	listview_transaction_records=(PullToRefreshListView) ll1.findViewById(R.id.listview_transaction_records);
    	
    	
    	listview_transaction_records2=(PullToRefreshListView) ll2.findViewById(R.id.listview_transaction_records);
    	
    	
    	ll.addView(ll1);
    	listview_transaction_records.setPullLoadEnabled(true);
		listview_transaction_records.setScrollLoadEnabled(false);
		listview_transaction_records.setPullRefreshEnabled(false);
		
		listview_transaction_records2.setPullLoadEnabled(false);
		listview_transaction_records2.setScrollLoadEnabled(false);
		listview_transaction_records2.setPullRefreshEnabled(false);
		
		DensityUtil du=new DensityUtil(act);
		int a=du.dip2px(12);
		
		mListView=listview_transaction_records.getRefreshableView();
		mListView.setDivider(getResources().getDrawable(R.color.line6));
		mListView.setDividerHeight(a);
		mListView.setScrollingCacheEnabled(false);
		mListView.setFadingEdgeLength(0);
		//mListView.setAdapter(adapter);
		
		mListView2=listview_transaction_records2.getRefreshableView();
		mListView2.setDivider(getResources().getDrawable(R.color.line6));
		mListView2.setDividerHeight(a);
		mListView2.setScrollingCacheEnabled(false);
		mListView2.setFadingEdgeLength(0);
		
		listview_transaction_records.setOnRefreshListener(new OnRefreshListener<ListView>() {
			@Override
			public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
               System.out.println(122);
				
			}

			@Override
			public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
				if(pltext!=null)
				{
					refreshView.setLastUpdatedLabel("已全部加载完毕");
					return;
				}
//				UserRecordParam urp=new UserRecordParam();
//				urp.startLine=start+"";
//				urp.endLine=end+"";
//				urp.memberID=BeikBankApplication.getUserid();
//				//urp.memberID="4811";
//				new UserRecordManager(act, icb, urp).start();
//				start+=count;
//				end+=count;
//				if(count2<=6)
//				{ 
				  count2+=count;
				  initData();
				//}
//				else
//				{
//					HandlerBase.showMsg(act,"没有更多数据了",0);
//				}
			}
		});
//		list1=new ArrayList<UserRecord>();
//		UserRecord ur=new UserRecord();
//		UserRecord ur2=new UserRecord();
//		list1.add(ur);
//		list1.add(ur2);
//		adapter=new UserRecordAdapteer(act, list1);
//		mListView.setAdapter(adapter);
    }
    /**
     * 处理数据
     */
    private void doData(List<UserRecord> list)
    {
    	for(UserRecord use:list)
    	{
    		list1.add(use);
    	}
    }
    ICallBack icb=new ICallBack() {
		
		@Override
		public void back(Object obj) {
		listview_transaction_records.onPullUpRefreshComplete();
		listview_transaction_records.onPullDownRefreshComplete();
		  if(obj!=null)
		  {
			  UserRecord_data ud=(UserRecord_data) obj;
			  
			  
			  if(ud.data.size()>=0)
			  {   
				  List<UserRecord> list=ud.data;
				  doData(list);
				  count();
				  //count2();
				  if(adapter==null)
				  {
					  adapter=new UserRecordAdapteer(act,list11,money,1);
					  adapter.endCount=2;
					  mListView.setAdapter(adapter);
				  }
				  else
				  {
					  adapter.notifyDataSetChanged();
				  }
//				  adapter=new UserRecordAdapteer(act,list11,money,1);
//				  mListView.setAdapter(adapter);
//				  //adapter.notifyDataSetChanged();
				  if(adapter2==null)
				  {  
					  String s="0";
					  for(UserRecord2 lists:list12)
					  {
						  s=NumberManager.getAddString(s,lists.interest,4);
					  }
					  adapter2=new UserRecordAdapteer(act,list12,s,2);
					  adapter2.endCount=2;
					  mListView2.setAdapter(adapter2);
				  }
				
//				  if("1".equals(index))
//				  {   
//					  ll.removeAllViews();
//					  ll.addView(ll1);
//				  }
//				  else
//				  {   
//					  ll.removeAllViews();
//					  ll.addView(ll2);
//				  }
			  }
			  else
			  {  pltext="完成";
				 // HandlerBase.showMsg(act,"已加载全部数据",0);
			  }
		  }
		  else
		  {   
//			  list11=new ArrayList<UserRecord2>();
			  adapter=new UserRecordAdapteer(act,list11,money,1);
			  mListView.setAdapter(adapter);
			  
			  
			  list12=new ArrayList<UserRecord2>();
			  adapter2=new UserRecordAdapteer(act,list12,money,2);
			  mListView2.setAdapter(adapter2);
		  }
			
		}
	};
	
	/**
	 * 计算进度条相关相
	 */
	private  void count()
	{  
		try
		{
		DensityUtil du=new DensityUtil(this);
		int dp32=du.dip2px(32);
		int width=BeikBankApplication.getWidth(act);
		totalWidth=width-dp32;
		double max=0;
		double min=-100;
		//找出最大的一项UserRecord ur:list1
		for(int i=0;i<list1.size();i++)
		{   
			UserRecord ur=list1.get(i);
			String s=ur.interest;
			double interest=Double.parseDouble(s);
			if(interest>max)
			{
				max=interest;
			}
			if(min==-100)
			{
				min=interest;
			}
			else
			{   
				if(min>interest)
				{
					min=interest;
				}
			}
		}
		list11.clear();
		list12.clear();
		list11.add(new UserRecord2());
		list12.add(new UserRecord2());
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String str=list1.get(0).dealTime;
		List<String> dates=DateManager.convertWeekByDate(sdf.parse(str));
 		//计算每项的宽度
		for(int i=0;i<list1.size();i++)
		{   
			UserRecord ur=list1.get(i);
			String s=ur.interest;
			double interest=Double.parseDouble(s);
			double scale=interest/max;
			//int w=(int)((double)totalWidth*scale);
			int w=0;
			w=getViewWidth(max, min,interest,totalWidth);
			UserRecord2 ur2=new UserRecord2();
			ur2.dealTime=ur.dealTime;
			ur2.interest=ur.interest;
			ur2.width=w;
			
			list11.add(ur2);
			if(i<=6)
			{
				list12.add(ur2);
			}
//			for(String date:dates)
//			{
//				if(date.equals(ur.dealTime))
//				{
//					list12.add(ur2);
//				}
//			}
		}
		
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void count2()
	{
		try 
		{
			DensityUtil du=new DensityUtil(this);
			int dp32=du.dip2px(32);
			int width=BeikBankApplication.getWidth(act);
			totalWidth=width-dp32;
			double max=0;
			double min=-100;

			//找出最大的一项UserRecord ur:list1
			for(int i=0;i<list12.size();i++)
			{   
				
				UserRecord2 ur=list12.get(i);
				if(ur.interest==null)
				{
					continue;
				}
				String s=ur.interest;
				double interest=Double.parseDouble(s);
				if(interest>max)
				{
					max=interest;
				}
				if(min==-100)
				{
					min=interest;
				}
				else
				{   
					if(min>interest)
					{
						min=interest;
					}
				}
			}
			for(int i=0;i<list12.size();i++)
			{ 
				UserRecord2 ur=list12.get(i);
				String s=ur.interest;
				if(ur.interest==null)
				{
					continue;
				}
				double interest=Double.parseDouble(s);
				double scale=interest/max;
				//int w=(int)((double)totalWidth*scale);
				int w=0;
				w=getViewWidth(max, min,interest,totalWidth);
				ur.width=w;
			}
			
		} catch
		(Exception e) 
		{
			e.printStackTrace();
		}
	}
	/**
	 * 计算进度条宽度
	 * @return
	 */
	private int getViewWidth(double max,double min,double count,double totalwidth)
	{
		int i=0;
		double a=max-min;
		double b=max-count;
		double c=1d;
		if(a!=0)
		{
			c=1d-(b/a);
		}
		i=(int) (totalwidth*c);
		if(i<totalwidth*0.6)
		{
			i=(int) (totalwidth*0.6);
		}
		return i;
	}

	private void initTitle(String index)
	{
		if("1".equals(index))
		{
			all.setBackgroundResource(R.drawable.bg_userrecord_left);
			all.setTextColor(0xffffffff);
			week.setBackgroundColor(0x00000000);
			week.setTextColor(0xffdd2238);
			ll.removeAllViews();
			ll.addView(ll1);
		}
		else
		{
			all.setBackgroundColor(0x00000000);
			all.setTextColor(0xffdd2238);
			week.setBackgroundResource(R.drawable.bg_userrecord_right);
			week.setTextColor(0xffffffff);
			ll.removeAllViews();
			ll.addView(ll2);
		}
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.linear_left:
			finish();
			break;
		case R.id.all:
			initTitle("1");
			index="1";
			break;
		case R.id.week:
			initTitle("2");
			index="2";
			break;
		default:
			break;
		}
		
	}
   
}
