package com.beikbank.android.data;

import java.util.ArrayList;

public class BankListInfo {
	public ArrayList<BankInfo> bankList;
	public boolean hasUpdate;
	public String version;

	public ArrayList<BankInfo> getBankList() {
		return bankList;
	}

	public void setBankList(ArrayList<BankInfo> bankList) {
		this.bankList = bankList;
	}

	public boolean isHasUpdate() {
		return hasUpdate;
	}

	public void setHasUpdate(boolean hasUpdate) {
		this.hasUpdate = hasUpdate;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
	
	
}
