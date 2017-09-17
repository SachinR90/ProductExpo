package com.example.productexpo.modules.product_details;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.productexpo.entities.Product;
import com.example.productexpo.utils.AppUtils;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * Created on 9/17/2017.
 */

public class ProductDetailsPresenterImpl implements ProductDetailsPresenter {
    ProductDetailsView detailsView;
    View emptyView;
    ViewPager pager;

    public ProductDetailsPresenterImpl(ProductDetailsView detailsView) {
        this.detailsView = detailsView;
    }

    @Override
    public void showErrorMessage(int errorCode, String message) {

    }

    @Override
    public void onConfigChanged(Configuration configuration) {

    }

    @Override
    public void restoreInstance(Bundle savedInstanceState) {

    }

    @Override
    public void saveInstance(Bundle outState) {

    }

    @Override
    public void updateViewPager(Product product) {
        ProductDetailPagerAdapter adapter;
        if (product == null || AppUtils.isCollectionEmpty(product.getProductGallery())) {
            adapter = new ProductDetailPagerAdapter(detailsView.getViewContext(), null);
            emptyView.setVisibility(VISIBLE);
        } else {
            emptyView.setVisibility(GONE);
            adapter = new ProductDetailPagerAdapter(detailsView.getViewContext(), product);
        }
        this.pager.setAdapter(adapter);
    }

    @Override
    public void handlerViewPager(ViewPager vpProductDetails, View emptyView) {
        this.pager = vpProductDetails;
        this.emptyView = emptyView;
    }
}
