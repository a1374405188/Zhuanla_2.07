package com.beikbank.android.activity;

import java.math.BigDecimal;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.beikbank.android.conmon.DisplayManger;
import com.beikbank.android.conmon.FinalIndex;
import com.beikbank.android.conmon.MessageManger;
import com.beikbank.android.dao.BankListDao;
import com.beikbank.android.dao.CardInfoDao;
import com.beikbank.android.dao.TableDaoSimple;
import com.beikbank.android.dao.UserInfoDao;
import com.beikbank.android.data.BankInfo;
import com.beikbank.android.data.BankList;
import com.beikbank.android.data.CardInfo;
import com.beikbank.android.data.FundInfo;
import com.beikbank.android.data.Poundage;
import com.beikbank.android.data.Poundage_data;
import com.beikbank.android.data.ReqPayforP;
import com.beikbank.android.data.ReqPayforP_Data;
import com.beikbank.android.data.UserInfo;
import com.beikbank.android.dataparam.ReqPayforPParam;
import com.beikbank.android.exception.LogHandler;
import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.net.HandlerBase;
import com.beikbank.android.net.IBusiness;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.NetServicesFactory;
import com.beikbank.android.net.impl.HandMoneyManager;
import com.beikbank.android.net.impl.ReqPayforPManager;
import com.beikbank.android.utils.BeikBankConstant;
import com.beikbank.android.utils.DialogManager;
import com.beikbank.android.utils.NumberManager;
import com.beikbank.android.utils.Utils;
import com.beikbank.android.utils2.StateBarColor;
import com.beikbank.android.widget.ClearableEditText;
import com.nineoldandroids.animation.AnimatorSet;
import coma.beikbank.android.R;



//取现
public class RedeemActivity extends BaseActivity1 implements OnClickListener{
	static String TAG="RedeemActivity";
    private Activity act=this;
	private TextView titleTv,textview_toast,textview_credit_card;
	/**
	 * 手续费
	 */
	private TextView tv1;
	/**
	 * 手续费提示
	 */
	private TextView tv2;
	private Button button_next;
	private LinearLayout linear_left,linear_toast;
	private double totalcapital;
	private AnimatorSet toastAnimSet;
	private ClearableEditText clearedittext_redeem_money;
	//手续费提示
	ImageView iv_info;
	  /**
     * 手续费提示文本
     */
	private LinearLayout ll;
	FundInfo fi;
	public static String index="index";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_redeem);
		StateBarColor.init(this,0xffffffff);
		fi=(FundInfo) getIntent().getSerializableExtra(index);
		initView();
		initOn();
		
		//showLoading();
		//new LoadData().start();
		UserInfo ui=BeikBankApplication.getUserInfo();
		if(ui!=null)
		{
			new HandMoneyManager(act, icb).start(ui.id);
		}

	}

	//是否有手续费
	boolean is_poundage=false;
	Poundage p;
	ICallBack icb=new ICallBack() {
		
		@Override
		public void back(Object obj) {
		  if(obj!=null)
		  {  
			  Poundage_data pd=(Poundage_data) obj;
			  p=pd.data;
			 double d= NumberManager.StoD(p.freeChargeCount);
			if(d<=0)
      		{  
				is_poundage=true;
      			tv1.setText(p.poundage+"元");
      			//tv2.setVisibility(View.GONE);
      			iv_info.setVisibility(View.VISIBLE);
      		}
			else
			{
				is_poundage=false;
			}
		  }
		}
	};
