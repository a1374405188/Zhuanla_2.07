package com.beikbank.android.utils;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.widget.Adapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.beikbank.android.adapter.MBaseAdapter;
import com.beikbank.android.adapter.TransactionRecordsAdapter;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.pullrefresh.PullToRefreshBase;
import com.beikbank.android.pullrefresh.PullToRefreshBase.OnRefreshListener;
import com.beikbank.android.pullrefresh.PullToRefreshListView;

import comc.beikbank.android.R;

/**
 * @author yuguohe
 * <p>
 * email:1374405188@qq.com
 * </p>
 *2015-3-24
 * 刷新工具类
 */
public class ViewRullUtil {
	public PullToRefreshListView prl;
	public ListView mListView;
	private ICallBack down;
	private ICallBack up;
	//private List<Object> list;
	/**
	 * 数据适配器
	 */
	public  MBaseAdapter adapter;
	public LinearLayout ll2;
	/*public int down_start=0;
	public int down_end=39;
	public int up_start=0;
	public int up_end=39;*/
	public int start=0;
	public int size=40;

	public int init;
	public ViewRullUtil(ICallBack down,ICallBack up)
	{
		this.down=down;
		this.up=up;
	}
	public void init(int size)
	{   
		start=size;
		init=size;
		
		
	}
  public  String pltext;
	/**
	 * 初始化，该方法必须被调用
	 * @param act
	 * @param ll
	 */
	public LinearLayout initView(Activity act,LinearLayout ll)
	{   
		LinearLayout ll0=new LinearLayout(act);
		if(ll==null)
		{
			ll2=(LinearLayout) act.getLayoutInflater().inflate(R.layout.view_rull_util,ll0,false);
		}
		else
		{
			ll2=ll;
		}
		prl=(PullToRefreshListView)ll2.findViewById(R.id.prl);
		mListView=prl.getRefreshableView();
		if(up==null)
		{
			prl.setPullLoadEnabled(false);
		}
		else
		{
			prl.setPullLoadEnabled(true);
		}
		if(down==null)
		{
			prl.setPullRefreshEnabled(false);
		}
		else
		{
			prl.setPullRefreshEnabled(true);
		}
		prl.setScrollLoadEnabled(false);
		
		
		
		prl.setOnRefreshListener(new OnRefreshListener<ListView>() {

			@Override
			public void onPullDownToRefresh(
					
					PullToRefreshBase<ListView> refreshView) {
			        
			   
				start=start-size;
				   
			    if(start<init)
			    {
			    	start=init;
			    	
			    }
			        
			        down.back(null);
			       
				    
			}

			@Override
			public void onPullUpToRefresh(
					PullToRefreshBase<ListView> refreshView) 
			{      
				if(pltext!=null)
				{
					refreshView.setLastUpdatedLabel("已全部加载完毕");
					return;
				}   
				   
				   
				   
				    up.back(null);
			}
		});
		//mListView.setDivider(act.getResources().getDrawable(R.color.line3));
		mListView.setDivider(act.getResources().getDrawable(R.color.line5));
		
		mListView.setDividerHeight(1);
		mListView.setScrollingCacheEnabled(false);
		mListView.setFadingEdgeLength(0);
		mListView.setAdapter(adapter);
		return ll2;
	}
	public List<Object> getList(Object obj)
	{   
		List<Object> list0;
		if(obj instanceof ArrayList)
		{   
			ArrayList<Object> list=(ArrayList<Object>) obj;
			for(int i=0;i<list.size();i++)
			{
				
			}
			list0=list;
		}
		return null;
	}
	public void doDownCompelete(List list)
	{   
		
		
		prl.onPullDownRefreshComplete();
		if(list!=null&&list.size()>0)
		{   
			adapter.list.clear();
			doData(adapter.list, list);
			adapter.notifyDataSetChanged();
//			start=start-list.size();
//			   
//		    if(start<init)
//		    {
//		    	start=init;
//		    	
//		    }
			
			 
		}
		
		
		
	}
	public void doNew(List list)
	{
		if(list!=null&&list.size()>0)
		{   
			adapter.list.clear();
			doData(adapter.list, list);
			adapter.notifyDataSetChanged();
			
		}
		
		
	}
	public void doUpCompelete(List list)
	{   
	    
		
		prl.onPullUpRefreshComplete();
		if(list!=null&&list.size()>0)
		{
			adapter.list.clear();
		  doData(adapter.list, list);
		  //((TransactionRecordsAdapter)adapter).notifyDataSetChanged();
		  adapter.notifyDataSetChanged();
		  
		  start=start+list.size();
		  
		   
		}
		else
		{
			pltext="完成";
		}
	}
	private void doData(List<Object> all,List<Object> list)
	{
		all.addAll(list);
	}
}
