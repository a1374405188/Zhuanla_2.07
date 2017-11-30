package com.beikbank.android.widget2;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.beikbank.android.data.DingqiP2;
import com.beikbank.android.data.DingqiP_data2;
import com.beikbank.android.dataparam.UserParam;
import com.beikbank.android.fragment.BeikBankApplication;
import com.beikbank.android.net.ICallBack;
import com.beikbank.android.net.impl.TongYongManager;
import com.beikbank.android.net.impl2.ChanpinManager;
import com.beikbank.android.utils.DensityUtil;
import com.beikbank.android.utils2.StateBarColor;
import com.beikbank.android.widget.MyViewPager;
import coma.beikbank.android.R;




import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v4.widget.EdgeEffectCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

public class PageView2 extends LinearLayout{
	
	private MyAdapter mAdapter;
	public MyViewPager mPager;
	private MyPageChangeListener myPageChangeListener;
	private ArrayList<View> pagerItemList = new ArrayList<View>();
	private Activity act;
	
	private LinearLayout ll_huoqi,ll_dingqi,ll_xinshou;
	public Pview1 p1;
	private interface MyPageChangeListener {
		public void onPageSelected(int position);
	}
	public PageView2(Context context) {
		super(context);
		this.act=(Activity) context;
		init(context);
		
		initData();
	}
	public PageView2(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		//init(context);
		this.act=(Activity) context;
	}
	/**
	 * 初始化,该方法必须调用
	 */
    private void init(Context context)
    {   
        
    	//LinearLayout ll=new LinearLayout(act);
    	View view= LayoutInflater.from(context).inflate(R.layout.view_pageview2,this); 
    	mPager = (MyViewPager) view.findViewById(R.id.pager);
if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT)
    		
