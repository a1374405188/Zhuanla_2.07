package com.beikbank.android.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.beikbank.android.adapter.HelpCenterAdapter;
import com.beikbank.android.adapter.HelpCenterAdapter3;
import com.beikbank.android.conmon.MessageManger;
import com.beikbank.android.data.HelpAndSafety;
import com.beikbank.android.data.HelpAndSafety_data;
import com.beikbank.android.data.HelpInfo;
import com.beikbank.android.data.HelpInfo3;
import com.beikbank.android.data.HelpInfo3_data;
import com.beikbank.android.dataparam.HelpAndSafetyParam;
import com.beikbank.android.dataparam.HelpCenterParam2;
import com.beikbank.android.exception.LogHandler;
import com.beikbank.android.net.HandlerBase;
import com.beikbank.android.net.IBusiness;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.NetServicesFactory;
import com.beikbank.android.net.impl.HelpCenterManager;
import com.beikbank.android.net.impl.TongYongManager;
import com.beikbank.android.utils.Utils;
import com.beikbank.android.utils2.StateBarColor;

import comc.beikbank.android.R;

//帮助中心二级界面
public class HelpCenterActivity3 extends BaseActivity implements OnClickListener{
    private Activity act=this;
    private String TAG="HelpCenterActivity";
	private TextView titleTv;
	//private ListView listview_help_center;
	private ExpandableListView listview_help_center;
	private HelpCenterAdapter3 adapter;
	private LinearLayout linear_left;
	private ArrayList<HelpInfo> list=new ArrayList<HelpInfo>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_help_center3);
		StateBarColor.init(this,0xffffffff);
		initView();

//		HelpCenterManager hcm=new HelpCenterManager(act, icb);
//		hcm.init("1");
//		hcm.start();
		initData();
	}
	public void initView(){
		//initData();
		titleTv = (TextView) findViewById(R.id.titleTv);
		String s1=getIntent().getStringExtra("index1");
		titleTv.setText(s1);
		
		linear_left = (LinearLayout) findViewById(R.id.linear_left);
		linear_left.setVisibility(View.VISIBLE);
		linear_left.setOnClickListener(this);
		
		listview_help_center=(ExpandableListView) findViewById(R.id.listview_help_center);
		//adapter=new HelpCenterAdapter(this,list);
		//listview_help_center.setAdapter(adapter);
		listview_help_center.setGroupIndicator(null);
	}
//	ICallBack icb=new ICallBack() {
//		
//		@Override
//		public void back(Object obj) {
//			HelpAndSafety_data hsd=(HelpAndSafety_data) obj;
//			ArrayList<HelpAndSafety> list2=hsd.data;
//			for(int i=0;i<list2.size();i++)
//			{   
//				HelpAndSafety hs=list2.get(i);
//				HelpInfo hi=new HelpInfo();
//				hi.setProblem(hs.title);
//				hi.setAnswer(hs.content);
//				list.add(hi);
//			}
//			adapter=new HelpCenterAdapter(act,list,listview_help_center);
//			listview_help_center.setAdapter(adapter);
//		}
//	};
	private ICallBack icb2=new ICallBack() {
		
		@Override
		public void back(Object obj) {
			if(obj!=null)
			{
				HelpInfo3_data hd=(HelpInfo3_data) obj;
				
				ArrayList<HelpInfo3> list2=hd.data;
				for(int i=0;i<list2.size();i++)
				{   
					HelpInfo3 hi3=list2.get(i);
					HelpInfo hi=new HelpInfo();
					hi.setProblem(hi3.title);
					hi.setAnswer(hi3.content);
					list.add(hi);
				}
				
				adapter=new HelpCenterAdapter3(act,list,listview_help_center);
				listview_help_center.setAdapter(adapter);

			}
		}
	};
	private void initData()
	{   
		String type=(String) getIntent().getSerializableExtra("index");
		HelpCenterParam2 hp=new HelpCenterParam2();
		hp.type=type;
		TongYongManager tym=new TongYongManager(act,icb2,hp);
		tym.start();
	}

	


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.linear_left:
			finish();
			break;
		}
	}


	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
	
}
