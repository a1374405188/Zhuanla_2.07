package com.beikbank.android.adapter;

import java.util.ArrayList;

import coma.beikbank.android.R;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


public class QuickAdapter extends BaseAdapter{

	private Context context;
	private ArrayList<String> list;

	public QuickAdapter(Context context,ArrayList<String> list){
		this.context=context;
		this.list=list;
	}

	@Override
	public int getCount() {
		
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		
		return position;
	}

	@Override
	public long getItemId(int position) {
		
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		String str=list.get(position);
		Holder holder =null;
		if(convertView==null){
			convertView=LayoutInflater.from(context).inflate(R.layout.dialog_list_item, null);
			holder=new Holder();
			holder.dialog_list_item_textview=(TextView) convertView.findViewById(
					R.id.dialog_list_item_textview);
			convertView.setTag(holder);
		}else{
			holder=(Holder) convertView.getTag();
		}
		
		holder.dialog_list_item_textview.setText(str);
		
		return convertView;
	}
	
	class Holder{
		TextView dialog_list_item_textview;
	}
	
	
}
