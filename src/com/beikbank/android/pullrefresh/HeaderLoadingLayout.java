package com.beikbank.android.pullrefresh;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.beikbank.android.widget.CustomProgressBar;
import coma.beikbank.android.R;



/**
 * 这个类封装了下拉刷新的布局
 * 
 * @author Li Hong
 * @since 2013-7-30
 */
public class HeaderLoadingLayout extends LoadingLayout {
    /**Header的容器*/
    private RelativeLayout mHeaderContainer;
    /**进度条*/
    private CustomProgressBar mProgressBar;
    /**状态提示TextView*/
    private TextView mHintTextView;
    
    /**
     * 构造方法
     * 
     * @param context context
     */
    public HeaderLoadingLayout(Context context) {
        super(context);
        init(context);
    }

    /**
     * 构造方法
     * 
     * @param context context
     * @param attrs attrs
     */
    public HeaderLoadingLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    /**
     * 初始化
     * 
     * @param context context
     */
    private void init(Context context) {
        mHeaderContainer = (RelativeLayout) findViewById(R.id.head_contentLayout);
        mHintTextView = (TextView) findViewById(R.id.head_tipsTextView);
        mProgressBar = (CustomProgressBar) findViewById(R.id.head_progressBar);
        
    }
    public void setBackColor()
    {
    	
    }

    @Override
    public void setLastUpdatedLabel(CharSequence label) {
        // 如果最后更新的时间的文本是空的话，隐藏前面的标题
    	 mHintTextView.setText(label);
    }

    @Override
    public int getContentSize() {
        if (null != mHeaderContainer) {
            return mHeaderContainer.getHeight();
        }
        
        return (int) (getResources().getDisplayMetrics().density * 60);
    }
    
    @Override
    protected View createLoadingView(Context context, AttributeSet attrs) {
        View container = LayoutInflater.from(context).inflate(R.layout.pull_to_refresh_head, null);
        return container;
    }
    
    @Override
    protected void onStateChanged(State curState, State oldState) {
        mProgressBar.setVisibility(View.GONE);
        
        super.onStateChanged(curState, oldState);
    }

    @Override
    protected void onReset() {
        mHintTextView.setText(R.string.pull_to_refresh_header_hint_normal);
    }

    @Override
    protected void onPullToRefresh() {
        mHintTextView.setText(R.string.pull_to_refresh_header_hint_normal);
    }

    @Override
    protected void onReleaseToRefresh() {
        mHintTextView.setText(R.string.pull_to_refresh_header_hint_ready);
    }

    @Override
    protected void onRefreshing() {
        mProgressBar.setVisibility(View.VISIBLE);
        mHintTextView.setText(R.string.pull_to_refresh_header_hint_loading);
    }
    
    public void setTextColor(int color)
    {
    	mHintTextView.setTextColor(color);
    }
}
