package com.brentpanther.borderedviews;


import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class BorderedLinearLayout extends LinearLayout implements Bordered {
	
	private Borders borders;

    public BorderedLinearLayout(Context context) {
        super(context);
    }

    public BorderedLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        borders = new Borders(context, attrs);
        borders.setBackground(this);
    }

    public BorderedLinearLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        borders = new Borders(context, attrs);
        borders.setBackground(this);
    }

    public void setViewPadding(int left, int top, int right, int bottom) {
        super.setPadding(left, top, right, bottom);
        borders.setBackground(this);
        invalidate();
    }

    public void setBorders(boolean left, boolean top, boolean right, boolean bottom) {
        borders.setBorders(this, left, top, right, bottom);
    }

    public void setBorderColor(int borderColor) {
        borders.setBorderColor(this, borderColor);
    }

    public void setBorderWidth(int borderWidth) {
        borders.setBorderWidth(this, borderWidth);
    }

    public void setRadii(float topLeft, float topRight, float bottomRight, float bottomLeft) {
        borders.setRadii(this, topLeft, topRight, bottomRight, bottomLeft);
    }

    public void setBackgroundColor(int color) {
        borders.setBackgroundColor(this, color);
    }
}
