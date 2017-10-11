package com.beikbank.android.adapter;

import java.util.ArrayList;
import java.util.Collections;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.beikbank.android.activity.SelectHongbaoActivity;
import com.beikbank.android.data.HelpInfo;
import com.beikbank.android.data.Hongbao;
import com.beikbank.android.utils.BeikBankDialogListener;
import com.beikbank.android.utils.DensityUtil;
import com.beikbank.android.utils.LoginManager;
import com.beikbank.android.utils.NumberManager;
import com.beikbank.android.utils.Utils;
import com.beikbank.android.utils.hongbao.HongbaoUtil;
import com.google.gson.JsonObject;

import comc.beikbank.android.R;
/**
 * 红包适配器
 * @author Administrator
 *
 */
public class HongbaoAdapter extends BaseAdapter{
	//如果当前选中可叠加 true,不可叠加false
	/**当前选择的是
	 * 2没有选择 1 叠加0不可叠加
	 */
	int b2=0;
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
    ArrayList<Hongbao> list3=new ArrayList<Hongbao>();
    /**
     * 当前  左边背景 0灰色，1亮色
     */
    ArrayList<Integer> list4=new ArrayList<Integer>();
    
    /**
     * 左边背景 0灰色，1亮色  可叠加全部值亮
     */
    ArrayList<Integer> list5=new ArrayList<Integer>();
    
