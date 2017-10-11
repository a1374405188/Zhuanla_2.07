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
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.beikbank.android.activity.help.NoneData;
import com.beikbank.android.adapter.HuoqiAdapter;

import com.beikbank.android.conmon.SystemConfig;
import com.beikbank.android.data.Config_data;
import com.beikbank.android.data.Confing;
import com.beikbank.android.data2.GetUserZhiChan2;
import com.beikbank.android.data2.getHuoQiXiangQin2_1;
import com.beikbank.android.data2.getHuoQiXiangQin2_data;
import com.beikbank.android.data2.getHuoQiXiangQin_data;
import com.beikbank.android.dataparam.getHuoQiXiangQinParam2;
import com.beikbank.android.dataparam2.getHuoQiXiangQinParam;
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

//活期详情
public class LinHuoBaoActivity extends BaseActivity1 implements OnClickListener{
    private  Activity act=this;
	private TextView titleTv;
	private LinearLayout linear_left;
	private LinearLayout ll_fenxiang;
	private LinearLayout ll_zaiquan;
	private LinearLayout ll_zhuanrang;
	private LinearLayout ll_zuori;
	private LinearLayout ll_leiji;
	private LinearLayout ll_zuanrangzhong;
	
	private TextView tv_zhican;
	private TextView tv_zuori;
	private TextView tv_leiji;
	private TextView tv_right;
	private TextView tv_shuhui;
	String name;
	GetUserZhiChan2 gz;
	LinearLayout ll_parent;
	Button bu_goumai;
	//ListView lv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_linhuobao);
		StateBarColor.init(this,0xffffffff);
		name=getIntent().getStringExtra("name");
		gz=(GetUserZhiChan2) getIntent().getSerializableExtra("gz");
		act=this;
		initView();
		initData();
	}
	
	public void initView(){
		titleTv = (TextView) findViewById(R.id.titleTv);
		titleTv.setText(name);
		
		
		linear_left = (LinearLayout) findViewById(R.id.linear_left);
		linear_left.setVisibility(View.VISIBLE);
		linear_left.setOnClickListener(this);
		ll_zuori = (LinearLayout) findViewById(R.id.ll_zuori);
		ll_leiji = (LinearLayout) findViewById(R.id.ll_leiji);
		ll_zuori.setOnClickListener(this);
		ll_leiji.setOnClickListener(this);
	     tv_leiji=(TextView) findViewById(R.id.tv_leiji);
	     tv_shuhui=(TextView) findViewById(R.id.tv_shuhui);
	     tv_zhican=(TextView) findViewById(R.id.tv_zhican);
	     tv_zuori=(TextView) findViewById(R.id.tv_zuori);
	     tv_right=(TextView) findViewById(R.id.right);
	     tv_right.setText("已转让");
	     tv_right.setVisibility(View.VISIBLE);
	     tv_right.setOnClickListener(this);
	     
	     ll_zhuanrang=(LinearLayout) findViewById(R.id.ll_zhuanrang);
	     ll_zuanrangzhong=(LinearLayout) findViewById(R.id.ll_zuanrangzhong);
	     ll_zuanrangzhong.setOnClickListener(this);
	     
	     ll_parent=(LinearLayout) findViewById(R.id.ll_parent);
	  
	     ll_zhuanrang.setOnClickListener(this);
	    // lv=(ListView) findViewById(R.id.lv);
	     
	     bu_goumai=(Button) findViewById(R.id.bu_goumai);
	     bu_goumai.setOnClickListener(this);
	     
//	     NoneData nd=new NoneData();
//	     nd.setView(act, ll_parent, 1);
	}
	getHuoQiXiangQin2_data gd;
   public void initData()
   {   
	   ICallBack icb_ghq=new ICallBack() {
		
		@Override
		public void back(Object obj) {
			ArrayList<getHuoQiXiangQin2_1>  list=null;
			if(obj!=null)
			{
				gd=(getHuoQiXiangQin2_data) obj;
				tv_leiji.setText(NumberManager.getGeshiHua(gd.body.intreTotal,2));
				tv_zhican.setText(NumberManager.getGeshiHua(gd.body.currCapValue,2));
				tv_zuori.setText(NumberManager.getGeshiHua(gd.body.intreYesterday,2));
				
				tv_shuhui.setText(NumberManager.getGeshiHua(gd.body.transCapValue,2));
				//HuoqiAdapter ha=new HuoqiAdapter(act,gd.body.assetsFundList);
				//lv.setAdapter(ha);
				list=gd.body.assetsFundList;
				addView(gd.body.assetsFundList);
				if(list!=null&&list.size()>0)
				{
					bu_goumai.setEnabled(true);
				}
			}
			else
			{
				
			}
			if(list==null||list.size()==0)
			{
				NoneData.setView(act,ll_parent,1);
			}
		}
	};
//	   getHuoQiXiangQinParam ghq=new getHuoQiXiangQinParam();
//	   ghq.user_id=BeikBankApplication.getUserCode();
//	   TongYongManager2 tm2=new TongYongManager2(act, icb_ghq,ghq);
//	   tm2.start();
	   
	   
	   
	
	   
	   getHuoQiXiangQinParam2 ghq2=new getHuoQiXiangQinParam2();
	   ghq2.user_id=BeikBankApplication.getUserCode();
	   ghq2.product_type_id=gz.product_type_id;
	   TongYongManager2 tm3=new TongYongManager2(act, icb_ghq,ghq2);
	   tm3.start();
	   
	   
	   
	   
   }
  
   private void addView(ArrayList<getHuoQiXiangQin2_1> list)
   {
	   getHuoQiXiangQin2_1 gqx=null;
	   LinearLayout ll=new LinearLayout(this);
	   for(int i=0;i<list.size();i++)
	   {
		   gqx=list.get(i);
		   View view=getLayoutInflater().inflate(R.layout.activity_linhuobao_item1,ll,false);
			
			TextView name=(TextView) view.findViewById(R.id.tv_name);
			TextView state=(TextView) view.findViewById(R.id.tv_state);
			TextView money=(TextView) view.findViewById(R.id.tv_money);
			TextView zuori=(TextView) view.findViewById(R.id.tv_zuori);
			TextView dangqian=(TextView) view.findViewById(R.id.tv_dangqian);
			
			name.setText(gqx.prodName);
			state.setText(gqx.status);
			money.setText(NumberManager.getGeshiHua(gqx.calAmount,2));
			zuori.setText(NumberManager.getGeshiHua(gqx.intrestYesterday,2));
			dangqian.setText(NumberManager.getGeshiHua(gqx.intrestTotal,2));
			view.setTag(gqx);
			view.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					 getHuoQiXiangQin2_1 gqx=(getHuoQiXiangQin2_1) v.getTag();
					Intent intent=getIntent();
					intent.setClass(act,HuoqiDetailActivity4.class);
					intent.putExtra("assets_id",gqx.assets_id);
					intent.putExtra("prod_id",gd.body.prodId);
					intent.putExtra("name",gqx.prodName);
					act.startActivity(intent);
					
				}
			});
			ll_parent.addView(view);
		   
	   }
	   
	   
	   
	   
   }
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent=new Intent();
		switch(v.getId()){
		case R.id.bu_goumai:
			intent.setClass(this,HuoqiZhuanRangActivity.class);
			if(gd!=null)
			{
			  intent.putExtra("list",gd.body.assetsFundList);
			}
        	
        	startActivity(intent);
			
			break; 
		
		
		case R.id.ll_zuanrangzhong:
			intent.setClass(this,HuoqiDetailActivity2.class);
			//intent.putExtra("list",gd.body.assetsFundList);
        	startActivity(intent);
			break;      
		case R.id.right:
			intent.setClass(this,HuoqiDetailActivity3.class);
			//intent.putExtra("list",gd.body.assetsFundList);
        	startActivity(intent);
			break; 
		case R.id.linear_left:
			finish();
			break;      
      

        case R.id.ll_zuori:
        	
        	intent.setClass(this,UserRecordActivity_v2.class);
        	if(gz!=null)
        	{
        		intent.putExtra("product_type_id",gz.product_type_id);
        		intent.putExtra("name",gz.product_name);
        	}
        	if(gd!=null)
        	{
        		intent.putExtra("money",gd.body.intreTotal);
        	}
    
        	startActivity(intent);
	           break;    
	    case R.id.ll_leiji:
	        	
	        	intent.setClass(this,UserRecordActivity_v2.class);
	        	if(gz!=null)
	        	{
	        		intent.putExtra("product_type_id",gz.product_type_id);
	        		intent.putExtra("name",gz.product_name);
	        	}
	        	if(gd!=null)
	        	{
	        		intent.putExtra("money",gd.body.intreTotal);
	        	}
	        	startActivity(intent);
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
