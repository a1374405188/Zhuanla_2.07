package com.beikbank.android.adapter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.beikbank.android.conmon.FinalIndex;
import com.beikbank.android.data.TransactionInfo;
import com.beikbank.android.utils.NumberManager;

import comc.beikbank.android.R;

public class TransactionRecordsAdapter extends MBaseAdapter {
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

	public TransactionRecordsAdapter(Context context) {
		this.context = context;
        
	}
	public TransactionRecordsAdapter(Context context,List<Object> list) {
		this.context = context;
        this.list=list;
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
		TransactionInfo transactionInfo = (TransactionInfo) list.get(position);
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

		holder.textview_transaction_records_time.setText(transactionInfo
				.getPurchaseDate());
		// 购买
	
		String mount=transactionInfo.planAmount;
	    
		holder.textview_transaction_records_kind
				.setText(transactionInfo.productName);
		holder.textview_transaction_records_money.setText(NumberManager.getGeshiHua(mount, 2)+"元");
		holder.textview_transaction_records_money
		.setTextColor(color3);
		holder.textview_transaction_records_flag.setTextColor(color3);
		if("2".equals(transactionInfo.tradeType))
		{
			holder.textview_transaction_records_flag
			.setText("银行卡购买成功");
		}
		else if("1".equals(transactionInfo.tradeType))
		{
			holder.textview_transaction_records_flag
			.setText("充值成功");
			
			holder.textview_transaction_records_kind.setText("充值");
		}
		else if("5".equals(transactionInfo.tradeType))
		{
			holder.textview_transaction_records_flag
			.setText("已提现");
			holder.textview_transaction_records_kind.setText("提现");
		}
		else if("3".equals(transactionInfo.tradeType))
		{
			holder.textview_transaction_records_flag
			.setText("钱包购买成功");
		}
		
		
		
//		// 债权
//		if (FinalIndex.jiaoyi_type4.equals(transactionInfo.productType)||"".equals(transactionInfo.productType)) {
//			// 购买
//			if (FinalIndex.jiaoyi_type1.equals(transactionInfo.tradeType)) {
//
//				// 失败
//				if (transactionInfo.getTransactionStatus().equals(
//						FinalIndex.type3)) {
//                    if("0".equals(transactionInfo.tradeMode))
//                    {
//					   holder.textview_transaction_records_flag
//							.setText("银行卡购买活期失败");
//                    }
//                    else
//                    {
//                    	holder.textview_transaction_records_flag
//						.setText("钱包购买活期失败");
//                    }
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
//					if("0".equals(transactionInfo.tradeMode))
//					{
//					    holder.textview_transaction_records_flag.setText("银行卡购买活期处理中");
//					}
//					else
//					{
//						holder.textview_transaction_records_flag.setText("钱包购买活期处理中");
//					}
//					holder.textview_transaction_records_money.setText(money);
//					holder.textview_transaction_records_money
//							.setTextColor(color3);
//					holder.textview_transaction_records_flag.setTextColor(color2);
//				}
//				// 处理成功
//				else {
//					if("0".equals(transactionInfo.tradeMode))
//					{
//				    	holder.textview_transaction_records_flag
//							.setText("银行卡购买活期成功");
//					}
//					else
//					{
//						holder.textview_transaction_records_flag
//						.setText("钱包购买活期成功");
//					}
//					holder.textview_transaction_records_flag
//							.setTextColor(color3);
//					holder.textview_transaction_records_money.setText(money);
//					holder.textview_transaction_records_money
//							.setTextColor(color3);
//
//				}
//			} 
//			//充值
//			else if("3".equals(transactionInfo.tradeType))
//			{
//				// 失败
//				if (transactionInfo.getTransactionStatus().equals(
//						FinalIndex.type3)) {
//
//					holder.textview_transaction_records_flag
//							.setText("充值失败");
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
//					holder.textview_transaction_records_flag.setText("充值处理中");
//					holder.textview_transaction_records_money.setText(money);
//					holder.textview_transaction_records_money
//							.setTextColor(color3);
//					holder.textview_transaction_records_flag.setTextColor(color2);
//				}
//				// 处理成功
//				else {
//
//					holder.textview_transaction_records_flag
//							.setText("充值成功");
//					holder.textview_transaction_records_flag
//							.setTextColor(color3);
//					holder.textview_transaction_records_money.setText(money);
//					holder.textview_transaction_records_money
//							.setTextColor(color3);
//
//				}
//			}
//			//充值
//			else if("4".equals(transactionInfo.tradeType))
//			{
//				// 失败
//				if (transactionInfo.getTransactionStatus().equals(
//						FinalIndex.type3)) {
//
//					holder.textview_transaction_records_flag
//							.setText("活期转定期失败");
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
//					holder.textview_transaction_records_flag.setText("活期转定期处理中");
//					holder.textview_transaction_records_money.setText(money);
//					holder.textview_transaction_records_money
//							.setTextColor(color3);
//					holder.textview_transaction_records_flag.setTextColor(color2);
//				}
//				// 处理成功
//				else {
//
//					holder.textview_transaction_records_flag
//							.setText("活期转定期成功");
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
//				if("1".equals(transactionInfo.tradeType))
//				{
//					
//					 holder.textview_transaction_records_kind
//						.setText("活期赎回");
//					 
//					 setColor(holder, transactionInfo);
//					 if (transactionInfo.getTransactionStatus().equals(
//								FinalIndex.type3))
//					 {
//						 holder.textview_transaction_records_flag
//							.setText("活期到钱包失败");
//					 }
//					 else if(transactionInfo.getTransactionStatus().equals(
//								FinalIndex.type2))
//					 {
//						 holder.textview_transaction_records_flag
//							.setText("活期到钱包成功");
//					 }
//					 else
//					 {
//						 holder.textview_transaction_records_flag
//							.setText("冻结中");
//					 }
//				}
//				else
//				{
//					
//					 holder.textview_transaction_records_kind
//						.setText("钱包");
//					 holder.textview_transaction_records_flag
//						.setTextColor(color1);
//					 holder.textview_transaction_records_money
//						.setTextColor(color1);
//					 setColor(holder, transactionInfo);
//					 if (transactionInfo.getTransactionStatus().equals(
//								FinalIndex.type3))
//					 {
//						 holder.textview_transaction_records_flag
//							.setText("提现失败");
//					 }
//					 else if(transactionInfo.getTransactionStatus().equals(
//								FinalIndex.type2))
//					 {
//						 holder.textview_transaction_records_flag
//							.setText("已提现");
//					 }
//					 else
//					 {
//						 holder.textview_transaction_records_flag
//							.setText("提现处理中");
//					 }
//				}
//				
//	         	
//		        holder.textview_transaction_records_money.setText(money);
//		       
//		       
//
//			}
//		} 
//		//债权
//		else {
//		  if("0".equals(transactionInfo.tradeType))
//		  {
//		    if(transactionInfo.getTransactionStatus().equals(
//					FinalIndex.type2))
//		    {
//		    	holder.textview_transaction_records_flag
//				.setTextColor(color3);
//				holder.textview_transaction_records_money
//				.setTextColor(color3);
//				if("0".equals(transactionInfo.tradeMode))
//				{
//				   holder.textview_transaction_records_flag.setText("银行卡购买定期成功");
//				}
//				else
//				{
//				   holder.textview_transaction_records_flag.setText("钱包购买定期成功");
//				}
//		    }
//		    else if(transactionInfo.getTransactionStatus().equals(
//					FinalIndex.type3))
//		    {
//		    	holder.textview_transaction_records_flag
//				.setTextColor(color2);
//				holder.textview_transaction_records_money
//				.setTextColor(color2);
//				if("0".equals(transactionInfo.tradeMode))
//				{
//				   holder.textview_transaction_records_flag.setText("银行卡购买定期失败");
//				}
//				else
//				{
//				   holder.textview_transaction_records_flag.setText("钱包购买定期失败");
//				}
//		    }
//		    else if(transactionInfo.getTransactionStatus().equals(
//					FinalIndex.type1))
//		    {
//				holder.textview_transaction_records_flag
//				.setTextColor(color2);
//				holder.textview_transaction_records_money
//				.setTextColor(color2);
//				if("0".equals(transactionInfo.tradeMode))
//				{
//				   holder.textview_transaction_records_flag.setText("银行卡购买定期处理中");
//				}
//				else
//				{
//				   holder.textview_transaction_records_flag.setText("钱包购买定期处理中");
//				}
//		    }
//		    holder.textview_transaction_records_money.setText(money);
//		  }
//		  if("4".equals(transactionInfo.tradeType))
//		  {
//		    if(transactionInfo.getTransactionStatus().equals(
//					FinalIndex.type2))
//		    {
//		    	holder.textview_transaction_records_flag
//				.setTextColor(color3);
//				holder.textview_transaction_records_money
//				.setTextColor(color3);
//				holder.textview_transaction_records_flag.setText("活期转定期成功");
//		    }
//		    else if(transactionInfo.getTransactionStatus().equals(
//					FinalIndex.type3))
//		    {
//		    	holder.textview_transaction_records_flag
//				.setTextColor(color2);
//				holder.textview_transaction_records_money
//				.setTextColor(color2);
//				holder.textview_transaction_records_flag.setText("活期转定期失败");
//		    }
//		    else if(transactionInfo.getTransactionStatus().equals(
//					FinalIndex.type1))
//		    {
//				holder.textview_transaction_records_flag
//				.setTextColor(color2);
//				holder.textview_transaction_records_money
//				.setTextColor(color2);
//				holder.textview_transaction_records_flag.setText("活期转定期处理中");
//		    }
//		    holder.textview_transaction_records_money.setText(money);
//		  }
//		  setColor(holder, transactionInfo);
//		}

		return convertView;
	}

	class Holder {
		TextView textview_transaction_records_kind;
		TextView textview_transaction_records_time;
		TextView textview_transaction_records_money;
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
		     if("1".equals(transactionInfo.tradeType))
			 {
				 
		    	 holder.textview_transaction_records_money
					.setTextColor(color1);
			     holder.textview_transaction_records_flag.setTextColor(color1);
			 }
		 }
		 else
		 {
			  holder.textview_transaction_records_money
				.setTextColor(0xff0976aa);
		      holder.textview_transaction_records_flag.setTextColor(0xff0976aa);
		      
		      if("1".equals(transactionInfo.tradeType))
				 {
					 
			    	 holder.textview_transaction_records_money
						.setTextColor(color4);
				     holder.textview_transaction_records_flag.setTextColor(color4);
				 }
		 }
		
	}
   int color4=0xff0976aa;
}
