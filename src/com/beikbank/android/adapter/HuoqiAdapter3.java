package com.beikbank.android.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes.Name;

import com.beikbank.android.activity.DingqiDetailActivity2;
import com.beikbank.android.adapter.TransactionRecordsAdapter.Holder;
import com.beikbank.android.data.PayList;
import com.beikbank.android.data2.getHuoQiXiangQin2;
import com.beikbank.android.data2.getHuoQiXiangQin2_1;
import com.beikbank.android.data2.getYiGou;
import com.beikbank.android.data2.getZhiChan;
import com.beikbank.android.utils.DensityUtil;
import com.beikbank.android.utils.NumberManager;
import com.beikbank.android.utils.Utils;
import coma.beikbank.android.R;



import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 *copyright yuguohe
 *email: 1374405188@qq.com
 *活期详情   已转让适配器
 */
public class HuoqiAdapter3 extends MBaseAdapter{
	
	Activity act;
	
	//ArrayList<getZhiChan> list;
    public HuoqiAdapter3(Activity act,ArrayList<getZhiChan> list)
    {  
    	List list0=list;
    	this.list=list0;
    	this.act=act;
    }
    public HuoqiAdapter3(Activity act)
    {
    	
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
		getZhiChan gzc=(getZhiChan) list.get(position);
		if (convertView == null) 
		{
			LinearLayout l=new LinearLayout(act);
			convertView=act.getLayoutInflater().inflate(R.layout.activity_linhuobao_item2,l,false);
			holder = new Holder();
			holder.name=(TextView) convertView.findViewById(R.id.tv_name);
			holder.shouyi=(TextView) convertView.findViewById(R.id.tv_shouyi);
			holder.money=(TextView) convertView.findViewById(R.id.tv_money);
			holder.qixi=(TextView) convertView.findViewById(R.id.tv_qixi);
			holder.zuanrang=(TextView) convertView.findViewById(R.id.tv_zuanrang);
			convertView.setTag(holder);
			if(act instanceof DingqiDetailActivity2)
			{
				TextView tv_zuanrang_text= (TextView) convertView.findViewById(R.id.tv_zuanrang_text);
				TextView tv= (TextView) convertView.findViewById(R.id.tv_futou);
				if(gzc.is_reinvest.equals("0"))
				{
				tv.setVisibility(View.VISIBLE);
				}
				
				tv_zuanrang_text.setText("到期日期");
				holder.zuanrang.setText(gzc.expired_date);
			}
			else
			{
				holder.zuanrang.setText(gzc.repayment_time);
			}
		}
		else
		{
			holder = (Holder) convertView.getTag();
		}
		
		
		
		holder.name.setText(gzc.product_name);
		
		holder.shouyi.setText(NumberManager.getGeshiHua(gzc.intrest_total,2));
		holder.money.setText(NumberManager.getGeshiHua(gzc.current_principal_balance,2));
		holder.qixi.setText(gzc.original_calculate_date);
		
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
		TextView shouyi;
		TextView money;
		TextView qixi;
		TextView zuanrang;
	}
}
