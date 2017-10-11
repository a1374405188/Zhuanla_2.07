package com.beikbank.lib.interfaces;

import android.content.Context;

/**
 *copyright yuguohe
 *email: 1374405188@qq.com
 *2015-6-24
 */
public interface LibInterface {
   public void onCreate(Context con,int i);
   public void onStart(Context con,int i);
   public void onRestart(Context con,int i);
   public void onResume(Context con,int i);
   public void onPause(Context con,int i);
   public void onStop(Context con,int i);
   public void onDestroy(Context con,int i);
}
