package com.beikbank.android.conmon;

import android.app.Activity;
import android.widget.Toast;
 /**
 *copyright 喻国合 
 *email: 1374405188@qq.com
 *2014-12-4
 **/
public class MessageManger {
   public static void showMeg(Activity act,String text,int type)
   {
	   Toast.makeText(act, text,type).show();
   }
}
