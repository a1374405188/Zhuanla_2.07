package com.beikbank.android.activity.help;

import coma.beikbank.android.R;

import android.app.Activity;
import android.content.Intent;

public class ActivitySwitchHelp {
  public static void start(Activity act,Intent intent,boolean isend)
  {
	  
	  act.startActivity(intent);
	  if(isend)
	  {
		  act.finish();
	  }
	  //act.overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out); 
	 // act.overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
	  //act.overridePendingTransition(R.anim.zoomin, R.anim.zoomout); 


  }
}
