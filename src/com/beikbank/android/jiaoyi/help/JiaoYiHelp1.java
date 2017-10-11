package com.beikbank.android.jiaoyi.help;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.widget.Toast;

import com.beikbank.android.activity.DingqiGouMaiState;
import com.beikbank.android.activity.PurchaseConfirmActivity;
import com.beikbank.android.activity.PurchaseSumbitInfo;
import com.beikbank.android.activity.help.TypeUtil;
import com.beikbank.android.conmon.FinalIndex;
import com.beikbank.android.data.ConfirmPay;
import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.utils.BeikBankConstant;
import com.beikbank.android.widget.CustomToast;

/**
 * 
 * @author Administrator
 *交易完成后跳转到订单帮助类
 */
public class JiaoYiHelp1 {
	private Activity act;
	public JiaoYiHelp1(Activity act)
	{
		this.act=act;
	}
	public void startTast()
	{   
		
		ConfirmPay cp=(ConfirmPay) act.getIntent().getSerializableExtra(TypeUtil.jiaoyi_state);
		final int type=act.getIntent().getIntExtra(TypeUtil.jiaoyi_type,-1);
		Runnable rn=new Runnable() 
		{
			
			@Override
			public void run() {
				BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.re_home,true);
				Intent intent=act.getIntent();
				if(type==TypeUtil.jiaoyi_huoqi)
				{
					intent.setClass(act,PurchaseSumbitInfo.class);
				}
				else
				{
					intent.setClass(act,DingqiGouMaiState.class);
				}
				
		    	//intent.putExtra(PurchaseSumbitInfo.index,cp);
		    	//intent.putExtra(PurchaseSumbitInfo.index1,cp.amount);
		    	//intent.putExtra(PurchaseSumbitInfo.index2,fi.name);
				act.startActivity(intent);
				act.finish();
			}
		};
		String s="购买成功";
		if(cp.status.equals(FinalIndex.type1))
		{
			s="申请已提交";
		}
		else if(cp.status.equals(FinalIndex.type2))
		{
			s="购买成功";
		}
		else
		{
			s="购买失败";
		}
		CustomToast.makeText(act,s,Toast.LENGTH_SHORT).show();
	    Handler handler=new Handler();
	    handler.postDelayed(rn,1000);
	}
}
