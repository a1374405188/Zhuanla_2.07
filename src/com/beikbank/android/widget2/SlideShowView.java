package com.beikbank.android.widget2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;


import com.beikbank.android.net.ICallBack;
import com.beikbank.android.utils.DensityUtil;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import coma.beikbank.android.R;




/**
 * ViewPager
 * 
 * 
 *
 */

public class SlideShowView extends FrameLayout {
	
	
	private ImageLoader imageLoader = ImageLoader.getInstance();

    //
    private final static int IMAGE_COUNT = 5;
    //
    private final static int TIME_INTERVAL = 5000;
    //
    private final static boolean isAutoPlay = true; 
    
    //
    private  String[] imageUrls;
    //ImageView list
    private  List<ImageView> imageViewsList;
    //
    private  List<View> dotViewsList;
    
    private ViewPager viewPager;
    //
    private int currentItem  = 0;
    //
    private ScheduledExecutorService scheduledExecutorService;
    
    private Context context;
    
    //Handler
    private Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            if(msg.what==1)
            {
            	 viewPager.setCurrentItem(currentItem);
            }
           
        }
        
    };
    
    public SlideShowView(Context context) {
        this(context,null);
        // TODO Auto-generated constructor stub
        initImageLoader(context);
    }
    public SlideShowView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        // TODO Auto-generated constructor stub
        initImageLoader(context);
    }
    public SlideShowView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        initImageLoader(context);
