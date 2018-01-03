package com.beikbank.android.activity;

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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;


import com.beikbank.android.activity.help.GoumaiManager;
import com.beikbank.android.activity.help.LiuChenManager;
import com.beikbank.android.activity.help.NoNetShow;
import com.beikbank.android.activity.help.TypeUtil;
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
import com.beikbank.android.dataparam.CheckBankParam;
import com.beikbank.android.dataparam.DingqiPIParam;
import com.beikbank.android.dataparam2.CheckJiaoYiMiMaParam;
import com.beikbank.android.dataparam2.HuTouOpenOrCloseParam;
import com.beikbank.android.dataparam2.getDingQiXiangQingParam;
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
import com.beikbank.android.utils2.StateBarColor;
import com.beikbank.android.widget.ClearableEditText;
import com.beikbank.android.widget.SwitchButton;
import coma.beikbank.android.R;



/** 定期详细
 * Created by Administrator on 2015/3/16.
 */
public class DingqiDetailActivity extends BaseActivity1 implements View.OnClickListener
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
     * 募集期限
     */
    TextView tv_tv40;
    /**
     * 起购金额
     */
    TextView tv_tv5;
    /**
     * 起息方式
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
    /**
     * 计算收益金额
     */
    EditText et_et1;
    
    /**
     * 产品特性
     */
    RelativeLayout rl_rl1;
    /**
     * 分散债券
     */
    RelativeLayout rl_rl2;
    /**
     * 已购人数
     */
    RelativeLayout rl_rl3;
    /**
     * 收益计算器
     */
    Button bu_bu1;
    /**
     * 购买
     */
    Button bu_bu2;
    
    //加挣年化收益率父布局
    LinearLayout ll2;
    RelativeLayout rl2;
    RelativeLayout rl_fenxian;
    RelativeLayout rl_touzhi;
    /**
     * 产品编号
     */
    public static String index="index";
    /**
     * 是否可以购买
     */
    public static String index2="index2";
    //是否是新手
    public String index3="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dingqi);
        StateBarColor.init(this,0xffffffff);
        init();
        Intent intent=getIntent();
        String name=intent.getStringExtra("name");
        title.setText("资产详情");
//        boolean isnet=isNetworkConnected(this);
//        if(dp==null||dp.huancun||!isnet)
//        {
//        	NoNetShow.show(this,"赚啦定期",null);
//        	return;
//        }
        
         initData();

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
	/**
	 * 如果点击的是新手标，进行相应的处理 
	 * @return true 不进行后续的代码
	 */
	public  boolean doNew(DingqiP2 dp2,String isNew)
	{
		
		if(dp2.termbondType.equals("1"))
		{
			if(isNew.equals("0"))
			{   
				
			}
			else
			{  
				PageUtil pu=new PageUtil(act,countRate(dp2.yearRate,dp2.extraRate));
				pu.showShapeDialog();
				return true;
			}
		}
		
		return false;
	}
	String assets_id;
	private void initData()
	{   
		assets_id=getIntent().getStringExtra("assets_id");
		String prod_id=getIntent().getStringExtra("prod_id");
		ICallBack icb=new ICallBack() {
			
			@Override
			public void back(Object obj) {
				// TODO Auto-generated method stub
				if(obj!=null)
				{
					getDingQiXiangQing_data gd=(getDingQiXiangQing_data) obj;
					initView(gd);
				}
			}
		};
		getDingQiXiangQingParam gp=new getDingQiXiangQingParam();
		gp.assets_id=assets_id;
		gp.prod_id=prod_id;
		gp.user_id=BeikBankApplication.getUserCode();
		TongYongManager2 tym2=new TongYongManager2(act, icb,gp);
		tym2.start();
	}
	SwitchButton sb;
	private void initView(getDingQiXiangQing_data gd)
	{   
		getDingQiXiangQing g=gd.body;
		TextView tv_name=(TextView)findViewById(R.id.tv_name);
        TextView tv_money=(TextView)findViewById(R.id.tv_money);
        TextView tv_benjin=(TextView)findViewById(R.id.tv_benjin);
        TextView tv_dangqian_shouyi=(TextView)findViewById(R.id.tv_dangqian_shouyi);
        TextView tv_all_shouyi=(TextView)findViewById(R.id.tv_all_shouyi);
        TextView tv_nianhua_shouyi=(TextView)findViewById(R.id.tv_ninahua_shouyi);
        TextView tv_qixian=(TextView)findViewById(R.id.tv_qixian);
        TextView tv_time=(TextView)findViewById(R.id.tv_time);
        TextView tv_daoqi_time=(TextView)findViewById(R.id.tv_daoqi_time);
        TextView tv_jixi=(TextView)findViewById(R.id.tv_jixi);
        TextView tv_jixi_money=(TextView)findViewById(R.id.tv_jixi_money);
        LinearLayout ll_futou=(LinearLayout) findViewById(R.id.ll_futou);
        
        
        TextView tv_jixi_text=(TextView)findViewById(R.id.tv_jixi_text);
        rl_fenxian=(RelativeLayout) findViewById(R.id.rl_fenxian);
        rl_touzhi=(RelativeLayout) findViewById(R.id.rl_touzhi);
        rl_fenxian.setOnClickListener(this);
        rl_touzhi.setOnClickListener(this);
        
        
        sb=(SwitchButton) findViewById(R.id.sb);
       
        String name=getIntent().getStringExtra("name");
        tv_name.setText(name);
        tv_money.setText(NumberManager.getGeshiHua(g.total_payable,2));
        tv_benjin.setText(NumberManager.getGeshiHua(g.current_principal_balance,2)+"元");
        tv_jixi_money.setText(NumberManager.getGeshiHua(g.current_calculate_amount,2)+"元");
        tv_dangqian_shouyi.setText(NumberManager.getGeshiHua(g.intrest_total,2)+"元");
        tv_all_shouyi.setText(NumberManager.getGeshiHua(g.intrest_total_estimate,2)+"元");
       String s1=NumberManager.getString(g.intrest_year,"100",2);
        tv_nianhua_shouyi.setText(s1+"%");
        tv_qixian.setText(g.term+"天");
        tv_time.setText(g.original_calculate_date);
        tv_daoqi_time.setText(g.expired_date);
        tv_jixi.setText(g.remain_return_day+"天后到期");
        tv_jixi_text.setText(g.status);
        if("0".equals(g.is_reinvest))
        {
        	sb.setChecked(true,false);
        }
        
        double d1=Double.parseDouble(g.remain_return_day);
        if(d1<1)
        {
        	 tv_jixi.setVisibility(View.GONE);
        	 tv_jixi_text.setText("还款中");
        	 sb.setEnabled(false);
        	 ll_futou.setOnClickListener(this);
        }
        else if(d1==1)
        {
        	sb.setEnabled(false);
        	ll_futou.setOnClickListener(this);
        }
        else
        {
        	
        	sb.setOnCheckedChangeListener(new OnCheckedChangeListener() {

    			@Override
    			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    createDialog(assets_id);
    			}
    		}); 
        	
        	
        }
       
        if("0".equals(g.is_new_user_mark))
		  {
        	ll_futou.setOnClickListener(new OnClickListener() {
	    			
	    			@Override
	    			public void onClick(View v) {
	    				Toast.makeText(act, "新手专享产品不支持到期复投", Toast.LENGTH_LONG).show();
	    				
	    			}
	    		});
			  sb.setEnabled(false);
		  }
        
	}
    private void init()
    {
        ll1=(LinearLayout)findViewById(R.id.linear_left);
        title=(TextView)findViewById(R.id.titleTv);
        
        ll1.setOnClickListener(this);
        
        
       
    }
