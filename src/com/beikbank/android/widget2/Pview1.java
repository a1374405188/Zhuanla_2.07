package com.beikbank.android.widget2;

import java.math.BigDecimal;

import com.beikbank.android.activity.HomeActivity3;
import com.beikbank.android.activity.HuodongActivity2;
import com.beikbank.android.activity.HuoqiDetailActivity;
import com.beikbank.android.activity.HuoqiReturnIActivity;
import com.beikbank.android.activity.LoginRegActivity;
import com.beikbank.android.activity.PurchaseActivity;
import com.beikbank.android.activity.help.GoumaiManager;
import com.beikbank.android.activity.help.LiuChenManager;
import com.beikbank.android.data.Biaoshi;
import com.beikbank.android.data.Biaoshi_data;
import com.beikbank.android.data.FundInfo;
import com.beikbank.android.dataparam.BiaoshiParam;
import com.beikbank.android.dataparam2.XiaoxiParam;
import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.ImageUrl;
import com.beikbank.android.net.ManagerParam;
import com.beikbank.android.net.impl.FundInfoManager;
import com.beikbank.android.net.impl.TongYongManager;
import com.beikbank.android.pullrefresh.PullToRefreshBase;
import com.beikbank.android.pullrefresh.PullToRefreshScrollView;
import com.beikbank.android.pullrefresh.PullToRefreshBase.OnRefreshListener;
import com.beikbank.android.utils.BeikBankConstant;
import com.beikbank.android.utils.NumberManager;
import com.beikbank.android.widget.MagicTextView;
import coma.beikbank.android.R;



import android.app.Activity;
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
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * 
 * @author Administrator
 *活期
 */