    	{
    	LinearLayout ll_top=(LinearLayout) view.findViewById(R.id.ll_top);
    	int h=StateBarColor.getStatusBarHeight(act);
    	DensityUtil du=new DensityUtil(act);
    	ll_top.setPadding(du.dip2px(16),h,du.dip2px(16),0);
    	LayoutParams lp_top=new LayoutParams(LayoutParams.MATCH_PARENT,du.dip2px(44)+h);
    	ll_top.setLayoutParams(lp_top);
    	}
    	ll_huoqi=(LinearLayout) view.findViewById(R.id.ll_huoqi);
    	ll_dingqi=(LinearLayout) view.findViewById(R.id.ll_dingqi);
    	ll_xinshou=(LinearLayout) view.findViewById(R.id.ll_xinshou);
    	
    	
    	ll_huoqi.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mPager.setCurrentItem(0,true);
			}
		});
    	
    	ll_dingqi.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mPager.setCurrentItem(1,true);
			}
		});
    	ll_xinshou.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mPager.setCurrentItem(2,true);
			}
		});
    	
    	
    	class Pageon implements OnPageChangeListener
		{
			 private EdgeEffectCompat leftEdge;  
			 private EdgeEffectCompat rightEdge; 

            public Pageon()
            {
            	 try {   
            		 
            		 Field leftEdgeField = mPager.getClass().getSuperclass().getDeclaredField("mLeftEdge");             
            		 Field rightEdgeField = mPager.getClass().getSuperclass().getDeclaredField("mRightEdge");     
            		 if(leftEdgeField != null && rightEdgeField != null){    
            			 leftEdgeField.setAccessible(true);              
            			 rightEdgeField.setAccessible(true);               
            			 leftEdge = (EdgeEffectCompat) leftEdgeField.get(mPager);    
            			 rightEdge = (EdgeEffectCompat) rightEdgeField.get(mPager);       
            			 }       
            		 } catch (Exception e) {     
            			 e.printStackTrace();      
            			 } 


            }
			@Override
			public void onPageSelected(int position) {

				mPager.setCurrentIndex(position);
                
			    setTitle(position);
			
				 if(leftEdge != null && rightEdge != null) 
				 {             
					 leftEdge.finish();     
					 rightEdge.finish();    
					 leftEdge.setSize(0, 0);  
					 rightEdge.setSize(0, 0);    
				  }
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				 if(leftEdge != null && rightEdge != null) 
				 {             
					 leftEdge.finish();     
					 rightEdge.finish();    
					 leftEdge.setSize(0, 0);  
					 rightEdge.setSize(0, 0);    
				  }
			}

			@Override
			public void onPageScrollStateChanged(int position) {

			}
		}
    	
    	p1=new Pview1(context);
    	Pview2 p2=new Pview2(context);
    	//Pview3 p3=new Pview3(context);
    	
    	pagerItemList.add(p1);
    	pagerItemList.add(p2);
    	//pagerItemList.add(p3);
    	
    	
    		Pview3 p3=new Pview3(context);
    		pagerItemList.add(p3);
    	
    	
		mPager.setOnPageChangeListener(new Pageon());
		mAdapter = new MyAdapter();
		mPager.setAdapter(mAdapter);
		mPager.setCurrentItem(0);
    }
    /**
     * 设置当前的项
     * @param index
     */
    public void setItem(int index)
    {
    	mPager.setCurrentItem(index,false);
    	
    }
    /**
     * 初始化新手标
     */
    private void init2()
    {   
    	TextView tv3=(TextView) findViewById(R.id.tv3);
    	
    	View v3=findViewById(R.id.v3);
    	if(isHasNew)
    	{
    		
    		tv3.setVisibility(View.VISIBLE);
        	//v3.setVisibility(View.VISIBLE);
    	}
    	else
    	{
    		
        	
        	tv3.setVisibility(View.GONE);
        	v3.setVisibility(View.GONE);
    	}
    }
    private void setTitle(int index)
    {
    	TextView tv1=(TextView) findViewById(R.id.tv1);
    	TextView tv2=(TextView) findViewById(R.id.tv2);
    	TextView tv3=(TextView) findViewById(R.id.tv3);
    	View v1=findViewById(R.id.v1);
    	View v2=findViewById(R.id.v2);
    	View v3=findViewById(R.id.v3);
    	int color1=0x5affffff;
    	int color2=0xffffffff;
    	
    	tv1.setTextColor(color1);
    	tv2.setTextColor(color1);
    	tv3.setTextColor(color1);
    	v1.setBackgroundColor(color1);
    	v2.setBackgroundColor(color1);
    	v3.setBackgroundColor(color1);
    	v1.setVisibility(View.INVISIBLE);
    	v2.setVisibility(View.INVISIBLE);
    	v3.setVisibility(View.INVISIBLE);
    	if(index==0)
    	{
    		tv1.setTextColor(color2);
    		v1.setBackgroundColor(color2);
    		v1.setVisibility(View.VISIBLE);
    	}
    	 else if(index==1)
    	{
    		 tv2.setTextColor(color2);
     		 v2.setBackgroundColor(color2);
     		 v2.setVisibility(View.VISIBLE);
    	}
    	 else
    	 {
    		 tv3.setTextColor(color2);
     		 v3.setBackgroundColor(color2);
     		 v3.setVisibility(View.VISIBLE);
    	 }
    }
    public class MyAdapter extends PagerAdapter
	{

		@Override
		public int getCount() {
			return pagerItemList.size();
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// TODO Auto-generated method stub
			container.removeView((View)object);
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			container.addView(pagerItemList.get(position));
			return pagerItemList.get(position);
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return  arg0==arg1;
		}

		@Override
		public int getItemPosition(Object object) {
			// TODO Auto-generated method stub
			return POSITION_NONE;
		}
	}
    private ChanpinManager cm;
    /**
     * 
     */
    private void initData()
    {
    	UserParam up=new UserParam();
 		String id=BeikBankApplication.getUserid();
 		if(id==null)
 		{
 			id="";
 		}
 		up.memberID=id;
 		TongYongManager tym=new TongYongManager(act, icb3,up);
 		tym.start();
    }
    /**
  	 * 所有定期产品
  	 */
  	public List<DingqiP2> list2;
  	public DingqiP_data2 dpd;
  	/**
  	 * 是否有新手标
  	 */
  	private boolean isHasNew;
    private ICallBack icb3=new ICallBack() {
		
  		@Override
  		public void back(Object obj) {
  				
  		if(obj!=null)
  		{
  			 dpd=(DingqiP_data2)obj;
		     list2=dpd.data.productBond;
  	         for(DingqiP2 dp:list2)
  	         {
  	        	 if(dp.termbondType.equals("1"))
  	        	 {
  	        		 isHasNew=true;
  	        	 }
  	         }
  	        init2();
  		}
  	       
  		}
  	};
}