//    private UserInfo getUserInfo()
//    {
//    	//UserInfo ui=UserInfoDao.getInstance(this).getUserInfo();
//    	UserInfo ui=(UserInfo) TableDaoSimple.queryone(UserInfo.class,null,null);
//    	return ui;
//    }
	public void initView(){
		toastAnimSet = new AnimatorSet();
		titleTv = (TextView) findViewById(R.id.titleTv);
		titleTv.setText(getString(R.string.redeem));
 
		iv_info=(ImageView) findViewById(R.id.iv_info);
		tv1=(TextView) findViewById(R.id.tv_tv1);
		//tv2=(TextView) findViewById(R.id.tv_tv2);
		linear_left = (LinearLayout) findViewById(R.id.linear_left);
		linear_left.setVisibility(View.VISIBLE);
		linear_left.setOnClickListener(this);
		ll=(LinearLayout) findViewById(R.id.ll_ll1);
		linear_toast=(LinearLayout)findViewById(R.id.linear_toast);
		textview_toast=(TextView)findViewById(R.id.textview_toast);
		textview_credit_card=(TextView)findViewById(R.id.textview_credit_card);
		//CardInfo cardInfo=CardInfoDao.getInstance(RedeemActivity.this).getCardInfo();
		//CardInfo cardInfo=(CardInfo) TableDaoSimple.queryone(CardInfo.class,null,null);
		String s=getIntent().getStringExtra(BeikBankConstant.INTENT_TOTALCAPITAL);
		totalcapital=Double.parseDouble(s);
		
		CardInfo cardInfo=CardInfoDao.getCardInfo();
		if(cardInfo!=null)
		{
			BankList bl=BankListDao.getBankByType(cardInfo.getType());
			if(bl!=null)
			{
				String cardNumber=cardInfo.getCardNumber();
				textview_credit_card.setText(bl.bankName
						+"(尾号"+cardNumber.substring(cardNumber.length()-4, cardNumber.length())+")");

//				clearedittext_redeem_money=(ClearableEditText)findViewById(R.id.clearedittext_redeem_money);
//				setSecondPoint(clearedittext_redeem_money);

//				button_next=(Button)findViewById(R.id.button_next);
//				button_next.setOnClickListener(this);
				
			}
		}
		button_next=(Button)findViewById(R.id.button_next);
		button_next.setOnClickListener(this);
		clearedittext_redeem_money=(ClearableEditText)findViewById(R.id.clearedittext_redeem_money);
		setSecondPoint(clearedittext_redeem_money);
		
		//BankInfo bankInfo=BankInfoDao.getInstance(RedeemActivity.this).getBankInfoByType(cardInfo.getType());
//		BankList bl=BankListDao.getBankByType(cardInfo.getType());
//		
//		String cardNumber=cardInfo.getCardNumber();
//		textview_credit_card.setText(bl.bankName
//				+"(尾号"+cardNumber.substring(cardNumber.length()-4, cardNumber.length())+")");
//
//		clearedittext_redeem_money=(ClearableEditText)findViewById(R.id.clearedittext_redeem_money);
//		setSecondPoint(clearedittext_redeem_money);
//
//		button_next=(Button)findViewById(R.id.button_next);
//		button_next.setOnClickListener(this);
//		totalcapital=getIntent().getDoubleExtra(BeikBankConstant.INTENT_TOTALCAPITAL, 0);

	}
	 Dialog dia=null;
	 private void initOn()
	    {
	    	ll.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					if(!is_poundage)
					{
						return;
					}
					if(dia==null)
					{
						dia=Utils.creadialog1(act, null, null);
					}
					if(!dia.isShowing())
					{
						dia.show();
					}
				}
			});
	    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.linear_left:
			finish();
			break;
		case R.id.button_next:	
			String money=clearedittext_redeem_money.getText().toString();
			if(!Utils.isPointLast(money)){
				//textview_toast.setText("输入金额格式错误");
				//Utils.performAnimateForToast(linear_toast,toastAnimSet);
				HandlerBase.showMsg(act,"输入金额格式错误",0);
			}else if(Double.parseDouble(money)>totalcapital){
				//textview_toast.setText("取现金额不能大于总资产");
				//Utils.performAnimateForToast(linear_toast,toastAnimSet);
				HandlerBase.showMsg(act,"取现金额不能大于总资产",0);
			}else{
			  try {
				  if(p!=null)
				  {
					  if(is_poundage)
					  {
						  afterSubmit(money,p.poundage);
					  }
					  else
					  {
						  afterSubmit(money,"0");
					  }
				  }
			} catch (Exception e) {
				e.printStackTrace();
				LogHandler.writeLogFromException(TAG,e);
				MessageManger.showMeg(act,"请输入纯数字",Toast.LENGTH_SHORT);
			}
				
//				Intent intent=new Intent(RedeemActivity.this,RedeemConfirmActivity.class);
//				intent.putExtra(BeikBankConstant.INTENT_SID, getIntent().getStringExtra(BeikBankConstant.INTENT_SID));
//				intent.putExtra(BeikBankConstant.INTENT_AMOUNT,money);
//				startActivity(intent);
			}

			break;
		}

	}

	protected <T> void startAimActivity(final Class<T> pActClassName) {
		Intent _Intent = new Intent();
		_Intent.setClass(this, pActClassName);
		startActivity(_Intent);
	}

	
	public void setSecondPoint(final EditText editText) {
		editText.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				if (s.toString().contains(".")) {
					if (s.length() - 1 - s.toString().indexOf(".") > 2) {
						s = s.toString().subSequence(0,
								s.toString().indexOf(".") + 3);
						editText.setText(s);
						editText.setSelection(s.length());
					}
				}
				if (s.toString().trim().substring(0).equals(".")) {
					s = "0" + s;
					editText.setText(s);
					editText.setSelection(2);
				}

				if (s.toString().startsWith("0")
						&& s.toString().trim().length() > 1) {
					if (!s.toString().substring(1, 2).equals(".")) {
						editText.setText(s.subSequence(0, 1));
						editText.setSelection(1);
						return;
					}
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				String money=clearedittext_redeem_money.getText().toString();
				if(money.length()>0&&Double.parseDouble(money)>0){
					button_next.setEnabled(true);
				}else{
					button_next.setEnabled(false);
				}
			}

		});

	}

	private Dialog dialog=null;

	/**
	 * 点击取现提交后调用该方法
	 * @param money 取现金额
	 * @param money2 手续费
	 */
    private void afterSubmit(String money,String money2)
    {  
    	final String  temp=money;
    	//double f2;
    	if(money2==null||"".equals(money2))
    	{
    		//f2=0;
    		money2="0";
    	}
//    	double f1=Float.parseFloat(money);
     	
    	String residue=NumberManager.getSubString(money,money2,2);
    	double f3=Double.parseDouble(residue);
    	//BigDecimal bd=new BigDecimal(f3);
    	//String residue=bd.setScale(2,BigDecimal.ROUND_HALF_UP).toString();
    	//String residue=NumberManager.getString(f3,"1",2);
    	//BigDecimal bd2=new BigDecimal(f1);
    	//money=bd2.setScale(2,BigDecimal.ROUND_HALF_UP).toString();
    	money=NumberManager.getString(money, "1",2);
    	
    	//BigDecimal bd3=new BigDecimal(f2);
    	//money2=bd3.setScale(2,BigDecimal.ROUND_HALF_UP).toString();
    	money2=NumberManager.getString(money2, "1",2);
    	
    	//String residue=f3+"";
//        if(residue.endsWith("00"))
//        {
//        	money=(int)f1+"";
//        	money2=(int)f2+"";
//        	residue=(int)f3+"";
//        }

    	View v=null;
    	TextView tv1=null;
    	TextView tv2=null;
    	TextView tv3=null;
    	TextView tv4=null;
    	TextView tv5=null;
    	if(f3>0)
    	{  
//    		   v=LayoutInflater.from(this).inflate(
//       			   R.layout.redeem_dialog1,null);
//    	   tv1=(TextView)v.findViewById(R.id.tv_tv1);
//    	   tv2=(TextView)v.findViewById(R.id.tv_tv2);
//    	   tv3=(TextView)v.findViewById(R.id.tv_tv3);
//    	   tv4=(TextView)v.findViewById(R.id.tv_tv4);
//    	   tv5=(TextView)v.findViewById(R.id.tv_tv5);
//    	   tv5.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				dialog.dismiss();
				Intent intent=new Intent(RedeemActivity.this,RedeemConfirmActivity.class);
				String index=BeikBankApplication.mSharedPref.getSharePrefString(PurchaseActivity.index);
				intent.putExtra(BeikBankConstant.INTENT_SID,index);
				intent.putExtra(this.index,fi);
				intent.putExtra(BeikBankConstant.INTENT_AMOUNT,residue);
				//取现金额
				intent.putExtra("tv1",money);
				//手续费
				intent.putExtra("tv2",money2);
				startActivity(intent);
				return;
//			}
//		});
    	   
    	}
    	else
    	{
    	  
    	   v=LayoutInflater.from(this).inflate(
    			   R.layout.redeem_dialog2,null);
    	   tv1=(TextView)v.findViewById(R.id.tv_tv1);
    	   tv2=(TextView)v.findViewById(R.id.tv_tv2);
    	   tv3=(TextView)v.findViewById(R.id.tv_tv3);
    	   tv4=(TextView)v.findViewById(R.id.tv_tv4);
    	}  
    	   tv1.setText(money+"元");
    	   tv2.setText(money2+"元");
    	   tv3.setText(residue+"元");
    	   tv4.setOnClickListener(new OnClickListener() {
   			
   			@Override
   			public void onClick(View v) {
   				if(dialog!=null&&dialog.isShowing())
   				{
   					dialog.dismiss();
   				}
   				
   			}
   		  });

        dialog=DialogManager.getDiaolg1(this, v);
    	dialog.show();
    }
    /**
     * ---------------------------一下是网络部分处理-------------------------------------------------------------------------
     */
