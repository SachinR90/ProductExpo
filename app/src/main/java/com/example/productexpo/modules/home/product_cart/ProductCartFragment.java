package com.example.productexpo.modules.home.product_cart;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.productexpo.R;
import com.example.productexpo.modules.base.fragment.BaseFragment;
import com.example.productexpo.modules.base.fragment.BaseFragmentView;

/**
 * Created on 9/17/2017.
 */

public class ProductCartFragment extends BaseFragment implements BaseFragmentView {
    /**
     * contains two tabs<br>
     * <li> Product</li>
     * <li> Cart</li>
     */
    private TabLayout tlProductCart;

    /**
     * this holds Product Fragment and Cart Fragment
     */
    private ViewPager vpProductCart;

    private ProductCartPresenter presenter;

    /**
     * Creates a  new instance of this fragment using newInstance() method, Instead of standard constructor.
     * <br> If Android decides to recreate your Fragment later, it's going to call the no-argument constructor of our fragment.
     * <br> So overloading the constructor is not a solution.
     *
     * @return instance of the ProductCartFragment
     */
    public static ProductCartFragment newInstance() {
        Bundle args = new Bundle();
        ProductCartFragment fragment = new ProductCartFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new ProductCartPresenterImpl(this);
        Log.i("CartProduct", "OnCreate");
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
        return R.layout.fragment_product_cart;
    }

    @Override
    public void initializeUIComponents(View v) {
        //initialize tab layout
        tlProductCart = (TabLayout) v.findViewById(R.id.tl_product_cart);
        vpProductCart = (ViewPager) v.findViewById(R.id.vp_product_cart);

        // make presenter to perform business logic on these two views
        presenter.handleViewPager(vpProductCart);
        presenter.handleTabLayout(tlProductCart);
    }

    public void switchToProductTab() {
        presenter.selectProductTab();
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
}
