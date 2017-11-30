package com.beikbank.android.adapter;

import java.util.List;

import com.beikbank.android.data.UserCapitalInfo2;
import com.beikbank.android.utils.NumberManager;
import coma.beikbank.android.R;



import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * @author yuguohe
 * <p>
 * email:1374405188@qq.com
 * </p>
 *2015-3-26
 * 
 */
public class ZhiChanAdapter extends BaseAdapter
{
	List<?> list;
	Activity act;
	LayoutInflater li;
	public ZhiChanAdapter(List<?> list,Activity act)
	{
		this.list=list;
		this.act=act;
		li=act.getLayoutInflater();
	}
	@Override
	public int getCount() {
		
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		
		return null;
	}

	@Override
	public long getItemId(int position) {
		
		return 0;
	}
    
	TextView tv1;
	TextView tv2;
	TextView tv3;
	TextView tv4;
	TextView tv5;
	TextView tv6;
	/**
	 * 收益率
	 */
	TextView tv66;
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LinearLayout ll=(LinearLayout) li.inflate(R.layout.activity_dingqi_detail1,null);
		if(position==list.size()-1)
		{
			View view=ll.findViewById(R.id.view);
			view.setVisibility(View.VISIBLE);
		}
		UserCapitalInfo2 uc2=(UserCapitalInfo2) list.get(position);
		tv1=(TextView) ll.findViewById(R.id.tv1);
		tv2=(TextView) ll.findViewById(R.id.tv2);
		tv3=(TextView) ll.findViewById(R.id.tv3);
		tv4=(TextView) ll.findViewById(R.id.tv4);
		tv5=(TextView) ll.findViewById(R.id.tv5);
		tv6=(TextView) ll.findViewById(R.id.tv6);
		tv66=(TextView) ll.findViewById(R.id.tv66);
		String s0=NumberManager.getAddString(uc2.yearRate,uc2.extraRate,8);
		s0=NumberManager.getString(s0,"100",2);
		
		tv66.setText(s0+"%");
		tv1.setText(uc2.termbondName);
		String s2=NumberManager.getString(uc2.amount,"1",2);
		s2=NumberManager.getGeshiHua(s2, 2);
		tv2.setText(s2+"元");
		//tv3.setText(getShouyi(uc2.amount,uc2.termbondPeriod,uc2.yearRate,uc2.extraRate)+"元");
		String s1=NumberManager.getString(uc2.predictIncome,"1",2);
		tv3.setText(s1+"元");
		double d=NumberManager.StoD(uc2.expirationCountdown);
		if(d<0)
		{
			tv4.setText("还款中");
		}
		else
		{
			tv4.setText(NumberManager.getString(d+"","1",0)+"天");
		}
		tv5.setText(uc2.countDate);
		tv6.setText(uc2.expirationDate);
		return ll;
	}
	/**
	 * @param s0 购买金额
	 * @param s1 期限
	 * @param s2 收益率
	 * @param s3 加赠收益率
	 * @return 收益率
	 */
    public static String getShouyi(String s0,String s1,String s2,String s3)
    {   
    	String s4=NumberManager.getAddString(s2,s3,8);
    	s4=NumberManager.getDivString(s4,"365",20);
    	s4=NumberManager.getString(s1,s4,20);
    	s4=NumberManager.getString(s0,s4,2);
    	return s4;
    }
}
