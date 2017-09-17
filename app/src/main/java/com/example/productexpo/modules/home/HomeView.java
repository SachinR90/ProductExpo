package com.example.productexpo.modules.home;

import com.example.productexpo.entities.Product;
import com.example.productexpo.modules.base.activity.BaseActivityView;

/**
 * Created on 9/17/2017.
 */

public interface HomeView extends BaseActivityView {

    /**
     * load the product to show it int product details screen
     *
     * @param product to be displayed
     */
    void loadProductDetails(Product product);


}
