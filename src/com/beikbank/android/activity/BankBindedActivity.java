package com.beikbank.android.activity;
//package com.beikbank.android.activity;
//
//import java.util.ArrayList;
//
//import android.app.Activity;
//import android.app.Dialog;
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.widget.Button;
//import android.widget.LinearLayout;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//
//import com.beikbank.android.R;
//import com.beikbank.android.dao.BankListDao;
//import com.beikbank.android.dao.TableDaoSimple;
//import com.beikbank.android.data.BankInfo;
//import com.beikbank.android.data.BankList;
//import com.beikbank.android.data.CardInfo;
//import com.beikbank.android.utils.BeikBankDialogListener;
//import com.beikbank.android.utils.Utils;
//
////已经绑卡成功的用户，进行额度提升
//public class BankBindedActivity extends BaseActivity implements OnClickListener{
//
//	private final String TAG="BankBindedActivity";
//	private Button button_next;
//	private TextView titleTv,textview_cardnumber,textview_bankname,textview_check;
//	private LinearLayout linear_left;
//
//	private Dialog dialog;
//	private boolean isPhoneVerfi=true;
//	private RelativeLayout relative_check;
//	private ArrayList<String> items;
//
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_bank_binded);
//		initView();
//
//	}
//	public void initView(){
//		titleTv = (TextView) findViewById(R.id.titleTv);
//		titleTv.setText(getString(R.string.confirm_cardinfo));
//
//		linear_left = (LinearLayout) findViewById(R.id.linear_left);
//		linear_left.setVisibility(View.VISIBLE);
//		linear_left.setOnClickListener(this);
//
//		textview_cardnumber=(TextView)findViewById(R.id.textview_cardnumber);
//		button_next=(Button)findViewById(R.id.button_next);
//		button_next.setOnClickListener(this);
//		textview_bankname=(TextView)findViewById(R.id.textview_bankname);
//
//		//CardInfo cardInfo=CardInfoDao.getInstance(BankBindedActivity.this).getCardInfo();
//		CardInfo cardInfo=(CardInfo) TableDaoSimple.queryone(CardInfo.class,null,null);
//		//BankInfo bankInfo=BankInfoDao.getInstance(BankBindedActivity.this).getBankInfoByType(cardInfo.getType());
//		BankList bl=BankListDao.getBankByType(cardInfo.getType());
//		textview_bankname.setText(bl.bankName);
//		textview_cardnumber.setText(cardInfo.getCardNumber());
//		
//		relative_check=(RelativeLayout)findViewById(R.id.relative_check);
//		relative_check.setOnClickListener(this);
//		textview_check=(TextView)findViewById(R.id.textview_check);
//
//
//     、、、
//	}
//
//
//	@Override
//	public void onClick(View v) {
//		// TODO Auto-generated method stub
//		switch(v.getId()){
//		case R.id.button_next:
//			if(isPhoneVerfi){
//				startAimActivity(BankConfirmActivity.class);
//			}else{
//				startAimActivity(BankCheckActivity.class);
//			}
//
//			break;
//		case R.id.linear_left:
//			finish();
//			break;
//		case R.id.relative_check:
//			items=new ArrayList<String>();
//			items.clear();
//			items.add("（有银行预留手机号）手机验证");
//			items.add("（无银行预留手机号）小额打款");
//			dialog=Utils.createListViewDialog(BankBindedActivity.this, "选择验证方式", items, new BeikBankDialogListener() {
//
//				@Override
//				public void onRightBtnClick() {
//					// TODO Auto-generated method stub
//
//				}
//
//				@Override
//				public void onListItemLongClick(int position, String string) {
//					// TODO Auto-generated method stub
//
//				}
//
//				@Override
//				public void onListItemClick(int position, String string) {
//					// TODO Auto-generated method stub
//					String str=items.get(position);
//					textview_check.setText(str);
//					if(position==0){
//						isPhoneVerfi=true;
//					}else{
//						isPhoneVerfi=false;
//					}
//				}
//
//				@Override
//				public void onLeftBtnClick() {
//					// TODO Auto-generated method stub
//
//				}
//
//				@Override
//				public void onCancel() {
//					// TODO Auto-generated method stub
//
//				}
//			});
//			dialog.show();
//			break;
//		}
//	}
//
//
//	@Override
//	protected void onDestroy() {
//		// TODO Auto-generated method stub
//		super.onDestroy();
//	}
//
//	protected <T> void startAimActivity(final Class<T> pActClassName) {
//		Intent _Intent = new Intent();
//		_Intent.setClass(this, pActClassName);
//		startActivity(_Intent);
//	}
//
//}
