package com.beikbank.android.activity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.beikbank.android.activity.help.NoneData;
import com.beikbank.android.adapter.MBaseAdapter;
import com.beikbank.android.adapter.TransactionRecordsAdapter;
import com.beikbank.android.adapter.UserRecordAdapteer;
import com.beikbank.android.data.UserRecord;
import com.beikbank.android.data.UserRecord2;
import com.beikbank.android.data.UserRecord_data;
import com.beikbank.android.data2.getLeiJiShouYi;
import com.beikbank.android.data2.getLeiJiShouYi_data;
import com.beikbank.android.data2.getMeiRiShouYi;
import com.beikbank.android.data2.getMeiRiShouYi_data;
import com.beikbank.android.dataparam.UserRecordParam;
import com.beikbank.android.dataparam2.getJiaoYiJiLvParam;
import com.beikbank.android.dataparam2.getLeiJiShouYiParam;
import com.beikbank.android.dataparam2.getMeiRiShouYiParam;
import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.net.HandlerBase;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.impl.TongYongManager2;
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
 *
 * 收益明细 2.0
 */
public class UserRecordActivity_v2 extends BaseActivity1 implements OnClickListener
{   

	
	
	/**
	 * 返回
	 */
    LinearLayout left;
    /**
     * 标题
     */
    TextView title;

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
   // LinearLayout ll2;
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
    /**
     * 近一周收益
     */
    String money2;
    
