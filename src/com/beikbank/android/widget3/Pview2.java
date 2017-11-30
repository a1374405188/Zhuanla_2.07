package com.beikbank.android.widget3;


import com.beikbank.android.activity.ChanPinActivityV2;

import com.beikbank.android.activity.HuodongActivity2;
import com.beikbank.android.activity.help.NoneData;
import com.beikbank.android.adapter.XiangMuXinXiAdapter;

import com.beikbank.android.conmon.SystemConfig;
import com.beikbank.android.data2.GetChanPin;
import com.beikbank.android.data2.getXiangMuXinXi_data;
import com.beikbank.android.dataparam2.getXiangMuXinXiParam;

import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.impl.TongYongManager2;

import com.beikbank.android.pullrefresh.PullToRefreshBase;
import com.beikbank.android.pullrefresh.PullToRefreshScrollView;
import com.beikbank.android.pullrefresh.PullToRefreshBase.OnRefreshListener;
import com.beikbank.android.utils.BeikBankConstant;
import com.beikbank.android.utils.ViewRullUtil;

import com.beikbank.android.utils.NumberManager;
import com.beikbank.android.widget.MagicTextView;
import coma.beikbank.android.R;





import android.content.Context;
import android.content.Intent;

import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import android.view.View.OnClickListener;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;


/**
 * 
 * @author Administrator
 *活期
 */
public class Pview2 extends LinearLayout implements OnClickListener{
	private PullToRefreshScrollView prs;
    private ScrollView sv;
    private ChanPinActivityV2 act;
    /**
     * 年化收益率
     */
    MagicTextView  tv1;
    /**
     * 累计收益
     */
    TextView tv2;
    /**
     * 累计投资
     */
    TextView tv3;
    /**
     * 已投人数
     */
    TextView tv4;
    /**
     * 万份收益
     */
    TextView tv5;
    /**
     * 万份收益
     */
    TextView tv14;
    /**
     * 标识
     */
    private ImageView iv5;
    /**
     * 产品id
     */
    private String sid;
    private Button bu_goumai;
    private LinearLayout ll_xiangqing;
    
