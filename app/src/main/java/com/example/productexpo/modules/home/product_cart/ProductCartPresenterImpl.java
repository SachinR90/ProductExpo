package com.example.productexpo.modules.home.product_cart;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.example.productexpo.R;
import com.example.productexpo.entities.Product;
import com.example.productexpo.entities.ProductResponse;
import com.example.productexpo.modules.base.fragment.BaseFragmentView;
import com.example.productexpo.modules.home.product_cart.cart.CartFragment;
import com.example.productexpo.modules.home.product_cart.product.ProductFragment;

import java.util.List;

/**
 * Created on 9/17/2017.
 */

public class ProductCartPresenterImpl implements ProductCartPresenter {
    BaseFragmentView productCartView;
    private TabLayout tlProductCart;
    private ViewPager vpProductCart;

    public ProductCartPresenterImpl(BaseFragmentView productCartView) {
        this.productCartView = productCartView;
    }


    @Override
    public void handleViewPager(ViewPager viewPager) {
        this.vpProductCart = viewPager;
        VPAdapterProductCart adapterProductCart = new VPAdapterProductCart(productCartView.getChildManagerForFragment());
        adapterProductCart.add(0, ProductFragment.newInstance(), productCartView.getViewContext().getString(R.string.str_product_list));
        adapterProductCart.add(0, CartFragment.newInstance(), productCartView.getViewContext().getString(R.string.str_cart_list));
        this.vpProductCart.setAdapter(adapterProductCart);
        this.vpProductCart.setOffscreenPageLimit(2);

    }

    @Override
    public void handleTabLayout(TabLayout tabLayout) {
        this.tlProductCart = tabLayout;
        this.tlProductCart.setupWithViewPager(this.vpProductCart, true);
        this.tlProductCart.clearOnTabSelectedListeners();
        this.tlProductCart.addOnTabSelectedListener(new TabLayoutSelectionListener());
        this.tlProductCart.getTabAt(0).setIcon(R.drawable.vc_product_list);
        this.tlProductCart.getTabAt(1).setIcon(R.drawable.vc_shopping_cart);
    }

    @Override
    public void selectProductTab() {
        this.tlProductCart.getTabAt(0).select();
    }

    @Override
    public void onGetProductsReceived(ProductResponse productResponse) {

    }

    @Override
    public void fetchCartProducts() {

    }

    @Override
    public void onCartProductReceived(List<Product> products) {

    }

    @Override
    public void fetchProducts() {

    }

    private class TabLayoutSelectionListener implements TabLayout.OnTabSelectedListener {

        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            try {
                VPAdapterProductCart adapter = (VPAdapterProductCart) vpProductCart.getAdapter();

                if (tab.getPosition() == 0) {
                    //get products

                } else if (tab.getPosition() == 1) {
                    //get cart products from the shared preference
                }
            } catch (Exception ignored) {

            }
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {
            try {
                VPAdapterProductCart adapter = (VPAdapterProductCart) vpProductCart.getAdapter();

                if (tab.getPosition() == 0) {
                    //get products
                } else if (tab.getPosition() == 1) {
                    //get cart products from the shared preference
                }
            } catch (Exception ignored) {

            }
        }
    }
}
