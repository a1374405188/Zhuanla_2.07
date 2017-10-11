package com.beikbank.android.activity;

import java.util.ArrayList;
import java.util.List;

import com.beikbank.android.activity.help.NoneData;
import com.beikbank.android.adapter.QianbaoRecordsAdapter;
import com.beikbank.android.adapter.ShouZhiMinXiAdapter;
import com.beikbank.android.adapter.TransactionRecordsAdapter;
import com.beikbank.android.conmon.FinalIndex;
import com.beikbank.android.data.Qianbao3;
import com.beikbank.android.data.Qianbao3_data;
import com.beikbank.android.data.QianbaoRecord;
import com.beikbank.android.data.QianbaoRecord_data;
import com.beikbank.android.data.TransactionInfo;
import com.beikbank.android.data2.getJiaoYiJiLv;
import com.beikbank.android.data2.getJiaoYiJiLv_data;
import com.beikbank.android.data2.getShouZhiMinXi;
import com.beikbank.android.data2.getShouZhiMinXi_data;
import com.beikbank.android.dataparam.QianbaoParam3;
import com.beikbank.android.dataparam.QianbaoRecordParam;
import com.beikbank.android.dataparam2.getJiaoYiJiLvParam;
import com.beikbank.android.dataparam2.getShouZhiMinXiParam;
import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.impl.Qianbao5Manager;
import com.beikbank.android.net.impl.TongYongManager;
import com.beikbank.android.net.impl.TongYongManager2;
import com.beikbank.android.utils.ViewRullUtil;
import com.beikbank.android.utils2.StateBarColor;
import com.beikbank.android.widget.ClearableEditText;

import comc.beikbank.android.R;

import android.app.Activity;
import android.content.Intent;
import android.opengl.Visibility;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

/**
 *copyright yuguohe
 *email: 1374405188@qq.com
 *2015-5-22
 *收支明细
 */
public class QianbaoActivity4 extends BaseActivity1 implements View.OnClickListener
{   

	TextView title;
	TextView right;
	/**
	 * 全部
	 */
	TextView tv1;
	/**
	 * 充值
	 */
	TextView tv2;
	/**
	 * 取现
	 */
	TextView tv3;
    /**
     * return
     */
    LinearLayout ll;
    /**
     * 下一步
     */
    Button button_next;
    
    Button bu_all;
    Button bu_chongzhi;
    Button bu_tixian;
    Button bu_goumai;
    Button bu_huikuan;
    ClearableEditText et1;
    /**
     * 充值取现记录
     */
    QianbaoRecordsAdapter qra;
    /**
     * 上啦控件
     */
    LinearLayout ll_pull;
    LinearLayout ll2;
    /**
     * 
     */
    ViewRullUtil vpu;
    /**
     * listview父布局
     */
    LinearLayout ll1;
    Activity act=this;
    /**
     * 0购买 1活期赎回，2取现，3充值  4:定期到期(回余额)，5:定期还款(回银行卡)
     * 
     * 1全部2支入3支出
     */
    private int index=1;
    TransactionRecordsAdapter tra;
    ListView lv;
    List<Object> list0;
    /**
     * 1.充值2.银行卡购买3.钱包购买5.提现6.转让 11.网银充值12.网银购买（app查询购买和充值时记得把网银充值和购买选上），啥不传显示全部

     */
    ArrayList<String>     type=new ArrayList<String>();
    /**
     * /** 充值1，提现2，还款3，购买4，默认0全部

     */
    String type2="0";
    
    LinearLayout ll_select;
    ArrayList<getShouZhiMinXi> list;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_qianbao4_2);
		StateBarColor.init(this,0xffffffff);
		initView();
		//initData();
		
		
		
