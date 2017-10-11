package com.beikbank.android.net;

import android.os.Handler;

/**
 * @author yuguohe
 * <p>
 * email:1374405188@qq.com
 * </p>
 *2015-1-6
 * 该接口用于执行网络的操作，运行于子线程中
 */
public interface ICallBackNet 
{
    public void  back(Handler handler) throws Exception;
}
