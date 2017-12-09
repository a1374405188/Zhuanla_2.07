package com.beikbank.android.activity;

import com.beikbank.android.activity.help.ActivitySwitchHelp;
import com.beikbank.android.data.ShoushiIsSet_data;
import com.beikbank.android.data.UserInfo;
import com.beikbank.android.data2.UserCheck2;
import com.beikbank.android.data2.UserCheck2_data;
import com.beikbank.android.data2.getUserOrXiuGai;
import com.beikbank.android.data2.getUserOrXiuGai_data;
import com.beikbank.android.dataparam.ShoushiIsSetParam;
import com.beikbank.android.dataparam2.UserCheckParam2;
import com.beikbank.android.dataparam2.getUserOrXiuGaiParam;
import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.impl.BankListManager;
import com.beikbank.android.net.impl.TongYongManager;
import com.beikbank.android.net.impl.TongYongManager2;
import com.beikbank.android.utils.BeikBankConstant;
import com.beikbank.android.utils.BeikBankDialogListener;
import com.beikbank.android.utils.DialogManager;
import com.beikbank.android.utils.LoginManager;
import com.beikbank.android.utils.Utils;
import com.beikbank.android.utils2.StateBarColor;
import com.beikbank.android.widget.SwitchButton;
import coma.beikbank.android.R;



import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Toast;

/**
 * 个人中心
 * @author Administrator
 *
 */
public class GerenActivity extends BaseActivity implements OnClickListener
{
   private TextView tv_phone;
   private TextView title;
   private LinearLayout back;
   TextView tv_name;
   TextView tv_bank;
   TextView tv_jiaoui; 
	 
	 
	 
	ImageView iv_name;
	 ImageView iv_bank;
   private LinearLayout ll_tuichu;
   /**
    * 实名认证
    */
   private LinearLayout ll_shimin;
   /**
    * 银行卡
    */
   private LinearLayout ll_bank;
   /**
    * xiugai
    */
   private LinearLayout ll_xiugai;
   /**
    * 找回
    */
   private LinearLayout ll_zhaohui;
   /**
    * 弹出的修改密码布局
    */
   private LinearLayout ll_mima;
   /**
    * 更换手机号
    */
   private LinearLayout ll_gegnhuan;
   TextView tv_xiugai_denglu;
	 TextView tv_xiugai_jiaoyi;
	 TextView tv_qvxiao;
   private Activity act;
   /**
    * 1修改2找回
    */
  int index=1;
  
