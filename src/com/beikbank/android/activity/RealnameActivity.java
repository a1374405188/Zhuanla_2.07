package com.beikbank.android.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.beikbank.android.activity.help.LiuChenManager;
import com.beikbank.android.api.BeikBankApi;
import com.beikbank.android.dao.TableDaoSimple;
import com.beikbank.android.dao.UserInfoDao;
import com.beikbank.android.data.RealName;
import com.beikbank.android.data.RealName_data;
import com.beikbank.android.data.UserInfo;
import com.beikbank.android.data2.NameRengzhen_data;
import com.beikbank.android.dataparam2.NameRengzhenParam;
import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.http.Response;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.impl.BindBankManager;
import com.beikbank.android.net.impl.RealNameManager;
import com.beikbank.android.net.impl.TongYongManager2;
import com.beikbank.android.utils.BeikBankConstant;
import com.beikbank.android.utils.DialogManager;
import com.beikbank.android.utils.IdcardValidator;
import com.beikbank.android.utils.Utils;
import com.beikbank.android.utils2.StateBarColor;
import com.beikbank.android.widget.ClearableEditText;
import com.nineoldandroids.animation.AnimatorSet;

import comc.beikbank.android.R;

//实名认证
public class RealnameActivity extends BaseActivity1 implements OnClickListener,OnFocusChangeListener{

	private final String TAG="RealNameActivity";
	private ClearableEditText clearedittext_realname,clearedittext_icnumber;
	private Button button_next;
	private TextView titleTv,textview_toast,textview_phone_enlarge;
	private LinearLayout linear_toast,linear_phone_enlarge,linear_bottom;

