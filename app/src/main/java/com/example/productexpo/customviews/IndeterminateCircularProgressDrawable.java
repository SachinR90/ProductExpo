package com.example.productexpo.customviews;

import android.animation.Animator;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.util.Property;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

public class IndeterminateCircularProgressDrawable extends Drawable implements Animatable {

    private static final Interpolator ANGLE_INTERPOLATOR      = new LinearInterpolator();
    private static final Interpolator SWEEP_INTERPOLATOR      = new FastOutSlowInInterpolator();
    private static final int          ANGLE_ANIMATOR_DURATION = 1600;
    private static final int          SWEEP_ANIMATOR_DURATION = 800;
    private static final int          MIN_SWEEP_ANGLE         = 40;
    private static final int          MAX_SIZE                = 40;

    private final RectF fBounds = new RectF();

    private int     colorIndex        = 0;
    private boolean avoidMaxSizeParam = false;

    private ObjectAnimator mObjectAnimatorSweep;
    private ObjectAnimator mObjectAnimatorAngle;
    private boolean        mModeAppearing;
    private Paint          circlePaint;
    private Paint          strokePaint;
    private float          mCurrentGlobalAngleOffset;
    private float          mCurrentGlobalAngle;
    private float          mCurrentSweepAngle;
    private float mBorderWidth = 1.0f;
    private boolean mRunning;
    private Context mContext;
    private int[]   mColors;
    private int     currentColor;

    private Property<IndeterminateCircularProgressDrawable, Float> mAngleProperty = new Property<IndeterminateCircularProgressDrawable, Float>(Float.class, "angle") {
        @Override
        public Float get(IndeterminateCircularProgressDrawable object) {
            return object.getCurrentGlobalAngle();
        }

        @Override
        public void set(IndeterminateCircularProgressDrawable object,
                        Float value) {
            object.setCurrentGlobalAngle(value);
        }
    };

    private Property<IndeterminateCircularProgressDrawable, Float> mSweepProperty = new Property<IndeterminateCircularProgressDrawable, Float>(Float.class, "arc") {
        @Override
        public Float get(IndeterminateCircularProgressDrawable object) {
            return object.getCurrentSweepAngle();
        }

        @Override
        public void set(IndeterminateCircularProgressDrawable object,
                        Float value) {
            object.setCurrentSweepAngle(value);
        }
    };

    /**
     * The only difference with other constructor is the avoidMaxSizeParam
     *
     * @param context     current context of drawable
     * @param borderWidth width of the drawable border in dp
     * @param colors      csv of colors
     */
    public IndeterminateCircularProgressDrawable(Context context, float borderWidth, int... colors) {
        init(context, borderWidth, colors);
    }

    /**
     * This method specifies whether the drawable should be of the exact view size or not<br>
     * This should be at the time of initiating so that circle progress can take exact size of
     * the view
     *
     * @param context           current context of drawable
     * @param borderWidth       width of the drawable border in dp
     * @param avoidMaxSizeParam used to avoid the max size of the view. Note: the border may go beyond the view bounds
     * @param colors            csv of colors
     */
    public IndeterminateCircularProgressDrawable(Context context, float borderWidth, boolean avoidMaxSizeParam, int... colors) {
        this.avoidMaxSizeParam = avoidMaxSizeParam;
        init(context, borderWidth, colors);
    }

    private void init(Context context, float borderWidth, int[] colors) {
        this.mContext = context;
        mBorderWidth = pxFromDp(borderWidth);
        mColors = colors;
        circlePaint = new Paint();
        circlePaint.setAntiAlias(true);
        circlePaint.setStyle(Paint.Style.STROKE);
        circlePaint.setStrokeWidth(borderWidth);
        currentColor = mColors[colorIndex];
        circlePaint.setColor(currentColor);
        circlePaint.setStrokeCap(Paint.Cap.ROUND);
        strokePaint = new Paint(circlePaint);
        setupAnimations();
        start();
    }

