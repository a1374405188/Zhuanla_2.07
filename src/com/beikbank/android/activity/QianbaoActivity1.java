package com.beikbank.android.activity;

import com.beikbank.android.data.CheckBank;
import com.beikbank.android.data.CheckBank_data;
import com.beikbank.android.data.Qianbao4_data;
import com.beikbank.android.data.Yuer;
import com.beikbank.android.dataparam.CheckBankParam;
import com.beikbank.android.dataparam.QianbaoParam1;
import com.beikbank.android.dataparam.TotalMoneyParam;
import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.impl.Qianbao1Manager;
import com.beikbank.android.net.impl.Qianbao6Manager;
import com.beikbank.android.net.impl.TongYongManager;
import com.beikbank.android.utils.BeikBankConstant;
import com.beikbank.android.utils.NumberManager;
import com.beikbank.android.utils2.StateBarColor;

import comc.beikbank.android.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 *copyright yuguohe
 *email: 1374405188@qq.com
 *2015-5-22
 *钱包
 */
public class QianbaoActivity1 extends BaseActivity1 implements View.OnClickListener
{
	TextView title;
	TextView right;
	/**
	 * 余额
	 */
	TextView tv1;
	/**
	 * 钱包详情
	 */
	TextView tv3;
    /**
     * return
     */
    LinearLayout ll;
    /**
     * 充值
     */
    Button bu1;
    /**
     * 提现
     */
    Button bu2;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_qianbao1);
		StateBarColor.init(this,0xffffffff);
		initView();
		
	}
	
	String money;
	ICallBack icb=new ICallBack() {
		
		@Override
		public void back(Object obj) {
			if(obj!=null)
			{  
			   Qianbao4_data qd=(Qianbao4_data) obj;
			   money=qd.data.activeAmount;
			   String s1=NumberManager.getAddString(qd.data.activeAmount,qd.data.frozenAmountPurchase,8);
			   s1=NumberManager.getAddString(s1,qd.data.frozenAmountRedeem,8);
			   s1=NumberManager.getAddString(s1,qd.data.frozenAmountWithdraw,2);
			   tv1.setText("¥ "+NumberManager.getGeshiHua(s1,2));
			}
		}
	};
	
	private void initData()
	{   
		TotalMoneyParam tp=new TotalMoneyParam();
		tp.memberID=BeikBankApplication.getUserid();
		Qianbao6Manager qm=new Qianbao6Manager(this, icb,tp);
		qm.start();
	}
    private  void initView()
    {
    	 ll=(LinearLayout)findViewById(R.id.linear_left);
         title=(TextView)findViewById(R.id.titleTv);
         title.setText("钱包");
         right=(TextView)findViewById(R.id.right);
         right.setVisibility(View.VISIBLE);
         right.setText("收支明细");
         bu1=(Button) findViewById(R.id.bu1);
         bu2=(Button) findViewById(R.id.bu2);
         tv3=(TextView)findViewById(R.id.tv3);
         tv3.setOnClickListener(this);
         
         tv1=(TextView) findViewById(R.id.tv1);
         bu1.setOnClickListener(this);
         bu2.setOnClickListener(this);
         ll.setOnClickListener(this);
         right.setOnClickListener(this);
    }
    
	@Override
	protected void onResume() {
		
		super.onResume();
		initData();
	}
	@Override
	public void onClick(View v) {
		Intent intent=new Intent();
		switch (v.getId()) {
		case R.id.linear_left:
            finish();
			break;
		case R.id.bu1:
//            intent.setClass(this,QianbaoActivity2.class);
//            startActivity(intent);
			checkBank();
			break;
		case R.id.bu2:
			intent.setClass(this,QianbaoActivity3.class);
			intent.putExtra("index",money);
            startActivity(intent);
			break;
		case R.id.tv3:
			intent.setClass(this,QianbaoActivity7.class);
            startActivity(intent);
			break;		
		case R.id.right:
			intent.setClass(this,QianbaoActivity4.class);
            startActivity(intent);
			break;			
		default:
			break;
		}
		
	}
	//检查是否需要绑定银行卡
		private void checkBank()
		{
			CheckBankParam cbp=new CheckBankParam();
			cbp.memberID=BeikBankApplication.getUserid();
			TongYongManager tym=new TongYongManager(this, icb2,cbp);
			tym.start();
			
			
		}
		ICallBack icb2=new ICallBack() {
			
			@Override
			public void back(Object obj) {
				
				if(obj!=null)
				{     
					
					CheckBank_data cd=(CheckBank_data) obj;
					CheckBank cb=cd.data;
					if(cb.UserCardLimit.equals("0"))
					   {   
						 Intent intent=new Intent(QianbaoActivity1.this,BankBindActivity2.class);
					     startActivity(intent);
						  return ;
					   }
					Intent intent=new Intent(QianbaoActivity1.this,QianbaoActivity2.class);
					
			        startActivity(intent);
					
				}
			}
		};
	/**
	 * 计算总资产
	 * @param ye
	 * @return
	 */
	 public static String countTotal(Yuer ye)
	  {
		  String s1=ye.activeAmount;
		  //String s2=ye.frozenAmountPurchase;
		  String s3=ye.frozenAmountRedeem;
		  //String s4=ye.frozenAmountWithdraw;
//		  s1=NumberManager.getAddString(s1,s2,8);
		  s1=NumberManager.getAddString(s1,s3,8);
//		  s1=NumberManager.getAddString(s1,s4,4);
		  return s1;
	  }
}
