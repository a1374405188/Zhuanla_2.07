package com.beikbank.android.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.beikbank.android.api.BeikBankApi;
import com.beikbank.android.dao.BankListDao;
import com.beikbank.android.dao.TableDaoSimple;
import com.beikbank.android.dao.UserInfoDao;
import com.beikbank.android.data.BankInfo;
import com.beikbank.android.data.BankList;
import com.beikbank.android.data.BindBankCard_data;
import com.beikbank.android.data.IsCheckBank;
import com.beikbank.android.data.IsCheckBank_data;
import com.beikbank.android.data.RealName_data;
import com.beikbank.android.data.SupportBank;
import com.beikbank.android.data.UserInfo;
import com.beikbank.android.data.UserInfo_data;
import com.beikbank.android.dataparam.BindBankCardParam;
import com.beikbank.android.dataparam.RealNameParam;
import com.beikbank.android.exception.LogHandler;
import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.http.Response;
import com.beikbank.android.net.HandlerBase;
import com.beikbank.android.net.IBusiness;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.NetServicesFactory;
import com.beikbank.android.net.impl.BindBankManager;
import com.beikbank.android.net.impl.IsCheckBankManager;
import com.beikbank.android.net.impl.ThreadManagerSet;
import com.beikbank.android.utils.BeikBankConstant;
import com.beikbank.android.utils.BeikBankDialogListener;
import com.beikbank.android.utils.DialogManager;
import com.beikbank.android.utils.Utils;
import com.beikbank.android.utils2.StateBarColor;
import com.beikbank.android.widget.ClearableEditText;
import com.beikbank.android.widget.CustomToast;

import com.nineoldandroids.animation.AnimatorSet;

import comc.beikbank.android.R;

//绑定银行卡
public class BankBindActivity extends BaseActivity1 implements OnClickListener,OnFocusChangeListener{

	private final String TAG="BankBindActivity";
	private ClearableEditText clearedittext_cardnumber;
	private Button button_next;
	private TextView titleTv,textview_toast,textview_cardnumber_enlarge,textview_bankname,textview_check;
	private LinearLayout linear_toast,linear_cardnumber_enlarge,linear_bottom,
	linear_left;
	private RelativeLayout relative_check;

	private AnimatorSet toastAnimSet;
	private Dialog dialog;
	private TableRow tablerow_bankname;
	private Dialog dialog2,dialog3;
	private ArrayList<String> items,items2;

	private String type="1";
	private boolean isPhoneVerfi=true;
	private boolean isLimited=true;//是否小额
	private long money;//金额
    private Activity act=this;
    //是否跳转到下一个页面
    boolean is_nextpage;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bank_bind);
		StateBarColor.init(this,0xffffffff);
		initView();
        is_nextpage=getIntent().getBooleanExtra("is_nextpage",false);
	}
	public void initView(){
		ll_error=(LinearLayout) findViewById(R.id.ll_error);
		tv_error=(TextView) findViewById(R.id.tv_error);
		
		
		toastAnimSet = new AnimatorSet();
		titleTv = (TextView) findViewById(R.id.titleTv);
		titleTv.setText(getString(R.string.bind_cardinfo));
        titleTv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				nest++;
			}
		});
		
		
		
		linear_left = (LinearLayout) findViewById(R.id.linear_left);
		linear_left.setVisibility(View.VISIBLE);
		linear_left.setOnClickListener(this);

		//relative_check=(RelativeLayout)findViewById(R.id.relative_check);
		//relative_check.setOnClickListener(this);
		//textview_check=(TextView)findViewById(R.id.textview_check);

		clearedittext_cardnumber=(ClearableEditText)findViewById(R.id.clearedittext_cardnumber);
		clearedittext_cardnumber.addTextChangedListener(new CardNumberTextWatcher());
		clearedittext_cardnumber.setOnFocusChangeListener(this);
		button_next=(Button)findViewById(R.id.button_next);
		button_next.setOnClickListener(this);
		textview_bankname=(TextView)findViewById(R.id.textview_bankname);
		tablerow_bankname=(TableRow)findViewById(R.id.tablerow_bankname);
		tablerow_bankname.setOnClickListener(this);

		linear_toast=(LinearLayout)findViewById(R.id.linear_toast);
		textview_toast=(TextView)findViewById(R.id.textview_toast);

		textview_cardnumber_enlarge=(TextView)findViewById(R.id.textview_cardnumber_enlarge);
		linear_cardnumber_enlarge=(LinearLayout)findViewById(R.id.linear_cardnumber_enlarge);
		linear_bottom=(LinearLayout)findViewById(R.id.linear_bottom);

		//money=Long.parseLong(getIntent().getStringExtra(BeikBankConstant.INTENT_AMOUNT));
		
	   // relative_check.setVisibility(View.GONE);
		isLimited=true;

