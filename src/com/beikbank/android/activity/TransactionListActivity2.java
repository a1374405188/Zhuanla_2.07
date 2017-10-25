package com.beikbank.android.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.beikbank.android.activity.help.NoneData;
import com.beikbank.android.adapter.ShouZhiMinXiAdapter2;
import com.beikbank.android.adapter.TransactionRecordsAdapter;
import com.beikbank.android.api.BeikBankApi;
import com.beikbank.android.conmon.FinalIndex;
import com.beikbank.android.conmon.SystemConfig;
import com.beikbank.android.dao.JiaoYiDao;
import com.beikbank.android.dao.TableDaoSimple;
import com.beikbank.android.dao.UserInfoDao;
import com.beikbank.android.data.TranList_Data;
import com.beikbank.android.data.TransactionInfo;
import com.beikbank.android.data.UserInfo;
import com.beikbank.android.data2.getJiaoYiJiLv;
import com.beikbank.android.data2.getJiaoYiJiLv_data;
import com.beikbank.android.data2.getShouZhiMinXi;
import com.beikbank.android.dataparam.TranListParam;
import com.beikbank.android.dataparam2.getJiaoYiJiLvParam;
import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.http.Response;
import com.beikbank.android.net.HandlerBase;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.impl.TongYongManager2;
import com.beikbank.android.net.impl.TranListManager;
import com.beikbank.android.pullrefresh.PullToRefreshBase;
import com.beikbank.android.pullrefresh.PullToRefreshBase.OnRefreshListener;
import com.beikbank.android.pullrefresh.PullToRefreshListView;
import com.beikbank.android.utils.BeikBankConstant;
import com.beikbank.android.utils.Utils;
import com.beikbank.android.utils.ViewRullUtil;
import com.beikbank.android.utils2.StateBarColor;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import comc.beikbank.android.R;

//交易列表
public class TransactionListActivity2 extends BaseActivity1 implements OnClickListener{

	private final String TAG="TransactionListActivity2";
	private TextView titleTv;
	private LinearLayout linear_left;

	//全部，购买，取现
	private TextView tv1,tv2,tv3;
	private View v1,v2,v3;
	private TextView tv4,tv5,tv6;
	
	
	/**
	 * 全部或者活期头部
	 */
	private LinearLayout ll_tol;


	/**
	 * 头部点击布局
	 */
	private LinearLayout ll_ll1;
	/**
	 * 全部
	 */
	private RelativeLayout rl1;
	/**
	 * 活期
	 */
	private RelativeLayout rl2;
	/**
	 * 定期
	 */
	private RelativeLayout rl3;
	/**
	 * 选择菜单
	 */
	private LinearLayout ll_ll2;
	

	/**
	 * 展开状态图标
	 */
	private ImageView iv_iv1;
    Activity act=this;
    
    /**
     * 1全部，2购买，3取现
     */
    int index=1;
    /**
     * 0债权1基金以外全部
     */
    String tindex="10";
    
    
    
