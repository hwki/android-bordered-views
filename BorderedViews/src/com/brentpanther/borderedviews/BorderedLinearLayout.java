package com.brentpanther.borderedviews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class BorderedLinearLayout extends LinearLayout implements Bordered {

	private Borders borders;
	
	public BorderedLinearLayout(Context context) {
		super(context);
		borders = new Borders();
		this.setWillNotDraw(false);
	}

	public BorderedLinearLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		borders = new Borders(this, context, attrs);
		if(borders.needToSetBackgroundTransparent) super.setBackgroundColor(Color.TRANSPARENT);
		this.setWillNotDraw(false);
	}
	
	void superSetBackgroundColor(int background) {
		super.setBackgroundColor(background);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		borders.onViewDraw(this, canvas);
		super.onDraw(canvas);
	}
	
	public void setBackgroundColor(int background) {
		borders.setBackgroundColor(background);
		postInvalidate();
	}

	public int getBackgroundColor() {
		return borders.getBackgroundColor();
	}

	public void setBorders(boolean left, boolean top, boolean right, boolean bottom) {
		borders.setBorders(left, top, right, bottom);
		postInvalidate();
	}

	public void setBorderColor(int borderColor) {
		borders.setBorderColor(borderColor);
		postInvalidate();
	}

	public int getBorderColor() {
		return borders.getBorderColor();
	}

	public void setBorderWidth(int borderWidth) {
		borders.setBorderWidth(borderWidth);
		postInvalidate();
	}

	public int getBorderWidth() {
		return borders.getBorderWidth();
	}

	public void setRadii(float topLeft, float topRight, float bottomRight, float bottomLeft) {
		borders.setRadii(topLeft, topRight, bottomRight, bottomLeft);
		postInvalidate();
	}

	public float[] getRadii() {
		return borders.getRadii();
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		borders.onMeasure(this, widthMeasureSpec, heightMeasureSpec);
	}

}

