package com.beikbank.android.data;

import java.io.Serializable;

import com.beikbank.android.annotation.DBPrimaryKey;

/**
 * @author yuguohe
 * <p>
 * email:1374405188@qq.com
 * </p>
 *2015-2-10
 * 
 */
public class Message implements Serializable{
	/**
	 * 标题
	 */
   public String tittle;
   /**
    * 内容
    */
   public String content;
   /**
    * 发送者
    */
   public String sender;
   /**
    * 发送时间
    */
   public String sendTime;
   /**
    * 更新时间
    */
   public String updateTime;
   //public String type;
   /**
    * 0 未读，1已读
    */
   public String readFlg;
   /**
    * 消息id
    */
   @DBPrimaryKey(privaryKey=1)
   public String id;
}
