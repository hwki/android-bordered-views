package com.brentpanther.borderedviews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.ImageView;

public class BorderedImageView extends ImageView implements Bordered {

	private Borders borders;
	
	public BorderedImageView(Context context) {
		super(context);
		borders = new Borders();
	}

	public BorderedImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		borders = new Borders(this, context, attrs);
		if(borders.needToSetBackgroundTransparent) super.setBackgroundColor(Color.TRANSPARENT);
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

}
