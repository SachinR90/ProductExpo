package com.example.productexpo.modules.home.product_cart.cart;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.productexpo.R;
import com.example.productexpo.customviews.EmptyRecyclerView;

/**
 * Created on 9/17/2017.
 */

public class CartPresenterImpl implements CartPresenter, View.OnClickListener {

    CartView cartView;
    EmptyRecyclerView recyclerView;
    AppCompatTextView priceTextView;
    final private RecyclerView.AdapterDataObserver cartListCountObeserVer = new RecyclerView.AdapterDataObserver() {
        @Override
        public void onChanged() {
            calculatePrice();
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
        }
    };

    public CartPresenterImpl(CartView cartView) {
        this.cartView = cartView;
    }

    private void calculatePrice() {
        if (recyclerView.getAdapter().getItemCount() > 0) {
            priceTextView.setVisibility(View.VISIBLE);
        } else {
            priceTextView.setVisibility(View.INVISIBLE);
        }
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
    public void requestFocusOnEmptyView() {
        recyclerView.getEmptyView().requestFocus();
    }

    @Override
    public void handlePriceText(AppCompatTextView tvTotalPrice) {
        this.priceTextView = tvTotalPrice;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_cart_empty_view:
                cartView.switchToProductScreen();
                break;
        }
    }

    @Override
    public void showErrorMessage(int errorCode, String message) {

    }
}
