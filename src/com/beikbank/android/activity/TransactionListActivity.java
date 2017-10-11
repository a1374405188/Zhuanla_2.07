package com.beikbank.android.activity;
//package com.beikbank.android.activity;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.widget.AdapterView;
//import android.widget.AdapterView.OnItemClickListener;
//import android.widget.LinearLayout;
//import android.widget.ListView;
//import android.widget.TextView;
//
//import com.beikbank.android.R;
//import com.beikbank.android.adapter.TransactionRecordsAdapter;
//import com.beikbank.android.api.BeikBankApi;
//import com.beikbank.android.conmon.SystemConfig;
//import com.beikbank.android.dao.TableDaoSimple;
//import com.beikbank.android.dao.UserInfoDao;
//import com.beikbank.android.data.TranList_Data;
//import com.beikbank.android.data.TransactionInfo;
//import com.beikbank.android.data.UserInfo;
//import com.beikbank.android.fragment.BeikBankApplication;
//import com.beikbank.android.http.Response;
//import com.beikbank.android.net.ICallBack;
//import com.beikbank.android.net.impl.TranListManager;
//import com.beikbank.android.pullrefresh.PullToRefreshBase;
//import com.beikbank.android.pullrefresh.PullToRefreshBase.OnRefreshListener;
//import com.beikbank.android.pullrefresh.PullToRefreshListView;
//import com.beikbank.android.utils.BeikBankConstant;
//import com.beikbank.android.utils.Utils;
//import com.google.gson.Gson;
//import com.google.gson.reflect.TypeToken;
//
////交易列表
//public class TransactionListActivity extends BaseActivity1 implements OnClickListener{
//
//	private final String TAG="TransactionListActivity";
//	private TextView titleTv;
//	private LinearLayout linear_left;
//	private PullToRefreshListView listview_transaction_records;
//	private ListView mListView;
//	private TransactionRecordsAdapter adapter;
//	//全部
//	private ArrayList<TransactionInfo> list1=new ArrayList<TransactionInfo>();
//	/**
//	 * 购买
//	 */
//	private ArrayList<TransactionInfo> list2=new ArrayList<TransactionInfo>();
//	/**
//	 * 取现
//	 */
//	private ArrayList<TransactionInfo> list3=new ArrayList<TransactionInfo>();
//	
//	/**
//	 * 当前使用的数据
//	 */
//	private ArrayList<TransactionInfo> list=new ArrayList<TransactionInfo>();
//	//全部，购买，取现
//	private TextView tv1,tv2,tv3;
//	
//	private LinearLayout ll;
//    int start1=0;
//    int end1=19;
//    int start2=0;
//    int end2=19;
//    int start3=0;
//    int end3=19;
//    int count=20;
//    Activity act=this;
//    /**
//     * 交易类型 0购买 2取现 10全部
//     */
//    String type="10";
//    /**
//     * 1全部，2购买，3取现
//     */
//    int index=1;
//    UserInfo userInfo;
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_transaction);
//		initView();
//	    userInfo=BeikBankApplication.getUserInfo();
//	}
//	public void initView(){
//		titleTv = (TextView) findViewById(R.id.titleTv);
//		titleTv.setText(getString(R.string.transaction_records));
//        ll=(LinearLayout)findViewById(R.id.ll);
//        
//        tv1=(TextView) findViewById(R.id.tv1);
//        tv2=(TextView) findViewById(R.id.tv2);
//        tv3=(TextView) findViewById(R.id.tv3);
//        
//        tv1.setOnClickListener(this);
//        tv2.setOnClickListener(this);
//        tv3.setOnClickListener(this);
//        
//		linear_left = (LinearLayout) findViewById(R.id.linear_left);
//		linear_left.setVisibility(View.VISIBLE);
//		linear_left.setOnClickListener(this);
//
//		listview_transaction_records=(PullToRefreshListView)findViewById(R.id.listview_transaction_records);
////		listview_transaction_records.setPullLoadEnabled(false);
////		listview_transaction_records.setScrollLoadEnabled(false);
//		listview_transaction_records.setPullLoadEnabled(true);
//		listview_transaction_records.setScrollLoadEnabled(false);
//		listview_transaction_records.setPullRefreshEnabled(false);
//		
//		adapter=new TransactionRecordsAdapter(this);
//		mListView=listview_transaction_records.getRefreshableView();
//		mListView.setDivider(getResources().getDrawable(R.color.line3));
//		mListView.setDividerHeight(1);
//		mListView.setScrollingCacheEnabled(false);
//		mListView.setFadingEdgeLength(0);
//		mListView.setAdapter(adapter);
//		mListView.setOnItemClickListener(new OnItemClickListener() {
//
//			@Override
//			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
//					long arg3) {
//				// TODO Auto-generated method stub
//				TransactionInfo transactionInfo=(TransactionInfo)adapter.getItem(position);
//				Intent intent=new Intent(TransactionListActivity.this,TransactionDetailActivity.class);
//				intent.putExtra(BeikBankConstant.INTENT_TRANSACTION, transactionInfo);
//				startActivity(intent);
//			}
//		});
//		//final UserInfo userInfo=UserInfoDao.getInstance(TransactionListActivity.this).getUserInfo();
//		
//		listview_transaction_records.setOnRefreshListener(new OnRefreshListener<ListView>() {
//			@Override
//			public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
////				BeikBankApi.getInstance().getTransactionListInfo(TransactionListActivity.this,
////						userInfo.getId(),"0","9999", getTransactionListInfoHandler);
////				new TranListManager(act, icb).start(userInfo.getId(), start+"", end+"");
////				start+=count;
////				end+=count;
//				new TranListManager(act, icb).start(userInfo.getId(), start1+"", end1+"",type);
//			}
//
//			@Override
//			public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
//				int a=0;
//				int b=0;
//				if(index==1)
//				{
//					a=start1;
//					b=end1;
//				}
//				else if(index==2)
//				{
//					a=start2;
//					b=end2;
//				}
//				else if(index==3)
//				{
//					a=start3;
//					b=end3;
//				}
//				new TranListManager(act, icb).start(userInfo.getId(),a+"",b+"",type);
//				if(index==1)
//				{
//					start1+=count;
//					end1+=count;
//				}
//				else if(index==2)
//				{
//					start2+=count;
//					end2+=count;
//				}
//				else if(index==3)
//				{
//					start3+=count;
//					end3+=count;
//				}
//			}
//		});
//		listview_transaction_records.doPullRefreshing(true, 200);
//
//	}
//ICallBack icb=new ICallBack() {
//	
//	@Override
//	public void back(Object obj) {
//		listview_transaction_records.onPullUpRefreshComplete();
//		if(obj!=null&&obj instanceof TranList_Data)
//		{   
//			TranList_Data td=(TranList_Data) obj;
//			ArrayList<TransactionInfo> transactionInfoList=td.data;
//			if(transactionInfoList==null|transactionInfoList.size()==0)
//			{
//				no_record();
//			}
//			if(SystemConfig.isDebug())
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
//					t3.transactionStatus="2";
//					t3.tradeType="0";
//					
//					
//					transactionInfoList.add(t1);
//					transactionInfoList.add(t2);
//					transactionInfoList.add(t3);
//				}
//			if(index==1)
//			{
//				list1.addAll(transactionInfoList);
//			}
//			else if(index==2)
//			{
//				list2.addAll(transactionInfoList);
//			}
//			else if(index==3)
//			{
//				list3.addAll(transactionInfoList);
//			}
//			setData();
//		}
//	}
//};
//
//	protected <T> void startAimActivity(final Class<T> pActClassName) {
//		Intent _Intent = new Intent();
//		_Intent.setClass(this, pActClassName);
//		startActivity(_Intent);
//	}
//
//	@Override
//	public void onClick(View v) {
//		// TODO Auto-generated method stub
//		switch(v.getId()){
//		case R.id.linear_left:
//			BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.re_home,false);
//			finish();
//			break;
//			
//		case R.id.tv1:
//			tv2.setBackgroundColor(0x00ffffff);
//			tv3.setBackgroundColor(0x00ffffff);
//			tv2.setTextColor(getResources().getColor(R.color.red6));
//			tv3.setTextColor(getResources().getColor(R.color.red6));
//			tv1.setTextColor(0xffffffff);
//			tv1.setBackgroundResource(R.drawable.bg_transa_list_left);
//			index=1;
//			type="10";
//			switchData();
//			break;
//		case R.id.tv2:
//			tv1.setBackgroundColor(0x00ffffff);
//			tv2.setBackgroundColor(0xffdd2238);
//			tv3.setBackgroundColor(0x00ffffff);
//			
//			tv1.setTextColor(getResources().getColor(R.color.red6));
//			tv3.setTextColor(getResources().getColor(R.color.red6));
//			tv2.setTextColor(0xffffffff);
//			index=2;
//			type="0";
//			switchData();
//			break;
//		case R.id.tv3:
//			tv2.setBackgroundColor(0x00ffffff);
//			tv1.setBackgroundColor(0x00ffffff);
//			tv3.setBackgroundResource(R.drawable.bg_transa_list_right);
//			
//			tv1.setTextColor(getResources().getColor(R.color.red6));
//			tv2.setTextColor(getResources().getColor(R.color.red6));
//			tv3.setTextColor(0xffffffff);
//			index=3;
//			type="2";
//			switchData();
//			break;
//		}
//	}
//   //将数据分类
//   public void initData()
//   {
//	   if(list!=null)
//	   {   
//		   list2=new ArrayList<TransactionInfo>();
//		   list3=new ArrayList<TransactionInfo>();
//		   for(TransactionInfo ti:list)
//		   {
//			   if("0".equals(ti.tradeType))
//			   {
//				   list2.add(ti);
//			   }
//			   else if("2".equals(ti.tradeType))
//			   {
//				   list3.add(ti);
//			   }
//		   }
//	   }
//   }
//	/**
//	 * 
//	 * 
//	 */
//   private void switchData()
//   {
//	   if(index==1)
//	   {
//		   if(list1.size()==0)
//		   {
//			   new TranListManager(act, icb).start(userInfo.getId(), start1+"", end1+"",type);
//		   }
//		   else
//		   {
//			   setData();
//		   }
//	   }
//	   else if(index==2)
//	   {
//		   if(list2.size()==0)
//		   {
//			   new TranListManager(act, icb).start(userInfo.getId(), start2+"", end2+"",type);
//		   }
//		   else
//		   {
//			   setData();
//		   }
//	   }
//	   else if(index==3)
//	   {
//		   if(list3.size()==0)
//		   {
//			   new TranListManager(act, icb).start(userInfo.getId(), start3+"", end3+"",type);
//		   }
//		   else
//		   {
//			   setData();
//		   }
//	   }
//   }
//
//   public void setData()
//   {   
////	   if(list2==null)
////	   {
////		   initData();
////	   }
//	   
//	   if(1==index)
//	   {
//		    //list=list1;
//		    //adapter=new TransactionRecordsAdapter(this,list);
//		    doData(list1);
//			//mListView.setAdapter(adapter);
//	   }
//	   else if(2==index)
//	   {
//		   
//		    doData(list2);
//		    //adapter=new TransactionRecordsAdapter(this,list);
//			//mListView.setAdapter(adapter);
//	   }
//	   else if(3==index)
//	   {
//		    doData(list3);
//		    //adapter=new TransactionRecordsAdapter(this,list);
//			//mListView.setAdapter(adapter);
//	   }
//	   adapter.notifyDataSetChanged();
//	   no_record();
//   }
//   private void doData(List<TransactionInfo> list4)
//   {   
//	   list.clear();
//	   for(TransactionInfo ti:list4)
//	   {
//		   list.add(ti);
//	   }
//   }
//	@Override
//	protected void onDestroy() {
//		// TODO Auto-generated method stub
//		super.onDestroy();
//	}
//    private void no_record()
//    {   
//      if(index==1)
//      {
//    	if(list1==null||list1.size()==0)
//    	{
//    		ll.removeAllViews();
//    		View view=getLayoutInflater().inflate(R.layout.activity_no_trad_record,ll,false);
//    		ll.addView(view);
//    	}
//    	else
//    	{  ll.removeAllViews();
//    		ll.addView(listview_transaction_records);
//    	}
//      }
//      else if(index==2)
//      {
//    	  if(list2==null||list2.size()==0)
//      	{
//      		ll.removeAllViews();
//      		View view=getLayoutInflater().inflate(R.layout.activity_no_trad_record,ll,false);
//      		ll.addView(view);
//      	}
//    	  else
//      	{   ll.removeAllViews();
//      		ll.addView(listview_transaction_records);
//      	}
//      }
//      else if(index==3)
//      {
//    	  if(list3==null||list3.size()==0)
//      	{
//      		ll.removeAllViews();
//      		View view=getLayoutInflater().inflate(R.layout.activity_no_trad_record,ll,false);
//      		ll.addView(view);
//      	}
//    	  else
//      	{   ll.removeAllViews();
//      		ll.addView(listview_transaction_records);
//      	}
//      }
//    }
////	JsonHttpResponseHandler getTransactionListInfoHandler = new JsonHttpResponseHandler(){
////
////		@Override
////		public void onStart() {
////			super.onStart();
////		}
////
////		@Override
////		public void onFinish() {
////			super.onFinish();
////				listview_transaction_records.onPullDownRefreshComplete();
////		}
////
////		@Override
////		public void onFailure(Throwable error, String content) {
////			Utils.log(TAG, "onFailure()"+content);			
////		}
////
////		@Override
////		public void onSuccess(Response response) {
////			Utils.log(TAG, "onSuccess()"+response.getResponseString());	
////			Gson gson=new Gson();
////			ArrayList<TransactionInfo> transactionInfoList=gson.fromJson(Utils.getJsonResult(
////					response.getResponseString(), BeikBankConstant.TYPE_JSONDATA), new TypeToken<List<TransactionInfo>>(){  
////			}.getType());
////			if(transactionInfoList.size()==0)
////			{   
////				no_record();
////				return;
////			}
////			String result=Utils.getJsonResult(response.getResponseString(),BeikBankConstant.TYPE_JSONSTATUS);
////			if(result.equals("success")){
////				list.clear();
////			
////				
////				
////				if(SystemConfig.isDebug())
////				{   
////					if(transactionInfoList.size()<=0)
////					{
////						return;
////					}
////					TransactionInfo t0=transactionInfoList.get(0);
////                    TransactionInfo t1=new TransactionInfo();
////					
////					TransactionInfo t2=new TransactionInfo();
////					TransactionInfo t3=new TransactionInfo();
////					TransactionInfo array[]={t1,t2,t3};
////					for(int i=0;i<3;i++)
////					{
////						array[i].orderNumber=t0.orderNumber;
////						array[i].productID=t0.productID;
////						//array[i].productName=t0.productName;
////						array[i].productType=t0.productType;
////						array[i].purchaseDate=t0.purchaseDate;
////						array[i].tradingID=t0.tradingID;
////					}
////					t1.transactionStatus="3";
////					t1.tradeType="0";
////					
////					t2.transactionStatus="1";
////					t2.tradeType="0";
////					
////					t3.transactionStatus="3";
////					t3.tradeType="2";
////					
////					
////					transactionInfoList.add(t1);
////					transactionInfoList.add(t2);
////					transactionInfoList.add(t3);
////				}
////				list.addAll(transactionInfoList);
////				adapter.notifyDataSetChanged();
////			}
////
////		}
////
////	};
////	
//}
