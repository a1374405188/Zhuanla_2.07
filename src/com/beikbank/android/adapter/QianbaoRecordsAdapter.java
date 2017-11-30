package com.beikbank.android.adapter;

import java.math.BigDecimal;
import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.beikbank.android.adapter.TransactionRecordsAdapter.Holder;
import com.beikbank.android.conmon.FinalIndex;
import com.beikbank.android.data.Qianbao3;
import com.beikbank.android.data.QianbaoRecord;
import com.beikbank.android.data.TransactionInfo;
import coma.beikbank.android.R;


/**
 * 钱包交易记录适配器
 * @author Administrator
 *
 */
public class QianbaoRecordsAdapter extends MBaseAdapter {
    /**
     * 绿
     */
	private final int color1=0xff00901f;
	 /**
     * 灰
     */
	private final int color2=0xff747474;
	 /**
     * 红
     */
	private final int color3=0xffdd2238;
	private Context context;

	// private ArrayList<TransactionInfo> list;

	public QianbaoRecordsAdapter(Context context) {
		this.context = context;

	}

	@Override
	public int getCount() {

		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
	//	Qianbao3 qb = (Qianbao3) list.get(position);
		TransactionInfo tra=(TransactionInfo)list.get(position);
		Holder holder = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.item_transaction_records, null);
			holder = new Holder();
			holder.textview_transaction_records_kind = (TextView) convertView
					.findViewById(R.id.textview_transaction_records_kind);
			holder.textview_transaction_records_time = (TextView) convertView
					.findViewById(R.id.textview_transaction_records_time);
			holder.textview_transaction_records_money = (TextView) convertView
					.findViewById(R.id.textview_transaction_records_money);
			holder.textview_transaction_records_flag = (TextView) convertView
					.findViewById(R.id.textview_transaction_records_flag);
			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
		}

		holder.textview_transaction_records_time.setText(tra.purchaseDate);
		// 购买
		String isbuy = tra.tradeType;
		String msg = null;
		String money = null;
		int color = -1;
		BigDecimal bd_amount = new BigDecimal(
				tra.transactionAmount);
		bd_amount = bd_amount.setScale(2, BigDecimal.ROUND_DOWN);
		if ("1".equals(isbuy)) {
			money = "+" + bd_amount + "元";
		} else {
			money = "-" + bd_amount + "元";
		}
		//充值
		if(tra.tradeType.equals("1"))
		{
			 if("1".equals(tra.transactionStatus))
			 {
				 holder.textview_transaction_records_flag.setText("失败");
					holder.textview_transaction_records_money.setText(money);
					holder.textview_transaction_records_money
							.setTextColor(color3);
					holder.textview_transaction_records_flag.setTextColor(color2);
			 }
			 else if("0".equals(tra.transactionStatus))
			 {
				 holder.textview_transaction_records_flag
					.setText("充值成功");
			holder.textview_transaction_records_flag
					.setTextColor(color3);
			holder.textview_transaction_records_money.setText(money);
			holder.textview_transaction_records_money
					.setTextColor(color3);
			 }
			 else
			 {
				 holder.textview_transaction_records_flag
					.setText("处理中");
			
			holder.textview_transaction_records_flag
					.setTextColor(color3);
			holder.textview_transaction_records_money.setText(money);
			holder.textview_transaction_records_money
					.setTextColor(color3);
			 }
				holder.textview_transaction_records_kind
				.setText("充值");
		}
		else if(tra.tradeType.equals("2"))
		{
			if("1".equals(tra.transactionStatus))
			 {
				 holder.textview_transaction_records_flag.setText("失败");
					holder.textview_transaction_records_money.setText(money);
					holder.textview_transaction_records_money
							.setTextColor(color1);
					holder.textview_transaction_records_flag.setTextColor(color1);
			 }
			 else if("0".equals(tra.transactionStatus))
			 {
				 holder.textview_transaction_records_flag
					.setText("购买成功");
			holder.textview_transaction_records_flag
					.setTextColor(color1);
			holder.textview_transaction_records_money.setText(money);
			holder.textview_transaction_records_money
					.setTextColor(color1);
			 }
			 else
			 {
				 holder.textview_transaction_records_flag
					.setText("处理中");
			
			holder.textview_transaction_records_flag
					.setTextColor(color1);
			holder.textview_transaction_records_money.setText(money);
			holder.textview_transaction_records_money
					.setTextColor(color1);
			 }
			//holder.textview_transaction_records_kind
			//.setText("提现");
		}
		else if(tra.tradeType.equals("3"))
		{
			if("1".equals(tra.transactionStatus))
			 {
				 holder.textview_transaction_records_flag.setText("购买"+tra.productName);
					holder.textview_transaction_records_money.setText(money);
					holder.textview_transaction_records_money
							.setTextColor(color1);
					holder.textview_transaction_records_flag.setTextColor(color1);
					holder.textview_transaction_records_kind
					.setText("购买"+tra.productName);
			 }
			 else if("0".equals(tra.transactionStatus))
			 {   
				 holder.textview_transaction_records_flag.setText("购买"+tra.productName);
					holder.textview_transaction_records_money.setText(money);
					holder.textview_transaction_records_money
							.setTextColor(color1);
					holder.textview_transaction_records_flag.setTextColor(color1);
					holder.textview_transaction_records_kind
					.setText("购买"+tra.productName);
			 }
			 else
			 {
				 holder.textview_transaction_records_flag.setText("购买"+tra.productName);
					holder.textview_transaction_records_money.setText(money);
					holder.textview_transaction_records_money
							.setTextColor(color1);
					holder.textview_transaction_records_flag.setTextColor(color1);
					holder.textview_transaction_records_kind
					.setText("购买"+tra.productName);
			 }
		}
		else if(tra.tradeType.equals("4"))
		{
			if("1".equals(tra.transactionStatus))
			 {
				 holder.textview_transaction_records_flag.setText("定期到期" +
				 		"失败");
					holder.textview_transaction_records_money.setText(money);
					holder.textview_transaction_records_money
							.setTextColor(color3);
					holder.textview_transaction_records_flag.setTextColor(color2);
			 }
			 else if("0".equals(tra.transactionStatus))
			 {
				 holder.textview_transaction_records_flag
					.setText("定期到期");
			holder.textview_transaction_records_flag
					.setTextColor(color3);
			holder.textview_transaction_records_money.setText(money);
			holder.textview_transaction_records_money
					.setTextColor(color3);
			 }
			 else
			 {
				 holder.textview_transaction_records_flag
					.setText("定期到期处理中");
			
			holder.textview_transaction_records_flag
					.setTextColor(color2);
			holder.textview_transaction_records_money.setText(money);
			holder.textview_transaction_records_money
					.setTextColor(color2);
			 }
			holder.textview_transaction_records_kind
			.setText("定期到期");
		}
		else if(tra.tradeType.equals("5"))
		{
			if("2".equals(tra.transactionStatus))
			 {
				 holder.textview_transaction_records_flag.setText("已提现");
					holder.textview_transaction_records_money.setText(money);
					holder.textview_transaction_records_money
							.setTextColor(color3);
					holder.textview_transaction_records_flag.setTextColor(color1);
			 }
			 else if("0".equals(tra.transactionStatus))
			 {
				 holder.textview_transaction_records_flag
					.setText("已提现");
			holder.textview_transaction_records_flag
					.setTextColor(color1);
			holder.textview_transaction_records_money.setText(money);
			holder.textview_transaction_records_money
					.setTextColor(color1);
			 }
			 else
			 {
				 holder.textview_transaction_records_flag
					.setText("已提现");
			
			holder.textview_transaction_records_flag
					.setTextColor(color1);
			holder.textview_transaction_records_money.setText(money);
			holder.textview_transaction_records_money
					.setTextColor(color1);
			 }
			holder.textview_transaction_records_kind
			.setText("提现");
		}
		else if(tra.tradeType.equals("0"))
		{
//			if(FinalIndex.qianbao_type1.equals(tra.transactionStatus))
//			 {
//				 holder.textview_transaction_records_flag.setText("购买" +
//				 		"处理中");
//					holder.textview_transaction_records_money.setText(money);
//					holder.textview_transaction_records_money
//							.setTextColor(color3);
//					holder.textview_transaction_records_flag.setTextColor(color2);
//			 }
//			 else if(FinalIndex.qianbao_type2.equals(tra.transactionStatus))
//			 {
////			  if("0".equals(qr.productType))
////			  {
////				  holder.textview_transaction_records_flag
////					 .setText("购买定期");
////				  holder.textview_transaction_records_kind
////					.setText("购买定期");
//			  }
//			 else
//			 {
//				  holder.textview_transaction_records_flag
//					 .setText("购买活期");
//				  holder.textview_transaction_records_kind
//					.setText("购买活期");
//			 }
////			holder.textview_transaction_records_flag
////					.setTextColor(color1);
////			holder.textview_transaction_records_money.setText(money);
////			holder.textview_transaction_records_money
////					.setTextColor(color1);
////			 }
//			 else
//			 {
//				 holder.textview_transaction_records_flag
//					.setText("购买失败");
//			
//			holder.textview_transaction_records_flag
//					.setTextColor(color2);
//			holder.textview_transaction_records_money.setText(money);
//			holder.textview_transaction_records_money
//					.setTextColor(color2);
//			 }
			
			
//			
//			 if("0".equals(qr.productType))
//			  {
//				 
//				  holder.textview_transaction_records_kind
//					.setText("购买"+qr.productName);
//			  }
//			 else
//			 {
//				 
//				  holder.textview_transaction_records_kind
//					.setText("购买活期");
//			 }
//			holder.textview_transaction_records_kind
//			.setText("购买");
		}
