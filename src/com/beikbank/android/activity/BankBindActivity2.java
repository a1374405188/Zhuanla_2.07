package com.beikbank.android.activity;

import java.text.NumberFormat;
import java.util.ArrayList;

import u.aly.co;

import com.beikbank.android.activity.BandTestActivity.MyCount;
import com.beikbank.android.activity.BankBindActivity.CardNumberTextWatcher;
import com.beikbank.android.dao.CardInfoDao;
import com.beikbank.android.dao.TableDaoSimple;
import com.beikbank.android.dao.UserInfoDao;
import com.beikbank.android.data.CardInfo;
import com.beikbank.android.data.CheckBankAndPhone_data;
import com.beikbank.android.data.SupportBank;
import com.beikbank.android.data.UserInfo;
import com.beikbank.android.data2.BindBankQinQiu;
import com.beikbank.android.data2.BindBankQinQiu_data;
import com.beikbank.android.data2.BindBankQueReng_data;
import com.beikbank.android.dataparam.CheckBankAndPhoneParam;
import com.beikbank.android.dataparam.CheckBankAndPhoneRParam;
import com.beikbank.android.dataparam2.BindBankQinQiuParam;
import com.beikbank.android.dataparam2.BindBankQueQengParam;
import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.impl.BindBankManager;
import com.beikbank.android.net.impl.CheckBankAndPhoneManager;
import com.beikbank.android.net.impl.CheckBankAndPhoneRManager;
import com.beikbank.android.net.impl.TongYongManager2;
import com.beikbank.android.utils.AdvancedCountdownTimer;
import com.beikbank.android.utils.BeikBankConstant;
import com.beikbank.android.utils.DataCheck;
import com.beikbank.android.utils.DialogManager;
import com.beikbank.android.utils.NumberManager;
import com.beikbank.android.utils.Utils;
import com.beikbank.android.utils2.StateBarColor;
import com.beikbank.android.widget.ClearableEditText;

import comc.beikbank.android.R;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;

/**
 * @author yuguohe
 * <p>
 * email:1374405188@qq.com
 * </p>
 *2015-4-14
 * 绑卡并验证预留手机号
 */
public class BankBindActivity2 extends BaseActivity1 implements OnClickListener,OnFocusChangeListener
{
	private ClearableEditText clearedittext_cardnumber;
	private Button button_next;
	private TextView titleTv,textview_cardnumber_enlarge,textview_bankname,textview_check;
	private LinearLayout linear_cardnumber_enlarge,linear_bottom,linear_left;
	private Dialog dialog2,dialog3;
	private Activity act=this;
	private final int TOTALTIME=60*1000;
	private final int COUNTDOWNINTERVAL=1000;//间隔1秒
	/**
	 * 得到验证码
	 */
	private TextView tv1;
	/**
	 * 到数时间
	 */
	private TextView tv2;
	/**
	 * 手机号
	 */
	private ClearableEditText ed_ed1;
	/**
	 * 验证码
	 */
	private ClearableEditText ed_ed2;
	
