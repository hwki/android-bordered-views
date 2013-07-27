package com.brentpanther.borderedviews;

public interface Bordered {

	void setBorders(boolean left, boolean top, boolean right, boolean bottom);

	void setBorderColor(int borderColor);

	void setBorderWidth(int borderWidth);

	void setRadii(float topLeft, float topRight, float bottomRight, float bottomLeft);

}
