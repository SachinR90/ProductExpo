package com.example.productexpo.modules.base.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.productexpo.R;
import com.example.productexpo.utils.UIUtils;

public abstract class BaseActivity extends AppCompatActivity implements BaseActivityView {
    int iAnimationCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        iAnimationCount++;
        enterAnimation(savedInstanceState);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        initializeUIComponent();
    }

    @Override
    public Context getViewContext() {
        return this;
    }

    @Override
    protected void onStart() {
        super.onStart();
        exitAnimation();
    }

    @Override
    public void showProgress(String message) {
        UIUtils.showLoader(message, this);
    }

    @Override
    public void hideProgress() {
        try {
            UIUtils.dismissLoader();
        } catch (Exception ignored) {
        }
    }

    @Override
    public void showYesNoDialog(String title, String message,
                                String positiveButtonText, String negativeButtonText,
                                DialogInterface.OnClickListener positiveButtonClickListener,
                                DialogInterface.OnClickListener negativeButtonClickListener) {
        UIUtils.showCancelableAlert(this, title, message, positiveButtonText, negativeButtonText, positiveButtonClickListener, negativeButtonClickListener);
    }

    @Override
    public void showOKDialog(String title, String message, String positiveButtonText, DialogInterface.OnClickListener onClickListener) {
        UIUtils.showOKAlert(this, title, message, positiveButtonText, onClickListener);
    }

    @Override
    public FragmentManager getManagerForFragment() {
        return getSupportFragmentManager();
    }

    public void enterAnimation(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            this.overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left);
        } else {
            // Enter Animation is already performed.
            iAnimationCount = 2;
        }
    }

    public void exitAnimation() {
        if (iAnimationCount == 2) {
            this.overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right);
        } else if (iAnimationCount == 1) {
            iAnimationCount++;
        }
    }
}