//		
		
	}
	private void initData()
	{
//		vpu=new ViewRullUtil(down, up);
//		QianbaoRecordsAdapter qr=new QianbaoRecordsAdapter(act);
//		vpu.adapter=qr;
//		ll1=vpu.initView(this,null);
//		ll_pull.removeAllViews();
//		ll_pull.addView(ll1);
//		vpu.mListView.setOnItemClickListener(new OnItemClickListener() {
//
//			@Override
//			public void onItemClick(AdapterView<?> parent, View view,
//					int position, long id) {
//				QianbaoRecord qr=(QianbaoRecord) vpu.adapter.getItem(position);
//				Qianbao3 qb3=new Qianbao3();
//				qb3.purchaseDate=qr.dealTime;
//				qb3.transactionAmount=qr.amount;
//				qb3.tradeType=qr.tradeType;
//				qb3.transactionStatus=qr.status;
//				qb3.orderNumber=qr.orderNumber;
//				qb3.productType=qr.productType;
//				Intent intent=new Intent(act,QianbaoActivity5.class);
//				//从钱包跳转过去
//				intent.putExtra("index2",1);
//				intent.putExtra("index",qb3);
//				startActivity(intent);
//			}
//		});
		  ICallBack icb_jy=new ICallBack() {
				
				@Override
				public void back(Object obj) {
					if(obj!=null)
					{
						getJiaoYiJiLv_data gd=(getJiaoYiJiLv_data) obj;
						ArrayList<getJiaoYiJiLv> list=gd.body.list;
						
						list0=getList(list);
					
						tra=new TransactionRecordsAdapter(act,list0);
						lv.setAdapter(tra);
					}
					
				}
			};
	        getJiaoYiJiLvParam gjy=new getJiaoYiJiLvParam();
	        gjy.user_code=BeikBankApplication.getUserCode();
	        gjy.limit="20";
	        gjy.offset="1";
	        TongYongManager2 tym2=new TongYongManager2(this, icb_jy,gjy);
	        
	    	tym2.start();
	    	
	    	
	    	
	    	
	    	
		
	}
	  private List<Object> getList(ArrayList<getJiaoYiJiLv> list)
	    {    
	    	List<Object> list0=new ArrayList<Object>();
	    	for(int i=0;i<list.size();i++)
	    	{
	    		TransactionInfo ti=new TransactionInfo();
	    		getJiaoYiJiLv gjy=list.get(i);
	    		ti.orderNumber=gjy.order_id;
	    		ti.transactionAmount=gjy.amount;
	    		ti.productID=gjy.product_id;
	    		ti.purchaseDate=gjy.create_time;
	    		ti.transactionStatus=gjy.order_status;
	    		ti.tradeType=gjy.trade_type;
	    		ti.productName=gjy.product_name;
	    		ti.planAmount=gjy.amount;
	    		ti.termbondPeriod=gjy.term;
	    		if("1".equals(ti.tradeType)||"5".equals(ti.tradeType)||"3".equals(ti.tradeType)||"6".equals(ti.tradeType)||"11".equals(ti.tradeType)||"12".equals(ti.tradeType))
	    		{
	    			list0.add(ti);
	    		}
	    		
	    	}
	    	return list0;
	    }
	  
	  /**
	   * 选择充值或者提现
	   * @param list
	   * @param type
	   */
	  private List<Object> select(List<Object> list,String type)
	  {   
		  List<Object> list0=new ArrayList<Object>();
		  TransactionInfo t=null;
		  for(Object g:list)
		  {  
			  t=(TransactionInfo) g;
			  if(type.equals(t.tradeType))
			  {
				  list0.add(g);
			  }
		  }
		  return list0;
	  }
	  ICallBack icb_jy_up=new ICallBack() {
			
			@Override
			public void back(Object obj) {
				if(obj!=null)
				{   
					
					//getJiaoYiJiLv_data gd=(getJiaoYiJiLv_data) obj;
					//ArrayList<getJiaoYiJiLv> list=gd.body.list;
					
					//list0=getList(list);
				
//					tra=new TransactionRecordsAdapter(act,list0);
//					lv.setAdapter(tra);
					getShouZhiMinXi_data gd=(getShouZhiMinXi_data) obj;
					list=gd.body;
					vpu.doUpCompelete(gd.body);
				}
				else
				{
					 vpu.doUpCompelete(null);
				}
				if(vpu.adapter.list.size()==0)
				   {
					 NoneData nd=new NoneData();
					 nd.setView(act,ll_pull,6);
				   }
			}
		};	 
