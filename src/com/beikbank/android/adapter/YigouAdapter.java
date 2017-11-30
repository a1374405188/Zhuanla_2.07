package com.beikbank.android.adapter;

import java.util.ArrayList;
import java.util.List;

import com.beikbank.android.adapter.TransactionRecordsAdapter.Holder;
import com.beikbank.android.data.PayList;
import com.beikbank.android.data2.getYiGou;
import com.beikbank.android.utils.DensityUtil;
import com.beikbank.android.utils.NumberManager;
import com.beikbank.android.utils.Utils;
import coma.beikbank.android.R;



import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 *copyright yuguohe
 *email: 1374405188@qq.com
 *2015-7-1
 */
public class YigouAdapter extends MBaseAdapter{
	
	Activity act;
	
	//ArrayList<getYiGou> list;
    public YigouAdapter(Activity act)
    {
    	//this.list=list;
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
			convertView=LayoutInflater.from(act).inflate(R.layout.yigourenshu_item,null);
			holder = new Holder();
			holder.name=(TextView) convertView.findViewById(R.id.name);
			holder.money=(TextView) convertView.findViewById(R.id.money);
			holder.time=(TextView) convertView.findViewById(R.id.time);
			convertView.setTag(holder);
		}
		else
		{
			holder = (Holder) convertView.getTag();
		}
		
		getYiGou gg=(getYiGou) list.get(position);
		holder.name.setText(gg.real_name);
		holder.money.setText(NumberManager.getString(gg.amount,"1",2));
		holder.time.setText(gg.create_time);
		
		
		
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
		TextView money;
		TextView time;
	}
}
