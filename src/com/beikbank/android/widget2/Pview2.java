package com.beikbank.android.widget2;

import java.util.ArrayList;
import java.util.List;

import com.beikbank.android.activity.DingqiDetailActivity;
import com.beikbank.android.activity.DingqiGoumaiActivity;
import com.beikbank.android.activity.HomeActivity3;
import com.beikbank.android.activity.HuodongActivity2;
import com.beikbank.android.activity.help.GoumaiManager;
import com.beikbank.android.activity.help.LiuChenManager;
import com.beikbank.android.activity.help.TypeUtil;
import com.beikbank.android.conmon.FinalIndex;
import com.beikbank.android.data.Biaoshi;
import com.beikbank.android.data.Biaoshi_data;
import com.beikbank.android.data.DingqiP2;
import com.beikbank.android.data.DingqiP_data2;
import com.beikbank.android.dataparam.BiaoshiParam;
import com.beikbank.android.dataparam.UserParam;
import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.ImageUrl;
import com.beikbank.android.net.ManagerParam;
import com.beikbank.android.net.impl.TongYongManager;
import com.beikbank.android.pullrefresh.PullToRefreshBase;
import com.beikbank.android.pullrefresh.PullToRefreshScrollView;
import com.beikbank.android.pullrefresh.PullToRefreshBase.OnRefreshListener;
import com.beikbank.android.utils.BeikBankConstant;
import com.beikbank.android.utils.DateManager;
import com.beikbank.android.utils.DensityUtil;
import com.beikbank.android.utils.NumberManager;
import com.beikbank.android.utils.PageUtil;
import com.beikbank.android.utils.ViewDataUtil;
import com.beikbank.android.widget.MagicTextView;

import comc.beikbank.android.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * 
 * @author Administrator
 *定期
 */
public class Pview2 extends LinearLayout implements OnClickListener
{
	private PullToRefreshScrollView prs;
    private ScrollView sv;
    private Activity act;
    /**
     * 年化收益率
     */
    private   MagicTextView tv11;
    /**
     * 加真年化收益率
     */
    private TextView tv12;
    /**
     * 万份收益
     */
    private TextView tv13;
    /**
     * 剩余可够余额
     */
    private TextView tv14;
    private 
    /**
     * 选几个月父布局
     */
     LinearLayout ll2;
    
    /**
     * 标识
     */
    private ImageView iv5;
    private Button bu_goumai;
    /**
     * 详情
     */
    private LinearLayout ll_xiangqing;
    private YuanView yv;
	public Pview2(Context context) {
		super(context);
		init((Activity) context);
	}
	public Pview2(Context context, AttributeSet attrs) {
		super(context, attrs);
		
	}
    /**
     * 初始化布局
     * @param view
     */
	private void init2(View view)
	{
		 tv11=(MagicTextView) view.findViewById(R.id.tv11);
		 tv12=(TextView) view.findViewById(R.id.tv12);
		 tv13=(TextView) view.findViewById(R.id.tv13);
		 tv14=(TextView) view.findViewById(R.id.tv14);
		 bu_goumai=(Button) view.findViewById(R.id.bu_goumai);
		 ll_xiangqing=(LinearLayout) view.findViewById(R.id.ll_xiangqing);
		 ll_xiangqing.setOnClickListener(this);
		 ll2=(LinearLayout) view.findViewById(R.id.ll2);
		 
		 bu_goumai.setOnClickListener(this);
	}
	public void init(Activity context)
	{   
		act=context;
		LinearLayout ll=new LinearLayout(context);
		View view0 =LayoutInflater.from(context).inflate(R.layout.page_wealth11,ll,false);
		
		View view= LayoutInflater.from(context).inflate(R.layout.pview2,ll,false);
		RelativeLayout rl=(RelativeLayout) view.findViewById(R.id.rl1);
		WindowManager wm = context.getWindowManager();
	    int width = wm.getDefaultDisplay().getWidth();
	    DensityUtil du=new DensityUtil(context);
	    width=width-du.dip2px(16)*2;
		LayoutParams lp=new LayoutParams(width,width-80);
		lp.leftMargin=du.dip2px(16);
		lp.topMargin=du.dip2px(16);
		//rl.setLayoutParams(lp);
		
		LinearLayout ll_shouyi=(LinearLayout) view.findViewById(R.id.ll_shouyi);
		int top=(width-width/10*7)/2+width/12;
		ll_shouyi.setPadding(0,top,0,0);
		
		
		yv=(YuanView) view.findViewById(R.id.yv);
		yv.init2(context,270);
		
		iv5=(ImageView) view.findViewById(R.id.iv_biaoshi);
		prs=(PullToRefreshScrollView) view0.findViewById(R.id.prs);
		prs.setPullLoadEnabled(false);
		prs.setScrollLoadEnabled(false);
		prs.getRefreshableView();
		prs.setOnRefreshListener(new OnRefreshListener<ScrollView>() 
		{

			@Override
			public void onPullDownToRefresh(
					PullToRefreshBase<ScrollView> refreshView) {
				    addData2();
			}

			@Override
			public void onPullUpToRefresh(
					PullToRefreshBase<ScrollView> refreshView) {
				// TODO Auto-generated method stub
				
			}
		});
		
		sv=prs.getRefreshableView();
		sv.addView(view);
		init2(view0);
		addView(view0);
	    prs.doPullRefreshing(false,0);
	}
	ImageView iv1;
	ImageView iv2;
	ImageView iv3;
	ImageView iv4;
	RelativeLayout rl1;
	RelativeLayout rl2;
	RelativeLayout rl3;
	RelativeLayout rl4;
	TextView tv1;
	TextView tv2;
	TextView tv3;
	TextView tv4;
	
