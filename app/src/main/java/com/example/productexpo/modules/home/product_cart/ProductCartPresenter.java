package com.example.productexpo.modules.home.product_cart;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.example.productexpo.entities.Product;
import com.example.productexpo.entities.ProductResponse;
import com.example.productexpo.modules.base.BasePresenter;

import java.util.List;

/**
 * Created on 9/17/2017.
 */

public interface ProductCartPresenter extends BasePresenter {
    /**
     * Method to set reference to View Pager
     *
     * @param viewPager to be handled by presenter
     */
    void handleViewPager(ViewPager viewPager);

    /**
     * Method to handle the TabsLayout
     *
     * @param tabLayout to be handled by the presenter
     */
    void handleTabLayout(TabLayout tabLayout);

    /**
     * perform selections on tab to select the Products tab
     */
    void selectProductTab();

    /**
     * fetch products from webservice
     */
    void fetchProducts();

    /**
     * result received from webservice
     *
     * @param productResponse contains list of products
     */
    void onGetProductsReceived(ProductResponse productResponse);

    /**
     * fetch cart products from shared preference
     */
    void fetchCartProducts();

    /**
     * cart products received
     *
     * @param products
     */
    void onCartProductReceived(List<Product> products);
}