    /**
     * true 显示全部,false 显示选择
     */
    boolean guoqi;
    SelectHongbaoActivity sa;
    /**
     *  购买的金额
     */
    String money;
    /**
     * 
     * @param context
     * @param list 所有的红包
     * @param list3 选中的红包
     * @param money 购买的金额
     * @param guoqi true 显示全部,false 显示选择
     */
	public HongbaoAdapter(Context context,String money,ArrayList<Hongbao> list,ArrayList<String> list2,boolean guoqi){
		this.context=context;
		
		this.list=list;
		this.guoqi=guoqi;
		this.list2=list2;
		this.money=money;
//		if(0==0)
//		{
//			list.get(0).endTime="2016-01-10 00:00:00";
//			list.get(1).endTime="2016-01-10 00:00:00";
//			list.get(3).content="{\"amontEnable\":\"v1\",\"couponAmont\":\"12\"}";
//			list.get(0).unLimit="1";
//			list.get(1).unLimit="1";
//		}
		
		//Collections.sort(this.list);
		for(int i=0;i<list.size();i++)
		{
			list4.add(0);
			
//			Hongbao hb=list.get(i);
//			if(hb.unLimit.equals("1"))
//			{
//				list5.add(1);
//			}
//			else
//			{
//				list5.add(0);
//			}
		}
		
		if(list2!=null&&list2.size()>0)
		{
			Hongbao hb=list.get(Integer.parseInt(list2.get(0)));
			if(hb.unLimit.equals("1"))
			{
				b2=1;
				for(int i=0;i<list.size();i++)
				{
					Hongbao hb2=list.get(i);
					if(hb2.unLimit.equals("1"))
					{
						list4.add(i,1);
					}
				}
			}
			else
			{
				b2=0;
				for(int i=0;i<list2.size();i++)
				{   
					int a=Integer.parseInt(list2.get(0));
					list4.add(a,1);
				}
			}
			
		}
		else
		{
			b2=2;
			list4.clear();
			for(int i=0;i<list.size();i++)
			{
				list4.add(1);
			}
		}
		if(b2==1)
		{
			setKeXuan();
		}
//		if(!guoqi)
//		{
//			sa=(SelectHongbaoActivity) context;
//		for(int i=0;i<list3.size();i++)
//		{
//			int j=list.indexOf(list3.get(i));
//			list2.add(j+"");
//			if(list3.get(i).unLimit.equals("1"))
//			{
//				b2=true;
//			}
//		}
//		 sa.countMoney(list, list2);
//		}
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
		final Hongbao hb=list.get(position);
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
			holder.rl=(RelativeLayout) convertView.findViewById(R.id.rl);
			holder.ll0=(LinearLayout) convertView.findViewById(R.id.ll0);
			
			
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
		
		if(!guoqi)
		{
		holder.ll0.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ImageView img=(ImageView) v.findViewById(R.id.img);
				
				
				if(hb.unLimit.equals("0"))
				{   
					if(b2==2)
					{ 
						    boolean b1=counMoney(hb);
							if(!b1)
							{     
								  Toast t=Toast.makeText(context, "超出购买金额",Toast.LENGTH_SHORT);
								  t.show();
								  return;
							}
						  list2.clear();
						  img.setImageResource(R.drawable.hongbao7);
						  list2.add(position+"");
						  b2=0;
					  
					}
					else if(b2==1)
					{
						  Toast t=Toast.makeText(context, "不可与已选红包叠加使用",Toast.LENGTH_SHORT);
						  t.show();
						 return;
					}
					else
					{   
						 if(list2.get(0).equals(position+""))
						  {
							 
							  img.setImageResource(R.drawable.hongbao6);
							  list2.clear();
							  b2=2;
						  }
						  else
						  {
							  Toast t=Toast.makeText(context, "不可与已选红包叠加使用",Toast.LENGTH_SHORT);
							  t.show();
							  return;
						  }
						 
					}
					
				}
				else
				{   //当前选择的是不可叠加
					if(b2==0)
					{ 
						  Toast t=Toast.makeText(context, "不可与已选红包叠加使用",Toast.LENGTH_SHORT);
						  t.show();
						  return;
					}
					else
					{    
						  boolean b=list2.contains(position+"");
						  if(b)
						  {
							img.setImageResource(R.drawable.hongbao6);
							list2.remove(position+"");
							if(list2.size()<=0)
							{
								b2=2;
							}
						 }
						 else
						 {  
							    boolean b1=counMoney(hb);
								if(!b1)
								{     
									  Toast t=Toast.makeText(context, "超出购买金额",Toast.LENGTH_SHORT);
									  t.show();
									  return;
								}
							 
							img.setImageResource(R.drawable.hongbao7);
							list2.add(position+"");
							b2=1;
						 }
					}
				 
				
				}
				//
				if(b2==2)
				{
					 setO(1);
				}
				else if(b2==1)
				{
					//list4.clear();
					//list4.addAll(list5);
					setKeXuan();
				}
				else
				{
					setO(0);
					list4.add(position,1);
				}
				SelectHongbaoActivity sa=(SelectHongbaoActivity)context;
				String money=HongbaoUtil.countMoney(list, list2);
				sa.setCount(money,list2.size()+"");
				notifyDataSetChanged();
			}
		});
		}
		else
		{
			
			holder.img.setVisibility(View.GONE);
		}
		
		
	
		//设置灰色
		if(list4.get(position)==0)
		{   
			if(hb.unLimit.equals("0"))
			{
			  holder.ll.setBackgroundResource(R.drawable.hongbao3);
			}
			else
			{
			  holder.ll.setBackgroundResource(R.drawable.hongbao11);
			}
		}
		else
		{
			if(hb.unLimit.equals("0"))
			{
				holder.ll.setBackgroundResource(R.drawable.hongbao2);
			}
			else
			{
				holder.ll.setBackgroundResource(R.drawable.left1);
			}
			
		}
		
		if(list2.contains(position+""))
		{
			holder.img.setImageResource(R.drawable.hongbao7);
		}
		else
		{
			holder.img.setImageResource(R.drawable.hongbao6);
		}
		String s1=hb.content;
		JSONObject jo=null;
		try {
			jo=new JSONObject(s1);
			s1=jo.getString("couponAmont");
		} catch (JSONException e) {
			
			e.printStackTrace();
		}
		holder.tv1.setText(s1);
		holder.tv2.setText(hb.name);
		if(hb.usePType.equals("0"))
		{
			holder.tv3.setText("购买"+hb.mustMeet+"元以上定期可用");
		}
		else if(hb.usePType.equals("1"))
		{
			holder.tv3.setText("购买"+hb.mustMeet+"元以上活期可用");
		}
		else
		{
			holder.tv3.setText("购买"+hb.mustMeet+"元以上可用");
		}
		 int c=HongbaoUtil.compreS(hb.mustMinDay,hb.mustMaxDay);
		if(c==0)
		{   
			if(HongbaoUtil.compreS(hb.mustMinDay,"0")==0)
			{
			  holder.tv5.setVisibility(View.GONE);
			}
			else
			{
				holder.tv5.setText("适用期限:仅限"+hb.mustMinDay+"天");
			}
		}
		else
		{
			holder.tv5.setText("适用期限:"+hb.mustMinDay+"-"+hb.mustMaxDay+"天");
		}
		if(!hb.usePType.equals("0"))
		{
			 holder.tv5.setVisibility(View.GONE);
		}
		holder.tv4.setText("有效期至"+HongbaoUtil.getDate2(hb.endTime));
		return convertView;
	}
	
	class Holder{
		/**
		 * 总父布局
		 */
		LinearLayout ll0;
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
		 * 购买天数
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
		/**
		 * 又边布局
		 */
		RelativeLayout rl;
		
	}
