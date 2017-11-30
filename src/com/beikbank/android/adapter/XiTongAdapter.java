package com.beikbank.android.adapter;

import java.util.List;

import com.beikbank.android.data.PayList;
import com.beikbank.android.data2.Xiaoxi;
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
 *2015-7-1
 */
//系统公告适配器
public class XiTongAdapter extends MBaseAdapter{
	
	Activity act;
    public XiTongAdapter(Activity act)
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
		LinearLayout l=new LinearLayout(act);
		View view=act.getLayoutInflater().inflate(R.layout.xitong_gonggao_item,l,false);
		TextView tv_biaoti=(TextView) view.findViewById(R.id.tv_biaoti);
		
		TextView tv_time=(TextView) view.findViewById(R.id.tv_time);
		TextView tv_text=(TextView) view.findViewById(R.id.tv_text);
		Xiaoxi xi=(Xiaoxi) list.get(position);
//	    tv_biaoti.setText(xi.m_msg_title);
//	    tv_time.setText(xi.m_send_time);
//	    tv_text.setText(xi.m_msg_content);
	    DensityUtil du=new DensityUtil(act);
		LayoutParams lp=new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
		view.setLayoutParams(lp);
		return view;
	}

}
