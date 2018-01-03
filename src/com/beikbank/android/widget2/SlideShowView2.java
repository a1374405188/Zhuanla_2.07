package com.beikbank.android.widget2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.beikbank.android.activity.HomeActivity3;
import com.beikbank.android.activity.HomeActivity4;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.utils.DensityUtil;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import coma.beikbank.android.R;



/**
 * ViewPager实现的轮播图广告自定义视图，如京东首页的广告轮播图效果； 既支持自动轮播页面也支持手势滑动切换页面 引导
 * 
 */

public class SlideShowView2 extends FrameLayout {

	// 使用universal-image-loader插件读取网络图片，需要工程导入universal-image-loader-1.8.6-with-sources.jar
	private ImageLoader imageLoader = ImageLoader.getInstance();

	// 轮播图图片数量
	private final static int IMAGE_COUNT = 5;
	// 自动轮播的时间间隔
	private final static int TIME_INTERVAL = 5000;
	// 自动轮播启用开关
	private final static boolean isAutoPlay = true;

	// 自定义轮播图的资源
	private String[] imageUrls;
	// 放轮播图片的ImageView 的list
	private List<ImageView> imageViewsList;
	// 放圆点的View的list
	private List<View> dotViewsList;

	private ViewPager viewPager;
	// 当前轮播页
	private int currentItem = 0;
	// 定时任务
	private ScheduledExecutorService scheduledExecutorService;

	private Context context;

