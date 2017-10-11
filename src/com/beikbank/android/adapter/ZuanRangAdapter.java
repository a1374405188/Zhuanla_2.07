package com.beikbank.android.adapter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.beikbank.android.activity.DingqiDetailActivity;
import com.beikbank.android.activity.DingqiLicaiActivity;
import com.beikbank.android.activity.ForgetPwdRealnameActivity;
import com.beikbank.android.activity.HuoqiZhuanRangActivity;
import com.beikbank.android.activity.LoginPwdInputActivity;
import com.beikbank.android.animation.AnimationManager;
import com.beikbank.android.animation.ToastAnimation;
import com.beikbank.android.conmon.FinalIndex;
import com.beikbank.android.data.ProjectInfo;
import com.beikbank.android.data2.CheckJiaoYiMiMa_data;
import com.beikbank.android.data2.getDingQiXiangQin;
import com.beikbank.android.data2.getHuoQiXiangQin2_1;
import com.beikbank.android.data2.getXiangMuXinXi;
import com.beikbank.android.dataparam2.CheckJiaoYiMiMaParam;
import com.beikbank.android.dataparam2.HuTouOpenOrCloseParam;
import com.beikbank.android.dataparam2.HuoQiShuHuiParam2;
import com.beikbank.android.dataparam2.checkYanZhenMaParam;
import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.impl.TongYongManager2;
import com.beikbank.android.utils.BeikBankConstant;
import com.beikbank.android.utils.DialogManager;
import com.beikbank.android.utils.MD5;
import com.beikbank.android.utils.NumberManager;
import com.beikbank.android.widget.ClearableEditText;

import comc.beikbank.android.R;
/**
 * 
 * @author Administrator
 * 转让
 */
public class ZuanRangAdapter extends BaseAdapter{

