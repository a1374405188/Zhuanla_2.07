package com.beikbank.android.widget2;

import java.util.ArrayList;
import java.util.List;

import com.beikbank.android.activity.DingqiDetailActivity;
import com.beikbank.android.activity.DingqiGoumaiActivity;
import com.beikbank.android.activity.HomeActivity3;
import com.beikbank.android.activity.help.GoumaiManager;
import com.beikbank.android.activity.help.LiuChenManager;
import com.beikbank.android.activity.help.TypeUtil;
import com.beikbank.android.conmon.FinalIndex;
import com.beikbank.android.data.DingqiP2;
import com.beikbank.android.data.DingqiP_data2;
import com.beikbank.android.dataparam.UserParam;
import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.ManagerParam;
import com.beikbank.android.net.impl.TongYongManager;
import com.beikbank.android.pullrefresh.PullToRefreshBase;
import com.beikbank.android.pullrefresh.PullToRefreshBase.OnRefreshListener;
import com.beikbank.android.pullrefresh.PullToRefreshScrollView;
import com.beikbank.android.utils.DateManager;
import com.beikbank.android.utils.DensityUtil;
import com.beikbank.android.utils.NumberManager;
import com.beikbank.android.utils.PageUtil;
import com.beikbank.android.utils.ViewDataUtil;
import com.beikbank.android.widget.MagicTextView;

import comc.beikbank.android.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * 
 * @author Administrator
 *新手标
 */
public class Pview3 extends LinearLayout  implements OnClickListener{
	private PullToRefreshScrollView prs;
    private ScrollView sv;
    
    /**
     * 年化收益率
     */
    private   MagicTextView tv11;
    /**
     * 加真年化收益率
     */
    private TextView tv12;
    /**
     * 万份收益
     */
    private TextView tv13;
    /**
     * 投资期限
     */
    private TextView tv4;
    /**
     * 起够金额
     */
    private TextView tv5;

    /**
     *剩余可够
     */
    private TextView tv14;
    private 
    /**
     * 选几个月父布局
     */
     LinearLayout ll2;

  
    /**
     * 最高可购
     */
    private TextView tv6;
    private Button bu_bu1;
    Activity act;
    private LinearLayout ll_xiangqing;
    private YuanView yv;
	public Pview3(Context context) {
		super(context);
		init((Activity) context);
	}
	public Pview3(Context context, AttributeSet attrs) {
		super(context, attrs);
		init((Activity) context);
	}
	/**
	 * 初始化视图
	 */
	private void initView(View view)
	{
		tv11=(MagicTextView) view.findViewById(R.id.tv11);
		tv12=(TextView) view.findViewById(R.id.tv12);
		tv13=(TextView) view.findViewById(R.id.tv13);
		tv14=(TextView) view.findViewById(R.id.tv14);
		tv4=(TextView) view.findViewById(R.id.tv4);
		tv6=(TextView) view.findViewById(R.id.tv6);
		bu_bu1=(Button) view.findViewById(R.id.bu_bu1);
		
		ll_xiangqing=(LinearLayout) view.findViewById(R.id.ll_xiangqing);
		bu_bu1.setOnClickListener(this);
		ll_xiangqing.setOnClickListener(this);
	}
	
	private void adddata()
	{
		UserParam up=new UserParam();
		String id=BeikBankApplication.getUserid();
		if(id==null)
		{
			id="";
		}
		up.memberID=id;
        ManagerParam mp=new ManagerParam();
		
		mp.isShowDialog=false;
		TongYongManager tym=new TongYongManager(act, icb6,up,mp);
		tym.start();
	}
	/**
	 * 所有定期产品
	 */
	List<DingqiP2> list2;
	DingqiP2 dp2;
	DingqiP_data2 dpd;
	
ICallBack icb6=new ICallBack() {
		
		@Override
		public void back(Object obj) {
			prs.onPullDownRefreshComplete();
			   if(obj!=null)
			   {
				   dpd=(DingqiP_data2)obj;
				   list2=dpd.data.productBond;
				   if(list2!=null)
				   {
					   for(DingqiP2 dp:list2)
					   {
						   if(dp.termbondType.equals("1"))
						   {
							   dp2=dp;
						   }
					   }
					   yv.drawYiGou(dp2);
					   
					   
					   double d=Double.parseDouble(ViewDataUtil.getD1(dp2.yearRate));
					   tv11.setValue(d);
					   mHandler.sendEmptyMessageDelayed(0, 0);
					   
					   tv4.setText(dp2.termbondPeriod+"天");
					   tv6.setText(dp2.singleAmountLimit+"元");
					   String s2=NumberManager.getString(dp2.extraRate,"100",2);
					   tv12.setText(s2);
					   
					   double dou=Double.parseDouble(dp2.extraRate);
					   if(dou<=0)
					   {
						  tv12.setVisibility(View.INVISIBLE);
					   }
					   else
					   {
						  tv12.setVisibility(View.VISIBLE);
					   }
					   
					   String s0=NumberManager.getAddString(dp2.yearRate,dp2.extraRate,4);
					   String ss=NumberManager.getDivString(s0,"365",10);
					   ss=NumberManager.getString(ss,"10000",2);
					   setTextColor(tv13,"万份收益"+ss+"元");
					 
					   tv14.setText("剩余可购金额"+dp2.remainAmount+"元");
					   double kegou=Double.parseDouble(dp2.remainAmount);
					   if(!dp2.status.equals(FinalIndex.dingqi1)||kegou<=0)
					   {
						   tv14.setText("剩余可购金额"+0+"元");
						   bu_bu1.setEnabled(false);
					   }
					   else
					   {
						   bu_bu1.setEnabled(true);
					   }
				   }
				   
			   }
		}
	};
	
	
	