	//剩余时间的秒数
    private long time;

	
	final int color1=0xff747474;
	final int color2=0xffdd2238; 
	private void setView2()
	{
		if(iv1!=null)
		{
			iv1.setVisibility(View.GONE);
			tv1.setTextColor(color1);
			tv1.setBackgroundResource(R.drawable.bg_page_yue);
		}
		if(iv2!=null)
		{
			iv2.setVisibility(View.GONE);
			tv2.setTextColor(color1);
			tv2.setBackgroundResource(R.drawable.bg_page_yue);
		}
		if(iv3!=null)
		{
			iv3.setVisibility(View.GONE);
			tv3.setTextColor(color1);
			tv3.setBackgroundResource(R.drawable.bg_page_yue);
		}
		if(iv4!=null)
		{
			iv4.setVisibility(View.GONE);
			tv4.setTextColor(color1);
			tv4.setBackgroundResource(R.drawable.bg_page_yue);
		}
	}
	
	
	Handler handler=new Handler();
	Runnable run;
	//开始倒计时计算
	private void startDownTime()
	{
		  run=new Runnable() {
			
			@Override
			public void run() {
				time--;
				if(time<=0)
				{
					  bu_goumai.setEnabled(true);
					  bu_goumai.setText("立即购买");
					  handler.removeCallbacks(run);
				}
				else
				{  
					bu_goumai.setEnabled(false);
					bu_goumai.setText("距离开售还有    "+DateManager.countDateSub(time));
				}
				
				handler.postDelayed(run,1000);
				
			}
		};
		
		handler.postDelayed(run,1000);
	}
	public void addData2()
	{   
		UserParam up=new UserParam();
		String id=BeikBankApplication.getUserid();
		if(id==null)
		{
			id="";
		}
		up.memberID=id;
		ManagerParam mp=new ManagerParam();
		
		mp.isShowDialog=false;
		TongYongManager tym=new TongYongManager(act, icb6,up,mp);
		tym.start();
		
		
		
		/**
		 * 加息标识回调
		 */
		ICallBack icb7=new ICallBack() {
			
			@Override
			public void back(Object obj) {
				if(obj!=null)
				{
					Biaoshi_data bsd=(Biaoshi_data)obj;
					if(bsd!=null)
					{
					  final Biaoshi bs=bsd.data;
					  if(bs!=null&&bs.icon!=null&&!bs.icon.equals(""))
					  { 
						
						iv5.setVisibility(View.VISIBLE);
						if(bs.linkUrl!=null&&!bs.linkUrl.equals(""))
						{   
							iv5.setOnClickListener(new OnClickListener() {
								
								@Override
								public void onClick(View v) {
									Intent intent=new Intent(act,HuodongActivity2.class);
									intent.putExtra("url",bs.linkUrl);
									intent.putExtra("title",bs.title);
									act.startActivity(intent);
								}
							});
							
						}
					    ImageUrl iu=new ImageUrl(iv5,bs.icon);
					    iu.start();
					  }
					}
				}
			}
		};
		//标识部分
		BiaoshiParam bp=new BiaoshiParam();
		bp.type="0";
		ManagerParam mp2=new ManagerParam();
		mp2.isShowMsg=false;
		mp2.isShowDialog=false;
		TongYongManager tym2=new TongYongManager(act, icb7,bp,mp2);
		tym2.start();
		
//		BiaoshiParam bp=new BiaoshiParam();
//		bp.type="0";
//		ManagerParam mp=new ManagerParam();
//		mp.isShowMsg=false;
//		TongYongManager tym2=new TongYongManager(act, icb7,bp,mp);
//		tym2.start();
	}
	
ICallBack icb6=new ICallBack() {
		
		@Override
		public void back(Object obj) {
			prs.onPullDownRefreshComplete();
			   if(obj!=null)
			   {
			     dpd=(DingqiP_data2)obj;
			     list2=dpd.data.productBond;
			    
			     list2=newDpSort(list2);
			     dp2=getDP2(dp2, list2);
			     setViewData(dp2);
			     setView();
			   }
		}
	};
	//当前是第几项
	int index;
	/**
	 * 得到当前的的dp
	 */
	private DingqiP2 getDP2(DingqiP2 dp,List<DingqiP2> list)
	{
		if(dp==null)
		{   
			dp=list.get(0);
			index=0;
			return list.get(0);
		}
		else
		{
			for(int i=0;i<list.size();i++)
			{   
				DingqiP2 dp2=list.get(i);
				if(dp.termbondCode.equals(dp2.termbondCode))
				{   
					index=i;
					dp=dp2;
					return dp2;
				}
			}
		}
		dp=list.get(0);
		index=0;
		return list.get(0);
	}
	private void setView()
	{   
		LayoutInflater li=act.getLayoutInflater();
		LinearLayout ll=new LinearLayout(act);
		View ll1=null;
		if(list2.size()==1)
		{
			ll1=li.inflate(R.layout.page_dingqi1,ll,false);
		}
		else if(list2.size()==2)
		{
			ll1=li.inflate(R.layout.page_dingqi2,ll,false);
		}
		else if(list2.size()==3)
		{
			ll1=li.inflate(R.layout.page_dingqi3,ll,false);
		}
		else
		{
			ll1=li.inflate(R.layout.page_dingqi4,ll,false);
		}
		tv1=(TextView) ll1.findViewById(R.id.tv1);
		tv2=(TextView) ll1.findViewById(R.id.tv2);
		tv3=(TextView) ll1.findViewById(R.id.tv3);
		tv4=(TextView) ll1.findViewById(R.id.tv4);
		
		iv1=(ImageView) ll1.findViewById(R.id.iv1);
		iv2=(ImageView) ll1.findViewById(R.id.iv2);
		iv3=(ImageView) ll1.findViewById(R.id.iv3);
		iv4=(ImageView) ll1.findViewById(R.id.iv4);
		rl1=(RelativeLayout) ll1.findViewById(R.id.rl1);
		rl2=(RelativeLayout) ll1.findViewById(R.id.rl2);
		rl3=(RelativeLayout) ll1.findViewById(R.id.rl3);
		rl4=(RelativeLayout) ll1.findViewById(R.id.rl4);
		ll2.removeAllViews();
		ll2.addView(ll1);
		setView2();
		setView3();
		if(list2.size()>1)
		{  
			if(index==3)
			{
				iv4.setVisibility(View.VISIBLE);
				tv4.setBackgroundResource(R.drawable.bg_page_yue2);
				tv4.setTextColor(color2);
			}
			else if(index==1)
			{
				iv2.setVisibility(View.VISIBLE);
				tv2.setBackgroundResource(R.drawable.bg_page_yue2);
				tv2.setTextColor(color2);
			}
			else if(index==2)
			{
				iv3.setVisibility(View.VISIBLE);
				tv3.setBackgroundResource(R.drawable.bg_page_yue2);
				tv3.setTextColor(color2);
			}
			else{
				iv1.setVisibility(View.VISIBLE);
				tv1.setBackgroundResource(R.drawable.bg_page_yue2);
				tv1.setTextColor(color2);
			}
		}
		else
		{
			
		}
	}
	private void setView3()
	{
		if(rl1!=null)
		{  
			tv1.setText(list2.get(0).showName);
			rl1.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					setView2();
					if(list2!=null&&list2.size()>1)
					{   
						dp2=list2.get(0);
						iv1.setVisibility(View.VISIBLE);
						tv1.setTextColor(color2);
						tv1.setBackgroundResource(R.drawable.bg_page_yue2);
						setViewData(dp2);
					}
				}
			});
		}
		if(rl2!=null)
		{   
			tv2.setText(list2.get(1).showName);
			rl2.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					setView2();
					if(list2!=null&&list2.size()>1)
					{  
					   dp2=list2.get(1);
					   iv2.setVisibility(View.VISIBLE);
					   tv2.setTextColor(color2);
					   tv2.setBackgroundResource(R.drawable.bg_page_yue2);
					   setViewData(dp2);
					}
				}
			});
		}
		if(rl3!=null)
		{   
			tv3.setText(list2.get(2).showName);
			rl3.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					setView2();
					if(list2!=null&&list2.size()>1)
					{ 
						dp2=list2.get(2);
					   iv3.setVisibility(View.VISIBLE);
					    tv3.setTextColor(color2);
						tv3.setBackgroundResource(R.drawable.bg_page_yue2);
					   setViewData(dp2);
					}
				}
			});
		}
		if(rl4!=null)
		{   
			tv4.setText(list2.get(3).showName);
			rl4.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					setView2();
					if(list2!=null&&list2.size()>1)
					{   
						dp2=list2.get(3);
					    iv4.setVisibility(View.VISIBLE);
					    tv4.setTextColor(color2);
						tv4.setBackgroundResource(R.drawable.bg_page_yue2);
					    setViewData(dp2);
					}
				}
			});
		}
	}
	private void setViewData(DingqiP2 dp)
	{      
		
		try
		{    
			yv.drawYiGou(dp2);
			
			 if(run!=null)
			   {
				   handler.removeCallbacks(run);
			   }
			 if(dp.categoryName!=null)
			 {
		        //tv_tv1.setText(dp.categoryName);
			 }
			 
		   double d=Double.parseDouble(ViewDataUtil.getD1(dp.yearRate));
		   tv11.setValue(d);
		   mHandler.sendEmptyMessageDelayed(0, 0);
		   String s2=NumberManager.getString(dp.extraRate,"100",2);
		   
		   tv12.setText("+"+s2+"%");
		   double dou=Double.parseDouble(dp.extraRate);
		   if(dou<=0)
		   {
			  tv12.setVisibility(View.INVISIBLE);
		   }
		   else
		   {
			   tv12.setVisibility(View.VISIBLE);
		   }
		   
		   String s0=NumberManager.getAddString(dp.yearRate,dp.extraRate,4);
		   String ss=NumberManager.getDivString(s0,"365",10);
		   ss=NumberManager.getString(ss,"10000",2);
		   //tv13.setText(ss);
//		   
		   Pview3.setTextColor(tv13, "万份收益"+ss+"元");
		   tv14.setText("剩余可购金额"+dp.remainAmount+"元");
//		   
//		   String s3=NumberManager.getString(dp.remainAmount,"1",0);
//		   tv_tv6.setText(NumberManager.getGeshiHua(s3, 0));
//		   tv_tv7.setText(dp.security);
		   //tv_tv8.setText(dp.showName);
		   if(dp.status.equals(FinalIndex.dingqi1))
		   {   
			   double d2=Double.parseDouble(dp.remainAmount);
			   if(d2<=0)
			   {
				   bu_goumai.setEnabled(false);
				   bu_goumai.setText("售罄");
				 
				   return;
			   }
			   bu_goumai.setEnabled(true);
			   bu_goumai.setText("立即购买");
			   //倒计时
			  
			   
			   time=Long.parseLong(dp.countdown);
			   if(time>0)
			   {
				   bu_goumai.setEnabled(false);
				   bu_goumai.setText("距离开售还有    "+DateManager.countDateSub(time));
				  
				   startDownTime();
			   }
			   else
			   {
				   bu_goumai.setEnabled(true);
				   bu_goumai.setText("立即购买");
			   }
		   }
		   else
		   {
			   bu_goumai.setEnabled(false);
			   bu_goumai.setText("售罄");
			   tv14.setText("剩余可购金额"+"0"+"元");
		   }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	/**
	 * 对标的进行排序，如果是新手，新手标放在前面，如果是老手，新手标放后面
	 * @param list
	 * @return
	 */
	private List<DingqiP2> newDpSort(List<DingqiP2> list)
	{   
		boolean do_success=BeikBankApplication.isLogin();
//		if(!do_success)
//		{
//			return list;
//		}
		//new
		List<DingqiP2> list2=new ArrayList<DingqiP2>();
		//old
		List<DingqiP2> list3=new ArrayList<DingqiP2>();
		List<DingqiP2> list4=new ArrayList<DingqiP2>();
		for(DingqiP2 dp2:list)
		{
			if("1".equals(dp2.termbondType))
			{
				//list2.add(dp2);
			}
			else
			{
				list3.add(dp2);
			}
		}
		
		if(dpd.data.userLevel.equals("0"))
		{
			for(DingqiP2 dp3:list2)
			{
				list4.add(dp3);
			}
			for(DingqiP2 dp3:list3)
			{
				list4.add(dp3);
			}
		}
		else
		{
			for(DingqiP2 dp3:list3)
			{
				list4.add(dp3);
			}
			for(DingqiP2 dp3:list2)
			{
				list4.add(dp3);
			}
		}
		return list4;
	}
	
	
	
	DingqiP2 dp2;
	/**
	 * 所有定期产品
	 */
	List<DingqiP2> list2;
	DingqiP_data2 dpd;
	ICallBack icb4=new ICallBack() {
		
		@Override
		public void back(Object obj) {
			 if(obj==null)
			 {
			   Intent intent=new Intent(act,DingqiGoumaiActivity.class);
			   //intent.putExtra(DingqiDetailActivity.index,dp.termbondCode);
			   intent.putExtra(DingqiDetailActivity.index,dp2);
			  
			   ///intent.putExtra("money",act.vpl.page1.uc.fundAmount);
			   
			   act.startActivity(intent);
			 }
		}
	};
	
	//选择支付方式
		private void selectPlay()
		{
			LiuChenManager.selectPay(icb4, act,true);
		}
		//回调选择支付方式
		ICallBack icb5=new ICallBack() {
			
			@Override
			public void back(Object obj) {
				 //LiuChenManager.StartNext(act,icb4);
				if(obj==null)
				{ 
					  boolean b=doNew(dp2,dpd.data.userLevel);
					   if(!b)
					   {	
				          selectPlay();
					   }
				}
			}
		};
		/**
		 * 如果点击的是新手标，进行相应的处理 
		 * @return true 不进行后续的代码
		 */
		public  boolean doNew(DingqiP2 dp2,String isNew)
		{
			
			if(dp2.termbondType.equals("1"))
			{
				if(isNew.equals("0"))
				{   
					
				}
				else
				{
					PageUtil pu=new PageUtil(act,DingqiDetailActivity.countRate(dp2.yearRate,dp2.extraRate));
					pu.showShapeDialog();
					return true;
				}
			}
			
			return false;
		}
	
	protected void startAimActivity(Class<?> pActClassName) {
		Intent _Intent = new Intent();
		_Intent.setClass(act, pActClassName);
		act.startActivity(_Intent);
	}


	public Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			tv11.doScroll(0);
		};
	};
	@Override
	public void onClick(View v) {
		
		if(dp2==null||dpd==null)
		{
			return;
		}
	
		switch(v.getId()){
		case R.id.bu_goumai:
			GoumaiManager.goumaiDingQi(act,dp2,dpd.data.userLevel);
//			BeikBankApplication.mSharedPref.putSharePrefInteger(BeikBankConstant.huo_ding,
//					2);
//			if(dp2!=null&&dp2.termbondCode!=null)
//			   {   
//					   LiuChenManager.StartNext(act,icb5);
//			   }
			break;
		case R.id.ll_xiangqing:
			  
			   if(dp2!=null&&dp2.termbondCode!=null)
			   {   
				 
				   Intent intent=new Intent(act,DingqiDetailActivity.class);
				   //intent.putExtra(DingqiDetailActivity.index,dp.termbondCode);
				   //dp2.countdown=time+"";
				   intent.putExtra(TypeUtil.dingdi_data,dp2);
				   intent.putExtra("index3",dpd.data.userLevel);
				   act.startActivity(intent);
			   }
				break;
		}
	}
}
