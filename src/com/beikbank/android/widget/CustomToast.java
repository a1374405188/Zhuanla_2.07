package com.beikbank.android.widget;

import coma.beikbank.android.R;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class CustomToast extends Toast{
	
	public CustomToast(Context context) {  
		super(context);  
	}  
   /**
    * 成功
    * @param context
    * @param text
    * @param duration
    * @return
    */
	public static Toast makeText(Context context, CharSequence text, int duration) {  
		Toast result = new Toast(context);  

		//获取LayoutInflater对象  
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);   
		//由layout文件创建一个View对象  
		View layout = inflater.inflate(R.layout.view_toast, null);  

		//实例化ImageView和TextView对象  
		TextView textView = (TextView) layout.findViewById(R.id.tv1);  

		textView.setText(text);  

		result.setView(layout);  
		result.setGravity(Gravity.CENTER_VERTICAL, 0, 0);  
		result.setDuration(duration);  

		return result;  
	}
	/**
	 * 失败
	 * @param context
	 * @param text
	 * @param duration
	 * @return
	 */
	public static Toast makeText2(Context context, CharSequence text, int duration) {  
		Toast result = new Toast(context);  

		//获取LayoutInflater对象  
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);   
		//由layout文件创建一个View对象  
		View layout = inflater.inflate(R.layout.view_toast, null);  

		//实例化ImageView和TextView对象  
		TextView textView = (TextView) layout.findViewById(R.id.tv1);
		ImageView iv=(ImageView) layout.findViewById(R.id.iv1);
		iv.setBackgroundResource(R.drawable.toast_img2);
		//textView.setTextColor(0xffdd2238);
		textView.setText(text);  

		result.setView(layout);  
		result.setGravity(Gravity.CENTER_VERTICAL, 0, 0);  
		result.setDuration(duration);  

		return result;  
	}  
}