//		holder.textview_transaction_records_kind
//				.setText(transactionInfo.productName);
//
//		// 债权
//		if (FinalIndex.jiaoyi_type4.equals(transactionInfo.productType)) {
//			// 购买
//			if (FinalIndex.jiaoyi_type1.equals(transactionInfo.tradeType)) {
//
//				// 失败
//				if (transactionInfo.getTransactionStatus().equals(
//						FinalIndex.type3)) {
//
//					holder.textview_transaction_records_flag
//							.setText("购买失败");
//					
//					holder.textview_transaction_records_flag
//							.setTextColor(color2);
//					holder.textview_transaction_records_money.setText(money);
//					holder.textview_transaction_records_money
//							.setTextColor(color2);
//
//				}
//				// 等待处理
//				else if (FinalIndex.type0.equals(transactionInfo
//						.getTransactionStatus())) {
//
//				}
//				// 处理中
//				else if (FinalIndex.type1.equals(transactionInfo
//						.getTransactionStatus())) {
//					holder.textview_transaction_records_flag.setText("购买处理中");
//					holder.textview_transaction_records_money.setText(money);
//					holder.textview_transaction_records_money
//							.setTextColor(color3);
//					holder.textview_transaction_records_flag.setTextColor(color2);
//				}
//				// 处理成功
//				else {
//
//					holder.textview_transaction_records_flag
//							.setText("购买成功");
//					holder.textview_transaction_records_flag
//							.setTextColor(color3);
//					holder.textview_transaction_records_money.setText(money);
//					holder.textview_transaction_records_money
//							.setTextColor(color3);
//
//				}
//			} 
//			//取现
//			else {
//				holder.textview_transaction_records_flag
//				.setText("取现申请已提交");
//	         	holder.textview_transaction_records_flag
//				.setTextColor(color1);
//		        holder.textview_transaction_records_money.setText(money);
//		        holder.textview_transaction_records_money
//				.setTextColor(color1);
//			}
//		} 
//		//债权
//		else {
//		    if(transactionInfo.getTransactionStatus().equals(
//					FinalIndex.type2))
//		    {
//		    	holder.textview_transaction_records_flag
//				.setTextColor(color3);
//				holder.textview_transaction_records_money
//				.setTextColor(color3);
//				holder.textview_transaction_records_flag.setText("购买成功");
//		    }
//		    else if(transactionInfo.getTransactionStatus().equals(
//					FinalIndex.type3))
//		    {
//		    	holder.textview_transaction_records_flag
//				.setTextColor(color2);
//				holder.textview_transaction_records_money
//				.setTextColor(color2);
//				holder.textview_transaction_records_flag.setText("购买失败");
//		    }
//		    else if(transactionInfo.getTransactionStatus().equals(
//					FinalIndex.type1))
//		    {
//				holder.textview_transaction_records_flag
//				.setTextColor(color2);
//				holder.textview_transaction_records_money
//				.setTextColor(color2);
//				holder.textview_transaction_records_flag.setText("购买队列中");
//		    }
//		    holder.textview_transaction_records_money.setText(money);
//		}

		return convertView;
	}

	class Holder {
		/**
		 * 类型
		 */
		TextView textview_transaction_records_kind;
		/**
		 * 时间
		 */
		TextView textview_transaction_records_time;
		/**
		 * 钱
		 */
		TextView textview_transaction_records_money;
		/**
		 * 状态
		 */
		TextView textview_transaction_records_flag;
	}
	/**
	 * 设置颜色
	 */
	private void setColor(Holder holder,TransactionInfo transactionInfo)
	{
		 if (transactionInfo.getTransactionStatus().equals(
					FinalIndex.type3))
		 {
			 holder.textview_transaction_records_money
				.setTextColor(color2);
		     holder.textview_transaction_records_flag.setTextColor(color2);
		 }
		 else if(transactionInfo.getTransactionStatus().equals(
					FinalIndex.type2))
		 {
			 holder.textview_transaction_records_money
				.setTextColor(color3);
		     holder.textview_transaction_records_flag.setTextColor(color3);
		    
		 }
		 else
		 {
			  holder.textview_transaction_records_money
				.setTextColor(0xff0976aa);
		      holder.textview_transaction_records_flag.setTextColor(0xff0976aa);
		      
		      if("1".equals(transactionInfo.tradeType))
				 {
					 
			    	 holder.textview_transaction_records_money
						.setTextColor(0xff0976aa);
				     holder.textview_transaction_records_flag.setTextColor(0xff0976aa);
				 }
		 }
		
	}
}
