package com.beikbank.android.adapter;

import java.math.BigDecimal;
import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.beikbank.android.data.ProjectInfo;
import com.beikbank.android.utils.NumberManager;
import coma.beikbank.android.R;



public class ProjectAdapter extends BaseAdapter{

	private Context context;
	private ArrayList<ProjectInfo> list;

	public ProjectAdapter(Context context,ArrayList<ProjectInfo> list){
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
		ProjectInfo projectInfo=list.get(position);
		Holder holder =null;
		if(convertView==null){
			convertView=LayoutInflater.from(context).inflate(R.layout.item_project2, null);
			holder=new Holder();
			holder.textview_platformName=(TextView) convertView.findViewById(
					R.id.textview_platformName);
			holder.textview_project_name=(TextView) convertView.findViewById(
					R.id.textview_project_name);
            holder.text1=(TextView) convertView.findViewById(
					R.id.project_text1);
            holder.text2=(TextView) convertView.findViewById(
					R.id.project_text2);
            holder.text3=(TextView) convertView.findViewById(
					R.id.project_text3);
            holder.text4=(TextView) convertView.findViewById(
					R.id.project_text4);
            holder.text5=(TextView) convertView.findViewById(
					R.id.project_text5);
            holder.text6=(TextView) convertView.findViewById(
					R.id.project_text6);
            holder.text7=(TextView) convertView.findViewById(
					R.id.project_text7);
            holder.text8=(TextView) convertView.findViewById(
					R.id.project_text8);
			convertView.setTag(holder);
		}else{
			holder=(Holder) convertView.getTag();
		}	
		//float yearrate=Float.parseFloat(projectInfo.getYearRate());
		holder.textview_platformName.setText(projectInfo.getPlatformName());
		holder.textview_project_name.setText(projectInfo.getBondName());
		String s=NumberManager.getString(projectInfo.yearRate,"100",2);
		holder.text1.setText(s+"%");
		String s2=NumberManager.getString(projectInfo.raiseAmount,"1",0);
		holder.text2.setText(s2+"元");
		//holder.text2.setText(projectInfo.amount+"元");
		holder.text3.setText(projectInfo.repayment);
		holder.text4.setText(projectInfo.bondType);
		holder.text5.setText(projectInfo.beginDate);
		holder.text6.setText(projectInfo.endDate);
		holder.text7.setText(projectInfo.amount+"元");
		
		String s1=NumberManager.getString(projectInfo.reserve+"","100",2);
		holder.text8.setText(s1+"%");
		//BigDecimal bd_rate=new BigDecimal(yearrate*100);
		///bd_rate=bd_rate.setScale(2,BigDecimal.ROUND_DOWN);
	
		
		return convertView;
	}
	
	class Holder{
		TextView textview_platformName;
		TextView textview_project_name;
		TextView text1;
		TextView text2;
		TextView text3;
		TextView text4;
		TextView text5;
		TextView text6;
		TextView text7;
		TextView text8;
	}
	
	
}
