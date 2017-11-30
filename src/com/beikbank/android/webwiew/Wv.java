package com.beikbank.android.webwiew;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.beikbank.android.activity.HomeActivity3;
import com.beikbank.android.conmon.SystemConfig;
import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.net.HttpManager;
import com.beikbank.android.net.HttpUtil;
import com.beikbank.android.share.MQQShare;
import com.beikbank.android.share.ShareMUtil;
import com.beikbank.android.share.ShareParam;
import com.beikbank.android.share.ShateUtil;
import com.beikbank.android.utils.Utils;
import coma.beikbank.android.R;



public class Wv {
	Handler handler=new Handler()
	   {

		@Override
		public void handleMessage(Message msg) {
			if(msg.what==1)
			{
				 TextView tv=(TextView)act.findViewById(R.id.titleTv);
				 tv.setText((CharSequence) msg.obj);
			}
			//微信
			else if(msg.what==2)
			{
				ShateUtil su=new ShateUtil(HomeActivity3.ha,right);
				ShareParam sp=(ShareParam) msg.obj;
				su.shapeWeixin(sp.url,sp.img,sp.title,sp.text);
			}
			//朋友圈
			else if(msg.what==3)
			{
				ShateUtil su=new ShateUtil(HomeActivity3.ha,right);
				ShareParam sp=(ShareParam) msg.obj;
				su.shapeWeixinFriend(sp.url,sp.img,sp.title,sp.text);
			}
			//qq
			else if(msg.what==4)
			{
//				ShateUtil su=new ShateUtil(HomeActivity3.ha);
				ShareParam sp=(ShareParam) msg.obj;
//				su.shareQQ(sp.url,sp.img,sp.title,sp.text);
				
				
				MQQShare qqs=new MQQShare(HomeActivity3.ha);
				
				qqs.shareToQQ(sp.title,sp.text,sp.url,sp.img);
			}
			//qq空间
			else if(msg.what==5)
			{
				//ShateUtil su=new ShateUtil(HomeActivity3.ha);
				ShareParam sp=(ShareParam) msg.obj;
				//su.shareZone(sp.url,sp.img,sp.title,sp.text);
                MQQShare qqs=new MQQShare(HomeActivity3.ha);
				
				qqs.shareToQzone(sp.title,sp.text,sp.url,sp.img);
			}
			//网络请求回调
			else if(msg.what==6)
			{
					wv.loadUrl((String)msg.obj);
	
			}
			
			else if(msg.what==7)
			{
				   
				   right.setText("分享");
				   right.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						ShateUtil su=new ShateUtil(act,right);
						su.share2(act,sp2.title, sp2.text,sp2.url,sp2.img);
						
					}
				});
			}
			else if(msg.what==8)
			{
				   
				   right.setText("刷新");
				   right.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							wv.reload();
						}
					});
			}
			
			else if(msg.what==9)
			{
				ShateUtil su=new ShateUtil(act,right);
				su.share2(act,sp.title, sp.text,sp.url,sp.img);
			}
		}
		   
	   };
	   /**
	    * 活动页面的分享或者刷新按钮
	    */
	private TextView right;
    Activity act;
    WebView wv;
    public Wv(WebView wv,Activity act)
    {
    	this.wv=wv;
    	this.act=act;
    	right=(TextView) act.findViewById(R.id.right);
    }
	@JavascriptInterface
	public void close()
	{  
		if(act!=null)
		{
			act.finish();
		}
		
	}
	@JavascriptInterface
	public void back()
	{   
		if(act!=null)
		{
			wv.goBack();
		}
		
	}
	/**
	 * index=1 网页返回上一级 否则的话关闭当前页
	 * @param index
	 */
	@JavascriptInterface
	public void set_back(String index)
	{   
		if(wv==null||act==null)
		{
			return;
		}
		LinearLayout ll1=(LinearLayout)act.findViewById(R.id.linear_left);
		if(index.equals("1"))
		{
			ll1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			   
				wv.goBack();
			}
		});
		}
		else
		{
       ll1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			   
				act.finish();
			}
		});
		}
	}
@JavascriptInterface
	public void set_title(String title)
	{    
	if(wv==null||act==null)
		{
			return;
		}
	 Message msg=new Message();
	 msg.what=1;
	 msg.obj=title;
	 handler.sendMessage(msg);
	 
	}
/**
 * 得到系统版标示号
 * @return
 */
@JavascriptInterface
	public String get_version()
	{
		return "1";
	}
@JavascriptInterface
	public String getid()
	{
		return BeikBankApplication.getUserid();
	}
