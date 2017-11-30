package com.beikbank.android.utils.hongbao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.beikbank.android.activity.DingdanConfimActivity2;
import com.beikbank.android.data.Hongbao;
import com.beikbank.android.data2.getAllYouHuiQuan;
import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.sharedpref.SharePrefConstant;
import com.beikbank.android.utils.BeikBankConstant;
import com.beikbank.android.utils.DensityUtil;
import com.beikbank.android.utils.NumberManager;
import coma.beikbank.android.R;


/**
 * 红包工具2 购买时选择红包
 * @author Administrator
 *
 */
public class HongbaoUtil2_2_v2 {
	Activity act;
	/***
	 * 选中的优惠券
	 */
	List<getAllYouHuiQuan> list1=new ArrayList<getAllYouHuiQuan>();
	ArrayList<View> listView=new ArrayList<View>();
	ArrayList<String> listI=new ArrayList<String>();
	List<getAllYouHuiQuan> list=null;
	TextView tv;
	TextView count;
	/**
	 * 如果为ture全部置灰
	 */
	boolean noHongBao=false;
	public HongbaoUtil2_2_v2(Context act,TextView tv,TextView count)
	{
		this.act=(Activity) act;
		this.tv=tv;
		this.count=count;
	}
	/**
	 * 添加子view
	 * @param ll
	 * @param list
	 */
	public void addView(LinearLayout ll,List<getAllYouHuiQuan> list)
	{   
		this.list=list;
		//final getAllYouHuiQuan hb=null;
		Holder holder =null;
		for(int i=0;i<list.size();i++)
		{
			final getAllYouHuiQuan	hb=list.get(i);
			final String index=i+"";
			//final String usePType=hb.usePType;
			View view=LayoutInflater.from(act).inflate(R.layout.activity_select_hongbao_item, null);
			holder=new Holder();
			holder.tv1=(TextView) view.findViewById(R.id.tv1);
			holder.tv2=(TextView) view.findViewById(R.id.tv2);
			holder.tv3=(TextView) view.findViewById(R.id.tv3);
			holder.tv4=(TextView) view.findViewById(R.id.tv4);
			holder.tv5=(TextView) view.findViewById(R.id.tv5);
			holder.img=(ImageView) view.findViewById(R.id.img);
			holder.ll=(LinearLayout)view.findViewById(R.id.ll);
			holder.ll0=(LinearLayout)view.findViewById(R.id.ll0);
			holder.ll2=(LinearLayout) view.findViewById(R.id.ll2);
//			if(hb.unLimit.equals("0"))
//			{
//				holder.ll.setBackgroundResource(R.drawable.hongbao2);
//			}
//			else
//			{
//				holder.ll.setBackgroundResource(R.drawable.left1);
//			}
//			int a=Integer.parseInt(hb.needCount);
//			if(a>0&&hb.isOld.equals("0"))
//			{
////				holder.ll0.setOnClickListener(new OnClickListener() {
////					
////					@Override
////					public void onClick(View v) {
////						Intent intent3 = new Intent(act, HomeActivity2.class);
////						if(usePType.equals("0"))
////						{
////							
////							intent3.putExtra("page","page4");
////						}
////						else
////						{
////							intent3.putExtra("page","page2");
////						}
////						BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.re_home,true);
////						BeikBankApplication.mSharedPref.putSharePrefString(BeikBankConstant.is_after_hongbao,"page4");
////						intent3.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); 
////						act.startActivity(intent3);
////					}
////				});
//			}
//			else
//			{   
//				if(hb.unLimit.equals("0"))
//				{
//					holder.ll.setBackgroundResource(R.drawable.hongbao4);
//				}
//				else
//				{
//					holder.ll.setBackgroundResource(R.drawable.hongbao12);
//					
//				}
//				
//				
//				if(HongbaoUtil.compreS(hb.needCount,"0")<=0)
//				{   
//					holder.rl.setBackgroundResource(R.drawable.hongbao10);
//				}
//				else
//				{
//					holder.rl.setBackgroundResource(R.drawable.hongbao9);
//				}
//				
//			}
//			DensityUtil du=new DensityUtil(act);
//			view.setPadding(0,du.dip2px(12),0,0);
//			
//			holder.img.setVisibility(View.GONE);
//			String s1=hb.content;
//			JSONObject jo=null;
//			try {
//				jo=new JSONObject(s1);
//				s1=jo.getString("couponAmont");
//			} catch (JSONException e) {
//				
//				e.printStackTrace();
//			}
//			holder.tv1.setText(s1);
//			holder.tv2.setText(hb.name);
//			if(hb.usePType.equals("0"))
//			{
//				holder.tv3.setText("购买"+hb.mustMeet+"元以上定期可用");
//			}
//			else if(hb.usePType.equals("1"))
//			{
//				holder.tv3.setText("购买"+hb.mustMeet+"元以上活期可用");
//			}
//			else
//			{
//				holder.tv3.setText("购买"+hb.mustMeet+"元以上可用");
//			}
//			 int c=HongbaoUtil.compreS(hb.mustMinDay,hb.mustMaxDay);
//				if(c==0)
//				{
//					if(HongbaoUtil.compreS(hb.mustMinDay,"0")==0)
//					{
//					    holder.tv5.setVisibility(View.GONE);
//					}
//					else
//					{
//						holder.tv5.setText("适用期限:仅限"+hb.mustMinDay+"天");
//					}
//				}
//				else
//				{
//					holder.tv5.setText("适用期限:"+hb.mustMinDay+"-"+hb.mustMaxDay+"天");
//					if(hb.mustMaxDay.equals("0"))
//					{
//						holder.tv5.setText("适用期限:"+hb.mustMinDay+"天以上");
//					}
//				}
//				if(!hb.usePType.equals("0"))
//				{
//					 holder.tv5.setVisibility(View.GONE);
//				}	
			holder.tv4.setText("有效期至"+HongbaoUtil.getDate2(hb.expire_date));
			holder.tv2.setText("单笔投资金额"+hb.least_amount+"元及以上可用");
			double d1=NumberManager.StoD(hb.floor_term);
			double d2=NumberManager.StoD(hb.upper_term);
			double d3=NumberManager.StoD(hb.least_amount);
			if(d3==0)
			{
				holder.tv2.setText("单笔投资金额不限");
			}
			if(d1==0&&d2==0)
			{
				holder.tv5.setText("适用期限不限");
			}
			else if(d1==0&&d2>0)
			{
				holder.tv5.setText("适用期限"+hb.upper_term+"天及以下");
			}
			else if(d1>0&&d2==0)
			{
				holder.tv5.setText("适用期限"+hb.floor_term+"天及以上");
			}
			else if(d1>0&&d2>0)
			{
				holder.tv5.setText("适用期限"+hb.floor_term+"天-"+hb.upper_term+"天可用");
			}
			holder.tv1.setText("¥"+hb.coupon_value);
			holder.img.setVisibility(View.VISIBLE);
			if(noHongBao)
			{
				
				holder.ll.setBackgroundResource(R.drawable.img_coupons_failure_left);
//				if("1".equals(hb.usage_status))
//				{
//					holder.ll2.setBackgroundResource(R.drawable.img_coupons_used_right);
//				}
//				else
//				{
//					holder.ll2.setBackgroundResource(R.drawable.img_coupons_failure_right);
//				}
				TextView tv_dikoujin=(TextView) view.findViewById(R.id.tv_dikojin);
				tv_dikoujin.setTextColor(0xffd6d6d6);
				holder.tv1.setTextColor(0xffd6d6d6);
				holder.tv3.setTextColor(0xffcbcbcb);
				holder.tv5.setTextColor(0xffd6d6d6);
				holder.tv4.setTextColor(0xffd6d6d6);
				holder.tv2.setTextColor(0xffcbcbcb);
				holder.img.setVisibility(View.INVISIBLE);
				
			}
			else
			{
			view.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					if(listI.size()>0)
					{
						if(!listI.contains(index))
						{
							Toast.makeText(act,"不可与已选红包叠加使用",Toast.LENGTH_SHORT).show();
							return;
						}
					}
					Object obj=v.getTag();
					ImageView iv=(ImageView) v.findViewById(R.id.img);
					if(obj==null||"0".equals(obj))
					{   
						v.setTag("1");
						iv.setBackgroundResource(R.drawable.img_coupons_choose);
						
						list1.add(hb);
						listI.add(index);
					}
					else
					{   v.setTag("0");
						iv.setBackgroundResource(R.drawable.img_coupons_uncheck);
						delete(hb);
						listI.remove(index);
					}
					setMoney(tv);
					count.setText("共"+list1.size()+"项");
					update();
				}
			});
			}
			ll.addView(view);
			listView.add(view);
		}
		
	}
	public void setNoHongBao(boolean b)
	{
		noHongBao=b;
	}
	private void delete(getAllYouHuiQuan gyh)
	{   
		int index=0;
		for(int i=0;i<list1.size();i++)
		{
			if(list1.get(i).coupon_no.equals(gyh.coupon_no))
			{
				index=i;
				break;
			}
		}
		list1.remove(index);
	}
	public void init(getAllYouHuiQuan gyh)
	{   
		
		int index=0;
		for(int i=0;i<list.size();i++)
		{
			getAllYouHuiQuan gyh1=list.get(i);
			if(gyh1.coupon_no.equals(gyh.coupon_no))
			{
				index=i;
				break;
			}
		}
		if(listView.size()==0)
		{
			return;
		}
		View view=listView.get(index);
		ImageView iv=(ImageView) view.findViewById(R.id.img);
		iv.setBackgroundResource(R.drawable.img_coupons_choose);
		view.setTag("1");
		list1.add(gyh);
		listI.add(index+"");
		setMoney(tv);
		count.setText("共"+list1.size()+"项");
		update();
	}
	private void update()
	{  
		if(listI.size()==0)
		{
		 for(int i=0;i<listView.size();i++)
		 {
			View view=listView.get(i);
			LinearLayout ll=(LinearLayout)view.findViewById(R.id.ll);
			LinearLayout ll2=(LinearLayout)view.findViewById(R.id.ll2);
			ImageView iv=(ImageView) view.findViewById(R.id.img);
			TextView tv1=(TextView) view.findViewById(R.id.tv1);
			TextView tv_dikoujin=(TextView) view.findViewById(R.id.tv_dikojin);
			ll.setBackgroundResource(R.drawable.img_coupons_left);
			ll2.setBackgroundResource(R.drawable.img_coupons_right);
			iv.setBackgroundResource(R.drawable.img_coupons_uncheck);
			tv1.setTextColor(0xff0097e2);
			tv_dikoujin.setTextColor(0xff0097e2);
			
		 }
		}
		else
		{
			
			 for(int i=0;i<listView.size();i++)
			 {  
				
				View view=listView.get(i);
				LinearLayout ll=(LinearLayout)view.findViewById(R.id.ll);
				LinearLayout ll2=(LinearLayout)view.findViewById(R.id.ll2);
				ImageView iv=(ImageView) view.findViewById(R.id.img);
				TextView tv1=(TextView) view.findViewById(R.id.tv1);
				TextView tv_dikoujin=(TextView) view.findViewById(R.id.tv_dikojin);
				if(listI.contains(i+""))
				{
				  ll.setBackgroundResource(R.drawable.img_coupons_left);
				  ll2.setBackgroundResource(R.drawable.img_coupons_right);
				  iv.setBackgroundResource(R.drawable.img_coupons_choose);
				  tv1.setTextColor(0xff0097e2);
					tv_dikoujin.setTextColor(0xff0097e2);
				}
				else
				{
					  ll.setBackgroundResource(R.drawable.img_coupons_failure_left);
					  ll2.setBackgroundResource(R.drawable.img_coupons_right);
					  iv.setBackgroundResource(R.drawable.img_coupons_uncheck);
					  tv1.setTextColor(0xffd6d6d6);
						tv_dikoujin.setTextColor(0xffd6d6d6);
				}
			 }
			
			
			
		}
		
	}
	public void setMoney(TextView tv)
	{   
		String money="0";
		String tag="";
		getAllYouHuiQuan	hb=null;
		for(int i=0;i<list1.size();i++)
		{
			hb=list1.get(i);
			money=NumberManager.getAddString(money,hb.coupon_value,6);
			if(i==0)
			{
				tag=hb.coupon_no;
			}
			else
			{
				tag=tag+","+hb.coupon_no;
			}
		}
		 DingdanConfimActivity2.gyh=hb;
		 BeikBankApplication.mSharedPref.putSharePrefString(SharePrefConstant.hongbao2,tag);
		 BeikBankApplication.mSharedPref.putSharePrefString(SharePrefConstant.hongbao,money);
		tv.setText("¥"+NumberManager.getString("1",money,0));
	}
	
	
	class Holder{
		/**
		 * 父布局
		 */
		LinearLayout ll0;
		/**
		 * 背景图片
		 */
		LinearLayout ll;
		/**
		 * 金额
		 */
		TextView tv1;
		/**
		 * 标题
		 */
		TextView tv2;
		/**
		 * 描述
		 */
		TextView tv3;
		/**
		 * 期限
		 */
		TextView tv4;
		/**
		 * 过期
		 */
		TextView tv5;
		/**
		 * 是否被选中
		 */
		boolean isSelect;
		/**
		 * 选中
		 */
		ImageView img;
		/**
		 * 右边背景
		 */
		LinearLayout ll2;
		
	}
}
