package com.example.productexpo.modules.home.product_cart.product;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.example.productexpo.R;
import com.example.productexpo.customviews.EmptyRecyclerView;
import com.example.productexpo.entities.Product;
import com.example.productexpo.modules.base.fragment.BaseFragment;

import java.util.List;

/**
 * Created on 9/17/2017.
 */

public class ProductFragment extends BaseFragment implements ProductView {
    ProductPresenter presenter;
    private EmptyRecyclerView rvProduct;
    private TextView tvNoDataFound;

    public static ProductFragment newInstance() {
        Bundle args = new Bundle();
        ProductFragment fragment = new ProductFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new ProductPresenterImpl(this);
    }

    @Override
    public int getResId() {
        return R.layout.fragment_product;
    }

    @Override
    public void initializeUIComponents(View v) {
        rvProduct = (EmptyRecyclerView) v.findViewById(R.id.rv_product);
        tvNoDataFound = (TextView) v.findViewById(R.id.tv_no_data_found);

        //make presenter handle the empty textview and RecyclerView
        presenter.handleEmptyRecyclerView(rvProduct, tvNoDataFound);
    }

    @Override
    public void updateProductList(List<Product> listOfProducts) {

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && presenter!=null) {
            presenter.requestFocusOnEmptyView();
        }
    }
}