//    /**
//     * 进度条对话框
//     */
//    Dialog  loading;
//    
//    private void showLoading()
//    {
//    	if(loading==null)
//    	{
//    		 loading=Utils.createPorgressDialogNoCancel(act, null);
//    	}
//    	loading.show();
//    }
//	Handler handler=new HandlerBase(this){
//
//		@Override
//		public void handleMessage(Message msg) {
//			loading.dismiss();
//			switch (msg.what) {
//		    //success
//          	case HandlerBase.success1:
//          		if("0".equals(poundage.freeChargeCount))
//          		{
//          			tv1.setText(poundage.poundage+"元");
//          			tv2.setVisibility(View.GONE);
//          		}
//				break;
//          	case HandlerBase.data_error:
//          		Poundage_data d=(Poundage_data) msg.obj;
//          		MessageManger.showMeg(act,d.message,Toast.LENGTH_SHORT);
//          		break;
//          	case HandlerBase.nonet:
//          		MessageManger.showMeg(act,getString(R.string.no_net),Toast.LENGTH_SHORT);
//          		break;
//			}
//			
//		}
//		
//	};
//  class LoadData extends Thread
//  {
//	  @Override
//		public void run() {
//			 Message msg=new Message();
//			try
//			{ 
//			 boolean b= isNetworkConnected(act);
//			 if(!b)
//			 {
//				 msg.what=HandlerBase.nonet;
//				 handler.sendMessage(msg);
//			 }
//			 UserInfo ui=getUserInfo();
//			  IBusiness ibs=(IBusiness)NetServicesFactory.getNetServices(NetServicesFactory.BUSINESS);
//			  Poundage_data pd=(Poundage_data) ibs.getUserPoundage(ui.getId());
//			  if(!HandlerBase.success.equals(pd.result))
//			  {
//				     msg.what=HandlerBase.data_error;
//				     msg.obj=pd;
//					 handler.sendMessage(msg);
//					 return;
//			  }
//			  poundage=pd.data;
//			  if(poundage==null)
//			  {
//				  throw new NullPointerException();
//			  }
//			  msg.what=HandlerBase.success1;
//			  msg.obj=poundage;
//			  handler.sendMessage(msg);
//			}
//			catch(Exception e)
//			{  
//				msg.what=HandlerBase.error;
//				handler.sendMessage(msg);
//				e.printStackTrace();
//				LogHandler.writeLogFromException(TAG,e);
//			}
//		}
//  }
}
