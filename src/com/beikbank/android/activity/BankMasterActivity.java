package com.beikbank.android.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.beikbank.android.dao.BankListDao;
import com.beikbank.android.dao.CardInfoDao;
import com.beikbank.android.dao.TableDaoSimple;
import com.beikbank.android.dao.UserInfoDao;
import com.beikbank.android.data.BankInfo;
import com.beikbank.android.data.BankList;
import com.beikbank.android.data.CardInfo;
import com.beikbank.android.data.IsCheckBank;
import com.beikbank.android.data.IsCheckBank_data;
import com.beikbank.android.data.PhoneGet_Data;
import com.beikbank.android.data.UserInfo;
import com.beikbank.android.data2.getQianBao;
import com.beikbank.android.data2.getQianBao_data;
import com.beikbank.android.dataparam.PhoneGetParam;
import com.beikbank.android.dataparam2.getQianBaoParam;
import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.impl.IsCheckBankManager;
import com.beikbank.android.net.impl.TongYongManager;
import com.beikbank.android.net.impl.TongYongManager2;
import com.beikbank.android.utils.BeikBankConstant;
import com.beikbank.android.utils.DensityUtil;
import com.beikbank.android.utils.DialogManager;
import com.beikbank.android.utils.NetDataManager;
import com.beikbank.android.utils.Utils;
import com.beikbank.android.utils2.StateBarColor;

import comc.beikbank.android.R;


//银行卡管理
public class BankMasterActivity extends BaseActivity implements OnClickListener{

	private TextView titleTv,textview_singlepay,textview_daypay,textview_icnumber;
	private LinearLayout linear_left;
	private RelativeLayout relative_bankmaster;
	//验证银行卡
    TextView tv_tv1;
    Activity act=this;
    ImageView iv_tishi;
  //手机号
    TextView tv_shouji;
  
    TextView tv_xiugai;
    
    LinearLayout ll_xiugai;
    
    /*
     * 0未绑卡1绑卡
     */
    String is_bank="0";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bank_master);
		StateBarColor.init(this,0xffffffff);
		is_bank=getIntent().getStringExtra("is_bank");
		initView();
		
		if("1".equals(is_bank))
		{
			initData();
		}
		else
		{
//			LinearLayout ll_add=(LinearLayout) findViewById(R.id.ll_add_bank);
//			ll_add.setVisibility(View.VISIBLE);
			
			
			 LinearLayout ll=(LinearLayout) findViewById(R.id.ll_add_bank);
				RelativeLayout rl=(RelativeLayout) findViewById(R.id.relative_bankmaster);

			    ll.setVisibility(View.VISIBLE);
			    rl.setVisibility(View.GONE);
		}
	
		
	}
	getQianBao gqb;
	private void initData()
	{
//		PhoneGetParam pgp=new PhoneGetParam();
//		pgp.userId=BeikBankApplication.getUserid();
//		TongYongManager tym=new TongYongManager(this, icb3,pgp);
//		tym.start();
		ICallBack icb_qianbao=new ICallBack() {
			
			@Override
			public void back(Object obj) {
				if(obj!=null)
				{
					getQianBao_data gd=(getQianBao_data) obj;
					gqb=gd.body.card;
					init2();
					setbank();
				}
				
			}
		};
		
		
		getQianBaoParam gqp=new getQianBaoParam();
		gqp.acc_type_id="1";
	    gqp.user_code=BeikBankApplication.getUserCode();
		TongYongManager2 tym3=new TongYongManager2(act, icb_qianbao, gqp);
		tym3.start();
		
	}
	ICallBack icb3=new ICallBack() {
		
		@Override
		public void back(Object obj) {
			if(obj!=null)
			{
				PhoneGet_Data pd=(PhoneGet_Data) obj;
				tv_shouji.setText(Utils.getEncryptedPhonenumber(pd.data.phoneNumber));
			}
			
		}
	};
	
	
	