ICallBack icb_jy_down=new ICallBack() {
			
			@Override
			public void back(Object obj) {
				if(obj!=null)
				{  
				
					//getJiaoYiJiLv_data gd=(getJiaoYiJiLv_data) obj;
					//ArrayList<getJiaoYiJiLv> list=gd.body.list;
					
					getShouZhiMinXi_data gd=(getShouZhiMinXi_data) obj;
					//list0=getList(list);
				
//					tra=new TransactionRecordsAdapter(act,list0);
//					lv.setAdapter(tra);
					list=gd.body;
					vpu.doDownCompelete(gd.body);
				}
				else
				{
					 vpu.doDownCompelete(null);
				}
				if(vpu.adapter.list.size()==0)
				   {
					 NoneData nd=new NoneData();
					 nd.setView(act,ll_pull,6);
				   }
			}
		};	 	
		
		
	ICallBack down=new ICallBack() {
		
		@Override
		public void back(Object obj) {
			
//			   getJiaoYiJiLvParam gjy=new getJiaoYiJiLvParam();
//		        gjy.user_code=BeikBankApplication.getUserCode();
//		        gjy.limit=vpu.size+"";
//		        gjy.offset=vpu.start+"";
//		        gjy.trade_type=type;
//		        TongYongManager2 tym2=new TongYongManager2(act, icb_jy_down,gjy);
//		        
//		    	tym2.start();
		    	
		    	
		    	
		    	
		    	
		    	getShouZhiMinXiParam gp=new getShouZhiMinXiParam();
				gp.acc_id=BeikBankApplication.getAccid();
				gp.order_type=type2;
				gp.page_num=vpu.start+"";
				gp.page_size=vpu.size+"";
				
				
				TongYongManager2 tym2=new TongYongManager2(act, icb_jy_down,gp);
				tym2.start();
		}
	};
	ICallBack up=new ICallBack() {
		
		@Override
		public void back(Object obj) {
//			 getJiaoYiJiLvParam gjy=new getJiaoYiJiLvParam();
//		        gjy.user_code=BeikBankApplication.getUserCode();
//		        gjy.limit=vpu.size+"";
//		        gjy.offset=vpu.start+"";
//		        gjy.trade_type=type;
//		        TongYongManager2 tym2=new TongYongManager2(act, icb_jy_up,gjy);
//		        
//		    	tym2.start();
		    	
		    	getShouZhiMinXiParam gp=new getShouZhiMinXiParam();
				gp.acc_id=BeikBankApplication.getAccid();
				gp.order_type=type2;
				gp.page_num=vpu.start+"";
				gp.page_size=vpu.size+"";
				
				
				TongYongManager2 tym2=new TongYongManager2(act, icb_jy_up,gp);
				tym2.start();
		}
	};
	private List selectData(List list)
	{   
		List list2=new ArrayList<QianbaoRecord>();
		QianbaoRecord qr=null;
		for(int i=0;i<list.size();i++)
		{
			qr=(QianbaoRecord) list.get(i);
			if(qr.status.equals(FinalIndex.qianbao_type2)||qr.status.equals(FinalIndex.qianbao_type3))
			{
				
				
			}
			else
			{
				continue;
			}
		if(index==1)
		{   
			 list2.add(qr);
		}
		else if(index==2)
		{
			if(qr.tradeType.equals("1")||qr.tradeType.equals("3")||qr.tradeType.equals("4"))
			{
				list2.add(qr);
			}
		}
		else 
		{
			if(qr.tradeType.equals("0")||qr.tradeType.equals("2")||qr.tradeType.equals("5"))
			{
				list2.add(qr);
			}
		}
		}
		return list2;
	}
	ICallBack icbup=new ICallBack() {
		
		@Override
		public void back(Object obj) {
			if(obj!=null)
			{
				QianbaoRecord_data qd=(QianbaoRecord_data) obj;
				List list=qd.data;
				list=selectData(list);
				vpu.doUpCompelete(list);
				int a=vpu.adapter.list.size();
				if(list.size()==0&&a<=0)
				{
					no_record();
				}
				else
				{  
					if(ll1!=null)
					{   
						ll_pull.removeAllViews();
						ll_pull.addView(ll1);
					}
				}
			}
			else
			{
				vpu.doUpCompelete(null);
			}
			
		}
	};
	ICallBack icbdown=new ICallBack() {
		
		@Override
		public void back(Object obj) {
			if(obj!=null)
			{
				QianbaoRecord_data qd=(QianbaoRecord_data) obj;
				List list=qd.data;
				list=selectData(list);
				vpu.doDownCompelete(list);
				int a=vpu.adapter.list.size();
				if(list.size()==0&&a<=0)
				{
					no_record();
				}
				else
				{  
					if(ll1!=null)
					{   
						ll_pull.removeAllViews();
						ll_pull.addView(ll1);
					}
				}
			}
			else
			{
				vpu.doDownCompelete(null);
			}
		}
	};
    private  void initView()
    {
    	 ll=(LinearLayout)findViewById(R.id.linear_left);
         title=(TextView)findViewById(R.id.titleTv);
         title.setText("收支明细");
         ll_pull=(LinearLayout) findViewById(R.id.ll_pull);
         tv1=(TextView) findViewById(R.id.tv1);
         tv2=(TextView) findViewById(R.id.tv2);
         tv3=(TextView) findViewById(R.id.tv3);
         lv=(ListView) findViewById(R.id.lv);
         right=(TextView) findViewById(R.id.right);
         right.setOnClickListener(this);
         ll_select=(LinearLayout) findViewById(R.id.ll_select);
         ll_select.setOnClickListener(this);
         
//         tv2.setText("支入");
//         tv3.setText("支出");
//         tv1.setOnClickListener(this);
//         tv2.setOnClickListener(this);
//         tv3.setOnClickListener(this);
         ll.setOnClickListener(this);
         
         
         
           ll_pull=(LinearLayout) findViewById(R.id.ll_parent);
	       vpu=new ViewRullUtil(down, up);
	      
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
	       //tra=new TransactionRecordsAdapter(this);
	       
	      // QianbaoRecordsAdapter qa=new QianbaoRecordsAdapter(act);
	       ShouZhiMinXiAdapter sa=new ShouZhiMinXiAdapter(act);
		   vpu.adapter=sa;
	       ll2=vpu.initView(this,null);
	       vpu.init(1);
		   ll_pull.removeAllViews();
		   ll_pull.addView(ll2);
		   vpu.prl.doPullRefreshing(true,200);
		   
		   vpu.mListView.setOnItemClickListener(new OnItemClickListener() {

	 			@Override
	 			public void onItemClick(AdapterView<?> parent, View view,
	 					int position, long id) 
	 			{
//	 				Intent intent=new Intent(QianbaoActivity4.this,TransactionDQDetailActivity.class);
//	 				TransactionInfo s=(TransactionInfo) list0.get(position);
//	 				intent.putExtra("ti",s);
//	 				startActivity(intent);
	 				Intent intent=new Intent(QianbaoActivity4.this,ShouZhiMinXiActivity.class);
	 				getShouZhiMinXi gz=list.get(position);
	 				intent.putExtra("order_id",gz.order_seq);
	 				intent.putExtra("product_type_pid",gz.product_type_pid);
	 				intent.putExtra("yue",gz.remain_amount_after);
	 				startActivity(intent);
	 			}
	 		});
    }
    private void reData()
    {   
    	
    	//vpu.doDownCompelete(null);
    	//vpu.doUpCompelete(null);
    	//vpu.adapter.list.clear();
    	
    }
    /**
     * 选择全部，充值，取现
     */
    private void select(int select)
    {   
    	index=select;
    	if(index==1)
    	{
    		tv2.setBackgroundColor(0x00ffffff);
			tv3.setBackgroundColor(0x00ffffff);
			tv2.setTextColor(getResources().getColor(R.color.red6));
			tv3.setTextColor(getResources().getColor(R.color.red6));
			tv1.setTextColor(0xffffffff);
			tv1.setBackgroundResource(R.drawable.bg_transa_list_left);
			
			
			//tra=new TransactionRecordsAdapter(act,list0);
			//lv.setAdapter(tra);
			//vpu.doNew(list0);
    	}
    	else if(index==2)
    	{   
    		tv1.setBackgroundColor(0x00ffffff);
			tv2.setBackgroundColor(0xffdd2238);
			tv3.setBackgroundColor(0x00ffffff);
			
			tv1.setTextColor(getResources().getColor(R.color.red6));
			tv3.setTextColor(getResources().getColor(R.color.red6));
			tv2.setTextColor(0xffffffff);
    		
			//List<Object> list=select(list0,"1");
			//vpu.doNew(list);
    	}
    	else if(index==3)
    	{
    		
			
			
    		tv2.setBackgroundColor(0x00ffffff);
			tv1.setBackgroundColor(0x00ffffff);
			tv3.setBackgroundResource(R.drawable.bg_transa_list_right);
			
			tv1.setTextColor(getResources().getColor(R.color.red6));
			tv2.setTextColor(getResources().getColor(R.color.red6));
			tv3.setTextColor(0xffffffff);
			//List<Object> list=select(list0,"5");
			//vpu.doNew(list);
    	}
    	//reData();
    }
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
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
		case R.id.linear_left:
            finish();
            
			break;
		case R.id.tv1:
			// type=new ArrayList<String>();
            //select(1);
			 vpu.init(1);
            vpu.prl.doPullRefreshing(true,200);
           
			break;
        case R.id.tv2:
        	 type=new ArrayList<String>();
        	 type.add("1");
        	//select(2);
        	 vpu.init(1);
        	vpu.prl.doPullRefreshing(true,200);
			break;
        case R.id.tv3:
