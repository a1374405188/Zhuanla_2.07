package com.beikbank.android.share;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.framework.Platform.ShareParams;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.system.text.ShortMessage;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.tencent.qzone.QZone;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;


import com.beikbank.android.activity.BaseActivity1;
import com.beikbank.android.activity.HomeActivity3;
import com.beikbank.android.activity.HuodongActivity2;
import com.beikbank.android.activity.LoginRegActivity;
import com.beikbank.android.animation.Params;
import com.beikbank.android.animation.ToastAnimationImpl;
import com.beikbank.android.animation.aParams;
import com.beikbank.android.conmon.SystemConfig;
import com.beikbank.android.data.Share;
import com.beikbank.android.data.Share_data;
import com.beikbank.android.exception.LogHandler;
import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.impl.TongYongManager;
import com.beikbank.android.utils.BeikBankConstant;
import com.beikbank.android.utils.DensityUtil;
import com.beikbank.android.utils.NetDataManager;

import comc.beikbank.android.R;

import android.animation.Animator.AnimatorListener;
import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

public class ShateUtil implements OnClickListener{
	BaseActivity1 act;
	private final String TAG="ShateUtil";
	private  String share_text="我刚把余额宝的钱存了赚啦理财，最高15%年化收益率！4倍哟！赶紧看看";//分享内容
	//private final String share_text2="我刚把余额宝的钱存了赚啦理财，最高15%年化收益率！4倍哟！赶紧看看";//分享内容
	public   String share_url="http://www.beikbank.com/beikbankapp/appDownLoad.jsp";
	//private  String share_url2="http://www.beikbank.com/beikbankapp/appDownLoad.jsp";
	/**
	 * 新手标分享
	 */
	public static  String share_url3=SystemConfig.kHOST_URL_BASE+"beikbankapp/register/html5/test/register.html";
	private  String share_title="赚啦理财！赚啦理财！";//分享标题
	private String share_icon;
	public final String share_title2="赚啦理财出新手标啦！";//分享标题
	View view1;
	public ShateUtil(Activity act,View view)
	{   
		
		
			this.act=(BaseActivity1) act;

	    view1=view;
		
		wxs=new WeiXinShare(act);
		ShareSDK.initSDK(act);
	}
   public void share(Activity act)
   {
	   this.act=(BaseActivity1) act;
	   wxs=new WeiXinShare(act);
	   initParam();
	  
   }
   public void share2(Activity act,String title,String content,String url,String icon)
   {
	   this.act=(BaseActivity1) act;
	   String shareMs;
	   String id=BeikBankApplication.getUserid();
	   if(id==null)
	   {
		   shareMs="";
	   }
	   else
	   {
	      String shareM=ShareMUtil.toSerialCode(Long.parseLong(id));
	      shareMs="?shareCode="+shareM;
	   }
	   wxs=new WeiXinShare(act);
	    share_title=title;
		share_text=content;
		share_url=url+shareMs;
		share_icon=icon;
		showPopwindow();
   }
   /**
    * 初始化分享参数
    */
   private void initParam()
   {
	   TongYongManager tym=new TongYongManager(act, icb,new Share());
	   tym.start();
	   
   }
   ICallBack icb=new ICallBack() {
	
	@Override
	public void back(Object obj) {
		
		if(obj!=null)
		{   
			 String id=BeikBankApplication.getUserid();
			   
			   String shareM=ShareMUtil.toSerialCode(Long.parseLong(id));
			   final String shareMs="?shareCode="+shareM;
			Share_data sd=(Share_data) obj;
			Share s=sd.data;
			share_title=s.title;
			share_text=s.content;
			share_url=s.linkUrl+shareMs;
			share_icon=s.icon;
			showPopwindow();
		}
	}
};
   private void moveAniation()
   {   
	   LayoutInflater inflater = (LayoutInflater) act.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	   View view = inflater.inflate(R.layout.popwindowlayout, null);
	   Params p=new Params();
	   AnimationListener al=new AnimationListener() {
		
		@Override
		public void onAnimationStart(Animation animation) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onAnimationRepeat(Animation animation) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onAnimationEnd(Animation animation) {
			tv.setBackgroundColor(0xd0ffffff);
			
		}
	};
	
   }
   private Handler handler = new Handler();
   private Handler handler2 = new Handler();
   private Runnable task = new Runnable() {  
       @SuppressLint("NewApi") public void run() {   
    	   DensityUtil du=new DensityUtil(act);
          int h=du.dip2px(300);
          ToastAnimationImpl ti=new ToastAnimationImpl();
          Params p=new Params();
          p.toy=-h;
          AnimationListener al=new AnimationListener() {
      		
      		@Override
      		public void onAnimationStart(Animation animation) {
      			// TODO Auto-generated method stub
      			
      		}
      		
      		@Override
      		public void onAnimationRepeat(Animation animation) {
      			// TODO Auto-generated method stub
      			
      		}
      		
      		@Override
      		public void onAnimationEnd(Animation animation) {
      			//tv.setBackgroundColor(0xa0000000);
      			b=false;
      			
      			
      		}
          };
       //  ti.MoveAnimation1(ll2, p,al);
         
         
          AnimatorListener al1=new AnimatorListener() {
			
			@Override
			public void onAnimationStart(Animator animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationRepeat(Animator animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationEnd(Animator animation) {
				b=false;
				
			}
			
			@Override
			public void onAnimationCancel(Animator animation) {
				// TODO Auto-generated method stub
				
			}
		};
         //ObjectAnimator.ofFloat(ll2,"translationY",0f,-h).setDuration(1000).start();
         ObjectAnimator oa=ObjectAnimator.ofFloat(ll2,"translationY",0f,-h);
         oa.addListener(al1);
         oa.setDuration(200).start();
         //ObjectAnimator.ofInt(tv,"backgroundColor",0x00000000,0x50000000).setDuration(1000).start();
         colorAnima(tv);
         
       }   
   };
   Runnable task2;
   int start=0x00000000;
   int a=0x08000000;
   boolean b=true;
   private void colorAnima(final View view)
   {   
	   start=0x00000000;
	   b=true;
	   task2=new Runnable() {
			
			@Override
			public void run() 
			{   
				
				view.setBackgroundColor(start);
				start+=a;
				
				if(b)
				{
				  handler2.postDelayed(task2,10);
				}
			}
		};
		handler2.post(task2);
   }
   private void move()
   {
	   
   }
   View view;
   LinearLayout ll1;
   LinearLayout ll2;
   TextView tv;
   /**
    * 显示popupWindow
    */
   private void showPopwindow() {
     // 利用layoutInflater获得View
     LayoutInflater inflater = (LayoutInflater) act.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
     view = inflater.inflate(R.layout.popwindowlayout, null);
    
     // 下面是两种方法得到宽度和高度 getWindow().getDecorView().getWidth()

      act.window = new PopupWindow(view,
         WindowManager.LayoutParams.MATCH_PARENT,
         WindowManager.LayoutParams.MATCH_PARENT);

     // 设置popWindow弹出窗体可点击，这句话必须添加，并且是true
      act.window.setFocusable(true);
     
    // window.setAnimationStyle(R.style.mypopwindow_anim_style);
     ll1=(LinearLayout) view.findViewById(R.id.ll1);
     ll2=(LinearLayout) view.findViewById(R.id.ll2);
   tv=(TextView) view.findViewById(R.id.tv);
     tv.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			if(act.window.isShowing())
			{
				//act.window.dismiss();
			}
			
		}
	});
     
