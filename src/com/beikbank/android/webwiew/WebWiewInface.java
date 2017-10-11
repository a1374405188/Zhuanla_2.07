package com.beikbank.android.webwiew;
import com.beikbank.android.fragment.BeikBankApplication;
import com.umeng.message.proguard.ac;

import comc.beikbank.android.R;

import android.app.Activity;
import android.net.http.SslError;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebSettings.RenderPriority;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;
/**
 * 
 * @author Administrator
 *
 */
public class WebWiewInface
{   
   
   Activity act;
   public  void  setInface(WebView wv,Activity act)
   {   
	   
	   wv.setWebViewClient(new WebClent());
	   wv.addJavascriptInterface(new Wv(wv,act),"wv");
	   wv.getSettings().setJavaScriptEnabled(true);
       wv.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
       wv.getSettings().setAllowFileAccess(true);
       wv.getSettings().setAllowFileAccess(true);
       wv.getSettings().setAppCacheEnabled(true);
       wv.getSettings().setDomStorageEnabled(true);
       wv.getSettings().setDatabaseEnabled(true);
     
       this.act=act;
       
       
       
       
       wv.setWebViewClient(new WebViewClient(){

			@Override
			public void onReceivedSslError(WebView view,
					SslErrorHandler handler, SslError error) {
				handler.proceed(); 
				//super.onReceivedSslError(view, handler, error);
			}
       	
       	
       	
       	
       });
   }
   
   private void init()
   {
	   Wv wv1=new Wv(null,null);
	  
	   wv1.close();
	   wv1.back();
	   wv1.set_back("0");
	   wv1.set_title("");
	   wv1.get_version();
	   wv1.getid();
   }
   class WebClent extends WebViewClient
   {
   
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			view.loadUrl(url);
			return true;
		}
   	
   }
  
   
}
