package com.beikbank.android.activity;

import java.util.Set;
import java.util.TreeSet;

import javax.crypto.spec.IvParameterSpec;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;
import com.beikbank.android.activity.help.NoticeHlep;
import com.beikbank.android.conmon.DisplayManger;
import com.beikbank.android.conmon.SystemConfig;
import com.beikbank.android.data.FundInfo;
import com.beikbank.android.data.Notice;
import com.beikbank.android.data.Notice_data;
import com.beikbank.android.data.Win;
import com.beikbank.android.data.Win_data;
import com.beikbank.android.dataparam.ShoushiIsSetParam;
import com.beikbank.android.dataparam.WinParam;
import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.gif.CustomGifView;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.ImageUrl;
import com.beikbank.android.net.ManagerParam;
import com.beikbank.android.net.impl.CheckUpdateManager;
import com.beikbank.android.net.impl.NoticeManager;
import com.beikbank.android.net.impl.TongYongManager;
import com.beikbank.android.net.impl2.MessageManager2;
import com.beikbank.android.net.impl2.ZhiCanManager;
import com.beikbank.android.utils.BeikBankConstant;
import com.beikbank.android.utils.BeikBankDialogListener;
import com.beikbank.android.utils.Utils;
import com.beikbank.android.utils2.StateBarColor;
import com.beikbank.android.webwiew.WebWiewInface;
import com.beikbank.android.webwiew.Wv;
import com.beikbank.android.widget.FirstLoginWindows;
import com.beikbank.android.widget2.PageView1;
import com.beikbank.android.widget2.PageView2;
import com.beikbank.android.widget2.PageView3;
import com.beikbank.android.widget2.Pview2;
import coma.beikbank.android.R;




import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ProgressBar;
import android.widget.TextView;
/**
 * 
 * @author Administrator
 *新程序主界面
 */
