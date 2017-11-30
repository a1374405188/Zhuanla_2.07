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
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.beikbank.android.activity.help.NoneData;
import com.beikbank.android.adapter.DingQiXiangQiAdapter;


import com.beikbank.android.data.Config_data;
import com.beikbank.android.data.Confing;
import com.beikbank.android.data2.CheckJiaoYiMiMa_data;
import com.beikbank.android.data2.getDingDanXiangQin_data;
import com.beikbank.android.data2.getDingQiXiangQin;
import com.beikbank.android.data2.getDingQiXiangQin_data;
import com.beikbank.android.dataparam2.CheckJiaoYiMiMaParam;
import com.beikbank.android.dataparam2.HuTouOpenOrCloseParam;
import com.beikbank.android.dataparam2.getDingQiXiangQinParam;
import com.beikbank.android.exception.LogHandler;
import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.net.HandlerBase;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.impl.CheckUpdateManager;
import com.beikbank.android.net.impl.ConfigManager;
import com.beikbank.android.net.impl.TongYongManager2;
import com.beikbank.android.utils.BeikBankConstant;
import com.beikbank.android.utils.BeikBankDialogListener;
import com.beikbank.android.utils.DensityUtil;
import com.beikbank.android.utils.DialogManager;
import com.beikbank.android.utils.MD5;
import com.beikbank.android.utils.NumberManager;
import com.beikbank.android.utils.Utils;
import com.beikbank.android.utils2.StateBarColor;
import com.beikbank.android.widget.ClearableEditText;
import coma.beikbank.android.R;



//定期理财

public class DingqiLicaiActivity extends BaseActivity1 implements OnClickListener{

