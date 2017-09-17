package com.example.productexpo.modules.base.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.productexpo.modules.base.activity.BaseActivity;
import com.example.productexpo.utils.UIUtils;

/**
 * Parent of All Fragments. Note:This is a fully Customizable class.
 * You can override the methods of this class to tweak implementations according to your own need by overriding the initViewObjects class
 */
public abstract class BaseFragment extends Fragment implements BaseFragmentView {

    /**
     * context for the fragment
     */
    private Context context;

    public BaseFragment() {
        //required empty constructor
    }

    @Override
    public void onAttach(Activity context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    /**
     * If you need to implement your own logic in onCreateView method then you will not call super.OnCreateView.<br>
     * Simply inflate your view and return the view. <p><b>Note: bindControls, bindValues, setClickListener, bindAdapters
     * methods must be called manually and in your own sequence</b></p><p>
     * Original Doc -<br> {@inheritDoc}
     * </p>
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        @LayoutRes int resId = getResId();
        if (resId == -1 || resId == 0) {
            return null;
        }
        View rootLayout = inflater.inflate(resId, container, false);
        initializeUIComponents(rootLayout);
        return rootLayout;
    }

    /**
     * This is used to check if current activity is null or finishing
     *
     * @return true if finishing/null else false;
     */
    @Override
    public boolean isActivityFinishing() {
        return context != null && ((Activity) context).isFinishing();
    }

    @Override
    public Context getViewContext() {
        return this.context;
    }

    @Override
    public void showProgress(String message) {
        ((BaseActivity) this.context).showProgress(message);
    }

    @Override
    public void hideProgress() {
        try {
            ((BaseActivity) this.context).hideProgress();
        } catch (Exception ex) {
            try {
                UIUtils.dismissLoader();
            } catch (Exception ex1) {
            }
        }
    }

    @Override
    public void showYesNoDialog(String title, String message,
                                String positiveButtonText, String negativeButtonText,
                                DialogInterface.OnClickListener positiveButtonClickListener,
                                DialogInterface.OnClickListener negativeButtonClickListener) {
        try {
            ((BaseActivity) this.context).showYesNoDialog(title, message, positiveButtonText, negativeButtonText, positiveButtonClickListener, negativeButtonClickListener);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void showOKDialog(String title, String message, String positiveButtonText, DialogInterface.OnClickListener onClickListener) {
        try {
            ((BaseActivity) this.context).showOKDialog(title, message, positiveButtonText, null);
        } catch (Exception ex) {

        }
    }

    @Override
    public FragmentManager getChildManagerForFragment() {
        return getChildFragmentManager();
    }
}