//package com.beikbank.android.widget;
//
//import com.beikbank.android.R;
//import com.beikbank.android.activity.HomeActivity2;
//import com.beikbank.android.net.HandlerBase;
//
//import android.content.Context;
//import android.util.AttributeSet;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
///**
// * @author yuguohe
// * <p>
// * email:1374405188@qq.com
// * </p>
// *2015-1-23
// * 网络没有连接页面
// */
//public class Page3 extends LinearLayout implements OnClickListener{
//	HomeActivity2 act;
//	TextView tv;
//	public Page3(Context context) {
//		super(context);
//		act=(HomeActivity2) context;
//		init();
//	}
//	public Page3(Context context, AttributeSet attrs) {
//		super(context, attrs);
//		act=(HomeActivity2) context;
//		init();
//	}
//	//初始化控件
//    private void init()
//    {
//    	LayoutInflater li=act.getLayoutInflater();
//		LinearLayout ll=new LinearLayout(act);
//		View view = li.inflate(R.layout.activity_no_net,ll,false);
//		tv=(TextView) view.findViewById(R.id.tv_tv);
//		addView(view);
//		tv.setOnClickListener(this);
//    }
//	@Override
//	public void onClick(View v) {
//		if(v.getId()==R.id.tv_tv)
//		{
//			boolean isnet=act.isNetworkConnected(act);
//			if(isnet)
//			{
//				act.vpl.setLoginInfo();
//				act.vpl.page2.refresh();
//			}
//			else
//			{
//				HandlerBase.showMsg(act,act.getString(R.string.error_5),0);
//			}
//		}
//		
//	}
//    
//}
