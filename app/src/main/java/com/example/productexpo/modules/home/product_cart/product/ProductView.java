package com.example.productexpo.modules.home.product_cart.product;

import com.example.productexpo.callbacks.ProductCartCallback;

/**
 * Created on 9/17/2017.
 */

public interface ProductView extends ProductCartCallback {
    /**
     * tell the parent view  to refresh the product list
     */
    void refreshList();

}
