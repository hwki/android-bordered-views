package com.brentpanther.borderedviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;

public class Borders {
	
	private int borderWidth;
	private int borderColor;
	private int border;
	private float[] radii;
	private Paint paint;
	
	public Borders() {
		paint = new Paint();
	}	

	public Borders(Context context, AttributeSet attrs) {
		TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.Borders, 0, 0);
		radii = new float[4];
		try {
			if(a.hasValue(R.styleable.Borders_borderRadius)) {
				int radius = a.getInt(R.styleable.Borders_borderRadius, 0);
				radii = new float[] {radius, radius, radius, radius};
			} else {
				radii[0] = a.getInt(R.styleable.Borders_radiusTopLeft, 0);
				radii[1] = a.getInt(R.styleable.Borders_radiusTopRight, 0);
				radii[2] = a.getInt(R.styleable.Borders_radiusBottomRight, 0);
				radii[3] = a.getInt(R.styleable.Borders_radiusBottomLeft, 0);
			}
			borderWidth = a.getDimensionPixelSize(R.styleable.Borders_borderWidth, 1);
			borderColor = a.getColor(R.styleable.Borders_borderColor, Color.BLACK);
			border = a.getInteger(R.styleable.Borders_borders, 15);
			paint = new Paint();
		} finally {
			a.recycle();
		}
	}
	
	public void onViewDraw(View view, Canvas canvas) {
		canvas.restore();
		int width = view.getMeasuredWidth();
		int height = view.getMeasuredHeight();
		int d = borderWidth / 2;
		Path path = new Path();
		paint.setStrokeWidth(borderWidth);
		paint.setAntiAlias(true);
		paint.setColor(borderColor);
		paint.setStyle(Paint.Style.STROKE);
		float[] r = new float[]{radii[0], radii[0], radii[1], radii[1], radii[2], radii[2], radii[3], radii[3]};
		RectF rectangle = new RectF(0+d, 0+d, width-d, height-d);
		if(!checkBit(0)) rectangle.top -= borderWidth;
		if(!checkBit(1)) rectangle.bottom += borderWidth;
		if(!checkBit(2)) rectangle.left -= borderWidth;
		if(!checkBit(3)) rectangle.right += borderWidth;
		path = new Path();
		path.addRoundRect(rectangle, r, Direction.CW);
		canvas.drawPath(path, paint);
	}
	
	private int getTopBorder() {
		return checkBitInt(0) * borderWidth;
	}
	
	private int getBottomBorder() {
		return checkBitInt(1) * borderWidth;
	}
	
	private int getLeftBorder() {
		return checkBitInt(2) * borderWidth;
	}
	
	private int getRightBorder() {
		return checkBitInt(3) * borderWidth;
	}
	
	private boolean checkBit(int x) {
	    return (border & 1 << x) != 0;
	}
	
	private int checkBitInt(int x) {
		return checkBit(x) ? 1 : 0;
	}
	
	public void setBorders(boolean left, boolean top, boolean right, boolean bottom) {
		int b = 0;
		if(left) b += 4;
		if(top) b += 1;
		if(right) b+= 8;
		if(bottom) b+= 2;
		border = b;
	}
	
	public void setBorderColor(int borderColor) {
		this.borderColor = borderColor;
	}
	
	public int getBorderColor() {
		return borderColor;
	}
	
	public void setBorderWidth(int borderWidth) {
		this.borderWidth = borderWidth;
	}
	
	public int getBorderWidth() {
		return borderWidth;
	}
	
	public void setRadii(float topLeft, float topRight, float bottomRight, float bottomLeft) {
		radii = new float[] {topLeft, topRight, bottomRight, bottomLeft};
	}
	
	public float[] getRadii() {
		return radii;
	}

	public void draw(View view, Canvas canvas) {
		RectF rectangle = new RectF(
				getLeftBorder(),
				getTopBorder(), 
				view.getMeasuredWidth()-getRightBorder(),
				view.getMeasuredHeight()-getBottomBorder());
		canvas.save();
		canvas.clipRect(rectangle);
	}

	public int getMeasuredWidth(View view, int measureSpec) {
		int requestedWidth = view.getMeasuredWidth();
		if(MeasureSpec.getMode(measureSpec) != MeasureSpec.EXACTLY) {
			requestedWidth += getLeftBorder() + getRightBorder();
		}
		return requestedWidth;
	}

	public int getMeasuredHeight(View view, int heightMeasureSpec) {
		int requestedHeight = view.getMeasuredHeight();
		if(MeasureSpec.getMode(heightMeasureSpec) != MeasureSpec.EXACTLY) {
			requestedHeight += getTopBorder() + getBottomBorder();
		}
		return requestedHeight;
	}

	public void translate(Canvas canvas) {
		canvas.translate(getLeftBorder(), getTopBorder());
	}

}
