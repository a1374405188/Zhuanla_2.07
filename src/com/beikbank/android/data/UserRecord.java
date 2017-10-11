package com.beikbank.android.data;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author yuguohe
 * <p>
 * email:1374405188@qq.com
 * </p>
 *2015-3-2
 * 
 */
public class UserRecord implements Comparable{
	/**
	 * 收益金额
	 */
  public String interest;
  /**
   * 日期
   */
  
  public String dealTime;
  @Override
  public int compareTo(Object another) {
	  SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		try {
			UserRecord ur=(UserRecord) another;
			Date d1=df.parse(dealTime);
			Date d2=df.parse(ur.dealTime);
			if(d1.after(d2))
			{
				return -1;
			}
			else
			{
				return 1;
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
  }
}
