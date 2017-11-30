package com.beikbank.android.activity;

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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.beikbank.android.activity.help.GoumaiManager;
import com.beikbank.android.activity.help.LiuChenManager;
import com.beikbank.android.activity.help.NoNetShow;
import com.beikbank.android.adapter.ZhiChanAdapter;
import com.beikbank.android.conmon.SystemConfig;
import com.beikbank.android.data.CheckBank;
import com.beikbank.android.data.CheckBank_data;
import com.beikbank.android.data.DingqiPI;
import com.beikbank.android.data.DingqiPI_data;
import com.beikbank.android.data.FundInfo;
import com.beikbank.android.dataparam.CheckBankParam;
import com.beikbank.android.dataparam.DingqiPIParam;
import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.impl.DingqiPIManager;
import com.beikbank.android.net.impl.TongYongManager;
import com.beikbank.android.utils.BeikBankConstant;
import com.beikbank.android.utils.DialogManager;
import com.beikbank.android.utils.NumberManager;
import com.beikbank.android.utils.ViewDataUtil;
import com.beikbank.android.utils2.StateBarColor;
import coma.beikbank.android.R;



/** 活期产品详情
 * Created by Administrator on 2015/3/16.
 */
public class HuoqiDetailActivity extends BaseActivity1 implements View.OnClickListener
{   
    Activity act=this;
    TextView title;
    /**
     * return
     */
    LinearLayout ll1;
    /**
     * 年化收益率
     */
    TextView tv_tv1;
    /**
     * 加真年化收益率
     */
    TextView tv_tv2;
    /**
     * 安全保障
     */
    TextView tv_tv3;
    /**
     * 理财期限
     */
    TextView tv_tv4;
    /**
     * 起购金额
     */
    TextView tv_tv5;
    /**
     * 起息时间
     */
    TextView tv_tv6;
    /**
     * 返款时间
     */
    TextView tv_tv7;
    /**
     * 返款方式
     */
    TextView tv_tv8;
    
    /**
     * 计算收益收益
     */
    TextView tv_tv9;
    /**
     * 计算收益时间
     */
    TextView tv_tv10;
//    /**
//     * 计算收益金额
//     */
//    EditText et_et1;
    
    /**
     * 产品特性
     */
    RelativeLayout rl_rl1;
    /**
     * 分散债权
     */
    RelativeLayout rl_rl2;
    /**
     * 取现
     */
    Button bu_bu1;
    /**
     * 购买
     */
    Button bu_bu2;
    
    
    //加挣年化收益率父布局
    LinearLayout ll2;
    RelativeLayout rl2;
    /**
	 * 计算器
	 */
	ImageView iv;
    /**
     * 计算收益金额
     */
    EditText et_et1;
    /**
     * 理财天数
     */
    EditText et_et2;
    /**
     * 产品编号
     */
    public static String index="index";
    /**
     * 活期产品
     */
    FundInfo fi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_huoqi);
        StateBarColor.init(this,0xffffffff);
        Intent intent=getIntent();
        fi=(FundInfo) intent.getSerializableExtra(index);
        boolean isnet=isNetworkConnected(this);
        if(fi==null||fi.huancun||!isnet)
        {
        	NoNetShow.show(this,"赚啦活期",null);
        	return;
        }
        init();
        
        
     
    }
    Dialog dialog4;
	/**
	 * 计算器计算按钮
	 */
	Button bu_but3;
	  /**
     * 显示收益计算器
     */
	private void showCountDialog()
	   {
		    LinearLayout ll=new LinearLayout(act);
		    View v=LayoutInflater.from(act).inflate(
	  			   R.layout.count_shouyi2,ll,false);
		    tv_tv9=(TextView) v.findViewById(R.id.tv_tv1);
		    //tv_tv10=(TextView) v.findViewById(R.id.tv_tv2);
		    //tv_tv10.setText("40");
//		    if(SystemConfig.isDebug())
//		    {
//		    	 tv_tv10.setText("30");
//		    }
		    et_et1=(EditText)v.findViewById(R.id.et_et1);
		    et_et2=(EditText)v.findViewById(R.id.et_et2);
		    et_et1.setFilters(new InputFilter[]{new InputFilter.LengthFilter(7)}); 
		    et_et2.setFilters(new InputFilter[]{new InputFilter.LengthFilter(7)}); 
		    bu_but3=(Button) v.findViewById(R.id.bu_bu1);
		    bu_but3.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
				  String s=et_et1.getText().toString();
				  String s2=et_et2.getText().toString();
				  tv_tv9.setText(ZhiChanAdapter.getShouyi(s,s2,fi.rate,"0"));
				}
			});
		    
	        dialog4=DialogManager.getDiaolg1(act, v);
	        dialog4.setCanceledOnTouchOutside(true);
	    	dialog4.show();
	    	et_et1.setFocusable(true);
	   	    et_et1.setFocusableInTouchMode(true);
	   	    et_et1.requestFocus();
	    	Runnable run=new Runnable() {
				
				@Override
				public void run() {	
					InputMethodManager inputManager = (InputMethodManager)et_et1.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
					inputManager.showSoftInput(et_et2, 0);
				}
			};
	        Handler handler=new Handler();
	        handler.postDelayed(run, 500);
	   }
    private void init()
    {
        ll1=(LinearLayout)findViewById(R.id.linear_left);
        title=(TextView)findViewById(R.id.titleTv);
        
        
        
        tv_tv1=(TextView)findViewById(R.id.tv_tv1);
        tv_tv2=(TextView)findViewById(R.id.tv_tv2);

        
        rl_rl1=(RelativeLayout)findViewById(R.id.rl_rl1);
        rl_rl2=(RelativeLayout)findViewById(R.id.rl_rl2);
        
        bu_bu1=(Button)findViewById(R.id.bu_bu1);
        bu_bu2=(Button)findViewById(R.id.bu_bu2);
        
        bu_bu1.setOnClickListener(this);
        bu_bu2.setOnClickListener(this);
        ll1.setOnClickListener(this);
        
        rl_rl1.setOnClickListener(this);
        rl_rl2.setOnClickListener(this);
        title.setText(fi.name);
		tv_tv1.setText(ViewDataUtil.getD1(fi.rate)+"%");
		tv_tv2.setText("+"+ViewDataUtil.getD2("0")+"%");
		
	     rl2=(RelativeLayout)findViewById(R.id.rl2);
	        ll2=(LinearLayout)findViewById(R.id.ll2);
		double d=Double.parseDouble("0");
		if(d<=0)
		{
			ll2.setVisibility(View.GONE);
			rl2.setVisibility(View.GONE);
		}
		
		  double kegou=NumberManager.StoD(fi.fundAmount);
		  if(kegou<=0)
		   {
			   bu_bu2.setText("售罄");
			   bu_bu2.setEnabled(false);
		   }
		   else
		   {
			   bu_bu2.setText("立即购买");
			   bu_bu2.setEnabled(true);
		   }
    }
