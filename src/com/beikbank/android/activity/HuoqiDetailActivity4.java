package com.beikbank.android.activity;

import java.util.ArrayList;

import u.aly.cb;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.beikbank.android.activity.HuoqiZhuanRangActivity.TextWatcherListener;
import com.beikbank.android.activity.help.GoumaiManager;
import com.beikbank.android.activity.help.LiuChenManager;
import com.beikbank.android.activity.help.NoNetShow;
import com.beikbank.android.adapter.HuoqiAdapter;
import com.beikbank.android.adapter.ZhiChanAdapter;
import com.beikbank.android.conmon.SystemConfig;
import com.beikbank.android.data.CheckBank;
import com.beikbank.android.data.CheckBank_data;
import com.beikbank.android.data.DingqiPI;
import com.beikbank.android.data.DingqiPI_data;
import com.beikbank.android.data.FundInfo;
import com.beikbank.android.data2.getDingQiXiangQing_data;
import com.beikbank.android.data2.getHuoQiXiangQin2_1;
import com.beikbank.android.dataparam.CheckBankParam;
import com.beikbank.android.dataparam.DingqiPIParam;
import com.beikbank.android.dataparam2.HuoQiShuHuiParam2;
import com.beikbank.android.dataparam2.getDingQiXiangQingParam;
import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.impl.DingqiPIManager;
import com.beikbank.android.net.impl.TongYongManager;
import com.beikbank.android.net.impl.TongYongManager2;
import com.beikbank.android.utils.BeikBankConstant;
import com.beikbank.android.utils.DialogManager;
import com.beikbank.android.utils.NumberManager;
import com.beikbank.android.utils.ViewDataUtil;
import com.beikbank.android.utils2.StateBarColor;
import com.beikbank.android.widget.ClearableEditText;
import coma.beikbank.android.R;



/** 活期资产详情 
 * Created by Administrator on 2015/3/16.
 */
