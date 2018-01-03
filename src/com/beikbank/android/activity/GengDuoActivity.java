package com.beikbank.android.activity;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.beikbank.android.conmon.SystemConfig;
import com.beikbank.android.data.Config_data;
import com.beikbank.android.data.Confing;
import com.beikbank.android.data2.CheckUpdate_data;
import com.beikbank.android.data2.getShare_data;
import com.beikbank.android.dataparam2.CheckUpdateParam;
import com.beikbank.android.dataparam2.getShareParam;
import com.beikbank.android.exception.LogHandler;
import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.net.HandlerBase;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.ManagerParam;
import com.beikbank.android.net.impl.CheckUpdateManager;
import com.beikbank.android.net.impl.ConfigManager;
import com.beikbank.android.net.impl.TongYongManager2;
import com.beikbank.android.share.ShateUtil;
import com.beikbank.android.utils.BeikBankConstant;
import com.beikbank.android.utils.BeikBankDialogListener;
import com.beikbank.android.utils.LoginManager;
import com.beikbank.android.utils.Utils;
import com.beikbank.android.utils2.StateBarColor;
import coma.beikbank.android.R;



//更多
public class GengDuoActivity extends BaseActivity1 implements OnClickListener{
    private static Activity act;
	private TextView titleTv;
	private TextView  lianxikehu;
	private LinearLayout linear_left;
	private LinearLayout ll_guanyu;
	private LinearLayout ll_bangzhu;
	private LinearLayout ll_yijian;
	private LinearLayout ll_guli;
	private LinearLayout ll_banben;
	private LinearLayout ll_share;
	   Dialog change_account_dialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gengduo);
		StateBarColor.init(this,0xffffffff);
		act=this;
		initView();
	}
	
	public void initView(){
		titleTv = (TextView) findViewById(R.id.titleTv);
		titleTv.setText("更多");
		
		lianxikehu = (TextView) findViewById(R.id.tv_lianxikehu);
		lianxikehu.setOnClickListener(this);
		
		linear_left = (LinearLayout) findViewById(R.id.linear_left);
		linear_left.setVisibility(View.VISIBLE);
		linear_left.setOnClickListener(this);
        
		ll_share=(LinearLayout) findViewById(R.id.ll_share);
		ll_share.setOnClickListener(this);
		
		ll_guanyu=(LinearLayout) findViewById(R.id.ll_guanyu);
		ll_bangzhu=(LinearLayout) findViewById(R.id.ll_bangzhu);
		ll_banben=(LinearLayout) findViewById(R.id.ll_banben);
		ll_guli=(LinearLayout) findViewById(R.id.ll_guli);
		ll_yijian=(LinearLayout) findViewById(R.id.ll_yijian);
	    LinearLayout	ll_geren=(LinearLayout) findViewById(R.id.ll_geren);
	    ll_geren.setOnClickListener(this);
		ll_guanyu.setOnClickListener(this);
		ll_bangzhu.setOnClickListener(this);
		ll_banben.setOnClickListener(this);
		ll_guli.setOnClickListener(this);
		ll_yijian.setOnClickListener(this);
		
		TextView tv_banben=(TextView) findViewById(R.id.tv_banben);
		tv_banben.setText(BeikBankApplication.getVersion(this));
		
		LinearLayout ll_denglu_or_zhuce=(LinearLayout) findViewById(R.id.ll_denglu_or_zhuce);
		ll_denglu_or_zhuce.setOnClickListener(this);
		TextView tv_phone=(TextView) findViewById(R.id.tv_phone);
		
		String phone=BeikBankApplication.getPhoneNumber();
		boolean islogin=BeikBankApplication.mSharedPref.getSharePrefBoolean(BeikBankConstant.DO_SUCCESS,false);
		TextView tv_denglu=(TextView) findViewById(R.id.tv_denglu);
		if(islogin)
		{   
			tv_phone.setText(Utils.getEncryptedPhonenumber(phone));
		
			tv_denglu.setText("退出登录");
		}
		else
		{   
			tv_phone.setText("个人中心");
			tv_denglu.setText("登录/注册");
		}
	}
	Dialog  dialog1;
   
	
	private void share()
	{   
		ICallBack icb=new ICallBack() {
			
			@Override
			public void back(Object obj) {
				if(obj!=null)
				{   
					getShare_data gd=(getShare_data) obj;
					
					ShateUtil su=new ShateUtil(act,ll_share);
					su.share2(act,gd.body.title,gd.body.content,gd.body.linkurl,gd.body.icon);
				}
			}
		};
		getShareParam gsp=new getShareParam();
		gsp.share_type="0";
		
		TongYongManager2 tym2=new TongYongManager2(act, icb,gsp);
		tym2.start();
	}

	/**
	 * 退出登录
	 */
	public static void tuichu()
	{
		BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.DO_SUCCESS,false);
		BeikBankApplication.setSharePref(BeikBankConstant.money_is_yincang,"0");
		//BeikBankApplication.mSharedPref.putSharePrefString(BeikBankConstant.LOGIN_ACCOUNT,"");
		BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.re_home,true);
		BeikBankApplication.setSharePref(BeikBankConstant.user_code,"");
	}
	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		initView();
	}
	@Override
	public void onClick(View v) {
		boolean islogin=BeikBankApplication.mSharedPref.getSharePrefBoolean(BeikBankConstant.DO_SUCCESS,false);
		Intent intent=null;
		switch(v.getId()){
		case R.id.ll_share:
		   share();
		break;
		case R.id.ll_denglu_or_zhuce:
			if(!islogin)
			{
				intent=new Intent(this,LoginRegActivity.class);
				startActivity(intent);
			}
			else
			{   
				
				change_account_dialog=Utils.createSimpleDialog(this,
						getString(R.string.logout_text),getString(R.string.ok2),new BeikBankDialogListener() {

					@Override
					public void onRightBtnClick() {
						//intent = new Intent(this, HomeActivity4.class); 
					    //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					    tuichu();
						//startActivity(intent);
						initView();
						
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
				
			}
			break;
		case R.id.tv_lianxikehu:
			dialog1=Utils.createSimpleDialog(this, 
					getString(R.string.beike_telephone), getString(R.string.dial), new BeikBankDialogListener() {

				@Override
				public void onRightBtnClick() {
					// TODO Auto-generated method stub
					String phoneStr=getString(R.string.beike_telephone2);
					//phoneStr=phoneStr.substring(0,phoneStr.indexOf("("));
					Intent telephoneIntent = new Intent();
					telephoneIntent.setAction(Intent.ACTION_CALL);
					telephoneIntent.setData(Uri.parse("tel:"+phoneStr.replaceAll("-", "")));
					startActivity(telephoneIntent);
				}

				@Override
				public void onListItemLongClick(int position, String string) {
					// TODO Auto-generated method stub

				}

				@Override{
					// TODO Auto-generated method stub
					dialog1.dismiss();
				}
				public void onListItemClick(int position, String string) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onLeftBtnClick()

				@Override
				public void onCancel() {
					// TODO Auto-generated method stub
					dialog1.dismiss();
				}
			});
			dialog1.show();
			break;
		case R.id.linear_left:
			finish();
			break;
        case R.id.ll_yijian:
        	startAimActivity(FeedbackActivity.class);
			break;	
        case R.id.ll_guli:
        	shareToMarket();
			break;
        case R.id.ll_banben:
        	//String version=Utils.getVersion(act);
        	//new CheckUpdateManager(act, icb).start();
			checkUpdate();
			break;
		case R.id.ll_bangzhu:
			//startAimActivity(HelpCenterActivity2.class);
			intent=new Intent(act,HuodongActivity2.class);
			intent.putExtra("title","帮助中心");
			intent.putExtra("url",SystemConfig.huodong_url+"#!/help");
			intent.putExtra("right","0");
			startActivity(intent);
			break;
        case R.id.ll_guanyu:
        	startAimActivity(AboutActivity.class);
			break;	
        case R.id.ll_geren:
        	if(!islogin)
			{
				intent=new Intent(this,LoginRegActivity.class);
				startActivity(intent);
			}
			else
			{   
				startAimActivity(GerenActivity.class);
			}
        	
			break;	
			
		}
	}

	private void checkUpdate()
	{
		ICallBack icb=new ICallBack() {
			@Override
			public void back(Object obj) {
				if(obj!=null)
				{

					CheckUpdate_data cd=(CheckUpdate_data)obj;
					String version=Utils.getVersion(act);
					if(!version.equals(cd.body.android))
					{   if("1".equals(cd.body.android_update)) {
						GengDuoActivity.showUpdataDialog(act,cd.body.android_url,true);
					}
					else
					{
						GengDuoActivity.showUpdataDialog(act,cd.body.android_url,false);
					}
					}
                    else
					{
						showNewDialog();
					}
				}
			}
		};
		CheckUpdateParam cp=new CheckUpdateParam();

		ManagerParam mp=new ManagerParam();
		mp.isShowDialog=false;
		mp.isShowMsg=true;
		TongYongManager2 tym=new TongYongManager2(this,icb,cp,mp);
		tym.start();

	}
private ICallBack icb=new ICallBack() {
		
		@Override
		public void back(Object obj) {
			if(obj!=null){
 
				//showUpdataDialog(act,(String)obj);
			}
			else
			{
				showNewDialog();
			}
			
		}
	};
	
	
	//鼓励一下
		private void shareToMarket(){
			try {
				String marketUri = "market://details?id=" + act.getPackageName();
				Uri uri = Uri.parse(marketUri);
				Intent intent  = new Intent(Intent.ACTION_VIEW,uri);
				act.startActivity(intent);
			}catch (Exception e) {
				Toast.makeText(act, "搜索不到应用市场,请安装后再尝试", Toast.LENGTH_LONG).show();
			}
		}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	protected <T> void startAimActivity(final Class<T> pActClassName) {
		Intent _Intent = new Intent();
		_Intent.setClass(act, pActClassName);
		act.startActivity(_Intent);
	}
	private static Dialog upgrade_version_dialog,newest_version_dialog;
   public static void showUpdataDialog(Activity act1,final String downloadUrl,boolean isQiangZhi){
		act=act1;
		if(upgrade_version_dialog!=null&&upgrade_version_dialog.isShowing())
		{
			return;
		}
		upgrade_version_dialog=Utils.createSimpleDialog(act,
				act.getString(R.string.upgrade_version),act.getString(R.string.update),new BeikBankDialogListener() {

			@Override
			public void onRightBtnClick() {

				downloadApk(downloadUrl);
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
				upgrade_version_dialog.dismiss();
			}

			@Override
			public void onCancel() {
				// TODO Auto-generated method stub

			}
		});
	   if(isQiangZhi)
	   {

		   upgrade_version_dialog=Utils.createSimpleDialog2(act,
				   act.getString(R.string.upgrade_version),act.getString(R.string.update),new BeikBankDialogListener() {

					   @Override
					   public void onRightBtnClick() {

						   downloadApk(downloadUrl);
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
						   upgrade_version_dialog.dismiss();
					   }

					   @Override
					   public void onCancel() {
						   // TODO Auto-generated method stub

					   }
				   });
	    }
		upgrade_version_dialog.show();
	}
	
	public void showNewDialog(){
		newest_version_dialog=Utils.createRemindDialog(act,
				act.getString(R.string.newest_version),new BeikBankDialogListener() {

			@Override
			public void onRightBtnClick() {
				// TODO Auto-generated method stub
				newest_version_dialog.dismiss();
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
				newest_version_dialog.dismiss();
			}

			@Override
			public void onCancel() {
				// TODO Auto-generated method stub

			}
		});

		newest_version_dialog.show();
	}
	static ProgressDialog pd;
	public static void downloadApk(final String url) {  
	    //进度条对话框  
		pd = new ProgressDialog(act);  
		pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);  
		pd.setMessage("正在下载更新");  
		pd.show();  
		new Thread(){  
			@Override  
			public void run() {  
				try {  
					File file = getFileFromServer(url, pd);  
					sleep(3000);  
					installApk(file);
					Message msg = new Message();  
					msg.what =HandlerBase.success1;  
					handler.sendMessage(msg);  
					//pd.dismiss(); //结束掉进度条对话框  
				} catch (Exception e) {  
					Message msg = new Message();  
					msg.what =HandlerBase.error1;  
					handler.sendMessage(msg);  
					e.printStackTrace();
					LogHandler.writeLogFromException("gengduoactivity", e);
				}
			
			}}.start();  
	}  

	public static void installApk(File file) {  
	    Intent intent = new Intent();  
	    //执行动作  
	    intent.setAction(Intent.ACTION_VIEW);  
	    //执行的数据类型  
	    intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");  
	    act.startActivity(intent);  
	}  
	
	public static Handler handler = new Handler(){  
	      
	    @Override  
	    public void handleMessage(Message msg) {  
	        // TODO Auto-generated method stub 
	    	if(pd!=null)
	    	{
	    		pd.dismiss();
	    	}
	        super.handleMessage(msg);  
	        switch (msg.what) {  
//	        case UPDATA_CLIENT:  
//	            //对话框通知用户升级程序  
//	        	String downloadUrl=(String)msg.obj;
//	            showUpdataDialog(downloadUrl);  
//	            break;  
//	        case NEWEST_CLIENT:  
//	            //对话框通知用户升级程序   
//	            showNewDialog();  
//	            break; 
	        case HandlerBase.error1:  
	            //下载apk失败  
	            Toast.makeText(act, "下载新版本失败", Toast.LENGTH_SHORT).show();  
	            break;   
	        case HandlerBase.success1:
	        	break;
	        }  
	    }  
	};
	public static File getFileFromServer(String path, ProgressDialog pd) throws Exception{
		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
			URL url = new URL(path);
			HttpURLConnection conn =  (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(5000);
			//获取到文件的大小 
			pd.setMax(conn.getContentLength());
			InputStream is = conn.getInputStream();
			File file = new File(Environment.getExternalStorageDirectory(), "zhuanla_android.apk");
			FileOutputStream fos = new FileOutputStream(file);
			BufferedInputStream bis = new BufferedInputStream(is);
			byte[] buffer = new byte[1024];
			int len ;
			int total=0;
			while((len =bis.read(buffer))!=-1){
				fos.write(buffer, 0, len);
				total+= len;
				//获取当前下载量
				pd.setProgress(total);
			}
			fos.close();
			bis.close();
			is.close();
			return file;
		}
		else{
			return null;
		}
	}
}