	private TextView titleTv;
	private LinearLayout linear_left;
	private LinearLayout ll_guanyu;
	private LinearLayout ll_bangzhu;
	private LinearLayout ll_yijian;
	private LinearLayout ll_guli;
	private LinearLayout ll_banben;
	TextView tv_zhican;
	TextView tv_zuori;
	TextView tv_leiji;
	TextView right;
	//产品id
	String id;
	ListView lv;
	String name;
	Activity act=this;
	LinearLayout ll_parent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dingqi_licai);
		StateBarColor.init(this,0xffffffff);
		id=getIntent().getStringExtra("id");
		name=getIntent().getStringExtra("name");
		act=this;
		initView();
		initData();
	}
	
	public void initView(){
		titleTv = (TextView) findViewById(R.id.titleTv);
		titleTv.setText(name);
		lv=(ListView) findViewById(R.id.lv);
		
		tv_zhican = (TextView) findViewById(R.id.tv_zhican);
		tv_zuori = (TextView) findViewById(R.id.tv_zuori);
		tv_leiji = (TextView) findViewById(R.id.tv_leiji);
		
		right=(TextView) findViewById(R.id.right);
		right.setVisibility(View.VISIBLE);
		right.setText("已到期");
		right.setOnClickListener(this);
		
		linear_left = (LinearLayout) findViewById(R.id.linear_left);
		linear_left.setVisibility(View.VISIBLE);
		linear_left.setOnClickListener(this);

		LinearLayout ll_zuori = (LinearLayout) findViewById(R.id.ll_zuori);
		LinearLayout ll_leiji = (LinearLayout) findViewById(R.id.ll_leiji);
		ll_zuori.setOnClickListener(this);
		ll_leiji.setOnClickListener(this);
		ll_parent=(LinearLayout) findViewById(R.id.ll_parent);
		
		
		
	}
	getDingQiXiangQin_data gd;
    private void initData()
    {   
    	ICallBack icb_gdq=new ICallBack() {
			
			@Override
			public void back(Object obj) {
				if(obj!=null)
				{
					gd=(getDingQiXiangQin_data) obj;
					
					tv_zhican.setText(NumberManager.getGeshiHua(gd.body.current_capital_value,2));
					tv_zuori.setText(NumberManager.getGeshiHua(gd.body.intrest_yesterday,2));
					tv_leiji.setText(NumberManager.getGeshiHua(gd.body.intrest_total,2));
					
//					DingQiXiangQiAdapter gqa=new DingQiXiangQiAdapter(act,gd.body.productOverviews);
//					lv.setAdapter(gqa);
					
					
				
					addView(ll_parent,gd.body.productOverviews);
					
				}
				if(gd==null||gd.body.productOverviews.size()==0)
				{
					NoneData.setView(act,ll_parent,1);
				}
				
			}
		};
    	getDingQiXiangQinParam gdq=new getDingQiXiangQinParam();
    	gdq.page_index="1";
    	gdq.page_size="100";
    	gdq.product_type_id=id;
    	gdq.user_id=BeikBankApplication.getUserCode();
    	TongYongManager2 tym2=new TongYongManager2(act, icb_gdq, gdq);
    	tym2.start();
    }
    private void addView(LinearLayout ll_parent,ArrayList<getDingQiXiangQin>  list)
    {   
    	ll_parent.removeAllViews();
    	View convertView;
    	for(int i=0;i<list.size();i++)
		{
    		final getDingQiXiangQin gdq=list.get(i);
    		
    		
    			convertView=LayoutInflater.from(act).inflate(R.layout.activity_dingqi_licai_item, null);
    			
    			TextView tv_benjin=(TextView) convertView.findViewById(R.id.tv_benjin);
    			TextView tv_name=(TextView) convertView.findViewById(R.id.tv_name);
    			TextView tv_shouyi=(TextView) convertView.findViewById(R.id.tv_shouyi);
    			TextView tv_daoqi=(TextView) convertView.findViewById(R.id.tv_daoqi);
    			TextView tv_futou=(TextView) convertView.findViewById(R.id.tv_futou);
    			TextView tv_daojishi=(TextView) convertView.findViewById(R.id.tv_daojishi);
    			ImageView iv= (ImageView) convertView.findViewById(R.id.iv);
    			
    			
    			
    			
    			convertView.setOnClickListener(new OnClickListener() 
    			{
    					
    					@Override
    					public void onClick(View v) 
    					{
    						Intent intent=getIntent();
    						intent.setClass(act,DingqiDetailActivity.class);
    						
    						//intent.putExtra(BeikBankConstant.IS_FORGETTRANSACTIONPWD,true);
    						//intent.putExtra(BeikBankConstant.INTENT_PHONENUMBER,BeikBankApplication.getPhoneNumber());
    						intent.putExtra("name",gdq.product_name);
    						intent.putExtra("assets_id",gdq.assets_id);
    						intent.putExtra("prod_id",gdq.prod_Id);
    						act.startActivity(intent);
    					}
    				});
    			
    		
    		//String s="<font color='#999999'>本金</font>"+"<font color='#333333' size='35'>"+gdq.current_principal_balance+"元"+"</font>";
    		//String s1="<font color='#999999'>还款倒计时</font>"+"<font color='#333333' size='35'>"+gdq.remain_return_day+"天"+"</font>";
    		 //  SpannableStringBuilder sb = new SpannableStringBuilder(gdq.current_principal_balance); // 包装字体内容  
    	        ForegroundColorSpan fcs = new ForegroundColorSpan(0xff333333); // 设置字体颜色  
    	       // StyleSpan bss = new StyleSpan(android.graphics.Typeface.BOLD); // 设置字体样式  
    	        AbsoluteSizeSpan ass = new AbsoluteSizeSpan(50);  // 设置字体大小  
    	       // sb.setSpan(fcs, 2, gdq.current_principal_balance.length()+3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);  
    	        //sb.setSpan(bss, 0, 20, Spannable.SPAN_INCLUSIVE_INCLUSIVE);  
    	       // sb.setSpan(ass,2,gdq.current_principal_balance.length()+3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);  
    		
    			   SpannableStringBuilder sb2 = new SpannableStringBuilder("还款倒计时 "+gdq.remain_return_day+" 天"); // 包装字体内容  
    		      
    		       
    			   ForegroundColorSpan fcs2 = new ForegroundColorSpan(0xff333333); // 设置字体颜色  
    		       // StyleSpan bss = new StyleSpan(android.graphics.Typeface.BOLD); // 设置字体样式  
    		        AbsoluteSizeSpan ass2 = new AbsoluteSizeSpan(DensityUtil.sp2px(act, 16));  // 设置字体大小  
    		        sb2.setSpan(fcs2, 5, gdq.remain_return_day.length()+6, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);  
    		        //sb.setSpan(bss, 0, 20, Spannable.SPAN_INCLUSIVE_INCLUSIVE);  
    		        sb2.setSpan(ass2,5,gdq.remain_return_day.length()+6, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);     
    	        
    		 tv_daojishi.setText(sb2);
    		tv_benjin.setText(NumberManager.getGeshiHua(gdq.current_principal_balance,2));
    		tv_name.setText(gdq.product_name);
    		tv_shouyi.setText("当前收益 "+NumberManager.getGeshiHua(gdq.intrest_total,2));
    		
    	
    		
    		tv_daoqi.setText("到期日期 "+gdq.maturity_date);
    		if("0".equals(gdq.is_reinvest))
    		{
    			iv.setImageResource(R.drawable.img_futou_true);
    			iv.setTag("0");
    		}
    		else
    		{
    			iv.setImageResource(R.drawable.img_futou_false);
    			iv.setTag("1");
    		}
    	    iv.setOnClickListener(new OnClickListener() {
    			
    			@Override
    			public void onClick(View v) {
    				createDialog((ImageView)v,gdq.assets_id);
    				
    			}
    		});
    	    
    		  double d2=Double.parseDouble(gdq.remain_return_day);
			
    		  
    		  
    		  if(d2<=1)
			   {   
				   if(d2==0)
				   {
					   tv_daojishi.setText("还款中");
					   tv_daojishi.setTextColor(0xff333333);
				   }
				 
					if("0".equals(gdq.is_reinvest))
		    		{
		    			iv.setImageResource(R.drawable.img_futou_true2);
		    			iv.setTag("0");
		    		}
		    		else
		    		{
		    			iv.setImageResource(R.drawable.img_futou_false2);
		    			iv.setTag("1");
		    		}
					
					   iv.setOnClickListener(new OnClickListener() {
			    			
			    			@Override
			    			public void onClick(View v) {
			    				Toast.makeText(act, "还款中及倒计时为1天时，不能改变复投状态", Toast.LENGTH_LONG).show();
			    				
			    			}
			    		});
			   }
			   else
			   {
				   tv_daojishi.setText(sb2);
			   }
    		  
    		  if("0".equals(gdq.is_new_user_mark))
			  {
				  iv.setOnClickListener(new OnClickListener() {
		    			
		    			@Override
		    			public void onClick(View v) {
		    				Toast.makeText(act, "新手专享产品不支持到期复投", Toast.LENGTH_LONG).show();
		    				
		    			}
		    		});
				 
			  }
    	    ll_parent.addView(convertView);
    		
		}
    }
    Dialog   dialog4;
	ClearableEditText et_mima;
	TextView tv_quereng;
	private void createDialog(final ImageView iv,final String id)
	{
		 LinearLayout ll=new LinearLayout(act);
		    View v=LayoutInflater.from(act).inflate(
	  			   R.layout.check_jiaoyimima,ll,false);
		    
		   et_mima=(ClearableEditText) v.findViewById(R.id.et_mima);
		    et_mima
			.addTextChangedListener(new TextWatcherListener());
		    TextView tv_qvxiao=(TextView) v.findViewById(R.id.tv_qvxiao);
		    tv_quereng=(TextView) v.findViewById(R.id.tv_quereng);
		    tv_error=(TextView) v.findViewById(R.id.tv_error);
		    ll_error=(LinearLayout) v.findViewById(R.id.ll_error);
		    TextView tv_mima=(TextView) v.findViewById(R.id.tv_mima);
		   
		    
		    
		    tv_mima.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent intent=new Intent(act,ForgetPwdRealnameActivity.class);
					intent.putExtra(BeikBankConstant.IS_FORGETTRANSACTIONPWD,true);
					intent.putExtra(BeikBankConstant.INTENT_PHONENUMBER,BeikBankApplication.getPhoneNumber());
					act.startActivity(intent);
					
				}
			});
		    tv_qvxiao.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					dialog4.dismiss();
					
				}
			});
		    tv_quereng.setOnClickListener(new OnClickListener() {
				
						@Override
						public void onClick(View v) {
							
							String s=et_mima.getText().toString();
							if(s!=null&&!"".equals(s))
							{
								checkJiaoyi(iv,s,id);
							}
						}
					});
		    dialog4=DialogManager.getDiaolg1(act, v);
	        dialog4.setCanceledOnTouchOutside(false);
	    	dialog4.show();
	}  
	
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		initData();
	}

	private void checkJiaoyi(final ImageView iv,String passwd,final String id)
	{   
		ICallBack icb=new ICallBack() {
			
			@Override
			public void back(Object obj) {
				if(obj!=null)
				{   
					if(obj instanceof CheckJiaoYiMiMa_data)
					{
						
					
					CheckJiaoYiMiMa_data cd=(CheckJiaoYiMiMa_data) obj;
					if("0000".equals(cd.header.re_code))
					{   
						
						if("0".equals(iv.getTag()))
						{
							iv.setImageResource(R.drawable.img_futou_false);
							iv.setTag("1");
						}
						else
						{
							iv.setImageResource(R.drawable.img_futou_true);
							iv.setTag("0");
						}
						
						HuTouOpenOrCloseParam hp=new HuTouOpenOrCloseParam();
						hp.is_reinvest=(String) iv.getTag();
						hp.assets_id=id;
						TongYongManager2 tym2=new TongYongManager2(act, icb_hutou,hp);
						tym2.start();
						dialog4.dismiss();
					}
					  else
					  {
						  showToast(cd.header.re_msg);
						  Log.d("msg",cd.header.re_msg);
					  }
					}
					
					
				}
				
			}
		};
		CheckJiaoYiMiMaParam cp=new CheckJiaoYiMiMaParam();
		cp.tra_password=MD5.md5s32(passwd);
		cp.user_code=BeikBankApplication.getUserCode();
		TongYongManager2  tym2=new TongYongManager2(act, icb,cp);
		tym2.start();
	}
	ICallBack icb_hutou=new ICallBack() {
		
		@Override
		public void back(Object obj) {
			// TODO Auto-generated method stub
			
		}
	};
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent=getIntent();
		switch(v.getId()){
		case R.id.right:
			intent.setClass(act,DingqiDetailActivity2.class);
			startActivity(intent);
			break;
		case R.id.linear_left:
			finish();
			break;
        case R.id.ll_zuori:
        	intent.setClass(this,UserRecordActivity_v2.class);
        	intent.putExtra("product_type_id",id);
        	if(gd!=null)
        	{
        		intent.putExtra("money",gd.body.intrest_total);
        	}
        	
        	intent.putExtra("name",name);
        	startActivity(intent);
			break;	
        case R.id.ll_leiji:
        	intent.setClass(this,UserRecordActivity_v2.class);
        	intent.putExtra("product_type_id",id);
        	if(gd!=null)
        	{
        		intent.putExtra("money",gd.body.intrest_total);
        	}
        	intent.putExtra("name",name);
        	startActivity(intent);
			break;
       
     
		
			
		}
	}
	class TextWatcherListener implements TextWatcher {
		@Override
		public void afterTextChanged(Editable s) {
			// TODO Auto-generated method stub

		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			// TODO Auto-generated method stub
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			if(s.length()>=6)
			{
				tv_quereng.setTextColor(0xff333333);
			}
			else
			{
				tv_quereng.setTextColor(0xff999999);
			}
			
		}

	}

	protected <T> void startAimActivity(final Class<T> pActClassName) {
		Intent _Intent = new Intent();
		_Intent.setClass(act, pActClassName);
		act.startActivity(_Intent);
	}

	
}