//        	 type=new ArrayList<String>();
//        	 type.add("2");
//        	 type.add("3");
//        	 type.add("5");
        	//select(3);
        	 vpu.init(1);
        	vpu.prl.doPullRefreshing(true,200);
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
		default:
			break;
		}
		
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
			 type2="0";
			 bu_all.setBackgroundResource(R.drawable.btn_selector_button3);
			 bu_all.setTextColor(0xffffffff);
			 title.setText("交易记录");
		 }
		 else if(index==R.id.bu_chongzhi)
		 {   
			 type2="1";
			 bu_chongzhi.setBackgroundResource(R.drawable.btn_selector_button3);
			 bu_chongzhi.setTextColor(0xffffffff);
			 title.setText("充值");
		 }
		 else if(index==R.id.bu_goumai)
		 {   
			 type2="4";
			 bu_goumai.setBackgroundResource(R.drawable.btn_selector_button3);
			 bu_goumai.setTextColor(0xffffffff);
			 title.setText("购买");
		 }else if(index==R.id.bu_huikuan)
		 {   
			 type2="3";
			 bu_huikuan.setBackgroundResource(R.drawable.btn_selector_button3);
			 bu_huikuan.setTextColor(0xffffffff);
			 title.setText("回款");
		 }
		 else if(index==R.id.bu_tixian)
		 {   type2="2";
			 bu_tixian.setBackgroundResource(R.drawable.btn_selector_button3);
			 bu_tixian.setTextColor(0xffffffff);
			 title.setText("提现");
		 }
		 ll_pull.removeAllViews();
			ll_pull.addView(ll2);
		 vpu.adapter.list.clear();
		 right.setText("筛选");
		 vpu.init(1);
     	vpu.prl.doPullRefreshing(true,200);
     	ll_select.setVisibility(View.GONE);
	}
	  private void no_record()
	    {       
	    	    LinearLayout ll=new LinearLayout(this);
	            ll_pull.removeAllViews();
	    		View view=getLayoutInflater().inflate(R.layout.activity_no_trad_record,ll,false);
	    		ll_pull.addView(view);
	    }
}
