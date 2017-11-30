package com.beikbank.android.activity;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.beikbank.android.api.BeikBankApi;
import com.beikbank.android.dao.TableDaoSimple;
import com.beikbank.android.dao.UserInfoDao;
import com.beikbank.android.data.UserInfo;
import com.beikbank.android.dataparam.OneKeyParam;
import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.http.Response;
import com.beikbank.android.net.HandlerBase;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.impl.OneKeyManager;
import com.beikbank.android.net.impl.OneKeyManager2;
import com.beikbank.android.utils.BeikBankConstant;
import com.beikbank.android.utils.BeikBankDialogListener;
import com.beikbank.android.utils.Utils;
import com.beikbank.android.utils2.StateBarColor;
import com.beikbank.android.widget.ClearableEditText;
import coma.beikbank.android.R;



//意见反馈
public class FeedbackActivity extends BaseActivity1 implements OnClickListener{

	private final String TAG="FeedbackActivity";
	private TextView titleTv;
	private ClearableEditText clearedittext_contact;
	private EditText edittext_feedbackcontent;
	private TextView textview_feedback_left;
	private Button button_submit;
	private LinearLayout linear_left,linear_telephone;	
	private static final int MAX_COUNT = 140; 
	private Dialog dialog,dialog1;
    Activity act=this;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_feedback);
		StateBarColor.init(this,0xffffffff);
		initView();
        getData();
	}
	public void initView(){
		ll_error=(LinearLayout) findViewById(R.id.ll_error);
    	tv_error=(TextView) findViewById(R.id.tv_error);
		
		
		titleTv = (TextView) findViewById(R.id.titleTv);
		titleTv.setText(getString(R.string.feedback));

		linear_left = (LinearLayout) findViewById(R.id.linear_left);
		linear_left.setVisibility(View.VISIBLE);
		linear_left.setOnClickListener(this);

		clearedittext_contact=(ClearableEditText)findViewById(R.id.clearedittext_contact);
		
		clearedittext_contact.addTextChangedListener(new TextWatcherListener());
		edittext_feedbackcontent=(EditText)findViewById(R.id.edittext_feedbackcontent);
		edittext_feedbackcontent.addTextChangedListener(mTextWatcher);
		edittext_feedbackcontent.setSelection(edittext_feedbackcontent.length());

		textview_feedback_left=(TextView)findViewById(R.id.textview_feedback_left);
		linear_telephone=(LinearLayout)findViewById(R.id.linear_telephone);
		linear_telephone.setOnClickListener(this);

		setLeftCount(); 
		button_submit=(Button)findViewById(R.id.button_next);
		button_submit.setText("提交");
		button_submit.setOnClickListener(this);
		
		String phonenumber=BeikBankApplication.mSharedPref.getSharePrefString(BeikBankConstant.LOGIN_ACCOUNT);
		if(!phonenumber.equals("")||phonenumber!=null){
			clearedittext_contact.setText(phonenumber);
			edittext_feedbackcontent.requestFocus();
			
		}
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
			String phonenumber=clearedittext_contact.getText().toString();
			String feedback=edittext_feedbackcontent.getText().toString();
			if(phonenumber.length()>0&&feedback.length()>0){
				button_submit.setEnabled(true);
			}else{
				button_submit.setEnabled(false);
			}
		}

	}
	private TextWatcher mTextWatcher = new TextWatcher() {  

		private int editStart;  

		private int editEnd;  

		public void afterTextChanged(Editable s) {  
			editStart = edittext_feedbackcontent.getSelectionStart();  
			editEnd = edittext_feedbackcontent.getSelectionEnd();  

			edittext_feedbackcontent.removeTextChangedListener(mTextWatcher);  

			while (s.toString().length() > MAX_COUNT) { // 当输入字符个数超过限制的大小时，进行截断操作  
				s.delete(editStart - 1, editEnd);  
				editStart--;  
				editEnd--;  
			}  
			edittext_feedbackcontent.setSelection(editStart);
			edittext_feedbackcontent.addTextChangedListener(mTextWatcher);  
			setLeftCount(); 
			String phonenumber=clearedittext_contact.getText().toString();
			String feedback=edittext_feedbackcontent.getText().toString();
			if(phonenumber.length()>0&&feedback.length()>0){
				button_submit.setEnabled(true);
			}else{
				button_submit.setEnabled(false);
			}
		}  

		public void beforeTextChanged(CharSequence s, int start, int count,  
				int after) {  

		}  

		public void onTextChanged(CharSequence s, int start, int before,  
				int count) {  
			ll_error.clearAnimation();
			ll_error.setVisibility(View.INVISIBLE);
		}  

	};  
    ICallBack icb=new ICallBack() {
		
		@Override
		public void back(Object obj) {
			if(obj!=null)
			{
				HandlerBase.showMsg(act,act.getString(R.string.success_1),0);
				button_submit.setEnabled(false);
			}
			else
			{
				button_submit.setEnabled(true);
			}
		}
	};
	String phonenumber;
	String feedback;
    public OneKeyParam getData()
    {   
    	OneKeyParam okp=new OneKeyParam();
    	TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);   
        String imei= tm.getDeviceId();
    	//String softversion=tm.getDeviceSoftwareVersion(); 
        //String ip=getLocalIpAddress();
        String device_model = Build.MODEL; // 设备型号   
        String version_sdk = Build.VERSION.SDK; // 设备SDK版本   
        String version_release = Build.VERSION.RELEASE;
        //String error=BeikBankApplication.mSharedPref.getSharePrefString(BeikBankConstant.s_end_error);
        UserInfo ui=BeikBankApplication.getUserInfo();
        
    	okp.content=feedback;
    	
