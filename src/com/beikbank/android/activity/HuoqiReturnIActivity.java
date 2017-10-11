package com.beikbank.android.activity;

import com.beikbank.android.data.FundInfo;
import com.beikbank.android.data.HuoqiReturnR_data;
import com.beikbank.android.data2.CreateDingDan_data;
import com.beikbank.android.data2.getHuoQiXiangQin;
import com.beikbank.android.dataparam.HuoqiReturnRParam;
import com.beikbank.android.dataparam2.CreateDingDanParam;
import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.impl.TongYongManager;
import com.beikbank.android.net.impl.TongYongManager2;
import com.beikbank.android.utils.BeikBankConstant;
import com.beikbank.android.utils.NumberManager;
import com.beikbank.android.utils2.StateBarColor;
import com.beikbank.android.widget.ClearableEditText;

import comc.beikbank.android.R;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 *copyright yuguohe
 *email: 1374405188@qq.com
 *2015-7-3
 *活期赎回金额输入
 */
public class HuoqiReturnIActivity extends BaseActivity2 implements OnClickListener
{   
	//
	private TextView titleTv;
	private Button button_next;
	private LinearLayout linear_left;
	private ClearableEditText et1;

	/**
	 * 钱包余额
	 */
	TextView tv_money;

	getHuoQiXiangQin gd;
	LinearLayout ll_all;
	ImageView iv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_huoqi_return_i);
		StateBarColor.init(this,0xffffffff);
		act=this;
		gd=(getHuoQiXiangQin) getIntent().getSerializableExtra("gd");
		initView();
		
	}
    private void initView()
    {   
    	ll_error=(LinearLayout) findViewById(R.id.ll_error);
    	tv_error=(TextView) findViewById(R.id.tv_error);
    	titleTv = (TextView) findViewById(R.id.titleTv);
		titleTv.setText("零活宝转让");
		linear_left = (LinearLayout) findViewById(R.id.linear_left);
		linear_left.setOnClickListener(this);
		tv_money=(TextView) findViewById(R.id.tv_money);
		tv_money.setText(NumberManager.getGeshiHua(gd.currCapValue,2)+"元");
		iv=(ImageView) findViewById(R.id.iv);
		
		button_next=(Button)findViewById(R.id.button_next);
		button_next.setOnClickListener(this);
		et1=(ClearableEditText) findViewById(R.id.et);
		setSecondPoint(et1);
		
		ll_all=(LinearLayout) findViewById(R.id.ll_all);
		ll_all.setOnClickListener(this);
		
    }
    
    private void addData()
    {    
    	 ICallBack icb=new ICallBack() {
			
			@Override
			public void back(Object obj) {
				if(obj!=null)
				{   
					CreateDingDan_data cd=(CreateDingDan_data) obj;
					Intent intent=new Intent(act, HuoqiReturnActivity.class);
					intent.putExtra("money",et1.getText().toString());
					intent.putExtra("order_id",cd.body.order_id);
					startActivity(intent);
				}
				
			}
		};
    	
    	 CreateDingDanParam cdp=new CreateDingDanParam();
    	 String acc_id=BeikBankApplication.getSharePref(BeikBankConstant.zhanghao);
    	 String bank=BeikBankApplication.getSharePref(BeikBankConstant.bank);
		 cdp.acc_id=acc_id;
		 cdp.acc_number=bank;
		 cdp.amount=et1.getText().toString();
		 cdp.assets_id=gd.assetsId;
		 
		 cdp.order_type="4";
		 cdp.pro_id=gd.prodId;
		 cdp.pro_type="";
		 cdp.user_code=BeikBankApplication.getUserCode();
		 
		 TongYongManager2 tym2=new TongYongManager2(act, icb,cdp);
		 tym2.start();
    	
    	
    	
    }
    boolean is_all=false;
	@Override
	public void onClick(View v) 
	{
		switch(v.getId()){
		case R.id.linear_left:
			finish();
			break;
		case R.id.button_next:
		     addData();
			break;
		case R.id.ll_all:
			if(is_all==false)
			{
				is_all=true;
				iv.setImageResource(R.drawable.img_futou_true);
				et1.setText(gd.currCapValue);
			}
			else
			{
				is_all=false;
				iv.setImageResource(R.drawable.img_futou_false);
				et1.setText("");
			}
			break;			
		}
	}

	public void setSecondPoint(final EditText editText) {
		editText.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				if (s.toString().contains(".")) {
					if (s.length() - 1 - s.toString().indexOf(".") > 2) {
						s = s.toString().subSequence(0,
								s.toString().indexOf(".") + 3);
						editText.setText(s);
						editText.setSelection(s.length());
					}
				}
				if (s.toString().trim().substring(0).equals(".")) {
					s = "0" + s;
					editText.setText(s);
					editText.setSelection(2);
				}

				if (s.toString().startsWith("0")
						&& s.toString().trim().length() > 1) {
					if (!s.toString().substring(1, 2).equals(".")) {
						editText.setText(s.subSequence(0, 1));
						editText.setSelection(1);
						return;
					}
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				ll_error.clearAnimation();
				ll_error.setVisibility(View.INVISIBLE);
				String money=et1.getText().toString();
				if(money.length()>0&&Double.parseDouble(money)>0){
					button_next.setEnabled(true);
				}else{
					button_next.setEnabled(false);
				}
			}

		});

	}

}
