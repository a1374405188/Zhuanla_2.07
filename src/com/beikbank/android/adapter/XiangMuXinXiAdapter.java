package com.beikbank.android.adapter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.beikbank.android.activity.HuodongActivity2;
import com.beikbank.android.conmon.SystemConfig;
import com.beikbank.android.data.ProjectInfo;
import com.beikbank.android.data2.getXiangMuXinXi;
import com.beikbank.android.utils.NumberManager;

import comc.beikbank.android.R;

public class XiangMuXinXiAdapter extends MBaseAdapter{

	private Context context;
	//private ArrayList<getXiangMuXinXi> list;
    
	public XiangMuXinXiAdapter(Context context){
		this.context=context;
		
	}
	public XiangMuXinXiAdapter(Context context,List<Object> list){
		this.context=context;
		this.list=list;
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
		final getXiangMuXinXi gxm=(getXiangMuXinXi) list.get(position);
		Holder holder =null;
		if(convertView==null){
			convertView=LayoutInflater.from(context).inflate(R.layout.pview1_v2_item, null);
			holder=new Holder();
			holder.tv_id=(TextView) convertView.findViewById(R.id.tv_id);
			holder.tv_name=(TextView) convertView.findViewById(R.id.tv_name);
			holder.tv_lilv=(TextView) convertView.findViewById(R.id.tv_lilv);
			holder.tv_money=(TextView) convertView.findViewById(R.id.tv_money);
			holder.iv1=(ImageView) convertView.findViewById(R.id.iv1);
			holder.iv2=(ImageView) convertView.findViewById(R.id.iv2);
			holder.iv3=(ImageView) convertView.findViewById(R.id.iv3);
			holder.iv4=(ImageView) convertView.findViewById(R.id.iv4);
			holder.iv5=(ImageView) convertView.findViewById(R.id.iv5);
			convertView.setTag(holder);
		}else{
			holder=(Holder) convertView.getTag();
		}	
		
		holder.tv_id.setText(gxm.debt_id);
		holder.tv_money.setText(gxm.debt_amount+"元");
		String s1=NumberManager.getString(gxm.ori_pro_rate,"1",2);
		holder.tv_lilv.setText(s1+"%");
		int a=Integer.parseInt(gxm.risk_level);
		if(a>=1)
		{
			holder.iv1.setVisibility(View.VISIBLE);
		}
		if(a>=2)
		{
			holder.iv2.setVisibility(View.VISIBLE);
		}
		if(a>=3)
		{
			holder.iv3.setVisibility(View.VISIBLE);
		}
		if(a>=4)
		{
			holder.iv4.setVisibility(View.VISIBLE);
		}
		if(a>=5)
		{
			holder.iv5.setVisibility(View.VISIBLE);
		}
		convertView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(context,HuodongActivity2.class);
				
				intent.putExtra("title","项目信息");
				intent.putExtra("url",SystemConfig.huodong_url+"#!/productDetails/"+gxm.debt_code);
				context.startActivity(intent);
				
			}
		});
		
		
		return convertView;
	}
	
	class Holder{
		TextView tv_name;
		TextView tv_id;
		TextView tv_money;
		TextView tv_lilv;
		ImageView iv1;
		ImageView iv2;
		ImageView iv3;
		ImageView iv4;
		ImageView iv5;
	}
	
	
}