	public static void setTextColor(TextView tv,String text)
	{
		SpannableStringBuilder builder = new SpannableStringBuilder(text);
		ForegroundColorSpan f1 = new ForegroundColorSpan(0xff5c5c5c);  
		ForegroundColorSpan f2 = new ForegroundColorSpan(0xffe4393c);
		
		
		
		builder.setSpan(f1, 0, 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);  
		builder.setSpan(f2,4,8, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
		builder.setSpan(f1,8,9, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
		tv.setText(builder);
	}
	public void init(Activity act)
	{   
		this.act=act;
		LinearLayout ll=new LinearLayout(act);
		View view0 =LayoutInflater.from(act).inflate(R.layout.page_wealth11,ll,false);
		
		View view= LayoutInflater.from(act).inflate(R.layout.pview3,ll,false);
		
		RelativeLayout rl=(RelativeLayout) view.findViewById(R.id.rl1);
		WindowManager wm = act.getWindowManager();
	    int width = wm.getDefaultDisplay().getWidth();
	    DensityUtil du=new DensityUtil(act);
	    width=width-du.dip2px(16)*2;
		LayoutParams lp=new LayoutParams(width,width);
		lp.leftMargin=du.dip2px(16);
		lp.topMargin=du.dip2px(16);
		//rl.setLayoutParams(lp);
		
		LinearLayout ll_shouyi=(LinearLayout) view.findViewById(R.id.ll_shouyi);
		int top=(width-width/10*7)/2+width/12;
		ll_shouyi.setPadding(0,top,0,0);
		yv=(YuanView) view.findViewById(R.id.yv);
		yv.init2(act,270);
	    
		prs=(PullToRefreshScrollView) view0.findViewById(R.id.prs);
		prs.setPullLoadEnabled(false);
		prs.setScrollLoadEnabled(false);
		prs.getRefreshableView();
		prs.setOnRefreshListener(new OnRefreshListener<ScrollView>() 
		{

			@Override
			public void onPullDownToRefresh(
					PullToRefreshBase<ScrollView> refreshView) {
				    adddata();
			}

			@Override
			public void onPullUpToRefresh(
					PullToRefreshBase<ScrollView> refreshView) {
				
				
			}
		});
		
		sv=prs.getRefreshableView();
		sv.addView(view);
		
		addView(view0);
		
		initView(view0);
		
		 prs.doPullRefreshing(false,0);
	}
	
	//选择支付方式
			private void selectPlay()
			{
				LiuChenManager.selectPay(icb4, act,true);
			}
			//回调选择支付方式
			ICallBack icb5=new ICallBack() {
				
				@Override
				public void back(Object obj) {
					 //LiuChenManager.StartNext(act,icb4);
					if(obj==null)
					{ 
						 	
					          selectPlay();
						   
					}
				}
			};
			
			ICallBack icb4=new ICallBack() {
				
				@Override
				public void back(Object obj) {
					 if(obj==null)
					 {
					   Intent intent=new Intent(act,DingqiGoumaiActivity.class);
					   //intent.putExtra(DingqiDetailActivity.index,dp.termbondCode);
					 //  intent.putExtra(DingqiDetailActivity.index,dp2);
					  
					   ///intent.putExtra("money",act.vpl.page1.uc.fundAmount);
					   
					   act.startActivity(intent);
					 }
				}
			};
		protected void startAimActivity(Class<?> pActClassName) {
			Intent _Intent = new Intent();
			_Intent.setClass(act, pActClassName);
			act.startActivity(_Intent);
		}


		public Handler mHandler = new Handler() {
			public void handleMessage(android.os.Message msg) {
				tv11.doScroll(0);
			};
		};
		@Override
		public void onClick(View v) {
			
			if(dp2==null||dpd==null)
			{
				return;
			}
			switch (v.getId()) {
			//购买
			case R.id.bu_bu1:
				GoumaiManager.goumaiDingQi(act, dp2,dpd.data.userLevel);
				break;
		    //详情		
            case R.id.ll_xiangqing:
            	if(dp2!=null&&dp2.termbondCode!=null)
 			   {   
 				 
 				   Intent intent=new Intent(act,DingqiDetailActivity.class);
 				   //intent.putExtra(DingqiDetailActivity.index,dp.termbondCode);
 				   //dp2.countdown=time+"";
 				   intent.putExtra(TypeUtil.dingdi_data,dp2);
 				   intent.putExtra("index3",dpd.data.userLevel);
 				   act.startActivity(intent);
 			   }
				break;
			default:
				break;
			}
			
		}
		
}
