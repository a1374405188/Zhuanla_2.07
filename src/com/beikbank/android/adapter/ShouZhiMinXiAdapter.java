package com.beikbank.android.adapter;

import java.math.BigDecimal;
import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.beikbank.android.data.ProjectInfo;
import com.beikbank.android.data2.getShouZhiMinXi;
import com.beikbank.android.utils.NumberManager;
import com.beikbank.android.widget2.PageView2.MyAdapter;

import comc.beikbank.android.R;
/**
 * 
 * @author Administrator
 *收支明细
 */
public class ShouZhiMinXiAdapter extends MBaseAdapter
{

	private Context context;
	

	public ShouZhiMinXiAdapter(Context context){
		this.context=context;
		
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		Holder holder =null;
		getShouZhiMinXi gsz=(getShouZhiMinXi) list.get(position);
		if(convertView==null){
			convertView=LayoutInflater.from(context).inflate(R.layout.activity_qianbao4_2_item, null);
			holder=new Holder();
			holder.name=(TextView) convertView.findViewById(R.id.name);
			holder.time=(TextView) convertView.findViewById(R.id.time);
			holder.money=(TextView) convertView.findViewById(R.id.money);
			holder.yue=(TextView) convertView.findViewById(R.id.yue);
			holder.v=convertView.findViewById(R.id.v);
			holder.shuomin=(TextView) convertView.findViewById(R.id.shuomin);
			
			convertView.setTag(holder);
		}else{
			holder=(Holder) convertView.getTag();
		}	
		holder.time.setText(gsz.operation_time);
	    holder.money.setText("-"+NumberManager.getGeshiHua(gsz.amount,2));
		holder.yue.setText("余额 "+NumberManager.getGeshiHua(gsz.remain_amount_after,2));
		if("1".equals(gsz.order_type)||"11".equals(gsz.order_type))
		{
			holder.name.setText("充值");
			holder.money.setText("+"+NumberManager.getGeshiHua(gsz.amount,2));
		}
		else if("5".equals(gsz.order_type))
		{
			//holder.v.setVisibility(View.VISIBLE);
			holder.name.setText("提现");
			//holder.shuomin.setVisibility(View.VISIBLE);
			//holder.shuomin.setText("银行卡支付");
		}
		else if("3".equals(gsz.order_type))
		{   
			holder.name.setText("购买");
			//holder.v.setVisibility(View.VISIBLE);
			//holder.shuomin.setVisibility(View.VISIBLE);
			//holder.shuomin.setText("钱包支付支付");
		}
		else if("13".equals(gsz.order_type)||"15".equals(gsz.order_type))
		{
			holder.name.setText("回款");
			holder.money.setText("+"+NumberManager.getGeshiHua(gsz.amount,2));
		}
		
		
		
		return convertView;
	}
	
	class Holder{
		TextView name;
		TextView time;
		TextView money;
		TextView yue;
		View v;
		TextView shuomin;
	}
	
	
}
