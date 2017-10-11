package com.beikbank.android.data;

import java.io.Serializable;

import com.beikbank.android.annotation.ParamAnnotation;

/**
 * @author yuguohe
 * <p>
 * email:1374405188@qq.com
 * </p>
 *2015-3-21
 * 定期产品列表
 */
public class DingqiP implements Serializable
{   
	/**
	 * 产品编号
	 */
   public String termbondCode;
   /**
    *  产品类别
    */
   public String categoryId;
   /**
    * 产品列表名称
    */
   public String categoryName;
   /**
    * 产品名称
    */
   public String termbondName;
   /**
    * 显示名称
    */
   public String showName;
   /**
    * 年化收益率
    */
   public String yearRate;
   /**
    * 加送收益率
    */
   public String extraRate;
   /**
    * 理财期限
    */
   public String termbondPeriod;
   /**
    * 可购金额
    */
   public String remainAmount;
   /**
    * 资金安全
    */
   public String security;
   /**
    * 状态
    */
   public String status;
   /**
    * 开售时间
    */
   public String saleTime;
   /**
    * 单笔限额
    */
   public String singleAmountLimit;
//   /**
//    * 显示时间
//    */
//   public String showTime;
   /**
    * 倒计时
    */
   public String countdown;
}