@JavascriptInterface
public String getid2()
{   
	String id=BeikBankApplication.getUserid();
	if(id==null||id.equals(""))
	{
		return "";
	}
	else
	{
		return ShareMUtil.toSerialCode(Long.parseLong(id));
	}
	
}
  @JavascriptInterface
  public void share_weixin(String title,String text,String url,String icon)
 {   
	  Message msg=new Message();
	  msg.what=2;
	  ShareParam sp=new ShareParam();
	  sp.img=icon;
	  sp.text=text;
	  sp.title=title;
	  sp.url=url;
	  msg.obj=sp;
	  handler.sendMessage(msg);
 }
  @JavascriptInterface
  public void share_friends(String title,String text,String url,String icon)
 {   
	  Message msg=new Message();
	  msg.what=3;
	  ShareParam sp=new ShareParam();
	  sp.img=icon;
	  sp.text=text;
	  sp.title=title;
	  sp.url=url;
	  msg.obj=sp;
	  handler.sendMessage(msg);
 }
  @JavascriptInterface
  public void share_qq(String title,String text,String url,String icon)
 {   
	  Message msg=new Message();
	  msg.what=4;
	  ShareParam sp=new ShareParam();
	  sp.img=icon;
	  sp.text=text;
	  sp.title=title;
	  sp.url=url;
	  msg.obj=sp;
	  handler.sendMessage(msg);
 }
  @JavascriptInterface
  public void share_qq_zone(String title,String text,String url,String icon)
 {   
	  Message msg=new Message();
	  msg.what=5;
	  ShareParam sp=new ShareParam();
	  sp.img=icon;
	  sp.text=text;
	  sp.title=title;
	  sp.url=url;
	  msg.obj=sp;
	  handler.sendMessage(msg);
 }
  /**
   * 
   * @param url
   * @param params
   */
  @JavascriptInterface
  public void send_get(String url,String params,String name)
 {   
	HttpManager hm=new HttpManager(url, params, name, wv,1);
	hm.start();
 }
  /**
   * 
   * @param url
   * @param params
   */
  @JavascriptInterface
  public void send_post(String url,String params,String name)
 {   
	 HttpManager hm=new HttpManager(url, params, name, wv,2);
	 hm.start();
 }
  
  /**
   * 调出本地分享方法
   * @param title
   * @param text
   * @param url
   * @param icon
   */
  @JavascriptInterface
  public void shareWithShareData(String json)
  {    
	  Log.d("share", json);
	  JSONObject jobj;
	     sp=new ShareParam();
	     
		   try {
			jobj=new JSONObject(json.toString());
			sp.text=jobj.getString("content");
			sp.img=jobj.getString("icon");
			sp.title=jobj.getString("title");
			sp.url=jobj.getString("linkUrl");
		
		  } catch (JSONException e) 
		  {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 }
		 
		   
			      Message msg=new Message();
				  msg.what=9;
				  handler.sendMessage(msg);
		
	  
	  
  }
  
  
  ShareParam sp;
  ShareParam sp2;
  /**
   * 设置分享内容
   * @param json
   */
  @JavascriptInterface
  public void share(String json)
 {    
	 Log.d("share", json);
	 if(right==null)
	 {
		 return;
	 }
     JSONObject jobj;
     sp2=new ShareParam();
     String isShare=null;
	   try {
		jobj=new JSONObject(json.toString());
		sp2.text=jobj.getString("content");
		sp2.img=jobj.getString("icon");
		sp2.title=jobj.getString("title");
		sp2.url=jobj.getString("linkUrl");
		isShare=jobj.getString("code");
	  } catch (JSONException e) 
	  {
		// TODO Auto-generated catch block
		e.printStackTrace();
	 }
	 
	   if("1".equals(isShare))
	   {   
		      Message msg=new Message();
			  msg.what=7;
			  handler.sendMessage(msg);
	   }
	   else
	   {
		      Message msg=new Message();
			  msg.what=8;
			  handler.sendMessage(msg);
	   }
	   
 }
  
  
  /**
   * 
   * @param 
   * @param 
   */
  @JavascriptInterface
  public void goback()
 {   
	 if(wv.canGoBack())
	 {
		 wv.goBack();
	 }
	 else
	 {
		 act.finish();
	 }
 }
  /**
   * 
   * @param index 0活期1定期2新手标
   */
  @JavascriptInterface
  public void go(String index)
 {   
	 Intent intent=new Intent(act,HomeActivity3.class);
	 intent.putExtra("go","go");
	 if("0".equals(index))
	 {
		 intent.putExtra("indexgo",0);
	 }
	 else if("1".equals(index))
	 {
		 intent.putExtra("indexgo",1);
	 }
	 else if("2".equals(index))
	 {
		 intent.putExtra("indexgo",2);
	 }
	 
	 act.startActivity(intent);
 }
}
