package com.example.productexpo.modules.home.product_cart.cart;

import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.example.productexpo.R;
import com.example.productexpo.customviews.EmptyRecyclerView;

/**
 * Created on 9/17/2017.
 */

public class CartPresenterImpl implements CartPresenter, View.OnClickListener {
    CartView cartView;
    EmptyRecyclerView recyclerView;

    public CartPresenterImpl(CartView cartView) {
        this.cartView = cartView;
    }

    @Override
    public void handleEmptyRecyclerView(EmptyRecyclerView recyclerView, View emptyView) {
        this.recyclerView = recyclerView;
        this.recyclerView.setEmptyView(emptyView);

        //todo set to 3 on landscape
        this.recyclerView.setLayoutManager(new GridLayoutManager(cartView.getViewContext(), 2));

        emptyView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_cart_empty_view:
                cartView.switchToProductScreen();
                break;
        }
    }
}
