package com.beikbank.android.activity.help;

import android.app.Activity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;

import com.beikbank.android.utils.AdvancedCountdownTimer;
import coma.beikbank.android.R;



public class YanZhenMaHelp 
{   
	private Activity act;
	/**
	 * 
	 * @param act
	 * @param tv1  得到验证码
	 * @param tv2 验证码倒计时
	 */
	public YanZhenMaHelp(Activity act,TextView tv1,TextView tv2)
	{
		this.act=act;
		this.tv1=tv1;
		this.tv2=tv2;
	}
	public void start()
	{
		timer = new MyCount(TOTALTIME, COUNTDOWNINTERVAL);
		timer.start();
	}
	/**
	 * 发送验证码
	 */
	private TextView tv1;
	/**
	 * 倒数文本
	 */
	private TextView tv2;
	private MyCount timer;
	private final int TOTALTIME=60*1000;//定时60秒
	private final int COUNTDOWNINTERVAL=1000;//间隔1秒
	//实现计时功能的类  
		class MyCount extends AdvancedCountdownTimer {  

			public MyCount(long millisInFuture, long countDownInterval) {  
				super(millisInFuture, countDownInterval);  
				tv2.setVisibility(View.VISIBLE);
				tv1.setVisibility(View.GONE);
				ForegroundColorSpan fcs = new ForegroundColorSpan(act.getResources().getColor(R.color.red1));
				SpannableString ssb = new SpannableString(String.valueOf(TOTALTIME/COUNTDOWNINTERVAL));
				ssb.setSpan(fcs, 0, ssb.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);			
				tv2.setText(ssb);
				tv2.append(act.getString(R.string.identify_code_reload));
			}  

			@Override  
			public void onFinish() {              
				tv2.setVisibility(View.GONE);
				tv1.setVisibility(View.VISIBLE);
				tv1.setText(act.getString(R.string.identify_code_reloading));
				timer=null;
			}     
			//更新剩余时间  
			@Override  
			public void onTick(long millisUntilFinished, int percent) { 
				long time = (millisUntilFinished / 1000);  
				ForegroundColorSpan fcs = new ForegroundColorSpan(act.getResources().getColor(R.color.red1));
				SpannableString ssb = new SpannableString(String.valueOf(time));
				ssb.setSpan(fcs, 0, ssb.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);			
				tv2.setText(ssb);
				tv2.append(act.getString(R.string.identify_code_reload));           

			}  

		}  
}
