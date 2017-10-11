package com.beikbank.android.activity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.beikbank.android.activity.help.TypeUtil;
import com.beikbank.android.dao.BankListDao;
import com.beikbank.android.dao.CardInfoDao;
import com.beikbank.android.data.BankList;
import com.beikbank.android.data.CardInfo;
import com.beikbank.android.data.type.DingDan;
import com.beikbank.android.data2.GetChanPin;
import com.beikbank.android.data2.getDingDanXiangQin;
import com.beikbank.android.data2.getDingDanXiangQin_data;
import com.beikbank.android.dataparam2.getDingDanXiangQinParam;
import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.impl.TongYongManager2;
import com.beikbank.android.utils.BeikBankConstant;
import com.beikbank.android.utils.NumberManager;
import com.beikbank.android.utils2.StateBarColor;

import comc.beikbank.android.R;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * copyright yuguohe email: 1374405188@qq.com 2015-5-22 交易详情
 */
public class JiaoYiXiangQingActivity extends BaseActivity1 implements
		View.OnClickListener {

	TextView title;
	TextView right;
	/**
	 * return
	 */
	LinearLayout ll;

	TextView leixing;
	TextView fangshi;
	TextView jinge;
	TextView dingdan;
	TextView zhuangtai;
	TextView tishi;
	ImageView iv;
	Handler handler = new Handler();
    Button bu;
    LinearLayout ll_xiugai;
    GetChanPin gcp;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jiaoyi_xiangqing);
		StateBarColor.init(this, 0xffffffff);
		gcp=(GetChanPin) getIntent().getSerializableExtra("gcp");
		initView();

		BeikBankApplication.mSharedPref.putSharePrefBoolean(
				BeikBankConstant.is_after_pay, true);

		// handler.postDelayed(run,4000);
		// handler.postDelayed(run,8000);
        initData();
	}
	public void initData()
	{   
		ICallBack icb=new ICallBack() {
			
			@Override
			public void back(Object obj) {
				if(obj!=null)
				{   
					getDingDanXiangQin_data gd=(getDingDanXiangQin_data) obj;
					getDingDanXiangQin gdd=gd.body;
					if(gcp!=null)
					{
						if("0".equals(gdd.order_status))
						{
							if("2".equals(gdd.order_type)||"3".equals(gdd.order_type))
							{
								if("3".equals(gcp.product_type_pid))
								{
									ll_xiugai.setVisibility(View.VISIBLE);
								}
							}
						}
					}
					
					
					
					DingDan dd=new DingDan();
					dd.amount=gdd.amount;
					if("1".equals(gdd.order_type)||"2".equals(gdd.order_type))
					{
						dd.fangshi="0";
					}
					else
					{
						dd.fangshi="1";
					}
					if("1".equals(gdd.order_type))
					{
						dd.leixing=3;
						leixing.setText("充值");
					}
					else if("2".equals(gdd.order_type))
					{
						dd.leixing=1;
						leixing.setText("购买"+gdd.product_name);
					}
					else if("3".equals(gdd.order_type))
					{
						dd.leixing=1;
						fangshi.setText("钱包");
						leixing.setText("购买"+gdd.product_name);
					}
					else if("4".equals(gdd.order_type))
					{
						dd.leixing=4;
						leixing.setText("转让");
					}
					else if("5".equals(gdd.order_type))
					{
						dd.leixing=6;
						leixing.setText("提现");
					}
					
					dd.orderNumber=gdd.order_id;
					dd.retMsg=gdd.error_msg;
					dd.status=gdd.order_status;
					dd.time=gdd.create_time;
					dd.amount=gdd.amount;
					setData(dd);
					
					
				}
				
			}
		};
		getDingDanXiangQinParam gdd=new getDingDanXiangQinParam();
		gdd.order_id=getIntent().getStringExtra("order_id");
		TongYongManager2 tym=new TongYongManager2(this, icb,gdd);
		tym.start();
	}

	// Runnable run=new Runnable() {
	//
	// @Override
	// public void run() {
	// if(ti!=null&&ti.transactionStatus.equals("2"))
	// {
	// return;
	// }
	// initData();
	//
	// }
	// };
	private void setData(DingDan dd) {
		
		jinge.setText(NumberManager.getGeshiHua(dd.amount,2)+"元");
		dingdan.setText(dd.orderNumber);
		
		
//		int ptype=dd.leixing;
//		if (ptype == 1) {
//
//			leixing.setText("购买定期");
//			
//		} else if (ptype == 2) {
//			leixing.setText("购买活期");
//		} else if (ptype == 3) {
//			leixing.setText("充值");
//		} else if (ptype == 4) {
//			leixing.setText("活期赎回");
//		} else if (ptype == 5) {
//			leixing.setText("购买定期");
//			fangshi.setText("活期");
//		}
//		else if (ptype == 6) {
//			leixing.setText("提现");
//		}
		
		
		
		
		if (dd.status.equals("0")) {
			if (dd.leixing == 2) {
				zhuangtai.setText("购买成功");
				tishi.setText("今日24:00显示收益");
			} 
			else if(dd.leixing == 1)
			{
				zhuangtai.setText("购买成功");
				tishi.setText("明日开始计算收益");
			}
			else if (dd.leixing == 3) {
				zhuangtai.setText("充值成功");
				tishi.setVisibility(View.GONE);
			} else if (dd.leixing == 4) {
				zhuangtai.setText("申请已提交");
				
				tishi.setText("今日24点到钱包");
			}
			else if (dd.leixing == 5) {
				zhuangtai.setText("购买成功");
				tishi.setText("明日开始计算收益");
			}
			else if(dd.leixing == 6)
			{
				SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Calendar ca=format.getCalendar();
				int hour =ca.get(Calendar.HOUR_OF_DAY); 
				zhuangtai.setText("申请已提交");
				if(hour<16)
				{
					tishi.setText("工作日当天到账");
				}
				else
				{
					tishi.setText("下一个工作日到账");
				}
			}
			iv.setImageResource(R.drawable.img_jiaoyi_ok);
		} else if (dd.status.equals("4")) {
			zhuangtai.setText("处理中");
			tishi.setText("请稍后在交易记录中查看结果");
			iv.setImageResource(R.drawable.img_jiaoyi_chuli);
			
			if(dd.leixing == 4)
			{   
				
				zhuangtai.setText("申请已提交");
				
					tishi.setText("今日24点到钱包");
				
				iv.setImageResource(R.drawable.img_jiaoyi_ok);
			}
			else if(dd.leixing == 3)
			{
				
				
				
			}
			else if(dd.leixing == 5)
			{
				
				
				
			}
			
			else if(dd.leixing == 6)
			{
				SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Calendar ca=format.getCalendar();
				int hour =ca.get(Calendar.HOUR_OF_DAY); 
				zhuangtai.setText("申请已提交");
				if(hour<16)
				{
					tishi.setText("工作日当天到账");
				}
				else
				{
					tishi.setText("下一个工作日到账");
				}
			}
			
		} else {
			if (dd.leixing == 1 || dd.leixing == 2) {
				zhuangtai.setText("购买失败");
			} else if (dd.leixing == 3) {
				zhuangtai.setText("充值失败");
			}
			if (dd.retMsg != null) {
				tishi.setText(dd.retMsg);
			}
			iv.setImageResource(R.drawable.img_jiaoyi_fail);
		}
        if(dd.leixing==6||dd.leixing==4||dd.leixing==3)
        {
        	RelativeLayout rl=(RelativeLayout) findViewById(R.id.rl2);
        	rl.setVisibility(View.GONE);
        }
		
		
	}

	// TransactionInfo ti;
	// private void initData()
	// {
	//
	// String id=BeikBankApplication.getUserid();
	// TranListParam tl=new TranListParam();
	// tl.startLine="0";
	// tl.endLine="0";
	// tl.memberID=id;
	// tl.tradeType="10";
	// tl.productType="2";
	// tl.transactionStatus="";
	// tl.tradeMode="";
	//
	// new TranListManager(this, icb1,tl).start();
	//
	// }
	// private ICallBack icb1=new ICallBack() {
	//
	// @Override
	// public void back(Object obj) {
	// if(obj!=null)
	// {
	// TranList_Data td=(TranList_Data) obj;
	// List list=td.data;
	// ti=(TransactionInfo) list.get(0);
	// if(ti.transactionStatus.equals("2"))
	// {
	// if(ptype==1||ptype==2)
	// {
	// zhuangtai.setText("购买成功");
	// tishi.setText("今日24:00显示收益");
	// }
	// else if(ptype==3)
	// {
	// zhuangtai.setText("充值成功");
	// tishi.setVisibility(View.GONE);
	// }
	// else if(ptype==4)
	// {
	// zhuangtai.setText("申请已提交");
	// tishi.setText("今日24点到钱包");
	// }
	// iv.setImageResource(R.drawable.img_jiaoyi_ok);
	// }
	// else if(ti.transactionStatus.equals("1"))
	// {
	// zhuangtai.setText("处理中");
	// tishi.setText("请稍后在交易记录中查看结果");
	// iv.setImageResource(R.drawable.img_jiaoyi_chuli);
	// }
	// else
	// {
	// if(ptype==1||ptype==2)
	// {
	// zhuangtai.setText("购买失败");
	// }
	// else if(ptype==3)
	// {
	// zhuangtai.setText("充值失败");
	// }
	// if(ti.retMsg!=null)
	// {
	// tishi.setText(ti.retMsg);
	// }
	// iv.setImageResource(R.drawable.img_jiaoyi_fail);
	// }
	// }
	// }
	// };
	// int ptype;
	
	private void initView() {

		tv_error = (TextView) findViewById(R.id.tv_error);
		ll = (LinearLayout) findViewById(R.id.linear_left);
		title = (TextView) findViewById(R.id.titleTv);
		title.setText("交易详情");
		right = (TextView) findViewById(R.id.right);
		right.setText("完成");
		right.setVisibility(View.VISIBLE);
		
		right.setTextColor(0xff747474);
		right.setOnClickListener(this);
		iv = (ImageView) findViewById(R.id.iv);

		zhuangtai = (TextView) findViewById(R.id.tv1);
		tishi = (TextView) findViewById(R.id.tv_tishi);
		leixing = (TextView) findViewById(R.id.tv3);
		fangshi = (TextView) findViewById(R.id.tv5);
		jinge = (TextView) findViewById(R.id.tv7);
		dingdan = (TextView) findViewById(R.id.tv9);

		ll.setOnClickListener(this);
        
		
		
		ll_xiugai=(LinearLayout) findViewById(R.id.ll_xiugai);
		bu=(Button) findViewById(R.id.bu);
		bu.setOnClickListener(this);
//		CardInfo cardInfo = CardInfoDao.getCardInfo();
//
//		if (cardInfo != null && cardInfo.type != null) {
//			BankList bl = BankListDao.getBankByType(cardInfo.getType());
//			if (bl != null) {
//				String cardNumber = cardInfo.getCardNumber();
//
//			}
//		}

//		 dd = (DingDan) getIntent().getSerializableExtra(
//				TypeUtil.jiaoyi_state);
//
//		// ConfirmPay cp=(ConfirmPay)
//		// getIntent().getSerializableExtra(TypeUtil.jiaoyi_state);
//
//		dingdan.setText(dd.orderNumber);
//		jinge.setText(NumberManager.getGeshiHua(dd.amount, 2) + "元");
//		// String
//		// play=BeikBankApplication.mSharedPref.getSharePrefString(SharePrefConstant.play_select);
//		// ptype=Integer.parseInt(getIntent().getStringExtra(TypeUtil.jiaoyi_leixing));
//		String play = dd.fangshi;
//		int ptype = dd.leixing;
//		if (play.equals("1")) {
//			fangshi.setText("钱包");
//		} else {
//			fangshi.setText("银行卡");
//		}
//		
//		
//		if (ptype == 1) {
//
//			leixing.setText("购买定期");
//			
//		} else if (ptype == 2) {
//			leixing.setText("购买活期");
//		} else if (ptype == 3) {
//			leixing.setText("充值");
//		} else if (ptype == 4) {
//			leixing.setText("活期赎回");
//		} else if (ptype == 5) {
//			leixing.setText("购买定期");
//			fangshi.setText("活期");
//		}
//		else if (ptype == 6) {
//			leixing.setText("提现");
//		}

	//setData(dd);
	}

	@Override
	public void onClick(View v) {
		Intent intent=new Intent(this,HomeActivity4.class);
		switch (v.getId()) {
		case R.id.linear_left:
			finish();
			break;
		case R.id.bu:
			intent.putExtra("indexgo",2);
			startActivity(intent);
			break;		
		case R.id.right:
			
			
//			if(dd.leixing==1)
//			{
//				intent.putExtra("go","go");
//				intent.putExtra("indexgo",1);
//			}
//			else if(dd.leixing==2)
//			{
//				intent.putExtra("go","go");
//				intent.putExtra("indexgo",0);
//			}
			BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.re_home,true);
			startActivity(intent);
			break;
		default:
			break;
		}

	}

}
