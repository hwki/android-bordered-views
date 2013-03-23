package com.brentpanther.borderedviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class Borders {

	private int borderWidth;
	private int borderColor;
	private int border;
	private float[] radii;
	private Paint paint;

	Borders() {
		paint = new Paint();
	}

	Borders(Context context, AttributeSet attrs) {
		this();
		TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.Borders, 0, 0);
		radii = new float[4];
		try {
			if (a.hasValue(R.styleable.Borders_borderRadius)) {
				int radius = a.getDimensionPixelSize(R.styleable.Borders_borderRadius, 0);
				radii = new float[] { radius, radius, radius, radius };
			} else {
				radii[0] = a.getDimensionPixelSize(R.styleable.Borders_radiusTopLeft, 0);
				radii[1] = a.getDimensionPixelSize(R.styleable.Borders_radiusTopRight, 0);
				radii[2] = a.getDimensionPixelSize(R.styleable.Borders_radiusBottomRight, 0);
				radii[3] = a.getDimensionPixelSize(R.styleable.Borders_radiusBottomLeft, 0);
			}
			borderWidth = a.getDimensionPixelSize(R.styleable.Borders_borderWidth, 1);
			borderColor = a.getColor(R.styleable.Borders_borderColor, Color.BLACK);
			border = a.getInteger(R.styleable.Borders_borders, 15);
		} finally {
			a.recycle();
		}
	}

	void draw(View view, Canvas canvas) {
		int width = view.getMeasuredWidth();
		int height = view.getMeasuredHeight();
		int mid = borderWidth / 2;
		Path path = new Path();
		paint.setStrokeWidth(borderWidth);
		paint.setAntiAlias(true);
		paint.setColor(borderColor);
		paint.setStyle(Paint.Style.STROKE);
		float[] r = new float[] { radii[0], radii[0], radii[1], radii[1], radii[2], radii[2], radii[3], radii[3] };
		RectF rectangle = new RectF(getBorderLeft()-mid, getBorderTop()-mid, width + mid - getBorderRight(), height + mid - getBorderBottom());
		path.addRoundRect(rectangle, r, Direction.CW);
		canvas.drawPath(path, paint);
	}
	
	void drawBackground(View view, Canvas canvas, Bitmap other) {
		int width = view.getMeasuredWidth();
		int height = view.getMeasuredHeight();
		int mid = borderWidth / 2;
		Bitmap output = Bitmap.createBitmap(width, height, Config.ARGB_8888);
		Canvas tempCanvas = new Canvas(output);
		Rect backgroundRect = new Rect(0, 0, width, height);
		Path path = new Path();
		paint.setStrokeWidth(borderWidth);
		paint.setAntiAlias(true);
		paint.setColor(0xff424242);
		paint.setStyle(Style.FILL);
		float[] r = new float[] { radii[0], radii[0], radii[1], radii[1], radii[2], radii[2], radii[3], radii[3] };
		RectF rectangle = new RectF(getBorderLeft()-mid, getBorderTop()-mid, width + mid - getBorderRight(), height + mid - getBorderBottom());
		path.addRoundRect(rectangle, r, Direction.CW);
		tempCanvas.drawARGB(0, 0, 0, 0);
		tempCanvas.drawPath(path, paint);
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
	    tempCanvas.drawBitmap(other, backgroundRect, backgroundRect, paint);
	    canvas.drawARGB(0, 0, 0, 0);
	    paint = new Paint();
	    canvas.drawBitmap(output, 0, 0, paint);
	}

	int getBorderTop() {
		return checkBit(0) * borderWidth;
	}

	int getBorderBottom() {
		return checkBit(1) * borderWidth;
	}

	int getBorderLeft() {
		return checkBit(2) * borderWidth;
	}

	int getBorderRight() {
		return checkBit(3) * borderWidth;
	}
	
	boolean hasBorders() {
		return border!=0;
	}

	private int checkBit(int x) {
		return (border & 1 << x) != 0 ? 1 : 0;
	}

	void setBorders(View view, boolean left, boolean top, boolean right, boolean bottom) {
		int paddingLeft = view.getPaddingLeft() - getBorderLeft();
		int paddingTop = view.getPaddingTop() - getBorderTop();
		int paddingRight = view.getPaddingRight() - getBorderRight();
		int paddingBottom = view.getPaddingBottom() - getBorderBottom();
		int b = 0;
		if (top) b += 1;
		if (left) b += 4;
		if (right) b += 8;
		if (bottom) b += 2;
		border = b;
		view.setPadding(paddingLeft + getBorderLeft(), paddingTop + getBorderTop(), paddingRight + getBorderRight(), paddingBottom + getBorderBottom());
		view.invalidate();
	}

	void setBorderColor(View view, int borderColor) {
		this.borderColor = borderColor;
		view.invalidate();
	}

	int getBorderColor() {
		return borderColor;
	}

	void setBorderWidth(View view, int borderWidth) {
		int borderLeft = getBorderLeft();
		int borderTop = getBorderTop();
		int borderRight = getBorderRight();
		int borderBottom = getBorderBottom();
		this.borderWidth = borderWidth;
		view.setPadding(view.getPaddingLeft()-borderLeft, view.getPaddingTop()-borderTop, view.getPaddingRight()-borderRight, view.getPaddingBottom()-borderBottom);
		view.invalidate();
	}

	int getBorderWidth() {
		return borderWidth;
	}

	void setRadii(View view, float topLeft, float topRight, float bottomRight, float bottomLeft) {
		radii = new float[] { topLeft, topRight, bottomRight, bottomLeft };
		view.invalidate();
	}

	float[] getRadii() {
		return radii;
	}

}
