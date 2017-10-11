package com.beikbank.android.dataparam;

import com.beikbank.android.annotation.ParamAnnotation;

/**
 * @author yuguohe
 * <p>
 * email:1374405188@qq.com
 * </p>
 *2015-1-10
 * 交易记录参数
 */
public class TranListParam {
	/**
	 *用户id
	 */
	@ParamAnnotation(index=0)	
  public String memberID="";
	/**
	 * 
	 */
	@ParamAnnotation(index=1)	
  public String startLine="";
	/**
	 *
	 */
	@ParamAnnotation(index=2)	
  public String endLine="";
	/**
	 *1待确认2成功3失败 其他 10全部
	 */
	@ParamAnnotation(index=3)	
 public String tradeType="";
	/**
	 *产品类型0债权1基金 以外 全部
	 */
	@ParamAnnotation(index=4)	
 public String productType="";
	/**
	 *交易状态：0:待处理,1:处理中,2:成功,3:失败  以外全部
	 */
	@ParamAnnotation(index=5)	
	public String transactionStatus="";
	/**
	 *交易模式：0:银行卡，1:余额，2：组合  以外全部
	 */
	@ParamAnnotation(index=6)	
	public String tradeMode="";
}
