package com.beikbank.android.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes.Name;

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
 *活期详情 转让中适配器
 */
public class HuoqiAdapter2 extends MBaseAdapter{
	
	Activity act;
	
	//ArrayList<getZhiChan> list;
    public HuoqiAdapter2(Activity act,ArrayList<getZhiChan> list)
    {   
    	List list0=list;
    	this.list=list0;
    	this.act=act;
    }
    public HuoqiAdapter2(Activity act)
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
		
		getZhiChan gzc=(getZhiChan) list.get(position);
		
		holder.name.setText(gzc.product_name);
		if(gzc.repayment_time!=null)
		{
			holder.state.setText("预计"+gzc.repayment_time.subSequence(5,10)+"转让成功");
		}
		
		holder.money.setText(NumberManager.getGeshiHua(gzc.currCapValue,2)+"元");
		holder.zuori.setText(NumberManager.getGeshiHua(gzc.intrest_yesterday,2)+"元");
		holder.dangqian.setText(NumberManager.getGeshiHua(gzc.intrest_total,2)+"元");
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