   @Override
  	protected void onCreate(Bundle savedInstanceState) {
  		super.onCreate(savedInstanceState);
  		setContentView(R.layout.activity_gerenzhongxin);
  		StateBarColor.init(this,0xffffffff);
  		act=this;
  	    initView();
  	    initData();
  	}
   Dialog change_account_dialog;
   private SwitchButton sb;
	@Override
	public void onClick(View v) {
		
		Intent intent=new Intent();
		switch (v.getId()) {
		case R.id.ll_genghuan:
			 intent=new Intent(act,YanZhenShengFenActivity.class);
			 Object obj=tv_name.getTag();
			 if(obj!=null)
			 {
				    String tag=tv_name.getTag().toString();
				   
					intent.putExtra("tag",tag);
					intent.putExtra("name",gu.real_name);
			        act.startActivity(intent);
				
			 }
			
			break;
		case R.id.tv_qvxiao:
			ll_mima.setVisibility(View.GONE);
			break;
	     case R.id.tv_xiugai_denglu:
	    	    ll_mima.setVisibility(View.GONE);
	    	   
				if(index==1)
				{
					 intent=new Intent(act,LoginPwdUpdateActivity.class);
				     act.startActivity(intent);
				}
				else
				{
					 if(uc2==null)
			    		{
			    			return;
			    		}
					if("1".equals(uc2.is_real_name))
					{
						intent=new Intent(this,ForgetPwdRealnameActivity.class);
						String phonenumber=BeikBankApplication.getPhoneNumber();
						intent.putExtra(BeikBankConstant.INTENT_PHONENUMBER, phonenumber);
					}
					else
					{
						intent=new Intent(this,ForgetPwdAnonymousActivity.class);
						String phonenumber=BeikBankApplication.getPhoneNumber();
						intent.putExtra(BeikBankConstant.INTENT_PHONENUMBER, phonenumber);
					}
					act.startActivity(intent);
				}
			break;
	     case R.id.tv_xiugai_jiaoyi:
	    		//if("1".equals(uc2.is_tra_password))
				//{
	    	 ll_mima.setVisibility(View.GONE);
	    	 if(index==1)
	    	 {
	    		 intent=new Intent(act,TransactionPwdUpdateActivity.class);
			     act.startActivity(intent);
	    	 }
	    	 else
	    	 {  
	    		 String phonenumber=BeikBankApplication.getPhoneNumber();
	    		intent=new Intent(this,ForgetPwdRealnameActivity.class);
	 			intent.putExtra(BeikBankConstant.IS_FORGETTRANSACTIONPWD, true);
	 			intent.putExtra(BeikBankConstant.INTENT_PHONENUMBER, phonenumber);
	 			startActivity(intent);
	    	 }
					
				//}
//				else
//				{
//					intent=new Intent(act,TransactionPwdSetActivity.class);
//					act.startActivity(intent);
//				}
				break;
		case R.id.ll_shimin:
			if(uc2==null)
			{
				return;
			}
			if("0".equals(uc2.is_real_name))
			{
			 intent=new Intent(act,RealnameActivity.class);
			 act.startActivity(intent);
			}
			break;
		case R.id.linear_left:
			finish();
			break;
		case R.id.ll_xgshoushi:
			Intent intent2=new Intent(act,GesturePwdOldActivity.class);
			intent2.putExtra(BeikBankConstant.INTENT_CHANGEGESTURE, true);
			act.startActivity(intent2);
			break;
		case R.id.ll_xiugai:
			
			
				ll_mima.setVisibility(View.VISIBLE);
				tv_xiugai_denglu.setText("修改登录密码");
				tv_xiugai_jiaoyi.setText("修改交易密码");
			    index=1;
			break;
		case R.id.ll_zhaohui:
			 index=2;
			ll_mima.setVisibility(View.VISIBLE);
			tv_xiugai_denglu.setText("找回登录密码");
			tv_xiugai_jiaoyi.setText("找回交易密码");
			break;
		case R.id.ll_bank:
			if(uc2==null)
			{
				return;
			}   
			if("0".equals(uc2.is_real_name))
			{
				
				Toast.makeText(this,"请先实名认证",Toast.LENGTH_LONG).show();
				
				return;
			}


			intent=new Intent(act,BankMasterActivity.class);
			intent.putExtra("is_bank",uc2.is_bind_card);
			intent.putExtra("state",uc2.card_bind_status);
			act.startActivity(intent);

				
				
			
			break;
		case R.id.ll_tuichu:
			change_account_dialog=Utils.createSimpleDialog(this,
					getString(R.string.logout_text),getString(R.string.ok2),new BeikBankDialogListener() {

				@Override
				public void onRightBtnClick() {

					LoginManager.outLogin(GerenActivity.this,0);
					
					Intent intent3 = new Intent(GerenActivity.this, HomeActivity3.class); 
					HomeActivity3.index=1;
					startActivity(intent3);
                    finish();
				}

				@Override
				public void onListItemLongClick(int position, String string) {
					

				}

				@Override
				public void onListItemClick(int position, String string) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onLeftBtnClick() {
					// TODO Auto-generated method stub
					change_account_dialog.dismiss();
				}

				@Override
				public void onCancel() {
					// TODO Auto-generated method stub

				}
			});
			change_account_dialog.show();
			break;
		default:
			break;
		}
	}
	getUserOrXiuGai gu;
	private void getNameInfo()
	{
		  ICallBack icb_gu=new ICallBack() {
				
				@Override
				public void back(Object obj) {
					if(obj!=null)
					{
						 getUserOrXiuGai_data gd=(getUserOrXiuGai_data) obj;
						 gu=gd.body.get(0);
						 if(gu!=null&&!"".equals(gu.real_name))
						 {
							
								 iv_name.setVisibility(View.GONE);
								 String s1=gu.real_name;
								 if(s1.length()==2)
								 {   
									 s1=gu.real_name.substring(0,1);
									 s1=s1+"*";
								 }
								 else if(s1.length()==3)
								 {   
									 s1=gu.real_name.substring(0,1);
									 s1=s1+"**";
								 }
								 else if(s1.length()==4)
								 {   
									 s1=gu.real_name.substring(0,2);
									 s1=s1+"**";
								 }
								 String s2=Utils.getEncryptedIcNumber(gu.id_number);
								 BeikBankApplication.setSharePref(BeikBankConstant.id_number,gu.id_number);
								 String s=s1+"  "+s2;
								 tv_name.setText(s);
								 tv_name.setTag(s1);
								
								 ll_gegnhuan.setVisibility(View.VISIBLE);
							 }
						 
					}
					
				}
			};
  		  
  		  getUserOrXiuGaiParam gu=new getUserOrXiuGaiParam();
  		  gu.user_code=BeikBankApplication.getUserCode();
  		  gu.operation_type="1";
  		  TongYongManager2 tym3=new TongYongManager2(this, icb_gu,gu);
  		  tym3.start();
		
	}
	UserCheck2 uc2;
	private void getUserInfo()
	{
		ICallBack icb=new ICallBack() {
			
			@Override
			public void back(Object obj) {
				if(obj!=null)
				{
				 UserCheck2_data uc2d=(UserCheck2_data) obj;
					uc2=uc2d.body;
					BeikBankApplication.setSharePref(BeikBankConstant.is_bindbank,uc2.is_bind_card);
					BeikBankApplication.setSharePref(BeikBankConstant.is_shimin,uc2.is_real_name);
					BeikBankApplication.setSharePref(BeikBankConstant.is_olduser,uc2.is_new_user);
					BeikBankApplication.setSharePref(BeikBankConstant.is_jiaoyi,uc2.is_tra_password);
				 if("1".equals(uc2.is_bind_card)||"3".equals(uc2.card_bind_status))
				 {
					 tv_bank.setText("已绑定");
				 }
				
				
				}
				
			}
		};
		
		UserCheckParam2 uc2=new UserCheckParam2();
    	uc2.check_type="0";
    	//uc2.phone_number=BeikBankApplication.getPhoneNumber();
    	uc2.user_code=BeikBankApplication.getUserCode();
     	TongYongManager2 tym2=new TongYongManager2(act, icb,uc2);
    	tym2.start();
	}
	
