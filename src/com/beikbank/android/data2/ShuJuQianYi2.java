package com.beikbank.android.data2;

import java.io.Serializable;
import java.util.ArrayList;

public class ShuJuQianYi2 implements Serializable
{
	/**
	 * 账号A银行卡号后4位
	 */
   public String bank_num;
   /**
    * 账号A银行名称
    */
   public String   bank_name;

    /**
     * id
     */
    public String   user_code;
    /**
     * 	总资产
     */
    public String   amt_total;
    /**
 * 钱包余额
 */
public String    amt_wallet;
    /**
     * 银行单日限额
     */
    public String    bank_per_day;
    /**
     * 	银行单笔限额
     */
    public String    bank_per_order;
    /**
     *
     */
    public ArrayList<String> phone_list;
}
