package com.beikbank.android.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.beikbank.android.data2.ShuJuQianYi1;
import com.beikbank.android.data2.ShuJuQianYi1_data;
import com.beikbank.android.data2.ShuJuQianYi2;
import com.beikbank.android.dataparam2.ShuJuQianYi1Param;
import com.beikbank.android.dataparam2.ShuJuQianYi2Param;
import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.impl.TongYongManager2;
import com.beikbank.android.utils.NumberManager;
import com.beikbank.android.utils.Utils;
import com.beikbank.android.utils2.StateBarColor;

import java.util.ArrayList;

import coma.beikbank.android.R;


//数据迁移3
public class ShuJuQianYiActivity3 extends BaseActivity1 implements OnClickListener{
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
    LinearLayout ll;
    ArrayList <ShuJuQianYi2> list;
    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shujuqianyi3);
		StateBarColor.init(this,0xffffffff);
		initView();
		
		addData();
        
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
            ll=(LinearLayout)findViewById(R.id.ll);




			bu.setEnabled(true);
			bu.setText("确定");
		}
        private void addData()
        {

            ICallBack icb=new ICallBack() {
                @Override
                public void back(Object obj) {
                    if(obj!=null)
                    {
                        ShuJuQianYi1_data sd=(ShuJuQianYi1_data)obj;
                        list=sd.body.list;
                        addView(list);
                    }
                }
            };

            ShuJuQianYi1Param sp=new ShuJuQianYi1Param();
            sp.user_code= BeikBankApplication.getUserCode();
            TongYongManager2 tym=new TongYongManager2(this,icb,sp);
            tym.start();
        }
        View cview;
        private void addView(ArrayList <ShuJuQianYi2> list)
        {

              for(int i=0;i<list.size();i++)
              {
				  final ShuJuQianYi2 sy2=list.get(i);
                  View view= LayoutInflater.from(act).inflate(R.layout.activity_shujuqianyi3_item, null);
                  ImageView iv1=(ImageView) view.findViewById(R.id.iv1);
                  ImageView iv2=(ImageView) view.findViewById(R.id.iv2);
                  TextView tv_phone=(TextView) view.findViewById(R.id.tv_phone);
                  TextView tv_bank=(TextView) view.findViewById(R.id.tv_bank);
                  tv_bank.setText(list.get(i).bank_name+"(尾号"+list.get(i).bank_num.substring(list.get(i).bank_num.length()-4)+"）");
                  tv_phone.setText(list.get(i).phone_list.get(0));
				  View v=view.findViewById(R.id.v);
				  if(i<list.size()-1)
				  {
                       v.setVisibility(View.VISIBLE);

				  }
				  else
				  {
					  v.setVisibility(View.GONE);
				  }

				  TextView tv_yue=(TextView) view.findViewById(R.id.tv_yue);
				  TextView tv_zhican=(TextView) view.findViewById(R.id.tv_zhican);
				  TextView tv_danbi=(TextView) view.findViewById(R.id.tv_danbi);
				  TextView tv_danri=(TextView) view.findViewById(R.id.tv_danri);


				  tv_yue.setText(NumberManager.getGeshiHua(list.get(i).amt_wallet,2)+"元");
				  tv_zhican.setText(NumberManager.getGeshiHua(list.get(i).amt_total,2)+"元");
				  tv_danbi.setText(NumberManager.getString(sy2.bank_per_order,"1",0)+"万元");
				  tv_danri.setText(NumberManager.getString(sy2.bank_per_day,"1",0)+"万元");
				  LinearLayout ll_bank=(LinearLayout) view.findViewById(R.id.ll_bank);
				  LinearLayout ll_iv1=(LinearLayout) view.findViewById(R.id.ll_iv1);
				  LinearLayout ll_iv2=(LinearLayout) view.findViewById(R.id.ll_iv2);
				  if(i==0)
				  {
					  iv1.setImageResource(R.drawable.img_data_number_checkbox_select);
                     // iv2.setImageResource(R.drawable.img_data_number_dropmenu_back);
					 // ll_bank.setVisibility(View.VISIBLE);
					  view.setTag(sy2);
					  code=sy2.user_code;
					  phone=sy2.phone_list.get(0);
                      cview=view;
					  ll_bank.setVisibility(View.GONE);
				  }
				  else
				  {
					  iv1.setImageResource(R.drawable.img_data_number_checkbox);
					  iv2.setImageResource(R.drawable.img_data_number_dropmenu);
					  ll_bank.setVisibility(View.GONE);
				  }
				  ll_iv2.setTag(view);
				  ll_iv1.setOnClickListener(new OnClickListener() {
					  @Override
					  public void onClick(View view) {
                          setView(ll);
                          if(cview!=view) {
//                              LinearLayout ll_bank = (LinearLayout) view.findViewById(R.id.ll_bank);
//                              ll_bank.setVisibility(View.VISIBLE);
                              ImageView iv1 = (ImageView) view.findViewById(R.id.iv1);
                              ImageView iv2 = (ImageView) view.findViewById(R.id.iv2);
                              iv1.setImageResource(R.drawable.img_data_number_checkbox_select);
                              //iv2.setImageResource(R.drawable.img_data_number_dropmenu_back);
                              view.setTag(sy2);
                              cview = view;
                              code = sy2.user_code;
                              phone = sy2.phone_list.get(0);
							  bu.setEnabled(true);
                          }
                          else
						  {
							  cview=null;
							  bu.setEnabled(false);
						  }
					  }
				  });
				  ll_iv2.setOnClickListener(new OnClickListener() {
                      @Override
                      public void onClick(View view) {
                              View view1=(View)view.getTag();
                              LinearLayout ll_bank = (LinearLayout) view1.findViewById(R.id.ll_bank);
						  if(ll_bank.getVisibility()==View.VISIBLE)
						  {
							  ll_bank.setVisibility(View.GONE);
							  ImageView iv2 = (ImageView) view.findViewById(R.id.iv2);

							  iv2.setImageResource(R.drawable.img_data_number_dropmenu);
						  }
						  else
						  {
							  ll_bank.setVisibility(View.VISIBLE);
//                              ImageView iv1 = (ImageView) view.findViewById(R.id.iv1);
							  ImageView iv2 = (ImageView) view.findViewById(R.id.iv2);
//                              iv1.setImageResource(R.drawable.img_data_number_checkbox_select);
							  iv2.setImageResource(R.drawable.img_data_number_dropmenu_back);
						  }

                      }
                  });
                  ll.addView(view);
              }



        }
    String code;
	String phone;
	private  void addData2()
	{

		ICallBack icb=new ICallBack() {
			@Override
			public void back(Object obj) {
				if(obj!=null)
				{
					Intent intent=new Intent(ShuJuQianYiActivity3.this,LoginRegActivity.class);
					startActivity(intent);
					finish();
				}
			}
		};

		ShuJuQianYi2Param sp=new ShuJuQianYi2Param();
		sp.user_code= code;
		sp.change_type="2";
		sp.phone_num=phone;
		TongYongManager2 tym=new TongYongManager2(this,icb,sp);
		tym.start();
	}
        private void setView(LinearLayout ll)
		{

               for(int i=0;i<ll.getChildCount();i++)
			   {
                      View view=ll.getChildAt(i);
				      LinearLayout ll_bank=(LinearLayout) view.findViewById(R.id.ll_bank);
				      ll_bank.setVisibility(View.GONE);

				      ImageView iv1=(ImageView) view.findViewById(R.id.iv1);
				      ImageView iv2=(ImageView) view.findViewById(R.id.iv2);
				     iv1.setImageResource(R.drawable.img_data_number_checkbox);
				     iv2.setImageResource(R.drawable.img_data_number_dropmenu);
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
                  addData2();
		          break;
	      }
	      
	   }
}
