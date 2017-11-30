package com.beikbank.android.utils;

import com.beikbank.android.conmon.DisplayManger;
import coma.beikbank.android.R;



import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.WindowManager;
 /**
 *copyright 喻国合 
 *email: 1374405188@qq.com
 *2014-12-9
 **/
public class DialogManager {
	/**
	 * 购买确认对话框
	 * @return
	 */
   public static Dialog getDiaolg1(Context context,View v)
   {
	   Dialog d=null;
	   d = new Dialog(context, R.style.mxx_theme_dialog);
	   d.setCanceledOnTouchOutside(false);
	   d.setContentView(v);
	   d.setCancelable(false);
	   WindowManager.LayoutParams params= d.getWindow().getAttributes();
	   params.width=(int)(DisplayManger.getWidth((Activity) context)*0.8);
	   return d;
   }
   /**
	 * 创建可取消的对话框
	 * @return
	 */
  public static Dialog getDiaolg2(Context context,View v)
  {
	   Dialog d=null;
	   d = new Dialog(context, R.style.mxx_theme_dialog);
	   d.setCanceledOnTouchOutside(true);
	   d.setContentView(v);
	   d.setCancelable(true);
	   WindowManager.LayoutParams params= d.getWindow().getAttributes();
	   params.width=(int)(DisplayManger.getWidth((Activity) context)*0.8);
	   return d;
  }
  /**
 	 * 创建分享对话框
 	 * @return
 	 */
   public static Dialog getDiaolg3(Context context,View v)
   {
 	   Dialog d=null;
 	   d = new Dialog(context, R.style.mxx_theme_dialog);
 	   d.setCanceledOnTouchOutside(true);
 	   d.setContentView(v);
 	   d.setCancelable(true);
 	   WindowManager.LayoutParams params= d.getWindow().getAttributes();
 	   params.width=(int)(DisplayManger.getWidth((Activity) context)*0.8);
 	   params.height=params.WRAP_CONTENT;
 	   return d;
   }
}