//		initImageLoader(context);
//		
//        initData();
//        if(isAutoPlay){
//            startPlay();
//        }
        
    }
    private ICallBack icb;
    /**
     * 
     * @param list 图片地址
     * @param icb 点击图片时回调接口
     */
    public void start(String imgs[],ICallBack icb)
    {   
    	if(imgs==null||imgs.length==0)
    	{
    		nodata();
    		return;
    	}
    	if(imageUrls!=null)
    	{
    		if(imageUrls.length==imgs.length)
    		{   
    			boolean b=true;
    			for(int i=0;i<imgs.length;i++)
    			{
    				if(!imgs[i].equals(imageUrls[i]))
    				{
    					b=false;
    					break;
    				}
    			}
    			if(b)
    			{
    				return;
    			}
    			
    		}
    	}
    	

        initData();
        imageUrls=imgs;
        this.icb=icb;
        initUI(context);
        stopPlay();
        if(isAutoPlay){
            startPlay();
        }
    	
    }
    /**
     * 处理没有
     */
    private void nodata()
    {
    	ImageView iv=new ImageView(context);
    	iv.setBackgroundResource(R.drawable.img_no_banner);
    	LayoutParams lp=new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
    	iv.setLayoutParams(lp);
    	addView(iv);
    }
    /**
     * 开始轮播图切换
     */
    private void startPlay(){
        //scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        //scheduledExecutorService.scheduleAtFixedRate(new SlideShowTask(), 1, 4, TimeUnit.SECONDS);
       handler.postDelayed(run,TIME_INTERVAL);
        
    }
    
    private Runnable run=new Runnable() {
		
		@Override
		public void run() {
			if(imageViewsList.size()==0)
			{
				return;
			}
			 currentItem = (currentItem+1)%imageViewsList.size();
             Message msg= handler.obtainMessage();
             msg.what=1;
             handler.sendMessage(msg);
			 handler.postDelayed(run,TIME_INTERVAL);
		}
	};
    /**
     * 停止轮播图切换
     */
    private void stopPlay(){
    	handler.removeCallbacks(run);
//    	if(scheduledExecutorService!=null)
//    	{
//        scheduledExecutorService.shutdownNow();
//    	}
    }
    
   Map<Integer,Boolean> map=new HashMap<Integer, Boolean>();
    /**
     * 初始化相关Data
     */
    private void initData(){
    	currentItem=0;
    	
        imageViewsList = new ArrayList<ImageView>();
        dotViewsList = new ArrayList<View>();
        map=new HashMap<Integer, Boolean>();
        // 
       // new GetListTask().execute("");
    }
    /**
     * 初始化Views等UI
     */
    private void initUI(Context context){
    	if(imageUrls == null || imageUrls.length == 0)
    		return;
        removeAllViews();
        LayoutInflater.from(context).inflate(R.layout.layout_slideshow, this, true);
        
        LinearLayout dotLayout = (LinearLayout)findViewById(R.id.dotLayout);
        dotLayout.removeAllViews();
        
        // 
        for (int i = 0; i < imageUrls.length; i++) {
        	final ImageView view =  new ImageView(context);
        	view.setTag(imageUrls[i]);
        	//if(i==0)//
        		view.setBackgroundResource(R.drawable.img_no_banner);
        	
        	view.setScaleType(ScaleType.FIT_XY);
        	final int a=i;
        	view.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					icb.back(a);
					
				}
			});
        	imageViewsList.add(view);
             map.put(i,false);
        	DensityUtil du=new DensityUtil(context);
        	int width=du.dip2px(8);
        	ImageView dotView =  new ImageView(context);
        	LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width,width);
        	params.leftMargin = 4;
			params.rightMargin = 4;
			if(i==0)
			{
				dotView.setBackgroundResource(R.drawable.dot_focus);
			}
			else
			{
				dotView.setBackgroundResource(R.drawable.dot_blur);
			}
			dotLayout.addView(dotView, params);
        	dotViewsList.add(dotView);
		}
        android.widget.RelativeLayout.LayoutParams lp2=new   android.widget.RelativeLayout.LayoutParams(android.widget.RelativeLayout.LayoutParams.MATCH_PARENT,android.widget.RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp2.bottomMargin=15;
        lp2.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        dotLayout.setLayoutParams(lp2);
        
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setFocusable(true);
        MyPagerAdapter ma=new MyPagerAdapter();
        viewPager.setAdapter(ma);
        viewPager.setOnPageChangeListener(new MyPageChangeListener());
        ma.notifyDataSetChanged();
    }
    
    /**
     * 填充ViewPager的页面适配器
     * 
     */
    private class MyPagerAdapter  extends PagerAdapter{

        @Override
        public void destroyItem(View container, int position, Object object) {
            // TODO Auto-generated method stub
            //((ViewPag.er)container).removeView((View)object);
            ((ViewPager)container).removeView(imageViewsList.get(position));
        }

        @Override
        public Object instantiateItem(View container, int position) {
        	ImageView imageView = imageViewsList.get(position);
        	boolean b1=map.get(position);
        	if(!b1)
            {
            	imageLoader.displayImage(imageView.getTag() + "", imageView);
            	map.put(position,true);
            }
			
            else
            {
            	
            }
        	
            ((ViewPager)container).addView(imageViewsList.get(position));
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
     * ViewPager的监听器
     * 当ViewPager中页面的状态发生改变时调用
     * 
     */
    private class MyPageChangeListener implements OnPageChangeListener{

        boolean isAutoPlay = false;

        @Override
        public void onPageScrollStateChanged(int arg0) {
            // TODO Auto-generated method stub
            switch (arg0) {
            case 1:// 
                isAutoPlay = false;
                break;
            case 2:// 
                isAutoPlay = true;
                break;
            case 0:// 
//                // 
                if (viewPager.getCurrentItem() == viewPager.getAdapter().getCount() - 1 && !isAutoPlay) {
                    viewPager.setCurrentItem(0);
                }
                // 
                else if (viewPager.getCurrentItem() == 0 && !isAutoPlay) {
                    viewPager.setCurrentItem(viewPager.getAdapter().getCount() - 1);
                }
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
            for(int i=0;i < dotViewsList.size();i++){
                if(i == pos){
                    ((View)dotViewsList.get(pos)).setBackgroundResource(R.drawable.dot_focus);
                }else {
                    ((View)dotViewsList.get(i)).setBackgroundResource(R.drawable.dot_blur);
                }
            }
        }
        
    }
    
    /**
     *执行轮播图切换任务
     *
     */
    private class SlideShowTask implements Runnable{

        @Override
        public void run() {
            // TODO Auto-generated method stub
            synchronized (viewPager) {
                currentItem = (currentItem+1)%imageViewsList.size();
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
                //
                drawable.setCallback(null);
            }
        }
    }
    int lastX;
    int lastY;
    @Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
    	 int x = (int) ev.getX();
         int y = (int) ev.getY();

         switch (ev.getAction()){
             case MotionEvent.ACTION_DOWN:
                 getParent().requestDisallowInterceptTouchEvent(true);//父容器不拦截点击事件，子控件拦截点击事件
                 break;
             case MotionEvent.ACTION_MOVE:
                 int deltaX = x - lastX;
                 int deltaY = y - lastY;
//                 if(Math.abs(deltaY) - Math.abs(deltaX) > 0){//竖直滑动的父容器拦截事件
//                     getParent().requestDisallowInterceptTouchEvent(false);
//                 }
                 break;
             case MotionEvent.ACTION_UP:
                 break;
             default:
                 break;
         }
         lastX = x;
         lastY = y;
       
		return super.dispatchTouchEvent(ev);
	}
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
    	
    	
		if(ev.getAction()==MotionEvent.ACTION_DOWN)
		{
			stopPlay();
		}
		else if(ev.getAction()==MotionEvent.ACTION_UP)
		{
			startPlay();
		}
		else if(ev.getAction()==MotionEvent.ACTION_CANCEL)
		{
			startPlay();
		}
		return super.onInterceptTouchEvent(ev);
	}
//	/**
//	 * 
//	 * 
//	 */
//	class GetListTask extends AsyncTask<String, Integer, Boolean> {
//
//		@Override
//		protected Boolean doInBackground(String... params) {
//			try {
//				//
//				
//				imageUrls = new String[]{
//						"http://pic30.nipic.com/20130626/8174275_085522448172_2.jpg",
//						"http://pic30.nipic.com/20130626/8174275_085522448172_2.jpg",
//						"http://pic30.nipic.com/20130626/8174275_085522448172_2.jpg",
//						"http://pic30.nipic.com/20130626/8174275_085522448172_2.jpg"
//				};
//				return true;
//			} catch (Exception e) {
//				e.printStackTrace();
//				return false;
//			}
//		}

//		@Override
//		protected void onPostExecute(Boolean result) {
//			super.onPostExecute(result);
//			if (result) {
//		        initUI(context);
//			}
//		}
//	}
	
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
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context).threadPriority(Thread.NORM_PRIORITY - 2).denyCacheImageMultipleSizesInMemory().discCacheFileNameGenerator(new Md5FileNameGenerator()).tasksProcessingOrder(QueueProcessingType.LIFO).writeDebugLogs() // Remove
																																																																								// for
																																																																								// release
																																																																								// app
				.build();
		// Initialize ImageLoader with configuration.
		ImageLoader.getInstance().init(config);
	}
}