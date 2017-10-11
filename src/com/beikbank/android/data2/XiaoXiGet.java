package com.beikbank.android.data2;

import java.io.Serializable;
import java.util.ArrayList;

import com.beikbank.android.data.TransactionInfo;

public class XiaoXiGet implements Serializable{
	/**
	 * 创建时间
	 */
  public String  send_time;
	/**
	 * 是否已读  0----已读;1----未读; 默认1
	 */
public String  is_read;
/**
 * 内容
 */
public String  msg_content;
 
/**
 * 消息id
 */
public String   msg_log_id;
/**
 * 标题
 */
public String   msg_title;
}
