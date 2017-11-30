package com.beikbank.android.activity;

import java.io.InputStream;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.beikbank.android.gif.CustomGifView;
import com.beikbank.android.gif.GifView;
import com.beikbank.android.gif.GifView.GifImageType;
import com.beikbank.android.utils.GifHelper.GifFrame;
import com.beikbank.android.utils2.StateBarColor;
import com.beikbank.android.utils.Utils;
import com.beikbank.android.widget.PlayGifTask;
import coma.beikbank.android.R;



//新手势密码引导  更新gif动画加载方式
public class GesturePwdGuideActivity2 extends BaseActivity implements OnClickListener{

	private Button button_create;
	private TextView titleTv;
	private LinearLayout linear_left;
//	private ImageView imageview_guidegif;
//	private PlayGifTask playGifTask;
//	 GifFrame[] frames;
	 
	 
	CustomGifView cgf;
	private GifView gifview; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gesturepwd_guide2);
		StateBarColor.init(this,0xffffffff);
		initView();
		

	}
	@SuppressLint("NewApi") public void initView(){
		titleTv = (TextView) findViewById(R.id.titleTv);
		titleTv.setText(getString(R.string.gesture_password_guide_creat_btn));

		linear_left = (LinearLayout) findViewById(R.id.linear_left);
		linear_left.setVisibility(View.VISIBLE);
		linear_left.setOnClickListener(this);
		
		button_create=(Button)findViewById(R.id.button_create);
		button_create.setOnClickListener(this);
		
		
		cgf=(CustomGifView) findViewById(R.id.gifview);
		cgf.setGifResId(R.drawable.gif_gesture_guide);
		 if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
		        cgf.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
		    }
//		gifview=(GifView) findViewById(R.id.gifview);
//		gifview.setGifImage(R.drawable.gif_gesture_guide);
//		gifview.setGifImageType(GifImageType.COVER);
//		
//		gifview.setShowDimension(500,600);
//		imageview_guidegif=(ImageView)findViewById(R.id.imageview_guidegif);
//		//gif动画
//		 InputStream is=this.getResources().openRawResource(R.drawable.gif_gesture_guide);
//	    frames=Utils.getGif(is);
//		playGifTask=new PlayGifTask(imageview_guidegif, frames);
//		playGifTask.start();
		
	}



	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.linear_left:
			finish();
			break;
		case R.id.button_create:
			startAimActivity(GesturePwdCreateActivity.class);
			break;
		}
	}

	@Override
	protected void onDestroy() {
		
		super.onDestroy();
//		imageview_guidegif=null;
//		playGifTask=null;
//		button_create=null;
//		titleTv=null;
//		linear_left=null;
//		for(int i=0;i<frames.length;i++)
//		{
//			GifFrame gf=frames[i];
//			gf.image.recycle();
//		}
	}
	protected <T> void startAimActivity(final Class<T> pActClassName) 
	{
		Intent _Intent = new Intent();
		_Intent.setClass(this, pActClassName);
		startActivity(_Intent);
		finish();
	}
	



}
