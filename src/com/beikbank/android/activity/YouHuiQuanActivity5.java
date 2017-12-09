package com.beikbank.android.activity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.beikbank.android.adapter.HongbaoAdapter;
import com.beikbank.android.conmon.SystemConfig;
import com.beikbank.android.data.DingqiPI;
import com.beikbank.android.data.Hongbao;
import com.beikbank.android.data.Hongbao_data;
import com.beikbank.android.data2.getAllYouHuiQuan;
import com.beikbank.android.data2.getAllYouHuiQuan_data;
import com.beikbank.android.dataparam.HongbaoParam;
import com.beikbank.android.dataparam2.getAllYouhuiQuanParam;
import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.impl.TongYongManager;
import com.beikbank.android.net.impl.TongYongManager2;
import com.beikbank.android.sharedpref.SharePrefConstant;
import com.beikbank.android.utils.BeikBankConstant;
import com.beikbank.android.utils.NumberManager;
import com.beikbank.android.utils.hongbao.HongbaoUtil;
import com.beikbank.android.utils.hongbao.HongbaoUtil2;
import com.beikbank.android.utils.hongbao.HongbaoUtil2_2_v2;
import com.beikbank.android.utils.hongbao.HongbaoUtil2_v2;
import com.beikbank.android.utils.hongbao.HongbaoUtil3;
import com.beikbank.android.utils.hongbao.HongbaoUtil_v2;
import com.beikbank.android.utils2.StateBarColor;
import com.beikbank.android.webwiew.WebWiewInface;
import coma.beikbank.android.R;



/**选择优惠券
 *copyright yuguohe
 *email: 1374405188@qq.com
 *2015-6-11
 */

public class YouHuiQuanActivity5 extends BaseActivity1 implements View.OnClickListener
{
    List list=new ArrayList();
    //ListView lv;
    Activity act;
    private TextView right,titleTv;
    private ScrollView sl;
    /**
     * 跳转到历史优惠券
     */
    TextView tv1;
    /**
     * 跳转到历史优惠券
     */
    TextView tv2;
    //
    private RelativeLayout rl;
    /**
     * 夫布局
     */
    LinearLayout ll;
    TextView tv_money;
    TextView count;
    TextView tv_ok;
    getAllYouHuiQuan gyh;
    String money;
    String tianshu;
    LinearLayout ll_ok;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youhuiquan5);
        StateBarColor.init(this,0xffffffff);
        initView();
        act=this;
        initData();
    }
    private void initView()
    {
        
        LinearLayout left=(LinearLayout) findViewById(R.id.linear_left);
        left.setOnClickListener(this);
     //   lv=(ListView) findViewById(R.id.lv);
        titleTv = (TextView) findViewById(R.id.titleTv);
        titleTv.setText("我的红包");
        tv1=(TextView) findViewById(R.id.tv1);
        tv2=(TextView) findViewById(R.id.tv2);
    	right=(TextView) findViewById(R.id.right);
		right.setVisibility(View.VISIBLE);
		right.setText("使用说明");
		ll_ok=(LinearLayout) findViewById(R.id.ll_ok);
		right.setOnClickListener(this);
		
		sl=(ScrollView) findViewById(R.id.sv);
		rl=(RelativeLayout) findViewById(R.id.rl);
		
		tv2.setOnClickListener(this);
        ll=(LinearLayout) findViewById(R.id.ll);
        tv_money=(TextView) findViewById(R.id.money);
        count=(TextView) findViewById(R.id.count);
        tv_ok=(TextView) findViewById(R.id.tv_ok);
        tv_ok.setOnClickListener(this);
        BeikBankApplication.mSharedPref.putSharePrefString(SharePrefConstant.hongbao2,"");
        BeikBankApplication.mSharedPref.putSharePrefString(SharePrefConstant.hongbao,"");
    }
    
	private void initData()
	{   
		 money=getIntent().getStringExtra("money");
		 tianshu=getIntent().getStringExtra("tianshu");
		 gyh=(getAllYouHuiQuan) getIntent().getSerializableExtra("gyh");
		boolean is_has=getIntent().getBooleanExtra("is_has",false);
		ArrayList<getAllYouHuiQuan> list=(ArrayList<getAllYouHuiQuan>) getIntent().getSerializableExtra("list");

		 if(is_has==false)
		 {
			  HongbaoUtil2_2_v2 hu2=new HongbaoUtil2_2_v2(act,tv_money,count);
			  Collections.sort(list);
			  hu2.setNoHongBao(true);
			  hu2.addView(ll, list);
			  ll_ok.setVisibility(View.GONE);
			  return;
		 } 
		if(list.size()>0)
		   {
		  
		  HongbaoUtil2_2_v2 hu2=new HongbaoUtil2_2_v2(act,tv_money,count);
		  //list=HongbaoUtil_v2.select1(list);
		  //list=HongbaoUtil_v2.select3(tianshu,money,list);
		  Collections.sort(list);
		  hu2.addView(ll, list);
		 if(gyh!=null) {
             hu2.init(gyh);
         }
		  
		   }
	
	}
    ICallBack icb=new ICallBack() {
		
		@Override
		public void back(Object obj) {
			if(obj!=null)
			{
				Hongbao_data hd=(Hongbao_data) obj;
				ArrayList<Hongbao> list=hd.data;
			   if(list!=null&&list.size()>0)
			   {
				 //HongbaoAdapter ha=new HongbaoAdapter(act,list,null,true);
				 //lv.setAdapter(ha);
				   list=HongbaoUtil.select6(list);
				   if(list.size()>0)
				   {
				 HongbaoUtil3 hu2=new HongbaoUtil3(act);
				 Collections.sort(list);
				 hu2.addView(ll, list);
				 LinearLayout ll2=(LinearLayout) findViewById(R.id.ll2);
				 //ll2.setVisibility(View.VISIBLE);
				   }
				   else
				   {
					   
					   //sl.setVisibility(View.GONE);
					   //rl.setVisibility(View.VISIBLE);
				   }
			   }
			   else
			   {
				   //sl.setVisibility(View.GONE);
				   //rl.setVisibility(View.VISIBLE);
			   }
			}
			else
			{
				//sl.setVisibility(View.GONE);
				//rl.setVisibility(View.VISIBLE);
			}
			
		}
	};
   public void onClick(View view)
   {
      switch (view.getId())
      {
         case R.id.linear_left:
             finish();
          break;
         case R.id.right:
             startAct(YouHuiQuanActivity2.class);
          break;
         case R.id.tv1:
             startAct(YouHuiQuanActivity3.class);
             break;
         case R.id.tv2:
             startAct(YouHuiQuanActivity3.class);
             break;  
         case R.id.tv_ok:
             finish();
             break;
      }
      
   }
   private void startAct(Class cla)
   {
	   Intent intent=new Intent(this, cla);
	   startActivity(intent);
   }
}
