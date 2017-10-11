package com.beikbank.android.animation;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;
 /**
 *copyright 喻国合 
 *email: 1374405188@qq.com
 *2014-12-5
 **/
public interface ToastAnimation {
	/**
	 * 在当前窗口直行一个动画
	 * @param act
	 * @param parentView 父视图 必须指明宽度高度
	 * @param tagarView  动画视图 必须指明宽度高度
	 */
    public void performToast(Activity act,final View parentView,final View tagarView);
    /**
     * 字体移动动画
     * @param tv
     * @param length 一次移动的长度 
     * @param time   一次移动的时间 毫秒
     */
    public void performTextMove(View tv,int one,int time);
//    /**
//     * 
//     * @param pView
//     * @param view
//     */
//    public void performNoticeMove(View pView,View view);
}