//  Dialog  change_account_dialog=null;
//  private void ok(final View v,final int position)
//  {
//	change_account_dialog=Utils.createSimpleDialog(context,
//				"该红包不可与已选红包叠加使用,确定使用吗？",context.getString(R.string.ok2),new BeikBankDialogListener() {
//
//			@Override
//			public void onRightBtnClick() {
//				
//				list2.clear();
//				((ImageView) v).setImageResource(R.drawable.hongbao7);
//				list2.add(position+"");
//				b2=false;
//				SelectHongbaoActivity sa=(SelectHongbaoActivity)context;
//				sa.countMoney(list, list2);
//				notifyDataSetChanged();
//			}
//
//			@Override
//			public void onListItemLongClick(int position, String string) {
//				
//
//			}
//
//			@Override
//			public void onListItemClick(int position, String string) {
//				// TODO Auto-generated method stub
//
//			}
//
//			@Override
//			public void onLeftBtnClick() {
//				// TODO Auto-generated method stub
//				change_account_dialog.dismiss();
//			}
//
//			@Override
//			public void onCancel() {
//				// TODO Auto-generated method stub
//
//			}
//		});
//		change_account_dialog.show();
//  }
	/**
	 * 当点击可叠加时，计算可选红包背景颜色
	 */
	private void setKeXuan()
	{
		
		for(int i=0;i<list.size();i++)
		{
			boolean b=list2.contains(i+"");
			if(b)
			{
				list4.add(i,1);
			}
			else
			{
				Hongbao hb=list.get(i);
				if(hb.unLimit.equals("0"))
				{
					list4.add(i,0);
				}
				else
				{
					String s=HongbaoUtil.countMoney(list,list2);
					String s2=HongbaoUtil.getMoney(hb);
					String s3=NumberManager.getAddString(s,s2,2);
					if(HongbaoUtil.compreS(this.money,s3)>=0)
					{
						list4.add(i,1);
					}
					else
					{
						list4.add(i,0);
					}
				}
			}
		}
	}
   private void setO(int index)
   {   
	   int size=list4.size();
	   list4.clear();
	   for(int i=0;i<size;i++)
	   {
		   list4.add(index);
	   }
   }
   /*
    * 判断选择金额是否超过购买金额金额
    * Hongbao hb 当前选择的
    * true 没有超过
    */
   private boolean counMoney(Hongbao hb)
   {  
	   boolean b=false;
	   ArrayList<Hongbao> list5=new ArrayList<Hongbao>();
	   try {
           for(int i=0;i<list2.size();i++)
           {
			   int a=Integer.parseInt(list2.get(i));
			   list5.add(list.get(a));
           }
            list5.add(hb);
            String money= HongbaoUtil.getCountMoney(list5);
            double d1=Double.parseDouble(money);
            double d2=Double.parseDouble(this.money);
            if(d1<=d2)
            {
            	return true;
            }
	    } 
	   catch (Exception e)
	   {
		  e.printStackTrace();
	   }
	   
	   return b;
   }
}
