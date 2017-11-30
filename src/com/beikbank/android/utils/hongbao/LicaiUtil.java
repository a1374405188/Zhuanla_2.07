package com.beikbank.android.utils.hongbao;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.beikbank.android.activity.ChanPinActivityV2;
import com.beikbank.android.activity.help.LiuChenSelect;
import com.beikbank.android.data.Hongbao;
import com.beikbank.android.data2.GetChanPin;
import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.huatu.YuanView;
import com.beikbank.android.utils.BeikBankConstant;
import com.beikbank.android.utils.DensityUtil;
import com.beikbank.android.utils.NumberManager;
import coma.beikbank.android.R;


/**
 * 理财工具
 * @author Administrator
 *
 */
public class LicaiUtil {
	public static ArrayList<MyTime> lists=new ArrayList<LicaiUtil.MyTime>();
	Activity act;
	public LicaiUtil(Context act)
	{
		this.act=(Activity) act;
	}
	/**
	 * 添加子view
	 * @param ll
	 * @param list
	 */
	public void addView(LinearLayout ll,List<GetChanPin> list)
	{   
		for(MyTime mt:lists)
		{
			mt.end=true;
		}
		lists.clear();
		
		Holder holder =null;
		for(int i=0;i<list.size();i++)
		{
			
			View view=LayoutInflater.from(act).inflate(R.layout.activity_licai_item, null);
			holder=new Holder();
			holder.tv_name=(TextView) view.findViewById(R.id.tv_name);
			holder.tv_shouyi=(TextView) view.findViewById(R.id.tv_shouyi);
			holder.tv_qigou=(TextView) view.findViewById(R.id.tv_qigou);
			holder.tv_qixian=(TextView) view.findViewById(R.id.tv_qixian);
			holder.tv_xinshou=(TextView) view.findViewById(R.id.tv_xinshou);
			holder.tv_goumai=(TextView) view.findViewById(R.id.tv_goumai);
			holder.tv_jiaxi=(TextView) view.findViewById(R.id.tv_jiaxi);
			holder.tv_genxin=(TextView) view.findViewById(R.id.tv_genxin);
			holder.yv=(YuanView) view.findViewById(R.id.yuan);
			
			final GetChanPin gcp=list.get(i);
			final String s1=NumberManager.getString(gcp.pro_share,"1",0);
			int a=NumberManager.isDaYu(gcp.pro_share,"0");
			if(a<=0)
			{
				holder.yv.setPaintColor(holder.yv.color1);
				holder.tv_goumai.setTextColor(0xff666666);
				holder.tv_goumai.setText("售罄");
				if(gcp.next_update_time!=null)
				{
					
					
					holder.tv_genxin.setVisibility(view.VISIBLE);
				long l=getTime(gcp.next_update_time);
				MyTime mt=new MyTime(l,holder.tv_genxin);
				mt.start();
				lists.add(mt);
				}
			}
			
			double d=Double.parseDouble(gcp.increase_interest_return_rate);
			String sy=NumberManager.getString(gcp.benchmark_return_rate,"100",2);
			if(d>0)
			{
				holder.tv_jiaxi.setVisibility(view.VISIBLE);
				String s=NumberManager.getString(gcp.increase_interest_return_rate,"100",2);
				holder.tv_jiaxi.setText("+"+s+"%");
				holder.tv_shouyi.setText(sy);
			}
			else
			{
				holder.tv_shouyi.setText(sy+"%");
			}
			
			
			holder.tv_name.setText(gcp.product_name);
		
		
			holder.tv_qigou.setText(gcp.purchase_amount+"元起购");
			if("4".equals(gcp.product_type_pid))
			{
				holder.tv_qixian.setText("灵活存取");
				holder.tv_qixian.setTextColor(0xff333333);
			}
			else
			{   
				//String s="<font color='#999999'>期限</font>"+"<font color='#333333'>"+gcp.term+"天"+"</font>";
				 SpannableStringBuilder sb = new SpannableStringBuilder("期限"+gcp.term+"天"); // 包装字体内容  
			        ForegroundColorSpan fcs = new ForegroundColorSpan(0xff333333); // 设置字体颜色  
			       // StyleSpan bss = new StyleSpan(android.graphics.Typeface.BOLD); // 设置字体样式  
			        AbsoluteSizeSpan ass = new AbsoluteSizeSpan(DensityUtil.sp2px(act,21));  // 设置字体大小  
			        sb.setSpan(fcs, 2,gcp.term.length()+3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);  
			        //sb.setSpan(bss, 0, 20, Spannable.SPAN_INCLUSIVE_INCLUSIVE);  
			        sb.setSpan(ass,2,gcp.term.length()+3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);  
				
				
				holder.tv_qixian.setText(sb);
			}
			if("0".equals(gcp.is_new_user_mark))
			{
				holder.tv_xinshou.setVisibility(view.VISIBLE);
			}
			
			ll.addView(view);
			view.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
				 Intent intent=new Intent(act,ChanPinActivityV2.class);
				 intent.putExtra("index1",gcp);
				 act.startActivity(intent);
					
				}
			});
			holder.tv_goumai.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					if("0".equals(s1))
					{
						
					}
					else
					{
					LiuChenSelect ls=new LiuChenSelect();
					ls.startNext(act,2,gcp);
					}
					
				}
			});
		}
		
	}
	private long getTime(String time)
	{   
		 // 设置传入的时间格式  
	      SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");  
	      // 指定一个日期  
	      Date date=null;
		try {
			date = dateFormat.parse(time);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		 int h=date.getHours();
		 int m=date.getMinutes();
		 int s=date.getSeconds();
	     long l=h*3600+m*60+s;
		return l;
	}
	class MyTime extends Thread
	{   
		public boolean end=false;
		
		public MyTime(long time,TextView tv)
		{
			this.time=time;
			this.tv=tv;
		}
		public long time;
		TextView tv;
        public Handler handler=new Handler()
        {

			@Override
			public void handleMessage(Message msg) {
				if(msg.what==1)
				{
					tv.setText(msg.obj.toString());
				}
				if(msg.what==2)
				{
					tv.setVisibility(View.GONE);
				}
			}
        	
        };
		@Override
		public void run() 
		{
			time1();
		}
    private  void time1() 
    {
    	while (time > 0) {
             time--;
             try {
            	 if(end)
            	 {
            		 break;
            	 }
                 Thread.sleep(1000);
                 long hh = time / 60 / 60 % 60;
                 long mm = time / 60 % 60;
                 long ss = time % 60;
                 Message msg=new Message();
                 msg.what=1;
                 String s1=hh+":"+mm+":"+ss;
                 msg.obj="下次份额更新 "+s1;
                 handler.sendMessage(msg);
                //System.out.println("还剩" + hh + "小时" + mm + "分钟" + ss + "秒");
            } catch (InterruptedException e) {
                 e.printStackTrace();
             }
         }
    	 Message msg=new Message();
         msg.what=2;
         handler.sendMessage(msg);
     }
		
	}
	class Holder{
		TextView tv_name;
		TextView tv_shouyi;
		TextView tv_qixian;
		TextView tv_qigou;
		TextView tv_xinshou;
		TextView tv_goumai;
		TextView tv_jiaxi;
		TextView tv_genxin;
		YuanView yv;
	}
}
