package com.beikbank.android.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.TextView;

import com.beikbank.android.adapter.HelpCenterAdapter.Holder;
import com.beikbank.android.data.HelpInfo;
import coma.beikbank.android.R;


/**
 * 
 * @author Administrator
 *可展开
 */
public class HelpCenterAdapter3 extends BaseExpandableListAdapter{
	private Context context;
	 private ArrayList<HelpInfo> list;
	public HelpCenterAdapter3(Context context,ArrayList<HelpInfo> list,ListView lv){
		this.context=context;
		
		this.list=list;
		
	}
	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public Object getGroup(int groupPosition) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getGroupId(int groupPosition) {
		// TODO Auto-generated method stub
		return groupPosition;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return childPosition;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		HelpInfo hi=list.get(groupPosition);
		Holder holder =null;
		if(convertView==null){
			convertView=LayoutInflater.from(context).inflate(R.layout.item_help_center, null);
			holder=new Holder();
			holder.linear_help_center_title=(LinearLayout) convertView.findViewById(
					R.id.linear_help_center_title);
			holder.linear_help_center_content=(LinearLayout) convertView.findViewById(
					R.id.linear_help_center_content);
			holder.problem=(TextView) convertView.findViewById(R.id.textview_help_center_problem);
			holder.answer=(TextView) convertView.findViewById(R.id.textview_help_center_answer);
			holder.icon=(ImageView) convertView.findViewById(R.id.imageview_what);
			convertView.setTag(holder);
		}else{
			holder=(Holder) convertView.getTag();
		}	
		final View view=convertView;
				holder.problem.setText(hi.getProblem());
		holder.answer.setText(hi.getAnswer());
		return convertView;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		HelpInfo hi=list.get(groupPosition);
		Holder holder =null;
		if(convertView==null){
			convertView=LayoutInflater.from(context).inflate(R.layout.item_help_center_1, null);
			holder=new Holder();
			//holder.linear_help_center_title=(LinearLayout) convertView.findViewById(
			//		R.id.linear_help_center_title);
//			holder.linear_help_center_content=(LinearLayout) convertView.findViewById(
//					R.id.linear_help_center_content);
			//holder.problem=(TextView) convertView.findViewById(R.id.textview_help_center_problem);
			holder.answer=(TextView) convertView.findViewById(R.id.textview_help_center_answer);
			//holder.icon=(ImageView) convertView.findViewById(R.id.imageview_what);
			convertView.setTag(holder);
		}else{
			holder=(Holder) convertView.getTag();
		}	
		final View view=convertView;
		//		holder.problem.setText(hi.getProblem());
		holder.answer.setText(hi.getAnswer());
		return convertView;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return false;
	}

	class Holder{
		LinearLayout linear_help_center_title;
		LinearLayout linear_help_center_content;
		TextView problem;
		TextView answer;
		ImageView icon;
		
	}

}
