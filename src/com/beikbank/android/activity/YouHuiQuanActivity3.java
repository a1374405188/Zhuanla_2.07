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
import com.beikbank.android.utils.BeikBankConstant;
import com.beikbank.android.utils.hongbao.HongbaoUtil;
import com.beikbank.android.utils.hongbao.HongbaoUtil2;
import com.beikbank.android.utils.hongbao.HongbaoUtil2_v2;
import com.beikbank.android.utils.hongbao.HongbaoUtil_v2;
import com.beikbank.android.utils2.StateBarColor;
import com.beikbank.android.webwiew.WebWiewInface;
import coma.beikbank.android.R;



/**历史红包
 *copyright yuguohe
 *email: 1374405188@qq.com
 *2015-6-11
 */

public class YouHuiQuanActivity3 extends BaseActivity1 implements View.OnClickListener
{
    
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
     * 父布局
     */
    LinearLayout ll;
    /**
     * 没有过期需要显示文本
     */
    LinearLayout ll2;
    /**
     * 过期需要显示文本
     */
    LinearLayout ll3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youhuiquan3);
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
        titleTv.setText("历史优惠券");

    	//right=(TextView) findViewById(R.id.right);
		//right.setVisibility(View.VISIBLE);
		//right.setText("使用说明");
		
		//right.setOnClickListener(this);
		
		

        ll=(LinearLayout) findViewById(R.id.ll);
       
      
     
        
    }
	private void initData()
	{   
//		HongbaoParam hp=new HongbaoParam();
//		hp.userId=BeikBankApplication.getUserid();
//		//hp.userId="59";
//		TongYongManager tym=new TongYongManager(this, icb,hp);
//		tym.start();
        ICallBack icb=new ICallBack() {
			
			@Override
			public void back(Object obj) {
				if(obj!=null)
				{
					getAllYouHuiQuan_data gd=(getAllYouHuiQuan_data) obj;
					ArrayList<getAllYouHuiQuan> list=gd.body;
					   if(list.size()>0)
					   {
					  HongbaoUtil2_v2 hu2=new HongbaoUtil2_v2(act);
					  list=HongbaoUtil_v2.select2(list);
					  Collections.sort(list);
					  hu2.addView(ll, list);
					  
					   }
				}
				
			}
		};
		getAllYouhuiQuanParam gp=new getAllYouhuiQuanParam();
		gp.user_code=BeikBankApplication.getUserCode();
		TongYongManager2 tym=new TongYongManager2(this, icb,gp);
		tym.start();
		
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
				 list=HongbaoUtil.select7(list);
				 HongbaoUtil2 hu2=new HongbaoUtil2(act);
				 Collections.sort(list);
				 hu2.addView(ll, list);
				 ll3.setVisibility(View.VISIBLE);
			   }
			   else
			   {
//				   sl.setVisibility(View.GONE);
//				   rl.setVisibility(View.VISIBLE);
			   }
			}
			else
			{
				//sl.setVisibility(View.GONE);
				//rl.setVisibility(View.VISIBLE);
				
			}
			 ll3.setVisibility(View.VISIBLE);
			
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
             startAct(YouHuiQuanActivity2.class);
             break;
         case R.id.tv2:
             startAct(YouHuiQuanActivity2.class);
             break;    
      }
      
   }
   private void startAct(Class cla)
   {
	   Intent intent=new Intent(this, cla);
	   startActivity(intent);
   }
}