    Button bu_all;
    Button bu_chongzhi;
    Button bu_tixian;
    Button bu_goumai;
    Button bu_huikuan;
    /**
     * /** 充值1，提现2，还款3，购买4，默认0全部

     */
    String type2;
    /**
     * 1.充值2.银行卡购买3.钱包购买5.提现6.转让 11.网银充值12.网银购买（app查询购买和充值时记得把网银充值和购买选上），啥不传显示全部
     * 13 还款 15还款  16未匹配还钱包,17未匹配还钱包成功,18未匹配还钱包失败

     */
    ArrayList<String>     type=new ArrayList<String>();
    LinearLayout ll_select;
    TextView right;
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
   private UserInfo userInfo;
   ListView lv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_qianbao4_2);
		StateBarColor.init(this,0xffffffff);
		initView();
	    //userInfo=BeikBankApplication.getUserInfo();
	    //initData();
	}
	
	LinearLayout ll;
	TransactionRecordsAdapter tra;

	ArrayList<getJiaoYiJiLv> list;
    private void initData()
    {
        //vpu=new ViewRullUtil(down,up);
    	tra=new TransactionRecordsAdapter(this,null);
    	
//    	vpu.adapter=tra;
//        ll=vpu.initView(this,null);
//    	ll_pull.removeAllViews();
//    	ll_pull.addView(ll);
//        vpu.mListView.setOnItemClickListener(new OnItemClickListener() {
//
//			@Override
//			public void onItemClick(AdapterView<?> parent, View view,
//					int position, long id) {
//				
//				TransactionInfo transactionInfo=(TransactionInfo)vpu.adapter.getItem(position);
//				Intent intent=null;
//				if(FinalIndex.jiaoyi_type4.equals(transactionInfo.productType)||"".equals(transactionInfo.productType))
//				{
//				   intent=new Intent(TransactionListActivity2.this,TransactionDetailActivity.class);
//				}
//				else
//				{
//					intent=new Intent(TransactionListActivity2.this,TransactionDQDetailActivity.class);
//				}
//				
//				intent.putExtra(BeikBankConstant.INTENT_TRANSACTION, transactionInfo);
//				startActivity(intent);
//				
//			}
//		});
    	//vpu.prl.doPullRefreshing(true,200);
        lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) 
			{
				Intent intent=new Intent(TransactionListActivity2.this,TransactionDQDetailActivity.class);
				getJiaoYiJiLv s=list.get(position);
				intent.putExtra("order_id",s.order_id);
				startActivity(intent);
			}
		});
    	
    	
    	
    	
       
