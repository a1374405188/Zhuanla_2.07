package com.beikbank.android.scroller;

import java.util.List;

import com.beikbank.android.data.Message;

import comc.beikbank.android.R;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AbsListView.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
/**
 * 系统消息适配器
 */
public class DeleteAdapter2 extends BaseAdapter {

	public static ListItemDelete itemDelete = null;
	//private List<String> listDatas;
	private List<Message> list;
	private LayoutInflater mInflater;
	private Context context;

	public DeleteAdapter2(Context context, List<Message> list) {
		mInflater = LayoutInflater.from(context);
		this.list= list;
		this.context = context;
	}

	@Override
	public int getCount() {
		return list == null ? 0 : list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		Message msg=list.get(position);
		//初始化控件
		if (convertView == null) {
			holder = new ViewHolder();
			LinearLayout ll=new LinearLayout(context);
			LayoutParams lp=new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
		
			convertView = mInflater.inflate(R.layout.item_delete,ll,false);
			convertView.setLayoutParams(lp);
			holder.iv=(ImageView) convertView.findViewById(R.id.iv_iv1);
			holder.title = (TextView) convertView
					.findViewById(R.id.title);
			holder.context = (TextView) convertView
					.findViewById(R.id.context);
			holder.time = (TextView) convertView.findViewById(R.id.time);
			
			holder.ll_del=(LinearLayout) convertView.findViewById(R.id.ll_del);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		//设置控件的属性和值
		if("0".equals(msg.readFlg))
		{
			 holder.iv.setImageResource(R.drawable.message_un);
		}
		else
		{
			 holder.iv.setImageResource(R.drawable.message_has);
		}
		holder.title.setText(msg.tittle);
		holder.context.setText(msg.sender);
		holder.time.setText(msg.sendTime);
		holder.ll_del.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				list.remove(position);
				notifyDataSetChanged();
				ItemDeleteReset();
			}
		});
		
		
		return convertView;
	}

	class ViewHolder {
		ImageView iv;
		TextView title;
		TextView context;
		TextView time;
		LinearLayout ll_del;
	}

	private Toast mToast;

	public void showInfo(String text) {
		if (mToast == null) 
		{
			mToast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
		} else {
			mToast.setText(text);
			mToast.setDuration(Toast.LENGTH_SHORT);
		}
		mToast.show();
	}

	public static void ItemDeleteReset() {
		if (itemDelete != null) {
			itemDelete.reSet();
		}
	}
}
