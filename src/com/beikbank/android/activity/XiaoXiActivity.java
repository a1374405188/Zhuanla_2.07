package com.beikbank.android.activity;
//package com.beikbank.android.activity;
//
//
//import java.util.ArrayList;
//import java.util.Date;
//
//import u.aly.cx;
//
//import com.beikbank.android.R;
//import com.beikbank.android.data2.Xiaoxi;
//import com.beikbank.android.utils2.StateBarColor;
//import com.beikbank.android.utils2.TimeUtil;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.widget.LinearLayout;
//import android.widget.TextView;
///**
// * 
// * @author Administrator
// *消息中心
// */
//public class XiaoXiActivity extends BaseActivity1 implements OnClickListener{
//	TextView title;
//    /**
//     * return
//     */
//    LinearLayout ll;
//	private TextView tv_count_jy;
//	private TextView tv_count_xt;
//	private TextView tv_count_fuwu;
//	
//	private TextView tv_jy;
//	private TextView tv_xt;
//	private TextView tv_fuwu;
//	
//	private LinearLayout ll_xiaoxi;
//	private LinearLayout ll_xitong;
//	private LinearLayout ll_fuwu;
//	@Override
//	protected void onCreate(Bundle savedInstanceState) 
//	{
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_xiaoxi);
//		StateBarColor.init(this,0xffffffff);
//		initView();
//		addData();
//	}
//	private void initView()
//	{
//		    ll=(LinearLayout)findViewById(R.id.linear_left);
//	        title=(TextView)findViewById(R.id.titleTv);
//	        title.setText("消息中心");
//	        tv_count_jy=(TextView) findViewById(R.id.tv_count_jy);
//	        tv_count_xt=(TextView) findViewById(R.id.tv_count_xt);
//	        tv_count_fuwu=(TextView) findViewById(R.id.tv_count_fuwu);
//	        
//	        tv_jy=(TextView) findViewById(R.id.tv_jiaoyi);
//	        tv_xt=(TextView) findViewById(R.id.tv_gonggao);
//	        tv_fuwu=(TextView) findViewById(R.id.tv_fuwu);
//	        
//	        
//	        ll_xiaoxi=(LinearLayout) findViewById(R.id.ll_jiaoyi);
//	        ll_xitong=(LinearLayout) findViewById(R.id.ll_xitong);
//	        ll_fuwu=(LinearLayout) findViewById(R.id.ll_fuwu);
//	        
//	        ll.setOnClickListener(this);
//	        ll_xiaoxi.setOnClickListener(this);
//	        ll_xitong.setOnClickListener(this);
//	        ll_fuwu.setOnClickListener(this);
//	}
//	
//	private void addData()
//	{
//		ArrayList<Xiaoxi> list=new ArrayList<Xiaoxi>();
//		Xiaoxi xi=new Xiaoxi();
////		xi.m_msg_category="0";
////		xi.m_msg_content="公告11111111111111111111111111111111111111111111111111111";
////		xi.m_msg_title="公告1";
////		xi.m_send_time="2016-07-25";
////		xi.m_msg_read="0";
////		list.add(xi);
////		xi=new Xiaoxi();
////		xi.m_msg_category="0";
////		xi.m_msg_content="公告21111111111111111111111111111111111111111111111111111";
////		xi.m_msg_title="公告2";
////		xi.m_send_time="2016-07-24";
////		xi.m_msg_read="1";
//		list.add(xi);
//		
//		
//		
//		
//		
//		
//		
//		Xiaoxi xi2=new Xiaoxi();
//		xi2.m_msg_category="1";
//		xi2.m_msg_content="交易2222222222222222222222222222222222222222222222222222";
//		xi2.m_msg_title="交易2";
//		xi2.m_send_time="2016-07-26";
//		xi2.m_msg_read="0";
//		list.add(xi2);
//		
//		Xiaoxi xi3=new Xiaoxi();
//		xi3.m_msg_category="2";
//		xi3.m_msg_content="公告2222222222222222222222222222222222222222222222222222";
//		xi3.m_msg_title="服务2";
//		xi3.m_send_time="2016-07-27";
//		xi3.m_msg_read="0";
//		list.add(xi3);
//		
//		
//		doData(list);
//		updateView();
//		
//	}
//	private ArrayList<Xiaoxi> list_jiaoyi;
//	private ArrayList<Xiaoxi> list_gonggao;
//	private ArrayList<Xiaoxi> list_fuwu;
//	private void doData(ArrayList<Xiaoxi> list)
//	{   
//		list_jiaoyi=new ArrayList<Xiaoxi>();
//		list_gonggao=new ArrayList<Xiaoxi>();
//		list_fuwu=new ArrayList<Xiaoxi>();
//		for(Xiaoxi xi:list)
//		{
//			if(xi.m_msg_category.equals("0"))
//			{
//				list_gonggao.add(xi);
//			}
//			else if(xi.m_msg_category.equals("1"))
//			{
//				list_jiaoyi.add(xi);
//			}
//			else if(xi.m_msg_category.equals("2"))
//			{
//				list_fuwu.add(xi);
//			}
//		} //test
//	}
//	/**
//	 * 更新view
//	 */
//	private void updateView()
//	{
//		int jiaoyi=getNewCount(list_jiaoyi);
//		if(jiaoyi>0)
//		{
//			tv_count_jy.setVisibility(View.VISIBLE);
//			tv_count_jy.setText(jiaoyi+"");
//		}
//		else
//		{
//			tv_count_jy.setVisibility(View.GONE);
//		}
//		String tjiaoyi=getNewMsg(list_jiaoyi);
//		tv_jy.setText(tjiaoyi);
//		
//		
//		int gonggao=getNewCount(list_gonggao);
//		if(gonggao>0)
//		{
//			tv_count_xt.setVisibility(View.VISIBLE);
//			tv_count_xt.setText(gonggao+"");
//		}
//		else
//		{
//			tv_count_xt.setVisibility(View.GONE);
//		}
//		String tgonggao=getNewMsg(list_gonggao);
//		tv_xt.setText(tgonggao);
//		
//		int fuwu=getNewCount(list_fuwu);
//		if(fuwu>0)
//		{
//			tv_count_fuwu.setVisibility(View.VISIBLE);
//			tv_count_fuwu.setText(gonggao+"");
//		}
//		else
//		{
//			tv_count_fuwu.setVisibility(View.GONE);
//		}
//		String tfuwu=getNewMsg(list_fuwu);
//		tv_fuwu.setText(tfuwu);
//	}
//	/**
//	 * 得到未读消息的数量
//	 */
//	private int getNewCount(ArrayList<Xiaoxi> list)
//	{   
//		int count=0;
//		for(Xiaoxi xi:list)
//		{
//			if(xi.m_msg_read.equals("0"))
//			{
//				count++;
//			}
//		}
//		return count;
//	}
//	/**
//	 * 
//	 * @param list
//	 * @return
//	 */
//	private String getNewMsg(ArrayList<Xiaoxi> list)
//	{
//		String s="";
//		Xiaoxi cxi=null;
//		for(int i=0;i<list.size();i++)
//		{
//			if(i==0)
//			{
//				cxi=list.get(i);
//			}
//			else
//			{   
//				Xiaoxi nxi=list.get(i);
//				Date cdate=TimeUtil.getDate(cxi.m_send_time);
//				
//				Date ndate=TimeUtil.getDate(nxi.m_send_time);
//				if(!cdate.after(ndate))
//				{
//					cxi=nxi;
//				}
//			}
//		}
//		s=cxi.m_msg_title;
//		return s;
//	}
//	@Override
//	public void onClick(View v) 
//	{
//	    Intent intent=new Intent();
//		switch (v.getId()) 
//		{
//		case R.id.linear_left:
//			finish();
//			break;
//		case R.id.ll_xiaoxi:
//			
//			break;
//        case R.id.ll_xitong:
//			intent.setClass(this,XiTongGGActivity.class);
//			intent.putExtra("index",list_gonggao);
//			startActivity(intent);
//			break;
//        case R.id.ll_fuwu:
//			
//			break;
//		default:
//			break;
//		}
//	}
//
//}
