package com.beikbank.android.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.beikbank.android.activity.RegisterCodeInputActivity.MyCount;
import com.beikbank.android.activity.YanZhenShengFenActivity.TextWatcherListener;
import com.beikbank.android.conmon.SystemConfig;
import com.beikbank.android.data.Config_data;
import com.beikbank.android.data.Confing;
import com.beikbank.android.data2.checkYanZhenMa_data;
import com.beikbank.android.dataparam2.HeadParam2;
import com.beikbank.android.dataparam2.checkYanZhenMaParam;
import com.beikbank.android.dataparam2.getYanZhenMaParam;
import com.beikbank.android.dataparam2.phoneChangeParam;
import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.impl.ConfigManager;
import com.beikbank.android.net.impl.TongYongManager2;
import com.beikbank.android.utils.AdvancedCountdownTimer;
import com.beikbank.android.utils.BeikBankConstant;
import com.beikbank.android.utils.BeikBankDialogListener;
import com.beikbank.android.utils.Utils;
import com.beikbank.android.utils2.StateBarColor;
import com.beikbank.android.webwiew.WebWiewInface;
import com.beikbank.android.widget.ClearableEditText;
import coma.beikbank.android.R;



//更换手机号
public class PhoneChangeActivity extends BaseActivity1 implements OnClickListener{
	TextView title;
    /**
     * return
     */
    LinearLayout ll1;
  
