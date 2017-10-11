package com.beikbank.android.activity;

import com.beikbank.android.activity.help.TypeUtil;
import com.beikbank.android.conmon.FinalIndex;
import com.beikbank.android.dao.BankListDao;
import com.beikbank.android.dao.CardInfoDao;
import com.beikbank.android.data.BankList;
import com.beikbank.android.data.CardInfo;
import com.beikbank.android.data.Qianbao1;
import com.beikbank.android.data.Qianbao1_data;
import com.beikbank.android.data.Qianbao3;
import com.beikbank.android.data.TransactionInfo;
import com.beikbank.android.data2.getDingDanXiangQin;
import com.beikbank.android.data2.getDingDanXiangQin_data;
import com.beikbank.android.dataparam2.getDingDanXiangQinParam;
import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.impl.TongYongManager2;
import com.beikbank.android.utils.BeikBankConstant;
import com.beikbank.android.utils.NumberManager;
import com.beikbank.android.utils2.StateBarColor;
import com.beikbank.android.widget.ClearableEditText;

import comc.beikbank.android.R;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 *copyright yuguohe
 *email: 1374405188@qq.com
 *2015-5-22
 *收支明细详情
 */
public class ShouZhiMinXiActivity extends BaseActivity1 implements View.OnClickListener
{
	TextView title;
	TextView right;
    /**
     * return
     */
    LinearLayout ll;
    TextView leixin;
    TextView money;
    TextView time;
    TextView state;
    TextView shouxu;
    TextView zhanghu;
    TextView dingdan;
    TextView yue;
    TextView beizu;
    TextView name;
    TextView nianhua;
    TextView qixian;
    TextView zhifu;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
	