//        getJiaoYiJiLvParam gjy=new getJiaoYiJiLvParam();
//        gjy.user_code=BeikBankApplication.getUserCode();
//        gjy.limit="20";
//        gjy.offset="0";
//        TongYongManager2 tym2=new TongYongManager2(this, icb_jy,gjy);
//        
//    	tym2.start();
    }
    ICallBack icb_jy_down=new ICallBack() {
		
		@Override
		public void back(Object obj) {
			if(obj!=null)
			{   
				
				getJiaoYiJiLv_data gd=(getJiaoYiJiLv_data) obj;
				list=gd.body.list;
				
				//List<Object> list0=getList(list);
				
				 vpu.doDownCompelete(list);

			}
			else
			{
				 vpu.doUpCompelete(null);
			}
			if(vpu.adapter.list.size()==0)
			   {
				 NoneData nd=new NoneData();
				 nd.setView(act,ll_pull,5);
			   }
		}
	};
    ICallBack icb_jy_up=new ICallBack() {
		
		@Override
		public void back(Object obj) {
			if(obj!=null)
			{   
			
				getJiaoYiJiLv_data gd=(getJiaoYiJiLv_data) obj;
				list=gd.body.list;
				
				//List<Object> list0=getList(list);
				
				 vpu.doUpCompelete(list);

			}
			else
			{
				 vpu.doUpCompelete(null);
			}
			 if(vpu.adapter.list.size()==0)
			   {
				 NoneData nd=new NoneData();
				 nd.setView(act,ll_pull,5);
			   }
		}
	};
    private List<Object> getList(ArrayList<getJiaoYiJiLv> list)
    {    
    	List<Object> list0=new ArrayList<Object>();
    	for(int i=0;i<list.size();i++)
    	{   
    		TransactionInfo ti=new TransactionInfo();
    		getJiaoYiJiLv gjy=list.get(i);
    		//if(!"1".equals(gjy.trade_type)&&!"5".equals(gjy.trade_type))
    		//{
    		
    		ti.orderNumber=gjy.order_id;
    		ti.transactionAmount=gjy.amount;
    		ti.productID=gjy.product_id;
    		ti.purchaseDate=gjy.create_time;
    		ti.transactionStatus=gjy.order_status;
    		ti.tradeType=gjy.trade_type;
    		ti.productName=gjy.product_name;
    		ti.planAmount=gjy.amount;
    		//ti.termbondPeriod=gjy.
    		list0.add(ti);
    		//}
    	}
    	return list0;
    }
    private void reData()
    {   
    	ll_pull.removeAllViews();
		ll_pull.addView(ll);
    	vpu.adapter.list.clear();
    	vpu.init(40);
    	vpu.prl.doPullRefreshing(true,200);
    }
	public void initView(){
		titleTv = (TextView) findViewById(R.id.titleTv);
		titleTv.setText(getString(R.string.transaction_records));
//		    tv1=(TextView) findViewById(R.id.tv1);
//	        tv2=(TextView) findViewById(R.id.tv2);
//	        tv3=(TextView) findViewById(R.id.tv3);
//	        tv1.setOnClickListener(this);
//	        tv2.setOnClickListener(this);
//	        tv3.setOnClickListener(this);
//          ll_tol=(LinearLayout) findViewById(R.id.ll_top);
//          ll_pull=(LinearLayout) findViewById(R.id.ll_pull);
//          ll_tol.setVisibility(View.GONE);
//          ll_ll1=(LinearLayout) findViewById(R.id.ll_ll1);
//          ll_ll2=(LinearLayout) findViewById(R.id.ll_ll2);
//        iv_iv1=(ImageView) findViewById(R.id.iv_iv1);
		linear_left = (LinearLayout) findViewById(R.id.linear_left);
		linear_left.setVisibility(View.VISIBLE);
		linear_left.setOnClickListener(this);
	//	ll_ll1.setOnClickListener(this);
		
		 right=(TextView) findViewById(R.id.right);
         right.setOnClickListener(this);
         ll_select=(LinearLayout) findViewById(R.id.ll_select);
         ll_select.setOnClickListener(this);
        //lv=(ListView) findViewById(R.id.lv);
		
		
//		rl1=(RelativeLayout) findViewById(R.id.rl1);
//		rl2=(RelativeLayout) findViewById(R.id.rl2);
//		rl3=(RelativeLayout) findViewById(R.id.rl3);
//		rl1.setOnClickListener(this);
//		rl2.setOnClickListener(this);
//		rl3.setOnClickListener(this);
//		
		   bu_all=(Button) findViewById(R.id.bu_all);
	       bu_chongzhi=(Button) findViewById(R.id.bu_chongzhi);
	       bu_tixian=(Button) findViewById(R.id.bu_tixian);
	       bu_goumai=(Button) findViewById(R.id.bu_goumai);
	       bu_huikuan=(Button) findViewById(R.id.bu_huikuan);
	       bu_all.setOnClickListener(this);
	       bu_chongzhi.setOnClickListener(this);
	       bu_tixian.setOnClickListener(this);
	       bu_goumai.setOnClickListener(this);
	       bu_huikuan.setOnClickListener(this);
		
		
		   ll_pull=(LinearLayout) findViewById(R.id.ll_parent);
	       vpu=new ViewRullUtil(down, up);
	      
	       
	       //tra=new TransactionRecordsAdapter(this);
	       ShouZhiMinXiAdapter2 sa2=new ShouZhiMinXiAdapter2(act);
		   vpu.adapter=sa2;
	       ll2=vpu.initView(this,null);
	       vpu.init(0);
		   ll_pull.removeAllViews();
		   ll_pull.addView(ll2);
		   vpu.prl.doPullRefreshing(true,200);
		   
		   
		   
		   vpu.mListView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) 
				{
//					Intent intent=new Intent(TransactionListActivity2.this,TransactionDQDetailActivity.class);
//					getJiaoYiJiLv s=list.get(position);
//					intent.putExtra("order_id",s.order_id);
//					startActivity(intent);
					
					
					
					
					Intent intent=new Intent(TransactionListActivity2.this,ShouZhiMinXiActivity.class);
	 				getJiaoYiJiLv gz=list.get(position);
	 				intent.putExtra("order_id",gz.order_id);
	 				intent.putExtra("product_type_pid",gz.product_type_pid);
	 				//intent.putExtra("yue",gz.remain_amount_after);
	 				startActivity(intent);
				}
			});
	}
	ICallBack down=new ICallBack() {
		
		@Override
		public void back(Object obj) {
	
			
			
			
			
			    getJiaoYiJiLvParam gjy=new getJiaoYiJiLvParam();
		        gjy.user_code=BeikBankApplication.getUserCode();
		        gjy.limit=vpu.size+"";
		        gjy.offset=vpu.start+"";
		        gjy.trade_type=type;
		        TongYongManager2 tym2=new TongYongManager2(act, icb_jy_down,gjy);
		        
		    	tym2.start();
		}
	};
	ICallBack up=new ICallBack() {
		
		@Override
		public void back(Object obj) {

		    getJiaoYiJiLvParam gjy=new getJiaoYiJiLvParam();
	        gjy.user_code=BeikBankApplication.getUserCode();
	        gjy.limit=vpu.size+"";
	        gjy.offset=vpu.start+"";
	        gjy.trade_type=type;
	        TongYongManager2 tym2=new TongYongManager2(act, icb_jy_up,gjy);
	        
	    	tym2.start();
		}
	};
	LinearLayout ll3;
	private boolean doNoNet()
	{   
		boolean isnet=isNetworkConnected(this);
		if(isnet)
		{
			return true;
		}
		if(ll3==null)
		{
			ll3=(LinearLayout) findViewById(R.id.ll3);
		}
		TextView tv=(TextView) ll3.findViewById(R.id.tv_tv);
		tv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				boolean isnet=isNetworkConnected(act);
				if(isnet)
				{
					ll3.setVisibility(View.GONE);
					ll_pull.setVisibility(View.VISIBLE);
					reData();
				}
				else
				{
					HandlerBase.showMsg(act,act.getString(R.string.error_5),0);
				}
			}
		});
		ll3.setVisibility(View.VISIBLE);
		ll_pull.setVisibility(View.GONE);
		return false;
	}
	ICallBack icbd=new ICallBack() {
		
		@Override
		public void back(Object obj) {
			List list=null;
			if(obj!=null&&obj instanceof TranList_Data)
			{
				TranList_Data td=(TranList_Data) obj;
				list=td.data;
				JiaoYiDao.set(list);
			}
			else
			{
				list=JiaoYiDao.get();
				
			}
			if(list==null)
			{  
				vpu.doDownCompelete(null);
				return;
			}
			
				list=selectTrans(list);
				 
				int a=vpu.adapter.list.size();
				if(list.size()==0&&a<=0)
				{
					no_record();
					
				}
				else
				{  
					if(ll!=null)
					{   
						ll_pull.removeAllViews();
						ll_pull.addView(ll);
						
					}
					
				}
				vpu.doDownCompelete(list);
			
			
		}
	};
	
	ICallBack icbu=new ICallBack() {
		
		@Override
		public void back(Object obj) {
			List list=null;
			if(obj!=null&&obj instanceof TranList_Data)
			{
				TranList_Data td=(TranList_Data) obj;
				list=td.data;
				JiaoYiDao.set(list);
			}
			else
			{
				list=JiaoYiDao.get();
			}
			if(list==null)
			{
				vpu.doUpCompelete(null);
				return;
			}
				
			    list=selectTrans(list);
				
				int a=vpu.adapter.list.size();
				if(list.size()==0&&a<=0)
				{
					no_record();
					Log.d("trans","no");
				}
				else
				{  
					if(ll!=null)
					{   
						ll_pull.removeAllViews();
						ll_pull.addView(ll);
						Log.d("trans","no1");
					}
					Log.d("trans","no2");
				}
				vpu.doUpCompelete(list);
			
			
		}
	};
