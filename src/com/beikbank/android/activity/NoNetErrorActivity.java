package com.beikbank.android.activity;

import com.beikbank.android.utils.DialogManager;
import coma.beikbank.android.R;



import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 
 * @author Administrator
 *错误提示
 */
public class NoNetErrorActivity extends BaseActivity2 implements OnClickListener
{
    
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_error);
	   TextView	titleTv = (TextView) findViewById(R.id.titleTv);
	   titleTv.setText("错误提示");
	   
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
	
}
