package com.beikbank.android.widget;

import comc.beikbank.android.R;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;


public class CustomProgressBar2 extends LinearLayout{


    public CustomProgressBar2(Context context){
        super(context);
        init( context);
    }
    public CustomProgressBar2(Context context, AttributeSet attrs){
        super(context, attrs);
        init( context);
    }
    private void init(Context context){
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View  progressBarItemView=(View)inflater.inflate(R.layout.progressbar_item2, null);
        addView(progressBarItemView);
        ImageView progressImageView=(ImageView)this.findViewById(R.id.imageview_loading);
        AnimationDrawable animationDrawable = (AnimationDrawable) progressImageView.getDrawable();
        animationDrawable.start();
    }
}
