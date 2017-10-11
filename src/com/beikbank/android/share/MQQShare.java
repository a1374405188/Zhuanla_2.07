package com.beikbank.android.share;

import java.util.ArrayList;

import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.tencent.connect.share.QQShare;
import com.tencent.connect.share.QzoneShare;
import com.tencent.mm.sdk.openapi.ConstantsAPI;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
/**
 * 
 * @author Administrator
 *qq分享工具
 */
public class MQQShare {
   public Tencent ten;
   Activity act;
   private static String appid="1103529245";
   public MQQShare(Activity act)
   {
	   this.act=act;
	   ten=Tencent.createInstance(appid,act.getApplicationContext());
	   
   }
   public void shareToQQ(String title,String text,String url,String img)
   {
	   final Bundle params = new Bundle();
	    params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
	    params.putString(QQShare.SHARE_TO_QQ_TITLE,title);
	    params.putString(QQShare.SHARE_TO_QQ_SUMMARY,text);
	    params.putString(QQShare.SHARE_TO_QQ_TARGET_URL,url);
	    params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL,img);
	    params.putString(QQShare.SHARE_TO_QQ_APP_NAME,  "赚啦理财");
	    //params.putInt(QQShare.SHARE_TO_QQ_EXT_INT,  "其他附加功能");		
	    ten.shareToQQ(act, params, new BaseUiListener());
   }
   public void shareToQzone (String title,String text,String url,String img) {
	 //分享类型
	   final Bundle params = new Bundle();
	     params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
	     params.putString(QQShare.SHARE_TO_QQ_TITLE,title);
		    params.putString(QQShare.SHARE_TO_QQ_SUMMARY,text);
		    params.putString(QQShare.SHARE_TO_QQ_TARGET_URL,url);
		    
		    ArrayList<String> list=new ArrayList<String>();
		    list.add(img);
		    params.putStringArrayList(QzoneShare.SHARE_TO_QQ_IMAGE_URL,list);
		    params.putString(QQShare.SHARE_TO_QQ_APP_NAME,  "赚啦理财");
	     ten.shareToQzone(act, params, new BaseUiListener());
	 }
    class BaseUiListener implements IUiListener {
	   
      
	   protected void doComplete(JSONObject values) 
	   {
	   }
	   @Override
	   public void onError(UiError e) {
	      Toast toast=Toast.makeText(act, "qq分享错误",Toast.LENGTH_SHORT);
	   }
	   @Override
	   public void onCancel() {
	  
	   }
	    @Override
	    public void onComplete(Object arg0) {
		// TODO Auto-generated method stub
		
	    }
	  }
}