//	
//	ICallBack icb2=new ICallBack() {
//		@Override
//		public void back(Object obj) {
//		  if(obj!=null)
//		  {
//			IsCheckBank_data id=(IsCheckBank_data) obj;
//			IsCheckBank icb=id.data;
//			String s=icb.UserCardLimit;
//			//如果已经验证过
//			if("1".equals(s))
//			{
//				BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.is_check_bink,true);
//				tv_tv1.setVisibility(View.INVISIBLE);
//			}
//			else
//			{
//				tv_tv1.setVisibility(View.VISIBLE);
//				BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.is_check_bink,false);
//			}
//			reCheckBink();
//		  }
//		}
//	};
	private void reCheckBink()
	{
		boolean is_check_bink=BeikBankApplication.mSharedPref.getSharePrefBoolean(BeikBankConstant.is_check_bink,false);
		if(!is_check_bink)
		{
			tv_tv1.setVisibility(View.VISIBLE);
			tv_tv1.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent intent=new Intent(act,BandTestActivity.class);
					startActivity(intent);
					
				}
			});
		}
		else
		{
			tv_tv1.setVisibility(View.INVISIBLE);
		}
	}
	BankList bl;
	private void init2()
	{
		  
		    LinearLayout ll=(LinearLayout) findViewById(R.id.ll_add_bank);
			RelativeLayout rl=(RelativeLayout) findViewById(R.id.relative_bankmaster);
			// tv_tv1.setVisibility(View.VISIBLE);
			if(gqb==null||"".equals(gqb.bank_name))
			{
				
			    ll.setVisibility(View.VISIBLE);
			    rl.setVisibility(View.GONE);
			}
			else
			{
				ll.setVisibility(View.GONE);
			    rl.setVisibility(View.VISIBLE);
			   
//				IsCheckBankManager icbm=new IsCheckBankManager(act, icb2);
//				String uerid=BeikBankApplication.getUserid();
//				icbm.init(uerid);
//				icbm.start();
			    
			    
			}
			
	}
	public void setbank()
	{
		   
			if(gqb!=null&&!"".equals(gqb.bank_name))
			 {
				//CardInfo cardInfo=CardInfoDao.getInstance(BankMasterActivity.this).getCardInfo();
				//CardInfo cardInfo=(CardInfo) TableDaoSimple.queryone(CardInfo.class,null,null);
				
				
				
				textview_icnumber.setText(Utils.getEncryptedCardNumber(gqb.acc_number));
				//BankInfo bankInfo=BankInfoDao.getInstance(BankMasterActivity.this).getBankInfoByType(cardInfo.getType());
				
				  textview_singlepay.setText(gqb.max_amount+"万元");
				  textview_daypay.setText(gqb.simplet_date_amount+"万元");
				 TextView name=(TextView) findViewById(R.id.name);
				 name.setText(gqb.bank_name);
//				int type=Integer.parseInt("1");
//				switch(type){
//				case 1:
//					relative_bankmaster.setBackgroundResource(R.drawable.bg_bank_gongshang);
//					break;
//				case 2:
//					relative_bankmaster.setBackgroundResource(R.drawable.bg_bank_nongye);
//					break;
//				case 3:
//					relative_bankmaster.setBackgroundResource(R.drawable.bg_bank_jianshe);
//					break;
//				case 4:
//					relative_bankmaster.setBackgroundResource(R.drawable.bg_bank_zhongguo);
//					break;
//				case 5:
//					relative_bankmaster.setBackgroundResource(R.drawable.bg_bank_youzheng);
//					break;
//				case 6:
//					relative_bankmaster.setBackgroundResource(R.drawable.bg_bank_zhaoshang);
//					break;
//				case 7:
//					relative_bankmaster.setBackgroundResource(R.drawable.bg_bank_guangda);
//					break;
//				case 8:
//					relative_bankmaster.setBackgroundResource(R.drawable.bg_bank_guangfa);
//					break;
//				case 9:
//					relative_bankmaster.setBackgroundResource(R.drawable.bg_bank_guangzhou);
//					break;
//				case 10:
//					relative_bankmaster.setBackgroundResource(R.drawable.bg_bank_pingan);
//					break;
//				case 11:
//					relative_bankmaster.setBackgroundResource(R.drawable.bg_bank_minsheng);
//					break;
//				case 14:
//					relative_bankmaster.setBackgroundResource(R.drawable.bg_bank_xinye);
//					break;
//				case 15:
//					relative_bankmaster.setBackgroundResource(R.drawable.bg_bank_zhongxin);
//					break;		
//				}
				if(gqb!=null)
				{
			     	new NetDataManager(icb,gqb.logo_url,this);
					//new NetDataManager(icb,"http://www.beikbank.com/beikbankapp/img/bank/bank_logo/cib.png");
				}
			}else{
				relative_bankmaster.setVisibility(View.GONE);
				ll_xiugai.setVisibility(View.GONE);
			}
	}
	public void initView(){
		
		titleTv = (TextView) findViewById(R.id.titleTv);
		titleTv.setText(getString(R.string.bank_master));
		//tv_tv1=(TextView) findViewById(R.id.title2);
	    //reCheckBink();
		
		linear_left = (LinearLayout) findViewById(R.id.linear_left);
		linear_left.setVisibility(View.VISIBLE);
		linear_left.setOnClickListener(this);
		ll_xiugai=(LinearLayout) findViewById(R.id.ll_xiugai);
		
		
		
		relative_bankmaster=(RelativeLayout)findViewById(R.id.relative_bankmaster);
		LinearLayout ll_add=(LinearLayout) findViewById(R.id.ll_add_bank);
		ll_add.setOnClickListener(this);
		
		DensityUtil du=new DensityUtil(act);
		int dp16=du.dip2px(16);
		int width=BeikBankApplication.getWidth(act);
		width-=2*dp16;
		int height=(int) (((double)width)/686*424);
		LayoutParams lp=new LayoutParams(width,00);
		lp.leftMargin=dp16;
		lp.rightMargin=dp16;
		lp.topMargin=dp16;
		lp.bottomMargin=dp16;
		//relative_bankmaster.setLayoutParams(lp);
		iv_tishi=(ImageView) findViewById(R.id.iv_tishi);
		iv_tishi.setOnClickListener(this);
		
		tv_shouji=(TextView) findViewById(R.id.tv_shouji);
		tv_xiugai=(TextView) findViewById(R.id.tv_xiugai);
		tv_xiugai.setOnClickListener(this);
		
		
		textview_singlepay=(TextView)findViewById(R.id.textview_singlepay);
		textview_daypay=(TextView)findViewById(R.id.textview_daypay);
		textview_icnumber=(TextView)findViewById(R.id.textview_icnumber);
		
		//UserInfo userInfo=UserInfoDao.getInstance(BankMasterActivity.this).getUserInfo();
		
		
		TextView tv_zhichi=(TextView)findViewById(R.id.tv_zhichi);
		tv_zhichi.setOnClickListener(this);

	}

    ICallBack icb=new ICallBack() {
		
		@Override
		public void back(Object obj) {
			if(obj!=null)
			{
			Drawable da=new BitmapDrawable((Bitmap)obj);
			relative_bankmaster.setBackgroundDrawable(da);
			
			
			}
		}
	};
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		//reCheckBink();
		
	}
	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		if("1".equals(is_bank))
		{
			
		
		initData();
		initView();
		init2();
		}
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent;
		switch(v.getId()){
		case R.id.linear_left:			
			finish();
			break;
		case R.id.iv_tishi:			
			showDialog();
			break;
		case R.id.tv_zhichi:			
			intent=new Intent(this,BankSupportActivity.class);
			act.startActivity(intent);
			break;
		case R.id.tv_xiugai:			
			intent=new Intent(this,UpdatePhoneActivity.class);
			act.startActivity(intent);
			
			break;
		case R.id.ll_add_bank:			
			intent=new Intent(act,BankBindActivity2.class);
			act.startActivity(intent);
			break;
		}
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}


	/**
	  * 收益对话框
	  */
	 Dialog dialog1;
	 /**
	  * 显示提示对话框
	  */
	 private void showDialog()
	 {   
		 
		
		 
		 LinearLayout ll=new LinearLayout(act);
		   View v=LayoutInflater.from(act).inflate(
	  			   R.layout.redeem_dialog6,ll,false);
		   
	    	//标题
	    	TextView tv1=(TextView) v.findViewById(R.id.tv_tv1);
	    	tv1.setVisibility(View.GONE);
	    	//内容
	    	TextView tv2=(TextView) v.findViewById(R.id.tv_tv2);
	    	
	    	//tv1.setText(act.getString(R.string.page6));
	    	//tv2.setText(act.getString(R.string.page7));
	    	
	    	tv2.setText("1.银行预留手机号是办理银行卡时在银行登记的手机号，若在银行变更过该手机号，需要在赚啦理财平台同步进行修改才能正常交易。");
	    	
	    	TextView tv3=(TextView) v.findViewById(R.id.tv_tv3);
	    	tv3.setText("2.银行预留手机号区别于注册手机号，修改预留手机号不会对注册手机号造成影响。");
	    	
	    	
	    	
	    	TextView tv5=(TextView) v.findViewById(R.id.tv_tv5);
	    	tv5.setText(act.getString(R.string.page8));
	    	tv5.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					dialog1.dismiss();
				}
			});
	    	//BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.is_finrst_bank,true);
	        dialog1=DialogManager.getDiaolg1(act, v);
	    	dialog1.show();
	 }

}
