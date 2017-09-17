package com.example.productexpo.modules.base;

import android.content.Context;
import android.content.DialogInterface;

/**
 * Created on 9/16/2017.
 */

public interface BaseView {
    /**
     * get Views Context
     *
     * @return
     */
    Context getViewContext();

    /**
     * Show Progress dialog
     *
     * @param message message to show on progress dialog
     */
    void showProgress(String message);

    void showToast(String message);

    /**
     * hide progress dialog
     */
    void hideProgress();

    /**
     * show Dialog with yes and no button
     *
     * @param title                       Title to show on the Alert Dialog. can be null/empty/blank to hide the title
     * @param message                     Message to be displayed in the alert dialog.
     * @param positiveButtonText          text on positive button click, can be blank/empty/null to show "OK" by default
     * @param negativeButtonText          text on negative button click, can be blank/empty/null to show "Cancel" by default
     * @param positiveButtonClickListener callback for positive button click. can be null. will dismiss by default and do nothing
     * @param negativeButtonClickListener callback for negative button click. can be null. will dismiss by default and do nothing
     */
    void showYesNoDialog(String title, String message,
                         String positiveButtonText, String negativeButtonText,
                         DialogInterface.OnClickListener positiveButtonClickListener,
                         DialogInterface.OnClickListener negativeButtonClickListener);

    /**
     * Show Alert Dialog with positive button only
     *
     * @param title              Title to show on the Alert Dialog. can be null/empty/blank to hide the title
     * @param message            Message to be displayed in the alert dialog.
     * @param positiveButtonText text on positive button click, can be blank/empty/null to show "OK" by default
     * @param onClickListener    callback for positive button click. can be null. will dismiss by default and do nothing
     */
    void showOKDialog(String title,
                      String message,
                      String positiveButtonText,
                      DialogInterface.OnClickListener onClickListener);
}