    /**
     * Set the colors the progress spinner alternates between.
     *
     * @param mColors Array of integers describing the colors. Must be non-<code>null</code>.
     */
    public void setColors(int... mColors) {
        this.mColors = mColors;
    }

    public int[] setColorsFromResource(int... resIds) {
        Resources resources = getContext().getResources();
        int[] resColors = new int[resIds.length];
        int i = 0;
        for (int resource : resIds) {
            resColors[i] = resources.getColor(resource);
            i++;
        }
        this.mColors = resColors.clone();
        invalidateSelf();
        return mColors;
    }

    private void gotoNextColor() {
        int previousColor = currentColor;
        colorIndex = (colorIndex + 1) % (mColors.length);
        currentColor = mColors[colorIndex];

        ValueAnimator anim = new ValueAnimator();
        anim.setIntValues(previousColor, currentColor);
        anim.setEvaluator(new ArgbEvaluator());
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                strokePaint.setColor((Integer) valueAnimator.getAnimatedValue());
            }
        });
        anim.setDuration(300);
        anim.start();

        //        final float[] from = new float[3];
        //        final float[] to   = new float[3];
        //        final float[] hsv  = new float[3];
        //        Color.colorToHSV(previousColor, from);
        //        Color.colorToHSV(currentColor, to);

        //        ValueAnimator anim = ValueAnimator.ofFloat(0, 1);
        //        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
        //            @Override
        //            public void onAnimationUpdate(ValueAnimator animation) {
        //                // Transition along each axis of HSV (hue, saturation, value)
        //                hsv[0] = from[0] + (to[0] - from[0]) * animation.getAnimatedFraction();
        //                hsv[1] = from[1] + (to[1] - from[1]) * animation.getAnimatedFraction();
        //                hsv[2] = from[2] + (to[2] - from[2]) * animation.getAnimatedFraction();
        //                strokePaint.setColor(Color.HSVToColor(hsv));
        //            }
        //        });
        //        anim.setDuration(300);
        //        anim.start();
    }

    private Context getContext() {
        return mContext;
    }

    @Override
    public void draw(Canvas canvas) {
        float startAngle = mCurrentGlobalAngle - mCurrentGlobalAngleOffset;
        float sweepAngle = mCurrentSweepAngle;
        if (!mModeAppearing) {
            startAngle = startAngle + sweepAngle;
            sweepAngle = 360 - sweepAngle - MIN_SWEEP_ANGLE - (MIN_SWEEP_ANGLE / 2);
        } else {
            sweepAngle += MIN_SWEEP_ANGLE - (MIN_SWEEP_ANGLE / 2);
        }
        circlePaint.setColor(Color.GRAY);
        circlePaint.setStrokeWidth(1f);
        canvas.drawArc(fBounds, 0, 360, false, circlePaint);
        strokePaint.setStrokeWidth(mBorderWidth);
        canvas.drawArc(fBounds, startAngle, sweepAngle, false, strokePaint);
    }

    @Override
    public void setAlpha(int alpha) {
        circlePaint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(ColorFilter cf) {
        circlePaint.setColorFilter(cf);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSPARENT;
    }

    ///////////////////////////////////Animation///////////////////////////////////////////

    private void toggleAppearingMode() {
        mModeAppearing = !mModeAppearing;
        if (mModeAppearing) {
            mCurrentGlobalAngleOffset = (mCurrentGlobalAngleOffset + MIN_SWEEP_ANGLE * 2) % 360;
            gotoNextColor();
        }
    }

    private float pxFromDp(float dp) {
        float scale = getContext().getResources().getDisplayMetrics().density;
        return (dp * scale + 0.5f);
    }

    private float dpFromPx(float px) {
        float scale = getContext().getResources().getDisplayMetrics().density;
        return (px / scale + 0.5f);
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        RectF mRect = getRectF(bounds);
        setFBounds(mRect);
    }

    private void setFBounds(RectF mRect) {
        fBounds.left = mRect.left + mBorderWidth / 2f + .5f;
        fBounds.right = mRect.right - mBorderWidth / 2f - .5f;
        fBounds.top = mRect.top + mBorderWidth / 2f + .5f;
        fBounds.bottom = mRect.bottom - mBorderWidth / 2f - .5f;
        invalidateSelf();
    }

    /**
     * This method is used to set custom bounds for the circular progress drawable
     */
    public void setProgressBounds(RectF mRect) {
        float minBoundSize = 0;
        float mSize = 0;
        if (mRect.right <= mRect.bottom) {
            minBoundSize = mRect.right;
        } else {
            minBoundSize = mRect.bottom;
        }
        mSize = minBoundSize;
        mBorderWidth = mSize / 6f;
        if (mBorderWidth <= 5f) {
            mBorderWidth = 5f;
        }

        setFBounds(mRect);
    }

    private RectF getRectF(Rect bounds) {
        RectF mRect = new RectF(bounds);
        float maxSize = pxFromDp(MAX_SIZE);
        float mSize = 0;
        float minBoundSize = 0;
        if (bounds.right <= bounds.bottom) {
            minBoundSize = bounds.right;
        } else {
            minBoundSize = bounds.bottom;
        }
        if (minBoundSize >= maxSize && !avoidMaxSizeParam) {
            mSize = maxSize;
        } else {
            mSize = minBoundSize;
            mBorderWidth = mSize / 6f;
            if (mBorderWidth <= 5f) {
                mBorderWidth = 5f;
            }
        }
        mRect.left = bounds.centerX() - (mSize / 2);
        mRect.top = bounds.centerY() - (mSize / 2);
        mRect.right = bounds.centerX() + (mSize / 2);
        mRect.bottom = bounds.centerY() + (mSize / 2);
        return mRect;
    }

    private void setupAnimations() {
        mObjectAnimatorAngle = ObjectAnimator.ofFloat(this, mAngleProperty, 360f);
        mObjectAnimatorAngle.setInterpolator(ANGLE_INTERPOLATOR);
        mObjectAnimatorAngle.setDuration(ANGLE_ANIMATOR_DURATION);
        mObjectAnimatorAngle.setRepeatMode(ValueAnimator.RESTART);
        mObjectAnimatorAngle.setRepeatCount(ValueAnimator.INFINITE);

        mObjectAnimatorSweep = ObjectAnimator.ofFloat(this, mSweepProperty, 360f - MIN_SWEEP_ANGLE * 2);
        mObjectAnimatorSweep.setInterpolator(SWEEP_INTERPOLATOR);
        mObjectAnimatorSweep.setDuration(SWEEP_ANIMATOR_DURATION);
        mObjectAnimatorSweep.setRepeatMode(ValueAnimator.RESTART);
        mObjectAnimatorSweep.setRepeatCount(ValueAnimator.INFINITE);

        mObjectAnimatorSweep.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                toggleAppearingMode();
            }
        });
    }

    @Override
    public void start() {
        if (isRunning()) {
            return;
        }
        mRunning = true;
        mObjectAnimatorAngle.start();
        mObjectAnimatorSweep.start();
        invalidateSelf();
    }

    @Override
    public void stop() {
        if (!isRunning()) {
            return;
        }
        mRunning = false;
        mObjectAnimatorAngle.cancel();
        mObjectAnimatorSweep.cancel();
        invalidateSelf();
    }

    @Override
    public boolean isRunning() {
        return mRunning;
    }

    public float getCurrentGlobalAngle() {
        return mCurrentGlobalAngle;
    }

    public void setCurrentGlobalAngle(float currentGlobalAngle) {
        mCurrentGlobalAngle = currentGlobalAngle;
        invalidateSelf();
    }

    public float getCurrentSweepAngle() {
        return mCurrentSweepAngle;
    }

    public void setCurrentSweepAngle(float currentSweepAngle) {
        mCurrentSweepAngle = currentSweepAngle;
        invalidateSelf();
    }
}
