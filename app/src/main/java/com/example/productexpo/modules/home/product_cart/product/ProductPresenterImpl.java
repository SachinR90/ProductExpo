package com.example.productexpo.modules.home.product_cart.product;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.example.productexpo.R;
import com.example.productexpo.customviews.EmptyRecyclerView;
import com.example.productexpo.customviews.ItemClickListenerRV;
import com.example.productexpo.data.Preferences;
import com.example.productexpo.entities.Product;
import com.example.productexpo.entities.ProductResponse;
import com.example.productexpo.utils.UIUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 9/17/2017.
 */

public class ProductPresenterImpl implements ProductPresenter, SwipeRefreshLayout.OnRefreshListener, ItemClickListenerRV {
    ProductView productView;
    EmptyRecyclerView recyclerView;
    SwipeRefreshLayout mSwipeRefreshLayout;

    public ProductPresenterImpl(ProductView productView) {
        this.productView = productView;
    }

    @Override
    public void handleEmptyRecyclerView(EmptyRecyclerView recyclerView, View emptyView, SwipeRefreshLayout mSwipeRefreshLayout) {
        this.recyclerView = recyclerView;
        this.recyclerView.setEmptyView(emptyView);
        this.mSwipeRefreshLayout = mSwipeRefreshLayout;
        // set swipe refresh
        mSwipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(productView.getViewContext(), R.color.colorAccent));
        mSwipeRefreshLayout.setOnRefreshListener(this);
        //set empty adapter
        RVAdapterProductList rvAdapterProductList = new RVAdapterProductList(productView.getViewContext(), new ArrayList<Product>());
        rvAdapterProductList.setClickListener(this);
        this.recyclerView.setAdapter(rvAdapterProductList);

        int count;
        if (!UIUtils.isTablet(productView.getViewContext()) && UIUtils.getDeviceOrientation(productView.getViewContext()) == Configuration.ORIENTATION_LANDSCAPE) {
            count = 3;
        } else {
            count = 2;
        }
        this.recyclerView.setLayoutManager(new GridLayoutManager(productView.getViewContext(), count, GridLayoutManager.VERTICAL, false));
    }

    @Override
    public void requestFocusOnEmptyView() {
        recyclerView.getEmptyView().requestFocus();
    }

    @Override
    public void updateProductList(List<Product> productList) {
        if (this.recyclerView != null) {
            this.mSwipeRefreshLayout.setRefreshing(false);
            RVAdapterProductList adapter = (RVAdapterProductList) this.recyclerView.getAdapter();
            adapter.setClickListener(this);
            adapter.replaceList(productList);
        }
    }

    @Override
    public void restoreInstance(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            List<Product> productList = savedInstanceState.getParcelableArrayList(KEY_PRODUCT_LIST);
            if (productList == null) {
                productList = new ArrayList<>(0);
            }
            updateProductList(productList);
        }
    }

    @Override
    public void saveInstance(Bundle outState) {
        outState.putParcelableArrayList(KEY_PRODUCT_LIST, getProductList());
    }

    @Override
    public ArrayList<Product> getProductList() {
        RVAdapterProductList adapter = (RVAdapterProductList) this.recyclerView.getAdapter();
        return adapter.getData();
    }

    @Override
    public void addProductToCartList(Product product) {
        Preferences instance = Preferences.getInstance();
        ProductResponse response = instance.getProductResponseFromKey(Preferences.Key.KEY_CART_LIST);
        if (response != null) {
            try {
                response.getProducts().add(product);
                instance.setObjectAsString(Preferences.Key.KEY_CART_LIST, new Gson().toJson(response));
                productView.showToast("Product added to cart successfully");
            } catch (Exception e) {
                productView.showToast("Something went wrong");
            }
        } else {
            response = new ProductResponse();
            response.setProducts(new ArrayList<Product>());
            response.getProducts().add(product);
            instance.setObjectAsString(Preferences.Key.KEY_CART_LIST, new Gson().toJson(response));
            productView.showToast("Product added to cart successfully");
        }
    }

    @Override
    public void showErrorMessage(int errorCode, String message) {

    }

    @Override
    public void onConfigChanged(Configuration configuration) {
        int count;
        if (!UIUtils.isTablet(productView.getViewContext()) && UIUtils.getDeviceOrientation(productView.getViewContext()) == Configuration.ORIENTATION_LANDSCAPE) {
            count = 3;
        } else {
            count = 2;
        }
        this.recyclerView.setLayoutManager(new GridLayoutManager(productView.getViewContext(), count, GridLayoutManager.VERTICAL, false));
    }

    @Override
    public void onRefresh() {
        this.mSwipeRefreshLayout.setRefreshing(false);
        productView.refreshList();
    }

    @Override
    public void onItemClick(View v, int pos, Object data, Bundle bundle) {
        Product product = (Product) data;
        switch (v.getId()) {
            case R.id.ll_home_grid_parent:
                productView.showProductGallery(product);
                break;
            case R.id.btn_add_cart:
                addProductToCartList(product);
                break;
        }
    }
}
