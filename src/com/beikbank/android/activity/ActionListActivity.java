package com.beikbank.android.activity;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.beikbank.android.adapter.ActionAdapter;
import com.beikbank.android.data.Action;
import com.beikbank.android.data.Actionlist;
import com.beikbank.android.data.Actionlist_data;
import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.impl.ActionListManager;
import com.beikbank.android.utils2.StateBarColor;

import comc.beikbank.android.R;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

/**
 * @author yuguohe
 * <p>
 * email:1374405188@qq.com
 * </p>
 *2015-2-28
 * 活动列表
 */
public class ActionListActivity extends BaseActivity1 implements OnClickListener{
    Activity act=this;
    LinearLayout linear_left;
    TextView tv_title;
    ListView lv;
    //0点击活动跳转，1点击广告页跳转
    int index;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_action_list);
		StateBarColor.init(this,0xffffffff);
	    initView();
	    initData();
	    new ActionListManager(act, icb).start();
	   
	}
    private void initView()
    {
    	linear_left=(LinearLayout) findViewById(R.id.linear_left);
    	tv_title=(TextView) findViewById(R.id.titleTv);
    	tv_title.setText("活动");
    	lv=(ListView) findViewById(R.id.lv);
    	linear_left.setOnClickListener(this);
    }
    Action action;
    private void initData()
    {   
    	Intent intent=getIntent();
    	index=intent.getIntExtra("index",0);
 
    }
    ICallBack icb=new ICallBack() {
		
		@Override
		public void back(Object obj) {
			if(obj!=null)
			{
				Actionlist_data ad=(Actionlist_data) obj;
				List<Actionlist> list=ad.data;
				ActionAdapter aa=new ActionAdapter(list, act);
		    	lv.setAdapter(aa);
			}
			
		}
	};
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.linear_left:
			if(index==0)
			{
				finish();
			}
			else
			{
				Intent intent=new Intent(this,HomeActivity3.class);
				startActivity(intent);
				finish();
			}
			break;

		default:
			break;
		}
		
	}
}
