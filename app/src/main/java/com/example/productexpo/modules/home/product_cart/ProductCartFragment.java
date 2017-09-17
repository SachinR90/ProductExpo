package com.example.productexpo.modules.home.product_cart;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

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

        //initialize the presenter
        presenter = new ProductCartPresenterImpl(this);
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

        //select product tab by default
        presenter.selectProductTab();
    }

    public void switchToProductTab() {
        presenter.selectProductTab();
    }
}