public class HomeActivity3 extends BaseActivity1 implements View.OnClickListener
{   
	public static HomeActivity3 ha;
	/**
	 * 当前选择的是什么1 精选2活期定期3资产
	 */
	public static int index=1;
	/**
	 * 是否要刷新消息
	 */
	public static boolean isMsg;
	/**
	 * 获得消息，通知记数后更新视图
	 */
	public  int msgCount;
	TextView title;
    /**
     * return
     */
    LinearLayout ll1;
    /**
     * 中间部分父布局
     */
    LinearLayout center;
    /**
     * 精选，理财，资产
     */
    LinearLayout ll11,ll12,ll13;
    public PageView1 pv1;
    public PageView2 pv2;
    public PageView3 pv3;
    public FundInfo fi;
    public ZhiCanManager zc;
    private Activity act;
    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_home3);
		StateBarColor.init(this,0xffe22c2f);
		
		PushManager.startWork(getApplicationContext(),PushConstants.LOGIN_TYPE_API_KEY,"qalIsXIyP2LATIG1zFnGGaxX");
		 ha=this;
		 act=this;
	    initView();
	   
	    
	    boolean one_open=BeikBankApplication.mSharedPref.getSharePrefBoolean(
				BeikBankConstant.one_open, false);
		if(one_open)
		{   
			BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.one_open,false);
			lock();
			
		}
		initData();
		tuisong();
		
		//检查更新
		
		Handler handler=new Handler();
		handler.postAtTime(new Runnable() {
			
			@Override
			public void run() {
				
				new CheckUpdateManager(act,icb_update).start();
				new MessageManager2(act, icb_message).start();
			}
		},5000);
	}
    ICallBack icb_message=new ICallBack() {
		
		@Override
		public void back(Object obj) {
			if(obj!=null)
			{   
				
			    ImageView iv1=(ImageView) pv3.findViewById(R.id.iv_xiaoxi2);
				iv1.setVisibility(View.VISIBLE);
				
				ImageView iv2=(ImageView) pv1.findViewById(R.id.iv_xiaoxi2);
				iv2.setVisibility(View.VISIBLE);
				
				ImageView iv3=(ImageView) findViewById(R.id.iv_xiaoxi3);
				iv3.setVisibility(View.VISIBLE);
			}
			else
			{
				ImageView iv1=(ImageView) pv3.findViewById(R.id.iv_xiaoxi2);
				iv1.setVisibility(View.GONE);
				
				ImageView iv2=(ImageView) pv1.findViewById(R.id.iv_xiaoxi2);
				iv2.setVisibility(View.GONE);
			}
			
		}
	};
    ICallBack icb_update=new ICallBack() {
		
		@Override
		public void back(Object obj) {
			if(obj!=null){
				 
				GengDuoActivity.showUpdataDialog(act,(String)obj);
			}
			
		}
	};
	/**
	 * 设置推送信息
	 */
	private void tuisong()
	{
		  //极光推送
        boolean isnet=isNetworkConnected(this);
        if(isnet)
        {
            registerMessageReceiver();
		    jginit();
		    String s = getRegistrationID(this);
		    if (s != null) 
		    {
			   Log.d("register_id", s);
		    }
		    String id=BeikBankApplication.getUserCode();
		    if(id==null)
		    {
		    	return;
		    }
		    Set<String> list=new TreeSet<String>();
		    list.add(id);
            JPushInterface.setAlias(this,id,new TagAliasCallback() {
				
				@Override
				public void gotResult(int arg0, String arg1, Set<String> arg2) {
					if(arg0==0)
					{
					   Log.d("alias","成功");
					}
					else
					{
						Log.d("alias","失败");
					}
					
				}
			});
        }
	}
	
	
    private void initData()
    {    
    	
    	
    	
    	ICallBack icb6=new ICallBack() {
			
			@Override
			public void back(Object obj) {
				if(obj!=null)
				{  
					Win_data wd=(Win_data) obj;
					final Win w=wd.data;
					BeikBankApplication.mSharedPref.putSharePrefString(BeikBankConstant.is_win,"");
					final View view=FirstLoginWindows.addView2(act,R.layout.first_win_msg);
					ImageView iv=(ImageView) view.findViewById(R.id.iv);
					ImageView iv0=(ImageView) view.findViewById(R.id.iv0);
					int width=DisplayManger.getWidth(act);
					LayoutParams lp=new LayoutParams(width/5*4,width/5*4);
					iv.setLayoutParams(lp);
					ImageUrl iu=new ImageUrl(iv,w.icon);
					iu.start();
					iv0.setOnClickListener(new OnClickListener() 
					{
						
						@Override
						public void onClick(View v) 
						{
							 act.getWindowManager().removeView(view);
						}
					});
					iv.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							
						  if(w.linkUrl!=null&&!w.linkUrl.equals(""))
						  {   
							  
							  Intent intent=new Intent(act,HuodongActivity2.class);
							  intent.putExtra("url",w.linkUrl);
							  intent.putExtra("title",w.title);
							  startActivity(intent);
							  act.getWindowManager().removeView(view);
						  }
						  
						}
					});
					
				}
				
			}
		};
		String win=BeikBankApplication.mSharedPref.getSharePrefString(BeikBankConstant.is_win);
		if(win!=null&&!win.equals(""))
		{
		  WinParam wp=new WinParam();
		  ManagerParam mp=new ManagerParam();
		  mp.isShowDialog=false;
		  mp.isShowMsg=false;
		  TongYongManager tym=new TongYongManager(this,icb6,wp,mp);
		  tym.start();
		}
    	
    }
    
    
    private void initView()
    {   
    	center=(LinearLayout) findViewById(R.id.center);
    	pv1=new PageView1(this);
    	pv3=new PageView3(this);
    	LayoutParams lp=new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
    	pv3.setLayoutParams(lp);
    	pv1.setLayoutParams(lp);
    	
    	center.removeAllViews();
    	center.addView(pv1);
    	ll11=(LinearLayout) findViewById(R.id.ll11);
    	ll12=(LinearLayout) findViewById(R.id.ll12);
    	ll13=(LinearLayout) findViewById(R.id.ll13);
    	ll11.setOnClickListener(this);
    	ll12.setOnClickListener(this);
    	ll13.setOnClickListener(this);
    	select(index);
    	
    	
    }
    /**
     * 第一次进入时候引导
     * @param index
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB) 
    private void first(int index)
    {
    	if(index==1)
    	{
    		boolean b=BeikBankApplication.mSharedPref.getSharePrefBoolean(BeikBankConstant.first_enter_jinxuan,true);
			if(b)
			{
			   final View view=FirstLoginWindows.addView(this,R.layout.first_enter_jinxuan);
			   BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.first_enter_jinxuan,false);
			   CustomGifView cgf=(CustomGifView) view.findViewById(R.id.gifview);
			   cgf.setGifResId(R.drawable.img_gif1);
			   int w=cgf.getWidth();
				 if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) 
				 {
				        cgf.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
				 }
				 StateBarColor.init(this,0xff701616);
				 view.setOnClickListener(new OnClickListener() 
			        {
						
						@Override
						public void onClick(View v) {
							getWindowManager().removeView(view);
						
							StateBarColor.init(ha,0xffe22c2f);
						}
					});
			   
			}
    	}
    	else if(index==2)
    	{
    		boolean b=BeikBankApplication.mSharedPref.getSharePrefBoolean(BeikBankConstant.first_enter_chanping,true);
			if(b)
			{
			   final View view=FirstLoginWindows.addView(this,R.layout.first_enter_jinxuan);
			   BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.first_enter_chanping,false);
			   CustomGifView cgf=(CustomGifView) view.findViewById(R.id.gifview);
			   cgf.setGifResId(R.drawable.img_gif2);
				 if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) 
				 {
				        cgf.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
				 }
				 StateBarColor.init(this,0xff701616);
				 view.setOnClickListener(new OnClickListener() 
			        {
						
						@Override
						public void onClick(View v) {
							getWindowManager().removeView(view);
						
							StateBarColor.init(ha,0xffe22c2f);
						}
					});
			}
    	}
    }
    /**
     * 选择当前是第几项
     * @param index 1精选 
     */
  public void select(int index)
    {   
    	
	    ImageView iv1=(ImageView) pv3.findViewById(R.id.iv_xiaoxi2);
		
		ImageView iv3=(ImageView) findViewById(R.id.iv_xiaoxi3);
    	
    	
    	if(index==1)
    	{   
    		
    		//pv1=new PageView1(this);
			//LayoutParams lp=new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
	    	//pv1.setLayoutParams(lp);
			center.removeAllViews();
			center.addView(pv1);
			setBottomColor(1);
			this.index=index;
			
			first(1);
			
			
			//消息红点
			
			if(iv1.getVisibility()==View.VISIBLE)
			{
				iv3.setVisibility(View.VISIBLE);
			}
			else
			{
				iv3.setVisibility(View.GONE);
			}
			
    	}
    	else if(index==2)
    	{   
    		
    		setBottomColor(2);
    		
    		if(pv2==null)
    		{
    			pv2=new PageView2(this);
            	LayoutParams lp=new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
    	    	pv2.setLayoutParams(lp);
    		}
        	
        	center.removeAllViews();
			center.addView(pv2);
			this.index=index;
			first(2);
			if(iv1.getVisibility()==View.VISIBLE)
			{
				iv3.setVisibility(View.VISIBLE);
			}
			else
			{
				iv3.setVisibility(View.GONE);
			}
    	}
    	else
    	{   
    		boolean do_success=BeikBankApplication.mSharedPref.getSharePrefBoolean(BeikBankConstant.DO_SUCCESS,false);
    		this.index=index;
			if(!do_success)
			{
				Intent intent=new Intent(this,LoginRegActivity.class);
				this.startActivity(intent);
			}
			else
			{
				if(pv3==null)
				{
					pv3=new PageView3(this);
			    	LayoutParams lp=new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
			    	pv3.setLayoutParams(lp);
				}
				
		    	setBottomColor(3);
	      	    center.removeAllViews();
				center.addView(pv3);
				
			}
			iv3.setVisibility(View.GONE);
			
    	}
    	
    }
	@Override
	public void onClick(View v) {
		boolean do_success=BeikBankApplication.mSharedPref.getSharePrefBoolean(BeikBankConstant.DO_SUCCESS,false);
		switch (v.getId()) {
		case R.id.ll11:
			select(1);
			break;
        case R.id.ll12:
        	select(2);
			break;
        case R.id.ll13:
        	select(3);
			break;
		}
	}
	/**
	 * 设置底部颜色
	 * @param index 1精选 2理财 3资产
	 */
    private void setBottomColor(int index)
    {   
    	ImageView iv11=(ImageView) findViewById(R.id.iv11);
    	ImageView iv12=(ImageView) findViewById(R.id.iv12);
    	ImageView iv13=(ImageView) findViewById(R.id.iv13);
    	
    	TextView tv11=(TextView) findViewById(R.id.tvl11);
    	TextView tv12=(TextView) findViewById(R.id.tvl12);
    	TextView tv13=(TextView) findViewById(R.id.tvl13);
    	
    	iv11.setImageResource(R.drawable.home3_img2);
    	iv12.setImageResource(R.drawable.home3_img3);
    	iv13.setImageResource(R.drawable.home3_img4);
    	
    	tv11.setTextColor(0xff888888);
    	tv12.setTextColor(0xff888888);
    	tv13.setTextColor(0xff888888);
    	if(index==1)
    	{
    		tv11.setTextColor(0xffe4393c);
    		iv11.setImageResource(R.drawable.home3_img5);
    	}
    	else if(index==2)
    	{
    		tv12.setTextColor(0xffe4393c);
    		iv12.setImageResource(R.drawable.home3_img6);
    	}
    	else
    	{   
    		
    		tv13.setTextColor(0xffe4393c);
    		iv13.setImageResource(R.drawable.home3_img7);
    	}
    }
    
    
	@Override
	protected void onNewIntent(Intent intent) {
		// TODO Auto-generated method stub
		super.onNewIntent(intent);
		setIntent(intent);
		int index=getIntent().getIntExtra("indexgo",-1);
		if(index==0)
		{   
			this.index=2;
			select(this.index);
			pv2.setItem(index);
		}
		else if(index==1)
		{   
			this.index=2;
			select(this.index);
			pv2.setItem(index);
		}
		else if(index==2)
		{   
			this.index=2;
			select(this.index);
			pv2.setItem(index);
		}
	}


	@Override
	protected void onRestart() {
		
		super.onRestart();
		
		select(index);
		boolean b=BeikBankApplication.mSharedPref.getSharePrefBoolean(BeikBankConstant.re_home,true);
		if(b)
		{   
			initView();
			initData();
			BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.re_home,false);
		}
		
		
		if(isMsg)
		{   
			isMsg=false;
			new MessageManager2(act, icb_message).start();
		}
		
		
    
	}
	
	

	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		pv1.init2();
		pv3.init2();
		
		
		
	}

	@Override
	protected void onDestroy() {
		
		super.onDestroy();
		
	}
	//public PopupWindow window;
	private long exitTime = 0;
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
			if(window!=null&&window.isShowing())
			{
				window.dismiss();
			}
			
			else if((System.currentTimeMillis()-exitTime) > 2000){  
				Toast.makeText(HomeActivity3.this, getString(R.string.second_time_exit), Toast.LENGTH_SHORT).show();                                
				exitTime = System.currentTimeMillis();   
			} else {
				finish();
				//System.exit(0);
			}
			return true;
	}
		return super.onKeyDown(keyCode, event);
	}
    
	
}
