package com.example.productexpo.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;

import com.example.productexpo.R;
import com.example.productexpo.application.ProductExpoApp;
import com.leo.simplearcloader.ArcConfiguration;
import com.leo.simplearcloader.SimpleArcDialog;
import com.leo.simplearcloader.SimpleArcLoader;

/**
 * Common utilities as required by the UI
 */
public class UIUtils {
    public static SimpleArcDialog pgLoader;
    public static Handler removeLoader;
    public static Runnable rRemoveLoader;

    /**
     * Convert DPs to pixels
     *
     * @param dp - to be converted to the pixels
     * @return pixels
     */
    public static float dpToPx(float dp) {
        return dp * Resources.getSystem().getDisplayMetrics().density;
    }

    /**
     * Converts Pixels to Dp
     *
     * @param px - to be converted to dp
     * @return dp
     */
    public static float pxToDp(float px) {
        return px / Resources.getSystem().getDisplayMetrics().density;
    }

    /**
     * This function shows a Progress (Loading) dialog to User.
     *
     * @param strMessage -The Message needed to be displayed to the User on the Loading Dialog.
     */
    public static void showLoader(final String strMessage, final Context currentContext) {
        try {
            if (removeLoader != null) {
                removeLoader.removeCallbacks(rRemoveLoader);
            }
            try {
                if (pgLoader != null && pgLoader.isShowing()) pgLoader.dismiss();
                pgLoader = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
            removeLoader = new Handler();
            rRemoveLoader = new Runnable() {
                @Override
                public void run() {
                    try {
                        if (pgLoader != null && pgLoader.isShowing())
                            pgLoader.dismiss();
                        pgLoader = null;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            removeLoader.postDelayed(rRemoveLoader, ProductExpoApp.MAX_REQUEST_TIMEOUT_MS);
            int[] colorArray =
                    {
                            ContextCompat.getColor(currentContext, R.color.colorPrimary), ContextCompat.getColor(currentContext, R.color.colorAccent)
                    };

            ArcConfiguration configuration = new ArcConfiguration(currentContext);
            configuration.setLoaderStyle(SimpleArcLoader.STYLE.COMPLETE_ARC);
            configuration.setText(strMessage);
            configuration.setTextColor(ContextCompat.getColor(currentContext, android.R.color.black));
            configuration.setColors(colorArray);
            configuration.setAnimationSpeedWithIndex(SimpleArcLoader.SPEED_FAST);

            // Create New Loader
            pgLoader = new SimpleArcDialog(currentContext);
            pgLoader.setConfiguration(configuration);
            pgLoader.setCancelable(false);
            pgLoader.show();
        } catch (Exception e) {
            if (pgLoader != null) {
                if (pgLoader.isShowing())
                    pgLoader.dismiss();
            }
            e.printStackTrace();
        }
    }

    /**
     * This function dismiss the current loading dialog present on the screen
     */
    public static void dismissLoader() {
        try {
            if (pgLoader != null && pgLoader.isShowing()) {
                pgLoader.dismiss();
            }
            pgLoader = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Template for showing the user the alert dialog with positive and negative buttons only
     *
     * @param context                     where you want to show the dialog
     * @param title                       of the dialog
     * @param message                     of the dialog
     * @param positiveButtonText          of positive button
     * @param negativeButton              of negative button
     * @param positiveButtonClickListener for positive button
     * @param negativeButtonClickListener for negative button
     */
    public static void showCancelableAlert(Context context, String title, String message,
                                           String positiveButtonText, String negativeButton,
                                           DialogInterface.OnClickListener positiveButtonClickListener,
                                           DialogInterface.OnClickListener negativeButtonClickListener) {
        AlertDialog.Builder builder;
        //        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        //            builder = new AlertDialog.Builder(context, android.R.style.Theme_Material_Dialog_Alert);
        //        } else {
        //            builder = new AlertDialog.Builder(context, AlertDialog.THEME_DEVICE_DEFAULT_DARK);
        //        }
        builder = new AlertDialog.Builder(context);
        if (!TextUtils.isEmpty(title)) {
            builder.setTitle(title);
        }
        builder.setCancelable(false);
        builder.setMessage(message);
        if (positiveButtonClickListener == null) {
            positiveButtonClickListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            };
        }
        builder.setPositiveButton(TextUtils.isEmpty(positiveButtonText) ? "Ok" : positiveButtonText, positiveButtonClickListener);
        String negative = TextUtils.isEmpty(negativeButton) ? "Cancel" : negativeButton;
        if (negativeButtonClickListener == null) {
            negativeButtonClickListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            };
        }
        builder.setNegativeButton(negative, negativeButtonClickListener);
        builder.create().show();
    }

    /**
     * Template for showing the user the alert dialog with OK button only
     *
     * @param context            where you want to show the dialog
     * @param title              of the dialog
     * @param message            of the dialog
     * @param positiveButtonText
     * @param okClickListener
     */
    public static void showOKAlert(Context context, String title, String message, String positiveButtonText, DialogInterface.OnClickListener okClickListener) {
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(context);
        if (!TextUtils.isEmpty(title)) {
            builder.setTitle(title);
        }
        builder.setCancelable(true);
        builder.setMessage(message);
        if (okClickListener == null) {
            okClickListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            };
        }
        if (TextUtils.isEmpty(positiveButtonText)) {
            positiveButtonText = context.getString(R.string.str_ok);
        }
        builder.setPositiveButton(positiveButtonText, okClickListener);
        builder.create().show();
    }
}