private ICallBack icb=new ICallBack() {
	
	@Override
	public void back(Object obj) {
	
		if(obj!=null&&obj instanceof TranList_Data)
		{   
			TranList_Data td=(TranList_Data) obj;
			ArrayList<TransactionInfo> transactionInfoList=td.data;
			if(transactionInfoList==null|transactionInfoList.size()==0)
			{
				
			}
			if(SystemConfig.isDebug())
				{   
					if(transactionInfoList.size()<=0)
					{
						return;
					}
					TransactionInfo t0=transactionInfoList.get(0);
                    TransactionInfo t1=new TransactionInfo();
					
					TransactionInfo t2=new TransactionInfo();
					TransactionInfo t3=new TransactionInfo();
					TransactionInfo array[]={t1,t2,t3};
					for(int i=0;i<3;i++)
					{
						array[i].orderNumber=t0.orderNumber;
						array[i].productID=t0.productID;
						//array[i].productName=t0.productName;
						array[i].productType=t0.productType;
						array[i].purchaseDate=t0.purchaseDate;
						array[i].tradingID=t0.tradingID;
					}
					t1.transactionStatus="3";
					t1.tradeType="0";
					
					t2.transactionStatus="1";
					t2.tradeType="0";
					
					t3.transactionStatus="2";
					t3.tradeType="0";
					
					
					transactionInfoList.add(t1);
					transactionInfoList.add(t2);
					transactionInfoList.add(t3);
				}

			
		}
	}
};
    private List selectTrans(List list)
    {   
    	if(list==null)
    	{
    		return null;
    	}
    	ArrayList<TransactionInfo> lists=(ArrayList<TransactionInfo>) list;
    	List list2=new ArrayList();
    	for(TransactionInfo ti:lists)
    	{
    		if("2".equals(ti.tradeType)||"3".equals(ti.tradeType))
    		{
    			continue;
    		}
    		list2.add(ti);
    	}
    	
    	return list2;
    }
	protected <T> void startAimActivity(final Class<T> pActClassName) {
		Intent _Intent = new Intent();
		_Intent.setClass(this, pActClassName);
		startActivity(_Intent);
	}
	
    /**
     * 设置菜单样式
     */
    private void setMenu(int index)
    {  
    	if(v1==null)
    	{
    		v1=findViewById(R.id.v1);
    		v2=findViewById(R.id.v2);
    		v3=findViewById(R.id.v3);
    		
    		tv4=(TextView) findViewById(R.id.tv4);
    		tv5=(TextView) findViewById(R.id.tv5);
    		tv6=(TextView) findViewById(R.id.tv6);
    	}
    	if(index==0)
    	{
    		v3.setBackgroundColor(0xffdd2238);
    		tv6.setTextColor(0xffdd2238);
    		
    		v2.setBackgroundColor(0x00dd2238);
    		v1.setBackgroundColor(0x00dd2238);
    		tv5.setTextColor(0xff747474);
    		tv4.setTextColor(0xff747474);
    		
    		
    	}
    	else if(index==1)
    	{
    		v2.setBackgroundColor(0xffdd2238);
    		tv5.setTextColor(0xffdd2238);
    		
    		v1.setBackgroundColor(0x00dd2238);
    		v3.setBackgroundColor(0x00dd2238);
    		tv4.setTextColor(0xff747474);
    		tv6.setTextColor(0xff747474);
    	}
    	else
    	{
    		v1.setBackgroundColor(0xffdd2238);
    		tv4.setTextColor(0xffdd2238);
    		
    		v2.setBackgroundColor(0x00dd2238);
    		v3.setBackgroundColor(0x00dd2238);
    		tv5.setTextColor(0xff747474);
    		tv6.setTextColor(0xff747474);
    	}
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.right:
            if(ll_select.getVisibility()==View.VISIBLE)
            {
            	ll_select.setVisibility(View.GONE);
            	right.setText("筛选");
            }
            else
            {
            	ll_select.setVisibility(View.VISIBLE);
            	right.setText("收起");
            }
			break;
		
		case R.id.ll_ll1:
			if(ll_ll2.getVisibility()==View.VISIBLE)
			{
				ll_ll2.setVisibility(View.GONE);
				iv_iv1.setImageResource(R.drawable.jiaoyijilu_unextends);
			}
			else
			{   
				
				ll_ll2.setVisibility(View.VISIBLE);
				iv_iv1.setImageResource(R.drawable.jiaoyijilu_exteds);
			}
			break;
		case R.id.linear_left:
			BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.re_home,false);
			finish();
			break;
		 case R.id.bu_all:
	        	select2(v.getId());
	        	break;
	        case R.id.bu_chongzhi:	
	        	select2(v.getId());
	        	break;
	        case R.id.bu_goumai:
	        	select2(v.getId());
	        	break;
	        case R.id.bu_huikuan:
	        	select2(v.getId());
	        	break;
	        case R.id.bu_tixian:
	        	select2(v.getId());
	        	break;	 	
		case R.id.tv1:
			tv2.setBackgroundColor(0x00ffffff);
			tv3.setBackgroundColor(0x00ffffff);
			tv2.setTextColor(getResources().getColor(R.color.red6));
			tv3.setTextColor(getResources().getColor(R.color.red6));
			tv1.setTextColor(0xffffffff);
			tv1.setBackgroundResource(R.drawable.bg_transa_list_left);
			index=1;
			
			reData();
			break;
		case R.id.tv2:
			tv1.setBackgroundColor(0x00ffffff);
			tv2.setBackgroundColor(0xffdd2238);
			tv3.setBackgroundColor(0x00ffffff);
			
			tv1.setTextColor(getResources().getColor(R.color.red6));
			tv3.setTextColor(getResources().getColor(R.color.red6));
			tv2.setTextColor(0xffffffff);
			index=2;
			
			reData();
			break;
		case R.id.tv3:
			tv2.setBackgroundColor(0x00ffffff);
			tv1.setBackgroundColor(0x00ffffff);
			tv3.setBackgroundResource(R.drawable.bg_transa_list_right);
			
			tv1.setTextColor(getResources().getColor(R.color.red6));
			tv2.setTextColor(getResources().getColor(R.color.red6));
			tv3.setTextColor(0xffffffff);
			index=3;
			
			reData();
			break;
		}
	}

	
 
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
    private void no_record()
    {       
    	    LinearLayout ll=new LinearLayout(this);
            ll_pull.removeAllViews();
    		View view=getLayoutInflater().inflate(R.layout.activity_no_trad_record,ll,false);
    		ll_pull.addView(view);
    }
    
    private void select2(int index)
	{
		
		 bu_all.setBackgroundResource(R.drawable.btn_selector_button5);
		 bu_chongzhi.setBackgroundResource(R.drawable.btn_selector_button5);
		 bu_goumai.setBackgroundResource(R.drawable.btn_selector_button5);
		 bu_huikuan.setBackgroundResource(R.drawable.btn_selector_button5);
		 bu_tixian.setBackgroundResource(R.drawable.btn_selector_button5);
		 
		 bu_all.setTextColor(0xff666666);
		 bu_chongzhi.setTextColor(0xff666666);
		 bu_goumai.setTextColor(0xff666666);
		 bu_huikuan.setTextColor(0xff666666);
		 bu_tixian.setTextColor(0xff666666);
		 if(index==R.id.bu_all)
		 {   
			 type=new ArrayList<String>();
			
			 bu_all.setBackgroundResource(R.drawable.btn_selector_button3);
			 bu_all.setTextColor(0xffffffff);
			 titleTv.setText("交易记录");
		 }
		 else if(index==R.id.bu_chongzhi)
		 {   
			 type=new ArrayList<String>();
			 type.add("1");
			 type.add("11");
			 bu_chongzhi.setBackgroundResource(R.drawable.btn_selector_button3);
			 bu_chongzhi.setTextColor(0xffffffff);
			 titleTv.setText("充值");
		 }
		 else if(index==R.id.bu_goumai)
		 {   
			 type=new ArrayList<String>();
			 type.add("2");
			 type.add("3");
			 bu_goumai.setBackgroundResource(R.drawable.btn_selector_button3);
			 bu_goumai.setTextColor(0xffffffff);
			 titleTv.setText("购买");
		 }else if(index==R.id.bu_huikuan)
		 {   
			 type=new ArrayList<String>();
			 type.add("13");
			 type.add("15");
			 bu_huikuan.setBackgroundResource(R.drawable.btn_selector_button3);
			 bu_huikuan.setTextColor(0xffffffff);
			 titleTv.setText("回款");
		 }
		 else if(index==R.id.bu_tixian)
		 {   type=new ArrayList<String>();
		     type.add("5");
		 
			 bu_tixian.setBackgroundResource(R.drawable.btn_selector_button3);
			 bu_tixian.setTextColor(0xffffffff);
			 titleTv.setText("提现");
		 }
		 ll_pull.removeAllViews();
			ll_pull.addView(ll2);
		 vpu.adapter.list.clear();
		 vpu.init(0);
		 right.setText("筛选");
     	vpu.prl.doPullRefreshing(true,200);
     	ll_select.setVisibility(View.GONE);
	}
