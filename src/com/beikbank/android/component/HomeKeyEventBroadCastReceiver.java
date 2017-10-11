package com.beikbank.android.component;

import com.beikbank.android.activity.BaseActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * copyright 喻国合 email: 1374405188@qq.com 2014-12-11
 **/
public class HomeKeyEventBroadCastReceiver extends BroadcastReceiver {
	public static final String SYSTEM_REASON = "reason";
	public static final String SYSTEM_HOME_KEY = "homekey";// home key
	public static final String SYSTEM_RECENT_APPS = "recentapps";// long home
																	// key

	@Override
	public void onReceive(Context context, Intent intent) {

		String action = intent.getAction();
		if (action.equals(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)) {
			String reason = intent.getStringExtra(SYSTEM_REASON);
			if (reason != null) {
				if (reason.equals(SYSTEM_HOME_KEY)) {
					// home key处理点
					BaseActivity.isUnLock=false;

				} else if (reason.equals(SYSTEM_RECENT_APPS)) {
					// long home key处理点
				}
			}
		}

	}

}
