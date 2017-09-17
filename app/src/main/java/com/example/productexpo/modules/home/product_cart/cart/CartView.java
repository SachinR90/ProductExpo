package com.example.productexpo.modules.home.product_cart.cart;

import com.example.productexpo.modules.home.product_cart.ProductCartCallback;

/**
 * Created on 9/17/2017.
 */

public interface CartView extends ProductCartCallback {
    void switchToProductScreen();

    void callVendor(String phoneNumber);
}
