package com.beikbank.android.activity;

import com.beikbank.android.conmon.SystemConfig;
import com.beikbank.android.utils2.StateBarColor;
import com.beikbank.android.webwiew.WebWiewInface;
import com.beikbank.android.webwiew.Wv;
import coma.beikbank.android.R;




import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
/**
 * 
 * @author Administrator
 *活动标识网页
 */
public class HuodongActivity2 extends BaseActivity1 implements View.OnClickListener
{
	TextView title;
    /**
     * return
     */
    LinearLayout ll1;
    ProgressBar pb;
    WebView wv;
    //String url=SystemConfig.kHOST_URL_BASE+"beikbankapp/activity/newActivity.html";
	/*刷新
	 * 
	 */
    TextView tv1;
    String url=null;
    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_huodong);
		StateBarColor.init(this,0xffffffff);
		initView();
        String url=getIntent().getStringExtra("url");
        String title=getIntent().getStringExtra("title");
        if(url!=null&&!url.equals(""))
        {   
        	if(title!=null&&!title.equals(""))
            {
            	this.title.setText(title);
            }
        	this.url=url;
        }
        //init();
        //this.url=SystemConfig.kHOST_URL_BASE+"beikbankapp/activity/newActivity.html";
       
        //this.url="http://www.beikbank.com/beikbankapp/activity/jiaxi201707/addInterest.html";
        //this.url="http://172.16.100.37:8888/build/#!/productDetails/25541";
        new WebWiewInface().setInface(wv,this);
        wv.loadUrl(this.url);
	}
    
    
//    private void init()
//    {
// 	  Wv wv1=new Wv(null,null);
// 	  
// 	   wv1.close();
// 	   wv1.back();
// 	   wv1.set_back("0");
// 	   wv1.set_title("");
// 	   wv1.get_version();
// 	   wv1.getid();
//    }

	  @SuppressLint("SetJavaScriptEnabled") 
	  private void initView()
	    {
	        ll1=(LinearLayout)findViewById(R.id.linear_left);
	        ll1.setOnClickListener(this);
	        title=(TextView)findViewById(R.id.titleTv);
	        title.setText("活动图标");
	        pb=(ProgressBar) findViewById(R.id.pb);
	        tv1=(TextView) findViewById(R.id.right);
			tv1.setVisibility(View.VISIBLE);
			tv1.setText("刷新"); 
	        tv1.setOnClickListener(this);
	        String s1=getIntent().getStringExtra("right");
	        if("0".equals(s1))
	        {
	        	tv1.setVisibility(View.GONE);
	        }
	        
	        wv=(WebView) findViewById(R.id.wv);
	        wv.setWebChromeClient(new WebChromeClient(){

				@Override
				public void onProgressChanged(WebView view, int newProgress) {
					if(newProgress==100)
					{
						pb.setVisibility(View.GONE);
					}
					else
					{   pb.setVisibility(View.VISIBLE);
						pb.setProgress(newProgress);
					}
					super.onProgressChanged(view, newProgress);
				}});
	        
	        
	        
	        
	    }

	   public void onClick(View view)
	   {
	      switch (view.getId())
	      {
	         case R.id.linear_left:
	             if(wv.canGoBack())
	             {
	            	 wv.goBack();
	             }
	             else
	             {
	            	 finish();
	             }
	          break;
	         case R.id.right:
	             wv.reload();
	          break;
	      }
	      
	   }
	   
	   
	
}
