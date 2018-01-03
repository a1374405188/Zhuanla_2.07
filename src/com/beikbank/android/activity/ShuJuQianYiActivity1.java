package com.beikbank.android.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.beikbank.android.data2.ShuJuQianYi2;
import com.beikbank.android.data2.checkYanZhenMa_data;
import com.beikbank.android.dataparam2.HeadParam2;
import com.beikbank.android.dataparam2.checkYanZhenMaParam;
import com.beikbank.android.dataparam2.getYanZhenMaParam;
import com.beikbank.android.dataparam2.phoneChangeParam;
import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.impl.TongYongManager2;
import com.beikbank.android.utils.AdvancedCountdownTimer;
import com.beikbank.android.utils.BeikBankConstant;
import com.beikbank.android.utils.DensityUtil;
import com.beikbank.android.utils.Utils;
import com.beikbank.android.utils2.StateBarColor;
import com.beikbank.android.widget.ClearableEditText;

import java.util.ArrayList;

import coma.beikbank.android.R;


//数据迁移1
public class ShuJuQianYiActivity1 extends BaseActivity1 implements OnClickListener{
	TextView title;
    /**
     * return
     */
    LinearLayout ll1;
  
    TextView tv1;
	TextView tv2;
	TextView tv3;
    Activity act=this;
	Button bu;
    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shujuqianyi1);
		StateBarColor.init(this,0xffffffff);
		initView();
		
		
        
	}


	  
	  private void initView()
	    {   
		    ll_error=(LinearLayout) findViewById(R.id.ll_error);
			tv_error=(TextView) findViewById(R.id.tv_error);
	        ll1=(LinearLayout)findViewById(R.id.linear_left);
	        ll1.setOnClickListener(this);
	        title=(TextView)findViewById(R.id.titleTv);
	        title.setText("银行存管数据迁移");

	        bu=(Button) findViewById(R.id.button_next);
	        bu.setOnClickListener(this);

			tv1=(TextView)findViewById(R.id.tv1);
			tv2=(TextView)findViewById(R.id.tv2);
			tv3=(TextView)findViewById(R.id.tv3);


			SpannableStringBuilder sb = new SpannableStringBuilder("1. 选择要保留的登录手机号：若有多个注册手机号绑定了同一张银行卡，需要选择一个要保留的登录手机号，选择后其他注册手机号将无法登录，对应账号的资产将划入要保留的手机号对应的账号中。"); // 包装字体内容
			ForegroundColorSpan fcs = new ForegroundColorSpan(0xff333333); // 设置字体颜色

			// StyleSpan bss = new StyleSpan(android.graphics.Typeface.BOLD); // 设置字体样式
			AbsoluteSizeSpan ass = new AbsoluteSizeSpan(DensityUtil.sp2px(act,16));  // 设置字体大小
			sb.setSpan(fcs, 0,14, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			//sb.setSpan(bss, 0, 20, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
			//sb.setSpan(ass,0,14, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			//sb.setSpan(ass,15,sb.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

			ForegroundColorSpan fcs11 = new ForegroundColorSpan(0xff666666); // 设置字体颜色
			sb.setSpan(fcs11, 15,sb.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);



			tv1.setText(sb);






			SpannableStringBuilder sb2 = new SpannableStringBuilder("2. 使用您选择的手机号进行登录：选择完成后，使用您选择的手机号登录即可正常使用。"); // 包装字体内容
			ForegroundColorSpan fcs2 = new ForegroundColorSpan(0xff333333); // 设置字体颜色
			sb2.setSpan(fcs2, 0,18, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			ForegroundColorSpan fcs22 = new ForegroundColorSpan(0xff666666); // 设置字体颜色
			sb2.setSpan(fcs22, 19,sb2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			tv2.setText(sb2);


			SpannableStringBuilder sb3 = new SpannableStringBuilder("3. 使用您选择保留的手机号进行登录：以上操作完成后，使用您选择保留的手机号进行登录即可正常使用。"); // 包装字体内容
			ForegroundColorSpan fcs3 = new ForegroundColorSpan(0xff333333); // 设置字体颜色
			sb3.setSpan(fcs2, 0,18, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			ForegroundColorSpan fcs33 = new ForegroundColorSpan(0xff666666); // 设置字体颜色
			sb3.setSpan(fcs33, 19,sb3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		//	tv3.setText(sb3);

			bu.setEnabled(true);
			bu.setOnClickListener(this);
		}

		 private void showTiShi()
		 {
				
				
				final Dialog dialog=Utils.createTiShiDialog(this,null);
				dialog.show();
				
				final Handler h=new Handler()
				{

					@Override
					public void handleMessage(Message msg) {
						
						super.handleMessage(msg);
						if(msg.what==1)
						{
							dialog.dismiss();
							finish();
						}
					}
					
				};
				Runnable run=new Runnable() {
					
					@Override
					public void run() {
					
						Message msg=new Message();
						msg.what=1;
						h.sendMessage(msg);
					}
				};
				h.postDelayed(run,2);
				
		}
	   public void onClick(View view)
	   {
	      switch (view.getId())
	      {
	         case R.id.linear_left:
				 GengDuoActivity.tuichu();
	             finish();
	          break;
	         case R.id.right:
	           
	          break;
	         case R.id.bu_yanzhenma:


		          break;
	         case R.id.button_next:
				 Intent intent=getIntent();

				 ArrayList<ShuJuQianYi2> list=(ArrayList<ShuJuQianYi2>)getIntent().getSerializableExtra("list");
				 ArrayList<ShuJuQianYi2> list2=new ArrayList<ShuJuQianYi2>();
				 for(int i=0;i<list.size();i++)
				 {
					 if(list.get(i).phone_list.size()>1)
					 list2.add(list.get(i));
				 }
				 if(list2.size()>0)
				 {
					 intent.setClass(this,ShuJuQianYiActivity2.class);
					 intent.putExtra("list",list2);
				 }
				 else
				 {
					 intent.setClass(this,ShuJuQianYiActivity3.class);
				 }
				 startActivity(intent);
				 finish();
		          break;
	      }
	      
	   }
}
