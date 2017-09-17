package com.example.productexpo.modules.home.product_cart.cart;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.productexpo.R;
import com.example.productexpo.customviews.EmptyRecyclerView;
import com.example.productexpo.entities.Product;
import com.example.productexpo.modules.base.fragment.BaseFragment;
import com.example.productexpo.modules.home.product_cart.ProductCartFragment;

import java.util.List;

/**
 * Created on 9/17/2017.
 */

public class CartFragment extends BaseFragment implements CartView {
    private EmptyRecyclerView rvProduct;
    private LinearLayout llHistoryEmptyView;

    public static CartFragment newInstance() {
        Bundle args = new Bundle();
        CartFragment fragment = new CartFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getResId() {
        return R.layout.fragment_cart;
    }

    @Override
    public void initializeUIComponents(View v) {
        rvProduct = (EmptyRecyclerView) v.findViewById(R.id.rv_product);
        llHistoryEmptyView = (LinearLayout) v.findViewById(R.id.ll_cart_empty_view);
    }

    @Override
    public void updateProductList(List<Product> listOfProducts) {

    }

    @Override
    public void switchToProductScreen() {
        ProductCartFragment parentFragment = (ProductCartFragment) getParentFragment();

    }
}
