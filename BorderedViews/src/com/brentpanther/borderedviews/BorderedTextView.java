package com.brentpanther.borderedviews;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.TextView;

public class BorderedTextView extends TextView {
	
	private Borders borders;
	
	public BorderedTextView(Context context) {
		super(context);
		borders = new Borders();
	}

	public BorderedTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		borders = new Borders(context, attrs);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		borders.translate(canvas);
		super.onDraw(canvas);
		borders.onViewDraw(this, canvas);
	}
	
	@Override
	public void draw(Canvas canvas) {
		borders.draw(this, canvas);
		super.draw(canvas);
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
		int w = borders.getMeasuredWidth(this, widthMeasureSpec);
		int h = borders.getMeasuredHeight(this, heightMeasureSpec);
		setMeasuredDimension(w, h);
	}
}
