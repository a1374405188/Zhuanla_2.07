package com.beikbank.android.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.beikbank.android.data2.ShuJuQianYi1_data;
import com.beikbank.android.data2.ShuJuQianYi2;
import com.beikbank.android.dataparam2.ShuJuQianYi1Param;
import com.beikbank.android.dataparam2.ShuJuQianYi2Param;
import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.impl.TongYongManager2;
import com.beikbank.android.utils.DensityUtil;
import com.beikbank.android.utils.Utils;
import com.beikbank.android.utils2.StateBarColor;

import java.util.ArrayList;

import coma.beikbank.android.R;


//数据迁移2
public class ShuJuQianYiActivity2 extends BaseActivity1 implements OnClickListener{
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
	ArrayList<ShuJuQianYi2> list;
	TextView tv_bank;
	LinearLayout ll;
	String phone;
    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shujuqianyi2);
		StateBarColor.init(this,0xffffffff);
		list=(ArrayList<ShuJuQianYi2>)getIntent().getSerializableExtra("list");
		initView();
		
		addView(list.get(0).phone_list);
        
	}


	  
	  private void initView()
	    {   
		    ll_error=(LinearLayout) findViewById(R.id.ll_error);
			tv_error=(TextView) findViewById(R.id.tv_error);
	        ll1=(LinearLayout)findViewById(R.id.linear_left);
	        ll1.setOnClickListener(this);
	        title=(TextView)findViewById(R.id.titleTv);
	        title.setText("银行存管数据迁移");
			ll=(LinearLayout) findViewById(R.id.ll);
	        bu=(Button) findViewById(R.id.button_next);
	        bu.setOnClickListener(this);
			bu.setEnabled(true);
			tv_bank=(TextView) findViewById(R.id.tv_bank);
			ShuJuQianYi2 sy=list.get(0);
            tv_bank.setText(sy.bank_name+"("+"尾号"+sy.bank_num.substring(sy.bank_num.length()-4)+")");
			bu.setText("确定");


		}
        private void addView(ArrayList<String> list)
		{



            for(int i=0;i<list.size();i++)
			{
				 View view= LayoutInflater.from(act).inflate(R.layout.activity_shujuqianyi2_item, null);
				 ImageView iv=(ImageView) view.findViewById(R.id.iv);
				 TextView tv=(TextView) view.findViewById(R.id.tv);
				LinearLayout ll_line=(LinearLayout) view.findViewById(R.id.ll_line);
				if(i==0)
				{
                      // iv.setImageResource(R.drawable.img_data_number_checkbox_select);
					//phone=list.get(i);
				}
                else
				{
					iv.setImageResource(R.drawable.img_data_number_checkbox);
				}
				if(i!=list.size()-1)
				{
                           ll_line.setVisibility(View.VISIBLE);
				}
				else
				{
					ll_line.setVisibility(View.GONE);
				}
				tv.setText(list.get(i));
				view.setTag(list.get(i));
				view.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {

						ImageView iv=(ImageView) v.findViewById(R.id.iv);
						initSelect(ll,v);
//						Object tag=iv.getTag();
//						if(tag==null||"0".equals(tag))
//						{
//							iv.setImageResource(R.drawable.img_data_number_checkbox_select);
//							phone = v.getTag().toString();
//							bu.setEnabled(true);
//							iv.setTag("1");
//
//						}
//						else
//						{
//							iv.setImageResource(R.drawable.img_data_number_checkbox);
//							phone = v.getTag().toString();
//							bu.setEnabled(false);
//							iv.setTag("0");
//						}
					}
				});
				ll.addView(view);
			}



		}
		 private void initSelect(LinearLayout ll,View cview)
		 {
			 for(int i=0;i<ll.getChildCount();i++)
			 {
				 View view = ll.getChildAt(i);
				 if(view!=cview)
				 {

					 ImageView iv = (ImageView) view.findViewById(R.id.iv);
					 iv.setImageResource(R.drawable.img_data_number_checkbox);
					 iv.setTag("0");

				 }
				 else
				 {

					 ImageView iv = (ImageView) view.findViewById(R.id.iv);
					 Object  obj=iv.getTag();
					 if(!"1".equals(obj))
					 {
						 iv.setImageResource(R.drawable.img_data_number_checkbox_select);
						 iv.setTag("1");
						 phone = view.getTag().toString();
						 bu.setEnabled(true);
					 }
					 else
					 {
						 iv.setImageResource(R.drawable.img_data_number_checkbox);
						 iv.setTag("0");
						 phone = view.getTag().toString();
						 bu.setEnabled(false);
					 }

				 }
			 }



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
		private void addData()
		{
			ICallBack icb=new ICallBack() {
				@Override
				public void back(Object obj) {
					if(obj!=null)
					{
                        finish();
						int size=getIntent().getIntExtra("size",0);
						if(size==1)
						{
							Intent intent=new Intent(ShuJuQianYiActivity2.this,LoginRegActivity.class);
							startActivity(intent);
							return;
						}
                        list.remove(0);
						if(list.size()>0)
						{
							ShuJuQianYi2 sy=list.get(0);
							if(sy.phone_list.size()>1) {
								Intent intent = new Intent(act, ShuJuQianYiActivity2.class);
								intent.putExtra("list", list);
								startActivity(intent);
							}
							else
							{
								Intent intent = new Intent(act, ShuJuQianYiActivity3.class);
								intent.putExtra("list", list);
								startActivity(intent);
							}

						}
						else
						{
							Intent intent = new Intent(act, ShuJuQianYiActivity3.class);
							intent.putExtra("list", list);
							startActivity(intent);
						}
					}
				}
			};
			ShuJuQianYi2 sy=list.get(0);
			ShuJuQianYi2Param sp=new ShuJuQianYi2Param();
			sp.user_code= sy.user_code;
			sp.phone_num=phone;
			sp.change_type="1";
			TongYongManager2 tym=new TongYongManager2(this,icb,sp);
			tym.start();
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
				 addData();
		          break;
	      }
	      
	   }
}
