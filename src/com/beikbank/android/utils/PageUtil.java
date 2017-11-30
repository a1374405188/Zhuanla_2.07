package com.beikbank.android.utils;

import com.beikbank.android.data.Share;
import com.beikbank.android.data.Share_data;
import com.beikbank.android.dataparam.ShareParam;
import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.ImageUrl;
import com.beikbank.android.net.ManagerParam;
import com.beikbank.android.net.impl.TongYongManager;
import com.beikbank.android.share.ShareMUtil;
import com.beikbank.android.share.ShateUtil;
import coma.beikbank.android.R;



import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class PageUtil {
	Activity act;
	String shouyi;
	Share share;
	public PageUtil(Activity act,String shouyi)
	{
		this.act=act;
		this.shouyi=shouyi;
	}
   //显示分享对话框
   public  void showShapeDialog()
   {   
	   ShareParam sp=new ShareParam();
	   ManagerParam mp=new ManagerParam();
	   mp.isShowDialog=false;
	   TongYongManager tym=new TongYongManager(act, icb,sp,mp);
	   tym.start();
   }
   private void show()
   {
	   LinearLayout l=new LinearLayout(act);
	   View view=act.getLayoutInflater().inflate(R.layout.dialog_shape,l,false);
	   ImageView iv=(ImageView) view.findViewById(R.id.iv0);
	   ImageUrl iu=new ImageUrl(iv,"http://zhuanlalicai.com/source_files/shareImg.png");
	   iu.start();
	   
	   final LinearLayout ll1=(LinearLayout) view.findViewById(R.id.ll1);
	   final LinearLayout ll2=(LinearLayout) view.findViewById(R.id.ll2);
	   String id=BeikBankApplication.getUserCode();
	   
	  // String shareM=ShareMUtil.toSerialCode(Long.parseLong(id));
	   String shareM=ShateUtil.countId(id);
	   final String shareMs="?shareCode="+shareM;
	   //
	   ll1.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
		
			ShateUtil su=new ShateUtil(act,ll1);
			su.shapeWeixin(share.linkUrl+shareMs,share.icon,share.title,share.content);
			//new NetDataManager(icb2,share.icon,act);
		}
	});
	   ll2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			
				ShateUtil su=new ShateUtil(act,ll2);
				//微信这里应该有问题和微信分享不一样
				su.shapeWeixinFriend(share.linkUrl+shareMs,share.icon,share.title,share.content);
				//new NetDataManager(icb2,share.icon,act);
			}
		});
	   Dialog dia=DialogManager.getDiaolg3(act,view);
	   dia.show();
   }
   /**
    * 得到分享参数
    */
   ICallBack icb=new ICallBack() {
	
	@Override
	public void back(Object obj) 
	{
		if(obj!=null)
		{
			Share_data sd=(Share_data)obj;
			share=sd.data;
			show();
		}
		
	}
   };
//   /**
//    * 得到分享图片后微信分享回调
//    */
//   ICallBack icb2=new ICallBack() 
//   {
//	
//	@Override
//	public void back(Object obj) 
//	{
//		
//		
//	}
//   };
//   /**
//    * 得到分享图片
//    */
//   ICallBack icb3=new ICallBack() {
//	
//	@Override
//	public void back(Object obj) 
//	{
//		
//		
//	}
//   };
}
