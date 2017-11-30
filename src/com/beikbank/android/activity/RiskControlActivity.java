package com.beikbank.android.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.beikbank.android.conmon.MessageManger;
import com.beikbank.android.data.HelpAndSafety;
import com.beikbank.android.data.HelpAndSafety_data;
import com.beikbank.android.data.HelpInfo;
import com.beikbank.android.dataparam.HelpAndSafetyParam;
import com.beikbank.android.exception.LogHandler;
import com.beikbank.android.net.HandlerBase;
import com.beikbank.android.net.IBusiness;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.NetServicesFactory;
import com.beikbank.android.net.impl.HelpCenterManager;
import com.beikbank.android.utils2.StateBarColor;
import coma.beikbank.android.R;



//产品特征
public class RiskControlActivity extends BaseActivity implements OnClickListener{
    private Activity act=this;
    private final String TAG="RiskControlActivity";
	private TextView titleTv;
	private LinearLayout linear_left;
    TextView tv1,tv2,tv3;
    ArrayList<HelpAndSafety> list;
    ListView lv;
    public static final String index="index";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String index1=getIntent().getStringExtra(index);
		
		setContentView(R.layout.activity_riskcontrol2);
		StateBarColor.init(this,0xffffffff);
		initView();
        HelpCenterManager hcm=new HelpCenterManager(act, icb);
        if("".equals(index1) || index1==null)
        {
        	 hcm.init("2");
        }
        else
        {
        	 hcm.init("3");
        }
        hcm.start();
	}
	public void initView(){
		titleTv = (TextView) findViewById(R.id.titleTv);
		titleTv.setText("产品特征");
		linear_left = (LinearLayout) findViewById(R.id.linear_left);
		linear_left.setVisibility(View.VISIBLE);
		linear_left.setOnClickListener(this);
		lv=(ListView) findViewById(R.id.lv);
		
		tv1=(TextView) findViewById(R.id.tv_tv1);
		tv2=(TextView) findViewById(R.id.tv_tv2);
		tv3=(TextView) findViewById(R.id.tv_tv3);
	

	}
   ICallBack icb=new ICallBack() {
	
	@Override
	public void back(Object obj) {
		if(obj!=null)
		{
			HelpAndSafety_data has=(HelpAndSafety_data) obj;
			list=has.data;
			Risk risk=new Risk(list);
			lv.setAdapter(risk);
		}
		
	}
};
   private void initData()
   {
	   Risk risk=new Risk(list);
	   lv.setAdapter(risk);
   }

	@Override
	public void onClick(View v) {
	
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
	class Risk extends BaseAdapter
	{  
		List<HelpAndSafety> list;
        public Risk(List<HelpAndSafety> obj)
        {
        	list=obj;
        }
		@Override
		public int getCount() 
		{
			return list.size();
		}

		@Override
		public Object getItem(int arg0) {
			return null;
		}

		@Override
		public long getItemId(int arg0) {
			return 0;
		}

		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) {
			//LinearLayout ll=new LinearLayout(act);
			View view=getLayoutInflater().inflate(R.layout.activity_riskcontrol_item,null);
			TextView tv1=(TextView) view.findViewById(R.id.tv1);
			TextView tv2=(TextView) view.findViewById(R.id.tv2);
			HelpAndSafety hs=list.get(arg0);
			tv1.setText(hs.title);
			tv2.setText(hs.content);
			return view;
		}
		
	}
	
	//---------------------------------------------------------------------------------------------------------------------------
	private Dialog dialog;
	class HelpLoad extends Thread
	{
		@Override
		public void run()
		{    
		  try
		  {
			Message msg=new Message();
			IBusiness im= (IBusiness) NetServicesFactory.getNetServices(act,NetServicesFactory.BUSINESS);
			if(!isNetworkConnected(act))
	 		{  
	 			   msg.what=HandlerBase.nonet;
	 			   handler.sendMessage(msg);
	 			   return ;
	 		}
			
			HelpAndSafetyParam hp=new HelpAndSafetyParam();
			hp.type="2";
			HelpAndSafety_data has=(HelpAndSafety_data) im.helpandSafety(HelpAndSafety_data.class,null,hp);
			if(!HandlerBase.success.equals(has.result))
    		{   
    			String s="服务器数据错误";
		    	if(has!=null&&has.message!=null)
		    	{
		    		s+=has.message;
		    	}
		    	LogHandler.writeLogFromString(TAG,s);
    			 msg.what=HandlerBase.data_error;
    			 msg.obj=has;
	 			 handler.sendMessage(msg);
	 			 return;
    		}
			
			ArrayList<HelpAndSafety> listh=has.data;
			list=new ArrayList<HelpAndSafety>();
			for(int i=0;i<listh.size();i++)
			{
				list.add(listh.get(i));
			}
			
			msg.what=HandlerBase.success1;
			handler.sendMessage(msg);
		  }
		  catch(Exception e)
		  {   
			  Message msg=new Message();
			  msg.what=HandlerBase.error;
			  handler.sendMessage(msg);
			  e.printStackTrace();
			  LogHandler.writeLogFromException(TAG,e);
		  }
		}
	}
	Handler handler=new Handler()
	{

		@Override
		public void handleMessage(Message msg) {
			if(dialog!=null)
			{
				dialog.dismiss();
			}
			switch(msg.what)
			{
			   case HandlerBase.nonet:
				   MessageManger.showMeg(act,getString(R.string.no_net),Toast.LENGTH_SHORT);
				   break;
			   case HandlerBase.data_error:
				   HelpAndSafety_data has=(HelpAndSafety_data)msg.obj;
				   String s=getString(R.string.service_data_error);
				   if(!"".equals(has.message))
				   {
					   s=has.message;
				   }
				   MessageManger.showMeg(act,s,Toast.LENGTH_SHORT);
				   break;
			   case HandlerBase.success1:
				   initData();
				   break;
			}
		}
		
	};

}
