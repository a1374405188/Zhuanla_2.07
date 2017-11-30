package com.beikbank.android.animation;

import coma.beikbank.android.R;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

 /**
 *copyright 喻国合 
 *email: 1374405188@qq.com
 *2014-12-5
 **/
public class AnimationManager {
	public final static int TOAST_ANIMATION1=1;
	public static Object getAnimation(int index)
	{   
		Object obj=null;
		switch (index) {
		case TOAST_ANIMATION1:
			obj=new ToastAnimationImpl();
			break;

		default:
			break;
		}
		return obj;
	}
	public static void performToast(Activity act,String text)
	{
		ToastAnimation ta=(ToastAnimation) AnimationManager.getAnimation(AnimationManager.TOAST_ANIMATION1);
		View  parentView=act.getLayoutInflater().inflate(R.layout.toast_message,null);
		View tagarView= parentView.findViewById(R.id.toast_ll);
		TextView textView=(TextView) parentView.findViewById(R.id.toast_text);
		textView.setText(text);
		ta.performToast(act, parentView, tagarView);
	}
}
