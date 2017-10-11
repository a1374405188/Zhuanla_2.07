package com.beikbank.android.data;

import com.beikbank.android.annotation.DBPrimaryKey;

/**
 * @author yuguohe
 * <p>
 * email:1374405188@qq.com
 * </p>
 *2015-1-6
 * 
 */
public class BankList {
	/**
	 * 最小限额
	 */
	public String minLimit;
	/**
	 * 排序
	 */
	public String disorder;
	/**
	 * 累计交易限额
	 */
	public String cumulativeLimit;
	/**
	 * 单次交易限额
	 */
	public String singleLimit;
	/**
	 * 图标地址
	 */
	public String iconUrl;
	/**
	 * logo地址
	 */
	public String logoUrl;
	/**
	 * 类型
	 */
	public String type;
	/**
	 *银行名称 
	 */
	@DBPrimaryKey(privaryKey=1)
	public String bankName;
}
