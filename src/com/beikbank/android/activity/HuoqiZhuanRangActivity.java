package com.beikbank.android.activity;

import java.util.ArrayList;

import org.json.JSONObject;

import u.aly.cb;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.beikbank.android.activity.DingqiLicaiActivity.TextWatcherListener;
import com.beikbank.android.activity.help.GoumaiManager;
import com.beikbank.android.activity.help.LiuChenManager;
import com.beikbank.android.activity.help.NoNetShow;
import com.beikbank.android.adapter.HuoqiAdapter;
import com.beikbank.android.adapter.ZhiChanAdapter;
import com.beikbank.android.adapter.ZuanRangAdapter;

import com.beikbank.android.conmon.SystemConfig;
import com.beikbank.android.data.CheckBank;
import com.beikbank.android.data.CheckBank_data;
import com.beikbank.android.data.DingqiPI;
import com.beikbank.android.data.DingqiPI_data;
import com.beikbank.android.data.FundInfo;
import com.beikbank.android.data2.CheckJiaoYiMiMa_data;
import com.beikbank.android.data2.getHuoQiXiangQin2_1;
import com.beikbank.android.dataparam.CheckBankParam;
import com.beikbank.android.dataparam.DingqiPIParam;
import com.beikbank.android.dataparam2.CheckJiaoYiMiMaParam;
import com.beikbank.android.dataparam2.HuTouOpenOrCloseParam;
import com.beikbank.android.dataparam2.HuoQiShuHuiParam2;
import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.impl.DingqiPIManager;
import com.beikbank.android.net.impl.TongYongManager;
import com.beikbank.android.net.impl.TongYongManager2;
import com.beikbank.android.utils.BeikBankConstant;
import com.beikbank.android.utils.DialogManager;
import com.beikbank.android.utils.MD5;
import com.beikbank.android.utils.NumberManager;
import com.beikbank.android.utils.ViewDataUtil;
import com.beikbank.android.utils2.StateBarColor;
import com.beikbank.android.widget.ClearableEditText;

import comc.beikbank.android.R;

/** 活期转让
 * Created by Administrator on 2015/3/16.
 */
