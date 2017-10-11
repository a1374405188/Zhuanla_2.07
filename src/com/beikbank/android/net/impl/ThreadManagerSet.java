package com.beikbank.android.net.impl;

import java.util.ArrayList;

import com.beikbank.android.net.HandlerBase;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.ThreadManager;

/**
 * @author yuguohe
 * <p>
 * email:1374405188@qq.com
 * </p>
 *2015-1-21
 *@see ThreadManagerImpls
 * 进行多个网络请求的工具类
 * 
 */
public class ThreadManagerSet extends ThreadManager{
	/**
	 * 多线程同步加锁对象
	 */
    public static Object syn_obj=new Object();
	public ThreadManagerSet(ICallBack icb)
	{
		count=0;
		total_count=0;
		this.icb=icb;
	}
    public ArrayList list=new ArrayList();
    /**
     * 线程执行完记数
     */
    public static int count;
    /**
     * 需要完成的任务
     */
    public static int total_count;
    /**
     * 添加任务
     * @param tm
     */
    public void add(ThreadManager tm)
    {
    	list.add(tm);
    }
	@Override
	public void start() 
	{
	  if(list.size()==0)
	  {
		  throw new IllegalStateException("no have cound execute ThreadManager");
	  }
	  HandlerBase.global_erroe=false;
	  total_count=list.size();
      for(int i=0;i<list.size();i++)
      {
    	  ThreadManager tm=(ThreadManager) list.get(i);
    	  new ThreadManagerImpls(tm.act,this, tm.icbn, tm.icbh, tm.error_code).start();
      }
	}
	@Override
	public void finish() 
	{
		count++;
    	if(count==total_count)
    	{
    		icb.back(null);
    	}
	}
}
