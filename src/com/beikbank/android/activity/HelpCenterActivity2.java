package com.beikbank.android.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.beikbank.android.adapter.HelpCenterAdapter;
import com.beikbank.android.adapter.HelpCenterAdapter2;
import com.beikbank.android.conmon.MessageManger;
import com.beikbank.android.data.HelpAndSafety;
import com.beikbank.android.data.HelpAndSafety_data;
import com.beikbank.android.data.HelpInfo;
import com.beikbank.android.data.HelpInfo2;
import com.beikbank.android.data.HelpInfo2_data;
import com.beikbank.android.dataparam.HelpAndSafetyParam;
import com.beikbank.android.dataparam.HelpCenterParam;
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

//帮助中心v2
public class HelpCenterActivity2 extends BaseActivity implements OnClickListener{
    private Activity act=this;
    private String TAG="HelpCenterActivity";
	private TextView titleTv;
	
	private LinearLayout linear_left;
    private ListView lv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_help_center);
		StateBarColor.init(this,0xffffffff);
		initView();
        initData();
	}
	public void initView(){
		//initData();
		titleTv = (TextView) findViewById(R.id.titleTv);
		titleTv.setText(getString(R.string.help_center));
		
		linear_left = (LinearLayout) findViewById(R.id.linear_left);
		linear_left.setVisibility(View.VISIBLE);
		linear_left.setOnClickListener(this);
		lv=(ListView) findViewById(R.id.listview_help_center);
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				HelpInfo2 h2=list.get(position);
				Intent intent=new Intent(act,HelpCenterActivity3.class);
				intent.putExtra("index",h2.type);
				intent.putExtra("index1",h2.typeName);
				act.startActivity(intent);
				
			}
		});
		
		
	}
	ArrayList<HelpInfo2> list;
	private ICallBack icb=new ICallBack() {
		
		@Override
		public void back(Object obj) {
			if(obj!=null)
			{   
				HelpInfo2_data hd=(HelpInfo2_data) obj;
				list=hd.data;
				HelpCenterAdapter2 hcp=new HelpCenterAdapter2(act, list);
				lv.setAdapter(hcp);
				
			}
			
		}
	};
	public void initData()
	{   
		HelpCenterParam hcp=new HelpCenterParam();
		TongYongManager tym=new TongYongManager(act, icb,hcp);
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
