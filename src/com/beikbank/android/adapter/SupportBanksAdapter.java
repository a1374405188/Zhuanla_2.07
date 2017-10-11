package com.beikbank.android.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.beikbank.android.data.BankInfo;
import com.beikbank.android.data.BankList;
import com.beikbank.android.data2.getAllBank;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import comc.beikbank.android.R;

public class SupportBanksAdapter extends BaseAdapter{

	private Context context;
	private ArrayList<getAllBank> list=new ArrayList<getAllBank>();
	DisplayImageOptions options;

	public SupportBanksAdapter(Context context,ArrayList<getAllBank> list){
		this.context=context;
		this.list.addAll(list);
		options = new DisplayImageOptions.Builder()
		.showImageOnLoading(R.drawable.ic_bank_default)
		.showImageForEmptyUri(R.drawable.ic_bank_default)
		.showImageOnFail(R.drawable.ic_bank_default)
		.cacheInMemory(true)
		.cacheOnDisk(true)
		.considerExifParams(true)
		.displayer(new FadeInBitmapDisplayer(100))
		.bitmapConfig(Bitmap.Config.RGB_565)
		.build();
		com.nostra13.universalimageloader.utils.L.disableLogging();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
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
		Holder holder =null;
		getAllBank ga=list.get(position);
		if(convertView==null){
			convertView=LayoutInflater.from(context).inflate(R.layout.item_support_bank, null);
			holder=new Holder();
			holder.imageview_bank_logo=(ImageView) convertView.findViewById(
					R.id.imageview_bank_logo);
			holder.textview_bank_name=(TextView) convertView.findViewById(
					R.id.textview_bank_name);
			holder.textview_bank_singleLimit=(TextView) convertView.findViewById(
					R.id.textview_bank_singleLimit);
			holder.textview_bank_cumulativeLimit=(TextView) convertView.findViewById(
					R.id.textview_bank_cumulativeLimit);
			convertView.setTag(holder);
		}else{
			holder=(Holder) convertView.getTag();
		}
		
		ImageLoader.getInstance().displayImage(ga.icon_url, holder.imageview_bank_logo, options);
		holder.textview_bank_name.setText(ga.bank_name);
		String text="单笔最低"+ga.min_amount+"元,最高"+ga.simplet_amount+"万元";
		//holder.textview_bank_singleLimit.setText("单笔限额"+sb.singleLimit+"万元");
		holder.textview_bank_singleLimit.setText(text);
		holder.textview_bank_cumulativeLimit.setText("当日限额"+ga.simplet_date_amount+"万元");
		
		
		return convertView;
	}
	
	class Holder{
		ImageView imageview_bank_logo;
		TextView textview_bank_name;
		TextView textview_bank_singleLimit;
		TextView textview_bank_cumulativeLimit;
		
	}
	
	
}
