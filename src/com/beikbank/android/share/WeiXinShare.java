package com.beikbank.android.share;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.SendMessageToWX;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tencent.mm.sdk.openapi.WXMediaMessage;
import com.tencent.mm.sdk.openapi.WXWebpageObject;

public class WeiXinShare {
	Context context;
	private IWXAPI api;
    public WeiXinShare(Context context)
    {
    	api = WXAPIFactory.createWXAPI(context, null);
    	// 将该app注册到微信
    	api.registerApp(Constants.APP_ID);
    }
    /**
     * 
     * @param title 分享标题
     * @param text 分享内容
     * @param url 分享url
     * @param imgurl 分享图片url 
     * @param weixin false 分享到微信 true 分享到朋友圈
     * @return
     */
    public boolean share(String title,String text,String url,Bitmap bitmap,boolean weixin)
    {
    	boolean b=false;
    	WXWebpageObject webpage = new WXWebpageObject();
		webpage.webpageUrl =url;
		WXMediaMessage msg = new WXMediaMessage(webpage);
		msg.title =title;
		msg.description =text;
		Bitmap thumb =bitmap;
		thumb = Bitmap.createScaledBitmap(thumb,thumb.getWidth(),thumb.getHeight(), true);
		msg.thumbData = Util.bmpToByteArray(thumb, true);
		
		SendMessageToWX.Req req = new SendMessageToWX.Req();
		req.transaction = buildTransaction("webpage");
		req.message = msg;
		req.scene =weixin? SendMessageToWX.Req.WXSceneTimeline : SendMessageToWX.Req.WXSceneSession;
		b=api.sendReq(req);
    	
    	
    	return b;
    }
    private String buildTransaction(final String type) {
		return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
	}
}
