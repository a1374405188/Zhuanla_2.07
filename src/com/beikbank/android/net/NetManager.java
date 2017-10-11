package com.beikbank.android.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.beikbank.android.conmon.SystemConfig;

import android.util.Log;

/**
 * 
 * @author Administrator
 *网络检测和管理
 */
public class NetManager 
{   
	/**
	 * 判断某个地址是否能连接
	 * @return
	 */
	private static final boolean ping() {

		String result = null;

		try {

		String ip = SystemConfig.kHOST_URL;// 除非百度挂了，否则用这个应该没问题~

		Process p = Runtime.getRuntime().exec("ping -c 3 -w 100 " + ip);//ping3次


		// 读取ping的内容，可不加。

		InputStream input = p.getInputStream();

		BufferedReader in = new BufferedReader(new InputStreamReader(input));

		StringBuffer stringBuffer = new StringBuffer();

		String content = "";

		while ((content = in.readLine()) != null) {

		stringBuffer.append(content);

		}

		Log.i("TTT", "result content : " + stringBuffer.toString());


		// PING的状态

		int status = p.waitFor();

		if (status == 0) {

		result = "successful~";

		return true;

		} else {

		result = "failed~ cannot reach the IP address";

		}

		} catch (IOException e) {

		result = "failed~ IOException";

		} catch (InterruptedException e) {

		result = "failed~ InterruptedException";

		} finally {

		Log.i("TTT", "result = " + result);

		}

		return false;

		}
}