	// Handler
	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			if (msg.what == 1) {
				viewPager.setCurrentItem(currentItem);
			}

		}

	};

	public SlideShowView2(Context context) {
		this(context, null);
		// TODO Auto-generated constructor stub
	}

	public SlideShowView2(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
		// TODO Auto-generated constructor stub
	}

	public SlideShowView2(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.context = context;

		// initImageLoader(context);
		//
		// initData();
		// if(isAutoPlay){
		// startPlay();
		// }

	}

	Activity act;
	private ICallBack icb;

	/**
	 * 
	 * @param list
	 *            图片地址
	 * @param icb
	 *            点击图片时回调接口
	 */
	public void start(Activity act) {

		this.act = act;
		// initImageLoader(context);
		initData();

		initUI(context);
		// stopPlay();
		if (isAutoPlay) {
			// startPlay();
		}

	}

	/**
	 * 处理没有
	 */
	private void nodata() {
		ImageView iv = new ImageView(context);
		iv.setBackgroundResource(R.drawable.img_no_banner);
		LayoutParams lp = new LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT,
				android.view.ViewGroup.LayoutParams.MATCH_PARENT);
		iv.setLayoutParams(lp);
		addView(iv);
	}

	/**
	 * 开始轮播图切换
	 */
	private void startPlay() {
		// scheduledExecutorService =
		// Executors.newSingleThreadScheduledExecutor();
		// scheduledExecutorService.scheduleAtFixedRate(new SlideShowTask(), 1,
		// 4, TimeUnit.SECONDS);
		handler.postDelayed(run, TIME_INTERVAL);

	}

	private Runnable run = new Runnable() {

		@Override
		public void run() {
			if (imageViewsList.size() == 0) {
				return;
			}
			currentItem = (currentItem + 1) % imageViewsList.size();
			Message msg = handler.obtainMessage();
			msg.what = 1;
			handler.sendMessage(msg);
			handler.postDelayed(run, TIME_INTERVAL);
		}
	};

	/**
	 * 停止轮播图切换
	 */
	private void stopPlay() {
		handler.removeCallbacks(run);
		// if(scheduledExecutorService!=null)
		// {
		// scheduledExecutorService.shutdownNow();
		// }
	}

	private void des() {
		for (ImageView iv : imageViewsList) {

			BitmapDrawable drawable = (BitmapDrawable) iv.getBackground();
			Bitmap bmp = drawable.getBitmap();
			if (null != bmp && !bmp.isRecycled()) {
				bmp.recycle();
				bmp = null;
			}
		}
	}

	/**
	 * 初始化相关Data
	 */
	private void initData() {
		currentItem = 0;

		imageViewsList = new ArrayList<ImageView>();
		dotViewsList = new ArrayList<View>();

		ImageView iv1 = new ImageView(context);
		iv1.setBackgroundResource(R.drawable.img_start1);

		ImageView iv2 = new ImageView(context);
		iv2.setBackgroundResource(R.drawable.img_start2);

		ImageView iv3 = new ImageView(context);
		iv3.setBackgroundResource(R.drawable.img_start3);

		ImageView iv4 = new ImageView(context);
		iv4.setBackgroundResource(R.drawable.img_start4);

		ImageView iv5 = new ImageView(context);
		//iv5.setBackgroundResource(R.drawable.img_start5);
		iv4.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				des();
				Intent intent = new Intent(context, HomeActivity4.class);
				context.startActivity(intent);
				act.finish();
				
			}
		});

		imageViewsList.add(iv1);
		imageViewsList.add(iv2);
		imageViewsList.add(iv3);
		imageViewsList.add(iv4);
		//imageViewsList.add(iv5);
		// 一步任务获取图片
		// new GetListTask().execute("");
	}

	/**
	 * 初始化Views等UI
	 */
	private void initUI(Context context) {

		View view = LayoutInflater.from(context).inflate(
				R.layout.layout_slideshow, this, true);

		viewPager = (ViewPager) findViewById(R.id.viewPager);
		// viewPager.setFocusable(true);

		viewPager.setAdapter(new MyPagerAdapter());
		viewPager.setOnPageChangeListener(new MyPageChangeListener());

		LinearLayout dotLayout = (LinearLayout) findViewById(R.id.dotLayout);
		dotLayout.removeAllViews();

		// 热点个数与图片特殊相等
		for (int i = 0; i < imageViewsList.size(); i++) {
			// final ImageView view = new ImageView(context);

			// view.setScaleType(ScaleType.FIT_XY);

			// imageViewsList.add(view);
			DensityUtil du = new DensityUtil(context);
			int width = du.dip2px(8);
			ImageView dotView = new ImageView(context);
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
					android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
			params.leftMargin = 8;
			params.rightMargin = 8;

			dotLayout.addView(dotView, params);

			if (i == 0) {
				dotView.setBackgroundResource(R.drawable.bg_yuan5);
			} else {
				dotView.setBackgroundResource(R.drawable.bg_yuan4);
			}
			dotViewsList.add(dotView);

		}
		android.widget.RelativeLayout.LayoutParams lp2 = new android.widget.RelativeLayout.LayoutParams(
				android.view.ViewGroup.LayoutParams.MATCH_PARENT,
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
		lp2.bottomMargin = 90;
		lp2.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);

		dotLayout.setLayoutParams(lp2);

	}

	/**
	 * 填充ViewPager的页面适配器
	 * 
	 */
	private class MyPagerAdapter extends PagerAdapter {

		@Override
		public void destroyItem(View container, int position, Object object) {
			// TODO Auto-generated method stub
			// ((ViewPag.er)container).removeView((View)object);
			((ViewPager) container).removeView(imageViewsList.get(position));
		}

		@Override
		public Object instantiateItem(View container, int position) {
			ImageView imageView = imageViewsList.get(position);

			// imageLoader.displayImage(imageView.getTag() + "", imageView);

			((ViewPager) container).addView(imageViewsList.get(position));
			return imageViewsList.get(position);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return imageViewsList.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0 == arg1;
		}

		@Override
		public void restoreState(Parcelable arg0, ClassLoader arg1) {
			// TODO Auto-generated method stub

		}

		@Override
		public Parcelable saveState() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void startUpdate(View arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void finishUpdate(View arg0) {
			// TODO Auto-generated method stub

		}

	}

	/**
	 * ViewPager的监听器 当ViewPager中页面的状态发生改变时调用
	 * 
	 */
	private class MyPageChangeListener implements OnPageChangeListener {

		boolean isAutoPlay = false;

		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub
			switch (arg0) {
			case 1:// 手势滑动，空闲中
				isAutoPlay = false;
				break;
			case 2:// 界面切换中
				isAutoPlay = true;
				break;
			case 0:// 滑动结束，即切换完毕或者加载完毕
				// // 当前为最后一张，此时从右向左滑，则切换到第一张
				// if (viewPager.getCurrentItem() ==
				// viewPager.getAdapter().getCount() - 1 && !isAutoPlay) {
				// viewPager.setCurrentItem(0);
				// }
				// // 当前为第一张，此时从左向右滑，则切换到最后一张
				// else if (viewPager.getCurrentItem() == 0 && !isAutoPlay) {
				// viewPager.setCurrentItem(viewPager.getAdapter().getCount() -
				// 1);
				// }
				break;
			}
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onPageSelected(int pos) {
			// TODO Auto-generated method stub

			currentItem = pos;
			for (int i = 0; i < dotViewsList.size(); i++) {
				if (i == pos) {
					dotViewsList.get(pos)
							.setBackgroundResource(R.drawable.bg_yuan5);
				} else {
					dotViewsList.get(i)
							.setBackgroundResource(R.drawable.bg_yuan4);
				}
			}
		}

	}

	/**
	 * 执行轮播图切换任务
	 * 
	 */
	private class SlideShowTask implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			synchronized (viewPager) {
				currentItem = (currentItem + 1) % imageViewsList.size();
				handler.obtainMessage().sendToTarget();
			}
		}

	}

	/**
	 * 销毁ImageView资源，回收内存
	 * 
	 */
	private void destoryBitmaps() {

		for (int i = 0; i < IMAGE_COUNT; i++) {
			ImageView imageView = imageViewsList.get(i);
			Drawable drawable = imageView.getDrawable();
			if (drawable != null) {
				// 解除drawable对view的引用
				drawable.setCallback(null);
			}
		}
	}

	// @Override
	// public boolean onInterceptTouchEvent(MotionEvent ev) {
	// if(ev.getAction()==MotionEvent.ACTION_DOWN)
	// {
	// stopPlay();
	// }
	// else if(ev.getAction()==MotionEvent.ACTION_UP)
	// {
	// startPlay();
	// }
	// else if(ev.getAction()==MotionEvent.ACTION_CANCEL)
	// {
	// startPlay();
	// }
	// return super.onInterceptTouchEvent(ev);
	// }
	// /**
	// * 异步任务,获取数据
	// *
	// */
	// class GetListTask extends AsyncTask<String, Integer, Boolean> {
	//
	// @Override
	// protected Boolean doInBackground(String... params) {
	// try {
	// // 这里一般调用服务端接口获取一组轮播图片，下面是从百度找的几个图片
	//
	// imageUrls = new String[]{
	// "http://pic30.nipic.com/20130626/8174275_085522448172_2.jpg",
	// "http://pic30.nipic.com/20130626/8174275_085522448172_2.jpg",
	// "http://pic30.nipic.com/20130626/8174275_085522448172_2.jpg",
	// "http://pic30.nipic.com/20130626/8174275_085522448172_2.jpg"
	// };
	// return true;
	// } catch (Exception e) {
	// e.printStackTrace();
	// return false;
	// }
	// }

	// @Override
	// protected void onPostExecute(Boolean result) {
	// super.onPostExecute(result);
	// if (result) {
	// initUI(context);
	// }
	// }
	// }

	/**
	 * ImageLoader 图片组件初始化
	 * 
	 * @param context
	 */
	public static void initImageLoader(Context context) {
		// This configuration tuning is custom. You can tune every option, you
		// may tune some of them,
		// or you can create default configuration by
		// ImageLoaderConfiguration.createDefault(this);
		// method.
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				context).threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory()
				.discCacheFileNameGenerator(new Md5FileNameGenerator())
				.tasksProcessingOrder(QueueProcessingType.LIFO)
				.writeDebugLogs() // Remove
									// for
									// release
									// app
				.build();
		// Initialize ImageLoader with configuration.
		ImageLoader.getInstance().init(config);
	}
}