	private AnimatorSet toastAnimSet;
	private Dialog dialog;
	private boolean isPurchaseActivity;
	private String money;
	private LinearLayout linear_left;
	/**
	 * 剩余实名认证次数
	 */
	private TextView tv6;
    Activity act=this;
    //是否跳转到下一个页面
    boolean is_nextpage;
    /**
     * 是否从绑卡页面跳过来
     */
    boolean is_bank;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_realname);
		StateBarColor.init(this,0xffffffff);
		is_nextpage=getIntent().getBooleanExtra("is_nextpage",false);
		is_bank=getIntent().getBooleanExtra("is_bank",false);
		initView();
		
	}
	public void initView(){
		ll_error=(LinearLayout) findViewById(R.id.ll_error);
		tv_error=(TextView) findViewById(R.id.tv_error);
		
		tv6=(TextView) findViewById(R.id.tv6);
		String count=BeikBankApplication.mSharedPref.getSharePrefString(BeikBankConstant.renalName);
		if(count.equals(""))
		{
			tv6.setText("0次");
		}
		else
		{
			tv6.setText(count+"次");
		}
		LinearLayout ll= (LinearLayout) findViewById(R.id.ll1);
		if(is_bank)
		{
			ll.setVisibility(View.VISIBLE);
		}
		
		toastAnimSet = new AnimatorSet();
		titleTv = (TextView) findViewById(R.id.titleTv);
		titleTv.setText(getString(R.string.realname_confirm));

		linear_left = (LinearLayout) findViewById(R.id.linear_left);
		linear_left.setVisibility(View.VISIBLE);
		linear_left.setOnClickListener(this);

		clearedittext_realname=(ClearableEditText)findViewById(R.id.clearedittext_realname);	
		clearedittext_icnumber=(ClearableEditText)findViewById(R.id.clearedittext_icnumber);
		clearedittext_realname.setOnFocusChangeListener(this);
		clearedittext_icnumber.setOnFocusChangeListener(this);
		clearedittext_realname.addTextChangedListener(new TextWatcherListener());
		clearedittext_icnumber.addTextChangedListener(new IcnumberTextWatcher());
		button_next=(Button)findViewById(R.id.button_next);
		button_next.setOnClickListener(this);

		linear_toast=(LinearLayout)findViewById(R.id.linear_toast);
		textview_toast=(TextView)findViewById(R.id.textview_toast);

		textview_phone_enlarge=(TextView)findViewById(R.id.textview_phone_enlarge);
		linear_phone_enlarge=(LinearLayout)findViewById(R.id.linear_phone_enlarge);
		linear_bottom=(LinearLayout)findViewById(R.id.linear_bottom);

		money=getIntent().getStringExtra(BeikBankConstant.INTENT_AMOUNT);
		//点击购买时
		isPurchaseActivity=getIntent().getBooleanExtra(BeikBankConstant.INTENT_PURCHASE, false);
	}

	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.clearedittext_realname:
			if(hasFocus){
				if(clearedittext_icnumber.getText().toString().length()>0){
					Utils.performAnimateForUp(linear_bottom);
				}
			}
			break;
		case R.id.clearedittext_icnumber:
			if(hasFocus){
				if(clearedittext_icnumber.getText().toString().length()>0){
					Utils.performAnimateForDown(linear_bottom,linear_phone_enlarge);
				}
			}
			break;


		}


	}
	/**
	 * 实名认证回调
	 */
    private ICallBack icb3=new ICallBack() {
		
		@Override
		public void back(Object obj) {
			button_next.setEnabled(true);
			if(obj!=null)
			{   
				NameRengzhen_data nd=(NameRengzhen_data) obj;
				String s1=nd.body.user_code;
				BeikBankApplication.setSharePref(BeikBankConstant.user_code,s1);
				
				if(!is_nextpage)
				{  
				   finish();
				   return ;
				}
				
				
				
			}
			
		}
	};

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.button_next:
			IdcardValidator iv = new IdcardValidator();
			String realName=clearedittext_realname.getText().toString();
			String idNumber=clearedittext_icnumber.getText().toString();
			if(!Utils.isRealname(realName)){
				//textview_toast.setText("中文姓名格式不正确！");
				//Utils.performAnimateForToast(linear_toast, toastAnimSet);
				showToast("中文姓名格式不正确！");
			}else if(!iv.isValidatedAllIdcard(idNumber)){
				//textview_toast.setText("身份证号码格式不正确！");
				//Utils.performAnimateForToast(linear_toast, toastAnimSet);
				showToast("身份证号码格式不正确！");
			}else{

				//UserInfo userInfo=UserInfoDao.getInstance(RealnameActivity.this).getUserInfo();

//				  UserInfo userInfo=BeikBankApplication.getUserInfo();
//				  //BeikBankApi.getInstance().handleAuthRealnameInfo(RealnameActivity.this, realName, idNumber,
//					//	userInfo.getId(), handleAuthRealnameInfoHandler);
//				  if(userInfo!=null)
//				  {   
//					  button_next.setEnabled(false);
//					  new RealNameManager(act,userInfo.getId(), realName,idNumber, icb).start();
//				  }
	           
				   button_next.setEnabled(false);
				    NameRengzhenParam pp=new NameRengzhenParam();
					pp.birth_day="";
					pp.home_address="";
					pp.id_number=idNumber;
					pp.id_type="0";
					
					pp.marry_status="";
					pp.phone_id="";
					pp.real_name=realName;
					pp.sex="";
					pp.phone_number=BeikBankApplication.getPhoneNumber();
			    	TongYongManager2 tym2=new TongYongManager2(act, icb3,pp);
			    	tym2.start();
			    	BeikBankApplication.setSharePref(BeikBankConstant.id_number,idNumber);
				  
				  
			}
			break;
		case R.id.linear_left:
			finish();
//			if(is_nextpage)
//			{
//				createDialog();
//			}
//			else
//			{
//				finish();
//			}
			break;
		}
	}
