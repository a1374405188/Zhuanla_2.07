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


import com.beikbank.android.data.Hongbao;
import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.utils.BeikBankConstant;
import com.beikbank.android.utils.DensityUtil;
import com.beikbank.android.utils.NumberManager;
import coma.beikbank.android.R;


/**
 * 红包工具3
 * @author Administrator
 *
 */
public class HongbaoUtil3 {
	Activity act;
	public HongbaoUtil3(Context act)
	{
		this.act=(Activity) act;
	}
	/**
	 * 添加子view
	 * @param ll
	 * @param list
	 */
	public void addView(LinearLayout ll,List<Hongbao> list)
	{   
		 Hongbao hb=null;
		Holder holder =null;
		for(int i=0;i<list.size();i++)
		{
			hb=list.get(i);
			final String usePType=hb.usePType;
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
			holder.rl=(RelativeLayout)view.findViewById(R.id.rl);
			if(hb.unLimit.equals("0"))
			{
				holder.ll.setBackgroundResource(R.drawable.hongbao2);
				holder.ll.setBackgroundResource(R.drawable.hongbao3);
			}
			else
			{
				holder.ll.setBackgroundResource(R.drawable.left1);
				holder.ll.setBackgroundResource(R.drawable.hongbao3);
			}
			int a=Integer.parseInt(hb.needCount);
			if(a>0&&hb.isOld.equals("0"))
			{
//				holder.ll0.setOnClickListener(new OnClickListener() {
//					
//					@Override
//					public void onClick(View v) {
//						Intent intent3 = new Intent(act, HomeActivity2.class);
//						if(usePType.equals("0"))
//						{
//							
//							intent3.putExtra("page","page4");
//						}
//						else
//						{
//							intent3.putExtra("page","page2");
//						}
//						BeikBankApplication.mSharedPref.putSharePrefBoolean(BeikBankConstant.re_home,true);
//						BeikBankApplication.mSharedPref.putSharePrefString(BeikBankConstant.is_after_hongbao,"page4");
//						intent3.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); 
//						act.startActivity(intent3);
//					}
//				});
			}
			else
			{   
				if(hb.unLimit.equals("0"))
				{
					holder.ll.setBackgroundResource(R.drawable.hongbao4);
					holder.ll.setBackgroundResource(R.drawable.hongbao3);
				}
				else
				{
					holder.ll.setBackgroundResource(R.drawable.hongbao12);
					holder.ll.setBackgroundResource(R.drawable.hongbao3);
					
				}
				
				
				if(HongbaoUtil.compreS(hb.needCount,"0")<=0)
				{   
					holder.rl.setBackgroundResource(R.drawable.hongbao10);
				}
				else
				{
					holder.rl.setBackgroundResource(R.drawable.hongbao9);
				}
				
			}
			DensityUtil du=new DensityUtil(act);
			view.setPadding(0,du.dip2px(12),0,0);
			
			holder.img.setVisibility(View.GONE);
			String s1=hb.content;
			JSONObject jo=null;
			try {
				jo=new JSONObject(s1);
				s1=jo.getString("couponAmont");
			} catch (JSONException e) {
				
				e.printStackTrace();
			}
			holder.tv1.setText(s1);
			holder.tv2.setText(hb.name);
			if(hb.usePType.equals("0"))
			{
				holder.tv3.setText("购买"+hb.mustMeet+"元以上定期可用");
			}
			else if(hb.usePType.equals("1"))
			{
				holder.tv3.setText("购买"+hb.mustMeet+"元以上活期可用");
			}
			else
			{
				holder.tv3.setText("购买"+hb.mustMeet+"元以上可用");
			}
			 int c=HongbaoUtil.compreS(hb.mustMinDay,hb.mustMaxDay);
				if(c==0)
				{
					if(HongbaoUtil.compreS(hb.mustMinDay,"0")==0)
					{
					    holder.tv5.setVisibility(View.GONE);
					}
					else
					{
						holder.tv5.setText("适用期限:仅限"+hb.mustMinDay+"天");
					}
				}
				else
				{
					holder.tv5.setText("适用期限:"+hb.mustMinDay+"-"+hb.mustMaxDay);
					if(hb.mustMaxDay.equals("0"))
					{
						holder.tv5.setText("适用期限:"+hb.mustMinDay+"天以上");
					}
				}
				if(!hb.usePType.equals("0"))
				{
					 holder.tv5.setVisibility(View.GONE);
				}	
			holder.tv4.setText("有效期至"+HongbaoUtil.getDate2(hb.endTime));
			ll.addView(view);
		}
		
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
		RelativeLayout rl;
		
	}
}