    ListView lv;
    String product_type_id;
    String name;
    TextView tv_leiji;
    LinearLayout ll_parent;
    
    
    
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
    MBaseAdapter tra;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_userrecord_v2);
		StateBarColor.init(this,0xffffffff);
		product_type_id=getIntent().getStringExtra("product_type_id");
    	money=getIntent().getStringExtra("money");
    	name=getIntent().getStringExtra("name");
		initView();
	    //initData();
		
	}

    private  void initView()
    {
    	left=(LinearLayout) findViewById(R.id.linear_left);
    	title=(TextView) findViewById(R.id.titleTv);
    	title.setText(name+"收益");
    	left.setOnClickListener(this);
    	ll=(LinearLayout) findViewById(R.id.ll);
    	tv_leiji=(TextView) findViewById(R.id.tv_leiji);
//    	String money=getIntent().getStringExtra("money");
//    	total=(TextView) findViewById(R.id.total_money);
//    	total.setText(money);
    	lv=(ListView) findViewById(R.id.lv);
    	String  s=NumberManager.getGeshiHua(money,2);
        tv_leiji.setText(s);
		
		ll_parent=(LinearLayout) findViewById(R.id.ll);
		
		
		
		   ll_pull=(LinearLayout) findViewById(R.id.ll);
	       vpu=new ViewRullUtil(down, up);
	      
	       
	       tra=new UserRecordAdapteer(act, money,1);
		   vpu.adapter=tra;
		  
	       ll2=vpu.initView(this,null);
	       vpu.init(1);
		   ll_pull.removeAllViews();
		   ll_pull.addView(ll2);
		   DensityUtil du=new DensityUtil(act);
		   vpu.mListView.setDividerHeight(du.dip2px(10));
		   vpu.prl.doPullRefreshing(true,200);

    }
 ICallBack icb_down=new ICallBack() {
	
	@Override
	public void back(Object obj) {
		if(obj!=null)
		{
			gd=(getMeiRiShouYi_data) obj;
			List<UserRecord2> list;
			List<UserRecord> list0=toUserRecord(gd.body);
			list=count(list0);
			//adapter=new UserRecordAdapteer(act,null, money,1);
			//lv.setAdapter(adapter);
			//lv.setEnabled(false);
			 vpu.doDownCompelete(list);
		}
		else
		{
			 vpu.doDownCompelete(null);
		}
		if(gd==null||gd.body.size()==0)
		{
			NoneData.setView(act, ll_parent,10);
		}
		
	}
};  
ICallBack icb_up=new ICallBack() {
	
	@Override
	public void back(Object obj) {
		if(obj!=null)
		{
			gd=(getMeiRiShouYi_data) obj;
			List<UserRecord2> list;
			List<UserRecord> list0=toUserRecord(gd.body);
			list=count(list0);
			//adapter=new UserRecordAdapteer(act,null, money,1);
			//lv.setAdapter(adapter);
			//lv.setEnabled(false);
			 vpu.doDownCompelete(list);
		}
		else
		{
			 vpu.doDownCompelete(null);
		}
		if(gd==null||gd.body.size()==0)
		{
			NoneData.setView(act, ll_parent,10);
		}
		
	}
};
ICallBack down=new ICallBack() {
		
		@Override
		public void back(Object obj) {
	
			
			
			
			
			getMeiRiShouYiParam gmr=new getMeiRiShouYiParam();
	    	gmr.page_index=vpu.start+"";
	    	gmr.page_size=vpu.size+"";
	    	gmr.user_id=BeikBankApplication.getUserCode();
	    	gmr.product_type_id=product_type_id;
	    	TongYongManager2 tym2=new TongYongManager2(act, icb_down,gmr);
	    	tym2.start();
		}
	};
	ICallBack up=new ICallBack() {
		
		@Override
		public void back(Object obj) {

			getMeiRiShouYiParam gmr=new getMeiRiShouYiParam();
			gmr.page_index=vpu.start+"";
	    	gmr.page_size=vpu.size+"";
	    	gmr.user_id=BeikBankApplication.getUserCode();
	    	gmr.product_type_id=product_type_id;
	    	TongYongManager2 tym2=new TongYongManager2(act, icb_up,gmr);
	    	tym2.start();
		}
	};
    getMeiRiShouYi_data gd;
    private void initData()
    {
       ICallBack icb_lei=new ICallBack() {
			
			@Override
			public void back(Object obj) {
				if(obj!=null)
				{
					gd=(getMeiRiShouYi_data) obj;
					List<UserRecord2> list;
					List<UserRecord> list0=toUserRecord(gd.body);
					list=count(list0);
					adapter=new UserRecordAdapteer(act,null, money,1);
					lv.setAdapter(adapter);
					lv.setEnabled(false);
				}
				if(gd==null||gd.body.size()==0)
				{
					NoneData.setView(act, ll_parent,10);
				}
				
			}
		};
    	getMeiRiShouYiParam gmr=new getMeiRiShouYiParam();
    	gmr.page_index="1";
    	gmr.page_size="40";
    	gmr.user_id=BeikBankApplication.getUserCode();
    	gmr.product_type_id=product_type_id;
    	TongYongManager2 tym2=new TongYongManager2(act, icb_lei,gmr);
    	tym2.start();
    }
    private List<UserRecord> toUserRecord(ArrayList<getMeiRiShouYi> list0)
    {
    	
    	List<UserRecord> list=new ArrayList<UserRecord>();
    	for(int i=0;i<list0.size();i++)
    	{
    		UserRecord ur=new UserRecord();
    		ur.dealTime=list0.get(i).trade_time;
    		ur.interest=list0.get(i).amout_total;
    		list.add(ur);
    	}
    	
    	
    	return list;
    	
    }
	/**
	 * 计算进度条相关相
	 */
	private  List<UserRecord2> count(List<UserRecord> list)
	{   
		
		
		List<UserRecord2> list11=new ArrayList<UserRecord2>();
		if(list.size()==0)
		{
			return list11;
		}
		try
		{
		DensityUtil du=new DensityUtil(this);
		int dp32=du.dip2px(32);
		int width=BeikBankApplication.getWidth(act);
		totalWidth=width-dp32;
		double max=0;
		double min=-100;
		//找出最大的一项UserRecord ur:list1
		for(int i=0;i<list.size();i++)
		{   
			UserRecord ur=list.get(i);
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
		
		list12.add(new UserRecord2());
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String str=list.get(0).dealTime;
		List<String> dates=DateManager.convertWeekByDate(sdf.parse(str));
 		//计算每项的宽度
		for(int i=0;i<list.size();i++)
		{   
			UserRecord ur=list.get(i);
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
//			if(i<=6)
//			{
//				list12.add(ur2);
//			}
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
		return list11;
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
				String s=ur.interest;
//				if(s==null)
//				{
//					continue;
//				}
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
//				if(s==null)
//				{
//					continue;
//				}
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


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.linear_left:
			finish();
			break;
		
		default:
			break;
		}
		
	}

}
