package com.example.productexpo.customviews;

import android.content.Context;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.example.productexpo.utils.UIUtils;

/**
 * onMeasure() is your opportunity to tell Android how big you want your custom view to be dependent the layout constraints provided by the parent; it is also your custom view's opportunity to learn what those layout constraints are (in case you want to behave differently in a match_parent situation than a wrap_content situation). These constraints are packaged up into the MeasureSpec values that are passed into the method. Here is a rough correlation of the mode values:
 * <p>
 * <li>EXACTLY means the layout_width or layout_height value was set to a specific value. You should probably make your view this size. This can also get triggered when match_parent is used, to set the size exactly to the parent view (this is layout dependent in the framework).</li>
 * <li>AT_MOST typically means the layout_width or layout_height value was set to match_parent or wrap_content where a maximum size is needed (this is layout dependent in the framework), and the size of the parent dimension is the value. You should not be any larger than this size.</li>
 * <li>UNSPECIFIED typically means the layout_width or layout_height value was set to wrap_content with no restrictions. You can be whatever size you would like. Some layouts also use this callback to figure out your desired size before determine what specs to actually pass you again in a second measure request.</li>
 * <br>The contract that exists with onMeasure() is that setMeasuredDimension() MUST be called at the end with the size you would like the view to be. This method is called by all the framework implementations, including the default implementation found in View, which is why it is safe to call super instead if that fits your use case.
 * <p>
 * Granted, because the framework does apply a default implementation, it may not be necessary for you to override this method, but you may see clipping in cases where the view space is smaller than your content if you do not, and if you lay out your custom view with wrap_content in both directions, your view may not show up at all because the framework doesn't know how large it is!
 * <br> Created on 9/17/2017.
 */

public class CustomFrameLayout extends FrameLayout {
    private int deviceWidth;
    private int deviceHeight;
    private Context context;

    public CustomFrameLayout(Context context) {
        this(context, null);
    }

    public CustomFrameLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
        this.context = context;
    }

    public void init(@NonNull Context context, @Nullable AttributeSet attrs) {
        deviceWidth = context.getResources().getDisplayMetrics().widthPixels;
        deviceHeight = context.getResources().getDisplayMetrics().heightPixels;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (this.context == null) {
            return;
        }
        int desiredWidth = (int) (deviceWidth / 2f);
        if (!UIUtils.isTablet(context) && UIUtils.getDeviceOrientation(context) == Configuration.ORIENTATION_LANDSCAPE) {
            desiredWidth = (int) (deviceWidth / 3f);
        }
        int desiredHeight = (int) (deviceHeight / 3f);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width;
        int height;

        //Measure Width
        if (widthMode == MeasureSpec.EXACTLY) {
            //Must be this size
            width = widthSize;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            //Can't be bigger than...
            width = Math.min(desiredWidth, widthSize);
        } else {
            //Be whatever you want
            width = desiredWidth;
        }

        //Measure Height
        if (heightMode == MeasureSpec.EXACTLY) {
            //Must be this size
            height = heightSize;
        } else if (heightMode == MeasureSpec.AT_MOST) {
            //Can't be bigger than...
            height = Math.min(desiredHeight, heightSize);
        } else {
            //Be whatever you want
            height = desiredHeight;
        }

        //MUST CALL THIS
//        setMeasuredDimension(width, height);
        super.onMeasure(MeasureSpec.makeMeasureSpec(width, widthMode), MeasureSpec.makeMeasureSpec((height), heightMode));

    }
}
