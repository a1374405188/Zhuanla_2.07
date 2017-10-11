package com.beikbank.android.adapter;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

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

import com.beikbank.android.activity.SelectHongbaoActivity;
import com.beikbank.android.data.HelpInfo;
import com.beikbank.android.data.Hongbao;
import com.beikbank.android.utils.DensityUtil;
import com.beikbank.android.utils.hongbao.HongbaoUtil;
import com.google.gson.JsonObject;

import comc.beikbank.android.R;
/**
 * 已过期红包显示适配器
 * @author Administrator
 *
 */
public class HongbaoAdapter2 extends BaseAdapter{

	private Context context;
    private ArrayList<Hongbao> list;
   // private ListView lv;
    /**
     * 被选中的索引
     */
    public ArrayList<String> list2=new ArrayList<String>();
    /**
     * 被选中的红包
     */
    ArrayList<Hongbao> list3;
    /**
     * 是否是过期
     */
    boolean guoqi;
    SelectHongbaoActivity sa;
    /**
     * 
     * @param context
     * @param list 所有的红包
     * @param list3 选中的红包
     * @param guoqi
     */
	public HongbaoAdapter2(Context context,ArrayList<Hongbao> list,ArrayList<Hongbao> list3,boolean guoqi){
		this.context=context;
		sa=(SelectHongbaoActivity) context;
		this.list=list;
		this.guoqi=guoqi;
		this.list3=list3;
		for(int i=0;i<list3.size();i++)
		{
			int j=list.indexOf(list3.get(i));
			list2.add(j+"");
		}
		//sa.countMoney(list, list2);
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
		Hongbao hb=list.get(position);
		Holder holder =null;
		if(convertView==null){
			
			convertView=LayoutInflater.from(context).inflate(R.layout.activity_select_hongbao_item, null);
			holder=new Holder();
			convertView.setTag(holder);
			
			
			holder.tv1=(TextView) convertView.findViewById(R.id.tv1);
			holder.tv2=(TextView) convertView.findViewById(R.id.tv2);
			holder.tv3=(TextView) convertView.findViewById(R.id.tv3);
			holder.tv4=(TextView) convertView.findViewById(R.id.tv4);
			holder.tv5=(TextView) convertView.findViewById(R.id.tv5);
			holder.img=(ImageView) convertView.findViewById(R.id.img);
			holder.ll=(LinearLayout) convertView.findViewById(R.id.ll);
			if(hb.unLimit.equals("0"))
			{
				holder.ll.setBackgroundResource(R.drawable.left1_no);
			}
			else
			{
				holder.ll.setBackgroundResource(R.drawable.left1);
			}
			
			if(list2.contains(position+""))
			{
				holder.img.setImageResource(R.drawable.hongbao7);
			}
			else
			{
				holder.img.setImageResource(R.drawable.hongbao6);
			}
			
			holder.img.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					boolean b=list2.contains(position+"");
					if(b)
					{
						((ImageView) v).setImageResource(R.drawable.hongbao6);
						list2.remove(position+"");
					}
					else
					{
						((ImageView) v).setImageResource(R.drawable.hongbao7);
						list2.add(position+"");
					}
					SelectHongbaoActivity sa=(SelectHongbaoActivity)context;
					//String money=HongbaoUtil.countMoney(list, list3);
					//sa.setCount(money,list3.size()+"");
				}
			});
			
		}else{
			holder=(Holder) convertView.getTag();
		}
		
		
		if(position==0)
		{   
			DensityUtil du=new DensityUtil(context);
			convertView.setPadding(0,du.dip2px(12),0,0);
		}
		else if(position==list.size()-1)
		{   
			DensityUtil du=new DensityUtil(context);
			convertView.setPadding(0,0,0,du.dip2px(12));
		}
		else
		{
			convertView.setPadding(0,0,0,0);
			
		}
		String s1=hb.content;
		JSONObject jo=null;
		try {
			jo=new JSONObject(s1);
			s1=jo.getString("couponAmont")+"元";
		} catch (JSONException e) {
			
			e.printStackTrace();
		}
		holder.tv1.setText(s1);
		holder.tv2.setText(hb.name);
		holder.tv3.setText("购买"+hb.mustMeet+"元以上");
		holder.tv4.setText("有效期至"+hb.endTime);
		return convertView;
	}
	
	class Holder{
		/**
		 * 背景图片
		 */
		LinearLayout ll;
		/**
		 * 金额
		 */
		TextView tv1;
		/**
		 * 标题
		 */
		TextView tv2;
		/**
		 * 描述
		 */
		TextView tv3;
		/**
		 * 期限
		 */
		TextView tv4;
		/**
		 * 过期
		 */
		TextView tv5;
		/**
		 * 是否被选中
		 */
		boolean isSelect;
		/**
		 * 选中
		 */
		ImageView img;
		
	}
	


}
