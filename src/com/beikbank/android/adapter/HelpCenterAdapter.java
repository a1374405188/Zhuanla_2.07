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
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.TextView;

import com.beikbank.android.data.HelpInfo;

import comc.beikbank.android.R;

public class HelpCenterAdapter extends BaseAdapter{

	private Context context;
	private View mLastView;
	/**
	 * 上一个点击的向
	 */
    private int mLastPosition;
    /**
     * 上一个可见的向
     */
    private int mLastVisibility=-1;
    private ArrayList<HelpInfo> list;
    private ListView lv;
	public HelpCenterAdapter(Context context,ArrayList<HelpInfo> list,ListView lv){
		this.context=context;
		mLastPosition = -1;
		this.list=list;
		this.lv=lv;
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
		HelpInfo hi=list.get(position);
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
		
		
	 LayoutParams lp1=new LayoutParams(android.widget.LinearLayout.LayoutParams.MATCH_PARENT,android.widget.LinearLayout.LayoutParams.WRAP_CONTENT);
	 LayoutParams lp2=new LayoutParams(lp1.MATCH_PARENT,0);
	if(mLastPosition==position)
		{
		        if(holder.linear_help_center_content.getVisibility()==View.VISIBLE)
		        {
		        	holder.linear_help_center_content.setVisibility(View.GONE);
		        	holder.linear_help_center_content.setLayoutParams(lp2);
		        }
		        else
		        {
		    	   holder.linear_help_center_content.setVisibility(View.VISIBLE);
		    	   holder.linear_help_center_content.setLayoutParams(lp1);
		        }
        }
		else
		{
			holder.linear_help_center_content.setVisibility(View.GONE);
			holder.linear_help_center_content.setLayoutParams(lp2);
		}
		
		
		
		final View view=convertView;
		holder.linear_help_center_title.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//changeImageVisable(view,position);
				mLastPosition = position;
				
				lv.setSelection(position);
				//lv.setSelected(selected)
				HelpInfo hi=list.get(list.size()-1);
				HelpInfo hi2=new HelpInfo();
				hi2.answer=hi.answer;
				hi2.problem=hi.problem;
				list.remove(list.size()-1);
				list.add(hi2);
				notifyDataSetChanged();
			   
			}
		});
		holder.problem.setText(hi.getProblem());
		holder.answer.setText(hi.getAnswer());
		return convertView;
	}
	
	class Holder{
		LinearLayout linear_help_center_title;
		LinearLayout linear_help_center_content;
		TextView problem;
		TextView answer;
		ImageView icon;
		
	}
	
	public void changeImageVisable(View view,int position) {
		
        if(mLastView != null && mLastPosition != position ) {
            Holder holder = (Holder) mLastView.getTag();
            switch(holder.linear_help_center_content.getVisibility()) {
            case View.VISIBLE:
                holder.linear_help_center_content.setVisibility(View.GONE);
                //holder.icon.setBackgroundResource(R.drawable.ic_what_normal);
                mLastVisibility = View.GONE;
                break;
            default :
                break;
            }
        }
       
       
        
        mLastPosition = position;
        mLastView = view;
        
        Holder holder = (Holder) view.getTag();
      
        switch(holder.linear_help_center_content.getVisibility()) {
        case View.GONE:
            holder.linear_help_center_content.setVisibility(View.VISIBLE);
            //holder.icon.setBackgroundResource(R.drawable.ic_what_focused);
            mLastVisibility = View.VISIBLE;
            
            break;
        case View.VISIBLE:
            holder.linear_help_center_content.setVisibility(View.GONE);
           
            //holder.icon.setBackgroundResource(R.drawable.ic_what_normal);
            mLastVisibility = View.GONE;
            
            mLastPosition=-1;
            break;
        }
       
    }

}