//	@Override
//	public boolean onKeyDown(int keyCode, KeyEvent event) {
//		if(keyCode == KeyEvent.KEYCODE_BACK)
//		{   
//			if(is_nextpage)
//			{
//				createDialog();
//			}
//			else
//			{
//				finish();
//			}
//			return false;
//		}
//		return super.onKeyDown(keyCode, event);
//	}  
	
	
	Dialog dialog1;
	//next
    public void createDialog()
    {   
    	LinearLayout ll=new LinearLayout(this);
    	View v=LayoutInflater.from(this).inflate(
  			   R.layout.redeem_dialog4,ll,false);
    	//cacle
    	TextView tv4=(TextView) v.findViewById(R.id.tv_tv4);
    	tv4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dialog1.dismiss();
			}
		});
    	TextView tv5=(TextView) v.findViewById(R.id.tv_tv5);
    	tv5.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
        dialog1=DialogManager.getDiaolg1(this, v);
    	dialog1.show();
    }
    ICallBack icb=new ICallBack() {
		
		@Override
		public void back(Object obj) {
		button_next.setEnabled(true);
		 if(obj!=null)
		 {
			RealName_data rd=(RealName_data) obj;
			RealName rn=rd.data;
			BeikBankApplication.mSharedPref.putSharePrefString(BeikBankConstant.renalName,rn.countAuLeft);
			if(!is_nextpage)
			{  
			   finish();
			   return ;
			}
			if(1==1)
			{   
				if(!is_bank)
				{
					LiuChenManager.is_bank=true;
				}
				LiuChenManager.StartNext(act, icb2);
				
				//finish();
				return ;
			}
			
			UserInfo userInfo=BeikBankApplication.getUserInfo();
			Intent intent=getIntent();
			boolean is_check_bink=BeikBankApplication.mSharedPref.getSharePrefBoolean(BeikBankConstant.is_check_bink,false);
			//点击购买时进行认证
			if(isPurchaseActivity&&!userInfo.isHasBindCard()){
//				userInfo.setHasAuthenticated(true);
//				userInfo.setRealName(realName);
//				userInfo.setIdNumber(idNumber);
//				//UserInfoDao.getInstance(RealnameActivity.this).deleteAll();
//				//UserInfoDao.getInstance(RealnameActivity.this).insertUserInfo(userInfo);
//				TableDaoSimple.delete(UserInfo.class,null,null);
//				TableDaoSimple.insert(userInfo);
				intent.setClass(RealnameActivity.this,BankBindActivity.class);
				intent.putExtra(BeikBankConstant.INTENT_AMOUNT, money);
				startActivity(intent);
			}else if(!is_check_bink){
				intent.setClass(RealnameActivity.this,BandTestActivity.class);
				//Intent intent=new Intent(BankBindActivity.this,BandTestActivity.class);
				startActivity(intent);
			}
			else if(!userInfo.hasSetPaypassword)
			{  
				intent.setClass(RealnameActivity.this,TransactionPwdSetActivity.class);
				//Intent intent=new Intent(BankBindActivity.this,TransactionPwdSetActivity.class);
				intent.putExtra(BeikBankConstant.IS_FORGETTRANSACTIONPWD, false);
				startActivity(intent);
			}
			else
			{
				intent.setClass(RealnameActivity.this,PurchaseActivity.class);
				startActivity(intent);
			}
//			else{
//				userInfo.setHasAuthenticated(true);
//				userInfo.setRealName(realName);
//				userInfo.setIdNumber(idNumber);
//				//UserInfoDao.getInstance(RealnameActivity.this).deleteAll();
//				//UserInfoDao.getInstance(RealnameActivity.this).insertUserInfo(userInfo);
//               
//				TableDaoSimple.delete(UserInfo.class,null,null);
//				TableDaoSimple.insert(userInfo);
//				intent.setClass(RealnameActivity.this, HomeActivity2.class); 
//				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//				startActivity(intent);
//				
//			}
			   finish();
		 }
		}
	};
	ICallBack icb2=new ICallBack() {
		
		@Override
		public void back(Object obj) {
			if(obj!=null&&obj instanceof String)
			{
				if("finish".equals(obj))
				{
					act.finish();
				}
			}
			
		}
	};
	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	protected <T> void startAimActivity(final Class<T> pActClassName) {
		Intent _Intent = new Intent();
		_Intent.setClass(this, pActClassName);
		startActivity(_Intent);
	}

	class TextWatcherListener implements TextWatcher{
		@Override
		public void afterTextChanged(Editable s) {
			// TODO Auto-generated method stub

		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			// TODO Auto-generated method stub
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			ll_error.clearAnimation();
			ll_error.setVisibility(View.INVISIBLE);
			String realname=clearedittext_realname.getText().toString();
			String icnumber=clearedittext_icnumber.getText().toString();
			if(realname.length()>0&&icnumber.length()==18){
				button_next.setEnabled(true);
			}else{
				button_next.setEnabled(false);
			}
		}

	}


	class IcnumberTextWatcher implements TextWatcher{
		int beforeTextLength=0;
		int onTextLength=0;
		boolean isChanged=false;

		int location=0;
		private char[] tempChar;
		private StringBuffer buffer=new StringBuffer();
		int blankspaceNumberB=0;

		@Override
		public void afterTextChanged(Editable editable) {
			// TODO Auto-generated method stub
			String str="";
			if(isChanged){
				location=clearedittext_icnumber.getSelectionEnd();
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
					if((index==6||index==15)){
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

				textview_phone_enlarge.setText(str);
				isChanged=false;

			}
			if(beforeTextLength==0){
				textview_phone_enlarge.setText(editable.toString());
				Utils.performAnimateForDown(linear_bottom,linear_phone_enlarge);
			}else if(beforeTextLength>0&&editable.toString().length()==0){
				textview_phone_enlarge.setText("");
				Utils.performAnimateForUp(linear_bottom);
			}

			if(editable.toString().length()==18&&!clearedittext_realname.getText().toString().equals("")){
				button_next.setEnabled(true);
			}else{
				button_next.setEnabled(false);
			}

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

//	JsonHttpResponseHandler handleAuthRealnameInfoHandler = new JsonHttpResponseHandler(){
//
//		@Override
//		public void onStart() {
//			super.onStart();	
//			dialog=Utils.createPorgressDialogNoCancel(RealnameActivity.this, null);
//			dialog.show();
//			
//		}
//
//		@Override
//		public void onFinish() {
//			super.onFinish();
//			dialog.dismiss();
//			first=1;
//		}
//
//		@Override
//		public void onFailure(Throwable error, String content) {
//			Utils.log(TAG, "onFailure()"+content);
//			first=1;
//		}
//
//		@Override
//		public void onSuccess(Response response) {
//			Utils.log(TAG, "onSuccess()"+response.getResponseString());	
//			String result=Utils.getJsonResult(response.getResponseString(),BeikBankConstant.TYPE_JSONSTATUS);
//			if(result.equals("success")){
//				//UserInfo userInfo=UserInfoDao.getInstance(RealnameActivity.this).getUserInfo();
//				UserInfo userInfo=(UserInfo) TableDaoSimple.queryone(UserInfo.class,null,null);
//				
//				String realName=clearedittext_realname.getText().toString();
//				String idNumber=clearedittext_icnumber.getText().toString();
//				if(isPurchaseActivity&&!userInfo.isHasBindCard()){
//					userInfo.setHasAuthenticated(true);
//					userInfo.setRealName(realName);
//					userInfo.setIdNumber(idNumber);
//					//UserInfoDao.getInstance(RealnameActivity.this).deleteAll();
//					//UserInfoDao.getInstance(RealnameActivity.this).insertUserInfo(userInfo);
//					TableDaoSimple.delete(UserInfo.class,null,null);
//					TableDaoSimple.insert(userInfo);
//					
//					Intent intent=new Intent(RealnameActivity.this,BankBindActivity.class);
//					intent.putExtra(BeikBankConstant.INTENT_AMOUNT, money);
//					startActivity(intent);
//				}else if(isPurchaseActivity&&userInfo.isHasBindCard()){
//					Intent intent = new Intent(RealnameActivity.this, PurchaseActivity.class); 
//					intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); 
//					startActivity(intent); 
//				}else{
//					userInfo.setHasAuthenticated(true);
//					userInfo.setRealName(realName);
//					userInfo.setIdNumber(idNumber);
//					//UserInfoDao.getInstance(RealnameActivity.this).deleteAll();
//					//UserInfoDao.getInstance(RealnameActivity.this).insertUserInfo(userInfo);
//                   
//					TableDaoSimple.delete(UserInfo.class,null,null);
//					TableDaoSimple.insert(userInfo);
//					
//					Intent intent = new Intent(RealnameActivity.this, HomeActivity2.class); 
//					intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//					startActivity(intent);
//					
//				}
//				   finish();
//			}else{
//				String msg=Utils.getJsonResult(response.getResponseString(),BeikBankConstant.TYPE_JSONMESSAGE);
//				textview_toast.setText(msg);
//				Utils.performAnimateForToast(linear_toast, toastAnimSet);
//			}
//
//		}
//
//	};
//	

}
