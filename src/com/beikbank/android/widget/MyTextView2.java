package com.beikbank.android.widget;


import comc.beikbank.android.R;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

public class MyTextView2 extends TextView{
	private String text;
	private float textSize;
	private float paddingLeft;
	private float paddingRight;
	private float marginLeft;
	private float marginRight;
	private int textColor;
	
	
	private Paint paint1 = new Paint();
	private float textShowWidth;
	public MyTextView2(Context context, AttributeSet attrs) {
		super(context, attrs);
		
	   TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.MyTextView2);
	   textSize  = a.getDimension(R.styleable.MyTextView2_textSize, -1);
		marginLeft=a.getDimension(R.styleable.MyTextView2_marginLeft, -1);
		marginRight =a.getDimension(R.styleable.MyTextView2_marginRight, -1);
		textColor=a.getColor(R.styleable.MyTextView2_textColor,-1);
		

		paint1.setTextSize(textSize);
		paint1.setColor(textColor);
		paint1.setAntiAlias(true);
		textShowWidth = ((Activity) context).getWindowManager().getDefaultDisplay().getWidth() - paddingLeft - paddingRight - marginLeft - marginRight;
		//textShowWidth-=10;
	}
	@Override
	protected void onDraw(Canvas canvas) {
		//super.onDraw(canvas);
		int lineCount = 0;
		text = this.getText().toString();//.replaceAll("\n", "\r\n");
		if(text==null)return;
		char[] textCharArray = text.toCharArray();
		float drawedWidth = 0;
		float charWidth;
		for (int i = 0; i < textCharArray.length; i++) {
			charWidth = paint1.measureText(textCharArray, i, 1);
			
			if(textCharArray[i]=='\n'){
				lineCount++;
				drawedWidth = 0;
				continue;
			}
			if (textShowWidth - drawedWidth < charWidth) {
				lineCount++;
				drawedWidth = 0;
			}
			canvas.drawText(textCharArray, i, 1, paddingLeft + drawedWidth,
					(lineCount + 1) * textSize, paint1);
			drawedWidth += charWidth;
		}
		char []a={' '};
		canvas.drawText(a,0, 1, paddingLeft + drawedWidth,
				(lineCount + 2) * textSize, paint1);
		setHeight((lineCount + 1) * (int) textSize + 5);
	}
}