     Button bu=(Button)view.findViewById(R.id.bu);
     bu.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			if(act.window.isShowing())
			{
				act.window.dismiss();
			}
			
		}
	});
     setOn(view);
//     // 实例化一个ColorDrawable颜色为半透明
//     ColorDrawable dw = new ColorDrawable(0xb0000000);
//     window.setBackgroundDrawable(dw);

     
     // 设置popWindow的显示和消失动画
    // window.setAnimationStyle(R.style.mypopwindow_anim_style);
     // 在底部显示
     act.window.showAtLocation(view1,
         Gravity.BOTTOM, 0, 0);
     handler.postDelayed(task,300);
    
     
   }
   private ImageView imageview_weixinmoment,imageview_weixinfriend,
	imageview_weibo,imageview_qq,imageview_qqzone,imageview_message;
   private void setOn(View view)
   {
	    imageview_weixinmoment=(ImageView)view.findViewById(R.id.imageview_weixinmoment);
		imageview_weixinfriend=(ImageView)view.findViewById(R.id.imageview_weixinfriend);
		imageview_weibo=(ImageView)view.findViewById(R.id.imageview_weibo);
		imageview_qq=(ImageView)view.findViewById(R.id.imageview_qq);
		imageview_qqzone=(ImageView)view.findViewById(R.id.imageview_qqzone);
		imageview_message=(ImageView)view.findViewById(R.id.imageview_message);
		
		DensityUtil du=new DensityUtil(act);
		int width=du.getScreenWidth()/10;
		
		LayoutParams lp=new LayoutParams(width,width);
		lp.topMargin=10;
		lp.bottomMargin=10;
		imageview_weixinmoment.setLayoutParams(lp);
		imageview_weixinfriend.setLayoutParams(lp);
		imageview_weibo.setLayoutParams(lp);
		imageview_qq.setLayoutParams(lp);
		imageview_qqzone.setLayoutParams(lp);
		imageview_message.setLayoutParams(lp);
		
		
		
		imageview_weixinmoment.setOnClickListener(this);
		imageview_weixinfriend.setOnClickListener(this);
		imageview_weibo.setOnClickListener(this);
		imageview_qq.setOnClickListener(this);
		imageview_qqzone.setOnClickListener(this);
		imageview_message.setOnClickListener(this);
   }
	WeiXinShare wxs=null;
	//分享是否成功
    boolean b2;
    Bitmap bitmap=null;
   @Override
	public void onClick(View v) {
			   
		
		
		
		switch(v.getId()){
		case R.id.imageview_weibo://新浪微博
			ShareParams sp_weibo = new ShareParams();
			sp_weibo.setText(share_text+share_url);
           
			Platform weibo = ShareSDK.getPlatform(act,SinaWeibo.NAME);
			//weibo.removeAccount();
		    weibo.SSOSetting(false);
			weibo.setPlatformActionListener(pa); // 设置分享事件回调
			weibo.share(sp_weibo);
			
			break;
		case R.id.imageview_qq://qq
//			ShareParams sp_qq = new ShareParams();
//			sp_qq.setTitle(share_title);
//			sp_qq.setTitleUrl(share_url);
//			sp_qq.setText(share_text);
//			//sp_qq.imagePath=share_icon;
//			sp_qq.setImageUrl(share_icon);
//			sp_qq.setShareType(Platform.SHARE_WEBPAGE);
//			Platform qq = ShareSDK.getPlatform (QQ.NAME);
//			
//			qq.setPlatformActionListener (pa);
//			qq.share(sp_qq);
			
			MQQShare qqs=new MQQShare(act);
			qqs.shareToQQ(share_title,share_text,share_url,share_icon);
			break;
		case R.id.imageview_qqzone://QQ空间
//			ShareParams sp_qqzone = new ShareParams();
//			sp_qqzone.setTitle(share_title);
//			sp_qqzone.setTitleUrl(share_url);
//			sp_qqzone.setImageUrl(share_icon);
//			sp_qqzone.setText(share_text);
//			sp_qqzone.setShareType(Platform.SHARE_WEBPAGE);
//			sp_qqzone.setSite("");
//			sp_qqzone.setSiteUrl(share_url);
//
//			Platform qzone = ShareSDK.getPlatform (QZone.NAME);
//			qzone. setPlatformActionListener (pa); // 设置分享事件回调
//			// 执行图文分享
//			qzone.share(sp_qqzone);
			MQQShare qqs2=new MQQShare(act);
			qqs2.shareToQzone(share_title,share_text,share_url,share_icon);
			break;
		case R.id.imageview_weixinmoment://微信朋友圈
//			Platform plat_weixin = ShareSDK.getPlatform(act, WechatMoments.NAME);
//			WechatMoments.ShareParams sp_weixin = new WechatMoments.ShareParams();
//			sp_weixin.text = share_text;
//			sp_weixin.title = share_title;
//			sp_weixin.url = share_url;
//			sp_weixin.shareType = Platform.SHARE_WEBPAGE;
//			Resources res=act.getResources();			   
//			Bitmap bitmap=BitmapFactory.decodeResource(res, R.drawable.app_logo);
//			sp_weixin.setImageData(bitmap);
//			plat_weixin.setPlatformActionListener(pa);
//			plat_weixin.share(sp_weixin);
			//shapeWeixinFriend(share_url,share_title,share_text);
//			if(bitmap!=null)
//			{
//			  b2=wxs.share(share_text,share_title,share_url, bitmap,true);
//			  if(!b)
//			 {
//			  	 Toast.makeText(act,"请安装微信客户端",Toast.LENGTH_SHORT).show();
//			 }
//			}
//			else
//			{
//				new NetDataManager(icb2,share_icon,act);
//			}
			shapeWeixinFriend(share_url,share_icon,share_title,share_text);
			
			break;
		case R.id.imageview_weixinfriend://微信好友
//			Platform plat_weixinfriend = ShareSDK.getPlatform(act, Wechat.NAME);
//
//			Wechat.ShareParams sp_weixinfriend = new Wechat.ShareParams();
//			sp_weixinfriend.title = share_title;
//			sp_weixinfriend.text = share_text;
//			sp_weixinfriend.url = share_url;
//			sp_weixinfriend.shareType = Platform.SHARE_WEBPAGE;
//			Resources res2=act.getResources();			   
//			Bitmap bitmap2=BitmapFactory.decodeResource(res2, R.drawable.app_logo);
//			sp_weixinfriend.setImageData(bitmap2);
//			plat_weixinfriend.setPlatformActionListener(pa);
//			plat_weixinfriend.share(sp_weixinfriend);
			//shapeWeixin(share_url,share_title,share_text);
//			if(bitmap!=null)
//			{
//			b2=wxs.share(share_text,share_title,share_url, bitmap,false);
//			if(!b)
//			{
//				 Toast.makeText(act,"请安装微信客户端",Toast.LENGTH_SHORT).show();
//			}
//			}
//			else
//			{
//				new NetDataManager(icb3,share_icon,act);
//				
//			}
			shapeWeixin(share_url,share_icon,share_title,share_text);
			break;
		case R.id.imageview_message://短信
			ShareParams sp_message = new ShareParams();
			sp_message.setTitle(share_title);
			sp_message.setText(share_title+share_text+share_url);
			Platform message = ShareSDK.getPlatform(ShortMessage.NAME);
			message.setPlatformActionListener(pa); 
			message.share(sp_message);
			break;
		}
		}
   ICallBack icb2=new ICallBack() {
	
	@Override
	public void back(Object obj) {
		if(obj!=null)
		{  
			boolean b;
			bitmap=(Bitmap) obj;
			b=wxs.share(share_text,share_title,share_url, bitmap,true);
			if(!b)
			{
				 Toast.makeText(act,"请安装微信客户端",Toast.LENGTH_SHORT).show();
			}
		}
		else
		{
			boolean b;
			if(bitmap==null)
			{
			Resources res=act.getResources();			   
			bitmap=BitmapFactory.decodeResource(res, R.drawable.app_logo);
			}
			b=wxs.share(share_text,share_title,share_url, bitmap,true);
			bitmap=null;
			if(!b)
			{
				 Toast.makeText(act,"请安装微信客户端",Toast.LENGTH_SHORT).show();
			}
		}
		
	}
};
ICallBack icb3=new ICallBack() {
	
	@Override
	public void back(Object obj) {
		if(obj!=null)
		{  
			boolean b;
			bitmap=(Bitmap) obj;
			b=wxs.share(share_text,share_title,share_url, bitmap,false);
			if(!b)
			{
				 Toast.makeText(act,"请安装微信客户端",Toast.LENGTH_SHORT).show();
			}
		}
		else
		{
			boolean b;
			if(bitmap==null)
			{
			Resources res=act.getResources();			   
			bitmap=BitmapFactory.decodeResource(res, R.drawable.app_logo);
			}
			b=wxs.share(share_text,share_title,share_url, bitmap,false);
			bitmap=null;
			if(!b)
			{
				 Toast.makeText(act,"请安装微信客户端",Toast.LENGTH_SHORT).show();
			}
		}
		
	}
};
/**
 * 分享到qq空间
 * @param share_url
 * @param img_url
 * @param title
 * @param text
 */
