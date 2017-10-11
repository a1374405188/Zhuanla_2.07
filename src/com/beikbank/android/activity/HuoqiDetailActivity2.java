package com.beikbank.android.activity;

import java.util.ArrayList;

import u.aly.cb;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputFilter;
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

import com.beikbank.android.activity.help.GoumaiManager;
import com.beikbank.android.activity.help.LiuChenManager;
import com.beikbank.android.activity.help.NoNetShow;
import com.beikbank.android.activity.help.NoneData;
import com.beikbank.android.adapter.HuoqiAdapter;
import com.beikbank.android.adapter.HuoqiAdapter2;
import com.beikbank.android.adapter.HuoqiAdapter3;
import com.beikbank.android.adapter.ZhiChanAdapter;
import com.beikbank.android.conmon.SystemConfig;
import com.beikbank.android.data.CheckBank;
import com.beikbank.android.data.CheckBank_data;
import com.beikbank.android.data.DingqiPI;
import com.beikbank.android.data.DingqiPI_data;
import com.beikbank.android.data.FundInfo;
import com.beikbank.android.data2.getHuoQiXiangQin2_1;
import com.beikbank.android.data2.getZhiChan_data;
import com.beikbank.android.dataparam.CheckBankParam;
import com.beikbank.android.dataparam.DingqiPIParam;
import com.beikbank.android.dataparam2.getZhiChanParam;
import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.impl.DingqiPIManager;
import com.beikbank.android.net.impl.TongYongManager;
import com.beikbank.android.net.impl.TongYongManager2;
import com.beikbank.android.utils.BeikBankConstant;
import com.beikbank.android.utils.DialogManager;
import com.beikbank.android.utils.NumberManager;
import com.beikbank.android.utils.ViewDataUtil;
import com.beikbank.android.utils.ViewRullUtil;
import com.beikbank.android.utils2.StateBarColor;

import comc.beikbank.android.R;

/** 活期详情 转让中
 * Created by Administrator on 2015/3/16.
 */
public class HuoqiDetailActivity2 extends BaseActivity1 implements View.OnClickListener
{   
    Activity act=this;
    TextView title;
    /**
     * return
     */
    LinearLayout ll1;
    ArrayList<getHuoQiXiangQin2_1> list;
    ListView lv;
    
    
    
    /**
     * 上啦控件
     */
    LinearLayout ll_pull;
    /**
     * 
     */
    ViewRullUtil vpu;
    /**
     * listview父布局
     */
    LinearLayout ll2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_huoqi2);
        StateBarColor.init(this,0xffffffff);
        Intent intent=getIntent();
     
        init();
        
//        HuoqiAdapter ha=new HuoqiAdapter(this, list);
//        lv.setAdapter(ha);
       // initData();
    }
  
    private void init()
    {
        ll1=(LinearLayout)findViewById(R.id.linear_left);
        title=(TextView)findViewById(R.id.titleTv);
        
        ll1.setOnClickListener(this);
        title.setText("转让中");
        lv=(ListView) findViewById(R.id.lv);
        
        

        ll_pull=(LinearLayout) findViewById(R.id.ll_parent);
	       vpu=new ViewRullUtil(down, up);
	      
	       HuoqiAdapter2 ha=new HuoqiAdapter2(act);
	      
		   vpu.adapter=ha;
	       ll2=vpu.initView(this,null);
	       vpu.init(1);
		   ll_pull.removeAllViews();
		   ll_pull.addView(ll2);
		   vpu.prl.doPullRefreshing(true,200);
        
    }
ICallBack icb_down=new ICallBack() {
    	
    	@Override
    	public void back(Object obj) {
    		if(obj!=null)
    		{
    			  getZhiChan_data gd=null;
    			  gd=(getZhiChan_data) obj;
    			  vpu.doDownCompelete(gd.body);

    		}
    		else
    		{
    			 vpu.doUpCompelete(null);
    		}
    		if(vpu.adapter.list.size()==0)
    		   {
    			
    			 NoneData.setView(act,ll_pull,3);
    		   }
    		
    	}
    };
    ICallBack icb_up=new ICallBack() {
    	
    	@Override
    	public void back(Object obj) {
    		if(obj!=null)
    		{
    			  getZhiChan_data gd=null;
    			  gd=(getZhiChan_data) obj;
    			  vpu.doUpCompelete(gd.body);

    		}
    		else
    		{
    			 vpu.doUpCompelete(null);
    		}
    		if(vpu.adapter.list.size()==0)
    		   {
    			
    			 NoneData.setView(act,ll_pull,3);
    		   }
    		
    	
    	}
    };
    ICallBack down=new ICallBack() {
    	
    	@Override
    	public void back(Object obj) {
    		  getZhiChanParam gp=new getZhiChanParam();
    		  gp.is_reinvest="1";
    		  gp.page_index=vpu.start+"";
    		  
    		  gp.page_size=vpu.size+"";
    		  gp.prod_type="4";
    		  gp.status="0";
    		  gp.user_id=BeikBankApplication.getUserCode();
    		  TongYongManager2 tym2=new TongYongManager2(act, icb_down,gp);
    		  tym2.start();
    	}
    };
    ICallBack up=new ICallBack() {
    	
    	@Override
    	public void back(Object obj) {
    		// TODO Auto-generated method stub
    		  getZhiChanParam gp=new getZhiChanParam();
    		  gp.is_reinvest="1";
    		  gp.page_index=vpu.start+"";
    		  
    		  gp.page_size=vpu.size+"";
    		  gp.prod_type="4";
    		  gp.status="0";
    		  gp.user_id=BeikBankApplication.getUserCode();
    		  TongYongManager2 tym2=new TongYongManager2(act, icb_up,gp);
    		  tym2.start();
    	}
    };
         
    
    
    
    
    
    private void initData()
    {   
  	  ICallBack icb_gzc=new ICallBack() {
  		
  		@Override
  		public void back(Object obj) {
  			if(obj!=null)
  			{
  				getZhiChan_data gd=(getZhiChan_data) obj;
  				HuoqiAdapter2 ha2=new HuoqiAdapter2(act,gd.body);
  				lv.setAdapter(ha2);
  			}
  			
  		}
  	};
  	  getZhiChanParam gp=new getZhiChanParam();
  	  gp.is_reinvest="1";
  	  gp.page_index="1";
  	  gp.page_size="100";
  	  gp.prod_type="4";
  	  gp.status="0";
  	  gp.user_id=BeikBankApplication.getUserCode();
  	  TongYongManager2 tym2=new TongYongManager2(act, icb_gzc,gp);
  	  tym2.start();
  	  
    }
   public void onClick(View view)
   {  
	   Intent intent=getIntent();
	   boolean do_success=BeikBankApplication.mSharedPref.getSharePrefBoolean(BeikBankConstant.DO_SUCCESS,false);

      switch (view.getId())
      {
         case R.id.linear_left:
             finish();
          break;
          
     

    
 
      }
   }



}
