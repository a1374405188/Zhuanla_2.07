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

import com.beikbank.android.data.Config_data;
import com.beikbank.android.data.Confing;
import com.beikbank.android.data.type.ZhiChan;
import com.beikbank.android.data2.GetUserZhiChan2;
import com.beikbank.android.exception.LogHandler;
import com.beikbank.android.net.HandlerBase;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.impl.CheckUpdateManager;
import com.beikbank.android.net.impl.ConfigManager;
import com.beikbank.android.utils.BeikBankDialogListener;
import com.beikbank.android.utils.NumberManager;
import com.beikbank.android.utils.Utils;
import com.beikbank.android.utils2.StateBarColor;
import com.beikbank.android.widget3.ZhiCanView;
import coma.beikbank.android.R;



//资产分布
public class ZhicanFenbuActivity extends BaseActivity1 implements OnClickListener{
    private static Activity act;
	private TextView titleTv;
	private LinearLayout linear_left;
	ZhiCanView zcv;
	ArrayList<GetUserZhiChan2> list;
	
	String money;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_zhicanfenbu);
		StateBarColor.init(this,0xffffffff);
		list=(ArrayList<GetUserZhiChan2>) getIntent().getSerializableExtra("list");
		money=getIntent().getStringExtra("money");
		act=this;
		initView();
	}
	
	public void initView(){
		titleTv = (TextView) findViewById(R.id.titleTv);
		titleTv.setText("资产分布");
		zcv=(ZhiCanView) findViewById(R.id.zcview);
		TextView tv_zhichan=(TextView) findViewById(R.id.tv_zhichan);
		String s1=NumberManager.getGeshiHua(money,2);
		tv_zhichan.setText(s1);
		
		
		
		linear_left = (LinearLayout) findViewById(R.id.linear_left);
		linear_left.setVisibility(View.VISIBLE);
		linear_left.setOnClickListener(this);

		LinearLayout ll_parent=(LinearLayout) findViewById(R.id.ll_parent);
	    if(list!=null)
	    {    
	    	 LinearLayout ll=new LinearLayout(act);
	    	 ImageView iv;
	    	 TextView name;
	    	 TextView money;
	    	 GetUserZhiChan2 gz=null;
	    	 for(int i=0;i<list.size();i++)
	    	 {   
	    		 gz=list.get(i);
	    		 View view=LayoutInflater.from(this).inflate(R.layout.activity_zhicanfenbu_item,ll,false); 
	    	     iv=(ImageView) view.findViewById(R.id.iv);
	    	     name=(TextView) view.findViewById(R.id.name);
	    	     money=(TextView) view.findViewById(R.id.money);
	    	     name.setText(gz.product_name);
	    	     view.setTag(gz);
	    	     money.setText(NumberManager.getGeshiHua(gz.currCapValue,2));
	    	     if(i==0)
	    	     {
	    	    	 iv.setImageResource(R.drawable.bg_yuan_chen);
	    	     }
	    	     else if(i==1)
	    	     {
	    	    	 iv.setImageResource(R.drawable.bg_yuan_hong);
	    	    	 
	    	     }
	    	     else if(i==2)
	    	     {
	    	    	 iv.setImageResource(R.drawable.bg_yuan_huang);
	    	    	 
	    	     }
	    	     else if(i==3)
	    	     {
	    	    	 iv.setImageResource(R.drawable.bg_yuan_lan);
	    	     }
	    	     else if(i==4)
	    	     {
	    	    	 iv.setImageResource(R.drawable.bg_yuan_zhi);
	    	     }
	    	     else if(i==5)
	    	     {
	    	    	 iv.setImageResource(R.drawable.bg_yuan_yuan5);
	    	     }
	    	     else if(i==6)
	    	     {
	    	    	 iv.setImageResource(R.drawable.bg_yuan_yuan6);
	    	     }
	    	     else if(i==7)
	    	     {
	    	    	 iv.setImageResource(R.drawable.bg_yuan_yuan7);
	    	     }
	    	     else if(i==8)
	    	     {
	    	    	 iv.setImageResource(R.drawable.bg_yuan_yuan8);
	    	     }
	    	     else
	    	     {
	    	    	 iv.setImageResource(R.drawable.bg_yuan_yuan9);
	    	     }
	    	     view.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						GetUserZhiChan2 gz=(GetUserZhiChan2) v.getTag();
						if("4".equals(gz.product_type_pid))
						{
							Intent intent=new Intent(act,LinHuoBaoActivity.class);
							intent.putExtra("name",gz.product_name);
							intent.putExtra("product_type_pid",gz.product_type_pid);
							intent.putExtra("gz",gz);
							act.startActivity(intent);
						}
						else if(gz.product_type_pid==null)
						{
							Intent intent=new Intent(act,QianbaoActivity8.class);
					    	
					    	   intent.putExtra("money",gz.currCapValue);
					    	
							act.startActivity(intent);
						}
						else
						{
							Intent intent=new Intent(act,DingqiLicaiActivity.class);
					    	intent.putExtra("id",gz.product_type_id);
					    	intent.putExtra("name",gz.product_name);
					    	intent.putExtra("product_type_pid",gz.product_type_pid);
							act.startActivity(intent);
						}
						
					}
				});
	    	     ll_parent.addView(view);
	    	 }
	    	 
	    	 
	    	 
	    	 
	    	    ArrayList<Integer> list0=new ArrayList<Integer>();
	    	    for(int i=0;i<list.size();i++)
	    	    {   
	    	    	String s=list.get(i).currCapValue;
	    	        double d=NumberManager.StoD(s);
	    	    	list0.add((int)d);
	    	    	
	    	    	
	    	    }
	    	    
	    	    zcv.init2(this, list0);
	    }
		
	  
	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.linear_left:
			finish();
			break;


	
		
			
		}
	}

	
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	


	
}
