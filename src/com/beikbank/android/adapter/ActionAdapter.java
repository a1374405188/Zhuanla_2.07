package com.beikbank.android.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.sax.StartElementListener;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AbsListView.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.beikbank.android.activity.ActionDetial;
import com.beikbank.android.data.Actionlist;
import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.media.AsynImageLoader;
import com.beikbank.android.utils.DensityUtil;

import comc.beikbank.android.R;

/**
 * @author yuguohe
 * <p>
 * email:1374405188@qq.com
 * </p>
 *2015-2-28
 * 活动适配器
 */
public class ActionAdapter extends BaseAdapter
{   
	List<Actionlist> list;
	Activity act;
	AsynImageLoader ail;
    public ActionAdapter(List<Actionlist> list,Activity act)
    {
    	this.list=list;
    	this.act=act;
    	countWidthAndHeight();
    	ail=new AsynImageLoader(act);
    }
    //控件的宽度
    int width;
    //控件的高度
    int height;
    //图片的高度
    int iheight;
    //计算控件宽度和高度，
    private void countWidthAndHeight()
    {
    	DensityUtil du=new DensityUtil(act);
    	int dp16=du.dip2px(16);
    	width=BeikBankApplication.getWidth(act)-dp16*2;
    	height=width/2+du.dip2px(10);
    	iheight=width/2;
    }
    
	@Override
	public int getCount() 
	{
		
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		
		return null;
	}

	@Override
	public long getItemId(int position) {
		
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) 
	{   
		LayoutParams lp=new LayoutParams(width, height);
		final Actionlist al=list.get(position);
		
		
		LinearLayout ll=null;
		LinearLayout ll0=new LinearLayout(act);
	    ll=(LinearLayout) act.getLayoutInflater().inflate(R.layout.ll_loading_img,ll0,false);
	    WebView tv=(WebView) ll.findViewById(R.id.tv);
	    //图片未加载时默认图片
	    ImageView iv=(ImageView) ll.findViewById(R.id.iv);
	    //结束图片
	    ImageView iv2=(ImageView) ll.findViewById(R.id.iv2);
	    ll.setLayoutParams(lp);

	      //iv=new ImageView(act);
		  //iv.setLayoutParams(lp);
		  //iv.setBackgroundColor(0xffffffff);R.drawable.load_net_img_def
			ImageView  iv0=null;
			String url=al.thumb;
		  if("1".equals(al.isEnd))
		  {   
			  iv0=iv2;
			  iv.setVisibility(View.GONE);
			  iv2.setVisibility(View.VISIBLE);
			  if(iv2.getId()==R.id.iv2)
			  {
				  System.out.println(123);
			  }
			  iv0.setImageResource(R.drawable.load_net_img_def);
			  ail.showImageAsyn(iv0, url,true,width,iheight);
		  }
		  else
		  {
			  iv0=iv;
			  iv0.setImageResource(R.drawable.load_net_img_def);
			  ail.showImageAsyn(iv0, url,false,width,height);
		  }
		  iv0.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(act,ActionDetial.class);
				intent.putExtra("no",al.activityNO);
				act.startActivity(intent);
			}
		  });
		

		return ll;
	}
}
