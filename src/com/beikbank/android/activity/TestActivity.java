package com.beikbank.android.activity;

import com.beikbank.android.activity.help.LiuChenManager;
import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.media.ImageTools;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.pullrefresh.PullToRefreshBase;
import com.beikbank.android.pullrefresh.PullToRefreshScrollView;
import com.beikbank.android.utils.DialogManager;
import coma.beikbank.android.R;



import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * @author yuguohe
 * <p>
 * email:1374405188@qq.com
 * </p>
 *2015-3-10
 * 
 */
public class TestActivity  extends Activity{

    Activity act=this;
	@Override
	protected void onCreate(Bundle savedInstanceState)
    {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Bitmap bitmap=BitmapFactory.decodeResource(getResources(), R.drawable.bg_bank_gongshang);
	   // bitmap=ImageTools.toGrayscale(bitmap);
		ImageView iv=new ImageView(this);
		
		iv.setImageBitmap(bitmap);
		setContentView(R.layout.activity_test);
		LinearLayout ll=(LinearLayout) findViewById(R.id.ll);
		ll.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				LiuChenManager.selectPay(icb,TestActivity.this,false);
				
			}
		});
        //init();
        getDesty();
	}
	ICallBack icb=new ICallBack() {
		
		@Override
		public void back(Object obj) {
			
			
		}
	};
	private void getDesty()
	{   
		int s=BeikBankApplication.getStaticHeight(act);
		DisplayMetrics dm=getResources().getDisplayMetrics();
	    float f=getResources().getDisplayMetrics().density;
	    System.out.println(f);
	    int i= getResources().getDisplayMetrics().densityDpi;
	   
	}
    PullToRefreshScrollView prs;
    ScrollView sv;
    private void init()
    {
        LayoutInflater li=act.getLayoutInflater();
        LinearLayout ll=new LinearLayout(act);
        View view = li.inflate(R.layout.page_wealth11,ll,false);
        View view2= li.inflate(R.layout.page_wealth3,ll,false);
        prs=(PullToRefreshScrollView) view.findViewById(R.id.prs);
        prs.setPullLoadEnabled(false);
        prs.setScrollLoadEnabled(false);
        prs.getRefreshableView();
        sv=prs.getRefreshableView();
        sv.addView(view2);
        setContentView(view);


        prs.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                prs.onPullDownRefreshComplete();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {

            }
        });
    }
}