//	JsonHttpResponseHandler getTransactionListInfoHandler = new JsonHttpResponseHandler(){
//
//		@Override
//		public void onStart() {
//			super.onStart();
//		}
//
//		@Override
//		public void onFinish() {
//			super.onFinish();
//				listview_transaction_records.onPullDownRefreshComplete();
//		}
//
//		@Override
//		public void onFailure(Throwable error, String content) {
//			Utils.log(TAG, "onFailure()"+content);			
//		}
//
//		@Override
//		public void onSuccess(Response response) {
//			Utils.log(TAG, "onSuccess()"+response.getResponseString());	
//			Gson gson=new Gson();
//			ArrayList<TransactionInfo> transactionInfoList=gson.fromJson(Utils.getJsonResult(
//					response.getResponseString(), BeikBankConstant.TYPE_JSONDATA), new TypeToken<List<TransactionInfo>>(){  
//			}.getType());
//			if(transactionInfoList.size()==0)
//			{   
//				no_record();
//				return;
//			}
//			String result=Utils.getJsonResult(response.getResponseString(),BeikBankConstant.TYPE_JSONSTATUS);
//			if(result.equals("success")){
//				list.clear();
//			
//				
//				
//				if(SystemConfig.isDebug())
//				{   
//					if(transactionInfoList.size()<=0)
//					{
//						return;
//					}
//					TransactionInfo t0=transactionInfoList.get(0);
//                    TransactionInfo t1=new TransactionInfo();
//					
//					TransactionInfo t2=new TransactionInfo();
//					TransactionInfo t3=new TransactionInfo();
//					TransactionInfo array[]={t1,t2,t3};
//					for(int i=0;i<3;i++)
//					{
//						array[i].orderNumber=t0.orderNumber;
//						array[i].productID=t0.productID;
//						//array[i].productName=t0.productName;
//						array[i].productType=t0.productType;
//						array[i].purchaseDate=t0.purchaseDate;
//						array[i].tradingID=t0.tradingID;
//					}
//					t1.transactionStatus="3";
//					t1.tradeType="0";
//					
//					t2.transactionStatus="1";
//					t2.tradeType="0";
//					
//					t3.transactionStatus="3";
//					t3.tradeType="2";
//					
//					
//					transactionInfoList.add(t1);
//					transactionInfoList.add(t2);
//					transactionInfoList.add(t3);
//				}
//				list.addAll(transactionInfoList);
//				adapter.notifyDataSetChanged();
//			}
//
//		}
//
//	};
//	
}
