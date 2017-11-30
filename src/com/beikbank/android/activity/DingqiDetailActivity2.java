package com.beikbank.android.activity;

import java.util.List;

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
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;


import com.beikbank.android.activity.help.GoumaiManager;
import com.beikbank.android.activity.help.LiuChenManager;
import com.beikbank.android.activity.help.NoNetShow;
import com.beikbank.android.activity.help.NoneData;
import com.beikbank.android.activity.help.TypeUtil;
import com.beikbank.android.adapter.HuoqiAdapter3;
import com.beikbank.android.adapter.TransactionRecordsAdapter;
import com.beikbank.android.adapter.ZhiChanAdapter;
import com.beikbank.android.conmon.FinalIndex;
import com.beikbank.android.conmon.SystemConfig;
import com.beikbank.android.data.CheckBank;
import com.beikbank.android.data.CheckBank_data;
import com.beikbank.android.data.DingqiP;
import com.beikbank.android.data.DingqiP2;
import com.beikbank.android.data.DingqiPI;
import com.beikbank.android.data.DingqiPI_data;
import com.beikbank.android.data2.CheckJiaoYiMiMa_data;
import com.beikbank.android.data2.getDingQiXiangQing;
import com.beikbank.android.data2.getDingQiXiangQing_data;
import com.beikbank.android.data2.getJiaoYiJiLv_data;
import com.beikbank.android.data2.getZhiChan_data;
import com.beikbank.android.dataparam.CheckBankParam;
import com.beikbank.android.dataparam.DingqiPIParam;
import com.beikbank.android.dataparam2.CheckJiaoYiMiMaParam;
import com.beikbank.android.dataparam2.HuTouOpenOrCloseParam;
import com.beikbank.android.dataparam2.getDingQiXiangQingParam;
import com.beikbank.android.dataparam2.getZhiChanParam;
import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.impl.DingqiPIManager;
import com.beikbank.android.net.impl.TongYongManager;
import com.beikbank.android.net.impl.TongYongManager2;
import com.beikbank.android.sharedpref.SharePrefConstant;
import com.beikbank.android.utils.BeikBankConstant;
import com.beikbank.android.utils.DialogManager;
import com.beikbank.android.utils.MD5;
import com.beikbank.android.utils.NumberManager;
import com.beikbank.android.utils.PageUtil;
import com.beikbank.android.utils.ViewDataUtil;
import com.beikbank.android.utils.ViewRullUtil;
import com.beikbank.android.utils2.StateBarColor;
import com.beikbank.android.widget.ClearableEditText;
import com.beikbank.android.widget.SwitchButton;
import coma.beikbank.android.R;



/** 已到期定期
 * Created by Administrator on 2015/3/16.
 */
public class DingqiDetailActivity2 extends BaseActivity1 implements View.OnClickListener
{   
	Activity act=this;
    TextView title;
    /**
     * return
     */
    LinearLayout ll1;

    LinearLayout ll_parent;
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
        setContentView(R.layout.activity_dingqi2);
        StateBarColor.init(this,0xffffffff);
        init();
       
        title.setText("已到期");
        lv=(ListView) findViewById(R.id.lv);
     
       //initData();

    }
   
	/**
	 * 计算年发收益率
	 * 
	 * @return 15%
	 */
	public static String countRate(String yearRate,String extraRate)
	{   
		String s=NumberManager.getAddString(yearRate,extraRate,8);
		s=ViewDataUtil.getD1(s)+"%";
		return s;
	}

	private void initData()
	{   
		 ICallBack icb_gzc=new ICallBack() {
				
				@Override
				public void back(Object obj) {
					getZhiChan_data gd=null;
					if(obj!=null)
					{
						gd=(getZhiChan_data) obj;
						HuoqiAdapter3 ha=new HuoqiAdapter3(act,gd.body);
						lv.setAdapter(ha);
					}
					else
					{
						
						
					}
					if(gd==null||gd.body.size()==0)
					{
						NoneData.setView(act,ll_parent,2);
					}
				}
			};
			  getZhiChanParam gp=new getZhiChanParam();
			  gp.is_reinvest="1";
			  gp.page_index="1";
			  gp.page_size="100";
			  gp.prod_type="3";
			  gp.status="1";
			  gp.user_id=BeikBankApplication.getUserCode();
			  TongYongManager2 tym2=new TongYongManager2(act, icb_gzc,gp);
			  tym2.start();
	}
	

    private void init()
    {
        ll1=(LinearLayout)findViewById(R.id.linear_left);
        title=(TextView)findViewById(R.id.titleTv);
        ll_parent=(LinearLayout)findViewById(R.id.ll_parent);
        ll1.setOnClickListener(this);
        
         
           ll_pull=(LinearLayout) findViewById(R.id.ll_parent);
	       vpu=new ViewRullUtil(down, up);
	      
	       HuoqiAdapter3 ha=new HuoqiAdapter3(act);
	      
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
			
			 NoneData.setView(act,ll_pull,2);
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
			
			 NoneData.setView(act,ll_pull,2);
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
		  gp.prod_type="3";
		  gp.status="1";
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
		  gp.prod_type="3";
		  gp.status="1";
		  gp.user_id=BeikBankApplication.getUserCode();
		  TongYongManager2 tym2=new TongYongManager2(act, icb_up,gp);
		  tym2.start();
	}
};
  
  
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
   public void onClick(View view)
   {  
	   Intent intent=getIntent();
	  
      switch (view.getId())
      {
         case R.id.linear_left:
             finish();
          break;
       
         case R.id.rl_fenxian:
        	 
      	  
        break;
       case R.id.rl_touzhi:
      	
        break;
  
  
      }
   }



}