public class Pview1 extends LinearLayout implements OnClickListener{
	private PullToRefreshScrollView prs;
    private ScrollView sv;
    private Activity act;
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
	public Pview1(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init(context);
	}
	public Pview1(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}
	public void init(Context context)
	{   
		act=(Activity) context;
		LinearLayout ll=new LinearLayout(context);
		View view0 =LayoutInflater.from(context).inflate(R.layout.page_wealth11,ll,false);
		
		View view= LayoutInflater.from(context).inflate(R.layout.pview1,ll,false);
		//YuanView yv=(YuanView) view.findViewById(R.id.yv);
		//yv.init2(act,0,240);
		//yv.setDraw(true);
		WindowManager wm = act.getWindowManager();
	    int width = wm.getDefaultDisplay().getWidth();
		LinearLayout ll_shouyi=(LinearLayout) view.findViewById(R.id.ll_shouyi);
		int top=(width-width/10*7)/2+width/12;
		ll_shouyi.setPadding(0,top,0,0);
		
		ImageView iv=(ImageView) view.findViewById(R.id.iv_img1);
		LayoutParams lp=new LayoutParams(width/10*8,width/10*8);
		iv.setLayoutParams(lp);
		iv.setPadding(0,(width-width/10*7)/2,0,0);
		
		
		tv1=(MagicTextView) view.findViewById(R.id.tv1);
		tv2=(TextView) view.findViewById(R.id.tv2);
		tv3=(TextView) view.findViewById(R.id.tv3);
		tv4=(TextView) view.findViewById(R.id.tv4);
		tv5=(TextView) view.findViewById(R.id.tv5);
		tv14=(TextView) view.findViewById(R.id.tv14);
		
		iv5=(ImageView) view.findViewById(R.id.iv_biaoshi);
		ll_xiangqing=(LinearLayout) view.findViewById(R.id.ll_xiangqing);
		ll_xiangqing.setOnClickListener(this);
		
		bu_goumai=(Button) view.findViewById(R.id.bu_goumai);
		bu_goumai.setOnClickListener(this);
		prs=(PullToRefreshScrollView) view0.findViewById(R.id.prs);
		prs.setPullLoadEnabled(false);
		prs.setScrollLoadEnabled(false);
		prs.getRefreshableView();
		prs.setOnRefreshListener(new OnRefreshListener<ScrollView>() 
		{

			@Override
			public void onPullDownToRefresh(
					PullToRefreshBase<ScrollView> refreshView) {
				    //prs.onPullDownRefreshComplete();
				addData();
			}

			@Override
			public void onPullUpToRefresh(
					PullToRefreshBase<ScrollView> refreshView) {
				// TODO Auto-generated method stub
				
			}
		});
		
		sv=prs.getRefreshableView();
		sv.addView(view);
		
		addView(view0);
		
		
		prs.doPullRefreshing(false,0);
	}
	public void addData()
	{
		new FundInfoManager(act, icb).start();
		
		
		
		/**
		 * 加息标识回调
		 */
		ICallBack icb7=new ICallBack() {
			
			@Override
			public void back(Object obj) {
				if(obj!=null)
				{
					Biaoshi_data bsd=(Biaoshi_data)obj;
					if(bsd!=null)
					{
					  final Biaoshi bs=bsd.data;
					  if(bs!=null&&bs.icon!=null&&!bs.icon.equals(""))
					  { 
						
						iv5.setVisibility(View.VISIBLE);
						if(bs.linkUrl!=null&&!bs.linkUrl.equals(""))
						{   
							iv5.setOnClickListener(new OnClickListener() {
								
								@Override
								public void onClick(View v) {
									Intent intent=new Intent(act,HuodongActivity2.class);
									intent.putExtra("url",bs.linkUrl);
									intent.putExtra("title",bs.title);
									act.startActivity(intent);
								}
							});
							
						}
					    ImageUrl iu=new ImageUrl(iv5,bs.icon);
					    iu.start();
					  }
					}
				}
			}
		};
		//标识部分
//		BiaoshiParam bp=new BiaoshiParam();
//		bp.type="1";
//		ManagerParam mp=new ManagerParam();
//		mp.isShowMsg=false;
//		mp.isShowDialog=false;
//		TongYongManager tym2=new TongYongManager(act, icb7,bp,mp);
//		tym2.start();
		
		
//		   
//		   ICallBack icb=new ICallBack() {
//			
//			@Override
//			public void back(Object obj) {
//				// TODO Auto-generated method stub
//				
//			}
//		};
//		   XiaoxiParam xp=new XiaoxiParam();
//		   xp.req_time="2016-02-06";
//		   xp.type="";
//		   xp.userid=BeikBankApplication.getUserid();
//		   
//		   TongYongManager tmy=new TongYongManager(act, icb,xp);
		   //tmy.start();
	}
	/**
	 * 活期产品
	 */
	public FundInfo fundInfo;
	   public ICallBack icb=new ICallBack() {
		
		@Override
		public void back(Object obj) {
			prs.onPullDownRefreshComplete();
			if(obj!=null&&obj instanceof FundInfo)
			{ 
			    fundInfo=(FundInfo) obj;
			    HomeActivity3.ha.fi=fundInfo;
				BeikBankApplication.getInstance().getSharedPref().putSharePrefString(BeikBankConstant.FUNDINFO_SID,fundInfo.sid);
				
				String rate_info=fundInfo.getRate().equals("")?"0":fundInfo.getRate();
				
				BigDecimal bd_rate=new BigDecimal(Double.parseDouble(rate_info)*100);
				bd_rate=bd_rate.setScale(2,BigDecimal.ROUND_UP);
				
				String s1=NumberManager.getString(rate_info,"100",2);
				if(tv1!=null)
				{
					tv1.setValue(Double.parseDouble(s1));
				}
				
				mHandler.sendEmptyMessageDelayed(0, 0);
				
			
				
				
				BeikBankApplication.mSharedPref.putSharePrefString(BeikBankConstant.PRODUCT_RATE, 
								s1);
				
				sid=fundInfo.getSid();

				
		        //累计投资
				

				String s0=NumberManager.getString(fundInfo.totalInvestment,"1",0);

				if(tv3!=null)
				{
				  tv3.setText(NumberManager.getGeshiHua(s0, 0));
				}
				//累计收益

				String s=NumberManager.getString(fundInfo.totalIncome,"1",0);

				if(tv2!=null)
				{
				  tv2.setText(NumberManager.getGeshiHua(s, 0));
				}
				
				String s6=NumberManager.getString(fundInfo.totalInvestors,"1",0);
				tv4.setText(s6);
				//s6=NumberManager.getString(fundInfo.getTotalProjects(),"1",0);
				//tv4.setText(s6);
				
				//万份收益

				
				String s3=NumberManager.getString(fundInfo.getRate(),"10000",4);
				s3=NumberManager.getDivString(s3,"365",2);

				if(tv5!=null)
				{
					//tv5.setText("万分收益"+s3+"元");
					Pview3.setTextColor(tv5,"万份收益"+s3+"元");
				}
			   tv14.setText("剩余可购金额"+NumberManager.getGeshiHua(fundInfo.fundAmount,0)+"元");
			   double kegou=NumberManager.StoD(fundInfo.fundAmount);
			   if(kegou<=0)
			   {
				   bu_goumai.setText("售罄");
				   bu_goumai.setEnabled(false);
			   }
			   else
			   {
				   bu_goumai.setText("立即购买");
				   bu_goumai.setEnabled(true);
			   }

			}

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
			BeikBankApplication.mSharedPref.putSharePrefInteger(BeikBankConstant.huo_ding,
					1);
			if(!do_success){
				BeikBankApplication.mSharedPref.putSharePrefInteger(BeikBankConstant.HOME_TYPE,
						2);
				startAimActivity(LoginRegActivity.class);
			}else{
               if(fundInfo!=null)
				//toTntent(1);
            	 // selectPlay();
            	   GoumaiManager.goumaiHuoQi(act, fundInfo);
			}
			break;
	   //详情
		case R.id.ll_xiangqing:
			if(fundInfo!=null)
			{
			  intent=new Intent();
			  intent.putExtra(HuoqiDetailActivity.index,fundInfo);
			  intent.setClass(act,HuoqiDetailActivity.class);
			  act.startActivity(intent);
			}
			
			
			
	       break;
		}
		
	}
	//选择支付方式
		private void selectPlay()
		{
			LiuChenManager.selectPay(icb3, act,false);
		}
		/**
		 * 回调到赎回
		 */
		ICallBack icb5=new ICallBack() {
			
			@Override
			public void back(Object obj) {
			
				if(obj==null)
				{
				 Intent intent=new Intent();
				 intent.setClass(act,HuoqiReturnIActivity.class);
				 intent.putExtra("index",fundInfo);
				 //intent.putExtra("index1",act.vpl.page1.uc.fundAmount);
				 act.startActivity(intent);
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
				//toTntent(1);
				  selectPlay();
				}
			}
		};
		Intent intent=new Intent();
		/**
		 * 回调到购买
		 */
		ICallBack icb3=new ICallBack() {
			
			@Override
			public void back(Object obj) {
				if(obj==null)
				{
					  intent.setClass(act,PurchaseActivity.class);
					  intent.putExtra(PurchaseActivity.index,fundInfo);
					  act.startActivity(intent);
				}
			}
		};
	public void toTntent(int flag)
	{  
		BeikBankApplication.mSharedPref.putSharePrefString(PurchaseActivity.index,fundInfo.sid);
		if(flag==0){//0跳到取现
			
			LiuChenManager.StartNext(act,icb5);
		}else{//1跳到购买
			
			LiuChenManager.StartNext(act,icb4);
		}
	}
	 protected <T> void startAimActivity(final Class<T> pActClassName) 
	 {
			Intent _Intent = new Intent();
			_Intent.setClass(act, pActClassName);
			act.startActivity(_Intent);
	 }
}
