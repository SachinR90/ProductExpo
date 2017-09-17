package com.example.productexpo.modules.product_details;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.productexpo.R;
import com.example.productexpo.entities.Product;
import com.example.productexpo.modules.base.fragment.BaseFragment;

/**
 * Created on 9/17/2017.
 */

public class ProductDetailsFragment extends BaseFragment implements ProductDetailsView {
    private final static String KEY_PRODUCT = "PRODUCT";
    /**
     * this holds Product Fragment and Cart Fragment
     */
    private ViewPager vpProductDetails;

    private ProductDetailsPresenter presenter;
    private Product product;

    /**
     * Creates a  new instance of this fragment using newInstance() method, Instead of standard constructor.
     * <br> If Android decides to recreate your Fragment later, it's going to call the no-argument constructor of our fragment.
     * <br> So overloading the constructor is not a solution.
     *
     * @return instance of the ProductCartFragment
     */
    public static ProductDetailsFragment newInstance(Product product) {
        Bundle args = new Bundle();
        ProductDetailsFragment fragment = new ProductDetailsFragment();
        args.putParcelable(KEY_PRODUCT, product);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null) {
            product = arguments.getParcelable(KEY_PRODUCT);
        }
        presenter = new ProductDetailsPresenterImpl(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i("CartProduct", "OnCreateView");
        View v = super.onCreateView(inflater, container, savedInstanceState);
        presenter.restoreInstance(savedInstanceState);
        return v;
    }

    @Override
    public int getResId() {
        return R.layout.fragment_product_details;
    }

    @Override
    public void initializeUIComponents(View v) {
        vpProductDetails = (ViewPager) v.findViewById(R.id.vp_product_details);
        View tvNoDataFound = v.findViewById(R.id.tv_no_data_found);
        presenter.handlerViewPager(vpProductDetails,tvNoDataFound);
        if (product != null) {
            presenter.updateViewPager(product);
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (presenter != null) {
            presenter.onConfigChanged(newConfig);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        presenter.saveInstance(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void updateContent(Product product) {
        if (product != null && product.getProductGallery() != null) {
            presenter.updateViewPager(product);
        }
    }
}
