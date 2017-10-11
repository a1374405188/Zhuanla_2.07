package com.beikbank.android.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.beikbank.android.data.HelpInfo;
import com.beikbank.android.data.HelpInfo2;

import comc.beikbank.android.R;
/**
 * 
 * @author Administrator
 *帮助中心V2
 */
public class HelpCenterAdapter2 extends BaseAdapter{

	private Context context;

    private ArrayList<HelpInfo2> list;

	public HelpCenterAdapter2(Context context,ArrayList<HelpInfo2> list){
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
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		HelpInfo2 hi=list.get(position);
		LinearLayout ll=new LinearLayout(context);
		View view=LayoutInflater.from(context).inflate(R.layout.item_help_center2,null);
		TextView tv=(TextView) view.findViewById(R.id.tv_text);
		tv.setText(hi.typeName);
		return view;
	}
	
	
	
}
