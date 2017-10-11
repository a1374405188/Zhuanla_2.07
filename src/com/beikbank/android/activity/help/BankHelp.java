package com.beikbank.android.activity.help;

import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.utils.BeikBankConstant;

import android.widget.TextView;

public class BankHelp {
	/**
	 * 
	 * @param bank
	 * @param xiane
	 */
    public static void setBnak(TextView tv_bank,TextView tv_xiane)
    {
    	String bank=BeikBankApplication.getSharePref(BeikBankConstant.bank);
		String bank_name=BeikBankApplication.getSharePref(BeikBankConstant.bank_name);
		String bank_xiane=BeikBankApplication.getSharePref(BeikBankConstant.bank_xiane);
		tv_bank.setText(bank_name+"尾号("+bank.substring(bank.length()-4,bank.length())+")");
		if(tv_xiane!=null)
		{
			tv_xiane.setText("单笔限额"+bank_xiane+"万元");
		}
		
    }
}
