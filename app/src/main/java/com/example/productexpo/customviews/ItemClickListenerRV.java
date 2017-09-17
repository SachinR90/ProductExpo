package com.example.productexpo.customviews;

import android.os.Bundle;
import android.view.View;

/**
 * Created on 7/21/2017.
 */
public interface ItemClickListenerRV<T> {
    /**
     * return following when an item is clicked
     *
     * @param v      view which was clicked
     * @param pos    position of the view in the list
     * @param data   payload to return
     * @param bundle key-value bundle to return
     */
    void onItemClick(View v, int pos, T data, Bundle bundle);
}