//	Dialog dialog4;
//	/**
//	 * 计算器计算按钮
//	 */
//	Button bu_but3;
//    /**
//     * 显示收益计算器
//     */
//   private void showCountDialog()
//   {
//	    LinearLayout ll=new LinearLayout(this);
//	    View v=LayoutInflater.from(this).inflate(
//  			   R.layout.count_shouyi,ll,false);
//	    tv_tv9=(TextView) v.findViewById(R.id.tv_tv1);
//	    tv_tv10=(TextView) v.findViewById(R.id.tv_tv2);
//	    tv_tv10.setText(dpi.termbondPeriod);
////	    if(SystemConfig.isDebug())
////	    {
////	    	 tv_tv10.setText("30");
////	    }
//	    et_et1=(EditText)v.findViewById(R.id.et_et1);
//	    et_et1.setFilters(new InputFilter[]{new InputFilter.LengthFilter(7)}); 
//	    
//	    bu_but3=(Button) v.findViewById(R.id.bu_bu1);
//	    bu_but3.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//			  String s=et_et1.getText().toString();
//			  tv_tv9.setText(ZhiChanAdapter.getShouyi(s,dpi.termbondPeriod,dpi.yearRate,dpi.extraRate));
//			}
//		});
//	    
//        dialog4=DialogManager.getDiaolg1(this, v);
//        dialog4.setCanceledOnTouchOutside(true);
//    	dialog4.show();
//    	et_et1.setFocusable(true);
//   	    et_et1.setFocusableInTouchMode(true);
//   	    et_et1.requestFocus();
//    	Runnable run=new Runnable() {
//			
//			@Override
//			public void run() {	
//				InputMethodManager inputManager = (InputMethodManager)et_et1.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
//				inputManager.showSoftInput(et_et1, 0);
//			}
//		};
//        Handler handler=new Handler();
//        handler.postDelayed(run, 500);
//   }
  
   Dialog   dialog5;
   ClearableEditText et_mima;
   TextView tv_quereng;
	private void createDialog(final String id)
	{
		 LinearLayout ll=new LinearLayout(this);
		    View v=LayoutInflater.from(this).inflate(
	  			   R.layout.check_jiaoyimima,ll,false);
		    
		   et_mima=(ClearableEditText) v.findViewById(R.id.et_mima);
		    et_mima
			.addTextChangedListener(new TextWatcherListener());
		    TextView tv_qvxiao=(TextView) v.findViewById(R.id.tv_qvxiao);
		    tv_quereng=(TextView) v.findViewById(R.id.tv_quereng);
		    tv_error=(TextView) v.findViewById(R.id.tv_error);
		    ll_error=(LinearLayout) v.findViewById(R.id.ll_error);
		    TextView tv_mima=(TextView) v.findViewById(R.id.tv_mima);
		   
		    
		    
		    tv_mima.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent intent=new Intent(act,ForgetPwdRealnameActivity.class);
					intent.putExtra(BeikBankConstant.IS_FORGETTRANSACTIONPWD,true);
					intent.putExtra(BeikBankConstant.INTENT_PHONENUMBER,BeikBankApplication.getPhoneNumber());
					act.startActivity(intent);
					
				}
			});
		    tv_qvxiao.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) 
				{   
					dialog5.dismiss();
				
					if(sb.isChecked())
					{
						sb.setChecked2(false);
					}
					else
					{
						sb.setChecked2(true);
					}
					
					//hander.postDelayed(run,1000);
					
				}
			});
		    tv_quereng.setOnClickListener(new OnClickListener() {
				
						@Override
						public void onClick(View v) {
							
							String s=et_mima.getText().toString();
							if(s!=null&&!"".equals(s))
							{
								checkJiaoyi(sb,s,id);
							}
						}
					});
		    dialog5=DialogManager.getDiaolg1(act, v);
	        dialog5.setCanceledOnTouchOutside(true);
	    	dialog5.show();
	}  
	private void checkJiaoyi(final SwitchButton sb,String passwd,final String id)
	{   
		ICallBack icb=new ICallBack() {
			
			@Override
			public void back(Object obj) {
				if(obj!=null)
				{   
					if(obj instanceof CheckJiaoYiMiMa_data)
					{
						
					
					CheckJiaoYiMiMa_data cd=(CheckJiaoYiMiMa_data) obj;
					if("0000".equals(cd.header.re_code))
					{   
						
					
						
						HuTouOpenOrCloseParam hp=new HuTouOpenOrCloseParam();
						if(sb.isChecked())
						{
							hp.is_reinvest="0";
						}
						else
						{
							hp.is_reinvest="1";
						}
						
						hp.assets_id=id;
						TongYongManager2 tym2=new TongYongManager2(act, icb_hutou,hp);
						tym2.start();
						dialog5.dismiss();
						
					}
					  else
					  {
						  showToast(cd.header.re_msg);
						 
					  }
					}
					
					
				}
				
			}
		};
		CheckJiaoYiMiMaParam cp=new CheckJiaoYiMiMaParam();
		cp.tra_password=MD5.md5s32(passwd);
		cp.user_code=BeikBankApplication.getUserCode();
		TongYongManager2  tym2=new TongYongManager2(this, icb,cp);
		tym2.start();
	}
	ICallBack icb_hutou=new ICallBack() {
		
		@Override
		public void back(Object obj) {
			// TODO Auto-generated method stub
			
		}
	};
	class TextWatcherListener implements TextWatcher {
		@Override
		public void afterTextChanged(Editable s) {
			// TODO Auto-generated method stub

		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			// TODO Auto-generated method stub
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			
			if(s.length()>=6)
			{
				tv_quereng.setTextColor(0xff333333);
			}
			else
			{
				tv_quereng.setTextColor(0xff999999);
			}
		}

	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
   public void onClick(View view)
   {  
	   Intent intent=getIntent();
	   String assets_id=intent.getStringExtra("assets_id");
	   String prod_id=intent.getStringExtra("prod_id");
      switch (view.getId())
      {
      
      case R.id.ll_futou:
    	
    	  Toast.makeText(act, "还款中及倒计时为1天时，不能改变复投状态", Toast.LENGTH_LONG).show();
       break;
         case R.id.linear_left:
             finish();
          break;
       
         case R.id.rl_fenxian:
        	 
      	   String url=SystemConfig.huodong_url+"#!/fxfs/"+assets_id;
      	   intent.setClass(act,HuodongActivity2.class);
      	    intent.putExtra("title","风险分散");
				intent.putExtra("url",url);
				act.startActivity(intent);
        break;
       case R.id.rl_touzhi:
      	 String product_type_pid=getIntent().getStringExtra("product_type_pid");
      	 url=SystemConfig.huodong_url+"#!/bondList/"+assets_id+"/"+product_type_pid;
      	 Log.d("url",url);
      	 
    	   intent.setClass(act,HuodongActivity2.class);
    	    intent.putExtra("title","投资债权");
				intent.putExtra("url",url);
				act.startActivity(intent);
        break;
  
         case R.id.rl_rl1:
             intent.setClass(this,RiskControlActivity.class);
             intent.putExtra(RiskControlActivity.index,"1");
             startActivity(intent);
             break;
         case R.id.sb:
        	createDialog(assets_id);
             break;
  
      }
   }


   Handler hander=new Handler();
   Runnable run=new Runnable() {
	
	@Override
	public void run() {
		if(sb.isChecked())
		{
			sb.setChecked(false,false);
		}
		else
		{
			sb.setChecked(true,false);
		}
		hander.removeCallbacks(run);
	}
};

}
