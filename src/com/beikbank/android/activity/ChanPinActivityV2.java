package com.beikbank.android.activity;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.InputFilter;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.beikbank.android.activity.help.LiuChenManager;
import com.beikbank.android.activity.help.LiuChenSelect;
import com.beikbank.android.adapter.XiangMuXinXiAdapter;
import com.beikbank.android.adapter.ZhiChanAdapter;
import com.beikbank.android.data.Config_data;
import com.beikbank.android.data.Confing;
import com.beikbank.android.data2.GetChanPin;
import com.beikbank.android.data2.getChanPinXiangQin;
import com.beikbank.android.data2.getChanPinXiangQin_data;
import com.beikbank.android.data2.getXiangMuXinXi_data;
import com.beikbank.android.data2.getYiGou;
import com.beikbank.android.data2.getYiGou_data;
import com.beikbank.android.dataparam2.getChanPinXiangQinParam;
import com.beikbank.android.dataparam2.getXiangMuXinXiParam;
import com.beikbank.android.dataparam2.getYiGouParam;
import com.beikbank.android.exception.LogHandler;
import com.beikbank.android.net.HandlerBase;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.ManagerParam;
import com.beikbank.android.net.impl.CheckUpdateManager;
import com.beikbank.android.net.impl.ConfigManager;
import com.beikbank.android.net.impl.TongYongManager2;
import com.beikbank.android.utils.BeikBankDialogListener;
import com.beikbank.android.utils.DensityUtil;
import com.beikbank.android.utils.DialogManager;
import com.beikbank.android.utils.NumberManager;
import com.beikbank.android.utils.Utils;
import com.beikbank.android.utils2.StateBarColor;
import com.beikbank.android.widget.MyViewPager;

import com.beikbank.android.widget2.PageView2.MyAdapter;
import com.beikbank.android.widget3.Pview1;
import com.beikbank.android.widget3.Pview2;
import com.beikbank.android.widget3.Pview3;

import comc.beikbank.android.R;


// 2.0产品详情
public class ChanPinActivityV2 extends BaseActivity1 implements OnClickListener{
    private static Activity act;
	private TextView titleTv;
	private LinearLayout linear_left;
	
	
	