	private HuoqiZhuanRangActivity context;
	private ArrayList<getHuoQiXiangQin2_1> list;
    private Map<Integer,Boolean> map=new HashMap<Integer, Boolean>();
	public ZuanRangAdapter(Context context,ArrayList<getHuoQiXiangQin2_1> list,boolean all){
		this.context=(HuoqiZhuanRangActivity) context;
		this.list=list;
		
		for(int i=0;i<list.size();i++)
		{   
			if(all)
			{
				map.put(i,true);
			}
			else
			{
			   map.put(i,false);
			}
		}
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
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
		final getHuoQiXiangQin2_1 ghq=list.get(position);
		Holder holder =null;
		if(convertView==null){
			convertView=LayoutInflater.from(context).inflate(R.layout.activity_huoqi_zhuanrang_item, null);
			holder=new Holder();
		
			holder.tv_name=(TextView) convertView.findViewById(R.id.tv_name);
			holder.tv_dangqian=(TextView) convertView.findViewById(R.id.tv_dangqian);
			holder.tv_money=(TextView) convertView.findViewById(R.id.tv_money);
			holder.tv_state=(TextView) convertView.findViewById(R.id.tv_state);
			holder.tv_zuori=(TextView) convertView.findViewById(R.id.tv_zuori);
			holder.iv= (ImageView) convertView.findViewById(R.id.iv);
			
			convertView.setTag(holder);
			
			LinearLayout ll=(LinearLayout) convertView.findViewById(R.id.ll);
			
			ll.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						
//						Intent intent=new Intent(context,DingqiDetailActivity.class);
//						//intent.putExtra(BeikBankConstant.IS_FORGETTRANSACTIONPWD,true);
//						//intent.putExtra(BeikBankConstant.INTENT_PHONENUMBER,BeikBankApplication.getPhoneNumber());
//						intent.putExtra("name",gdq.product_name);
//						intent.putExtra("assets_id",gdq.assets_id);
//						intent.putExtra("prod_id",gdq.prod_Id);
//						context.startActivity(intent);
						boolean b=map.get(position);
						ImageView iv= (ImageView)v.findViewById(R.id.iv);
						if(b)
						{
							map.put(position,false);
							iv.setImageResource(R.drawable.img_futou_false);
						}
						else
						{
							map.put(position,true);
							iv.setImageResource(R.drawable.img_futou_true);
						}
						
						String money="0";
						for(Integer i:map.keySet())
						{
							boolean b1=map.get(i);
							if(b1)
							{
								money=NumberManager.getAddString(money,list.get(i).calAmount,4);
							}
						}
						context.setMoney(money);
					}
				});
		}else{
			holder=(Holder) convertView.getTag();
		}	
		holder.tv_zuori.setText(NumberManager.getGeshiHua(ghq.intrestYesterday,2));
	    holder.tv_state.setText(ghq.status);
	    holder.tv_dangqian.setText(NumberManager.getGeshiHua(ghq.intrestTotal,2));
	    holder.tv_money.setText(NumberManager.getGeshiHua(ghq.calAmount,2));
		holder.tv_name.setText(ghq.prodName);
		boolean b=map.get(position);
		if(b)
		{
			
			holder.iv.setImageResource(R.drawable.img_futou_true);
		}
		else
		{
			
			holder.iv.setImageResource(R.drawable.img_futou_false);
		}
	  
	  
	  
	  
		return convertView;
	}
	
	class Holder{
		TextView tv_name;
		
		TextView tv_dangqian;
		TextView tv_zuori;
		TextView tv_money;
		TextView tv_state;
		ImageView iv;
	}
	Dialog   dialog4;
	ClearableEditText et_mima;
	TextView tv_quereng;
	private void createDialog(final ImageView iv,final String id)
	{
		 LinearLayout ll=new LinearLayout(context);
		    View v=LayoutInflater.from(context).inflate(
	  			   R.layout.check_jiaoyimima,ll,false);
		    
		   et_mima=(ClearableEditText) v.findViewById(R.id.et_mima);
		    et_mima
			.addTextChangedListener(new TextWatcherListener());
		    TextView tv_qvxiao=(TextView) v.findViewById(R.id.tv_qvxiao);
		    tv_quereng=(TextView) v.findViewById(R.id.tv_quereng);
		    context.tv_error=(TextView) v.findViewById(R.id.tv_error);
		    context.ll_error=(LinearLayout) v.findViewById(R.id.ll_error);
		    TextView tv_mima=(TextView) v.findViewById(R.id.tv_mima);
		   
		    
		    
		    tv_mima.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent intent=new Intent(context,ForgetPwdRealnameActivity.class);
					intent.putExtra(BeikBankConstant.IS_FORGETTRANSACTIONPWD,true);
					intent.putExtra(BeikBankConstant.INTENT_PHONENUMBER,BeikBankApplication.getPhoneNumber());
					context.startActivity(intent);
					
				}
			});
		    tv_qvxiao.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					dialog4.dismiss();
					
				}
			});
		    tv_quereng.setOnClickListener(new OnClickListener() {
				
						@Override
						public void onClick(View v) {
							
							String s=et_mima.getText().toString();
							if(s!=null&&!"".equals(s))
							{
								checkJiaoyi(iv,s,id);
							}
						}
					});
		    dialog4=DialogManager.getDiaolg1(context, v);
	        dialog4.setCanceledOnTouchOutside(false);
	    	dialog4.show();
	}  
	
	   /**
	    * 显示界面消息
	    * @return
	    */
	   public boolean showToast(String text)
	   {
		   if(context.ll_error==null||context.tv_error==null)
		   {
			   return false;
		   }
		   context.ll_error.setVisibility(View.VISIBLE);
		   context.tv_error.setText(text);
		   ToastAnimation ta=(ToastAnimation) AnimationManager.getAnimation(AnimationManager.TOAST_ANIMATION1);
		   ta.performTextMove(context.ll_error,40,50);
		   return true;
	   }
	
	private void checkJiaoyi(final ImageView iv,String passwd,final String id)
	{   
		ICallBack icb=new ICallBack() {
			
			@Override
			public void back(Object obj) {
				if(obj!=null)
				{   
					if(obj instanceof CheckJiaoYiMiMa_data)
					{
						
					
					CheckJiaoYiMiMa_data cd=(CheckJiaoYiMiMa_data) obj;
					if("0000".equals(cd.header.re_code))
					{   
						
						if("0".equals(iv.getTag()))
						{
							iv.setImageResource(R.drawable.img_futou_false);
							iv.setTag("1");
						}
						else
						{
							iv.setImageResource(R.drawable.img_futou_true);
							iv.setTag("0");
						}
						
						HuTouOpenOrCloseParam hp=new HuTouOpenOrCloseParam();
						hp.is_reinvest=(String) iv.getTag();
						hp.assets_id=id;
						TongYongManager2 tym2=new TongYongManager2((Activity) context, icb_hutou,hp);
						tym2.start();
						dialog4.dismiss();
					}
					  else
					  {
						  showToast(cd.header.re_msg);
						  Log.d("msg",cd.header.re_msg);
					  }
					}
					
					
				}
				
			}
		};
		CheckJiaoYiMiMaParam cp=new CheckJiaoYiMiMaParam();
		cp.tra_password=MD5.md5s32(passwd);
		cp.user_code=BeikBankApplication.getUserCode();
		TongYongManager2  tym2=new TongYongManager2((Activity)context, icb,cp);
		tym2.start();
	}
	ICallBack icb_hutou=new ICallBack() {
		
		@Override
		public void back(Object obj) {
			// TODO Auto-generated method stub
			
		}
	};
	
	
	public String getMoney()
	{
		String money="0";
		for(Integer i:map.keySet())
		{
			boolean b1=map.get(i);
			if(b1)
			{
				money=NumberManager.getAddString(money,list.get(i).calAmount,4);
			}
		}
		return money;
	}
	public ArrayList<Integer> getList()
	{   
		ArrayList<Integer> list=new ArrayList<Integer>();
		for(Integer i:map.keySet())
		{   
			boolean b=map.get(i);
			if(b)
			{ 
			  int a=Integer.parseInt(this.list.get(i).assets_id);
			  list.add(a);
			}
		}
		return list;
	}
	class TextWatcherListener implements TextWatcher {
		@Override
		public void afterTextChanged(Editable s) {
			// TODO Auto-generated method stub

		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			// TODO Auto-generated method stub
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			if(s.length()>=6)
			{
				tv_quereng.setTextColor(0xff333333);
			}
			else
			{
				tv_quereng.setTextColor(0xff999999);
			}
			
		}

	}
}
