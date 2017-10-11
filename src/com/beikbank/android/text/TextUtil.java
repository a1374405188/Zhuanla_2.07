package com.beikbank.android.text;

import com.beikbank.android.conmon.DisplayManger;

import android.app.Activity;
import android.widget.TextView;

/**
 * 处理字体相关
 * @author Administrator
 *
 */
public class TextUtil {
	/**
	 * 根据文本设置空间的大小
	 * @param tv
	 * @param text
	 */
  public static void setSize(Activity act,TextView tv,String text)
  {
	  if(text==null||text.equals("")||tv==null)
	  {
		  return;
	  }
	  int size=0;
	  size=DisplayManger.getWidth(act)/25;
	  
	  if(text.length()>8)
	  {
		  tv.setTextSize(size);
	  }
	  
  }
}
