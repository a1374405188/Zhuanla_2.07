package com.beikbank.android.activity;

import com.beikbank.android.activity.help.LiuChenSelect;
import com.beikbank.android.activity.help.NoNetShow;
import com.beikbank.android.data.CheckBank;
import com.beikbank.android.data.CheckBank_data;
import com.beikbank.android.data.Poundage;
import com.beikbank.android.data.Poundage_data;
import com.beikbank.android.data.Qianbao1_data;
import com.beikbank.android.data.Qianbao2_data;
import com.beikbank.android.data.Qianbao4;
import com.beikbank.android.data.Qianbao4_data;
import com.beikbank.android.data.UserInfo;
import com.beikbank.android.data.Yuer;
import com.beikbank.android.data.Yuer_data;
import com.beikbank.android.data2.getQianBao_data;
import com.beikbank.android.data2.getTiXianQianZhi_data;
import com.beikbank.android.dataparam.CheckBankParam;
import com.beikbank.android.dataparam.QianbaoParam1;
import com.beikbank.android.dataparam.QianbaoParam2;
import com.beikbank.android.dataparam.TotalMoneyParam;
import com.beikbank.android.dataparam.YuerParam;
import com.beikbank.android.dataparam2.getQianBaoParam;
import com.beikbank.android.dataparam2.getTiXianQianZhiParam;
import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.impl.HandMoneyManager;
import com.beikbank.android.net.impl.Qianbao1Manager;
import com.beikbank.android.net.impl.Qianbao2Manager;
import com.beikbank.android.net.impl.Qianbao3Manager;
import com.beikbank.android.net.impl.Qianbao4Manager;
import com.beikbank.android.net.impl.Qianbao6Manager;
import com.beikbank.android.net.impl.TongYongManager;
import com.beikbank.android.net.impl.TongYongManager2;
import com.beikbank.android.utils.BeikBankConstant;
import com.beikbank.android.utils.NumberManager;
import com.beikbank.android.utils2.StateBarColor;
import com.beikbank.android.widget.ClearableEditText;

import comc.beikbank.android.R;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 1
 *copyright yuguohe
 *email: 1374405188@qq.com
 *2015-5-22
 *钱包2
 */
public class QianbaoActivity8 extends BaseActivity2 implements View.OnClickListener
{   

	 /**
     * return
     */
    LinearLayout ll;
    TextView title;
	TextView right;
	 /**
     * 充值
     */
    Button bu1;
    /**
     * 提现
     */
    Button bu2;
    /**
	 * 余额
	 */
	TextView tv1;
	/**
	 * 可用余额
	 */
	TextView tv2;
	/**
	 * 充值未使用
	 */
	TextView tv3;
   
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		act=this;
		setContentView(R.layout.activity_qianbao8);
		StateBarColor.init(this,0xffffffff);
		
	    initView();
	    boolean isnet=isNetworkConnected(this);
	    if(!isnet)
        {
        	NoNetShow.show(this,"我的钱包",null);
        	return;
        }
	}
	private void initData()
	{  
       ICallBack icb_tixian=new ICallBack() {
			
			@Override
			public void back(Object obj) {
				if(obj!=null)
				{
					getTiXianQianZhi_data gd=(getTiXianQianZhi_data) obj;
					String s2=NumberManager.getGeshiHua(gd.body.repay_amt_have,2);
					String s3=NumberManager.getGeshiHua(gd.body.recharge_amt_have,2);
					tv2.setText(s2);
					tv3.setText(s3);
					
				}
			}
		};
		getTiXianQianZhiParam gti=new getTiXianQianZhiParam();
		gti.acc_id=BeikBankApplication.getAccid();
		gti.user_code=BeikBankApplication.getUserCode();
		TongYongManager2 tmy2=new TongYongManager2(act, icb_tixian,gti);
		tmy2.start();
		
		
		
     ICallBack icb=new ICallBack() {
			
			@Override
			public void back(Object obj) {
				
				if(obj!=null)
				{  
					
				
					getQianBao_data gd=(getQianBao_data) obj;
					tv1.setText(NumberManager.getGeshiHua(gd.body.card.acc_amount, 2));
					
				}
				
			}
		};

		
		
		
		
		getQianBaoParam gqp=new getQianBaoParam();
		gqp.acc_type_id="1";
	    gqp.user_code=BeikBankApplication.getUserCode();
		TongYongManager2 tym2=new TongYongManager2(act, icb, gqp);
		tym2.start();
		
	}
   
	
	
	@Override
	public void onClick(View v) {
		Intent intent=new Intent();
		 LiuChenSelect ls=new LiuChenSelect();
		switch (v.getId()) {
		case R.id.linear_left:
            finish();
			break;
		case R.id.bu1:
			
			    ls.startNext(act,1,null);
			break;
		case R.id.bu2:
			
			  ls.startNext(act,0,null);
			
			break;
		case R.id.right:
			intent.setClass(this,QianbaoActivity4.class);
            startActivity(intent);
			break;		
		}
	}
	
	@Override
	protected void onResume() {
		
		super.onResume();
		initData();
	}
	
	 private  void initView()
	    {
	    	 ll=(LinearLayout)findViewById(R.id.linear_left);
	    	 
	    	 tv1=(TextView) findViewById(R.id.tv1);
	    	 tv2=(TextView) findViewById(R.id.tv2);
	    	 tv3=(TextView) findViewById(R.id.tv3);
	    	 title=(TextView)findViewById(R.id.titleTv);
	         title.setText("钱包");
	         right=(TextView)findViewById(R.id.right);
	         right.setVisibility(View.VISIBLE);
	         right.setText("收支明细");
	         bu1=(Button) findViewById(R.id.bu1);
	         bu2=(Button) findViewById(R.id.bu2);
	         
	         bu1.setOnClickListener(this);
	         bu2.setOnClickListener(this);
	         ll.setOnClickListener(this);
	         right.setOnClickListener(this);
	    }
 
	
   
}
