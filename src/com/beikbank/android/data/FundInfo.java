package com.beikbank.android.data;

import java.io.Serializable;

import com.beikbank.android.annotation.DBPrimaryKey;

/**
 * 基金产品信息
 */
public class FundInfo extends BaseData implements Serializable{
    /**
     * 基金Id
     */
	@DBPrimaryKey(privaryKey=1)
    public String sid="";
    /**
     * 年化收益率
     */
    public String rate="";
    /**
     * 累计投资金额
     */
    public String totalInvestment="";
    /**
     * 累计收益
     */
    public String totalIncome="";
    /**
     * 基金名称
     */
    public String name="";
    /**
     * 投资项目数
     */
    public String totalProjects="";
    /**
     * 累计用户
     */
    public String totalInvestors="";
    
    /**
     *  可够余额
     */
   public String fundAmount="";
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public String getTotalInvestment() {
		return totalInvestment;
	}
	public void setTotalInvestment(String totalInvestment) {
		this.totalInvestment = totalInvestment;
	}
	public String getTotalIncome() {
		return totalIncome;
	}
	public void setTotalIncome(String totalIncome) {
		this.totalIncome = totalIncome;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTotalProjects() {
		return totalProjects;
	}
	public void setTotalProjects(String totalProjects) {
		this.totalProjects = totalProjects;
	}
	public String getTotalInvestors() {
		return totalInvestors;
	}
	public void setTotalInvestors(String totalInvestors) {
		this.totalInvestors = totalInvestors;
	}
    
	

}