public void shareZone(final String share_url,String img_url,final String title,final String text)
{
	ShareParams sp_qqzone = new ShareParams();
	sp_qqzone.setTitle(title);
	sp_qqzone.setTitleUrl(share_url);
	sp_qqzone.setImageUrl(img_url);
	sp_qqzone.setText(text);
	sp_qqzone.setShareType(Platform.SHARE_WEBPAGE);
	sp_qqzone.setSite("");
	sp_qqzone.setSiteUrl(share_url);

	Platform qzone = ShareSDK.getPlatform (QZone.NAME);
	qzone. setPlatformActionListener (pa); // 设置分享事件回调
	// 执行图文分享
	qzone.share(sp_qqzone);
}

  public void shareQQ(final String share_url,String img_url,final String title,final String text)
  {
	    ShareParams sp_qq = new ShareParams();
		sp_qq.setTitle(title);
		sp_qq.setTitleUrl(share_url);
		sp_qq.setText(text);
		//sp_qq.imagePath=share_icon;
		sp_qq.setImageUrl(img_url);
		sp_qq.setShareType(Platform.SHARE_WEBPAGE);
		Platform qq = ShareSDK.getPlatform (QQ.NAME);
		
		qq.setPlatformActionListener (pa);
		qq.share(sp_qq);
  }
  public void shapeWeixin(final String share_url,String img_url,final String title,final String text)
  {     
//	    Platform plat_weixinfriend = ShareSDK.getPlatform(act, Wechat.NAME);
//
//		Wechat.ShareParams sp_weixinfriend = new Wechat.ShareParams();
//		sp_weixinfriend.title = title;
//		sp_weixinfriend.text = text;
//		sp_weixinfriend.url = share_url;
//		sp_weixinfriend.shareType = Platform.SHARE_WEBPAGE;
//		Resources res2=act.getResources();			   
//		//Bitmap bitmap2=BitmapFactory.decodeResource(res2, R.drawable.app_logo);
//		//sp_weixinfriend.setImageData(bitmap2);
//		sp_weixinfriend.imageUrl=share_icon;
//		plat_weixinfriend.setPlatformActionListener(pa);
//		plat_weixinfriend.share(sp_weixinfriend);
	    ICallBack icb4=new ICallBack() {
			
			@Override
			public void back(Object obj) {
				
				if(obj!=null)
				{  
					boolean b;
					bitmap=(Bitmap) obj;
					b=wxs.share(title,text,share_url, bitmap,false);
					if(!b)
					{
						 Toast.makeText(act,"请安装微信客户端",Toast.LENGTH_SHORT).show();
					}
				}
			}
		};
	    new NetDataManager(icb4,img_url,act);
  }
  public void shapeWeixinFriend(final String share_url,String img_url,final String title,final String text)
  {
//	    Platform plat_weixin = ShareSDK.getPlatform(act, WechatMoments.NAME);
//		WechatMoments.ShareParams sp_weixin = new WechatMoments.ShareParams();
//		sp_weixin.text = text;
//		sp_weixin.title = title;
//		sp_weixin.url = share_url;
//		sp_weixin.shareType = Platform.SHARE_WEBPAGE;
//		Resources res=act.getResources();			   
//		//Bitmap bitmap=BitmapFactory.decodeResource(res, R.drawable.app_logo);
//		//sp_weixin.setImageData(bitmap);
//		sp_weixin.imageUrl=share_icon;
//		plat_weixin.setPlatformActionListener(pa);
//		plat_weixin.share(sp_weixin);
	  ICallBack icb4=new ICallBack() {
			
			@Override
			public void back(Object obj) {
				
				if(obj!=null)
				{  
					boolean b;
					bitmap=(Bitmap) obj;
					b=wxs.share(text,title,share_url, bitmap,true);
					if(!b)
					{
						 Toast.makeText(act,"请安装微信客户端",Toast.LENGTH_SHORT).show();
					}
				}
			}
		};
	    new NetDataManager(icb4,img_url,act);
  }