public class HuoqiZhuanRangActivity extends BaseActivity1 implements View.OnClickListener
{   
    Activity act=this;
    TextView title;
    /**
     * return
     */
    LinearLayout ll1;
    ArrayList<getHuoQiXiangQin2_1> list;
    ListView lv;
    TextView tv_quanxuan;
    ImageView iv;
    ZuanRangAdapter za;
    TextView tv_money;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_huoqi_zhuanrang);
        StateBarColor.init(this,0xffffffff);
        
     
        init();
        list=(ArrayList<getHuoQiXiangQin2_1>) getIntent().getSerializableExtra("list");
     
      if(list!=null)
      {
    	  za=new ZuanRangAdapter(act, list,false);
    	  lv.setAdapter(za);
      }
      else
      {
    	  Log.d("list","null");
      }
    }
  
    public void setMoney(String money)
    {
    	TextView tv_money=(TextView) findViewById(R.id.tv_money1);
    	tv_money.setText(NumberManager.getGeshiHua(money,2));
    	
    	if(NumberManager.isDaYu(money, "0")>0)
    	{
    		   Button bu_ok=(Button) findViewById(R.id.bu_ok);
    		   bu_ok.setEnabled(true);
    	}
    	else
    	{    
    		   Button bu_ok=(Button) findViewById(R.id.bu_ok);
    		   bu_ok.setEnabled(false);
    	}
    }
    Dialog   dialog4;
	ClearableEditText et_mima;
	TextView tv_quereng;
	HuoqiZhuanRangActivity context=this;
	private void createDialog(String money)
	{
		 LinearLayout ll=new LinearLayout(context);
		    View v=LayoutInflater.from(context).inflate(
	  			   R.layout.zhuangrang_quereng,ll,false);
		    
		   et_mima=(ClearableEditText) v.findViewById(R.id.et_mima);
		    et_mima
			.addTextChangedListener(new TextWatcherListener());
		    TextView tv_qvxiao=(TextView) v.findViewById(R.id.tv_qvxiao);
		    tv_quereng=(TextView) v.findViewById(R.id.tv_quereng);
		    context.tv_error=(TextView) v.findViewById(R.id.tv_error);
		    context.ll_error=(LinearLayout) v.findViewById(R.id.ll_error);
		    TextView tv_mima=(TextView) v.findViewById(R.id.tv_mima);
		    TextView tv_money=(TextView) v.findViewById(R.id.money);
		    tv_money.setText("￥"+money);
		    
		    
		    tv_mima.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent intent=new Intent(context,ForgetPwdRealnameActivity.class);
					intent.putExtra(BeikBankConstant.IS_FORGETTRANSACTIONPWD,true);
					intent.putExtra(BeikBankConstant.INTENT_PHONENUMBER,BeikBankApplication.getPhoneNumber());
					context.startActivity(intent);
					
				}
			});
		    tv_qvxiao.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					dialog4.dismiss();
					
				}
			});
		    tv_quereng.setOnClickListener(new OnClickListener() {
				
						@Override
						public void onClick(View v) {
							
							String s=et_mima.getText().toString();
							if(s!=null&&!"".equals(s))
							{
								zuanrang();
							}
						}
					});
		    dialog4=DialogManager.getDiaolg1(this, v);
	        dialog4.setCanceledOnTouchOutside(false);
	    	dialog4.show();
	}  
    
    
    
    private void init()
    {
       // ll1=(LinearLayout)findViewById(R.id.linear_left);
        title=(TextView)findViewById(R.id.titleTv);
        
       // ll1.setOnClickListener(this);
        title.setText("转让");
        tv_quanxuan=(TextView)findViewById(R.id.tv_quanxuan);
        tv_quanxuan.setOnClickListener(this);
        lv=(ListView) findViewById(R.id.lv);
        Button bu_ok=(Button) findViewById(R.id.bu_ok);
        bu_ok.setOnClickListener(this);
        iv=(ImageView) findViewById(R.id.iv);
        iv.setOnClickListener(this);
        tv_money=(TextView) findViewById(R.id.tv_money1);
        if(list==null||list.size()==0)
        {
        	bu_ok.setEnabled(false);
        }
    }
    class TextWatcherListener implements TextWatcher {
		@Override
		public void afterTextChanged(Editable s) {
			// TODO Auto-generated method stub

		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			// TODO Auto-generated method stub
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			if(s.length()>=6)
			{
				tv_quereng.setTextColor(0xff333333);
			}
			else
			{
				tv_quereng.setTextColor(0xff999999);
			}
			
		}

	}
    
    
    
    
	public void zuanrang()
	{   
		
		
		
		
		ICallBack icb=new ICallBack() {
			
			@Override
			public void back(Object obj) {
				if(obj!=null)
				{
					Intent intent=getIntent();
					intent.setClass(act,JiaoYiXiangQingActivity2.class);
					//intent.putExtra("order_id",cdd.order_id);
					intent.putExtra("name",list.get(0).prodName);
					intent.putExtra("money",za.getMoney());
					startActivity(intent);
				}
			}
		};
		
		
		
		ArrayList<Integer> list=za.getList();
		HuoQiShuHuiParam2 hp=new HuoQiShuHuiParam2();
		hp.assets_id=list;
		TongYongManager2 tym2=new TongYongManager2(context, icb,hp);
		tym2.start();
	}
	/**
	 * 1 全选 2全请
	 */
	int index=1;
   public void onClick(View view)
   {  
	   Intent intent=getIntent();
	   boolean do_success=BeikBankApplication.mSharedPref.getSharePrefBoolean(BeikBankConstant.DO_SUCCESS,false);

      switch (view.getId())
      {
         case R.id.linear_left:
             finish();
          break;
         case R.id.tv_quanxuan:
        	 if(list==null||list.size()==0)
        	 {
        		 return;
        	 }
            if(index==1)
            {
            	  tv_quanxuan.setText("全清");
            	 
            	
            	  za=new ZuanRangAdapter(act, list,true);
            	   lv.setAdapter(za);
            	   za.notifyDataSetChanged();
            	    setMoney(za.getMoney());
            	    index=2;
            }
            else
            {   
            	
            	tv_quanxuan.setText("全选");
            	
            	za=new ZuanRangAdapter(act, list,false);
            	
         	   lv.setAdapter(za);
         	   
         	   za.notifyDataSetChanged();
         	  
         	   setMoney(za.getMoney());
            	 index=1;
            }
            
            
         
          break;
         case R.id.iv:
        	 finish();
        	 
          break;
         case R.id.bu_ok:
        	 createDialog(NumberManager.getGeshiHua(za.getMoney(),2));
          break;

    
 
      }
   }



}
