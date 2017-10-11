package com.beikbank.android.activity.help;

import comc.beikbank.android.R;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 没有数据时页面显示
 * @author Administrator
 *
 */
public class NoneData {
	/**
	 * 
	 * @param act
	 * @param parent
	 * @param index 1活期定期详情2定期已到期   3活期转让中  4 已转让 定期已到期5交易记录6收支明细7项目信息8已购用户9定期理财收益10昨日收益11红包12消息
	 */
  public static void setView(Activity act,LinearLayout parent,int index)
  {
	  parent.removeAllViews();
	  View view=null;
	  view=LayoutInflater.from(act).inflate(R.layout.none_data, null);
	  ImageView iv=(ImageView) view.findViewById(R.id.iv);
	  TextView tv=(TextView) view.findViewById(R.id.tv);
	  if(index==1)
	  {
		  iv.setImageResource(R.drawable.empty_zichan);
		  tv.setText("这里空空的，赶紧填满它");
	  }
	  else if(index==2)
	  {
		  iv.setImageResource(R.drawable.empty_jiluxinxi);
		  tv.setText("暂无已到期资产");
	  }
	  else if(index==3)
	  {
		  iv.setImageResource(R.drawable.empty_jiluxinxi);
		  tv.setText("暂无转让中资产");
	  }
	  else if(index==4)
	  {
		  iv.setImageResource(R.drawable.empty_jiluxinxi);
		  tv.setText("暂无已转让资产");
	  }
	  else if(index==5)
	  {
		  iv.setImageResource(R.drawable.empty_jiluxinxi);
		  tv.setText("暂无交易记录");
	  }
	  else if(index==6)
	  {
		  iv.setImageResource(R.drawable.empty_jiluxinxi);
		  tv.setText("暂无收支明细");
	  }
	  else if(index==7)
	  {
		  iv.setImageResource(R.drawable.empty_wumuji);
		  tv.setText("暂无募集中的项目");
	  }
	  else if(index==8)
	  {
		  iv.setImageResource(R.drawable.empty_wuyonghu);
		  tv.setText("快来抢沙发吧");
	  }
	  else if(index==9)
	  {
		  iv.setImageResource(R.drawable.empty_shouyi);
		  tv.setText("暂无收益");
	  }
	  else if(index==10)
	  {
		  iv.setImageResource(R.drawable.empty_shouyi);
		  tv.setText("你不理财，财不理你");
	  }
	  else if(index==11)
	  {
		  iv.setImageResource(R.drawable.empty_hongbao);
		  tv.setText("暂无可用红包");
	  }
	  else if(index==12)
	  {
		  iv.setImageResource(R.drawable.empty_tongzhi);
		  tv.setText("暂无消息");
	  }
	  parent.addView(view);
  }
}
