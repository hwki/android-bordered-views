package com.brentpanther.borderedviews;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.FrameLayout;

public class BorderedFrameLayout extends FrameLayout implements Bordered {

	private Borders borders;

	public BorderedFrameLayout(Context context) {
		super(context);
		borders = new Borders();
		setPadding(getPaddingLeft(), getPaddingTop(), getPaddingRight(), getPaddingBottom());
	}

	public BorderedFrameLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		borders = new Borders(context, attrs);
		setPadding(getPaddingLeft(), getPaddingTop(), getPaddingRight(), getPaddingBottom());
	}
	
	public BorderedFrameLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		borders = new Borders(context, attrs);
		setPadding(getPaddingLeft(), getPaddingTop(), getPaddingRight(), getPaddingBottom());
	}

	public void setPadding(int left, int top, int right, int bottom) {
		if(isLayoutRequested() || borders==null) {
			super.setPadding(left, top, right, bottom);
		} else {
			super.setPadding(left + borders.getBorderLeft(), top + borders.getBorderTop(), right + borders.getBorderRight(), bottom + borders.getBorderBottom());
		}
	}
	
	@Override
	public void draw(Canvas canvas) {
		Bitmap bitmap = Bitmap.createBitmap(canvas.getWidth(), canvas.getHeight(), Config.ARGB_8888);
		super.draw(new Canvas(bitmap));
		borders.drawBackground(this, canvas, bitmap);
		borders.draw(this, canvas);
	}
	
	public void setBorders(boolean left, boolean top, boolean right, boolean bottom) {
		borders.setBorders(this, left, top, right, bottom);
	}

	public void setBorderColor(int borderColor) {
		borders.setBorderColor(this, borderColor);
	}

	public int getBorderColor() {
		return borders.getBorderColor();
	}

	public void setBorderWidth(int borderWidth) {
		borders.setBorderWidth(this, borderWidth);
	}

	public int getBorderWidth() {
		return borders.getBorderWidth();
	}

	public void setRadii(float topLeft, float topRight, float bottomRight, float bottomLeft) {
		borders.setRadii(this, topLeft, topRight, bottomRight, bottomLeft);
	}

	public float[] getRadii() {
		return borders.getRadii();
	}

}