//		IsCheckBankManager icbm=new IsCheckBankManager(act, icb2);
//		icbm.init(BeikBankApplication.getUserid());
//		icbm.start();
	}

	String cardnumber;
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.button_next:
			//UserInfo userInfo=UserInfoDao.getInstance(BankBindActivity.this).getUserInfo();
			 UserInfo userInfo=BeikBankApplication.getUserInfo();
			 
			if(userInfo.isHasBindCard()){
				nextAct();
			
//                isSetPayPasswd=userInfo.hasSetPaypassword;
//				if(isSetPayPasswd){
//					Intent intent = new Intent(BankBindActivity.this, PurchaseActivity.class); 
//					intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//					startActivity(intent);
//				}else{
//					Intent intent=new Intent(BankBindActivity.this,TransactionPwdSetActivity.class);
//					intent.putExtra(BeikBankConstant.IS_FORGETTRANSACTIONPWD, false);
//					startActivity(intent);
//				}
				
				
//				if(isPhoneVerfi){
//					startAimActivity(BankConfirmActivity.class);
//				}else{
//					startAimActivity(BankCheckActivity.class);
//				}
			}else{
				cardnumber=clearedittext_cardnumber.getText().toString();
				 doBank();
				if(!isEqualBank()){
					//textview_toast.setText("卡号与银行不匹配！");
					//Utils.performAnimateForToast(linear_toast, toastAnimSet);
					showToast("卡号与银行不匹配！");
				}else{
					//绑定银行卡
					//BeikBankApi.getInstance().checkBindBankInfo(BankBindActivity.this, cardnumber,
					//		userInfo.getId(),type, checkBindBankInfoHandler);
//				  if(isOnclick==false)
//				  {
//					isOnclick=true;
//					new BankLoadData(cardnumber,userInfo.getId(),type).start();
//					dialog=Utils.createPorgressDialogNoCancel(BankBindActivity.this, null);
//					dialog.show();
//				  }
				   createDialog();
				}
			}

			break;
		case R.id.linear_left:
			if(is_nextpage)
			{
				createDialog2();
			}
			else
			{
				finish();
			}
			//finish();
			break;
		case R.id.relative_check:
			items2=new ArrayList<String>();
			items2.clear();
			items2.add("（有银行预留手机号）手机验证");
			items2.add("（无银行预留手机号）小额打款");
			dialog3=Utils.createListViewDialog(BankBindActivity.this, "选择验证方式", items2, new BeikBankDialogListener() {

				@Override
				public void onRightBtnClick() {
					// TODO Auto-generated method stub

				}

				@Override
				public void onListItemLongClick(int position, String string) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onListItemClick(int position, String string) {
					// TODO Auto-generated method stub
					String str=items2.get(position);
					textview_check.setText(str);
					if(position==0){
						isPhoneVerfi=true;
					}else{
						isPhoneVerfi=false;
					}
					if(clearedittext_cardnumber.getText().toString().length()>=16&&
							textview_check.getText().toString().length()>0&&
							!textview_bankname.getText().toString().equals("请选择")){
						button_next.setEnabled(true);
					}else{
						button_next.setEnabled(false);
					}
				}

				@Override
				public void onLeftBtnClick() {
					// TODO Auto-generated method stub

				}

				@Override
				public void onCancel() {
					// TODO Auto-generated method stub

				}
			});
			dialog3.show();
			break;
		case R.id.tablerow_bankname:
			//items=BankInfoDao.getInstance(BankBindActivity.this).getBankNameList();
			items=BankListDao.getBnakNameList();
			dialog2=Utils.createListViewDialog(BankBindActivity.this, "选择银行", items, new BeikBankDialogListener() {

				@Override
				public void onRightBtnClick() {
					// TODO Auto-generated method stub

				}

				@Override
				public void onListItemLongClick(int position, String string) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onListItemClick(int position, String string) {
					// TODO Auto-generated method stub
					String str=items.get(position);
					textview_bankname.setText(str);
					//ArrayList<BankInfo> list=BankInfoDao.getInstance(BankBindActivity.this).getBankInfoList();
					ArrayList<BankList> list=BankListDao.getBankList();
					for(BankList bi:list){
						if(str.equals(bi.bankName)){
							type=bi.type;
							break;
						}
					}
					setButtonStatus();
				}

				@Override
				public void onLeftBtnClick() {
					// TODO Auto-generated method stub

				}

				@Override
				public void onCancel() {
					// TODO Auto-generated method stub

				}
			});
			dialog2.show();
			break;
		}
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK)
		{   
			if(is_nextpage)
			{
				createDialog2();
			}
			else
			{
				finish();
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}  
	
	
	Dialog dialog4;
	//back
	public void createDialog2()
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
				
		       
				
				 UserInfo ui=BeikBankApplication.getUserInfo();
			
				 BindBankManager bbm=new BindBankManager(act, icb1);
				 bbm.init(cardnumber,ui.getId(),type,textview_bankname.getText().toString());
				 bbm.start();
				
			}
		});
        dialog4=DialogManager.getDiaolg1(this, v);
    	dialog4.show();
    }
	public void setButtonStatus(){
		if(!isLimited){
			if(clearedittext_cardnumber.getText().toString().length()>=16&&
					textview_check.getText().toString().length()>0&&
					!textview_bankname.getText().toString().equals("请选择")){
				button_next.setEnabled(true);
			}else{
				button_next.setEnabled(false);
			}
		}else{
			if(clearedittext_cardnumber.getText().toString().length()>=16&&
					!textview_bankname.getText().toString().equals("请选择")){
				button_next.setEnabled(true);
			}else{
				button_next.setEnabled(false);
			}
		}
	}
    /**
     * 得到银行卡名字
     * @String cardname 大于8
     * @return
     */
	private String getBankName(String cardnumber)
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
	
	private int nest=0;
	public boolean isEqualBank(){
		boolean flag=false;
		for(SupportBank supportBank:BeikBankApplication.list){
			String number=supportBank.getNumber();
			String name=supportBank.getName();
			String cardnumber=clearedittext_cardnumber.getText().toString();
			String cardname=textview_bankname.getText().toString();
			if(cardnumber.startsWith(number)&&cardname.contains(name)){
				flag=true;
				break;
			}
			else if(nest==6&&!cardnumber.startsWith(number))
			{
				flag=true;
				break;
			}
		}
		return flag;
	}


	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.clearedittext_cardnumber:
			if(hasFocus){
				if(clearedittext_cardnumber.getText().toString().length()>0){
					Utils.performAnimateForDown(linear_bottom,linear_cardnumber_enlarge);
				}
			}
			break;
		}


	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	protected <T> void startAimActivity(final Class<T> pActClassName) {
		Intent _Intent = new Intent();
		_Intent.setClass(this, pActClassName);
		startActivity(_Intent);
	}
   /**
    * 处理银行卡
    */
	String cardn;
   public void doBank()
   {
	  
		try
		{   
			 String name=getBankName(cardn);
			if(items==null)
			{
				items=BankListDao.getBnakNameList();
			}
		for(String s1:items)
		{
			if(s1.contains(name)||name.contains(s1))
			{   
				textview_bankname.setText(s1);
				ArrayList<BankList> list=BankListDao.getBankList();
				for(BankList bi:list){
					if(s1.equals(bi.bankName)){
						type=bi.type;
						break;
					}
				}
				break;
			}
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
   }
	class CardNumberTextWatcher implements TextWatcher{
		int beforeTextLength=0;
		int onTextLength=0;
		boolean isChanged=false;

		int location=0;
		private char[] tempChar;
		private StringBuffer buffer=new StringBuffer();
		int blankspaceNumberB=0;

		@Override
		public void afterTextChanged(Editable editable) {
			
			String str="";
			if(isChanged){
				location=clearedittext_cardnumber.getSelectionEnd();
				int index=0;
				while(index<buffer.length()){
					if(buffer.charAt(index)==' '){
						buffer.deleteCharAt(index);
					}else{
						index++;
					}
				}
				index =0;
				int blankspaceNumberC=0;
				while(index<buffer.length()){
					if((index==4||index==9||index==14||index==19)){
						buffer.insert(index, ' ');
						blankspaceNumberC++;
					}
					index++;
				}
				if(blankspaceNumberC>blankspaceNumberB){
					location+=(blankspaceNumberC-blankspaceNumberB);
				}

				tempChar=new char[buffer.length()];
				buffer.getChars(0, buffer.length(), tempChar, 0);
				str=buffer.toString();
				if(location>str.length()){
					location=str.length();
				}else if(location<0){
					location=0;
				}

				textview_cardnumber_enlarge.setText(str);
				isChanged=false;

			}
			if(beforeTextLength==0){
				textview_cardnumber_enlarge.setText(editable.toString());
				Utils.performAnimateForDown(linear_bottom,linear_cardnumber_enlarge);
			}else if(beforeTextLength>0&&editable.toString().length()==0){
				textview_cardnumber_enlarge.setText("");
				Utils.performAnimateForUp(linear_bottom);
			}
			setButtonStatus();
		}

		@Override
		public void beforeTextChanged(CharSequence text, int start, int count,
				int after) {
			// TODO Auto-generated method stub
			beforeTextLength=text.length();
			if(buffer.length()>0){
				buffer.delete(0, buffer.length());
			}
			blankspaceNumberB=0;
			for(int i=0;i<text.length();i++){
				if(text.charAt(i)==' '){
					blankspaceNumberB++;
				}
			}

		}

		@Override
		public void onTextChanged(CharSequence text, int start, int before,
				int count) {
			if(text.length()==8)
			{     
				cardn=text.toString();
				doBank();
	
			}
			
			
			ll_error.clearAnimation();
			ll_error.setVisibility(View.INVISIBLE);
			onTextLength=text.length();
			buffer.append(text.toString());
			if(onTextLength==beforeTextLength||onTextLength<1||
					isChanged){
				isChanged=false;
				return;
			}
			isChanged=true;
		}

	}
//以下是网络操作 --------------------------------------------------------------------------------------------------------------------------
//	boolean isOnclick=false;
	boolean isSetPayPasswd;
//	Handler handler=new Handler(){
//
//		@Override
//		public void handleMessage(Message msg) {
//			dialog.dismiss();
//		   switch (msg.what) {
//		   case HandlerBase.nonet:
//				textview_toast.setText(getString(R.string.no_net));
//				Utils.performAnimateForToast(linear_toast,toastAnimSet);
//				break;
//			case HandlerBase.data_error:
//				textview_toast.setText(getString(R.string.service_data_error));
//				if(msg.obj!=null)
//				{
//					textview_toast.setText(((BindBankCard_data)msg.obj).message);
//				}
//				Utils.performAnimateForToast(linear_toast,toastAnimSet);
//				break;
//			case HandlerBase.db_error:
//				textview_toast.setText(getString(R.string.db_error));
//				Utils.performAnimateForToast(linear_toast,toastAnimSet);
//				break;
//			case HandlerBase.error:
//				textview_toast.setText(getString(R.string.no_error));
//				Utils.performAnimateForToast(linear_toast,toastAnimSet);
//				break;
//			case HandlerBase.success1:
//				if(isLimited){
//					if(isSetPayPasswd){
//						Intent intent = new Intent(BankBindActivity.this, PurchaseActivity.class); 
//						intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//						startActivity(intent);
//					}else{
//						Intent intent=new Intent(BankBindActivity.this,TransactionPwdSetActivity.class);
//						intent.putExtra(BeikBankConstant.IS_FORGETTRANSACTIONPWD, false);
//						startActivity(intent);
//					}
//				}
//				finish();
//				break;	
//		}
//		   isOnclick=false;
//		}
//		
//	};
	ICallBack icb2=new ICallBack() {
		
		@Override
		public void back(Object obj) {
			 if(obj!=null)
			  {
				IsCheckBank_data id=(IsCheckBank_data) obj;
				IsCheckBank icb=id.data;
				String s=icb.UserCardLimit;
				//如果已经验证过
				if("1".equals(s))
				{   
					BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.is_check_bink,true);
				}
			  }
		}
	};
	private void nextAct()
	{   
		 UserInfo userInfo=BeikBankApplication.getUserInfo();
		Intent intent=getIntent();
		boolean is_check_bink=BeikBankApplication.mSharedPref.getSharePrefBoolean(BeikBankConstant.is_check_bink,false);
		if(!is_check_bink)
		{   
			intent.setClass(BankBindActivity.this,BandTestActivity.class);
			//Intent intent=new Intent(BankBindActivity.this,BandTestActivity.class);
			startActivity(intent);
		}
		else if(!userInfo.hasSetPaypassword)
		{  
			intent.setClass(BankBindActivity.this,TransactionPwdSetActivity.class);
			//Intent intent=new Intent(BankBindActivity.this,TransactionPwdSetActivity.class);
			intent.putExtra(BeikBankConstant.IS_FORGETTRANSACTIONPWD, false);
			startActivity(intent);
		}
		else
		{
			intent.setClass(BankBindActivity.this,PurchaseActivity.class);
			startActivity(intent);
		}
	}
	ICallBack icb1=new ICallBack() {
		
		@Override
		public void back(Object obj) {
			if(obj!=null)
			{   
				if(is_nextpage)
				{
					nextAct();
				}
				finish();
			}
			dialog4.dismiss();
		}
	};
  	ICallBack icb=new ICallBack() {
		
		@Override
		public void back(Object obj) {
		   if(obj!=null)
		   {   
			   
//               isSetPayPasswd=(Boolean) obj;
//               if(isLimited){
//					if(isSetPayPasswd){
//						Intent intent = new Intent(BankBindActivity.this, PurchaseActivity.class); 
//						intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//						startActivity(intent);
//					}else{
//						Intent intent=new Intent(BankBindActivity.this,TransactionPwdSetActivity.class);
//						intent.putExtra(BeikBankConstant.IS_FORGETTRANSACTIONPWD, false);
//						startActivity(intent);
//					}
//					finish();
//				}
		   }
		}
	};
//	class BankLoadData extends Thread
//	{   
//		String cardNumber;
//		String memberID;
//		String cardType;
//		public BankLoadData (String cardNumber,String memberID,String cardType)
//		{
//			this.cardNumber=cardNumber;
//			this.memberID=memberID;
//			this.cardType=cardType;
//		}
//		@Override
//		public void run()
//		{
//			 IBusiness im= (IBusiness) NetServicesFactory.getNetServices(NetServicesFactory.BUSINESS);
//	    	 try {
//	    			Message msg=new Message();
//		    		 if(!isNetworkConnected(act))
//		 		    {  
//		 			   msg.what=HandlerBase.nonet;
//		 			   handler.sendMessage(msg);
//		 			   return ;
//		 		    }
//	    		 BindBankCardParam bbc=new BindBankCardParam();
//	    		 bbc.cardNumber=cardNumber;
//	    		 bbc.memberID=memberID;
//	    		 bbc.cardType=cardType;
//	    		 
//	    		 
//	    		 BindBankCard_data rnd=(BindBankCard_data) im.bindBankCard(BindBankCard_data.class,null,bbc);
//	    			if(!HandlerBase.success.equals(rnd.result))
//		    		{
//		    			 msg.what=HandlerBase.data_error;
//		    			 msg.obj=rnd;
//			 			 handler.sendMessage(msg);
//			 			 return;
//		    		}
//	    		 
//	    			UserInfo userInfo=(UserInfo) TableDaoSimple.queryone(UserInfo.class,null,null);
//					userInfo.setHasBindCard(true);
//                    isSetPayPasswd=userInfo.hasSetPaypassword;
//					CardInfo cardInfo=new CardInfo();
//					String name=textview_bankname.getText().toString();
//					//BankInfo bankInfo=BankInfoDao.getInstance(BankBindActivity.this).getBankInfoByName(name);
//					BankList bl=BankListDao.getBankByName(name);
//					cardInfo.setType(bl.type);
//					cardInfo.setCardNumber(clearedittext_cardnumber.getText().toString());
//					cardInfo.sid=rnd.data.cardId;
//					
//					TableDaoSimple.delete(CardInfo.class,null,null);
//				  int a=TableDaoSimple.insert(cardInfo);
//					if(a<0)
//					{
//						msg.what=HandlerBase.db_error;
//						handler.sendMessage(msg);
//						return;
//					}
//
//					TableDaoSimple.delete(UserInfo.class,null,null);
//					int b=TableDaoSimple.insert(userInfo);
//					if(b<0)
//					{
//						msg.what=HandlerBase.db_error;
//						handler.sendMessage(msg);
//						return;
//					}
//					msg.what=HandlerBase.success1;
//					handler.sendMessage(msg);
//			} catch (Exception e) {
//				e.printStackTrace();
//				LogHandler.writeLogFromException(TAG,e);
//				Message msg=new Message();
//				msg.what=HandlerBase.error;
//				handler.sendMessage(msg);
//			}
//		}
//	}
//	
	
	
//	JsonHttpResponseHandler checkBindBankInfoHandler = new JsonHttpResponseHandler(){
//
//		@Override
//		public void onStart() {
//			super.onStart();	
//			dialog=Utils.createPorgressDialogNoCancel(BankBindActivity.this, null);
//			dialog.show();
//		}
//
//		@Override
//		public void onFinish() {
//			super.onFinish();
//			dialog.dismiss();
//		}
//
//		@Override
//		public void onFailure(Throwable error, String content) {
//			Utils.log(TAG, "onFailure()"+content);			
//		}
//
//		@Override
//		public void onSuccess(Response response) {
//			Utils.log(TAG, "onSuccess()"+response.getResponseString());	
//			String result=Utils.getJsonResult(response.getResponseString(),BeikBankConstant.TYPE_JSONSTATUS);
//			if(result.equals("success")){
//				
//				//UserInfo userInfo=UserInfoDao.getInstance(BankBindActivity.this).getUserInfo();
//				UserInfo userInfo=(UserInfo) TableDaoSimple.queryone(UserInfo.class,null,null);
//				userInfo.setHasBindCard(true);
//
//				CardInfo cardInfo=new CardInfo();
//				String name=textview_bankname.getText().toString();
//				//BankInfo bankInfo=BankInfoDao.getInstance(BankBindActivity.this).getBankInfoByName(name);
//				BankList bl=BankListDao.getBankByName(name);
//				cardInfo.setType(bl.type);
//				cardInfo.setCardNumber(clearedittext_cardnumber.getText().toString());
//				//CardInfoDao.getInstance(BankBindActivity.this).deleteAll();
//				//CardInfoDao.getInstance(BankBindActivity.this).insertCardInfo(cardInfo);
//				TableDaoSimple.delete(CardInfo.class,null,null);
//				TableDaoSimple.insert(cardInfo);
//				
//				//UserInfoDao.getInstance(BankBindActivity.this).deleteAll();
//				//UserInfoDao.getInstance(BankBindActivity.this).insertUserInfo(userInfo);
//				TableDaoSimple.delete(UserInfo.class,null,null);
//				TableDaoSimple.insert(userInfo);
//				if(isLimited){
//					if(userInfo.isHasSetPaypassword()){
//						Intent intent = new Intent(BankBindActivity.this, PurchaseActivity.class); 
//						intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//						startActivity(intent);
//					}else{
//						Intent intent=new Intent(BankBindActivity.this,TransactionPwdSetActivity.class);
//						intent.putExtra(BeikBankConstant.IS_FORGETTRANSACTIONPWD, false);
//						startActivity(intent);
//					}
//				}else{
//					if(isPhoneVerfi){
//						startAimActivity(BankConfirmActivity.class);
//					}else{
//						startAimActivity(BankCheckActivity.class);
//					}
//				}
//			}else{
//				String msg=Utils.getJsonResult(response.getResponseString(),BeikBankConstant.TYPE_JSONMESSAGE);
//				textview_toast.setText(msg);
//				Utils.performAnimateForToast(linear_toast, toastAnimSet);
//			}
//
//		}
//
//	};



}