	/**
	 * 重新实名认证
	 */
	private TextView right;
	//是否跳转到下一个页面
    boolean is_nextpage;
    private MyCount timer;
    private String codeid;
    /**
     * 是否从实名认证跳过来的
     */
    boolean is_bank;
    
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bank_bind2);
		StateBarColor.init(this,0xffffffff);
		 is_nextpage=getIntent().getBooleanExtra("is_nextpage",false);
		 is_bank=getIntent().getBooleanExtra("is_bank",false);
		 initView();
       
	}
	private void initView()
	{
		ll_error=(LinearLayout) findViewById(R.id.ll_error);
		tv_error=(TextView) findViewById(R.id.tv_error);
		titleTv = (TextView) findViewById(R.id.titleTv);
		titleTv.setText(getString(R.string.bind_cardinfo));
		
		linear_left = (LinearLayout) findViewById(R.id.linear_left);
		linear_left.setVisibility(View.VISIBLE);
		linear_left.setOnClickListener(this);
		
		
		
		
		right=(TextView) findViewById(R.id.right);
		right.setText("支持银行卡");
		right.setOnClickListener(this);
//		if(!is_bank)
//		{
//			right.setVisibility(View.VISIBLE);
//		}
//		else
//		{
//			right.setVisibility(View.GONE);
//		}
		
		clearedittext_cardnumber=(ClearableEditText)findViewById(R.id.clearedittext_cardnumber);
		clearedittext_cardnumber.addTextChangedListener(new CardNumberTextWatcher());
		clearedittext_cardnumber.setOnFocusChangeListener(this);
		button_next=(Button)findViewById(R.id.button_next);
		button_next.setOnClickListener(this);
		//textview_bankname=(TextView)findViewById(R.id.textview_bankname);
	

		textview_cardnumber_enlarge=(TextView)findViewById(R.id.textview_cardnumber_enlarge);
		linear_cardnumber_enlarge=(LinearLayout)findViewById(R.id.linear_cardnumber_enlarge);
		linear_bottom=(LinearLayout)findViewById(R.id.linear_bottom);
        
		tv1=(TextView) findViewById(R.id.tv1);
		tv2=(TextView) findViewById(R.id.tv2);
		
		tv1.setOnClickListener(this);
		
		ed_ed1=(ClearableEditText)findViewById(R.id.ed_ed1);
		ed_ed2=(ClearableEditText)findViewById(R.id.ed_ed2);
		
		ed_ed1.addTextChangedListener(new CardNumberTextWatcher());
		ed_ed2.addTextChangedListener(new CardNumberTextWatcher());
		
		
		
		tv1.setTextColor(0xff747474);
		tv1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				
			}
		});
	}
	private ArrayList<String> items;
	/**
	 * 根据卡号得到卡名字
	 * @param card
	 * @return
	 */
	private String  getBankName(String card)
	{
		String name="";
		name=getBankName2(card);
		return name;
	}
	 /**
     * 得到银行卡名字
     * @String cardname 大于8
     * @return
     */
	private String getBankName2(String cardnumber)
	{
		String n=null;
		for(SupportBank supportBank:BeikBankApplication.list)
		{
			String number=supportBank.getNumber();
			String name=supportBank.getName();
			if(cardnumber.startsWith(number))
			{
				 return name;
			}
		}
		return n;
	}
	
	@Override
	protected void onResume() 
	{
		super.onResume();
		String count=BeikBankApplication.mSharedPref.getSharePrefString(BeikBankConstant.renalName);
		double a=NumberManager.StoD(count);
//		is_bank=getIntent().getBooleanExtra("is_bank",false);
//		if(a>0&&!is_bank)
//		{
//			right.setVisibility(View.VISIBLE);
//			Log.d("right", "true");
//		}
//		else
//		{
//			right.setVisibility(View.GONE);
//			Log.d("right", "false");
//		}
	}

	class CardNumberTextWatcher implements TextWatcher{
		

		@Override
		public void afterTextChanged(Editable editable) {
			
			

			
		}
		@Override
		public void beforeTextChanged(CharSequence text, int start, int count,
				int after) {
		

		}

		@Override
		public void onTextChanged(CharSequence text, int start, int before,
				int count) {
			
			if(ed_ed1.getText().toString().length()==11&&clearedittext_cardnumber.toString().length()>=6)
			{
				tv1.setTextColor(0xff0976aa);
				tv1.setOnClickListener((BankBindActivity2)act);
				if(ed_ed2.getText().toString().length()>=4&&is_fasong)
				{
					button_next.setEnabled(true);
				}
				else
				{
					button_next.setEnabled(false);
				}
			}
			else
			{
				tv1.setTextColor(0xff747474);
				tv1.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						
						
					}
				});
			}
//			if(text.length()==10)
//			{     
//				String name=getBankName(text.toString());
//				if(!"".equals(name))
//				{
//					textview_bankname.setText(name);
//					cardname=name;
//				}
//			}
//			if(text.length()==0)
//			{
//				textview_bankname.setText("");
//			}
			
			ll_error.clearAnimation();
			ll_error.setVisibility(View.INVISIBLE);
		}
	}
