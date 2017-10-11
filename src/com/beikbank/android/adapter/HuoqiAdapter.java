package com.beikbank.android.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes.Name;

import com.beikbank.android.activity.HuoqiDetailActivity4;
import com.beikbank.android.adapter.TransactionRecordsAdapter.Holder;
import com.beikbank.android.data.PayList;
import com.beikbank.android.data2.getHuoQiXiangQin2;
import com.beikbank.android.data2.getHuoQiXiangQin2_1;
import com.beikbank.android.data2.getYiGou;
import com.beikbank.android.utils.DensityUtil;
import com.beikbank.android.utils.NumberManager;
import com.beikbank.android.utils.Utils;

import comc.beikbank.android.R;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 *copyright yuguohe
 *email: 1374405188@qq.com
 *活期详情适配器
 */
public class HuoqiAdapter extends MBaseAdapter{
	
	Activity act;
	
	ArrayList<getHuoQiXiangQin2_1> list;
    public HuoqiAdapter(Activity act,ArrayList<getHuoQiXiangQin2_1> list)
    {
    	this.list=list;
    	this.act=act;
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

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Holder holder = null;
		if (convertView == null) 
		{
			LinearLayout l=new LinearLayout(act);
			convertView=act.getLayoutInflater().inflate(R.layout.activity_linhuobao_item1,l,false);
			holder = new Holder();
			holder.name=(TextView) convertView.findViewById(R.id.tv_name);
			holder.state=(TextView) convertView.findViewById(R.id.tv_state);
			holder.money=(TextView) convertView.findViewById(R.id.tv_money);
			holder.zuori=(TextView) convertView.findViewById(R.id.tv_zuori);
			holder.dangqian=(TextView) convertView.findViewById(R.id.tv_dangqian);
			convertView.setTag(holder);
		}
		else
		{
			holder = (Holder) convertView.getTag();
		}
		
		getHuoQiXiangQin2_1 gqx=list.get(position);
		
		holder.name.setText(gqx.prodName);
		holder.state.setText(gqx.status);
		holder.money.setText(NumberManager.getGeshiHua(gqx.calAmount,2));
		holder.zuori.setText(NumberManager.getGeshiHua(gqx.intrestYesterday,2));
		holder.dangqian.setText(NumberManager.getGeshiHua(gqx.intrestTotal,2));
		
		convertView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(act,HuoqiDetailActivity4.class);
				act.startActivity(intent);
				
			}
		});
		
		
//		LinearLayout l=new LinearLayout(act);
//		View view=act.getLayoutInflater().inflate(R.layout.yigourenshu_item,l,false);
//		TextView tv1=(TextView) view.findViewById(R.id.tv1);
//		TextView tv2=(TextView) view.findViewById(R.id.tv2);
//		PayList pl=(PayList) list.get(position);
//		
//	    tv1.setText(pl.userName);
//		
//		String s=NumberManager.getString(pl.amount,"1",2);
//		tv2.setText(s);
//		DensityUtil du=new DensityUtil(act);
//		LayoutParams lp=new LayoutParams(LayoutParams.MATCH_PARENT,du.dip2px(44));
//		view.setLayoutParams(lp);
		return convertView;
	}
	class Holder {
		TextView name;
		TextView state;
		TextView money;
		TextView zuori;
		TextView dangqian;
	}
}
