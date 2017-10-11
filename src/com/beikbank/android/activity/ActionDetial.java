package com.beikbank.android.activity;

import java.net.URL;

import com.beikbank.android.activity.ActionActivity.sleeptime;
import com.beikbank.android.data.ActionInfo;
import com.beikbank.android.data.ActionInfo_data;
import com.beikbank.android.dataparam.ActionInfoParam;
import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.media.AsynImageLoader;
import com.beikbank.android.media.ImageManager;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.impl.ActionInfoManager;
import com.beikbank.android.utils.DensityUtil;
import com.beikbank.android.utils2.StateBarColor;

import comc.beikbank.android.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * @author yuguohe
 * <p>
 * email:1374405188@qq.com
 * </p>
 *2015-3-1
 * 活动详情
 */
public class ActionDetial extends BaseActivity1 implements OnClickListener
{
    TextView title;
    LinearLayout ll_left;
    ImageView iv;
    TextView tv;
    String no;
    ActionInfo ai;
    Activity act=this;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_action_detail);
		StateBarColor.init(this,0xffffffff);
		initView();
		initData();
		ActionInfoParam aip=new ActionInfoParam();
		aip.activityNO=no;
		new ActionInfoManager(this, icb, aip).start();
	}
    private void initView()
    {
    	ll_left=(LinearLayout) findViewById(R.id.linear_left);
    	ll_left.setOnClickListener(this);
    	
    	title=(TextView) findViewById(R.id.titleTv);
    	iv=(ImageView) findViewById(R.id.iv_iv);
    	tv=(TextView) findViewById(R.id.tv_tv);
    }
    private void initData()
    {
    	Intent intent=getIntent();
    	no=intent.getStringExtra("no");
    }
    ICallBack icb=new ICallBack() {
		
		@Override
		public void back(Object obj) {
			if(obj!=null)
			{
				ActionInfo_data ad=(ActionInfo_data) obj;
				ai=ad.data;
				title.setText(ai.tittle);
				//AsynImageLoader ail=new AsynImageLoader();
				//ail.showImageAsyn(iv,ai.thumb,R.drawable.load_net_img_def);
				if("2".equals(ai.type))
				{
					new ImageLoad().start();
				}
				else
				{
					iv.setVisibility(View.GONE);
					tv.setVisibility(View.VISIBLE);
					tv.setText(ai.content);
				}
			}
		}
	};
	@Override
	public void onClick(View v) {
		
		switch (v.getId()) {
		case R.id.linear_left:
			 finish();
			break;

		default:
			break;
		}
	}

	Bitmap bitmap;
    class ImageLoad extends Thread
    {

		@Override
		public void run() {
			URL url;
			try {
				url = new URL(ai.thumb);
			    bitmap=BitmapFactory.decodeStream(url.openStream());
			    //屏幕宽度
			    int dwidth=BeikBankApplication.getWidth(act);
			    //屏幕高度
			    int dheight=BeikBankApplication.getHeight(act);
			    //状态栏高度
			    int sheight=BeikBankApplication.getStaticHeight(act);
			    //标题栏的高度
			    DensityUtil du=new DensityUtil(act);
			    int title=du.dip2px(48);
			    //图片的最大高度
			    int maxHeight=dheight-sheight-title;
			    bitmap=ImageManager.getBItmap(bitmap, dwidth, maxHeight);
				Message msg=new Message();
				msg.what=1;
				handler.sendMessage(msg);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
    	
    }
    Handler handler=new Handler()
    {

		@Override
		public void handleMessage(Message msg) {
		    if(msg.what==1)
		    {
		    	if(bitmap!=null)
		    	{
		    		iv.setImageBitmap(bitmap);
		    	}
		    	
		    }
		   
		}
    	
    };
}
