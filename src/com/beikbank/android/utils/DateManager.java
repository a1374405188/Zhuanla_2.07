package com.beikbank.android.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @author yuguohe
 * <p>
 * email:1374405188@qq.com
 * </p>
 *2015-3-2
 * 日期时间管理工具
 */
public class DateManager {
	
	/**
	 * 得到给定日期所在周所有的日期
	 * 日期格式 "yyyy-MM-dd"
	 * @param time
	 * @return
	 */
	public static List<String> convertWeekByDate(Date time) {  

	     
        List<String> list=new ArrayList<String>();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式  

        Calendar cal = Calendar.getInstance();  

        cal.setTime(time);  

        //判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了  

       int dayWeek = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天  

        if(1 == dayWeek) {  

           cal.add(Calendar.DAY_OF_MONTH, -1);  

        }  

       System.out.println("要计算日期为:"+sdf.format(cal.getTime())); //输出要计算日期  

       cal.setFirstDayOfWeek(Calendar.MONDAY);//设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一  

       int day = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天  

       cal.add(Calendar.DATE, cal.getFirstDayOfWeek()-day);//根据日历的规则，给当前日期减去星期几与一个星期第一天的差值   

//       String imptimeBegin = sdf.format(cal.getTime()); 
//       System.out.println("所在周星期一的日期："+imptimeBegin);  
//
//       cal.add(Calendar.DATE, 6);  
//
//        String imptimeEnd = sdf.format(cal.getTime());  
//
//       System.out.println("所在周星期日的日期："+imptimeEnd);  
       for(int i=0;i<7;i++)
       {
    	   cal.add(Calendar.DATE,i); 
    	   list.add(sdf.format(cal.getTime()));
       }

        

         return list;

   }  
	/**
	 * 将秒变为 如21:30:26
	 * @return
	 */
	public static String countDateSub(long times)
	{    
		  String s1="00";

		try
		{

		  long hour=(times/(60*60));
		  long min=((times/(60))-hour*60);
		  long s=(times-hour*60*60-min*60);
		  
		 s1=countTime(hour)+":"+countTime(min)+":"+countTime(s);
//		  System.out.println(hour+"小时"+min+"分"+s+"秒");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
      
		return s1;
	}
	private static String countTime(long time)
	{
		String s="00";

		if(time>=0&&time<10)
		{
			s="0"+time;
		}
		else
		{
			s=time+"";
		}
		
		return s;
	}
}