	private MyAdapter mAdapter;
	public MyViewPager mPager;
	private MyPageChangeListener myPageChangeListener;
	private interface MyPageChangeListener {
		public void onPageSelected(int position);
	}
	private ArrayList<View> pagerItemList = new ArrayList<View>();
	GetChanPin gcp;
	Pview3 p3;
	Pview2 p2;
	Button bu_goumai;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_canpin_v2);
		//StateBarColor.init(this,0xffe22c2f);
		act=this;
		gcp=(GetChanPin) getIntent().getSerializableExtra("index1");
		initView();
		initData();
	}
	
	public void initView(){
		titleTv = (TextView) findViewById(R.id.titleTv);
		titleTv.setText(gcp.product_name);
		
		   if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT)
	    		
	    	{  
			   View v=findViewById(R.id.v);
			   StateBarColor.init(this,0xfffb5a5c);
	    	   int h=StateBarColor.getStatusBarHeight(act);
	    	   LayoutParams lp=new LayoutParams(LayoutParams.MATCH_PARENT,h);
	    	  
	    	   v.setLayoutParams(lp);
	    	   v.setBackgroundColor(0xfffb5a5c);
	    	}
		
		
		
		linear_left = (LinearLayout) findViewById(R.id.linear_left);
		linear_left.setVisibility(View.VISIBLE);
		linear_left.setOnClickListener(this);
        bu_goumai=(Button) findViewById(R.id.bu_goumai);
        bu_goumai.setOnClickListener(this);
		ImageView iv_jisuanqi=(ImageView) findViewById(R.id.iv_jisuanqi);
        iv_jisuanqi.setOnClickListener(this);
        
		TextView tv_nianfa=(TextView) findViewById(R.id.tv_nianfa);
		TextView tv_qixian=(TextView) findViewById(R.id.tv_qixian);
		TextView tv_jiaxi=(TextView) findViewById(R.id.tv_jiaxi);
		String nianhua=NumberManager.getString(gcp.benchmark_return_rate,"100",2);
		tv_nianfa.setText(NumberManager.getGeshiHua(nianhua,2));
		double jiaxi=Double.parseDouble(gcp.increase_interest_return_rate);
		if(jiaxi>0)
		{  
			String s=NumberManager.getString(gcp.increase_interest_return_rate,"100",2);
			tv_jiaxi.setText("+"+s+"%");
		}
		if("4".equals(gcp.product_type_pid))
		{
			
			TextView tv_tian=(TextView) findViewById(R.id.tv_tian);
			
			tv_qixian.setText("灵活存取");
			tv_tian.setVisibility(View.GONE);
		}
		else
		{
		   tv_qixian.setText(gcp.term);
		
		}

		SpannableStringBuilder builder = new SpannableStringBuilder("购买(剩余"+gcp.pro_share+"元)");
		  
		//ForegroundColorSpan f2 = new ForegroundColorSpan(0xffe4393c);
		
		builder.setSpan(new AbsoluteSizeSpan(DensityUtil.sp2px(act, 15)), 0,2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		
		Button bu=(Button) findViewById(R.id.bu_goumai);
		bu.setText(builder);
		double d1=Double.parseDouble(gcp.pro_share);
		if(d1<=0)
		{
			bu.setText("售罄");
			bu.setEnabled(false);
		}
		
		
		
		
		mPager = (MyViewPager)findViewById(R.id.pager);
		
	    Pview1 p1=new Pview1(act);
		p2=new Pview2(act);
		p3=new Pview3(act);
		p1.addData(gcp);
		
    	pagerItemList.add(p1);
    	pagerItemList.add(p2);
    
    	
    	
    	
    	pagerItemList.add(p3);
    	
    	
		mPager.setOnPageChangeListener(new Pageon());
		mAdapter = new MyAdapter();
		mPager.setAdapter(mAdapter);
		mPager.setCurrentItem(0);
		
		setTop();
		
		initData2(null);
	}
	public void initData2(final ICallBack icb1)
	{  
		final Button bu=(Button) findViewById(R.id.bu_goumai);
		ICallBack icb=new ICallBack() {
			
			@Override
			public void back(Object obj) {
				if(icb1!=null)
				{
				  icb1.back(null);
				}
				if(obj!=null)
				{
					getChanPinXiangQin_data gd=(getChanPinXiangQin_data) obj;
					getChanPinXiangQin gcp=gd.body;
					
					SpannableStringBuilder builder = new SpannableStringBuilder("购买(剩余"+gcp.pro_share+"元)");
					  
					//ForegroundColorSpan f2 = new ForegroundColorSpan(0xffe4393c);
					
					builder.setSpan(new AbsoluteSizeSpan(DensityUtil.sp2px(act, 15)), 0,2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
					
				
					bu.setText(builder);
					double d1=Double.parseDouble(gcp.pro_share);
					if(d1<=0)
					{
						bu.setText("售罄");
						bu.setEnabled(false);
					}
					else
					{
						bu.setEnabled(true);
					}
				}
				
			}
		};
		if(gcp==null)
		{
			return;
		}
		getChanPinXiangQinParam gp=new getChanPinXiangQinParam();
		gp.product_id=gcp.product_id;
		ManagerParam mp=new ManagerParam();
		mp.isShowDialog=false;
		TongYongManager2 tym2=new TongYongManager2(act, icb,gp,mp);
		tym2.start();
	}
	private void initData()
	{
		
//		ICallBack icb=new ICallBack() {
//			
//			@Override
//			public void back(Object obj) {
//				if(obj!=null)
//				{
//					getXiangMuXinXi_data gd=(getXiangMuXinXi_data) obj;
//					//XiangMuXinXiAdapter xa=new XiangMuXinXiAdapter(act,gd.body.debtList);
//					//p2.lv.setAdapter(xa);
//					
//					//Log.d("gd",gd.body.debtList.size()+"");
//				}
//				
//			}
//		};
//		
//		getXiangMuXinXiParam gxx=new getXiangMuXinXiParam();
//		gxx.id=gcp.product_id;
//		gxx.page="1";
//		gxx.size="100";
//		
//		TongYongManager2 tym2=new TongYongManager2(act, icb,gxx);
//		tym2.start();
		
		p2.addData(gcp.product_id);
		p3.addData(gcp.product_id);
//		
//		ICallBack icb_gyg=new ICallBack() {
//			
//			@Override
//			public void back(Object obj) {
//				if(obj!=null)
//				{   
//					getYiGou_data gd=(getYiGou_data) obj;
//					LinearLayout ll_yigou=(LinearLayout) p3.findViewById(R.id.ll_yigou);
//					//setYiGou(ll_yigou,gd.body);
//					p3.addData(gd.body.list);
//				}
//				
//			}
//		};
//		getYiGouParam gyg=new getYiGouParam();
//		gyg.begin_index=0;
//		gyg.count=100;
//		gyg.pro_id=gcp.product_id;
//		TongYongManager2 tym3=new TongYongManager2(act, icb_gyg,gyg);
//		tym3.start();
	}
	private void setYiGou(LinearLayout ll_yigou,ArrayList<getYiGou> list)
	{
		  getYiGou gyg;
		  LinearLayout ll=new LinearLayout(this);
		  ll_yigou.removeAllViews();
		  TextView tv_name;
		  TextView tv_time;
		  TextView tv_money;
		  for(int i=0;i<list.size();i++)
		  {
			  gyg=list.get(i);
			 
			  View view =LayoutInflater.from(this).inflate(R.layout.pview3_yigou,ll,false);
			  tv_name=(TextView) view.findViewById(R.id.tv_name);
			  tv_time=(TextView) view.findViewById(R.id.tv_time);
			  tv_money=(TextView) view.findViewById(R.id.tv_moeney);
			  tv_name.setText(gyg.real_name);
			  tv_time.setText(gyg.create_time);
			  tv_money.setText(NumberManager.getGeshiHua(gyg.amount,2));
			  ll_yigou.addView(view);
		  }
		
		
	}
	
	
	
	TextView tv_chanpin;
	TextView tv_xinagmu;
	TextView tv_yigou;
	
	View v_chanpin;
	View v_xiangmu;
	View v_yigou;
	private  void setTop()
	{
		LinearLayout ll_chanpin=(LinearLayout) findViewById(R.id.ll_canpin);
		LinearLayout ll_xiangmu=(LinearLayout) findViewById(R.id.ll_xiangmu);
		LinearLayout ll_yigou=(LinearLayout) findViewById(R.id.ll_yigou);
		
		 tv_chanpin=(TextView) findViewById(R.id.tv_canpin);
		 tv_xinagmu=(TextView) findViewById(R.id.tv_xiangmu);
		 tv_yigou=(TextView) findViewById(R.id.tv_yigou);
		
		v_chanpin=findViewById(R.id.v_canpin);
		v_xiangmu=findViewById(R.id.v_xiangmu);
		v_yigou=findViewById(R.id.v_yigou);
		
		ll_chanpin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			 setTop2(0);
			 mPager.setCurrentItem(0,true);
			}
		});
		
        ll_xiangmu.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				setTop2(1);
				mPager.setCurrentItem(1,true);
			}
		});
          ll_yigou.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				setTop2(2);
				mPager.setCurrentItem(2,true);
			}
		});
		
	}
	private void setTop2(int index)
	{   
		tv_chanpin.setTextColor(0xff333333);
		v_chanpin.setBackgroundColor(0x00000000);
		
		tv_xinagmu.setTextColor(0xff333333);
		v_xiangmu.setBackgroundColor(0x00000000);
		tv_yigou.setTextColor(0xff333333);
		v_yigou.setBackgroundColor(0x00000000);
		
		
		if(index==0)
		{
			tv_chanpin.setTextColor(0xfffb5a5c);
			v_chanpin.setBackgroundColor(0xfffb5a5c);
		}
		else if(index==1)
		{
			tv_xinagmu.setTextColor(0xfffb5a5c);
			v_xiangmu.setBackgroundColor(0xfffb5a5c);
		}
		else
		{
			tv_yigou.setTextColor(0xfffb5a5c);
			v_yigou.setBackgroundColor(0xfffb5a5c);
		}
	}
	
	
	  public class MyAdapter extends PagerAdapter
		{

			@Override
			public int getCount() {
				return pagerItemList.size();
			}

			@Override
			public void destroyItem(ViewGroup container, int position, Object object) {
				// TODO Auto-generated method stub
				container.removeView((View)object);
			}

			@Override
			public Object instantiateItem(ViewGroup container, int position) {
				container.addView(pagerItemList.get(position));
				return pagerItemList.get(position);
			}

			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				// TODO Auto-generated method stub
				return  arg0==arg1;
			}

			@Override
			public int getItemPosition(Object object) {
				// TODO Auto-generated method stub
				return POSITION_NONE;
			}
		}
	  class Pageon implements OnPageChangeListener
	  {

		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onPageSelected(int arg0) {
			mPager.setCurrentIndex(arg0);
			setTop2(arg0);
		}
		  
	  }
	  

		ICallBack icb_lc=new ICallBack() {
			
			@Override
			public void back(Object obj) {
				Intent intent;
				if("0".equals(gcp.term))
				{
					 intent=new Intent(act,PurchaseActivity.class);
					 intent.putExtra("gcp",gcp);
					 act.startActivity(intent);
				}
				else
				{
					 intent=new Intent(act,DingqiGoumaiActivity.class);
					 intent.putExtra("gcp",gcp);
					 act.startActivity(intent);
				}
				
			}
		};
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.linear_left:
			finish();
			break;
     
		case R.id.bu_goumai:
//			if(gcp!=null)
//			{   
//				LiuChenManager.selectPay(icb_lc, act,false);
//				
//			}
			
			LiuChenSelect ls=new LiuChenSelect();
			ls.startNext(this,2,gcp);
			break;
		case R.id.iv_jisuanqi:
			showCountDialog();
			break;	
			
		}
	}

	
	TextView tv_tv9;
	TextView tv_tv10;
	EditText et_et1;
	Dialog dialog4;
	/**
	 * 计算器计算按钮
	 */
	Button bu_but3;
    /**
     * 显示收益计算器
     */
   private void showCountDialog()
   {
	    LinearLayout ll=new LinearLayout(this);
	    View v=LayoutInflater.from(this).inflate(
  			   R.layout.count_shouyi,ll,false);
	    tv_tv9=(TextView) v.findViewById(R.id.tv_tv1);
	    tv_tv10=(TextView) v.findViewById(R.id.tv_tv2);
	    tv_tv10.setText(gcp.term);
//	    if(SystemConfig.isDebug())
//	    {
//	    	 tv_tv10.setText("30");
//	    }
	    et_et1=(EditText)v.findViewById(R.id.et_et1);
	    et_et1.setFilters(new InputFilter[]{new InputFilter.LengthFilter(7)}); 
	    
	    bu_but3=(Button) v.findViewById(R.id.bu_bu1);
	    bu_but3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			  String s=et_et1.getText().toString();
			  tv_tv9.setText(ZhiChanAdapter.getShouyi(s,gcp.term,gcp.benchmark_return_rate,gcp.increase_interest_return_rate));
			}
		});
	    
        dialog4=DialogManager.getDiaolg1(this, v);
        dialog4.setCanceledOnTouchOutside(true);
    	dialog4.show();
    	et_et1.setFocusable(true);
   	    et_et1.setFocusableInTouchMode(true);
   	    et_et1.requestFocus();
    	Runnable run=new Runnable() {
			
			@Override
			public void run() {	
				InputMethodManager inputManager = (InputMethodManager)et_et1.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
				inputManager.showSoftInput(et_et1, 0);
			}
		};
        Handler handler=new Handler();
        handler.postDelayed(run, 500);
   }
	
	
	
	
	
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	protected <T> void startAimActivity(final Class<T> pActClassName) {
		Intent _Intent = new Intent();
		_Intent.setClass(act, pActClassName);
		act.startActivity(_Intent);
	}
	
	
	
	
	
}
