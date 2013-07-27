package com.brentpanther.borderedviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

public class Borders {

    private Drawable backgroundDrawable;
    private int borderWidth;
	private int borderColor;
	private float[] radii;
    private boolean[] borders;
    private Integer backgroundColor;
    private int paddingLeft;
    private int paddingRight;
    private int paddingTop;
    private int paddingBottom;

    Borders() {
        borders = new boolean[4];
	}

	Borders(Context context, AttributeSet attrs) {
		this();
		TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.Borders, 0, 0);
		radii = new float[8];
		try {
			backgroundColor = Color.TRANSPARENT;
			if(a.hasValue(R.styleable.Borders_android_background)) {
				TypedValue v = a.peekValue(R.styleable.Borders_android_background);
				if(v.type == TypedValue.TYPE_INT_COLOR_ARGB4 || v.type == TypedValue.TYPE_INT_COLOR_ARGB8
					|| v.type == TypedValue.TYPE_INT_COLOR_RGB4 || v.type == TypedValue.TYPE_INT_COLOR_RGB8) {
					backgroundColor = a.getColor(R.styleable.Borders_android_background, -1);
				}  else {
                    backgroundDrawable = a.getDrawable(R.styleable.Borders_android_background);
                }
			}
			if (a.hasValue(R.styleable.Borders_borderRadius)) {
				int radius = a.getDimensionPixelSize(R.styleable.Borders_borderRadius, 0);
				radii = new float[] {radius, radius, radius, radius, radius, radius, radius, radius};
			} else {
				radii[0] = a.getDimensionPixelSize(R.styleable.Borders_radiusTopLeft, 0);
                radii[1] = radii[0];
				radii[2] = a.getDimensionPixelSize(R.styleable.Borders_radiusTopRight, 0);
                radii[3] = radii[2];
				radii[4] = a.getDimensionPixelSize(R.styleable.Borders_radiusBottomRight, 0);
                radii[5] = radii[4];
				radii[6] = a.getDimensionPixelSize(R.styleable.Borders_radiusBottomLeft, 0);
                radii[7] = radii[6];
			}
			borderWidth = a.getDimensionPixelSize(R.styleable.Borders_borderWidth, 1);
			borderColor = a.getColor(R.styleable.Borders_borderColor, Color.BLACK);
			int border = a.getInteger(R.styleable.Borders_borders, 15);
			borders[0] = (border & 1) != 0;
			borders[1] = (border & 2) != 0;
			borders[2] = (border & 4) != 0;
			borders[3] = (border & 8) != 0;
		} finally {
			a.recycle();
		}
	}

	void setBorders(View view, boolean left, boolean top, boolean right, boolean bottom) {
        borders = new boolean[] {top, bottom, left, right};
        setBackground(view);
        view.invalidate();
	}

	void setBorderColor(View view, int borderColor) {
		this.borderColor = borderColor;
        setBackground(view);
		view.invalidate();
	}

    void setBackgroundColor(View view, int color) {
        this.backgroundColor = color;
        setBackground(view);
        view.invalidate();
    }

	void setBorderWidth(View view, int borderWidth) {
		this.borderWidth = borderWidth;
        setBackground(view);
		view.invalidate();
	}

	void setRadii(View view, float topLeft, float topRight, float bottomRight, float bottomLeft) {
		radii = new float[] { topLeft, topLeft, topRight, topRight, bottomRight, bottomRight, bottomLeft, bottomLeft }; 
        setBackground(view);
        view.invalidate();
	}

	@SuppressWarnings("deprecation")
	void setBackground(View view) {
        paddingLeft = view.getPaddingLeft();
        paddingRight = view.getPaddingRight();
        paddingTop = view.getPaddingTop();
        paddingBottom = view.getPaddingBottom();
        GradientDrawable d = new GradientDrawable();
        d.setColor(backgroundColor);
        d.setCornerRadii(radii);
        d.setStroke(borderWidth, borderColor);
        GradientDrawable d2 = new GradientDrawable();
        d2.setColor(backgroundColor);
        d2.setCornerRadii(radii);
        d2.setStroke(borderWidth, backgroundColor);
        LayerDrawable background = new LayerDrawable(new Drawable[] {d, d2});
        background.setLayerInset(1, borders[2] ? borderWidth : 0, borders[0] ? borderWidth : 0, borders[3] ? borderWidth : 0, borders[1] ? borderWidth : 0);
        view.setBackgroundDrawable(background);
        view.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
    }

}
