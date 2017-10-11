package com.beikbank.android.activity;

import java.util.ArrayList;
import java.util.List;

import com.beikbank.android.adapter.ZhiChanAdapter;
import com.beikbank.android.data.UserCapital2;
import com.beikbank.android.data.UserCapitalInfo2;
import com.beikbank.android.utils2.StateBarColor;

import comc.beikbank.android.R;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

/**
 * @author yuguohe
 * <p>
 * email:1374405188@qq.com
 * </p>
 *2015-3-29
 * 定期资产详情
 */
public class DingqiZhichanDetial extends BaseActivity1 implements View.OnClickListener{
   ZhiChanAdapter za;
   LinearLayout ll0;
   ListView lv;
   ArrayList<UserCapitalInfo2> list;
   public final static String index="index";
   
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dingqi_detail);
		StateBarColor.init(this,0xffffffff);
		initview();
		list=(ArrayList<UserCapitalInfo2>) getIntent().getSerializableExtra(index);
		if(list==null||list.size()==0)
		{
			lv.setVisibility(View.GONE);
		}
		else
		{
			addData();
		}
		
	}
	private void initview()
	{
		ll0=(LinearLayout) findViewById(R.id.linear_left);
		lv=(ListView) findViewById(R.id.lv);
		
		ll0.setOnClickListener(this);
		TextView title=(TextView) findViewById(R.id.titleTv);
		title.setText("定期明细");
	}
	private void addData()
	{   
		
		ZhiChanAdapter za=new  ZhiChanAdapter(list,this);
		lv.setAdapter(za);
	}
	@Override
	public void onClick(View v) {
		 switch (v.getId())
	      {
	         case R.id.linear_left:
	             finish();
	          break;
	      }
		
	}
   
}
