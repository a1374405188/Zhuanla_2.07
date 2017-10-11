package com.beikbank.android.activity;

import java.util.ArrayList;
import java.util.List;

import com.beikbank.android.adapter.QianbaoRecordsAdapter;
import com.beikbank.android.adapter.YigouAdapter;
import com.beikbank.android.data.PayList_data;
import com.beikbank.android.data.QianbaoRecord_data;
import com.beikbank.android.dataparam.PayListParam;
import com.beikbank.android.dataparam.QianbaoRecordParam;
import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.impl.TongYongManager;
import com.beikbank.android.utils.ViewRullUtil;
import com.beikbank.android.utils2.StateBarColor;

import comc.beikbank.android.R;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

/** 
 *copyright yuguohe
 *email: 1374405188@qq.com
 *2015-6-30
 */
public class YigouActivity extends BaseActivity2 implements View.OnClickListener
{
    ListView lv;
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
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_yigourenshu);
		StateBarColor.init(this,0xffffffff);
		initView();
		initData();
	}
   private void initView()
   {
	   lv=(ListView) findViewById(R.id.lv);
	   ll1=(LinearLayout)findViewById(R.id.linear_left);
	   ll1.setOnClickListener(this);
	   
       title=(TextView)findViewById(R.id.titleTv);
       title.setText("已购用户");
       
       
       ll_pull=(LinearLayout) findViewById(R.id.ll_pull);
       vpu=new ViewRullUtil(down, up);
       vpu.init(200);
//       YigouAdapter ya=new YigouAdapter(YigouActivity.this);
//	   vpu.adapter=ya;
	 
	   ll2=vpu.initView(this,null);
	   ll_pull.removeAllViews();
	   ll_pull.addView(ll2);
   }
  
   private void initData()
   {   
//	   String index=getIntent().getStringExtra("index");
//	   PayListParam pp=new PayListParam();
//	   pp.startLine="0";
//	   pp.endLine="10000";
//	   pp.termbondCode=index;
//	   //TongYongManager tym=new TongYongManager(this, icb, pp);
//	  // tym.start();
	   vpu.prl.doPullRefreshing(true,200);
   }
   ICallBack down=new ICallBack() {
		
		@Override
		public void back(Object obj) {
			   String index=getIntent().getStringExtra("index");
			   PayListParam pp=new PayListParam();
			   pp.startLine=vpu.start+"";
			   pp.endLine="";
			   pp.termbondCode=index;
			   TongYongManager tym=new TongYongManager(YigouActivity.this, icbdown, pp);
			   tym.start();
		}
	};
	ICallBack up=new ICallBack() {
		
		@Override
		public void back(Object obj) {
			   String index=getIntent().getStringExtra("index");
			   PayListParam pp=new PayListParam();
			   pp.startLine=vpu.start+"";
			   pp.endLine="";
			   pp.termbondCode=index;
			   TongYongManager tym=new TongYongManager(YigouActivity.this, icbup, pp);
			   tym.start();
		}
	};
   
      ICallBack icbdown=new ICallBack() {
		
		@Override
		public void back(Object obj) {
			
				if(obj!=null)
				{
				   PayList_data pd=(PayList_data) obj;
				   if(pd!=null)
				   {
				   List list=pd.data;
				   vpu.doDownCompelete(list);
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
					   PayList_data pd=(PayList_data) obj;
					   if(pd!=null)
					   {
					   List list=pd.data;
					   vpu.doUpCompelete(list);
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
//   ICallBack icb=new ICallBack() {
//	
//	@Override
//	public void back(Object obj) 
//	{   
//		if(obj!=null)
//		{   
//			PayList_data pd=(PayList_data) obj;
//			if(pd!=null&&pd.data!=null)
//			{
//		     YigouAdapter ya=new YigouAdapter(pd.data, YigouActivity.this);
//		     lv.setAdapter(ya);
//		     }
//		}
//	}
//};
@Override
public void onClick(View v) {
	 switch (v.getId())
	 {
	 case R.id.linear_left:
         finish();
      break;
	 }
	
}
}