public class HuoqiDetailActivity4 extends BaseActivity1 implements View.OnClickListener
{   
    Activity act=this;
    TextView title;
    /**
     * return
     */
    LinearLayout ll1;
    ArrayList<getHuoQiXiangQin2_1> list;
    ListView lv;
    TextView tv_name;
    TextView tv_money;
    TextView tv_goumai_money;
    TextView tv_nianhua;
    TextView tv_qixi;
    TextView tv_status;
    TextView tv_dangqian;
    LinearLayout ll_fenxian;
    LinearLayout ll_touzhi;
    LinearLayout ll_zhuanrang;
    Button bu_goumai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_huoqi4);
        StateBarColor.init(this,0xffffffff);
        
     
        init();
        initData();
     
     
    }
    String assets_id;
    String prod_id;
    getDingQiXiangQing_data gd;
    private void initData()
    {
    	assets_id=getIntent().getStringExtra("assets_id");
		prod_id=getIntent().getStringExtra("prod_id");
	  ICallBack icb=new ICallBack() {
			
			@Override
			public void back(Object obj) {
				// TODO Auto-generated method stub
				if(obj!=null)
				{
					gd=(getDingQiXiangQing_data) obj;
					 tv_money.setText(NumberManager.getGeshiHua(gd.body.total_payable,2));
				     tv_goumai_money.setText(NumberManager.getGeshiHua(gd.body.current_principal_balance,2)+"元");
				    String s1=NumberManager.getString(gd.body.intrest_year,"100",2);
				    tv_nianhua.setText(s1+"%");
				    tv_qixi.setText(gd.body.original_calculate_date);
				    String s2=NumberManager.getString(gd.body.intrest_total,"1",2);
				    tv_dangqian.setText(s2+"元");
				    tv_status.setText(gd.body.status);
				}
			}
		};
		getDingQiXiangQingParam gp=new getDingQiXiangQingParam();
		gp.assets_id=assets_id;
		gp.prod_id=prod_id;
		gp.user_id=BeikBankApplication.getUserCode();
		TongYongManager2 tym2=new TongYongManager2(act, icb,gp);
		tym2.start();
    	
    	
    }
    String name;
    private void init()
    {   
    	Intent intent=getIntent();
    	name=intent.getStringExtra("name");
    	//String status=intent.getStringExtra("status");
        ll1=(LinearLayout)findViewById(R.id.linear_left);
        title=(TextView)findViewById(R.id.titleTv);
        
        ll1.setOnClickListener(this);
        title.setText("资产详情");
        
        tv_name=(TextView) findViewById(R.id.tv_name);
        tv_money=(TextView) findViewById(R.id.tv_money);
        tv_goumai_money=(TextView) findViewById(R.id.tv_goumai_money);
        tv_dangqian=(TextView) findViewById(R.id.tv_dangqian);
        tv_nianhua=(TextView) findViewById(R.id.tv_nianhua);
        tv_qixi=(TextView) findViewById(R.id.tv_qixi);
        tv_status=(TextView) findViewById(R.id.tv_status);
        ll_fenxian=(LinearLayout) findViewById(R.id.ll_fenxian);
        ll_touzhi=(LinearLayout) findViewById(R.id.ll_touzhi);
        ll_zhuanrang=(LinearLayout) findViewById(R.id.ll_zhuanrang);
        
        bu_goumai=(Button) findViewById(R.id.bu_goumai);
        bu_goumai.setOnClickListener(this);
        tv_name.setText(name);
       
        ll_fenxian.setOnClickListener(this);
        ll_touzhi.setOnClickListener(this);
        ll_zhuanrang.setOnClickListener(this);
    }
   

    
    
   public void onClick(View view)
   {  
	   Intent intent=getIntent();
	   boolean do_success=BeikBankApplication.mSharedPref.getSharePrefBoolean(BeikBankConstant.DO_SUCCESS,false);

      switch (view.getId())
      {
         case R.id.linear_left:
             finish();
          break;
         case R.id.bu_goumai:
        	 if(gd!=null)
        	 {
        		 createDialog(gd.body.total_payable);
        	 }
             
          break;
         case R.id.ll_fenxian:
        	 
        	   String url=SystemConfig.huodong_url+"#!/fxfs/"+assets_id;
        	   intent.setClass(act,HuodongActivity2.class);
        	    intent.putExtra("title","风险分散");
				intent.putExtra("url",url);
				act.startActivity(intent);
          break;
         case R.id.ll_touzhi:
        	 String product_type_pid=getIntent().getStringExtra("product_type_pid");
        	 url=SystemConfig.huodong_url+"#!/bondList/"+assets_id+"/"+product_type_pid;
      	   intent.setClass(act,HuodongActivity2.class);
      	    intent.putExtra("title","投资债权");
				intent.putExtra("url",url);
				act.startActivity(intent);
          break;
    
 
      }
   }

   Dialog   dialog4;
  	ClearableEditText et_mima;
  	TextView tv_quereng;
  	HuoqiDetailActivity4 context=this;
  	private void createDialog(final String money)
  	{
  		 LinearLayout ll=new LinearLayout(context);
  		    View v=LayoutInflater.from(context).inflate(
  	  			   R.layout.zhuangrang_quereng,ll,false);
  		    
  		   et_mima=(ClearableEditText) v.findViewById(R.id.et_mima);
  		    et_mima
  			.addTextChangedListener(new TextWatcherListener());
  		    TextView tv_qvxiao=(TextView) v.findViewById(R.id.tv_qvxiao);
  		    tv_quereng=(TextView) v.findViewById(R.id.tv_quereng);
  		    context.tv_error=(TextView) v.findViewById(R.id.tv_error);
  		    context.ll_error=(LinearLayout) v.findViewById(R.id.ll_error);
  		    TextView tv_mima=(TextView) v.findViewById(R.id.tv_mima);
  		    TextView tv_money=(TextView) v.findViewById(R.id.money);
  		    tv_money.setText("￥"+NumberManager.getGeshiHua(money,2));
  		    
  		    
  		    tv_mima.setOnClickListener(new OnClickListener() {
  				
  				@Override
  				public void onClick(View v) {
  					Intent intent=new Intent(context,ForgetPwdRealnameActivity.class);
  					intent.putExtra(BeikBankConstant.IS_FORGETTRANSACTIONPWD,true);
  					intent.putExtra(BeikBankConstant.INTENT_PHONENUMBER,BeikBankApplication.getPhoneNumber());
  					context.startActivity(intent);
  					
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
  								zuanrang(money);
  							}
  						}
  					});
  		    dialog4=DialogManager.getDiaolg1(this, v);
  	        dialog4.setCanceledOnTouchOutside(false);
  	    	dialog4.show();
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
  	public void zuanrang(final String money)
	{   
		
		
		
		
		ICallBack icb=new ICallBack() {
			
			@Override
			public void back(Object obj) {
				if(obj!=null)
				{
					Intent intent=getIntent();
					intent.setClass(act,JiaoYiXiangQingActivity2.class);
					//intent.putExtra("order_id",cdd.order_id);
					intent.putExtra("name",name);
					intent.putExtra("money",money);
					startActivity(intent);
				}
			}
		};
		
		
		
		ArrayList<Integer> list=new ArrayList<Integer>();
		HuoQiShuHuiParam2 hp=new HuoQiShuHuiParam2();
		hp.assets_id=list;
		TongYongManager2 tym2=new TongYongManager2(context, icb,hp);
		tym2.start();
	}

}