//    	if(error!=null)
//    	{
//    		okp.content+="\nerr0r"+error;
//    	}
    	okp.rasPhoneNumber=phonenumber;
    	//操作系统类型
    	okp.rasType="2";
    	if(ui!=null)
    	{
    		okp.userId=ui.id;
    	}
    	else
    	{
    		okp.userId="";
    	}
    	 
    	okp.osVersion=version_release+":"+version_sdk;
    	okp.netType=getCurrentNetType(this);
    	okp.rasIM="";
    	//系统版本
    	okp.softVersion="";
    	//okp.rasIp="";
    	//设备型号
    	okp.rasModel="";
    	if(imei!=null)
    	{
    		okp.rasIM=imei;
    	}

    		okp.softVersion=Utils.getVersion(act);
    	
//    	if(ip!=null)
//    	{
//    		okp.rasIp=ip;
//    	}
    	if(device_model!=null)
    	{
    		okp.rasModel=device_model;
    	}
    	return okp;
    }
    private String intToIp(int i) {       
    	       
    	          return (i & 0xFF ) + "." +       
    	       ((i >> 8 ) & 0xFF) + "." +       
    	       ((i >> 16 ) & 0xFF) + "." +       
    	       ( i >> 24 & 0xFF) ;  
    	    }   

    public String getLocalIpAddress() { 
        try { 
        	  //获取wifi服务  
        	        WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);  
        	       //判断wifi是否开启  
        	       if (wifiManager.isWifiEnabled()) {  
        	    	   WifiInfo wifiInfo = wifiManager.getConnectionInfo();       
            	       int ipAddress = wifiInfo.getIpAddress();   
            	       String ip = intToIp(ipAddress);   
            	       return ip;
        	        }  
        	      
                   
        	
        	
        for (Enumeration<NetworkInterface> en = NetworkInterface .getNetworkInterfaces(); en.hasMoreElements();) { 
                NetworkInterface intf = en.nextElement(); 
                for (Enumeration<InetAddress> enumIpAddr = intf .getInetAddresses(); enumIpAddr.hasMoreElements();) { 
                        InetAddress inetAddress = enumIpAddr.nextElement(); 
                        if (!inetAddress.isLoopbackAddress()) { 
                                return inetAddress.getHostAddress().toString(); 
                        } 
                } 
                } 
        } catch (SocketException ex) { 
                Log.e("WifiPreference IpAddress", ex.toString()); 
        } 
        return null; 
} 
    public static String getCurrentNetType(Context context) {  
          String type = ""; 
       try
       {
    	   
       
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);  
        NetworkInfo info = cm.getActiveNetworkInfo();  
        if (info == null) {  
            type = "null";  
        } else if (info.getType() == ConnectivityManager.TYPE_WIFI) {  
            type = "wifi";  
        } else if (info.getType() == ConnectivityManager.TYPE_MOBILE) {  
            int subType = info.getSubtype();  
            if (subType == TelephonyManager.NETWORK_TYPE_CDMA || subType == TelephonyManager.NETWORK_TYPE_GPRS  
                    || subType == TelephonyManager.NETWORK_TYPE_EDGE) {  
                type = "2g";  
            } else if (subType == TelephonyManager.NETWORK_TYPE_UMTS || subType == TelephonyManager.NETWORK_TYPE_HSDPA  
                    || subType == TelephonyManager.NETWORK_TYPE_EVDO_A || subType == TelephonyManager.NETWORK_TYPE_EVDO_0  
                    || subType == TelephonyManager.NETWORK_TYPE_EVDO_B) {  
                type = "3g";  
            } else if (subType == TelephonyManager.NETWORK_TYPE_LTE) {// LTE是3g到4g的过渡，是3.9G的全球标准  
                type = "4g";  
            }  
        } 
       }
       catch(Exception e)
       {
    	   e.printStackTrace();
       }
        return type;  
    }  
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.button_next:
			 phonenumber=clearedittext_contact.getText().toString();
			 feedback=edittext_feedbackcontent.getText().toString();
			//BeikBankApi.getInstance().handleFeedbackInfo(FeedbackActivity.this,phonenumber,feedback,handleFeedbackInfoHandler);
			if(feedback==null||"".equals(feedback))
			{
				HandlerBase.showMsg(act,act.getString(R.string.error_4),0);
			}
			else
			{
				//new OneKeyManager2(act, icb).start(phonenumber,feedback);
				OneKeyManager okm=new OneKeyManager(act, icb);
				okm.init(getData());
				okm.start();
			}
			break;
		case R.id.linear_left:
			finish();
			break;
		case R.id.linear_telephone:
			dialog1=Utils.createSimpleDialog(FeedbackActivity.this, 
					getString(R.string.beike_telephone), getString(R.string.dial), new BeikBankDialogListener() {

				@Override
				public void onRightBtnClick() {
					// TODO Auto-generated method stub
					String phoneStr=getString(R.string.beike_telephone2);
					phoneStr=phoneStr.substring(0,phoneStr.indexOf("("));
					Intent telephoneIntent = new Intent();
					telephoneIntent.setAction(Intent.ACTION_CALL);
					telephoneIntent.setData(Uri.parse("tel:"+phoneStr.replaceAll("-", "")));
					startActivity(telephoneIntent);
				}

				@Override
				public void onListItemLongClick(int position, String string) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onListItemClick(int position, String string) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onLeftBtnClick() {
					// TODO Auto-generated method stub
					dialog1.dismiss();
				}

				@Override
				public void onCancel() {
					// TODO Auto-generated method stub
					dialog1.dismiss();
				}
			});
			dialog1.show();
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


	private void setLeftCount() {  
		textview_feedback_left.setText(String.format(getResources().getString(R.string.feedback_left), 
				String.valueOf((MAX_COUNT - getInputCount()))));
	}  

	private long getInputCount() {  
		return edittext_feedbackcontent.getText().toString().length();  
	}

//	JsonHttpResponseHandler handleFeedbackInfoHandler = new JsonHttpResponseHandler(){
//
//		@Override
//		public void onStart() {
//			super.onStart();	
//			dialog=Utils.createPorgressDialog(FeedbackActivity.this, null);
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
//				finish();
//				Toast.makeText(FeedbackActivity.this, "反馈成功！", Toast.LENGTH_SHORT).show();
//			}
//
//		}
//
//	};
	


}
