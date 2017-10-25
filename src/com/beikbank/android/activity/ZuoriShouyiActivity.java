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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.beikbank.android.activity.help.NoneData;
import com.beikbank.android.data.Config_data;
import com.beikbank.android.data.Confing;
import com.beikbank.android.data2.GetUserZhiChan2;
import com.beikbank.android.data2.getLeiJiShouYi;
import com.beikbank.android.data2.getLeiJiShouYi_data;
import com.beikbank.android.data2.getZuoRiShouYi;
import com.beikbank.android.data2.getZuoRiShouYi_data;
import com.beikbank.android.dataparam2.getLeiJiShouYiParam;
import com.beikbank.android.dataparam2.getZuoRiShouYiParam;
import com.beikbank.android.exception.LogHandler;
import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.net.HandlerBase;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.impl.CheckUpdateManager;
import com.beikbank.android.net.impl.ConfigManager;
import com.beikbank.android.net.impl.TongYongManager2;
import com.beikbank.android.utils.BeikBankDialogListener;
import com.beikbank.android.utils.NumberManager;
import com.beikbank.android.utils.Utils;
import com.beikbank.android.utils2.StateBarColor;

import comc.beikbank.android.R;

//昨日收益
public class ZuoriShouyiActivity extends BaseActivity1 implements OnClickListener{
    private static Activity act;
	private TextView titleTv;
	private LinearLayout linear_left;
	private TextView tv_leiji;
	private TextView tv_zuori_text;
	private TextView tv_leiji_text;
	private TextView tv_shouyi_title;
	LinearLayout ll_parent; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_zuorishouyi);
		StateBarColor.init(this,0xffffffff);
		act=this;
		initView();
		initData();
	}
	
	public void initView(){
		//titleTv = (TextView) findViewById(R.id.titleTv);
		//titleTv.setText("资产分布");
		
//		
		ll_parent=(LinearLayout)findViewById(R.id.ll_parent); 
		linear_left = (LinearLayout) findViewById(R.id.linear_left);
		linear_left.setVisibility(View.VISIBLE);
		linear_left.setOnClickListener(this);
            tv_leiji=(TextView) findViewById(R.id.tv_leiji);
	
            tv_leiji_text=(TextView) findViewById(R.id.tv_leiji_title);
            tv_zuori_text=(TextView) findViewById(R.id.tv_zuori_title);
            tv_shouyi_title=(TextView) findViewById(R.id.tv_shouyi_title);
            tv_leiji_text.setOnClickListener(this);
            tv_zuori_text.setOnClickListener(this);
            
	}
	getZuoRiShouYi_data gzr;
	getLeiJiShouYi_data glj;
    private void initData()
    {   
    	ICallBack icb=new ICallBack() {
			
			@Override
			public void back(Object obj) {
				// TODO Auto-generated method stub
				if(obj!=null)
				{
					gzr=(getZuoRiShouYi_data) obj;
					String s1=NumberManager.getString(gzr.body.yesterdayIntrest.yesterdayIntrest,"1",2);
					
					tv_leiji.setText(NumberManager.getGeshiHua(s1,2));
					addChanPin(gzr.body.prodYesterdayIntrest);
				}
				if(gzr==null||gzr.body.prodYesterdayIntrest.size()==0)
				{
					NoneData.setView(act, ll_parent,10);
				}
			}
		};
		
		ICallBack icb_lei=new ICallBack() {
			
			@Override
			public void back(Object obj) {
				if(obj!=null)
				{
					glj=(getLeiJiShouYi_data) obj;
					
					
				}
				
				if(glj==null||glj.body.prodTotalIntrest.size()==0)
				{
					NoneData.setView(act, ll_parent,10);
				}
			}
		};
    	getZuoRiShouYiParam gzr=new getZuoRiShouYiParam();
    	gzr.user_id=BeikBankApplication.getUserCode();
    	
    	TongYongManager2 tym=new TongYongManager2(act, icb,gzr);
    	tym.start();
    	
    	
    	getLeiJiShouYiParam glj=new getLeiJiShouYiParam();
    	glj.user_id=BeikBankApplication.getUserCode();
    	
    	TongYongManager2 tym2=new TongYongManager2(act, icb_lei,glj);
    	tym2.start();
    	
    }
	
    /**
	 * 添加活期 定期产品
	 * @param list
	 */
	private void addChanPin(ArrayList<getZuoRiShouYi> list)
	{      
		   LinearLayout ll_parent=(LinearLayout)findViewById(R.id.ll_parent); 
		   ll_parent.removeAllViews();
		   
		   LinearLayout ll=new LinearLayout(act);
		   View view=null;
		   //LayoutInflater.from(act).inflate(R.layout.page_wealth11,ll,false);
		   ImageView img;
		   TextView tv_name;
		   TextView tv_money;
		   View v;
		   LinearLayout ll0;
		   for(int i=0;i<list.size();i++)
		   {    
			    final getZuoRiShouYi gz=list.get(i);
			   
			   
			    view=LayoutInflater.from(act).inflate(R.layout.activity_zuorishouyi_item,ll,false);
			    ll0=(LinearLayout) view.findViewById(R.id.ll);
			    ll0.setTag(i);
			    ll0.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						Intent intent=new Intent(act,UserRecordActivity_v2.class);
						intent.putExtra("name",gz.product_name);
						
						intent.putExtra("money",glj.body.prodTotalIntrest.get(Integer.parseInt(v.getTag().toString())).intrest_total);
					
						
						
					
			        	intent.putExtra("product_type_id",gz.product_type_id);
			        	
			        	startActivity(intent);
					}
				});
			    
			    img=(ImageView) view.findViewById(R.id.iv);
			    tv_name=(TextView) view.findViewById(R.id.tv_name);
			    tv_money=(TextView) view.findViewById(R.id.tv_money);
			    v=view.findViewById(R.id.v);
			    tv_name.setText(gz.product_name);
			    tv_money.setText(NumberManager.getGeshiHua(gz.intrest_yesterday,2)+"元");
			    if("15".equals(gz.product_type_id))
			    {
			    	img.setImageResource(R.drawable.bg_yuan_hong);
			    }
			    else if("24".equals(gz.product_type_id))
			    {
			    	img.setImageResource(R.drawable.bg_yuan_hong2);
			    }
			    else if("16".equals(gz.product_type_id))
			    {
			    	img.setImageResource(R.drawable.bg_yuan_lan);
			    }
			    else
			    {
			    	img.setImageResource(R.drawable.bg_yuan_huang);
			    }
			    
			    if(i==0)
	    	     {
	    	    	 img.setImageResource(R.drawable.bg_yuan_chen);
	    	     }
	    	     else if(i==1)
	    	     {
	    	    	 img.setImageResource(R.drawable.bg_yuan_hong);
	    	    	 
	    	     }
	    	     else if(i==2)
	    	     {
	    	    	 img.setImageResource(R.drawable.bg_yuan_huang);
	    	    	 
	    	     }
	    	     else if(i==3)
	    	     {
	    	    	 img.setImageResource(R.drawable.bg_yuan_lan);
	    	     }
	    	     else if(i==4)
	    	     {
	    	    	 img.setImageResource(R.drawable.bg_yuan_zhi);
	    	     }
	    	     else if(i==5)
	    	     {
	    	    	 img.setImageResource(R.drawable.bg_yuan_yuan5);
	    	     }
	    	     else if(i==6)
	    	     {
	    	    	 img.setImageResource(R.drawable.bg_yuan_yuan6);
	    	     }
	    	     else if(i==7)
	    	     {
	    	    	 img.setImageResource(R.drawable.bg_yuan_yuan7);
	    	     }
	    	     else if(i==8)
	    	     {
	    	    	 img.setImageResource(R.drawable.bg_yuan_yuan8);
	    	     }
	    	     else
	    	     {
	    	    	 img.setImageResource(R.drawable.bg_yuan_yuan9);
	    	     }
			    
			    ll_parent.addView(view);
		   }
		
	}

	
	
	 /**
		 * 添加活期 定期产品
		 * @param list
		 */
		private void addChanPin2(ArrayList<getLeiJiShouYi> list)
		{      
			   LinearLayout ll_parent=(LinearLayout)findViewById(R.id.ll_parent); 
			   ll_parent.removeAllViews();
			   
			   LinearLayout ll=new LinearLayout(act);
			   View view=null;
			   //LayoutInflater.from(act).inflate(R.layout.page_wealth11,ll,false);
			   ImageView img;
			   TextView tv_name;
			   TextView tv_money;
			   View v;
			   LinearLayout ll0;
			   for(int i=0;i<list.size();i++)
			   {    
				    final getLeiJiShouYi gz=list.get(i);
				    view=LayoutInflater.from(act).inflate(R.layout.activity_zuorishouyi_item,ll,false);
				    ll0=(LinearLayout) view.findViewById(R.id.ll);
				    ll0.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							Intent intent=new Intent(act,UserRecordActivity_v2.class);
							intent.putExtra("name",gz.product_name);
							intent.putExtra("money",gz.intrest_total);
						
							
							
						
				        	intent.putExtra("product_type_id",gz.product_type_id);
				        	
				        	startActivity(intent);
						}
					});
				    
				    img=(ImageView) view.findViewById(R.id.iv);
				    tv_name=(TextView) view.findViewById(R.id.tv_name);
				    tv_money=(TextView) view.findViewById(R.id.tv_money);
				    v=view.findViewById(R.id.v);
				    tv_name.setText(gz.product_name);
				    tv_money.setText(NumberManager.getGeshiHua(gz.intrest_total,2)+"元");
				    if("15".equals(gz.product_type_id))
				    {
				    	img.setImageResource(R.drawable.bg_yuan_hong);
				    }
				    else if("24".equals(gz.product_type_id))
				    {
				    	img.setImageResource(R.drawable.bg_yuan_hong2);
				    }
				    else if("16".equals(gz.product_type_id))
				    {
				    	img.setImageResource(R.drawable.bg_yuan_lan);
				    }
				    else
				    {
				    	img.setImageResource(R.drawable.bg_yuan_huang);
				    }
				    
				    ll_parent.addView(view);
			   }
			
		}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.linear_left:
			finish();
			break;
       
		case R.id.tv_zuori_title:
	        	tv_zuori_text.setBackgroundResource(R.drawable.bg_sysnotice_left);
			    tv_leiji_text.setBackgroundResource(R.drawable.bg_sysnotice_right);
			    tv_zuori_text.setTextColor(0xffffffff);
			    tv_leiji_text.setTextColor(0xffdd2238);  
			    
			    tv_shouyi_title.setText("昨日收益(元)");
			    if(gzr!=null)
			    {
	        	String s1=NumberManager.getString(gzr.body.yesterdayIntrest.yesterdayIntrest,"1",2);
	        	tv_leiji.setText(NumberManager.getGeshiHua(s1, 2));
	        	addChanPin(gzr.body.prodYesterdayIntrest);
	        	if(gzr==null||gzr.body.prodYesterdayIntrest.size()==0)
				{
					NoneData.setView(act, ll_parent,10);
				}
			    }
				break;	 
      
	
        case R.id.tv_leiji_title:
        	tv_zuori_text.setBackgroundResource(R.drawable.bg_sysnotice_left2);
		    tv_leiji_text.setBackgroundResource(R.drawable.bg_sysnotice_right2);
		    tv_leiji_text.setTextColor(0xffffffff);   
		    tv_zuori_text.setTextColor(0xffdd2238);
		    tv_shouyi_title.setText("累计收益(元)");
		    if(glj!=null)
		    {
        	   String s2=NumberManager.getGeshiHua(glj.body.totalIntrest.totalIntrest,2);
        	    tv_leiji.setText(s2);
        	    addChanPin2(glj.body.prodTotalIntrest);
		    }
			break;	
		
			
		}
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
