package com.beikbank.android.activity;

import com.beikbank.android.data.Poundage;
import com.beikbank.android.data.Poundage_data;
import com.beikbank.android.data.Qianbao1_data;
import com.beikbank.android.data.Qianbao2_data;
import com.beikbank.android.data.UserInfo;
import com.beikbank.android.data.Yuer;
import com.beikbank.android.data.Yuer_data;
import com.beikbank.android.dataparam.QianbaoParam1;
import com.beikbank.android.dataparam.QianbaoParam2;
import com.beikbank.android.dataparam.YuerParam;
import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.impl.HandMoneyManager;
import com.beikbank.android.net.impl.Qianbao1Manager;
import com.beikbank.android.net.impl.Qianbao2Manager;
import com.beikbank.android.net.impl.Qianbao3Manager;
import com.beikbank.android.net.impl.Qianbao4Manager;
import com.beikbank.android.net.impl.TongYongManager;
import com.beikbank.android.utils.BeikBankConstant;
import com.beikbank.android.utils.NumberManager;
import com.beikbank.android.utils2.StateBarColor;
import com.beikbank.android.widget.ClearableEditText;
import coma.beikbank.android.R;



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
 *钱包详情
 */
public class QianbaoActivity7 extends BaseActivity2 implements View.OnClickListener
{   

	/**
	 * 定期到期
	 */
	TextView tv1;

	/**
	 * 活期到期
	 */
	TextView tv2;

	/**
	 * 未使用
	 */
	TextView tv3;

	/**
	 * 冻结中
	 */
	TextView tv4;
	/**
	 * 标题
	 */
	TextView title;
    /**
     * return
     */
    LinearLayout ll;
   
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_qianbao7);
		StateBarColor.init(this,0xffffffff);
		initView();
		addData();
	}
	
	private void addData()
	{   
		YuerParam yp=new YuerParam();
		yp.memberID=BeikBankApplication.getUserid();
		TongYongManager tym=new TongYongManager(this, icb,yp);
		tym.start();
		
	
		
	  //  new HandMoneyManager(this, icb1).start(yp.memberID);
		
	}
//	ICallBack icb1=new ICallBack() {
//		
//		@Override
//		public void back(Object obj) {
//			if(obj!=null)
//			{
//				 Poundage_data pd=(Poundage_data) obj;
//				 Poundage p=pd.data;
//				 TextView tv5=(TextView) QianbaoActivity7.this.findViewById(R.id.tv5);
//				 tv5.setText(p.freeWithdrawCount+"次免费提现机会，每到期一笔多一次");
//			}
//			
//		}
//	};
	ICallBack icb=new ICallBack() {
		
		@Override
		public void back(Object obj) {
			
			if(obj!=null)
			{
				Yuer_data yd=(Yuer_data) obj;
				Yuer ye=yd.data;
				String s1=NumberManager.getString(ye.bondLimit,"1",2);
				String s2=NumberManager.getString(ye.fundLimit,"1",2);
				String s3=NumberManager.getString(ye.rechargeLimit,"1",2);
				String s4=NumberManager.getAddString(ye.frozenAmountPurchase,ye.frozenAmountRedeem,8);
				s4=NumberManager.getAddString(s4,ye.frozenAmountWithdraw,2);
				tv1.setText(s1);
				tv2.setText(s2);
				tv3.setText(s3);
				tv4.setText(s4);
			}
		}
	};
    private  void initView()
    {    
    	
    	 ll=(LinearLayout)findViewById(R.id.linear_left);
         title=(TextView)findViewById(R.id.titleTv);
         title.setText("钱包详情");
         ll.setOnClickListener(this);
         tv1=(TextView)findViewById(R.id.tv1);
         tv2=(TextView)findViewById(R.id.tv2);
         tv3=(TextView)findViewById(R.id.tv3);
         tv4=(TextView)findViewById(R.id.tv4);
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