//		public void setButtonStatus()
//		{
//			
//				if(clearedittext_cardnumber.getText().toString().length()>=16&&
//						!textview_bankname.getText().toString().equals("请选择")){
//					button_next.setEnabled(true);
//				}else{
//					button_next.setEnabled(false);
//				}
//		}
		//实现计时功能的类  
				class MyCount extends AdvancedCountdownTimer {  

					public MyCount(long millisInFuture, long countDownInterval) {  
						super(millisInFuture, countDownInterval);  
						tv1.setVisibility(View.GONE);
						tv2.setVisibility(View.VISIBLE);
						ForegroundColorSpan fcs = new ForegroundColorSpan(getResources().getColor(R.color.red1));
						SpannableString ssb = new SpannableString(String.valueOf(TOTALTIME/COUNTDOWNINTERVAL));
						ssb.setSpan(fcs, 0, ssb.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);			
						tv2.setText(ssb);
						tv2.append(getString(R.string.identify_code_reload));
					}  

					@Override  
					public void onFinish() {
					
						tv2.setVisibility(View.GONE);
						tv1.setVisibility(View.VISIBLE);
						tv1.setText(getString(R.string.identify_code_reloading));
						timer=null;
					}     
					//更新剩余时间  
					@Override  
					public void onTick(long millisUntilFinished, int percent) { 
						long time = (millisUntilFinished / 1000);  
						ForegroundColorSpan fcs = new ForegroundColorSpan(getResources().getColor(R.color.red1));
						SpannableString ssb = new SpannableString(String.valueOf(time));
						ssb.setSpan(fcs, 0, ssb.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);			
						tv2.setText(ssb);
						tv2.append(getString(R.string.identify_code_reload));
					}  

				}
	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		
		
	}
	//卡号
    private  String cardnum;
    //卡名字
    private  String cardname;
    //
    private  String type;
    private  String id;
	@Override
	public void onClick(View v) {
		int id=v.getId();
		if(id==R.id.tv1)
		{  
			String card=clearedittext_cardnumber.getText().toString();
			cardnum=card;
			String phone=ed_ed1.getText().toString();
			if(!DataCheck.checkPhone(phone))
			{
				showToast("请输入正确的手机格式");
				return;
			}
			if(card!=null&&!"".equals(card)&&phone!=null&&!phone.equals(""))
			{
			
//			 CheckBankAndPhoneParam cbp=new CheckBankAndPhoneParam();
//			 cbp.cardNumber=card;
//			 cbp.memberID=BeikBankApplication.getUserid();
//			 cbp.phonenumber=phone;
//			 CheckBankAndPhoneManager cm=new CheckBankAndPhoneManager(act, icb, cbp);
//			 cm.start();
				BindBankQinQiuParam pp=new BindBankQinQiuParam();
				pp.acc_number=card;
				pp.reserve_phone_number=phone;
				pp.user_code=BeikBankApplication.getUserCode();
				pp.id_number=BeikBankApplication.getSharePref(BeikBankConstant.id_number);
				TongYongManager2 tym2=new TongYongManager2(act, icb,pp);
		    	tym2.start();
		    	
		    	
			}
		}
		else if(id==R.id.button_next)
		{
			createDialog();
		}
		else if(id==R.id.linear_left)
		{       
			finish();
			return;
			   
		}
		else if(id==R.id.right)
		{
//			Intent intent=new Intent(act,RealnameActivity.class);
//			intent.putExtra(BeikBankConstant.INTENT_PURCHASE, true);
//			intent.putExtra("is_nextpage", true);
//			intent.putExtra("is_bank", true);
//			startActivity(intent);
//			finish();
			Intent intent=new Intent(this,BankSupportActivity.class);
			act.startActivity(intent);
		}
	}
	Dialog dialog4;
	//next
    public void createDialog()
    {
    	View v=LayoutInflater.from(this).inflate(
  			   R.layout.redeem_dialog3,null);
    	//cacle
    	TextView tv4=(TextView) v.findViewById(R.id.tv_tv4);
    	tv4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dialog4.dismiss();
			}
		});
    	TextView tv5=(TextView) v.findViewById(R.id.tv_tv5);
    	tv5.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
			   dialog4.dismiss();	
		       yanzhen();
				
			}
		});
        dialog4=DialogManager.getDiaolg1(this, v);
    	dialog4.show();
    }
	private void next()
	{       
		    Intent intent=getIntent();
		 
//		    userInfo.hasBindCard=true;
//		    
//		    TableDaoSimple.delete(UserInfo.class,null,null);
//            UserInfoDao.setUserInfo(userInfo);
//            
//            TableDaoSimple.delete(CardInfo.class,null,null);
//            CardInfo ci=new CardInfo();
//            ci.cardNumber=cardnum;
//            ci.bankName=cardname;
//            ci.sid="1";
//            ci.type=type;
//            CardInfoDao.setCardInfo(ci);
		    boolean is_nextpage=intent.getBooleanExtra("is_nextpage",false);
			if(!is_nextpage)
			{  
				finish();
				return;
			}
		 
		  
				intent.setClass(act,HomeActivity4.class);
				startActivity(intent);
				finish();
			
	}
	BindBankQinQiu b;
	boolean is_fasong=false;
   ICallBack icb=new ICallBack() {
	
	@Override
	public void back(Object obj) {
		
		if(obj!=null)
		{   
//			CheckBankAndPhone_data cd=(CheckBankAndPhone_data) obj;
//			codeid=cd.data.verificodeId;
//			type=cd.data.bankType;
			timer = new MyCount(TOTALTIME, COUNTDOWNINTERVAL);
			timer.start();
//			button_next.setEnabled(true);
			
			BindBankQinQiu_data bb=(BindBankQinQiu_data) obj;
			b=bb.body;
			
			is_fasong=true;
		}
		else
		{
			is_fasong=false;
		}
	}
};
 

