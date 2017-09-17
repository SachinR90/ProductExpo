package com.example.productexpo.modules.home.product_cart.cart;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.productexpo.R;
import com.example.productexpo.customviews.EmptyRecyclerView;
import com.example.productexpo.customviews.ItemClickListenerRV;
import com.example.productexpo.data.Preferences;
import com.example.productexpo.entities.Product;
import com.example.productexpo.entities.ProductResponse;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 9/17/2017.
 */

public class CartPresenterImpl implements CartPresenter, View.OnClickListener,ItemClickListenerRV {

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
            calculatePrice();

        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            calculatePrice();
        }
    };

    public CartPresenterImpl(CartView cartView) {
        this.cartView = cartView;
    }

    private void calculatePrice() {
        RVAdapterCartList adapter = (RVAdapterCartList) recyclerView.getAdapter();
        if (adapter.getItemCount() > 0) {
            priceTextView.setVisibility(View.VISIBLE);
            ArrayList<Product> data = adapter.getData();
            int total=0;
            for(int i=0;i<data.size();i++) {
                try {
                    int price = Integer.parseInt(data.get(i).getPrice());
                    total += price;
                } catch (Exception ex) {
                    break;
                }
            }
            priceTextView.setText("Total : \u20B9" + total);
        } else {
            priceTextView.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void handleEmptyRecyclerView(EmptyRecyclerView recyclerView, View emptyView) {
        this.recyclerView = recyclerView;
        this.recyclerView.setEmptyView(emptyView);
        this.recyclerView.setLayoutManager(new GridLayoutManager(cartView.getViewContext(), 1));
        RVAdapterCartList rvAdapterCartList = new RVAdapterCartList(cartView.getViewContext(), new ArrayList<Product>(0));
        rvAdapterCartList.setClickListener(this);
        rvAdapterCartList.registerAdapterDataObserver(cartListCountObeserVer);
        this.recyclerView.setAdapter(rvAdapterCartList);
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
    public void updateProductList(List<Product> productList) {
        if (this.recyclerView != null) {
            RVAdapterCartList adapter = (RVAdapterCartList) this.recyclerView.getAdapter();
            adapter.replaceList(productList);
        }
    }

    @Override
    public void restoreInstance(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            List<Product> productList = savedInstanceState.getParcelableArrayList(KEY_CART_LIST);
            if (productList == null) {
                productList = new ArrayList<>(0);
            }
            updateProductList(productList);
        }
    }

    @Override
    public void saveInstance(Bundle outState) {
        outState.putParcelableArrayList(KEY_CART_LIST, getProductList());
    }

    @Override
    public ArrayList<Product> getProductList() {
        RVAdapterCartList adapter = (RVAdapterCartList) this.recyclerView.getAdapter();
        return adapter.getData();
    }

    @Override
    public void removeProductFromCartList(int position, Product product) {
        Preferences instance = Preferences.getInstance();
        ProductResponse response = instance.getProductResponseFromKey(Preferences.Key.KEY_CART_LIST);
        if (response != null) {
            try {
                for (int i = 0; i < response.getProducts().size(); i++) {
                    if (i == position && response.getProducts().get(i).getProductName()
                            .equalsIgnoreCase(product.getProductName())) {
                        RVAdapterCartList adapter = (RVAdapterCartList) this.recyclerView.getAdapter();
                        adapter.removeProduct(position);
                        response.getProducts().remove(response.getProducts().get(i));
                        break;
                    }
                }
                instance.setObjectAsString(Preferences.Key.KEY_CART_LIST, new Gson().toJson(response));
            } catch (Exception e) {

            }
        }
    }

    @Override
    public void refreshCartList() {
        Preferences instance = Preferences.getInstance();
        String objectAsString = instance.getObjectAsString(Preferences.Key.KEY_CART_LIST);
        try {
            ProductResponse productResponse = new Gson().fromJson(objectAsString, ProductResponse.class);
            updateProductList(productResponse.getProducts());
        } catch (Exception ex) {
            updateProductList(new ArrayList<Product>());
        }
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

    @Override
    public void onConfigChanged(Configuration configuration) {

    }

    @Override
    public void onItemClick(View v, int pos, Object data, Bundle bundle) {
        switch (v.getId()) {
            case R.id.btn_remove_cart:
                removeProductFromCartList(pos, (Product) data);
                break;
            case R.id.btn_call_vendor:
                cartView.callVendor(((Product) data).getPhoneNumber());
                break;
            case R.id.ll_holder:
                break;
        }
    }
}