		setContentView(R.layout.activity_shouzhiminxi);
		StateBarColor.init(this,0xffffffff);
		initView();
		addData();
	}
	private void addData()
	{   
		ICallBack icb=new ICallBack() {
			
			@Override
			public void back(Object obj) {
				if(obj!=null)
				{
					 getDingDanXiangQin_data gd=(getDingDanXiangQin_data) obj;
					 getDingDanXiangQin gdd=gd.body;
					 if("2".equals(gdd.order_type)||"3".equals(gdd.order_type))
					 {
						 setData2(gdd);
					 }
					 else
					 {
						 setData(gdd);
					 }
				}
				
			}
		};
		String id=getIntent().getStringExtra("order_id");
		if(id!=null&&!"".equals(id))
		{
			
		
		   getDingDanXiangQinParam gdd=new getDingDanXiangQinParam();
		   gdd.order_id=getIntent().getStringExtra("order_id");
		   TongYongManager2 tym=new TongYongManager2(this, icb,gdd);
		
		   tym.start();
		}
	}
	private void setData2(getDingDanXiangQin gdd)
	{
		setContentView(R.layout.activity_shouzhiminxi2);
		initView();
		
		 LinearLayout ll0=(LinearLayout) findViewById(R.id.ll);
		 String s0=getIntent().getStringExtra("product_type_pid");
		 if("4".equals(s0))
		 {
			 ll0.setVisibility(View.GONE);
		 }
		 
		 if("5".equals(gdd.order_type))
		 {
			 leixin.setText("提现");
		 }
		 else if("1".equals(gdd.order_type)||"11".equals(gdd.order_type))
		 {
			 leixin.setText("充值");
		 }
		 else if("2".equals(gdd.order_type)||"3".equals(gdd.order_type)||"12".equals(gdd.order_type))
		 {
			 leixin.setText("购买");
		 }
		 else if("4".equals(gdd.order_type))
		 {
			 leixin.setText("赎回");
		 }
		 else if("6".equals(gdd.order_type))
		 {
			 leixin.setText("转让");
		 }
		 else if("13".equals(gdd.order_type)||"15".equals(gdd.order_type))
		 {
			 leixin.setText("回款");
		 }
		 
		 money.setText(NumberManager.getGeshiHua(gdd.amount,2)+"元");
		 time.setText(gdd.create_time);
		 
		 if("0".equals(gdd.order_status))
		 {
			 state.setText("成功");
		 }
		 else if("1".equals(gdd.order_status))
		 {  
			 state.setText("失败");
			 
		 }
		 else
		 {
			 state.setText("处理中");
		 }
		 
		 dingdan.setText(gdd.order_id);
		 //shouxu.setText(NumberManager.getGeshiHua(gdd.service_amt,2)+"元");
		 
		 String s1=getIntent().getStringExtra("yue");
		 if(s1==null||"".equals(s1))
		 {
			 RelativeLayout rl=(RelativeLayout) findViewById(R.id.rl_yue);
			 LinearLayout ll=(LinearLayout) findViewById(R.id.ll_yue);
			 rl.setVisibility(View.GONE);
			 ll.setVisibility(View.GONE);
		 }
		 else
		 {
			 yue.setText(NumberManager.getGeshiHua(s1,2)+"元");
		 }
		 
		 
		 zhanghu.setText("钱包");
		 if("5".equals(gdd.order_type)||"1".equals(gdd.order_type)||"2".equals(gdd.order_type))
		 {
			 String bank=BeikBankApplication.getSharePref(BeikBankConstant.bank);
				String bank_name=BeikBankApplication.getSharePref(BeikBankConstant.bank_name);
				
				zhanghu.setText(bank_name
							+"(尾号"+bank.substring(bank.length()-4,bank.length())+")");
		 }
		 
		 
		 
		 
		 
		 name.setText(gdd.product_name);
		 String s2=NumberManager.getAddString(gdd.increase_interest_return_rate,gdd.benchmark_return_rate, 8);
		 s2=NumberManager.getString(s2,"100",2);
		 nianhua.setText(s2+"%");
		 zhifu.setText(NumberManager.getGeshiHua(gdd.amount,2)+"元");
		 qixian.setText(gdd.term+"天");
		 if("1".equals(gdd.order_status))
		 {
			 RelativeLayout rl=(RelativeLayout) findViewById(R.id.rl_beizhu);
			 LinearLayout ll=(LinearLayout) findViewById(R.id.ll_beizhu);
			 rl.setVisibility(View.VISIBLE);
			 ll.setVisibility(View.VISIBLE);
		 }
	}
	private void setData(getDingDanXiangQin gdd)
	{
		 if("5".equals(gdd.order_type))
		 {
			 leixin.setText("提现");
		 }
		 else if("1".equals(gdd.order_type)||"11".equals(gdd.order_type))
		 {
			 leixin.setText("充值");
		 }
		 else if("2".equals(gdd.order_type)||"3".equals(gdd.order_type)||"2".equals(gdd.order_type))
		 {
			 leixin.setText("购买");
		 }
		 else if("4".equals(gdd.order_type))
		 {
			 leixin.setText("赎回");
		 }
		 else if("6".equals(gdd.order_type))
		 {
			 leixin.setText("转让");
		 }
		 else if("13".equals(gdd.order_type)||"15".equals(gdd.order_type))
		 {
			 leixin.setText("回款");
		 }
		 
		 money.setText(NumberManager.getGeshiHua(gdd.amount,2)+"元");
		 time.setText(gdd.create_time);
		 
		 if("0".equals(gdd.order_status))
		 {
			 state.setText("成功");
		 }
		 else if("1".equals(gdd.order_status))
		 {  
			 state.setText("失败");
			 
		 }
		 else
		 {
			 state.setText("处理中");
		 }
		 
		 dingdan.setText(gdd.order_id);
		 shouxu.setText(NumberManager.getGeshiHua(gdd.service_amt,2)+"元");
		 String s1=getIntent().getStringExtra("yue");
		 yue.setText(NumberManager.getGeshiHua(s1,2)+"元");
		 
		 zhanghu.setText("钱包");
		 if("5".equals(gdd.order_type)||"1".equals(gdd.order_type)||"2".equals(gdd.order_type))
		 {
			 String bank=BeikBankApplication.getSharePref(BeikBankConstant.bank);
				String bank_name=BeikBankApplication.getSharePref(BeikBankConstant.bank_name);
				
				zhanghu.setText(bank_name
							+"(尾号"+bank.substring(bank.length()-4,bank.length())+")");
		 }
	}
    private  void initView()
    {    
    	
    	 tv_error=(TextView)findViewById(R.id.tv_error);
    	 ll=(LinearLayout)findViewById(R.id.linear_left);
    	 ll.setOnClickListener(this);
         title=(TextView)findViewById(R.id.titleTv);
         title.setText("订单详情");
         leixin=(TextView)findViewById(R.id.leixin);
         money=(TextView)findViewById(R.id.money);
         time=(TextView)findViewById(R.id.time);
         state=(TextView)findViewById(R.id.state);
         shouxu=(TextView)findViewById(R.id.shouxu);
         zhanghu=(TextView)findViewById(R.id.zhanghu);
         dingdan=(TextView)findViewById(R.id.dingdan);
         yue=(TextView)findViewById(R.id.yue);
         
         
         beizu=(TextView)findViewById(R.id.beizu);
         zhifu=(TextView)findViewById(R.id.zhifu);
         name=(TextView)findViewById(R.id.name);
         nianhua=(TextView)findViewById(R.id.nianhua);
         qixian=(TextView)findViewById(R.id.qixian);
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
