package com.beikbank.android.activity.help;

import java.util.List;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Handler;


import com.beikbank.android.activity.JiaoYiXiangQingActivity;
import com.beikbank.android.data.TranList_Data;
import com.beikbank.android.data.TransactionInfo;
import com.beikbank.android.data.type.DingDan;
import com.beikbank.android.dataparam.TranListParam;
import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.impl.TranListManager;

/**
 * 
 * @author Administrator 订单状态帮助
 */
public class DingdanHelp {
	Activity act0;
	int type;
	Handler handler = new Handler();

	boolean isFinish;
	Dialog dialog;
	/**
	 * 当前
	 */
	int count;

	/**
	 * 
	 * @param act
	 * @param type
	 *            1购买定期 2购买活期 3充值 4赎回 5活期转定期6提现
	 * @param dialog
	 */
	public void start(Activity act, int type, Dialog dialog) {
		act0 = act;
		this.type = type;
		this.dialog = dialog;
		handler.postDelayed(run, 3000);
		handler.postDelayed(run2, 5000);
	}

	Runnable run = new Runnable() {

		@Override
		public void run() {

			addData();

		}
	};

	Runnable run2 = new Runnable() {

		@Override
		public void run() {
			count = 2;
			if (ti != null && ti.transactionStatus.equals("2")) {
				return;
			}
			addData2();

		}
	};

	private void addData() {
		String id = BeikBankApplication.getUserid();
		TranListParam tl = new TranListParam();
		tl.startLine = "0";
		tl.endLine = "0";
		tl.memberID = id;
		tl.tradeType = "10";
		tl.productType = "2";
		tl.transactionStatus = "";
		tl.tradeMode = "";

		//new TranListManager(act0, icb1, tl).start();
	}

	private void addData2() {
		String id = BeikBankApplication.getUserid();
		TranListParam tl = new TranListParam();
		tl.startLine = "0";
		tl.endLine = "0";
		tl.memberID = id;
		tl.tradeType = "10";
		tl.productType = "2";
		tl.transactionStatus = "";
		tl.tradeMode = "";

		//new TranListManager(act0, icb2, tl).start();
	}

	TransactionInfo ti;
	private ICallBack icb1 = new ICallBack() {

		@Override
		public void back(Object obj) {
			if (obj != null) {
				TranList_Data td = (TranList_Data) obj;
				List list = td.data;
				ti = (TransactionInfo) list.get(0);

				DingDan dd = new DingDan();
				dd.amount = ti.planAmount;
				dd.fangshi = ti.tradeMode;
				dd.orderNumber = ti.orderNumber;
				dd.status = ti.transactionStatus;
				dd.leixing = type;
				dd.retMsg=ti.retMsg;
				dd.time=ti.purchaseDate;
				if (dd.status.equals("2") && isFinish == false) {
					if(dialog!=null&&dialog.isShowing())
					{
					  dialog.dismiss();
					}
					isFinish = true;
					Intent intent = new Intent(act0,
							JiaoYiXiangQingActivity.class);
					intent.putExtra(TypeUtil.jiaoyi_state, dd);
					act0.startActivity(intent);
					act0.finish();
				}

			} else {
				//dialog.dismiss();
			}
		}
	};

	private ICallBack icb2 = new ICallBack() {

		@Override
		public void back(Object obj) {
			if (obj != null) {
				TranList_Data td = (TranList_Data) obj;
				List list = td.data;
				ti = (TransactionInfo) list.get(0);

				DingDan dd = new DingDan();
				dd.amount = ti.planAmount;
				dd.fangshi = ti.tradeMode;
				dd.orderNumber = ti.orderNumber;
				dd.status = ti.transactionStatus;
				dd.leixing = type;
				dd.retMsg=ti.retMsg;
				dd.time=ti.purchaseDate;
				if (isFinish == false) {
					dialog.dismiss();
					isFinish = true;
					Intent intent = new Intent(act0,
							JiaoYiXiangQingActivity.class);
					intent.putExtra(TypeUtil.jiaoyi_state, dd);
					act0.startActivity(intent);
					act0.finish();
				}

			} else {
				dialog.dismiss();
			}
		}
	};
}
