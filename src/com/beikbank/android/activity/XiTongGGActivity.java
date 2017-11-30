package com.beikbank.android.activity;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.beikbank.android.adapter.XiTongAdapter;
import com.beikbank.android.adapter.YigouAdapter;
import com.beikbank.android.conmon.SystemConfig;
import com.beikbank.android.data.Config_data;
import com.beikbank.android.data.Confing;
import com.beikbank.android.data.PayList_data;
import com.beikbank.android.data2.Xiaoxi;
import com.beikbank.android.dataparam.PayListParam;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.impl.ConfigManager;
import com.beikbank.android.net.impl.TongYongManager;
import com.beikbank.android.utils.BeikBankDialogListener;
import com.beikbank.android.utils.Utils;
import com.beikbank.android.utils.ViewRullUtil;
import com.beikbank.android.utils2.StateBarColor;
import com.beikbank.android.webwiew.WebWiewInface;
import coma.beikbank.android.R;



//系统公告
public class XiTongGGActivity extends BaseActivity1 implements OnClickListener{
	TextView title;
    /**
     * return
     */
    LinearLayout ll1;
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
    private ArrayList<Xiaoxi> list;
    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_xitong_gg);
		StateBarColor.init(this,0xffffffff);
		initView();
        initData();
	}

    private void initData()
    {   
       list=(ArrayList<Xiaoxi>) getIntent().getSerializableExtra("index");
       

 	   vpu.prl.doPullRefreshing(true,200);
    }
    ICallBack down=new ICallBack() {
 		
 		@Override
 		public void back(Object obj) {
// 			   String index=getIntent().getStringExtra("index");
// 			   PayListParam pp=new PayListParam();
// 			   pp.startLine=vpu.start+"";
// 			   pp.endLine=vpu.end+"";
// 			   pp.termbondCode=index;
// 			   TongYongManager tym=new TongYongManager(XiTongGGActivity.this, icbdown, pp);
// 			   tym.start();
 			 icbdown.back(list);
 		}
 	};
 	ICallBack up=new ICallBack() {
 		
 		@Override
 		public void back(Object obj) {
// 			   String index=getIntent().getStringExtra("index");
// 			   PayListParam pp=new PayListParam();
// 			   pp.startLine=vpu.start+"";
// 			   pp.endLine=vpu.end+"";
// 			   pp.termbondCode=index;
// 			   TongYongManager tym=new TongYongManager(XiTongGGActivity.this, icbup, pp);
// 			   tym.start();
 			   icbup.back(list);
 		}
 	};
    
       ICallBack icbdown=new ICallBack() {
 		
 		@Override
 		public void back(Object obj) {
 			
 				if(obj!=null)
 				{
 				   
 				   if(list!=null)
 				   {
 				   List list1=list;
 				   vpu.doDownCompelete(list1);
 				   }
 				   else
 				   {
 					   vpu.doDownCompelete(null);
 				    
 				   }
 				}
 				else
 				{
 					vpu.doDownCompelete(null);
 				}
 		}
 		
 	};
 	 ICallBack icbup=new ICallBack() {
 			
 			@Override
 			public void back(Object obj) {
 				
 					if(obj!=null)
 					{
 					  
 					   if(list!=null)
 					   {
 						  List list1=list;
 					      vpu.doUpCompelete(list1);
 					   }
 					   else
 					   {
 						  vpu.doUpCompelete(null);
 					    
 					   }
 					}
 					else
 					{
 						  vpu.doUpCompelete(null);
 					}
 			}
 			
 		};
	 
	  private void initView()
	    {
	        ll1=(LinearLayout)findViewById(R.id.linear_left);
	        ll1.setOnClickListener(this);
	        title=(TextView)findViewById(R.id.titleTv);
	        title.setText("系统公告");

	        ll_pull=(LinearLayout) findViewById(R.id.ll_pull);
	        vpu=new ViewRullUtil(down, up);
	        vpu.init(200);
	        XiTongAdapter xa=new XiTongAdapter(this);
	 	    vpu.adapter=xa;
	 	    
	 	    
	 	    ll2=vpu.initView(this,null);
	 	    ll_pull.removeAllViews();
	 	    ll_pull.addView(ll2);
	        
	        
	    }

	   public void onClick(View view)
	   {
	      switch (view.getId())
	      {
	         case R.id.linear_left:
	             finish();
	         break;
	         
	      }
	      
	   }
}