	private void initData()
	{
		
		//ShoushiIsSetParam  ssp=new ShoushiIsSetParam();
		String id=BeikBankApplication.getUserCode();
		
		Log.d("code","code"+id);
//		if(id==null||id.equals(""))
//		{
//			return;
//		}
//		ssp.type="2";
//		ssp.userId=id;
//    	ICallBack icb_shoushi=new ICallBack() {
//			
//			@Override
//			public void back(Object obj) {
//				if(obj!=null)
//				{
//					ShoushiIsSet_data sd=(ShoushiIsSet_data) obj;
//					if(sd.data.equals("0"))
//					{
//						BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.IS_BINDGESTURE,true);
//						flag=true;
//						initShouShi();
//					}
//				}
//				
//			}
//		};
//    	
//    	TongYongManager tym=new TongYongManager(this, icb_shoushi,ssp);
//    	tym.start();
    	if(uc2!=null)
    	{
    		if("1".equals(uc2.is_gesture_password))
    		{
    			BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.IS_BINDGESTURE,true);
				flag=true;
				initShouShi();
    		}
    	}
    	
    	getNameInfo();
    	getUserInfo();
	
	}
	private void initView()
	{
		 tv_phone=(TextView) findViewById(R.id.tv_phone);
		 String phone=BeikBankApplication.getPhoneNumber();
		 tv_phone.setText(Utils.getEncryptedPhonenumber(phone));
		 title=(TextView) findViewById(R.id.titleTv);
		 title.setText("个人中心");
		 back=(LinearLayout) findViewById(R.id.linear_left);
		 back.setOnClickListener(this);
		 ll_tuichu=(LinearLayout) findViewById(R.id.ll_tuichu);
		 ll_tuichu.setOnClickListener(this);
		 
		 ll_mima=(LinearLayout) findViewById(R.id.ll_mima);
		 ll_mima.setOnClickListener(this);
		 tv_xiugai_denglu=(TextView) findViewById(R.id.tv_xiugai_denglu);
		 tv_xiugai_jiaoyi=(TextView) findViewById(R.id.tv_xiugai_jiaoyi);
		 tv_qvxiao=(TextView) findViewById(R.id.tv_qvxiao);
		 tv_xiugai_denglu.setOnClickListener(this);
		 tv_xiugai_jiaoyi.setOnClickListener(this);
		 tv_qvxiao.setOnClickListener(this);
		 
		 ll_bank=(LinearLayout) findViewById(R.id.ll_bank);
		 ll_xiugai=(LinearLayout) findViewById(R.id.ll_xiugai);
		 ll_zhaohui=(LinearLayout) findViewById(R.id.ll_zhaohui);
		 ll_shimin=(LinearLayout) findViewById(R.id.ll_shimin);
		 ll_bank.setOnClickListener(this);
		 ll_xiugai.setOnClickListener(this);
		 ll_zhaohui.setOnClickListener(this);
		 ll_shimin.setOnClickListener(this);
		 tv_jiaoui=(TextView) findViewById(R.id.tv_jiaoyi);
		 ll_gegnhuan=(LinearLayout) findViewById(R.id.ll_genghuan);
		 ll_gegnhuan.setOnClickListener(this);
		 //UserInfo userInfo=BeikBankApplication.getUserInfo();
//		 if(userInfo!=null)
//		 {
//			 tv_jiaoui.setText("修改交易密码");
//		 }
		
		 
		 initShouShi();
		 
		 tv_name=(TextView) findViewById(R.id.tv_name);
		 tv_bank=(TextView) findViewById(R.id.tv_bank);
		 
		 
		 
		 
		 iv_name=(ImageView) findViewById(R.id.iv_name);
		 iv_bank=(ImageView) findViewById(R.id.iv_bank);
		
		 
//		 if(userInfo.hasBindCard)
//		 {
//			 tv_bank.setText("");
//		 }
//		 if(userInfo.hasAuthenticated)
//		 {
//			 iv_name.setVisibility(View.GONE);
//			 String s1="**";
//			 if(s1.length()==2)
//			 {   
//				 s1=userInfo.getRealName().substring(0,1);
//				 s1=s1+"*";
//			 }
//			 else if(s1.length()==3)
//			 {   
//				 s1=userInfo.getRealName().substring(0,1);
//				 s1=s1+"**";
//			 }
//			 else if(s1.length()==4)
//			 {   
//				 s1=userInfo.getRealName().substring(0,2);
//				 s1=s1+"**";
//			 }
//			 String s2=Utils.getEncryptedIcNumber(userInfo.getIdNumber());
//			 
//			 String s=s1+"  "+s2;
//			 tv_name.setText(s);
//		 }
		 
		 
		 
		 
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		flag=BeikBankApplication.mSharedPref.getSharePrefBoolean(BeikBankConstant.IS_BINDGESTURE,false);
		initView();
		initShouShi();
		
		getNameInfo();
		 getUserInfo();
	}
	boolean flag=false;
	private void initShouShi()
	{
		 sb=(SwitchButton) findViewById(R.id.sb_shoushi);
		
			
		 LinearLayout ll_xgshoushi =(LinearLayout) findViewById(R.id.ll_xgshoushi);
		 ll_xgshoushi.setOnClickListener(this);
		 View view=findViewById(R.id.v_xgshoushi);
		 if(!flag){
				//linear_gesture_parent.setVisibility(View.GONE);
				sb.setChecked(false,false);
				ll_xgshoushi.setVisibility(View.GONE);
				view.setVisibility(View.GONE);
			}else{
				//linear_gesture_parent.setVisibility(View.VISIBLE);
				sb.setChecked(true,false);
				ll_xgshoushi.setVisibility(View.VISIBLE);
				view.setVisibility(View.VISIBLE);
			}
			sb.setOnCheckedChangeListener(new OnCheckedChangeListener() {

				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

	                setGuese(isChecked,sb.isOnlick);
				}
			}); 
	}
	/**
	 * 设置手势密码的状态
	 * @param b
	 */
	public void setGuese(boolean b,boolean isFounce)
	{        
			 if(b)
			 {   
				 if(isFounce)
				 {
					
					 Intent intent=new Intent(this,GesturePwdGuideActivity2.class);
					 ActivitySwitchHelp.start(this, intent,false);
				 }
				 //linear_gesture_parent.setVisibility(View.VISIBLE);
			 }
			 else
			 {  
				 if(isFounce)
				 {
					 Intent intent=new Intent(act,GesturePwdOldActivity.class);
					 intent.putExtra(BeikBankConstant.INTENT_CHANGEGESTURE, false);//关闭手势密码
					 act.startActivity(intent);
				 }
				 //linear_gesture_parent.setVisibility(View.GONE);
			 }
	}
	Dialog dialog4;
	//next
    public void createDialog()
    {
    	View v=LayoutInflater.from(this).inflate(
    			R.layout.dialog_simple_layout,null);
    	TextView tv1=(TextView) v.findViewById(R.id.dialog_confirm_title);
    	tv1.setText("请先实名认证");
    	
    	
    	//cacle
    	TextView tv4=(TextView) v.findViewById(R.id.dialog_confirm_btn2);
    	tv4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dialog4.dismiss();
			}
		});
    	TextView tv5=(TextView) v.findViewById(R.id.dialog_confirm_btn1);
    	tv5.setText("实名认证");
    	tv5.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			   
			   dialog4.dismiss();	
		       Intent intent=new Intent(act,RealnameActivity.class);
		       act.startActivity(intent);
			}
		});
        dialog4=DialogManager.getDiaolg1(this, v);
    	dialog4.show();
    }
	
}
