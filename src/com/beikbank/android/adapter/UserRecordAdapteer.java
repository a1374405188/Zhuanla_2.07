package com.beikbank.android.adapter;

import java.util.ArrayList;
import java.util.List;

import com.beikbank.android.data.UserRecord;
import com.beikbank.android.data.UserRecord2;
import com.beikbank.android.net.HandlerBase;
import com.beikbank.android.utils.DensityUtil;
import com.beikbank.android.utils.NumberManager;
import coma.beikbank.android.R;



import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * @author yuguohe
 * <p>
 * email:1374405188@qq.com
 * </p>
 *2015-3-2
 * 
 */
public class UserRecordAdapteer extends MBaseAdapter
{   
	/**
	 * 保留几位小数
	 */
	public int endCount=2;
	Activity act;
	//List<UserRecord2> list;
	/**
	 * 最大金额
	 */
	int max;
	/**
	 * 累计收益
	 */
    String money;
    /**
     * 1全部2近一周
     */
    int index=0;
	/**
	 * 
	 * @param act
	 * @param list
	 * @param width 进度条总宽度
	 * @param money累计收益
	 */
    public UserRecordAdapteer(Activity act,String money,int index)
    {
    	this.act=act;
    	
    	this.money=money;
    	this.index=index;
    }
	/**
	 * 
	 * @param act
	 * @param list
	 * @param width 进度条总宽度
	 * @param money累计收益
	 */
    public UserRecordAdapteer(Activity act,List<UserRecord2> list,String money,int index)
    {
    	this.act=act;
    	List<UserRecord2> list1=list;
    	List list2=list1;
    	this.list=list2;
    	this.money=money;
    	this.index=index;
    }
    /**
     * 将原有的数据加上一条空的数据
     * @return
     */
    private List<UserRecord2> getData(List<UserRecord2> list)
    {   
    	List<UserRecord2> list2=new ArrayList<UserRecord2>();
    	UserRecord2 ur2=new UserRecord2();
    	list2.add(ur2);
    	for(UserRecord2 ur:list)
    	{
    		list2.add(ur);
    	}
    	return list2;
    }
    
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		DensityUtil du=new DensityUtil(act);
		LinearLayout ll=new LinearLayout(act);
		LayoutInflater lf=act.getLayoutInflater();
//		if(position==0)
//		{
//			LinearLayout ll1=(LinearLayout) lf.inflate(R.layout.userrecoed_chid,ll,false);
//			TextView tv_money=(TextView) ll1.findViewById(R.id.total_money);
//			String str=NumberManager.getString(money,"1",2);
//			str=NumberManager.getGeshiHua(str,2);
//			tv_money.setText(str);
//			if(index==2)
//			{
//				TextView tv=(TextView) ll1.findViewById(R.id.tv);
//				tv.setText("近一周收益(元)");
//			}
//			LayoutParams lp=new LayoutParams(LayoutParams.MATCH_PARENT,du.dip2px(135));
//			ll1.setLayoutParams(lp);
//			return ll1;
//			
//		}
		
		LinearLayout ll2=(LinearLayout) lf.inflate(R.layout.userrecord_item,ll,false);
		LinearLayout ll3=(LinearLayout) ll2.findViewById(R.id.ll_item);
		LinearLayout ll0=(LinearLayout) ll2.findViewById(R.id.ll);
		UserRecord2 ur2=(UserRecord2) list.get(position);
		TextView time=(TextView) ll2.findViewById(R.id.time);
		TextView money=(TextView) ll2.findViewById(R.id.money);
		//NumberManager.getString("1",ur2.interest,endCount);
		time.setText(ur2.dealTime);
		money.setText(NumberManager.getString("1",ur2.interest,endCount));
		LayoutParams lp=new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
		
		
		android.widget.LinearLayout.LayoutParams lp3=new android.widget.LinearLayout.LayoutParams(ur2.width,LayoutParams.MATCH_PARENT);
		if(position==0)
		{
			ll3.setBackgroundColor(0xffdd2238);
			android.widget.LinearLayout.LayoutParams lp0=new android.widget.LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,du.dip2px(32));
			lp0.topMargin=du.dip2px(16);
			lp0.leftMargin=du.dip2px(16);
			lp0.rightMargin=du.dip2px(16);
			ll0.setLayoutParams(lp0);
			//lp.topMargin=du.dip2px(16);
			//time.setText("昨日");
		}
		else
		{
			ll3.setBackgroundColor(0xffb0b0b0);
		}
		ll3.setLayoutParams(lp3);
		ll2.setLayoutParams(lp);
		
		return ll2;
	}

}
