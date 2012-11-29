package com.brentpanther.borderedviews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public class BorderedRelativeLayout extends RelativeLayout implements Bordered {

	private Borders borders;
	
	public BorderedRelativeLayout(Context context) {
		super(context);
		borders = new Borders();
		this.setWillNotDraw(false);
	}

	public BorderedRelativeLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		borders = new Borders(this, context, attrs, new Borders.Closure() {
			@Override
			public void setBackgroundTransparent() {
				BorderedRelativeLayout.super.setBackgroundColor(Color.TRANSPARENT);
			}
		});
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

}