ICallBack icb11=new ICallBack() {
		
		@Override
		public void back(Object obj) {
          
		Intent	intent=getIntent().setClass(act,RedeemActivity.class);
			intent.putExtra(PurchaseActivity.index,fi);
			act.startActivity(intent);
		}
	};
   public void onClick(View view)
   {  
	   Intent intent=getIntent();
	   boolean do_success=BeikBankApplication.mSharedPref.getSharePrefBoolean(BeikBankConstant.DO_SUCCESS,false);
	   BeikBankApplication.mSharedPref.putSharePrefString(PurchaseActivity.index,fi.sid);
      switch (view.getId())
      {
         case R.id.linear_left:
             finish();
          break;
          
         case R.id.iv:
            showCountDialog();
          break;
         case R.id.bu_bu1:
//             if(!do_success)
//           {
//			  BeikBankApplication.mSharedPref.putSharePrefInteger(BeikBankConstant.HOME_TYPE,
//					2);
//			  intent.setClass(this,LoginRegActivity.class);
//			  startActivity(intent);
//            }
//             else
//             {
//            	 LiuChenManager.StartNext2(this,icb11);
//             }
        	 showCountDialog();
           break;
         case R.id.bu_bu2:
        	  if(!do_success)
              {
   			    BeikBankApplication.mSharedPref.putSharePrefInteger(BeikBankConstant.HOME_TYPE,
   					2);
   			    intent.setClass(this,LoginRegActivity.class);
   			    startActivity(intent);
               }
        	  else
        	  {   
        		  GoumaiManager.goumaiHuoQi(this, fi);
        		  //LiuChenManager.StartNext(this,icb2);
        		  //selectPlay();
        	  }
           break;
         case R.id.rl_rl1:
             intent.setClass(this,RiskControlActivity.class);
             startActivity(intent);
             break;
         case R.id.rl_rl2:
//        	 if(fi!=null)
// 			{
// 			intent.putExtra(ProjectListActivity.index,fi.sid);
// 			intent.setClass(act,ProjectListActivity.class);
// 			act.startActivity(intent);
// 			}
        	 intent.setClass(act,HuoqiZaiquanActivity.class);
        	 act.startActivity(intent);
             break;   
      }
   }
// //选择支付方式
// 	private void selectPlay()
// 	{
// 		LiuChenManager.selectPay(icb4, act);
// 	}
//   ICallBack icb4=new ICallBack() {
//		
//		@Override
//		public void back(Object obj) {
//			 LiuChenManager.StartNext(act,icb2);
//		
//		}
//	};
   ICallBack icb2=new ICallBack() {
	
	@Override
	public void back(Object obj) {
	  if(obj==null)
	  { 
		CheckBankParam cbp=new CheckBankParam();
		cbp.memberID=BeikBankApplication.getUserCode();
		TongYongManager tym=new TongYongManager(act, icb3,cbp);
		tym.start();
		
		
	  }
	}
};
// 判断是否需要绑卡后回调
   ICallBack icb3=new ICallBack() {
	
	@Override
	public void back(Object obj) {
		if(obj!=null)
		{
			CheckBank_data cd=(CheckBank_data) obj;
			CheckBank cb=cd.data;
			if(cb.UserCardLimit.equals("0"))
			{
				Intent intent=new Intent(act,BankBindActivity2.class);
			    startActivity(intent);
			}
			else
			{
				Intent intent=new Intent(act,PurchaseActivity.class);
				intent.putExtra(PurchaseActivity.index,fi);
				startActivity(intent);
			}
		}
		
	}
};
}
