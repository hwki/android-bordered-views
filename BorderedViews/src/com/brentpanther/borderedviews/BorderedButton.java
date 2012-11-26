package com.brentpanther.borderedviews;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.Button;

public class BorderedButton extends Button {

	private Borders borders;

	public BorderedButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		borders = new Borders(this, context, attrs);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		borders.onViewDraw(this, canvas);
		super.onDraw(canvas);
	}
	
	public void setBackgroundColor(int background) {
		borders.setBackgroundColor(background);
	}

	public int getBackgroundColor() {
		return borders.getBackgroundColor();
	}

	public void setBorders(boolean left, boolean top, boolean right, boolean bottom) {
		borders.setBorders(left, top, right, bottom);
	}

	public void setBorderColor(int borderColor) {
		borders.setBorderColor(borderColor);
	}

	public int getBorderColor() {
		return borders.getBorderColor();
	}

	public void setBorderWidth(int borderWidth) {
		borders.setBorderWidth(borderWidth);
	}

	public int getBorderWidth() {
		return borders.getBorderWidth();
	}

	public void setRadii(float topLeft, float topRight, float bottomRight, float bottomLeft) {
		borders.setRadii(topLeft, topRight, bottomRight, bottomLeft);
	}

	public float[] getRadii() {
		return borders.getRadii();
	}

}
