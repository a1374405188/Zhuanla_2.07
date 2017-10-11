package com.beikbank.android.net;

import android.os.Message;

/**
 * @author yuguohe
 * <p>
 * email:1374405188@qq.com
 * </p>
 *2015-1-8
 * 该接口用于执行网络返回后的操作，运行于主线程中
 */
public interface ICallBackHnadler {
   public void back(Message msg);
}