    LinearLayout ll_pull;
    public ListView lv;
	public Pview2(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init(context);
	}
	public Pview2(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}
	private void init(Context context)
	{   
		act=(ChanPinActivityV2) context;
		LinearLayout ll=new LinearLayout(context);
		//View view0 =LayoutInflater.from(context).inflate(R.layout.page_wealth11,ll,false);
		
		View view= LayoutInflater.from(context).inflate(R.layout.pview2_v2,ll,false);
		LayoutParams lp=new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
		view.setLayoutParams(lp);
		
		
		//YuanView yv=(YuanView) view.findViewById(R.id.yv);
		//yv.init2(act,0,240);
		//yv.setDraw(true);
		WindowManager wm = act.getWindowManager();
	    int width = wm.getDefaultDisplay().getWidth();
		LinearLayout ll_shouyi=(LinearLayout) view.findViewById(R.id.ll_shouyi);
		int top=(width-width/10*7)/2+width/12;
		
		
		ll_pull=(LinearLayout) view.findViewById(R.id.ll_pull);
		
		NoneData nd=new NoneData();
		nd.setView(act,ll_pull,7);
		
		
		addView(view);
		
		
		//ll_shouyi.setPadding(0,top,0,0);
		
//		ImageView iv=(ImageView) view.findViewById(R.id.iv_img1);
//		LayoutParams lp=new LayoutParams(width/10*8,width/10*8);
//		iv.setLayoutParams(lp);
//		iv.setPadding(0,(width-width/10*7)/2,0,0);
		
		
//		tv1=(MagicTextView) view.findViewById(R.id.tv1);
//		tv2=(TextView) view.findViewById(R.id.tv2);
//		tv3=(TextView) view.findViewById(R.id.tv3);
//		tv4=(TextView) view.findViewById(R.id.tv4);
//		tv5=(TextView) view.findViewById(R.id.tv5);
//		tv14=(TextView) view.findViewById(R.id.tv14);
		//lv=(ListView) view.findViewById(R.id.lv);
//		
//		
//		iv5=(ImageView) view.findViewById(R.id.iv_biaoshi);
//		ll_xiangqing=(LinearLayout) view.findViewById(R.id.ll_xiangqing);
//		ll_xiangqing.setOnClickListener(this);
//		
//		bu_goumai=(Button) view.findViewById(R.id.bu_goumai);
//		bu_goumai.setOnClickListener(this);
//		prs=(PullToRefreshScrollView) view0.findViewById(R.id.prs);
//		prs.setPullLoadEnabled(false);
//		prs.setScrollLoadEnabled(false);
//		prs.getRefreshableView();
//		prs.setOnRefreshListener(new OnRefreshListener<ScrollView>() 
//		{
//
//			@Override
//			public void onPullDownToRefresh(
//					PullToRefreshBase<ScrollView> refreshView) {
//				    //prs.onPullDownRefreshComplete();
//				addData();
//			}
//
//			@Override
//			public void onPullUpToRefresh(
//					PullToRefreshBase<ScrollView> refreshView) {
//				// TODO Auto-generated method stub
//				
//			}
//		});
//		
//		sv=prs.getRefreshableView();
//		sv.addView(view);
//		
//		addView(view0);
//		
//		
//		prs.doPullRefreshing(false,0);
	}
	  ViewRullUtil vpu;
	    /**
	     * listview父布局
	     */
	    LinearLayout ll2;
	    String id;    
	public void addData(String id)
	{      
		   this.id=id;
		   ll_pull=(LinearLayout) findViewById(R.id.ll_pull);
	       vpu=new ViewRullUtil(down, up);
	      
	       XiangMuXinXiAdapter xa=new XiangMuXinXiAdapter(act);
	      
		   vpu.adapter=xa;
	       ll2=vpu.initView(act,null);
		   ll_pull.removeAllViews();
		   ll_pull.addView(ll2);
		   vpu.init(1);
		   vpu.prl.doPullRefreshing(true,200);
	
	}
	   ICallBack icb_jy_up=new ICallBack() {
			
			@Override
			public void back(Object obj) {
				if(obj!=null)
				{    
					 getXiangMuXinXi_data gd=(getXiangMuXinXi_data) obj;
					 vpu.doUpCompelete(gd.body.debtList);
				}
				else
				{
				   vpu.doDownCompelete(null);
				   if(vpu.adapter.list.size()==0)
				   {
					 NoneData nd=new NoneData();
					 nd.setView(act,ll_pull,7);
				   }
				}
			}
		};
		   ICallBack icb_jy_down=new ICallBack() {
				
				@Override
				public void back(Object obj) {
					if(obj!=null)
					{
						getXiangMuXinXi_data gd=(getXiangMuXinXi_data) obj;
						
						 vpu.doDownCompelete(gd.body.debtList);

					}else
					{
						vpu.doDownCompelete(null);
						if(vpu.adapter.list.size()==0)
						   {
							 NoneData nd=new NoneData();
							 nd.setView(act,ll_pull,7);
						   }
					}
					
				}
			};
ICallBack down=new ICallBack() {
		
		@Override
		public void back(Object obj) {
	
			act.initData2(null);
			
			getXiangMuXinXiParam gxx=new getXiangMuXinXiParam();
			gxx.id=id;
			gxx.page=vpu.size+"";
			gxx.size=vpu.start+"";
			
			TongYongManager2 tym2=new TongYongManager2(act, icb_jy_down,gxx);
			tym2.start();
			
			  
		}
	};
	ICallBack up=new ICallBack() {
		
		@Override
		public void back(Object obj) {

			getXiangMuXinXiParam gxx=new getXiangMuXinXiParam();
			gxx.id=id;
			gxx.page=vpu.size+"";
			gxx.size=vpu.start+"";
			
			TongYongManager2 tym2=new TongYongManager2(act, icb_jy_up,gxx);
			tym2.start();
		}
	};
	
	
	
	public Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if(tv1!=null)
			{
				tv1.doScroll(0);
			}
			
		};
	};
	@Override
	public void onClick(View v) {
		
		boolean do_success=BeikBankApplication.mSharedPref.getSharePrefBoolean(BeikBankConstant.DO_SUCCESS,false);
		switch(v.getId())
		{
		//购买
		case R.id.bu_goumai:
		
			break;
	   //详情
		case R.id.ll_xiangqing:
			
			
			
			
	       break;
		}
		
	}
	
		/**
		 * 回调到赎回
		 */
		ICallBack icb5=new ICallBack() {
			
			@Override
			public void back(Object obj) {
			
				if(obj==null)
				{
				
				}
			}
		};
		/**
		 * 回调到选择支付
		 */
		ICallBack icb4=new ICallBack() {
			
			@Override
			public void back(Object obj) {
				if(obj==null)
				{
				
				}
			}
		};
	
	
}
