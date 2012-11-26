android-bordered-views
======================

Custom views for Android that support borders (optionally rounded).

Screenshots
-----------

![BorderedTextView](https://raw.github.com/hwki/android-bordered-views/master/BorderedViews/screens/screen1.png)

![BorderedTextView rounded](https://raw.github.com/hwki/android-bordered-views/master/BorderedViews/screens/screen2.png)

Usage
-----

See attrs.xml for the complete list of attributes.

    <com.brentpanther.borderedviews.BorderedTextView
        xmlns:b="http://schemas.android.com/apk/res-auto"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:padding="5dp"
        android:text="This is sample text"
        b:borderWidth="2dp"
        b:borders="top|left|right"/>
		
	<com.brentpanther.borderedviews.BorderedTextView
        xmlns:b="http://schemas.android.com/apk/res-auto"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:padding="5dp"
        android:text="This is sample text"
        b:radiusTopLeft="15"
        b:radiusTopRight="15"
        b:borderWidth="2dp"
        b:borderColor="#ff0000"
        b:borders="all"/>
	