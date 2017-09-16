package com.example.productexpo.customviews;

import android.os.SystemClock;
import android.support.animation.DynamicAnimation;
import android.support.animation.SpringAnimation;
import android.support.animation.SpringForce;
import android.view.MotionEvent;
import android.view.View;

import java.lang.ref.SoftReference;

public class CustomOnTouchListener implements View.OnTouchListener {
    private static final float INITIAL_SCALE = 1f;
    private long mLastClickTime;
    private SpringAnimation animationX;
    private SpringAnimation animationY;
    private AnimationEndListener mEndListener;

    public CustomOnTouchListener() {

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            //check if system elapsed (milliseconds - lastClick) time > LAST_CLICK_TIME
            if (SystemClock.elapsedRealtime() - mLastClickTime > 500) {//save last click time
                if (animationX == null) {
                    animationX = createAnimation(v, SpringAnimation.SCALE_X);
                }
                if (animationY == null) {
                    animationY = createAnimation(v, SpringAnimation.SCALE_Y);
                    if (mEndListener == null) {
                        mEndListener = new AnimationEndListener(v);
                    }
                }
                mLastClickTime = SystemClock.elapsedRealtime();
                if (animationX != null && animationY != null) {
                    mEndListener.addToSoftReference(v);
                    animationX.cancel();
                    animationY.cancel();
                    v.setScaleX(0.925f);
                    v.setScaleY(0.925f);
                    animationX.start();
                    animationY.start();
                    animationY.addEndListener(mEndListener);
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Create a spring animation based on the property provided
     *
     * @param v        the view on which the animation will be applied
     * @param property property of the view for the animation will run
     * @return Spring animation object
     */
    private SpringAnimation createAnimation(View v, DynamicAnimation.ViewProperty property) {
        final SpringAnimation animation = new SpringAnimation(v, property);
        SpringForce springForce = new SpringForce(INITIAL_SCALE);
        springForce.setStiffness(SpringForce.STIFFNESS_MEDIUM);
        springForce.setDampingRatio(SpringForce.DAMPING_RATIO_MEDIUM_BOUNCY);
        animation.setSpring(springForce);
        return animation;
    }

    /**
     * this class listens for ending of Spring animation. as soon a the animation is end,
     * <br>It calls the onClick listener of the view
     */
    private class AnimationEndListener implements DynamicAnimation.OnAnimationEndListener {
        /**
         * create a soft reference so that the view can be garbage collected
         */
        SoftReference<View> weakView;

        AnimationEndListener(View v) {
            weakView = new SoftReference<>(v);
        }

        /**
         * Hold soft-reference to the view
         *
         * @param v for which a soft reference will be held
         */
        private void addToSoftReference(View v) {
            if (weakView == null) {
                weakView = new SoftReference<>(v);
            } else {
                View view = weakView.get();
                if (view == null) {
                    weakView = new SoftReference<>(v);
                }
            }
        }

        @Override
        public void onAnimationEnd(DynamicAnimation animation, boolean canceled, float value, float velocity) {
            View view = weakView.get();
            if (view != null) {
                //call onclick listener
                view.callOnClick();
            }
        }
    }
}