    TextView tv1;
    ClearableEditText et1;
    ClearableEditText et2;
    private final int TOTALTIME=60*1000;
    	private final int COUNTDOWNINTERVAL=1000;//间隔1秒
    Button bu;
    Button bu_yanzhenma;
    private MyCount timer;
    Activity act=this;
    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_phone_change);
		StateBarColor.init(this,0xffffffff);
		initView();
		
		
        
	}


	  
	  private void initView()
	    {   
		    ll_error=(LinearLayout) findViewById(R.id.ll_error);
			tv_error=(TextView) findViewById(R.id.tv_error);
	        ll1=(LinearLayout)findViewById(R.id.linear_left);
	        ll1.setOnClickListener(this);
	        title=(TextView)findViewById(R.id.titleTv);
	        title.setText("绑定新号");
	        et1=(ClearableEditText) findViewById(R.id.et1);
	        et2=(ClearableEditText) findViewById(R.id.et2);
	        et1.addTextChangedListener(new TextWatcherListener2());
	        et2.addTextChangedListener(new TextWatcherListener());
	        bu=(Button) findViewById(R.id.button_next);
	        bu.setOnClickListener(this);
	        bu_yanzhenma=(Button) findViewById(R.id.bu_yanzhenma);
	        bu_yanzhenma.setOnClickListener(this);
	        
	    }
	  class TextWatcherListener2 implements TextWatcher {
			@Override
			public void afterTextChanged(Editable s) {

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				String s1=et1.getText().toString();
				
				
			
				if (s1.length()==11) {
					bu_yanzhenma.setEnabled(true);
				} else {
					bu.setEnabled(false);
				}
			}

		}
	  class TextWatcherListener implements TextWatcher {
			@Override
			public void afterTextChanged(Editable s) {

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				String s1=et1.getText().toString();
				String s2=et2.getText().toString();
				
			
				if (s1.length()==11&&s2.length()>0) {
					bu.setEnabled(true);
				} else {
					bu.setEnabled(false);
				}
			}

		}
	//实现计时功能的类  
		class MyCount extends AdvancedCountdownTimer {  

			public MyCount(long millisInFuture, long countDownInterval) {  
				super(millisInFuture, countDownInterval);  
				
				ForegroundColorSpan fcs = new ForegroundColorSpan(getResources().getColor(R.color.red1));
				SpannableString ssb = new SpannableString(String.valueOf(TOTALTIME/COUNTDOWNINTERVAL));
				ssb.setSpan(fcs, 0, ssb.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);			
				bu_yanzhenma.setText(ssb);
				bu_yanzhenma.append(getString(R.string.identify_code_reload));
				bu_yanzhenma.setEnabled(false);
			}  

			@Override  
			public void onFinish() {              
				
				bu_yanzhenma.setText(getString(R.string.identify_code_reloading));
				bu_yanzhenma.setEnabled(true);
				timer=null;
			}     
			//更新剩余时间  
			@Override  
			public void onTick(long millisUntilFinished, int percent) { 
				long time = (millisUntilFinished / 1000);  
//				ForegroundColorSpan fcs = new ForegroundColorSpan(getResources().getColor(R.color.red1));
//				SpannableString ssb = new SpannableString(String.valueOf(time));
//				ssb.setSpan(fcs, 0, ssb.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);			
				bu_yanzhenma.setEnabled(false);
				//bu_yanzhenma.setText(ssb);
				//bu_yanzhenma.append(getString(R.string.identify_code_reload));
				bu_yanzhenma.setText(time+"s后重新获取");
			}  

		}  
		TongYongManager2 tym2;
		 HeadParam2 hp;
		private void sent()
		{
			
	ICallBack icb_gyz=new ICallBack() {
				
				@Override
				public void back(Object obj) {
					// TODO Auto-generated method stub
					if(obj!=null)
					{
						hp=tym2.hp;
					}
				}
			};
			getYanZhenMaParam gyz=new getYanZhenMaParam();
			gyz.generate_code_type="0";
			gyz.phone_number=et1.getText().toString();
			
			tym2=new TongYongManager2(this, icb_gyz,gyz);
			tym2.start();
			
		}
		private void check()
		{   
			if(hp==null)
			{
				showToast("请先发送验证码");
				return;
			}
			String vertifycode=et2.getText().toString();

			ICallBack icb_gyz=new ICallBack() {
				
				@Override
				public void back(Object obj) {
					// TODO Auto-generated method stub
					if(obj!=null)
					{   
						checkYanZhenMa_data cd=(checkYanZhenMa_data) obj;
						if("0000".equals(cd.header.re_code))
						{
							
						
//						Intent intent=new Intent(RegisterCodeInputActivity.this,RegisterPwdSetActivity.class);
//						intent.putExtra(BeikBankConstant.IS_FORGETLOGINPWD, false);
//						intent.putExtra(BeikBankConstant.INTENT_PHONENUMBER, phonenumber);
//						startActivity(intent);
						    change();
						}
					}
				}
			};
			checkYanZhenMaParam cyzm=new checkYanZhenMaParam();
			cyzm.generate_seq=hp.request_seq;
			cyzm.phone_number=et1.getText().toString();
			cyzm.verification_code=vertifycode;
			TongYongManager2 tym2=new TongYongManager2(this, icb_gyz,cyzm);
			tym2.start();
		}
		
		private void change()
		{  
			ICallBack icb=new ICallBack() {
				
				@Override
				public void back(Object obj) {
					// TODO Auto-generated method stub
					if(obj!=null)
					{
						String code=BeikBankApplication.getUserCode();
						 BeikBankApplication.setPhoneNumber(et1.getText().toString());
						 
						 BeikBankApplication.setSharePref(BeikBankConstant.user_code,code);
						 showTiShi();
					}
				}
			};
			phoneChangeParam pp=new phoneChangeParam();
			pp.change_type="0";
			pp.new_phone_number=et1.getText().toString();
			pp.old_phone_number=BeikBankApplication.getPhoneNumber();
			TongYongManager2 tym2=new TongYongManager2(this, icb,pp);
			tym2.start();
		}
		 private void showTiShi()
		 {
				
				
				final Dialog dialog=Utils.createTiShiDialog(this,null);
				dialog.show();
				
				final Handler h=new Handler()
				{

					@Override
					public void handleMessage(Message msg) {
						
						super.handleMessage(msg);
						if(msg.what==1)
						{
							dialog.dismiss();
							finish();
						}
					}
					
				};
				Runnable run=new Runnable() {
					
					@Override
					public void run() {
					
						Message msg=new Message();
						msg.what=1;
						h.sendMessage(msg);
					}
				};
				h.postDelayed(run,2);
				
		}
	   public void onClick(View view)
	   {
	      switch (view.getId())
	      {
	         case R.id.linear_left:
	             finish();
	          break;
	         case R.id.right:
	           
	          break;
	         case R.id.bu_yanzhenma:
		           sent();
		           timer = new MyCount(TOTALTIME, COUNTDOWNINTERVAL);
				   timer.start();	
		          break;
	         case R.id.button_next:
		           check();
		          break;
	      }
	      
	   }
}
