package com.brentpanther.borderedviews;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.TextView;

public class BorderedTextView extends TextView {
	
	private Borders borders;

	public BorderedTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		borders = new Borders(this, context, attrs);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		borders.onViewDraw(this, canvas);
		super.onDraw(canvas);
	}

}