final static int success=1;
final static int faild=2;
final static int cacle=3;
Handler shareHand=new Handler()
{

	@Override
	public void handleMessage(Message msg) 
	{
		super.handleMessage(msg);
		switch (msg.what) 
		{
		  case success:
			 // Toast.makeText(act, "分享成功",Toast.LENGTH_SHORT).show();
			break;

		  case faild:
			  String s="没有安装客户端";
			  Toast.makeText(act,s,Toast.LENGTH_SHORT).show();
			break;
			
		  case cacle:
			  Toast.makeText(act, "分享取消",Toast.LENGTH_SHORT).show();
			break;		
		}
		
	}
	
};
PlatformActionListener pa=new PlatformActionListener() {
	
	@Override
	public void onError(Platform arg0, int arg1, Throwable arg2) {
		Message msg=new Message();
		String s=arg2.getMessage();
		msg.obj=s;
		msg.what=faild;
		shareHand.sendMessage(msg);
		Log.e("share_log",arg2.getMessage());
		LogHandler.writeLogFromException(TAG, new Exception(arg2));
	}
	
	@Override
	public void onComplete(Platform arg0, int arg1, HashMap<String, Object> arg2) {
		Message msg=new Message();
		msg.what=success;
		shareHand.sendMessage(msg);
		
	}
	
	@Override
	public void onCancel(Platform arg0, int arg1) {
		Message msg=new Message();
		msg.what=cacle;
		shareHand.sendMessage(msg);
		
	}
};
}