//back
private void createDialog2()
{     

	  LinearLayout ll=new LinearLayout(this);
	   View v=LayoutInflater.from(this).inflate(
  			   R.layout.redeem_dialog4,ll,false);
	   TextView tv1=(TextView) v.findViewById(R.id.tv_tv1);
	   TextView tv2=(TextView) v.findViewById(R.id.tv_tv2);
	   
    	//cacle
    	TextView tv4=(TextView) v.findViewById(R.id.tv_tv4);
    	tv4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dialog4.dismiss();
			}
		});
    	TextView tv5=(TextView) v.findViewById(R.id.tv_tv5);
    	tv5.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
    	//BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.is_finrst_bank,true);
        dialog4=DialogManager.getDiaolg1(this, v);
    	dialog4.show();
}
/**
 * 验证短信
 */
private void yanzhen()
{   
//	if(codeid!=null&&!"".equals(codeid))
//	{
//		CheckBankAndPhoneRParam cr=new CheckBankAndPhoneRParam();
//		cr.memberID=BeikBankApplication.getUserid();
//		cr.verifiId=codeid;
//		cr.verifiCode=ed_ed2.getText().toString();
//		CheckBankAndPhoneRManager cm=new CheckBankAndPhoneRManager(act, icb1, cr);
//		cm.start();
//	}
	BindBankQueQengParam bq=new BindBankQueQengParam();
	bq.application_number=b.application_number;
	bq.sms_verify=ed_ed2.getText().toString();
	bq.user_code=BeikBankApplication.getUserCode();
	TongYongManager2 tym2=new TongYongManager2(act, icb1,bq);
	tym2.start();
	
	
}
ICallBack icb1=new ICallBack() {
	
	@Override
	public void back(Object obj) {
		if(obj!=null)
		{   
			BindBankQueReng_data bqr=(BindBankQueReng_data) obj;
			if("0000".equals(bqr.header.re_code))
			{
				next();
			}
			
		}
	}
};
}
	
