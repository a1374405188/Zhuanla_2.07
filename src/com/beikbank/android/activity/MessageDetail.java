package com.beikbank.android.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.beikbank.android.data.Message;
import com.beikbank.android.data2.XiaoXiGet;
import com.beikbank.android.utils2.StateBarColor;

import comc.beikbank.android.R;

/**
 * @author yuguohe
 * <p>
 * email:1374405188@qq.com
 * </p>
 *2015-2-11
 * 消息详细内容
 */
public class MessageDetail extends BaseActivity1 implements OnClickListener
 {  
	//主标题
	TextView title1;
	//消息标题
	TextView title2;
	//消息的发布者
	TextView tv;
	//消息时间
	TextView time;
	//消息内容
	TextView text;
	LinearLayout linear_left;
	XiaoXiGet msg;
	String type;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.message_detail_activity);
		StateBarColor.init(this,0xffffffff);
		msg=(XiaoXiGet) getIntent().getSerializableExtra("msg");
		type=getIntent().getStringExtra("type");
		initView();
	}
	private void initView()
	{  
		linear_left=(LinearLayout) findViewById(R.id.linear_left);
		title1=(TextView) findViewById(R.id.titleTv);
		title2=(TextView) findViewById(R.id.title);
		tv=(TextView) findViewById(R.id.context);
		time=(TextView) findViewById(R.id.time);
		text=(TextView) findViewById(R.id.text);
		
		if("1".equals(type))
		{
			title1.setText("通知");
		}
		else
		{
			title1.setText("消息");
		}
		title2.setText(msg.msg_title);
		tv.setText(msg.msg_content);
		time.setText(msg.send_time);
		text.setText(msg.msg_content);
		
		linear_left.setOnClickListener(this);
	}
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
}
